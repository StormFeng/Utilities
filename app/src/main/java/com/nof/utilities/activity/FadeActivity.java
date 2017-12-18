package com.nof.utilities.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeScroll;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import com.nof.utilities.R;

/**
 * Created by Administrator on 2017/12/15.
 */

public class FadeActivity extends Activity implements View.OnClickListener {

    private ViewGroup mRootView;
    private View mBlackBox, mRedBox, mBlueBox, mOrangeBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade);
        mRootView = findViewById(R.id.root_layout);
        mBlackBox = findViewById(R.id.black_box);
        mRedBox = findViewById(R.id.red_box);
        mBlueBox = findViewById(R.id.blue_box);
        mOrangeBox = findViewById(R.id.orange_box);

        mRootView.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        TransitionManager.beginDelayedTransition(mRootView, new Explode());
        toggleVisibility(mBlackBox, mRedBox, mBlueBox, mOrangeBox);
    }

    private static void toggleVisibility(View...views){
        for (View view:views){
            boolean isVisible = view.getVisibility() == View.VISIBLE;
            view.setVisibility(isVisible ? View.INVISIBLE : View.VISIBLE);
        }
    }
}
