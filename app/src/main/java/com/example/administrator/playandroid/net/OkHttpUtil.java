package com.example.administrator.playandroid.net;

import android.content.Context;
import android.os.Handler;

import com.example.administrator.playandroid.util.LogUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/8/23.
 */

public class OkHttpUtil {
    private OkHttpClient client;
    private static OkHttpUtil instance;
    public OkHttpUtil(){
        client = new OkHttpClient();
    }
    public static OkHttpUtil getSingleOkHttp(){
        if(instance == null){
            instance = new OkHttpUtil();
        }
        return instance;
    }
    /**
     * post 请求
     * */
    public void post(Context context, final String url, final Map<String,String> params,final IResponseHandler iResponseHandler){
        postBody(context,url,params,iResponseHandler);
    }

    private void postBody(Context context, String url, Map<String, String> params, IResponseHandler iResponseHandler) {
        FormBody.Builder builder = new FormBody.Builder();
        if(params != null && params.size() > 0){
            for (Map.Entry<String,String> entry : params.entrySet()){
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        Request request;
        if(context == null){
            request = new Request.Builder()
                    .url(url)
                    .post(builder.build())
                    .build();
        }else{
            request = new Request.Builder()
                    .url(url)
                    .post(builder.build())
                    .tag(context)
                    .build();
        }
        client.newCall(request).enqueue(new GHomeCallback(new Handler(), iResponseHandler));
    }
    public void getUrl(Context context, String url, Map<String, String> params, IResponseHandler iResponseHandler){
        getBody(context,url,params,iResponseHandler);
    }

    private void getBody(Context context, String url, Map<String, String> params, IResponseHandler iResponseHandler) {
        String get_url = url;
        if (params != null && params.size()>0){
            int i = 0;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if(i++ == 0) {
                    get_url = get_url + "?" + entry.getKey() + "=" + entry.getValue();
                } else {
                    get_url = get_url + "&" + entry.getKey() + "=" + entry.getValue();
                }
            }
        }
        Request request;
        if(context == null) {
            request = new Request.Builder()
                    .url(url)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .tag(context)
                    .build();
        }
        client.newCall(request).enqueue(new GHomeCallback(new Handler(),iResponseHandler));
    }

    private class GHomeCallback implements Callback {
        private Handler mHandler;
        private IResponseHandler mResponseHandler;

        public GHomeCallback(Handler handler, IResponseHandler responseHandler) {
            mHandler = handler;
            mResponseHandler = responseHandler;

        }

        //如下两个方法属于第三方库的
        @Override
        public void onFailure(Call call, final IOException e) {
            //LogUtil.loge("luosuihan","onFailure e = "+e);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mResponseHandler.onFailure(0,""+e);
                }
            });
        }

        @Override
        public void onResponse(Call call, final Response response) throws IOException {
            if (response.isSuccessful()){
                final String response_body = response.body().string();
                if(mResponseHandler instanceof GsonHandler){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Gson gson = new Gson();
                                ((GsonHandler)mResponseHandler).onSuccess(response.code(),
                                        gson.fromJson(response_body, ((GsonHandler)mResponseHandler).getType()));
                            } catch (Exception e) {
                               // LogUtil.loge("luosuihan","onResponse fail parse gson, body=" + response_body+" ,"+e);
                                mResponseHandler.onFailure(response.code(), "fail parse gson, body=" + response_body);
                            }
                        }
                    });
                }else if(mResponseHandler instanceof JsonHandler){
                    try {
                        final JSONObject jsonBody = new JSONObject(response_body);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                ((JsonHandler)mResponseHandler).onSuccess(response.code(), jsonBody);
                            }
                        });
                    } catch (JSONException e) {
                        //LogUtil.loge("luosuihan","onResponse fail parse jsonobject, body=" + response_body);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mResponseHandler.onFailure(response.code(), "fail parse jsonobject, body=" + response_body);
                            }
                        });
                    }
                }else if(mResponseHandler instanceof RawHandler) {     //raw字符串回调
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ((RawHandler)mResponseHandler).onSuccess(response.code(), response_body);
                        }
                    });
                }else{
                   // LogUtil.loge("luosuihan","onResponse fail status=" + response.code());
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mResponseHandler.onFailure(0, "fail status=" + response.code());
                        }
                    });
                }
            }
        }
    }
}
