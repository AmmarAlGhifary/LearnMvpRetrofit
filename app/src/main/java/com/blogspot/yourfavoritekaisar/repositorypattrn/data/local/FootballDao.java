package com.blogspot.yourfavoritekaisar.repositorypattrn.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.blogspot.yourfavoritekaisar.repositorypattrn.model.TeamData;

import java.util.List;

@Dao
public interface FootballDao {

    @Insert
    void insertItem(TeamData teamData);

    @Query("SELECT * FROM teams WHERE idTeam = :id")
    TeamData selectedItem(String id);

    @Delete
    void delete(TeamData teamData);

    @Query("SELECT * FROM teams ORDER BY strTeam ASC")
    List<TeamData> selectFavorite();

    @Query("SELECT * FROM teams WHERE strTeam = :teams")
    TeamData selectedItemSearch(String teams);
}
