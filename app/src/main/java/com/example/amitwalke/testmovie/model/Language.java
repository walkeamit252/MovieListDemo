package com.example.amitwalke.testmovie.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bharatsupekar on 10/24/18.
 */

public class Language {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
