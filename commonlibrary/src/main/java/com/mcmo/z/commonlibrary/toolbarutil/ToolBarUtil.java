package com.mcmo.z.commonlibrary.toolbarutil;

import android.app.Application;

public class ToolBarUtil {
    public static void register(Application application){
        application.registerActivityLifecycleCallbacks(new ToolBarActLifecycleCallback());
    }
}
