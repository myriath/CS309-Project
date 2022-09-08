package com.example.demo.mappings;

import com.example.demo.Database;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Authentication rest controller
 * Controls requests under the /auth path, allowing users to sign in and register
 * EXPERIMENT 4
 *
 * @author Mitch Hudson
 */
@RestController
@RequestMapping("/auth")
public class Authentication {
    /**
     * These integers are response codes that need to be synced between the app and the server. If they are not
     * synced there will be miscommunication between them.
     */
    public static final int RESULT_ERROR = -1;
    public static final int RESULT_ERROR_USER_HASH_MISMATCH = -2;
    public static final int RESULT_ERROR_USERNAME_TAKEN = -3;
    public static final int RESULT_ERROR_EMAIL_TAKEN = -4;

    public static final int RESULT_OK = 0;
    public static final int RESULT_LOGGED_IN = 1;
    public static final int RESULT_USER_CREATED = 2;

    /**
     * Get Mapping to get the databases stored salt for the account. This is done to make sure two users with the same password
     * don't end up with the same hash value, (a salt is a random number appended to the given password before hashing
     * to reduce collisions)
     * This method is used as a part of the login process.
     *
     * @param json  JSON Object structured as follows:
     *                  "username"  : string for account username,
     *                  "hash"      : Base64 string of the 256 byte hashed password+salt to check against DB stored hash
     * @return      JSON string structured as follows:
     *                  "result"    : integer result (use a constant from this class)
     *                  "salt"      : Base64 encoded string of the salt retrieved from the database.
     */
    @PostMapping("/getSalt")
    public String getSalt(@RequestBody String json) {
        JSONObject obj = new JSONObject(json);
        Database.User user = Database.get(obj.getString("username"));
        System.out.println("SALT: " + user);
        if (user == null) return "{\"result\":" + RESULT_ERROR_USER_HASH_MISMATCH + "}";

        return "{\"result\":" + RESULT_OK + ",\"salt\":\"" + user.getEncodedSalt() + "\"}";
    }

    /**
     * Post Mapping to attempt a login.
     * First, retrieve the hash from the database for the given username, then check it against the given hash.
     * Give a RESULT_LOGGED_IN if they match and RESULT_ERROR_USER_HASH_MISMATCH if they don't.
     * For any errors or for nonexistent accounts return RESULT_USER_HASH_MISMATCH.
     * @param json  JSON Object structured as follows:
     *                  "username"  : string for account username,
     *                  "hash"      : Base64 string of the 256 byte hashed password+salt to check against DB stored hash
     * @return      JSON string structured as follows:
     *                  "result"    : integer result (use a constant from this class)
     */
    @PostMapping("/login")
    public String login(@RequestBody String json) {
        JSONObject obj = new JSONObject(json);
        Database.User user = Database.get(obj.getString("username"));
        System.out.println("LOGIN: " + user);
        if (user == null) return "{\"result\":" + RESULT_ERROR_USER_HASH_MISMATCH + "}";
        else if (user.getEncodedHash().trim().equals(obj.getString("hash").trim())) return "{\"result\":" + RESULT_LOGGED_IN + "}";

        return "{\"result\":" + RESULT_ERROR_USER_HASH_MISMATCH + "}";
    }

    /**
     * Post Mapping to register a new account.
     * First, check the database for an existing account. If there is none, store the given values into the database.
     * Email checking doesn't work, and logic probably needs to be changed.
     *
     * @param json  JSON Object structured as follows:
     *                  "username"  : string for account username,
     *                  "email"     : string for account email,
     *                  "salt"      : Base64 string of the 16 byte salt value,
     *                  "hash"      : Base64 string of the 256 byte hashed password+salt
     * @return      JSON string structured as follows:
     *                  "result"    : integer result (use a constant from this class)
     */
    @PostMapping("/register")
    public String register(@RequestBody String json) {
        JSONObject obj = new JSONObject(json);
        Database.User user = Database.get(obj.getString("username"));
        if (user != null) {
            if (user.getEmail().equals(obj.getString("email"))) {
                return "{\"result\":" + RESULT_ERROR_EMAIL_TAKEN + "}";
            }
            return "{\"result\":" + RESULT_ERROR_USERNAME_TAKEN + "}";
        }

        try {
            Database.User user1 = new Database.User(
                    obj.getString("username"),
                    obj.getString("email"),
                    Base64.decodeBase64(obj.getString("salt")),
                    Base64.decodeBase64(obj.getString("hash")));
            System.out.println("REGISTER: " + user1);
            Database.put(user1);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"result\":" + RESULT_ERROR + "}";
        }

        return "{\"result\":" + RESULT_USER_CREATED + "}";
    }
}