package com.mcmo.z.commonlibrary.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public interface IView {
    void setContext(Context context);
    Context getContext();
    int getLayoutId();
    void onCreate();
    void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState);
    void onDestroy();
    void onDestroyView();
}
