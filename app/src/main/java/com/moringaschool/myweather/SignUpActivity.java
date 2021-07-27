package com.moringaschool.myweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = SignUpActivity.class.getSimpleName();
    //bind the Login Text view
    @BindView(R.id.loginTextView) TextView mAlreadyHaveAccount;//when a user aleady has an account
    //create account in Firebase
    @BindView(R.id.createUserButton) Button mCreateAccount; //button
    @BindView(R.id.confirmPasswordEditText) EditText mConfirmPassord; 
    @BindView(R.id.passwordEditText) EditText mPassordEditText;
    @BindView(R.id.emailEditText)EditText mEmailEditText;
    @BindView(R.id.nameEditText) EditText mNameEditText;
    //add member variable to get instance
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        //set TextView on click listener
        mAlreadyHaveAccount.setOnClickListener(this);
        //set Button on click listener
        mCreateAccount.setOnClickListener(this);
        //get instance of firebase
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        //navigate to Login Page on click
        if(mAlreadyHaveAccount == v){
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if(mCreateAccount == v){
            createNewUser();
        }

    }

    private void createNewUser() {
        final String name = mNameEditText.getText().toString().trim();
        final String email = mEmailEditText.getText().toString().trim();
        String password = mPassordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPassord.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this,task -> {
                    if(task.isSuccessful()){
                        Log.d(TAG, "Authentication successful");
                    }else {
                        Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}