package com.example.myapplication.mvp.model;

import com.example.myapplication.calback.RxObserverCallBack;
import com.example.myapplication.di.component.DaggerOkComponent;
import com.example.myapplication.mvp.model.api.APiSelect;
import javax.inject.Inject;

public class RxOpretorImpl {
    @Inject
    APiSelect aPiSelect;
    public RxOpretorImpl(){
        DaggerOkComponent.create().getSingleApiServicr(this);
    }
    public <T> void rxGetRequest(String url, RxObserverCallBack<T> callBack){
        RxOperator
    }
}
