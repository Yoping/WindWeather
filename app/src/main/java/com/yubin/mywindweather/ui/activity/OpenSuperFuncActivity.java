package com.yubin.mywindweather.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.yubin.mywindweather.R;
import com.yubin.mywindweather.application.MyApplication;
import com.yubin.mywindweather.application.MyConstants;
import com.yubin.mywindweather.util.SharedPreferencesUtil;
import com.yubin.mywindweather.util.TestUtil;

/**
 * Created by YUBIN at 17-11-2 下午4:50
 * Last modified at 17-8-2 下午3:14
 */

/**
 * Created by YUBIN at 17-7-28 下午4:33
 * Last modified at 17-7-28 下午4:33
 */

public class OpenSuperFuncActivity extends AppCompatActivity {

    private View layout;
    private TextView tvInfo1;
    private TextView tvInfo2;
    private TextView tvInfo3;
    private TextView tvInfo4;
    private TextView tvInfo5;
    private TextView tvOpenWechat;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_super_func);

        /**
         * 开通白天黑夜轮转等高级功能
         */
        SharedPreferencesUtil.putBoolean(MyConstants.test, true);
        MyApplication.myTest = true;
        MyApplication.updateWeather = true;

        layout = findViewById(R.id.layout_open_super_func);
        tvInfo1 = (TextView) findViewById(R.id.tv_super_func_info_1);
        tvInfo2 = (TextView) findViewById(R.id.tv_super_func_info_2);
        tvInfo3 = (TextView) findViewById(R.id.tv_super_func_info_3);
        tvInfo4 = (TextView) findViewById(R.id.tv_super_func_info_4);
        tvInfo5 = (TextView) findViewById(R.id.tv_super_func_info_5);
        tvOpenWechat = (TextView) findViewById(R.id.tv_open_wechat);


        layout.startAnimation(AnimationUtils.loadAnimation(OpenSuperFuncActivity.this, R.anim.page_turning_for_change_mode));
        ;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvInfo1.startAnimation(AnimationUtils.loadAnimation(OpenSuperFuncActivity.this, R.anim.rotate));
            }
        }, 1500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvInfo2.setVisibility(View.VISIBLE);
                tvInfo2.startAnimation(AnimationUtils.loadAnimation(OpenSuperFuncActivity.this, R.anim.left_in));
            }
        }, 2500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvInfo3.setVisibility(View.VISIBLE);
                tvInfo3.startAnimation(AnimationUtils.loadAnimation(OpenSuperFuncActivity.this, R.anim.right_in));
            }
        }, 2500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvInfo4.setVisibility(View.VISIBLE);
                tvInfo4.startAnimation(AnimationUtils.loadAnimation(OpenSuperFuncActivity.this, R.anim.left_in));
            }
        }, 3000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvInfo5.setVisibility(View.VISIBLE);
                tvInfo5.startAnimation(AnimationUtils.loadAnimation(OpenSuperFuncActivity.this, R.anim.right_in));
            }
        }, 3000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvOpenWechat.setVisibility(View.VISIBLE);
                tvOpenWechat.startAnimation(AnimationUtils.loadAnimation(OpenSuperFuncActivity.this, R.anim.rotate));
            }
        }, 3500);

        tvOpenWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TestUtil.isWeixinAvilible(OpenSuperFuncActivity.this)) {
                    Intent intent = new Intent();
                    ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                    intent.setAction(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(cmp);
                    startActivity(intent);
                } else {
                    Toast.makeText(OpenSuperFuncActivity.this, "居然没有装微信？好尴尬啊...", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
