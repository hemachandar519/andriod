package com.gotogyms.gotogyms;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//import com.roughike.bottombar.BottomBar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.facebook.FacebookSdk;
import com.gotogyms.gotogyms.gFragments.CheckinFragment;
import com.gotogyms.gotogyms.gFragments.ContactFragment;
import com.gotogyms.gotogyms.gFragments.GymsFragment;
import com.gotogyms.gotogyms.gFragments.HomeFragment;
import com.gotogyms.gotogyms.gFragments.OrdersFragment;
import com.gotogyms.gotogyms.gFragments.SettingsFragment;

import java.util.Locale;

import static android.R.attr.typeface;

public class MainActivity extends AppCompatActivity{
    //    BottomBar bottomBar;
    AHBottomNavigation ahBottomNavigation;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        AssetManager assetManager=getAssets();
        Typeface customFont=Typeface.createFromAsset(assetManager,"fonts/source_angelina.ttf");

        ahBottomNavigation = (AHBottomNavigation) findViewById(R.id.bottomBar);
        this.createNavItems();
    }

    //bottomBar=BottomBar.attach(this.savedInstanceState);
//        bottomBar.setItemsFromMenu(R.menu.bottom_bar,OnMenuTabClickListener(){
    private void createNavItems() {
        AHBottomNavigationItem HomeItem = new AHBottomNavigationItem("Home", R.drawable.ic_home_launch);
        AHBottomNavigationItem GymsItem = new AHBottomNavigationItem("Find Gyms", R.mipmap.ic_app_search_gyms);
        AHBottomNavigationItem ContactItem = new AHBottomNavigationItem("Contact", R.mipmap.ic_app_phone);
        AHBottomNavigationItem OrdersItem = new AHBottomNavigationItem("Orders", R.mipmap.ic_shopping_cart_black_24dp);
        AHBottomNavigationItem CheckinItem = new AHBottomNavigationItem("Checkin", R.drawable.ic_center_focus_weak_black_24dp);

        ahBottomNavigation.addItem(HomeItem);
        ahBottomNavigation.addItem(GymsItem);
        ahBottomNavigation.addItem(ContactItem);
        ahBottomNavigation.addItem(OrdersItem);
        ahBottomNavigation.addItem(CheckinItem);

        //set properties

        ahBottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        ahBottomNavigation.setAccentColor(Color.parseColor("#0000ff"));
        ahBottomNavigation.setInactiveColor(Color.parseColor("#cc747474"));

        //  Set current item
        ahBottomNavigation.setCurrentItem(0);

        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener(){

            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                //
                switch (position) {
                    case 0:
                        HomeFragment homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, homeFragment).commit();
                        break;
                    case 1:
                        GymsFragment gymsFragment = new GymsFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, gymsFragment).commit();
                        break;
                    case 2:
                        ContactFragment contactFragment = new ContactFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, contactFragment).commit();
                        break;
                    case 3:
                        OrdersFragment ordersFragment = new OrdersFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, ordersFragment).commit();
                        break;
                    case 4:
                        CheckinFragment checkinFragment = new CheckinFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, checkinFragment).commit();
                        break;

                }
                return true;
            }
        });
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}