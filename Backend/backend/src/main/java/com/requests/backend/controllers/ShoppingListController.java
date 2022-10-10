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
import com.requests.backend.repositories.TokenRepository;
import com.requests.backend.repositories.UserRepository;
import com.util.security.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.util.Constants.*;

@RestController
@RequestMapping(path="/shopping")
public class ShoppingListController {

    @Autowired
    private ShoppingListRepository shoppingRepository;
    @Autowired
    private TokenRepository tokenRepository;

    /**
     * Gets the users shopping list if the hash provided is valid.
     * If the user does not exist return error code with empty shopping list.
     * If the user exists, but the hash is incorrect, return hash mismatch error code with empty list.
     * Otherwise, the credentials are valid: return "OK" result code and shopping list for associated user.
     * @param token Token for authentication
     * @return
     */
    @GetMapping(path="/get/{token}")
    public @ResponseBody String getShoppingList(@PathVariable String token) {
        // TODO: Look up username from token table
        //       If username doesn't exist, return RESULT_USER_HASH_MISMATCH

        Collection<User> userQueryRes = userRepository.queryValidateUsername(username);

        Gson gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();

        ShoppingListGetResponse res = new ShoppingListGetResponse();

        if (userQueryRes.isEmpty()) {
            res.setResult(RESULT_ERROR);
        }
        // If the credentials aren't valid, return hash mismatch error code.
        else {
            ShoppingList[] listItems = shoppingRepository.queryGetShoppingList(username);

            // User already passed authentication, just return shopping list
            res.setResult(RESULT_OK);
            res.setShoppingList(listItems);
        }

        return gson.toJson(res);

    }

    @PostMapping(path="/add/{token}")
    public @ResponseBody String addToShoppingList(@PathVariable String token, @RequestBody String json) {

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        ShoppingListAddRequest req = gson.fromJson(json, ShoppingListAddRequest.class);

        SimpleFoodItem foodItem = req.getFoodItem();

        String hashedToken = Hasher.sha256(token);
        String username = ""; // temp
        // TODO: Look up username from token table
        //       If username doesn't exist, return RESULT_USER_HASH_MISMATCH

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


    @PatchMapping (path="/strikeout/{token}")
    public @ResponseBody String changeStrikeout(@PathVariable String token, @RequestBody String json) {

        StrikeoutRequest req = new Gson().fromJson(json, StrikeoutRequest.class);

        String hashedToken = Hasher.sha256(token);
        String username = ""; // temp
        // TODO: Look up username from token table
        //       If username doesn't exist, return RESULT_USER_HASH_MISMATCH

        String itemName = req.getItemName();

        Collection<User> userQueryRes = userRepository.queryValidateUsername(username);

        ResultResponse res = new ResultResponse();

        if (userQueryRes.isEmpty()) {
            res.setResult(RESULT_ERROR);
        }
        else {
            // User already passed authentication from token earlier
            shoppingRepository.queryShoppingChangeStricken(username, itemName);
            res.setResult(RESULT_OK);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(res);
    }

    @PutMapping (path = "/remove/{token}")
    public @ResponseBody String removeFromList(@PathVariable String token, @RequestBody String json) {

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        ShoppingListRemoveRequest req = gson.fromJson(json, ShoppingListRemoveRequest.class);

        String username = ""; // temp
        // TODO: Look up username from token table
        //       If username doesn't exist, return RESULT_USER_HASH_MISMATCH

        String itemName = req.getItemName();

        Collection<User> userQueryRes = userRepository.queryValidateUsername(username);

        ResultResponse res = new ResultResponse();

        if (userQueryRes.isEmpty()) {
            res.setResult(RESULT_ERROR);
        }
        else {
            // User already passed authentication from token lookup
            shoppingRepository.queryDeleteListItem(username, itemName);
            res.setResult(RESULT_OK);
        }

        return gson.toJson(res);

    }
}
