package com.requests.backend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.requests.backend.models.CustomFood;
import com.requests.backend.models.FoodsResponse;
import com.requests.backend.models.requests.CustomFoodRequest;
import com.requests.backend.models.responses.CustomFoodResponse;
import com.requests.backend.repositories.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.util.Constants.RESULT_ERROR;
import static com.util.Constants.RESULT_OK;

@RestController
@RequestMapping(path="/food")
public class CustomController {

    @Autowired
    private CustomRepository customRepository;

    @GetMapping("/get")
    public String get(@RequestParam String query) throws JsonProcessingException {

        customRepository.queryGetCustomFoods(query);
        FoodsResponse res = new FoodsResponse();

        CustomFood[] foods = customRepository.findByNameLike("%" + query + "%");

        res.setItems(foods);

        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "="
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    @PostMapping("/add")
    public @ResponseBody String add(@RequestBody String json) throws JsonProcessingException {

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        CustomFoodRequest req = gson.fromJson(json, CustomFoodRequest.class);

        CustomFood food = req.getItem();

        CustomFoodResponse res = new CustomFoodResponse();

        try {
            CustomFood savedFood = new CustomFood(food.getName(), food.getCalories(), food.getCarbs(), food.getProtein(), food.getFat());
            savedFood = customRepository.save(savedFood);
            int dbId = savedFood.getDbId();

            res.setResult(RESULT_OK);
            res.setDbId(dbId);
        } catch (Exception e) {
            res.setResult(RESULT_ERROR);
        }

        return gson.toJson(res);
    }





}
