package sophie.testgooglemaps;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 25/02/2017.
 */

public class AlertActivity  extends Activity {

    // Lien vers votre page php sur votre serveur
    private static final String	UPDATE_URL	= "https://sophietheai.herokuapp.com/alert";

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);



        Button myButton = (Button) findViewById(R.id.button4);
        myButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new AlertActivity.JSONParse().execute();

            }

        });

    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(AlertActivity.this);
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
            JSONObject json = jsonParser.makeHttpRequest (UPDATE_URL, "GET", params);

            //Log.i("Sophie_the_AI", json.toString());
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();



        }
    }
}

