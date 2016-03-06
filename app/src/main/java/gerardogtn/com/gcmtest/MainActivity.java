package gerardogtn.com.gcmtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import gerardogtn.com.gcmtest.gcm.RegistrationIntentService;
import gerardogtn.com.gcmtest.sharedpreferences.SharedPreferencesHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferencesHelper.initialize(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPlayServicesConfigured()) {
            registerToGcm();
        }
    }

    private boolean isPlayServicesConfigured() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            handlePlayServicesError(apiAvailability, resultCode);
            return false;
        }
        return true;
    }

    private void handlePlayServicesError(GoogleApiAvailability apiAvailability, int resultCode) {
        if (apiAvailability.isUserResolvableError(resultCode)) {
            apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
        } else {
            finish();
        }
    }

    private void registerToGcm() {
        if (!SharedPreferencesHelper.loadIsTokenSentToServer()) {
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }
}
