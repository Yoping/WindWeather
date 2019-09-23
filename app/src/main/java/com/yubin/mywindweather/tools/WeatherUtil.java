package com.yubin.mywindweather.tools;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.application.MyApplication;
import com.yubin.mywindweather.util.RandomUtil;

/**
 * Created by YUBIN at 17-11-2 下午5:37
 * Last modified at 17-8-7 上午9:53
 */

/**
 * Created by YUBIN at 17-7-26 上午10:10
 * Last modified at 17-7-26 上午10:10
 * 天气转换器
 */

public class WeatherUtil {
    /**
     * 通过天气得到相应的天气图标
     * @param weather
     * @return
     */
    public static int getImageByWeather(String weather) {
        int imageSource = R.mipmap.qing_min;
        if (weather.contains("晴")) {
            imageSource = R.mipmap.b_qing;
        } else if (weather.contains("雷")) {
            imageSource = R.mipmap.b_leiyu;
        } else if (weather.contains("多云")) {
            imageSource = R.mipmap.b_duoyun;
        } else if (weather.contains("阵雨")) {
            imageSource = R.mipmap.b_zhenyu;
        } else if (weather.contains("阴")) {
            imageSource = R.mipmap.b_yin;
        }else if (weather.contains("小雨")) {
            imageSource = R.mipmap.b_xiaoyu;
        }else if (weather.contains("中雨")) {
            imageSource = R.mipmap.b_zhenyu;
        }else if (weather.contains("大雨")) {
            imageSource = R.mipmap.b_dayu;
        }else if (weather.contains("暴雨")) {
            imageSource = R.mipmap.b_baoyu;
        }else if (weather.contains("小雪")) {
            imageSource = R.mipmap.b_xiaoxue;
        }else if (weather.contains("中雪")) {
            imageSource = R.mipmap.b_zhongxue;
        }else if (weather.contains("大雪")) {
            imageSource = R.mipmap.b_daxue;
        }else if (weather.contains("暴雪")) {
            imageSource = R.mipmap.b_baoxue;
        }else if (weather.contains("雾")) {
            imageSource = R.mipmap.b_wu;
        }else if (weather.contains("霾")) {
            imageSource = R.mipmap.b_mai;
        }else if (weather.contains("雨夹雪")) {
            imageSource = R.mipmap.b_yujiaxue;
        }else if (weather.contains("霜")||weather.contains("冻")) {
            imageSource = R.mipmap.b_shuangdong;
        }
        return imageSource;
    }

    /**
     * 通过天气数据得到标准天气类型
     * @param weather
     * @return
     */
    public static int getWeatherFlag(String weather) {
        int weatherFlag;
        if (weather.contains("晴")) {
            int i = RandomUtil.getRandom().nextInt(100);
            if (i < 50) {
                weatherFlag = MyApplication.SunnyDay;
            } else if (i < 70) {
                weatherFlag = MyApplication.SunRainyDay;
            } else {
                weatherFlag = MyApplication.MeteorRainyDay;
            }
        } else if (weather.contains("雷阵雨")) {
            weatherFlag = MyApplication.LightShowerRainyDay;
        } else if (weather.contains("多云") || weather.contains("阴")) {
            weatherFlag = MyApplication.CloudyDay;
        } else if (weather.contains("阵雨")) {
            weatherFlag = MyApplication.RainyShowerDay;
        } else if (weather.contains("中雨")) {
            weatherFlag = MyApplication.RainyDay;
        } else if (weather.contains("大雨") || weather.contains("暴雨")) {
            weatherFlag = MyApplication.RainstormDay;
        } else if (weather.contains("小雪")) {
            weatherFlag = MyApplication.SnowyDay;
        } else if (weather.contains("中雪")) {
            weatherFlag = MyApplication.MiddleSnowyDay;
        } else if (weather.contains("大雪") || (weather.contains("暴雪"))) {
            weatherFlag = MyApplication.BigSnowyDay;
        } else if (weather.contains("雨夹雪")) {
            weatherFlag = MyApplication.RainSnowyDay;
        } else if (weather.contains("雾")||weather.contains("霾")||weather.contains("沙")||weather.contains("尘")) {
            weatherFlag = MyApplication.FogDay;
        } else if (weather.contains("霜")||weather.contains("冻")) {
            weatherFlag = MyApplication.FrostDay;
        }else{
            weatherFlag = MyApplication.BTDay;
        }
        return weatherFlag;
    }
}
