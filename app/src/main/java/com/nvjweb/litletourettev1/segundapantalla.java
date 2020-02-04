package com.nvjweb.litletourettev1;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nvjweb.litletourettev1.data.UsuarioContract;
import com.nvjweb.litletourettev1.data.UsuarioHelper;
import com.nvjweb.litletourettev1.objetos.Usuario;

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

import javax.net.ssl.HttpsURLConnection;

public class segundapantalla extends AppCompatActivity {
    private static final String PREFS_KEY = "PREFS";

    ImageView registrar;
    String avatar, idusuario=null;
    EditText ingresanombre,ingresaclave,txtedad;

    public final static String path = "https://littletourettebase.herokuapp.com/guardarusuario";
    java.net.URL url;
    String responseText;
    StringBuffer response;
    UsuarioHelper dbUser;
    ServicioWebRegistrar registrarusuario;
    Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segundapantalla);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        dbUser= new UsuarioHelper(getApplicationContext());

        avatar = getIntent().getStringExtra("avatar");
        //Toast.makeText(getApplicationContext(), avatar, Toast.LENGTH_LONG).show();

        registrar=findViewById(R.id.registrar);
        ingresanombre=findViewById(R.id.ingresanombre);
        ingresaclave=findViewById(R.id.ingresaclave);
        txtedad=findViewById(R.id.txtedad);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnectedToInternet())
                {
                    registrarusuario = (ServicioWebRegistrar) new ServicioWebRegistrar().execute();
                }
                else
                {
                    saveContentProvider();

                   // Toast.makeText(getApplicationContext(),"no hay internet", Toast.LENGTH_LONG).show();
                }
            }
        });

        txtedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.txtedad:
                        showDatePickerDialog();
                        break;
                }
            }
        });

    }
    private void saveContentProvider(){
        SQLiteDatabase db = dbUser.getWritableDatabase();
        ContentValues values = new ContentValues();
        user= new Usuario();
        user.setUserName(ingresanombre.getText()+"");
        user.setPassword(ingresaclave.getText()+"");
        user.setAvatar(avatar);
        user.setFechaNacimiento(txtedad.getText()+"");
        values.put(UsuarioContract.UsuariosEntry.USERNAME,user.getUserName());
        values.put(UsuarioContract.UsuariosEntry.PASSWORD,user.getPassword());
        values.put(UsuarioContract.UsuariosEntry.AVATAR,user.getAvatar());
        values.put(UsuarioContract.UsuariosEntry.FECHA_NACIMIENTO,user.getFechaNacimiento());
        values.put(UsuarioContract.UsuariosEntry.GUARDADO,"false");
        long newRowId = db.insert(UsuarioContract.UsuariosEntry.TABLE_NAME, null, values);
        Log.i("rowId", newRowId+"");
        Intent itemintent = new Intent(segundapantalla.this, cuartapantalla.class);
        segundapantalla.this.startActivity(itemintent);
        guardarusuario(segundapantalla.this,"idusuario", ""+newRowId);
        guardarusuario(segundapantalla.this,"nombre", String.valueOf(user.getUserName()));
        guardarusuario(segundapantalla.this,"avatar", user.getAvatar());

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

    private void showDatePickerDialog() {

        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                month=month+1;
                String formattedMonth= String.valueOf(month);
                String formattedDayOfMonth= String.valueOf(day);
                if(month < 10){

                    formattedMonth = "0" + month;
                }
                if(day < 10){

                    formattedDayOfMonth = "0" + day;
                }
                final String selectedDate = formattedDayOfMonth + "/" + formattedMonth + "/" + year;
                EditText etPlannedDate = (EditText) findViewById(R.id.txtedad);
                etPlannedDate.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private class ServicioWebRegistrar extends AsyncTask<Integer, Integer, String> {


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
            saveContentProvider();

            stringMap.put("nombre", ""+ingresanombre.getText());
            stringMap.put("clave", ""+ingresaclave.getText());
            stringMap.put("nacimiento", ""+txtedad.getText());
            stringMap.put("avatar", ""+avatar);

            String requestBody = Utils.buildPostParameters(stringMap);
            try {
                urlConnection = (HttpURLConnection) Utils.makeRequest("POST", path, null, "application/x-www-form-urlencoded", requestBody);
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
                    idusuario = jsonobject.getString("id");
                }

            } catch (Exception e) {
            }
            return idusuario;
        }

        @Override
        protected void onPostExecute(String nombre) {
            super.onPostExecute(nombre);

            if (idusuario!=null){
                Intent itemintent = new Intent(segundapantalla.this, cuartapantalla.class);
                segundapantalla.this.startActivity(itemintent);
                guardarusuario(segundapantalla.this,"idusuario", ""+idusuario);
                guardarusuario(segundapantalla.this,"nombre", String.valueOf(ingresanombre.getText()));
                guardarusuario(segundapantalla.this,"avatar", avatar);

            }else{
                Toast.makeText(getApplicationContext(),"no se pudo registrar", Toast.LENGTH_LONG).show();

            }

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
