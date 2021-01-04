package com.example.project.mvp.ui.fragment.tongpao;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.R;
import com.example.project.base.BaseFragment;
import com.example.project.base.BasePresenter;
import com.example.project.engine.adapter.tongpao.MyAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TPHomeFragment extends BaseFragment {
    List<String> tabs;
    List<Fragment> fragments;
    @BindView(R.id.tab_tongpao)
    TabLayout tabTongpao;
    @BindView(R.id.vp_tongpao)
    ViewPager vpTongpao;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {

    }



    @Override
    protected void initData() {
        tabs = new ArrayList<>();
        fragments = new ArrayList<>();
        tabs.add("推荐");
        tabs.add("广场");
        tabs.add("视频");
        tabs.add("摄影");
        tabs.add("知识文章");
        fragments.add(new RecommendFragment());

        //tablayout横向滚动
        tabTongpao.setTabMode(TabLayout.MODE_SCROLLABLE);

        MyAdapter myAdapter = new MyAdapter(getFragmentManager(), fragments, tabs);

        //初始化ViewPager
        vpTongpao.setAdapter(myAdapter);
        tabTongpao.setupWithViewPager(vpTongpao);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_t_p_home;
    }
}