package com.imran.room_databasemvvm.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.imran.room_databasemvvm.database.Database;
import com.imran.room_databasemvvm.database.UsersDao;
import com.imran.room_databasemvvm.database.Users;

import java.util.List;

public class UsersRespository
{
    private Database database;
    private UsersDao usersDao;
    private LiveData<List<Users>> userList;

    public UsersRespository(Application application)
    {
        database=Database.getDatabase(application);
        usersDao=database.usersDao();
        userList=usersDao.getAllUsers();
    }

    public LiveData<List<Users>> getAllUsers()
    {
        return database.usersDao().getAllUsers();
    }

    public void insertUser(final Users users)
    {
        new AsyncTask<Void, Void, Void>(

        ) {
            @Override
            protected Void doInBackground(Void... voids) {
                database.usersDao().insertUser(users);
                return null;
            }
        }.execute();
    }

    public void updateUser(final Users users)
    {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.usersDao().updateUser(users);
                return null;
            }
        }.execute();
    }

    public void deleteUser(final Users users)
    {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.usersDao().deleteUser(users);
                return null;
            }
        }.execute();
    }
}
