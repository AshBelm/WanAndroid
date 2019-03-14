package com.mcmo.z.module_collect;

import com.mcmo.z.commonlibrary.mvp.MvpPresenter;
import com.mcmo.z.commonlibrary.net.ErrorMsg;
import com.mcmo.z.commonlibrary.net.RetrofitCallback;
import com.mcmo.z.module.baselibrary.net.RetrofitManager;
import com.mcmo.z.module_collect.net.ServiceApi_MCollect;
import com.mcmo.z.module_collect.net.bean.CollectData;

public class CollectPresenter extends MvpPresenter<CollectView> {
    @Override
    public void onCreate() {
        super.onCreate();
        ServiceApi_MCollect api = RetrofitManager.getInstance().create(ServiceApi_MCollect.class);
        api.getCollectList(0).enqueue(new RetrofitCallback<CollectData>() {
            @Override
            public void onSuccess(CollectData data) {
                getIView().setCollectListData(data.datas);
            }

            @Override
            public void onFailed(ErrorMsg error) {

            }
        });
    }

}
