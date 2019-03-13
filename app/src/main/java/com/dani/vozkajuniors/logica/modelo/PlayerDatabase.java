package com.dani.vozkajuniors.logica.modelo;

import android.content.Context;

import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Player.class}, version = 1)
public abstract class PlayerDatabase extends RoomDatabase {

    public abstract PlayerDAO playerDAO();

    private static PlayerDatabase instance;

    public static synchronized PlayerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), PlayerDatabase.class, "database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    @VisibleForTesting
    public static void switchToInMemory(Context context) {
        instance = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                PlayerDatabase.class).build();
    }

}
