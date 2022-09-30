package com.requests.backend.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.requests.backend.models.LoginResponse;
import com.requests.backend.models.ShoppingList;
import com.requests.backend.models.ShoppingListAddRequest;
import com.requests.backend.models.StrikeoutRequest;
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
    public @ResponseBody String getShoppingList(@RequestParam String username) {

        List<ShoppingList> listItems = shoppingRepository.queryGetShoppingList(username);

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(listItems);
    }

    @GetMapping(path="/add")
    public @ResponseBody String addToShoppingList(@RequestBody String json) {

        ShoppingListAddRequest req = new Gson().fromJson(json, ShoppingListAddRequest.class);

        String username = req.getUsername();
        String itemName = req.getItemName();
        int fdcId = req.getFdcId();
        boolean stricken = req.getStricken();

        // TODO Create custom response
        LoginResponse res = new LoginResponse();

        try {
            shoppingRepository.queryCreateShoppingListEntry(username, itemName, fdcId, stricken);
            res.setResult(1);
        } catch (Exception e) {
            res.setResult(0);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }


    @PatchMapping (path="/strikeout")
    public @ResponseBody String changeStrikeout(@RequestBody String json) {

        StrikeoutRequest req = new Gson().fromJson(json, StrikeoutRequest.class);

        String username = req.getUsername();
        String itemName = req.getItemName();

        // TODO Create custom response
        LoginResponse res = new LoginResponse();

        try {
            shoppingRepository.queryShoppingChangeStricken(username, itemName);
            res.setResult(1);
        } catch (Exception e) {
            res.setResult(0);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }
}
