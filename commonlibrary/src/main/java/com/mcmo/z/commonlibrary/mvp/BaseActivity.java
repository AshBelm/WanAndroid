package com.mcmo.z.commonlibrary.mvp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<T extends AbsView> extends AppCompatActivity {
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prevInitMVP();
        initMVP();
        postInitMVP();
    }
    public void prevInitMVP(){
        // TODO: 2019/1/7
    }
    public void postInitMVP(){
        // TODO: 2019/1/7
    }
    private void initMVP() {
        iView = providerView();
        iView.setContext(this);
        iPresenter = providerPresenter();
        iPresenter.setView(iView);
        setContentView(iView.getLayoutId());
        iView.onCreateView(this);
        iPresenter.initData();
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        iPresenter.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        iPresenter.onRestoreInstanceState(savedInstanceState);
    }
}
