package com.yubin.mywindweather.modle.day;

import android.content.Context;
import android.graphics.Canvas;

/**
 * Created by YUBIN at 17-7-19 下午3:33
 * Last modified at 17-7-19 下午3:33
 */

public abstract class Day {
    /**
     * 初始化白天的场景
     * @param context
     */
    public abstract void initDay(Context context);

    /**
     * 初始化夜间的场景
     * @param context
     */
    public abstract void initNight(Context context);

    /**
     * 绘制场景
     * @param canvas
     * @param holderWith
     * @param holderHeight
     */
    public abstract void draw(Canvas canvas, int holderWith, int holderHeight);

    /**
     * 手动加速释放部分内存，白天、夜间场景切换时内部调用
     */
    public abstract void releasePartMemory();

    /**
     * 手动加速释放所有内存，切换不同天气场景时外部调用
     */
    public abstract void releaseAllMemory();
}
