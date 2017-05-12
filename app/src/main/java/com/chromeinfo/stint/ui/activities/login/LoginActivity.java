package com.chromeinfo.stint.ui.activities.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.baseapp.BaseAppActivity;

import com.chromeinfo.stint.connection.FacebookConnection;
import com.chromeinfo.stint.connection.GPlusConnection;
import com.chromeinfo.stint.models.register.UserLocation;
import com.chromeinfo.stint.networkoperation.callbacks.ICallbackNetwork;
import com.chromeinfo.stint.models.facebook.FbUserDetails;
import com.chromeinfo.stint.networkoperation.api.ApiResponse;
import com.chromeinfo.stint.networkoperation.api.RestClient;
import com.chromeinfo.stint.ui.activities.BrowseJobActivity.BrowseJobActivity;
import com.chromeinfo.stint.ui.activities.forgotpass.ForgotPasswordAct;
import com.chromeinfo.stint.ui.activities.registration.RegistrationAct;
import com.chromeinfo.stint.utils.SharedPreference;
import com.chromeinfo.stint.utils.Utils;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class is used for login purpose  . This class is responsible for gathering the user information such as email
 * and password , after that it verifies the credentials of user
 */

public class LoginActivity extends BaseAppActivity implements View.OnClickListener, ICallbackNetwork, GoogleApiClient.OnConnectionFailedListener {

    private Button btnLogin, btnRegister, btnFacebookLogin, btnGplusLogin = null;

    private TextView tvForgotPassword;

    private EditText edtEmail, edtPassword;

    private String mUserEmailId, mUserPassword;

    private ProgressDialog progressDialog;

    private com.chromeinfo.stint.models.register.User userInfo;

    private CallbackManager callbackManager;

    private SharedPreference sharedPreference;

    private static final String TAG = LoginActivity.class.getSimpleName();

    //Signin constant to check the activity result
    private int RC_SIGN_IN = 100;

    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInResult result;

    private GPlusConnection gPlusConnection;

    private GoogleSignInAccount acct;


    private static class DialogChecker implements DialogInterface.OnClickListener {
        DialogChecker() {
        }

        public void onClick(DialogInterface dialog, int id) {
            dialog.dismiss();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FacebookSdk.sdkInitialize(this);
        setViewsReferences();
        setViewsListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void setViewsReferences() {
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnFacebookLogin = (Button) findViewById(R.id.btnFacebookLogin);
        btnGplusLogin = (Button) findViewById(R.id.btnGplusLogin);
    }

    @Override
    public void setViewsListeners() {
        btnRegister.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnFacebookLogin.setOnClickListener(this);
        btnGplusLogin.setOnClickListener(this);
    }

    @Override
    public void initilizer() {

    }

    @Override
    public void onClick(View v) {
        boolean isNew = false;
        switch (v.getId()) {

            case R.id.btnRegister:
                startActivity(new Intent(LoginActivity.this, RegistrationAct.class));
                break;
            case R.id.tvForgotPassword:
                startActivity(new Intent(LoginActivity.this, ForgotPasswordAct.class));
                break;
            case R.id.btnLogin:

                if (!Utils.checkInternet(this)) {
                    Utils.noInternetDailog(this);
                } else if (this.edtEmail.getText().toString().length() <= 0
                        || this.edtPassword.getText().toString().length() <= 0) {

                    if (this.edtEmail.getText().length() <= 0) {
                        Utils.showDialog(this, "Login Error", "email should not be blank.");
                    } else if (this.edtEmail.getText().length() > 0 && this.edtPassword.getText().length() <= 0) {
                        Utils.showDialog(this, "Login Error", "Password should not be blank.");
                    } else if (!Utils.isValidEmail(this.edtEmail.getText().toString())) {
                        Utils.showDialog(this, "Invalid Email", "Please enter valid Email Id");
                    }
                } else {
                    sharedPreference = new SharedPreference();
                    getUserDetails();
                    new LoginAsync().execute();

                }


                break;
            case R.id.btnFacebookLogin:
                getFacebookLogin();
                break;
            case R.id.btnGplusLogin:
                if (gPlusConnection == null) {
                    gPlusConnection = new GPlusConnection(this);
                    mGoogleApiClient = gPlusConnection.gPlusInitilize();
                    if (mGoogleApiClient != null) {
                        signInGoogle();
                        if (acct != null)
                            isNew = gPlusConnection.getInstance(acct.getEmail());
                        if (isNew) {
                            startActivity(new Intent(LoginActivity.this, RegistrationAct.class));

                        }
                    }
                }
                break;
            default:

        }
    }

    private void signInGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void getFacebookLogin() {
        if (FacebookConnection.TOKEN == null) {
            FacebookConnection facebookConnection = new FacebookConnection(this);
            facebookConnection.loginPermission();
            callbackManager = facebookConnection.callBack();
            facebookConnection.getInstance();
            Utils.logDebug(TAG, "In btn get");
        }
    }

    /*Method is used for getting the user login info from server*/
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
                        sharedPreference.saveObject(LoginActivity.this, apiResponse.user.userLocation, getResources().getString(R.string.user_location_pref), String.valueOf(getResources().getInteger(R.integer.user_preference_mode)));
                        sendUserData(apiResponse.user.userLocation);
                        Utils.logDebug(TAG, apiResponse.user.userLocation.getUserId());
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

    /*Method is used to send data to second activity */
    private void sendUserData(UserLocation userInfo) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("userRegData", userInfo);
        Intent intent = new Intent(LoginActivity.this, BrowseJobActivity.class);
        startActivity(intent);

    }

    /*Method is used to get the user email and password information*/
    private void getUserDetails() {
        mUserEmailId = edtEmail.getText().toString();
        mUserPassword = edtPassword.getText().toString();
    }

    @Override
    public void userInfoFb(FbUserDetails user) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("userFbInfo", user);
        Intent intent = new Intent(LoginActivity.this, RegistrationAct.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /*Class is used to do a api  call the  server to check the user credantial*/
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

    /*Method is used to handle the google sign in result */
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            acct = result.getSignInAccount();
            Utils.logDebug(TAG, "gplus" + acct.getEmail());
            setDataIntoBundle(acct);
        } else {
            new GPlusConnection().signOut(mGoogleApiClient);
            Utils.commonDialog(getApplicationContext(), "Account Signout  ");
        }
    }

    /*This method is used to set the user data into registration activity */
    private void setDataIntoBundle(GoogleSignInAccount acct) {
        Bundle bundle = new Bundle();
        bundle.putString("email", acct.getEmail());
        bundle.putString("userName", acct.getDisplayName());
        Intent intent = new Intent(LoginActivity.this, RegistrationAct.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (callbackManager != null)
            callbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    protected void onDestroy() {
        mGoogleApiClient.disconnect();
        super.onDestroy();

    }

    public void commonDialog(Context context, String message) {
        AlertDialog.Builder al = new AlertDialog.Builder(context);
        al.setMessage(message);
        al.setPositiveButton("ok", new DialogChecker());
        al.create();
        try {
            al.show();
        } catch (Exception e) {

        }
    }
}

