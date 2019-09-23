package com.yubin.mywindweather.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by YUBIN at 17-10-24 下午5:10
 * Last modified at 17-9-25 下午5:46
 */

/**
 * Created by YUBIN on 2017/5/3.
 */

public class ToastUtil {

    /**
     * 快速显示Toast
     * @param context
     * @param text
     */
    public static void show(Context context,String text){
        if(context!=null){
            Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
        }
    }

    public static void showLong(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }
}
