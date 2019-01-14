package com.mcmo.z.commonlibrary.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<T extends AbsFragmentView> extends Fragment {
    private T iView;
    private AbsPresenter<T> iPresenter;
    public abstract AbsPresenter<T> providerPresenter();
    public abstract T providerView();

    public T getIView() {
        return iView;
    }

    public AbsPresenter<T> getIPresenter() {
        return iPresenter;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initMVP();
        return inflater.inflate(iView.getLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iView.onViewCreated(view);
        iPresenter.initData();
    }
    public void prevInitMVP(){
        // TODO: 2019/1/7
    }
    public void postInitMVP(){
        // TODO: 2019/1/7
    }
    private void initMVP() {
        iView = providerView();
        iView.setFragment(this);
        iPresenter = providerPresenter();
        iPresenter.setView(iView);
    }
}
