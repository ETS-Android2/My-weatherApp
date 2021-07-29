package com.moringaschool.myweather.network;

import com.moringaschool.myweather.models.WeatherSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather?appid=439d4b804bc8187953eb36d2a8c26a02")

    Call<WeatherSearchResponse> getWeather(
            @Query("q") String location
    );

}
