package com.example.proyectofinal.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user ORDER BY highScore DESC")
    List<User> getAll();

    @Query("SELECT * FROM user  WHERE username=:username")
    User getByUsername(String username);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Query("UPDATE user SET highScore=:highScore WHERE id=:id")
    void updateHighScore(int highScore, int id);
}
