package com.example.newgankio.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.Utils.FrameUtils.BaseActivity;
import com.example.Utils.FrameUtils.BasePresenter;
import com.example.Utils.FrameUtils.BaseView;
import com.example.newgankio.Presenter.RegisterPresenter;
import com.example.newgankio.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView{

    @BindView(R.id.user_edit2)
    EditText userEdit2;
    @BindView(R.id.password_edit2)
    EditText passwordEdit2;
    @BindView(R.id.confirm_edit)
    EditText confirmEdit;
    @BindView(R.id.confirm_register)
    Button confirmRegister;

    @Override
    public RegisterPresenter createPresenter() {
        presenter = new RegisterPresenter();
        return presenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void noNetwork(){
        Intent intent =new Intent(context, NoInternetActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.confirm_register)
    public void onClick(View view) {
        presenter.register(userEdit2.getText().toString(),passwordEdit2.getText().toString(),
                confirmEdit.getText().toString());
    }

    @Override
    public void sendUserName() {
        Intent intent = new Intent(RegisterActivity.this,HomePageActivity.class);
        intent.putExtra("user",userEdit2.getText().toString());
        startActivity(intent);
        finish();
    }
}
