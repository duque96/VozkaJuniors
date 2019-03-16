package com.dani.vozkajuniors.logica.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dani.vozkajuniors.R;
import com.dani.vozkajuniors.logica.modelo.Player;
import com.dani.vozkajuniors.logica.util.Utilidades;
import com.joooonho.SelectableRoundedImageView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    private List<Player> players;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Player player = players.get(position);
        holder.mText.setText(player.name);
        holder.image.setImageBitmap(Utilidades.getImage(player.image));
        holder.itemView.setBackgroundColor(player.isSelected ? Color.CYAN : Color.WHITE);
        holder.mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setSelected(!player.isSelected);
                holder.itemView.setBackgroundColor(player.isSelected ? Color.GREEN : Color.WHITE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return players == null ? 0 : players.size();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mText;
        SelectableRoundedImageView image;

        ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.player_item, parent, false));
            mText = itemView.findViewById(R.id.player_name);
            image = itemView.findViewById(R.id.player_image);
        }

    }

}
