package com.example.fetching_api_using_retrofit_1;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static String Base_URL="https://api.openweathermap.org/data/2.5/";

    public static Retrofit getRetrofitInstance()
    {
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(Base_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
