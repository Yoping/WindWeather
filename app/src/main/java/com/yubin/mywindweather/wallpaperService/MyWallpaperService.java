package com.yubin.mywindweather.wallpaperService;

import android.graphics.Canvas;
import android.os.Build;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import com.yubin.mywindweather.application.MyApplication;
import com.yubin.mywindweather.application.MyConstants;
import com.yubin.mywindweather.modle.day.AnalyseDay;
import com.yubin.mywindweather.modle.day.BTDay;
import com.yubin.mywindweather.modle.day.BigSnowyDay;
import com.yubin.mywindweather.modle.day.CloudyDay;
import com.yubin.mywindweather.modle.day.FogDay;
import com.yubin.mywindweather.modle.day.FrostDay;
import com.yubin.mywindweather.modle.day.LightRainyShowerDay;
import com.yubin.mywindweather.modle.day.MeteorRainyDay;
import com.yubin.mywindweather.modle.day.MiddleSnowyDay;
import com.yubin.mywindweather.modle.day.RainSnowyDay;
import com.yubin.mywindweather.modle.day.RainstormDay;
import com.yubin.mywindweather.modle.day.RainyDay;
import com.yubin.mywindweather.modle.day.RainyShowerDay;
import com.yubin.mywindweather.modle.day.SnowyDay;
import com.yubin.mywindweather.modle.day.SunRainyDay;
import com.yubin.mywindweather.modle.day.SunnyDay;
import com.yubin.mywindweather.modle.weather.DataBean;
import com.yubin.mywindweather.myInterface.GetCityNameListener;
import com.yubin.mywindweather.myInterface.GetWeatherDataListener;
import com.yubin.mywindweather.tools.HttpManager;
import com.yubin.mywindweather.tools.WeatherUtil;
import com.yubin.mywindweather.util.LogUtil;
import com.yubin.mywindweather.util.SharedPreferencesUtil;
import com.yubin.mywindweather.util.TestUtil;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by YUBIN at 17-11-2 下午4:53
 * Last modified at 17-8-9 上午9:40
 */

/**
 * Created by YUBIN at 17-7-10 下午3:20
 * Last modified at 17-7-11 下午4:10
 * 自定义壁纸服务
 */

public class MyWallpaperService extends WallpaperService {
    public static MyEngine myEngine;

    @Override
    public Engine onCreateEngine() {
        LogUtil.show("===========onCreateEngine");
        myEngine = new MyEngine();
        return myEngine;
    }

