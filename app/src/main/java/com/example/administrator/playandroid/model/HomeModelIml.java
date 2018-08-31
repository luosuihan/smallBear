package com.example.administrator.playandroid.model;

import android.content.Context;

import com.example.administrator.playandroid.bean.HomeListBean;
import com.example.administrator.playandroid.net.IResponseHandler;
import com.example.administrator.playandroid.net.JsonHandler;
import com.example.administrator.playandroid.net.NetConstants;
import com.example.administrator.playandroid.net.OkHttpUtil;
import com.example.administrator.playandroid.util.LogUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/23.
 */

public class HomeModelIml implements HomeModel {

    private OkHttpUtil okHttpUtil;
    private Context mContext;
    private IResponseHandler iResponse;
    public Map<String, String> params = new HashMap<String, String>();
    public HomeModelIml(Context context){
        LogUtil.e("luosuihan","HomeModelIml()");
        mContext = context;
        okHttpUtil = OkHttpUtil.getSingleOkHttp();
        params.put("","");
    }

    @Override
    public void onSucceed() {
        portSucceed();
    }

    @Override
    public void onFail(String s) {
        LogUtil.e("luosuihan","onFail() = "+s);
    }

    public ArrayList<HomeListBean.HDatas> portSucceed() {
        final ArrayList<HomeListBean.HDatas> data = new ArrayList<HomeListBean.HDatas>();
        okHttpUtil.getUrl(mContext, NetConstants.HOMELIST,params,new JsonHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                String s = response.toString();
                LogUtil.e("s = "+s);
            }

            @Override
            public void onFailure(int statusCode, String error) {

            }

            @Override
            public void onProgress(long currentBytes, long totalBytes) {

            }
        });
        return data;
    }
}
