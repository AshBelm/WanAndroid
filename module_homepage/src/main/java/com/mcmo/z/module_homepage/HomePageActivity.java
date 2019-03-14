package com.mcmo.z.module_homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.mcmo.z.module.baselibrary.base.BaseActivity;
import com.mcmo.z.module.baselibrary.constants.RouteCons;

@Route(path = RouteCons.Home.ACTIVITY)
public class HomePageActivity extends BaseActivity {
    private final String TAG_ARTICLELIST="articlelist";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewAndPresenter(new HomePageView(),new HomePagePresenter());
        translucentStatusBar();
        addFragmentRoute(TAG_ARTICLELIST,RouteCons.Home.ARTICLE_LIST_FRAGMENT);
        changeFragment(TAG_ARTICLELIST);
    }
    private void changeFragment(String tag){
        changeFragment(R.id.fragment_container,tag);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String prevFragmentTag = savedInstanceState.getString(KEY_PREV_FRAGMENT);
        if (prevFragmentTag != null && prevFragmentTag.length() > 0) {
            changeFragment(prevFragmentTag);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (getCurFragment() != null) {
            outState.putString(KEY_PREV_FRAGMENT, getCurFragment().getTag());
        }
    }
}
