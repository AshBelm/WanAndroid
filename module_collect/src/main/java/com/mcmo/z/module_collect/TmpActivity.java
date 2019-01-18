package com.mcmo.z.module_collect;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mcmo.z.commonlibrary.constants.RouteCons;

public class TmpActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().build(RouteCons.Collect.COLLECT_LIST).withString("aaa","对了").navigation();
    }
}
