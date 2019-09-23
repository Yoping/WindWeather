package com.yubin.mywindweather.modle.day;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.yubin.mywindweather.modle.element.CloudBean;
import com.yubin.mywindweather.modle.element.MeteorBean;
import com.yubin.mywindweather.modle.element.MoonBean;
import com.yubin.mywindweather.modle.element.StarBean;
import com.yubin.mywindweather.modle.element.SunBean;
import com.yubin.mywindweather.modle.element.WindMillBean;
import com.yubin.mywindweather.tools.InitUtil;
import com.yubin.mywindweather.util.LogUtil;
import com.yubin.mywindweather.tools.MemoryUtil;
import com.yubin.mywindweather.tools.MyPaint;
import com.yubin.mywindweather.util.TestUtil;

/**
 * Created by YUBIN at 17-7-17 下午3:49
 * Last modified at 17-7-17 下午3:45
 */

public class SunnyDay extends Day {
    private Context context;
    private Bitmap imageGrass;
    private Bitmap imageGrass2;
    private CloudBean cloud[];
    private WindMillBean windMill[];
    private Paint paint;
    private Matrix matrix;
    private MeteorBean meteor;
    private MoonBean moon;
    private SunBean sun;
    private Bitmap bitmapSky;
    private StarBean stars[];
    private Bitmap starry;
    private boolean hasDayInit = false;
    private boolean hasNightInit = false;

    /**
     * 初始化晴天需要的内容
     *
     * @param context
     */
    @Override
    public void initDay(Context context) {
        LogUtil.show("===========SunnyDay initDay");
        releasePartMemory();
        this.context = context;
        paint = new Paint();
        matrix = new Matrix();

        if (windMill == null) {
            windMill=InitUtil.initWindMill(context,windMill);
        }
        imageGrass=InitUtil.initGrass(context,imageGrass,true);
        imageGrass2=InitUtil.initGrass2(context,imageGrass2,true);
        cloud=InitUtil.initClouds(context,cloud,CloudBean.cloudTypeWhite);
        bitmapSky=InitUtil.initSky(context,bitmapSky,true);

        sun=InitUtil.initSun(context,sun);
        hasDayInit = true;
        hasNightInit = false;

    }

    @Override
    public void initNight(Context context) {
        LogUtil.show("===========SunnyDay initNight");
        releasePartMemory();
        this.context = context;
        paint = new Paint();
        matrix = new Matrix();

        if (windMill == null) {
            windMill=InitUtil.initWindMill(context,windMill);
        }
        imageGrass=InitUtil.initGrass(context,imageGrass,false);
        imageGrass2=InitUtil.initGrass2(context,imageGrass2,false);
        cloud=InitUtil.initClouds(context,cloud,CloudBean.cloudTypeBlack);
        bitmapSky=InitUtil.initSky(context,bitmapSky,false);

        meteor=InitUtil.initMeteor(context,meteor);
        moon=InitUtil.initMoon(context,moon);
        stars=InitUtil.initStars(context,stars);
        starry=InitUtil.initStarry(context,starry);
        hasDayInit = false;
        hasNightInit = true;

    }


    /**
     * 绘制晴天场景
     * @param canvas
     * @param holderWith
     * @param holderHeight
     */
    @Override
    public void draw(Canvas canvas,int holderWith,int holderHeight){
        MyPaint.drawSky(canvas,bitmapSky,matrix,holderWith,holderHeight,paint);
        MyPaint.drawGrass2(canvas,imageGrass2,holderWith,holderHeight,paint);
        MyPaint.drawGrass(canvas,imageGrass,holderWith,holderHeight,paint);

        if(!TestUtil.getIfNight()){
            if(!hasDayInit){
                initDay(context);
            }
            MyPaint.drawSun(canvas,sun,holderWith,holderHeight,paint);
        }else{
            if(!hasNightInit){
                initNight(context);
            }

            MyPaint.drawStarry(canvas,starry,matrix,holderWith,holderHeight, paint);
            MyPaint.drawMoon(canvas,moon,holderWith,holderHeight,paint);
            MyPaint.drawStars(canvas,stars,holderWith,holderHeight,paint);
            MyPaint.drawMeteor(canvas,meteor,holderWith,holderHeight,paint);
        }
        MyPaint.drawCloud(canvas,cloud,holderWith,holderHeight,paint);
        MyPaint.drawWindMillGroup(windMill,canvas,holderWith,holderHeight,paint);


    }

    /**
     * 手动加速释放暂时不需要的内存
     */
    @Override
    public void releasePartMemory() {
        LogUtil.show("===========SunnyDay releasePartMemory");
        MemoryUtil.releaseImageGrass(imageGrass);
        MemoryUtil.releaseImageGrass2(imageGrass2);
        MemoryUtil.releaseClouds(cloud);
        MemoryUtil.releaseSun(sun);
        MemoryUtil.releaseStars(stars);
        MemoryUtil.releaseMeteor(meteor);
        MemoryUtil.releaseMoon(moon);
        MemoryUtil.releaseStarry(starry);
        MemoryUtil.releaseSky(bitmapSky);
        System.gc();

    }

    /**
     * 手动加速释放所有内存
     */
    @Override
    public void releaseAllMemory() {
        LogUtil.show("===========SunnyDay releaseAllMemory");
        releasePartMemory();
        MemoryUtil.releaseWindMill(windMill);
        System.gc();
    }
}
