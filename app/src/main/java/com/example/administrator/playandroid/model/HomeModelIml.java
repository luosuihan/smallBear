package com.example.administrator.playandroid.model;

import android.content.Context;

import com.example.administrator.playandroid.bean.HomeBean;
import com.example.administrator.playandroid.bean.HomeListBean;
import com.example.administrator.playandroid.net.IResponseHandler;
import com.example.administrator.playandroid.net.JsonHandler;
import com.example.administrator.playandroid.net.NetConstants;
import com.example.administrator.playandroid.net.OkHttpUtil;
import com.example.administrator.playandroid.util.LogUtil;
import com.example.administrator.playandroid.util.StorageDataUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/23.
 */

public class HomeModelIml implements HomeModel {

    private OkHttpUtil okHttpUtil;
    private Context mContext;
    private IResponseHandler iResponse;
    public Map<String, String> params = new HashMap<String, String>();
    private static final String HOMELISTKEY = "homelistkey";
    public HomeModelIml(Context context) {
        LogUtil.e("luosuihan", "HomeModelIml()");
        mContext = context;
        okHttpUtil = OkHttpUtil.getSingleOkHttp();
        params.put("", "");
    }
    /*public List<HomeBean.DataHome> portSucceed() {
        final List<HomeBean.DataHome> data = new ArrayList<HomeBean.DataHome>();
        okHttpUtil.getUrl(mContext, NetConstants.HOMELIST, params, new JsonHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                LogUtil.e("s = " + response.toString());
                Gson gson = new Gson();
                String s = response.toString();
                HomeBean gHomeBean = gson.fromJson(s, HomeBean.class);
                data.clear();
                data.addAll(gHomeBean.result.data);

            }

            @Override
            public void onFailure(int statusCode, String error) {
                LogUtil.e("。。。网络请求失败。。。");
            }

            @Override
            public void onProgress(long currentBytes, long totalBytes) {

            }
        });
        return data;
    }*/

    @Override
    public void requestForData(final OnHomeListener listener) {
        final List<HomeBean.DataHome> data = new ArrayList<HomeBean.DataHome>();
        final Gson gson = new Gson();
        //StorageDataUtil.getStorage(mContext)
        String lishi = StorageDataUtil.getStringData(mContext, HOMELISTKEY);
        if (lishi != null) {
            LogUtil.e("getStringData()");
            HomeBean gHomeBean = gson.fromJson(lishi, HomeBean.class);
            data.clear();
            data.addAll(gHomeBean.result.data);
            if (listener != null) {
                listener.onSucceed(data);
            }
        } else {
            LogUtil.e("okHttpUtil()");
            okHttpUtil.getUrl(mContext, NetConstants.HOMELIST, params, new JsonHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) {
                    LogUtil.e("s = " + response.toString());
                    StorageDataUtil.saveStringData(mContext, HOMELISTKEY, response.toString());
                    String s = response.toString();
                    HomeBean gHomeBean = gson.fromJson(s, HomeBean.class);
                    data.clear();
                    data.addAll(gHomeBean.result.data);
                    if (listener != null) {
                        listener.onSucceed(data);
                    }
                }

                @Override
                public void onFailure(int statusCode, String error) {
                    LogUtil.e("。。。网络请求失败。。。");
                }

                @Override
                public void onProgress(long currentBytes, long totalBytes) {

                }
            });
        }
    }
}
