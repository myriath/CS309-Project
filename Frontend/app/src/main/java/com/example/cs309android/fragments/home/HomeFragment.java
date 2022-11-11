package com.example.cs309android.fragments.home;

import static com.example.cs309android.util.Constants.PARCEL_RECIPE;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.recipe.RecipeDetailsActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.adapters.HomeItemAdapter;
import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.request.home.GetUserFeedRequest;
import com.example.cs309android.models.api.response.recipes.GetRecipeListResponse;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<Recipe> recipes;
    HomeItemAdapter adapter;
    ListView listView;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        //THIS IS JUST TEST DATA
//        SimpleRecipeItem item = new SimpleRecipeItem(1, "String Cheese", "Cook and stuff");
//        recipes = new ArrayList<>();
//        recipes.add(item);
//        item = new SimpleRecipeItem(2, "Cantelope", "Bake and stuff asdf asdf jkl asdf als;jdk;flasdfjkl; as;ldf asdf  asdf asdf asdf asdf asdf asdf asdfsg sdfgs dg dgs dfgsdf g dfg sdf g sdfg dsfg sdg sdfg s sdfgsdfgsdfgsdf gsdfg s s fgsdfg sdfgsdfg sdfg sdfg dsg asdfas ddsf asdf asfd asdfasdf asdf a fas dfasd asdf asdf");
//        recipes.add(item);
//        item = new SimpleRecipeItem(3, "Meat", "Heat and stuff");
//        recipes.add(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        new GetUserFeedRequest(((GlobalClass) requireActivity().getApplicationContext()).getToken()).request(response -> {
            try {
                System.out.print(response.toString(3));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GetRecipeListResponse recipeResponse = Util.objFromJson(response, GetRecipeListResponse.class);

            if (recipeResponse == null) {
                Toaster.toastShort("Error getting recipes", requireContext());
                return;
            }

            Recipe[] newItems = recipeResponse.getRecipes();
            recipes = new ArrayList<>();
            if(newItems != null) {
                recipes.addAll(Arrays.asList(newItems));
            }
            else {
                System.out.println("HELLOOOOOOOO");
            }

        }, requireContext());

        adapter = new HomeItemAdapter(this.getActivity(), recipes);
        listView = view.findViewById(R.id.feed_item);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe selectedItem = (Recipe) parent.getItemAtPosition(position);
                Intent i = new Intent(getActivity(), RecipeDetailsActivity.class);
                i.putExtra(PARCEL_RECIPE, selectedItem);
                startActivity(i);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.home_feed), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            mlp.topMargin = insets.top;
            insets = windowInsets.getInsets(WindowInsetsCompat.Type.ime());
            mlp.bottomMargin = insets.bottom;
            return WindowInsetsCompat.CONSUMED;
        });

        return view;
    }

}