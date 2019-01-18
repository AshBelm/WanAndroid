package com.mcmo.z.module_collect;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mcmo.z.commonlibrary.busniess.UserInfoSaver;
import com.mcmo.z.commonlibrary.constants.RouteCons;
import com.mcmo.z.commonlibrary.mvp.AbsActivityPresenter;
import com.mcmo.z.commonlibrary.mvp.BaseActivity;
import com.mcmo.z.commonlibrary.utils.CookiesStore;
import com.mcmo.z.commonlibrary.utils.ToastUtil;

import java.nio.file.Path;
import java.util.Set;

@Route(path = RouteCons.Collect.COLLECT_LIST, extras = RouteCons.EXTRA_LOGIN_NEEDED)
public class CollectActivity extends BaseActivity<CollectView> {
    @Autowired(name = "aaa")
    String test;

    @Override
    public AbsActivityPresenter<CollectView> providerPresenter() {
        return new CollectPresenter();
    }

    @Override
    public CollectView providerView() {
        return new CollectView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        Toolbar toolbar = findViewById(R.id.mcollect_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (test != null)
            ToastUtil.showToastUtil(this, test);
    }
}
