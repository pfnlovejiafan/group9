package com.example.project.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.project.HomeActivity;
import com.example.project.R;
import com.example.project.base.BaseActivity;
import com.example.project.base.BasePresenter;
import com.example.project.engine.adapter.GuideVpAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity {

    @BindView(R.id.guide_vp)
    ViewPager mGuideVp;


    private Integer[] mPagers = {R.drawable.guide_page_one, R.drawable.guide_page_two, R.drawable.guide_page_three};

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {
        GuideVpAdapter vpAdapter = new GuideVpAdapter(getPagerData());
        mGuideVp.setAdapter(vpAdapter);

    }







    private List<Integer> getPagerData() {
        List<Integer> pagerList = new ArrayList<>();

        for (int i = 0; i < mPagers.length; i++) {
            pagerList.add(mPagers[i]);
        }

        return pagerList;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

}