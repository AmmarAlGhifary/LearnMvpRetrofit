package com.blogspot.yourfavoritekaisar.repositorypattrn.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blogspot.yourfavoritekaisar.repositorypattrn.R;
import com.blogspot.yourfavoritekaisar.repositorypattrn.ui.favorite.FavoriteFragment;
import com.blogspot.yourfavoritekaisar.repositorypattrn.ui.teams.TeamsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            =  new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_teams:
                   // Menampilkan teams fragment
                    TeamsFragment teamsFragment = new TeamsFragment();
                    loadFragment(teamsFragment);
                    return true;
                case R.id.navigation_favorite:
                    // Menampilkan fragment favorite
                    FavoriteFragment favoriteFragment = new FavoriteFragment();
                    loadFragment(favoriteFragment);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().setTitle("Teams");

        // Buat object fragment teams
        TeamsFragment teamsFragment = new TeamsFragment();
        FavoriteFragment favoriteFragment = new FavoriteFragment();
    }

    private void loadFragment(Fragment fragment) {
        // Mennampilkan Fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // MENGGANTI FRAME LAYOUT DENGAN FRAGMENT
        transaction.replace(R.id.fl_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

}
