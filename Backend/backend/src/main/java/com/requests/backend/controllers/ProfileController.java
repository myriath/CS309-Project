package com.requests.backend.controllers;

import com.requests.backend.models.Favorite;
import com.requests.backend.models.User;
import com.requests.backend.models.requests.UpdateProfileRequest;
import com.requests.backend.models.responses.ProfileResponse;
import com.requests.backend.repositories.FavoriteRepository;
import com.requests.backend.repositories.FollowRepository;
import com.requests.backend.repositories.TokenRepository;
import com.requests.backend.repositories.UserRepository;
import com.util.security.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Collection;

import static com.util.Constants.*;

/**
 * This controller handles all requests related to the user's individual profile.
 * This includes viewing the profile, updating the profile, and viewing the user's profile picture and banner.
 * @author Mitch
 */
@RestController
@RequestMapping(path="/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private FollowRepository followRepository;

    /**
     * Get profile information based on a provided username.
     * @param username
     * @return JSON object containing the user's profile information.
     */
    @GetMapping(path="/getProfile/{username}")
    public @ResponseBody String getProfile(@PathVariable String username) {
        ProfileResponse res = new ProfileResponse();

        Collection<User> users = userRepository.queryGetUserByUsername(username);
        if (users.isEmpty()) {
            res.setResult(RESULT_ERROR);
        } else {
            User user = (User) users.toArray()[0];
            res.setResult(RESULT_OK);

            res.setFollowers(followRepository.queryGetFollowers(username).length);
            res.setFollowing(followRepository.queryGetFollowing(username).length);
            res.setBio(user.getBio());
        }

        return GSON.toJson(res);
    }

    /**
     * Get a user's profile picture based on a provided username.
     * @param username
     * @return The user's profile picture as a JSON byte array.
     * @throws IOException
     */
    @GetMapping(path="/profilePicture/{username}", produces="image/webp")
    public @ResponseBody byte[] getPFP(@PathVariable String username) throws IOException {
        File img = new File(PFP_SOURCE, Hasher.sha256plaintext(username) + ".webp");
        if (!img.exists()) {
            img = new File(DEFAULT_PFP);
        }
        InputStream in = new DataInputStream(new FileInputStream(img));
        return in.readAllBytes();
    }

    /**
     * Get a user's profile banner based on a provided username.
     * @param username
     * @return The user's profile banner as a JSON byte array.
     * @throws IOException
     */
    @GetMapping(path="/profileBanner/{username}", produces="image/webp")
    public @ResponseBody byte[] getBanner(@PathVariable String username) throws IOException {
        File img = new File(BANNER_SOURCE, Hasher.sha256plaintext(username) + ".webp");
        if (!img.exists()) {
            img = new File(DEFAULT_BANNER);
        }
        InputStream in = new DataInputStream(new FileInputStream(img));
        return in.readAllBytes();
    }

    /**
     * Update a user's profile information.
     * @param token
     * @param json
     * @return JSON string containing the result of the update attempt.
     */
    @PatchMapping(path="/updateProfile/{token}")
    public @ResponseBody String updateProfile(@PathVariable String token, @RequestBody String json) {
        return UserController.getUsernameFromToken(token, (username, res) -> {
            UpdateProfileRequest request = GSON.fromJson(json, UpdateProfileRequest.class);
            userRepository.queryUpdateBio(username, request.getNewBio());
        }, tokenRepository);
    }

    /**
     * Update a user's profile picture.
     * @param token
     * @param file
     * @return JSON string containing the result of the update attempt.
     */
    @PatchMapping(path="/updatePfp/{token}")
    public @ResponseBody String updatePFP(@PathVariable String token, @RequestParam("image") MultipartFile file) {
        return updateImage(token, file, PFP_SOURCE);
    }

    /**
     * Update a user's profile banner.
     * @param token
     * @param file
     * @return JSON string containing the result of the update attempt.
     */
    @PatchMapping(path="/updateBanner/{token}")
    public @ResponseBody String updateBanner(@PathVariable String token, @RequestParam("image") MultipartFile file) {
        return updateImage(token, file, BANNER_SOURCE);
    }

    public String updateImage(String token, MultipartFile file, String basePath) {
        return UserController.getUsernameFromToken(token, (username, res) -> {
            String filename = Hasher.sha256plaintext(username) + ".webp";
            File file1 = new File(basePath, filename);
            try (FileOutputStream outputStream = new FileOutputStream(file1)) {
                outputStream.write(file.getBytes());
                res.setResult(RESULT_OK);
            } catch (IOException e) {
                e.printStackTrace();
                res.setResult(RESULT_ERROR);
            }
        }, tokenRepository);
    }

    /**
     *
     * @return
     */
    @GetMapping(path="/dbquery")
    public @ResponseBody String dbQuery() {
        Collection<User> users = userRepository.queryGetAllUsers();
        User papa = users.iterator().next();
        return papa.getUsername();
    }

    /**
     * Get a user's favorite foods based on a provided username.
     * @param username
     * @return JSON string containing the user's favorite foods.
     */
    @GetMapping(path="/getFavoritesByUsername/{username}")
    public @ResponseBody String getFavoritesByUsername(@PathVariable String username) {
        Collection<Favorite> favorites = favoriteRepository.queryGetFavorites(username);
        return favorites.iterator().next().getFoodName();
    }

    /**
     * Get more information about a user based on a provided username.
     * @param username
     * @return JSON string containing the user's information.
     */
    @GetMapping(path="/getUserByUsername/{username}")
    public @ResponseBody String getUserByUsername(@PathVariable String username) {
        Collection<User> user = userRepository.queryGetUserByUsername(username);
        return user.iterator().next().getUsername();
    }
}
