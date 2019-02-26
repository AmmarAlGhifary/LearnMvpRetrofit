package com.blogspot.yourfavoritekaisar.repositorypattrn.ui.favorite;

import android.content.Context;

import com.blogspot.yourfavoritekaisar.repositorypattrn.model.TeamData;

import java.util.List;

public interface FavoriteContract {

    interface View{
        void showDataList(List<TeamData> teamDataList);
        void showFailureMessage(String msg);
        void hideRefresh();
    }

    interface presenter{
        void getDataListTeam(Context context);
        void searchTeam(Context context, String searchText);
    }

}
