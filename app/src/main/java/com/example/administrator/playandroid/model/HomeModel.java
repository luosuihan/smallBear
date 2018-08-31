package com.example.administrator.playandroid.model;

import com.example.administrator.playandroid.bean.HomeBean;
import com.example.administrator.playandroid.bean.HomeListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/23.
 */

public interface HomeModel {
  /*  List<HomeBean.DataHome> onSucceed();
    void onFail(String s);*/
  void requestForData(OnHomeListener listener);
}
