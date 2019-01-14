package com.mcmo.z.module_homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mcmo.z.commonlibrary.constants.RouteCons;
import com.mcmo.z.commonlibrary.mvp.AbsPresenter;
import com.mcmo.z.commonlibrary.mvp.BaseActivity;

public class HomePageActivity extends BaseActivity<HomePageView> {
    @Override
    public AbsPresenter<HomePageView> providerPresenter() {
        return new HomePagePresenter();
    }

    @Override
    public HomePageView providerView() {
        return new HomePageView();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        translucentStatusBar();
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteCons.Home.ARTICLE_LIST_FRAGMENT).navigation();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fragment,"articleList").commitAllowingStateLoss();
    }

}
