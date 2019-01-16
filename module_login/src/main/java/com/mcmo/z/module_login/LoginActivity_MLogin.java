package com.mcmo.z.module_login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mcmo.z.commonlibrary.constants.RouteCons;
import com.mcmo.z.commonlibrary.mvp.AbsActivityPresenter;
import com.mcmo.z.commonlibrary.mvp.BaseActivity;

import java.util.HashMap;

@Route(path = RouteCons.Login.ACTIVITY)
public class LoginActivity_MLogin extends BaseActivity<LoginView_MLogin> {
//    private Fragment mCurFragment;
//    private HashMap<String, String> fragmentMap;//key = tag ,value = route
    private final String TAG_LOGIN = "login";
    private final String TAG_REGISTER = "register";

    @Override
    public AbsActivityPresenter<LoginView_MLogin> providerPresenter() {
        return new LoginPresenter_MLogin();
    }

    @Override
    public LoginView_MLogin providerView() {
        return new LoginView_MLogin();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragmentRoute(TAG_LOGIN, RouteCons.Login.LOGIN_FRAGMENT);
        addFragmentRoute(TAG_REGISTER, RouteCons.Login.REGISTER_FRAGMENT);

        changeFragmentToLogin();
    }

    public void loginSuccessAndFinish() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (getCurFragment().getTag().equals(TAG_REGISTER)) {
            changeFragmentToLogin();
        } else {
            super.onBackPressed();
        }
    }

    public void changeFragmentToRegister() {
        changeFragment(R.id.root_login,TAG_REGISTER);
    }

    public void changeFragmentToLogin() {
        changeFragment(R.id.root_login,TAG_LOGIN);
    }

//    private void changeFragment(String tag) {
//        if (tag == null || tag.length() == 0) {
//            return;
//        }
//        if (mCurFragment != null && mCurFragment.getTag().equals(tag)) {
//            return;
//        }
//        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
//        boolean isNewFragment = false;
//        if (fragment == null) {
//            String routePath = fragmentMap.get(tag);
//            if (routePath == null || routePath.length() == 0) {
//                return;
//            }
//            fragment = (Fragment) ARouter.getInstance().build(routePath).navigation();
//            isNewFragment = true;
//        }
//        if (fragment != null) {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            if (isNewFragment) {
//                ft.add(R.id.root_login, fragment, tag);
//            } else {
//                ft.show(fragment);
//            }
//            if (mCurFragment != null) {
//                ft.hide(mCurFragment);
//            }
//            ft.commitAllowingStateLoss();
//            mCurFragment = fragment;
//        }
//    }

}
