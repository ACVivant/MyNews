package com.vivant.annecharlotte.mynews;

import android.content.Context;
import android.content.SharedPreferences;

import com.vivant.annecharlotte.mynews.Controller.NotificationResults;
import com.vivant.annecharlotte.mynews.Controller.NotificationWindowActivity;
import com.vivant.annecharlotte.mynews.Views.TextNotif;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowApplication;

import static android.content.Context.MODE_PRIVATE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Anne-Charlotte Vivant on 10/01/2019.
 */
@RunWith(RobolectricTestRunner.class)
public class NotificationTest {

    private Context context = RuntimeEnvironment.application.getApplicationContext();

    @Test
    public void formatFQuery_fromcheckbox() {

        NotificationResults res = new NotificationResults(context);
        res.setTravelOnOff(true);
        res.setSportOnOff(true);

        res.setPoliticsOnOff(false);
        res.setEntrepreneursOnOff(false);
        res.setBusinessOnOff(false);
        res.setArtsOnOff(false);

        System.out.println("Arts " +res.isArtsOnOff());
        System.out.println("Business " +res.isBusinessOnOff());
        System.out.println("Entrepreneurs " +res.isEntrepreneursOnOff());
        System.out.println("Politics " +res.isPoliticsOnOff());
        System.out.println("Travel " +res.isTravelOnOff());
        System.out.println("Sport " +res.isSportOnOff());

        assertEquals("news_desk:(\"sport\" \"travel\" )", res.formatTextFQuery());
    }


    @Test
    public void message_notification_newarticles() {
        TextNotif alertTest = new TextNotif();
        assertEquals("Vous avez 3 nouveaux articles à découvrir aujourd'hui!", alertTest.createMessage(context, 3));
    }

    @Test
    public void message_notification_onenewarticles() {
        TextNotif alertTest = new TextNotif();
        assertEquals("Vous avez un nouvel article à découvrir aujourd'hui!", alertTest.createMessage(context,1));
    }

    @Test
    public void message_notification_nonewarticles() {
        TextNotif alertTest = new TextNotif();
        assertEquals("Vous n'avez aucun article à découvrir aujourd'hui!", alertTest.createMessage(context,0));
    }
}
