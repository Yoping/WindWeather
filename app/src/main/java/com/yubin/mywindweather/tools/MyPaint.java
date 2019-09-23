package com.yubin.mywindweather.tools;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import com.yubin.mywindweather.application.MyApplication;
import com.yubin.mywindweather.application.MyConstants;
import com.yubin.mywindweather.modle.element.CloudBean;
import com.yubin.mywindweather.modle.element.LightningBean;
import com.yubin.mywindweather.modle.element.MeteorBean;
import com.yubin.mywindweather.modle.element.MoonBean;
import com.yubin.mywindweather.modle.element.RainBean;
import com.yubin.mywindweather.modle.element.SnowBean;
import com.yubin.mywindweather.modle.element.StarBean;
import com.yubin.mywindweather.modle.element.SunBean;
import com.yubin.mywindweather.modle.element.WaterBean;
import com.yubin.mywindweather.modle.element.WindMillBean;
import com.yubin.mywindweather.util.RandomUtil;

/**
 * Created by YUBIN at 17-11-2 下午5:37
 * Last modified at 17-8-8 上午10:37
 */

/**
 * Created by YUBIN at 17-7-14 下午4:09
 * Last modified at 17-7-14 下午4:09
 * 画笔静态类工具类，用于绘画各种元素
 */

public class MyPaint {

    /**
     * 绘制背景
     *
     * @param canvas
     * @param bitmapBackground
     * @param matrix
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawBackgroundDay(Canvas canvas, Bitmap bitmapBackground, Matrix matrix, int holderWidth, int holderHeight, Paint paint) {
        canvas.drawColor(Color.argb(255, 158, 158, 158));
        if (bitmapBackground != null) {
            float xScale = (float) holderWidth / bitmapBackground.getWidth();
            float yScale = (float) holderHeight / bitmapBackground.getHeight();
            matrix.reset();
            matrix.postScale(xScale, yScale);
            canvas.drawBitmap(bitmapBackground, matrix, paint);
        }

    }

    /**
     * 绘制夜间的背景
     * @param canvas
     * @param bitmapBackground
     * @param matrix
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawBackgroundNight(Canvas canvas, Bitmap bitmapBackground, Matrix matrix, int holderWidth, int holderHeight, Paint paint) {
        canvas.drawColor(Color.argb(255, 66, 66, 66));
        if (bitmapBackground != null) {
            float xScale = (float) holderWidth / bitmapBackground.getWidth();
            float yScale = (float) holderHeight / bitmapBackground.getHeight();
            matrix.reset();
            matrix.postScale(xScale, yScale);
            canvas.drawBitmap(bitmapBackground, matrix, paint);
        }

    }

    /**
     * 绘制天空
     *
     * @param canvas
     * @param bitmapSky
     * @param matrix
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawSky(Canvas canvas, Bitmap bitmapSky, Matrix matrix, int holderWidth, int holderHeight, Paint paint) {
        float xScale = (float) holderWidth / bitmapSky.getWidth();
        float yScale = (float) holderHeight / bitmapSky.getHeight();
        matrix.reset();
        matrix.postScale(xScale, yScale);
        canvas.drawBitmap(bitmapSky, matrix, paint);
    }

    /**
     * 绘制星空
     *
     * @param canvas
     * @param bitmapStarry
     * @param matrix
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawStarry(Canvas canvas, Bitmap bitmapStarry, Matrix matrix, int holderWidth, int holderHeight, Paint paint) {
        float xScale = (float) holderWidth / bitmapStarry.getWidth();
        float yScale = (float) (holderHeight / 4) / bitmapStarry.getHeight();
        matrix.reset();
        matrix.postScale(xScale, yScale);
        canvas.drawBitmap(bitmapStarry, matrix, paint);
    }

    /**
     * 绘制草地
     *
     * @param canvas
     * @param bitmapGrass
     * @param holderWith
     * @param holderHeight
     */
    public static int grassDevide = 3;

    public static void drawGrass(Canvas canvas, Bitmap bitmapGrass, int holderWith, int holderHeight, Paint paint) {
        Rect grassSrcRect = new Rect(0, 0, bitmapGrass.getWidth(), bitmapGrass.getHeight());
        int left = 0;
        int top = holderHeight * (grassDevide - 1) / grassDevide;
        int right = holderWith;
        int bottom = holderHeight;
        Rect grassDesRect = new Rect(left, top, right, bottom);
        canvas.drawBitmap(bitmapGrass, grassSrcRect, grassDesRect, paint);
    }

