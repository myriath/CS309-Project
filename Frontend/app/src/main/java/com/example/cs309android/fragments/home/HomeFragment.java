package com.example.cs309android.fragments.home;

import static com.example.cs309android.util.Constants.PARCEL_RECIPE;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
    HomeItemAdapter adapter;
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

        adapter = new HomeItemAdapter(this.getActivity(), recipes);
        listView = view.findViewById(R.id.feed_item);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Recipe selectedItem = (Recipe) parent.getItemAtPosition(position);
            Intent i = new Intent(getActivity(), RecipeDetailsActivity.class);
            i.putExtra(PARCEL_RECIPE, selectedItem);
            startActivity(i);
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
