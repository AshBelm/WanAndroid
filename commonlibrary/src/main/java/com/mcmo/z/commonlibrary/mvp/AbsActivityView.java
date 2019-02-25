package com.mcmo.z.commonlibrary.mvp;

import android.app.Activity;
import android.content.Context;

public abstract class AbsActivityView implements IView{
    private Activity activity;

    protected void setActivity(Activity activity) {
        this.activity = activity;
    }
    public Context getContext(){
        return activity;
    }

    public abstract void onViewCreated(Activity act);

}
