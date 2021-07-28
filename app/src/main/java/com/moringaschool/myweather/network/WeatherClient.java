package com.moringaschool.myweather.network;

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

    public static WeatherApi getClient() {
        if (retrofit == null) {
            //OkHttpClient intercepts each request and adds an HTTP Authorization header with the value of the Yelp API token.
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest = chain.request().newBuilder()
                                    .addHeader("Authorization", WEATHER_API_KEY)
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    })
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(WEATHER_BASE_URL)
                    .client(okHttpClient)
                    //converter handles data serialization from JSON to Java objects
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(WeatherApi.class);
    }
}
