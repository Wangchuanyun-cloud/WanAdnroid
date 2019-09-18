package com.example.newgankio.View;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.Utils.FrameUtils.BaseActivity;
import com.example.newgankio.Adapter.ViewpagerAdapter;
import com.example.newgankio.Presenter.HomePagePresenter;
import com.example.newgankio.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageActivity extends BaseActivity<HomePagePresenter> implements HomepageView {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private TextView userName;
    private String username;
    private View headerLayout;
    private ViewpagerAdapter myFragmentAdapter;

    @Override
    public HomePagePresenter createPresenter() {
        presenter = new HomePagePresenter();
        return presenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_homepage;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        headerLayout = navView.getHeaderView(0);
        userName = headerLayout.findViewById(R.id.username);
        getUser();
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu2);
        }
       // navView.setCheckedItem(R.id.back);//默认为返回登录
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.back:
                        Intent intent1 = new Intent(HomePageActivity.this,LoginActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.aboutUs:
                        showToast("该功能还未开通");
                    case R.id.myCollection :
                        showToast("该功能还未开通");
                    default:
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        myFragmentAdapter = new ViewpagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentAdapter);
        mTablayout.addTab(mTablayout.newTab().setText("首页").setIcon(R.drawable.first_ye));
        mTablayout.addTab(mTablayout.newTab().setText("知识体系").setIcon(R.drawable.zhishitixi));

        mTablayout.addTab(mTablayout.newTab().setText("项目").setIcon(R.drawable.xiangmu));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTablayout));
        mTablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch ((item.getItemId())){
            case android.R.id.home:
                if(drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.closeDrawers();
                drawerLayout.openDrawer(GravityCompat.START);
            default:
        }
        return  true;
    }

    @Override
    public void initData() {

    }

    @Override
    public void noNetwork() {
        Intent intent =new Intent(context,NoInternetActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void getUser() {
        Intent intent = getIntent();
        username = intent.getStringExtra("user");
        userName.setText(username);
    }

}
