package com.example.ceshi1.mvp.mvp.ui.activity;

import android.view.KeyEvent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;


import com.example.ceshi1.R;
import com.example.ceshi1.mvp.base.App;
import com.example.ceshi1.mvp.base.BaseActivity;
import com.example.ceshi1.mvp.base.BasePresenter;
import com.example.ceshi1.mvp.engine.adapter.HomeActVpAdapter;
import com.example.ceshi1.mvp.manager.ContainManager;
import com.example.ceshi1.mvp.manager.ThreadPoolManager;
import com.example.ceshi1.mvp.mvp.ui.fragment.CollectFragment;
import com.example.ceshi1.mvp.mvp.ui.fragment.HomeFragment;
import com.example.ceshi1.mvp.mvp.ui.fragment.OneFragment;
import com.example.ceshi1.mvp.mvp.ui.fragment.ThreeFragment;
import com.example.ceshi1.mvp.mvp.ui.fragment.TwoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.home_act_vp)
    ViewPager mHomeActVp;
    @BindView(R.id.home_act_tab)
    TabLayout mHomeActTab;
    private boolean mIsExit;
    private FragmentManager mManager;
    private int[] mTabs = {R.string.home_tab_title, R.string.collect_tab_title,R.string.hhh_tab_title,R.string.one_tab_title,R.string.two_tab_title};

    @Override
    protected void init() {
        mManager = getSupportFragmentManager();
        HomeActVpAdapter homeActVpAdapter = new HomeActVpAdapter(mManager, getFragments(),mTabs);
        mHomeActVp.setAdapter(homeActVpAdapter);
        mHomeActTab.setupWithViewPager(mHomeActVp);

        mHomeActTab.getTabAt(0).setIcon(R.drawable.a);
        mHomeActTab.getTabAt(1).setIcon(R.drawable.b);
        mHomeActTab.getTabAt(2).setIcon(R.drawable.e);
        mHomeActTab.getTabAt(3).setIcon(R.drawable.c);
        mHomeActTab.getTabAt(4).setIcon(R.drawable.d);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList();
        fragments.add(new HomeFragment());
        fragments.add(new CollectFragment());
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
        return fragments;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.home_activity;
    }

    //监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //TODO  2秒内连续点击两次 退出程序，  如果第二次点击超过2秒了，会Toast提示再按一次退出程序
            if (!mIsExit) {
                mIsExit = true;
                Toast.makeText(App.context(), "再按一次退出程序", Toast.LENGTH_LONG).show();
                //2秒之后把变量值改成true
                ThreadPoolManager.
                        getThreadPool(ThreadPoolManager.SCHDULE_THREADPOOL).
                        executeTimerTask(new Runnable() {
                            @Override
                            public void run() {
                                mIsExit = false;
                            }
                        }, 2, TimeUnit.SECONDS);
            } else {
                ContainManager.getmManager().clearActivity();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


}
