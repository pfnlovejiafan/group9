package com.example.day4_jiaying.presenter;

import com.example.day4_jiaying.View.IView;
import com.example.day4_jiaying.callback.Resultback;
import com.example.day4_jiaying.model.PaoModel;

public class PaoPresenter {
    private IView mIView;
    private PaoModel paoModel;

    public PaoPresenter(IView mIView) {
        this.mIView = mIView;
        paoModel=new PaoModel();
    }


    public void start() {
        paoModel.requetsPaoData(new Resultback() {
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
