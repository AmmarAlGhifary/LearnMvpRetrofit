package com.blogspot.yourfavoritekaisar.repositorypattrn.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamResponse {

    @SerializedName("teams")
    private List<TeamData> teams;

    public void setTeams(List<TeamData> teams) {
        this.teams = teams;
    }

    public List<TeamData> getTeams() {
        return teams;
    }
}
