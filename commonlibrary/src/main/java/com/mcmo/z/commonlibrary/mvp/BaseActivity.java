package com.mcmo.z.commonlibrary.mvp;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.HashMap;

public abstract class BaseActivity<T extends AbsActivityView> extends AppCompatActivity {
    private T iView;
    private AbsActivityPresenter<T> iPresenter;
    public abstract AbsActivityPresenter<T> providerPresenter();
    public abstract T providerView();

    private Fragment mCurFragment;
    private HashMap<String, String> fragmentMap;//key = tag ,value = route


    public T getIView() {
        return iView;
    }

    public AbsActivityPresenter<T> getIPresenter() {
        return iPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentSettings();
        prevInitMVP();
        initMVP();
        postInitMVP();
    }

    public void prevInitMVP(){
        // TODO: 2019/1/7
    }
    public void postInitMVP(){
        // TODO: 2019/1/7
    }
    private void initMVP() {
        iView = providerView();
        iView.setActivity(this);
        iPresenter = providerPresenter();
        iPresenter.setActivity(this);
        iPresenter.setView(iView);
        setContentView(iView.getLayoutId());
        iView.onViewCreated(this);
        iPresenter.initData();
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        iPresenter.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        iPresenter.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * 沉浸式status bar
     */
    public void translucentStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//FLAG_FULLSCREEN为全屏沉浸模式
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//底部导航栏
        }
    }
    private void initFragmentSettings() {
        fragmentMap = new HashMap<>();
    }


    public void addFragmentRoute(String tag,String route){
        fragmentMap.put(tag,route);
    }

    public Fragment getCurFragment() {
        return mCurFragment;
    }

    public void changeFragment(int viewId, String tag) {
        if (tag == null || tag.length() == 0) {
            return;
        }
        if (mCurFragment != null && mCurFragment.getTag().equals(tag)) {
            return;
        }
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        boolean isNewFragment = false;
        if (fragment == null) {
            String routePath = fragmentMap.get(tag);
            if (routePath == null || routePath.length() == 0) {
                return;
            }
            fragment = (Fragment) ARouter.getInstance().build(routePath).navigation();
            isNewFragment = true;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (isNewFragment) {
                ft.add(viewId, fragment, tag);
            } else {
                ft.show(fragment);
            }
            if (mCurFragment != null) {
                ft.hide(mCurFragment);
            }
            ft.commitAllowingStateLoss();
            mCurFragment = fragment;
        }
    }

}
