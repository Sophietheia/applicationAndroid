package sophie.testgooglemaps;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import static java.sql.DriverManager.getConnection;

/**
 * Created by Alex on 16/02/2017.
 */

public class LoginActivity extends Activity {

    // Lien vers votre page php sur votre serveur
    private static final String	UPDATE_URL	= "https://sophietheai.herokuapp.com/login";
    private static final String PREF_NAME = "APIAI_preferences";
    private static final String SESSION_ID = "sessionId";

    public ProgressDialog progressDialog;

    private EditText UserEditText;
    private EditText PassEditText;

    String username,password;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(SaveSharedPreference.getSession_ID(LoginActivity.this).length() == 0) // si l'utilisateur ne s'est pas déjà connecté
        {
            setContentView(R.layout.activity_login);

            // Récupération des éléments de la vue définis dans le xml
            UserEditText = (EditText) findViewById(R.id.username);
            PassEditText = (EditText) findViewById(R.id.password);

            Button myButton = (Button) findViewById(R.id.okbutton);

            myButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              Log.i("Sophie_the_AI", "Ca marche");

                username = UserEditText.getText().toString();
                password = PassEditText.getText().toString();


                try {
                    JSONObject response = new JSONParse().execute().get(); // On rajouter .get() à la fin pour récupérer le JSONObject qu'on return avec la méthode doInBackground
                    String session_id = response.optString("user_id");
                    System.out.println("session" +session_id);  // Pour vérifier la valeure de connection dans les logs
                    //**************************************************
                    save(LoginActivity.this, session_id);

                    System.out.println("Username: .....: "+getValue(LoginActivity.this));
                    //**************************************************


                    if (session_id.equals("-1"))
                    {
                        Intent myIntent = new Intent(LoginActivity.this, LoginActivity.class);
                        startActivity(myIntent);
                    }

                    else
                    {
                        // Transforme le UserEditText en string pour l'utiliser dans la fonction SaveSharedPreference.setUserName(LoginActivity.this, inputText);
                        final String inputText = UserEditText.getText().toString();

                        // Test dans la console si ça marche
                        System.out.println(inputText);

                        SaveSharedPreference.setSessionID(LoginActivity.this, session_id);

                        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(myIntent);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }
            });
       }

        else // si l'utilisateur s'est déjà connecté
        {
            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(myIntent);
        }

    }

    public class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            UserEditText = (EditText) findViewById(R.id.username);
            PassEditText = (EditText) findViewById(R.id.password);

            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        public JSONObject doInBackground(String... args) {
            JSONParser jsonParser = new JSONParser();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));
            // Getting JSON from URL
            JSONObject json = jsonParser.makeHttpRequest (UPDATE_URL, "POST", params);

            Log.i("Sophie_the_AI", json.toString());
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
        }
    }

    public void save(Context context, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(SESSION_ID, text); //3
        editor.commit(); //4
    }

    public String getValue(Context context) {
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE); //1
        text = settings.getString(SESSION_ID, ""); //2
        return text;
    }

}

