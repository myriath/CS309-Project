package com.example.cs309android.fragments.shopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.adapters.ShoppingListAdapter;
import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.models.api.request.shopping.GetListRequest;
import com.example.cs309android.models.api.response.shopping.GetListResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Shopping list fragment.
 * Consists of a ListView and a FAB to add items.
 * The list view has a custom adapter that has a checkbox to strike out the text, a label, and
 * a button to remove the item.
 *
 * @author Mitch Hudson
 */
public class ShoppingFragment extends BaseFragment {
    /**
     * Runs when the fragment is created.
     *
     * @param inflater           Inflates the fragment
     * @param container          Parent container for the fragment
     * @param savedInstanceState Saved state
     * @return Inflated fragment view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);

        ListView listView = view.findViewById(R.id.shopping_list);
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        refreshList(view);

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.shopping_list), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            mlp.topMargin = insets.top;
            insets = windowInsets.getInsets(WindowInsetsCompat.Type.ime());
            mlp.bottomMargin = insets.bottom;
            return WindowInsetsCompat.CONSUMED;
        });

        return view;
    }

    /**
     * Refreshes the shopping list
     *
     * @param view Root view of the fragment
     */
    public void refreshList(View view) {
        ArrayList<SimpleFoodItem> items = MainActivity.getShoppingList();
        ShoppingListAdapter adapter = new ShoppingListAdapter(this.getActivity(), items);
        ((ListView) view.findViewById(R.id.shopping_list)).setAdapter(adapter);
//        ExtendedFloatingActionButton fab = view.findViewById(R.id.add_item);

        TextView empty = view.findViewById(R.id.empty_text);
        if (items.isEmpty()) {
            empty.setVisibility(View.VISIBLE);
//            fab.extend();
        } else {
            empty.setVisibility(View.INVISIBLE);
//            fab.shrink();
        }
    }
}
