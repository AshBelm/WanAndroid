package com.mcmo.z.commonlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.mcmo.z.commonlibrary.base.ContextHolder;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

public class CookiesStore {
    private static String NAME = "MCMO_WANANDROID_USER_COOKIE";
    private static String KEY_COOKIE = "cookie";
    private static WeakReference<Set<String>> weakCookies;

    public static boolean save(HashSet<String> cookies) {
        if (cookies.isEmpty()) {
            return false;
        }
        weakCookies = new WeakReference<Set<String>>(cookies);
        SharedPreferences sp = ContextHolder.getInstance().getApplicationContext().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(KEY_COOKIE, cookies);
        return editor.commit();
    }

    public static Set<String> getCookie() {
        if (weakCookies != null && weakCookies.get() != null) {
            return weakCookies.get();
        }
        SharedPreferences sp = ContextHolder.getInstance().getApplicationContext().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        Set<String> cookies = sp.getStringSet(KEY_COOKIE, null);
        weakCookies = new WeakReference<>(cookies);
        return cookies;
    }

    public static void clear() {
        SharedPreferences sp = ContextHolder.getInstance().getApplicationContext().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        if(weakCookies.get()!=null){
            weakCookies.get().clear();
        }
    }
}
