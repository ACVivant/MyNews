package com.vivant.annecharlotte.mynews;

import android.content.Context;
import android.content.SharedPreferences;

import com.vivant.annecharlotte.mynews.Controller.NotificationResults;
import com.vivant.annecharlotte.mynews.Controller.NotificationWindowActivity;
import com.vivant.annecharlotte.mynews.Views.TextNotif;

import org.junit.Test;

import static android.content.Context.MODE_PRIVATE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Anne-Charlotte Vivant on 10/01/2019.
 */
public class NotificationTest {

 /*   @Test
    public void formatFQuery_fromcheckbox() {

        // Je voudrais bien savoir où est le problème avec ce test...
        // Pourquoi le set ne fonctionne pas?
        // Pourquoi l'appel ) formatTextQuery() renvoie null
        // Expected :news_desk:("sport" "travel" )
        // Actual   :null

        NotificationResults res = mock(NotificationResults.class);
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
    }*/

/*
    @Test
    public void message_notification_newarticles() {
        // Comment je m'en sors pour récupérer le context?
        TextNotif alertTest = new TextNotif();
        //TextNotif alertTest = mock(TextNotif.class);
        assertEquals("Vous avez 3 nouveaux articles à découvrir aujourd'hui!", alertTest.createMessage(, 3));
    }

    @Test
    public void message_notification_nonewarticles() {
        TextNotif alertTest = new TextNotif();
        assertEquals("Vous n'avez aucun article à découvrir aujourd'hui!", alertTest.createMessage(0));
    }*/
}
