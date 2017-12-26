package com.nof.utilities.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nof.utilities.utils.HttpUtil;
import com.nof.utilities.R;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/14.
 */

public class HttpActivity extends Activity {

    private final int GET = 0;
    private final int POST = 1;
    private final String BASEURL = "http://192.168.6.118:8080/login";
    private Handler mHandler;
    private Map<String, String> params = new HashMap<>();

    private TextView mTvResult;
    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        params.put("key","6137d567e4bb6d3f07f87c2d088f2b7b");
        mProgressbar = findViewById(R.id.progressbar);
        mTvResult = findViewById(R.id.tvResult);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                    String result = (String) msg.obj;
                    switch (msg.what){
                        case GET:
                            result = "GET 请求测试\n"+result;
                            break;
                        case POST:
                            result = "POST 请求测试\n"+result;
                            break;
                    }
                    mTvResult.setText(result);
            }
        };
    }

    public void getData(View view){
        switch (view.getId()){
            case R.id.btnGet:
                new NetTask().execute();
                break;
            case R.id.btnPost:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String result = HttpUtil.post(BASEURL,params);
                        Message msg = mHandler.obtainMessage(POST,result);
                        msg.sendToTarget();
                    }
                }).start();
                break;
        }
    }

    class NetTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strs) {
            return HttpUtil.get(BASEURL,null);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mProgressbar.setVisibility(View.GONE);
            mTvResult.setText(s);
        }
    }
}
