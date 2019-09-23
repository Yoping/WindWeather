package com.yubin.mywindweather.modle.element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.util.RandomUtil;

/**
 * Created by YUBIN at 17-7-14 下午2:56
 * Last modified at 17-7-14 下午2:56
 */

public class MoonBean {
    private int imageSource;
    private Bitmap bitmap;
    private int locationX;
    private int locationY;
    private int dayNumber;
    private Matrix matrix;

    public void init(int dayNum){
        locationX= RandomUtil.getRandom().nextInt(800);
        locationY=RandomUtil.getRandom().nextInt(300);
        dayNumber=dayNum;
        if(dayNum<4&&dayNum>0){
            imageSource= R.mipmap.moon_01;
        }else if(dayNum<8){
            imageSource= R.mipmap.moon_02;
        }else if(dayNum<12){
            imageSource= R.mipmap.moon_03;
        }else if(dayNum<17){
            imageSource= R.mipmap.moon_04;
        }else if(dayNum<22){
            imageSource= R.mipmap.moon_05;
        }else if(dayNum<27){
            imageSource= R.mipmap.moon_06;
        }else if(dayNum<32){
            imageSource= R.mipmap.moon_07;
        }else{
            imageSource= R.mipmap.moon_07;
            dayNumber=30;
        }
        if(matrix==null){
            matrix=new Matrix();
        }

    }

    public void initBitmap(Context context){
        bitmap= BitmapFactory.decodeResource(context.getResources(),getImageSource());
    }



    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
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

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }


}
