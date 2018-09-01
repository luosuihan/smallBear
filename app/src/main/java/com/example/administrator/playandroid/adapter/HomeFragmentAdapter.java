package com.example.administrator.playandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.bean.HomeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/1.
 */

public class HomeFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeBean.DataHome> mData;
    public HomeFragmentAdapter(Context context, List<HomeBean.DataHome> list){
        mContext = context;
        mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.home_item,null);
        HomeBean.DataHome dHome = mData.get(position);
        TextView viewById = (TextView)view.findViewById(R.id.tv);
        viewById.setText(dHome.title);
        return view;
    }
}
