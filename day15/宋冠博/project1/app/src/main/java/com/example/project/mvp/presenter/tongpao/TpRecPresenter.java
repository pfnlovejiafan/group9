package com.example.project.mvp.presenter.tongpao;

import com.example.project.base.BaseFragment;
import com.example.project.base.BasePresenter;
import com.example.project.base.IBaseView;
import com.example.project.callback.RxObserverCallBack;
import com.example.project.mvp.model.RxPoretorImpl;
import com.example.project.mvp.model.constants.Constants;
import com.example.project.mvp.model.entity.tongpao.BannerBean;
import com.example.project.mvp.model.entity.tongpao.Topic_discussedBean;
import com.example.project.mvp.ui.fragment.tongpao.RecommendFragment;
import com.example.project.utils.GsonUtil;

import java.io.IOException;

import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class TpRecPresenter extends BasePresenter {

    private IBaseView mView;
    private RxPoretorImpl mImpl;

    public TpRecPresenter(IBaseView view) {
        mView = view;
        mImpl = new RxPoretorImpl();
    }

    @Override
    public void start() {
        mImpl.rxGetRequest(Constants.TONGPAO_BASE_URL + Constants.TONGPAO_HOME_BANNER, new RxObserverCallBack() {
            @Override
            public void onSucceed(Object o) {
                if (mView != null) {
                    ResponseBody body = (ResponseBody) o;
                    try {
                        String jsonStr = body.string();
                        BannerBean bannerBean = GsonUtil.jsonStr2Bean(jsonStr, BannerBean.class);

                        mView.onSucceed(bannerBean);

                    } catch (IOException e) {
                        e.printStackTrace();
                        mView.onFiled("banner" + e.getMessage());
                    }
                }
            }

            @Override
            public void onFiled(String errorMsg) {
                if (mView != null) {
                    mView.onFiled("banner" + errorMsg);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        });
        mImpl.rxGetRequest(Constants.TONGPAO_BASE_URL + Constants.TONGPAO_HOME_TALK, new RxObserverCallBack() {
            @Override
            public void onSucceed(Object o) {
                if (mView != null) {
                    ResponseBody body = (ResponseBody) o;
                    try {
                        String jsonStr = body.string();

                        Topic_discussedBean discussedBean = GsonUtil.jsonStr2Bean(jsonStr, Topic_discussedBean.class);

                        mView.onSucceed(discussedBean);

                    } catch (IOException e) {
                        e.printStackTrace();
                        mView.onFiled("talk" + e.getMessage());
                    }
                }
            }

            @Override
            public void onFiled(String errorMsg) {
                if (mView != null) {
                    mView.onFiled("talk" + errorMsg);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        });
    }
}
