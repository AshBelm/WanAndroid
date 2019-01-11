package com.mcmo.z.commonlibrary.mvp;

import android.os.Bundle;
import android.os.PersistableBundle;

public abstract class AbsPresenter<T extends AbsView> {
    private T absView ;

    public void setView(T absView) {
        this.absView = absView;
    }

    protected T getView(){
        return absView;
    }
    public void initData(){

    }
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState){}
    public void onRestoreInstanceState(Bundle savedInstanceState){}
}
