package com.mcmo.z.commonlibrary.mvp;

import android.content.Context;

public abstract class AbsPresenter<T extends IView> {
    private T absView ;

    public void setView(T absView) {
        this.absView = absView;
    }

    protected T getView(){
        return absView;
    }
    public abstract Context getContext();
    public abstract void initData();

}
