package com.requests.backend.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.requests.backend.models.*;
import com.requests.backend.models.requests.ShoppingListAddRequest;
import com.requests.backend.models.requests.ShoppingListRemoveRequest;
import com.requests.backend.models.requests.StrikeoutRequest;
import com.requests.backend.models.responses.ResultResponse;
import com.requests.backend.models.responses.ShoppingListGetResponse;
import com.requests.backend.repositories.ShoppingListRepository;
import com.requests.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

        Gson gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();

        ShoppingListGetResponse res = new ShoppingListGetResponse();

        if (userQueryRes.isEmpty()) {
            res.setResult(RESULT_ERROR);
        }
        // If the credentials aren't valid, return hash mismatch error code.
        else {

            ShoppingList[] listItems = shoppingRepository.queryGetShoppingList(username);

            User user = userQueryRes.iterator().next();

            if (user.getPHash().compareTo(hash) != 0) {
                res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
            }
            // Otherwise, the user credentials are valid. Return the user's shopping list.
            else {
                res.setResult(RESULT_OK);
                res.setShoppingList(listItems);
            }
        }

        return gson.toJson(res);

    }

    @PostMapping(path="/add")
    public @ResponseBody String addToShoppingList(@RequestBody String json) {

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        ShoppingListAddRequest req = gson.fromJson(json, ShoppingListAddRequest.class);

        SimpleFoodItem foodItem = req.getFoodItem();
        AuthModel auth = req.getAuth();

        String username = auth.getUsername();
        String itemName = foodItem.getDescription();
        int fdcId = foodItem.getFdcId();
        boolean stricken = false;

        ResultResponse res = new ResultResponse();

        try {
            shoppingRepository.queryCreateShoppingListEntry(username, itemName, fdcId, stricken);
            res.setResult(RESULT_OK);
        } catch (Exception e) {
            res.setResult(RESULT_ERROR);
        }

        return gson.toJson(res);
    }


    @PatchMapping (path="/strikeout")
    public @ResponseBody String changeStrikeout(@RequestBody String json) {

        StrikeoutRequest req = new Gson().fromJson(json, StrikeoutRequest.class);

        AuthModel auth = req.getAuth();

        String itemName = req.getItemName();
        String username = auth.getUsername();

        Collection<User> userQueryRes = userRepository.queryValidateUsername(username);

        ResultResponse res = new ResultResponse();

        if (userQueryRes.isEmpty()) {
            res.setResult(RESULT_ERROR);
        }
        else {
            User user = userQueryRes.iterator().next();

            if (user.getPHash().compareTo(auth.getHash()) != 0) {
                res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
            }
            else {
                shoppingRepository.queryShoppingChangeStricken(username, itemName);
                res.setResult(RESULT_OK);
            }
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    @PutMapping (path = "/remove")
    public @ResponseBody String removeFromList(@RequestBody String json) {

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        ShoppingListRemoveRequest req = gson.fromJson(json, ShoppingListRemoveRequest.class);

        AuthModel auth = req.getAuth();

        String itemName = req.getItemName();
        String username = auth.getUsername();

        Collection<User> userQueryRes = userRepository.queryValidateUsername(username);

        ResultResponse res = new ResultResponse();

        if (userQueryRes.isEmpty()) {
            res.setResult(RESULT_ERROR);
        }
        else {
            User user = userQueryRes.iterator().next();

            if (user.getPHash().compareTo(auth.getHash()) != 0) {
                res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
            }
            else {
                shoppingRepository.queryDeleteListItem(username, itemName);
                res.setResult(RESULT_OK);
            }
        }

        return gson.toJson(res);

    }
}
