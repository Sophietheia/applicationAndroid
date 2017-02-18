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

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import android.app.Activity;
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
    private static final String	UPDATE_URL	= "https://glacial-falls-56009.herokuapp.com/register";

    public ProgressDialog progressDialog;


    private EditText UserEditText;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Récupération des éléments de la vue définis dans le xml
        UserEditText = (EditText) findViewById(R.id.username);

        Button myButton = (Button) findViewById(R.id.okbutton);
        myButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i("Sophie_the_AI", "Ca marche");

               /* List<NameValuePair> params = new ArrayList<NameValuePair>();
                JSONParser jsonParser = new JSONParser();
                Log.i("Sophie_the_AI", "Etape 2");


                jsonParser.makeHttpRequest ("https://glacial-falls-56009.herokuapp.com/testdb?info=test", "GET", params);

                Log.i("Sophie_the_AI", "Requete json faite");*/

                new JSONParse().execute();

                Log.i("Sophie_the_AI", "Requete json faite");

             // Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
              //startActivity(myIntent);



            }
        });

    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jsonParser = new JSONParser();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // Getting JSON from URL
            JSONObject json = jsonParser.makeHttpRequest ("https://glacial-falls-56009.herokuapp.com/testdb?info=test", "GET", params);
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();


        }
    }

}

