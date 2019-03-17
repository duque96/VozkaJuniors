package com.dani.vozkajuniors.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.dani.vozkajuniors.R;
import com.dani.vozkajuniors.logica.modelo.Player;
import com.dani.vozkajuniors.logica.util.Utilidades;
import com.joooonho.SelectableRoundedImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class LineUpActivity extends AppCompatActivity {
    private List<Player> players;
    private List<Player> equipo1;
    private List<Player> equipo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_up);

        players = getIntent().getExtras().getParcelableArrayList("list");

        Objects.requireNonNull(getSupportActionBar()).hide();

        crearEquipos();

        mostrarEquipos();
    }

    private void crearEquipos() {
        equipo1 = new ArrayList<>();
        equipo2 = new ArrayList<>();

        int numPlayers = players.size();
        for (int i = 0; i < numPlayers; i++) {
            Random random = new Random();
            int index;
            if (players.size() > 1)
                index = random.nextInt(players.size() - 1);
            else
                index = 0;

            Player p = players.remove(index);

            if (equipo1.size() >= numPlayers / 2) {
                equipo2.add(p);
            } else {
                equipo1.add(p);
            }
        }
    }

    private void mostrarEquipos() {
        int inicio1 = 1;
        int inicio2 = 6;

        for (int i = 0; i < 5; i++) {
            int textId = getResources().getIdentifier("textView" + String.valueOf(i + inicio1),
                    "id",
                    getPackageName());
            int imageId = getResources().getIdentifier("image" + String.valueOf(i + inicio1),
                    "id",
                    getPackageName());
            TextView t = findViewById(textId);
            SelectableRoundedImageView imageView = findViewById(imageId);

            if (i < equipo1.size()) {
                t.setText(equipo1.get(i).name);
                imageView.setImageBitmap(Utilidades.getImage(equipo1.get(i).image));
            } else {
                t.setText(null);
                imageView.setImageBitmap(null);
            }
        }

        for (int i = 0; i < 5; i++) {
            int textId = getResources().getIdentifier("textView" + String.valueOf(i + inicio2), "id", getPackageName());
            int imageId = getResources().getIdentifier("image" + String.valueOf(i + inicio2),
                    "id",
                    getPackageName());
            TextView t = findViewById(textId);
            SelectableRoundedImageView imageView = findViewById(imageId);

            if (i < equipo2.size()) {
                t.setText(equipo2.get(i).name);
                imageView.setImageBitmap(Utilidades.getImage(equipo2.get(i).image));
            } else {
                t.setText(null);
                imageView.setImageBitmap(null);
            }
        }
    }
}
