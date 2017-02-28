package sophie.testgooglemaps;

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


    private TextView urlView;
    private TextView gameTextView;
    private String hardgame="";
    private String easygame="";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        gameTextView = (TextView) findViewById(R.id.gameTextView);


        hardgame="http://www.kidadoweb.com/jeux-enfants/memory-cuisine/memory-24.htm";
        easygame="http://www.kidadoweb.com/jeux-enfants/memory-animaux/memory-16.htm";

        gameTextView.setText(hardgame);

    }
}
