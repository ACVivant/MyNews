package com.vivant.annecharlotte.mynews;

import com.vivant.annecharlotte.mynews.Utils.SearchKeysValidation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Anne-Charlotte Vivant on 17/01/2019.
 */

public class SearchKeysValidationTest {
    @Test
    public void format_edittext_keywords() {
        SearchKeysValidation formatNotifText = new SearchKeysValidation("trump shutdown");
        assertEquals("(\"trump\" \"shutdown\" )", formatNotifText.keywordFormat("trump shutdown"));
    }

    @Test
    public void returnfalse_when_noboxchecked() {
        boolean ans = false;
        SearchKeysValidation checkboxTest = new SearchKeysValidation();
        checkboxTest.setLaunch(true);
        assertEquals(ans, checkboxTest.launchOrNotCheckBox(0) );
    }

    @Test
    public void returntrue_when_boxchecked() {
        boolean ans = true;
        SearchKeysValidation checkboxTest = new SearchKeysValidation();
        checkboxTest.setLaunch(true);
        assertEquals(ans, checkboxTest.launchOrNotCheckBox(1) );
    }

    @Test
    public void returnfalse_when_noword() {
        boolean ans = false;
        SearchKeysValidation keywordTest = new SearchKeysValidation();
        keywordTest.setLaunch(true);
        assertEquals(ans, keywordTest.launchOrNotKeywords("") );
    }

    @Test
    public void returntrue_when_word() {
        boolean ans = true;
        SearchKeysValidation keywordTest = new SearchKeysValidation();
        keywordTest.setLaunch(true);
        assertEquals(ans, keywordTest.launchOrNotKeywords("shutdown") );
    }


}
