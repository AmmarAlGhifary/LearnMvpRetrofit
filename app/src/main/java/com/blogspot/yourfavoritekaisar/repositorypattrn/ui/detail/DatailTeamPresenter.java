package com.blogspot.yourfavoritekaisar.repositorypattrn.ui.detail;

import android.content.Context;
import android.os.Bundle;

import com.blogspot.yourfavoritekaisar.repositorypattrn.data.local.FootballDatabase;
import com.blogspot.yourfavoritekaisar.repositorypattrn.model.TeamData;
import com.blogspot.yourfavoritekaisar.repositorypattrn.utils.Constant;

public class DatailTeamPresenter implements DetailTeamContract.Presenter {

    private final DetailTeamContract.View view;
    private FootballDatabase footballDatabase;

    public DatailTeamPresenter(DetailTeamContract.View view) {
        this.view = view;
    }

    @Override
    public void getDetailTeam(Bundle bundle) {
        if (bundle != null) {
            TeamData teamData = (TeamData) bundle.getSerializable(Constant.KEY_DATA);
            view.showDetailTeam(teamData);
        }else {
            view.showFailureMessage("DATAMU KOSONG");
        }

    }

    @Override
    public void addToFavorite(Context context, TeamData mTeamsData) {
        footballDatabase = FootballDatabase.getFootballDatabase(context);
        footballDatabase.footballDao().insertItem(mTeamsData);
        view.showSucsessMessage("TERSIMPAN");

    }

    @Override
    public void removeFavorite(Context context, TeamData mTeamData) {
        footballDatabase = FootballDatabase.getFootballDatabase(context);
        footballDatabase.footballDao().delete(mTeamData);
        view.showFailureMessage("TERHAPUS");

    }

    @Override
    public Boolean checkFavorite(Context context, TeamData mTeamData) {
        Boolean bFavorite = false;
        footballDatabase = FootballDatabase.getFootballDatabase(context);
        return bFavorite = footballDatabase.footballDao().selectedItem(mTeamData.getIdTeam()) != null;
    }
}
