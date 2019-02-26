package com.blogspot.yourfavoritekaisar.repositorypattrn.ui.detail;

import android.content.Context;
import android.os.Bundle;

import com.blogspot.yourfavoritekaisar.repositorypattrn.model.TeamData;

public interface DetailTeamContract {

    interface View{
        void showDetailTeam(TeamData teamData);
        void showFailureMessage(String msg);
        void showSucsessMessage(String msg);
    }

    interface Presenter {
        void getDetailTeam(Bundle bundle );
        void addToFavorite(Context context, TeamData mTeamsData);
        void removeFavorite(Context context, TeamData mTeamData);
        Boolean checkFavorite(Context context, TeamData mTeamData);

    }
}
