package com.chromeinfo.stint.ui.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.baseapp.BaseAppActivity;
import com.chromeinfo.stint.modles.register.User;
import com.chromeinfo.stint.networkoperation.callbacks.RestClient;
import com.chromeinfo.stint.ui.activities.forgotpass.ForgotPasswordAct;
import com.chromeinfo.stint.ui.activities.registration.RegistrationAct;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseAppActivity implements View.OnClickListener {

    private Button btnLogin, btnRegister = null;

    private TextView tvForgotPassword;

    private EditText edtEmail, edtPassword;

    private String mUserEmailId, mUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setViewsReferences();
        setViewsListeners();
    }

    @Override
    public void setViewsReferences() {
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
    }

    @Override
    public void setViewsListeners() {
        btnRegister.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void initilizer() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnRegister:
                startActivity(new Intent(LoginActivity.this, RegistrationAct.class));
                break;
            case R.id.tvForgotPassword:
                startActivity(new Intent(LoginActivity.this, ForgotPasswordAct.class));
                break;
            case R.id.btnLogin:
                getUserDetails();
                getLoginInfo();
              /*
                if (ConnectionCheck.isOnline(this)) {
                    startActivity(new Intent(LoginActivity.this, TutorialActivity.class));
                } else
                    Utils.noInternetDailog(this);
              */
                break;
            default:

        }
    }

    private void getLoginInfo() {
        Call<User> userCall = RestClient.getService().userLogin(mUserEmailId, mUserPassword, "0", "0", "android");
userCall.enqueue(new Callback<User>() {
    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if(response.isSuccessful()) {
            Log.e("RegistrationAct", "" + call.toString());
            Log.e("RegistrationAct", "" + response.body());
            Log.e("RegistrationAct", "" + response.code());

        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Log.e("RegistrationAct", "" + call.toString());
        t.printStackTrace();
    }
});
    }


    private void getUserDetails() {
        mUserEmailId = edtEmail.getText().toString();
        mUserPassword = edtPassword.getText().toString();
    }


}

