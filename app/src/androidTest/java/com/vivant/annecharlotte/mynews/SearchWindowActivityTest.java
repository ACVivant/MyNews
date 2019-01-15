package com.vivant.annecharlotte.mynews;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by Anne-Charlotte Vivant on 14/01/2019.
 */
@RunWith(AndroidJUnit4.class)
public class SearchWindowActivityTest {
    @Rule
    public ActivityTestRule<SearchWindowActivity> mSearchWindowActivityActivityTestRule = new ActivityTestRule<>(SearchWindowActivity.class);

    @Test
    public void listGoesOverTheFold() {

        onView(withId(R.id.begin_date_textview_label)).check(matches(isDisplayed()));
        onView(withId(R.id.end_date_textview_label)).check(matches(isDisplayed()));

        onView(withId(R.id.art_checkBox)).check(matches(isDisplayed()));
        onView(withId(R.id.business_checkBox)).check(matches(isDisplayed()));
        onView(withId(R.id.entrepreneurs_checkBox)).check(matches(isDisplayed()));
        onView(withId(R.id.politics_checkBox)).check(matches(isDisplayed()));
        onView(withId(R.id.sport_checkBox)).check(matches(isDisplayed()));
        onView(withId(R.id.travel_checkBox)).check(matches(isDisplayed()));

        onView(withId(R.id.switch_notification)).check(matches(not(isDisplayed())));
        onView(withId(R.id.search_button)).check(matches(isDisplayed()));
    }
}
