package com.mcmo.z.commonlibrary.mvp;

import android.app.Activity;
import android.content.Context;

public abstract class AbsView {
    private Context activityContext;

    protected void setContext(Context context) {
        this.activityContext = context;
    }
    public Context getContent(){
        return activityContext;
    }

    public abstract int getLayoutId();
    public abstract void onCreateView(Activity act);

}
