package com.chromeinfo.stint.ui.activities.tutorial;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.baseapp.BaseAppActivity;
import com.chromeinfo.stint.ui.fragment.tutorial.TutorialSlideFour;
import com.chromeinfo.stint.ui.fragment.tutorial.TutorialSlideOne;
import com.chromeinfo.stint.ui.fragment.tutorial.TutorialSlideThree;
import com.chromeinfo.stint.ui.fragment.tutorial.TutorialSlideTwo;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class TutorialActivity extends BaseAppActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        setViewsReferences();
        addFragment();
        setAdaptor();
    }

    private void addFragment() {
        fragments = new ArrayList<>();
        fragments.add(TutorialSlideOne.newInstance("", ""));
        fragments.add(TutorialSlideTwo.newInstance("", ""));
        fragments.add(TutorialSlideThree.newInstance("", ""));
        fragments.add(TutorialSlideFour.newInstance("", ""));

    }

    private void setAdaptor() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        circlePageIndicator.setViewPager(mViewPager);

    }

    @Override
    public void setViewsReferences() {
        mViewPager = (ViewPager) findViewById(R.id.container);
    }

    @Override
    public void setViewsListeners() {

    }

    @Override
    public void initilizer() {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {


            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }


    }
}
