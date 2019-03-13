package com.dani.vozkajuniors.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dani.vozkajuniors.R;
import com.dani.vozkajuniors.logica.async.AsyncCreatePlayer;
import com.dani.vozkajuniors.logica.modelo.Player;

public class CreatePlayerActivity extends AppCompatActivity {
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        name = findViewById(R.id.editTextName);
    }

    public void createPlayer (View view){
        if (!name.getText().toString().isEmpty()) {
            Player p = Player.byName(name.getText().toString());
            new AsyncCreatePlayer(this).execute(p);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
