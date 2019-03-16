package com.dani.vozkajuniors.vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;

import com.dani.vozkajuniors.R;
import com.dani.vozkajuniors.logica.async.AsyncCreatePlayer;
import com.dani.vozkajuniors.logica.modelo.Player;
import com.dani.vozkajuniors.logica.util.Utilidades;
import com.joooonho.SelectableRoundedImageView;

public class CreatePlayerActivity extends AppCompatActivity {
    private EditText name;
    private SelectableRoundedImageView imageView;

    private Activity activity;

    private final CharSequence[] items = {"Seleccionar de la galería", "Cancelar"};
    public static final int SELECT_FILE = 2;

    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        name = findViewById(R.id.editTextName);
        imageView = findViewById(R.id.imageButtonCamera);

        activity = this;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        // Acciones para añadir la imagen de perfil
        switch (requestCode) {
            // Coger foto de la galería
            case SELECT_FILE:
                if (resultCode == RESULT_OK)
                    cargarGaleria(imageReturnedIntent);
                break;
            case 1:

                if (imageReturnedIntent != null) {
                    // get the returned data
                    Bundle extras = imageReturnedIntent.getExtras();
                    // get the cropped bitmap
                    image = extras.getParcelable("data");

                    imageView.setImageBitmap(image);
                }
                break;
            default:
                break;
        }
    }

    public void cargarGaleria(Intent imageReturnedIntent) {
        Uri uri = imageReturnedIntent.getData();
        cropImage(uri);
    }


    private void cropImage(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, 1);

        image = ((Bitmap) intent.getExtras().get("data"));
        imageView.setImageBitmap(image);
    }

    public void cargarImagen(View view) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Editar foto de perfil");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Seleccionar de la galería")) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        solicitarPermiso(Manifest.permission.READ_EXTERNAL_STORAGE,
                                "Sin el permiso de archivos no podrás seleccionar una imagen de la galería.", 123, activity);
                        return;
                    }
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, SELECT_FILE);
                } else if (items[item].equals("Cancelar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void solicitarPermiso(final String permiso, String justificacion,
                                 final int requestCode, final Activity actividad) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(actividad,
                permiso)) {
            new AlertDialog.Builder(actividad)
                    .setTitle("Solicitud de permiso")
                    .setMessage(justificacion)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            ActivityCompat.requestPermissions(actividad,
                                    new String[]{permiso}, 123);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(actividad,
                    new String[]{permiso}, requestCode);
        }
    }

    public void createPlayer(View view) {
        if (!name.getText().toString().isEmpty()) {
            Player p = Player.byName(name.getText().toString(), Utilidades.getBytes(image));
            new AsyncCreatePlayer(this).execute(p);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
