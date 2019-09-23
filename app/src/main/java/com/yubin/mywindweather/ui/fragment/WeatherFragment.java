package com.yubin.mywindweather.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.application.MyApplication;
import com.yubin.mywindweather.application.MyConstants;
import com.yubin.mywindweather.modle.weather.DataBean;
import com.yubin.mywindweather.modle.weather.ForecastBean;
import com.yubin.mywindweather.myInterface.GetCityNameListener;
import com.yubin.mywindweather.myInterface.GetWeatherDataListener;
import com.yubin.mywindweather.tools.HttpManager;
import com.yubin.mywindweather.tools.WeatherUtil;
import com.yubin.mywindweather.ui.adapter.WeatherAdapter;
import com.yubin.mywindweather.util.LogUtil;
import com.yubin.mywindweather.util.SharedPreferencesUtil;
import com.yubin.mywindweather.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by YUBIN at 17-11-2 下午4:51
 * Last modified at 17-8-9 上午9:44
 */

/**
 * Created by YUBIN at 17-7-25 下午2:59
 * Last modified at 17-7-25 下午2:59
 */

public class WeatherFragment extends Fragment {
    private View rootView;

    private TextView tvDay1City;
    private TextView tvDay1Time;
    private TextView tvDay1Weather;
    private TextView tvDay1Temper;
    private TextView tvDay1TemperInterval;
    private TextView tvDay1Wind;
    private ImageView ivDay1Weather;
    private RecyclerView recyclerView;
    private WeatherAdapter adapter;

    private String ipAddress;
    private String cityName;
    private DataBean  weatherData=new DataBean();

    private boolean hasGetData=false;
    private final int msg_locationFail=0;
    private final int msg_locationSuccess=1;
    private final int msg_dataError=2;
    private final int msg_getDataError=3;

    private String weatherErrorInfo="";
    private boolean isGetNewTestData=true;

    private Context context;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==msg_locationSuccess){
                if(weatherData!=null){
                    if(weatherData.getForecast()!=null){
                        if(weatherData.getForecast().size()>0){
                            ToastUtil.show(context,weatherData.getCity());
                            hasGetData=true;
                            int weatherFlag=WeatherUtil.getWeatherFlag(weatherData.getForecast().get(0).getType());
                            SharedPreferencesUtil.putInt(MyConstants.currentWeather,weatherFlag);
                            MyApplication.updateWeather=true;
                            refreshView();
                        }
                    }
                }

            }else if(msg.arg1==msg_locationFail){
                ToastUtil.show(context,weatherErrorInfo);
            }else if(msg.arg1==msg_dataError){
                ToastUtil.show(context,weatherErrorInfo);
            }else if(msg.arg1==msg_getDataError){
                ToastUtil.show(context,weatherErrorInfo);
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_weather,null);
        if(!hasGetData){
            weatherData.setForecast(new ArrayList<ForecastBean>());
            initview();
            getData();
        }else {
            initview();
            refreshView();
        }

        return rootView;
    }

    /**
     * 初始化界面
     */
    private void initview(){
        tvDay1City=(TextView)rootView.findViewById(R.id.tv_day1_city);
        tvDay1Time=(TextView)rootView.findViewById(R.id.tv_day1_time);
        tvDay1Weather=(TextView)rootView.findViewById(R.id.tv_day1_weather);
        tvDay1Temper=(TextView)rootView.findViewById(R.id.tv_day1_temper);
        tvDay1TemperInterval=(TextView)rootView.findViewById(R.id.tv_day1_temper_interval);
        tvDay1Wind=(TextView)rootView.findViewById(R.id.tv_day1_wind);
        ivDay1Weather=(ImageView) rootView.findViewById(R.id.iv_day1_weather);
        recyclerView=(RecyclerView) rootView.findViewById(R.id.recycle_view);
        adapter=new WeatherAdapter(getActivity(),weatherData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void refreshView(){
        if(weatherData!=null){
            if(weatherData.getForecast()!=null){
                if(weatherData.getForecast().size()>=1){
                    ivDay1Weather.setImageResource(WeatherUtil.getImageByWeather(weatherData.getForecast().get(0).getType()));
                    tvDay1City.setText(weatherData.getCity());
                    tvDay1Time.setText(weatherData.getForecast().get(0).getDate());
                    tvDay1Weather.setText(weatherData.getForecast().get(0).getType());
                    tvDay1Temper.setText(weatherData.getWendu()+"℃");
                    tvDay1TemperInterval.setText(weatherData.getForecast().get(0).getLow().replace("低温","")+" ～ "+weatherData.getForecast().get(0).getHigh().replace("高温",""));
                    tvDay1Wind.setText(weatherData.getForecast().get(0).getFengli().replace("级",""));
                    adapter.refreshData(weatherData);
                }else{
                    weatherErrorInfo="数据错误！";
                    Message message=Message.obtain();
                    message.arg1=msg_dataError;
                    handler.sendMessage(message);
                }
            }else{
                weatherErrorInfo="数据错误！";
                Message message=Message.obtain();
                message.arg1=msg_dataError;
                handler.sendMessage(message);
            }
        }else{
            weatherErrorInfo="数据错误！";
            Message message=Message.obtain();
            message.arg1=msg_getDataError;
            handler.sendMessage(message);
        }


    }

    private void getData(){
        if(hasGetData){
            return;
        }
        ToastUtil.show(context,"正在定位...");
        if(Looper.myLooper()==null){
            Looper.prepare();
        }

        /**
         * 控制使用随机天气时，每打开五次才更新一次天气
         */
        if(MyApplication.openTimes%5==0){
            isGetNewTestData=true;
        }else{
            isGetNewTestData=false;
        }
        LogUtil.show("MyApplication.openTimes="+MyApplication.openTimes);
        LogUtil.show("isGetNewTestData="+isGetNewTestData);

       new Thread(getCityNameTask).start();
    }


    private Runnable getWeatherDataTask=new Runnable() {

        @Override
        public void run() {
            HttpManager.getWeatherNoVolley(cityName, new GetWeatherDataListener() {
                @Override
                public void onResult(DataBean weather) {
                    weatherData=weather;
                    Message message=Message.obtain();
                    message.arg1=msg_locationSuccess;
                    handler.sendMessage(message);
                }

                @Override
                public void onError(String errorInfo) {
                    weatherErrorInfo=errorInfo;
                    Message message=Message.obtain();
                    message.arg1=msg_dataError;
                    handler.sendMessage(message);
                }
            },isGetNewTestData);
        }
    };

    private Runnable getCityNameTask=new Runnable() {
        @Override
        public void run() {
            ipAddress= HttpManager.GetNetIp();
            LogUtil.show("IP："+ipAddress);
            HttpManager.getCityName(ipAddress, new GetCityNameListener() {
                @Override
                public void onResult(String city) {
                    cityName=city;
                    new Thread(getWeatherDataTask).start();
                }

                @Override
                public void onError(String errorInfo) {
                    weatherErrorInfo=errorInfo;
                    Message message=Message.obtain();
                    message.arg1=msg_locationFail;
                    handler.sendMessage(message);
                }
            });
        }
    };
}
