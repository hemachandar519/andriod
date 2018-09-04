package com.gotogyms.gotogyms.gFragments;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.gotogyms.gotogyms.R;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //    RadioGroup rg;
//    RadioButton rb;
    Button userexist;
    Button newuser;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//      TextView uname = (TextView) view.findViewById(R.id.user_name);
        TextView umail = (TextView) view.findViewById(R.id.user_mail_id);
        TextView upass = (TextView) view.findViewById(R.id.user_password);
        TextView ucpass = (TextView) view.findViewById(R.id.user_cnf_password);
        TextView umobile = (TextView) view.findViewById(R.id.user_mobile);
        userexist = (Button) view.findViewById(R.id.exisitinguser);
        Button userreg = (Button) view.findViewById(R.id.userreg);
        newuser = (Button) view.findViewById(R.id.newuser);
        Button usersignup = (Button) view.findViewById(R.id.usersignup);

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
 /*   public void radio(View v){
        int genderid=rg.getCheckedRadioButtonId();
        rb=(RadioButton)v.findViewById(genderid);
    }   */

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
        Button scanbutton=(Button)getView().findViewById(R.id.scanbutton);
        scanbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                        if(v.getId()==R.id.newuser){
                            RegisterFragment regFragment = new RegisterFragment();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, regFragment).commit();
                        }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
