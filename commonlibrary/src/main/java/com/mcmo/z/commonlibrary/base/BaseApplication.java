package com.mcmo.z.commonlibrary.base;

import android.app.Application;
import android.os.StrictMode;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mcmo.z.commonlibrary.BuildConfig;
import com.mcmo.z.commonlibrary.compatSize.CompatSize;
import com.mcmo.z.commonlibrary.toolbarutil.ToolBarUtil;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
            initStrictMode();//写在onCreate前否则不管用
        }
        super.onCreate();
        ContextHolder.getInstance().init(this);
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        CompatSize.getInstance().init(this, 360);
        ToolBarUtil.register(this);
    }

    private void initStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
//                .penaltyDialog()//先注释掉，总弹框影响开发(Aroute读取在主线程)
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectActivityLeaks()
                .detectLeakedClosableObjects()
                .penaltyDeath()
                .penaltyLog()
                .build());
    }
}
