package com.example.cs309android.fragments.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.recipe.AddRecipeActivity;
import com.example.cs309android.activities.recipe.RecipeDetailsActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.adapters.HomeItemAdapter;
import com.example.cs309android.models.api.models.SimpleRecipeItem;
import com.example.cs309android.models.api.request.recipes.GetUserRecipesRequest;
import com.example.cs309android.models.api.response.recipes.GetRecipeListResponse;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipesFragment extends BaseFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static ArrayList<SimpleRecipeItem> recipes;

    private String mParam1;
    private String mParam2;

    public RecipesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipesFragment.
     */

    public static RecipesFragment newInstance(String param1, String param2) {
        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        recipes = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        // Refresh the list of recipes
        refreshList(view);

        //Add button adds recipe to db
        FloatingActionButton addRecipe = view.findViewById(R.id.add_recipe);
        addRecipe.setOnClickListener(view1 -> {
            Intent myIntent = new Intent(view.getContext(), AddRecipeActivity.class);
            startActivity(myIntent);
            refreshList(view);
        });

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.recipe_frame_layout), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });

        return view;
    }

    public void refreshList(View view) {
        new GetUserRecipesRequest(((GlobalClass) requireActivity().getApplicationContext()).getToken()).request(response -> {
            try {
                System.out.println(response.toString(3));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GetRecipeListResponse recipeResponse = Util.objFromJson(response, GetRecipeListResponse.class);

            if (recipeResponse == null) {
                Toaster.toastShort("Error getting recipes", requireContext());
                return;
            }

            SimpleRecipeItem[] newItems = recipeResponse.getRecipes();
            recipes = new ArrayList<>();
            recipes.addAll(Arrays.asList(newItems));
        }, requireContext());

        TextView emptyText = view.findViewById(R.id.emptyText);
        if(recipes.isEmpty()){
            emptyText.setVisibility(View.VISIBLE);
        }
        else {
            emptyText.setVisibility(View.INVISIBLE);
        }

        HomeItemAdapter adapter = new HomeItemAdapter(this.getActivity(), recipes);
        ListView listView = view.findViewById(R.id.recipes_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SimpleRecipeItem selectedItem = (SimpleRecipeItem) parent.getItemAtPosition(position);
                Intent i = new Intent(getActivity(), RecipeDetailsActivity.class);
                i.putExtra("HomeFragment.recipe", selectedItem);
                startActivity(i);
            }
        });
    }

}
