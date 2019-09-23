package com.yubin.mywindweather.myInterface;

/**
 * Created by YUBIN at 17-7-24 下午3:30
 * Last modified at 17-7-24 下午3:17
 */


public interface GetCityNameListener {
     void onResult(String cityName);
     void onError(String errorInfo);
}
