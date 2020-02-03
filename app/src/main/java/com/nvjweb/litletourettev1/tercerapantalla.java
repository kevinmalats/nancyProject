package com.nvjweb.litletourettev1;
import android.content.Intent;
import android.content.pm.ActivityInfo;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class tercerapantalla extends AppCompatActivity {

    ImageView avatar1,avatar2,avatar3,avatar5,avatar6,avatar7,avatar9,avatar10,avatar11;
    String avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tercerapantalla);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        avatar1=findViewById(R.id.avatar1);
        avatar2=findViewById(R.id.avatar2);
        avatar3=findViewById(R.id.avatar3);
        avatar5=findViewById(R.id.avatar5);
        avatar6=findViewById(R.id.avatar6);
        avatar7=findViewById(R.id.avatar7);
        avatar9=findViewById(R.id.avatar9);
        avatar10=findViewById(R.id.avatar10);
        avatar11=findViewById(R.id.avatar11);

        avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar=(String) avatar1.getTag();
                Intent intent = new Intent(tercerapantalla.this,segundapantalla.class);
                intent.putExtra("avatar", avatar);
                tercerapantalla.this.startActivity(intent);

            }
        });
        avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar=(String) avatar2.getTag();
                Intent intent = new Intent(tercerapantalla.this,segundapantalla.class);
                intent.putExtra("avatar", avatar);
                tercerapantalla.this.startActivity(intent);

            }
        });
        avatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar=(String) avatar3.getTag();
                Intent intent = new Intent(tercerapantalla.this,segundapantalla.class);
                intent.putExtra("avatar", avatar);
                tercerapantalla.this.startActivity(intent);

            }
        });
        avatar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar=(String) avatar5.getTag();
                Intent intent = new Intent(tercerapantalla.this,segundapantalla.class);
                intent.putExtra("avatar", avatar);
                tercerapantalla.this.startActivity(intent);

            }
        });
        avatar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar=(String) avatar6.getTag();
                Intent intent = new Intent(tercerapantalla.this,segundapantalla.class);
                intent.putExtra("avatar", avatar);
                tercerapantalla.this.startActivity(intent);

            }
        });
        avatar7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar=(String) avatar7.getTag();
                Intent intent = new Intent(tercerapantalla.this,segundapantalla.class);
                intent.putExtra("avatar", avatar);
                tercerapantalla.this.startActivity(intent);

            }
        });
        avatar9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar=(String) avatar9.getTag();
                Intent intent = new Intent(tercerapantalla.this,segundapantalla.class);
                intent.putExtra("avatar", avatar);
                tercerapantalla.this.startActivity(intent);

            }
        });

        avatar10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar=(String) avatar10.getTag();
                Intent intent = new Intent(tercerapantalla.this,segundapantalla.class);
                intent.putExtra("avatar", avatar);
                tercerapantalla.this.startActivity(intent);

            }
        });

        avatar11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar=(String) avatar11.getTag();
                Intent intent = new Intent(tercerapantalla.this,segundapantalla.class);
                intent.putExtra("avatar", avatar);
                tercerapantalla.this.startActivity(intent);

            }
        });







    }
}
