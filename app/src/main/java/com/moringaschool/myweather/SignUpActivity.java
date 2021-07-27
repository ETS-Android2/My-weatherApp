package com.moringaschool.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    //bind the Login Text view
    @BindView(R.id.loginTextView) TextView mAlreadyHaveAccount;//when a user aleady has an account

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        //set TextView on click listener
        mAlreadyHaveAccount.setOnClickListener(this);
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

    }
}