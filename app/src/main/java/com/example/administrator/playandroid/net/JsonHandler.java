package com.example.administrator.playandroid.net;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/8/23.
 */

public abstract class JsonHandler implements IResponseHandler {

    public abstract void onSuccess(int statusCode, JSONObject response);

}
