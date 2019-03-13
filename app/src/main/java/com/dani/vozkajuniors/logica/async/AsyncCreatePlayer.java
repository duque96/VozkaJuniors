package com.dani.vozkajuniors.logica.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.dani.vozkajuniors.logica.modelo.Player;
import com.dani.vozkajuniors.logica.modelo.PlayerDatabase;

public class AsyncCreatePlayer extends AsyncTask<Player, Player, Boolean> {
    private Context context;
    private ProgressDialog progressDialog;

    public AsyncCreatePlayer(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Creando jugador..");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
    }

    @Override
    protected Boolean doInBackground(Player... players) {
       // progressDialog.show();
        PlayerDatabase.getInstance(context).playerDAO().insertOne(players[0]);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean){
            //progressDialog.dismiss();
        }
    }
}
