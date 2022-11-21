package com.example.cs309android.fragments.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.recipe.RecipeDetailsActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.adapters.HomeItemAdapter;
import com.example.cs309android.models.adapters.RecipePageAdapter;
import com.example.cs309android.models.api.models.Ingredient;
import com.example.cs309android.models.api.models.Instruction;
import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.models.api.request.recipes.GetUserRecipesRequest;
import com.example.cs309android.models.api.response.recipes.GetRecipeListResponse;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Fragment to display the Recipe feed
 *
 * @author Travis Massner
 */
public class RecipesFragment extends BaseFragment {
    /**
     * Recipe list to display
     */
    private static ArrayList<Recipe> recipes;

    /**
     * Runs when the fragment is created
     *
     * @param savedInstanceState Saved state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipes = new ArrayList<>();
    }

    /**
     * Runs when the fragment view is created
     *
     * @param inflater           Inflates the layout
     * @param container          Parent view group
     * @param savedInstanceState Saved state
     * @return Inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        // TODO: view pager is used to handle the recipe views now, not refreshList()
        TabLayout tabs = view.findViewById(R.id.tabs);
        ViewPager2 pager = view.findViewById(R.id.pager);
        pager.setAdapter(new RecipePageAdapter());
        new TabLayoutMediator(tabs, pager, (tab, position) -> {
            switch (position) {
                case 0: {
                    tab.setText(R.string.popular);
                    break;
                }
                case 1: {
                    tab.setText(R.string.newText);
                    break;
                }
                case 2: {
                    tab.setText(R.string.explore);
                    break;
                }
            }
        }).attach();

        // Refresh the list of recipes
//        refreshList(view);

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.recipe_frame_layout), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });

        return view;
    }

//    /**
//     * Refreshes the list of recipes
//     *
//     * @param view View to get children of
//     */
//    public void refreshList(View view) {
//        new GetUserRecipesRequest(((GlobalClass) requireActivity().getApplicationContext()).getToken()).request(response -> {
//            try {
//                System.out.println(response.toString(3));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            GetRecipeListResponse recipeResponse = Util.objFromJson(response, GetRecipeListResponse.class);
//
//            if (recipeResponse == null) {
//                Toaster.toastShort("Error getting recipes", requireContext());
//                return;
//            }
//
//            Recipe[] newItems = recipeResponse.getRecipes();
//            recipes = new ArrayList<>();
//            recipes.addAll(Arrays.asList(newItems));
//        }, requireContext());
//
//        TextView emptyText = view.findViewById(R.id.emptyText);
//
//        // TODO: Temporary data until get list works
//        recipes = new ArrayList<>();
//        recipes.add(new Recipe(0, "Apples", "Apples for apples", new Ingredient[]{
//                new Ingredient(new SimpleFoodItem("apple", ":)"), 1, "gram"),
//                new Ingredient(new SimpleFoodItem("sinnamon", ":("), 123, "pound")
//        }, new Instruction[]{
//                new Instruction(1, "Gimbo"),
//                new Instruction(2, "juicy juicer")
//        }, "apple"));
//
//        recipes.add(new Recipe(1, "Apples2", "Apples for apples", new Ingredient[]{
//                new Ingredient(new SimpleFoodItem("apple", ":)"), 1, "gram"),
//                new Ingredient(new SimpleFoodItem("sinnamon", ":("), 123, "pound")
//        }, new Instruction[]{
//                new Instruction(1, "Gimbo"),
//                new Instruction(2, "juicy juicer")
//        }, "papajohn"));
//
//        if (recipes.isEmpty()) {
//            emptyText.setVisibility(View.VISIBLE);
//        } else {
//            emptyText.setVisibility(View.INVISIBLE);
//        }
//
//        HomeItemAdapter adapter = new HomeItemAdapter(this.getActivity(), recipes);
//        ListView listView = view.findViewById(R.id.recipes_list);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener((parent, view1, position, id) -> {
//            Recipe selectedItem = (Recipe) parent.getItemAtPosition(position);
//            Intent i = new Intent(getActivity(), RecipeDetailsActivity.class);
//            i.putExtra("HomeFragment.recipe", selectedItem);
//            startActivity(i);
//        });
//    }
}
