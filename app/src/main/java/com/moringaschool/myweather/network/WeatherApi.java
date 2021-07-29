package com.moringaschool.myweather.network;

import com.moringaschool.myweather.models.WeatherSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("/data/2.5/weather")
    Call<WeatherSearchResponse> getWeather(
            @Query("q") String location
    );

}
