package com.mcmo.z.commonlibrary.toolbarutil;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class ToolBarActLifecycleCallback implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(final Activity activity, Bundle savedInstanceState) {
        if(activity instanceof IncludeToolBar){
            //设置为false，处理屏幕转动等造成activity重建。而为再次设置toolbar的问题
            activity.getIntent().putExtra(IncludeToolBar.KEY_INCLUDETOOLBAR,false);
        }
    }
    @Override
    public void onActivityStarted(Activity activity) {
        //因为回调在activity的super.onCreate前所以要求设置setContentView写在super前。而大多数人习惯将setContentView设置写在super后面。
        //为了减少不明缘由的群众出现找不到toolbar的问题。把初始化放在start了
        initToolbarIfNeed(activity);
    }

    private void initToolbarIfNeed(Activity activity) {
        if (activity instanceof IncludeToolBar) {
            boolean inited = activity.getIntent().getBooleanExtra(IncludeToolBar.KEY_INCLUDETOOLBAR, false);
            if (inited) {
                return;//如果已经初始化完成了那么就不再初始化了
            }
            activity.getIntent().putExtra(IncludeToolBar.KEY_INCLUDETOOLBAR, true);//设置为已经完成初始化toolbar

            InitParams initParams = ((IncludeToolBar) activity).getToolBarParams(InitParams.getDefault(activity));
            if (activity.findViewById(initParams.id) != null) {
                if (activity instanceof AppCompatActivity) {
                    android.support.v7.widget.Toolbar toolbar = activity.findViewById(initParams.id);
                    ((AppCompatActivity) activity).setSupportActionBar(toolbar);
                    if (initParams.title != null) {
                        //设置是否显示标题和副标题。实测结果是如果设置为true自定义的标题不会显示而显示mainfest中设置的label如果没有设置label显示类名，但副标题可以显示
                        ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
                    }
                    setToolbar_v7(activity, initParams, toolbar);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        android.widget.Toolbar toolbar = activity.findViewById(initParams.id);
                        activity.setActionBar(toolbar);
                        if (initParams.title != null) {
                            activity.getActionBar().setDisplayShowTitleEnabled(false);
                        }
                        setToolbar_lollipop(activity, initParams, toolbar);
                    }
                }

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setToolbar_lollipop(final Activity activity, InitParams initParams, android.widget.Toolbar toolbar) {
        if (initParams.title != null) {
            toolbar.setTitle(initParams.title);
        }
        if (initParams.subTitle != null) {
            toolbar.setSubtitle(initParams.subTitle);
        }
        if (!initParams.hideBackIcon) {
            toolbar.setNavigationIcon(initParams.backIconId);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();
                }
            });
        }
//        toolbar.setLogo();
    }

    private void setToolbar_v7(final Activity activity, InitParams initParams, android.support.v7.widget.Toolbar toolbar) {
        if (initParams.title != null) {
            toolbar.setTitle(initParams.title);
        }
        if (initParams.subTitle != null) {
            toolbar.setSubtitle(initParams.subTitle);
        }
        if (!initParams.hideBackIcon) {
            toolbar.setNavigationIcon(initParams.backIconId);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();
                }
            });
        }
    }

    //<editor-fold desc="Useless">
    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
    //</editor-fold>
}
