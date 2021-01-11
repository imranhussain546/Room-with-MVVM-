package com.imran.room_databasemvvm.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")  //table name
public class Users
{
    @PrimaryKey(autoGenerate = true)  //primary key
    private int id;

    @ColumnInfo(name = "username") //column name
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
