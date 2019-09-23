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
 * Created by YUBIN at 17-7-17 下午4:55
 * Last modified at 17-7-17 下午4:55
 */

public class StarBean {
    private int imageSource;
    private Bitmap bitmap;
    private int locationX;
    private int locationY;
    private Matrix matrix;
    private int alpha;
    private float scale;
    private Paint paint;

    public void init(){
        imageSource= R.mipmap.d_star;
        locationX= RandomUtil.getRandom().nextInt(1000);
        locationY=RandomUtil.getRandom().nextInt(500);
        if(matrix==null){
            matrix=new Matrix();
        }
        if(paint==null){
            paint=new Paint();
        }
        alpha=RandomUtil.getRandom().nextInt(255);
        scale=(float)RandomUtil.getRandom().nextInt(50)/100;
    }
    public void initBitmap(Context context){
        bitmap= BitmapFactory.decodeResource(context.getResources(),getImageSource());
    }

    public int getAlpha() {
        return  alpha;
    }

    public int getGoodAlpha() {
        if(alpha> MyConstants.maxIntNum){
            alpha=alpha%255;
        }
        if((alpha%510)>255){
            return (255-(alpha%255));
        }else{
            return alpha%255;
        }
    }
    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
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

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }


}
