package com.yubin.mywindweather.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yubin.mywindweather.util.DialogUtil;
import com.yubin.mywindweather.ui.activity.DebugActivity;
import com.yubin.mywindweather.R;
import com.yubin.mywindweather.application.MyApplication;
import com.yubin.mywindweather.ui.activity.OpenSuperFuncActivity;
import com.yubin.mywindweather.ui.activity.ShareActivity;
import com.yubin.mywindweather.util.LogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by YUBIN at 17-11-2 下午4:51
 * Last modified at 17-10-24 下午5:16
 */

/**
 * Created by YUBIN at 17-7-18 下午3:28
 * Last modified at 17-7-18 下午3:28
 */

public class IntroductionFragment extends Fragment {
    private int flag = 0;
    private View rootView;
    private TextView tvFlag;
    private TextView tvShare;
    private ImageView ivCodeImage;
    public static final int MyPermissionRequestCode = 100;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_introduction, null);
        tvFlag = (TextView) rootView.findViewById(R.id.tvFlag);
        tvShare=(TextView)rootView.findViewById(R.id.tv_share);
        ivCodeImage = (ImageView) rootView.findViewById(R.id.iv_code_image);

        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ShareActivity.class);
                getActivity().startActivity(intent);
            }
        });

        tvFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = flag + 1;
                if (flag >= 6) {
                    Intent intent = new Intent(getActivity(), DebugActivity.class);
                    getActivity().startActivity(intent);
                    MyApplication.isRelease = !MyApplication.isRelease;
                    if (MyApplication.isRelease) {
                        Toast.makeText(getActivity(), "release", Toast.LENGTH_SHORT).show();
                        MyApplication.getWeatherInterval=3600*1000*2;
                    } else {
                        Toast.makeText(getActivity(), "debug", Toast.LENGTH_SHORT).show();
                        MyApplication.getWeatherInterval=10*1000*2;
                    }
                    flag = 0;
                }
            }
        });

        ivCodeImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                DialogUtil.showTwoButtonDialog(getActivity(), "你确定要打赏作者吗？", "确定", "不了", true, new DialogUtil.OnClickLeftListener() {
                    @Override
                    public void onClick() {
                        if(Build.VERSION.SDK_INT>=23){
                            int permission=ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE);
                            if(permission!=PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MyPermissionRequestCode);
                            }else{
                                letsGo();
                            }
                        }else{
                            letsGo();
                        }
                    }
                }, new DialogUtil.OnClickRightListener() {
                    @Override
                    public void onClick() {

                    }
                });
                return false;
            }
        });

        return rootView;
    }



    public void letsGo() {
        Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.wechat_pay);
        saveImageToGallery(getActivity(), bitmap);
        Intent intent = new Intent(getActivity(), OpenSuperFuncActivity.class);
        getActivity().startActivity(intent);
    }

    /**
     * 保存图片
     *
     * @param context
     * @param bmp
     */
    public void saveImageToGallery(Context context, Bitmap bmp) {

        /**
         * 公共目录
         * 路径：/storage/emulated/0/wind_weather/pay_image.jpg
         */
        File appDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "wind_weather");
        if (!appDir.exists()) {
            appDir.mkdir();
        }

        /**
         * APP私有文件目录
         * 路径：/data/data/com.yubin.mywindweather/files/pay_image.jpg
         */
//        File appDir = getActivity().getFilesDir();

        /**
         * 公共图片目录
         * 路径：/storage/emulated/0/Pictures/pay_image.jpg
         */
//        File appDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        String fileName = "pay_image.jpg";
        File file = new File(appDir, fileName);
        try {
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(context, "图片保存成功！", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "图片保存失败，请截图保存...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(context, "图片保存失败，请截图保存...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        LogUtil.show("image path=" + file.getPath());
        notifyAmountImage(file.getPath());
    }

    /**
     * 通知图库更新数据
     *
     * @param imagePath
     */
    private void notifyAmountImage(String imagePath) {
        File imageFile = new File(imagePath);
        Uri localUri = Uri.fromFile(imageFile);
        Intent localIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri);
        getActivity().sendBroadcast(localIntent);
    }
}
