package com.nof.utilities.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.nof.utilities.R;
import com.nof.utilities.utils.ThreadUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2018/1/3.
 */

public class UploadImgActivity extends Activity {

    private static final String URL = "http://192.168.6.118:8080/register";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimg);
    }

    private void readServerData(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((len=inputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
        }
        outputStream.close();
        inputStream.close();
        System.out.println(new String(outputStream.toByteArray()));
    }

    private void getRequest(){
        ThreadUtils.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    executeGet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void executeGet() throws Exception {
        URL url = new URL(URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        int responseCode = urlConnection.getResponseCode();
        if(responseCode == 200){
            InputStream inputStream = urlConnection.getInputStream();
            readServerData(inputStream);
        }
    }
    private void headRequest(){

    }
    private void postRequest(){

    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_get:
                getRequest();
                break;
            case R.id.btn_head:
                headRequest();
                break;
            case R.id.btnPost:
                postRequest();
                break;
        }
    }
}
