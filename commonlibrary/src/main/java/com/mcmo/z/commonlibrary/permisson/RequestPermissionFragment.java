package com.mcmo.z.commonlibrary.permisson;

import android.app.AlertDialog;
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
    private SparseArray<PermissionProcess> mPermissionProcesses = new SparseArray<>();

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
        PermissionProcess process = new PermissionProcess(permissions, cb);
        checkPermission(process);
        if (process.isAllGranted()) {
            if (cb != null) {
                cb.onPermissionsAllow(permissions);
            }
        } else {
            int requestCode = generateKey();
            mPermissionProcesses.put(requestCode, process);
            requestPermissions(permissions, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        handleResult(requestCode, permissions, grantResults);
    }

    private void handleResult(final int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        final PermissionProcess process = mPermissionProcesses.get(requestCode);
        if (process == null || process.cb == null) {
            return;
        }

        checkPermission(process, permissions, grantResults);

        if (process.isAllGranted()) {
            mPermissionProcesses.remove(requestCode);
            process.cb.onPermissionsAllow(process.getGrantedPermissions());
        } else if (process.needGuideToSettings()) {
            //这里如果项目使用了今日头条的适配方案dialog可以使用下面这个style
            new AlertDialog.Builder(getContext(), R.style.DialogTheme).setMessage("应用需要使用某些权限，请到设置中打开").setPositiveButton(R.string.goto_settings, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PermissionHelper.gotoSettings(RequestPermissionFragment.this, requestCode);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mPermissionProcesses.remove(requestCode);
                    process.cb.onPermissionDeny(process.getGrantedPermissions(), process.getDeniedPermissions());
                }
            }).show();
        } else {
            mPermissionProcesses.remove(requestCode);
            process.cb.onPermissionDeny(process.getGrantedPermissions(), process.getDeniedPermissions());
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
        if (process == null || process.cb == null) {
            return;
        }
        mPermissionProcesses.remove(requestCode);
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
