package com.example.fetching_api_using_retrofit_1;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Model {
    @SerializedName("main")
    Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
    ArrayList<Weather>weather;

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }
}
