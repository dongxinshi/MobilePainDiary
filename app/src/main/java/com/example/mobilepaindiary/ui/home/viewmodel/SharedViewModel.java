package com.example.mobilepaindiary.ui.home.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilepaindiary.data.model.WeatherResponse;
import com.example.mobilepaindiary.data.retrofit.RetrofitClient;
import com.example.mobilepaindiary.data.retrofit.RetrofitInterface;
import com.google.android.gms.common.util.SharedPreferencesUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.mobilepaindiary.MyApplication.getContext;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private RetrofitInterface retrofitInterface;



    public SharedViewModel() {
        mText = new MutableLiveData<>();


        retrofitInterface = RetrofitClient.getRetrofitService();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setMessage(String text) {
        mText.setValue(text);
    }
    public void getCurrentWeather(){
        Call<WeatherResponse> callAsync =
                retrofitInterface.getWeatherByCity("shanghai,China");

        callAsync.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call,
                                   Response<WeatherResponse> response) {
                Log.d("Weather Response ",response.body().toString());
                if (response.isSuccessful()) {
//                    List<Items> list = response.body().items;
                    WeatherResponse weatherResponse = response.body();
                    String result= response.body().toString();
                    /**
                    SharedPreferences sharedPref= requireActivity().
                            getSharedPreferences("Message", Context.MODE_PRIVATE);
                    SharedPreferences.Editor spEditor = sharedPref.edit();
                    spEditor.putFloat("message",weatherResponse.main.temp );

                    spEditor.apply();
                     */

                    SharedPreferences sharedPref= getContext().
                            getSharedPreferences("Message", Context.MODE_PRIVATE);
                    SharedPreferences.Editor spEditor = sharedPref.edit();
                    spEditor.putFloat("temp",weatherResponse.main.temp );
                    spEditor.putFloat("hum",weatherResponse.main.humidity );
                    spEditor.putFloat("pressure",weatherResponse.main.pressure );

                    spEditor.apply();




                    mText.setValue("Current location is : " + weatherResponse.name
                            + "\n"
                            + "Current temperature is : " + weatherResponse.main.temp
                            + "\n"
                            + "Current humidity is : " + weatherResponse.main.humidity
                            + "\n"
                            + "Current pressure is : " + weatherResponse.main.pressure);
                }
                else {
                    Log.i("Error!","failed");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("Error!","can't reach API: " + t);
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }
}