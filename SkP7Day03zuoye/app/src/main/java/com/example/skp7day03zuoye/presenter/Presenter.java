package com.example.skp7day03zuoye.presenter;

import com.example.skp7day03zuoye.model.CallBack;
import com.example.skp7day03zuoye.model.Model;
import com.example.skp7day03zuoye.model.bean.VideoBean;
import com.example.skp7day03zuoye.view.IView;

public class Presenter {
    private IView iView;
    private Model model;

    public Presenter(IView iView) {
        this.iView = iView;
        model = new Model();
    }

    public void getShaFaData(int pageCount,String feedType){
        model.getShaFaData(pageCount, feedType, new CallBack() {
            @Override
            public void getTrue(Object object) {
                iView.getTrue(object);
            }

            @Override
            public void getFalse(String error) {
                iView.getFalse(error);
            }
        });

    }

    public void getShaFaData(int pageCount,String feedType,String feedId){
        model.getShaFaData(pageCount, feedType,feedId,new CallBack() {
            @Override
            public void getTrue(Object object) {
                iView.getTrue(object);
            }

            @Override
            public void getFalse(String error) {
                iView.getFalse(error);
            }
        });
    }

    public void getFaXianData(){
        model.getFaXianData(new CallBack() {
            @Override
            public void getTrue(Object object) {
                iView.getTrue(object);
            }

            @Override
            public void getFalse(String error) {
                iView.getFalse(error);
            }
        });
    }
}
