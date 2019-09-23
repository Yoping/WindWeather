package com.yubin.mywindweather.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.yubin.mywindweather.application.MyApplication;
import com.yubin.mywindweather.application.MyConstants;

import java.util.Calendar;
import java.util.List;

/**
 * Created by YUBIN at 17-7-26 下午2:58
 * Last modified at 17-7-26 下午2:58
 * 测试工具
 */

public class TestUtil {
    public static int currentWeather=0;
    public static int weatherSum=12;
    private static String getTestData(int index){
        String data[]=new String[weatherSum];
        data[0]="{\"data\":{\"yesterday\":{\"date\":\"25日星期二\",\"high\":\"高温 33℃\",\"fx\":\"无持续风向\",\"low\":\"低温 27℃\",\"fl\":\"微风\",\"type\":\"多云\"},\"city\":\"深圳\",\"aqi\":\"30\",\"forecast\":[{\"date\":\"26日星期三\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"暴雨\"},{\"date\":\"27日星期四\",\"high\":\"高温 33℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"28日星期五\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"中雨\"},{\"date\":\"29日星期六\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"30日星期天\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"}],\"ganmao\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\",\"wendu\":\"31\"},\"status\":1000,\"desc\":\"OK\"}";
        data[1]="{\"data\":{\"yesterday\":{\"date\":\"25日星期二\",\"high\":\"高温 33℃\",\"fx\":\"无持续风向\",\"low\":\"低温 27℃\",\"fl\":\"微风\",\"type\":\"多云\"},\"city\":\"广州\",\"aqi\":\"31\",\"forecast\":[{\"date\":\"26日星期三\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"晴\"},{\"date\":\"27日星期四\",\"high\":\"高温 33℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"28日星期五\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"中雨\"},{\"date\":\"29日星期六\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"30日星期天\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"}],\"ganmao\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\",\"wendu\":\"31\"},\"status\":1000,\"desc\":\"OK\"}";
        data[2]="{\"data\":{\"yesterday\":{\"date\":\"25日星期二\",\"high\":\"高温 33℃\",\"fx\":\"无持续风向\",\"low\":\"低温 27℃\",\"fl\":\"微风\",\"type\":\"多云\"},\"city\":\"长沙\",\"aqi\":\"32\",\"forecast\":[{\"date\":\"26日星期三\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"阴转多云\"},{\"date\":\"27日星期四\",\"high\":\"高温 33℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"28日星期五\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"中雨\"},{\"date\":\"29日星期六\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"30日星期天\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"}],\"ganmao\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\",\"wendu\":\"31\"},\"status\":1000,\"desc\":\"OK\"}";
        data[3]="{\"data\":{\"yesterday\":{\"date\":\"25日星期二\",\"high\":\"高温 33℃\",\"fx\":\"无持续风向\",\"low\":\"低温 27℃\",\"fl\":\"微风\",\"type\":\"多云\"},\"city\":\"西安\",\"aqi\":\"33\",\"forecast\":[{\"date\":\"26日星期三\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"小雪\"},{\"date\":\"27日星期四\",\"high\":\"高温 33℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"28日星期五\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"中雨\"},{\"date\":\"29日星期六\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"30日星期天\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"}],\"ganmao\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\",\"wendu\":\"31\"},\"status\":1000,\"desc\":\"OK\"}";
        data[4]="{\"data\":{\"yesterday\":{\"date\":\"25日星期二\",\"high\":\"高温 33℃\",\"fx\":\"无持续风向\",\"low\":\"低温 27℃\",\"fl\":\"微风\",\"type\":\"多云\"},\"city\":\"北京\",\"aqi\":\"34\",\"forecast\":[{\"date\":\"26日星期三\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"中雪\"},{\"date\":\"27日星期四\",\"high\":\"高温 33℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"28日星期五\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"中雨\"},{\"date\":\"29日星期六\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"30日星期天\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"}],\"ganmao\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\",\"wendu\":\"31\"},\"status\":1000,\"desc\":\"OK\"}";
        data[5]="{\"data\":{\"yesterday\":{\"date\":\"25日星期二\",\"high\":\"高温 33℃\",\"fx\":\"无持续风向\",\"low\":\"低温 27℃\",\"fl\":\"微风\",\"type\":\"多云\"},\"city\":\"上海\",\"aqi\":\"35\",\"forecast\":[{\"date\":\"26日星期三\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"暴雪\"},{\"date\":\"27日星期四\",\"high\":\"高温 33℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"28日星期五\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"中雨\"},{\"date\":\"29日星期六\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"30日星期天\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"}],\"ganmao\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\",\"wendu\":\"31\"},\"status\":1000,\"desc\":\"OK\"}";
        data[6]="{\"data\":{\"yesterday\":{\"date\":\"25日星期二\",\"high\":\"高温 33℃\",\"fx\":\"无持续风向\",\"low\":\"低温 27℃\",\"fl\":\"微风\",\"type\":\"多云\"},\"city\":\"杭州\",\"aqi\":\"36\",\"forecast\":[{\"date\":\"26日星期三\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"大雾\"},{\"date\":\"27日星期四\",\"high\":\"高温 33℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"28日星期五\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"中雨\"},{\"date\":\"29日星期六\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"30日星期天\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"}],\"ganmao\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\",\"wendu\":\"31\"},\"status\":1000,\"desc\":\"OK\"}";
        data[7]="{\"data\":{\"yesterday\":{\"date\":\"25日星期二\",\"high\":\"高温 33℃\",\"fx\":\"无持续风向\",\"low\":\"低温 27℃\",\"fl\":\"微风\",\"type\":\"多云\"},\"city\":\"武汉\",\"aqi\":\"37\",\"forecast\":[{\"date\":\"26日星期三\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"霜冻\"},{\"date\":\"27日星期四\",\"high\":\"高温 33℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"28日星期五\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"中雨\"},{\"date\":\"29日星期六\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"30日星期天\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"}],\"ganmao\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\",\"wendu\":\"31\"},\"status\":1000,\"desc\":\"OK\"}";
        data[8]="{\"data\":{\"yesterday\":{\"date\":\"25日星期二\",\"high\":\"高温 33℃\",\"fx\":\"无持续风向\",\"low\":\"低温 27℃\",\"fl\":\"微风\",\"type\":\"多云\"},\"city\":\"南京\",\"aqi\":\"38\",\"forecast\":[{\"date\":\"26日星期三\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"27日星期四\",\"high\":\"高温 33℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"28日星期五\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"中雨\"},{\"date\":\"29日星期六\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"30日星期天\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"}],\"ganmao\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\",\"wendu\":\"31\"},\"status\":1000,\"desc\":\"OK\"}";
        data[9]="{\"data\":{\"yesterday\":{\"date\":\"25日星期二\",\"high\":\"高温 33℃\",\"fx\":\"无持续风向\",\"low\":\"低温 27℃\",\"fl\":\"微风\",\"type\":\"多云\"},\"city\":\"成都\",\"aqi\":\"39\",\"forecast\":[{\"date\":\"26日星期三\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"冰雹\"},{\"date\":\"27日星期四\",\"high\":\"高温 33℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"28日星期五\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"中雨\"},{\"date\":\"29日星期六\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"30日星期天\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"}],\"ganmao\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\",\"wendu\":\"31\"},\"status\":1000,\"desc\":\"OK\"}";
        data[10]="{\"data\":{\"yesterday\":{\"date\":\"25日星期二\",\"high\":\"高温 33℃\",\"fx\":\"无持续风向\",\"low\":\"低温 27℃\",\"fl\":\"微风\",\"type\":\"多云\"},\"city\":\"南昌\",\"aqi\":\"39\",\"forecast\":[{\"date\":\"26日星期三\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"中雨\"},{\"date\":\"27日星期四\",\"high\":\"高温 33℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"28日星期五\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"中雨\"},{\"date\":\"29日星期六\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"30日星期天\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"}],\"ganmao\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\",\"wendu\":\"31\"},\"status\":1000,\"desc\":\"OK\"}";
        data[11]="{\"data\":{\"yesterday\":{\"date\":\"25日星期二\",\"high\":\"高温 33℃\",\"fx\":\"无持续风向\",\"low\":\"低温 27℃\",\"fl\":\"微风\",\"type\":\"多云\"},\"city\":\"重庆\",\"aqi\":\"39\",\"forecast\":[{\"date\":\"26日星期三\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"阵雨\"},{\"date\":\"27日星期四\",\"high\":\"高温 33℃\",\"fengli\":\"微风级\",\"low\":\"低温 26℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"28日星期五\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"中雨\"},{\"date\":\"29日星期六\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"},{\"date\":\"30日星期天\",\"high\":\"高温 32℃\",\"fengli\":\"微风级\",\"low\":\"低温 27℃\",\"fengxiang\":\"无持续风向\",\"type\":\"雷阵雨\"}],\"ganmao\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\",\"wendu\":\"31\"},\"status\":1000,\"desc\":\"OK\"}";

        return data[index];
    }

    public static String getNewTestData(){
        int index=RandomUtil.getRandom().nextInt(100);
        currentWeather=index%weatherSum;
        return  getTestData(currentWeather);
    }
    public static String getLastTestData(){
        return  getTestData(currentWeather);
    }

    /**
     * 判断当前时间是否为晚上
     */
    private static int temp=0;
    private static int hour=0;
    public static boolean getIfNight(){
        if(MyApplication.isRelease){
            hour= Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        }else{
            temp++;
            if(temp%20==0){
                hour++;
                hour=hour%24;
            }
            if(temp> MyConstants.maxIntNum){
                temp=0;
            }
        }

        if(((hour < 6 )||(hour >=18))&&MyApplication.myTest){
            return true;
        }else{
            return  false;
        }
    }


    /**
     * 判断本机是否安装了微信
     * @param context
     * @return
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }
}
