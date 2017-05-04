package com.chromeinfo.stint.ui.fragment.tutorial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chromeinfo.stint.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TutorialSlideOne#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TutorialSlideOne extends Fragment {


    public static TutorialSlideOne newInstance(String param1, String param2) {
        TutorialSlideOne fragment = new TutorialSlideOne();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorial_slide_one, container, false);
    }

}
