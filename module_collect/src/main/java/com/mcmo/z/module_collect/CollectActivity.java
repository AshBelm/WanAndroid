package com.mcmo.z.module_collect;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mcmo.z.module.baselibrary.constants.RouteCons;
import com.mcmo.z.module.baselibrary.mouduleinterface.CollectService;
import com.mcmo.z.module.baselibrary.mouduleinterface.DefaultSuccessCallBack;
import com.mcmo.z.module.baselibrary.mouduleinterface.ModuleImpCons;
import com.mcmo.z.module.baselibrary.base.BaseActivity;
import com.mcmo.z.commonlibrary.toolbarutil.IncludeToolBar;
import com.mcmo.z.commonlibrary.toolbarutil.InitParams;
import com.mcmo.z.commonlibrary.utils.ToastUtil;

@Route(path = RouteCons.Collect.COLLECT_LIST, extras = RouteCons.EXTRA_LOGIN_NEEDED)
public class CollectActivity extends BaseActivity implements IncludeToolBar {
    @Autowired(name = "aaa")
    String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setViewAndPresenter(new CollectView(),new CollectPresenter());
//        Toolbar toolbar = findViewById(R.id.tool_bar);
//        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mcollect_menu_a,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_a){
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
        return true;
    }

    @Override
    public InitParams getToolBarParams(InitParams initParams) {
        initParams.setTitle(R.string.mcollect_collectArticle_title);
        initParams.setSubTitle("ttttt");
        return initParams;
    }
}
