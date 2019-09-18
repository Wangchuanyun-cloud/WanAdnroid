package com.example.newgankio.View;

import com.example.Utils.FrameUtils.BaseView;
import com.example.newgankio.Model.ChildrenItem;

import java.util.List;

public interface ProjectView extends BaseView {
    void showContent( List<ChildrenItem> childrenItemList1 ,
                      List<ChildrenItem>childrenItemList2,
                      List<ChildrenItem>childrenItemList3 ,
                      List<ChildrenItem>childrenItemList4  );
}
