package com.example.myapplication.mvp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.bean.BaseActivity;
import com.example.myapplication.bean.BasePresenter;
import com.example.myapplication.manager.ContainManager;
import com.example.myapplication.mvp.ui.fragmrnt.HomeFragment;

public class HomeActivity extends BaseActivity {
    private boolean mIsExit;
    private Handler handler=new Handler();
    @Override
    protected BasePresenter createPrasenter() {
        return null;
    }

    @Override
    protected void init() {
        getSupportFragmentManager().beginTransaction().replace(R.id.ll,new HomeFragment()).commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            if (!mIsExit) {
                mIsExit = true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            } else {
                ContainManager.getContainManager().clearActivity();
            }
            return false;
        }
        return super.onKeyDown(keyCode,event);
    }
}
