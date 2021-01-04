package com.example.project.utils;

import com.google.gson.Gson;

//Gson解析的工具类
public class GsonUtil {

    private static Gson mGson;

    //静态代码块  跟着类的加载而加载，只调用一次
    static {
        mGson = new Gson();
    }

    //构造代码块
    {}


    //将json串解析成实体Bean  一般情况
    public static <T> T jsonStr2Bean(String jsonStr, Class<T> tClass) {
        T t = mGson.fromJson(jsonStr, tClass);
        return t;
    }

}
