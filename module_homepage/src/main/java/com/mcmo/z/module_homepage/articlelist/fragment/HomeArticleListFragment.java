package com.mcmo.z.module_homepage.articlelist.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mcmo.z.module.baselibrary.constants.RouteCons;
import com.mcmo.z.module.baselibrary.base.BaseFragment;

@Route(path = RouteCons.Home.ARTICLE_LIST_FRAGMENT)
public class HomeArticleListFragment extends BaseFragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewAndPresenter(new HomeArticleListView(),new HomeArticleListPresenter());
    }
}
