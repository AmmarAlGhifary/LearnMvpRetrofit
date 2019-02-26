package com.blogspot.yourfavoritekaisar.repositorypattrn.ui.teams;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.yourfavoritekaisar.repositorypattrn.R;
import com.blogspot.yourfavoritekaisar.repositorypattrn.model.TeamData;
import com.blogspot.yourfavoritekaisar.repositorypattrn.ui.detail.DetailTeamActivity;
import com.blogspot.yourfavoritekaisar.repositorypattrn.utils.Constant;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {

    private final Context context;
    private final List<TeamData> teamDataList;


    public TeamsAdapter(Context context, List<TeamData> teamDataList) {
        this.context = context;
        this.teamDataList = teamDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_teams, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final TeamData teamData = teamDataList.get(i);

        RequestOptions options = new RequestOptions().error(R.drawable.ic_error_black_24dp).placeholder(R.drawable.ic_error_outline_black_24dp);
        Glide.with(context).load(teamData.getStrTeamBadge()).apply(options).into(viewHolder.imgLogoTeam);
        viewHolder.txtNameTeam.setText(teamData.getStrTeam());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,DetailTeamActivity.class).putExtra(Constant.KEY_DATA, teamData));
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_logo_team)
        ImageView imgLogoTeam;
        @BindView(R.id.txt_name_team)
        TextView txtNameTeam;
        @BindView(R.id.card_view)
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
