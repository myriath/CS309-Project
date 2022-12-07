package com.example.cs309android;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.activities.login.LoginActivity;
import com.example.cs309android.fragments.nutrition.NutritionFragment;
import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.testutil.TestingUtil;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Util;
import com.example.cs309android.util.security.Hasher;
import com.example.cs309android.util.security.NukeSSLCerts;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Tests the nutrition add to food log functionality
 *
 * @author Travis Massner
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class FoodLogTest {
    @Rule
    public ActivityScenarioRule<MainActivity> rule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Tests adding a food to the food log,
     * and seeing if it shows up in the food log
     * Fails if the food doesn't show up in the food log
     * @throws InterruptedException Interrupts the sleep
     */
    @Test
    public void addToFoodLogTest() throws InterruptedException {
        MainActivity mainActivity = new MainActivity();
        mainActivity.clearFoodLog();
        SimpleFoodItem item = new SimpleFoodItem(1, "testDescription", "testBrand", (float) 9.9, (float) 8.8, (float) 7.7, (float) 6.6, "testMeal", false);
        mainActivity.addLogItem(item, 0);

        onView(withId(R.id.home)).perform(click());
        onView(withId(R.id.nutrition)).perform(click());

        onView(withId(R.id.breakfastCard)).check(matches(isDisplayed()));
    }

    /**
     * Tests if you can remove a current item from food log
     * Fails if the item doesn't delete from the food log
     * @throws InterruptedException Thrown if the code is broken to stop the sleep
     */
    @Test
    public void deleteFromFoodLogTest() throws InterruptedException {
        MainActivity mainActivity = new MainActivity();
        mainActivity.clearFoodLog();
        SimpleFoodItem item = new SimpleFoodItem(1, "testDescription", "testBrand", (float) 9.9, (float) 8.8, (float) 7.7, (float) 6.6, "testMeal", false);
        mainActivity.addLogItem(item, 0);

        onView(withId(R.id.home)).perform(click());
        onView(withId(R.id.nutrition)).perform(click());

        mainActivity.removeLogItem(0, 0);

        onView(withId(R.id.home)).perform(click());
        onView(withId(R.id.nutrition)).perform(click());

        onView(withId(R.id.breakfastCard)).check(doesNotExist());
    }

    /**
     * Tests if the date functionality works in the nutrition page
     * Fails if the date doesn't add a day when the next day button is pressed
     * @throws InterruptedException Thrown if the code is broken to stop the sleep
     */
    @Test
    public void testDateButtons() throws InterruptedException {
        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM, d, yyyy", Locale.getDefault());
        String date = format.format(currentTime);

        onView(withId(R.id.date)).perform(click());

        onView(withId(R.id.date)).check(matches(withText(date)));

        onView(withId(R.id.next_date_button)).perform(click());

        calendar.add(Calendar.DATE, 1);
        currentTime = calendar.getTime();
        date = format.format(currentTime);

        onView(withId(R.id.date)).check(matches(withText(date)));
    }
}