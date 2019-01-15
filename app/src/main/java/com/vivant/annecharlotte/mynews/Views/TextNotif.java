package com.vivant.annecharlotte.mynews.Views;

/**
 * Update text of the notification
 */
public class TextNotif {

    public String createMessage(int numberArticles) {
        if (numberArticles == 0) {
            return "Vous n'avez aucun article à découvrir aujourd'hui!";
        } else {
            return "Vous avez " + numberArticles + " nouveaux articles à découvrir aujourd'hui!";
        }
    }
}
