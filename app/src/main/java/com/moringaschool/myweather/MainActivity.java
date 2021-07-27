package com.moringaschool.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //binds for user when they want to create an account
    @BindView(R.id.LoginInButton) Button mLoginButton;
    @BindView(R.id.signUpButton) TextView mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //set the buutton on click
        mLoginButton.setOnClickListener(this);
        mSignUpButton.setOnClickListener(this);
    }

    //intents when a user clicks the buttons
    @Override
    public void onClick(View v) {

        if(v==mSignUpButton){
//            navigate to sign up activity
            Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(intent);
            finish();
        }
        if(v==mLoginButton){
            //navigate to login activity
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}