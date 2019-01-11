package com.mcmo.z.commonlibrary.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mcmo.z.commonlibrary.BuildConfig;
import com.mcmo.z.commonlibrary.compatSize.CompatSize;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextHolder.getInstance().init(this);
        if(BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        CompatSize.getInstance().init(this,360);
    }
}
