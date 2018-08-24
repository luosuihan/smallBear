package com.example.administrator.playandroid.net;

/**
 * Created by Administrator on 2018/8/23.
 */

public interface IResponseHandler {
    void onFail(int statusCode,String error);
    void onProgress(long currentBytes, long totalBytes);
}
