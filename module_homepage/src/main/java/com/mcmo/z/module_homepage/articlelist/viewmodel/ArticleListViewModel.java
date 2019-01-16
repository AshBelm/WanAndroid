package com.mcmo.z.module_homepage.articlelist.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mcmo.z.module_homepage.net.bean.ArticleData;
import com.mcmo.z.module_homepage.net.bean.BannerData;

import java.util.List;

public class ArticleListViewModel extends ViewModel {
    //文章列表数据
    private MutableLiveData<List<ArticleData>> articleListLiveData;
    //banner数据
    private MutableLiveData<List<BannerData>> bannerData;

    public MutableLiveData<List<ArticleData>> getArticleListLiveData() {
        if(articleListLiveData == null){
            articleListLiveData = new MutableLiveData<>();
        }
        return articleListLiveData;
    }

    public MutableLiveData<List<BannerData>> getBannerData() {
        if(bannerData == null){
            bannerData = new MutableLiveData<>();
        }
        return bannerData;
    }
}
