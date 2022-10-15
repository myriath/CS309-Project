package com.requests.backend.controllers;

import com.google.gson.GsonBuilder;
import com.requests.backend.models.*;
import com.requests.backend.repositories.TokenRepository;
import com.util.security.Hasher;
import com.requests.backend.models.requests.RegisterRequest;
import com.requests.backend.models.responses.ResultResponse;
import com.requests.backend.models.responses.SaltResponse;
import com.requests.backend.repositories.FavoriteRepository;
import com.requests.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.sql.Date;
import java.util.Collection;

import static com.util.Constants.*;

@RestController
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        // TODO: Probably should remove this for security
        return userRepository.findAll();
    }

    @GetMapping(path="/dbquery")
    public @ResponseBody String dbQuery() {
        Collection<User> users = userRepository.queryGetAllUsers();
        User papa = users.iterator().next();
        return papa.getUsername();
    }

    /**
     *
     * @param username
     * @return
     */
    @GetMapping(path="/getFavoritesByUsername/{username}")
    public @ResponseBody String getFavoritesByUsername(@PathVariable String username) {
        Collection<Favorite> favorites = favoriteRepository.queryGetFavorites(username);
        return favorites.iterator().next().getFoodName();
    }

    /**
     *
     * @param username
     * @return
     */
    @GetMapping(path="/getUserByUsername/{username}")
    public @ResponseBody String getUserByUsername(@PathVariable String username) {
        Collection<User> user = userRepository.queryGetUserByUsername(username);
        return user.iterator().next().getUsername();
    }

    @GetMapping("/getSalt/{username}")
    @ResponseBody
    public String getSalt(@PathVariable String username) {

        Collection<User> userRes = userRepository.queryValidateUsername(username);

        SaltResponse res = new SaltResponse();

        if (userRes.isEmpty()) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        } else {
            User user = userRes.iterator().next();
            String salt = user.getPSalt();
            res.setResult(RESULT_OK);
            res.setSalt(salt);
        }

        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "="
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    @GetMapping(path="/validateToken/{token}") // /users/validateLogin/{token}
    public @ResponseBody String validateTokenLogin(@PathVariable String token) {
        String hashedToken = Hasher.sha256(token);

        ResultResponse res = new ResultResponse();

        Token[] tokenQuery = tokenRepository.queryGetToken(hashedToken);

        try {
            // If the token is not valid, return RESULT_USER_HASH_MISMATCH
            if (tokenQuery.length == 0) {
                res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
            }
            else {

                // The token that was gathered from querying the database
                Token dbToken = tokenQuery[0];

                boolean outdatedToken = dbToken.isOutdated();

                // If the difference is one day or greater, a new token needs to be generated.
                if (outdatedToken) {
                    res.setResult(RESULT_REGEN_TOKEN);
                }
                // Otherwise, the user should be logged in, as the token is valid.
                else {
                    res.setResult(RESULT_LOGGED_IN);
                }
            }

        // If the server encounters an error, return RESULT_ERROR
        } catch (Exception e) {
            e.printStackTrace();
            res.setResult(RESULT_ERROR);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    @GetMapping(path="/validateLogin/{username}") // /users/validateLogin/{username}?hash=""&newToken=""
    public @ResponseBody String validateLogin(@PathVariable String username, @RequestParam(name="hash") String hash, @RequestParam(name="newToken") String newToken) {
        String pHash = Hasher.sha256(hash); // SHA-256's the incoming hash
        String tokenHash = Hasher.sha256(newToken); // SHA-256's the incoming token, this is added to the table as another hash for the username

        Collection<User> userRes = userRepository.queryValidateUsername(username);

        ResultResponse res = new ResultResponse(RESULT_OK);

        if (userRes.isEmpty()) {
            res.setResult(RESULT_ERROR);
        }
        else {

            // If a user with the username is found, assign that user to the user variable
            User user = userRes.iterator().next();

            // If the provided password does not match the user's password, return hash mismatch code
            if (user.getPHash().compareTo(pHash) != 0) {
                res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
            }
            // Otherwise, the password matches and the login is valid
            else {
                Token[] tokenQueryRes = tokenRepository.queryGetToken(tokenHash);

                // If the token already exists in the table, return RESULT_REGEN_TOKEN
                if (tokenQueryRes.length > 0) {
                    res.setResult(RESULT_REGEN_TOKEN);
                }
                // Otherwise, the token doesn't already exist -- add the hashed token to the tokens table
                else {
                    tokenRepository.queryAddToken(tokenHash, new Date(System.currentTimeMillis()), username);
                    res.setResult(RESULT_LOGGED_IN);
                }

                //       Note: THIS DOES NOT REPLACE ANY OTHER TOKENS!
                //       multiple tokens can be allowed for each user (this lets you log into multiple devices at once)
                //       in the future we'll add an email that goes out whenever someone logs into an account

            }
        }

        // If no users with the username is found, return code -1

        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "=")
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    @PostMapping(path="/register")
    public @ResponseBody String addNewUser (@RequestBody String json) {

        RegisterRequest req = new Gson().fromJson(json, RegisterRequest.class);

        String username = req.getUsername();
        String email = req.getEmail();
        String pHash = Hasher.sha256(req.getPHash()); // SHA-256's the incoming hash
        String pSalt = req.getPSalt();
        String tokenHash = Hasher.sha256(req.getToken()); // SHA-256's the incoming token

        ResultResponse res = new ResultResponse();

        Token[] tokenQuery = tokenRepository.queryGetToken(tokenHash);

        // If the token exists in the table, return RESULT_REGEN_TOKEN,
        if (tokenQuery.length > 0) {
            res.setResult(RESULT_REGEN_TOKEN);
        }
        else {
            // If the token does not already exist, try to add the user to user table
            try {
                userRepository.queryCreateUser(username, email, pHash, pSalt, "User");
                tokenRepository.queryAddToken(tokenHash, new Date(System.currentTimeMillis()), username);
                res.setResult(RESULT_USER_CREATED);

            // If the username already exists in the user table, return an error result
            } catch (Exception e) {
                res.setResult(RESULT_ERROR);
            }
        }

        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "="
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    @PutMapping(path="/regenToken/{oldToken}")
    public @ResponseBody String regenToken(@PathVariable String oldToken, @RequestParam(name="newToken") String newToken) {
        String oldTokenHash = Hasher.sha256(oldToken);
        String newTokenHash = Hasher.sha256(newToken);

        ResultResponse res = new ResultResponse();

        Token[] oldTokenQueryRes = tokenRepository.queryGetToken(oldTokenHash);
        Token[] newTokenQueryRes = tokenRepository.queryGetToken(newTokenHash);

        // If the oldToken isn't in the tokens table, return error result
        if (oldTokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR);
        }
        else {
            Token dbToken = oldTokenQueryRes[0];

            // If newToken is in the tokens table already, return regen token result
            if (dbToken.isOutdated() && newTokenQueryRes.length > 0) {
                res.setResult(RESULT_REGEN_TOKEN);
            }
            // Otherwise, if the token is expired, replace the old token with the new token
            // in the tokens table
            else if (dbToken.isOutdated()) {
                tokenRepository.queryUpdateToken(newTokenHash, new Date(System.currentTimeMillis()), oldTokenHash);
                res.setResult(RESULT_LOGGED_IN);
            }
            // Otherwise, oldToken is not outdated, and does not need replacement -- return an error
            else {
                res.setResult(RESULT_ERROR);
            }
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }
}
