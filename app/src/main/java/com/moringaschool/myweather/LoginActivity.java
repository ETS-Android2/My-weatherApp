package com.moringaschool.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    //signActivity TextView
    @BindView(R.id.registerTextView) TextView mRegesterText; //for navigating to signup

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mRegesterText.setOnClickListener(this);//for signup TextView
    }


    //set view on click for navigating to the signup button
    @Override
    public void onClick(View v) {
        if(v==mRegesterText){
            //navigate to sinup activity
            Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
            startActivity(intent);
            finish();
        }

    }
}