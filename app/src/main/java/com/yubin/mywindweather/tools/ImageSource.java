package com.yubin.mywindweather.tools;

import com.yubin.mywindweather.R;

/**
 * Created by YUBIN at 17-11-2 下午5:37
 * Last modified at 17-7-19 下午2:03
 */

/**
 * Created by YUBIN at 17-7-11 下午2:48
 * Last modified at 17-7-11 下午2:48
 */

public class ImageSource {

    /**
     * 小雨滴
     */
    public   static int[] imageWaterDropAId={R.mipmap.waterdrop_a_0,R.mipmap.waterdrop_a_1,R.mipmap.waterdrop_a_2,R.mipmap.waterdrop_a_3,R.mipmap.waterdrop_a_4,
            R.mipmap.waterdrop_a_5,R.mipmap.waterdrop_a_6,R.mipmap.waterdrop_a_7,R.mipmap.waterdrop_a_8,R.mipmap.waterdrop_a_9,
            R.mipmap.waterdrop_a_10,R.mipmap.waterdrop_a_11,R.mipmap.waterdrop_a_12,R.mipmap.waterdrop_a_13,R.mipmap.waterdrop_a_14,
            R.mipmap.waterdrop_a_15,R.mipmap.waterdrop_a_16,R.mipmap.waterdrop_a_17,R.mipmap.waterdrop_a_18,R.mipmap.waterdrop_a_19,
            R.mipmap.waterdrop_a_20,R.mipmap.waterdrop_a_21,R.mipmap.waterdrop_a_22,R.mipmap.waterdrop_a_23,R.mipmap.waterdrop_a_24};
    public static int getImageWaterDropAId(int num){
        return imageWaterDropAId[num];
    }

    /**
     * 大雨滴
     */
    public   static int[] imageWaterDropBId={R.mipmap.waterdrop_b_0,R.mipmap.waterdrop_b_1,R.mipmap.waterdrop_b_2,R.mipmap.waterdrop_b_3,R.mipmap.waterdrop_b_4,
            R.mipmap.waterdrop_b_5,R.mipmap.waterdrop_b_6,R.mipmap.waterdrop_b_7,R.mipmap.waterdrop_b_8,R.mipmap.waterdrop_b_9,
            R.mipmap.waterdrop_b_10,R.mipmap.waterdrop_b_11,R.mipmap.waterdrop_b_12,R.mipmap.waterdrop_b_13,R.mipmap.waterdrop_b_14,
            R.mipmap.waterdrop_b_15,R.mipmap.waterdrop_b_16,R.mipmap.waterdrop_b_17,R.mipmap.waterdrop_b_18,R.mipmap.waterdrop_b_19,
            R.mipmap.waterdrop_b_20,R.mipmap.waterdrop_b_21,R.mipmap.waterdrop_b_22,R.mipmap.waterdrop_b_23,R.mipmap.waterdrop_b_24};
    public static int getImageWaterDropBId(int num){
        return imageWaterDropBId[num];
    }


    /**
     * 白云
     */
    private   static int[] imageCouldWhite={R.mipmap.cloud_a_01,R.mipmap.cloud_b_01};
    public static int getImageCouldWhite(int num){
        return imageCouldWhite[num];
    }
    /**
     * 灰云
     */
    private   static int[] imageCouldGrey={R.mipmap.cloud_a_02,R.mipmap.cloud_b_02};
    public static int getImageCouldGrey(int num){
        return imageCouldGrey[num];
    }
    /**
     * 乌云
     */
    private   static int[] imageCouldBlack={R.mipmap.cloud_a_03,R.mipmap.cloud_b_03};
    public static int getImageCouldBlack(int num){
        return imageCouldBlack[num];
    }


}
