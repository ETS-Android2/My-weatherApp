package com.moringaschool.myweather.network;

import com.moringaschool.myweather.models.WeatherSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherApi {
    @GET("weather?q=")
    Call<WeatherSearchResponse> getWeather();

}
