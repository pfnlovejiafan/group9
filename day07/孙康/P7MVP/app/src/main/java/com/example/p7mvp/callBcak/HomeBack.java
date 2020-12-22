package com.example.p7mvp.callBcak;

import com.example.p7mvp.mvp.model.bean.ResultBean;

public interface HomeBack {
    void resultSucceed(ResultBean bean);
    void error(Throwable error);
}
