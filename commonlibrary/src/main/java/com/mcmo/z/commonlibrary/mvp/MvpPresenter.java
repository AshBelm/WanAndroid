package com.mcmo.z.commonlibrary.mvp;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

public abstract class MvpPresenter<T extends IView> implements IPresenter<T> {
    private Fragment fragment;
    private Context context;
    private T iView;

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setIView(T iView) {
        this.iView = iView;
    }

    @Override
    public T getIView() {
        return iView;
    }

    public Activity getActivity() {
        if (context != null && context instanceof Activity) {
            return (Activity) context;
        }else {
            throw new RuntimeException("If you want use getActivity method,must set the parameter to Activity when you call setContext method");
        }
    }

    @Override
    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public Fragment getFragment() {
        return fragment;
    }



    @Override
    public void onDestroy() {}
    @Override
    public void onCreate() {

    }

}
