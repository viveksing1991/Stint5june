package com.chromeinfo.stint.baseapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.models.NavListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 12/5/17.
 */

public abstract class AppBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public FrameLayout activityContent; //This is the framelayout to keep your content view

    private NavigationView navigationView; // The new navigation view from Android Design Library. Can inflate menu resources. Easy

    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    private Toolbar toolbar;

    private TextView tvToolbar;

    private List<NavListModel> navListModelList = new ArrayList<>();

    public abstract void setViewsReferences();  /* This method is used to set the references of views*/

    public abstract void setViewsListeners();  /*This method is used to set the listeners on views*/

    public abstract void initilizer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);// The base layout that contains your navigation drawer.
        setReferences();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        navigationView.setNavigationItemSelectedListener(this);

        setSupportActionBar(toolbar);

        tvToolbar.setText(setToolbarTitle());

        getSupportActionBar().setTitle("");

        getSupportActionBar().setDisplayShowTitleEnabled(true);

        toolbar.setNavigationIcon(R.drawable.menu_icon_new);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        addItem();
        setAdaptor();
    }
    // and so on...

    private void setReferences() {
        activityContent = (FrameLayout) findViewById(R.id.activity_content);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_drawer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvToolbar = (TextView) findViewById(R.id.tvToolbar);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /* Override all setContentView methods to put the content view to the FrameLayout view_stub
     * so that, we can make other activity implementations looks like normal activity subclasses.
     */
    @Override
    public void setContentView(int layoutResID) {
        if (activityContent != null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            View stubView = inflater.inflate(layoutResID, activityContent, false);
            activityContent.addView(stubView, lp);
        }
    }

    @Override
    public void setContentView(View view) {
        if (activityContent != null) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            activityContent.addView(view, lp);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (activityContent != null) {
            activityContent.addView(view, params);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    private void addItem() {

        navListModelList.add(new NavListModel(getResources().getString(R.string.browse_job_nav), R.drawable.setting_icon));
    }


    private void setAdaptor() {

        //  listView.setAdapter(new NavListAdaptor(this, navListModelList));
    }


    protected String setToolbarTitle() {
        return "browseJob";
    }

}