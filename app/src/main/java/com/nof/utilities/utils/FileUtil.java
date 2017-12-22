package com.nof.utilities.utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;


/**
 * Created by Administrator on 2017/12/14.
 */

public class FileUtil {

    private static final String SP_NAME = "DOWNLOAD_TEMP";
    private static final String DOWNLOADID = "currDownLoadId";
    private static FileUtil mFileUtil;

    public static FileUtil getInstance(){
        if(mFileUtil==null){
            mFileUtil = new FileUtil();
        }
        return mFileUtil;
    }


    public void downLoadFile(Context context, String url){
        CompleteReceiver receiver = new CompleteReceiver();
        context.registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        context.registerReceiver(receiver, new IntentFilter(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS));
        DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if(getParam(context, DOWNLOADID, 0L) != null){
            long queryId = (long) getParam(context, DOWNLOADID, 0L);
            DownloadManager.Query query = new DownloadManager.Query().setFilterById(queryId);
            Cursor c = dm.query(query);
            if(c!=null && c.moveToFirst()){
                Toast.makeText(context, "任务正在下载中...", Toast.LENGTH_SHORT).show();
            }else{
                Uri uri = Uri.parse(url);
                DownloadManager.Request req = new DownloadManager.Request(uri);
                req.setAllowedOverRoaming(true);
                /**
                 * 应用私有位置，默认情况下这个文件夹只有当前app有访问权限
                 * Android/data/data/your_package/
                 * File fileDir = context.getExternalFilesDir("TestDownload");
                 * LogUtils.e(fileDir);
                 */
                req.setDestinationInExternalFilesDir(context,"TestDownload", "WeChat.apk");
                req.setTitle("正在下载");
                req.setDescription("微信.apk");
                req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                long downLoadId = dm.enqueue(req);
                saveParams(context, DOWNLOADID, downLoadId);
            }
        }
    }

    class CompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            long queryId = (long) getParam(context,DOWNLOADID,0L);
            if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())){
                DownloadManager.Query query = new DownloadManager.Query().setFilterById(queryId);
                Cursor c = dm.query(query);
                if (c.moveToFirst()) {
                    int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    switch (status) {
                        case DownloadManager.STATUS_PAUSED:
                            System.out.println("下载暂停");
                        case DownloadManager.STATUS_PENDING:
                            System.out.println("下载等待");
                        case DownloadManager.STATUS_RUNNING:
                            System.out.println("正在下载");
                            break;
                        case DownloadManager.STATUS_SUCCESSFUL:
                            System.out.println("下载成功");
                            break;
                        case DownloadManager.STATUS_FAILED:
                            System.out.println("下载失败");
                            break;
                    }
                }else{
                    System.out.println("下载取消");
                }
            }
            removeParam(context, DOWNLOADID);
        }
    }

    public void saveParams(Context context,String key, Object object){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String type = object.getClass().getSimpleName();
        if("String".equals(type)){
            editor.putString(key, (String)object);
        }else if("Integer".equals(type)){
            editor.putInt(key, (Integer) object);
        }else if("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean)object);
        }else if("Float".equals(type)){
            editor.putFloat(key, (Float)object);
        }else if("Long".equals(type)){
            editor.putLong(key, (Long)object);
        }else{
            System.out.println("Data type error!!!");
        }
        editor.commit();
    }

    public Object getParam(Context context, String key, Object defaultObject){
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        if("String".equals(type)){
            return sp.getString(key, (String)defaultObject);
        } else if("Integer".equals(type)){
            return sp.getInt(key, (Integer)defaultObject);
        } else if("Boolean".equals(type)){
            return sp.getBoolean(key, (Boolean)defaultObject);
        } else if("Float".equals(type)){
            return sp.getFloat(key, (Float)defaultObject);
        } else if("Long".equals(type)){
            return sp.getLong(key, (Long)defaultObject);
        }
        return null;
    }

    public void removeParam(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }
}
