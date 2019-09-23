package com.yubin.mywindweather.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.application.MyApplication;

/**
 * Created by YUBIN at 17-11-2 下午4:50
 * Last modified at 17-8-8 下午2:53
 */

/**
 * Created by YUBIN at 17-7-20 下午12:13
 * Last modified at 17-7-20 下午12:12
 * 对各个元素逐一进行内存分析
 */

public class AnalyseMemoryActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse_memory);

        findViewById(R.id.tv_grass_true).setOnClickListener(this);
        findViewById(R.id.tv_grass2_true).setOnClickListener(this);
        findViewById(R.id.tv_sky_true).setOnClickListener(this);
        findViewById(R.id.tv_stary_true).setOnClickListener(this);
        findViewById(R.id.tv_windmill_true).setOnClickListener(this);
        findViewById(R.id.tv_rains_true).setOnClickListener(this);
        findViewById(R.id.tv_moon_true).setOnClickListener(this);
        findViewById(R.id.tv_stary_true).setOnClickListener(this);
        findViewById(R.id.tv_clouds_true).setOnClickListener(this);
        findViewById(R.id.tv_waters_true).setOnClickListener(this);
        findViewById(R.id.tv_lightnings_true).setOnClickListener(this);
        findViewById(R.id.tv_meteor_true).setOnClickListener(this);
        findViewById(R.id.tv_sun_true).setOnClickListener(this);

        findViewById(R.id.tv_grass_false).setOnClickListener(this);
        findViewById(R.id.tv_grass2_false).setOnClickListener(this);
        findViewById(R.id.tv_sky_false).setOnClickListener(this);
        findViewById(R.id.tv_stary_false).setOnClickListener(this);
        findViewById(R.id.tv_windmill_false).setOnClickListener(this);
        findViewById(R.id.tv_rains_false).setOnClickListener(this);
        findViewById(R.id.tv_moon_false).setOnClickListener(this);
        findViewById(R.id.tv_stary_false).setOnClickListener(this);
        findViewById(R.id.tv_clouds_false).setOnClickListener(this);
        findViewById(R.id.tv_waters_false).setOnClickListener(this);
        findViewById(R.id.tv_lightnings_false).setOnClickListener(this);
        findViewById(R.id.tv_meteor_false).setOnClickListener(this);
        findViewById(R.id.tv_sun_false).setOnClickListener(this);

        refreshView();
    }

    private void refreshView(){
        if(MyApplication.hasGrass){
            findViewById(R.id.tv_grass_true).setBackgroundColor(getResources().getColor(R.color.red));
        }else{
            findViewById(R.id.tv_grass_false).setBackgroundColor(getResources().getColor(R.color.green));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_grass_true:
                MyApplication.hasGrass=true;
                break;
            case R.id.tv_grass_false:
                MyApplication.hasGrass=false;
                break;

            case R.id.tv_grass2_true:
                MyApplication.hasGrass2=true;
                break;
            case R.id.tv_grass2_false:
                MyApplication.hasGrass2=false;
                break;

            case R.id.tv_sky_true:
                MyApplication.hasSky=true;
                break;
            case R.id.tv_sky_false:
                MyApplication.hasSky=false;
                break;

            case R.id.tv_stary_true:
                MyApplication.hasStarry=true;
                break;
            case R.id.tv_stary_false:
                MyApplication.hasStarry=false;
                break;

            case R.id.tv_windmill_true:
                MyApplication.hasWindMill=true;
                break;
            case R.id.tv_windmill_false:
                MyApplication.hasWindMill=false;
                break;

            case R.id.tv_rains_true:
                MyApplication.hasRains=true;
                break;
            case R.id.tv_rains_false:
                MyApplication.hasRains=false;
                break;

            case R.id.tv_moon_true:
                MyApplication.hasMoon=true;
                break;
            case R.id.tv_moon_false:
                MyApplication.hasMoon=false;
                break;

            case R.id.tv_stars_true:
                MyApplication.hasStars=true;
                break;
            case R.id.tv_stars_false:
                MyApplication.hasStars=false;
                break;

            case R.id.tv_clouds_true:
                MyApplication.hasClouds=true;
                break;
            case R.id.tv_clouds_false:
                MyApplication.hasClouds=false;
                break;

            case R.id.tv_waters_true:
                MyApplication.hasWaters=true;
                break;
            case R.id.tv_waters_false:
                MyApplication.hasWaters=false;
                break;

            case R.id.tv_lightnings_true:
                MyApplication.hasLightnings=true;
                break;
            case R.id.tv_lightnings_false:
                MyApplication.hasLightnings=false;
                break;

            case R.id.tv_sun_true:
                MyApplication.hasSun=true;
                break;
            case R.id.tv_sun_false:
                MyApplication.hasSun=false;
                break;

            case R.id.tv_meteor_true:
                MyApplication.hasMeteor=true;
                break;
            case R.id.tv_meteor_false:
                MyApplication.hasMeteor=false;
                break;

        }
        refreshView();
        finish();
    }
}
