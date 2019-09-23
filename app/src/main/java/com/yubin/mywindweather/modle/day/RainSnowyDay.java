package com.yubin.mywindweather.modle.day;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.yubin.mywindweather.modle.element.CloudBean;
import com.yubin.mywindweather.modle.element.RainBean;
import com.yubin.mywindweather.modle.element.SnowBean;
import com.yubin.mywindweather.modle.element.WindMillBean;
import com.yubin.mywindweather.tools.InitUtil;
import com.yubin.mywindweather.util.LogUtil;
import com.yubin.mywindweather.tools.MemoryUtil;
import com.yubin.mywindweather.tools.MyPaint;
import com.yubin.mywindweather.util.TestUtil;

/**
 * Created by YUBIN at 17-7-24 下午12:13
 * Last modified at 17-7-24 上午11:59
 */

public class RainSnowyDay extends Day{
    private Context context;
    private Bitmap imageGrass;
    private Bitmap imageGrass2;
    private CloudBean cloud[];
    private WindMillBean windMill[];
    private Paint paint;
    private Matrix matrix;
    private Bitmap bitmapSky;
    private SnowBean snows[];
    private RainBean[] rains;

    private boolean hasDayInit=false;
    private boolean hasNightInit=false;

    /**
     * 初始化雨夹雪的天气
     * @param context
     */
    public void initDay(Context context){
        releasePartMemory();
        this.context=context;
        paint = new Paint();
        matrix = new Matrix();

        if (windMill == null) {
            windMill=InitUtil.initWindMill(context,windMill);
        }
        if(snows==null){
            snows=InitUtil.initSnows(context,snows);
        }
        if(rains==null){
            rains=InitUtil.initRains(context,rains,10);
        }
        imageGrass=InitUtil.initGrass(context,imageGrass,true);
        imageGrass2=InitUtil.initGrass2(context,imageGrass2,true);
        cloud=InitUtil.initClouds(context,cloud,CloudBean.cloudTypeBlack);
        bitmapSky=InitUtil.initCloudySky(context,bitmapSky,true);

        hasDayInit=true;
        hasNightInit=false;

    }

    public void initNight(Context context){
        releasePartMemory();
        this.context=context;
        paint = new Paint();
        matrix = new Matrix();

        if (windMill == null) {
            windMill=InitUtil.initWindMill(context,windMill);
        }
        if(snows==null){
            snows=InitUtil.initSnows(context,snows);
        }
        if(rains==null){
            rains=InitUtil.initRains(context,rains,10);
        }
        imageGrass=InitUtil.initGrass(context,imageGrass,false);
        imageGrass2=InitUtil.initGrass2(context,imageGrass2,false);
        cloud=InitUtil.initClouds(context,cloud,CloudBean.cloudTypeBlack);
        bitmapSky=InitUtil.initCloudySky(context,bitmapSky,false);

        hasDayInit=false;
        hasNightInit=true;

    }


    /**
     * 绘制雨夹雪场景
     * @param canvas
     * @param holderWith
     * @param holderHeight
     */
    public void draw(Canvas canvas,int holderWith,int holderHeight){

        if(!TestUtil.getIfNight()){
            if(!hasDayInit){
                initDay(context);
            }
            MyPaint.drawBackgroundDay(canvas,null,matrix,holderWith,holderHeight, paint);
        }else{
            if(!hasNightInit){
                initNight(context);
            }
            MyPaint.drawBackgroundNight(canvas,null,matrix,holderWith,holderHeight, paint);
        }

        MyPaint.drawSky(canvas,bitmapSky,matrix,holderWith,holderHeight,paint);
        MyPaint.drawGrass2(canvas,imageGrass2,holderWith,holderHeight,paint);
        MyPaint.drawGrass(canvas,imageGrass,holderWith,holderHeight,paint);
        MyPaint.drawCloud(canvas,cloud,holderWith,holderHeight,paint);
        MyPaint.drawWindMillGroup(windMill,canvas,holderWith,holderHeight,paint);
        MyPaint.drawSnows(canvas,snows,matrix,holderWith,holderHeight,paint);
        MyPaint.drawRains(canvas, rains,holderWith,holderHeight,paint);


    }

    @Override
    public void releasePartMemory() {
        LogUtil.show("===========CloudyDay releasePartMemory");
        MemoryUtil.releaseImageGrass(imageGrass);
        MemoryUtil.releaseImageGrass(imageGrass2);
        MemoryUtil.releaseClouds(cloud);
        MemoryUtil.releaseSky(bitmapSky);
        System.gc();
    }

    @Override
    public void releaseAllMemory() {
        LogUtil.show("===========CloudyDay releaseAllMemory");
        releasePartMemory();
        MemoryUtil.releaseWindMill(windMill);
        MemoryUtil.releaseSnows(snows);
        MemoryUtil.releaseRains(rains);
        System.gc();
    }

}
