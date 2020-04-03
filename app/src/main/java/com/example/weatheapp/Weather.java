package com.example.weatheapp;

public class Weather {
    private String temp;
    private String temp_min;
    private String temp_max;
    private String humidity;



    public Weather(String temp, String temp_min, String temp_max, String humidity) {
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.humidity = humidity;
    }

    public String getTemp() {
        return temp;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public String getHumidity() {
        return humidity;
    }
}
