package com.gotogyms.gotogyms.gFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gotogyms.gotogyms.Gympartners;
import com.gotogyms.gotogyms.GymsAdapter;
import com.gotogyms.gotogyms.R;
import com.gotogyms.gotogyms.gotogymsSignin;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GymsFragment extends Fragment implements SearchView.OnQueryTextListener  {

    /* search Toolbar  */
    GymsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Gympartners> arrayList=new ArrayList<>();

    public GymsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gyms, container, false);

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Toolbar nToolbar=(Toolbar)view.findViewById(R.id.fragtoolbar);
        AppCompatActivity activity=(AppCompatActivity)getActivity();
        activity.setSupportActionBar(nToolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.gyms_disp_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        String[] gym_location={"ameerpeta","kodapur","madhapur"};
        int[] gym_pics={R.drawable.ameerpeta,R.drawable.kondapur,R.drawable.madhapur};

        recyclerView.setHasFixedSize(true);

        int count=0;
        for(String Name:gym_location){
            arrayList.add(new Gympartners(Name,gym_pics[count]));
            count++;
        }

        adapter = new GymsAdapter(arrayList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.search_items,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
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
