package com.example.cs309android.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.viewpager2.widget.ViewPager2;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.HomeNutritionCardModel;
import com.example.cs309android.models.adapters.RecipeListAdapter;
import com.example.cs309android.models.adapters.HomeNutritionAdapter;
import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.request.home.GetUserFeedRequest;
import com.example.cs309android.models.api.request.recipes.GetRecipeImageRequest;
import com.example.cs309android.models.api.response.recipes.GetRecipeListResponse;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.example.cs309android.views.HomeRecipeView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONException;

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

//        THIS IS JUST TEST DATA
//         item = new SimpleRecipeItem(1, "String Cheese", "Cook and stuff");
        recipes = new ArrayList<>();
//        recipes.add(item);
//        item = new SimpleRecipeItem(2, "Cantelope", "Bake and stuff asdf asdf jkl asdf als;jdk;flasdfjkl; as;ldf asdf  asdf asdf asdf asdf asdf asdf asdfsg sdfgs dg dgs dfgsdf g dfg sdf g sdfg dsfg sdg sdfg s sdfgsdfgsdfgsdf gsdfg s s fgsdfg sdfgsdfg sdfg sdfg dsg asdfas ddsf asdf asfd asdfasdf asdf a fas dfasd asdf asdf");
//        recipes.add(item);
//        item = new SimpleRecipeItem(3, "Meat", "Heat and stuff");
//        recipes.add(item);
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
                new HomeNutritionCardModel("Calories", null),
                new HomeNutritionCardModel("Carbohydrates", null),
                new HomeNutritionCardModel("Protein", null),
                new HomeNutritionCardModel("Fat", null)
        };

        HomeNutritionAdapter adapter1 = new HomeNutritionAdapter(models);
        nutritionPager.setAdapter(adapter1);

        new TabLayoutMediator(nutritionTabs, nutritionPager, (tab, position) -> {}).attach();

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
            if (newItems != null) {
                recipes.addAll(Arrays.asList(newItems));
            } else {
                System.out.println("HELLOOOOOOOO");
            }

        }, requireContext());

        adapter = new RecipeListAdapter(this.getActivity(), recipes);
        LinearLayout layout = view.findViewById(R.id.feed);

        HomeRecipeView view1 = new HomeRecipeView(requireContext());
        view1.initView("Test Recipe", "Test Description\nDescription\nDescription", view3 -> {
            // TODO: Load recipe details page
        });
        new GetRecipeImageRequest("0").request((ImageView) view1.findViewById(R.id.recipeImage), getContext());
        layout.addView(view1);

        HomeRecipeView view2 = new HomeRecipeView(requireContext());
        view2.initView("Test Recipe", "Test Description", view3 -> {
            // TODO: Load recipe details page
        });
        new GetRecipeImageRequest("0").request((ImageView) view2.findViewById(R.id.recipeImage), getContext());
        layout.addView(view2);


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
