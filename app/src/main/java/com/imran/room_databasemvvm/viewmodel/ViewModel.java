package com.imran.room_databasemvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.imran.room_databasemvvm.model.UsersRespository;
import com.imran.room_databasemvvm.database.Users;

import java.util.List;

public class ViewModel extends AndroidViewModel
{
    private UsersRespository usersRespository;
    private LiveData<List<Users>> userList;

    public ViewModel(@NonNull Application application)
    {
        super(application);
        usersRespository=new UsersRespository(application);
        userList=usersRespository.getAllUsers();
    }

    public LiveData<List<Users>> getAllUsers()
    {
        return usersRespository.getAllUsers();
    }

    public void insertUser(Users users)
    {
        usersRespository.insertUser(users);
    }

    public void updateUser(Users users)
    {
        usersRespository.updateUser(users);
    }

    public void deleteUser(Users users)
    {
        usersRespository.deleteUser(users);
    }
}
