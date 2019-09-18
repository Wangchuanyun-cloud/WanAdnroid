package com.example.Utils.FrameUtils;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newgankio.View.NoInternetActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    public Context mContext;
    public Toast mToast;
    public P mPresenter;
    public View mView;

    public BaseFragment() {

    }

    public abstract P createPresenter();
    public abstract int getLayoutId();
    public abstract void initView();
    public abstract void initData();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutId(), container, false);
        mContext = getActivity();
        mView = view;
        mPresenter = createPresenter();
        mPresenter.attach(this);
        initView();
        initData();
        return view;
    }

    @Override
    public void showToast(String s){
        if(mToast == null)
            mToast = Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT);
        mToast.show();
    }
    @Override
    public void noNetwork(){
        Intent intent =new Intent(mContext, NoInternetActivity.class);
        startActivity(intent);
    }
}
