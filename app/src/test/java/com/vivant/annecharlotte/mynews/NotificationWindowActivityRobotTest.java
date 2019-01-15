package com.vivant.annecharlotte.mynews;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

/**
 * Created by Anne-Charlotte Vivant on 15/01/2019.
 */
@RunWith(RobolectricTestRunner.class)
public class NotificationWindowActivityRobotTest {
    private NotificationWindowActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(NotificationWindowActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(activity);
    }
}
