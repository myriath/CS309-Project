package com.example.cs309android.fragments.home;

import static com.example.cs309android.util.Constants.BREAKFAST_LOG;
import static com.example.cs309android.util.Constants.DINNER_LOG;
import static com.example.cs309android.util.Constants.LUNCH_LOG;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_RECIPE;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.viewpager2.widget.ViewPager2;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.activities.recipe.RecipeDetailsActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.HomeNutritionCardModel;
import com.example.cs309android.models.adapters.HomeNutritionAdapter;
import com.example.cs309android.models.adapters.RecipeListAdapter;
import com.example.cs309android.models.api.models.Comment;
import com.example.cs309android.models.api.models.Ingredient;
import com.example.cs309android.models.api.models.Instruction;
import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.models.api.models.User;
import com.example.cs309android.models.api.request.home.GetUserFeedRequest;
import com.example.cs309android.models.api.response.recipes.GetRecipesResponse;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.example.cs309android.views.HomeRecipeView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Home fragment for displaying the homepage
 *
 * @author Travis Massner
 */
public class HomeFragment extends BaseFragment {
    /**
     * List of recipes to display
     */
    ArrayList<Recipe> recipes;
    /**
     * Adapter for the list view
     */
    RecipeListAdapter adapter;
    /**
     * List view to display recipes
     */
    ListView listView;

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
     * Runs when the view is created
     *
     * @param inflater           Inflates the fragment view
     * @param container          Parent of the fragment
     * @param savedInstanceState Saved state
     * @return Inflated view of the fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ViewPager2 nutritionPager = view.findViewById(R.id.nutritionPager);
        TabLayout nutritionTabs = view.findViewById(R.id.nutritionTabs);

        HomeNutritionCardModel[] models = new HomeNutritionCardModel[]{
                new HomeNutritionCardModel("Calories", R.drawable.calories),
                new HomeNutritionCardModel("Carbohydrates", R.drawable.carb),
                new HomeNutritionCardModel("Protein", R.drawable.meat),
                new HomeNutritionCardModel("Fat", R.drawable.fat)
        };
        int totalCalories = 0;
        int totalFat = 0;
        int totalCarbs = 0;
        int totalProtein = 0;

        for (SimpleFoodItem item : MainActivity.getLog(BREAKFAST_LOG)) {
            totalCalories += item.getCalories();
            totalFat += item.getFat();
            totalCarbs += item.getCarbs();
            totalProtein += item.getProtein();
        }
        for (SimpleFoodItem item : MainActivity.getLog(LUNCH_LOG)) {
            totalCalories += item.getCalories();
            totalFat += item.getFat();
            totalCarbs += item.getCarbs();
            totalProtein += item.getProtein();
        }
        for (SimpleFoodItem item : MainActivity.getLog(DINNER_LOG)) {
            totalCalories += item.getCalories();
            totalFat += item.getFat();
            totalCarbs += item.getCarbs();
            totalProtein += item.getProtein();
        }
        models[0].setLimit(1600);
        models[1].setLimit(200);
        models[2].setLimit(80);
        models[3].setLimit(53);

        models[0].setAmount(totalCalories);
        models[0].setAmount(943);
        models[1].setAmount(totalCarbs);
        models[2].setAmount(totalProtein);
        models[3].setAmount(totalFat);

        HomeNutritionAdapter adapter1 = new HomeNutritionAdapter(models);
        nutritionPager.setAdapter(adapter1);

        new TabLayoutMediator(nutritionTabs, nutritionPager, (tab, position) -> {}).attach();

        new GetUserFeedRequest(((GlobalClass) requireActivity().getApplicationContext()).getToken()).request(response -> {
            GetRecipesResponse recipeResponse = Util.objFromJson(response, GetRecipesResponse.class);

            if (recipeResponse == null) {
                Toaster.toastShort("Error getting recipes", requireContext());
                return;
            }

            Recipe[] newItems = recipeResponse.getRecipes();
            recipes = new ArrayList<>();
            if (newItems != null) {
                recipes.addAll(Arrays.asList(newItems));
            } else {
                System.out.println("HELLOOOOOOOO");
            }

        }, requireContext());

//        adapter = new RecipeListAdapter(this.getActivity(), recipes, recipe -> {
//            Intent intent = new Intent(getContext(), RecipeDetailsActivity.class);
//            intent.putExtra(PARCEL_RECIPE, recipe);
//            startActivity(intent);
//        });
        LinearLayout layout = view.findViewById(R.id.feed);

