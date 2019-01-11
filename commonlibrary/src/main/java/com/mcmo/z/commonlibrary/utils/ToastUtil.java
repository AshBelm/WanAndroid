package com.mcmo.z.commonlibrary.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void showToastUtil(Context context,String msg,int duration){
        Toast.makeText(context,msg,duration).show();
    }
    public static void showToastUtil(Context context,int msgId,int duration){
        Toast.makeText(context,msgId,duration).show();
    }
    public static void showToastUtil(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    public static void showToastUtil(Context context,int msgId){
        Toast.makeText(context,msgId,Toast.LENGTH_SHORT).show();
    }
}
