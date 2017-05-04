package com.chromeinfo.stint.ui.fragment.tutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chromeinfo.stint.R;


public class TutorialSlideTwo extends Fragment {


    public static TutorialSlideTwo newInstance(String param1, String param2) {
        TutorialSlideTwo fragment = new TutorialSlideTwo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorial_slide_two, container, false);
    }


}
