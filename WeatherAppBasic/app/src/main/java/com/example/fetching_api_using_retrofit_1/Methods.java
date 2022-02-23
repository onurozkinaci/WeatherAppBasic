package com.example.fetching_api_using_retrofit_1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Methods {
   @GET("weather")
   //Call<Model>getWeather(@Query("lat") String lat,@Query("lon") String lon,@Query("appid") String apiKey);
   Call<Model>getWeather(@Query("q") String cityName,@Query("appid") String apiKey);
}
