package com.example.project.mvp.ui.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.base.App;
import com.example.project.base.BaseActivity;
import com.example.project.base.BasePresenter;
import com.example.project.mvp.presenter.project.SelCourseActPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelCourseActivity extends BaseActivity {


    @BindView(R.id.sel_course_rv)
    RecyclerView mSelCourseRv;
    @BindView(R.id.sel_course_confirm)
    Button mSelCourseConfirm;

    @Override
    protected BasePresenter createPresenter() {
        return new SelCourseActPresenter(this);
    }

    @Override
    protected void init() {
        mSelCourseRv.setLayoutManager(new LinearLayoutManager(App.getContent(), RecyclerView.VERTICAL, false));

    }

    @Override
    protected void initData() {
        getPresenter().start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_course;
    }

}