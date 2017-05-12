package com.chromeinfo.stint.ui.activities.BrowseJobActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.baseapp.AppBaseActivity;
import com.chromeinfo.stint.baseapp.BaseAppActionActivity;
import com.chromeinfo.stint.baseapp.BaseAppActivity;
import com.chromeinfo.stint.helper.UserSessionManager;
import com.chromeinfo.stint.models.register.UserLocation;
import com.chromeinfo.stint.networkoperation.api.ApiResponse;
import com.chromeinfo.stint.networkoperation.api.RestClient;
import com.chromeinfo.stint.utils.SharedPreference;
import com.chromeinfo.stint.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseJobActivity extends BaseAppActionActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private ImageView imvSideNavHeader;


    private static final String TAG = BrowseJobActivity.class.getSimpleName();

    private String mUserId;

    private UserLocation userLocationObj;

    private ListView listViewBrowseJob;

    private TextView tvEnableLocService, tvUserLstLocService, tvUpdateLocManully;

    UserSessionManager userSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_job);
        Utils.logDebug(BrowseJobActivity.class.getSimpleName(), "Browse");
        initilizer();
        setViewsReferences();
        setViewsListeners();
        getUserEmail();
        getUserLocationData();
        apiCallRegistration();
        checkUserLocation();
    }

    private void getUserEmail() {

    }

    private void checkUserLocation() {
        if (userLocationObj.getLocationName().equalsIgnoreCase("")) {
            Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.fragment_alert_ui);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.show();
            Utils.logDebug(TAG, "location is" + userLocationObj.getLocationName());
        } else {

            Utils.logDebug(TAG, "User location is " + userLocationObj.getLocationName());

            apiCallLocation();
        }
    }

    private void apiCallLocation() {
        Call<ApiResponse> apiResponseCall = RestClient.getService().setUserLocation(userLocationObj.getUserId(), userLocationObj.getLat(), userLocationObj.getLng(), userLocationObj.getLocationName()
                , userLocationObj.getCountry(),
                userLocationObj.getState());
        apiResponseCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Utils.logDebug(TAG,"Api res"+response.body().toString());
                Utils.logDebug(TAG,"Api res"+response.body().error);
                Utils.logDebug(TAG,"Api res"+response.body().errorMessage);

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });

    }


    private void apiCallRegistration() {
        if (userLocationObj != null) {
            Call<ApiResponse> apiResponseCall = RestClient.getService().browseJob(userLocationObj.getUserId());
            apiResponseCall.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.body().error > 0) {

                    } else {

                        Utils.logDebug(TAG, response.body().jobList.toString());
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {

                }
            });
        }

    }

    /*Method is use to get the userLocation information*/
    private void getUserLocationData() {

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            UserLocation userData = bundle.getParcelable("userRegData");
            mUserId = userData.getUserId();
            Utils.logDebug(TAG, "" + mUserId);
        } else {
            Gson gson = new Gson();
            SharedPreferences settings = new SharedPreference().retrieveObj(this, getResources().getString(R.string.user_location_pref), String.valueOf(getResources().getInteger(R.integer.user_preference_mode)));

            String json = settings.getString("MyObject", "");
            userLocationObj = gson.fromJson(json, UserLocation.class);
            if (userLocationObj != null) {
                Utils.logDebug(TAG, "" + userLocationObj.getUserId());
            }
        }

    }


    @Override
    public void setViewsReferences() {
        //  imvSideNavHeader = (ImageView) findViewById(R.id.imvSideNavHeader);
        listViewBrowseJob = (ListView) findViewById(R.id.listViewBrowseJob);
        tvEnableLocService = (TextView) findViewById(R.id.tvEnableLocService);
        tvUpdateLocManully = (TextView) findViewById(R.id.tvUpdateLocManully);
        tvUserLstLocService = (TextView) findViewById(R.id.tvUserLstLocService);
    }

    @Override
    public void setViewsListeners() {
        //imvSideNavHeader.setOnClickListener(this);
    }

    @Override
    public void initilizer() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imvSideNavHeader:
                break;
            default:
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.map) {
        } else if (id == R.id.post_job) {

        } else if (id == R.id.browse_job) {

        } else if (id == R.id.signout) {

        } else if (id == R.id.category) {

        } else if (id == R.id.history) {

        } else if (id == R.id.account) {

        } else if (id == R.id.active_job) {

        }
        return true;
    }

    @Override
    protected String setToolbarTitle() {
        return getResources().getString(R.string.browse_job_nav);
    }
}

