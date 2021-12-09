package com.example.mobilepaindiary.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherResponse {

    @SerializedName("main")
    public Main main;

    @SerializedName("name")
    public String name;

}

