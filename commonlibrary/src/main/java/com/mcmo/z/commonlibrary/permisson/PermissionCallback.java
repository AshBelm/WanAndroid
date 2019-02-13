package com.mcmo.z.commonlibrary.permisson;

public interface PermissionCallback {
    /**
     * 在需要提示用户为什么申请权限时调用
     * @param builder 提示框构造器
     * @param rationalePermissions 需要提示的权限
     * @return 请返回传入的builder参数
     */
    PermissionDialogBuilder onShowRationale(PermissionDialogBuilder builder,String[] rationalePermissions);

    /**
     * 在需要提示用户打开设置页面去修改权限时调用
     * @param builder 提示框构造器
     * @param donotAskAgainPermissions 需要提示的权限
     * @return 请返回传入的builder参数
     */
    PermissionDialogBuilder onShowSettingsTip(PermissionDialogBuilder builder,String[] donotAskAgainPermissions);

    /**
     * 权限全部授权完成后回调
     * @param permissions 申请的全部权限
     */
    void onPermissionsAllow(String[] permissions);

    /**
     * 权限授权失败时回调
     * @param grantedPermissions 授权成功的权限
     * @param deniedPermissions 授权失败的权限
     */
    void onPermissionDeny(String[] grantedPermissions, String[] deniedPermissions);

}
