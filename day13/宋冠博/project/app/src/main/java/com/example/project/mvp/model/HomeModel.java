package com.example.project.mvp.model;



import com.example.project.callback.RxObserverCallBack;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeModel {
    //TODO 3.M层收到指令后，请求数据，通过接口回调返回给P层
    public void requestData(RxObserverCallBack callBack) {
        Request request = new Request.Builder().
                url("https://fanyi.baidu.com/?aldtype=85#en/zh/detach").build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFiled(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                callBack.onSucceed(string);
            }
        });

    }
}