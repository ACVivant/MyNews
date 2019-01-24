package com.vivant.annecharlotte.mynews.Utils;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.vivant.annecharlotte.mynews.R;

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

    public void setLaunch(boolean launch) {
        this.launch = launch;
    }

    boolean launch;
    Context mContext;
    private int index;

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

    public SearchKeysValidation() {
            }

    //----------------------------------------------------------------------------------------------------
    // Validation or not of keywords
    //----------------------------------------------------------------------------------------------------

    public String keywordResult() {
        launch = true;
        String keywordsResults1 = mEditText_keywords.getText().toString();

        shouldDisplayToastKeywords(keywordsResults1);
        launchOrNotKeywords(keywordsResults1);

        if (launch){
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
                keywordsResults += "\"" + splitArray[i] + "\" ";
            }
            keywordsResults += ")";
        return keywordsResults;
    }

    public boolean launchOrNotKeywords(String keyWords) {
        if (keyWords.length() < 1) {
            launch = false;
        } return launch;
    }

    public void shouldDisplayToastKeywords(String keyWords) {
        if (keyWords.length() < 1) Toast.makeText(mContext, R.string.notificationdialog_checkbox_error, Toast.LENGTH_LONG).show();
    }

    //----------------------------------------------------------------------------------------------------------
    // validation or not of checklist
    //----------------------------------------------------------------------------------------------------------

    public String checkboxResult () {
        index = 0;
        checkboxFormat();
        shouldDisplayToastCheckbox(index);
        launchOrNotCheckBox(index);
        return checkboxResults;
    }

    public void checkboxFormat() {
        checkboxResults = "news_desk:(";

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

    }

    public boolean launchOrNotCheckBox(int index) {
        if(index==0){
            launch = false;
        } return launch;
    }

    public void shouldDisplayToastCheckbox(int index) {
        if (index==0) Toast.makeText(mContext, R.string.personalization_error0, Toast.LENGTH_LONG).show();
    }

}
