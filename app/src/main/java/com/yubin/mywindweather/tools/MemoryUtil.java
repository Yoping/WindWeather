package com.yubin.mywindweather.tools;

import android.graphics.Bitmap;

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

/**
 * Created by YUBIN at 17-11-2 下午5:37
 * Last modified at 17-8-2 下午12:19
 */

/**
 * Created by YUBIN at 17-7-19 下午4:26
 * Last modified at 17-7-19 下午4:26
 * 内存释放工具
 */

public class MemoryUtil {
    public static void releaseWindMill(WindMillBean[] windMill){
        if (windMill != null) {
            for (int i = 0; i < windMill.length; i++) {
                if (windMill[i] != null) {
//                    windMill[i].getBitmapCenter().recycle();
                    windMill[i].getBitmapPillar().recycle();
                    windMill[i].getBitmapWing().recycle();
                    windMill[i] = null;
                }
            }
            windMill = null;
        }
    }

    public static void releaseImageGrass(Bitmap imageGrass){
        if (imageGrass != null) {
            imageGrass.recycle();
            imageGrass = null;
        }
    }
    public static void releaseImageGrass2(Bitmap imageGrass2){
        if (imageGrass2 != null) {
            imageGrass2.recycle();
            imageGrass2 = null;
        }
    }

    public static void releaseClouds(CloudBean[] clouds){
        if (clouds != null) {
            for (int i = 0; i < clouds.length; i++) {
                if (clouds[i] != null) {
                    if(clouds[i].getBitmap()!=null){
                        clouds[i].getBitmap().recycle();
                    }
                    clouds[i] = null;
                }
            }
            clouds = null;
        }
    }

    public static void releaseSun(SunBean sun){
        if (sun != null) {
            sun.getBitmapSun().recycle();
            sun.getBitmapGuangyun().recycle();
            sun.getBitmapShexian().recycle();
            sun.getBitmapLeng().recycle();
            sun = null;
        }
    }

    public static void releaseStars(StarBean[] stars){
        if (stars != null) {
            for (int i = 0; i < stars.length; i++) {
                if (stars[i] != null) {
                    stars[i].getBitmap().recycle();
                    stars[i] = null;
                }
            }
            stars = null;
        }
    }

    public static void releaseMeteor(MeteorBean meteor){
        if (meteor != null) {
            meteor.getBitmap().recycle();
            meteor = null;
        }
    }

    public static void releaseMeteors(MeteorBean[] meteors){
        if(meteors!=null){
            for(int i=0;i<meteors.length;i++){
                if(meteors[i]!=null){
                    meteors[i].getBitmap().recycle();
                    meteors[i]=null;
                }
            }
            meteors=null;
        }
    }

    public static void releaseMoon(MoonBean moon){
        if (moon != null) {
            moon.getBitmap().recycle();
            moon = null;
        }
    }


    public static void releaseStarry(Bitmap starry){
        if (starry != null) {
            starry.recycle();
            starry = null;
        }
    }

    public static void releaseSky(Bitmap bitmapSky){
        if (bitmapSky != null) {
            bitmapSky.recycle();
            bitmapSky = null;
        }
    }

    public static void releaseRains(RainBean[] rains){
        if(rains!=null){
            for(int i=0;i<rains.length;i++){
                if(rains[i]!=null){
                    rains[i].getBitmap().recycle();
                    rains[i]=null;
                }
            }
            rains=null;
        }
    }

    public static void releaseWaters(WaterBean[] waters){
        if(waters!=null){
            for(int i=0;i<waters.length;i++){
                if(waters[i]!=null){
                    for(int j=0;j<WaterBean.maxWaterNum;j++){
                        waters[i].getBitmap(j).recycle();
                    }
                    waters[i]=null;
                }
            }
            waters=null;
        }
    }

    public static void releaseLightnings(LightningBean[] lightnings){
        if(lightnings!=null){
            for(int i=0;i<lightnings.length;i++){
                if(lightnings[i]!=null){
                    lightnings[i].getBitmap().recycle();
                    lightnings[i]=null;
                }
            }
            lightnings=null;
        }
    }

    public static void releaseSnows(SnowBean[] snows){
        if(snows!=null){
            for(int i=0;i<snows.length;i++){
                if(snows[i]!=null){
                    snows[i].getBitmap().recycle();
                    snows[i]=null;
                }
            }
            snows=null;
        }
    }







}
