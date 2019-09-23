package com.yubin.mywindweather.modle.element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yubin.mywindweather.application.MyApplication;
import com.yubin.mywindweather.tools.ImageSource;
import com.yubin.mywindweather.util.RandomUtil;

/**
 * Created by YUBIN at 17-7-11 下午4:12
 * Last modified at 17-7-11 下午4:12
 */

public class WaterBean {

    public static final int waterTypeSmall=1;
    public static final int waterTypeBig=2;
    public static final int maxWaterNum=24;
    public int currentWaterNum;
    public int currentWaterType;



    /**
     * 雨滴图片资源
     */
    private int imageSources[];
    private Bitmap[] bitmap;
    /**
     * X坐标
     */
    private int divideX;
    /**
     * Y坐标
     */
    private int locationY;
    /**
     * 降落速度
     */
    private int speed;
    /**
     * 是否可见
     */
    private boolean visible;

    public void init(int waterType){
        if(waterType==waterTypeSmall){
            setImageSources(ImageSource.imageWaterDropAId);
            setDevideX(RandomUtil.getRandom().nextInt(10));
            setLocationY(RandomUtil.getRandom().nextInt(500)+300);
            setSpeed(RandomUtil.getRandom().nextInt(2)+ MyApplication.waterDropSpeed);
            setVisible(true);
        }else{
            setImageSources(ImageSource.imageWaterDropBId);
            setDevideX(RandomUtil.getRandom().nextInt(10));
            setLocationY(RandomUtil.getRandom().nextInt(500)+500);
            setSpeed(RandomUtil.getRandom().nextInt(2)+MyApplication.waterDropSpeed);
            setVisible(false);
        }
        currentWaterType=waterType;
        currentWaterNum=0;
    }

    public void initBitmap(Context context){
        if(bitmap==null){
            bitmap=new Bitmap[maxWaterNum];
            for(int i=0;i<bitmap.length;i++){
//                setBitmap(BitmapFactory.decodeResource(context.getResources(),ImageSource.getImageWaterDropBId(i)),i);
                setBitmap(BitmapFactory.decodeResource(context.getResources(),getImageSources()[i]),i);
            }
        }

    }

    public void refrshLocation(){
        if(currentWaterType==waterTypeSmall){
            setDevideX(RandomUtil.getRandom().nextInt(10));
            setLocationY(RandomUtil.getRandom().nextInt(500)+300);
            setSpeed(RandomUtil.getRandom().nextInt(10)+10);
        }else{
            setDevideX(RandomUtil.getRandom().nextInt(10));
            setLocationY(RandomUtil.getRandom().nextInt(500)+500);
            setSpeed(RandomUtil.getRandom().nextInt(2)+MyApplication.waterDropSpeed);
        }
        currentWaterNum=0;
    }

    public int getBimapSize(){
        if(bitmap==null){
            return 0;
        }else{
            return bitmap.length;
        }
    }

    public int[] getImageSources() {
        return imageSources;
    }

    public void setImageSources(int[] imageSources) {
        this.imageSources = imageSources;
    }
    public Bitmap getBitmap(int num) {
        return bitmap[num];
    }

    public void setBitmap(Bitmap bitmap,int num) {
        this.bitmap[num] = bitmap;
    }

    public int getDevideX() {
        return divideX;
    }

    public void setDevideX(int divideX) {
        this.divideX = divideX;
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
