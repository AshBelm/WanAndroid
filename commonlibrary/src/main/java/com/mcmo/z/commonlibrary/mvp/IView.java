package com.mcmo.z.commonlibrary.mvp;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public interface IView {
    void setContext(Context context);
    Context getContext();
    int getLayoutId();
    void onCreate();
    void onViewCreated(View view);
    void onDestroy();
    void onDestroyView();
}
