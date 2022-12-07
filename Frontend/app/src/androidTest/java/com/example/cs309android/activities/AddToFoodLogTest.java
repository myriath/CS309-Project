package com.example.cs309android.activities;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.cs309android.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddToFoodLogTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void addToFoodLogTest() {
        ViewInteraction textInputEditText = onView(
                allOf(childAtPosition(
                                childAtPosition(
                                        withId(R.id.unameField),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("papajohn"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(childAtPosition(
                                childAtPosition(
                                        withId(R.id.passwordField),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("pizzatime"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.buttonLogin), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                0),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.nutrition), withContentDescription("Nutrition"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navbar),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.date), withText("Wed Dec, 7, 2022"), withContentDescription("Date"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText("Wed Dec, 7, 2022")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.date), withText("Wed Dec, 7, 2022"), withContentDescription("Date"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView2.check(matches(withText("Wed Dec, 7, 2022")));

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.next_date_button), withContentDescription("Next day"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.date), withText("Thu Dec, 8, 2022"), withContentDescription("Date"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView3.check(matches(withText("Thu Dec, 8, 2022")));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.mainButton), withContentDescription("Main Add Button"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                                        0),
                                4),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.addLog), withContentDescription("Add to food log"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        DataInteraction materialTextView = onData(anything())
                .inAdapterView(allOf(withId(androidx.constraintlayout.widget.R.id.select_dialog_listview),
                        childAtPosition(
                                withId(androidx.constraintlayout.widget.R.id.contentPanel),
                                0)))
                .atPosition(0);
        materialTextView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("bread"), closeSoftKeyboard());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")), withText("bread"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete2.perform(pressImeActionButton());

        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.search_results),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                1)))
                .atPosition(2);
        relativeLayout.perform(click());

        ViewInteraction extendedFloatingActionButton = onView(
                allOf(withId(R.id.add_item), withText("ADD ITEM"), withContentDescription("ADD ITEM"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        extendedFloatingActionButton.perform(click());

        pressBack();

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.home), withContentDescription("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navbar),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.nutrition), withContentDescription("Nutrition"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navbar),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.breakfastCard),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));

        ViewInteraction bottomNavigationItemView4 = onView(
                allOf(withId(R.id.home), withContentDescription("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navbar),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView4.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.nutrientAmount), withText("99.0"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.recyclerview.widget.RecyclerView.class))),
                        isDisplayed()));
        textView4.check(matches(withText("99.0")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.nutrientAmount), withText("99.0"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.recyclerview.widget.RecyclerView.class))),
                        isDisplayed()));
        textView5.check(matches(withText("99.0")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
