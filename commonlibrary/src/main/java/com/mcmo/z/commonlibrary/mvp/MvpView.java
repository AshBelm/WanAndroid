package com.mcmo.z.commonlibrary.mvp;

import android.content.Context;
import android.view.View;

public abstract class MvpView implements IView {
    private Context context;

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    //<editor-fold desc="未实现接口">
    @Override
    public void onCreate() {}

    @Override
    public void onViewCreated(View view) {}

    @Override
    public void onDestroy() {}

    @Override
    public void onDestroyView() {}
    //</editor-fold>
}
