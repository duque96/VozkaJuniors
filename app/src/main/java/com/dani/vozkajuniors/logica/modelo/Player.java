package com.dani.vozkajuniors.logica.modelo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Player.TABLE_NAME)
public class Player {
    
    // Nombre de la tabla
    public static final String TABLE_NAME = "Players";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "name")
    public String name;

    public static Player byName(String name) {
        final Player player = new Player();

        if (name != null || !name.isEmpty())
            player.name = name;

        return player;
    }
}
