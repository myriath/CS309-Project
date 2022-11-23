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

import com.example.cs309android.activities.login.LoginActivity;
import com.example.cs309android.testutil.TestingUtil;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Util;
import com.example.cs309android.util.security.Hasher;
import com.example.cs309android.util.security.NukeSSLCerts;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests the login page and register page
 *
 * @author Mitch Hudson
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> rule =
            new ActivityScenarioRule<>(LoginActivity.class);

    /**
     * Tests putting text into the login fields, switching to register
     * and registering an existing account.
     * Fails if the username/password doesn't copy over to register page,
     * if the empty email check fails,
     * or if the register works
     * @throws InterruptedException Interrupts the sleep
     */
    @Test
    public void registerRegisteredAccountTest() throws InterruptedException {
        TestingUtil.getTextInputEditText(R.id.unameField).perform(typeText("test1"));
        TestingUtil.getTextInputEditText(R.id.passwordField).perform(typeText("test1pass"));
        onView(withId(R.id.buttonRegister)).perform(click());
        TestingUtil.getTextInputEditText(R.id.unameField).check(matches(withText("test1")));
        TestingUtil.getTextInputEditText(R.id.passwordField).check(matches(withText("test1pass")));
        onView(withId(R.id.buttonRegister)).perform(click());
        onView(withId(R.id.emailField)).check(matches(TestingUtil.hasErrorText("Email can't be empty")));
        TestingUtil.getTextInputEditText(R.id.emailField).perform(typeText("test1@gmail.com"));
        onView(withId(R.id.buttonRegister)).perform(click());
//        com.example.cs309android.util.Util.register();
//        onView(withId(R.id.unameField)).check(matches(Util.hasErrorText("Username taken")));
    }

    /**
     * Tests a register using the util method directly
     * Fails if the register succeeds or gives the wrong error value
     * @throws InterruptedException Thrown if the code is broken to stop the sleep
     */
    @Test
    public void testRegister() throws InterruptedException {
        NukeSSLCerts.nuke();
        Util.register(TestingUtil.getGlobal(), "test1@gmail.com", "test1", "test", "test", Assert::fail, result -> {
            assertEquals(Constants.RESULT_ERROR_USERNAME_TAKEN, result);
        }, error -> {
            error.printStackTrace();
            fail();
        }, 0);
        Util.register(TestingUtil.getGlobal(), "test1@gmail.com", "131411241253143", "test", "test", Assert::fail, result -> {
            assertEquals(Constants.RESULT_ERROR_EMAIL_TAKEN, result);
        }, error -> {
            error.printStackTrace();
            fail();
        }, 0);
        Thread.sleep(5000);
    }

    /**
     * Tests a login using the util method directly
     * Fails if the login succeeds or gives the wrong error value
     * @throws InterruptedException Thrown if the code is broken to stop the sleep
     */
    @Test
    public void testLogin() throws InterruptedException {
        NukeSSLCerts.nuke();
        Util.loginAttempt(TestingUtil.getGlobal(), "test1", "test1pass", Assert::fail, result -> {
            assertEquals(Constants.RESULT_ERROR_USER_HASH_MISMATCH, result);
        }, error -> {
            error.printStackTrace();
            fail();
        });
        Util.loginAttempt(TestingUtil.getGlobal(), Hasher.sha256plaintext("test token"), Assert::fail, result -> {
            assertEquals(Constants.RESULT_ERROR_USER_HASH_MISMATCH, result);
        }, error -> {
            error.printStackTrace();
            fail();
        });
        Thread.sleep(5000);
    }
}
