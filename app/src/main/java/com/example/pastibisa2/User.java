package com.example.pastibisa2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nim")
    public String nim;

    @ColumnInfo(name = "password")
    public String password;

    public User(String nim, String password){
        this.password = password;
        this.nim = nim;
    }

}
