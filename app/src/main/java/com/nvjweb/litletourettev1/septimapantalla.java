package com.nvjweb.litletourettev1;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class septimapantalla extends AppCompatActivity {
    ImageView fruta1, fruta2, fruta3,fruta4,fruta5,fruta6,fruta7,fruta8,fruta9, frutaadivinar1, frutaadivinar2,frutaadivinar3, regresar;
    ArrayList<String> frutas;
    String frutaelegida1, frutaelegida2;
    Integer errores=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.septimapantalla);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        regresar=findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(septimapantalla.this,octavapantalla.class);
                septimapantalla.this.startActivity(intent);
            }
        });
        errores= Integer.valueOf(getIntent().getStringExtra("errores"));
        frutas= new ArrayList<String>();
        frutaadivinar1=findViewById(R.id.frutaadivinar1);
        frutaadivinar2=findViewById(R.id.frutaadivinar2);
        frutaadivinar3=findViewById(R.id.frutaadivinar3);

        fruta1=findViewById(R.id.fruta1);
        fruta2=findViewById(R.id.fruta2);
        fruta3=findViewById(R.id.fruta3);
        fruta4=findViewById(R.id.fruta4);
        fruta5=findViewById(R.id.fruta5);
        fruta6=findViewById(R.id.fruta6);
        fruta7=findViewById(R.id.fruta7);
        fruta8=findViewById(R.id.fruta8);
        fruta9=findViewById(R.id.fruta9);

        Random rand = new Random();

        frutaelegida1 = getIntent().getStringExtra("frutaelegida1");
        ponerimagen(frutaadivinar1,frutaelegida1);
        frutaelegida2 = getIntent().getStringExtra("frutaelegida2");
        ponerimagen(frutaadivinar2,frutaelegida2);

        int numfrutaelegida3 = rand.nextInt(8)+1;
        ponerimagen(frutaadivinar3,"fruta"+numfrutaelegida3);

        final int puestoelegida = rand.nextInt(3)+1;
        if (puestoelegida==1){
            ponerimagen(fruta1,frutaelegida1);
            ponerimagen(fruta2,frutaelegida2);
            ponerimagen(fruta3,"fruta"+numfrutaelegida3);
        }else{
            int numfruta1 = rand.nextInt(8)+1;
            ponerimagen(fruta1,"fruta"+numfruta1);
            int numfruta2 = rand.nextInt(8)+1;
            ponerimagen(fruta2,"fruta"+numfruta2);
            int numfruta3 = rand.nextInt(8)+1;
            while (numfruta3==numfrutaelegida3){
                numfruta3 = rand.nextInt(8)+1;
            }
            ponerimagen(fruta3,"fruta"+numfruta3);
        }

        if (puestoelegida==2){
            ponerimagen(fruta4,frutaelegida1);
            ponerimagen(fruta5,frutaelegida2);
            ponerimagen(fruta6,"fruta"+numfrutaelegida3);
        }else{
            int numfruta4 = rand.nextInt(8)+1;
            ponerimagen(fruta4,"fruta"+numfruta4);
            int numfruta5 = rand.nextInt(8)+1;
            ponerimagen(fruta5,"fruta"+numfruta5);
            int numfruta6 = rand.nextInt(8)+1;
            while (numfruta6==numfrutaelegida3){
                numfruta6 = rand.nextInt(8)+1;
            }
            ponerimagen(fruta6,"fruta"+numfruta6);
        }

        if (puestoelegida==3){
            ponerimagen(fruta7,frutaelegida1);
            ponerimagen(fruta8,frutaelegida2);
            ponerimagen(fruta9,"fruta"+numfrutaelegida3);
        }else{
            int numfruta7 = rand.nextInt(8)+1;
            ponerimagen(fruta7,"fruta"+numfruta7);
            int numfruta8 = rand.nextInt(8)+1;
            ponerimagen(fruta8,"fruta"+numfruta8);
            int numfruta9 = rand.nextInt(8)+1;
            while (numfruta9==numfrutaelegida3){
                numfruta9 = rand.nextInt(8)+1;
            }
            ponerimagen(fruta9,"fruta"+numfruta9);
        }

        fruta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puestoelegida==1){
                    Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vib.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(septimapantalla.this, octavapantalla.class);
                    intent.putExtra("errores", ""+errores);
                    septimapantalla.this.startActivity(intent);
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
                    Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vib.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(septimapantalla.this, octavapantalla.class);
                    intent.putExtra("errores", ""+errores);
                    septimapantalla.this.startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"oh no, intenta otra vez!!",Toast.LENGTH_LONG).show();
                    errores++;
                }
            }
        });
        fruta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puestoelegida==1){
                    Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vib.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(septimapantalla.this, octavapantalla.class);
                    intent.putExtra("errores", ""+errores);
                    septimapantalla.this.startActivity(intent);
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
                    Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vib.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(septimapantalla.this, octavapantalla.class);
                    intent.putExtra("errores", ""+errores);
                    septimapantalla.this.startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"oh no, intenta otra vez!!",Toast.LENGTH_LONG).show();
                    errores++;
                }
            }
        });
        fruta5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puestoelegida==2){
                    Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vib.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(septimapantalla.this, octavapantalla.class);
                    intent.putExtra("errores", ""+errores);
                    septimapantalla.this.startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"oh no, intenta otra vez!!",Toast.LENGTH_LONG).show();
                    errores++;
                }
            }
        });
        fruta6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puestoelegida==2){
                    Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vib.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(septimapantalla.this, octavapantalla.class);
                    intent.putExtra("errores", ""+errores);
                    septimapantalla.this.startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"oh no, intenta otra vez!!",Toast.LENGTH_LONG).show();
                    errores++;
                }
            }
        });
        fruta7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puestoelegida==3){
                    Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vib.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(septimapantalla.this, octavapantalla.class);
                    intent.putExtra("errores", ""+errores);
                    septimapantalla.this.startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"oh no, intenta otra vez!!",Toast.LENGTH_LONG).show();
                    errores++;
                }
            }
        });
        fruta8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puestoelegida==3){
                    Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vib.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(septimapantalla.this, octavapantalla.class);
                    intent.putExtra("errores", ""+errores);
                    septimapantalla.this.startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"oh no, intenta otra vez!!",Toast.LENGTH_LONG).show();
                    errores++;
                }
            }
        });
        fruta9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puestoelegida==3){
                    Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vib.vibrate(800);
                    }
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(septimapantalla.this, octavapantalla.class);
                    intent.putExtra("errores", ""+errores);
                    septimapantalla.this.startActivity(intent);
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


