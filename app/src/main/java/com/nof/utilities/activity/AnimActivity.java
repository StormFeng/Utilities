package com.nof.utilities.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.nof.utilities.R;
import com.nof.utilities.view.ClockView;

/**
 * Created by Administrator on 2017/12/22.
 */

public class AnimActivity extends Activity {
    ClockView view;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        view = findViewById(R.id.clock_view);
        view.post(new Runnable() {
            @Override
            public void run() {
                view.setPivotX(view.getWidth()/2);
                view.setPivotY(0f);
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
                animator.setRepeatCount(-1);
                animator.setDuration(1000);
                animator.start();
            }
        });
    }
}
