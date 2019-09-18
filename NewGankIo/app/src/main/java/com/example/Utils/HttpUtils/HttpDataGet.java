package com.example.Utils.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public interface HttpDataGet {
    public  void  addDisposable(Observable<?> observable,Callback callback);

    public interface Callback{
       public void onResponse(Object response);
       public void onFail(Object errow);
    }
}
