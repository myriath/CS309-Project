package com.example.cs309android.models.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs309android.R;

/**
 * Adapter to use with the TabLayout for the recipe page
 *
 * @author Travis Massner
 * @author Mitch Hudson
 */
public class RecipePageAdapter extends RecyclerView.Adapter<RecipePageAdapter.ViewHolder> {
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
            switch (position) {
                case 0:
                    // TODO: Get popular recipes
//                results.setAdapter(new RecipeListAdapter(itemView.getContext(), ));
                    break;
                case 1:
                    // TODO: Get new recipes
//                results.setAdapter(new RecipeListAdapter(itemView.getContext(), ));
                    break;
                default:
                    searchView.setVisibility(View.VISIBLE);
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            // TODO: search
//                        results.setAdapter(new RecipeListAdapter(itemView.getContext(), ));
                            return true;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            return false;
                        }
                    });
                    break;
            }
        }
    }
}
