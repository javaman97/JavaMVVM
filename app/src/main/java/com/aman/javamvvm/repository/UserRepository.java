package com.aman.javamvvm.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.aman.javamvvm.data.User;
import com.aman.javamvvm.data.UserDao;
import com.aman.javamvvm.data.UserDatabase;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application){
        UserDatabase db = UserDatabase.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers(){
     return allUsers;
    }

    public void insertUser(User user){
        new InsertAsyncTask(userDao).execute(user);
    }

    private static class InsertAsyncTask  extends AsyncTask<User,Void,Void>{

        private UserDao userDao;
        InsertAsyncTask(UserDao dao){
            userDao = dao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users[0]);
            return null;
        }
    }
}
