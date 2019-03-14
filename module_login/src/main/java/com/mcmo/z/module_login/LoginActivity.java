package com.mcmo.z.module_login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mcmo.z.module.baselibrary.constants.RouteCons;
import com.mcmo.z.module.baselibrary.base.BaseActivity;

@Route(path = RouteCons.Login.ACTIVITY)
public class LoginActivity extends BaseActivity {
    private final String TAG_LOGIN = "login";
    private final String TAG_REGISTER = "register";
    private String prevPath;//拦截后进入登陆页面，会传递欲启动页面的path
    private Bundle bundle;//


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mlogin_activity);
        parserIntent();
        addFragmentRoute(TAG_LOGIN, RouteCons.Login.LOGIN_FRAGMENT);
        addFragmentRoute(TAG_REGISTER, RouteCons.Login.REGISTER_FRAGMENT);

        changeFragmentToLogin();
    }

    private void parserIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            prevPath = bundle.getString(RouteCons.KEY_PATH);
            bundle.remove(RouteCons.KEY_PATH);
            if (!bundle.isEmpty()) {
                this.bundle = bundle;
            }
        }
    }

    public void loginSuccessAndFinish() {
        if(TextUtils.isEmpty(prevPath)){
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        }else{
            Postcard postcard = ARouter.getInstance().build(prevPath);
            if(this.bundle!=null){
                postcard.with(this.bundle);
            }
            postcard.navigation(this);
        }
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
        changeFragment(R.id.root_login, TAG_REGISTER);
    }

    public void changeFragmentToLogin() {
        changeFragment(R.id.root_login, TAG_LOGIN);
    }


}
