package com.mcmo.z.module_homepage.net;

import com.mcmo.z.commonlibrary.net.NetResult;
import com.mcmo.z.module_homepage.net.bean.BannerData;
import com.mcmo.z.module_homepage.net.bean.MainPageData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceApi_HomePage {
    @GET("article/list/{page}/json")
    Call<NetResult<MainPageData>> mainList(@Path("page") int page);
    @GET("banner/json")
    Call<NetResult<List<BannerData>>> banner();

}
