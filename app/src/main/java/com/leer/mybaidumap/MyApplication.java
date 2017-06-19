package com.leer.mybaidumap;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by Leer on 2017/6/17.
 */

public class MyApplication extends Application {
    private static Handler mHandler;
    private static Context mContext;
    private static int mMainThreadID;

    @Override
    public void onCreate() {
        super.onCreate();

        mHandler = new Handler();
        mContext = getApplicationContext();
        mMainThreadID = android.os.Process.myTid();

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static Context getContext() {
        return mContext;
    }

    public static int getMainThreadID() {
        return mMainThreadID;
    }
}
