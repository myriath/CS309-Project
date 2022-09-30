package com.requests.backend;

import com.google.gson.GsonBuilder;
import com.requests.backend.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import java.util.Base64;
import java.util.Collection;

@RestController // This means that this class is a Controller
@RequestMapping(path="/users") // This means URL's start with /demo (after Application path)
public class UserController {

    public static final int RESULT_ERROR = -1;
    public static final int RESULT_ERROR_USER_HASH_MISMATCH = -2;
    public static final int RESULT_ERROR_USERNAME_TAKEN = -3;
    public static final int RESULT_ERROR_EMAIL_TAKEN = -4;

    public static final int RESULT_OK = 0;
    public static final int RESULT_LOGGED_IN = 1;
    public static final int RESULT_USER_CREATED = 2;

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
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
            String encodedSalt = Base64.getEncoder().encodeToString(salt.getBytes());
            res.setResult(RESULT_OK);
            res.setSalt(encodedSalt);
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

        Base64.Decoder decoder = Base64.getDecoder();
        String decodedHash = decoder.decode(p_hash).toString();

        Collection<User> userRes = userRepository.queryValidateUsername(username);

        LoginResponse res = new LoginResponse(0);

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

        // TODO: CONFIRM THAT DECODING WORKS AS INTENDED
        // TESTING ENCODING / DECODING
        Base64.Decoder decoder = Base64.getDecoder();
        String decodedHash = decoder.decode(pHash).toString();
        String decodedSalt = decoder.decode(pSalt).toString();

        LoginResponse res = new LoginResponse();

        try {
            userRepository.queryCreateUser(username, email, decodedHash, decodedSalt, "User");
            res.setResult(RESULT_USER_CREATED);
        } catch (Exception e) {
            res.setResult(RESULT_ERROR_USERNAME_TAKEN);
        }

        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "="
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }
}
