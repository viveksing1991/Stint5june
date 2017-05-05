package com.chromeinfo.stint.ui.activities.registration;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.baseapp.BaseAppActivity;
import com.chromeinfo.stint.modles.register.User;
import com.chromeinfo.stint.networkoperation.callbacks.RestClient;
import com.chromeinfo.stint.utils.Constants;
import com.chromeinfo.stint.utils.Url;
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
    }

    private User setUserInfo(String mEmail, String mFirstName, String mLastName, String mPhNo, String mUserName, String mPassword) {

        return new User(mEmail, mFirstName, mLastName, mUserName, mPhNo, mPassword, Constants.DEVICE_TYPE);
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
                registrationProcess();
                break;
            default:
                break;
        }
    }

    private void registrationProcess() {

        Call<User> uCall = (Call<User>) RestClient.getService().setUserRegistration(mEmail, mFirstName, mLastName, mUserName, mPhNo, mPassword, "0", "0", "android");
        uCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }

}