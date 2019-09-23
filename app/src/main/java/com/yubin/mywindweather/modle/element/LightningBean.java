package com.yubin.mywindweather.modle.element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.util.RandomUtil;

/**
 * Created by YUBIN at 17-7-14 上午11:56
 * Last modified at 17-7-14 上午11:56
 */

public class LightningBean {
    private int imageSource;
    private Bitmap bitmap;
    private int locationX;
    private int locationY;
    private boolean isVisible;
    private int lightNingFlag;
    private Paint paint;

    public void init(){
        int type= RandomUtil.getRandom().nextInt(100)%3;
        switch (type){
            case 0:
                imageSource= R.mipmap.g_lightning_01;
                break;
            case 1:
                imageSource= R.mipmap.g_lightning_02;
                break;
            case 2:
                imageSource= R.mipmap.g_lightning_03;
                break;
        }
        refreshLocation(1080);
        isVisible=true;
        lightNingFlag=RandomUtil.getRandom().nextInt(100);
        if(paint==null){
            paint=new Paint();
        }
    }

    public void initBitmap(Context context){
        bitmap= BitmapFactory.decodeResource(context.getResources(),getImageSource());
    }

    public void refreshLocation(int holderWidth){
        locationX=RandomUtil.getRandom().nextInt(holderWidth);
        locationY=RandomUtil.getRandom().nextInt(800)-200;
        lightNingFlag=lightNingFlag+RandomUtil.getRandom().nextInt(100);
    }



    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
    public int getLightNingFlag() {
        return lightNingFlag;
    }

    public void setLightNingFlag(int lightNingFlag) {
        this.lightNingFlag = lightNingFlag;
    }


    public int getImageSource() {
        return imageSource;
    }

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }


}
