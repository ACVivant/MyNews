package com.vivant.annecharlotte.mynews;

import android.content.Intent;
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

import com.vivant.annecharlotte.mynews.Views.Popup;
import com.vivant.annecharlotte.mynews.Views.TabPagerAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_resultssearch);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.viewpager);

        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        // Handle action bar item (top-right menu) clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as a parent activity is specified  in AndroidManifest.xml.
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

        // launch activities from navigation drawer
        String EDUCATION_SEARCH = "source:(\"The New York Times\")" + " AND" + " news_desk:(\"Education\")";
        String ENVIRONMENT_SEARCH = "source:(\"The New York Times\")" + " AND" + " news_desk:(\"Environment\")";
        String FOOD_SEARCH = "source:(\"The New York Times\")" + " AND" + " news_desk:(\"Food\")";
        String SCIENCE_SEARCH = "source:(\"The New York Times\")" + " AND" + " news_desk:(\"Science\")";

        int id = item.getItemId();

        if (id == R.id.nav_education) {
            launchSearchActivityFromNavigationDrawer(EDUCATION_SEARCH);
        } else if (id == R.id.nav_environment) {
            launchSearchActivityFromNavigationDrawer(ENVIRONMENT_SEARCH);
        } else if (id == R.id.nav_food) {
            launchSearchActivityFromNavigationDrawer(FOOD_SEARCH);
        } else if (id == R.id.nav_science) {
            launchSearchActivityFromNavigationDrawer(SCIENCE_SEARCH);
        } else if (id == R.id.nav_search) {
            Intent myIntent = new Intent(MainActivity.this, SearchWindowActivity.class);
            startActivity(myIntent);
    }          else if (id == R.id.nav_notification) {
            Intent myIntent = new Intent(MainActivity.this, NotificationWindowActivity.class);
            startActivity(myIntent);
    }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void launchSearchActivityFromNavigationDrawer(String key) {
        Intent myIntent = new Intent(MainActivity.this, SearchResultsActivity.class);
        myIntent.putExtra("fq", key);
        startActivity(myIntent);
    }

}
