package com.yubin.mywindweather;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.yubin.mywindweather.ui.fragment.IntroductionFragment;
import com.yubin.mywindweather.ui.fragment.SettingFragment;
import com.yubin.mywindweather.ui.fragment.WeatherFragment;
import com.yubin.mywindweather.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private MyTabLayoutFragmentPagerAdapter adapter;
    private IntroductionFragment introFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.show("Since it is learning exchanges, no charge, I will not confuse the code, do not use it to gaoshiqing is ok.");
//        Intent intent = new Intent(this,LiveWallpaper.class);
//        //  启动设置的动态壁纸的Service
//        startService(intent);
        setContentView(R.layout.activity_main);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        viewPager=(ViewPager) findViewById(R.id.viewpager);

        titleList=new ArrayList<>();
        titleList.add("天 气");
        titleList.add("设 置");
        titleList.add("简 介");
        fragmentList=new ArrayList<>();
        fragmentList.add(new WeatherFragment());
        fragmentList.add(new SettingFragment());
        introFragment=new IntroductionFragment();
        fragmentList.add(introFragment);

        adapter=new MyTabLayoutFragmentPagerAdapter(getSupportFragmentManager(),fragmentList,titleList);

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTabsFromPagerAdapter(adapter);

    }

    class MyTabLayoutFragmentPagerAdapter extends FragmentPagerAdapter{
        private List<Fragment> fragmentList;
        private List<String> titleList;
        public MyTabLayoutFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragmentList,List<String> titleList) {
            super(fm);
            this.fragmentList=fragmentList;
            this.titleList=titleList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }


        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        LogUtil.show("-----------onRequestPermissionsResult---------------");
        LogUtil.show("requestCode="+requestCode);
        for(int i=0;i<permissions.length;i++){
            LogUtil.show("permissions"+i+"="+permissions[i]);
        }
        for(int i=0;i<grantResults.length;i++){
            LogUtil.show("grantResults"+i+"="+grantResults[i]);
        }
        if(requestCode==IntroductionFragment.MyPermissionRequestCode){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                introFragment.letsGo();
            }else{
                Toast.makeText(MainActivity.this,"权限被拒绝...",Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
