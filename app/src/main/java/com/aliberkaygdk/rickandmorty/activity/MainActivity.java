package com.aliberkaygdk.rickandmorty.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


import com.aliberkaygdk.rickandmorty.OnClickListener;
import com.aliberkaygdk.rickandmorty.R;
import com.aliberkaygdk.rickandmorty.adapter.CharacterAdapter;
import com.aliberkaygdk.rickandmorty.adapter.LocationAdapter;
import com.aliberkaygdk.rickandmorty.api.ApiClient;
import com.aliberkaygdk.rickandmorty.api.ApiInterface;
import com.aliberkaygdk.rickandmorty.model.Example;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnClickListener  {

    RecyclerView recyclerView1,recyclerView2;
    LocationAdapter locationAdapter;
    CharacterAdapter characterAdapter;
    SharedPreferences sharedPreferences;

    @Override
    public void onClick() {

        sharedPreferences = getSharedPreferences("com.aliberkaygdk.rickandmorty.adapter", Context.MODE_PRIVATE);
        sharedPreferences.getInt("locid", 1);
        loadData2();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView1 = findViewById(R.id.recycler1);
        recyclerView2 = findViewById(R.id.recycler2);

        sharedPreferences = getSharedPreferences("com.aliberkaygdk.rickandmorty.adapter", Context.MODE_PRIVATE);
        sharedPreferences.getInt("locid", 1);
        loadData();
        loadData2();

        sharedPreferences.edit().clear().apply();

    }

    private void loadData(){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        apiService.getLocation().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                Log.i("MainActivity", response.toString());

                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView1.setLayoutManager(layoutManager);
                locationAdapter = new LocationAdapter(response.body().getResults());
                recyclerView1.setAdapter(locationAdapter);


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                t.printStackTrace();

            }
        });



    }
    private void loadData2(){
        ApiInterface apiService2 = ApiClient.getClient().create(ApiInterface.class);
        apiService2.getLocation().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {


                LinearLayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView2.setLayoutManager(layoutManager2);
                characterAdapter = new CharacterAdapter(response.body().getResults(),sharedPreferences.getInt("locid", 1),MainActivity.this);
                recyclerView2.setAdapter(characterAdapter);


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}