package com.example.skp7day04zuoye.presenter;

import com.example.skp7day04zuoye.model.CallBack;
import com.example.skp7day04zuoye.model.Model;
import com.example.skp7day04zuoye.view.IView;

public class Presenter {
    private IView iView;
    private Model model;

    public Presenter(IView iView) {
        this.iView = iView;
        model = new Model();
    }

    public void getHuodont() {

        model.getHuodong(new CallBack() {
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
