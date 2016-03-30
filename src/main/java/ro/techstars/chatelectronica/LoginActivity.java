package ro.techstars.chatelectronica;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;

/**
 * Created by horiaacalin on 30/03/16.
 */
public class LoginActivity extends FirebaseLoginBaseActivity {
    private Button logInButtonLA;
    private String TAG="Login";

    private Firebase mFireBaseRef = new Firebase("https://chatelectronica3.firebaseio.com");
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.e(TAG, "onCreate: ");
        setContentView(R.layout.activity_login);
        logInButtonLA= (Button) findViewById(R.id.lgInButtonLA);
        logInButtonLA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

      showFirebaseLoginPrompt();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        setEnabledAuthProvider(AuthProviderType.PASSWORD);
    }


    @Override
    protected Firebase getFirebaseRef() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putBoolean("login", true);
        return mFireBaseRef;
    }

    @Override
    protected void onFirebaseLoginProviderError(FirebaseLoginError firebaseLoginError) {
        Log.e(TAG, "onFirebaseLoginProviderError: "+firebaseLoginError);

    }

    @Override
    protected void onFirebaseLoginUserError(FirebaseLoginError firebaseLoginError) {
        Log.e(TAG, "onFirebaseLoginUserError: "+firebaseLoginError);
    }
}
