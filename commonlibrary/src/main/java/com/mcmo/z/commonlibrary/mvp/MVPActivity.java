package com.mcmo.z.commonlibrary.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MVPActivity extends AppCompatActivity implements IMvp {
    private MvpActivityHelper mMvpHelper;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMvpHelper = new MvpActivityHelper(this);


    }

    @Override
    public void setViewAndPresenter(IView view, MvpPresenter presenter) {
        mMvpHelper.setViewAndPresenter(view, presenter);
    }

    @Override
    public IView getMvpView() {
        return mMvpHelper.getMvpView();
    }

    @Override
    public IPresenter getMvpPresenter() {
        return mMvpHelper.getMvpPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMvpHelper.onDestroy();
    }
}
