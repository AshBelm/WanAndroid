package com.mcmo.z.commonlibrary.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;

public abstract class AbsActivityPresenter<T extends IView> extends AbsPresenter<T> {

    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    private Activity mActivity;

    protected void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    @Override
    public Context getContext() {
        return this.mActivity;
    }
}
