package com.requests.backend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * This class is responsible for handling all requests related to custom foods.
 * @author Logan
 */
@RestController
@RequestMapping(path="/usda")
class FoodController {

    /**
     * This method is used to get the food information from the USDA database.
     * @param foodName
     * @return A list of foods that match the query.
     * @throws JsonProcessingException
     */
    @GetMapping("/usdaFoodSearch/{foodName}")
    public String usdaFoodSearch(@PathVariable String foodName) throws JsonProcessingException {
        String uri = "https://api.nal.usda.gov/fdc/v1/foods/search?query=" + foodName + "&pageSize=6&requireAllWords=true&api_key=CK8FPcJEM6vXFDHGk80hTpWQg9CcWo7z4X7yCavt";

        // Initialize a new rest template and a new set of headers
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        // Create a new HttpEntity using the headers
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        // Gather a response entity from the designated URI as a String
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri, HttpMethod.GET, entity, String.class);

        String res = responseEntity.getBody().toString();

        return res;
    }

    /** Get information about a specific food item from the USDA database using the food's ID.
     * @param fdcId
     * @return A JSON string containing the food's information.
     * @throws JsonProcessingException
     */
    @GetMapping("/foodByID")
    public String foodByID(@RequestParam String fdcId) throws JsonProcessingException {
        String uri = "https://api.nal.usda.gov/fdc/v1/food/" + fdcId + "?api_key=CK8FPcJEM6vXFDHGk80hTpWQg9CcWo7z4X7yCavt";

        // Initialize a new rest template and a new set of headers
        RestTemplate restTemplate = new RestTemplate();

        // Create a new HttpEntity using the headers
        final HttpEntity<Void> entity = new HttpEntity<>(null);

        // Gather a response entity from the designated URI as a String
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri, HttpMethod.GET, entity, String.class);

        String res = responseEntity.getBody().toString();

        return res;
    }
}

