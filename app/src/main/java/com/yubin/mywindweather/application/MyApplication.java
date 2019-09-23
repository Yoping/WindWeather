package com.yubin.mywindweather.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.tencent.bugly.Bugly;
import com.yubin.mywindweather.util.LogUtil;
import com.yubin.mywindweather.util.SharedPreferencesUtil;

/**
 * Created by YUBIN at 17-7-18 上午10:54
 * Last modified at 17-7-18 上午10:54
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    public static int openTimes=0;
    /**
     * 正式发布版或者Debug版
     */
    public static boolean isRelease = true;
    /**
     * 当前天气状况
     */
    public static int currentWeather = 1;
    /**
     * 是否更新天气
     */
    public static boolean updateWeather=false;
    /**
     * 是否启用硬件加速
     */
    public static boolean useHardwareSpeedUp = false;
    /**
     * 是否开启随机天气
     */
    public static boolean useRandomWeather = false;

    /**
     * 是否开启测试
     */
    public static boolean myTest=false;

    public static int waterDropSpeed=2;
    public static int windMillRotateSpeed=3;
    public static int sunRotateTime=3;
    public static int getWeatherInterval=3600*1000*2;

    /**
     * 标准天气标识符
     */
    public static final int SunnyDay = 1;
    public static final int RainyDay = 2;
    public static final int CloudyDay = 3;
    public static final int SunRainyDay = 4;
    public static final int LightShowerRainyDay = 5;
    public static final int RainstormDay = 6;
    public static final int MeteorRainyDay = 7;
    public static final int BTDay = 8;
    public static final int AnalyseDay = 9;
    public static final int SnowyDay = 10;
    public static final int BigSnowyDay = 11;
    public static final int RainSnowyDay = 12;
    public static final int MiddleSnowyDay = 13;
    public static final int FogDay = 14;
    public static final int FrostDay = 15;
    public static final int RainyShowerDay = 16;


    /**
     * 内存分析标识符
     */
    public static boolean hasGrass=false;
    public static boolean hasGrass2=false;
    public static boolean hasSky=false;
    public static boolean hasStarry=false;
    public static boolean hasWindMill=false;
    public static boolean hasRains=false;
    public static boolean hasMoon=false;
    public static boolean hasStars=false;
    public static boolean hasClouds=false;
    public static boolean hasWaters=false;
    public static boolean hasLightnings=false;
    public static boolean hasMeteor=false;
    public static boolean hasSun=false;

    public MyApplication() {
    }

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.show("Since it is learning exchanges, no charge, I will not  confuse the code, do not use it to gaoshiqing is ok.");
        instance=this;

        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass();
        LogUtil.show("heapSize="+heapSize+"MB");

        SharedPreferencesUtil.getInstance();
        currentWeather=SharedPreferencesUtil.getInt(MyConstants.currentWeather,SunnyDay);
        useHardwareSpeedUp=SharedPreferencesUtil.getBoolean(MyConstants.useHardwareSpeedUp,false);
        useRandomWeather=SharedPreferencesUtil.getBoolean(MyConstants.useRandomWeather,false);
        myTest=SharedPreferencesUtil.getBoolean(MyConstants.test,false);
        /**
         * 腾讯Bugly
         * 第三个参数为SDK调试模式开关，调试模式的行为特性如下：
         * 1.输出详细的Bugly SDK的Log
         * 2.每一条Crash都会被立即上报
         * 3.自定义日志将会在Logcat中输出
         * 建议在测试阶段建议设置成true，发布时设置为false。
         */
//        CrashReport.initCrashReport(getApplicationContext(), MyConfig.Bugly_Key, false);
        Bugly.init(getApplicationContext(),MyConfig.Bugly_Key,false);//改用集成了自动更新功能的SDK
        initVolley();
    }

    private static RequestQueue requestQueue;
    private static Gson gson;

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static Gson getGson() {
        return gson;
    }

    private void initVolley() {
        LogUtil.show("initvolley");
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        gson = new Gson();
    }


}
