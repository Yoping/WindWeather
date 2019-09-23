package com.yubin.mywindweather.modle.weather;

import java.util.List;

/**
 * Created by YUBIN at 17-7-24 下午3:28
 * Last modified at 17-7-24 下午3:28
 */

public class DataBean {

    private YesterdayBean yesterday;
    private String city;
    private String aqi;
    private List<ForecastBean> forecast;
    private String ganmao;
    private String wendu;


    public YesterdayBean getYesterday() {
        return yesterday;
    }

    public void setYesterday(YesterdayBean yesterday) {
        this.yesterday = yesterday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public List<ForecastBean> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastBean> forecast) {
        this.forecast = forecast;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }


}
