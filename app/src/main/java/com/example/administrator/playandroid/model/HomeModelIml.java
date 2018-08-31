package com.example.administrator.playandroid.model;

import android.content.Context;

import com.example.administrator.playandroid.bean.HomeBean;
import com.example.administrator.playandroid.bean.HomeListBean;
import com.example.administrator.playandroid.net.IResponseHandler;
import com.example.administrator.playandroid.net.JsonHandler;
import com.example.administrator.playandroid.net.NetConstants;
import com.example.administrator.playandroid.net.OkHttpUtil;
import com.example.administrator.playandroid.util.LogUtil;
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
    public HomeModelIml(Context context){
        LogUtil.e("luosuihan","HomeModelIml()");
        mContext = context;
        okHttpUtil = OkHttpUtil.getSingleOkHttp();
        params.put("","");
    }

   /* @Override
    public List<HomeBean.DataHome> onSucceed() {
        List<HomeBean.DataHome> datases = portSucceed();
        return datases;
    }

    @Override
    public void onFail(String s) {
        LogUtil.e("luosuihan","onFail() = "+s);
    }*/

    public List<HomeBean.DataHome> portSucceed() {
        final List<HomeBean.DataHome> data = new ArrayList<HomeBean.DataHome>();
        okHttpUtil.getUrl(mContext, NetConstants.HOMELIST,params,new JsonHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                LogUtil.e("s = "+response.toString());
                Gson gson = new Gson();
                String s = response.toString();
                HomeBean gHomeBean = gson.fromJson(s, HomeBean.class);
                data.clear();
                data.addAll(gHomeBean.result.data);
                LogUtil.e("集合长度 。。 ： "+data.size());

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
    }

    @Override
    public void requestForData(final OnHomeListener listener) {
        final List<HomeBean.DataHome> data = new ArrayList<HomeBean.DataHome>();
        okHttpUtil.getUrl(mContext, NetConstants.HOMELIST,params,new JsonHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                LogUtil.e("s = "+response.toString());
                Gson gson = new Gson();
                String s = response.toString();
                HomeBean gHomeBean = gson.fromJson(s, HomeBean.class);
                data.clear();
                data.addAll(gHomeBean.result.data);
                LogUtil.e("集合长度 。。 ： "+data.size());
                if(listener != null){
                    LogUtil.e("集合长度 。。 listener： "+data.size());
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
