package com.chromeinfo.stint.ui.activities.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.ui.activities.login.LoginActivity;

import static com.chromeinfo.stint.utils.Constants.SPLASH_TIME_OUT;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moveToLogin();
    }

    private void moveToLogin() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
