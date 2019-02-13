package com.mcmo.z.commonlibrary.permisson;

import com.mcmo.z.commonlibrary.R;

/**
 * 添加了通用提示款的callback
 */
public abstract class SimplePermissionCallback implements PermissionCallback{
    @Override
    public PermissionDialogBuilder onShowRationale(PermissionDialogBuilder builder, String[] rationalePermissions) {
        builder.setPositiveText(R.string.i_know);
        builder.setTipMsg(R.string.rationale_tip);
        builder.setStyle(R.style.DialogTheme);
        return builder;
    }

    @Override
    public PermissionDialogBuilder onShowSettingsTip(PermissionDialogBuilder builder, String[] donotAskAgainPermissions) {
        builder.setPositiveText(R.string.goto_settings);
        builder.setNegativeText(R.string.cancel);
        builder.setTipMsg(R.string.goto_settings_tip);
        builder.setStyle(R.style.DialogTheme); //这里如果项目使用了今日头条的适配方案dialog可以使用这个style
        return builder;
    }
}
