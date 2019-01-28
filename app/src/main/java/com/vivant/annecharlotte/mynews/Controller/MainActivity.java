package com.vivant.annecharlotte.mynews.Controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vivant.annecharlotte.mynews.R;
import com.vivant.annecharlotte.mynews.Views.Popup;
import com.vivant.annecharlotte.mynews.Views.TabPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int PERSO_ACTIVITY_REQUEST_CODE = 0;

    private MenuItem nav1,
            nav2,
            nav3,
            nav4;

    private int artsIndex,
            businessIndex,
            politicsIndex,
            sportIndex,
            travelIndex,
            fashionIndex,
            foodIndex,
            scienceIndex,
            technologyIndex,
            worldIndex,
            healthIndex;

    private int keyWelcome;

    public static final String SHARED_PREFS_ND = "SharedPrefsND";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private String titleId;
    private String iconId;

    @BindView(R.id.toolbar_resultssearch) Toolbar toolbar;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        // adapt NavigationView with users preferences
        nav1 = navigationView.getMenu().findItem(R.id.nav_1);
        nav2 = navigationView.getMenu().findItem(R.id.nav_2);
        nav3 = navigationView.getMenu().findItem(R.id.nav_3);
        nav4 = navigationView.getMenu().findItem(R.id.nav_4);

        myNDView(nav1);
        myNDView(nav2);
        myNDView(nav3);
        myNDView(nav4);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the top-right menu
        getMenuInflater().inflate(R.menu.main, menu);
        // Inflate the toolbar menu
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item (top-right menu) clicks here.
        int id = item.getItemId();

        //launch NotificationWindowActivity
        if (id == R.id.action_notifications) {
            Intent myIntent = new Intent(MainActivity.this, NotificationWindowActivity.class);
            this.startActivity(myIntent);
            return true;
        }

        //launch Help Pop-up
        if (id == R.id.action_help) {
            Popup mPopup = new Popup(this, R.string.helpmenu_title, R.string.helpmenu_text, R.drawable.baseline_help_24);
            mPopup.personnaliseAndLaunchPopup();
            return true;
        }

        // launch About Pop-up
        if (id == R.id.action_about) {
            Popup mPopup = new Popup(this, R.string.aboutmenu_title, R.string.aboutmenu_text, R.drawable.baseline_insert_comment_24);
            mPopup.personnaliseAndLaunchPopup();
            return true;
        }

        // launch Search Activity
        if (id == R.id.menu_activity_main_search) {
            Intent myIntent = new Intent(MainActivity.this, SearchWindowActivity.class);
            this.startActivity(myIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        CharSequence theme = item.getTitle();

        if (theme == getResources().getString(R.string.arts_checkbox_text)) {
            launchTopStoriesActivityFromNavigationDrawer(2);
        }

        if (theme == getResources().getString(R.string.health_checkbox_text)) {
            launchTopStoriesActivityFromNavigationDrawer(3);
        }

        if (theme == getResources().getString(R.string.food_checkbox_text)) {
            launchTopStoriesActivityFromNavigationDrawer(4);
        }

        if (theme == getResources().getString(R.string.technology_checkbox_text)) {
            launchTopStoriesActivityFromNavigationDrawer(5);
        }

        if (theme == getResources().getString(R.string.sciences_checkbox_text)) {
            launchTopStoriesActivityFromNavigationDrawer(6);
        }

        if (theme == getResources().getString(R.string.business_checkbox_text)) {
            launchTopStoriesActivityFromNavigationDrawer(7);
        }

        if (theme == getResources().getString(R.string.fashion_checkbox_text)) {
            launchTopStoriesActivityFromNavigationDrawer(8);
        }

        if (theme == getResources().getString(R.string.politics_checkbox_text)) {
            launchTopStoriesActivityFromNavigationDrawer(9);
        }

        if (theme == getResources().getString(R.string.travel_checkbox_text)) {
            launchTopStoriesActivityFromNavigationDrawer(10);
        }

        if (theme == getResources().getString(R.string.sport_checkbox_text)) {
            launchTopStoriesActivityFromNavigationDrawer(11);
        }

        if (theme == getResources().getString(R.string.world_checkbox_text)) {
            launchTopStoriesActivityFromNavigationDrawer(12);
        }

        if (id == R.id.nav_search) {
            Intent myIntent = new Intent(MainActivity.this, SearchWindowActivity.class);
            startActivity(myIntent);
        }

        if (id == R.id.nav_notification) {
            Intent myIntent = new Intent(MainActivity.this, NotificationWindowActivity.class);
            startActivity(myIntent);
        }


        if (id == R.id.nav_perso_ND) {
            Intent myIntent = new Intent(MainActivity.this, PersonalizationActivity.class);
            startActivityForResult(myIntent,PERSO_ACTIVITY_REQUEST_CODE );
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void launchTopStoriesActivityFromNavigationDrawer(int key) {
        Intent myIntent = new Intent(MainActivity.this, NavigationDrawerActivity.class);
        myIntent.putExtra("pos", key);
        startActivity(myIntent);
    }

    //---------------------------------------------------------------------------------------------------------------
    // Personalization
    //--------------------------------------------------------------------------------------------------------------

    // recovers keys data from personalization activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PERSO_ACTIVITY_REQUEST_CODE ) {
            // get keys data from Intent
            artsIndex = data.getIntExtra("keyArts", 0);
            businessIndex = data.getIntExtra("keyBusiness", 0);
            politicsIndex = data.getIntExtra("keyPolitics", 0);
            sportIndex = data.getIntExtra("keySport", 0);
            travelIndex = data.getIntExtra("keyTravel", 0);
            fashionIndex = data.getIntExtra("keyFashion", 0);
            foodIndex = data.getIntExtra("keyFood", 0);
            scienceIndex = data.getIntExtra("keySciences", 0);
            technologyIndex = data.getIntExtra("keyTechnology", 0);
            worldIndex = data.getIntExtra("keyWorld", 0);
            healthIndex = data.getIntExtra("keyHealth", 0);

            updateNDView();
        }
    }

    // Put right Title and icon according to users preferences
    public void myNDView(MenuItem nav) {
        sharedPreferences = getSharedPreferences(SHARED_PREFS_ND, MODE_PRIVATE);
        nav.setTitle(sharedPreferences.getInt("Title"+nav.getItemId(),R.string.perso_firsttime));
        nav.setIcon(sharedPreferences.getInt("Icon" +nav.getItemId(),R.drawable.baseline_face_24));
    }

    // update navigation drawer when users saves new personalisation keys
    public void updateNDView() {
        int sumIndex = artsIndex + businessIndex + politicsIndex + sportIndex + travelIndex + fashionIndex + foodIndex + scienceIndex + technologyIndex+ worldIndex +healthIndex;

        nav1.setVisible(false);
        nav2.setVisible(false);
        nav3.setVisible(false);
        nav4.setVisible(false);

        switch (sumIndex) {

            case 4:
                personalization(nav4);
            case 3:
                personalization(nav3);
            case 2:
                personalization(nav2);
            case 1:
                personalization(nav1);
                break;
        }
    }

    // characteristic of each item according to themes
    public void personalization(MenuItem nav) {
        sharedPreferences = getSharedPreferences(SHARED_PREFS_ND, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        nav.setVisible(true);

        int k=0;
        keyWelcome=0;

        String id = Integer.toString(nav.getItemId());
        titleId = "Title" +id;
        iconId = "Icon" +id;

        if (politicsIndex ==1&&k==0) {
            editAndSaveKeys(nav, R.string.politics_checkbox_text, R.drawable.baseline_mic_24);
            politicsIndex =0;
            k+=1;
        }

        if (artsIndex ==1&&k==0) {
            editAndSaveKeys(nav, R.string.arts_checkbox_text, R.drawable.baseline_collections_24);
            artsIndex =0;
            k+=1;
        }

        if (businessIndex ==1&&k==0) {
            editAndSaveKeys(nav, R.string.business_checkbox_text, R.drawable.baseline_business_24);
            businessIndex =0;
            k+=1;
        }

        if (sportIndex ==1&&k==0) {
            editAndSaveKeys(nav, R.string.sport_checkbox_text, R.drawable.baseline_directions_run_24);
            sportIndex =0;
            k+=1;
        }

        if (fashionIndex ==1&&k==0) {
            editAndSaveKeys(nav, R.string.fashion_checkbox_text, R.drawable.baseline_people_24);
            fashionIndex =0;
            k+=1;
        }

        if (foodIndex ==1&&k==0) {
            editAndSaveKeys(nav, R.string.food_checkbox_text, R.drawable.baseline_local_dining_24);
            foodIndex =0;
            k+=1;
        }

        if (scienceIndex ==1&&k==0) {
            editAndSaveKeys(nav, R.string.sciences_checkbox_text, R.drawable.baseline_blur_linear_24);
            scienceIndex =0;
            k+=1;
        }

        if (technologyIndex ==1&&k==0) {
            editAndSaveKeys(nav, R.string.technology_checkbox_text, R.drawable.baseline_wb_iridescent_24);
            technologyIndex =0;
            k+=1;
        }

        if (travelIndex ==1&&k==0) {
            editAndSaveKeys(nav, R.string.travel_checkbox_text, R.drawable.baseline_flight_24);
            travelIndex =0;
            k+=1;

        }

        if (worldIndex ==1&&k==0) {
            editAndSaveKeys(nav, R.string.world_checkbox_text, R.drawable.baseline_public_24);
            worldIndex =0;
            k+=1;
        }

        if (healthIndex ==1&&k==0) {
            editAndSaveKeys(nav, R.string.health_checkbox_text, R.drawable.baseline_healing_24);
            healthIndex =0;
            k+=1;
        }
    }

    // prepare data to save them in SharedPreferences
    public void editAndSaveKeys(MenuItem nav, int title, int icon) {
        nav.setTitle(title);
        nav.setIcon(icon);

        editor.putInt(titleId, title);
        editor.putInt(iconId, icon);
        editor.apply();
    }
}
