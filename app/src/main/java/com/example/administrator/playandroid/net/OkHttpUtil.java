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
    private static OkHttpUtil singleOkHttp;

    private OkHttpUtil() {
        LogUtil.e("luosuihan", " OkHttpUtil() ");
        client = new OkHttpClient();
    }

    public static OkHttpUtil getSingleOkHttp() {
        if (singleOkHttp == null) {
            singleOkHttp = new OkHttpUtil();
        }
        return singleOkHttp;
    }

    public void post(Context context, final String url, final Map<String, String> params, final IResponseHandler iResponseHandler) {
        postBody(context, url, params, iResponseHandler);
    }

    private void postBody(Context context, String url, Map<String, String> params, IResponseHandler iResponseHandler) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        Request request;
        if (context == null) {
            request = new Request.Builder()
                    .url(url)
                    .post(builder.build())
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .post(builder.build())
                    .tag(context)
                    .build();
        }
        client.newCall(request).enqueue(new MyOkHttpCallback(new Handler(), iResponseHandler));
    }

    public void get(Context context, final String url, final IResponseHandler iResponseHandler) {
        getBody(context, url, iResponseHandler);
    }

    private void getBody(Context context, String url, IResponseHandler iResponseHandler) {

        Request request;
        if (context == null) {
            request = new Request.Builder()
                    .url(url)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .build();
        }
        client.newCall(request).enqueue(new MyOkHttpCallback(new Handler(), iResponseHandler));
    }

    class MyOkHttpCallback implements Callback {
        private Handler mHandler;
        private IResponseHandler mResponse;

        public MyOkHttpCallback(Handler handler, IResponseHandler responseHandler) {
            mHandler = handler;
            mResponse = responseHandler;
        }

        @Override
        public void onFailure(Call call, final IOException e) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mResponse.onFail(0, "异常消息：： e = " + e);
                }
            });
        }

        @Override
        public void onResponse(Call call, final Response response) throws IOException {
            if (response.isSuccessful()) {
                final String string = response.body().string();
                if (mResponse instanceof GsonHandler) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            ((GsonHandler) mResponse).onSuccess(response.code(), gson.fromJson(string, ((GsonHandler) mResponse).getType()));
                        }
                    });
                } else if (mResponse instanceof JsonHandler) {
                    try {
                        final JSONObject jsonbody = new JSONObject(string);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                ((JsonHandler) mResponse).onSuccess(response.code(), jsonbody);
                            }
                        });

                    } catch (final JSONException e) {
                        e.printStackTrace();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mResponse.onFail(response.code(), e + "");
                            }
                        });
                    }
                } else if (mResponse instanceof RawHandler) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ((RawHandler) mResponse).onSuccess(response.code(), string);
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mResponse.onFail(response.code(), "。。异常。。");
                        }
                    });
                }
            }
        }
    }
}
