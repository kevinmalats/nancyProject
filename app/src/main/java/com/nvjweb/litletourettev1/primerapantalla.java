package com.nvjweb.litletourettev1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nvjweb.litletourettev1.data.UsuarioContract;
import com.nvjweb.litletourettev1.data.UsuarioHelper;
import com.nvjweb.litletourettev1.objetos.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class primerapantalla extends AppCompatActivity {
    EditText ingresanombre, ingresaclave;
    Button btnlogin, btnregistrar;
    String avatar="avatar1",idusuario="";
    private static final String TAG = "Login";
    private static final String PREFS_KEY = "PREFS";
    public final static String path = "https://littletourettebase.herokuapp.com/leerusuarios";
    java.net.URL url;
    String responseText;
    StringBuffer response;
    ServicioWebLogin login;
    UsuarioHelper dbUser;
    Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primerapantalla);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        dbUser= new UsuarioHelper(getApplicationContext());

        ingresanombre=findViewById(R.id.ingresanombre);
        ingresaclave=findViewById(R.id.ingresaclave);


        btnregistrar=findViewById(R.id.btnregistrar);
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itemintent = new Intent(primerapantalla.this, tercerapantalla.class);
                primerapantalla.this.startActivity(itemintent);
            }
        });

        btnlogin=findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnectedToInternet())
                {
                    login = (ServicioWebLogin) new ServicioWebLogin().execute();
                }
                else
                {
                    loginContentProvider();
                    Toast.makeText(getApplicationContext(),"no hay internet", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
    private void loginContentProvider(){
        SQLiteDatabase db = dbUser.getWritableDatabase();
        Log.i("usernew",ingresanombre.getText()+"");
        String[] projection = {
                BaseColumns._ID,
                UsuarioContract.UsuariosEntry.USERNAME,
                UsuarioContract.UsuariosEntry.PASSWORD,
                UsuarioContract.UsuariosEntry.AVATAR
        };
        String selection = UsuarioContract.UsuariosEntry.USERNAME+ " = '"+ingresanombre.getText()+"' And "+UsuarioContract.UsuariosEntry.PASSWORD
        +"='"+ingresaclave.getText()+"'";
        Cursor cursor = db.query(
                UsuarioContract.UsuariosEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
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
             String avatar=cursor.getString(
                    cursor.getColumnIndexOrThrow( UsuarioContract.UsuariosEntry.AVATAR));

            Log.i("usernew",nombre);
            Log.i("usernew",password);
             user= new Usuario();
             user.setUserName(nombre);
             user.setPassword(password);
             user.setAvatar(avatar);
             user.setId(itemId+"");
             loginOk();

        }
    }
    private void loginOk(){
        Intent itemintent = new Intent(primerapantalla.this, cuartapantalla.class);
        primerapantalla.this.startActivity(itemintent);
        guardarusuario(primerapantalla.this,"nombre", String.valueOf(ingresanombre.getText()));
        guardarusuario(primerapantalla.this,"avatar", user.getAvatar());
        guardarusuario(primerapantalla.this,"idusuario", user.getId());
    }

    private class ServicioWebLogin extends AsyncTask<Integer, Integer, String> {


        @Override
        protected void onPreExecute() {
        }
        @Override
        protected String doInBackground(Integer... params) {
            return getWebServiceResponseData();
        }

        protected String getWebServiceResponseData() {

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

                login.cancel(true);

            }

            try {
                JSONArray jsonarray = new JSONArray(responseText);

                for (int i=0;i<jsonarray.length();i++){
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    String nombre = jsonobject.getString("nombre");
                    String clave=jsonobject.getString("clave");
                    if(String.valueOf(ingresanombre.getText()).equals(String.valueOf(nombre))){
                        if (String.valueOf(ingresaclave.getText()).equals(String.valueOf(clave))){
                            idusuario=jsonobject.getString("id");
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return idusuario;
        }

        @Override
        protected void onPostExecute(String nombre) {
            super.onPostExecute(nombre);

            if (idusuario!=null){
                Intent itemintent = new Intent(primerapantalla.this, cuartapantalla.class);
                primerapantalla.this.startActivity(itemintent);
                guardarusuario(primerapantalla.this,"nombre", String.valueOf(ingresanombre.getText()));
                guardarusuario(primerapantalla.this,"avatar", avatar);
                guardarusuario(primerapantalla.this,"idusuario", idusuario);



            }else{
                Toast.makeText(getApplicationContext(),"datos incorrectos", Toast.LENGTH_LONG).show();

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
    public static void guardarusuario(Context context, String keyPref, String valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString(keyPref, valor);
        editor.commit();
    }


}





















