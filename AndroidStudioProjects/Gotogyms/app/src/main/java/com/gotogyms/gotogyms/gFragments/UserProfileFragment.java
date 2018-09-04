package com.gotogyms.gotogyms.gFragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gotogyms.gotogyms.R;

/**
 * Created by Jyothsna on 7/7/2017.
 */

public class UserProfileFragment extends Fragment {
    // Inflate the layout for this fragment

public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    View view=inflater.inflate(R.layout.fragment_home,container,false);
    TextView emailtxt = (TextView) view.findViewById(R.id.home_user_email);
    TextView phonetxt = (TextView) view.findViewById(R.id.home_user_phone);
    TextView nametxt = (TextView) view.findViewById(R.id.home_user_name);
    TextView citytxt = (TextView) view.findViewById(R.id.home_user_city);
    TextView dobtxt = (TextView) view.findViewById(R.id.home_user_dob);
    TextView gendertxt = (TextView) view.findViewById(R.id.home_user_gender);

    Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/source_angelina.ttf");
        emailtxt.setTypeface(font);
        phonetxt.setTypeface(font);
        nametxt.setTypeface(font);
        citytxt.setTypeface(font);
        dobtxt.setTypeface(font);
        gendertxt.setTypeface(font);

        return view;
    }
}
