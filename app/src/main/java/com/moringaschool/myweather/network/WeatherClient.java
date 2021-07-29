package com.moringaschool.myweather.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.moringaschool.myweather.Constants.WEATHER_API_KEY;
import static com.moringaschool.myweather.Constants.WEATHER_BASE_URL;

public class WeatherClient {

    private static Retrofit retrofit = null;

     public static Retrofit getClient() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://openweathermap.org/data/2.5/")
                    //converter handles data serialization from JSON to Java objects
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;
    }
}
