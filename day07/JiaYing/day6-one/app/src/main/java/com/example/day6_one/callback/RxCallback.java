package com.example.day6_one.callback;

public interface RxCallback<T> {
   void onSuccessData(T t);
   void onErrorMsg(String msg);
}
