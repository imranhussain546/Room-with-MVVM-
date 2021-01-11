package com.imran.room_databasemvvm.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsersDao
{
    @Insert
    void insertUser(Users users);

    @Update
    void updateUser(Users users);

    @Delete
    void deleteUser(Users users);

    @Query("SELECT * from users")
    LiveData<List<Users>> getAllUsers();
}
