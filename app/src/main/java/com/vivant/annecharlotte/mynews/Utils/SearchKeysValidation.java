package com.vivant.annecharlotte.mynews.Utils;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Anne-Charlotte Vivant on 15/01/2019.
 */
public class SearchKeysValidation {
    private EditText mEditText_keywords;

    private String mKeywords;

    private CheckBox mArts,
            mBusiness,
            mEntrepreneurs,
            mPolitics,
            mSport,
            mTravel;

    private String checkboxResults;
    private String keywordsResults;

    public boolean isLaunch() {
        return launch;
    }

    boolean launch;

    Context mContext;


    public SearchKeysValidation(Context context, EditText editText, CheckBox arts, CheckBox business, CheckBox entrepreneurs, CheckBox politics, CheckBox sport, CheckBox travel) {
        mContext = context;
        mEditText_keywords = editText;
        mArts = arts;
        mBusiness = business;
        mEntrepreneurs = entrepreneurs;
        mPolitics = politics;
        mSport = sport;
        mTravel = travel;
    }

    public SearchKeysValidation(String keys) {
         mKeywords = keys;
    }

    public String keywordResult() {
        launch = true;
        String keywordsResults1 = mEditText_keywords.getText().toString();
        if (keywordsResults1.length() < 1) {
            Toast.makeText(mContext, "Il faut saisir au moins un mot clé", Toast.LENGTH_LONG).show();
            launch = false;
        } else {
            keywordsResults = keywordFormat(keywordsResults1);
        }
        return keywordsResults;
    }

    public String keywordFormat(String key) {
            keywordsResults = "(";
            String[] splitArray = null;
            String str = key;
            splitArray = str.split(" ");

            for (int i = 0; i < splitArray.length; i++) {
                System.out.println("élement n° " + i + "=[" + splitArray[i] + "]");
                keywordsResults += "\"" + splitArray[i] + "\" ";
            }
            keywordsResults += ")";
        return keywordsResults;
    }

    public String checkboxResult () {
        checkboxResults = "news_desk:(";
        int index = 0;

        if (mPolitics.isChecked()) {
            checkboxResults += "\"politics\" ";
            index+=1;
        }

        if (mArts.isChecked()) {
            checkboxResults += "\"arts\" ";
            index+=1;
        }

        if (mBusiness.isChecked()) {
            checkboxResults += "\"business\" ";
            index+=1;
        }

        if (mSport.isChecked()) {
            checkboxResults += "\"sports\" ";
            index+=1;
        }

        if (mEntrepreneurs.isChecked()) {
            checkboxResults += "\"entrepreneurs\" ";
            index+=1;
        }

        if (mTravel.isChecked()) {
            checkboxResults += "\"travel\" ";
            index+=1;
        }

        checkboxResults += ")";

        if(index==0){
            launch = false;
            Toast.makeText(mContext, "Il faut choisir au moins une catégorie", Toast.LENGTH_LONG).show();
        }
        return checkboxResults;
    }

}
