package com.example.administrator.playandroid.presenter;

import android.content.Context;
import android.os.Handler;

import com.example.administrator.playandroid.bean.HomeBean;
import com.example.administrator.playandroid.bean.HomeListBean;
import com.example.administrator.playandroid.fragment.HomeFragment;
import com.example.administrator.playandroid.model.HomeModelIml;
import com.example.administrator.playandroid.myview.HomeView;
import com.example.administrator.playandroid.util.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/8/24.
 */

public class HomePresenter {
    private Context mContext;
    private HomeModelIml homeModelIml;
    private HomeView homeView;
    private Handler handler;
    public HomePresenter(Context context,HomeView vivew){
        mContext = context;
        homeModelIml = new HomeModelIml(context);
        homeView = vivew;
        handler = new Handler();
    }
    public void homeSucceed(){
        //业务处理
        List<HomeBean.DataHome> datases = homeModelIml.onSucceed();
        LogUtil.e("集合长度  。HomePresenter 。  = "+datases.size());
    }
}
