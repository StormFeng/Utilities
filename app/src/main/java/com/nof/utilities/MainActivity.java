package com.nof.utilities;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.nof.utilities.Utils.FileUtil;
import com.nof.utilities.activity.FadeActivity;
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
            default:
                break;
        }
    }


}
