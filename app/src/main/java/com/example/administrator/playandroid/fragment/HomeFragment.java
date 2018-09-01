package com.example.administrator.playandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.adapter.HomeFragmentAdapter;
import com.example.administrator.playandroid.base.BaseFragment;
import com.example.administrator.playandroid.bean.HomeBean;
import com.example.administrator.playandroid.bean.HomeListBean;
import com.example.administrator.playandroid.myview.HomeView;
import com.example.administrator.playandroid.presenter.HomePresenter;

import java.util.List;

/**
 * Created by Administrator on 2018/8/23.
 *
 * 首页列表展示 --- 通过fresh来判断是该数据是否为新的，如果是新的将置顶
 */

public class HomeFragment extends BaseFragment implements HomeView, AdapterView.OnItemClickListener {
    private static HomeFragment singleFragment;
    private ListView homeLv;
    private HomePresenter homePresenter;

    public static HomeFragment getSingleHomeFragment(){
        if(singleFragment == null){
            singleFragment = new HomeFragment();
        }
        return singleFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.home_fragment, null);
        homeLv = (ListView)inflate.findViewById(R.id.home_lv);
        homeLv.setOnItemClickListener(this);
        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        homePresenter = new HomePresenter(getContext(),this);
        homePresenter.homeSucceed();
    }

    @Override
    public void showLoad() {

    }

    @Override
    public void hideLoad() {

    }

    @Override
    public void setItem(List<HomeBean.DataHome> d) {
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getContext(),d);
        homeLv.setAdapter(adapter);
    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        homePresenter.onItemClick(position);
    }
}
