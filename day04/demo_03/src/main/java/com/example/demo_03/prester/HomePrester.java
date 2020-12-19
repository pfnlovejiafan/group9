package com.example.demo_03.prester;

import com.example.demo_03.callback.Homecallback;
import com.example.demo_03.model.HomeModel;
import com.example.demo_03.view.IView;

public class HomePrester {
    private final HomeModel homeModel;
    private IView view;

    public HomePrester(IView view) {
        this.view = view;
        homeModel = new HomeModel();
    }

    public void start() {
        homeModel.request(new Homecallback() {
            @Override
            public void OnChenggong(Object ob) {
                view.ShowChenggong(ob);
            }

            @Override
            public void OnShibai(String str) {
view.ShowShibai(str);
            }
        });

    }
}
