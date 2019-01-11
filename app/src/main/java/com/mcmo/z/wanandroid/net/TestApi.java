package com.mcmo.z.wanandroid.net;

import android.util.Log;

import com.mcmo.z.wanandroid.net.bean.BannerData;
import com.mcmo.z.wanandroid.net.bean.MainPageData;
import com.mcmo.z.commonlibrary.net.NetResult;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestApi {
    private static final String TAG = "TestApi";
    public void getMainList(){
        Retrofit retrofit = getRetrofit();
        SerApi api = retrofit.create(SerApi.class);
        System.out.println("=============== hah");
        api.mainList(1).enqueue(new Callback<NetResult<MainPageData>>() {
            @Override
            public void onResponse(Call<NetResult<MainPageData>> call, Response<NetResult<MainPageData>> response) {
                Log.e(TAG, "onResponse: "+response.body().toString());
            }

            @Override
            public void onFailure(Call<NetResult<MainPageData>> call, Throwable t) {
                Log.e(TAG, "onFailure: Failure" );
            }
        });
    }
    public void getBanner(){
        Retrofit retrofit = getRetrofit();
        SerApi api = retrofit.create(SerApi.class);
        api.banner().enqueue(new Callback<NetResult<List<BannerData>>>() {
            @Override
            public void onResponse(Call<NetResult<List<BannerData>>> call, Response<NetResult<List<BannerData>>> response) {
                Log.e(TAG, "onResponse: "+response.body().toString() );
                System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<NetResult<List<BannerData>>> call, Throwable t) {
                Log.e(TAG, "onFailure: Failure" );
            }
        });
    }
    public Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SerApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        return retrofit;
    }
}
