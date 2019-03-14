package com.mcmo.z.wanandroid;

import android.Manifest;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mcmo.z.commonlibrary.permisson.PermissionHelper;
import com.mcmo.z.commonlibrary.permisson.SimplePermissionCallback;
import com.mcmo.z.module.baselibrary.constants.RouteCons;

import java.util.Arrays;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        TestApi api = new TestApi();
//        api.getBanner();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ARouter.getInstance().build(RouteCons.Home.ACTIVITY).navigation();
            }
        },2000);
    }

    public void onButtonClick(View view) {
        PermissionHelper.newInstance(this).requestPermissions(new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.READ_CALENDAR
        }, new SimplePermissionCallback() {
            @Override
            public void onPermissionsAllow(String[] permissions) {
                Log.e("aaa", "onPermissionsAllow: "+ Arrays.toString(permissions) );
            }

            @Override
            public void onPermissionDeny(String[] grantedPermissions, String[] deniedPermissions) {
                Log.e("aaa", "onPermissionDeny: granted = "+ Arrays.toString(grantedPermissions));
                Log.e("aaa", "onPermissionDeny: denied = "+ Arrays.toString(deniedPermissions));
            }
        });

    }
}
