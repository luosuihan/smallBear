package com.example.administrator.playandroid.model;

import android.content.Context;

import com.example.administrator.playandroid.net.IResponseHandler;
import com.example.administrator.playandroid.net.JsonHandler;
import com.example.administrator.playandroid.net.NetConstants;
import com.example.administrator.playandroid.net.OkHttpUtil;
import com.example.administrator.playandroid.util.LogUtil;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/8/23.
 */

public class HomeModelIml implements HomeModel {

    private OkHttpUtil okHttpUtil;
    private Context mContext;
    private IResponseHandler iResponse;
    public HomeModelIml(Context context){
        LogUtil.e("luosuihan","HomeModelIml()");
        mContext = context;
        okHttpUtil = OkHttpUtil.getSingleOkHttp();
    }

    @Override
    public void onSucceed() {
        portSucceed();
    }

    @Override
    public void onFail(String s) {
        LogUtil.e("luosuihan","onFail() = "+s);
    }

    public void portSucceed() {
        okHttpUtil.get(mContext, NetConstants.HOMELIST, new JsonHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                //数据处理
                LogUtil.e("luosuihan","statusCode = "+statusCode+"  ,response = "+response.toString());
            }
        });
    }
}
