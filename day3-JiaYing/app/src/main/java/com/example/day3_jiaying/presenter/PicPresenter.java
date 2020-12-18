package com.example.day3_jiaying.presenter;

import com.example.day3_jiaying.model.PicModel;
import com.example.day3_jiaying.View.IView;
import com.example.day3_jiaying.callback.Resultback;

public class PicPresenter {
    private IView mIView;
    private PicModel picModel;

    public PicPresenter(IView mIView) {
        this.mIView = mIView;
        picModel=new PicModel();
    }

    public void start() {
        picModel.requestData(new Resultback() {
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
