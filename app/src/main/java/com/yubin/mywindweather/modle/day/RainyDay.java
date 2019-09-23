package com.yubin.mywindweather.modle.day;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.yubin.mywindweather.modle.element.CloudBean;
import com.yubin.mywindweather.modle.element.RainBean;
import com.yubin.mywindweather.modle.element.WaterBean;
import com.yubin.mywindweather.modle.element.WindMillBean;
import com.yubin.mywindweather.tools.InitUtil;
import com.yubin.mywindweather.tools.MemoryUtil;
import com.yubin.mywindweather.tools.MyPaint;
import com.yubin.mywindweather.util.TestUtil;

/**
 * Created by YUBIN at 17-7-14 下午4:00
 * Last modified at 17-7-14 下午4:00
 */

public class RainyDay extends Day{
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
    private Bitmap bitmapWaterSky;
    private int numOfRains=5;

    private boolean hasDayInit=false;
    private boolean hasNightInit=false;



    /**
     * 初始化雨天需要的内容
     * @param context
     */
    @Override
    public void initDay(Context context){
        releasePartMemory();
        this.context=context;
        paint = new Paint();
        matrix = new Matrix();

        if (windMill == null) {
            windMill=InitUtil.initWindMill(context,windMill);
        }
        if(waters==null){
            waters=InitUtil.initWaters(context,waters);
        }
        if(rainBeans==null){
            rainBeans=InitUtil.initRains(context,rainBeans,numOfRains);
        }
        if(bitmapWaterSky==null){
            bitmapWaterSky=InitUtil.initWaterSky(context,bitmapWaterSky);
        }
        imageGrass=InitUtil.initGrass(context,imageGrass,true);
        imageGrass2=InitUtil.initGrass2(context,imageGrass2,true);
        cloud=InitUtil.initClouds(context,cloud,CloudBean.cloudTypeGrey);
        bitmapSky=InitUtil.initCloudySky(context,bitmapSky,true);


        hasDayInit=true;
        hasNightInit=false;
    }
    /**
     * 初始化雨天夜间需要的内容
     * @param context
     */
    @Override
    public void initNight(Context context){
        releasePartMemory();
        this.context=context;
        paint = new Paint();
        matrix = new Matrix();

        if (windMill == null) {
            windMill=InitUtil.initWindMill(context,windMill);
        }
        if(waters==null){
            waters=InitUtil.initWaters(context,waters);
        }
        if(rainBeans==null){
            rainBeans=InitUtil.initRains(context,rainBeans,numOfRains);
        }
        if(bitmapWaterSky==null){
            bitmapWaterSky=InitUtil.initWaterSky(context,bitmapWaterSky);
        }
        imageGrass=InitUtil.initGrass(context,imageGrass,false);
        imageGrass2=InitUtil.initGrass2(context,imageGrass2,false);
        cloud=InitUtil.initClouds(context,cloud,CloudBean.cloudTypeBlack);
        bitmapSky=InitUtil.initCloudySky(context,bitmapSky,false);

        hasDayInit=false;
        hasNightInit=true;
    }


    @Override
    public void draw(Canvas canvas,int holderWith,int holderHeight){

        if(!TestUtil.getIfNight()){
            /**
             * 白天
             */
            if(!hasDayInit){
                initDay(context);
            }
            MyPaint.drawBackgroundDay(canvas,null,matrix,holderWith,holderHeight,paint);
        }else{
            /**
             * 夜间
             */
            if(!hasNightInit){
                initNight(context);
            }
            MyPaint.drawBackgroundNight(canvas,null,matrix,holderWith,holderHeight,paint);
        }

        MyPaint.drawSky(canvas,bitmapSky,matrix,holderWith,holderHeight,paint);
        MyPaint.drawGrass2(canvas,imageGrass2,holderWith,holderHeight,paint);
        MyPaint.drawGrass(canvas,imageGrass,holderWith,holderHeight,paint);
        MyPaint.drawCloud(canvas,cloud,holderWith,holderHeight,paint);
        MyPaint.drawWindMillGroup(windMill,canvas,holderWith,holderHeight,paint);
        MyPaint.drawRains(canvas,rainBeans,holderWith,holderHeight,paint);
        MyPaint.drawSky(canvas,bitmapWaterSky,matrix,holderWith,holderHeight,paint);
        MyPaint.drawWatersGroup(canvas,waters,holderWith,holderHeight,paint);


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
        MemoryUtil.releaseSky(bitmapWaterSky);
        System.gc();
    }

}
