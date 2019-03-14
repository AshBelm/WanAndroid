package com.mcmo.z.module.baselibrary.mouduleinterface;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface LoginService extends IProvider {
    void logout(OnLogOut cb);
    public interface OnLogOut{
        void onSuccess();
        void onFailed();
    }
}
