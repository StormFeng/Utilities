package com.nof.utilities.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Administrator on 2018/1/3.
 */

public class Poster extends Handler{
    private static Poster instance;
    public static Poster getInstance(){
        if(instance == null){
            synchronized (Poster.class){
                if(instance == null){
                    instance = new Poster();
                }
            }
        }
        return instance;
    }

    private Poster(){
        super(Looper.getMainLooper());
    }
}
