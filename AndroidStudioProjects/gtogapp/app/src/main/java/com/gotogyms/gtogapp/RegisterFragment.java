package com.gotogyms.gtogapp;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    FragmentTransaction fragmentTransaction;
    Button signupbutton;
    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        TextView loginScreen=(TextView)getView().findViewById(R.id.user_sign_in);
        loginScreen.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            try {
                fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.profile_container, new LoginFragment());
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
