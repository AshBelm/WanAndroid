package com.mcmo.z.commonlibrary.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class ContextHolder {
    private static ContextHolder instance;
    private Application mContext;
    private Activity mTopActivity;
    private int mActivityCount = 0;
    private ActLifecycle mLifecycle;

    private ContextHolder() {
    }

    public static ContextHolder getInstance() {
        if (instance == null) {
            synchronized (ContextHolder.class) {
                if (instance == null) {
                    instance = new ContextHolder();
                }
            }
        }
        return instance;
    }

    public Context getApplicationContext() {
        return mContext;
    }

    public Activity getTopActivity() {
        return mTopActivity;
    }

    public void init(Application application) {
        release();
        mContext = application;
        mLifecycle = new ActLifecycle();
        mContext.registerActivityLifecycleCallbacks(mLifecycle);
    }

    public void release() {
        mContext = null;
        mTopActivity = null;
        mActivityCount = 0;
        if (mLifecycle != null && mContext != null) {
            mContext.unregisterActivityLifecycleCallbacks(mLifecycle);
        }
    }

    //<editor-fold desc="Activity Lifecycle Callbacks">
    private class ActLifecycle implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            mTopActivity = activity;
            mActivityCount++;
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
            mTopActivity = activity;
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
            mActivityCount--;
            if (mActivityCount <= 0) {
                Log.i("ContextHolder", "All Activity Destroyed");
                mTopActivity = null;
                mContext.unregisterActivityLifecycleCallbacks(mLifecycle);
            }
        }
    }
    //</editor-fold>
}
