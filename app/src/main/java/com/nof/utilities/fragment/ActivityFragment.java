package com.nof.utilities.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.nof.utilities.R;


public class ActivityFragment extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fragment);
    }
}
