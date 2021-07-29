package com.moringaschool.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        mSearchCityButton.setOnClickListener(this);
        String location = mCityNameEditText.getText().toString();
        fetchWeather(location);

      
    }

    @Override
    public void onClick(View v) {
        if(v == mSearchCityButton){



        }

    }



    private void fetchWeather (String location){

        //creating a client object and using it to make a request to the Weather API.
        WeatherApi client = WeatherClient.getClient();
        Call<WeatherSearchResponse> call = client.getWeather(location);

        call.enqueue(new Callback<WeatherSearchResponse>() {
            @Override
            public void onResponse(Call<WeatherSearchResponse> call, Response<WeatherSearchResponse> response) {

                Log.d("location",location);
                if (response.isSuccessful()) {

                    mResults.setText(response.body().getName());



                }
            }

            @Override
            public void onFailure(Call<WeatherSearchResponse> call, Throwable t) {

            }
        });

    }


    public void getweather(View view) {
        String tempUrl = "";
        String city = mCityNameEditText.getText().toString().trim();
        if(city.equals("")){
            mResults.setText("City cannot be empty");
        }else {
            tempUrl= WEATHER_BASE_URL + city +"&appid" + WEATHER_API_KEY;
        }
    }
}