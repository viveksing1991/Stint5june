package com.chromeinfo.stint.ui.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.baseapp.BaseAppActivity;
import com.chromeinfo.stint.ui.activities.forgotpass.ForgotPasswordAct;
import com.chromeinfo.stint.ui.activities.registration.RegistrationAct;


public class MainAct extends BaseAppActivity implements View.OnClickListener {

    private Button btnLogin, btnRegister = null;

    private TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewsReferences();
        setViewsListeners();
    }

    @Override
    public void setViewsReferences() {
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
    }

    @Override
    public void setViewsListeners() {
        btnRegister.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
    }

    @Override
    public void initilizer() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnRegister:
                startActivity(new Intent(MainAct.this, RegistrationAct.class));
                break;
            case R.id.tvForgotPassword:
                startActivity(new Intent(MainAct.this, ForgotPasswordAct.class));
                break;
            default:

        }
    }
}
