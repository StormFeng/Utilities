package com.nof.utilities;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

import com.nof.utilities.activity.AnimActivity;
import com.nof.utilities.activity.BezierActivity;
import com.nof.utilities.activity.StarsMenuActivity;
import com.nof.utilities.utils.FileUtil;
import com.nof.utilities.activity.HttpActivity;
import com.nof.utilities.fragment.ActivityFragment;

/**
 * @author W
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnHttp:
                startActivity(new Intent(this, HttpActivity.class));
                break;
            case R.id.btnDownload:
                String url = "http://dldir1.qq.com/weixin/android/weixin6523android1180.apk";
                FileUtil.getInstance().downLoadFile(this,url);
                break;
            case R.id.btnFragment:
                startActivity(new Intent(this, ActivityFragment.class));
                break;
            case R.id.btnAnimLeftToRight:
                startActivity(new Intent(this, AnimActivity.class));
                break;
            case R.id.btnStarsMenu:
                startActivity(new Intent(this, StarsMenuActivity.class));
                break;
            case R.id.btnBezier:
                startActivity(new Intent(this, BezierActivity.class));
                break;
            default:
                break;
        }
    }
}
