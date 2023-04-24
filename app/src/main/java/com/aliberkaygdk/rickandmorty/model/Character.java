package com.aliberkaygdk.rickandmorty.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Character {
    @SerializedName("name")
    private String name;
    public String getName() {
        return name;
    }

    @SerializedName("status")
    private String status;
    public String getStatus() {
        return status;
    }

    @SerializedName("species")
    private String species;
    public String getSpecies() {
        return species;
    }

    @SerializedName("gender")
    private String gender;
    public String getGender() {
        return gender;
    }


    @SerializedName("image")
    private String image;
    public String getImage() {
        return image;
    }


    @SerializedName("created")
    private String created;
    public String getCreated() {
        return created;
    }

    @SerializedName("origin")
    private Origin origin;
    public Origin getOrigin() {
        return origin;
    }

    @SerializedName("episode")
    private List<String> episode;
    public List<String> getEpisode() {
        return episode;
    }

    @SerializedName("location")
    private Location location;
    public Location getLocation() {
        return location;
    }


}
