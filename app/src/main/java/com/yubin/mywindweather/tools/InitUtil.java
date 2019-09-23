package com.yubin.mywindweather.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.modle.element.CloudBean;
import com.yubin.mywindweather.modle.element.LightningBean;
import com.yubin.mywindweather.modle.element.MeteorBean;
import com.yubin.mywindweather.modle.element.MoonBean;
import com.yubin.mywindweather.modle.element.RainBean;
import com.yubin.mywindweather.modle.element.SnowBean;
import com.yubin.mywindweather.modle.element.StarBean;
import com.yubin.mywindweather.modle.element.SunBean;
import com.yubin.mywindweather.modle.element.WaterBean;
import com.yubin.mywindweather.modle.element.WindMillBean;

import java.util.Calendar;

/**
 * Created by YUBIN at 17-11-2 下午5:37
 * Last modified at 17-8-8 上午10:18
 */

/**
 * Created by YUBIN at 17-7-19 下午4:31
 * Last modified at 17-7-19 下午4:31
 */

public class InitUtil {

    /**
     * 初始化风车组
     * @param context
     * @param windMill
     */
    public static WindMillBean[] initWindMill(Context context,WindMillBean[] windMill){
        windMill = new WindMillBean[8];
        for (int i = 0; i < windMill.length; i++) {
            windMill[i] = new WindMillBean();
        }
        windMill[0].init(2, 2, true);
        windMill[1].init(10, 3, true);
        windMill[2].init(2, 4, true);
        windMill[3].init(6, 5, true);
        windMill[4].init(2, 6, true);
        windMill[5].init(4, 7, true);
        windMill[6].init(1, 8, true);
        windMill[7].init(2, 9, true);
        for (int i = 0; i < windMill.length; i++) {
            windMill[i].initBitmap(context);
        }
        return  windMill;
    }

    /**
     * 初始化近处的草地
     * @param context
     * @param imageGrass
     * @param isDay
     */
    public static Bitmap initGrass(Context context,Bitmap imageGrass,boolean isDay){
        if(isDay){
            imageGrass = BitmapFactory.decodeResource(context.getResources(), R.mipmap.a_land_01);
        }else{
            imageGrass = BitmapFactory.decodeResource(context.getResources(), R.mipmap.a_land_03);
        }
        return  imageGrass;

    }

