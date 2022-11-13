package com.requests.backend.controllers;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.requests.backend.models.CustomFood;
import com.requests.backend.models.FoodResponse;
import com.requests.backend.models.FoodsResponse;
import com.requests.backend.models.Token;
import com.requests.backend.models.requests.CustomFoodRequest;
import com.requests.backend.models.responses.CustomFoodResponse;
import com.requests.backend.models.responses.ShoppingListGetResponse;
import com.requests.backend.repositories.CustomRepository;
import com.requests.backend.repositories.TokenRepository;
import com.util.security.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.util.Constants.*;

/**
 * This class is responsible for handling all requests related to custom foods.
 *
 * @author Logan
 * @author Mitch
 */
@RestController
@RequestMapping(path="/food")
public class CustomController {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private CustomRepository customRepository;

    /**
     * Get custom foods that best match the given query.
     * @param query
     * @return JSON string containing the results.
     * @throws JsonProcessingException
     */
    @GetMapping("/get")
    public String get(@RequestParam String query) throws JsonProcessingException {

        customRepository.queryGetCustomFoods(query);
        FoodsResponse res = new FoodsResponse();

        CustomFood[] foods = customRepository.findByNameContainingIgnoreCase(query);

        res.setItems(foods);

        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "="
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    /**
     * Get information about a specific custom food by its ID.
     * @param id
     * @return JSON string containing information about the food item.
     * @throws JsonProcessingException
     */
    @GetMapping("/get/{id}")
    public String get(@PathVariable int id) throws JsonProcessingException {
        FoodResponse res = new FoodResponse();

        CustomFood[] foods = customRepository.queryGetByID(id);

        if (foods.length == 0) {
            res.setResult(RESULT_ERROR);
        } else {
            res.setItem(foods[0]);
            res.setResult(RESULT_OK);
        }
        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "="
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    /**
     * Add a new custom food item to the database.
     * @param token
     * @param req
     * @return JSON string containing the result of the operation.
     * @throws JsonProcessingException
     */
    @PostMapping("/add/{token}")
    public @ResponseBody String add(@PathVariable String token, @RequestBody CustomFoodRequest req) throws JsonProcessingException {

        String hashedToken = Hasher.sha256(token);

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        // Find token in table
        Token[] tokenQueryRes = tokenRepository.queryGetToken(hashedToken);

        CustomFoodResponse res = new CustomFoodResponse();

        // If the token doesn't exist in the table, return error.
        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {

//            CustomFoodRequest req = gson.fromJson(req, CustomFoodRequest.class);

            CustomFood food = req.getItem();
                CustomFood savedFood = new CustomFood(food.getName(), food.getIngredients(), food.getCalories(), food.getCarbs(), food.getProtein(), food.getFat());
                savedFood = customRepository.save(savedFood);
                int dbId = savedFood.getDbId();

                res.setResult(RESULT_OK);
                res.setDbId(dbId);
        }

        return gson.toJson(res);
    }





}
