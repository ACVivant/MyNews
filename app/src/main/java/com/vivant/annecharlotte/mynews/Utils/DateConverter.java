package com.vivant.annecharlotte.mynews.Utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {
// Date conversion to display in the desired format (dd/MM/yy)

        public String published_date;

        // Convert base date format input 「yyyy-MM-dd'T'HH:mm:ssXXX」 to desired date format 「dd"+"/"+"MM"+"/"+"yyyy」
        // Example: 「2018-03-23T05:00:07-04:00」 to 「23/03/2018」

        public static String getPublished_date_converted(String published_date) {

            @SuppressLint("SimpleDateFormat") DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            @SuppressLint("SimpleDateFormat") DateFormat outputFormat = new SimpleDateFormat("dd"+"/"+"MM"+"/"+"yy");

            String convertedDate = "";

            try {
                convertedDate = outputFormat.format(inputFormat.parse(published_date));

            } catch (ParseException e) {
                e.printStackTrace();
            }

            return convertedDate;
        }
    }
