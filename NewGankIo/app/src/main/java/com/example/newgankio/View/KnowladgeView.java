package com.example.newgankio.View;

import com.example.Utils.FrameUtils.BaseView;
import com.example.newgankio.Model.KnowladgeItem;

import java.util.List;

public interface KnowladgeView extends BaseView {
    void showKnowladgeContent(List<KnowladgeItem>knowladgeItemList);
}
