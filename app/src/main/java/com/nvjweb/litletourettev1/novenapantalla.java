package com.nvjweb.litletourettev1;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nvjweb.litletourettev1.data.ActividadesContract;
import com.nvjweb.litletourettev1.data.ActividadesHelper;
import com.nvjweb.litletourettev1.data.UsuarioContract;
import com.nvjweb.litletourettev1.data.UsuarioHelper;
import com.nvjweb.litletourettev1.objetos.Actividades;
import com.nvjweb.litletourettev1.objetos.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class novenapantalla extends AppCompatActivity {
    private static final String PREFS_KEY = "PREFS";
    Integer errores=0, aciertos=0;
    String avatar,nombre;
    ImageView imgavatar, regresar;
    TextView txtaciertos, txterrores,txtnivel;

    String path,idusuario;
    java.net.URL url;
    String responseText;
    StringBuffer response;
    ServicioWebReporte reporte;
    Boolean flag;
    UsuarioHelper dbUser;
    Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novenapantalla);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       dbUser= new UsuarioHelper(getApplicationContext());


        avatar=leerusuario(this,"avatar");
        nombre=leerusuario(this,"nombre");
        idusuario=leerusuario(this,"idusuario");




        txtaciertos=findViewById(R.id.txtaciertos);
        txterrores=findViewById(R.id.txterrores);
        txtnivel=findViewById(R.id.txtnivel);
        imgavatar=findViewById(R.id.avatar);
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
        if(isConnectedToInternet())
        {
            path = "https://littletourettebase.herokuapp.com/mostraractividades/"+idusuario;
            reporte = (ServicioWebReporte) new ServicioWebReporte().execute();
            guardarinternet();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"no hay internet", Toast.LENGTH_LONG).show();






        }

        regresar=findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(novenapantalla.this,cuartapantalla.class);
                novenapantalla.this.startActivity(intent);
            }
        });


    }

    private void guardarinternet() {
         SQLiteDatabase db = dbUser.getWritableDatabase();
         //Log.i("usernew",ingresanombre.getText()+"");
        String[] projection = {
                    BaseColumns._ID,
                   UsuarioContract.UsuariosEntry.USERNAME,
                    UsuarioContract.UsuariosEntry.PASSWORD
        };

             Cursor cursor = db.query(
                   UsuarioContract.UsuariosEntry.TABLE_NAME,   // The table to query
                       projection,             // The array of columns to return (pass null to get all)
                          null,          // The values for the WHERE clause
                       null,          // The values for the WHERE clause
                      null,                   // don't filter by row groups
                      null,                   // don't filter by row groups
                     null             // The sort order
             );

              while(cursor.moveToNext()) {
                         long itemId = cursor.getLong(
                                 cursor.getColumnIndexOrThrow( UsuarioContract.UsuariosEntry._ID));
                         String nombre=cursor.getString(
                                 cursor.getColumnIndexOrThrow( UsuarioContract.UsuariosEntry.USERNAME));
                         String password=cursor.getString(
                                 cursor.getColumnIndexOrThrow( UsuarioContract.UsuariosEntry.PASSWORD));
                         /* String avatar=cursor.getString(
                                 cursor.getColumnIndexOrThrow( UsuarioContract.UsuariosEntry.AVATAR));*/



                            Log.i("usernew",nombre);
                                        Log.i("usernew",password);
                                         user= new Usuario();
                                         user.setUserName(nombre);
                                         user.setPassword(password);
                                         user.setAvatar("");
                                         user.setId(itemId+"");

                                    }






    }























    public static String leerusuario(Context context, String keyPref) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        return  preferences.getString(keyPref, "");
    }

    private class ServicioWebReporte extends AsyncTask<Integer, Integer, Boolean> {


        @Override
        protected void onPreExecute() {
        }
        @Override
        protected Boolean doInBackground(Integer... params) {
            return getWebServiceResponseData();
        }

        protected Boolean getWebServiceResponseData() {
            flag=false;
            try {
                url=new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    // Reading response from input Stream
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String output;
                    response = new StringBuffer();

                    while ((output = in.readLine()) != null) {
                        response.append(output);
                    }
                    in.close();
                }}
            catch(Exception e){
                e.printStackTrace();
            }

            try {
                responseText = response.toString();
            } catch (Exception e) {
                e.printStackTrace();
                reporte.cancel(true);
            }

            try {
                JSONArray jsonarray = new JSONArray(responseText);

                for (int i=0;i<jsonarray.length();i++){
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    flag=true;
                    errores=errores+ Integer.valueOf(jsonobject.getString("errores"));
                    aciertos=aciertos+ Integer.valueOf(jsonobject.getString("aciertos"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return flag;
        }

        @Override
        protected void onPostExecute(Boolean flag) {
            super.onPostExecute(flag);

            if(flag){
                txterrores.setText(""+errores);
                txtaciertos.setText(""+aciertos);
                if(Integer.valueOf(aciertos) < 10){
                    txtnivel.setText("1");
                }else{
                    if(Integer.valueOf(aciertos) < 20){
                        txtnivel.setText("2");
                    }else{
                        if(Integer.valueOf(aciertos) < 35){
                            txtnivel.setText("3");
                        }else{
                            if(Integer.valueOf(aciertos) < 50){
                                txtnivel.setText("4");
                            }else{
                                if(Integer.valueOf(aciertos) < 80){
                                    txtnivel.setText("5");
                                }else{
                                    txtnivel.setText("6");
                                }
                            }
                        }
                    }
                }
            }
        }
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
}


