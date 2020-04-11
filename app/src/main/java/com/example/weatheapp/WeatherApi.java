package com.example.weatheapp;


import android.util.Log;

import java.util.List;

public class WeatherApi {


    public Main main;



    public class Main {
        public String temp;
        public String humidity;
        public String temp_min;
        public String temp_max;

        public String getTemp() {
            return temp;
        }

        public String getHumidity() {
            return humidity;
        }

        public String getTemp_min() {
            return temp_min;
        }

        public String getTemp_max() {
            return temp_max;
        }
    }
}