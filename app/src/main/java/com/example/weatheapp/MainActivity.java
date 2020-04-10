package com.example.weatheapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private TextView temp;
    private TextView minTemp;
    private TextView maxTemp;
    private TextView humidity;
    private TextView cityText;
    private static final String TAG = "MainActivity";
    private static final String APPI_ID = "179f48e2a75510a77c55de8f02e18af2";
    //private static final String BASE_URL = "http://api.openweathermap.org/";
    private String city = "Enugu";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        FloatingActionButton fab = findViewById(R.id.fab);
        getWeather();


        temp = findViewById(R.id.temperature);
        minTemp = findViewById(R.id.temperature_min);
        maxTemp = findViewById(R.id.temperature_max);
        humidity = findViewById(R.id.humidity);
        cityText = findViewById(R.id.city_text);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getWeather() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);
        Call<WeatherApi> call = api.getWeather();
        call.enqueue(new Callback<WeatherApi>() {
            @Override
            public void onResponse(Call<WeatherApi> call, Response<WeatherApi> response) {
                //response is returnin null
                Log.d(TAG, "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<WeatherApi> call, Throwable t) {

            }
        });




    }
}

