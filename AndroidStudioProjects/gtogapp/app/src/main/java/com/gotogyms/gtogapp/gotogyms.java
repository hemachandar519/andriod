package com.gotogyms.gtogapp;

import android.*;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.gotogyms.gtogapp.LoginFragment;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import static com.gotogyms.gtogapp.RegActivity.MY_PREFS_NAME;

public class gotogyms extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentTransaction fragmentTransaction;
    DrawerLayout drawer;
    private DrawerLayout nDrawerLayout;

    TextView navusername,navuseremail,navuserphone,navusergender;
    String username,useremail,userphone,usergender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gotogyms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //  Floating button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9677045190"));
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.profile_container,new LoginFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("User Login");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);

        SharedPreferences prefs =getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        username= prefs.getString("USER_MOBILE", null);
        useremail= prefs.getString("USER_NAME", null);
        userphone= prefs.getString("USER_EMAIL", null);
        usergender= prefs.getString("USER_GENDER", null);

        navusername=(TextView)header.findViewById(R.id.nav_user_name);
        navuseremail=(TextView)header.findViewById(R.id.nav_user_email);
        navuserphone = (TextView)header.findViewById(R.id.nav_user_phone);
        navusergender=(TextView)header.findViewById(R.id.nav_user_gender);

        navusername.setText(username);
        navuseremail.setText(useremail);
        navuserphone.setText(userphone);
        navusergender.setText(usergender);

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

        if (id == R.id.nav_myprofile) {
            // Handle the camera action
            Bundle bundle = new Bundle();
            bundle.putString("USER_NAME", username);
            bundle.putString("USER_EMAIL", useremail);
            bundle.putString("USER_PHONE", userphone);
            bundle.putString("USER_GENDER", usergender);
            ProfileFragment fragobj = new ProfileFragment();
            fragobj.setArguments(bundle);
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.profile_container, fragobj);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("MY PROFILE");
            item.setChecked(true);
            drawer.closeDrawers();
        } else if (id == R.id.nav_mypasses) {
            // Handle the camera action
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.profile_container, new RegisterFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("USER REGISTRATION");
            item.setChecked(true);
            drawer.closeDrawers();
        } else if (id == R.id.nav_myaccessid) {
            //Display User qr code
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.profile_container, new AccessidFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("USER ACCESS ID");
            item.setChecked(true);
            drawer.closeDrawers();
        } else if (id == R.id.nav_myorders) {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.profile_container, new OrdersFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("MY ORDERS");
            item.setChecked(true);
            drawer.closeDrawers();
        } else if (id == R.id.nav_mylogs) {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.profile_container, new LogsFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("MY LOGS");
            item.setChecked(true);
            drawer.closeDrawers();
        }else if (id == R.id.nav_info) {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.profile_container, new InfoFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("HELP");
            item.setChecked(true);
            drawer.closeDrawers();
        }
        else if (id == R.id.nav_usercheckin) {
            try{
                Intent signInToMain = new Intent(gotogyms.this,UserCheckin.class);
                startActivity(signInToMain);
                getSupportActionBar().setTitle("USER CHECK-IN");
                item.setChecked(true);
                drawer.closeDrawers();
            }
            catch(ActivityNotFoundException e) {
                e.printStackTrace();
            }
        } else if (id == R.id.nav_search_gyms) {
    //        fragmentTransaction=getSupportFragmentManager().beginTransaction();
      //      fragmentTransaction.replace(R.id.profile_container, new SearchgymsFragment());
        //    fragmentTransaction.commit();
          //  getSupportActionBar().setTitle("SEARCH GYMS NEAR YOU");
            //item.setChecked(true);
            //drawer.closeDrawers();
        }/*else if (id == R.id.nav_logout) {

        } */else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
//            fragmentTransaction=getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.profile_container, new EmailFragment());
//            fragmentTransaction.commit();
//            getSupportActionBar().setTitle("EMAIL US");
//            item.setChecked(true);
//            drawer.closeDrawers();
            String[] to={"contactus@gotogyms.com"};
            String subject="Contact Gotogyms";
            Intent emailIntent = new Intent(Intent.ACTION_SEND,Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL,to);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, "");
            emailIntent.setType("message/rfc822");
            startActivity(Intent.createChooser(emailIntent,"Select Email with"));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
