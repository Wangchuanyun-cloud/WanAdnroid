package com.example.Utils.HttpUtils;

import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRtrofit {
    public final String BASE_SERVER_URL = "http://www.wanandroid.com/";   //baseurl

    private static ApiRtrofit apiRtrofit;
    private ServerApi serverApi;
    private Retrofit retrofit;

    private ApiRtrofit(){
        //Log.d("这是","联网1");
        retrofit = new Retrofit.Builder()      //初始化
                .baseUrl(BASE_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        serverApi = retrofit.create(ServerApi.class);   //
    }

    public static ApiRtrofit getInstance(){
        if(apiRtrofit == null)
            synchronized (ApiRtrofit.class) {
                if (apiRtrofit == null) {
                    apiRtrofit = new ApiRtrofit();
                }
            }
            return apiRtrofit;
    }

    public ServerApi getServerApi() {
       // Log.d("这是","联网2");
        return serverApi;
    }
}
