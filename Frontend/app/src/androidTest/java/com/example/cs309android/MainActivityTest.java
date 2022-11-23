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
import com.example.cs309android.models.api.request.shopping.ShoppingAddRequest;
import com.example.cs309android.testutil.TestingUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> rule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void test() throws InterruptedException {
        TestingUtil.checkLogin();

        SimpleFoodItem foodItem = new SimpleFoodItem(4, "Joenathan", "User added", true);

        new ShoppingAddRequest(foodItem, TestingUtil.getGlobal().getToken())
                .request(response -> {}, TestingUtil.getGlobal());
        Thread.sleep(2000);
        onView(withId(R.id.shopping)).perform(click());
        onView(withText("Joenathan")).check(matches(isDisplayed()));

        onView(allOf(
                withId(R.id.remove),
                hasSibling(withText("Joenathan"))
        )).perform(click());

        onView(withId(R.id.home)).perform(click());
        new ShoppingAddRequest(foodItem, TestingUtil.getGlobal().getToken())
                .request(response -> MainActivity.setShoppingList(new SimpleFoodItem[] {foodItem}), TestingUtil.getGlobal());
        Thread.sleep(2000);
        onView(withId(R.id.shopping)).perform(click());
        onView(withText("Joenathan")).check(matches(isDisplayed()));
    }
}
