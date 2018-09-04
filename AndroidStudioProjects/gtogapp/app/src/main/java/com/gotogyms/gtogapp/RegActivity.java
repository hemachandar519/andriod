package com.gotogyms.gtogapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class RegActivity extends AppCompatActivity {

    EditText userphone,userotp;
    Button userreg,otpsubmit;
    TextView signuptext,otptext,conftext;
    PopupWindow popUpWindow;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private ProgressDialog progressDialog;
    // [END declare_auth]

    boolean mVerificationInProgress = false;
    String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    public static final String MY_PREFS_NAME = "GotogymsPrefFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        userreg=(Button)findViewById(R.id.new_user_reg);
        otpsubmit=(Button)findViewById(R.id.new_user_otp);

        userphone=(EditText)findViewById(R.id.newusermobile);
        userotp=(EditText)findViewById(R.id.otp_code);

        otptext=(TextView)findViewById(R.id.otp_text);
        signuptext=(TextView)findViewById(R.id.signup_text);
        conftext=(TextView)findViewById(R.id.user_sign_up_text);

        progressDialog=new ProgressDialog(this);
        popUpWindow = new PopupWindow(this);

        initfireBase();
        addEventFirebaseListener();

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verificaiton without
                //     user action.
                //Log.d(TAG, "onVerificationCompleted:" + credential);
                progressDialog.hide();
                Toast.makeText(RegActivity.this,"Verification done",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegActivity.this,UdetailsActivity.class);
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("USER_MOBILE", userphone.getText().toString());
                editor.putBoolean("GOUSER_SESSION_EXISTS", true);
                editor.apply();
                intent.putExtra("USER_MOBILE",userphone.getText().toString());
                startActivity(intent);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                //Log.w(TAG, "onVerificationFailed", e);
                Toast.makeText(RegActivity.this,"Verification Failed",Toast.LENGTH_LONG).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                    Toast.makeText(RegActivity.this,"Invalid Mobile Number",Toast.LENGTH_LONG).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                    Toast.makeText(RegActivity.this,"Quota Over",Toast.LENGTH_LONG).show();
                }

                // Show a message and update the UI
                // ...
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                //Log.d(TAG, "onCodeSent:" + verificationId);
                progressDialog.hide();
                progressDialog.setMessage("OTP Sent");
                progressDialog.show();
                Toast.makeText(RegActivity.this,"Verification code sent to Mobile",Toast.LENGTH_LONG).show();
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                userphone.setVisibility(View.GONE);
                userreg.setVisibility(View.GONE);
                signuptext.setVisibility(View.GONE);
                conftext.setVisibility(View.GONE);
                userotp.setVisibility(View.VISIBLE);
                otpsubmit.setVisibility(View.VISIBLE);
                otptext.setVisibility(View.VISIBLE);
                // ...
                progressDialog.hide();
                progressDialog.setMessage("Waiting for OTP...");
                progressDialog.show();
            }
        };

        userreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Sending OTP...");
                progressDialog.show();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+userphone.getText().toString(),        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        RegActivity.this,               // Activity (for callback binding)
                        mCallbacks);        // OnVerificationStateChangedCallbacks
            }
        });

        otpsubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                PhoneAuthCredential credential=PhoneAuthProvider.getCredential(mVerificationId,userotp.getText().toString());
                signInWithPhoneAuthCredential(credential);
            }
        });
    }

    private void addEventFirebaseListener() {
        databaseReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(RegActivity.this,"Verification Done",Toast.LENGTH_LONG).show();
                            FirebaseUser user = task.getResult().getUser();
                            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                            //databaseReference.child("gotogyms_users").setValue(user.getUid(),user.);
                            Toast.makeText(RegActivity.this, (CharSequence) user,Toast.LENGTH_LONG).show();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(RegActivity.this,"Verification failed code invalid",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
    private void initfireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }
}
