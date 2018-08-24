package com.example.administrator.playandroid.presenter;

import android.content.Context;

import com.example.administrator.playandroid.fragment.HomeFragment;
import com.example.administrator.playandroid.model.HomeModelIml;

/**
 * Created by Administrator on 2018/8/24.
 */

public class HomePresenter {
    private Context mContext;
    private HomeModelIml homeModelIml;

    public HomePresenter(Context context){
        mContext = context;
        homeModelIml = new HomeModelIml(context);
    }
    public void homeSucceed(){
        //业务处理
        homeModelIml.onSucceed();
    }
}
