package com.requests.backend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.requests.backend.models.CustomFood;
import com.requests.backend.models.FoodsResponse;
import com.requests.backend.models.requests.CustomFoodRequest;
import com.requests.backend.repositories.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/food")
public class CustomController {

    @Autowired
    private CustomRepository customRepository;

    @GetMapping("/get")
    public String get(@RequestParam String query) throws JsonProcessingException {

        customRepository.queryGetCustomFoods(query);
        FoodsResponse res = new FoodsResponse();
        res.setItems(customRepository.queryGetCustomFoods(query));

        // Create a new GSON Builder and disable escaping (to allow for certain unicode characters like "="
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    @PostMapping("/add")
    public void add(@RequestBody String json) throws JsonProcessingException {

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        CustomFoodRequest req = gson.fromJson(json, CustomFoodRequest.class);

        CustomFood food = req.getItem();

        customRepository.queryCreateCustomFood(food.getName(), food.getCalories(), food.getFat(), food.getCarbs(), food.getProtein());
    }


}
