package com.chromeinfo.stint.ui.activities.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.baseapp.BaseAppActivity;

import com.chromeinfo.stint.connection.FacebookConnection;
import com.chromeinfo.stint.modles.register.User;
import com.chromeinfo.stint.networkoperation.ApiResponse;
import com.chromeinfo.stint.networkoperation.RestClient;
import com.chromeinfo.stint.ui.activities.forgotpass.ForgotPasswordAct;
import com.chromeinfo.stint.ui.activities.registration.RegistrationAct;
import com.chromeinfo.stint.utils.SharedPreference;
import com.chromeinfo.stint.utils.Utils;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class is used for login purpose  . This class is responsible for gathering the user information such as email
 * and password , after that it verifies the credentials of user
 */

public class LoginActivity extends BaseAppActivity implements View.OnClickListener {

    private Button btnLogin, btnRegister, btnFacebookLogin = null;

    private TextView tvForgotPassword;

    private EditText edtEmail, edtPassword;

    private String mUserEmailId, mUserPassword;

    private ProgressDialog progressDialog;

    private User userInfo;

    private CallbackManager callbackManager;

    private SharedPreference sharedPreference;

    private static final String TAG = LoginActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FacebookSdk.sdkInitialize(this);
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
        btnFacebookLogin = (Button) findViewById(R.id.btnFacebookLogin);
    }

    @Override
    public void setViewsListeners() {
        btnRegister.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnFacebookLogin.setOnClickListener(this);
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

                sharedPreference = new SharedPreference();
                new LoginAsync().execute();
              /*
                if (ConnectionCheck.isOnline(this)) {
                    startActivity(new Intent(LoginActivity.this, TutorialActivity.class));
                } else
                    Utils.noInternetDailog(this);
              */


                break;
            case R.id.btnFacebookLogin:
                getFacebookLogin();
            default:

        }
    }

    private void getFacebookLogin() {

        if (FacebookConnection.TOKEN == null) {
            FacebookConnection facebookConnection = new FacebookConnection(this);
            facebookConnection.loginPermission();
            callbackManager = facebookConnection.callBack();
            facebookConnection.getInstance();

        }
    }

    private void getLoginInfo() {

        final Call<ApiResponse> userCall = RestClient.getService().userLogin(mUserEmailId, mUserPassword, "", "", "android");
        userCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                ApiResponse apiResponse = response.body();

                if (response.isSuccessful()) {
                    Log.e(TAG, "" + response.body());
                    if (apiResponse.error > 0) {

                        progressDialog.dismiss();
                        progressDialog = null;

                        Utils.commonDialog(LoginActivity.this, apiResponse.errorMessage);
                    } else {

                        progressDialog.dismiss();

                        Utils.commonDialog(LoginActivity.this, getResources().getString(R.string.login_successful));
                        userInfo = apiResponse.user;
                        sharedPreference.save(LoginActivity.this, apiResponse.user.getUserName(), getResources().getString(R.string.user_pref_name), String.valueOf(getResources().getInteger(R.integer.user_preference_mode)));
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Utils.logDebug(TAG, "In OnResponse : Fail ");

                Utils.logDebug(TAG, "In OnResponse : not login ");
                t.printStackTrace();
                Utils.commonDialog(LoginActivity.this, getResources().getString(R.string.check_network_error));
            }
        });


    }


    private void getUserDetails() {
        mUserEmailId = edtEmail.getText().toString();
        mUserPassword = edtPassword.getText().toString();
    }

    private class LoginAsync extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage(getResources().getString(R.string.progress_dialog_login_message));
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getLoginInfo();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (progressDialog != null) {
                progressDialog.dismiss();
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

