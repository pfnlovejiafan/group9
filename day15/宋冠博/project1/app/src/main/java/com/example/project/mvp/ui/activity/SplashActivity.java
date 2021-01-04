package com.example.project.mvp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.base.App;
import com.example.project.base.BaseActivity;
import com.example.project.base.BasePresenter;
import com.example.project.manager.GlobalManager;
import com.example.project.mvp.model.constants.Constants;
import com.example.project.mvp.model.entity.project.DecodeBean;
import com.example.project.mvp.presenter.project.SelCourseActPresenter;
import com.example.project.mvp.presenter.project.SplashActPresenter;
import com.example.project.thread.ThreadPoolManager;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SplashActivity extends BaseActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected BasePresenter createPresenter() {
        return new SplashActPresenter(this);
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
        }, 2, TimeUnit.SECONDS);

        getPresenter().start(Constants.DEVICE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void onSucceed(Object object) {
        if (object instanceof DecodeBean) {
            DecodeBean decodeBean = (DecodeBean) object;
            String decode = decodeBean.getData();
            Log.e(TAG, "TAG:" + App.getStr(R.string.request_succeed) + decode);
            GlobalManager manager = GlobalManager.getManager();
            manager.save_str("decode", decode);
        }
        Toast.makeText(this, App.getStr(R.string.request_succeed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFiled(String errorMsg) {
        super.onFiled(errorMsg);
        Log.e(TAG, "TAG:" + App.getStr(R.string.request_error) + "：" + errorMsg);
        Toast.makeText(this, App.getStr(R.string.request_error), Toast.LENGTH_SHORT).show();

    }
}
