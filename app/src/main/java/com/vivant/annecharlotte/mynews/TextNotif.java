package com.vivant.annecharlotte.mynews;

/**
 * Created by Anne-Charlotte Vivant on 10/01/2019.
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