    /**
     * 绘制远处的草地
     *
     * @param canvas
     * @param bitmapGrass2
     * @param holderWith
     * @param holderHeight
     */
    public static void drawGrass2(Canvas canvas, Bitmap bitmapGrass2, int holderWith, int holderHeight, Paint paint) {
        Rect grassSrcRect = new Rect(0, 0, bitmapGrass2.getWidth(), bitmapGrass2.getHeight());
        int left = 0;
        int top = holderHeight * (grassDevide - 1) / grassDevide;
        int right = holderWith;
        int bottom = top + (holderHeight - top) / 5;
        Rect grassDesRect = new Rect(left, top, right, bottom);
        canvas.drawBitmap(bitmapGrass2, grassSrcRect, grassDesRect, paint);
    }

    /**
     * 绘制旋转的风车组
     *
     * @param windMills
     * @param canvas
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawWindMillGroup(WindMillBean[] windMills, Canvas canvas, int holderWidth, int holderHeight, Paint paint) {
        for (int i = 0; i < windMills.length; i++) {
            MyPaint.drawWindMill(windMills[i], canvas, holderWidth, holderHeight, paint);
        }
    }

    /**
     * 绘制旋转的风车
     *
     * @param windMill
     * @param canvas
     * @param holderWidth
     * @param holderHeight
     */
    public static void drawWindMill(WindMillBean windMill, Canvas canvas, int holderWidth, int holderHeight, Paint paint) {
        Matrix matrixPillar = windMill.getMatrixPillar();
        Matrix matrixWing = windMill.getMatrixWing();

        /**
         * 得到风车的大小
         */
        float xScale = (float) windMill.currentSizeLevel / WindMillBean.maxSizeLevel;
        float yScale = (float) windMill.currentSizeLevel / WindMillBean.maxSizeLevel;

        /**
         * 得到草地的高度和草地开始的高度范围
         */
        int baseTop = holderHeight * (grassDevide - 1) / grassDevide;
        int grassHeight = holderHeight - baseTop;

        /**
         * 计算支杆的左上角坐标
         */
        int xOfPillar = holderWidth * windMill.currentXLocadevide / WindMillBean.maxXLocationdevide;
        int yOfPillar = baseTop + (int) (grassHeight / 3 * yScale) - (int) (windMill.getBitmapPillar().getHeight() / 2 * yScale);

        /**
         * 计算风车左上角的坐标
         */
        int xOfWing = xOfPillar - (int) ((windMill.getBitmapWing().getWidth() - 80) / 2 * xScale);
        int yOfWing = yOfPillar - (int) ((windMill.getBitmapWing().getHeight() - 5) / 2 * yScale);

        /**
         * 计算风车旋转中心点的坐标
         */
        int xCenterWing = xOfWing + (int) (windMill.getBitmapWing().getWidth() / 2 * xScale);
        int yCenterWing = yOfWing + (int) (windMill.getBitmapWing().getHeight() / 2 * yScale);

        /**
         * 锁定支杆的位置
         */
        if (!windMill.isPillarLocationLocked) {
            matrixPillar.reset();
            matrixPillar.postScale(xScale, yScale);
            matrixPillar.postTranslate(xOfPillar, yOfPillar);
            windMill.setMatrixPillar(matrixPillar);
            windMill.isPillarLocationLocked = true;
        }

        /**
         * 旋转风车扇叶
         */
        windMill.setRotateAngle((windMill.getRotateAngle() + windMill.getRotateSpeed()) % 360);
        matrixWing.reset();
        matrixWing.postScale(xScale, yScale);
        matrixWing.postTranslate(xOfWing, yOfWing);
        matrixWing.postRotate(windMill.getRotateAngle(), xCenterWing, yCenterWing);
        windMill.setMatrixWing(matrixWing);

        /**
         * 绘制
         */
        canvas.drawBitmap(windMill.getBitmapPillar(), matrixPillar, windMill.getPaint());
        canvas.drawBitmap(windMill.getBitmapWing(), matrixWing, windMill.getPaint());

    }


