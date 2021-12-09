package com.example.mobilepaindiary.ui.room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao// Data access object
public interface UserDAO {
    @Query("SELECT * FROM user ORDER BY uid ASC")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE uid = :customerId LIMIT 1")
    User findByID(int customerId);

    @Insert
    void insert(User customer);

    @Delete
    void delete(User customer);

    @Update
    void updateCustomer(User customer);

    @Query("DELETE FROM user")
    void deleteAll();


    @Query("SELECT * FROM user ORDER BY uid DESC LIMIT 1")
    User getLatestUser();


}