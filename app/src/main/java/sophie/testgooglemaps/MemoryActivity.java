package sophie.testgooglemaps;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by Emna on 28/02/2017.
 */

public class MemoryActivity extends Activity {


    private static final String	UPDATE_URL	= "https://sophietheai.herokuapp.com/memory";

    String hardgame="http://www.kidadoweb.com/jeux-enfants/memory-cuisine/memory-24.htm";
    String easygame="http://www.kidadoweb.com/jeux-enfants/memory-animaux/memory-16.htm";

    private Button ButtonBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);




        Button playBtn = (Button) findViewById(R.id.playBtn);

        ButtonBack= (Button) findViewById(R.id.buttonBack);

        ButtonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                Intent myIntent = new Intent(MemoryActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });


        playBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                     try {
                    JSONObject response = new JSONParse().execute().get(); // On rajouter .get() à la fin pour récupérer le JSONObject qu'on return avec la méthode doInBackground
                     System.out.println("hello");
                    int stade = response.optInt("stade");
                    System.out.println(stade);  // Pour vérifier la valeure de connection dans les logs

                   if (stade==1)
                   {

                       try {
                           Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(easygame));
                           startActivity(browserIntent); }

                       catch (ActivityNotFoundException e) {
//                    Toast.makeText(this, "No application can handle this request." + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                           e.printStackTrace();
                       }

                   }
                   if (stade==2)
                   {

                       try {
                           Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(hardgame));
                           startActivity(browserIntent); }

                       catch (ActivityNotFoundException e) {
//                    Toast.makeText(this, "No application can handle this request." + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                           e.printStackTrace();
                       }

                   }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
   /*       try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(hardgame));
                startActivity(browserIntent); }
*/
                catch (ActivityNotFoundException e) {
                    // Toast.makeText(this, "No application can handle this request." + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


            }
        });

    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(MemoryActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jsonParser = new JSONParser();

            String session_id = SaveSharedPreference.getSession_ID(MemoryActivity.this);
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("sessionId", session_id));
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
