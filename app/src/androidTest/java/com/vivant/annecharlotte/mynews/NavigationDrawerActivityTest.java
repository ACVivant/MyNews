package com.vivant.annecharlotte.mynews;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vivant.annecharlotte.mynews.Controller.NavigationDrawerActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by Anne-Charlotte Vivant on 23/01/2019.
 */

@RunWith(AndroidJUnit4.class)
public class NavigationDrawerActivityTest {

    @Rule
    public ActivityTestRule<NavigationDrawerActivity> mMainActivityActivityTestRule = new ActivityTestRule<>(NavigationDrawerActivity.class);
}
