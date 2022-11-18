package com.example.cs309android.models.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs309android.R;
import com.example.cs309android.models.api.models.Recipe;

/**
 * Adapter to use with the TabLayout for the recipe page
 *
 * @author Travis Massner
 * @author Mitch Hudson
 */
public class RecipePageAdapter extends RecyclerView.Adapter<RecipePageAdapter.ViewHolder> {
    /**
     * Holds the list of nutrition cards
     */
    private Recipe[] pagerItems;

    /**
     * Public constructor
     *
     * @param pagerItems Items list to display in the pager
     */
    public RecipePageAdapter(Recipe[] pagerItems) {
        this.pagerItems = pagerItems;
    }

    /**
     * Getter for the list of tabs
     *
     * @return Models that make up the tabs in the TabLayout
     */
    public Recipe[] getPagerItems() {
        return pagerItems;
    }

    /**
     * Sets the list of the tabs
     *
     * @param items New list of nutrition cards
     */
    public void setItems(Recipe[] items) {
        this.pagerItems = items;
    }

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
        holder.bind(pagerItems[position]);
    }

    /**
     * Getter for the item count
     *
     * @return Number of pages in the pager
     */
    @Override
    public int getItemCount() {
        return pagerItems.length;
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
         * Binds a model to the view
         *
         * @param model Model to bind to the view in this holder
         */
        public void bind(Recipe model) {
            ListView results = itemView.findViewById(R.id.search_results);
            SearchView searchView = itemView.findViewById(R.id.search_bar);
            if (!model.searchable()) {
                searchView.setVisibility(View.GONE);
                results.setAdapter(new RecipePageListAdapter(itemView.getContext(), model.getRecipes()));
            } else {
                searchView.text;
            }
//            new GetRecipeImageRequest(String.valueOf(model.getRecipeID())).request((ImageView) itemView.findViewById(R.id.recipeImage), itemView.getContext());
//            ((TextView) itemView.findViewById(R.id.recipeTitle)).setText(model.getRecipeName());
//            ((TextView) itemView.findViewById(R.id.recipeDescription)).setText(model.getDescription());
//            itemView.setPadding((int) dp16, (int) dp8, (int) dp16, (int) dp8);
        }
    }
}
