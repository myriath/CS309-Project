package com.example.demo;

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
class ExampleController {

    ArrayList<String> exampleList = new ArrayList<>();

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

        // Read the response body into a Java Object of type "Example" from the rest package
        Food responseObject = objectMapper.readValue(responseEntity.getBody(), Food.class);

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

    // Experiment 6: Example CRUDL Functionality
    @GetMapping("/getFoods")
    public @ResponseBody ArrayList<String> getAllFoods() {
        return exampleList;
    }

    @PostMapping("/newFood")
    public @ResponseBody String addFood(@RequestBody String food) {
        System.out.println(food);
        exampleList.add(food);
        return "New food added to list: " + food;
    }

    @GetMapping("/foodList/{foodName}")
    public @ResponseBody String getFoodByName(@PathVariable String foodName) {
        String p = "Food does not exist in list!";
        if (exampleList.contains(foodName)) {
            p = exampleList.get(exampleList.indexOf(foodName));
        }
        return p;
    }

    @PutMapping("/foodList/{foodName}")
    public @ResponseBody String updatePerson(@PathVariable String foodName, @RequestBody Common p) {
        exampleList.add(exampleList.indexOf(p.getFoodName()), foodName);
        return exampleList.get(exampleList.indexOf(foodName));
    }

    @DeleteMapping("/foodList/{foodName}")
    public @ResponseBody ArrayList<String> deleteFoodName(@PathVariable String foodName) {
        exampleList.remove(foodName);
        return exampleList;
    }




}