    /**
     * 绘制秘密麻麻的雨
     *
     * @param canvas
     * @param rainBeans
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawRains(Canvas canvas, RainBean[] rainBeans, int holderWidth, int holderHeight, Paint paint) {
        for (int i = 0; i < rainBeans.length; i++) {
//          canvas.drawBitmap(rainBeans[i].getBitmap(),rainBeans[i].getLocationX(),rainBeans[i].getLocationY(),paint);
            MyPaint.drawRains(canvas, rainBeans[i], holderWidth, holderHeight, paint);
            rainBeans[i].setLocationY(rainBeans[i].getLocationY() + rainBeans[i].getSpeed());
            rainBeans[i].setLocationX(rainBeans[i].getLocationX());
            if (rainBeans[i].getLocationY() > holderHeight) {
                rainBeans[i].setLocationY(RandomUtil.getRandom().nextInt(holderHeight));
            }
            if (rainBeans[i].getLocationX() < 0) {
                rainBeans[i].setLocationX(RandomUtil.getRandom().nextInt(200));
            }
        }
    }

    /**
     * 绘制阵雨
     *
     * @param canvas
     * @param rainBeans
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    private static int rainTimes=0;
    public static void drawShowerRains(Canvas canvas, RainBean[] rainBeans, int holderWidth, int holderHeight, Paint paint) {
        rainTimes++;
        if((rainTimes%400)<200){
            drawRains(canvas,rainBeans,holderWidth,holderHeight,paint);
        }
        if(rainTimes>MyConstants.maxIntNum){
            rainTimes=0;
        }
    }


    /**
     * 绘制降落的雨
     *
     * @param rain
     * @param canvas
     * @param holderWidth
     * @param holderHeight
     */
    private static void drawRains(Canvas canvas, RainBean rain, int holderWidth, int holderHeight, Paint paint) {
        Matrix matrix = rain.getMatrix();
        float xScale = ((float) holderWidth / rain.getBitmap().getWidth() * (10 + rain.getCurrentSizeOfRain()) / (10 + RainBean.maxSizeOfRain));
        float yScale = xScale;
        matrix.reset();
        matrix.postScale(xScale, yScale);
        matrix.postTranslate(rain.getLocationX(), rain.getLocationY());
        rain.setMatrix(matrix);
        canvas.drawBitmap(rain.getBitmap(), matrix, paint);
    }


