package com.gotogyms.gotogyms.gFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gotogyms.gotogyms.R;


public class RegisterFragment extends Fragment {

    //    RadioGroup rg;
//    RadioButton rb;
    Button userexist;
    Button newuser;
    FrameLayout signupLayout;
    FrameLayout loginLayout;
    Fragment regFragment;
    Fragment loginFragment;
    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        TextView uname = (TextView) view.findViewById(R.id.user_name);
        TextView umail = (TextView) view.findViewById(R.id.user_mail_id);
        TextView upass = (TextView) view.findViewById(R.id.user_password);
        TextView ucpass = (TextView) view.findViewById(R.id.user_cnf_password);
        TextView umobile = (TextView) view.findViewById(R.id.user_mobile);
        userexist = (Button) view.findViewById(R.id.exisitinguser);
        Button userreg = (Button) view.findViewById(R.id.userreg);
        newuser = (Button) view.findViewById(R.id.newuser);
        Button usersignup = (Button) view.findViewById(R.id.usersignup);
        userexist.setOnClickListener((View.OnClickListener) this.getActivity());
        newuser.setOnClickListener((View.OnClickListener) this.getActivity());
        //       Button googlogin = (Button) view.findViewById(R.id.googlelogin);
//        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        //      Button fblogin = (Button) view.findViewById(R.id.fbloginbutton);
        //      TextView ucity = (TextView) view.findViewById(R.id.user_city);
        //    rg=(RadioGroup)view.findViewById(R.id.user_gender);
        //     Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/source_angelina.ttf");
        //   uname.setTypeface(font);
        //  umail.setTypeface(font);
        //   umobile.setTypeface(font);
        //   ucity.setTypeface(font);
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getFragmentManager().findFragmentById(R.id.homefragment);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        Button scanbutton = (Button) getView().findViewById(R.id.scanbutton);
        scanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (v.getId() == R.id.exisitinguser) {
                        HomeFragment loginFragment = new HomeFragment();;
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, loginFragment).commit();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
