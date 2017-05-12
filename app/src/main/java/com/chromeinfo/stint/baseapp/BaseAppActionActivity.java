package com.chromeinfo.stint.baseapp;

import android.support.annotation.LayoutRes;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.adaptors.NavListAdaptor;
import com.chromeinfo.stint.models.NavListModel;

/**
 * Created by root on 1/5/17.
 */
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*Class with Navigtion drawer android */

public abstract class BaseAppActionActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout fullView;

    private Toolbar toolbar;

    private TextView tvToolbar;

    protected NavigationView navigationView;

    protected FrameLayout activityContainer;

    private ListView listView;

    private List<NavListModel> navListModelList = new ArrayList<>();

    public abstract void setViewsReferences();  /* This method is used to set the references of views*/

    public abstract void setViewsListeners();  /*This method is used to set the listeners on views*/

    public abstract void initilizer();


    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);

        activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);

        getLayoutInflater().inflate(layoutResID, activityContainer, true);

        super.setContentView(fullView);

        navigationView = (NavigationView) findViewById(R.id.navigationView);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        tvToolbar = (TextView) findViewById(R.id.tvToolbar);

        listView = (ListView) findViewById(R.id.listView);

        tvToolbar.setText(setToolbarTitle());

        navigationView.setNavigationItemSelectedListener(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");

        getSupportActionBar().setDisplayShowTitleEnabled(true);

        toolbar.setNavigationIcon(R.drawable.menu_icon_new);

        LayoutInflater inflater = getLayoutInflater();
        View listHeaderView = inflater.inflate(R.layout.nav_drawer_header, null, false);
        //  listView.addHeaderView(listHeaderView);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fullView.openDrawer(Gravity.LEFT);
            }
        });
        addItem();
        setAdaptor();
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
