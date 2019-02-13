package com.mcmo.z.commonlibrary.permisson;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.mcmo.z.commonlibrary.R;

public class PermissionDialogBuilder {
    private String positiveText;//
    private int positiveTextId;
    private String negativeText;//
    private int negativeTextId;//
    private String tipMsg;//
    private int tipMsgId;//
    private int style = -1;
    private DialogInterface.OnClickListener positiveListener;
    private DialogInterface.OnClickListener negativeListener;

    public PermissionDialogBuilder(DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        this.positiveListener = positiveListener;
        this.negativeListener = negativeListener;
    }

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
        this.positiveTextId = -1;
    }

    public void setPositiveText(int resId) {
        this.positiveTextId = resId;
        this.positiveText = null;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
        this.negativeTextId = -1;
    }

    public void setNegativeText(int resId) {
        this.negativeTextId = resId;
        this.negativeText = null;
    }

    public void setTipMsg(String tipMsg) {
        this.tipMsg = tipMsg;
        this.tipMsgId = -1;
    }

    public void setTipMsg(int resId) {
        this.tipMsgId = resId;
        this.tipMsg = null;
    }

    public void setStyle(int resId) {
        this.style = resId;
    }

    protected Dialog createDialog(Context context) {
        AlertDialog.Builder builder;
        if (style > 0) {
            builder = new AlertDialog.Builder(context, style);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        //确定按钮
        if (!TextUtils.isEmpty(positiveText)) {
            builder.setPositiveButton(positiveText, positiveListener);
        } else if (positiveTextId > 0) {
            builder.setPositiveButton(positiveTextId, positiveListener);
        } else {
            builder.setPositiveButton(R.string.goto_settings, positiveListener);
        }
        //取消按钮
        if (!TextUtils.isEmpty(negativeText)) {
            builder.setNegativeButton(negativeText, negativeListener);
        } else if (negativeTextId > 0) {
            builder.setNegativeButton(negativeTextId, negativeListener);
        }
        //提示消息
        if (tipMsg != null) {
            builder.setMessage(tipMsg);
        } else if (tipMsgId > 0) {
            builder.setMessage(tipMsgId);
        } else {
            builder.setMessage("");
        }
        builder.setCancelable(false);//为了保证权限申请的回调能执行。要不用户点击返回、空白处等还需额外处理
        return builder.create();
    }
}
