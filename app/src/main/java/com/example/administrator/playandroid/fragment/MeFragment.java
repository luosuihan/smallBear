package com.example.administrator.playandroid.fragment;

import android.content.Context;
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
import com.example.administrator.playandroid.util.ToastUtil;
import com.example.administrator.playandroid.weight.ListView3;
import com.example.administrator.playandroid.weight.PullListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/23.
 */

public class MeFragment extends BaseFragment {
    private static MeFragment singleFragment;
    private PullListView plv;
    private ListView3 listView;
    private Context context;
    private List<String> dataList =  new ArrayList<>();
    private MyAdapter adapter;
    private TextView tv;

    public static MeFragment getSingleMeFragment(){
        if (singleFragment == null){
            singleFragment = new MeFragment();
        }
        return singleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.me_fragment, null);
//        plv = (PullListView)inflate.findViewById(R.id.plv);
        tv = inflate.findViewById(R.id.tv);
        initData();
//        tv.setPadding(0, -50, 0, 0);
        tv.setPadding(0, 0,0,0);
        listView = (ListView3)inflate.findViewById(R.id.plv);

        for (int i = 0; i < 30; i++) {
            dataList.add("item"+i);
        }

        adapter = new MyAdapter();
        listView.setAdapter(adapter);

        listView.setOnRefreshListener(new ListView3.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShortToast(getActivity(), "刷新成功");
                        dataList.add(0,"header  header  header");
                        adapter.notifyDataSetChanged();
                        listView.finishRefresh();
                    }
                }, 2000);
            }
        });

        listView.setOnLoadMoreListener(new ListView3.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShortToast(getContext(), "上拉加载成功");
                        dataList.add("footer  footer  footer  ");
                        adapter.notifyDataSetChanged();
                        listView.finishLoadMore();
                    }
                }, 2000);
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
