package com.example.mobilepaindiary.ui.login;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.mobilepaindiary.R;
import com.example.mobilepaindiary.data.model.LoggedInUser;
import com.example.mobilepaindiary.databinding.ActivityLoginBinding;
import com.example.mobilepaindiary.ui.RegisterActivity;
import com.example.mobilepaindiary.ui.home.MainActivity;
import com.example.mobilepaindiary.ui.home.fragment.AddFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.mobilepaindiary.MyApplication.getContext;


public class LoginActivity extends AppCompatActivity {

    public ActivityLoginBinding activityLoginBinding;

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = activityLoginBinding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();




///**






        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isUserNameValid(activityLoginBinding.username.getText().toString())){
                    activityLoginBinding.username.setError("not an email form");
                    activityLoginBinding.loginButton.setEnabled(false);
                } else{
                    activityLoginBinding.loginButton.setEnabled(true);
                }

                if (!isPasswordValid(activityLoginBinding.password.getText().toString())){
                    activityLoginBinding.password.setError("8 long and contain upper and lower");
                    activityLoginBinding.loginButton.setEnabled(false);
                } else{
                    activityLoginBinding.loginButton.setEnabled(true);
                }

            }
        };
        activityLoginBinding.username.addTextChangedListener(afterTextChangedListener);
        activityLoginBinding.password.addTextChangedListener(afterTextChangedListener);


        activityLoginBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 /**
                loginViewModel.login(
                        activityLoginBinding.username.getText().toString(),
                        activityLoginBinding.password.getText().toString());
                //Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                //startActivity(intent);
                 //*/
                String email = activityLoginBinding.username.getText().toString();
                String password = activityLoginBinding.password.getText().toString();
                login(email,password);




            }
        });







            activityLoginBinding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "turn to the register page", Toast.LENGTH_SHORT).show();





            }
        });

    }
    public void login(String email, String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this, "Authentication sucess.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                           // /**
                            SharedPreferences sharedPref= getContext().
                                    getSharedPreferences("Message", Context.MODE_PRIVATE);
                            SharedPreferences.Editor spEditor = sharedPref.edit();
                            spEditor.putString("email",email );

                            spEditor.apply();
                             //*/





                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);


                            startActivity(intent);


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();


                        }
                    }
                });
        Toast.makeText(LoginActivity.this, "Authentication.", Toast.LENGTH_SHORT).show();
    }


    private boolean isUserNameValid(String username) {

        // check upper case username.equals(username.toLowerCase())
        if (username != null && username.contains("@")) {
            return true;
        } else {
            return false;
        }
    }



    private boolean isPasswordValid(String password) {
        boolean isDigit = false;
        boolean isLetter = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                isDigit = true;
            }
            if (Character.isLetter(password.charAt(i))) {
                isLetter = true;
            }
        }
        if( password != null && password.trim().length() > 7 && isDigit && isLetter){
            return true;
        }else{
            return false;
        }
    }
}