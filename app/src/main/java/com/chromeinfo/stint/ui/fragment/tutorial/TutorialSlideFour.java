package com.chromeinfo.stint.ui.fragment.tutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chromeinfo.stint.R;


public class TutorialSlideFour extends Fragment {


    // TODO: Rename and change types and number of parameters
    public static TutorialSlideFour newInstance(String param1, String param2) {
        TutorialSlideFour fragment = new TutorialSlideFour();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorial_slide_four, container, false);
    }

}
