package com.example.cs309android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cs309android.R;
import com.example.cs309android.models.api.models.Comment;
import com.example.cs309android.models.api.request.profile.GetProfilePictureRequest;

/**
 * Custom view inflates the adapter_account layout
 * For comments on a recipe
 *
 * @author Mitch Hudson
 */
public class CommentView extends FrameLayout {
    public CommentView(@NonNull Context context) {
        super(context);
    }

    public CommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CommentView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CommentView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Inflates the comment view
     *
     * @param comment Comment for this view to display
     */
    public void initView(Comment comment) {
        View view = inflate(getContext(), R.layout.adapter_account, this);

        String username = comment.getUsername();
        ((TextView) view.findViewById(R.id.username)).setText(username);

        ImageView profilePicture = view.findViewById(R.id.profile_picture);
        new GetProfilePictureRequest(username).request(profilePicture, getContext());

        TextView bio = view.findViewById(R.id.bio);
        int maxLines = bio.getMaxLines();
        bio.setMaxLines(Integer.MAX_VALUE);
        bio.setText(comment.getComment());
        bio.post(() -> {
            if (bio.getLineCount() > maxLines) {
                bio.setMaxLines(maxLines);
                TextView showMoreLess = view.findViewById(R.id.showMoreLess);
                showMoreLess.setVisibility(View.VISIBLE);
                showMoreLess.setOnClickListener(view1 -> {
                    if (comment.showingFull()) {
                        comment.setShowFull(false);
                        showMoreLess.setText(R.string.show_more);
                        bio.setMaxLines(maxLines);
                    } else {
                        comment.setShowFull(true);
                        showMoreLess.setText(R.string.show_less);
                        bio.setMaxLines(Integer.MAX_VALUE);
                    }
                });
            }
        });
    }
}
