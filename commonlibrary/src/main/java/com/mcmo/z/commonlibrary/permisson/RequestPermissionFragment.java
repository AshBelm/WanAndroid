package com.mcmo.z.commonlibrary.permisson;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;

import com.mcmo.z.commonlibrary.R;

import java.util.ArrayList;
import java.util.Random;

public class RequestPermissionFragment extends Fragment {
    public static final boolean DEBUG = false;
    public static final String TAG = "RequestPermissonFragmen";
    private Random mRandom = new Random();
    private SparseArray<PermissionProcess> mPermissionProcesses = new SparseArray<>();//保存所有的请求

    public RequestPermissionFragment() {
    }


    public static RequestPermissionFragment newInstance() {
        return new RequestPermissionFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);// 设置为 true，表示 configuration change 的时候，fragment 实例不会被重新创建
    }

    public void requestPermissions(@NonNull String[] permissions, PermissionCallback cb) {
        if (permissions == null || permissions.length == 0) {
            return;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //如果系统小于6.0直接调用权限授权成功接口
            cb.onPermissionsAllow(permissions);
            return;
        }
        log(permissions);
        final PermissionProcess process = new PermissionProcess(permissions, cb);
        checkPermission(process);
        if (process.isAllGranted()) {
            if (cb != null) {
                cb.onPermissionsAllow(permissions);
            }
        } else {
            PermissionDialogBuilder builder = null;
            if (process.needRationale() && process.cb != null) {
                builder = process.cb.onShowRationale(getRationaleDialogBuilder(process), process.getNeedTipPermissions());
            }
            if (builder != null) {
                builder.createDialog(getContext()).show();
            } else {
                addAndRequestProcess(process);
            }
        }
    }

    private PermissionDialogBuilder getRationaleDialogBuilder(final PermissionProcess process) {
        return new PermissionDialogBuilder(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addAndRequestProcess(process);
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (process.cb != null) {
                    process.cb.onPermissionDeny(process.getGrantedPermissions(), process.getDeniedPermissions());
                }
            }
        });
    }

    private PermissionDialogBuilder getSettingsTipDialogBuilder(final PermissionProcess process, final int requestCode) {
        return new PermissionDialogBuilder(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PermissionHelper.gotoSettings(RequestPermissionFragment.this, requestCode);
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (process.cb != null) {
                    process.cb.onPermissionDeny(process.getGrantedPermissions(), process.getDeniedPermissions());
                }
            }
        });
    }

    private void addAndRequestProcess(PermissionProcess process) {
        int requestCode = generateKey();
        mPermissionProcesses.put(requestCode, process);
        requestPermissions(process.permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        handleResult(requestCode, permissions, grantResults);
    }

    private void handleResult(final int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        final PermissionProcess process = mPermissionProcesses.get(requestCode);
        if (process != null) mPermissionProcesses.remove(requestCode);
        if (process == null || process.cb == null) {
            return;
        }

        checkPermission(process, permissions, grantResults);

        if (process.isAllGranted()) {
            process.cb.onPermissionsAllow(process.getGrantedPermissions());
        } else {
            PermissionDialogBuilder builder = null;
            if (process.needGuideToSettings()) {
                builder = process.cb.onShowSettingsTip(getSettingsTipDialogBuilder(process, requestCode), process.getDonotAskAgainPermissions());
            }
            if (builder != null) {
                mPermissionProcesses.put(requestCode, process);//把这个请求流程再次加入到集合，因为onActivityResult中要再次使用
                builder.createDialog(getContext()).show();
            } else {
                process.cb.onPermissionDeny(process.getGrantedPermissions(), process.getDeniedPermissions());
            }
        }
    }


    private int generateKey() {
        int key = 0;
        do {
            key = mRandom.nextInt(256);//requsetcode要小于16bits
        } while (mPermissionProcesses.indexOfKey(key) >= 0);
        return key;
    }

    private void checkPermission(PermissionProcess process) {
        if (process == null || process.permissions == null) {
            return;
        }
        ArrayList<String> grantedList = new ArrayList<>();
        ArrayList<String> deniedList = new ArrayList<>();
        ArrayList<String> needTipList = new ArrayList<>();
        String[] permissions = process.permissions;
        for (int i = 0; i < permissions.length; i++) {
            if (ActivityCompat.checkSelfPermission(getContext(), permissions[i]) == PackageManager.PERMISSION_GRANTED) {
                grantedList.add(permissions[i]);
            } else {
                deniedList.add(permissions[i]);
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissions[i])) {
                    needTipList.add(permissions[i]);
                }
            }
        }
        process.setResult(grantedList, deniedList, null, needTipList);
    }

    private void checkPermission(PermissionProcess process, @NonNull String[] permissions, @NonNull int[] grantResults) {
        ArrayList<String> grantedList = new ArrayList<>();
        ArrayList<String> deniedList = new ArrayList<>();
        ArrayList<String> donotAskAgain = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            String permission = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                grantedList.add(permission);
            } else {
                deniedList.add(permission);
                if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                    donotAskAgain.add(permission);
                }
            }
        }
        process.setResult(grantedList, deniedList, donotAskAgain, null);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PermissionProcess process = mPermissionProcesses.get(requestCode);
        if (process != null) mPermissionProcesses.remove(requestCode);
        if (process == null || process.cb == null) {
            return;
        }
        checkPermission(process);
        if (process.isAllGranted()) {
            process.cb.onPermissionsAllow(process.permissions);
        } else {
            process.cb.onPermissionDeny(process.getGrantedPermissions(), process.getDeniedPermissions());
        }

    }

    private void log(String[] permissons) {
        if (DEBUG) {
            for (int i = 0; i < permissons.length; i++) {
                boolean granted = ActivityCompat.checkSelfPermission(getContext(), permissons[i]) == PackageManager.PERMISSION_GRANTED;
                boolean rationale = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissons[i]);
                Log.e(TAG, "permisson : " + permissons[i] + " Granted=" + granted + " ,requestRationale=" + rationale);
            }
        }
    }
}
