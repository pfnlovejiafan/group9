package com.example.p7mvp.mvp.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p7mvp.R;
import com.example.p7mvp.base.BaseActivity;
import com.example.p7mvp.base.BasePresenter;
import com.example.p7mvp.mvp.model.bean.ResultBean;
import com.example.p7mvp.mvp.presenter.HomePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {
        super.initData();
        getPresenter().start();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return super.getPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @OnClick(R.id.tv)
    @Override
    protected void onClick(View view) {
        super.onClick(view);
        Toast.makeText(this, ""+tv.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(Object object) {
        super.onSuccess(object);
        if (object instanceof ResultBean){
            ResultBean bean = (ResultBean) object;
            int errorCode = bean.getErrorCode();
            Log.e("TAG",""+errorCode);
        }
    }

    @Override
    public void onError(String msg) {
        super.onError(msg);
        Log.e("TAG",msg);
    }
}