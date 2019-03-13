package com.dani.vozkajuniors.logica.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dani.vozkajuniors.logica.modelo.Player;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    private List<Player> players;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mText.setText(players.get(position).name);
    }

    @Override
    public int getItemCount() {
        return players == null ? 0 : players.size();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mText;

        ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(
                    android.R.layout.simple_list_item_single_choice, parent, false));
            mText = itemView.findViewById(android.R.id.text1);
        }

    }

}
