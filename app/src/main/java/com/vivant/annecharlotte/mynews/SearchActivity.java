package com.vivant.annecharlotte.mynews;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Anne-Charlotte Vivant on 14/12/2018.
         */
public class SearchActivity extends AppCompatActivity {

    private EditText mEditText_keywords,
                     mEditText_beginDate,
                     mEditText_endDate;

    private Calendar mCalendar;

    private String mBeginDate,
                    mEndDate;

    private CheckBox mArts,
                        mBusiness,
                        mEntrepreneurs,
                        mPolitics,
                        mSport,
                        mTravel;

    private Switch notificationSwitch;

    private Button mSearchButton;

    private String checkboxResults;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_notif_window);
        configureLayoutLinks();
        this.configureSearchToolbar();
        this.configureWindow();
        initializeOnClickBeginDateListener();
        initializeOnClickEndDateListener();
        configureLayoutLinks();
    }

    private void configureLayoutLinks() {
        mEditText_keywords = findViewById(R.id.search_query_edittext);

        mEditText_beginDate = findViewById(R.id.begin_date_edittext);
        mEditText_endDate = findViewById(R.id.end_date_edittext);

        mArts = findViewById(R.id.art_checkBox);
        mSport = findViewById(R.id.sport_checkBox);
        mBusiness = findViewById(R.id.business_checkBox);
        mEntrepreneurs = findViewById(R.id.entrepreneurs_checkBox);
        mTravel = findViewById(R.id.travel_checkBox);
        mPolitics = findViewById(R.id.politics_checkBox);

        mSearchButton = findViewById(R.id.search_button);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchResultsSearchAcitivty();
            }
        });
    }

    private void configureSearchToolbar() {
        //Get the toolbar (Serialise)
        Toolbar searchToolbar = (Toolbar) findViewById(R.id.search_toolbar);
        //Set the toolbar
        setSupportActionBar(searchToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void configureWindow() {
        notificationSwitch = (Switch) findViewById(R.id.switch_notification);
        notificationSwitch.setVisibility(View.GONE);
    }

    // -----------------------------------------------------------
    // BEGIN DATE : Text View Listener + DatePicker + Label update
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
                new DatePickerDialog(SearchActivity.this, date, mCalendar
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

    // Sets date value to null
    private void nullifyBeginDate() {
        mBeginDate = null;
    }

    // ---------------------------------------------------------
    // END DATE : TextView Listener + DatePicker + Label update
    // ---------------------------------------------------------

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
                new DatePickerDialog(SearchActivity.this, date, mCalendar
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

    // Sets date value to null
    private void nullifyEndDate() {
        mEndDate = null;
    }

    //--------------------------------------------------------------------------------------------------------------
    // Save research criterium
    //--------------------------------------------------------------------------------------------------------------
    /*@Query("api-key") String apiKey,
                                     @Query("q") String query,
                                     @Query("fq") String fQuery,
                                     @Query("sort") String sort,
                                     @Query("begin_date") String beginDate,
                                     @Query("end_date") String endDate)*/

    private void launchResultsSearchAcitivty() {
        String keywordsResults = mEditText_keywords.getText().toString();
        keywordsResults = "\"" + keywordsResults + "\")";

        checkboxResult();


        Intent myIntent = new Intent(SearchActivity.this, ResultsSearchActivity.class);
        myIntent.putExtra("q", keywordsResults);
        myIntent.putExtra("fq", checkboxResults);
        myIntent.putExtra("begin_date","\"" + mBeginDate+ "\")");
        myIntent.putExtra("end_date","\"" + mEndDate+ "\")");
        startActivity(myIntent);
    }

    private void checkboxResult() {
        checkboxResults = "news_desk:(\"";

        if (mPolitics.isChecked()) {
            checkboxResults += "politics";
        }

        if (mArts.isChecked()) {
            checkboxResults += "arts";
        }

        if (mBusiness.isChecked()) {
            checkboxResults += "business";
        }

        if (mSport.isChecked()) {
            checkboxResults += "sports";
        }

        if (mEntrepreneurs.isChecked()) {
            checkboxResults += "entrepreneurs";
        }

        if (mTravel.isChecked()) {
            checkboxResults += "travel";
        }

        checkboxResults += "\")";
    }
}

//"source:(\"The New York Times\")" + " AND" + " news_desk:(\"Business\")"