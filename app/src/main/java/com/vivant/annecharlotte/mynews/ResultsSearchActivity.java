package com.vivant.annecharlotte.mynews;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ResultsSearchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ClipData.Item searchItem;

    private String mFQuery ;
    private String mQuery;
    private String mBeginDate ;
    private String mEndDate ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_resultssearch);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_resultssearch);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_resultssearch);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFQuery = getIntent().getStringExtra("q");
        mQuery = getIntent().getStringExtra("fq");
        mBeginDate = getIntent().getStringExtra("begin_date");
        mEndDate = getIntent().getStringExtra("end_date");

        // prepare informations for giving them to fragment
        Bundle bundle = new Bundle();
        bundle.putString("q", getIntent().getStringExtra("q"));
        bundle.putString("fq", getIntent().getStringExtra("fq"));
        bundle.putString("begin_date", getIntent().getStringExtra("begin_date"));
        bundle.putString("end_date", getIntent().getStringExtra("end_date"));
        // set Fragmentclass Arguments
        ResultSearchFragment fragobj = new ResultSearchFragment();
        fragobj.setArguments(bundle);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_resultssearch);
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
            Intent myIntent = new Intent(ResultsSearchActivity.this, NotificationActivity.class);
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
            Intent myIntent = new Intent(ResultsSearchActivity.this, SearchActivity.class);
            this.startActivity(myIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
