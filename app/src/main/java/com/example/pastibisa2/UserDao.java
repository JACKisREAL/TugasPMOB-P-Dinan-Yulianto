package com.example.pastibisa2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertAll(User ... users);
    @Query("SELECT * FROM user")
    List<User> getAllPersons();
    @Query("DELETE FROM user")
    void nukeData();
    @Query("SELECT * FROM user WHERE nim LIKE :nim ")
    List<User> findUser(String nim);
    @Query("SELECT * FROM user WHERE nim LIKE :nim AND password LIKE :password")
    List<User> findLogin(String nim, String password);
}
