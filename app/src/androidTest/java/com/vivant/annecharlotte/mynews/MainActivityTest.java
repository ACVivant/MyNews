package com.vivant.annecharlotte.mynews;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import com.vivant.annecharlotte.mynews.Controller.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Anne-Charlotte Vivant on 14/01/2019.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    // Test tab layout
    @Test
    public void listGoesOverTheFold() {
        onView(withText("TOP STORIES")).check(matches(isDisplayed()));
        onView(withText("MOST POPULAR")).check(matches(isDisplayed()));
        onView(withText("MONDE")).check(matches(isDisplayed()));
    }

    @Test
    public void clickSearchButton_opensSearchActivity()throws Exception {
        onView(withId(R.id.menu_activity_main_search)).perform(click());
        onView(withId(R.id.search_query_edittext)).check(matches(isDisplayed()));
    }

    @Test
            public void openHelp_when_clickOnHelpItem() {
        // Show the contextual action bar.
        onView(withId(R.id.toolbar_resultssearch))
                .perform(click());

        // Open the overflow menu from contextual action mode.
        openContextualActionModeOverflowMenu();

        // Click on the item.
        onView(withText("Help"))
                .perform(click());

        // Verify that we have really clicked on the item by
        // checking the TextView content.
        onView(withText(R.string.helpmenu_text))
                .check(matches(isDisplayed()));
    }

    @Test
    public void openAbout_when_clickOnAboutItem() {
        // Show the contextual action bar.
        onView(withId(R.id.toolbar_resultssearch))
                .perform(click());

        // Open the overflow menu from contextual action mode.
        openContextualActionModeOverflowMenu();

        // Click on the item.
        onView(withText("About"))
                .perform(click());

        // Verify that we have really clicked on the item by
        // checking the TextView content.
        onView(withText(R.string.aboutmenu_text))
                .check(matches(isDisplayed()));
    }

}


