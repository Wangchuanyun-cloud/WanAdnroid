package com.example.newgankio.Presenter;


import com.example.Utils.FrameUtils.BasePresenter;
import com.example.newgankio.Model.ArticleItem;
import com.example.newgankio.Model.ArticleOfHomeBean;
import com.example.newgankio.Model.PicOfBannerBean;
import com.example.newgankio.View.HomeFragView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragPresenter extends BasePresenter<HomeFragView> {
    public String page = "0";
    public int number = 0;
    private List<String> picofBanner = new ArrayList<>();
    private List<String>articleOfBanner = new ArrayList<>();
    public List<ArticleItem>articleItemList = new ArrayList<>();
    public void getArticle(String s){

        addDisposable(serverApi.articleOfHome(s), new Callback() {
            @Override
            public void onResponse(Object response) {
                page = ((ArticleOfHomeBean)response).getData().getCurPage();


                for (int i = 0;i<((ArticleOfHomeBean)response).getData().getDatas().size();i++){
                    ArticleItem articleItem = new ArticleItem(((ArticleOfHomeBean)response).getData().getDatas().get(i)
                    .getTitle(),((ArticleOfHomeBean)response).getData().getDatas().get(i).getAuthor(),
                            ((ArticleOfHomeBean)response).getData().getDatas().get(i).getNiceDate(),
                            ((ArticleOfHomeBean)response).getData().getDatas().get(i).getLink());
                    articleItemList.add(articleItem);
                }
                view.showListArticle(articleItemList);
                number += ((ArticleOfHomeBean)response).getData().getDatas().size();
            }

            @Override
            public void onFail(Object errow) {
                view.noNetwork();
            }
        });
    }

    public void getPic(){
        addDisposable(serverApi.getClassify("banner"), new Callback() {
            @Override
            public void onResponse(Object response) {
                for(int i = 0;i < ((PicOfBannerBean)response).getData().size();i++){
                   picofBanner.add (((PicOfBannerBean)response).getData().get(i).getImagePath());
                   articleOfBanner.add(((PicOfBannerBean) response).getData().get(i).getUrl());
                   view.showBannerContent(picofBanner,articleOfBanner);
                   //Log.d("bannerPIC",((PicOfBannerBean)response).getData().get(i).getImagePath());
                }
            }

            @Override
            public void onFail(Object errow) {
                view.noNetwork();

            }
        });
    }

}
