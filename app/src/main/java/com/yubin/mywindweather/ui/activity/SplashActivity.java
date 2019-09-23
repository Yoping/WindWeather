package com.yubin.mywindweather.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yubin.mywindweather.MainActivity;
import com.yubin.mywindweather.application.MyApplication;

/**
 * Created by YUBIN at 17-11-2 下午4:50
 * Last modified at 17-8-9 上午9:46
 */

/**
 * Created by YUBIN at 17-7-25 下午5:04
 * Last modified at 17-7-25 下午5:04
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.openTimes=MyApplication.openTimes+1;
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
//                Intent intent=new Intent(SplashActivity.this,DebugActivity.class);
                startActivity(intent);
                finish();
            }
        }, 800);
    }
}
