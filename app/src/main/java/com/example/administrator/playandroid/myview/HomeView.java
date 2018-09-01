package com.example.administrator.playandroid.myview;

import com.example.administrator.playandroid.bean.HomeBean;
import com.example.administrator.playandroid.bean.HomeListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/24.
 */

public interface HomeView {
    void showLoad();
    void hideLoad();
    void setItem(List<HomeBean.DataHome> d);
    void showMessage(String s);

}
