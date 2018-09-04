package com.gotogyms.gtogapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.PasswordAuthentication;
import java.util.Properties;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment {
    EditText email_user_name,email_id,emial_mobile,email_body;
    Button sendMail;
    public EmailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_email, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        email_user_name = (EditText)getActivity().findViewById(R.id.email_user_name);
        email_id = (EditText)getActivity().findViewById(R.id.email_id);
        emial_mobile = (EditText)getActivity().findViewById(R.id.emial_mobile);
        email_body = (EditText)getActivity().findViewById(R.id.email_body);
        sendMail=(Button)getActivity().findViewById(R.id.send_mail_message);
        sendMail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    String[] to={"contactus@gmail.com","gotogyms@gmail.com"};
                    String subject="user email";
                    String message=email_id.getText().toString()+"\n"+email_user_name.getText().toString()+"\n"+email_body.getText().toString();

                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.putExtra(Intent.EXTRA_EMAIL,to);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, message);
                    emailIntent.setType("message/rfc822");
                    startActivity(Intent.createChooser(emailIntent,"Select Email app"));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        email_body.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendMessage();
                    handled = true;
                }
                return handled;
            }
        });

    }

    public void sendMessage(){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "contactus@gotogyms.com",email_id.getText().toString(), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, email_user_name.getText().toString()+" "+emial_mobile.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, email_body.getText().toString());
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}