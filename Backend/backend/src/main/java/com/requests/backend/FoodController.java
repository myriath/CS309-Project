package com.requests.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping(path="/usda")
class FoodController {
    @Autowired
    private ObjectMapper objectMapper;

    // Finds top 8 search results
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
