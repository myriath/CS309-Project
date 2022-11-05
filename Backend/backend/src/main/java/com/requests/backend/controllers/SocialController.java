package com.requests.backend.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.requests.backend.models.Recipe;
import com.requests.backend.models.Token;
import com.requests.backend.models.User;
import com.requests.backend.models.responses.FollowResponse;
import com.requests.backend.models.responses.RecipeListResponse;
import com.requests.backend.models.responses.ResultResponse;
import com.requests.backend.repositories.CommentRepository;
import com.requests.backend.repositories.FollowRepository;
import com.requests.backend.repositories.RecipeRepository;
import com.requests.backend.repositories.TokenRepository;
import com.util.security.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.util.Constants.*;

@RestController
@RequestMapping(path="/social")
public class SocialController {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping (path="/getFollowers/{token}")
    public @ResponseBody String getFollowers(@PathVariable String token) {
        String tokenHash = Hasher.sha256(token);

        Token[] tokenQueryRes = tokenRepository.queryGetToken(tokenHash);

        FollowResponse res = new FollowResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String username = tokenQueryRes[0].getUsername();

            User[] followers = followRepository.queryGetFollowers(username);

            res.setUsers(followers);
            res.setResult(RESULT_OK);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    @GetMapping (path="/getFollowing/{token}")
    public @ResponseBody String getFollowing(@PathVariable String token) {
        String tokenHash = Hasher.sha256(token);

        Token[] tokenQueryRes = tokenRepository.queryGetToken(tokenHash);

        FollowResponse res = new FollowResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String username = tokenQueryRes[0].getUsername();

            User[] following = followRepository.queryGetFollowing(username);

            res.setUsers(following);
            res.setResult(RESULT_OK);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    @PostMapping (path="/follow/{token}")
    public @ResponseBody String follow(@PathVariable String token, @RequestParam String following) {
        String tokenHash = Hasher.sha256(token);

        Token[] tokenQueryRes = tokenRepository.queryGetToken(tokenHash);

        FollowResponse res = new FollowResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String follower = tokenQueryRes[0].getUsername();

            followRepository.queryAddFollow(follower, following);

            res.setResult(RESULT_OK);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    @PutMapping (path="/unfollow/{token}")
    public @ResponseBody String unfollow(@PathVariable String token, @RequestParam String following) {
        String tokenHash = Hasher.sha256(token);

        Token[] tokenQueryRes = tokenRepository.queryGetToken(tokenHash);

        FollowResponse res = new FollowResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String follower = tokenQueryRes[0].getUsername();

            followRepository.queryRemoveFollow(follower, following);

            res.setResult(RESULT_OK);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    /**
     * Gets a user's feed of posts from users they are following
     * @return
     */
    @GetMapping (path="/getFeed/{token}")
    public @ResponseBody String getFeed(@PathVariable String token) {
        String tokenHash = Hasher.sha256(token);

        Token[] tokenQueryRes = tokenRepository.queryGetToken(tokenHash);

        RecipeListResponse res = new RecipeListResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String username = tokenQueryRes[0].getUsername();

            Recipe[] feed = recipeRepository.queryGetFeed(username);

            res.setRecipes(feed);
            res.setResult(RESULT_OK);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    /**
     * Gets all of the posts of a given user
     * @return
     */
    @GetMapping (path="/getUserPosts/{token}")
    public @ResponseBody String getUserFeed(@PathVariable String token) {
        String tokenHash = Hasher.sha256(token);

        Token[] tokenQueryRes = tokenRepository.queryGetToken(tokenHash);

        RecipeListResponse res = new RecipeListResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String username = tokenQueryRes[0].getUsername();

            Recipe[] feed = recipeRepository.queryGetRecipeByUsername(username);

            res.setRecipes(feed);
            res.setResult(RESULT_OK);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    @PostMapping (path="/comment/{token}")
    public @ResponseBody String comment(@PathVariable String token, @RequestParam int rid, @RequestParam String body) {
        String tokenHash = Hasher.sha256(token);

        Token[] tokenQueryRes = tokenRepository.queryGetToken(tokenHash);

        ResultResponse res = new ResultResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String username = tokenQueryRes[0].getUsername();

            commentRepository.queryCreateComment(rid, username, body);

            res.setResult(RESULT_OK);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }
}
