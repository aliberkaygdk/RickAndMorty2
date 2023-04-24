package com.aliberkaygdk.rickandmorty.api;

import com.aliberkaygdk.rickandmorty.model.Character;
import com.aliberkaygdk.rickandmorty.model.Example;



import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("location")
    Call<Example> getLocation();


    @GET("character/"+"{id}")
    Call<Character>getCharsInLoc(@Path("id")int id);

}

