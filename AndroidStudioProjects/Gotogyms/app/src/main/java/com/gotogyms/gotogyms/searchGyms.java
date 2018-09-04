package com.gotogyms.gotogyms;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class searchGyms extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar nToolbar;

    /* search Toolbar  */
    String[] gym_location={"ameerpeta","kodapur","madhapur"};
    int[] gym_pics={R.drawable.ameerpeta,R.drawable.kondapur,R.drawable.madhapur};
    RecyclerView recyclerView;
    GymsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Gympartners> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nToolbar=(Toolbar)findViewById(R.id.gtoolbar);
        setSupportActionBar(nToolbar);

        recyclerView=(RecyclerView)findViewById(R.id.gyms_disp_view);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        int count=0;
        for(String Name:gym_location){
            arrayList.add(new Gympartners(Name,gym_pics[count]));
            count++;
        }
        adapter=new GymsAdapter(arrayList);
        recyclerView.setAdapter(adapter);

        Button loginbutton = (Button) findViewById(R.id.login);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), gotogymsSignin.class);
                view.getContext().startActivity(Intent);}
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_items,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText =newText.toLowerCase();
        ArrayList<Gympartners> newList=new ArrayList<>();
        for(Gympartners gympartners : arrayList)
        {
            String name=gympartners.getName().toLowerCase();
            if(name.contains(newText))
            {
                newList.add(gympartners);
            }

        }
        adapter.setFilter(newList);
        return true;
    }
}
