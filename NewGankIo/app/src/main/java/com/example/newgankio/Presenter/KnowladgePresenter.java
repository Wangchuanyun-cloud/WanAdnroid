package com.example.newgankio.Presenter;

import com.example.Utils.FrameUtils.BasePresenter;
import com.example.newgankio.Model.ArticleItem;
import com.example.newgankio.Model.KnowladgeBean;
import com.example.newgankio.Model.KnowladgeItem;
import com.example.newgankio.View.KnowladgeView;

import java.util.ArrayList;
import java.util.List;

public class KnowladgePresenter extends BasePresenter <KnowladgeView> {
    public  List<KnowladgeItem>knowladgeBeanList = new ArrayList<>();
    public void getKnowladge(){
        addDisposable(serverApi.getKnowladge("tree"), new Callback() {
            @Override
            public void onResponse(Object response) {
                for(int i = 0;i<((KnowladgeBean)response).getData().size();i++){
                    String content = "   ";
                    for(int j = 0;j < ((KnowladgeBean)response).getData().get(i).getChildren().size();j++){
                     content  +=  ((KnowladgeBean)response).getData().get(i).getChildren().get(j).getName() + "    ";

                    }
                    KnowladgeItem knowladgeItem = new KnowladgeItem (((KnowladgeBean)response)
                            .getData().get(i).getName(),content);
                    knowladgeBeanList.add(knowladgeItem);
                }
                view.showKnowladgeContent(knowladgeBeanList);
            }

            @Override
            public void onFail(Object errow) {
                view.noNetwork();

            }
        });
    }


}
