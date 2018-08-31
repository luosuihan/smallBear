package com.example.administrator.playandroid.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.administrator.playandroid.bean.HomeBean;
import com.example.administrator.playandroid.bean.HomeListBean;
import com.example.administrator.playandroid.fragment.HomeFragment;
import com.example.administrator.playandroid.model.HomeModel;
import com.example.administrator.playandroid.model.HomeModelIml;
import com.example.administrator.playandroid.model.OnHomeListener;
import com.example.administrator.playandroid.myview.HomeView;
import com.example.administrator.playandroid.util.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/8/24.
 */

public class HomePresenter {
    private Context mContext;
    private HomeModel homeModelIml;
    private HomeView homeView;
    private Handler handler;
    public HomePresenter(Context context,HomeView vivew){
        mContext = context;
        homeModelIml = new HomeModelIml(context);
        homeView = vivew;
        handler = new Handler(Looper.getMainLooper());
    }
    public void homeSucceed(){
        //业务处理
      /*  List<HomeBean.DataHome> datases = homeModelIml.onSucceed();
        LogUtil.e("集合长度  。HomePresenter 。  = "+datases.size());*/
        homeModelIml.requestForData(new OnHomeListener() {
            @Override
            public void onSucceed(final List<HomeBean.DataHome> d) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.e("集合长度  。HomePresenter 。  = "+d.size());
                    }
                });

            }

            @Override
            public void onFail(String s) {

            }
        });
    }
}
