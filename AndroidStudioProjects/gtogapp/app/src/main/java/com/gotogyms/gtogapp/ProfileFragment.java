package com.gotogyms.gtogapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;
import static com.gotogyms.gtogapp.RegActivity.MY_PREFS_NAME;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    Button editButton,updateButton;
    String username,useremail,userphone,usergender;
    TextView view_username,view_useremail,view_usermobile,view_usercity;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Bundle bundle = this.getArguments();
        //username = bundle.getString("USER_NAME");
        //useremail = bundle.getString("USER_EMAIL");
        //userphone = bundle.getString("USER_PHONE");
        //usergender = bundle.getString("USER_GENDER");
        SharedPreferences prefs =getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        username= prefs.getString("USER_MOBILE", null);
        useremail= prefs.getString("USER_NAME", null);
        userphone= prefs.getString("USER_EMAIL", null);
        usergender= prefs.getString("USER_GENDER", null);

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        editButton=(Button)getView().findViewById(R.id.edit_details);
        updateButton=(Button)getView().findViewById(R.id.update_details);

        final TextView view_username=(TextView)getView().findViewById(R.id.view_conusrname);
        final TextView view_useremail=(TextView)getView().findViewById(R.id.view_conusremail);
        final TextView view_usermobile=(TextView)getView().findViewById(R.id.view_conusrmobile);
        final TextView view_usercity=(TextView)getView().findViewById(R.id.view_usercity);
        final TextView view_usergender=(TextView) getView().findViewById(R.id.view_gender);
        final TextView view_userdob=(TextView)getView().findViewById(R.id.view_userdob);

        view_username.setText(username);
        view_useremail.setText(useremail);
        view_usermobile.setText(userphone);
        view_usercity.setText("HYDERABAD");
        view_usergender.setText(usergender);

        final EditText edit_username=(EditText) getView().findViewById(R.id.edit_conusrname);
        final EditText edit_useremail=(EditText) getView().findViewById(R.id.edit_conusremail);
        final EditText edit_usermobile=(EditText) getView().findViewById(R.id.edit_conusrmobile);
        final EditText edit_usercity=(EditText) getView().findViewById(R.id.edit_usercity);
        final EditText edit_usergender=(EditText) getView().findViewById(R.id.edit_gender);
        final EditText edit_userdob=(EditText) getView().findViewById(R.id.edit_userdob);

        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    editButton.setVisibility(View.GONE);
                    updateButton.setVisibility(View.VISIBLE);

                    // User Name
                    if(view_username.getText().equals("Name"))
                    {  edit_username.setText("");   }
                    else
                    { edit_username.setText(view_username.getText());   }
                    edit_username.setVisibility(View.VISIBLE);

                    // User Gender
                    edit_usergender.setText(view_usergender.getText());
                    edit_usergender.setVisibility(View.VISIBLE);

                    // User email
                    if(view_useremail.getText().equals("Email Id"))
                    {  edit_useremail.setText("");   }
                    else
                    { edit_useremail.setText(view_useremail.getText());   }
                    edit_useremail.setVisibility(View.VISIBLE);

                    // User Mobile
                    if(view_usermobile.getText().equals("Mobile Number"))
                    {  edit_usermobile.setText(""); }
                    else
                    {  edit_usermobile.setText(view_usermobile.getText()); }
                    edit_usermobile.setVisibility(View.VISIBLE);


                    // User City
                    if(view_usercity.getText().equals("City"))
                    {  edit_usercity.setText(""); }
                    else
                    {  edit_usercity.setText(view_usercity.getText()); }
                    edit_usercity.setVisibility(View.VISIBLE);

                    // User City
                    if(view_userdob.getText().equals("City"))
                    {  edit_userdob.setText(""); }
                    else
                    {  edit_userdob.setText(view_userdob.getText()); }
                    edit_userdob.setVisibility(View.VISIBLE);

                    //  Make View Text as invisible
                    view_username.setVisibility(View.GONE);
                    view_useremail.setVisibility(View.GONE);
                    view_usermobile.setVisibility(View.GONE);
                    view_usercity.setVisibility(View.GONE);
                    view_userdob.setVisibility(View.GONE);
                    view_usergender.setVisibility(View.GONE);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    editButton.setVisibility(View.VISIBLE);
                    updateButton.setVisibility(View.GONE);
                    // User Name
                    if(edit_username.getText().equals("Name"))
                    {  view_username.setText("");   }
                    else
                    { view_username.setText(edit_username.getText());   }
                    view_username.setVisibility(View.VISIBLE);

                    edit_username.setVisibility(View.GONE);
                    edit_useremail.setVisibility(View.GONE);
                    edit_usermobile.setVisibility(View.GONE);
                    edit_usercity.setVisibility(View.GONE);
                    edit_userdob.setVisibility(View.GONE);
                    edit_usergender.setVisibility(View.GONE);
//                    view_username.setVisibility(View.VISIBLE);
                    view_useremail.setVisibility(View.VISIBLE);
                    view_usermobile.setVisibility(View.VISIBLE);
                    view_usercity.setVisibility(View.VISIBLE);
                    view_userdob.setVisibility(View.VISIBLE);
                    view_usergender.setVisibility(View.VISIBLE);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
