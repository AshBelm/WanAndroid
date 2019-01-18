package com.mcmo.z.module_collect.net;

import com.mcmo.z.commonlibrary.net.NetResult;
import com.mcmo.z.module_collect.net.bean.CollectData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceApi_MCollect {
    @GET("lg/collect/list/{pageId}/json")
    Call<NetResult<CollectData>> getCollectList(@Path("pageId")int pageId);
}
