package com.blogspot.yourfavoritekaisar.repositorypattrn.ui.teams;

import com.blogspot.yourfavoritekaisar.repositorypattrn.model.TeamData;

import java.util.List;

public interface TeamContract {

    interface View{
        void showProgress();
        void hideProgress();
        void showDataList(List<TeamData> teamDataList);
        void showFailureMessage(String msg);

    }

    interface Presenters{
        void getDataListTeams();



    }


}
