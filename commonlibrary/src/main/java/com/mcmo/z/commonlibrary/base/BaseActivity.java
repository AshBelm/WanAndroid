package com.mcmo.z.commonlibrary.base;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mcmo.z.commonlibrary.mvp.AbsActivityPresenter;
import com.mcmo.z.commonlibrary.mvp.AbsActivityView;
import com.mcmo.z.commonlibrary.mvp.MVPActivity;

import java.util.HashMap;

public abstract class BaseActivity extends MVPActivity {
    private Fragment mCurFragment;
    private HashMap<String, String> fragmentMap;//key = tag ,value = route

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentSettings();

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
