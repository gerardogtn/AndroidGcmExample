package gerardogtn.com.gcmtest.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by gerardogtn on 3/5/16.
 */
public class SharedPreferencesHelper {

    private static SharedPreferences preferences;

    public static void initialize(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void storeIsTokenSentToServer(boolean isSent) {
        preferences.edit().putBoolean(Constant.KEY_IS_TOKEN_SENT_TO_SERVER, isSent).apply();
    }

    public static boolean loadIsTokenSentToServer() {
        return preferences.getBoolean(Constant.KEY_IS_TOKEN_SENT_TO_SERVER, false);
    }

    private class Constant {
        private static final String KEY_IS_TOKEN_SENT_TO_SERVER = "token_sent_to_server";
    }
}