    /**
     * 初始化远处的草地
     * @param context
     * @param imageGrass2
     * @param isDay
     */
    public static Bitmap initGrass2(Context context,Bitmap imageGrass2,boolean isDay){
        if(isDay){
            imageGrass2 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.a_land_02);
        }else{
            imageGrass2 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.a_land_04);
        }
        return imageGrass2;
    }
    /**
     * 初始化下雪天近处的草地
     * @param context
     * @param imageGrass
     * @param isDay
     */
    public static Bitmap initSnowyGrass(Context context,Bitmap imageGrass,boolean isDay){
        if(isDay){
            imageGrass = BitmapFactory.decodeResource(context.getResources(), R.mipmap.a_land_06);
        }else{
            imageGrass = BitmapFactory.decodeResource(context.getResources(), R.mipmap.a_land_08);
        }
        return  imageGrass;

    }

    /**
     * 初始化下雪天远处的草地
     * @param context
     * @param imageGrass2
     * @param isDay
     */
    public static Bitmap initSnowyGrass2(Context context,Bitmap imageGrass2,boolean isDay){
        if(isDay){
            imageGrass2 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.a_land_07);
        }else{
            imageGrass2 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.a_land_09);
        }
        return imageGrass2;
    }

    /**
     * 初始化蓝色的天空
     * @param context
     * @param bitmapSky
     * @param isDay
     */
    public static Bitmap initSky(Context context,Bitmap bitmapSky,boolean isDay){
        if(isDay){
            bitmapSky = BitmapFactory.decodeResource(context.getResources(), R.mipmap.sky_01);
        }else {
            bitmapSky = BitmapFactory.decodeResource(context.getResources(), R.mipmap.sky_02);
        }
        return  bitmapSky;
    }

    /**
     * 初始化灰暗的天空
     * @param context
     * @param bitmapSky
     * @param isDay
     * @return
     */
    public static Bitmap initCloudySky(Context context,Bitmap bitmapSky,boolean isDay){
        if(isDay){
            bitmapSky = BitmapFactory.decodeResource(context.getResources(), R.mipmap.sky_03);
        }else {
            bitmapSky = BitmapFactory.decodeResource(context.getResources(), R.mipmap.sky_04);
        }
        return  bitmapSky;
    }

    /**
     * 初始化有雾的天空
     * @param context
     * @param bitmapSky
     * @param isDay
     * @return
     */
    public static Bitmap initFogSky(Context context,Bitmap bitmapSky,boolean isDay){
        if(isDay){
            bitmapSky = BitmapFactory.decodeResource(context.getResources(), R.mipmap.fog_01);
        }else {
            bitmapSky = BitmapFactory.decodeResource(context.getResources(), R.mipmap.fog_02);
        }
        return  bitmapSky;
    }

    /**
     * 初始化霜冻、结冰的天空
     * @param context
     * @param bitmapSky
     * @param isDay
     * @return
     */
    public static Bitmap initFrostSky(Context context,Bitmap bitmapSky,boolean isDay){
        if(isDay){
            bitmapSky = BitmapFactory.decodeResource(context.getResources(), R.mipmap.e_frost);
        }else {
            bitmapSky = BitmapFactory.decodeResource(context.getResources(), R.mipmap.e_frost);
        }
        return  bitmapSky;
    }

    /**
     * 初始化飞雪的天空
     * @param context
     * @param bitmapSky
     * @return
     */
    public static Bitmap initSnowySky(Context context,Bitmap bitmapSky){
        bitmapSky = BitmapFactory.decodeResource(context.getResources(), R.mipmap.e_snow_04);
        return  bitmapSky;
    }
    /**
     * 初始化有雨滴的的天空
     * @param context
     * @param bitmapSky
     * @return
     */
    public static Bitmap initWaterSky(Context context,Bitmap bitmapSky){
        bitmapSky = BitmapFactory.decodeResource(context.getResources(), R.mipmap.c_waterdrop);
        return  bitmapSky;
    }


    /**
     * 初始化星空
     * @param context
     * @param starry
     */
    public static Bitmap initStarry(Context context,Bitmap starry){
        starry = BitmapFactory.decodeResource(context.getResources(), R.mipmap.d_sky_stars);
        return starry;
    }

    /**
     * 初始化雨
     * @param context
     * @param rainBeans
     * @param numOfRain
     */
    public static RainBean[] initRains(Context context,RainBean[] rainBeans,int numOfRain){
        rainBeans=new RainBean[numOfRain];
        for(int i=0;i<numOfRain;i++){
            rainBeans[i]=new RainBean();
            rainBeans[i].init(8);
            rainBeans[i].initBitmap(context);
        }
        return  rainBeans;
    }

    /**
     * 初始化月亮
     * @param context
     * @param moon
     */
    public static MoonBean initMoon(Context context,MoonBean moon){
        moon = new MoonBean();
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        moon.init(day);
        moon.initBitmap(context);
        return moon;
    }

    /**
     * 初始化一群星星
     * @param context
     * @param stars
     */
    public static StarBean[] initStars(Context context,StarBean[] stars){
        stars = new StarBean[6];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new StarBean();
            stars[i].init();
            stars[i].initBitmap(context);
        }
        return stars;
    }

    /**
     * 初始化云群
     * @param context
     * @param clouds
     * @param cloudType
     */
    public static CloudBean[] initClouds(Context context,CloudBean[] clouds,int cloudType){
        clouds=new CloudBean[4];
        for(int i=0;i<clouds.length;i++){
            clouds[i]=new CloudBean();
            clouds[i].init(cloudType);
            clouds[i].initBitmap(context);
        }
        return clouds;
    }

    /**
     * 初始化一些水滴
     * @param context
     * @param waters
     */
    public static WaterBean[] initWaters(Context context,WaterBean[] waters){
        waters=new WaterBean[3];
        for(int i=0;i<waters.length;i++){
            waters[i]=new WaterBean();
            if(i==0){
                waters[i].init(WaterBean.waterTypeBig);
            }else{
                waters[i].init(WaterBean.waterTypeSmall);
            }
            waters[i].initBitmap(context);
        }
        return waters;
    }

    /**
     * 初始化闪电组
     * @param context
     * @param lightnings
     */
    public static LightningBean[] initLightnings(Context context,LightningBean[] lightnings){
        lightnings=new LightningBean[3];
        for(int i=0;i<lightnings.length;i++){
            lightnings[i]=new LightningBean();
            lightnings[i].init();
            lightnings[i].initBitmap(context);
        }
        return lightnings;
    }

    /**
     * 初始化流星
     * @param context
     * @param meteor
     */
    public static MeteorBean initMeteor(Context context,MeteorBean meteor){
        meteor = new MeteorBean();
        meteor.init();
        meteor.initBitmap(context);
        return meteor;
    }

    /**
     * 初始化流星雨
     * @param context
     * @param meteors
     * @param numOfMeteors
     */
    public static MeteorBean[] initMeteors(Context context,MeteorBean[] meteors,int numOfMeteors){
        meteors=new MeteorBean[numOfMeteors];
       for(int i=0;i<10;i++){
           meteors[i] = new MeteorBean();
           meteors[i].init();
           meteors[i].initBitmap(context);
       }
        return meteors;
    }

    /**
     * 初始化太阳
     * @param context
     * @param sun
     */
    public static SunBean initSun(Context context,SunBean sun){
        sun = new SunBean();
        sun.init();
        sun.initBitmap(context);
        return sun;
    }

    /**
     * 初始化一片雪花
     * @param context
     * @param snow
     * @return
     */
    public static SnowBean initSnow(Context context,SnowBean snow,int size){
        snow=new SnowBean();
        snow.init(size);
        snow.initBitmap(context);
        return snow;
    }

    /**
     * 初始化漫天的雪花
     * @param context
     * @param snows
     * @return
     */
    public static SnowBean[] initBigSnows(Context context,SnowBean[] snows){
        snows=new SnowBean[14];
        for(int i=0;i<snows.length;i++){
            if(i<4){
                snows[i]=initSnow(context,snows[i],SnowBean.sizeSmall);
            }else if(i<7){
                snows[i]=initSnow(context,snows[i],SnowBean.sizeMiddle);
            }else if(i<12){
                snows[i]=initSnow(context,snows[i],SnowBean.sizeMany);
                if(i==8){
                    snows[i].setLocationY(snows[7].getLocationY()+500);
                    snows[i].setSpeed(snows[7].getSpeed());
                }else if(i==9){
                    snows[i].setLocationY(snows[8].getLocationY()+500);
                    snows[i].setSpeed(snows[8].getSpeed());
                }else if(i==10){
                    snows[i].setLocationY(snows[9].getLocationY()+500);
                    snows[i].setSpeed(snows[9].getSpeed());
                }else if(i==11){
                    snows[i].setLocationY(snows[10].getLocationY()+500);
                    snows[i].setSpeed(snows[10].getSpeed());
                }
            }else{
                snows[i]=initSnow(context,snows[i],SnowBean.sizeBig);
            }

        }
        return  snows;
    }
    /**
     * 初始化飘扬的小学
     * @param context
     * @param snows
     * @return
     */
    public static SnowBean[] initSnows(Context context,SnowBean[] snows){
        snows=new SnowBean[20];
        for(int i=0;i<snows.length;i++){
            if(i<11){
                snows[i]=initSnow(context,snows[i],SnowBean.sizeSmall);
            }else if(i<18){
                snows[i]=initSnow(context,snows[i],SnowBean.sizeMiddle);
            }else {
                snows[i]=initSnow(context,snows[i],SnowBean.sizeBig);
            }

        }
        return  snows;
    }
}
