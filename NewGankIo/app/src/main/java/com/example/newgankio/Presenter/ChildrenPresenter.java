package com.example.newgankio.Presenter;

import android.content.Intent;
import android.util.Log;

import com.example.Utils.FrameUtils.BasePresenter;
import com.example.newgankio.Model.ArticleOfHomeBean;
import com.example.newgankio.Model.ChildrenItem;
import com.example.newgankio.Model.KnowladgeBean;
import com.example.newgankio.View.ProjectView;

import java.util.ArrayList;
import java.util.List;

public class ChildrenPresenter extends BasePresenter<ProjectView> {
    public List<ChildrenItem> childrenItemList1 = new ArrayList<>();
    public List<ChildrenItem>childrenItemList2 = new ArrayList<>();
    public List<ChildrenItem>childrenItemList3 = new ArrayList<>();
    public List<ChildrenItem>childrenItemList4 = new ArrayList<>();

    public void getData(){
        addDisposable(serverApi.getChildren(), new Callback() {

            @Override
            public void onResponse(Object response) {
                for(int i = 0;i<4;i++){
                    final int finalI = i;
                    addDisposable(serverApi.getArticle("1", ((KnowladgeBean) response).getData().get(i).getId()),
                            new Callback() {
                                @Override
                                public void onResponse(Object response) {
                                    for(int j = 0;j<((ArticleOfHomeBean)response).getData().getDatas().size();j++){
                                   ChildrenItem childrenItem = new ChildrenItem(((ArticleOfHomeBean)response).getData().getDatas().get(j).getEnvelopePic(),
                                           ((ArticleOfHomeBean)response).getData().getDatas().get(j).getTitle(),
                                           ((ArticleOfHomeBean)response).getData().getDatas().get(j).getDesc(),
                                           ((ArticleOfHomeBean)response).getData().getDatas().get(j).getAuthor(),
                                           ((ArticleOfHomeBean)response).getData().getDatas().get(j).getLink());
                                        if( finalI == 0){
                                            childrenItemList1.add(childrenItem);
                                        }
                                        else if(finalI ==1){
                                            childrenItemList2.add(childrenItem);
                                        }else if(finalI ==2){
                                            childrenItemList3.add(childrenItem);
                                        }else if(finalI ==3){
                                            childrenItemList4.add(childrenItem);
                                        }
                                        view.showContent( childrenItemList1  ,
                                               childrenItemList2,
                                                childrenItemList3 ,
                                               childrenItemList4  );

                                }
                                }
                                @Override
                                public void onFail(Object errow) {
                                    view.noNetwork();
                                }
                            });
                }
            }

            @Override
            public void onFail(Object errow) {
                view.noNetwork();
            }
        });
    }
}
