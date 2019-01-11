package com.mcmo.z.module_login.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mcmo.z.commonlibrary.busniess.UserInfoSaver;
import com.mcmo.z.commonlibrary.mouduleinterface.LoginService;
import com.mcmo.z.commonlibrary.mouduleinterface.ModuleImpCons;
import com.mcmo.z.commonlibrary.net.ErrorMsg;
import com.mcmo.z.commonlibrary.net.RetrofitCallback;
import com.mcmo.z.commonlibrary.net.RetrofitManager;
import com.mcmo.z.commonlibrary.utils.CookiesStore;
import com.mcmo.z.module_login.net.ServiceApi_MLogin;

@Route(path = ModuleImpCons.Login.logout,name = "用户退出")
public class LoginServiceImpl implements LoginService {
    @Override
    public void logout(final OnLogOut cb) {
        ServiceApi_MLogin api = RetrofitManager.getInstance().create(ServiceApi_MLogin.class);
        api.logout().enqueue(new RetrofitCallback<Object>() {
            @Override
            public void onSuccess(Object data) {
                CookiesStore.clear();
                UserInfoSaver.clear();
                cb.onSuccess();
            }

            @Override
            public void onFailed(ErrorMsg error) {
                cb.onFailed();
            }
        });
    }

    @Override
    public void init(Context context) {

    }
}
