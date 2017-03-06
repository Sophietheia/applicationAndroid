package sophie.testgooglemaps;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Emna on 28/02/2017.
 */

public class MemoryActivity extends Activity {




    private String hardgame="";
    private String easygame="";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        hardgame="http://www.kidadoweb.com/jeux-enfants/memory-cuisine/memory-24.htm";
        easygame="http://www.kidadoweb.com/jeux-enfants/memory-animaux/memory-16.htm";

        Button playBtn = (Button) findViewById(R.id.playBtn);


        playBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(hardgame));
                startActivity(browserIntent); }

                catch (ActivityNotFoundException e) {
//                    Toast.makeText(this, "No application can handle this request." + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


            }
        });

    }
}
