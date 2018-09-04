package com.gotogyms.gotogymsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class gotogyms extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    CustomSwipeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gotogyms);

        viewPager=(ViewPager)findViewById(R.id.imageslider);
        adapter=new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);

        TextView registerScreen = (TextView) findViewById(R.id.user_sign_up);

        TextView loginScreen = (TextView) findViewById(R.id.user_sign_in);

       // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Switching to Register screen
        //        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
          //      startActivity(i);
                findViewById(R.id.login_container).setVisibility(View.GONE);
                findViewById(R.id.register_container).setVisibility(View.VISIBLE);
            }
        });

        // Listening to register new account link
        loginScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(R.id.login_container).setVisibility(View.VISIBLE);
                findViewById(R.id.register_container).setVisibility(View.GONE);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
        getMenuInflater().inflate(R.menu.gotogyms, menu);
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

        if (id == R.id.nav_mypasses) {
            // Handle the camera action
        } else if (id == R.id.nav_myorders) {

        } else if (id == R.id.nav_usercheckin) {

        } else if (id == R.id.nav_contact_us) {

        }else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
