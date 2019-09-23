package com.yubin.mywindweather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by YUBIN at 17-7-24 下午3:08
 * Last modified at 17-6-14 下午2:46
 */

public class NetworkUtil {
    /**
     * 没有网络
     */
    public static final int NETWORK_NONE=0;

    /**
     * wifi网络
     */
    public static final int NETWORK_WIFI=1;

    /**
     * 手机网络（流量）
     */
    public static final int NETWORK_PHONE=2;


    /**
     * 判断当前网络是否可用
     * @param context
     * @return
     */
    public static boolean isNewworkAvailable(Context context){
        Context  applicationContext=context.getApplicationContext();
        ConnectivityManager connectivityManager=(ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager==null){
            return false;
        }else{
            NetworkInfo[] networkInfos=connectivityManager.getAllNetworkInfo();
            if(networkInfos!=null&&networkInfos.length>0){
                for(int i=0;i<networkInfos.length;i++){
                    Log.i("=============",i+"网络类型="+networkInfos[i].getType()+"  状态="+networkInfos[i].getState());
                    if(networkInfos[i].getState()==NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int getNetworkType(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo.State state=connectivityManager.getActiveNetworkInfo().getState();
        int type=connectivityManager.getActiveNetworkInfo().getType();

        if(state==NetworkInfo.State.CONNECTED&&type==ConnectivityManager.TYPE_WIFI){
            return NETWORK_WIFI;
        }

        if(state==NetworkInfo.State.CONNECTED&&type==ConnectivityManager.TYPE_MOBILE){
            return NETWORK_PHONE;
        }

        return NETWORK_NONE;

    }

}

