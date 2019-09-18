package com.example.newgankio.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.Utils.FrameUtils.BaseActivity;
import com.example.newgankio.Presenter.LoginPresenter;
import com.example.newgankio.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @BindView(R.id.user_edit)
    EditText userEdit;
    @BindView(R.id.password_edit)
    EditText passwordEdit;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.checkbox)
    CheckBox checkbox;

    @Override
    public LoginPresenter createPresenter() {
        // Log.d("hahah","与presenter绑定了");
        presenter = new LoginPresenter();
        return presenter;
    }

    @Override
    public int getLayoutId() {
        // Log.d("hahah","布局了");
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        //Log.d("hahah","加载了");
        ButterKnife.bind(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = preferences.getBoolean("remember_password",false);
        if(isRemember){
            String account = preferences.getString("account","");
            String password = preferences.getString("password","");
            userEdit.setText(account);
            passwordEdit.setText(password);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void noNetwork() {
        Intent intent = new Intent(context, NoInternetActivity.class);
        startActivity(intent);
    }


    @OnClick({R.id.login, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                //  Log.d("hahah","登陆了");
                //  Log.d(userEdit.getText().toString(),passwordEdit.getText().toString());
                presenter.login(userEdit.getText().toString(), passwordEdit.getText().toString());
                break;
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void sendUserName() {
        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
        intent.putExtra("user", userEdit.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void remember() {
        String account = userEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        editor = preferences.edit();
        if(checkbox.isChecked()){
            editor.putBoolean("remember_password",true);
            editor.putString("account",account);
            editor.putString("password",password);
        }else{
            editor.clear();
        }
        editor.apply();
    }

}
