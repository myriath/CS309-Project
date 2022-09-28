package com.requests.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.requests.backend.models.FoodRepository;
import com.requests.backend.models.SearchFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@RestController
class FoodController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FoodRepository foodRepository;

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

    @GetMapping("/foodByID/{fdcId}")
    public String foodByID(@PathVariable String fdcId) throws JsonProcessingException {
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

    @GetMapping("/dbFoodSearch")
    public String dbFoodSearch(@RequestParam String foodName) throws JsonProcessingException {

        // Split each keyword by spaces into an array
        String[] keywordsArr = foodName.split(" ");

        // Add a '+' prefix to each keyword in the array
        for (int i = 0; i < keywordsArr.length; i++) {
            keywordsArr[i] = "+" + keywordsArr[i];
        }

        // Rejoin each word (now with a '+' prefix) into a single string
        String keywords = String.join(" ", keywordsArr);

        // Pass the string (Ex: "+golden +oreo") into the food search query
        List<SearchFood> res = foodRepository.queryGetDbFoods(keywords);

        // Parse the list of search results into a JSON object and return (via GSON)
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(res);
    }
}

