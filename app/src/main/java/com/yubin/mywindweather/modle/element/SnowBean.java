package com.yubin.mywindweather.modle.element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.util.RandomUtil;

/**
 * Created by YUBIN at 17-7-24 上午9:10
 * Last modified at 17-7-24 上午9:10
 */

public class SnowBean {
    public static final int sizeSmall=1;
    public static final int sizeMiddle=2;
    public static final int sizeBig=3;
    public static final int sizeMany=4;
    private int imageSource;
    private Bitmap bitmap;
    private int locationX;
    private int locationY;
    private int speed;
    private int type;
    public float scale=1;

    public void init(int sizeType){
        type=sizeType;
        locationX= RandomUtil.getRandom().nextInt(1000);
        locationY=-RandomUtil.getRandom().nextInt(800);
        switch (sizeType){
            case sizeSmall:
                imageSource= R.mipmap.e_snow_03;
                break;
            case sizeMiddle:
                imageSource= R.mipmap.e_snow_02;
                break;
            case sizeBig:
                imageSource= R.mipmap.e_snow_01;
                break;
            case sizeMany:
                imageSource= R.mipmap.e_snow_04;
                break;
        }
        speed=RandomUtil.getRandom().nextInt(5)+5;
    }

    public void initBitmap(Context context){
        bitmap= BitmapFactory.decodeResource(context.getResources(),getImageSource());
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getImageSource() {
        return imageSource;
    }

    public void setImageSource(int imageSource) {
        imageSource = imageSource;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


}
