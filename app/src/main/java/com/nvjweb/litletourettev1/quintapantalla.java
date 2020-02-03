package com.nvjweb.litletourettev1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class quintapantalla extends AppCompatActivity {

    ImageView fruta1, fruta2, fruta3, frutaadivinar, regresar;
    ArrayList <String> frutas;
    Integer errores=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quintapantalla);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        regresar=findViewById(R.id.regresar);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(quintapantalla.this,cuartapantalla.class);
                quintapantalla.this.startActivity(intent);


            }
        });

        frutas = new ArrayList<>();
        frutaadivinar=findViewById(R.id.frutaadivinar);
        fruta1=findViewById(R.id.fruta1);
        fruta2=findViewById(R.id.fruta2);
        fruta3=findViewById(R.id.fruta3);

        Random rand = new Random();

        int numfruta1 = rand.nextInt(8)+1;
        ponerimagen(fruta1,"fruta"+numfruta1);
        frutas.add("fruta"+numfruta1);
        int numfruta2 = rand.nextInt(8)+1;
        while(numfruta2==numfruta1){
            numfruta2=rand.nextInt(8)+1;
        }
        ponerimagen(fruta2,"fruta"+numfruta2);
        frutas.add("fruta"+numfruta2);

        int numfruta3 = rand.nextInt(8)+1;
        while(numfruta3==numfruta2 || numfruta3==numfruta1){
            numfruta3=rand.nextInt(8)+1;
        }
        ponerimagen(fruta3,"fruta"+numfruta3);
        frutas.add("fruta"+numfruta3);

        final int numfrutaadivinar=rand.nextInt(3);
        ponerimagen(frutaadivinar,frutas.get(numfrutaadivinar));

        fruta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numfrutaadivinar==0){
                    Toast.makeText(getApplicationContext(),"muy bien",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(quintapantalla.this, sextapantalla.class);
                    intent.putExtra("frutaelegida", frutas.get(numfrutaadivinar));
                    intent.putExtra("errores", ""+errores);
                    quintapantalla.this.startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(),"oh no, intenta otra vez!!",Toast.LENGTH_LONG).show();
                    errores++;

                }

            }
        });

        fruta2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if(numfrutaadivinar==1){
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(quintapantalla.this, sextapantalla.class);
                    intent.putExtra("frutaelegida", frutas.get(numfrutaadivinar));
                    intent.putExtra("errores", ""+errores);
                    quintapantalla.this.startActivity(intent);


                }else{
                    Toast.makeText(getApplicationContext(),"oh no, intenta otra vez!!",Toast.LENGTH_LONG).show();
                    errores++;

                }


            }
        });

        fruta3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(numfrutaadivinar==2){
                    Toast.makeText(getApplicationContext(),"muy bien!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(quintapantalla.this, sextapantalla.class);
                    intent.putExtra("frutaelegida", frutas.get(numfrutaadivinar));
                    intent.putExtra("errores", ""+errores);
                    quintapantalla.this.startActivity(intent);

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
}
