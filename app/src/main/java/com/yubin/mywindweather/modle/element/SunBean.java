package com.yubin.mywindweather.modle.element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.application.MyConstants;
import com.yubin.mywindweather.util.RandomUtil;

/**
 * Created by YUBIN at 17-7-17 上午11:24
 * Last modified at 17-7-17 上午11:24
 */

public class SunBean {
    private int sourceSun;
    private int sourceShexian;
    private int sourceLeng;
    private int sourceGuangyun;
    private Bitmap bitmapSun;
    private Bitmap bitmapShexian;
    private Bitmap bitmapLeng;
    private Bitmap bitmapGuangyun;
    private int locationX;
    private int locationY;
    private Matrix matrixLeng;
    private Matrix matrixGuangyun;

    private Paint paintGuangyun;

    private int angleLeng;
    private int angleGuangyun;

    private boolean rotateLocked;

    public void init() {
        sourceSun = R.mipmap.a_sun_03;
        sourceShexian = R.mipmap.a_sun_02;
        sourceLeng = R.mipmap.a_sun_01;
        sourceGuangyun = R.mipmap.a_sun_04;
        locationX = 800;
        locationY = 200;
        if (matrixLeng == null) {
            matrixLeng = new Matrix();
        }
        if (matrixGuangyun == null) {
            matrixGuangyun = new Matrix();
        }
        if(paintGuangyun==null){
            paintGuangyun=new Paint();
        }
        angleLeng = RandomUtil.getRandom().nextInt(360);
        angleGuangyun = RandomUtil.getRandom().nextInt(360);
        rotateLocked = false;
    }

    public void initBitmap(Context context) {
        bitmapSun = BitmapFactory.decodeResource(context.getResources(), getSourceSun());
        bitmapShexian = BitmapFactory.decodeResource(context.getResources(), getSourceShexian());
        bitmapLeng = BitmapFactory.decodeResource(context.getResources(), getSourceLeng());
        bitmapGuangyun = BitmapFactory.decodeResource(context.getResources(), getSourceGuangyun());
    }


    public boolean isRotateLocked() {
        return rotateLocked;
    }

    public void setRotateLocked(boolean rotateLocked) {
        this.rotateLocked = rotateLocked;
    }

    public int getAngleLeng() {
        return angleLeng;
    }

    public void setAngleLeng(int angleLeng) {
        if (angleLeng > MyConstants.maxIntNum) {
            angleLeng = angleLeng % 360;
        }
        this.angleLeng = angleLeng;
    }

    public int getAngleGuangyun() {
        return angleGuangyun;
    }

    public void setAngleGuangyun(int angleGuangyun) {
        if (angleGuangyun > MyConstants.maxIntNum) {
            angleGuangyun = angleGuangyun % 360;
        }
        this.angleGuangyun = angleGuangyun;
    }

    public Paint getPaintGuangyun() {
        return paintGuangyun;
    }

    public void setPaintGuangyun(Paint paintGuangyun) {
        this.paintGuangyun = paintGuangyun;
    }

    public Matrix getMatrixLeng() {
        return matrixLeng;
    }

    public void setMatrixLeng(Matrix matrixLeng) {
        this.matrixLeng = matrixLeng;
    }

    public Matrix getMatrixGuangyun() {
        return matrixGuangyun;
    }

    public void setMatrixGuangyun(Matrix matrixGuangyun) {
        this.matrixGuangyun = matrixGuangyun;
    }

    public int getSourceSun() {
        return sourceSun;
    }

    public void setSourceSun(int sourceSun) {
        this.sourceSun = sourceSun;
    }

    public int getSourceShexian() {
        return sourceShexian;
    }

    public void setSourceShexian(int sourceShexian) {
        this.sourceShexian = sourceShexian;
    }

    public int getSourceLeng() {
        return sourceLeng;
    }

    public void setSourceLeng(int sourceLeng) {
        this.sourceLeng = sourceLeng;
    }

    public int getSourceGuangyun() {
        return sourceGuangyun;
    }

    public void setSourceGuangyun(int sourceGuangyun) {
        this.sourceGuangyun = sourceGuangyun;
    }

    public Bitmap getBitmapSun() {
        return bitmapSun;
    }

    public void setBitmapSun(Bitmap bitmapSun) {
        this.bitmapSun = bitmapSun;
    }

    public Bitmap getBitmapShexian() {
        return bitmapShexian;
    }

    public void setBitmapShexian(Bitmap bitmapShexian) {
        this.bitmapShexian = bitmapShexian;
    }

    public Bitmap getBitmapLeng() {
        return bitmapLeng;
    }

    public void setBitmapLeng(Bitmap bitmapLeng) {
        this.bitmapLeng = bitmapLeng;
    }

    public Bitmap getBitmapGuangyun() {
        return bitmapGuangyun;
    }

    public void setBitmapGuangyun(Bitmap bitmapGuangyun) {
        this.bitmapGuangyun = bitmapGuangyun;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }


}
