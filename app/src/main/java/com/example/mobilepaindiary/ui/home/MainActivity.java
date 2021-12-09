package com.example.mobilepaindiary.ui.home;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mobilepaindiary.R;
import com.example.mobilepaindiary.databinding.ActivityMainBinding;
import com.example.mobilepaindiary.ui.room.User;
import com.example.mobilepaindiary.ui.room.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    private UserViewModel customerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.appBar.toolbar);


        User user1 = new User("1000",20.1f,20.2f,999.0f,"123@163.com",
                "neck","average",1,"2021-05-03");
        User user2 = new User("1000",20.1f,20.2f,999.0f,"123@163.com",
                "neck","average",2,"2021-05-04");
        User user3 = new User("1000",20.1f,20.2f,999.0f,"123@163.com",
                "neck","average",3,"2021-05-05");
        User user4 = new User("1000",20.1f,20.2f,999.0f,"123@163.com",
                "back","average",4,"2021-05-06");
        User user5 = new User("1000",20.1f,20.2f,999.0f,"123@163.com",
                "back","average",5,"2021-05-07");
        User user6 = new User("1000",20.1f,20.2f,999.0f,"123@163.com",
                "arm","average",6,"2021-05-08");
        User user7 = new User("1000",20.1f,20.2f,999.0f,"123@163.com",
                "arm","average",7,"2021-05-09");
        User user8 = new User("1000",20.1f,20.2f,999.0f,"123@163.com",
                "leg","average",8,"2021-05-10");
        User user9 = new User("1000",20.1f,20.2f,999.0f,"123@163.com",
                "leg","average",9,"2021-05-11");
        User user10 = new User("1000",20.1f,20.2f,999.0f,"123@163.com",
                "leg","average",10,"2021-05-12");
/**
        customerViewModel   = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserViewModel.class);
        customerViewModel.deleteAll();
        customerViewModel.insert(user1);
        customerViewModel.insert(user2);
        customerViewModel.insert(user3);
        customerViewModel.insert(user4);
        customerViewModel.insert(user5);
        customerViewModel.insert(user6);
        customerViewModel.insert(user7);
        customerViewModel.insert(user8);
        customerViewModel.insert(user9);
        customerViewModel.insert(user10);
*/


        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment)
                fragmentManager.findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_fragment, R.id.nav_add_fragment, R.id.nav_view_fragment)
                .setDrawerLayout(binding.drawerLayout).build();



        //Sets up a NavigationView for use with a NavController.
        NavigationUI.setupWithNavController(binding.navView, navController);

        NavigationUI.setupWithNavController(binding.appBar.toolbar, navController,
                mAppBarConfiguration);
    }

/**
    private void replaceFragment(Fragment nextFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,
                nextFragment);
        fragmentTransaction.commit();
    }
 */




}