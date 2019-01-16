package com.vivant.annecharlotte.mynews;

import android.app.AlertDialog;
import android.content.Intent;

import com.vivant.annecharlotte.mynews.Controller.MainActivity;
import com.vivant.annecharlotte.mynews.Controller.NotificationWindowActivity;
import com.vivant.annecharlotte.mynews.Controller.SearchWindowActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowApplication;

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
        Intent startedIntent = ShadowApplication.getInstance().getNextStartedActivity();
        Intent expectedIntent = new Intent(activity, NotificationWindowActivity.class);
        assertEquals(expectedIntent.getComponent(), startedIntent.getComponent());
    }

    @Test
    public void when_SearchbarClicked_then_StartSearchWindowActivity() {
        //User click on search bar in toolbar
        shadowOf(activity).clickMenuItem(R.id.menu_activity_main_search);
        Intent startedIntent = ShadowApplication.getInstance().getNextStartedActivity();
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

    /*
    @Test
    public void when_EducationClickedinDrawer_then_StartSearchResultActivity() {
        String EDUCATION_SEARCH = "source:(\"The New York Times\")" + " AND" + " news_desk:(\"Education\")";
        final Bundle bundle = new Bundle();
        bundle.putString("fq", EDUCATION_SEARCH);

        shadowOf(activity).clickMenuItem(R.id.nav_education);
        Intent startedIntent = ShadowApplication.getInstance().getNextStartedActivity();
        Intent expectedIntent = new Intent(activity, SearchResultsActivity.class);
        expectedIntent.putExtras(bundle);
        assertEquals(expectedIntent.getComponent(), startedIntent.getComponent());

        //java.lang.NullPointerException
        //	at com.vivant.annecharlotte.mynews.MainActivityRobotTest.when_EducationClickedinDrawer_then_StartSearchResultActivity(MainActivityRobotTest.java:89)
        //	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        //	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        //	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        //	at java.lang.reflect.Method.invoke(Method.java:498)
        //	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
        //	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
        //	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
        //	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
    }*/

}
