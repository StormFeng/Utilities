package com.nof.utilities.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/1/3.
 */

public class ThreadUtils {
    private static ExecutorService sExecutorService = Executors.newSingleThreadExecutor();

    public static void execute(Runnable runnable){
        sExecutorService.execute(runnable);
    }
}
