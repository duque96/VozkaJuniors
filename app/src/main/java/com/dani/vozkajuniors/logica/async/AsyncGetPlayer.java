package com.dani.vozkajuniors.logica.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.dani.vozkajuniors.logica.adapter.PlayerAdapter;
import com.dani.vozkajuniors.logica.modelo.Player;
import com.dani.vozkajuniors.logica.modelo.PlayerDatabase;

import java.util.List;

public class AsyncGetPlayer extends AsyncTask<String, PlayerAdapter, Boolean> {

    private PlayerAdapter adapter;
    private ProgressDialog progressDialog;
    private Context context;

    public AsyncGetPlayer(PlayerAdapter adapter, Context context) {
        this.adapter = adapter;
        this.context = context;

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Creando jugador..");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        //progressDialog.show();
        List<Player> list = PlayerDatabase.getInstance(context).playerDAO().getAll();
        adapter.setPlayers(list);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean) {
           // progressDialog.dismiss();
        }
    }
}
