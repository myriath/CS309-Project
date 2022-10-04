package com.requests.backend.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.requests.backend.models.*;
import com.requests.backend.models.responses.ShoppingListGetResponse;
import com.requests.backend.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static com.requests.backend.ResultCodes.*;

@RestController
@RequestMapping(path="/shopping")
public class ShoppingListController {

    @Autowired
    private ShoppingListRepository shoppingRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Gets the users shopping list if the hash provided is valid.
     * If the user does not exist return error code with empty shopping list.
     * If the user exists, but the hash is incorrect, return hash mismatch error code with empty list.
     * Otherwise, the credentials are valid: return "OK" result code and shopping list for associated user.
     * @param username
     * @param hash
     * @return
     */
    @GetMapping(path="/get/{username}")
    public @ResponseBody String getShoppingList(@PathVariable String username, @RequestParam String hash) {

        Collection<User> userQueryRes = userRepository.queryValidateUsername(username);

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        ShoppingListGetResponse res = new ShoppingListGetResponse();

        if (userQueryRes.isEmpty()) {
            res.setResult(RESULT_ERROR);
        }
        // If the credentials aren't valid, return hash mismatch error code.
        else {

            List<ShoppingList> shoppingList = shoppingRepository.queryGetShoppingList(username);
            return gson.toJson(shoppingList);

//            User user = userQueryRes.iterator().next();
//
//            List<ShoppingList> listItems = shoppingRepository.queryGetShoppingList(username);
//
//            if (user.getPHash().compareTo(hash) != 0) {
//                res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
//            }
//            // Otherwise, the user credentials are valid. Return the user's shopping list.
//            else {
//                res.setResult(RESULT_OK);
//                res.setShoppingList(listItems);
//            }
        }
        return gson.toJson(res);

    }

    @PostMapping(path="/add")
    public @ResponseBody String addToShoppingList(@RequestBody String json) {

        ShoppingListAddRequest req = new Gson().fromJson(json, ShoppingListAddRequest.class);

        String username = req.getUsername();
        String itemName = req.getItemName();
        int fdcId = req.getFdcId();
        boolean stricken = req.getStricken();

        ResultResponse res = new ResultResponse();

        try {
            shoppingRepository.queryCreateShoppingListEntry(username, itemName, fdcId, stricken);
            res.setResult(RESULT_OK);
        } catch (Exception e) {
            res.setResult(RESULT_ERROR);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }


    @PatchMapping (path="/strikeout")
    public @ResponseBody String changeStrikeout(@RequestBody String json) {

        StrikeoutRequest req = new Gson().fromJson(json, StrikeoutRequest.class);

        String username = req.getUsername();
        String itemName = req.getItemName();

        ResultResponse res = new ResultResponse();

        try {
            shoppingRepository.queryShoppingChangeStricken(username, itemName);
            res.setResult(RESULT_OK);
        } catch (Exception e) {
            res.setResult(RESULT_ERROR);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }
}
