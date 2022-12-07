package com.example.cs309android.models.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs309android.R;
import com.example.cs309android.models.api.models.Ingredient;
import com.example.cs309android.models.api.models.Instruction;
import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.request.recipes.GetRecipesTypeRequest;
import com.example.cs309android.models.api.response.recipes.GetRecipeListResponse;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Adapter to use with the TabLayout for the recipe page
 *
 * @author Travis Massner
 * @author Mitch Hudson
 */
public class RecipePageAdapter extends RecyclerView.Adapter<RecipePageAdapter.ViewHolder> {

    private static ArrayList<Recipe> recipes = new ArrayList<>();

    /**
     * Creates a view holder for the TabLayout
     *
     * @param parent   Parent of the ViewHolder
     * @param viewType unused
     * @return ViewHolder made with the parent
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    /**
     * Binds a view holder to an item in the list
     *
     * @param holder   ViewHolder to bind
     * @param position Position of the item to bind in the list
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    /**
     * Getter for the item count
     *
     * @return Number of pages in the pager
     */
    @Override
    public int getItemCount() {
        return 3;
    }

    /**
     * ViewHolder class used to display the card view
     *
     * @author Mitch Hudson
     * @author Travis Massner
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * Public constructor
         *
         * @param parent Parent of the holder
         */
        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_list, parent, false));
        }

        /**
         * Sets up the different tabs
         *
         * @param position Current tab number
         */
        public void bind(int position) {
            // Array Lists for the results adapter should come from an api call
            ListView results = itemView.findViewById(R.id.search_results);
            SearchView searchView = itemView.findViewById(R.id.search_bar);
            TextView empty = itemView.findViewById(R.id.emptyText);

            switch (position) {
                case 0:
                    recipes = getRecipesByType("popular", "");
                    if(recipes.isEmpty()) {
                        empty.setVisibility(View.VISIBLE);
                        Toaster.toastShort("Error getting popular recipes", itemView.getContext());
                        return;
                    }
                    else {
                        empty.setVisibility(View.GONE);
                        results.setAdapter(new RecipePageListAdapter(itemView.getContext(), recipes));
                    }
                    break;
                case 1:
                    recipes = getRecipesByType("new", "");
                    if(recipes.isEmpty()) {
                        empty.setVisibility(View.VISIBLE);
                        Toaster.toastShort("Error getting new recipes", itemView.getContext());
                        return;
                    }
                    else {
                        empty.setVisibility(View.GONE);
                        results.setAdapter(new RecipePageListAdapter(itemView.getContext(), recipes));
                    }
                    break;
                default:
                    searchView.setVisibility(View.VISIBLE);
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            recipes = getRecipesByType("search", query);
                            if(recipes.isEmpty()) {
                                empty.setVisibility(View.VISIBLE);
                                Toaster.toastShort("Error searching for recipes", itemView.getContext());
                                return false;
                            }
                            else {
                                empty.setVisibility(View.GONE);
                                results.setAdapter(new RecipePageListAdapter(itemView.getContext(), recipes));
                                return true;
                            }
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            return false;
                        }
                    });
                    break;
            }
        }

        /**
         * Get the list of recipes from the server by type of recipes
         * @param recipeType Type of recipes to get
         */
        private ArrayList<Recipe> getRecipesByType(String recipeType, String search) {
            ArrayList<Recipe> recipes = new ArrayList<>();
            new GetRecipesTypeRequest(recipeType, search).request(response -> {
                try {
                    System.out.println(response.toString(3));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                GetRecipeListResponse recipeResponse = Util.objFromJson(response, GetRecipeListResponse.class);

                if (recipeResponse == null) {
                    Toaster.toastShort("Error getting recipes", itemView.getContext());
                    return;
                }

                Recipe[] newItems = recipeResponse.getRecipes();

                recipes.addAll(Arrays.asList(newItems));
            }, itemView.getContext());

            return recipes;
        }
    }


}
