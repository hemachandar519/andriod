package com.gotogyms.gotogyms;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;

import com.gotogyms.gotogyms.gFragments.CheckinFragment;
import com.gotogyms.gotogyms.gFragments.ContactFragment;
import com.gotogyms.gotogyms.gFragments.HomeFragment;
import com.gotogyms.gotogyms.gFragments.OrdersFragment;
import com.gotogyms.gotogyms.gFragments.PassesFragment;
import com.gotogyms.gotogyms.gFragments.SettingsFragment;

public class homepage extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    private DrawerLayout nDrawerLayout;
    private ActionBarDrawerToggle nToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        nDrawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        nToggle=new ActionBarDrawerToggle(this, nDrawerLayout, R.string.open,R.string.close);
        nDrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                /* Fragments  */
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.profile_container,new HomeFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home Fragment");
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_myprofile:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.profile_container, new HomeFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Home Fragment");
                        item.setChecked(true);
                        nDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_mypasses:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.profile_container, new PassesFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Passes Fragment");
                        item.setChecked(true);
                        nDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_usercheckin:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.profile_container, new CheckinFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Checkin Fragment");
                        item.setChecked(true);
                        nDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_myorders:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.profile_container, new OrdersFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Orders Fragment");
                        item.setChecked(true);
                        nDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_settings:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.profile_container, new SettingsFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Settings Fragment");
                        item.setChecked(true);
                        nDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_contact_us:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.profile_container, new ContactFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Contact Us Fragment");
                        item.setChecked(true);
                        nDrawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(nToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
