package com.example.newgankio.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.newgankio.Model.ChildrenItem;
import com.example.newgankio.View.ChildrenFragment;

import java.util.ArrayList;
import java.util.List;

public class ChildrenFragmentAdapter extends FragmentStatePagerAdapter {
    private List<ChildrenItem>childrenItemList1 ;
    private List<ChildrenItem>childrenItemList2 ;
    private List<ChildrenItem>childrenItemList3 ;
    private List<ChildrenItem>childrenItemList4 ;
    private String[]TextTab = new String[]{"完整项目","下拉刷新","富文本编辑器","RV列表动效"};
    public ChildrenFragmentAdapter(FragmentManager fm,
                                     List<ChildrenItem>childrenItemList1 ,
                                     List<ChildrenItem>childrenItemList2,
                                     List<ChildrenItem>childrenItemList3 ,
                                     List<ChildrenItem>childrenItemList4   ) {
        super(fm);
        this.childrenItemList1 = childrenItemList1;
        this.childrenItemList2 = childrenItemList2;
        this.childrenItemList3 = childrenItemList3;
        this.childrenItemList4 = childrenItemList4;
    }
    @Override
    public Fragment getItem(int position) {
        if(position == 1){
            ChildrenFragment childrenFragment =  new ChildrenFragment();
            childrenFragment.getData(childrenItemList2);
            return childrenFragment;
        }else if(position ==2){
            ChildrenFragment childrenFragment =  new ChildrenFragment();
            childrenFragment.getData(childrenItemList3);
            return childrenFragment;
        }else if(position ==3){
            ChildrenFragment childrenFragment =  new ChildrenFragment();
            childrenFragment.getData(childrenItemList4);
            return childrenFragment;
        }
        ChildrenFragment childrenFragment =  new ChildrenFragment();
        childrenFragment.getData(childrenItemList1);
        return childrenFragment;
    }

    @Override
    public int getCount() {
        return TextTab.length;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return TextTab[position];
    }
}

