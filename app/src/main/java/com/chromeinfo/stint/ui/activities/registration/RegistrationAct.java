package com.chromeinfo.stint.ui.activities.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.baseapp.BaseAppActivity;
import com.chromeinfo.stint.helper.UserSessionManager;
import com.chromeinfo.stint.models.facebook.FbUserDetails;
import com.chromeinfo.stint.networkoperation.api.ApiResponse;
import com.chromeinfo.stint.networkoperation.api.RestClient;
import com.chromeinfo.stint.ui.activities.BrowseJobActivity.BrowseJobActivity;
import com.chromeinfo.stint.ui.activities.login.LoginActivity;
import com.chromeinfo.stint.utils.Constants;
import com.chromeinfo.stint.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationAct extends BaseAppActivity implements View.OnClickListener {

    /**
     * Widget Initialization start
     */
    private EditText edtRegFirstName, edtRegLastName,
            edtRegEmail, edtRegPhNo, edtRegUserName, edtRegPasswd, edtRegConfrmPasswd = null;

    private Button btnRegDone = null;

    /* Widget Initialization End */

    private String mFirstName, mLastName, mEmail, mPhNo, mUserName, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setViewsReferences();
        setViewsListeners();
        if (getIntent().getExtras() != null) {
            if (getFbDataFromBundle()) {
                setDataIntoViews();

            } else {
                if (getGplusData()) {
                    setDataIntoViews();
                }
            }
        }
    }


    private boolean getGplusData() {
        Bundle b = getIntent().getExtras();
        mEmail = b.getString("email");
        mUserName = b.getString("userName");
        return true;
    }

    private void setDataIntoViews() {
        edtRegEmail.setText(mEmail);
        if (mUserName != null) {
            String[] value = mUserName.split(" ");
            edtRegFirstName.setText(value[0]);
            edtRegLastName.setText(value[1]);
        }
    }

    private boolean getFbDataFromBundle() {

        FbUserDetails fb = getIntent().getExtras().getParcelable("userFbInfo");
        if (fb != null) {
            mEmail = fb.getEmail();
            mUserName = fb.getName();
            edtRegEmail.setText(mEmail);
            Utils.logDebug("RegistrationAct", "" + mEmail + " " + mUserName);
            return true;
        } else return false;
    }

    private void getUserInfo() {
        mFirstName = edtRegFirstName.getText().toString();
        mLastName = edtRegLastName.getText().toString();
        mEmail = edtRegEmail.getText().toString();
        mUserName = edtRegUserName.getText().toString();
        mPassword = edtRegPasswd.getText().toString();
        mPhNo = edtRegPhNo.getText().toString();
    }

    @Override
    public void setViewsReferences() {

        edtRegFirstName = (EditText) findViewById(R.id.edtRegFirstName);

        edtRegLastName = (EditText) findViewById(R.id.edtRegLastName);

        edtRegEmail = (EditText) findViewById(R.id.edtRegEmail);

        edtRegPhNo = (EditText) findViewById(R.id.edtRegPhNo);

        edtRegUserName = (EditText) findViewById(R.id.edtRegUserName);

        edtRegPasswd = (EditText) findViewById(R.id.edtRegPasswd);

        edtRegConfrmPasswd = (EditText) findViewById(R.id.edtRegConfrmPasswd);

        btnRegDone = (Button) findViewById(R.id.btnRegDone);

    }

    @Override
    public void setViewsListeners() {
        btnRegDone.setOnClickListener(this);
    }

    @Override
    public void initilizer() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnRegDone:
                getUserInfo();
                if (userValidation()) {
                    registrationProcess();
                }
                break;
            default:
                break;
        }
    }

    private boolean userValidation() {
        if (edtRegFirstName.getText().toString().length() <= 0) {
            Utils.commonDialog(this, "first name should not be blank");
        }

        return false;
    }

    private void registrationProcess() {
        final UserSessionManager userSessionManager = new UserSessionManager(RegistrationAct.this);
        Call<ApiResponse> uCall = RestClient.getService().setUserRegistration(mEmail, mFirstName, mLastName, mUserName, mPhNo, mPassword, "", "", Constants.DEVICE_TYPE);
        uCall.enqueue(new Callback<ApiResponse>() {
                          @Override
                          public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                              if (response.body().error > 0) {
                                  Utils.createLongToast(getApplicationContext(), response.body().errorMessage);
                                  startActivity(new Intent(RegistrationAct.this, LoginActivity.class));
                                  Utils.createShortToast(getApplicationContext(), response.body().errorMessage);
                              } else {
                                  userSessionManager.createUserLoginSession(mEmail, mUserName);
                                  Intent intent = new Intent(RegistrationAct.this, BrowseJobActivity.class);
                                  startActivity(intent);
                              }
                          }

                          @Override
                          public void onFailure(Call<ApiResponse> call, Throwable t) {

                          }
                      }

        );


    }

}