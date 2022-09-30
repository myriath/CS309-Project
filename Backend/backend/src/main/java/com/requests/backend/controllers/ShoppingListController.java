package com.requests.backend.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.requests.backend.models.FavoriteRepository;
import com.requests.backend.models.ShoppingList;
import com.requests.backend.models.User;
import com.requests.backend.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/shopping") // This means URL's start with /demo (after Application path)
public class ShoppingListController {

    @Autowired
    private ShoppingListRepository shoppingRepository;

    @GetMapping(path="/get")
    public @ResponseBody String getAllUsers(@RequestParam String username) {

        List<ShoppingList> listItems = shoppingRepository.queryGetShoppingList(username);

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(listItems);
    }

}
