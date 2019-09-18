package com.example.newgankio.Presenter;


import com.example.Utils.FrameUtils.BasePresenter;
import com.example.newgankio.Model.RegisterAndLoginBean;
import com.example.newgankio.View.LoginView;


public class LoginPresenter extends BasePresenter<LoginView> {

    private boolean isErrow;

    public void login(String username, String password) {
        addDisposable(serverApi.login("login",username, password), new Callback() {
            @Override
            public void onResponse(Object response) {
                String loginMsg = "";
                if (((RegisterAndLoginBean) response).getErrorCode() == -1) {
                    isErrow = true;
                     loginMsg = ((RegisterAndLoginBean) response).getErrorMsg();
                }else if(((RegisterAndLoginBean) response).getErrorCode() == 0){
                    isErrow = false;
                    loginMsg = "欢迎进入wanandroid";
                }
                if(isErrow){
                    view.showToast(loginMsg);
                }else{
                    view.showToast(loginMsg);
                    view.remember();
                    view.sendUserName();
                }
            }

            @Override
            public void onFail(Object errow) {
                view.noNetwork();

            }
        });
    }
}
