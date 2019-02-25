package com.mcmo.z.commonlibrary.mvp;

import android.app.Activity;

public class MvpActivityHelper implements IMvp{
    private Activity activity;
    private IView v;
    private IPresenter p;

    public MvpActivityHelper(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void setViewAndPresenter(IView view, MvpPresenter presenter) {
        if(view==null){
            throw new IllegalArgumentException("The IView is null");
        }
        if(presenter==null){
            throw new IllegalArgumentException("The MvpPresenter is null");
        }
        v = view;
        p = presenter;
        p.setIView(v);
        activity.setContentView(v.getLayoutId());
        v.setContext(activity);
        v.onCreate();
        p.onCreate();
        //清除不需要的引用
        activity = null;
    }

    @Override
    public IView getMvpView() {
        return v;
    }

    @Override
    public IPresenter getMvpPresenter() {
        return p;
    }

    public void onDestroy(){
        p.onDestroy();
        v.onDestroy();
    }
}
