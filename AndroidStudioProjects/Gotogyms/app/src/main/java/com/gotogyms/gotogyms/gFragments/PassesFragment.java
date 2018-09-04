package com.gotogyms.gotogyms.gFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gotogyms.gotogyms.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PassesFragment extends Fragment {


    public PassesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_passes, container, false);
        return view;
    }

}
