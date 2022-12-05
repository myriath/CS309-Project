package com.example.cs309android;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import static org.hamcrest.Matchers.allOf;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.models.api.request.shopping.GetListRequest;
import com.example.cs309android.models.api.request.shopping.ShoppingAddRequest;
import com.example.cs309android.models.api.response.shopping.GetListResponse;
import com.example.cs309android.testutil.TestingUtil;
import com.example.cs309android.util.Util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests various things in the main activity
 *
 * @author Mitch Hudson
 * @author Travis Massner
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    /**
     * Runs the tests on the MainActivity
     */
    @Rule
    public ActivityScenarioRule<MainActivity> rule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Tests adding and removing an item from the shopping list
     * @throws InterruptedException Thrown if the thread is interrupted
     */
    @Test
    public void testShoppingList() throws InterruptedException {
        TestingUtil.checkLogin();

        GlobalClass global = TestingUtil.getGlobal();
        String token = global.getToken();

        SimpleFoodItem foodItem = new SimpleFoodItem(4, "Joenathan", "User added", true);

        new ShoppingAddRequest(foodItem, token)
                .request(response -> {
                    MainActivity.clearShoppingList();
                    new GetListRequest(token).request(response1 -> {
                        GetListResponse shoppingResponse = Util.objFromJson(response1, GetListResponse.class);
                        MainActivity.setShoppingList(shoppingResponse.getShoppingList());
                    }, global);
                }, TestingUtil.getGlobal());
        Thread.sleep(2000);
        onView(withId(R.id.shopping)).perform(click());
        onView(withText("Joenathan")).check(matches(isDisplayed()));

        onView(allOf(
                withId(R.id.remove),
                hasSibling(withText("Joenathan"))
        )).perform(click());
        onView(withText("Joenathan")).check(doesNotExist());

        onView(withId(R.id.home)).perform(click());
        new ShoppingAddRequest(foodItem, token)
                .request(response -> {
                    MainActivity.clearShoppingList();
                    new GetListRequest(token).request(response1 -> {
                        GetListResponse shoppingResponse = Util.objFromJson(response1, GetListResponse.class);
                        MainActivity.setShoppingList(shoppingResponse.getShoppingList());
                    }, global);
                }, TestingUtil.getGlobal());
        Thread.sleep(2000);
        onView(withId(R.id.shopping)).perform(click());
        onView(withText("Joenathan")).check(matches(isDisplayed()));
    }
}
