package com.mcmo.z.module_collect;

import android.app.Activity;
import android.widget.Toolbar;

import com.mcmo.z.commonlibrary.mvp.AbsActivityView;

public class CollectView extends AbsActivityView {
    @Override
    public void onViewCreated(Activity act) {
    }

    @Override
    public int getLayoutId() {
        return R.layout.mcollect_activity;
    }
}
