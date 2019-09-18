package com.example.Utils.FrameUtils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.newgankio.View.NoInternetActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{
    public Context context;
    public Toast toast;
    public P presenter;

    public abstract  P createPresenter();  //获取q层实例
    public abstract int getLayoutId();   // 获取布局
    public abstract void initView();     //  初始化布局
    public abstract void initData();     //初始化数据

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        context = this;
        setContentView(getLayoutId());
        presenter = createPresenter();
        presenter.attach(this);   //与p层建立联系
        initView();
        initData();
    }



    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.removeDisposable();
        if(presenter != null)
            presenter.detachView();
    }

    @Override
    public void showToast(String s){     // 在有需求时toast消息
        if(toast == null)
            toast = Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void noNetwork(){
        Intent intent =new Intent(context, NoInternetActivity.class);
        startActivity(intent);
    }
}
