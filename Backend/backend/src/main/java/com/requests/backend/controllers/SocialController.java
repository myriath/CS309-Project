package com.requests.backend.controllers;

import com.requests.backend.models.Comment;
import com.requests.backend.models.Recipe;
import com.requests.backend.models.Token;
import com.requests.backend.models.User;
import com.requests.backend.models.responses.FollowResponse;
import com.requests.backend.models.responses.GetCommentsResponse;
import com.requests.backend.models.responses.RecipeListResponse;
import com.requests.backend.models.responses.ResultResponse;
import com.requests.backend.repositories.*;
import com.util.security.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.util.Constants.*;
import static com.util.Constants.UserType.USER_REG;

/**
 * This class is responsible for handling all requests related to recipes.
 * @author Logan
 * @author Mitch
 */
@RestController
@RequestMapping(path="/social")
public class SocialController {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    /**
     * This method gets a list of follows of a user given their username.
     * @param username The username of the user to get the followers of.
     * @return A list of followers of the user.
     */
    @GetMapping (path="/getFollowers/{username}")
    public @ResponseBody FollowResponse getFollowers(@PathVariable String username) {
        FollowResponse res = new FollowResponse();

        String[] followers = followRepository.queryGetFollowers(username);

        res.setUsers(followers);
        res.setResult(RESULT_OK);

        return res;
    }

    /**
     * Gets a list of users that a user is following.
     * @param username The username of the user
     * @return A list of users that the user is following.
     */
    @GetMapping (path="/getFollowing/{username}")
    public @ResponseBody FollowResponse getFollowing(@PathVariable String username) {
        FollowResponse res = new FollowResponse();

        String[] following = followRepository.queryGetFollowing(username);

        res.setUsers(following);
        res.setResult(RESULT_OK);

        return res;
    }

    /**
     * Checks if a user is following another user.
     * @param follower The username of the user that is following.
     * @param following The username of the user that is being followed.
     * @return A boolean value indicating if the user is following the other user.
     */
    @GetMapping (path="/isFollowing/{follower}/{following}")
    public @ResponseBody FollowResponse getIsFollowing(@PathVariable String follower, @PathVariable String following) {
        FollowResponse res = new FollowResponse();

        String[] query = followRepository.queryIsFollowing(follower, following);

        res.setUsers(query);
        res.setResult(RESULT_OK);

        return res;
    }

    /**
     * Has one user follow another user.
     * @param token The token of the user that is following.
     * @param following The username of the user that is being followed.
     * @return A result code indicating if the user was successfully followed.
     */
    @PostMapping (path="/follow/{token}")
    public @ResponseBody FollowResponse follow(@PathVariable String token, @RequestParam String following) {
        String tokenHash = Hasher.sha256(token);

        Token[] tokenQueryRes = tokenRepository.queryGetToken(tokenHash);

        FollowResponse res = new FollowResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String follower = tokenQueryRes[0].getUser().getUsername();

            followRepository.queryAddFollow(follower, following);

            res.setResult(RESULT_OK);
        }

