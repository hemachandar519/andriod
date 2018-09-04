package com.gotogyms.gtogapp;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public FragmentTransaction fragmentTransaction;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        TextView registerScreen=(TextView)getView().findViewById(R.id.user_sign_up);

        registerScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.profile_container, new RegisterFragment());
                    fragmentTransaction.commit();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
