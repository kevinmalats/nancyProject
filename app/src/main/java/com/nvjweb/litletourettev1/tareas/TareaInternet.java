package com.nvjweb.litletourettev1.tareas;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import com.nvjweb.litletourettev1.cuartapantalla;
import com.nvjweb.litletourettev1.data.UsuarioContract;
import com.nvjweb.litletourettev1.data.UsuarioHelper;
import com.nvjweb.litletourettev1.objetos.Usuario;
import com.nvjweb.litletourettev1.primerapantalla;
import com.nvjweb.litletourettev1.segundapantalla;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class TareaInternet  extends AsyncTask<Integer, Integer, String> {
public Context contex;
    UsuarioHelper dbUser;
    String responseText;
    Usuario user;
    StringBuffer response;
    public final static String path = "https://littletourettebase.herokuapp.com/guardarusuario";
    ArrayList<Usuario>listUsuario=new ArrayList<Usuario>();
    public TareaInternet(Context contex) {
        this.contex = contex;
        dbUser= new UsuarioHelper(contex.getApplicationContext());
    }
    @Override
    protected void onPreExecute() {
    }
    @Override
    protected String doInBackground(Integer... integers) {
        while(true){
       if(isConnectedToInternet()){
           if(subirDatos()){
               //return "ok";
               break;
            }
            }
        }
        return "ok";
    }
   private boolean subirDatos(){
        obterData();


        return true;
    }
    protected void getWebServiceResponseData() {

        HttpURLConnection urlConnection = null;

        ArrayList<Map> listString= new ArrayList<Map>();

        for (Usuario user:listUsuario
             ) {
            Map<String, String> stringMap = new HashMap<>();
            stringMap.put("nombre", ""+user.getUserName());
            stringMap.put("clave", ""+user.getPassword());
            stringMap.put("nacimiento", ""+user.getFechaNacimiento());
            stringMap.put("avatar", ""+user.getAvatar());
            listString.add(stringMap);

        }


        String requestBody = segundapantalla.Utils.buildPostParameters(listString);
        try {
            urlConnection = (HttpURLConnection) segundapantalla.Utils.makeRequest("POST", path, null, "application/x-www-form-urlencoded", requestBody);
            InputStream inputStream;
            if (urlConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = urlConnection.getInputStream();
            } else {
                inputStream = urlConnection.getErrorStream();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp, response = "";
            while ((temp = bufferedReader.readLine()) != null) {
                response += temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        try {
            URL url=new URL("https://littletourettebase.herokuapp.com/ultimoidusuario");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            Log.d("TAG", "Response code: " + responseCode);
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
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        try {
            responseText = response.toString();
            JSONArray jsonarray = new JSONArray(responseText);

            for (int i=0;i<jsonarray.length();i++){
                JSONObject jsonobject = jsonarray.getJSONObject(0);

            }

        } catch (Exception e) {
        }

    }
    private void obterData() {
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
            listUsuario.add(user);

        }





    }




    @Override
    protected void onPostExecute(String nombre) {
        super.onPostExecute(nombre);

        Log.i("datos",listUsuario.toString());

    }

    public boolean isConnectedToInternet(){

        ConnectivityManager connectivity = (ConnectivityManager)contex.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
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
