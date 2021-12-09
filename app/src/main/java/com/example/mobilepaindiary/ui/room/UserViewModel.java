package com.example.mobilepaindiary.ui.room;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;



import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UserViewModel extends AndroidViewModel {
    private UserRepository cRepository;
    private LiveData<List<User>> allCustomers;
    public UserViewModel(Application application) {
        super(application);
        cRepository = new UserRepository(application);
        allCustomers = cRepository.getAllCustomers();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<User> findByIDFuture(final int customerId){
        return cRepository.findByIDFuture(customerId);
    }
    public LiveData<List<User>> getAllCustomers() {
        return allCustomers;
    }
    public void insert(User user) {
        cRepository.insert(user);
    }

    public void deleteAll() {
        cRepository.deleteAll();
    }
    public void update(User user) {
        cRepository.updateCustomer(user);
    }
    public User getLatestCustomer() {
        return cRepository.getLatestCustomer();
    }
}