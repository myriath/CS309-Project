package com.example.cs309android.views;

import static com.example.cs309android.util.Constants.UserType.USER_REG;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.models.api.models.Comment;
import com.example.cs309android.models.api.request.profile.GetProfilePictureRequest;
import com.example.cs309android.models.api.request.social.DeleteCommentRequest;
import com.example.cs309android.models.api.request.social.EditCommentRequest;
import com.example.cs309android.models.api.response.GenericResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * Custom view inflates the adapter_account layout
 * For comments on a recipe
 *
 * @author Mitch Hudson
 */
public class CommentView extends FrameLayout {
    private boolean editable = false;

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
     * @param global  GlobalClass for checking ownership
     */
    public void initView(Comment comment, RunWithComment onEdit, RunWithComment onDelete, GlobalClass global) {
        View view = inflate(getContext(), R.layout.adapter_comment, this);

        String username = comment.getUsername();
        ((TextView) view.findViewById(R.id.username)).setText(username);

        ImageView profilePicture = view.findViewById(R.id.profile_picture);
        new GetProfilePictureRequest(username).request(profilePicture, getContext());

        TextView commentView = view.findViewById(R.id.comment);
        int maxLines = commentView.getMaxLines();
        commentView.setMaxLines(Integer.MAX_VALUE);
        commentView.setText(comment.getBody());
        commentView.post(() -> {
            if (commentView.getLineCount() > maxLines) {
                commentView.setMaxLines(maxLines);
                TextView showMoreLess = view.findViewById(R.id.showMoreLess);
                showMoreLess.setVisibility(View.VISIBLE);
                showMoreLess.setOnClickListener(view1 -> {
                    if (comment.showingFull()) {
                        comment.setShowFull(false);
                        showMoreLess.setText(R.string.show_more);
                        commentView.setMaxLines(maxLines);
                    } else {
                        comment.setShowFull(true);
                        showMoreLess.setText(R.string.show_less);
                        commentView.setMaxLines(Integer.MAX_VALUE);
                    }
                });
            }
        });

        if (global.getUsername().equals(username) || global.getUserType() > USER_REG) {
            ImageButton menuButton = findViewById(R.id.menu);
            menuButton.setVisibility(View.VISIBLE);
            menuButton.setOnClickListener(view1 -> {
                PopupMenu menu = new PopupMenu(getContext(), view1);
                menu.inflate(R.menu.moderation_menu);
                menu.show();
                menu.setOnMenuItemClickListener(item -> {
                    int id = item.getItemId();
                    if (id == R.id.edit) {
                        onEdit.run(comment);
                    } else if (id == R.id.delete) {
                        new DeleteCommentRequest(comment.getId(), global.getToken()).request(response -> {
                            GenericResponse genericResponse = Util.objFromJson(response, GenericResponse.class);
                            if (genericResponse.getResult() != Constants.Results.RESULT_OK) {
                                Toaster.toastShort("Error", getContext());
                                return;
                            }
                            onDelete.run(comment);
                        }, error -> Toaster.toastShort("Error", getContext()), getContext());
                    }
                    return true;
                });
            });

            TextInputLayout commentInput = findViewById(R.id.commentInputLayout);
            Objects.requireNonNull(commentInput.getEditText()).setText(comment.getBody());

            findViewById(R.id.updateButton).setOnClickListener(view1 -> {
                new EditCommentRequest(commentInput.getEditText().getText().toString(), comment.getId(), global.getToken()).request(response -> {
                    GenericResponse genericResponse = Util.objFromJson(response, GenericResponse.class);
                    if (genericResponse.getResult() != Constants.Results.RESULT_OK) {
                        Toaster.toastShort("Error", getContext());
                        return;
                    }
                    commentView.setText(commentInput.getEditText().getText().toString());
                }, error -> Toaster.toastShort("Error", getContext()), getContext());
                toggleEditable();
            });
            findViewById(R.id.cancelButton).setOnClickListener(view1 -> toggleEditable());
        }
    }

    /**
     * Toggles this view's editable parts
     */
    public void toggleEditable() {
        if (editable) {
            findViewById(R.id.nonEditable).setVisibility(VISIBLE);
            findViewById(R.id.editable).setVisibility(GONE);
        } else {
            findViewById(R.id.nonEditable).setVisibility(GONE);
            findViewById(R.id.editable).setVisibility(VISIBLE);
        }
        editable = !editable;
    }

    /**
     * Used to run a lambda with a given comment
     */
    public interface RunWithComment {
        void run(Comment comment);
    }
}
