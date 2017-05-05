package com.chromeinfo.stint.ui.activities.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.ui.activities.login.LoginActivity;
import com.chromeinfo.stint.utils.Utils;

import static com.chromeinfo.stint.utils.Constants.SPLASH_TIME_OUT;


public class SplashAct extends AppCompatActivity {

    /*  Datamember Initilization */

    private static final String TAG = SplashAct.class.getSimpleName();

    /* Data MemberInitilazation End */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new ActivityStarter().start();
    }


    private class ActivityStarter extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(SPLASH_TIME_OUT);
            } catch (InterruptedException e) {
                Utils.logDebug(TAG, "Splash Activity" + " In run ");
            }
            startActivity(new Intent(SplashAct.this, LoginActivity.class));
            finish();
        }
    }

}
