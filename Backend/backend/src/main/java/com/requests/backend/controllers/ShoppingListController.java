package com.requests.backend.controllers;

import com.requests.backend.models.ShoppingList;
import com.requests.backend.models.SimpleFoodItem;
import com.requests.backend.models.Token;
import com.requests.backend.models.User;
import com.requests.backend.models.requests.ShoppingListAddRequest;
import com.requests.backend.models.requests.ShoppingListRemoveRequest;
import com.requests.backend.models.requests.StrikeoutRequest;
import com.requests.backend.models.responses.ResultResponse;
import com.requests.backend.models.responses.ShoppingListGetResponse;
import com.requests.backend.repositories.ShoppingListRepository;
import com.requests.backend.repositories.SimpleFoodRepository;
import com.requests.backend.repositories.TokenRepository;
import com.requests.backend.repositories.UserRepository;
import com.util.security.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.util.Constants.*;

/**
 * This class is responsible for handling all requests related to the shopping list.
 * @author Logan
 * @author Mitch
 */
@RestController
@RequestMapping(path="/shopping")
public class ShoppingListController {

    @Autowired
    private ShoppingListRepository shoppingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private SimpleFoodRepository simpleFoodRepository;

    /**
     * Gets the users shopping list if the hash provided is valid.
     * If the user does not exist return error code with empty shopping list.
     * If the user exists, but the hash is incorrect, return hash mismatch error code with empty list.
     * Otherwise, the credentials are valid: return "OK" result code and shopping list for associated user.
     * @param token Token for authentication
     * @return Shopping list get response that contains all the shopping list items
     */
    @GetMapping(path="/get/{token}")
    public @ResponseBody ShoppingListGetResponse getShoppingList(@PathVariable String token) {

        String hashedToken = Hasher.sha256(token);

        // Find token in table
        Token[] tokenQueryRes = tokenRepository.queryGetToken(hashedToken);

        ShoppingListGetResponse res = new ShoppingListGetResponse();

        // If the token doesn't exist in the table, return error.
        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        // Otherwise, the token exists in the table
        else {
            // Get the username associated with the token
            User user = tokenQueryRes[0].getUser();

            // User already passed authentication, just return shopping list
            res.setResult(RESULT_OK);
            res.setShoppingList(user.getShoppingLists().toArray(new ShoppingList[0]));
        }

        return res;
    }

    /**
     * Adds an item to the shopping list if the hash provided is valid.
     * @param token Token for authentication
     * @param req JSON request body containing the item to add to the shopping list
     * @return Result code
     */
    @PostMapping(path="/add/{token}")
    public @ResponseBody ResultResponse addToShoppingList(@PathVariable String token, @RequestBody ShoppingListAddRequest req) {
        String hashedToken = Hasher.sha256(token);

        SimpleFoodItem foodItem = req.getFoodItem();

        ResultResponse res = new ResultResponse();

        // Find token in table
        Token[] tokenQueryRes = tokenRepository.queryGetToken(hashedToken);

        // If the token doesn't exist in the table, return error.
        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            // Get the username associated with the token
            User user = tokenQueryRes[0].getUser();

            try {
                foodItem = simpleFoodRepository.save(foodItem);
                ShoppingList list = new ShoppingList();
                list.setFoodItem(foodItem);
                list.setStricken(false);
                list = shoppingRepository.save(list);
                user.addShoppingList(list);
                userRepository.save(user);
                res.setResult(RESULT_OK);
            } catch (Exception e) {
                res.setResult(RESULT_ERROR);
            }
        }

        return res;
    }

    /**
     * Changes the strikeout status of an item in the shopping list if the hash provided is valid.
     * @param token Token for authentication
     * @param req JSON request body containing identifying information for the item to change
     * @return Result code
     */
    @PatchMapping (path="/strikeout/{token}")
    public @ResponseBody ResultResponse changeStrikeout(@PathVariable String token, @RequestBody StrikeoutRequest req) {
        String hashedToken = Hasher.sha256(token);

        int id = req.getId();
        boolean isCustom = req.isCustom();

        ResultResponse res = new ResultResponse();

        // Find token in table
        Token[] tokenQueryRes = tokenRepository.queryGetToken(hashedToken);

        // If the token doesn't exist in the table, return error.
        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {
            // Get the username associated with the token
            String username = tokenQueryRes[0].getUser().getUsername();

            // User does not exist
            try {
                // User already passed authentication from token earlier
                shoppingRepository.queryShoppingChangeStricken(id, isCustom, username);
                res.setResult(RESULT_OK);
            } catch (Exception e) {
                res.setResult(RESULT_ERROR);
            }
        }

        return res;
    }

    /**
     * Deletes an item from the shopping list if the hash provided is valid.
     * @param token Token for authentication
     * @param req JSON request body containing identifying information for the item to delete
     * @return Result code
     */
    @PutMapping (path = "/remove/{token}")
    public @ResponseBody ResultResponse removeFromList(@PathVariable String token, @RequestBody ShoppingListRemoveRequest req) {
        String hashedToken = Hasher.sha256(token);

        int foodId = req.getId();
        boolean foodCustom = req.isCustom();

        ResultResponse res = new ResultResponse();

        // Find token in table
        Token[] tokenQueryRes = tokenRepository.queryGetToken(hashedToken);

        // If the token doesn't exist in the table, return error.
        if (tokenQueryRes.length == 0) {
            res.setResult(RESULT_ERROR_USER_HASH_MISMATCH);
        }
        else {

            // Get the username associated with the token
            String username = tokenQueryRes[0].getUser().getUsername();

            try {
                // User already passed authentication from token lookup
                shoppingRepository.queryDeleteListItem(foodId, foodCustom, username);
                res.setResult(RESULT_OK);
            } catch (Exception e) {
                res.setResult(RESULT_ERROR);
            }
        }

        return res;

    }
}
