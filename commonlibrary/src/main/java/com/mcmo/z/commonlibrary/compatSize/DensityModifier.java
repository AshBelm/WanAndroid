package com.mcmo.z.commonlibrary.compatSize;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;

import java.lang.reflect.Field;

public class DensityModifier {
    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;

    public DensityModifier() {
        final DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        Log.i("Device config", "DensityModifier: screen size : "+displayMetrics.widthPixels+" x "+displayMetrics.heightPixels);
        Log.i("Device config", "DensityModifier: density : "+displayMetrics.density);
        Log.i("Device config", "DensityModifier: densityDpi : "+displayMetrics.densityDpi);
        Log.i("Device config", "DensityModifier: scaleDensity : "+displayMetrics.scaledDensity);
        Log.i("Device config", "DensityModifier: X x Y dpi : "+displayMetrics.xdpi+" x "+displayMetrics.ydpi);
        int calcXdpi = displayMetrics.widthPixels * 160/displayMetrics.densityDpi;
        int calcYdpi = displayMetrics.heightPixels * 160/displayMetrics.densityDpi;
        Log.i("Device config", "DensityModifier: calc X x Y dpi : "+calcXdpi+" x "+calcYdpi);
    }

    /**
     * @param activity
     * @param application
     * @param designDpi   设计尺寸dp
     */
    public void modifyDisplayMetric(@NonNull Activity activity, @NonNull Application application, int designDpi) {
        if (designDpi < 0 || activity == null || application == null) {
            return;
        }
        final DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = displayMetrics.density;
            sNoncompatScaledDensity = displayMetrics.scaledDensity;
        }
        /**
         * 解释：目标设备的 density = 目标屏幕宽度 / 目标屏幕宽的dip (width = dp * density)。因为适配原因我们希望目标屏幕宽的dip和我们设计相同所以
         *    将设计尺寸带入 目标屏幕宽度 / 希望屏幕宽的dip = 希望设备的density(即我们要修改设备的原density为这个希望值)
         */
        final float targetDensity = displayMetrics.widthPixels / designDpi;//360为设计dp
        final float targetScaleDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        final int targetDensityDpi = (int) (targetDensity * 160);

        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        setDisplayMetrics(targetDensity, targetScaleDensity, targetDensityDpi, appDisplayMetrics);

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        setDisplayMetrics(targetDensity, targetScaleDensity, targetDensityDpi, activityDisplayMetrics);
        //兼容 MIUI
        DisplayMetrics activityDisplayMetricsOnMIUI = getMetricsOnMiui(activity.getResources());
        DisplayMetrics appDisplayMetricsOnMIUI = getMetricsOnMiui(application.getResources());

        if (activityDisplayMetricsOnMIUI != null) {
            setDisplayMetrics(targetDensity, targetScaleDensity, targetDensityDpi, activityDisplayMetricsOnMIUI);
        }

        if (appDisplayMetricsOnMIUI != null) {
            setDisplayMetrics(targetDensity, targetScaleDensity, targetDensityDpi, appDisplayMetricsOnMIUI);
        }
    }

    private void setDisplayMetrics(float targetDensity, float targetScaleDensity, int targetDensityDpi, DisplayMetrics appDisplayMetrics) {
        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;
        appDisplayMetrics.scaledDensity = targetScaleDensity;
    }

    protected void notifyScaleDensityChanged() {
        sNoncompatScaledDensity = Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    /**
     * 解决 MIUI 更改框架导致的 MIUI7 + Android5.1.1 上出现的失效问题 (以及极少数基于这部分 MIUI 去掉 ART 然后置入 XPosed 的手机)
     * 来源于: https://github.com/Firedamp/Rudeness/blob/master/rudeness-sdk/src/main/java/com/bulong/rudeness/RudenessScreenHelper.java#L61:5
     *
     * @param resources {@link Resources}
     * @return {@link DisplayMetrics}, 可能为 {@code null}
     */
    private static DisplayMetrics getMetricsOnMiui(Resources resources) {
        if ("MiuiResources".equals(resources.getClass().getSimpleName()) || "XResources".equals(resources.getClass().getSimpleName())) {
            try {
                Field field = Resources.class.getDeclaredField("mTmpMetrics");
                field.setAccessible(true);
                return (DisplayMetrics) field.get(resources);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
