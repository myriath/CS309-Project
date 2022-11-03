package com.requests.backend.controllers;

import com.google.gson.GsonBuilder;
import com.requests.backend.models.*;
import com.requests.backend.models.responses.ProfileResponse;
import com.requests.backend.repositories.ProfileRepository;
import com.requests.backend.repositories.TokenRepository;
import com.util.security.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import static com.util.Constants.*;



@RestController
@RequestMapping(path="/profile")
public class ProfileController {
    public static final int RESULT_ERROR_USERNAME_NON_EXISTANT = -43;

    public static final int RESULT_PROFILE_CREATED = 44;




    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private TokenRepository tokenRepository;

/*
    @GetMapping("/getBio/{username}")
    public @ResponseBody String getBio(@PathVariable String username) {
        Profile[] profiles = profileRepository.queryGetBio(username);
        ProfileResponse res = new ProfileResponse();

        if (profiles.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        } else {
            res.Profile(profiles[0]);
            res.setResult(RESULT_OK);

        }

        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "="
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        //return gson.toJson(res.getProfile().getBio());
    }

    @GetMapping("/Profile/{username}")
    public @ResponseBody String getProfile(@PathVariable String username) {
        Profile[] profiles = profileRepository.queryGetBio(username);
        ProfileResponse res = new ProfileResponse();

        if (profiles.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        } else {
            res.setProfile(profiles[0]);
            res.setResult(RESULT_OK);

        }

        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "="
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res.getProfile());
    }
*/



    @PostMapping(path = "/updateProfile/{token}")
    @ResponseBody
    public String updateProfile(@PathVariable String token, @RequestBody String json) {
        String hashedToken = Hasher.sha256(token);
        Token[] tokenQueryRes = tokenRepository.queryGetToken(token);

        Profile req =  new Gson().fromJson(json, Profile.class);

        String username = req.getUserName();
        String bio = req.getBio();

        profileRepository.queryDeleteProfile(username);


        ProfileResponse res = new ProfileResponse();

        if(tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        } else {
            try {
                profileRepository.queryCreateProfile(username, bio);
                res.setResult(RESULT_PROFILE_CREATED);
            } catch (Exception e) {
                res.setResult(RESULT_ERROR_USERNAME_NON_EXISTANT);
            }
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }




}
