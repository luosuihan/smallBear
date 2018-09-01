package com.example.administrator.playandroid.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/9/1.
 */

public class StorageDataUtil {
    /*public void SharedPreferences(){

    }*/
    private static String HOMEFRAGMENTLIST = "home_fragment_list";
    //保存数据
    public static void saveStringData(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences(HOMEFRAGMENTLIST, Context.MODE_PRIVATE);
        //获取到edit对象
        SharedPreferences.Editor edit = sp.edit();
        //通过editor对象写入数据
        edit.putString(key,value);
        //提交数据存入到xml文件中
        edit.commit();
    }
    //获取保存的数据
    public static String getStringData(Context context,String key){
        SharedPreferences gsp = context.getSharedPreferences(HOMEFRAGMENTLIST, Context.MODE_PRIVATE);
        String value = gsp.getString(key,null);
        return value;
    }
}
