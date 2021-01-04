package com.example.project.mvp.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.project.R;
import com.example.project.base.App;
import com.example.project.base.BaseActivity;
import com.example.project.base.BasePresenter;
import com.example.project.engine.adapter.GuideVpAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity {

    @BindView(R.id.guide_vp)
    ViewPager mGuideVp;
    @BindView(R.id.guide_skip)
    TextView mGuideSkip;

    private Integer[] ivRes = {R.drawable.guide_page_one, R.drawable.guide_page_two, R.drawable.guide_page_three};

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {
        GuideVpAdapter vpAdapter = new GuideVpAdapter(getPagerData());
        mGuideVp.setAdapter(vpAdapter);


    }

    @Override
    protected void initListener() {
        mGuideVp.addOnPageChangeListener(pageListener);
    }

    private ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == ivRes.length - 1) {
                mGuideSkip.setVisibility(View.VISIBLE);
            }else{
                mGuideSkip.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @OnClick(R.id.guide_skip)
    public void onClicked() {
        Intent intent = new Intent(GuideActivity.this, SelCourseActivity.class);
        startActivity(intent);
        finish();
    }

    private List<View> getPagerData() {
        List<View> views = new ArrayList<>();

        for (int i = 0; i < ivRes.length; i++) {
            ImageView iv = new ImageView(App.getContent());
            iv.setBackgroundResource(ivRes[i]);
            views.add(iv);
        }

        return views;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

}