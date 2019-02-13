package com.mcmo.z.commonlibrary.permisson;

import java.util.ArrayList;

public class PermissionProcess {
    protected String[] permissions;
    protected PermissionCallback cb;

    private String[] grantedPermissions;//已授权
    private String[] deniedPermissions;//未授权
    private String[] donotAskAgainPermissions;//选择了不再提示的权限，在权限申请回调里面能判断出来
    private String[] needTipPermissions;//需要在申请前提示用户为什么要申请权限的权限

    private String[] stringArray = new String[0];//用来做参数

    protected PermissionProcess(String[] permissions, PermissionCallback cb) {
        this.permissions = permissions;
        this.cb = cb;
    }

    protected void setResult(ArrayList<String> grantedList, ArrayList<String> deniedList, ArrayList<String> donotAskAgainList, ArrayList<String> needTipList) {
        grantedPermissions = getPermissionsArray(grantedList);
        deniedPermissions = getPermissionsArray(deniedList);
        donotAskAgainPermissions = getPermissionsArray(donotAskAgainList);
        needTipPermissions = getPermissionsArray(needTipList);
    }

    private String[] getPermissionsArray(ArrayList<String> list) {
        if (list == null) {
            return new String[0];
        } else {
            return list.toArray(stringArray);
        }
    }

    public String[] getGrantedPermissions() {
        return grantedPermissions;
    }

    public String[] getDeniedPermissions() {
        return deniedPermissions;
    }

    public String[] getDonotAskAgainPermissions() {
        return donotAskAgainPermissions;
    }

    public String[] getNeedTipPermissions() {
        return needTipPermissions;
    }

    /**
     * 权限全部被允许
     *
     * @return
     */
    public boolean isAllGranted() {
        return deniedPermissions != null && deniedPermissions.length == 0;
    }

    /**
     * 是否有选择了Do not ask again的权限
     *
     * @return
     */
    public boolean needGuideToSettings() {
        return donotAskAgainPermissions != null && donotAskAgainPermissions.length > 0;
    }

    public boolean needRationale() {
        return needTipPermissions != null && needTipPermissions.length > 0;
    }

}
