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

public class MeFragment extends BaseFragment {
    private static MeFragment singleFragment;
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
        return inflate;
    }
}
