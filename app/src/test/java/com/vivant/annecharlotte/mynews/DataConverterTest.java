package com.vivant.annecharlotte.mynews;

import com.vivant.annecharlotte.mynews.Utils.DateConverter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DataConverterTest {
    @Test
    public void dataConverter() {
        DateConverter dataTest = new DateConverter();
        assertEquals("13/12/98",  dataTest.getPublished_date_converted("1998-12-13"));
    }
}