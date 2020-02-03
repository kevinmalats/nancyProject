package com.nvjweb.litletourettev1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class cuartapantalla extends AppCompatActivity {
    private static final String PREFS_KEY = "PREFS";
    FirebaseFirestore db;
    String avatar;
    TextView txtnombre;
    ImageView imgavatar,btnempezarr, cerrarsesion, puntaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuartapantalla);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        db = FirebaseFirestore.getInstance();
        txtnombre=findViewById(R.id.txtnombre);
        imgavatar=findViewById(R.id.imgavatar);
        avatar=leerusuario(this,"avatar");
        txtnombre.setText("Hola "+leerusuario(this, "nombre"));
        switch (avatar){
            case "avatar1":
                imgavatar.setImageResource(R.drawable.avatar1);
                break;
            case "avatar11":
                imgavatar.setImageResource(R.drawable.avatar11);
                break;
            case "avatar10":
                imgavatar.setImageResource(R.drawable.avatar10);
                break;
            case "avatar2":
                imgavatar.setImageResource(R.drawable.avatar2);
                break;
            case "avatar7":
                imgavatar.setImageResource(R.drawable.avatar7);
                break;
            case "avatar9":
                imgavatar.setImageResource(R.drawable.avatar9);
                break;
            case "avatar3":
                imgavatar.setImageResource(R.drawable.avatar3);
                break;
            case "avatar5":
                imgavatar.setImageResource(R.drawable.avatar5);
                break;
            case "avatar6":
                imgavatar.setImageResource(R.drawable.avatar6);
                break;

        }

        btnempezarr=findViewById(R.id.btnempezarr);


        btnempezarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cuartapantalla.this,quintapantalla.class);
                cuartapantalla.this.startActivity(intent);


            }
        });
        cerrarsesion=findViewById(R.id.cerrarsesion);
        cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.clear();
                editor.commit();
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        puntaje=findViewById(R.id.puntaje);
        puntaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), novenapantalla.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void onBackPressed (){
        return;
    }

    public boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }

    public static String leerusuario(Context context, String keyPref) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        return  preferences.getString(keyPref, "");
    }
}


