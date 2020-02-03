package com.nvjweb.litletourettev1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

//import android.support.v7.app.AppCompatActivity;
//import android.content.Intent;
//import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private static final int splash=3000;
    ImageView imgstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        imgstart=findViewById(R.id.start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (leerusuario(getApplicationContext(),"nombre")!=""){
                    Intent intent = new Intent(MainActivity.this,cuartapantalla.class);
                    MainActivity.this.startActivity(intent);
                }else{
                    Intent itemintent = new Intent(MainActivity.this, primerapantalla.class);
                    MainActivity.this.startActivity(itemintent);
                }
            }
        },splash);


    }@Override
    public void onBackPressed (){
        return;
    }
    public static String leerusuario(Context context, String keyPref) {
        SharedPreferences preferences = context.getSharedPreferences("PREFS", MODE_PRIVATE);
        return  preferences.getString(keyPref, "");
    }
}












