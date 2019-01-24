package com.vivant.annecharlotte.mynews;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vivant.annecharlotte.mynews.Controller.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
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

   /* @Test
   // Comment simuler le click sur le menu? (lancé via onCreateOptionsMenu de MainActivity)?
   // idem pour ND
    public void clickMenuButton_opensMenuWindow()throws Exception {
        onView(withId(R.id.???????)).perform(click());
        onView(withId(R.id.action_help)).check(matches(isDisplayed()));
    }*/

    /*@Test
    // Comment simuer un clic sur des élement sdu menu ou du ND?
    public void clickPersoButton_opensPersonalizationActivity()throws Exception {
        onView(withId(R.id.nav_perso_ND)).perform(click());
        onView(withId(R.id.personalization_textview1)).check(matches(isDisplayed()));
    }*/

}


