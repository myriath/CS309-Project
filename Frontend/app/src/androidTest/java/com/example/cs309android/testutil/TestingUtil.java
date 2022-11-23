package com.example.cs309android.testutil;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.cs309android.GlobalClass;
import com.google.android.material.textfield.TextInputLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Util class for espresso testing
 *
 * @author Mitch Hudson
 * @author Travis Massner
 */
public class TestingUtil {
    /**
     * Gets a TextInputEditText from the id of it's parent TextInputLayout
     * @param rid Resource id of the TextInputLayout parent
     * @return Espresso matcher to perform or check with
     */
    public static ViewInteraction getTextInputEditText(int rid) {
        return onView(allOf(
                isDescendantOfA(withId(rid)),
                isAssignableFrom(EditText.class)
        ));
    }

    /**
     * Custom matcher for matching error text in a TextInputLayout
     * @param expected Expected error text
     * @return Matcher to test error text
     */
    public static Matcher<View> hasErrorText(String expected) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (!(item instanceof TextInputLayout)) {
                    return false;
                }

                CharSequence error = ((TextInputLayout) item).getError();
                return error != null && error.equals(expected);
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

    /**
     * Gets the GlobalClass for testing util methods.
     * @return GlobalClass for the application
     */
    public static GlobalClass getGlobal() {
        return (GlobalClass) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }
}
