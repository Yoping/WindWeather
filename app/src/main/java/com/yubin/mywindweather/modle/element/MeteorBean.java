package com.yubin.mywindweather.modle.element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.util.RandomUtil;

/**
 * Created by YUBIN at 17-7-14 下午2:25
 * Last modified at 17-7-14 下午2:25
 */

public class MeteorBean {
    private int imageSource;
    private Bitmap bitmap;
    private int locationX;
    private int locationY;
    private boolean visible;
    private int meteorFlag;
    private int speed;

    public void init(){
        imageSource= R.mipmap.d_meteor;
        refreshLocation();
        visible=true;
        meteorFlag=RandomUtil.getRandom().nextInt(100);
        speed=50;
    }
    public void initBitmap(Context context){
        bitmap= BitmapFactory.decodeResource(context.getResources(),getImageSource());
    }

    public void refreshLocation(){
        locationX=800+ RandomUtil.getRandom().nextInt(500);
        locationY=RandomUtil.getRandom().nextInt(200);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMeteorFlag() {
        return meteorFlag;
    }

    public void setMeteorFlag(int meteorFlag) {
        this.meteorFlag = meteorFlag;
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
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


}
