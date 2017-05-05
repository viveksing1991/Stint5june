package com.chromeinfo.stint.ui.fragment.tutorial;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.baseapp.BaseFragment;
import com.chromeinfo.stint.ui.activities.BrowseJobActivity.BrowseJobActivity;
import com.chromeinfo.stint.ui.activities.tutorial.TutorialActivity;


public class TutorialSlideFour extends BaseFragment implements View.OnClickListener {

    private View btnTutPaypl;

    private IFragmentListener iFragmentListener;

    // TODO: Rename and change types and number of parameters
    public static TutorialSlideFour newInstance(String param1, String param2) {
        TutorialSlideFour fragment = new TutorialSlideFour();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutorial_slide_four, container, false);
        setViewsReferences(view);
        setViewsListeners();
        return view;

    }


    @Override
    public void setViewsReferences(View view) {
        btnTutPaypl = view.findViewById(R.id.btnTutPaypl);
    }

    @Override
    public void setViewsListeners() {
        btnTutPaypl.setOnClickListener(this);
    }

    @Override
    public void initilizer() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                Intent intent = new Intent(getActivity(), BrowseJobActivity.class);
                break;
            default:
                break;
        }
    }

    public interface IFragmentListener {
        public void startMyIntent(Intent i);
    }
}