        Recipe recipe1 = new Recipe(0, "Grilled cheese", "A simple grilled cheese", new Ingredient[]{
                new Ingredient(new SimpleFoodItem("test", "test"), 1, "mm"),
                new Ingredient(new SimpleFoodItem("test", "test"), 1, "mm"),
                new Ingredient(new SimpleFoodItem("test", "test"), 1, "mm"),
                new Ingredient(new SimpleFoodItem("test", "test"), 1, "mm")
        }, new Instruction[]{
                new Instruction(1, "step"),
                new Instruction(2, "step"),
                new Instruction(3, "step"),
                new Instruction(4, "step")
        }, new User("papajohn", 3, "hola"), new Comment[]{
        });
        Recipe recipe2 = new Recipe(1, "Delicious salad", "The perfect salad recipe", new Ingredient[]{
                new Ingredient(new SimpleFoodItem("test", "test"), 1, "mm"),
                new Ingredient(new SimpleFoodItem("test", "test"), 1, "mm"),
                new Ingredient(new SimpleFoodItem("test", "test"), 1, "mm"),
                new Ingredient(new SimpleFoodItem("test", "test"), 1, "mm")
        }, new Instruction[]{
                new Instruction(1, "step"),
                new Instruction(2, "step"),
                new Instruction(3, "step"),
                new Instruction(4, "step")
        }, new User("papajohn", 3, "hola"), new Comment[]{
        });
        Recipe recipe3 = new Recipe(2, "Tomato soup", "Robust tomato soup for a cold day", new Ingredient[]{
                new Ingredient(new SimpleFoodItem("test", "test"), 1, "mm"),
                new Ingredient(new SimpleFoodItem("test", "test"), 1, "mm"),
                new Ingredient(new SimpleFoodItem("test", "test"), 1, "mm"),
                new Ingredient(new SimpleFoodItem("test", "test"), 1, "mm")
        }, new Instruction[]{
                new Instruction(1, "step"),
                new Instruction(2, "step"),
                new Instruction(3, "step"),
                new Instruction(4, "step")
        }, new User("papajohn", 3, "hola"), new Comment[]{
        });

        HomeRecipeView view1 = new HomeRecipeView(requireContext());
        view1.initView(recipe1, view4 -> {
            Intent intent = new Intent(getContext(), RecipeDetailsActivity.class);
            intent.putExtra(PARCEL_RECIPE, recipe1);
            startActivity(intent);
        });
        layout.addView(view1);
        HomeRecipeView view2 = new HomeRecipeView(requireContext());
        view2.initView(recipe2, view4 -> {
            Intent intent = new Intent(getContext(), RecipeDetailsActivity.class);
            intent.putExtra(PARCEL_RECIPE, recipe2);
            startActivity(intent);
        });
        layout.addView(view2);
        HomeRecipeView view3 = new HomeRecipeView(requireContext());
        view3.initView(recipe3, view4 -> {
            Intent intent = new Intent(getContext(), RecipeDetailsActivity.class);
            intent.putExtra(PARCEL_RECIPE, recipe3);
            startActivity(intent);
        });
//        new GetRecipeImageRequest("0").request((ImageView) view1.findViewById(R.id.recipeImage), getContext());
        layout.addView(view3);

//        HomeRecipeView view2 = new HomeRecipeView(requireContext());
//        view2.initView("Test Recipe", "Test Description", view3 -> {
//            // TODO: Load recipe details page
//        });
//        new GetRecipeImageRequest("0").request((ImageView) view2.findViewById(R.id.recipeImage), getContext());
//        layout.addView(view2);

        // TODO: Write recipe details code (2 steps)
        //       1: Get image
        //       2: Get recipe and open page


//        listView.setOnItemClickListener((parent, view1, position, id) -> {
//            Recipe selectedItem = (Recipe) parent.getItemAtPosition(position);
//            Intent i = new Intent(getActivity(), RecipeDetailsActivity.class);
//            i.putExtra(PARCEL_RECIPE, selectedItem);
//            startActivity(i);
//        });
//        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.home_feed), (v, windowInsets) -> {
//            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
//            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
//            mlp.topMargin = insets.top;
//            insets = windowInsets.getInsets(WindowInsetsCompat.Type.ime());
//            mlp.bottomMargin = insets.bottom;
//            return WindowInsetsCompat.CONSUMED;
//        });

        return view;
    }
}
