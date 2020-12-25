package com.example.mvp.mvp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mvp.R;
import com.example.mvp.base.BaseActivity;
import com.example.mvp.base.BasePresenter;

public class DiTuActivity extends BaseActivity {


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_di_tu;
    }
}
