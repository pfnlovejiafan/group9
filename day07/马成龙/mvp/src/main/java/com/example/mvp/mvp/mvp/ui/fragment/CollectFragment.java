package com.example.mvp.mvp.mvp.ui.fragment;

import com.example.mvp.mvp.base.BaseFragment;
import com.example.mvp.mvp.base.BasePresenter;

public abstract class CollectFragment extends BaseFragment {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}