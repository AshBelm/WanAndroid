package com.mcmo.z.commonlibrary.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class LoadingDialog {
    private ProgressDialog mDialog;

    public void show(Context context) {
        if (mDialog == null) {
            mDialog = new ProgressDialog(context);
        }
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
