package com.yubin.mywindweather.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.modle.weather.DataBean;
import com.yubin.mywindweather.util.LogUtil;
import com.yubin.mywindweather.tools.WeatherUtil;

/**
 * Created by YUBIN at 17-11-2 下午4:52
 * Last modified at 17-7-26 下午12:03
 */

/**
 * Created by YUBIN at 17-7-25 下午5:24
 * Last modified at 17-7-25 下午5:24
 */

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private DataBean data;
    private Context context;
    private LayoutInflater inflater;
    public WeatherAdapter(Context context,DataBean data){
        this.context=context;
        this.inflater=LayoutInflater.from(context);
        this.data=data;
    }

    public void refreshData(DataBean data){
        this.data=data;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_temper,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LogUtil.show("position="+position+"  "+data.getForecast().get(position).getType());
        if(position==0){
            ((MyViewHolder)holder).ivImage.setImageResource(WeatherUtil.getImageByWeather(data.getYesterday().getType()));
            ((MyViewHolder)holder).tvTime.setText("昨天");
            ((MyViewHolder)holder).tvWeather.setText(data.getYesterday().getType());
//            ((MyViewHolder)holder).tvWeather.setText(data.getYesterday().getType()+"     "+ data.getYesterday().getFl().replace("级",""));
            ((MyViewHolder)holder).tvTemperInterval.setText(data.getYesterday().getLow().replace("低温","")+" ～ "+ data.getYesterday().getHigh().replace("高温",""));
        }else{
            ((MyViewHolder)holder).ivImage.setImageResource(WeatherUtil.getImageByWeather(data.getForecast().get(position).getType()));
            ((MyViewHolder)holder).tvTime.setText(data.getForecast().get(position).getDate().replace("日","日\t"));
            ((MyViewHolder)holder).tvWeather.setText(data.getForecast().get(position).getType()+"     "+ data.getForecast().get(position).getFengli().replace("级",""));
            ((MyViewHolder)holder).tvTemperInterval.setText(data.getForecast().get(position).getLow().replace("低温","")+" ～ "+ data.getForecast().get(position).getHigh().replace("高温",""));
        }


    }

    @Override
    public int getItemCount() {
        return data.getForecast().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private View layout;
        private ImageView ivImage;
        private TextView tvTime;
        private TextView tvWeather;
        private TextView tvTemperInterval;
        public MyViewHolder(View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.layout_item_weather);
            ivImage=(ImageView)itemView.findViewById(R.id.iv_item_weather);
            tvTime=(TextView)itemView.findViewById(R.id.tv_item_time);
            tvWeather=(TextView)itemView.findViewById(R.id.tv_item_weather);
            tvTemperInterval=(TextView)itemView.findViewById(R.id.tv_item_temper_interval);


        }
    }
}
