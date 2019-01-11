package com.mcmo.z.module_login.net;

import com.mcmo.z.commonlibrary.net.NetResult;
import com.mcmo.z.commonlibrary.net.RetrofitCallback;
import com.mcmo.z.module_login.bean.LoginInfoBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi_MLogin {
    //注册 NetResult{data={chapterTops=[], collectIds=[], email=, icon=, id=15281.0, password=, token=, type=0.0, username=Ash9999}, errorCode=0, errorMsg=''}
    @POST("user/register")
    Call<NetResult<LoginInfoBean>> register(@Query("username") String username, @Query("password") String password, @Query("repassword") String repassword);

    //登录
    @POST("user/login")
    Call<NetResult<LoginInfoBean>> login(@Query("username")String username, @Query("password")String password);

    //退出
    @GET("user/logout/json")
    Call<NetResult<Object>> logout();

    //收藏文章列表
    @GET("lg/collect/list/{pageNum}/json")
    Call<NetResult<Object>> getCollition(@Path("pageNum") int pageNum);
}
