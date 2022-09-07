package com.example.demo;

import com.example.demo.rest.Example;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Stream;

@RestController
class ExampleController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/")
    public String welcome() {
        return "Hello and welcome to COM S 309";
    }

    @GetMapping("/{name}")
    public String welcome(@PathVariable String name) {
        return "Hello and welcome to COMS 309: " + name;
    }

    // Experiment 1
    @GetMapping("/experimentOne")
    public String experimentOne() {
        return "This is a test route for Experiment 1!";
    }

    // Experiment 2
    @GetMapping("/experimentTwo/{numbers}")
    public String experimentTwo(@PathVariable String numbers) {
        String[] numList = numbers.split("");
        int sum = 0;

        for (int i = 0; i < numList.length; i++) {
            sum += Integer.valueOf(numList[i]);
        }
        return "This is the route for Experiment 2. \nAdded numbers: " + sum;
    }

    // Experiment 3
    @GetMapping("/APIRequest")
    public String apiRequest() {
        String uri = "https://jsonplaceholder.typicode.com/users";
        RestTemplate restTemplate = new RestTemplate();

        String res = restTemplate.getForObject(uri, String.class);

        return res;
    }

    // Experiment 4.1
    @GetMapping("/bigMac")
    public String bigMac() {
        String uri = "https://trackapi.nutritionix.com/v2//search/instant?query=Big Mac";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.set("x-app-id", "b7e6f2dc");
        headers.set("x-app-key", "c24ea00b49959892af532ec574e91165");

        //Create a new HttpEntity
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri, HttpMethod.GET, entity, String.class);

        String res = responseEntity.getBody();

        return res;
    }

    // Experiment 4.2
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

    // Experiment 5
    @GetMapping("/foodsearchobject/{foodName}")
    public String foodSearchObject(@PathVariable String foodName) throws JsonProcessingException {
        String uri = "https://trackapi.nutritionix.com/v2/search/instant?query=" + foodName;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.set("x-app-id", "b7e6f2dc");
        headers.set("x-app-key", "c24ea00b49959892af532ec574e91165");

        //Create a new HttpEntity
        final HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri, HttpMethod.GET, entity, String.class);

        com.example.demo.rest.Example responseObject = objectMapper.readValue(responseEntity.getBody(), Example.class);

        String[] foodItems = new String[responseObject.getBranded().size()];
        String res = "";

        for (int i = 0; i < foodItems.length; i++) {
            com.example.demo.rest.Branded newFoodItem = responseObject.getBranded().get(i);
            res += "Food Item Name: ";
            res += newFoodItem.getFoodName();
            res += "<br>";

            res += "Calories: ";
            res += newFoodItem.getNfCalories();
            res += "<br>";

            res += "Serving Size: ";
            res += newFoodItem.getServingQty();
            res += " ";
            res += newFoodItem.getServingUnit();
            res += "<br>";

            res += "<br>";
        }

        return res;

    }






}

