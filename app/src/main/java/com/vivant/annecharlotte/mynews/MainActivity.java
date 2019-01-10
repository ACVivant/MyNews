package com.vivant.annecharlotte.mynews;


import android.content.ClipData;
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
import android.widget.Toast;

import com.vivant.annecharlotte.mynews.Views.TabPagerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ClipData.Item searchItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_resultssearch);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notifications) {
            Intent myIntent = new Intent(MainActivity.this, NotificationActivity.class);
            this.startActivity(myIntent);
            return true;
        }

        if (id == R.id.action_help) {
            Toast.makeText(this, "Help n'est pas encore disponible", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.action_about) {
            Toast.makeText(this, "About n'est pas encore disponible", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.menu_activity_main_search) {
            Intent myIntent = new Intent(MainActivity.this, SearchActivity.class);
            this.startActivity(myIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        String EDUCATION_SEARCH = "source:(\"The New York Times\")" + " AND" + " news_desk:(\"Education\")";
        String ENERGY_SEARCH = "source:(\"The New York Times\")" + " AND" + " news_desk:(\"Energy\")";
        String ENVIRONMENT_SEARCH = "source:(\"The New York Times\")" + " AND" + " news_desk:(\"Environment\")";
        String FOOD_SEARCH = "source:(\"The New York Times\")" + " AND" + " news_desk:(\"Food\")";
        String SCIENCE_SEARCH = "source:(\"The New York Times\")" + " AND" + " news_desk:(\"Science\")";

        int id = item.getItemId();

        if (id == R.id.nav_education) {
            Intent myIntent = new Intent(MainActivity.this, ResultsSearchActivity.class);
            myIntent.putExtra("fq", EDUCATION_SEARCH);
            startActivity(myIntent);
        } else if (id == R.id.nav_environment) {
            Intent myIntent = new Intent(MainActivity.this, ResultsSearchActivity.class);
            myIntent.putExtra("fq", ENVIRONMENT_SEARCH);
            startActivity(myIntent);
        } else if (id == R.id.nav_food) {
            Intent myIntent = new Intent(MainActivity.this, ResultsSearchActivity.class);
            myIntent.putExtra("fq", FOOD_SEARCH);
            startActivity(myIntent);
        } else if (id == R.id.nav_science) {
            Intent myIntent = new Intent(MainActivity.this, ResultsSearchActivity.class);
            myIntent.putExtra("fq", SCIENCE_SEARCH);
            startActivity(myIntent);
        } else if (id == R.id.nav_search) {
            Intent myIntent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(myIntent);
    }          else if (id == R.id.nav_notification) {
            Intent myIntent = new Intent(MainActivity.this, NotificationActivity.class);
            startActivity(myIntent);
    }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
