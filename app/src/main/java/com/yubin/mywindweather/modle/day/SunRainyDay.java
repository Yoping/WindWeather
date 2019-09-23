package com.yubin.mywindweather.modle.day;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.yubin.mywindweather.modle.element.CloudBean;
import com.yubin.mywindweather.modle.element.MeteorBean;
import com.yubin.mywindweather.modle.element.MoonBean;
import com.yubin.mywindweather.modle.element.RainBean;
import com.yubin.mywindweather.modle.element.StarBean;
import com.yubin.mywindweather.modle.element.SunBean;
import com.yubin.mywindweather.modle.element.WindMillBean;
import com.yubin.mywindweather.tools.InitUtil;
import com.yubin.mywindweather.tools.MemoryUtil;
import com.yubin.mywindweather.tools.MyPaint;
import com.yubin.mywindweather.util.TestUtil;

/**
 * Created by YUBIN at 17-7-19 上午10:10
 * Last modified at 17-7-18 下午5:37
 */
public class SunRainyDay extends Day{
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
    private RainBean rainBeans[];
    private int numOfRain=10;
    private Bitmap bitmapSky;
    private StarBean stars[];
    private Bitmap starry;

    private boolean hasDayInit=false;
    private boolean hasNightInit=false;

    /**
     * 初始化太阳雨需要的内容
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
        imageGrass=InitUtil.initGrass(context,imageGrass,true);
        imageGrass2=InitUtil.initGrass2(context,imageGrass2,true);
        cloud=InitUtil.initClouds(context,cloud,CloudBean.cloudTypeWhite);
        bitmapSky=InitUtil.initSky(context,bitmapSky,true);

        sun=InitUtil.initSun(context,sun);
        rainBeans=InitUtil.initRains(context,rainBeans,numOfRain);

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
        imageGrass=InitUtil.initGrass(context,imageGrass,false);
        imageGrass2=InitUtil.initGrass2(context,imageGrass2,false);
        cloud=InitUtil.initClouds(context,cloud,CloudBean.cloudTypeGrey);
        bitmapSky=InitUtil.initSky(context,bitmapSky,false);

        meteor=InitUtil.initMeteor(context,meteor);
        moon=InitUtil.initMoon(context,moon);
        stars=InitUtil.initStars(context,stars);
        starry=InitUtil.initStarry(context,starry);

        hasDayInit=false;
        hasNightInit=true;

    }


    /**
     * 绘制晴天场景
     * @param canvas
     * @param holderWith
     * @param holderHeight
     */
    public void draw(Canvas canvas,int holderWith,int holderHeight){
        MyPaint.drawSky(canvas,bitmapSky,matrix,holderWith,holderHeight,paint);
        MyPaint.drawGrass2(canvas,imageGrass2,holderWith,holderHeight,paint);
        MyPaint.drawGrass(canvas,imageGrass,holderWith,holderHeight,paint);

        if(!TestUtil.getIfNight()){
            if(!hasDayInit){
                initDay(context);
            }
            MyPaint.drawSun(canvas,sun,holderWith,holderHeight,paint);
            MyPaint.drawRains(canvas,rainBeans,holderWith,holderHeight,paint);
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

    @Override
    public void releasePartMemory() {
        MemoryUtil.releaseImageGrass(imageGrass);
        MemoryUtil.releaseImageGrass2(imageGrass2);
        MemoryUtil.releaseClouds(cloud);
        MemoryUtil.releaseSky(bitmapSky);
        MemoryUtil.releaseMeteor(meteor);
        MemoryUtil.releaseMoon(moon);
        MemoryUtil.releaseStars(stars);
        MemoryUtil.releaseStarry(starry);
        MemoryUtil.releaseSun(sun);
        MemoryUtil.releaseRains(rainBeans);
        System.gc();
    }

    @Override
    public void releaseAllMemory() {
        releasePartMemory();
        MemoryUtil.releaseWindMill(windMill);
        System.gc();
    }
}
