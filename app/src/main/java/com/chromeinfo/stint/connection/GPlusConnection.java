package com.chromeinfo.stint.connection;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.chromeinfo.stint.networkoperation.api.ApiResponse;
import com.chromeinfo.stint.networkoperation.api.RestClient;
import com.chromeinfo.stint.utils.Utils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 9/5/17.
 */

public class GPlusConnection implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleSignInOptions gso;

    private AppCompatActivity activity;
    GoogleApiClient mGoogleApiClient;


    boolean isNew;

    public GPlusConnection() {

    }

    public GPlusConnection(AppCompatActivity activity) {
        this.activity = activity;
    }

    public GoogleApiClient gPlusInitilize() {

        try {
            gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            mGoogleApiClient = new GoogleApiClient.Builder(activity)
                    .enableAutoManage(activity, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();

        } catch (Exception e) {

        }

        return mGoogleApiClient;
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Utils.commonDialog(activity, "" + connectionResult.getErrorMessage());
    }

    public void signOut(GoogleApiClient mGoogleApiClient) {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Utils.logDebug(GPlusConnection.class.getSimpleName(), "" + status.getStatusMessage());
                    }
                });
    }

    public boolean getInstance(String email) {


        Call<ApiResponse> apiResponseCall = RestClient.getService().facebookLogin(email, null, null, "android");
        apiResponseCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                Utils.logDebug(GPlusConnection.class.getSimpleName(), "in get Instance" + response.body().errorMessage);

                isNew = response.body().isNew;

            }


            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
        return isNew;
    }

}

