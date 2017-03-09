package sophie.testgooglemaps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RappelsActivity extends AppCompatActivity {

    // Lien vers votre page php sur votre serveur
    private static final String	UPDATE_URL	= "https://sophietheai.herokuapp.com/reminders";
    private Button ButtonBack;

//    private TextView tv1;
//    private TextView tv2;
//    private TextView tv3;
//    private TextView tv4;
//    private TextView tv5;
//    private TextView tv6;
//    private TextView tv7;
//    private TextView tv8;
//    private TextView tv9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rappels);

        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ButtonBack= (Button) findViewById(R.id.buttonBack);
        ButtonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                Intent myIntent = new Intent(RappelsActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        try {
            JSONObject response = new RappelsActivity.JSONParse().execute().get();
            JSONArray arr = response.getJSONArray("json");

            for(int i = 0; i < arr.length() && i<10; i++){
                list.add(arr.getJSONObject(i).getString("name")+"\n"+arr.getJSONObject(i).getString("date_task"));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,android.R.id.text1,list);

            ListView listView1 = (ListView) findViewById(R.id.list_reminders);

            listView1.setAdapter(adapter);

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

            String username = SaveSharedPreference.getUserName(RappelsActivity.this);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // Getting JSON from URL
            params.add(new BasicNameValuePair("username", username));
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