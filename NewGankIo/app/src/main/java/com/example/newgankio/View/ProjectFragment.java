package com.example.newgankio.View;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.Utils.FrameUtils.BaseFragment;
import com.example.newgankio.Adapter.ChildrenFragmentAdapter;
import com.example.newgankio.Model.ChildrenItem;
import com.example.newgankio.Presenter.ChildrenPresenter;
import com.example.newgankio.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment<ChildrenPresenter> implements ProjectView {

    public List<ChildrenItem> childrenItemList1 = new ArrayList<>();
    public List<ChildrenItem>childrenItemList2 = new ArrayList<>();
    public List<ChildrenItem>childrenItemList3 = new ArrayList<>();
    public List<ChildrenItem>childrenItemList4 = new ArrayList<>();
    @BindView(R.id.children_tab)
    TabLayout childrenTab;
    @BindView(R.id.children_viewpager)
    ViewPager childrenViewpager;
    Unbinder unbinder;

    @Override
    public ChildrenPresenter createPresenter() {
      //  Log.d("hahah","与presenter绑定了");
        mPresenter = new ChildrenPresenter();
        return mPresenter;
    }

    @Override
    public int getLayoutId() {
        //Log.d("hahah","布局了");
        return R.layout.fragment_project;
    }

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, mView);
        mPresenter.getData();
        //Log.d("这是","START");
    }

    @Override
    public void initData() {

    }

    @Override
    public void noNetwork(){
        Intent intent =new Intent(mContext, NoInternetActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public ProjectFragment() {
        // Required empty public constructor
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showContent( List<ChildrenItem>childrenItemList1 ,
                             List<ChildrenItem>childrenItemList2,
                             List<ChildrenItem>childrenItemList3 ,
                             List<ChildrenItem>childrenItemList4  ) {
        this.childrenItemList1 =  mPresenter.childrenItemList1;
        //Log.d("这是",childrenItemList1.get(0).getPhoneUrl());
        this.childrenItemList2 =  mPresenter.childrenItemList2;
        this.childrenItemList3 =  mPresenter.childrenItemList3;
        this.childrenItemList4 =  mPresenter.childrenItemList4;
        ChildrenFragmentAdapter childrenFragmentAdapter = new ChildrenFragmentAdapter(getChildFragmentManager(),
                childrenItemList1,childrenItemList2,childrenItemList3,childrenItemList4);
        childrenViewpager.setAdapter(childrenFragmentAdapter);
        childrenTab.setupWithViewPager(childrenViewpager);

    }
}
