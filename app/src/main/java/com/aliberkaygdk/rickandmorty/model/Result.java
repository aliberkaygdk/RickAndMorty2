package com.aliberkaygdk.rickandmorty.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result{
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("residents")
    private List<String> residents;


    public List<String> getResidents() {
        return residents;
    }



    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }



}
