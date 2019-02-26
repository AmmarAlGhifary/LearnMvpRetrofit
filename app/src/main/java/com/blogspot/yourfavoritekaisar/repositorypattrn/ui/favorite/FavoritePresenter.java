package com.blogspot.yourfavoritekaisar.repositorypattrn.ui.favorite;

import android.content.Context;

import com.blogspot.yourfavoritekaisar.repositorypattrn.data.local.FootballDatabase;
import com.blogspot.yourfavoritekaisar.repositorypattrn.model.TeamData;

import java.util.List;

public class FavoritePresenter implements FavoriteContract.presenter{

    private final FavoriteContract.View view;
    private FootballDatabase footballDatabase;

    public FavoritePresenter(FavoriteContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListTeam(Context context) {
        footballDatabase = FootballDatabase.getFootballDatabase(context);
        if (footballDatabase.footballDao().selectFavorite() != null){
            view.showDataList(footballDatabase.footballDao().selectFavorite());
        }else {
            view.showFailureMessage("data di favorite tidak ada");
        }
        view.hideRefresh();
    }
}
