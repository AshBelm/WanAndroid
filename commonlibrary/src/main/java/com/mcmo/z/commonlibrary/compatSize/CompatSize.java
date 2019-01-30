package com.mcmo.z.commonlibrary.compatSize;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Modifier;

public class CompatSize {
    private static final String TAG = "CompatSize";
    private Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;
    private ScaleChangeListener mScaleChangeListener;
    protected DensityModifier mDensityModifier;
    protected Application mApplication;
    private int mDesignDpi;

    private volatile static CompatSize instance;//双检测的单例模式要加上volatile保证可见性

    private CompatSize() {
        mDensityModifier = new DensityModifier();
    }

    public static CompatSize getInstance() {
        if (instance == null) {
            synchronized (CompatSize.class) {
                if (instance == null) {
                    instance = new CompatSize();
                }
            }
        }
        return instance;
    }

    public void init(Application application, int designDpi) {
        clear();
        this.mDesignDpi = designDpi;
        this.mApplication = application;
        if (activityLifecycleCallbacks != null) {

        }
        activityLifecycleCallbacks = new CompatSizeActLifecycle();
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        mScaleChangeListener = new ScaleChangeListener();
        application.registerComponentCallbacks(mScaleChangeListener);
    }

    public int getDesignDpi() {
        return mDesignDpi;
    }

    private void clear() {
        if (mApplication != null) {
            if (activityLifecycleCallbacks != null)
                mApplication.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
            if (mScaleChangeListener != null)
                mApplication.unregisterComponentCallbacks(mScaleChangeListener);
        }
    }

    private class ScaleChangeListener implements ComponentCallbacks {

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            if (newConfig != null && newConfig.fontScale > 0 && mDensityModifier != null) {
                mDensityModifier.notifyScaleDensityChanged();
            }
        }

        @Override
        public void onLowMemory() {

        }
    }

}
