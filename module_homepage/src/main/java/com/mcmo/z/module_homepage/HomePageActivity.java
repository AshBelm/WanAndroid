package com.mcmo.z.module_homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mcmo.z.commonlibrary.constants.RouteCons;
import com.mcmo.z.commonlibrary.mvp.AbsActivityPresenter;
import com.mcmo.z.commonlibrary.mvp.BaseActivity;

public class HomePageActivity extends BaseActivity<HomePageView> {
    private final String TAG_ARTICLELIST="articlelist";
    @Override
    public AbsActivityPresenter<HomePageView> providerPresenter() {
        return new HomePagePresenter();
    }

    @Override
    public HomePageView providerView() {
        return new HomePageView();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            Log.e("aa", "onCreate: "+savedInstanceState.getString("aaa") );
        }else{
            Log.e("aa", "onCreate: meiyou");
        }
        translucentStatusBar();
        addFragmentRoute(TAG_ARTICLELIST,RouteCons.Home.ARTICLE_LIST_FRAGMENT);
        changeFragment(R.id.fragment_container,TAG_ARTICLELIST);
//        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteCons.Home.ARTICLE_LIST_FRAGMENT).navigation();
//        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fragment,"articleList").commitAllowingStateLoss();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("aaa","hahaha");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            Log.e("aa", "onRestoreInstanceState: "+savedInstanceState.getString("aaa") );
        }else{
            Log.e("aa", "onRestoreInstanceState: meiyou");
        }
    }
}
