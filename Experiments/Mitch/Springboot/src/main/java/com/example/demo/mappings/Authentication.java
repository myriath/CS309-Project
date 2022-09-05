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
 *
 * @author Mitch Hudson
 */
@RestController
@RequestMapping("/auth")
public class Authentication {
    public static final int RESULT_ERROR = -1;
    public static final int RESULT_ERROR_USER_HASH_MISMATCH = -2;
    public static final int RESULT_ERROR_USERNAME_TAKEN = -3;
    public static final int RESULT_ERROR_EMAIL_TAKEN = -4;

    public static final int RESULT_OK = 0;
    public static final int RESULT_LOGGED_IN = 1;
    public static final int RESULT_USER_CREATED = 2;

    @PostMapping("/getSalt")
    public String getSalt(@RequestBody String json) {
        JSONObject obj = new JSONObject(json);
        Database.User user = Database.get(obj.getString("username"));
        if (user == null) return "{\"result\":" + RESULT_ERROR_USER_HASH_MISMATCH + "}";

        return "{\"result\":" + RESULT_OK + ",\"salt\":\"" + user.getEncodedSalt() + "\"}";
    }

    @PostMapping("/login")
    public String login(@RequestBody String json) {
        JSONObject obj = new JSONObject(json);
        Database.User user = Database.get(obj.getString("username"));
        if (user == null) return "{\"result\":" + RESULT_ERROR_USER_HASH_MISMATCH + "}";
        else if (user.getEncodedHash().trim().equals(obj.getString("hash").trim())) return "{\"result\":" + RESULT_LOGGED_IN + "}";

        return "{\"result\":" + RESULT_ERROR_USER_HASH_MISMATCH + "}";
    }

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
            Database.put(new Database.User(
                    obj.getString("username"),
                    obj.getString("email"),
                    Base64.decodeBase64(obj.getString("salt")),
                    Base64.decodeBase64(obj.getString("hash"))));
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"result\":" + RESULT_ERROR + "}";
        }

        return "{\"result\":" + RESULT_USER_CREATED + "}";
    }
}