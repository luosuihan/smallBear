package com.example.administrator.playandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.base.BaseFragment;

/**
 * Created by Administrator on 2018/8/23.
 */

public class CollectFragment extends BaseFragment {
    private static CollectFragment collectFragment;
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
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflate;
    }
}
