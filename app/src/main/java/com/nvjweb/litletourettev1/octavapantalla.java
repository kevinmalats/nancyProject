package com.nvjweb.litletourettev1;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class octavapantalla extends AppCompatActivity {
    private static final String PREFS_KEY = "PREFS";
    Integer errores=0, aciertos=3;
    String avatar,nombre,idusuario;
    ImageView imgavatar, regresar;
    TextView txtnombre;

    public final static String path = "https://littletourettebase.herokuapp.com/guardaractividad";
    java.net.URL url;
    String responseText;
    StringBuffer response;
    ServicioWebActividad guardaractividad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.octavapantalla);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        errores= Integer.valueOf(getIntent().getStringExtra("errores"));
        avatar= leerusuario(this, "avatar");
        imgavatar= findViewById(R.id.imgavatar);

        txtnombre= findViewById(R.id.txtnombre);
        nombre=leerusuario(this, "nombre");
        idusuario=leerusuario(this, "idusuario");
        txtnombre.setText(""+nombre+", lo lograste!! con "+aciertos+ " aciertos y " + errores+" errores!");
        regresar=findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(octavapantalla.this,cuartapantalla.class);
                octavapantalla.this.startActivity(intent);
            }
        });
        guardaractividad = (ServicioWebActividad) new ServicioWebActividad().execute();

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
    }


    public static String leerusuario(Context context, String keyPref) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        return  preferences.getString(keyPref, "");
    }

    @Override
    public void onBackPressed (){
        return;
    }

    private class ServicioWebActividad extends AsyncTask<Integer, Integer, String> {


        @Override
        protected void onPreExecute() {
        }
        @Override
        protected String doInBackground(Integer... params) {
            return getWebServiceResponseData();
        }

        protected String getWebServiceResponseData() {

            HttpURLConnection urlConnection = null;
            Map<String, String> stringMap = new HashMap<>();

            stringMap.put("idusuario", ""+idusuario);
            stringMap.put("aciertos", ""+aciertos);
            stringMap.put("errores", ""+errores);

            String requestBody = segundapantalla.Utils.buildPostParameters(stringMap);
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
                return e.toString();
            } finally {
                if (urlConnection != null) {




                    urlConnection.disconnect();
                }
            }


            return idusuario;
        }

        @Override
        protected void onPostExecute(String nombre) {
            super.onPostExecute(nombre);

        }

    }

    public static class Utils{
        public static String buildPostParameters(Object content) {
            String output = null;
            if ((content instanceof String) ||
                    (content instanceof JSONObject) ||
                    (content instanceof JSONArray)) {
                output = content.toString();
            } else if (content instanceof Map) {
                Uri.Builder builder = new Uri.Builder();
                HashMap hashMap = (HashMap) content;
                if (hashMap != null) {
                    Iterator entries = hashMap.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        builder.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                        entries.remove(); // avoids a ConcurrentModificationException
                    }
                    output = builder.build().getEncodedQuery();
                }
            }

            return output;
        }

        public static URLConnection makeRequest(String method, String apiAddress, String accessToken, String mimeType, String requestBody) throws IOException {
            URL url = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(apiAddress);

                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(!method.equals("GET"));
                urlConnection.setRequestMethod(method);

                urlConnection.setRequestProperty("Authorization", "Bearer " + accessToken);

                urlConnection.setRequestProperty("Content-Type", mimeType);
                OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
                writer.write(requestBody);
                writer.flush();
                writer.close();
                outputStream.close();

                urlConnection.connect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return urlConnection;
        }
    }
}

