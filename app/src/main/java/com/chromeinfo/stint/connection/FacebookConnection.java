package com.chromeinfo.stint.connection;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.chromeinfo.stint.models.facebook.FbUserDetails;
import com.chromeinfo.stint.networkoperation.api.ApiResponse;
import com.chromeinfo.stint.networkoperation.api.RestClient;
import com.chromeinfo.stint.networkoperation.callbacks.ICallbackNetwork;
import com.chromeinfo.stint.utils.SharedPreference;
import com.chromeinfo.stint.utils.Utils;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 3/5/17.
 */

/*Class is used for Facebook connections */

public class FacebookConnection {

    private Activity mActivity;

    private CallbackManager callbackManager;


    public FacebookConnection(Activity activity) {

        this.mActivity = activity;
        iFaceBookCall = (ICallbackNetwork) activity;
    }

    private static String EXPIRES = "expires_in";

    public static AccessToken TOKEN = null;

    private static final String TAG = FacebookConnection.class.getSimpleName();

    private String email;

    private FbUserDetails fbDetails;

    private ICallbackNetwork iFaceBookCall;

    /*Method is used as callback Of facebook*/

    public CallbackManager callBack() {

        callbackManager = CallbackManager.Factory.create();

        return callbackManager;
    }

    /*Method is usd to set the permissions */
    public void loginPermission() {
        LoginManager.getInstance().logInWithReadPermissions(mActivity, Arrays.asList("public_profile", "email"));
    }

    public void getInstance() {

        LoginManager.getInstance().

                registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {

                                TOKEN = loginResult.getAccessToken();
                                EXPIRES = loginResult.getAccessToken().getExpires().toString();
                                loginResult.getAccessToken().getUserId();

                                Utils.logDebug("FacebookConnection", EXPIRES);
                                Utils.logDebug("FacebookConnection", loginResult.getAccessToken().getUserId());

                                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {

                                        Utils.logDebug(TAG, "facebook Responce" + response.toString());

                                        email = response.getJSONObject().optString("email");

                                        Utils.logDebug("FacebookConnection", response.toString());

                                        Utils.logDebug("FacebookConnection", "email ID" + email);
                                        if (email != null) {
                                            facebookLoginApi(email);

                                            fbDetails = new FbUserDetails(response.getJSONObject().optString("id"), response.getJSONObject().optString("name"), response.getJSONObject().optString("email"));

                                            iFaceBookCall.userInfoFb(fbDetails);
                                            Utils.logDebug(TAG, "" + fbDetails.getEmail());
                                        }
                                    }


                                });
                                Bundle parameters = new Bundle();
                                parameters.putString("fields", "id,name,email");
                                graphRequest.setParameters(parameters);
                                graphRequest.executeAsync();
                                graphRequest.executeAsync();

                            }

                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onError(FacebookException error) {

                            }
                        }

                );

    }

    private void facebookLoginApi(String email) {

        Call<ApiResponse> apiResponseCall = RestClient.getService().facebookLogin(email, "", "", "android");
        apiResponseCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().error > 0) {
                    Utils.logDebug(TAG, "In facebook login Api" + response.body().errorMessage);

                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }
}
