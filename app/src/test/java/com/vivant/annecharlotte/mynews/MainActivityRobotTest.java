package com.vivant.annecharlotte.mynews;

import android.app.AlertDialog;
import android.content.Intent;

import com.vivant.annecharlotte.mynews.controller.MainActivity;
import com.vivant.annecharlotte.mynews.controller.NotificationWindowActivity;
import com.vivant.annecharlotte.mynews.controller.SearchWindowActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowAlertDialog;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Anne-Charlotte Vivant on 15/01/2019.
 */

@RunWith(RobolectricTestRunner.class)
public class MainActivityRobotTest {
    private MainActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void when_MenuItemNotificationClicked_then_StartNotificationWindowActivity() {
        //User click on notification in menu starts notification activity
        shadowOf(activity).clickMenuItem(R.id.action_notifications);
        Intent startedIntent = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        Intent expectedIntent = new Intent(activity, NotificationWindowActivity.class);
        assertEquals(expectedIntent.getComponent(), startedIntent.getComponent());
    }

    @Test
    public void when_SearchbarClicked_then_StartSearchWindowActivity() {
        //User click on search bar in toolbar
        shadowOf(activity).clickMenuItem(R.id.menu_activity_main_search);
        Intent startedIntent = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        Intent expectedIntent = new Intent(activity, SearchWindowActivity.class);
        assertEquals(expectedIntent.getComponent(), startedIntent.getComponent());
    }

    @Test
    public void when_HelpClicked_then_StartPopupHelp() {
        //User click on help in menu
        shadowOf(activity).clickMenuItem(R.id.action_help);

        AlertDialog alert =
                ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog sAlert = shadowOf(alert);
        assertThat(sAlert.getTitle().toString(),
                equalTo(activity.getString(R.string.helpmenu_title)));
    }

    @Test
    public void when_AboutClicked_then_StartPopupAbout() {
        //User click on help in menu
        shadowOf(activity).clickMenuItem(R.id.action_about);

        AlertDialog alert =
                ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog sAlert = shadowOf(alert);
        assertThat(sAlert.getTitle().toString(),
                equalTo(activity.getString(R.string.aboutmenu_title)));
    }
}
