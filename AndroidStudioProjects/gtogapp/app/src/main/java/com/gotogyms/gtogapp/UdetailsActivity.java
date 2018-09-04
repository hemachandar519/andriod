package com.gotogyms.gtogapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.gotogyms.gtogapp.RegActivity.MY_PREFS_NAME;

public class UdetailsActivity extends AppCompatActivity implements View.OnClickListener{

    EditText new_user_name,new_user_email;
    TextView new_user_city;
    Button male_button, female_button, unspecified_button, get_start;
    String gender,phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udetails);

        new_user_name=(EditText)findViewById(R.id.new_user_name);
        new_user_email=(EditText)findViewById(R.id.new_user_email);
        new_user_city=(TextView) findViewById(R.id.new_user_city);

        male_button=(Button)findViewById(R.id.male_gender);
        female_button=(Button)findViewById(R.id.female_gender);
        unspecified_button=(Button)findViewById(R.id.unspecified_gender);
        get_start=(Button)findViewById(R.id.get_stated);

        //phonenumber=getIntent().getStringExtra("USER_MOBILE");
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        phonenumber= prefs.getString("USER_MOBILE", null);

        male_button.setOnClickListener(this);
        female_button.setOnClickListener(this);
        unspecified_button.setOnClickListener(this);
        get_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            if(v.getId()==R.id.male_gender)
            {
                male_button.setBackgroundColor(getResources().getColor(R.color.genderSelected));
                female_button.setBackgroundColor(getResources().getColor(R.color.genderunSelected));
                unspecified_button.setBackgroundColor(getResources().getColor(R.color.genderunSelected));
                gender="Male";
            }
            else if(v.getId()==R.id.female_gender)
            {
                male_button.setBackgroundColor(getResources().getColor(R.color.genderunSelected));
                female_button.setBackgroundColor(getResources().getColor(R.color.genderSelected));
                unspecified_button.setBackgroundColor(getResources().getColor(R.color.genderunSelected));
                gender="Female";
            }
            else if(v.getId()==R.id.unspecified_gender){
                male_button.setBackgroundColor(getResources().getColor(R.color.genderunSelected));
                female_button.setBackgroundColor(getResources().getColor(R.color.genderunSelected));
                unspecified_button.setBackgroundColor(getResources().getColor(R.color.genderSelected));
                gender="Unspecified";
            }
            else if(v.getId()==R.id.get_stated){
                Intent intent = new Intent(UdetailsActivity.this,gotogyms.class);
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("USER_NAME", new_user_name.getText().toString());
                editor.putString("USER_EMAIL", new_user_email.getText().toString());
                editor.putString("USER_GENDER", gender);
                editor.apply();
//                intent.putExtra("USER_MOBILE",phonenumber);
  //              intent.putExtra("USER_NAME",new_user_name.getText().toString());
    //            intent.putExtra("USER_EMAIL",new_user_email.getText().toString());
      //          intent.putExtra("USER_GENDER",gender);
                startActivity(intent);
            }
    }
}
