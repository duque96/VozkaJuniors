package com.dani.vozkajuniors.logica.async;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import com.dani.vozkajuniors.logica.adapter.PlayerAdapter;
import com.dani.vozkajuniors.logica.modelo.Player;
import com.dani.vozkajuniors.logica.modelo.PlayerDatabase;

public class AsyncDeletePlayer extends AsyncTask<Player, Player, Boolean> {
    private Context context;
    private String name;
    private PlayerAdapter adapter;

    public AsyncDeletePlayer(Context context, String name, PlayerAdapter adapter) {
        this.context = context;
        this.name = name;
        this.adapter = adapter;
    }

    @Override
    protected Boolean doInBackground(Player... players) {
        Player p = PlayerDatabase.getInstance(context).playerDAO().findByName(name);
        PlayerDatabase.getInstance(context).playerDAO().delete(p);
        adapter.setPlayers(PlayerDatabase.getInstance(context).playerDAO().getAll());
        Handler handler = new Handler(context.getMainLooper());

        Runnable r = new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        };

        handler.post(r);
        return true;
    }
}
