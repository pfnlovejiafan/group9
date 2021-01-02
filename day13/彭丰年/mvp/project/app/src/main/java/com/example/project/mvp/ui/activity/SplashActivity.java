package com.example.project.mvp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;

import android.content.Intent;
import android.os.Bundle;

import com.example.project.R;
import com.example.project.base.BaseActivity;
import com.example.project.base.BasePresenter;
import com.example.project.mvp.model.constants.Constants;
import com.example.project.thread.ThreadPoolManager;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SplashActivity extends BaseActivity {


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {
        ThreadPoolManager.getThreadPool(Constants.SCHEDULED_THREADPOOL).executorTimerTask(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                startActivity(intent);
                finish();
            }
        },2, TimeUnit.SECONDS);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}