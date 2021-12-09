package com.example.mobilepaindiary.ui.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDAO customerDao();


    private static UserDatabase INSTANCE;
    //we create an ExecutorService with a fixed thread pool so we can later run
// database operations asynchronously on a background thread.
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //A synchronized method in a multi threaded environment means that two threads
    // are not allowed to access data at the same time  
    public static synchronized UserDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, "UserDatabase")
                    // when schema or db version changed, clear data and recreate a new db based new schema
                    .fallbackToDestructiveMigration() // prevent crash an
                    .build();
        }
        return INSTANCE;
    }
}
