package com.example.mobilepaindiary.ui.room;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class UserRepository {
    private UserDAO userDao;
    private LiveData<List<User>> allCustomers;

    public UserRepository(Application application) {
        UserDatabase db = UserDatabase.getInstance(application);
        userDao = db.customerDao();
        allCustomers = userDao.getAll();
    }


    // Room executes this query on a separate thread
    public LiveData<List<User>> getAllCustomers() {
        return allCustomers;
    }

    public void insert(final User customer) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insert(customer);
            }
        });
    }

    public void deleteAll() {
       UserDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.deleteAll();
            }
        });
    }



    public void delete(final User customer) {
       UserDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.delete(customer);
            }
        });
    }

    public void updateCustomer(final User customer) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.updateCustomer(customer);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<User> findByIDFuture(final int customerId) {

        return CompletableFuture.supplyAsync(new Supplier<User>() {
            @Override
            public User get() {
                return userDao.findByID(customerId);
            }
        }, UserDatabase.databaseWriteExecutor);
    }

    public User getLatestCustomer(){

        return userDao.getLatestUser();
    }


}
