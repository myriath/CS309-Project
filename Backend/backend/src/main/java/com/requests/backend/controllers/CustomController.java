package com.requests.backend.controllers;

import com.requests.backend.models.CustomFood;
import com.requests.backend.models.FoodResponse;
import com.requests.backend.models.FoodsResponse;
import com.requests.backend.models.Token;
import com.requests.backend.models.requests.CustomFoodRequest;
import com.requests.backend.models.responses.CustomFoodResponse;
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
     * @param query Search query, this endpoint returns foods that have this in the name
     * @return JSON string containing the results.
     */
    @GetMapping("/get")
    public @ResponseBody FoodsResponse get(@RequestParam String query) {

        customRepository.queryGetCustomFoods(query);
        FoodsResponse res = new FoodsResponse();

        CustomFood[] foods = customRepository.findByNameContainingIgnoreCase(query);

        res.setItems(foods);

        return res;
    }

    /**
     * Get information about a specific custom food by its ID.
     * @param id ID of the item to get
     * @return JSON string containing information about the food item.
     */
    @GetMapping("/get/{id}")
    public @ResponseBody FoodResponse get(@PathVariable int id) {
        FoodResponse res = new FoodResponse();

        CustomFood[] foods = customRepository.queryGetByID(id);

        if (foods.length == 0) {
            res.setResult(RESULT_ERROR);
        } else {
            res.setItem(foods[0]);
            res.setResult(RESULT_OK);
        }

        return res;
    }

    /**
     * Add a new custom food item to the database.
     * @param token Token for authentication
     * @param req Custom food request containing the new custom food data
     * @return JSON string containing the result of the operation.
     */
    @PostMapping("/add/{token}")
    public @ResponseBody CustomFoodResponse add(@PathVariable String token, @RequestBody CustomFoodRequest req) {

        String hashedToken = Hasher.sha256(token);

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

        return res;
    }
}
