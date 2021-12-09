package com.example.mobilepaindiary.data.retrofit;


import com.example.mobilepaindiary.data.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    String BASE_PARAM = "weather?APPID=54ad2b34cc8fd035b86f6ea3d0348a53&units=metric";

    @GET(BASE_PARAM)
    Call<WeatherResponse> getWeatherByCity(
            @Query("q") String keyword);

    @GET(BASE_PARAM)
    Call<WeatherResponse> getWeatherByLatLon(
            @Query("lat") String lat,
            @Query("lon") String lon);
}
