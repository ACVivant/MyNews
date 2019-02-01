package com.vivant.annecharlotte.mynews.controller;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.vivant.annecharlotte.mynews.R;
import com.vivant.annecharlotte.mynews.utils.SearchKeysValidation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Generate search window
 * launch SearchResultsActivity for results
 */
public class SearchWindowActivity extends AppCompatActivity {

    private Calendar mCalendar;

    private String mBeginDate="",
            mEndDate="";

    private String checkboxResults;
    private String keywordsResults;

    boolean launch;
    boolean launchDate;

    @BindView(R.id.switch_notification) Switch notificationSwitch;
    @BindView(R.id.search_toolbar) Toolbar searchToolbar;
    @BindView(R.id.search_query_edittext) EditText mEditText_keywords;
    @BindView(R.id.begin_date_edittext) EditText mEditText_beginDate;
    @BindView(R.id.end_date_edittext) EditText mEditText_endDate;
    @BindView(R.id.search_button) Button mSearchButton;

    @BindView(R.id.art_checkBox) CheckBox mArts;
    @BindView(R.id.sport_checkBox) CheckBox mSport;
    @BindView(R.id.business_checkBox) CheckBox mBusiness;
    @BindView(R.id.entrepreneurs_checkBox) CheckBox mEntrepreneurs;
    @BindView(R.id.travel_checkBox) CheckBox mTravel;
    @BindView(R.id.politics_checkBox) CheckBox mPolitics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_notif_window);
        ButterKnife.bind(this);
        configureLayoutLinks();
        this.configureSearchToolbar();
        this.configureWindow();
        initializeOnClickBeginDateListener();
        initializeOnClickEndDateListener();
        configureLayoutLinks();
    }

    private void configureLayoutLinks() {

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDate = true;
                SearchKeysValidation keywordsValidation = new SearchKeysValidation(getBaseContext(), mEditText_keywords, mArts, mBusiness, mEntrepreneurs, mPolitics, mSport, mTravel);
                keywordsResults = keywordsValidation.keywordResult();
                checkboxResults = keywordsValidation.checkboxResult();

                launch = keywordsValidation.isLaunch();
                launchDate = checkDate();

                if (launch && launchDate) {
                    Intent myIntent = new Intent(SearchWindowActivity.this, SearchResultsActivity.class);
                    myIntent.putExtra("q", keywordsResults);
                    myIntent.putExtra("fq", checkboxResults);
                    myIntent.putExtra("begin_date", mBeginDate);
                    myIntent.putExtra("end_date", mEndDate);
                    startActivity(myIntent);
                    finish();
                }
            }
        });
    }

    private void configureSearchToolbar() {

        searchToolbar.setTitle(R.string.search_results_title);
        //Set the toolbar
        setSupportActionBar(searchToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    // no switch for the search window
    private void configureWindow() {
        notificationSwitch.setVisibility(View.GONE);
    }

    // -----------------------------------------------------------
    // DATE : Text View Listener + DatePicker + Label update
    // -----------------------------------------------------------

    // Attach listener to TextView that calls a DatePickerDialog when clicked on
    private void initializeOnClickBeginDateListener() {
        mCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateBeginDateLabel();
            }

        };

        mEditText_beginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SearchWindowActivity.this, date, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    // Update text field value with the chosen Date, date displayed should be: dd/MM/yyyy, for example 10/03/2018 for 10th March 2018
    private void updateBeginDateLabel() {
        String mFormat = "dd/MM/yyyy";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(mFormat, Locale.US);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

        mEditText_beginDate.setText(sdf.format(mCalendar.getTime()));
        mBeginDate = sdf2.format(mCalendar.getTime());
    }



    // Attach listener to TextView that calls a DatePickerDialog when clicked on
    private void initializeOnClickEndDateListener() {

        mCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateEndDateLabel();
            }
        };

        mEditText_endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SearchWindowActivity.this, date, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    // Update text field value with the chosen Date, date displayed should be: dd/MM/yyyy, for example 10/03/2018 for 10th March 2018
    private void updateEndDateLabel() {
        String dateFormat = "MM/dd/yyyy";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

        mEditText_endDate.setText(sdf.format(mCalendar.getTime()));
        mEndDate = sdf2.format(mCalendar.getTime());

    }

    // Verify if there is a begin et and end date
    private boolean checkDate() {
        boolean testDate;
        if (mBeginDate.length()<1 || mEndDate.length()<1) {
            Toast.makeText(this, R.string.date_error, Toast.LENGTH_LONG).show();
            testDate = false;
        }

        else {
            testDate = true;
        }
        return testDate;
    }
}
