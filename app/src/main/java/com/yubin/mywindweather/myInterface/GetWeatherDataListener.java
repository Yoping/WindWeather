package com.yubin.mywindweather.myInterface;

import com.yubin.mywindweather.modle.weather.DataBean;

/**
 * Created by YUBIN at 17-7-24 下午3:31
 * Last modified at 17-7-24 下午3:31
 */

public interface GetWeatherDataListener {
    void onResult(DataBean weatherData);
    void onError(String errorInfo);
}