        return res;
    }

    /**
     * Has one user unfollow another user.
     * @param token The token of the user that is unfollowing.
     * @param following The username of the user that is being unfollowed.
     * @return A result code indicating if the user was successfully unfollowed.
     */
    @PutMapping (path="/unfollow/{token}")
    public @ResponseBody FollowResponse unfollow(@PathVariable String token, @RequestParam String following) {
        String tokenHash = Hasher.sha256(token);

        Token[] tokenQueryRes = tokenRepository.queryGetToken(tokenHash);

        FollowResponse res = new FollowResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String follower = tokenQueryRes[0].getUser().getUsername();

            followRepository.queryRemoveFollow(follower, following);

            res.setResult(RESULT_OK);
        }

        return res;
    }

    /**
     * Gets the feed of recipes for a user based on a provided token.
     * @param token The token of the user to get the feed for.
     * @return A list of recipes for the user.
     */
    @GetMapping (path="/getFeed/{token}")
    public @ResponseBody RecipeListResponse getFeed(@PathVariable String token) {
        String tokenHash = Hasher.sha256(token);

        Token[] tokenQueryRes = tokenRepository.queryGetToken(tokenHash);

        RecipeListResponse res = new RecipeListResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String username = tokenQueryRes[0].getUser().getUsername();

            Recipe[] feed = recipeRepository.queryGetFeed(username);

            res.setRecipes(feed);
            res.setResult(RESULT_OK);
        }

        return res;
    }

    /**
     * Gets all the recipes posted by a user given their token.
     * @param token The token of the user to get the recipes of.
     * @return A list of recipes posted by the user.
     */
    @GetMapping (path="/getUserPosts/{token}")
    public @ResponseBody RecipeListResponse getUserFeed(@PathVariable String token) {
        String tokenHash = Hasher.sha256(token);

        Token[] tokenQueryRes = tokenRepository.queryGetToken(tokenHash);

        RecipeListResponse res = new RecipeListResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String username = tokenQueryRes[0].getUser().getUsername();

            Recipe[] feed = recipeRepository.queryGetRecipeByUsername(username);

            res.setRecipes(feed);
            res.setResult(RESULT_OK);
        }

        return res;
    }

    /**
     * Add a comment to a recipe.
     * @param token The token of the user that is commenting.
     * @param rid The id of the recipe to comment on.
     * @param body The body contents of the comment.
     * @return A result code indicating if the comment was successfully added.
     */
    @PostMapping (path="/comment/{token}/{rid}")
    public @ResponseBody ResultResponse comment(@PathVariable String token, @PathVariable int rid, @RequestBody String body) {
        String tokenHash = Hasher.sha256(token);

        Token[] tokenQueryRes = tokenRepository.queryGetToken(tokenHash);

        ResultResponse res = new ResultResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else return UserController.getUserFromToken(token, (user, res1) -> {
                Comment comment = new Comment();
                comment.setUser(user);
                comment.setBody(body);
                commentRepository.save(comment);
                res.setResult(RESULT_OK);
            }, tokenRepository);

        return res;
    }

    /**
     * Deletes a given comment by id.
     * Works if the token is of the owner or moderator+
     */
    @DeleteMapping(path = "/removeComment/{token}/{commentId}")
    public @ResponseBody ResultResponse deleteComment(@PathVariable String token, @PathVariable int commentId) {
        token = Hasher.sha256(token);

        return UserController.getUserFromToken(token, (user, res) -> {
            User commentUser = commentRepository.queryGetCommentByCid(commentId)[0].getUser();

            if (commentUser.getUsername().equals(user.getUsername()) || user.getUserType() > USER_REG) {
                commentRepository.queryRemoveComment(commentId);
                res.setResult(RESULT_OK);
            }
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }, tokenRepository);
    }

    /**
     * Edits a given comment
     * Works if the token is of the owner or moderator+
     */
    @DeleteMapping(path = "/editComment/{token}/{commentId}")
    public @ResponseBody ResultResponse editComment(@PathVariable String token, @PathVariable int commentId, @RequestBody String body) {
        token = Hasher.sha256(token);

        return UserController.getUserFromToken(token, (user, res) -> {
            User commentUser = commentRepository.queryGetCommentByCid(commentId)[0].getUser();

            if (commentUser.getUsername().equals(user.getUsername()) || user.getUserType() > USER_REG) {
                commentRepository.queryUpdateComment(commentId, body);
                res.setResult(RESULT_OK);
            }
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }, tokenRepository);
    }

    /**
     * Gets the comments of a recipe
     */
    @GetMapping(path = "/getComments/{rid}")
    public @ResponseBody GetCommentsResponse getComments(@PathVariable int rid) {
        GetCommentsResponse response = new GetCommentsResponse();
        try {
            response.setComments(commentRepository.queryGetCommentsByRid(rid));
            response.setResult(RESULT_OK);
        } catch (Exception e) {
            response.setResult(RESULT_ERROR);
        }
        return response;
    }
}
