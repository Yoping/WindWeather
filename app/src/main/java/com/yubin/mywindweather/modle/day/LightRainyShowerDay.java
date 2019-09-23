package com.yubin.mywindweather.modle.day;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.yubin.mywindweather.modle.element.CloudBean;
import com.yubin.mywindweather.modle.element.LightningBean;
import com.yubin.mywindweather.modle.element.RainBean;
import com.yubin.mywindweather.modle.element.WindMillBean;
import com.yubin.mywindweather.tools.InitUtil;
import com.yubin.mywindweather.util.LogUtil;
import com.yubin.mywindweather.tools.MemoryUtil;
import com.yubin.mywindweather.tools.MyPaint;
import com.yubin.mywindweather.util.TestUtil;

/**
 * Created by YUBIN at 17-8-7 上午9:40
 * Last modified at 17-8-2 下午12:17
 */

public class LightRainyShowerDay extends Day{
    private Context context;
    private int numOfRain=5;
    private Bitmap imageGrass;
    private Bitmap imageGrass2;
    private CloudBean cloud[];
    private WindMillBean windMill[];
    private RainBean rainBeans[];
    private Paint paint;
    private Matrix matrix;
    private LightningBean lightning[];
    private Bitmap bitmapSky;
    private Bitmap bitmapWaterSky;

    private boolean hasDayInit=false;
    private boolean hasNightInit=false;

    /**
     * 初始化雷阵雨需要的内容
     * @param context
     */
    public void initDay(Context context){
        releasePartMemory();
        LogUtil.show("===========lightRainyDay initDay");
        this.context=context;
        matrix = new Matrix();

        if (windMill == null) {
            windMill=InitUtil.initWindMill(context,windMill);
        }
        if(rainBeans==null){
            rainBeans=InitUtil.initRains(context,rainBeans,numOfRain);
        }
        if(lightning==null){
            lightning=InitUtil.initLightnings(context,lightning);
        }
        if(cloud==null){
            cloud=InitUtil.initClouds(context,cloud,CloudBean.cloudTypeBlack);
        }
        if(bitmapWaterSky==null){
            bitmapWaterSky=InitUtil.initWaterSky(context,bitmapWaterSky);
        }

        imageGrass=InitUtil.initGrass(context,imageGrass,true);
        imageGrass2=InitUtil.initGrass2(context,imageGrass2,true);
        bitmapSky=InitUtil.initCloudySky(context,bitmapSky,true);




        hasDayInit=true;
        hasNightInit=false;
    }
    /**
     * 初始化雨天夜间需要的内容
     * @param context
     */
    public void initNight(Context context){
        releasePartMemory();
        LogUtil.show("===========lightRainyDay initNight");
        this.context=context;
        paint = new Paint();
        matrix = new Matrix();

        if (windMill == null) {
            windMill=InitUtil.initWindMill(context,windMill);
        }
        if(rainBeans==null){
            rainBeans=InitUtil.initRains(context,rainBeans,numOfRain);
        }
        if(lightning==null){
            lightning=InitUtil.initLightnings(context,lightning);
        }
        if(cloud==null){
            cloud=InitUtil.initClouds(context,cloud,CloudBean.cloudTypeBlack);
        }
        if(bitmapWaterSky==null){
            bitmapWaterSky=InitUtil.initWaterSky(context,bitmapWaterSky);
        }
        imageGrass=InitUtil.initGrass(context,imageGrass,false);
        imageGrass2=InitUtil.initGrass2(context,imageGrass2,false);
        bitmapSky=InitUtil.initCloudySky(context,bitmapSky,false);

        hasDayInit=false;
        hasNightInit=true;
    }

    /**
     * 绘制雨天场景
     * @param canvas
     * @param holderWith
     * @param holderHeight
     */
    private int drawTimes=0;
    public void draw(Canvas canvas,int holderWith,int holderHeight){
        drawTimes=drawTimes+1;


        if(!TestUtil.getIfNight()){
            /**
             * 白天
             */
            if(!hasDayInit){
                initDay(context);
            }
            MyPaint.drawBackgroundDay(canvas,null,matrix,holderWith,holderHeight,paint);
            MyPaint.drawSky(canvas,bitmapSky,matrix,holderWith,holderHeight,paint);
            MyPaint.drawGrass2(canvas,imageGrass2,holderWith,holderHeight,paint);
            MyPaint.drawGrass(canvas,imageGrass,holderWith,holderHeight,paint);
            MyPaint.drawCloud(canvas,cloud,holderWith,holderHeight,paint);
            MyPaint.drawLightnings(canvas,lightning,holderWith,holderHeight,paint);
        }else{
            /**
             * 夜间
             */
            if(!hasNightInit){
                initNight(context);
            }
            MyPaint.drawBackgroundNight(canvas,null,matrix,holderWith,holderHeight,paint);
            MyPaint.drawSky(canvas,bitmapSky,matrix,holderWith,holderHeight,paint);
            MyPaint.drawGrass2(canvas,imageGrass2,holderWith,holderHeight,paint);
            MyPaint.drawGrass(canvas,imageGrass,holderWith,holderHeight,paint);
            MyPaint.drawCloud(canvas,cloud,holderWith,holderHeight,paint);
            if(drawTimes%100<10){
                paint=skyToBright(paint,drawTimes);
                MyPaint.drawWonderfulLightnings(canvas,lightning,holderWith,holderHeight,paint,drawTimes,matrix);
            }else{
                paint.setAlpha(255);
                MyPaint.drawLightnings(canvas,lightning,holderWith,holderHeight,paint);
            }

        }

        MyPaint.drawWindMillGroup(windMill,canvas,holderWith,holderHeight,paint);
        MyPaint.drawShowerRains(canvas,rainBeans,holderWith,holderHeight,paint);
        MyPaint.drawSky(canvas,bitmapWaterSky,matrix,holderWith,holderHeight,paint);


    }

    @Override
    public void releasePartMemory() {
        LogUtil.show("===========lightRainyDay releasePartMemory");
        MemoryUtil.releaseImageGrass(imageGrass);
        MemoryUtil.releaseImageGrass2(imageGrass2);
        MemoryUtil.releaseSky(bitmapSky);
        System.gc();
    }

    @Override
    public void releaseAllMemory() {
        LogUtil.show("===========lightRainyDay releaseAllMemory");
        releasePartMemory();
        MemoryUtil.releaseWindMill(windMill);
        MemoryUtil.releaseRains(rainBeans);
        MemoryUtil.releaseLightnings(lightning);
        MemoryUtil.releaseClouds(cloud);
        MemoryUtil.releaseSky(bitmapWaterSky);
        System.gc();
    }

    /**
     * 控制闪电出现时天空是否点亮
     */
    private Paint skyToBright(Paint paint,int num){
        paint.setAlpha(100);
        return paint;
    }

}
