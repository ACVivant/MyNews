package com.vivant.annecharlotte.mynews;

import android.widget.CheckBox;
import android.widget.EditText;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Anne-Charlotte Vivant on 10/01/2019.
 */
public class NotificationTest {
    @Test
    public void format_edittext_keywords() {
        NotificationActivity formatNotifText = new NotificationActivity();
        assertEquals("(\"trump\" \"shutdown\" )", formatNotifText.keywordFormat("trump shutdown"));
    }

    @Test
    public void message_notification_newarticles() {
        TextNotif alertTest = new TextNotif();
        assertEquals("Vous avez 3 nouveaux articles à découvrir aujourd'hui!", alertTest.createMessage(3));
    }

    @Test
    public void message_notification_nonewarticles() {
        TextNotif alertTest = new TextNotif();
        assertEquals("Vous n'avez aucun article à découvrir aujourd'hui!", alertTest.createMessage(0));
    }
}
