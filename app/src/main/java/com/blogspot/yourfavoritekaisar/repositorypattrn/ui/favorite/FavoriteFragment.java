package com.blogspot.yourfavoritekaisar.repositorypattrn.ui.favorite;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blogspot.yourfavoritekaisar.repositorypattrn.R;
import com.blogspot.yourfavoritekaisar.repositorypattrn.model.TeamData;
import com.blogspot.yourfavoritekaisar.repositorypattrn.ui.teams.TeamsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoriteContract.View {

   // @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    //@BindView(R.id.rv_favorite)
    RecyclerView rvFavorite;
    Unbinder unbinder;

    private FavoritePresenter favoritePresenter = new FavoritePresenter(this);

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvFavorite = view.findViewById(R.id.rv_favorite);
        swipeRefresh = getView().findViewById(R.id.swipe_refresh);
        favoritePresenter.getDataListTeam(getContext());
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                favoritePresenter.getDataListTeam(getContext());

            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showDataList(List<TeamData> teamDataList) {
        rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavorite.setAdapter(new TeamsAdapter(getContext(), teamDataList));

    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void hideRefresh() {
        swipeRefresh.setRefreshing(false);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       // unbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Memasukkan menu ke Fragment
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);

        //Membuat object
        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                favoritePresenter.searchTeam(getContext(), s);
                return false;
            }
        });
    }

}
