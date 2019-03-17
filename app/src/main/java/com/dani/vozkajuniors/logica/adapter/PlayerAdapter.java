package com.dani.vozkajuniors.logica.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dani.vozkajuniors.R;
import com.dani.vozkajuniors.logica.async.AsyncDeletePlayer;
import com.dani.vozkajuniors.logica.modelo.Player;
import com.dani.vozkajuniors.logica.util.Utilidades;
import com.joooonho.SelectableRoundedImageView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    private List<Player> players;
    private Context context;

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
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                player.setSelected(!player.isSelected);
                holder.itemView.setBackgroundColor(player.isSelected ? R.color.colorPrimary :
                        Color.WHITE);
            }
        });

        final PlayerAdapter adapter = this;

        holder.mText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AsyncDeletePlayer(context, ((TextView) v).getText().toString(), adapter).execute();
                return true;
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

    public void setContext(Context context) {
        this.context = context;
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
