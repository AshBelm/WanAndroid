package com.mcmo.z.commonlibrary.mvp;

public interface IMvp {
    void setViewAndPresenter(IView view, MvpPresenter presenter);
    IView getMvpView();
    IPresenter getMvpPresenter();
}
