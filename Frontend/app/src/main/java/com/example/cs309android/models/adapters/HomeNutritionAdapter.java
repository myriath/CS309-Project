package com.example.cs309android.models.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs309android.R;
import com.example.cs309android.models.HomeNutritionCardModel;
import com.google.android.material.progressindicator.LinearProgressIndicator;

/**
 * Adapter to use with the TabLayout for the homepage
 *
 * @author Travis Massner
 * @author Mitch Hudson
 */
public class HomeNutritionAdapter extends RecyclerView.Adapter<HomeNutritionAdapter.ViewHolder> {
    /**
     * Holds the list of nutrition cards
     */
    private HomeNutritionCardModel[] pagerItems;

    /**
     * Public constructor
     * @param pagerItems Items list to display in the pager
     */
    public HomeNutritionAdapter(HomeNutritionCardModel[] pagerItems) {
        this.pagerItems = pagerItems;
    }

    /**
     * Getter for the list of tabs
     * @return Models that make up the tabs in the TabLayout
     */
    public HomeNutritionCardModel[] getPagerItems() {
        return pagerItems;
    }

    /**
     * Sets the list of the tabs
     * @param items New list of nutrition cards
     */
    public void setItems(HomeNutritionCardModel[] items) {
        this.pagerItems = items;
    }

    /**
     * Creates a view holder for the TabLayout
     * @param parent    Parent of the ViewHolder
     * @param viewType  unused
     * @return          ViewHolder made with the parent
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    /**
     * Binds a view holder to an item in the list
     * @param holder    ViewHolder to bind
     * @param position  Position of the item to bind in the list
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(pagerItems[position]);
    }

    /**
     * Getter for the item count
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
         * @param parent Parent of the holder
         */
        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_nutrition_card, parent, false));
        }

        /**
         * Binds a model to the view
         * @param model Model to bind to the view in this holder
         */
        public void bind(HomeNutritionCardModel model) {
            double amount = model.getAmount();
            double limit = model.getLimit();
            int progress = Math.max(Math.min((int) ((amount / limit) * 100), 100), 0);

            ((TextView) itemView.findViewById(R.id.nutrientAmount)).setText(String.valueOf(amount));
            ((TextView) itemView.findViewById(R.id.nutrientLimit)).setText(String.valueOf(limit));
            ((LinearProgressIndicator) itemView.findViewById(R.id.nutrientProgressBar)).setProgress(progress);
            ((ImageView) itemView.findViewById(R.id.nutrientImage)).setImageBitmap(model.getImage());
            ((TextView) itemView.findViewById(R.id.nutrientTitle)).setText(model.getTitle());

//            itemView.findViewById(R.id.leftButton).setOnClickListener(model.getLeftListener());
//            itemView.findViewById(R.id.rightButton).setOnClickListener(model.getRightListener());
        }
    }
}
