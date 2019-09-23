package com.yubin.mywindweather.modle.element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.application.MyApplication;
import com.yubin.mywindweather.util.RandomUtil;

/**
 * Created by YUBIN at 17-7-12 下午2:12
 * Last modified at 17-7-12 下午2:12
 */

public class WindMillBean {
    public static final int maxXLocationdevide = 10;
    public static final int maxSizeLevel = 10;
    public int currentXLocadevide;
    public int currentSizeLevel;
    public boolean isPillarLocationLocked = false;
    public int rotateSpeed;


    /**
     * 风车转子，中心点图片
     */
    private int imageCenter;
    private Bitmap bitmapCenter;
    /**
     * 支杆图片
     */
    private int imagePillar;
    private Bitmap bitmapPillar;
    /**
     * 扇叶图片
     */
    private int imageWing;
    private Bitmap bitmapWing;
    /**
     * 中心点坐标
     */
    private int locaCenterX;
    private int locaCenterY;

    private Paint paint;


    /**
     * 支杆和扇叶大小、旋转变换矩阵
     */
    private Matrix matrixPillar;
    private Matrix matrixWing;


    /**
     * 扇叶旋转角度
     */
    private int rotateAngle;


    public void init(int sizeLevel, int xLocationDevide, boolean isDirectToRight) {
        if (sizeLevel > maxSizeLevel) {
            currentSizeLevel = maxSizeLevel;
        } else if (sizeLevel < 1) {
            currentSizeLevel = 1;
        } else {
            currentSizeLevel = sizeLevel;
        }

        if (xLocationDevide > maxXLocationdevide) {
            currentXLocadevide = maxXLocationdevide;
        } else if (xLocationDevide < 1) {
            currentXLocadevide = 1;
        } else {
            currentXLocadevide = xLocationDevide;
        }
        setLocaCenterX(0);
        setLocaCenterY(0);
        setImageCenter(R.mipmap.a_windmill_center_01);
        if (isDirectToRight) {
            setImagePillar(R.mipmap.a_windmill_pillar_01);
        } else {
            setImagePillar(R.mipmap.a_windmill_pillar_flip_01);
        }
        setImageWing(R.mipmap.a_windmill_wing);
        if (matrixPillar == null) {
            setMatrixPillar(new Matrix());
        }
        if (matrixWing == null) {
            setMatrixWing(new Matrix());
        }
        if(paint==null){
            paint=new Paint();
        }
        int hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY);
        if(hour>=6||hour>=18){
//            paint.setColor(Color.BLUE);
//            paint.setColorFilter(null);
//            ColorMatrix colorMatrix = new ColorMatrix();
//            colorMatrix.setSaturation(0);
//            ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
//                    colorMatrix);
//            paint.setColorFilter(colorMatrixFilter);
//            LogUtil.show("=======day==========paint.setColorFilter");
        }else{
//            paint.setColor(Color.BLACK);
//            paint.setAlpha(250);
//            ColorMatrix colorMatrix = new ColorMatrix();
//            colorMatrix.setSaturation(0);
//            ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
//                    colorMatrix);
//            paint.setColorFilter(colorMatrixFilter);
//            LogUtil.show("=======night==========paint.setColorFilter");
        }
        rotateAngle = RandomUtil.getRandom().nextInt(360);
        rotateSpeed= MyApplication.windMillRotateSpeed;
    }

    public static final Bitmap getGreyBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap faceIconGreyBitmap = Bitmap
                .createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
                colorMatrix);
        paint.setColorFilter(colorMatrixFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return faceIconGreyBitmap;
    }

    public void initBitmap(Context context) {
        Bitmap bitmapPillar2=BitmapFactory.decodeResource(context.getResources(), getImagePillar());
        Bitmap bitmapWing2=BitmapFactory.decodeResource(context.getResources(), getImageWing());
        bitmapPillar=getGreyBitmap(bitmapPillar2);
        bitmapWing=getGreyBitmap(bitmapWing2);
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public int getRotateAngle() {
        return rotateAngle;
    }

    public void setRotateAngle(int rotateAngle) {
        this.rotateAngle = rotateAngle;
    }

    public Matrix getMatrixPillar() {
        return matrixPillar;
    }

    public void setMatrixPillar(Matrix matrixPillar) {
        this.matrixPillar = matrixPillar;
    }

    public Matrix getMatrixWing() {
        return matrixWing;
    }

    public void setMatrixWing(Matrix matrixWing) {
        this.matrixWing = matrixWing;
    }

    public int getImageCenter() {
        return imageCenter;
    }

    public void setImageCenter(int imageCenter) {
        this.imageCenter = imageCenter;
    }

    public int getImagePillar() {
        return imagePillar;
    }

    public void setImagePillar(int imagePillar) {
        this.imagePillar = imagePillar;
    }

    public int getImageWing() {
        return imageWing;
    }

    public void setImageWing(int imageWing) {
        this.imageWing = imageWing;
    }

    public int getLocaCenterX() {
        return locaCenterX;
    }

    public void setLocaCenterX(int locaCenterX) {
        this.locaCenterX = locaCenterX;
    }

    public int getLocaCenterY() {
        return locaCenterY;
    }

    public void setLocaCenterY(int locaCenterY) {
        this.locaCenterY = locaCenterY;
    }

    public Bitmap getBitmapCenter() {
        return bitmapCenter;
    }

    public void setBitmapCenter(Bitmap bitmapCenter) {
        this.bitmapCenter = bitmapCenter;
    }

    public Bitmap getBitmapPillar() {
        return bitmapPillar;
    }

    public void setBitmapPillar(Bitmap bitmapPillar) {
        this.bitmapPillar = bitmapPillar;
    }

    public Bitmap getBitmapWing() {
        return bitmapWing;
    }

    public void setBitmapWing(Bitmap bitmapWing) {
        this.bitmapWing = bitmapWing;
    }

    public int getRotateSpeed() {
        return rotateSpeed;
    }

    public void setRotateSpeed(int rotateSpeed) {
        this.rotateSpeed = rotateSpeed;
    }


}
