package sophie.testgooglemaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button myButton = (Button) findViewById(R.id.button1);
        myButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i("Sophie_the_AI", "Ca marche");

                Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(myIntent);
            }
        });


    }
}
