package com.mcmo.z.module_collect;

import android.util.Log;

import com.mcmo.z.commonlibrary.mvp.AbsActivityPresenter;
import com.mcmo.z.commonlibrary.net.ErrorMsg;
import com.mcmo.z.commonlibrary.net.RetrofitCallback;
import com.mcmo.z.commonlibrary.net.RetrofitManager;
import com.mcmo.z.module_collect.net.ServiceApi_MCollect;
import com.mcmo.z.module_collect.net.bean.CollectData;

public class CollectPresenter extends AbsActivityPresenter<CollectView> {
    @Override
    public void initData() {
        ServiceApi_MCollect api = RetrofitManager.getInstance().create(ServiceApi_MCollect.class);
        api.getCollectList(0).enqueue(new RetrofitCallback<CollectData>() {
            @Override
            public void onSuccess(CollectData data) {
                Log.e("aa", "onSuccess: ");
            }

            @Override
            public void onFailed(ErrorMsg error) {

            }
        });
    }
}
