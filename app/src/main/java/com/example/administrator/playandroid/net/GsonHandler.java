package com.example.administrator.playandroid.net;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2018/8/23.
 */

public abstract class GsonHandler<T> implements IResponseHandler {

    Type mType;

    public GsonHandler(){
        Type myClass = getClass().getGenericSuperclass();
        if (myClass instanceof Class){
            throw new RuntimeException("类型转换失败....");
        }
        ParameterizedType parameter = (ParameterizedType) this.mType;
        mType = $Gson$Types.canonicalize(parameter.getActualTypeArguments()[0]);
    }
    public final Type getType(){
        return mType;
    }
    public abstract void onSuccess(int statusCode, T response);

}
