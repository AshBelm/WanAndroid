package com.mcmo.z.commonlibrary.permisson;

public interface PermissionCallback {
    void onPermissionsAllow(String[] permissions);
    void onPermissionDeny(String[] grantedPermissions, String[] deniedPermissions);
}
