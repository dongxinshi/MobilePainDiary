package com.example.mobilepaindiary.ui.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.mobilepaindiary.R;
import com.example.mobilepaindiary.databinding.AddFragmentBinding;
import com.example.mobilepaindiary.ui.RegisterActivity;
import com.example.mobilepaindiary.ui.home.viewmodel.SharedViewModel;
import com.example.mobilepaindiary.ui.login.LoginActivity;
import com.example.mobilepaindiary.ui.room.User;
import com.example.mobilepaindiary.ui.room.UserViewModel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    private AddFragmentBinding addBinding;
    private String degree;
    private String mood;
    private int steps ;
    private String position;
    private UserViewModel customerViewModel;
    private User user;


    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addBinding = AddFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        customerViewModel   = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().
                getApplication()).create(UserViewModel.class);


        addBinding.spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.degree);
                degree = languages[pos] ;

                Toast.makeText(getContext(), "You select :" + degree, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        addBinding.spinnerMood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.mood);
                mood = languages[pos] ;

                Toast.makeText(getContext(), "You select :" + mood, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }

        });

        addBinding.spinnerPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.position);
                position = languages[pos] ;

                Toast.makeText(getContext(), "You select :" + position, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        addBinding.saveT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPref= getContext().
                        getSharedPreferences("Message", Context.MODE_PRIVATE);
                String email= sharedPref.getString("email",null);
                Float temp = sharedPref.getFloat("temp", 0);
                Float hum = sharedPref.getFloat("hum", 0);
                Float pre = sharedPref.getFloat("pressure", 0);
                String step = addBinding.current.getText().toString();
                float steps = parseFloat(step);
                Date date = new Date();
                long times = date.getTime();//时间戳
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(date);
                int level = parseInt(degree);

                user= new User(step,temp,hum,pre,email,position,mood,level,dateString);

                customerViewModel.insert(user);
                Toast.makeText(getContext(), "User have been added", Toast.LENGTH_SHORT).show();
                String goal = addBinding.goals.getText().toString();
                float goals = parseFloat(goal);




                SharedPreferences.Editor spEditor = sharedPref.edit();
                spEditor.putFloat("step",steps );
                spEditor.putFloat("goal",goals);
                spEditor.apply();



                //addBinding.textView2.setText(email);
                addBinding.saveT3.setEnabled(false);
                addBinding.editT3.setEnabled(true);


                //addBinding.textView2.setText(message);





            }
        });



        addBinding.editT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPref= getContext().
                        getSharedPreferences("Message", Context.MODE_PRIVATE);

                String step = addBinding.current.getText().toString();
                float steps = parseFloat(step);
                Date date = new Date();
                long times = date.getTime();//时间戳
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(date);
                int level = parseInt(degree);


                User user1 = customerViewModel.getLatestCustomer();
                int id = user1.getUid();

/**
                if (android.os.Build.VERSION.SDK_INT >=
                        android.os.Build.VERSION_CODES.N) {
                    CompletableFuture<User> customerCompletableFuture =
                            customerViewModel.findByIDFuture(id);
                    ;
                    customerCompletableFuture.thenApply(user2 -> {
                        if (user2 != null) {
                            user2.setLevel(level);
                            user2.setPosition(position);
                            user2.setSteps(step);
                            user2.setMood(mood);


                            customerViewModel.update(user2);

                        }
                        return user2;
                    });
                }
 */







                //customerViewModel.insert(user);
                Toast.makeText(getContext(), "User have been updated", Toast.LENGTH_SHORT).show();






                String goal = addBinding.goals.getText().toString();
                float goals = parseFloat(goal);




                SharedPreferences.Editor spEditor = sharedPref.edit();
                spEditor.putFloat("step",steps );
                spEditor.putFloat("goal",goals);
                spEditor.apply();



                //addBinding.textView2.setText(email);
                //addBinding.saveT3.setEnabled(false);
                //addBinding.editT3.setEnabled(true);





            }
        });




        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}