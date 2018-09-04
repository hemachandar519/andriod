package com.gotogyms.gotogyms;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class gotogymsSignin extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{
    EditText gusername,guserpass;
    private Toolbar nToolbar;
    /* progress bar */
    private ProgressBar spinner;

    /*google sign in    */
    private LinearLayout profile_sectoin;
    private Button signout,glogin;
    private SignInButton signInButton;
    private TextView usrname,usremail;
    private ImageView prof_pic;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE=9001;
    /* facebook login */
    LoginButton loginButton;
    CallbackManager callbackManager;
    private static int RC_FB_SIGN_IN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_gotogyms_signin);

        gusername=(EditText)findViewById(R.id.gusrname);
        guserpass=(EditText)findViewById(R.id.gusrpass);

        nToolbar=(Toolbar)findViewById(R.id.gtoolbar);
        setSupportActionBar(nToolbar);

        /* Google Sign in */

        profile_sectoin=(LinearLayout)findViewById(R.id.userprofile);

        signout=(Button)findViewById(R.id.glogout);
        signInButton=(SignInButton)findViewById(R.id.googlelogin);
        glogin=(Button)findViewById(R.id.glogin);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        usrname=(TextView)findViewById(R.id.username);
        usremail=(TextView)findViewById(R.id.useremail);
        prof_pic=(ImageView)findViewById(R.id.userpic);

        /* facebook login */

        loginButton=(LoginButton)findViewById(R.id.fbloginbutton);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(gotogymsSignin.this, loginResult.getAccessToken().getUserId(),
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(gotogymsSignin.this, "Login Cancelled",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        RC_FB_SIGN_IN =loginButton.getRequestCode();
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.gotogyms.gotogyms",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        signInButton.setOnClickListener(this);
        glogin.setOnClickListener(this);
        signout.setOnClickListener(this);

        profile_sectoin.setVisibility(View.GONE);
        GoogleSignInOptions signInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();
    }

    public void onLogin(View view){
        String uname=gusername.getText().toString();
        String upass=guserpass.getText().toString();
        String type="login";
        backgroundWorker bgWorker= new backgroundWorker(this);
        bgWorker.execute(type,uname,upass);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.googlelogin:
                signIn();
            case R.id.fbloginbutton:
                signIn();
            case R.id.glogout:
                signOut();
            case R.id.glogin:
                spinner.setVisibility(View.VISIBLE);
                final String uname=gusername.getText().toString();
                if (!isValidEmail(uname)) {
                    gusername.setError("Invalid Email");
                }else {
                    final String upass = guserpass.getText().toString();
                    if (!isValidPassword(upass)) {
                        guserpass.setError("Invalid Password");
                    }else {
                        String type = "login";
                        backgroundWorker bgWorker = new backgroundWorker(this);
                        bgWorker.execute(type, uname, upass);
                    }
                }
        }
    }

    private void signIn(){
        Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);
    }

    private void signOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    private void handleResult(GoogleSignInResult signInResult){
        if(signInResult.isSuccess()){
            GoogleSignInAccount account=signInResult.getSignInAccount();
            String name=account.getDisplayName();
            String email=account.getEmail();
            String img_url=account.getPhotoUrl().toString();
            usrname.setText(name);
            usremail.setText(email);
            Glide.with(this).load(img_url).into(prof_pic);
            updateUI(true);
        }else{
            updateUI(false);
        }
    }

    private void updateUI(boolean isLogin) {
        if (isLogin) {
            profile_sectoin.setVisibility(View.VISIBLE);
            gusername.setVisibility(View.GONE);
            guserpass.setVisibility(View.GONE);
            glogin.setVisibility(View.GONE);
            signInButton.setVisibility(View.GONE);
        }
        else{
            gusername.setVisibility(View.VISIBLE);
            guserpass.setVisibility(View.VISIBLE);
            glogin.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.VISIBLE);
            profile_sectoin.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==RC_FB_SIGN_IN)
        {
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==REQ_CODE){
                GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleResult(result);
            }
        }
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
