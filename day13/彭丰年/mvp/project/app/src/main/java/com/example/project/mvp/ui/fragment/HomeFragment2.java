package com.example.project.mvp.ui.fragment;

import android.util.Log;
import android.widget.TextView;


import com.example.project.R;
import com.example.project.base.BaseFragment;
import com.example.project.base.BasePresenter;
import com.example.project.mvp.presenter.HomeFragPresenter;

import butterknife.BindView;


public class HomeFragment2 extends BaseFragment {

    @BindView(R.id.home_frag_tv)
    TextView homeFragTv;

    @Override
    protected BasePresenter createPresenter() {
        return new HomeFragPresenter(this);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {
        //TODO 1.V层向P层发起请求数据指令
        //1.V层向P层发起请求数据指令
        getPresenter().start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home2;
    }

    @Override
    public void onSucceed(Object o) {
        //TODO 5. 成功更新ui
        //3. 成功更新ui
        String str = (String) o;
        Log.e("TAG", "onSucceed: " + str);


    }

    @Override
    public void onFiled(String errorMsg) {
        //TODO 5. 失败更新ui
        //3. 失败更新ui
        Log.e("TAG", "onFiled: " + errorMsg);
    }
}
