package com.requests.backend.controllers;

import com.google.gson.GsonBuilder;
import com.requests.backend.models.*;
import com.requests.backend.repositories.TokenRepository;
import com.util.security.Hasher;
import com.requests.backend.models.requests.LoginRequest;
import com.requests.backend.models.requests.RegisterRequest;
import com.requests.backend.models.responses.ResultResponse;
import com.requests.backend.models.responses.SaltResponse;
import com.requests.backend.repositories.FavoriteRepository;
import com.requests.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

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
    public @ResponseBody String validateToken(@PathVariable String token) {
        // TODO: The request is a token request, handle that
        //       If the token is older than a day, return RESULT_REGEN_TOKEN to tell the app to generate a new token.
        //       If the token is not expired and valid, return RESULT_LOGGED_IN
        //       If the server encounters an error, return RESULT_ERROR

        ResultResponse res = new ResultResponse();

        Token[] tokenQuery = tokenRepository.queryGetToken(token);

        try {
            // If the token is not valid, return RESULT_USER_HASH_MISMATCH
            if (tokenQuery.length == 0) {
                res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
            }
            else {

                // The current date and time
                java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

                // The token that was gathered from querying the database
                Token dbToken = tokenQuery[0];

                // Calculate the difference between the time of creation of the token and the
                // current date in days.
                long diffInMils = Math.abs(currentDate.getTime() - dbToken.getCreation_date().getTime());
                long diff = TimeUnit.DAYS.convert(diffInMils, TimeUnit.MILLISECONDS);

                // If the difference is one day or greater, a new token needs to be generated.
                if (diff >= 1) {
                    res.setResult(RESULT_REGEN_TOKEN);
                }
                // Otherwise, the user should be logged in.
                else {
                    res.setResult(RESULT_LOGGED_IN);
                }
            }

        } catch (Exception e) {
            res.setResult(RESULT_ERROR);
        }

        // TODO: Maybe don't disable escaping, could it lead to an SQL injection?
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    @GetMapping(path="/validateLogin/{username}") // /users/validateLogin/{username}?hash=""
    public @ResponseBody String validateLogin(@PathVariable String username, @RequestParam(name="hash") String hash) {
        String p_hash = Hasher.sha256(hash); // SHA-256's the incoming hash

        Collection<User> userRes = userRepository.queryValidateUsername(username);

        LoginResponse res = new LoginResponse(null, RESULT_OK);

        if (userRes.isEmpty()) {
            res.setResult(RESULT_ERROR);
        }
        else {

            // If a user with the username is found, assign that user to the user variable
            User user = userRes.iterator().next();

            // If the provided password does not match the user's password, return hash mismatch code
            if (user.getPHash().compareTo(p_hash) != 0) {
                res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
            }
            // Otherwise, the password matches and the login is valid
            else {
                res.setResult(RESULT_LOGGED_IN);
                //TODO: Get the token from the table, if its expired set result to RESULT_REGEN_TOKEN
                res.setToken(null); // temp
            }
        }

        // If no users with the username is found, return code -1

        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "=")
        // TODO: Maybe don't disable escaping, could it lead to an SQL injection?
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
        String token = Hasher.sha256(req.getToken()); // SHA-256's the incoming token

        ResultResponse res = new ResultResponse();

        Token[] tokenQuery = tokenRepository.queryGetToken(token);

        // TODO: Add token calls to the db
        //       If token exists in the table, return RESULT_REGEN_TOKEN,
        //       If the username exists, return RESULT_ERROR,
        //       if not, add it to the token table along with Username and return RESULT_OK
        if (tokenQuery.length > 0) {
            res.setResult(RESULT_REGEN_TOKEN);
        }
        else {
            try {
                userRepository.queryCreateUser(username, email, pHash, pSalt, "User");
                tokenRepository.queryAddToken(token);
                res.setResult(RESULT_USER_CREATED);
            } catch (Exception e) {
                res.setResult(RESULT_ERROR_USERNAME_TAKEN);
            }
        }




        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "="
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }
}
