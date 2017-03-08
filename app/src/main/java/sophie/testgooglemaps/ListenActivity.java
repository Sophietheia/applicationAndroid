package sophie.testgooglemaps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import com.google.gson.JsonElement;
import java.util.Map;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alex on 01/02/2017.
 */

public class ListenActivity extends Activity implements AIListener {

    private Button listenButton2;
    private Button ButtonBack;
    private TextView resultTextView;
    private AIService aiService;
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);

        final AIConfiguration config = new AIConfiguration("324d5e56d0354221ba1f51766e2d1235",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

        //initialize the AI service and add this instance as the listener to handle events.
        aiService = AIService.getService(this, config);
        aiService.setListener(this);

        listenButton2 = (Button) findViewById(R.id.listenButton2);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        responseTextView = (TextView) findViewById(R.id.responseTextView);

        ButtonBack= (Button) findViewById(R.id.buttonBack);

        ButtonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                Intent myIntent = new Intent(ListenActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });


        // ask permission to use the microphone
        if (ContextCompat.checkSelfPermission(ListenActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ListenActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }
    }




    //method to start listening on the button click
    public void listenButtonOnClick(final View view) {
        aiService.startListening();

    }

    //method to show the results when the listening is complete
    public void onResult(final AIResponse response) {
        Result result = response.getResult();

        // Get parameters
        String parameterString = "";
        if (result.getParameters() != null && !result.getParameters().isEmpty()) {
            for (final Map.Entry<String, JsonElement> entry : result.getParameters().entrySet()) {
                parameterString += "(" + entry.getKey() + ", " + entry.getValue() + ") ";
            }
        }

        // Show results in TextView.
        resultTextView.setText("Query:" + result.getResolvedQuery() +
                "\nAction: " + result.getAction() +
                "\nParameters: " + parameterString + "\nSpeech: " + result.getFulfillment().getSpeech());


        responseTextView.setText(result.getFulfillment().getSpeech());


    }



    // method to handle errors
    @Override
    public void onError(final AIError error) {
        resultTextView.setText(error.toString());
    }

    //following empty methods to implement the AIListener interface
    @Override
    public void onListeningStarted() {}

    @Override
    public void onListeningCanceled() {}

    @Override
    public void onListeningFinished() {}

    @Override
    public void onAudioLevel(final float level) {}
}
