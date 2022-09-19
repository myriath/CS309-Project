package com.example.backend;

import com.example.demo.rest.Common;
import com.example.demo.rest.Food;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
class FoodController {
    @Autowired
    private ObjectMapper objectMapper;

    // Search for a food item by its name.
    @GetMapping("/foodsearch/{foodName}")
    public String foodSearch(@PathVariable String foodName) {
        String uri = "https://trackapi.nutritionix.com/v2/search/instant?query=" + foodName;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.set("x-app-id", "b7e6f2dc");
        headers.set("x-app-key", "c24ea00b49959892af532ec574e91165");

        //Create a new HttpEntity
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri, HttpMethod.GET, entity, String.class);

        String res = responseEntity.toString();

        return res;
    }

    @GetMapping("/foodsearchobject/{foodName}")
    public String foodSearchObject(@PathVariable String foodName) throws JsonProcessingException {
        String uri = "https://trackapi.nutritionix.com/v2/search/instant?query=" + foodName;

        // Initialize a new rest template and a new set of headers
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        // Set API ID and API key as request headers
        headers.set("x-app-id", "b7e6f2dc");
        headers.set("x-app-key", "c24ea00b49959892af532ec574e91165");

        // Create a new HttpEntity using the headers
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        // Gather a response entity from the designated URI as a String
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri, HttpMethod.GET, entity, String.class);

        // Read the response body into a Java Object of type "Food" from the rest package
        Food responseObject = objectMapper.readValue(responseEntity.getBody(), Food.class);

        return responseObject;
    }



}

