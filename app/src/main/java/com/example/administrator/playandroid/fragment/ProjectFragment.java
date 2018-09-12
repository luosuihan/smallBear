package com.example.administrator.playandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.base.BaseFragment;
import com.example.administrator.playandroid.fragment.weight_fragent.BannerViewFragment;

/**
 * Created by Administrator on 2018/8/23.
 */

public class ProjectFragment extends BaseFragment implements View.OnClickListener {
    private static ProjectFragment singleFragment;
    private Button mBtnvp;
    private FrameLayout viewById;

    public static ProjectFragment getSingleProjectFragment(){
        if (singleFragment == null){
            singleFragment = new ProjectFragment();
        }
        return singleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.project_fragment, null);
        mBtnvp = inflate.findViewById(R.id.btn_vp);
        viewById = inflate.findViewById(R.id.fl);
        mBtnvp.setOnClickListener(this);
        return inflate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_vp:
               BannerViewFragment bvf = new BannerViewFragment();
                getFragmentManager().beginTransaction().replace(R.id.fl,bvf).commit();
                break;
        }
    }
}
