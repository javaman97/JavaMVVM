package com.aman.javamvvm.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = User.class , exportSchema = false, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static volatile UserDatabase INSTANCE;

    public static UserDatabase getDatabase(final Context context){

        if(INSTANCE == null){
            synchronized (UserDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        UserDatabase.class,"user_db")
                        .build();
            }
        }
        return INSTANCE;
    }
}
