package com.example.mobilepaindiary.data.model;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    public float temp;
    @SerializedName("humidity")
    public float humidity;
    @SerializedName("pressure")
    public float pressure;

}
