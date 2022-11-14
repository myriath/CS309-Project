package com.requests.backend.controllers;

import com.requests.backend.models.Favorite;
import com.requests.backend.models.User;
import com.requests.backend.models.requests.UpdateProfileRequest;
import com.requests.backend.models.responses.ProfileResponse;
import com.requests.backend.models.responses.ResultResponse;
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
public class ProfileEndpointController {

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
     * @param username Username of the profile to get
     * @return JSON object containing the user's profile information.
     */
    @GetMapping(path="/getProfile/{username}")
    public @ResponseBody ProfileResponse getProfile(@PathVariable String username) {
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

        return res;
    }

    /**
     * Get a user's profile picture based on a provided username.
     * @param username Username to get the profile picture of
     * @return The user's profile picture as a JSON byte array.
     * @throws IOException Thrown when there is an error reading the file
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
     * @param username Username to get the banner image of
     * @return The user's profile banner as a JSON byte array.
     * @throws IOException Thrown when there is an error reading the file
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
     * @param token Token for authentication
     * @param req Request containing the new bio
     * @return JSON string containing the result of the update attempt.
     */
    @PatchMapping(path="/updateProfile/{token}")
    public @ResponseBody ResultResponse updateProfile(@PathVariable String token, @RequestBody UpdateProfileRequest req) {
        return UserController.getUsernameFromToken(token, (username, res) -> userRepository.queryUpdateBio(username, req.getNewBio()), tokenRepository);
    }

    /**
     * Update a user's profile picture.
     * @param token Token for authentication
     * @param file New profile picture in webp format
     * @return JSON string containing the result of the update attempt.
     */
    @PatchMapping(path="/updatePfp/{token}")
    public @ResponseBody ResultResponse updatePFP(@PathVariable String token, @RequestParam("image") MultipartFile file) {
        return updateImage(token, file, PFP_SOURCE);
    }

    /**
     * Update a user's profile banner.
     * @param token Token for authentication
     * @param file New banner image in webp format
     * @return JSON string containing the result of the update attempt.
     */
    @PatchMapping(path="/updateBanner/{token}")
    public @ResponseBody ResultResponse updateBanner(@PathVariable String token, @RequestParam("image") MultipartFile file) {
        return updateImage(token, file, BANNER_SOURCE);
    }

    /**
     * Updates an image on the server
     * @param token Token for authentication
     * @param file New image to replace
     * @param basePath Base path to the destination
     * @return Result response with the result code to be handled by the backend
     */
    public ResultResponse updateImage(String token, MultipartFile file, String basePath) {
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
     * Get a user's favorite foods based on a provided username.
     * @param username Username to get the favorites of
     * @return JSON string containing the user's favorite foods.
     */
    @GetMapping(path="/getFavoritesByUsername/{username}")
    public @ResponseBody String getFavoritesByUsername(@PathVariable String username) {
        Collection<Favorite> favorites = favoriteRepository.queryGetFavorites(username);
        return favorites.iterator().next().getFoodName();
    }
}
