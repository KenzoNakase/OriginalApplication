package com.trajour.trajour;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private int mGenre = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            mToolbar.setTitle("ホーム");
            mGenre = 1;
        } else if (id == R.id.nav_recordWeight) {
            Intent intent = new Intent(getApplicationContext(), RecordWeightActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_exerciseMenu) {
            mToolbar.setTitle("トレーニング メニュー");
            mGenre = 3;
        } else if (id == R.id.nav_recordDailyExercise) {
            mToolbar.setTitle("トレーニング記録をつける");
            mGenre = 4;
        } else if (id == R.id.nav_history) {
            mToolbar.setTitle("トレーニング履歴");
            mGenre = 5;
        } else if (id == R.id.nav_exerciseType) {
            mToolbar.setTitle("トレーニング種目");
            mGenre = 6;
        } else if (id == R.id.nav_rank) {
            mToolbar.setTitle("ランキング");
            mGenre = 7;
        } else if (id == R.id.nav_graph) {
            mToolbar.setTitle("グラフ");
            mGenre = 8;
        } else if (id == R.id.nav_calendar) {
            mToolbar.setTitle("カレンダー");
            mGenre = 9;
        } else if (id == R.id.nav_photos) {
            mToolbar.setTitle("カレンダー");
            mGenre = 10;
        } else if (id == R.id.nav_invitation) {
            mToolbar.setTitle("招待する");
            mGenre = 11;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
