package com.example.weatheapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        FloatingActionButton fab = findViewById(R.id.fab);
        getWeather("Enugu");


        temp = findViewById(R.id.temperature);
        minTemp = findViewById(R.id.temperature_min);
        maxTemp = findViewById(R.id.temperature_max);
        humidity = findViewById(R.id.humidity);
        cityText = findViewById(R.id.city_text);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchDalog();
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
            searchDalog();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getWeather(String city) {
               String SECOND_URL = "weather?q="+city+"&units=metric&appid=179f48e2a75510a77c55de8f02e18af2&lang=en";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<WeatherApi> call = api.getWeather(SECOND_URL);
        call.enqueue(new Callback<WeatherApi>() {
            @Override
            public void onResponse(Call<WeatherApi> call, Response<WeatherApi> response) {
                Log.d(TAG, "onResponse: "+response.isSuccessful());

                if (response.isSuccessful()){

                    WeatherApi weatherResponse = response.body();

                    Log.d(TAG, "onResponse: "+weatherResponse.main.getTemp_min());

                 temp.setText(weatherResponse.main.getTemp());
                 minTemp.setText(weatherResponse.main.getTemp_min());
                 maxTemp.setText(weatherResponse.main.getTemp_max());
                 humidity.setText(weatherResponse.main.getHumidity());

                }
            }

            @Override
            public void onFailure(Call<WeatherApi> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error: "+t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: "+t.getMessage());

            }
        });

    }
    private void searchDalog(){
        builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.search,null,false);
        TextView searchText = view.findViewById(R.id.search_text);
        Button searchButton = view.findViewById(R.id.submit_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searchText.getText().toString().isEmpty()){
                    getWeather(searchText.getText().toString());
                    dialog.dismiss();

                }else {
                    Snackbar.make(v,"Enter a valid city name",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }
}

