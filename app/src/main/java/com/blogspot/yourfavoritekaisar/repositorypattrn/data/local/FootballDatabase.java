package com.blogspot.yourfavoritekaisar.repositorypattrn.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.blogspot.yourfavoritekaisar.repositorypattrn.model.TeamData;


@Database(entities = TeamData.class, version =  1)
public abstract  class FootballDatabase extends RoomDatabase{
    public abstract FootballDao footballDao();

    private static FootballDatabase footballDatabase;

    public static FootballDatabase getFootballDatabase(Context context){
        synchronized (FootballDatabase.class){
            if (footballDatabase == null){
                // Membuat Table
                footballDatabase = Room.databaseBuilder(context,
                        FootballDatabase.class, "db_football").allowMainThreadQueries().build();
            }
        }return footballDatabase;
    }
}
