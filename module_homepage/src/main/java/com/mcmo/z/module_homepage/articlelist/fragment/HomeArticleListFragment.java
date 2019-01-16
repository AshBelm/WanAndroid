package com.mcmo.z.module_homepage.articlelist.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mcmo.z.commonlibrary.constants.RouteCons;
import com.mcmo.z.commonlibrary.mvp.AbsFragmentPresenter;
import com.mcmo.z.commonlibrary.mvp.BaseFragment;

@Route(path = RouteCons.Home.ARTICLE_LIST_FRAGMENT)
public class HomeArticleListFragment extends BaseFragment<HomeArticleListView>{


    @Override
    public AbsFragmentPresenter<HomeArticleListView> providerPresenter() {
        return new HomeArticleListPresenter();
    }

    @Override
    public HomeArticleListView providerView() {
        return new HomeArticleListView();
    }

}
