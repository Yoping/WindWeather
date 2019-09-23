package com.yubin.mywindweather.modle.day;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.yubin.mywindweather.application.MyApplication;
import com.yubin.mywindweather.modle.element.CloudBean;
import com.yubin.mywindweather.modle.element.LightningBean;
import com.yubin.mywindweather.modle.element.MeteorBean;
import com.yubin.mywindweather.modle.element.MoonBean;
import com.yubin.mywindweather.modle.element.RainBean;
import com.yubin.mywindweather.modle.element.StarBean;
import com.yubin.mywindweather.modle.element.SunBean;
import com.yubin.mywindweather.modle.element.WaterBean;
import com.yubin.mywindweather.modle.element.WindMillBean;
import com.yubin.mywindweather.tools.InitUtil;
import com.yubin.mywindweather.tools.MemoryUtil;
import com.yubin.mywindweather.tools.MyPaint;

/**
 * Created by YUBIN at 17-7-20 下午1:58
 * Last modified at 17-7-20 下午1:56
 */

public class AnalyseDay extends Day {
    private Context context;
    private Bitmap imageGrass;
    private Bitmap imageGrass2;
    private CloudBean cloud[];
    private WindMillBean windMill[];
    private WaterBean waters[];
    private RainBean rainBeans[];
    private Paint paint;
    private Matrix matrix;
    private Bitmap bitmapSky;
    private int numOfRains = 10;

    private MeteorBean meteor;
    private MoonBean moon;
    private SunBean sun;
    private StarBean stars[];
    private Bitmap starry;
    private LightningBean lightning[];


    /**
     * 初始化雨天需要的内容
     *
     * @param context
     */
    @Override
    public void initDay(Context context) {
        releasePartMemory();
        this.context = context;
        paint = new Paint();
        matrix = new Matrix();
    }

    /**
     * 初始化雨天夜间需要的内容
     *
     * @param context
     */
    @Override
    public void initNight(Context context) {
        releasePartMemory();
        this.context = context;
        paint = new Paint();
        matrix = new Matrix();
    }

    @Override
    public void draw(Canvas canvas, int holderWith, int holderHeight) {

        MyPaint.drawBackgroundDay(canvas, null, matrix, holderWith, holderHeight, paint);

        if (MyApplication.hasSky) {
            if(bitmapSky==null){
                bitmapSky = InitUtil.initSky(context, bitmapSky,true);
            }
            MyPaint.drawSky(canvas, bitmapSky, matrix, holderWith, holderHeight, paint);
        } else if (!MyApplication.hasSky) {
            MemoryUtil.releaseSky(bitmapSky);
        }

        if (MyApplication.hasGrass) {
            if(imageGrass==null){
                imageGrass = InitUtil.initGrass(context,imageGrass,true);
            }
            MyPaint.drawGrass(canvas, imageGrass, holderWith, holderHeight, paint);
        } else if (!MyApplication.hasGrass) {
            MemoryUtil.releaseImageGrass(imageGrass);
        }

        if (MyApplication.hasGrass2) {
            if(imageGrass2==null){
                imageGrass2 = InitUtil.initGrass(context,imageGrass2,true);
            }
            MyPaint.drawGrass2(canvas, imageGrass2, holderWith, holderHeight, paint);
        } else if (!MyApplication.hasGrass2) {
            MemoryUtil.releaseImageGrass(imageGrass2);
        }

        if (MyApplication.hasClouds) {
            if(cloud==null){
                cloud = InitUtil.initClouds(context, cloud,CloudBean.cloudTypeBlack);
            }
            MyPaint.drawCloud(canvas, cloud, holderWith, holderHeight, paint);
        } else if (!MyApplication.hasClouds) {
            MemoryUtil.releaseClouds(cloud);
        }

        if (MyApplication.hasWindMill) {
            if(windMill==null){
                windMill = InitUtil.initWindMill(context, windMill);
            }
            MyPaint.drawWindMillGroup(windMill, canvas, holderWith, holderHeight, paint);
        } else if (!MyApplication.hasWindMill) {
            MemoryUtil.releaseWindMill(windMill);
        }

        if (MyApplication.hasRains) {
            if(rainBeans==null){
                rainBeans = InitUtil.initRains(context, rainBeans,numOfRains);
            }
            MyPaint.drawRains(canvas, rainBeans, holderWith, holderHeight, paint);
        } else if (!MyApplication.hasRains) {
            MemoryUtil.releaseRains(rainBeans);
        }

        if (MyApplication.hasWaters) {
            if(waters==null){
                waters = InitUtil.initWaters(context, waters);
            }
            MyPaint.drawWatersGroup(canvas, waters, holderWith, holderHeight, paint);
        } else if (!MyApplication.hasWaters) {
            MemoryUtil.releaseWaters(waters);
        }

        if (MyApplication.hasMeteor) {
            if(meteor==null){
                meteor = InitUtil.initMeteor(context, meteor);
            }
            MyPaint.drawMeteor(canvas, meteor, holderWith, holderHeight, paint);
        } else if (!MyApplication.hasMeteor) {
            MemoryUtil.releaseMeteor(meteor);
        }

        if (MyApplication.hasMoon) {
            if(moon==null){
                moon = InitUtil.initMoon(context, moon);
            }
            MyPaint.drawMoon(canvas, moon, holderWith, holderHeight, paint);
        } else if (!MyApplication.hasMoon) {
            MemoryUtil.releaseMoon(moon);
        }

        if (MyApplication.hasSun) {
            if(sun==null){
                sun = InitUtil.initSun(context, sun);
            }
            MyPaint.drawSun(canvas, sun, holderWith, holderHeight, paint);
        } else if (!MyApplication.hasSun) {
            MemoryUtil.releaseSun(sun);
        }

        if (MyApplication.hasStars) {
            if(stars==null){
                stars = InitUtil.initStars(context, stars);
            }
            MyPaint.drawStars(canvas, stars, holderWith, holderHeight, paint);
        } else if (!MyApplication.hasStars) {
            MemoryUtil.releaseStars(stars);
        }

        if (MyApplication.hasStarry) {
            if(starry==null){
                starry = InitUtil.initStarry(context, starry);
            }
            MyPaint.drawStarry(canvas, starry, matrix, holderWith, holderHeight, paint);
        } else if (!MyApplication.hasStarry) {
            MemoryUtil.releaseStarry(starry);
        }
        if (MyApplication.hasLightnings) {
            if(lightning==null){
                lightning = InitUtil.initLightnings(context, lightning);
            }
            MyPaint.drawLightnings(canvas, lightning, holderWith, holderHeight, paint);
        } else if (!MyApplication.hasLightnings) {
            MemoryUtil.releaseLightnings(lightning);
        }

    }

    @Override
    public void releasePartMemory() {
        MemoryUtil.releaseImageGrass(imageGrass);
        MemoryUtil.releaseImageGrass2(imageGrass2);
        MemoryUtil.releaseClouds(cloud);
        MemoryUtil.releaseSky(bitmapSky);
        System.gc();
    }

    @Override
    public void releaseAllMemory() {
        releasePartMemory();
        MemoryUtil.releaseWindMill(windMill);
        MemoryUtil.releaseWaters(waters);
        MemoryUtil.releaseRains(rainBeans);
        MemoryUtil.releaseSun(sun);
        MemoryUtil.releaseMeteor(meteor);
        MemoryUtil.releaseMoon(moon);
        MemoryUtil.releaseStars(stars);
        MemoryUtil.releaseStarry(starry);
        MemoryUtil.releaseLightnings(lightning);
        System.gc();
    }

}
