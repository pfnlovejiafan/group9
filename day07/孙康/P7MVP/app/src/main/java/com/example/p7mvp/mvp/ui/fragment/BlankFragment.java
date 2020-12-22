package com.example.p7mvp.mvp.ui.fragment;


import android.view.View;
import com.example.p7mvp.R;
import com.example.p7mvp.base.BaseFragment;
import com.example.p7mvp.base.BasePresenter;
import com.example.p7mvp.mvp.presenter.FrgamentPresenter;

import butterknife.OnClick;

public class BlankFragment extends BaseFragment {

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new FrgamentPresenter(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick(R.id.tv)
    @Override
    protected void onClick(View view) {
        super.onClick(view);
    }
}