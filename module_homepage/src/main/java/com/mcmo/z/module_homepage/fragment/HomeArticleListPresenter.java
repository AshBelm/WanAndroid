package com.mcmo.z.module_homepage.fragment;

import com.mcmo.z.commonlibrary.mvp.AbsPresenter;
import com.mcmo.z.commonlibrary.net.ErrorMsg;
import com.mcmo.z.commonlibrary.net.RetrofitCallback;
import com.mcmo.z.commonlibrary.net.RetrofitManager;
import com.mcmo.z.module_homepage.net.ServiceApi_HomePage;
import com.mcmo.z.module_homepage.net.bean.BannerData;

import java.util.List;

public class HomeArticleListPresenter extends AbsPresenter<HomeArticleListView> {
    @Override
    public void initData() {
        super.initData();
        ServiceApi_HomePage api = RetrofitManager.getInstance().create(ServiceApi_HomePage.class);
        api.banner().enqueue(new RetrofitCallback<List<BannerData>>() {
            @Override
            public void onSuccess(List<BannerData> data) {
                getView().setBannerData(data);
            }

            @Override
            public void onFailed(ErrorMsg error) {

            }
        });
    }
}
