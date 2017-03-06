package sophie.testgooglemaps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RappelsActivity extends AppCompatActivity {

    // Lien vers votre page php sur votre serveur
    private static final String	UPDATE_URL	= "https://sophietheai.herokuapp.com/reminders";

//    private TextView tv1;
//    private TextView tv2;
//    private TextView tv3;
//    private TextView tv4;
//    private TextView tv5;
//    private TextView tv6;
//    private TextView tv7;
//    private TextView tv8;
    private TextView tv9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rappels);

        TextView tv_table[] = new TextView[10];

        tv_table[1] = (TextView) findViewById(R.id.tv1);
        tv_table[2] = (TextView) findViewById(R.id.tv2);
        tv_table[3] = (TextView) findViewById(R.id.tv3);
        tv_table[4] = (TextView) findViewById(R.id.tv4);
        tv_table[5] = (TextView) findViewById(R.id.tv5);
        tv_table[6] = (TextView) findViewById(R.id.tv6);
        tv_table[7] = (TextView) findViewById(R.id.tv7);
        tv_table[8] = (TextView) findViewById(R.id.tv8);
        tv_table[9] = (TextView) findViewById(R.id.tv9);

        Log.i("Sophie_the_AI", "on Create");

        try {
            JSONObject response = new RappelsActivity.JSONParse().execute().get();
            JSONArray arr = response.getJSONArray("json");
            List<String> list = new ArrayList<String>();
            for(int i = 0; i < arr.length() && i<10; i++){
                tv_table[i+1].setText(arr.getJSONObject(i).getString("name")+" le "+arr.getJSONObject(i).getString("date_task"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(RappelsActivity.this);
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
            JSONObject json = jsonParser.makeHttpRequest (UPDATE_URL, "POST", params);

            Log.i("Sophie_the_AI", json.toString());
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();



        }
    }
}