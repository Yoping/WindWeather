package com.yubin.mywindweather.util;

import android.util.Log;

import com.yubin.mywindweather.application.MyApplication;

/**
 * Created by YUBIN at 17-7-19 下午1:43
 * Last modified at 17-7-10 下午4:34
 */

public class LogUtil {
    public static void show(String info){
//        if(!MyApplication.isRelease){
//            Log.i("========",info);
//        }
        Log.i("========",info);
    }

    /**
     * 显示代码运行时间，可以通过这个时间计算
     * 1.CPU的性能
     * 2.每秒绘制页面的次数
     * 3.绘制时间间隔
     * 4.是否需要启动硬件加速
     * @param info
     */
    public static void showTime(String info){
        if(!MyApplication.isRelease){
//            Log.i("========",info);
        }
    }
}
