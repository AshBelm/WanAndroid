package com.mcmo.z.commonlibrary.compatSize;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class CompatSizeActLifecycle implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        CompatSize.getInstance().mDensityModifier.modifyDisplayMetric(activity,activity.getApplication(),CompatSize.getInstance().getDesignDpi());
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
