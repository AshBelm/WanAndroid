package com.mcmo.z.commonlibrary.busniess;

import android.content.Context;
import android.content.SharedPreferences;

import com.mcmo.z.commonlibrary.base.ContextHolder;

public class UserInfoSaver {
    private static final String NAME = "MCMO_WANANDROID_USER_DATA";
    public static boolean save(BaseUserInfo userInfo){
        SharedPreferences sp = ContextHolder.getInstance().getApplicationContext().getSharedPreferences(NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("id",userInfo.getId());
        editor.putString("name",userInfo.getUsername());
        editor.putString("email",userInfo.getEmail());
        editor.putString("icon",userInfo.getIcon());
        editor.putFloat("type",userInfo.getType());
        return editor.commit();
    }
    public static BaseUserInfo getUserInfo(){
        SharedPreferences sp = ContextHolder.getInstance().getApplicationContext().getSharedPreferences(NAME,Context.MODE_PRIVATE);
        BaseUserInfo baseUserInfo = new BaseUserInfo();
        baseUserInfo.setId(sp.getFloat("id",0.0f));
        baseUserInfo.setUsername(sp.getString("name",""));
        baseUserInfo.setEmail(sp.getString("email",""));
        baseUserInfo.setIcon(sp.getString("icon",""));
        baseUserInfo.setType(sp.getFloat("type",0.0f));
        return baseUserInfo;
    }
    public static void clear(){
        SharedPreferences sp = ContextHolder.getInstance().getApplicationContext().getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
