package com.example.weatheapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
   // http://api.openweathermap.org/data/2.5/weather?q=Enugu&appid=179f48e2a75510a77c55de8f02e18af2&lang


    String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    String SECOND_URL = "weather?q=Enugu&units=metric&appid=179f48e2a75510a77c55de8f02e18af2&lang=en";

   // https://openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22

    @GET(SECOND_URL)
    Call<WeatherApi> getWeather();




}