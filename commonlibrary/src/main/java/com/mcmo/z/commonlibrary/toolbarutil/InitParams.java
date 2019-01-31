package com.mcmo.z.commonlibrary.toolbarutil;

import android.content.Context;

import com.mcmo.z.commonlibrary.R;

public class InitParams {
    private Context context;
    protected int id;//toolbar的id
    protected int backIconId;//默认为一个小三角图标
    protected boolean hideBackIcon;//默认为false，显示返回按钮
    protected String title;//默认为mainfest中的label
    protected String subTitle;//默认没有

    public InitParams(Context context) {
        this.context = context;
    }

    public static InitParams getDefault(Context context){
        InitParams p = new InitParams(context);
        p.id = R.id.tool_bar;
        p.backIconId = R.drawable.ic_chevron_left_black_24dp;
        p.hideBackIcon = false;
        p.title = null;
        p.subTitle = null;
        return p;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBackIconId() {
        return backIconId;
    }

    public void setBackIconId(int backIconId) {
        this.backIconId = backIconId;
    }

    public boolean isHideBackIcon() {
        return hideBackIcon;
    }

    public void setHideBackIcon(boolean hideBackIcon) {
        this.hideBackIcon = hideBackIcon;
    }

    public String getTitle() {
        return title;
    }

    /**
     * 设置标题，如果不设置默认显示mainfest中设置的label，如果想不显示标题请设置为空字符串 ""
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 设置标题，如果不设置默认显示mainfest中设置的label，如果想不显示标题请设置为空字符串 ""
     * @param resId
     */
    public void setTitle(int resId){
        this.title = context.getString(resId);
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setSubTitle(int resId){
        this.subTitle = context.getString(resId);
    }
    public InitParams() {
    }

}

