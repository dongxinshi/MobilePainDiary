package com.example.mobilepaindiary.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mobilepaindiary.R;
import com.example.mobilepaindiary.ui.home.viewmodel.SharedViewModel;
import com.example.mobilepaindiary.ui.login.LoginActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class HomeFragment extends Fragment {

    private SharedViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(SharedViewModel.class);
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        homeViewModel.getCurrentWeather();

        Toast.makeText(getContext(), "it's time.",
                Toast.LENGTH_SHORT).show();

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}