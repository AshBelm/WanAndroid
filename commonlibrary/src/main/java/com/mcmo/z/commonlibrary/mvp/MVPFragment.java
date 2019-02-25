package com.mcmo.z.commonlibrary.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MVPFragment extends Fragment implements IMvp{
    private MvpFragmentHelper mvpFragmentHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpFragmentHelper = new MvpFragmentHelper(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mvpFragmentHelper.onCreateView(inflater,container);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mvpFragmentHelper.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mvpFragmentHelper.getMvpView().onDestroyView();
    }

    @Override
    public void setViewAndPresenter(IView view, MvpPresenter presenter) {
        mvpFragmentHelper.setViewAndPresenter(view,presenter);
    }

    @Override
    public IView getMvpView() {
        return mvpFragmentHelper.getMvpView();
    }

    @Override
    public IPresenter getMvpPresenter() {
        return mvpFragmentHelper.getMvpPresenter();
    }
}
