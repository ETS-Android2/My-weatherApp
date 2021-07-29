package com.moringaschool.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.myweather.models.Main;
import com.moringaschool.myweather.models.Weather;
import com.moringaschool.myweather.models.WeatherSearchResponse;
import com.moringaschool.myweather.network.WeatherApi;
import com.moringaschool.myweather.network.WeatherClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.moringaschool.myweather.Constants.WEATHER_API_KEY;
import static com.moringaschool.myweather.Constants.WEATHER_BASE_URL;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.searchCityButton) Button mSearchCityButton;
    @BindView(R.id.cityNameEditText) EditText mCityNameEditText;
    @BindView(R.id.results) TextView mResults;
    @BindView(R.id.decResult) TextView mDecResults;
    @BindView(R.id.countryResults) TextView mCountryResults;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        mSearchCityButton.setOnClickListener(this);
        String location = mCityNameEditText.getText().toString();
        hideProgressBar();

      
    }

    @Override
    public void onClick(View v) {
        if(v == mSearchCityButton){

        getWeather(mCityNameEditText.getText().toString().trim());
        }

    }



    private void getWeather (String location){
        showProgressbar();
        //creating a client object and using it to make a request to the Weather API.
        WeatherApi client = WeatherClient.getClient().create(WeatherApi.class);
        Call<WeatherSearchResponse> call = client.getWeather(location);

        call.enqueue(new Callback<WeatherSearchResponse>() {

            @Override
            public void onResponse(Call<WeatherSearchResponse> call, Response<WeatherSearchResponse> response) {


              if(response.isSuccessful()) {
                  hideProgressBar();
                  mResults.setText("Temp:" + response.body().getMain().getTemp());
                  mDecResults.setText("Feels_like:" + response.body().getMain().getFeelsLike());
//                mHumidResults.setText(response.body().getMain().getHumidity());
                  mCountryResults.setText("Country:" + response.body().getSys().getCountry());

              }else {
                  showUnSuccsesfulMessage();
              }
            }

            @Override
            public void onFailure(Call<WeatherSearchResponse> call, Throwable t) {
                hideProgressBar();
            }
        });

    }

    private void showUnSuccsesfulMessage() {
        mErrorTextView.setText("something went wrong .please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){

        mProgressBar.setVisibility(View.GONE);
    }
    private void showProgressbar(){

        mProgressBar.setVisibility(View.VISIBLE);
    }

//for the logout
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    return super.onCreateOptionsMenu(menu);
}

    //for the logout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //for the logout
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(WeatherActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}