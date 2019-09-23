package com.yubin.mywindweather.modle.element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yubin.mywindweather.tools.ImageSource;
import com.yubin.mywindweather.util.RandomUtil;

/**
 * Created by YUBIN at 17-7-11 下午4:18
 * Last modified at 17-7-11 下午4:18
 */

public class CloudBean {
    public static final int cloudTypeWhite=1;
    public static final int cloudTypeGrey=2;
    public static final int cloudTypeBlack=3;
    public static final int maxCloudYdivide=10;
    public static int currentCloudType;
    private int imageSource;
    private Bitmap bitmap;
    private int locationX;
    private int devideY;
    private int speed;
    private boolean visible;
    public void init(int cloudType){
        currentCloudType=cloudType;
        switch (cloudType){
            case cloudTypeWhite:
                /**
                 * 白云
                 */
                setImageSource(ImageSource.getImageCouldWhite(RandomUtil.getRandom().nextInt(10)%2));
                break;
            case cloudTypeGrey:
                /**
                 * 灰云
                 */
                setImageSource(ImageSource.getImageCouldGrey(RandomUtil.getRandom().nextInt(10)%2));
                break;
            case cloudTypeBlack:
                /**
                 * 乌云
                 */
                setImageSource(ImageSource.getImageCouldBlack(RandomUtil.getRandom().nextInt(10)%2));
                break;
        }
        setLocationX(-RandomUtil.getRandom().nextInt(1000));
        setDevideY(RandomUtil.getRandom().nextInt(3));
        setSpeed(5);
        setVisible(true);

    }

    public void initBitmap(Context context){
        setBitmap(BitmapFactory.decodeResource(context.getResources(),getImageSource()));
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

    public int getDevideY() {
        return devideY;
    }

    public void setDevideY(int devideY) {
        this.devideY = devideY;
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
