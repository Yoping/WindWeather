package com.yubin.mywindweather.wxapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.yubin.mywindweather.R;
import com.yubin.mywindweather.ui.activity.ShareActivity;
import com.yubin.mywindweather.util.LogUtil;
import com.yubin.mywindweather.util.ToastUtil;

/**
 * Created by YUBIN at 17-10-24 下午5:27
 * Last modified at 17-10-24 下午5:27
 */

public class WXEntryActivity extends AppCompatActivity  implements IWXAPIEventHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        LogUtil.show("--WXEntryActivity--onCreate--");
        ShareActivity.api.handleIntent(getIntent(), this); //处理微信传回的Intent,当然你也可以在别的地方处理
    }

    @Override
    public void onReq(BaseReq baseReq) {
        LogUtil.show("-------- onReq --------");
        LogUtil.show("--WXEntryActivity--openId--"+baseReq.openId);
        LogUtil.show("--WXEntryActivity--transaction--"+baseReq.transaction);
        LogUtil.show("--WXEntryActivity--checkArgs--"+baseReq.checkArgs());
        LogUtil.show("--WXEntryActivity--getType--"+baseReq.getType());
    }

    @Override
    public void onResp(BaseResp resp) {
        //在这个方法中处理微信传回的数据
        LogUtil.show("-------- onResp --------");
        LogUtil.show("--onResp--openId--"+resp.openId);
        LogUtil.show("--onResp--transaction--"+resp.transaction);
        LogUtil.show("--onResp--checkArgs--"+resp.checkArgs());
        LogUtil.show("--onResp--getType--"+resp.getType());
        LogUtil.show("--onResp--errStr--"+resp.errStr);
        LogUtil.show("--onResp--errCode--"+resp.errCode);

        //形参resp 有下面两个个属性比较重要
        //1.resp.errCode
        //2.resp.transaction则是在分享数据的时候手动指定的字符创,用来分辨是那次分享(参照4.中req.transaction)
        switch (resp.errCode) { //根据需要的情况进行处理
            case BaseResp.ErrCode.ERR_OK:
                //正确返回
                ToastUtil.show(WXEntryActivity.this,"分享成功！");
                LogUtil.show("ErrCode=ERR_OK "+BaseResp.ErrCode.ERR_OK);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                LogUtil.show("ErrCode=ERR_USER_CANCEL "+BaseResp.ErrCode.ERR_USER_CANCEL);
                //用户取消
                break;
//            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                //认证被否决
//                break;
//            case BaseResp.ErrCode.ERR_SENT_FAILED:
//                //发送失败
//                break;
//            case BaseResp.ErrCode.ERR_UNSUPPORT:
//                //不支持错误
//                break;
//            case BaseResp.ErrCode.ERR_COMM:
//                //一般错误
//                break;
            default:
                LogUtil.show("ErrCode=default");
                break;
        }
//        Intent intent = new Intent(this, xxx.class);
//        intent.putExtra("errCode", resp.errCode);
//        intent.putExtra("errStr", resp.transaction);
//        startActivity(intent);
        finish();
    }
}
