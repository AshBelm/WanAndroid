package com.mcmo.z.module_collect.net;

import com.mcmo.z.commonlibrary.net.NetResult;
import com.mcmo.z.module_collect.net.bean.CollectData;
import com.mcmo.z.module_collect.net.bean.CollectWebData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi_MCollect {
    //获取收藏列表
    @GET("lg/collect/list/{pageId}/json")
    Call<NetResult<CollectData>> getCollectList(@Path("pageId") int pageId);

    //收藏站内文章
    @POST("lg/collect/{id}/json")
    Call<NetResult<Object>> collectArticle(@Path("id") long articleId);

    //收藏站外文章
    @POST("lg/collect/add/json")
    Call<NetResult<Object>> collectArticle(@Query("title") String title, @Query("author") String author, @Query("link") String link);

    //文章列表中的取消收藏
    @POST("lg/uncollect_originId/{id}/json")
    Call<NetResult<Object>> unCollect(@Path("id") long articleId);

    /**
     * 取消收藏 -- 我的收藏页面内
     *
     * @param articleId
     * @param originId  列表页面下发，无则为-1
     * @return
     */
    @POST("/lg/uncollect/{id}/json")
    Call<NetResult<Object>> unCollect(@Path("id") long articleId, @Query("originId") long originId);

    //收藏网站列表
    @GET("lg/collect/usertools/json")
    Call<NetResult<CollectWebData>> getCollectWebSite();

    //收藏网站
    @POST("lg/collect/addtool/json")
    Call<NetResult<Object>> collectWebSite(@Query("name") String name, @Query("link") String link);

    //编辑收藏网站
    @POST("lg/collect/updatetool/json")
    Call<NetResult<Object>> editCollectWebSite(@Query("id") long id, @Query("name") String name, @Query("link") String link);

    //删除收藏网站
    @POST("lg/collect/deletetool/json")
    Call<NetResult<Object>> deleteCollectWebSite(@Query("id") long id);
}

