package com.blogspot.yourfavoritekaisar.repositorypattrn.ui.favorite;

import android.content.Context;

import com.blogspot.yourfavoritekaisar.repositorypattrn.data.local.FootballDatabase;
import com.blogspot.yourfavoritekaisar.repositorypattrn.model.TeamData;

import java.util.ArrayList;
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

    @Override
    public void searchTeam(Context context, String searchText) {
        if (!searchText.isEmpty()){
           // Memasukkan semua data favorit ke dalam variable list
            footballDatabase = FootballDatabase.getFootballDatabase(context);
            List<TeamData> teamDataList = footballDatabase.footballDao().selectFavorite();
            // Membuat penampung untuk penampung data yang sudah kita seleksi berdasarkan inputan user
            List<TeamData> mTeamDataList = new ArrayList<>();

            if (teamDataList != null){
                // Melakukan perulangan
                for (TeamData data: teamDataList){
                    // Pengecekkan team berdasarkan dengan permintaan user
                    String namaTeam = data.getStrTeam().toLowerCase();
                    if (namaTeam.contains(searchText.toLowerCase())){
                        // Memasukkan team yang sama dengan inputan user ke dalam wadah ke dua
                        mTeamDataList.add(data);
                    }
                }
                // Mengirimkan wadah ke dua ke view
                view.showDataList(mTeamDataList);
            }
        }else {
            // Apabila inputan user kosong ambil data dari keyword
            getDataListTeam(context);
        }
    }
}
