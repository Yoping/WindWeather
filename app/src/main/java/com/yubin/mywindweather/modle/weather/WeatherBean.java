package com.yubin.mywindweather.modle.weather;

/**
 * Created by YUBIN at 17-11-2 下午4:52
 * Last modified at 17-7-25 下午5:24
 */

/**
 * Created by YUBIN at 17-7-25 下午5:23
 * Last modified at 17-7-25 下午5:23
 */

public class WeatherBean {
    private int imageSource;
    private String time;
    private String weather;
    private String temperInterval;
    public int getImageSource() {
        return imageSource;
    }

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperInterval() {
        return temperInterval;
    }

    public void setTemperInterval(String temperInterval) {
        this.temperInterval = temperInterval;
    }


}
