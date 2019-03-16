package com.dani.vozkajuniors.logica.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Player.TABLE_NAME)
public class Player implements Parcelable {

    // Nombre de la tabla
    public static final String TABLE_NAME = "Players";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public byte[] image;

    public boolean isSelected = false;

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Player(){};

    public Player (Parcel in){
        name = in.readString();
        image = new byte[in.readInt()];
        in.readByteArray(image);
    }

    public static Player byName(String name, byte[] image) {
        final Player player = new Player();

        player.name = name;
        player.image = image;

        return player;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(image.length);
        dest.writeByteArray(image);
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>()
    {
        public Player createFromParcel(Parcel in)
        {
            return new Player(in);
        }
        public Player[] newArray(int size)
        {
            return new Player[size];
        }
    };
}