    /**
     * 自定义壁纸绘制引擎类
     */
    class MyEngine extends Engine {
        private Calendar calendar;
        private static final int drawFrameInterval = 50;
        private boolean visible = true;
        private Handler handler = new Handler();
        private RainyDay rainyDay;
        private SunnyDay sunnyDay;
        private CloudyDay cloudyDay;
        private SunRainyDay sunRainyDay;
        private LightRainyShowerDay lightRainyShowerDay;
        private RainstormDay rainstormDay;
        private MeteorRainyDay meteorRainyDay;
        private BTDay btDay;
        private AnalyseDay analyseDay;
        private SnowyDay snowyDay;
        private BigSnowyDay bigSnowyDay;
        private RainSnowyDay rainSnowyDay;
        private MiddleSnowyDay middleSnowyDay;
        private FogDay fogDay;
        private FrostDay frostDay;
        private RainyShowerDay rainyShowerDay;
        private boolean isLockedHardwareCanvas=false;


        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            LogUtil.show("===========MyEngine onCreate");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    getCityName();
                }
            }, 10, MyApplication.getWeatherInterval);
            init();
        }

        /**
         * 根据当前的天气状态初始化天气场景
         */
        public void init() {
            LogUtil.show("===========MyEngine init");
            releaseMemory();
            switch (MyApplication.currentWeather) {
                case MyApplication.SunnyDay:
                    sunnyDay = new SunnyDay();
                    break;
                case MyApplication.RainyDay:
                    rainyDay = new RainyDay();
                    break;
                case MyApplication.CloudyDay:
                    cloudyDay = new CloudyDay();
                    break;
                case MyApplication.SunRainyDay:
                    sunRainyDay = new SunRainyDay();
                    break;
                case MyApplication.LightShowerRainyDay:
                    lightRainyShowerDay = new LightRainyShowerDay();
                    break;
                case MyApplication.RainstormDay:
                    rainstormDay = new RainstormDay();
                    break;
                case MyApplication.MeteorRainyDay:
                    meteorRainyDay = new MeteorRainyDay();
                    break;
                case MyApplication.BTDay:
                    btDay = new BTDay();
                    break;
                case MyApplication.AnalyseDay:
                    analyseDay = new AnalyseDay();
                    break;
                case MyApplication.SnowyDay:
                    snowyDay = new SnowyDay();
                    break;
                case MyApplication.BigSnowyDay:
                    bigSnowyDay = new BigSnowyDay();
                    break;
                case MyApplication.RainSnowyDay:
                    rainSnowyDay = new RainSnowyDay();
                    break;
                case MyApplication.MiddleSnowyDay:
                    middleSnowyDay = new MiddleSnowyDay();
                    break;
                case MyApplication.FogDay:
                    fogDay = new FogDay();
                    break;
                case MyApplication.FrostDay:
                    frostDay = new FrostDay();
                    break;
                case MyApplication.RainyShowerDay:
                    rainyShowerDay = new RainyShowerDay();
                    break;

            }

            /**
             * 根据当前时间选择初始化白天或者夜晚的场景
             */
            if (!TestUtil.getIfNight()) {
                switch (MyApplication.currentWeather) {
                    case MyApplication.SunnyDay:
                        sunnyDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.RainyDay:
                        rainyDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.CloudyDay:
                        cloudyDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.SunRainyDay:
                        sunRainyDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.LightShowerRainyDay:
                        lightRainyShowerDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.RainstormDay:
                        rainstormDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.MeteorRainyDay:
                        meteorRainyDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.BTDay:
                        btDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.AnalyseDay:
                        analyseDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.SnowyDay:
                        snowyDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.BigSnowyDay:
                        bigSnowyDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.RainSnowyDay:
                        rainSnowyDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.MiddleSnowyDay:
                        middleSnowyDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.FogDay:
                        fogDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.FrostDay:
                        frostDay.initDay(getApplicationContext());
                        break;
                    case MyApplication.RainyShowerDay:
                        rainyShowerDay.initDay(getApplicationContext());
                        break;
                }

            } else {

                switch (MyApplication.currentWeather) {
                    case MyApplication.SunnyDay:
                        sunnyDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.RainyDay:
                        rainyDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.CloudyDay:
                        cloudyDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.SunRainyDay:
                        sunRainyDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.LightShowerRainyDay:
                        lightRainyShowerDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.RainstormDay:
                        rainstormDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.MeteorRainyDay:
                        meteorRainyDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.BTDay:
                        btDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.AnalyseDay:
                        analyseDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.SnowyDay:
                        snowyDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.BigSnowyDay:
                        bigSnowyDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.RainSnowyDay:
                        rainSnowyDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.MiddleSnowyDay:
                        middleSnowyDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.FogDay:
                        fogDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.FrostDay:
                        frostDay.initNight(getApplicationContext());
                        break;
                    case MyApplication.RainyShowerDay:
                        rainyShowerDay.initNight(getApplicationContext());
                        break;
                }
            }
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            this.visible = visible;
            if (visible) {
                drawFrame();
            } else {
                handler.removeCallbacks(task);
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            handler.removeCallbacks(task);
        }

        private long time = 0;

        private void drawFrame() {
            try{
                LogUtil.showTime("begin drawFrame====== time=" + System.currentTimeMillis() + "    intervalTime=" + (System.currentTimeMillis() - time) + "  interval=" + (float) 1000 / (System.currentTimeMillis() - time));
                time = System.currentTimeMillis();
                SurfaceHolder holder = getSurfaceHolder();
                int holderWith = holder.getSurfaceFrame().width();
                int holderHeight = holder.getSurfaceFrame().height() + 10;
                Canvas canvas;

                if (MyApplication.useHardwareSpeedUp) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        canvas = holder.getSurface().lockHardwareCanvas();
                        isLockedHardwareCanvas=true;
                    } else {
                        canvas = holder.lockCanvas();
                        isLockedHardwareCanvas=false;
                    }
                } else {
                    canvas = holder.lockCanvas();
                    isLockedHardwareCanvas=false;
                }

                if (canvas != null) {
                    LogUtil.showTime("openHardwareSpeedUp=" + MyApplication.useHardwareSpeedUp + "   isHardwareAccelerated=" + canvas.isHardwareAccelerated());
                    if (MyApplication.updateWeather) {
                        MyApplication.currentWeather = SharedPreferencesUtil.getInt(MyConstants.currentWeather, MyApplication.SunnyDay);
                        MyApplication.updateWeather = false;
                        init();
                    } else {
                        switch (MyApplication.currentWeather) {
                            case MyApplication.SunnyDay:
                                if (sunnyDay == null) {
                                    init();
                                }
                                sunnyDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.RainyDay:
                                if (rainyDay == null) {
                                    init();
                                }
                                rainyDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.CloudyDay:
                                if (cloudyDay == null) {
                                    init();
                                }
                                cloudyDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.SunRainyDay:
                                if (sunRainyDay == null) {
                                    init();
                                }
                                sunRainyDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.LightShowerRainyDay:
                                if (lightRainyShowerDay == null) {
                                    init();
                                }
                                lightRainyShowerDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.RainstormDay:
                                if (rainstormDay == null) {
                                    init();
                                }
                                rainstormDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.MeteorRainyDay:
                                if (meteorRainyDay == null) {
                                    init();
                                }
                                meteorRainyDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.BTDay:
                                if (btDay == null) {
                                    init();
                                }
                                btDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.AnalyseDay:
                                if (analyseDay == null) {
                                    init();
                                }
                                analyseDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.SnowyDay:
                                if (snowyDay == null) {
                                    init();
                                }
                                snowyDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.BigSnowyDay:
                                if (bigSnowyDay == null) {
                                    init();
                                }
                                bigSnowyDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.RainSnowyDay:
                                if (rainSnowyDay == null) {
                                    init();
                                }
                                rainSnowyDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.MiddleSnowyDay:
                                if (middleSnowyDay == null) {
                                    init();
                                }
                                middleSnowyDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.FogDay:
                                if (fogDay == null) {
                                    init();
                                }
                                fogDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.FrostDay:
                                if (frostDay == null) {
                                    init();
                                }
                                frostDay.draw(canvas, holderWith, holderHeight);
                                break;
                            case MyApplication.RainyShowerDay:
                                if (rainyShowerDay == null) {
                                    init();
                                }
                                rainyShowerDay.draw(canvas, holderWith, holderHeight);
                                break;
                        }
                    }

                    canvas.save();
                    canvas.restore();
                    LogUtil.showTime("end drawFrame====== time=" + System.currentTimeMillis() + "    speed time=" + (System.currentTimeMillis() - time));
                    handler.postDelayed(task, drawFrameInterval);

                    if(isLockedHardwareCanvas){
                        holder.getSurface().unlockCanvasAndPost(canvas);
                    }else{
                        holder.unlockCanvasAndPost(canvas);
                    }

                }

            }catch (Exception ex){
                ex.printStackTrace();
                handler.postDelayed(task, drawFrameInterval);
            }
        }

        private Runnable task = new Runnable() {
            @Override
            public void run() {
                drawFrame();
            }
        };

        private Timer timer;
        private String ipAddress;
        private String cityName;

        private Runnable getWeatherDataTask = new Runnable() {
            @Override
            public void run() {
                HttpManager.getWeatherNoVolley(cityName, new GetWeatherDataListener() {
                    @Override
                    public void onResult(DataBean weather) {
                        int weatherFlag= WeatherUtil.getWeatherFlag(weather.getForecast().get(0).getType());
                        SharedPreferencesUtil.putInt(MyConstants.currentWeather,weatherFlag);
                        MyApplication.updateWeather = true;
                    }

                    @Override
                    public void onError(String errorInfo) {
                    }
                },false);
            }
        };

        private void getCityName() {
            ipAddress = HttpManager.GetNetIp();
            LogUtil.show("=====获得IP：" + ipAddress);
            HttpManager.getCityName(ipAddress, new GetCityNameListener() {
                @Override
                public void onResult(String city) {
                    cityName = city;
                    new Thread(getWeatherDataTask).start();
                }

                @Override
                public void onError(String errorInfo) {
                }
            });
        }

        /**
         * 手动加速释放内存
         */
        private void releaseMemory() {
            LogUtil.show("===========MyEngine releaseMemory");
            if (sunnyDay != null) {
                sunnyDay.releaseAllMemory();
                sunnyDay = null;
            }
            if (rainyDay != null) {
                rainyDay.releaseAllMemory();
                rainyDay = null;
            }
            if (cloudyDay != null) {
                cloudyDay.releaseAllMemory();
                cloudyDay = null;
            }
            if (sunRainyDay != null) {
                sunRainyDay.releaseAllMemory();
                sunRainyDay = null;
            }
            if (lightRainyShowerDay != null) {
                lightRainyShowerDay.releaseAllMemory();
                lightRainyShowerDay = null;
            }
            if (rainstormDay != null) {
                rainstormDay.releaseAllMemory();
                rainstormDay = null;
            }
            if (meteorRainyDay != null) {
                meteorRainyDay.releaseAllMemory();
                meteorRainyDay = null;
            }
            if (btDay != null) {
                btDay.releaseAllMemory();
                btDay = null;
            }
            if (analyseDay != null) {
                analyseDay.releaseAllMemory();
                analyseDay = null;
            }
            if (snowyDay != null) {
                snowyDay.releaseAllMemory();
                snowyDay = null;
            }
            if (bigSnowyDay != null) {
                bigSnowyDay.releaseAllMemory();
                bigSnowyDay = null;
            }
            if (rainSnowyDay != null) {
                rainSnowyDay.releaseAllMemory();
                rainSnowyDay = null;
            }
            if (middleSnowyDay != null) {
                middleSnowyDay.releaseAllMemory();
                middleSnowyDay = null;
            }
            if (fogDay != null) {
                fogDay.releaseAllMemory();
                fogDay = null;
            }
            if (frostDay != null) {
                frostDay.releaseAllMemory();
                frostDay = null;
            }
            if (rainyShowerDay != null) {
                rainyShowerDay.releaseAllMemory();
                rainyShowerDay = null;
            }
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            LogUtil.show("===========MyEngine onSurfaceDestroyed");
            releaseMemory();
            if (timer != null) {
                timer.cancel();
                timer = null;
            }


        }
    }


}
