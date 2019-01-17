package com.vivant.annecharlotte.mynews;

import com.vivant.annecharlotte.mynews.Utils.SearchKeysValidation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anne-Charlotte Vivant on 17/01/2019.
 */
public class SearchKeysValidationTest {
    @Test
    public void format_edittext_keywords() {
        SearchKeysValidation formatNotifText = new SearchKeysValidation("trump shutdown");
        assertEquals("(\"trump\" \"shutdown\" )", formatNotifText.keywordFormat("trump shutdown"));
    }
}
