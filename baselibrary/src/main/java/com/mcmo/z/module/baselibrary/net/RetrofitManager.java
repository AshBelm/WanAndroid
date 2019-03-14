package com.mcmo.z.module.baselibrary.net;

import android.util.Log;


import com.mcmo.z.commonlibrary.net.interceptor.SaveCookiesInterceptor;
import com.mcmo.z.commonlibrary.net.interceptor.SetCookiesInterceptor;
import com.mcmo.z.module.baselibrary.constants.NetSiteCons;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static RetrofitManager instance;
    private Retrofit mRetrofit;
    //如果有多个baseurl可以用map来存储retrofit对象

    private RetrofitManager() {
        init();
    }

    public static RetrofitManager getInstance() {
        if (instance == null) {
            Log.e("aaa", "getInstance: retrofit");
            synchronized (RetrofitManager.class) {
                if (instance == null)
                    instance = new RetrofitManager();
            }
        }
        return instance;
    }

    private void init() {
        if (mRetrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new SaveCookiesInterceptor())
                    .addInterceptor(new SetCookiesInterceptor())
                    .readTimeout(20,TimeUnit.SECONDS)
                    .writeTimeout(10,TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(NetSiteCons.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            mRetrofit = retrofit;
        }
    }

    public <T> T create(final Class<T> service) {
        init();
        return mRetrofit.create(service);
    }
}
