package com.example.Utils.HttpUtils;


import com.example.newgankio.Model.ArticleOfHomeBean;
import com.example.newgankio.Model.KnowladgeBean;
import com.example.newgankio.Model.PicOfBannerBean;
import com.example.newgankio.Model.RegisterAndLoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ServerApi {

    @FormUrlEncoded      //注册API
    @POST("user/{path}")
    Observable<RegisterAndLoginBean> register(@Path("path")String path,@Field("username")String username,
                                              @Field("password")String password,@Field("repassword")String repassword);


    @FormUrlEncoded     //登录api
    @POST("user/{path}")
    Observable<RegisterAndLoginBean>login(@Path("path")String path,@Field("username")String username,
                                             @Field("password")String password);

    @GET("article/list/{page}/json")   // 首页的首页上面的文章list
    Observable<ArticleOfHomeBean>articleOfHome(@Path("page")String page);

    @GET("{path}/json")   //获取图片，分类目录
    Observable<PicOfBannerBean>getClassify(@Path("path")String path);

    @GET("{path}/json")   //获取图片，分类目录
    Observable<KnowladgeBean>getKnowladge(@Path("path")String path);

    @GET("project/tree/json")   //获取图片，分类目录
    Observable<KnowladgeBean>getChildren();



    @GET("project/list/{page}/json")   // 获取分类目录下的文章  cid : 60
    Observable<ArticleOfHomeBean>getArticle(@Path("page")String page,@Query("cid") String cid);
}
