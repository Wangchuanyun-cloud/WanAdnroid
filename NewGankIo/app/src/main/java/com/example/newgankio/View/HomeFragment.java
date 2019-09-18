package com.example.newgankio.View;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Utils.FrameUtils.BaseFragment;
import com.example.Utils.FrameUtils.GlideImageLoader;
import com.example.newgankio.Model.ArticleItem;
import com.example.newgankio.Presenter.HomeFragPresenter;
import com.example.newgankio.R;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomeFragPresenter> implements HomeFragView{
    private ArticleAdapter articleAdapter;
    public String a ;

    @BindView(R.id.first_banner)
    Banner firstBanner;
    @BindView(R.id.article_recycler)
    RecyclerView articleRecycler;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public HomeFragPresenter createPresenter() {
        mPresenter = new HomeFragPresenter();
        return mPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, mView);
        mPresenter.getPic();
        mPresenter.getArticle(mPresenter.page);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        articleRecycler.setLayoutManager(linearLayoutManager);
        articleAdapter = new ArticleAdapter(mPresenter.articleItemList);
        articleRecycler.setAdapter(articleAdapter);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_orange_dark,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);//设置刷新时的颜色
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                mPresenter.getArticle(mPresenter.page);
                //Log.d("a是是是是",a);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void noNetwork() {
        Intent intent =new Intent(mContext,NoInternetActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void showBannerContent(List<String> pic, final List<String> article) {
        firstBanner.setImageLoader(new GlideImageLoader()).setImages (pic)
                .isAutoPlay(true).setDelayTime(3000).start();
        firstBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(),ArticleToWebViewActivity.class);
                intent.putExtra("link",article.get(position));
                startActivity(intent);
            }
        });

    }

    @Override
    public void showListArticle(List<ArticleItem> articleItem) {
        Log.d("这是",articleItem.get(0).getNicetime());
        swipeRefresh.setRefreshing(false);
        articleAdapter.notifyDataSetChanged();
        articleRecycler.smoothScrollToPosition(mPresenter.number);
    }


    public class ArticleAdapter extends  RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
        private List<ArticleItem> myarticleList ;
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView articleTitle;
            TextView articleWriter;
            TextView articleTime;
            ImageView articleImage;
            public ViewHolder(View itemView) {
                super(itemView);
                articleTitle = (TextView)itemView.findViewById(R.id.article_title);
                articleWriter = (TextView)itemView.findViewById(R.id.article_writer);
                articleTime = (TextView)itemView.findViewById(R.id.article_time);
                articleImage = (ImageView)itemView.findViewById(R.id.article_image);
            }
        }
        public ArticleAdapter(List<ArticleItem>articleList){
            myarticleList = articleList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item,parent,false);
            final  ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final ArticleItem article = myarticleList.get(position);
            holder.articleTitle.setText(article.getTitle());
            holder.articleWriter.setText(article.getWriter());
            holder.articleTime.setText(article.getNicetime());
            holder.articleTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ArticleToWebViewActivity.class);
                    intent.putExtra("link",article.getURL());
                    startActivity(intent);
                }
            });
            final boolean[] iszan = {false};
            holder.articleImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(iszan[0] == true){
                        holder.articleImage.setImageResource(R.drawable.collection);
                        Toast.makeText(getActivity(),"大佬你取消点赞了！！！",Toast.LENGTH_SHORT).show();
                        iszan[0] =false;
                    }else{holder.articleImage.setImageResource(R.drawable.collection2);
                        Toast.makeText(getActivity(),"大佬你已经点赞了！！！",Toast.LENGTH_SHORT).show();
                        iszan[0] =true;}

                }
            });
        }

        @Override
        public int getItemCount() {
            return myarticleList.size();
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
