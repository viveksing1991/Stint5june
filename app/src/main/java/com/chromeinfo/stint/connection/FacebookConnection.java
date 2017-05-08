package com.chromeinfo.stint.connection;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.chromeinfo.stint.R;
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
import java.util.StringTokenizer;

import retrofit2.Call;

/**
 * Created by root on 3/5/17.
 */

/*Class is used for Facebook connections */

public class FacebookConnection {
    private Activity mActivity;
    private Context mContext;
    private String mMessage;
    private ProgressBar mProgressBar;
    private Bitmap mScreen;

    private SharedPreference sharedPreference;

    private CallbackManager callbackManager;


   /* public FacebookConnection(Activity activity, Context context, String text, ProgressBar progressBar, Bitmap bmp) {
        this.mMessage = "";
        this.mContext = context;
        this.mActivity = activity;
        this.mProgressBar = progressBar;
        this.mScreen = bmp;
        this.mMessage = text;
    }*/

    public FacebookConnection(Activity activity) {

        this.mActivity = activity;
    }

    private static String APP_ID = null;

    private static String EXPIRES = "expires_in";

    public static AccessToken TOKEN = null;

    private static final String KEY = "facebook_credantials";

    private static String permissions[];

    private String email;

    static {
        APP_ID = "";
        permissions = new String[]{"public_profile"};
    }


    public CallbackManager callBack() {

        callbackManager = CallbackManager.Factory.create();

        return callbackManager;
    }

    public void loginPermission() {
        LoginManager.getInstance().logInWithReadPermissions(mActivity, Arrays.asList("public_profile", "user_friends", "email"));
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
                                        email = response.getJSONObject().optString("email");
                                        Utils.logDebug("FacebookConnection", response.toString());

                                        Utils.logDebug("FacebookConnection", "email ID" + email);
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
}
