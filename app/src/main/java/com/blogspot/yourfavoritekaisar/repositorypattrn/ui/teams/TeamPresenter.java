package com.blogspot.yourfavoritekaisar.repositorypattrn.ui.teams;

import com.blogspot.yourfavoritekaisar.repositorypattrn.data.remote.ApiClient;
import com.blogspot.yourfavoritekaisar.repositorypattrn.data.remote.ApiInterface;
import com.blogspot.yourfavoritekaisar.repositorypattrn.model.TeamResponse;
import com.blogspot.yourfavoritekaisar.repositorypattrn.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamPresenter implements TeamContract.Presenters {

    private final TeamContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public TeamPresenter(TeamContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListTeams() {
        view.showProgress();

        Call<TeamResponse> call = apiInterface.getAllTeams(Constant.S, Constant.C);
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                view.hideProgress();

                if (response.body() != null){
                    view.showDataList(response.body().getTeams());
                }else {
                    view.showFailureMessage("Data ku kurang mass");
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                view.showFailureMessage(t.getMessage());

            }
        });
    }

    @Override
    public void getSearchTeams(String searchText) {
       // Mengecek apakah intputan user ada isinya?
        if (!searchText.isEmpty()){
            // Apabila ada lakukan request ke APi
            view.showProgress();
            Call<TeamResponse> call = apiInterface.getSearchTeams(searchText);
            call.enqueue(new Callback<TeamResponse>() {
                @Override
                public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                    view.hideProgress();
                    if (response.body() != null){
                        view.showDataList(response.body().getTeams());
                    }else {
                        view.showFailureMessage("tidak ada team dengan nama tersebut");
                    }
                }

                @Override
                public void onFailure(Call<TeamResponse> call, Throwable t) {
                    view.hideProgress();
                    view.showFailureMessage(t.getMessage());
                }
            });
        }else {
            // Apabila kosong maka lakukan pengambilan data teams tanpa search
            getDataListTeams();
        }
    }
}
