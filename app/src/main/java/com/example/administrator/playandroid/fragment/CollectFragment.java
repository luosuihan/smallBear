package com.example.administrator.playandroid.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.base.BaseFragment;
import com.example.administrator.playandroid.text.PullListViewText;
import com.example.administrator.playandroid.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/23.
 */

public class CollectFragment extends BaseFragment {
    private static CollectFragment collectFragment;
    private PullListViewText listView;
    private List<String> dataList =  new ArrayList<>();
    private CollectFragment.MyAdapter adapter;
    public static CollectFragment getSingleCollectFragment(){
        if (collectFragment == null){
            collectFragment = new CollectFragment();
        }
        return collectFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.collect_fragment, null);
        listView = inflate.findViewById(R.id.plvt);
        //return super.onCreateView(inflater, container, savedInstanceState);
        initData();
        for (int i = 0; i < 30; i++) {
            dataList.add("item"+i);
        }

        adapter = new CollectFragment.MyAdapter();
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(new PullListViewText.OnPullListViewListenerText() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataList.add(0,"header  header  header");
                        adapter.notifyDataSetChanged();
                        listView.finishRefresh();
                    }
                },3000);
            }
        });
        listView.setOnLoadMoreListener(new PullListViewText.OnPullListViewLoadMoreListenerText() {
            @Override
            public void upload() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShortToast(getContext(), "上拉加载成功");
                        dataList.add("脚布局");
                        adapter.notifyDataSetChanged();
                        listView.finishLoadMore();
                    }
                },3000);
            }
        });
        return inflate;
    }
    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dataList.add("item" + i);
        }
    }
    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(getContext());
            tv.setText(dataList.get(position));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(16);
            return tv;
        }
    }
}
