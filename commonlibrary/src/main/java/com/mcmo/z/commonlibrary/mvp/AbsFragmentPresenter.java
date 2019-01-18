package com.mcmo.z.commonlibrary.mvp;

import android.content.Context;
import android.support.v4.app.Fragment;

public abstract class AbsFragmentPresenter<T extends IView> extends AbsPresenter<T> {
    private Fragment mFragment;

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }

    public Fragment getFragment() {
        return this.mFragment;
    }

    @Override
    public Context getContext() {
        return this.mFragment.getContext();
    }
}
