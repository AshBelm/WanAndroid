package com.mcmo.z.commonlibrary.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MvpFragmentHelper implements IMvp {
    private Fragment fragment;
    private Activity activity;
    private IView v;
    private IPresenter p;

    public MvpFragmentHelper(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void setViewAndPresenter(IView view, MvpPresenter presenter) {
        if (view == null) {
            throw new IllegalArgumentException("The IView is null");
        }
        if (presenter == null) {
            throw new IllegalArgumentException("The MvpPresenter is null");
        }
        v = view;
        p = presenter;
        p.setIView(v);
        if (activity != null) {
            v.setContext(activity);
            p.setContext(activity);
        } else {
            v.setContext(fragment.getContext());
            p.setContext(fragment.getContext());
        }
        p.setFragment(fragment);
        //清除不需要的引用
        activity = null;
        fragment = null;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        IView iView = getMvpView();
        int layoutId = iView.getLayoutId();
        View v = inflater.inflate(layoutId, container, false);
        getMvpView().onCreate();
        return v;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getMvpView().onViewCreated(view, savedInstanceState);
        getMvpPresenter().onCreate();
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

    public void onDestroy() {
        p.onDestroy();
        v.onDestroy();
    }

}
