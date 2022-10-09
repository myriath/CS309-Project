package com.example.cs309android.fragments.recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.cs309android.R;
import com.example.cs309android.activities.FoodSearchActivity;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.gson.models.SimpleRecipeItem;
import com.example.cs309android.models.gson.request.recipes.AddRecipe;
import com.example.cs309android.models.gson.request.recipes.AddRecipeRequest;
import com.example.cs309android.models.gson.request.recipes.GetRecipeDetailsRequest;
import com.example.cs309android.models.gson.response.GenericResponse;
import com.example.cs309android.models.gson.response.recipes.GetRecipeDetailsResponse;
import com.example.cs309android.models.gson.response.shopping.GetListResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Util;

import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipesFragment extends BaseFragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.recipe_frame_layout), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });

        //Search button triggers search by RID
        view.findViewById(R.id.recipe_search_button).setOnClickListener(view1 -> {
            TextView ridInput = view.findViewById(R.id.recipeRidInput);
            TextView recipes = view.findViewById(R.id.recipeName);

            if(!(ridInput.getText().toString().matches("[0-9]+"))) {
                recipes.setText("Invalid Search");
                return;
            }
            int ridValue = Integer.parseInt(ridInput.getText().toString());
            new GetRecipeDetailsRequest(ridValue, MainActivity.AUTH_MODEL).request(response -> {
                try {
                    System.out.print(response.toString(4));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                GetRecipeDetailsResponse recipeResponse = Util.objFromJson(response, GetRecipeDetailsResponse.class);


                if(recipeResponse.getResult() == Constants.RESULT_OK) {
                    String stuff = recipeResponse.getRecipe().getRecipeName() + recipeResponse.getRecipe().getSteps();
                    recipes.setText(stuff);
                }
                else {
                    recipes.setText("Invalid RID");
                }
            },getContext());
        });

//        //Add button adds recipe to db
//        view.findViewById(R.id.add_recipe).setOnClickListener(view1 -> {
//            TextView RnameInput = view.findViewById(R.id.recipeNameInput);
//
//            new AddRecipe(MainActivity.AUTH_MODEL.getUsername(), RnameInput.getText().toString(), "put this in that and do stuff").request(response -> {
//                try {
//                    System.out.print(response.toString(4));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                GenericResponse recipeResponse = Util.objFromJson(response, GenericResponse.class);
//                TextView recipes = view.findViewById(R.id.recipeName);
//                if(recipeResponse.getResult() == Constants.RESULT_RECIPE_CREATED) {
//                    recipes.setText("Recipe Created");
//                }
//                else if(recipeResponse.getResult() == Constants.RESULT_ERROR_RID_TAKEN) {
//                    recipes.setText("That RID is taken");
//                }
//                else {
//                    recipes.setText("Something went wrong");
//                }
//            },getContext());
//        });
        return view;
    }
}
