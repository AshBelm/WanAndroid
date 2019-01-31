package com.mcmo.z.module_collect;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;


import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mcmo.z.commonlibrary.busniess.UserInfoSaver;
import com.mcmo.z.commonlibrary.constants.RouteCons;
import com.mcmo.z.commonlibrary.mouduleinterface.CollectService;
import com.mcmo.z.commonlibrary.mouduleinterface.DefaultSuccessCallBack;
import com.mcmo.z.commonlibrary.mouduleinterface.ModuleImpCons;
import com.mcmo.z.commonlibrary.mvp.AbsActivityPresenter;
import com.mcmo.z.commonlibrary.mvp.BaseActivity;
import com.mcmo.z.commonlibrary.toolbarutil.IncludeToolBar;
import com.mcmo.z.commonlibrary.toolbarutil.InitParams;
import com.mcmo.z.commonlibrary.utils.CookiesStore;
import com.mcmo.z.commonlibrary.utils.ToastUtil;

import java.nio.file.Path;
import java.util.Set;

@Route(path = RouteCons.Collect.COLLECT_LIST, extras = RouteCons.EXTRA_LOGIN_NEEDED)
public class CollectActivity extends BaseActivity<CollectView> implements IncludeToolBar {
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
//        Toolbar toolbar = findViewById(R.id.tool_bar);
//        setSupportActionBar(toolbar);
        CollectService service = (CollectService) ARouter.getInstance().build(ModuleImpCons.Collect.collect).navigation(this);
        service.showCollectArticleDialog(new DefaultSuccessCallBack() {
            @Override
            public void onSuccess() {
                ToastUtil.showToastUtil(CollectActivity.this,"success");
            }

            @Override
            public void onFailed() {
                ToastUtil.showToastUtil(CollectActivity.this,"failed");

            }

            @Override
            public void onManualCancel() {
                ToastUtil.showToastUtil(CollectActivity.this,"cancel");

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mcollect_menu_a,menu);
        return true;
    }

    @Override
    public InitParams getToolBarParams(InitParams initParams) {
        initParams.setTitle(R.string.mcollect_collectArticle_title);
        initParams.setSubTitle("ttttt");
        return initParams;
    }
}
