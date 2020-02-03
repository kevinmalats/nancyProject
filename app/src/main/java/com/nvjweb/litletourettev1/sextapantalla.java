package com.nvjweb.litletourettev1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class sextapantalla extends AppCompatActivity {
    ImageView fruta1, fruta2, fruta3,fruta4,fruta5,fruta6, frutaadivinar1, frutaadivinar2, regresar;
    ArrayList<String> frutas;
    String frutaelegida;
    int numfrutaelegida2;
    Integer errores=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sextapantalla);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        regresar=findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sextapantalla.this,cuartapantalla.class);
                sextapantalla.this.startActivity(intent);
            }
        });
        errores= Integer.valueOf(getIntent().getStringExtra("errores"));
        frutas= new ArrayList<String>();
        frutaadivinar1=findViewById(R.id.frutaadivinar1);
        frutaadivinar2=findViewById(R.id.frutaadivinar2);

        fruta1=findViewById(R.id.fruta1);
        fruta2=findViewById(R.id.fruta2);
        fruta3=findViewById(R.id.fruta3);
        fruta4=findViewById(R.id.fruta4);
        fruta5=findViewById(R.id.fruta5);
        fruta6=findViewById(R.id.fruta6);

        Random rand = new Random();

        frutaelegida = getIntent().getStringExtra("frutaelegida");
        ponerimagen(frutaadivinar1,frutaelegida);

        numfrutaelegida2 = rand.nextInt(8)+1;
        ponerimagen(frutaadivinar2,"fruta"+numfrutaelegida2);

        final int puestoelegida = rand.nextInt(3)+1;
        if (puestoelegida==1){
            ponerimagen(fruta1,frutaelegida);
            ponerimagen(fruta2,"fruta"+numfrutaelegida2);
        }else{
            int numfruta1 = rand.nextInt(8)+1;
            ponerimagen(fruta1,"fruta"+numfruta1);
            int numfruta2 = rand.nextInt(8)+1;
            while (numfruta2==numfrutaelegida2){
                numfruta2 = rand.nextInt(8)+1;
            }
            ponerimagen(fruta2,"fruta"+numfruta2);
        }

        if (puestoelegida==2){
            ponerimagen(fruta3,frutaelegida);
            ponerimagen(fruta4,"fruta"+numfrutaelegida2);
        }else{
            int numfruta3 = rand.nextInt(8)+1;
            ponerimagen(fruta3,"fruta"+numfruta3);
            int numfruta4 = rand.nextInt(8)+1;
            while (numfruta4==numfrutaelegida2){
                numfruta4 = rand.nextInt(8)+1;
            }
            ponerimagen(fruta4,"fruta"+numfruta4);
        }

        if (puestoelegida==3){
            ponerimagen(fruta5,frutaelegida);
            ponerimagen(fruta6,"fruta"+numfrutaelegida2);
        }else{
            int numfruta5 = rand.nextInt(8)+1;
            ponerimagen(fruta5,"fruta"+numfruta5);
            int numfruta6 = rand.nextInt(8)+1;
            while (numfruta6==numfrutaelegida2){
                numfruta6 = rand.nextInt(8)+1;
            }
            ponerimagen(fruta6,"fruta"+numfruta6);
        }

        fruta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puestoelegida==1){
                    Vibrator vi = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vi.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vi.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(sextapantalla.this, septimapantalla.class);
                    intent.putExtra("frutaelegida1", frutaelegida);
                    intent.putExtra("frutaelegida2", "fruta"+numfrutaelegida2);
                    intent.putExtra("errores", ""+errores);
                    sextapantalla.this.startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"oh no, intenta otra vez!!",Toast.LENGTH_LONG).show();
                    errores++;
                }
            }
        });

        fruta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puestoelegida==1){
                    Vibrator vi = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vi.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vi.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(sextapantalla.this, septimapantalla.class);
                    intent.putExtra("frutaelegida1", frutaelegida);
                    intent.putExtra("frutaelegida2", "fruta"+numfrutaelegida2);
                    intent.putExtra("errores", ""+errores);
                    sextapantalla.this.startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"oh no, intenta otra vez!!",Toast.LENGTH_LONG).show();
                    errores++;
                }
            }
        });

        fruta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puestoelegida==2){
                    Vibrator vi = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vi.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vi.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(sextapantalla.this, septimapantalla.class);
                    intent.putExtra("frutaelegida1", frutaelegida);
                    intent.putExtra("frutaelegida2", "fruta"+numfrutaelegida2);
                    intent.putExtra("errores", ""+errores);
                    sextapantalla.this.startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"oh no, intenta otra vez!!",Toast.LENGTH_LONG).show();
                    errores++;
                }
            }
        });

        fruta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puestoelegida==2){
                    Vibrator vi = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vi.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vi.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(sextapantalla.this, septimapantalla.class);
                    intent.putExtra("frutaelegida1", frutaelegida);
                    intent.putExtra("frutaelegida2", "fruta"+numfrutaelegida2);
                    intent.putExtra("errores", ""+errores);
                    sextapantalla.this.startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"oh no, intenta otra vez!!",Toast.LENGTH_LONG).show();
                    errores++;
                }
            }
        });

        fruta5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puestoelegida==3){
                    Vibrator vi = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vi.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vi.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(sextapantalla.this, septimapantalla.class);
                    intent.putExtra("frutaelegida1", frutaelegida);
                    intent.putExtra("frutaelegida2", "fruta"+numfrutaelegida2);
                    intent.putExtra("errores", ""+errores);
                    sextapantalla.this.startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"oh no, intenta otra vez!!",Toast.LENGTH_LONG).show();
                    errores++;
                }
            }
        });

        fruta6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puestoelegida==3){
                    Vibrator vi = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vi.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vi.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(sextapantalla.this, septimapantalla.class);
                    intent.putExtra("frutaelegida1", frutaelegida);
                    intent.putExtra("frutaelegida2", "fruta"+numfrutaelegida2);
                    intent.putExtra("errores", ""+errores);
                    sextapantalla.this.startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"oh no, intenta otra vez!!",Toast.LENGTH_LONG).show();
                    errores++;
                }
            }
        });

    }

    private void ponerimagen(ImageView imagen, String fruta) {
        switch (fruta){
            case "fruta1":
                imagen.setImageResource(R.drawable.fruta1);
                break;
            case "fruta2":
                imagen.setImageResource(R.drawable.fruta2);
                break;
            case "fruta3":
                imagen.setImageResource(R.drawable.fruta3);
                break;
            case "fruta4":
                imagen.setImageResource(R.drawable.fruta4);
                break;
            case "fruta5":
                imagen.setImageResource(R.drawable.fruta5);
                break;
            case "fruta6":
                imagen.setImageResource(R.drawable.fruta6);
                break;
            case "fruta7":
                imagen.setImageResource(R.drawable.fruta7);
                break;
            case "fruta8":
                imagen.setImageResource(R.drawable.fruta8);
                break;
        }
    }

    @Override
    public void onBackPressed (){
        return;
    }
}
