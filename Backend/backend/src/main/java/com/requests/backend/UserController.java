package com.requests.backend;

import com.google.gson.GsonBuilder;
import com.requests.backend.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import java.util.Collection;

@RestController
@RequestMapping(path="/users")
public class UserController {

    public static final int RESULT_ERROR = -1;
    public static final int RESULT_ERROR_USER_HASH_MISMATCH = -2;
    public static final int RESULT_ERROR_USERNAME_TAKEN = -3;
    public static final int RESULT_ERROR_EMAIL_TAKEN = -4;

    public static final int RESULT_OK = 0;
    public static final int RESULT_LOGGED_IN = 1;
    public static final int RESULT_USER_CREATED = 2;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

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

    @GetMapping("/getSalt")
    @ResponseBody
    public String getSalt(@RequestParam String username) {

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

    @PostMapping(path="/validateLogin")
    public @ResponseBody String validateLogin(@RequestBody String json) {

        LoginRequest req = new Gson().fromJson(json, LoginRequest.class);

        String username = req.getUsername();
        String p_hash = req.getPHash();

        Collection<User> userRes = userRepository.queryValidateUsername(username);

        ResultResponse res = new ResultResponse(0);

        // If no users with the username is found, return code -1
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
            }
        }

        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "="
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    @PostMapping(path="/register") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestBody String json) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        RegisterRequest req = new Gson().fromJson(json, RegisterRequest.class);

        String username = req.getUsername();
        String email = req.getEmail();
        String pHash = req.getPHash();
        String pSalt = req.getPSalt();

        ResultResponse res = new ResultResponse();

        try {
            userRepository.queryCreateUser(username, email, pHash, pSalt, "User");
            res.setResult(RESULT_USER_CREATED);
        } catch (Exception e) {
            res.setResult(RESULT_ERROR_USERNAME_TAKEN);
        }

        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "="
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }
}
