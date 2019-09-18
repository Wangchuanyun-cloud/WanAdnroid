package com.example.newgankio.View;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.Utils.FrameUtils.BaseFragment;
import com.example.Utils.FrameUtils.BasePresenter;
import com.example.Utils.FrameUtils.BaseView;
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
public class ChildrenFragment extends BaseFragment<BasePresenter> implements BaseView {


    @BindView(R.id.children_recycler)
    RecyclerView childrenRecycler;
    private List<ChildrenItem> mchildrenItemList ;
    ChildrenAdapter childrenAdapter;
    Unbinder unbinder;


    public ChildrenFragment() {
    }

    @Override
    public BasePresenter createPresenter() {
        mPresenter = new BasePresenter();
        return mPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_children;
    }

    public void getData(List<ChildrenItem>list1){
        mchildrenItemList = list1;
        //Log.d("这是",mchildrenItemList.get(0).getPhoneUrl());
    }
    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, mView);
        mContext = this.getActivity();
        childrenAdapter = new ChildrenAdapter(mchildrenItemList,mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        childrenRecycler.setLayoutManager(linearLayoutManager);
        childrenRecycler.setAdapter(childrenAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void noNetwork() {
        Intent intent =new Intent(mContext, NoInternetActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


    public class ChildrenAdapter extends RecyclerView.Adapter<ChildrenAdapter.ViewHolder>{
        private List<ChildrenItem>childrenItemList;
        private Context mContext;


        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView phonepic;
            TextView title;
            TextView aboutContent;
            TextView aboutWriter;
            public ViewHolder(View itemView) {
                super(itemView);
                phonepic = itemView.findViewById(R.id.phone_pic);
                title = itemView.findViewById(R.id.title);
                aboutContent = itemView.findViewById(R.id.about_content);
                aboutWriter = itemView.findViewById(R.id.about_writer);
            }
        }

        public ChildrenAdapter(List<ChildrenItem>list ,Context context){
            childrenItemList = list;
            mContext =context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.children_item,parent,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final ChildrenItem childrenItem = childrenItemList.get(position);
            holder.title.setText(childrenItem.getTitle());
            holder.aboutContent.setText(childrenItem.getAboutContent());
            holder.aboutWriter.setText(childrenItem.getAboutWriter());
            Log.d("这是",childrenItem.getPhoneUrl());
            Glide.with(mContext).load(childrenItem.getPhoneUrl()).into(holder.phonepic);
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),ArticleToWebViewActivity.class);
                    intent.putExtra("link",childrenItem.getURL());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return childrenItemList.size();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
