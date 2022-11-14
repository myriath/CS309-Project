package com.requests.backend.controllers;

import com.requests.backend.models.*;
import com.requests.backend.models.responses.LoginResponse;
import com.requests.backend.repositories.TokenRepository;
import com.util.security.Hasher;
import com.requests.backend.models.requests.RegisterRequest;
import com.requests.backend.models.responses.ResultResponse;
import com.requests.backend.models.responses.SaltResponse;
import com.requests.backend.repositories.FavoriteRepository;
import com.requests.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Collection;

import static com.util.Constants.*;

/**
 * This class is responsible for handling all requests related to users login, register, etc.
 * @author Logan
 * @author Mitch
 * @author Joe
 */
@RestController
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private TokenRepository tokenRepository;

    /**
     * Gets the salt for a given username.
     * @param username Username to get the salt of
     * @return JSON string containing the salt for the given username.
     */
    @GetMapping(path = "/getSalt/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SaltResponse getSalt(@PathVariable String username) {
        LOGGER.info(username);

        Collection<User> userRes = userRepository.queryValidateUsername(username);

        SaltResponse res = new SaltResponse();

        if (userRes.isEmpty()) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
            res.setSalt(null);
        } else {
            User user = userRes.iterator().next();
            String salt = user.getPSalt();
            res.setResult(RESULT_OK);
            res.setSalt(salt);
        }

        LOGGER.info(res.toString());

        return res;
    }

    /**
     * Checks validity of a given token and returns the username associated with it if valid.
     * If invalid, returns an expired token status.
     * @param token Authentication token
     * @return JSON string containing the username associated with the given token and a status code.
     */
    @GetMapping(path="/validateToken/{token}") // /users/validateLogin/{token}
    public @ResponseBody LoginResponse validateTokenLogin(@PathVariable String token) {
        String hashedToken = Hasher.sha256(token);

        LoginResponse res = new LoginResponse();

        Token[] tokenQuery = tokenRepository.queryGetToken(hashedToken);

        try {
            // If the token is not valid, return RESULT_USER_HASH_MISMATCH
            // TODO: Remove once bug is found
            LOGGER.info(token);
            LOGGER.info(hashedToken);
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
                    res.setUsername(dbToken.getUsername());
                }
                // Otherwise, the user should be logged in, as the token is valid.
                else {
                    res.setResult(RESULT_LOGGED_IN);
                    res.setUsername(dbToken.getUsername());
                }
            }

        // If the server encounters an error, return RESULT_ERROR
        } catch (Exception e) {
            e.printStackTrace();
            res.setResult(RESULT_ERROR);
        }

        LOGGER.info(String.valueOf(res.getResult()));
        return res;
    }

    /**
     * Checks validity of a given username and password and returns a token if valid.
     * @param username The username to check.
     * @param hash The hash of the password.
     * @param newToken Incoming token from the client. If the token is outdated, a new one will be generated.
     * @return JSON string containing the token associated with the given username and password and a status code.
     */
    @GetMapping(path="/validateLogin/{username}") // /users/validateLogin/{username}?hash=""&newToken=""
    public @ResponseBody LoginResponse validateLogin(@PathVariable String username, @RequestParam(name="hash") String hash, @RequestParam(name="newToken") String newToken) {
        String pHash = Hasher.sha256(hash); // SHA-256's the incoming hash
        String tokenHash = Hasher.sha256(newToken); // SHA-256's the incoming token, this is added to the table as another hash for the username

        Collection<User> userRes = userRepository.queryValidateUsername(username);

        LoginResponse res = new LoginResponse(RESULT_OK);

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
                    res.setUsername(username);
                }
                // Otherwise, the token doesn't already exist -- add the hashed token to the tokens table
                else {
                    tokenRepository.queryAddToken(tokenHash, new Date(System.currentTimeMillis()), username);
                    res.setResult(RESULT_LOGGED_IN);
                    res.setUsername(username);
                }

                //       Note: THIS DOES NOT REPLACE ANY OTHER TOKENS!
                //       multiple tokens can be allowed for each user (this lets you log into multiple devices at once)
                //       in the future we'll add an email that goes out whenever someone logs into an account

            }
        }

        return res;
    }

    /**
     * Adds a new user to the database.
     * @param req JSON string containing the username, password, and salt.
     * @return JSON string containing the result code.
     */
    @PostMapping(path="/register")
    public @ResponseBody LoginResponse addNewUser (@RequestBody RegisterRequest req) {
        String username = req.getUsername();
        String email = req.getEmail();
        String pHash = Hasher.sha256(req.getPHash()); // SHA-256's the incoming hash
        String pSalt = req.getPSalt();
        String tokenHash = Hasher.sha256(req.getToken()); // SHA-256's the incoming token

        LoginResponse res = new LoginResponse();

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
                res.setUsername(username);

            // If the username already exists in the user table, return an error result
            } catch (Exception e) {
                res.setResult(RESULT_ERROR);
            }
        }

        return res;
    }

    /**
     * Regenerates a token for a given username given the old, outdated token.
     * @param oldToken The old, outdated token.
     * @param newToken The new token to replace the old one.
     * @return JSON string containing the result code.
     */
    @PutMapping(path="/regenToken/{oldToken}")
    public @ResponseBody ResultResponse regenToken(@PathVariable String oldToken, @RequestParam(name="newToken") String newToken) {
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

        return res;
    }

    /**
     * Get the username associated with a given token.
     * @param token The token to query.
     * @param runner The runner to query.
     * @param tokenRepository The token repository.
     * @return JSON string containing the result code and username.
     */
    public static ResultResponse getUsernameFromToken(String token, RunWithUsername runner, TokenRepository tokenRepository) {
        String hashedToken = Hasher.sha256(token);

        ResultResponse res = new ResultResponse();

        Token[] tokenQuery = tokenRepository.queryGetToken(hashedToken);

        try {
            // If the token is not valid, return RESULT_USER_HASH_MISMATCH
            if (tokenQuery.length == 0) {
                res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
            }
            else {
                runner.run(tokenQuery[0].getUsername(), res);
            }

            // If the server encounters an error, return RESULT_ERROR
        } catch (Exception e) {
            e.printStackTrace();
            res.setResult(RESULT_ERROR);
        }

        return res;
    }

    public interface RunWithUsername {
        void run(String username, ResultResponse res);
    }
}
