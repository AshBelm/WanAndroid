package com.mcmo.z.commonlibrary.mvp;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

public abstract class AbsFragmentView implements IView {
    private Fragment fragment;
    public void setFragment(Fragment fragment){
        this.fragment = fragment;
    }
    @Override
    public Context getContent() {
        return fragment.getContext();
    }
    public abstract void onViewCreated(View view);


}
