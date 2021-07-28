package com.moringaschool.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        //creating a client object and using it to make a request to the Weather API.
        WeatherApi client = WeatherClient.getClient();
        Call<WeatherSearchResponse> call = client.getWeather();

        call.enqueue(new Callback<WeatherSearchResponse>(){
            @Override
            public void onResponse(Call<WeatherSearchResponse> call, Response<WeatherSearchResponse> response) {

                if(response.isSuccessful()){
                    List<Weather> WeatherList = response.body().getWeather();
                    String[] Weather = new String[WeatherList.size()];
                    for(int i=0; i < Weather.length;i++){
                        Weather[i] = WeatherList.get(i).getDescription();
                    }
                    ArrayAdapter adapter = new ArrayAdapter(WeatherActivity.this, android.R.layout.simple_list_item_1,Weather);
                    mResults.setText((CharSequence) adapter);
                }
            }

            @Override
            public void onFailure(Call<WeatherSearchResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    public void getWeatherDetails(View view) {
    }
}