package com.mcmo.z.commonlibrary.mvp;

import android.content.Context;
import android.support.v4.app.Fragment;

public interface IPresenter<T extends IView> {
    void setContext(Context context);
    Context getContext();
    void setFragment(Fragment fragment);
    Fragment getFragment();
    void setIView(T iView);
    T getIView();
    void onCreate();
    void onDestroy();
}
