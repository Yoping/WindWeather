package com.yubin.mywindweather.ui.fragment;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.application.MyApplication;
import com.yubin.mywindweather.application.MyConstants;
import com.yubin.mywindweather.util.SharedPreferencesUtil;
import com.yubin.mywindweather.util.ToastUtil;
import com.yubin.mywindweather.wallpaperService.MyWallpaperService;

/**
 * Created by YUBIN at 17-11-2 下午4:51
 * Last modified at 17-8-10 上午10:23
 */

/**
 * Created by YUBIN at 17-7-18 下午3:27
 * Last modified at 17-7-18 下午3:27
 */

public class SettingFragment extends Fragment implements View.OnClickListener{
    private View rootView;
    private TextView tvSetAsWallpaper;
    private RadioGroup radioGroup;
    private RadioButton radioBtnOpen;
    private RadioButton radioBtnClose;
    private RadioGroup radioGroupRandomWeather;
    private RadioButton radioBtnOpenRandomWeather;
    private RadioButton radioBtnCloseRandomWeather;

    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_setting,null);

        tvSetAsWallpaper=(TextView)rootView.findViewById(R.id.tv_set_as_wallpaper);

        radioGroup=(RadioGroup)rootView.findViewById(R.id.radio_group_hardware_speed_up);
        radioBtnOpen=(RadioButton) rootView.findViewById(R.id.radio_btn_open_hardware_speed_up);
        radioBtnClose=(RadioButton) rootView.findViewById(R.id.radio_btn_close_hardware_speed_up);

        radioGroupRandomWeather=(RadioGroup)rootView.findViewById(R.id.radio_group_random_weather);
        radioBtnOpenRandomWeather=(RadioButton)rootView.findViewById(R.id.radio_btn_open_random_weather);
        radioBtnCloseRandomWeather=(RadioButton)rootView.findViewById(R.id.radio_btn_close_random_weather);

        if(MyApplication.useHardwareSpeedUp){
            radioBtnOpen.setChecked(true);
            radioBtnClose.setChecked(false);
        }else{
            radioBtnOpen.setChecked(false);
            radioBtnClose.setChecked(true);
        }

        if(MyApplication.useRandomWeather){
            radioBtnOpenRandomWeather.setChecked(true);
            radioBtnCloseRandomWeather.setChecked(false);
        }else{
            radioBtnOpenRandomWeather.setChecked(false);
            radioBtnCloseRandomWeather.setChecked(true);
        }



        tvSetAsWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToWallPaper(getActivity());
            }
        });

        radioGroupRandomWeather.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==radioBtnOpenRandomWeather.getId()){
                    MyApplication.useRandomWeather=true;
                    SharedPreferencesUtil.putBoolean(MyConstants.useRandomWeather,true);
                }else{
                    MyApplication.useRandomWeather=false;
                    SharedPreferencesUtil.putBoolean(MyConstants.useRandomWeather,false);
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==radioBtnOpen.getId()){
                    MyApplication.useHardwareSpeedUp=true;
                    SharedPreferencesUtil.putBoolean(MyConstants.useHardwareSpeedUp,true);
                }else{
                    MyApplication.useHardwareSpeedUp=false;
                    SharedPreferencesUtil.putBoolean(MyConstants.useHardwareSpeedUp,false);
                }
                if(Build.VERSION.SDK_INT<23){
                    ToastUtil.show(context,"您的系统低于6.0，缺少本壁纸硬件加速所需要的API！");
                }
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {
    }

    public static void setToWallPaper(Context context) {
        try {
            final Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
            intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                    new ComponentName(context,MyWallpaperService.class));
            context.startActivity(intent);
        }catch (Exception ex){
            ex.printStackTrace();
            ToastUtil.show(context,"系统版本较低，无法使用快捷设置，请到系统设置中进行设置...");
        }

    }


}
