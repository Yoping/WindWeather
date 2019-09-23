package com.yubin.mywindweather.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.application.MyApplication;
import com.yubin.mywindweather.application.MyConstants;
import com.yubin.mywindweather.util.SharedPreferencesUtil;

/**
 * Created by YUBIN at 17-11-2 下午4:50
 * Last modified at 17-8-7 上午9:59
 */

/**
 * Created by YUBIN at 17-7-21 下午2:19
 * Last modified at 17-7-21 下午2:19
 * 在各种天气下进行调试
 */

public class DebugActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvSunnyDay;
    private TextView tvRainyDay;
    private TextView tvCloudyDay;
    private TextView tvSunRainyDay;
    private TextView tvLightRainyDay;
    private TextView tvRainstormDay;
    private TextView tvMeteorRainyDay;
    private TextView tvBTDay;
    private TextView tvAnalyseDay;
    private TextView tvDebug;
    private TextView tvSnowyDay;
    private TextView tvBigSnowyDay;
    private TextView tvRainSnowyDay;
    private TextView tvMiddleSnowyDay;
    private TextView tvFogDay;
    private TextView tvFrostDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        tvSunnyDay=(TextView)findViewById(R.id.sunny_day);
        tvRainyDay=(TextView)findViewById(R.id.rainy_day);
        tvCloudyDay=(TextView)findViewById(R.id.cloudy_day);
        tvSunRainyDay=(TextView)findViewById(R.id.sun_rainy_day);
        tvLightRainyDay=(TextView)findViewById(R.id.light_rainy_day);
        tvRainstormDay=(TextView)findViewById(R.id.rainstorm_day);
        tvMeteorRainyDay=(TextView)findViewById(R.id.meteor_rainy_day);
        tvBTDay=(TextView)findViewById(R.id.bt_day);
        tvAnalyseDay=(TextView)findViewById(R.id.analyse_day);
        tvDebug=(TextView)findViewById(R.id.debug);
        tvBigSnowyDay=(TextView)findViewById(R.id.big_snowy_day);
        tvSnowyDay=(TextView)findViewById(R.id.snowy_day);
        tvRainSnowyDay=(TextView)findViewById(R.id.rain_snowy_day);
        tvMiddleSnowyDay=(TextView)findViewById(R.id.middle_snowy_day);
        tvFogDay=(TextView)findViewById(R.id.fog_day);
        tvFrostDay=(TextView)findViewById(R.id.frost_day);
        setListener();
    }

    private void setListener(){
        tvSunnyDay.setOnClickListener(this);
        tvRainyDay.setOnClickListener(this);
        tvCloudyDay.setOnClickListener(this);
        tvSunRainyDay.setOnClickListener(this);
        tvLightRainyDay.setOnClickListener(this);
        tvRainstormDay.setOnClickListener(this);
        tvMeteorRainyDay.setOnClickListener(this);
        tvBTDay.setOnClickListener(this);
        tvAnalyseDay.setOnClickListener(this);
        tvDebug.setOnClickListener(this);
        tvSnowyDay.setOnClickListener(this);
        tvBigSnowyDay.setOnClickListener(this);
        tvRainSnowyDay.setOnClickListener(this);
        tvMiddleSnowyDay.setOnClickListener(this);
        tvFogDay.setOnClickListener(this);
        tvFrostDay.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.sunny_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather, MyApplication.SunnyDay);
                MyApplication.currentWeather=MyApplication.SunnyDay;
                break;
            case  R.id.rainy_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather,MyApplication.RainyDay);
                MyApplication.currentWeather=MyApplication.RainyDay;
                break;
            case  R.id.cloudy_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather,MyApplication.CloudyDay);
                MyApplication.currentWeather=MyApplication.CloudyDay;
                break;
            case  R.id.sun_rainy_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather,MyApplication.SunRainyDay);
                MyApplication.currentWeather=MyApplication.SunRainyDay;
                break;
            case  R.id.light_rainy_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather,MyApplication.LightShowerRainyDay);
                MyApplication.currentWeather=MyApplication.LightShowerRainyDay;
                break;
            case  R.id.rainstorm_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather,MyApplication.RainstormDay);
                MyApplication.currentWeather=MyApplication.RainstormDay;
                break;
            case  R.id.meteor_rainy_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather,MyApplication.MeteorRainyDay);
                MyApplication.currentWeather=MyApplication.MeteorRainyDay;
                break;
            case  R.id.bt_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather,MyApplication.BTDay);
                MyApplication.currentWeather=MyApplication.BTDay;
                break;
            case  R.id.analyse_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather,MyApplication.AnalyseDay);
                MyApplication.currentWeather=MyApplication.AnalyseDay;
                break;
            case  R.id.debug:
                Intent intent=new Intent(DebugActivity.this, AnalyseMemoryActivity.class);
                startActivity(intent);
                break;
            case  R.id.snowy_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather,MyApplication.SnowyDay);
                MyApplication.currentWeather=MyApplication.SnowyDay;
                break;
            case  R.id.big_snowy_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather,MyApplication.BigSnowyDay);
                MyApplication.currentWeather=MyApplication.BigSnowyDay;
                break;
            case  R.id.rain_snowy_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather,MyApplication.RainSnowyDay);
                MyApplication.currentWeather=MyApplication.RainSnowyDay;
                break;
            case  R.id.middle_snowy_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather,MyApplication.MiddleSnowyDay);
                MyApplication.currentWeather=MyApplication.MiddleSnowyDay;
                break;
            case  R.id.fog_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather,MyApplication.FogDay);
                MyApplication.currentWeather=MyApplication.FogDay;
                break;
            case  R.id.frost_day:
                SharedPreferencesUtil.putInt(MyConstants.currentWeather,MyApplication.FrostDay);
                MyApplication.currentWeather=MyApplication.FrostDay;
                break;
        }
        finish();
    }
}
