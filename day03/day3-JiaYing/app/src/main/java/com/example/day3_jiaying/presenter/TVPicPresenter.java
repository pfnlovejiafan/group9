package com.example.day3_jiaying.presenter;

import com.example.day3_jiaying.View.IView;
import com.example.day3_jiaying.callback.Resultback;
import com.example.day3_jiaying.model.TVModel;

public class TVPicPresenter {
    private IView mIView;
    private TVModel tvModel;

    public TVPicPresenter(IView mIView) {
        this.mIView = mIView;
        tvModel=new TVModel();
    }

    public void start() {
        tvModel.requestmodel(new Resultback() {
            @Override
            public void showokData(Object obj) {
                mIView.showokUi(obj);
            }

            @Override
            public void shownoData(String msg) {
                mIView.shownoUi(msg);
            }
        });
    }
}
