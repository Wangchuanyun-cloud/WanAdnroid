package com.example.newgankio.View;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Utils.FrameUtils.BaseFragment;
import com.example.newgankio.Model.KnowladgeBean;
import com.example.newgankio.Model.KnowladgeItem;
import com.example.newgankio.Presenter.KnowladgePresenter;
import com.example.newgankio.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowladgeFragment extends BaseFragment<KnowladgePresenter> implements KnowladgeView {


    @BindView(R.id.knowledge_recycler)
    RecyclerView knowledgeRecycler;
    Unbinder unbinder;

    public KnowladgeFragment() {
        // Required empty public constructor
    }

    @Override
    public KnowladgePresenter createPresenter() {
        mPresenter = new KnowladgePresenter();
        return mPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_knowladge;
    }

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, mView);
        mPresenter.getKnowladge();
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

    @Override
    public void showKnowladgeContent(List<KnowladgeItem> knowladgeItemList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        knowledgeRecycler.setLayoutManager(linearLayoutManager);
        KnowledgeAdapter knowledgeAdapter = new KnowledgeAdapter(knowladgeItemList);
        knowledgeRecycler.setAdapter(knowledgeAdapter);

    }

    public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.ViewHolder>{
        private List<KnowladgeItem>myknowledgeList;

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView knowledgeTitle;
            TextView content;
            public ViewHolder(View itemView) {
                super(itemView);
                knowledgeTitle = itemView.findViewById(R.id.knowledgetitle);
                content = itemView.findViewById(R.id.content);
            }
        }

        public KnowledgeAdapter(List<KnowladgeItem>knowledgeList){
            myknowledgeList = knowledgeList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.knowladge_item,parent,false);
            final  ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            KnowladgeItem knowledge = myknowledgeList.get(position);
            holder.knowledgeTitle.setText(knowledge.getkTitle());
            holder.content.setText(knowledge.getkAbout());

            holder.content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return myknowledgeList.size();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
