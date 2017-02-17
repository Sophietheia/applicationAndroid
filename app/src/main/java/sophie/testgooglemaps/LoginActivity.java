package sophie.testgooglemaps;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Alex on 16/02/2017.
 */

public class LoginActivity extends Activity {

    // Lien vers votre page php sur votre serveur
    private static final String	UPDATE_URL	= "https://glacial-falls-56009.herokuapp.com/register";

    public ProgressDialog				progressDialog;

    private EditText						UserEditText;

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




                /*// Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(this);
                String url ="http://www.google.com";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                mTextView.setText("Response is: "+ response.substring(0,500));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);*/

               try {
                    URL url = new URL(UPDATE_URL);
                    HttpURLConnection client = (HttpURLConnection) url.openConnection();
                   // InputStream stream = connection.getInputStream();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                catch(java.io.IOException e){
                    e.printStackTrace();
                }


              /* HttpURLConnection client = (HttpURLConnection) url.openConnection();

                client.setRequestMethode("POST");
                client.setRequestProperty("Key","Value");
                client.setDoOutput(true);
                */

                /*HttpClient httpClient = new DefaultHttpClient();

                // Create an object of HttpPost

                HttpPost httpPost = new HttpPost(UPDATE_URL);

                // Add POST parameters

                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                nameValuePair.add(new BasicNameValuePair("username", "test_user"));
                nameValuePair.add(new BasicNameValuePair("password", "123456789"));

                //Encoding POST data
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                } catch (UnsupportedEncodingException e) {
                    // log exception
                    e.printStackTrace();
                }

                //making POST request.
                try {
                    HttpResponse response = httpClient.execute(httpPost);
                    // write response to log
                    Log.d("Http Post Response:", response.toString());
                } catch (ClientProtocolException e) {
                    // Log exception
                    e.printStackTrace();
                } catch (IOException e) {
                    // Log exception
                    e.printStackTrace();
                }*/

               Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
               startActivity(myIntent);



            }
        });

    }

}

