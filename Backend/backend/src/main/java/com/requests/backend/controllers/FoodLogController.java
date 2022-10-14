package com.requests.backend.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.requests.backend.models.FoodLog;
import com.requests.backend.models.Token;
import com.requests.backend.models.User;
import com.requests.backend.models.requests.FoodLogAddRequest;
import com.requests.backend.models.requests.FoodLogGetDayRequest;
import com.requests.backend.models.responses.FoodLogGetResponse;
import com.requests.backend.models.responses.ResultResponse;
import com.requests.backend.repositories.FoodLogRepository;
import com.requests.backend.repositories.ShoppingListRepository;
import com.requests.backend.repositories.TokenRepository;
import com.requests.backend.repositories.UserRepository;
import com.util.security.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

import static com.util.Constants.*;

@RestController
@RequestMapping(path = "/log")
public class FoodLogController {

    @Autowired
    private FoodLogRepository foodLogRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @GetMapping (path = "/get/{token}")
    public @ResponseBody String getFoodLog(@PathVariable String token) {
        String hashedToken = Hasher.sha256(token);
        Token[] tokenQueryRes = tokenRepository.queryGetToken(hashedToken);

        FoodLogGetResponse res = new FoodLogGetResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String username = tokenQueryRes[0].getUsername();
            FoodLog[] foodLog = foodLogRepository.queryGetFoodLog(username);

            res.setFoodLog(foodLog);
            res.setResult(RESULT_OK);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();

        return gson.toJson(res);
    }

    @GetMapping (path = "/getDay/{token}")
    public @ResponseBody String getLogByDay(@PathVariable String token, @RequestBody String json) {

        Gson gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();

        FoodLogGetDayRequest req = gson.fromJson(json, FoodLogGetDayRequest.class);

        String hashedToken = Hasher.sha256(token);
        Token[] tokenQueryRes = tokenRepository.queryGetToken(hashedToken);

        FoodLogGetResponse res = new FoodLogGetResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String username = tokenQueryRes[0].getUsername();
            Date date = req.getDate();

            FoodLog[] foodLog = foodLogRepository.queryGetLogByDay(username, date);

            res.setFoodLog(foodLog);
            res.setResult(RESULT_OK);
        }

        return gson.toJson(res);
    }

    @PostMapping(path = "/add/{token}")
    public @ResponseBody String addToLog(@PathVariable String token, @RequestBody String json) {

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        FoodLogAddRequest req = gson.fromJson(json, FoodLogAddRequest.class);

        String hashedToken = Hasher.sha256(token);
        Token[] tokenQueryRes = tokenRepository.queryGetToken(hashedToken);

        ResultResponse res = new ResultResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String username = tokenQueryRes[0].getUsername();

            foodLogRepository.queryAddToLog(username, req.getFdcId(), req.getFoodName(), req.getServingAmt(),
                    req.getServingUnit(), req.getFat(), req.getSatFat(), req.getSodium(), req.getCarbohydrates(),
                    req.getFiber(), req.getSugars(), req.getProtein(), req.getDate(), req.getMeal());

            res.setResult(RESULT_OK);
        }

        return gson.toJson(res);
    }

    @DeleteMapping (path = "/remove/{token}")
    public @ResponseBody String removeFromLog(@PathVariable String token, @RequestParam int logId) {

        String hashedToken = Hasher.sha256(token);
        Token[] tokenQueryRes = tokenRepository.queryGetToken(hashedToken);

        ResultResponse res = new ResultResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR);
        }
        else {
            foodLogRepository.queryDeleteFromLog(logId);
            res.setResult(RESULT_OK);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }
}
