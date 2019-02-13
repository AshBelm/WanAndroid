package com.mcmo.z.commonlibrary.permisson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class PermissionHelper {
    private FragmentActivity activity;

    public PermissionHelper(@NonNull FragmentActivity activity) {
        this.activity = activity;
    }

    public static PermissionHelper newInstance(@NonNull FragmentActivity activity) {
        return new PermissionHelper(activity);
    }
    public static PermissionHelper newInstance(@NonNull Fragment fragment){
        return new PermissionHelper(fragment.getActivity());
    }

    private RequestPermissionFragment getRequestPermissionFragment() {
        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(RequestPermissionFragment.TAG);
        if (fragment == null) {
            fragment = RequestPermissionFragment.newInstance();
            activity.getSupportFragmentManager().beginTransaction().add(fragment, RequestPermissionFragment.TAG).commitNowAllowingStateLoss();
        }
        return (RequestPermissionFragment) fragment;
    }
    public void requestPermissions(@NonNull String[] permissions, PermissionCallback cb){
        getRequestPermissionFragment().requestPermissions(permissions, cb);
    }
    /**
     * 跳转到设置页面
     * @param requestCode
     */
    public static void gotoSettings(Activity activity,int requestCode) {
        Intent intent = getSettingsIntent(activity);
        activity.startActivityForResult(intent,requestCode);
    }
    public static void gotoSettings(Fragment fragment,int requestCode) {
        Intent intent = getSettingsIntent(fragment.getContext());
        fragment.startActivityForResult(intent,requestCode);
    }
    private static Intent getSettingsIntent(Context context){
        Intent intent = new Intent();
        try{
            // 将用户引导到系统设置页面
            if (Build.VERSION.SDK_INT >= 9) {
                Log.e("HLQ_Struggle", "APPLICATION_DETAILS_SETTINGS");
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                intent.setAction(Intent.ACTION_VIEW);
                intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
            }
        } catch (Exception e) {//抛出异常就直接打开设置页面
            Log.e("HLQ_Struggle", e.getLocalizedMessage());
            intent = new Intent(Settings.ACTION_SETTINGS);
        }
        return intent;
    }
}
