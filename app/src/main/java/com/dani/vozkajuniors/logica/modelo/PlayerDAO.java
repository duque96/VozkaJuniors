package com.dani.vozkajuniors.logica.modelo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PlayerDAO {
    @Query("SELECT * FROM Players")
    List<Player> getAll();

//   @Query("SELECT * FROM player WHERE uid IN (:userIds)")
//    List<Player> loadAllByIds(int[] userIds);
//
    @Query("SELECT * FROM Players WHERE name = :name LIMIT 1")
    Player findByName(String name);

    @Insert
    void insertAll(Player... users);

    @Insert
    void insertOne(Player player);

    @Delete
    void delete(Player user);
}
