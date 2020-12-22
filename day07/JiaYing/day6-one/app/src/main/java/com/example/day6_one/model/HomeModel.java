package com.example.day6_one.model;

import com.example.day6_one.callback.RxCallback;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class HomeModel {
    //使用接口回调    返回P层
    public void requestData(RxCallback callback) {
        Request request = new Request.Builder().url("https://fanyi.baidu.com/?aldtype=85#en/zh/detach")
                .build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onErrorMsg(e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String string = response.body().string();
                callback.onSuccessData(string);
            }
        });

    }
}
