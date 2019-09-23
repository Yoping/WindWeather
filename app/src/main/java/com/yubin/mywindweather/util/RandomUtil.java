package com.yubin.mywindweather.util;

import java.util.Random;

/**
 * Created by YUBIN at 17-7-11 下午4:25
 * Last modified at 17-7-11 下午4:25
 */

public class RandomUtil {
    public static Random random;
    public static Random getRandom(){
        if(random==null){
            random=new Random();
        }
        return random;
    }
}
