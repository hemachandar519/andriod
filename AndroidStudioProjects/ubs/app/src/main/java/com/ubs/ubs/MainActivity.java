package com.ubs.ubs;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button login,register;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Ubsimages> arrayList=new ArrayList<>();

    ViewPager viewPager;
    CustomSwipeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(Button)findViewById(R.id.user_login_button);
        login.setOnClickListener(this);
        register=(Button)findViewById(R.id.new_user_button);
        register.setOnClickListener(this);

        viewPager=(ViewPager)findViewById(R.id.imageslider);
        adapter=new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.user_login_button) {
            try{
                Intent signInToMain = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(signInToMain);    }
            catch(ActivityNotFoundException e) {
                e.printStackTrace();
            }
        } else if(v.getId()==R.id.new_user_button) {
            try{
                Intent signInToMain = new Intent(MainActivity.this,RegActivity.class);
                startActivity(signInToMain);    }
            catch(ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
