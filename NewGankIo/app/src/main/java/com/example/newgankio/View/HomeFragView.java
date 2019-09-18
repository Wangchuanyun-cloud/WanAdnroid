package com.example.newgankio.View;

import com.example.Utils.FrameUtils.BaseView;
import com.example.newgankio.Model.ArticleItem;

import java.util.List;

public interface HomeFragView extends BaseView{
    void showBannerContent(List<String> pic , List<String> article);
    void showListArticle(List<ArticleItem> articleItem);
}
