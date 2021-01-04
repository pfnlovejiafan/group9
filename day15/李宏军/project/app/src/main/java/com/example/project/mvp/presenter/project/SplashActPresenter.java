package com.example.project.mvp.presenter.project;

import com.example.project.base.BasePresenter;
import com.example.project.base.IBaseView;
import com.example.project.callback.RxObserverCallBack;
import com.example.project.mvp.model.RxPoretorImpl;
import com.example.project.mvp.model.constants.Constants;
import com.example.project.mvp.model.entity.project.DecodeBean;
import com.example.project.mvp.ui.activity.SplashActivity;
import com.example.project.utils.GsonUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class SplashActPresenter extends BasePresenter {
    private IBaseView mView;
    private RxPoretorImpl mImpl;

    public SplashActPresenter(IBaseView view) {
        this.mView = view;
        mImpl = new RxPoretorImpl();
    }

    @Override
    public void start(Object[] t) {
        String device = (String) t[0]; //android
        Map<String, Object> params = new HashMap<>();
        params.put("device", device);
        mImpl.rxPostRequest(Constants.RELEASE_BASE_URL + Constants.DECODE, params, new RxObserverCallBack() {
            @Override
            public void onSucceed(Object obj) {
                ResponseBody body = (ResponseBody) obj;
                try {
                    String jsonStr = body.string();
                    DecodeBean decodeBean = GsonUtil.jsonStr2Bean(jsonStr, DecodeBean.class);
                    if (mView != null) {
                        mView.onSucceed(decodeBean);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    if (mView != null) {
                        mView.onFiled(e.getMessage());
                    }
                }

            }

            @Override
            public void onFiled(String errorMsg) {
                if (mView != null) {
                    mView.onFiled(errorMsg);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        });
    }
}
