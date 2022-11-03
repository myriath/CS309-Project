package com.requests.backend.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.requests.backend.models.Favorite;
import com.requests.backend.models.Token;
import com.requests.backend.models.User;
import com.requests.backend.models.requests.RegisterRequest;
import com.requests.backend.models.requests.UpdateProfileRequest;
import com.requests.backend.models.responses.LoginResponse;
import com.requests.backend.models.responses.ResultResponse;
import com.requests.backend.models.responses.SaltResponse;
import com.requests.backend.repositories.FavoriteRepository;
import com.requests.backend.repositories.TokenRepository;
import com.requests.backend.repositories.UserRepository;
import com.util.security.Hasher;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Collection;

import static com.util.Constants.*;

@RestController
@RequestMapping(path="/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @GetMapping(path="/profilePicture/{username}", produces="image/webp")
    public @ResponseBody byte[] getPFP(@PathVariable String username) throws IOException {
        File img = new File(PFP_SOURCE, Hasher.sha256plaintext(username) + ".webp");
        if (!img.exists()) {
            img = new File(DEFAULT_PFP);
        }
        InputStream in = new DataInputStream(new FileInputStream(img));
        return in.readAllBytes();
    }

    @GetMapping(path="/profileBanner/{username}", produces="image/webp")
    public @ResponseBody byte[] getBanner(@PathVariable String username) throws IOException {
        File img = new File(BANNER_SOURCE, Hasher.sha256plaintext(username) + ".webp");
        if (!img.exists()) {
            img = new File(DEFAULT_BANNER);
        }
        InputStream in = new DataInputStream(new FileInputStream(img));
        return in.readAllBytes();
    }

    @PatchMapping(path="/updateProfile/{token}")
    public @ResponseBody String updateProfile(@PathVariable String token, @RequestBody String json) {
        return UserController.getUsernameFromToken(token, (username, res) -> {
            UpdateProfileRequest request = GSON.fromJson(json, UpdateProfileRequest.class);
            userRepository.queryUpdateBio(username, request.getNewBio());
        }, tokenRepository);
    }

    @PatchMapping(path="/updatePfp/{token}")
    public @ResponseBody String updatePFP(@PathVariable String token, @RequestParam("image") MultipartFile file) {
        return updateImage(token, file, PFP_SOURCE);
    }

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
}
