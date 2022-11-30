package com.requests.backend.controllers;

import com.requests.backend.models.FoodLog;
import com.requests.backend.models.Token;
import com.requests.backend.models.requests.FoodLogAddRequest;
import com.requests.backend.models.responses.FoodLogGetResponse;
import com.requests.backend.models.responses.ResultResponse;
import com.requests.backend.repositories.FoodLogRepository;
import com.requests.backend.repositories.TokenRepository;
import com.util.security.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

import static com.util.Constants.*;

/**
 * This class is responsible for handling all requests related to the food log.
 * All methods return a JSON string.
 * @author Logan
 */
@RestController
@RequestMapping(path = "/log")
public class FoodLogController {

    @Autowired
    private FoodLogRepository foodLogRepository;
    @Autowired
    private TokenRepository tokenRepository;

    /**
     * Gets all food log entries for a user
     * @param token     The provided token of the user
     * @return          A JSON array containing all food log entries for the given date
     */
    @GetMapping (path = "/get/{token}")
    public @ResponseBody FoodLogGetResponse getFoodLog(@PathVariable String token) {
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

        return res;
    }

    /**
     * Gets the food log entries for a user for a specific date
     * @param token     The provided token of the user
     * @param date      A request parameter containing the desired date
     * @return          A JSON array containing all food log entries for the given date
     */
    @GetMapping (path = "/getDay/{token}")
    public @ResponseBody FoodLogGetResponse getLogByDay(@PathVariable String token, @RequestParam Date date) {
        String hashedToken = Hasher.sha256(token);
        Token[] tokenQueryRes = tokenRepository.queryGetToken(hashedToken);

        FoodLogGetResponse res = new FoodLogGetResponse();

        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            String username = tokenQueryRes[0].getUsername();

            FoodLog[] foodLog = foodLogRepository.queryGetLogByDay(username, date);

            res.setFoodLog(foodLog);
            res.setResult(RESULT_OK);
        }

        return res;
    }

    /**
     * Adds a food log entry associated with a user
     * @param token  The provided token of the user
     * @param req   JSON object containing all food information (fdcId, foodName, nutrients etc.)
     * @return       JSON response containing result code
     */
    @PostMapping(path = "/add/{token}")
    public @ResponseBody ResultResponse addToLog(@PathVariable String token, @RequestBody FoodLogAddRequest req) {
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

        return res;
    }

    /**
     * Removes a log entry of a user of the database based on a given logId (PK of food_log)
     *
     * @param token  The provided token of the user
     * @param logId  The logId of the entry the user wants to remove
     * @return ResultResponse containing the result data
     */
    @DeleteMapping (path = "/remove/{token}")   // /remove/{token}?logId=
    public @ResponseBody ResultResponse removeFromLog(@PathVariable String token, @RequestParam int logId) {

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

        return res;
    }
}
