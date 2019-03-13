package com.dani.vozkajuniors.vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.dani.vozkajuniors.R;
import com.dani.vozkajuniors.logica.adapter.PlayerAdapter;
import com.dani.vozkajuniors.logica.async.AsyncGetPlayer;
import com.dani.vozkajuniors.logica.modelo.Player;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private PlayerAdapter playerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.list_players);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        playerAdapter = new PlayerAdapter();
        recyclerView.setAdapter(playerAdapter);

        new AsyncGetPlayer(playerAdapter, this).execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_player_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_player_menu) {
            Intent intent = new Intent(this, CreatePlayerActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}
