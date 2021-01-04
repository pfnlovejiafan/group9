package com.example.project.mvp.presenter.project;

import android.app.Activity;
import android.util.Log;

import com.example.project.base.BasePresenter;
import com.example.project.base.IBaseView;
import com.example.project.manager.GlobalManager;
import com.example.project.mvp.model.RxPoretorImpl;
import com.example.project.mvp.model.constants.Constants;
import com.example.project.mvp.ui.activity.SelCourseActivity;

public class SelCourseActPresenter extends BasePresenter {

    private IBaseView mView;
    private RxPoretorImpl mImpl;

    public SelCourseActPresenter(IBaseView activity) {
        this.mView = activity;
        mImpl = new RxPoretorImpl();
    }

    @Override
    public void start() {
        //请求分类数据之前先请求令牌数据
        GlobalManager manager = GlobalManager.getManager();

        String decode = manager.get_str("decode");
        Log.e("TAG", "start: "+decode );

//        mImpl.rxPostRequest(Constants.RELEASE_BASE_URL+Constants.);
    }
}
