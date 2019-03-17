package com.dani.vozkajuniors.vista;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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
        playerAdapter.setContext(this);

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

    public void createLineUp(View view) {
        List <Player> aux = new ArrayList<Player>();
        for (Player p : playerAdapter.getPlayers()) {
            if (p.isSelected){
                aux.add(p);
            }
        }
        Intent intent = new Intent(this, LineUpActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) aux);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
