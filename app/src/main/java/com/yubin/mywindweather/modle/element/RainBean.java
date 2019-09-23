package com.yubin.mywindweather.modle.element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.util.RandomUtil;

/**
 * Created by YUBIN at 17-7-11 下午5:57
 * Last modified at 17-7-11 下午5:57
 */

public class RainBean {
    public static final int maxSizeOfRain=10;
    private int currentSizeOfRain;
    private int imageSource;
    private Bitmap bitmap;
    private int locationX;
    private int locationY;
    private int speed;
    private boolean visible;
    private Matrix matrix;

    public void init(int size){
        if(size>maxSizeOfRain){
            currentSizeOfRain= maxSizeOfRain;
        }else if(size<1){
            currentSizeOfRain=1;
        }else{
            currentSizeOfRain=size;
        }
        int rainsType=RandomUtil.getRandom().nextInt(100)%3;
        switch (rainsType){
            case 0:
                setImageSource(R.mipmap.c_rain_01);
                break;
            case 1:
                setImageSource(R.mipmap.c_rain_02);
                break;
            case 2:
                setImageSource(R.mipmap.c_rain_03);
                break;
        }
        setLocationX(RandomUtil.getRandom().nextInt(300)-200);
        setLocationY(-RandomUtil.getRandom().nextInt(200));
        setSpeed(500+size*300);
        setVisible(true);
        if(matrix==null){
            matrix=new Matrix();
        }
    }

    public void initBitmap(Context context){
        setBitmap(BitmapFactory.decodeResource(context.getResources(),getImageSource()));
    }

    public int getCurrentSizeOfRain() {
        return currentSizeOfRain;
    }

    public void setCurrentSizeOfRain(int currentSizeOfRain) {
        this.currentSizeOfRain = currentSizeOfRain;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getImageSource() {
        return imageSource;
    }

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


}
