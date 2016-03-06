package gerardogtn.com.gcmtest.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import gerardogtn.com.gcmtest.R;
import gerardogtn.com.gcmtest.sharedpreferences.SharedPreferencesHelper;

/**
 * Created by gerardogtn on 3/5/16.
 */
public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            sequentiallyProcessTokenRefresh();
        } catch (IOException e) {
            SharedPreferencesHelper.storeIsTokenSentToServer(false);
            e.printStackTrace();
        }
    }

    private void sequentiallyProcessTokenRefresh() throws IOException {
        synchronized (TAG) {
            InstanceID instanceId = InstanceID.getInstance(this);
            String senderId = getString(R.string.gcm_defaultSenderId);

            if (senderId.length() != 0) {
                String token = instanceId.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                sendRegistrationTokenToServer(token);
            }
        }
    }

    // TODO: Send token to server.
    private void sendRegistrationTokenToServer(String token) {
        Log.d(TAG, "sendRegistrationTokenToServer() called with: " + "token = [" + token + "]");
        SharedPreferencesHelper.storeIsTokenSentToServer(true);
    }
}
