package com.example.mobilepaindiary.ui.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobilepaindiary.databinding.AskBinding;
import com.example.mobilepaindiary.databinding.ViewFragmentBinding;
import com.example.mobilepaindiary.ui.home.MainActivity;
import com.example.mobilepaindiary.ui.home.Map.MapsActivity;
import com.example.mobilepaindiary.ui.home.viewmodel.SharedViewModel;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public class ask extends Fragment {

    public AskBinding askBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        askBinding = AskBinding.inflate(inflater, container, false);
        View view = askBinding.getRoot();




        askBinding.address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = askBinding.username.getText().toString();

                if( address != null ){
                    Intent intent = new Intent(getActivity(), MapsActivity.class);
                    intent.putExtra("extra String",address);
                    startActivity(intent);

                }else{

                }




            }
        });


        return view;






    }










}
