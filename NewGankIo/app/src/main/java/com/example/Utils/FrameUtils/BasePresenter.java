package com.example.Utils.FrameUtils;


import com.example.Utils.HttpUtils.ApiRtrofit;
import com.example.Utils.HttpUtils.HttpDataGet;
import com.example.Utils.HttpUtils.ServerApi;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter<V extends BaseView> implements HttpDataGet{
    private CompositeDisposable compositeDisposable;

    public V view;
    public ServerApi serverApi = ApiRtrofit.getInstance().getServerApi();

    //   控制p层的生命周期

    public void attach (V view){   // 与v层建立联系
        this.view = view;
    }

    public void detachView(){      // 与v层断开联系
        view = null;

    }


    public void removeDisposable(){
        if(compositeDisposable != null)
            compositeDisposable.dispose();

    }


    // 网络封装添加水管
    @Override
    public void addDisposable(Observable<?> observable, final Callback callback) {
        if(compositeDisposable == null){

            compositeDisposable = new CompositeDisposable();
            // Log.d("这是","联网3");
        }
        // Log.d("这是","联网3");
        DisposableObserver observer = new DisposableObserver() {
            @Override
            public void onNext(Object o) {
                callback.onResponse(o);
            }

            @Override
            public void onError(Throwable e) {
                  callback.onFail(e);
            }

            @Override
            public void onComplete() {

            }
        };

        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer));
    }
}
