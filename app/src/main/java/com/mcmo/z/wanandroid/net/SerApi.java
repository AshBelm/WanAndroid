package com.mcmo.z.wanandroid.net;

import com.mcmo.z.wanandroid.net.bean.BannerData;
import com.mcmo.z.wanandroid.net.bean.MainPageData;
import com.mcmo.z.commonlibrary.net.NetResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SerApi {
    static String BASE_URL = "http://wanandroid.com/";

    @GET("article/list/{page}/json")
    Call<NetResult<MainPageData>> mainList(@Path("page") int page);
    @GET("banner/json")
    Call<NetResult<List<BannerData>>> banner();
}

