package com.example.administrator.playandroid.model;

import com.example.administrator.playandroid.bean.HomeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/31.
 */

public interface OnHomeListener {
    void onSucceed(List<HomeBean.DataHome> d);
    void onFail(String s);
}
