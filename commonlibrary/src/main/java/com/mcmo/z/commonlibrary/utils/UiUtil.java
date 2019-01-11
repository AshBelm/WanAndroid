package com.mcmo.z.commonlibrary.utils;

import android.content.res.Resources;

public class UiUtil {
    /**
     * 获取状态栏高度
     * @return
     */
    public static int getStatusBarHeight (){
        //如果使用了 今日头条 的适配方案 ，那么用application.getResources()取出来的值是用修改过的参数技术的不准确
        Resources resources = Resources.getSystem();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelOffset(resourceId);
    }
    public static int getNavigationBarHeight(){
        Resources resources = Resources.getSystem();
        int result = 0;
        int resourceId=0;
        int rid = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid!=0){
            resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            return resources.getDimensionPixelSize(resourceId);
        }else
            return 0;
    }
}
