package com.example.newgankio.Presenter;

import com.example.Utils.FrameUtils.BasePresenter;
import com.example.newgankio.Model.RegisterAndLoginBean;
import com.example.newgankio.View.RegisterView;

public class RegisterPresenter extends BasePresenter<RegisterView>{

    private boolean isErrow;

    public void register(String username,String password,String repassword){
        addDisposable(serverApi.register("register",username, password, repassword), new Callback() {
            @Override
            public void onResponse(Object response) {
                String registerMsg;
                if (((RegisterAndLoginBean) response).getErrorCode() == 1) {
                    isErrow = true;
                    registerMsg = ((RegisterAndLoginBean) response).getErrorMsg();
                }else{
                    isErrow = false;
                    registerMsg = "欢迎进入wanandroid";}
                if(isErrow){
                view.showToast(registerMsg);
                }else{
                    view.showToast(registerMsg);
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
