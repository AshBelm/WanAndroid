package com.mcmo.z.commonlibrary.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MvpFragmentHelper implements IMvp{
    private Fragment fragment;
    private IView v;
    private IPresenter p;

    public MvpFragmentHelper(Fragment fragment) {
        this.fragment = fragment;
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
        v.setContext(fragment.getContext());
        p.setContext(fragment.getContext());
        p.setFragment(fragment);
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container){
        IView iView = getMvpView();
        int layoutId = iView.getLayoutId();
        View v = inflater.inflate(layoutId,container,false);
        getMvpView().onCreate();
        getMvpView().onViewCreated(v);
        getMvpPresenter().onCreate();
        return v;
    }

    @Override
    public IView getMvpView() {
        if (v == null) {
            throw new RuntimeException("必须设置IView，请调用setViewAndPresenter方法设置");
        }
        return v;
    }

    @Override
    public IPresenter getMvpPresenter() {
        if (p == null) {
            throw new RuntimeException("必须设置IPresenter，请调用setViewAndPresenter方法设置");
        }
        return p;
    }

    public void onDestroy(){
        p.onDestroy();
        v.onDestroy();
    }

}
