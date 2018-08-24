package com.example.administrator.playandroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.playandroid.fragment.CollectFragment;
import com.example.administrator.playandroid.fragment.HomeFragment;
import com.example.administrator.playandroid.fragment.MeFragment;
import com.example.administrator.playandroid.fragment.ProjectFragment;
import com.example.administrator.playandroid.util.LogUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "MainActivity";
    private FrameLayout mFragmentContainer;
    private RadioGroup mainRg;
    private RadioButton mRbHome;
    private RadioButton mRbProject;
    private RadioButton mRbCollect;
    private RadioButton mRbMe;
    private HomeFragment homeFragment;
    private ProjectFragment projectFragment;
    private CollectFragment collectFragment;
    private MeFragment meFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mFragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        mainRg = (RadioGroup) findViewById(R.id.main_rg);
        mRbHome = (RadioButton) findViewById(R.id.rb0);
        mRbProject = (RadioButton) findViewById(R.id.rb1);
        mRbCollect = (RadioButton) findViewById(R.id.rb2);
        mRbMe = (RadioButton) findViewById(R.id.rb3);
        homeFragment = HomeFragment.getSingleHomeFragment();
        projectFragment = ProjectFragment.getSingleProjectFragment();
        collectFragment = CollectFragment.getSingleCollectFragment();
        meFragment = MeFragment.getSingleMeFragment();
        mainRg.check(R.id.rb0);//默认选中页
        mRbHome.setOnClickListener(this);
        mRbProject.setOnClickListener(this);
        mRbCollect.setOnClickListener(this);
        mRbMe.setOnClickListener(this);
        addFragment(homeFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb0:
                addFragment(homeFragment);
                break;
            case R.id.rb1:
                addFragment(projectFragment);
                break;
            case R.id.rb2:
                addFragment(collectFragment);
                break;
            case R.id.rb3:
                addFragment(meFragment);
                break;
        }
    }

    private void addFragment(Fragment fragment) {
        LogUtil.e(TAG,"fragment = "+fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}
