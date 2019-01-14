package com.mcmo.z.module_homepage.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mcmo.z.commonlibrary.constants.RouteCons;
import com.mcmo.z.commonlibrary.mvp.AbsPresenter;
import com.mcmo.z.commonlibrary.mvp.BaseFragment;

@Route(path = RouteCons.Home.ARTICLE_LIST_FRAGMENT)
public class HomeArticleListFragment extends BaseFragment<HomeArticleListView>{
    @Override
    public AbsPresenter<HomeArticleListView> providerPresenter() {
        return new HomeArticleListPresenter();
    }

    @Override
    public HomeArticleListView providerView() {
        return new HomeArticleListView();
    }

}