    /**
     * 绘制月亮
     *
     * @param moon
     * @param canvas
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawMoon(Canvas canvas, MoonBean moon, int holderWidth, int holderHeight, Paint paint) {
        moon.setLocationX(holderWidth * 7 / 10);
        moon.setLocationY(holderHeight * 1 / 10);
        Matrix matrix = moon.getMatrix();
        matrix.reset();
        float xScale = (float) holderWidth / moon.getBitmap().getWidth() / 5;
        float yScale = xScale;
        matrix.postScale(xScale, yScale);
        matrix.postTranslate(moon.getLocationX(), moon.getLocationY());
        moon.setMatrix(matrix);
        canvas.drawBitmap(moon.getBitmap(), matrix, paint);
    }

    /**
     * 绘制星星
     *
     * @param canvas
     * @param star
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawStar(Canvas canvas, StarBean star, int holderWidth, int holderHeight, Paint paint) {
        Matrix matrix = star.getMatrix();
        matrix.reset();
        matrix.postScale(star.getScale(), star.getScale());
        matrix.postTranslate(star.getLocationX(), star.getLocationY());
        star.setAlpha(star.getAlpha() + 15);
        star.setMatrix(matrix);
        star.getPaint().setAlpha(star.getGoodAlpha());
        canvas.drawBitmap(star.getBitmap(), matrix, star.getPaint());
    }

    /**
     * 绘制一片星星
     *
     * @param canvas
     * @param stars
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawStars(Canvas canvas, StarBean[] stars, int holderWidth, int holderHeight, Paint paint) {
        for (int i = 0; i < stars.length; i++) {
            drawStar(canvas, stars[i], holderWidth, holderHeight, stars[i].getPaint());
        }
    }

    /**
     * 绘制漂浮的云
     *
     * @param clouds
     * @param canvas
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawCloud(Canvas canvas, CloudBean[] clouds, int holderWidth, int holderHeight, Paint paint) {
        for (int i = 0; i < clouds.length; i++) {
            if (clouds[i].isVisible()) {
                canvas.drawBitmap(clouds[i].getBitmap(), clouds[i].getLocationX(), holderHeight * clouds[i].getDevideY() / CloudBean.maxCloudYdivide-100, paint);
                clouds[i].setLocationX(clouds[i].getLocationX() + clouds[i].getSpeed());
                if (clouds[i].getLocationX() > holderWidth) {
                    clouds[i].setLocationX(-clouds[i].getBitmap().getWidth() - RandomUtil.getRandom().nextInt(500));
                    clouds[i].setDevideY(1 + RandomUtil.getRandom().nextInt(5));
                }
            }
        }
    }

    /**
     * 绘制一组水滴
     *
     * @param canvas
     * @param waters
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    private  static int drawTimes=0;
    public static void drawWatersGroup(Canvas canvas, WaterBean[] waters, int holderWidth, int holderHeight, Paint paint) {
        drawTimes=drawTimes+1;
        if (waters[0].currentWaterNum >= 50) {
            waters[0].currentWaterNum = 0;
            waters[0].refrshLocation();
            waters[0].setVisible(RandomUtil.getRandom().nextBoolean());
        }
        if (waters[1].currentWaterNum >= 50) {
            waters[1].currentWaterNum = 0;
            waters[1].refrshLocation();
        }
        if (waters[2].currentWaterNum >= 60) {
            waters[2].currentWaterNum = 0;
            waters[2].refrshLocation();
        }

        for (int i = 0; i < waters.length; i++) {
            if (waters[i].isVisible() && waters[i].currentWaterNum < WaterBean.maxWaterNum) {
                canvas.drawBitmap(waters[i].getBitmap(waters[i].currentWaterNum), holderWidth / 10 * (waters[i].getDevideX()), waters[i].getLocationY(), paint);
                waters[i].setLocationY(waters[i].getLocationY() + waters[i].getSpeed());
            }
//            waters[i].currentWaterNum++;
            if(drawTimes%3==0){
                waters[i].currentWaterNum++;
            }
            if(drawTimes>= MyConstants.maxIntNum){
                drawTimes=0;
            }

        }
    }

    /**
     * 绘制一个水滴
     *
     * @param canvas
     * @param water
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawWater(Canvas canvas, WaterBean water, int holderWidth, int holderHeight, Paint paint) {
        if (water.isVisible() && water.currentWaterNum < water.getBimapSize()) {
            canvas.drawBitmap(water.getBitmap(water.currentWaterNum), holderWidth / 10 * (water.getDevideX()), water.getLocationY(), paint);
            water.setLocationY(water.getLocationY() + water.getSpeed());
        }
    }

    /**
     * 绘制闪电组
     *
     * @param canvas
     * @param lightnings
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawLightnings(Canvas canvas, LightningBean[] lightnings, int holderWidth, int holderHeight, Paint paint) {
        for (int i = 0; i < lightnings.length; i++) {
            lightnings[i].setLightNingFlag(lightnings[i].getLightNingFlag() + 1);
            int temp = lightnings[i].getLightNingFlag() % 100;
            if (temp < 10) {
                lightnings[i].setVisible(true);
                lightnings[i].getPaint().setAlpha(256 / 4 * temp);
                canvas.drawBitmap(lightnings[i].getBitmap(), lightnings[i].getLocationX(), lightnings[i].getLocationY(), lightnings[i].getPaint());
            } else if (temp == 10) {
                lightnings[i].refreshLocation(holderWidth);
            } else {
                lightnings[i].setVisible(false);
                if (lightnings[i].getLightNingFlag() >= 99999999) {
                    lightnings[i].setLightNingFlag(0);
                }
            }

        }
    }

    /**
     * 绘制闪电组
     *
     * @param canvas
     * @param lightnings
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawWonderfulLightnings(Canvas canvas, LightningBean[] lightnings, int holderWidth, int holderHeight, Paint paint,int num,Matrix matrix) {
        for (int i = 0; i < lightnings.length; i++) {
            lightnings[i].getPaint().setAlpha(256 / 4 * (num%4+1));
            matrix.reset();
            matrix.postScale(2,2);
            if(i==0){
                matrix.postTranslate(200,200);
            }else if(i==1){
                matrix.postTranslate(200,100);
            }else if(i==2){
                matrix.postTranslate(400,200);
            }else{
                matrix.postTranslate(150,250);
            }
            canvas.drawBitmap(lightnings[i].getBitmap(),matrix, lightnings[i].getPaint());
        }
    }


    /**
     * 绘制划过天际的流星
     *
     * @param canvas
     * @param meteor
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawMeteor(Canvas canvas, MeteorBean meteor, int holderWidth, int holderHeight, Paint paint) {
        /**
         * 控制流星的出现频率
         */
        meteor.setMeteorFlag(meteor.getMeteorFlag() + 1);
        int temp = meteor.getMeteorFlag() % 200;
        if (temp < 20) {
            meteor.setLocationX(meteor.getLocationX() - meteor.getSpeed());
            meteor.setLocationY(meteor.getLocationY() + meteor.getSpeed() * 2 / 3);
            canvas.drawBitmap(meteor.getBitmap(), meteor.getLocationX(), meteor.getLocationY(), paint);
        } else if (temp == 20) {
            meteor.refreshLocation();
        } else {
            if (meteor.getMeteorFlag() >= 99999999) {
                meteor.setMeteorFlag(0);
            }
        }
    }

    /**
     * 绘制流星雨
     *
     * @param canvas
     * @param meteors
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawMeteors(Canvas canvas, MeteorBean[] meteors, int holderWidth, int holderHeight, Paint paint) {
        for (int i = 0; i < meteors.length; i++) {
            drawMeteor(canvas, meteors[i], holderWidth, holderHeight, paint);
        }
    }

    /**
     * 绘制太阳和旋转的光晕
     *
     * @param canvas
     * @param sun
     * @param holderWidth
     * @param holderHeight
     * @param paint
     */
    public static void drawSun(Canvas canvas, SunBean sun, int holderWidth, int holderHeight, Paint paint) {
        sun.setLocationX(holderWidth - 300);
        sun.setLocationY(300);
        Matrix matrixLeng = sun.getMatrixLeng();
        Matrix matrixGuangyun = sun.getMatrixGuangyun();

        matrixLeng.reset();
        sun.setAngleLeng(sun.getAngleLeng() - MyApplication.sunRotateTime);
        matrixLeng.postScale(2, 2);
        matrixLeng.postTranslate(sun.getLocationX() - sun.getBitmapLeng().getWidth(), sun.getLocationY() - sun.getBitmapLeng().getHeight());
        matrixLeng.postRotate(sun.getAngleLeng() % 360, sun.getLocationX(), sun.getLocationY());
        sun.setMatrixLeng(matrixLeng);

        matrixGuangyun.reset();
        sun.setAngleGuangyun(sun.getAngleGuangyun() + MyApplication.sunRotateTime);
        float temp = Math.abs(((float) ((sun.getAngleGuangyun() + 110) % 180) / 90) - 1);
        float xScale = 1 + temp;
        float yScale = xScale;
        sun.getPaintGuangyun().setAlpha((int) (55 + 200 * temp));
        matrixGuangyun.postScale(xScale, yScale);
        matrixGuangyun.postTranslate(sun.getLocationX() - sun.getBitmapGuangyun().getWidth() / 2 * xScale, sun.getLocationY() - sun.getBitmapGuangyun().getHeight() / 2 * yScale);
        matrixGuangyun.postRotate(sun.getAngleGuangyun() % 360, sun.getLocationX(), sun.getLocationY());
        sun.setMatrixGuangyun(matrixGuangyun);
        canvas.drawBitmap(sun.getBitmapSun(), sun.getLocationX() - sun.getBitmapSun().getWidth() / 2, sun.getLocationY() - sun.getBitmapSun().getHeight() / 2, paint);
        canvas.drawBitmap(sun.getBitmapShexian(), sun.getLocationX() - sun.getBitmapShexian().getWidth() / 2, sun.getLocationY() - sun.getBitmapShexian().getHeight() / 2, paint);
        canvas.drawBitmap(sun.getBitmapLeng(), sun.getMatrixLeng(), paint);
        canvas.drawBitmap(sun.getBitmapGuangyun(), sun.getMatrixGuangyun(), sun.getPaintGuangyun());

    }
    public static void drawSnow(Canvas canvas, SnowBean snow, Matrix matrix,int holderWidth, int holderHeight, Paint paint){
        snow.setLocationY(snow.getLocationY()+snow.getSpeed());
        if(snow.getLocationY()>holderHeight){
            snow.setLocationX(RandomUtil.getRandom().nextInt(holderWidth));
            snow.setLocationY(-(int)(snow.getBitmap().getHeight()*snow.scale));
        }
        if(snow.getType()==SnowBean.sizeMany){
            matrix.reset();
            float scale=(float) holderWidth/snow.getBitmap().getWidth();
            matrix.postScale(scale,scale);
            matrix.postTranslate(0,snow.getLocationY());
            canvas.drawBitmap(snow.getBitmap(),matrix,paint);
            snow.scale=scale;
        }else{
            canvas.drawBitmap(snow.getBitmap(),snow.getLocationX(),snow.getLocationY(),paint);
        }

    }

    public static void drawSnows(Canvas canvas, SnowBean[] snows,Matrix matrix,int holderWidth, int holderHeight, Paint paint){
        for(int i=0;i<snows.length;i++){
            drawSnow(canvas,snows[i],matrix,holderWidth,holderHeight,paint);
        }
    }


}
