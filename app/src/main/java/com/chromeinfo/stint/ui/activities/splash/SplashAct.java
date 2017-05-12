package com.chromeinfo.stint.ui.activities.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.networkoperation.callbacks.GplusCall;
import com.chromeinfo.stint.ui.activities.BrowseJobActivity.BrowseJobActivity;
import com.chromeinfo.stint.ui.activities.login.LoginActivity;
import com.chromeinfo.stint.utils.SharedPreference;
import com.chromeinfo.stint.helper.UserSessionManager;
import com.chromeinfo.stint.utils.Utils;
import com.facebook.AccessToken;

import static com.chromeinfo.stint.utils.Constants.SPLASH_TIME_OUT;

/* Start activity of application . It shows the logo and advertisement of company */

public class SplashAct extends AppCompatActivity implements GplusCall {

    /*  Datamember Initilization */

    private static final String TAG = SplashAct.class.getSimpleName();

    /* Data MemberInitilazation End */

    private SharedPreference sharedPreference;

    boolean isUserLoggedIn = true;
    // User Session Manager Class
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        session = new UserSessionManager(getApplicationContext());
        isUserLoggedIn = session.isUserLoggedIn();
        new ActivityStarter().start();
    }

    @Override
    public void gplusCall(boolean isNew) {
        if (isNew) {
            startActivity(new Intent(SplashAct.this, BrowseJobActivity.class));
            finish();
        } else {
            startActivity(new Intent(SplashAct.this, LoginActivity.class));
            finish();
        }
    }

    /*Class is used to start an activity with some pause*/
    private class ActivityStarter extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(SPLASH_TIME_OUT);

            } catch (InterruptedException e) {
                e.printStackTrace();
                Utils.logDebug(TAG, "Splash Activity" + " In run ");
            }

            if (AccessToken.getCurrentAccessToken() != null) {
                startActivity(new Intent(SplashAct.this, BrowseJobActivity.class));
                finish();
            } else if (isUserLoggedIn) {
                startActivity(new Intent(SplashAct.this, BrowseJobActivity.class));
                finish();
            } else if (new SharedPreference().getValue(SplashAct.this, "userPref", String.valueOf(0))!=null) {
                startActivity(new Intent(SplashAct.this, BrowseJobActivity.class));
                finish();
            } else {
                startActivity(new Intent(SplashAct.this, LoginActivity.class));
                finish();
                Utils.logDebug(TAG, "Splash Activity" + "else");
            }

        }
    }

}
