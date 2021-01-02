package com.example.project.mvp.presenter;




import com.example.project.base.BasePresenter;
import com.example.project.callback.RxObserverCallBack;
import com.example.project.mvp.model.RxPoretorImpl;
import com.example.project.mvp.model.constants.Constants;
import com.example.project.mvp.ui.fragment.HomeFragment2;

import java.io.IOException;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class HomeFragPresenter extends BasePresenter {
//    private HomeModel model;
//    private HomeFragment mFragment;

//    public HomeFragPresenter(HomeFragment fragment) {
//        this.mFragment = fragment;
//        model = new HomeModel();
//    }

    //    @Override
//    public void start() {
//        //TODO 2.p层收到指令后向M层发送请求
//        model.requestData(new RxObserverCallBack() {
//            @Override
//            public void onSucceed(Object o) {
//                // TODO 4.P层再将数据交给V层更新UI
//                mFragment.onSucceed(o);
//            }
//
//            @Override
//            public void onFiled(String errorMsg) {
//                // TODO 4.P层再将数据交给V层更新UI
//                mFragment.onFiled(errorMsg);
//            }
//        });
//    }
    private static final String TAG = HomeFragment2.class.getSimpleName();

    private RxPoretorImpl mImpl;
    private HomeFragment2 mHomeFragment;

    public HomeFragPresenter(HomeFragment2 homeFragment) {
        this.mHomeFragment = homeFragment;
        mImpl = new RxPoretorImpl();

    }

    //2向M层进行数据请求，请求数据后将数据返回V层
    @Override
    public void start() {
        mImpl.rxGetRequest(Constants.BAIDU_LIST + Constants.BAIDU_LIST, new RxObserverCallBack<Object>() {

            @Override
            public void onSucceed(Object obj) {
                if (mHomeFragment != null) {
                    ResponseBody responseBody = (ResponseBody) obj;
                    try {
                        String string = responseBody.string();
                        //TODO GSON解析
//                        new Gson().fromJson(string, DataInfoBean);

                        mHomeFragment.onSucceed(string);

                    } catch (IOException e) {
                        e.printStackTrace();

                        mHomeFragment.onFiled(e.getMessage());
                    }

                }
            }

            @Override
            public void onFiled(String errorMsg) {
                if (mView != null) {
                    mHomeFragment.onFiled(errorMsg);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                if (disposable != null) {
                    addDisposable(disposable);
                }
            }
        });
//        addDisposable();
    }
}