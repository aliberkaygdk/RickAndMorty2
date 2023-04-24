package com.aliberkaygdk.rickandmorty.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {

    @SerializedName("results")
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }



}
