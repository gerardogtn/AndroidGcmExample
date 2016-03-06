package gerardogtn.com.gcmtest.gcm;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import gerardogtn.com.gcmtest.R;

/**
 * Created by gerardogtn on 3/5/16.
 */
public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);

        if (!data.isEmpty() && from.equals(getString(R.string.gcm_defaultSenderId))) {
            sendNotification(data);
        }
    }

    private void sendNotification(Bundle data) {
        Log.d(TAG, "sendNotification() called with: " + "data = [" + data + "]");
    }
}
