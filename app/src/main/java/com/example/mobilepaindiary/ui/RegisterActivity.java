package com.example.mobilepaindiary.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.mobilepaindiary.databinding.ActivityRegisterBinding;
import com.example.mobilepaindiary.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.mobilepaindiary.R;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    public ActivityRegisterBinding activityRegisterBinding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = activityRegisterBinding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isUserNameValid(activityRegisterBinding.username.getText().toString())){
                    activityRegisterBinding.username.setError("not an email form");
                    activityRegisterBinding.signUp.setEnabled(false);
                } else{
                    activityRegisterBinding.signUp.setEnabled(true);
                }

                if (!isPasswordValid(activityRegisterBinding.password.getText().toString())){
                    activityRegisterBinding.password.setError("8 long and contain upper and lower");
                    activityRegisterBinding.signUp.setEnabled(false);
                } else{
                    activityRegisterBinding.signUp.setEnabled(true);
                }

            }
        };
        activityRegisterBinding.username.addTextChangedListener(afterTextChangedListener);
        activityRegisterBinding.password.addTextChangedListener(afterTextChangedListener);

        activityRegisterBinding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 loginViewModel.login(
                 activityLoginBinding.username.getText().toString(),
                 activityLoginBinding.password.getText().toString());
                 //Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                 //startActivity(intent);
                 //*/
                String email = activityRegisterBinding.username.getText().toString();
                String password = activityRegisterBinding.password.getText().toString();
                register1(email,password);




            }
        });





    }

    public void register1(String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(RegisterActivity.this, "Success turn to login page.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);


                        } else {


                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();


                        }
                    }
                });
        //Toast.makeText(RegisterActivity.this, "test.", Toast.LENGTH_SHORT).show();
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {

        // check upper case username.equals(username.toLowerCase())
        if (username != null && username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return false;
        }
    }


    // A placeholder password validation check
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