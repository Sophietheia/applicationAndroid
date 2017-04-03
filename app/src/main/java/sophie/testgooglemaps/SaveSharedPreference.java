package sophie.testgooglemaps;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Alex on 06/03/2017.
 */

public class SaveSharedPreference extends Activity {

    static final String PREF_SESSION_ID= "";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setSessionID(Context ctx, String session_id)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_SESSION_ID, session_id);
        editor.commit();
    }

    public static String getSession_ID(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_SESSION_ID, "");
    }
}
