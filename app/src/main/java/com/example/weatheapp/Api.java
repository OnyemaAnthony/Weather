package com.example.weatheapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    String SECOND_URL = "weather?q=Enugu&units=metric&appid=179f48e2a75510a77c55de8f02e18af2&lang=en";

    @GET(SECOND_URL)
    Call<List<Weather>> getWeather();
