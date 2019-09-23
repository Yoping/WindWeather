package com.yubin.mywindweather.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.yubin.mywindweather.R;
import com.yubin.mywindweather.application.MyConfig;
import com.yubin.mywindweather.util.LogUtil;
import com.yubin.mywindweather.util.TestUtil;
import com.yubin.mywindweather.util.ToastUtil;
import com.yubin.mywindweather.util.Util;

/**
 * Created by YUBIN at 17-10-24 下午5:02
 * Last modified at 17-10-24 下午4:55
 * <p>
 * Created by YUBIN at 17-11-2 下午4:50
 * Last modified at 17-10-27 下午5:38
 */

/**
 * Created by YUBIN at 17-11-2 下午4:50
 * Last modified at 17-10-27 下午5:38
 */

/**
 * Created by YUBIN  at 17-9-15 上午11:18
 * Last modified at 17-9-15 上午11:18
 */

public class ShareActivity extends AppCompatActivity {

    private ImageView ivBack;
    private Tencent mTencent;
    private MyQQShareListener myQQShareListener;
    public static IWXAPI api;      //这个对象是专门用来向微信发送数据的一个重要接口,使用强引用持有,所有的信息发送都是基于这个对象的
    private Handler handler = new Handler();

    private TextView tvShareInfo;
    private ImageView ivShareToQQ;
    private ImageView ivShareToQzone;
    private ImageView ivShareToWechat;
    private ImageView ivShareToFriendZone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        mTencent = Tencent.createInstance(MyConfig.QQ_SHARE_SDK_KEY, ShareActivity.this);
        initview();
        setListener();
        registerWeChat(ShareActivity.this);
    }

    private void initview() {
        tvShareInfo = (TextView) findViewById(R.id.tv_share_info);
        ivBack = (ImageView) findViewById(R.id.iv_share_back);
        ivShareToQQ = (ImageView) findViewById(R.id.iv_share_to_qq);
        ivShareToQzone = (ImageView) findViewById(R.id.iv_share_to_qzone);
        ivShareToWechat = (ImageView) findViewById(R.id.iv_share_to_wechat);
        ivShareToFriendZone = (ImageView) findViewById(R.id.iv_share_to_friend_zone);

        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.share1);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.share2);
        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.share3);
        Animation animation4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.share4);
        ivShareToQQ.startAnimation(animation1);
        ivShareToWechat.startAnimation(animation2);
        ivShareToQzone.startAnimation(animation3);
        ivShareToFriendZone.startAnimation(animation4);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                tvShareInfo.startAnimation(animation);
                tvShareInfo.setVisibility(View.VISIBLE);
            }
        }, 1200);
    }


    private void setListener() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivShareToQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(ShareActivity.this, "请稍候...");
                shareToQQ(false);
            }
        });

        ivShareToQzone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(ShareActivity.this, "请稍候...");
                shareToQQ(true);
            }
        });

        ivShareToWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(ShareActivity.this, "请稍候...");
                shareToWechat(false);

            }
        });

        ivShareToFriendZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(ShareActivity.this, "请稍候...");
                shareToWechat(true);
            }
        });

        myQQShareListener = new MyQQShareListener() {
            @Override
            public void onError(UiError uiError) {
                super.onError(uiError);
                LogUtil.show(" errorDetail :" + uiError.errorDetail);
                LogUtil.show(" errorMessage :" + uiError.errorMessage);
            }

            @Override
            public void onCancel() {
                super.onCancel();
            }

            @Override
            public void onComplete(Object o) {
                super.onComplete(o);
                //{"ret":0}
                LogUtil.show(" onComplete :" + o.toString());
                ToastUtil.show(ShareActivity.this, "分享成功！");
            }
        };
    }

    public void shareToQQ(boolean isQzone) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "多风的天气");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "动态壁纸，随着你所在地天气的变化和白天黑夜的轮转将会出现阴、晴、雨、雪、雾、霾、霜、冰、雷、电、风、暴等24种不断变换的动态天气场景。");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://sj.qq.com/myapp/detail.htm?apkName=com.yubin.mywindweather");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://pp.myapp.com/ma_pic2/0/shot_52507078_4_1502254360/550");
        if (isQzone) {
            params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        } else {
            params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
        }
        mTencent.shareToQQ(ShareActivity.this, params, myQQShareListener);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * QQ分享回调
         */
        if (null != mTencent) {
            mTencent.onActivityResultData(requestCode, resultCode, data, myQQShareListener);
        }

    }


    private class MyQQShareListener implements IUiListener {

        @Override
        public void onComplete(Object o) {


        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(UiError uiError) {

        }
    }


    /**
     * 注册微信分享
     * @param context
     */
    public void registerWeChat(Context context) {   //向微信注册app
        api = WXAPIFactory.createWXAPI(context, MyConfig.Wechat_SHARE_SDK_KEY, true);
        api.registerApp(MyConfig.Wechat_SHARE_SDK_KEY);

    }

    /**
     * 分享到微信
     * @param isSharedToFriendZone
     */
    public void shareToWechat(boolean isSharedToFriendZone) {

        if (!TestUtil.isWeixinAvilible(ShareActivity.this)) {
            ToastUtil.show(ShareActivity.this, "没有检测到微信程序...");
            return;
        }
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = "http://sj.qq.com/myapp/detail.htm?apkName=com.yubin.mywindweather";

        WXMediaMessage msg = new WXMediaMessage(webpageObject);  //这个对象是用来包裹发送信息的对象
        msg.title = "多风的天气";
        msg.description = "动态壁纸，随着你所在地天气的变化和白天黑夜的轮转将会出现阴、晴、雨、雪、雾、霾、霜、冰、雷、电、风、暴等24种不断变换的动态天气场景。";
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo108x108);
        msg.thumbData = Util.bmpToByteArray(bitmap, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();    //创建一个请求对象
        req.transaction = "webpage";  //这个tag要唯一,用于在回调中分辨是哪个分享请求
        req.message = msg;  //把msg放入请求对象中
        if (isSharedToFriendZone) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;    //设置发送到朋友圈
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;   //设置发送给朋友
        }
        boolean res = api.sendReq(req);   //如果调用成功微信,会返回true
        LogUtil.show("res = " + res);
    }


}
