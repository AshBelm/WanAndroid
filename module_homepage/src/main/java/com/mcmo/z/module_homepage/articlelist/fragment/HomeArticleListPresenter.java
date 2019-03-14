package com.mcmo.z.module_homepage.articlelist.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

import com.mcmo.z.commonlibrary.mvp.MvpPresenter;
import com.mcmo.z.commonlibrary.net.ErrorMsg;
import com.mcmo.z.commonlibrary.net.RetrofitCallback;
import com.mcmo.z.module.baselibrary.net.RetrofitManager;
import com.mcmo.z.commonlibrary.utils.ToastUtil;
import com.mcmo.z.module_homepage.articlelist.viewmodel.ArticleListViewModel;
import com.mcmo.z.module_homepage.net.ServiceApi_HomePage;
import com.mcmo.z.module_homepage.net.bean.ArticleData;
import com.mcmo.z.module_homepage.net.bean.BannerData;
import com.mcmo.z.module_homepage.net.bean.MainPageData;
import com.mcmo.z.module_homepage.view.ArticleAdapter;

import java.util.List;


public class HomeArticleListPresenter extends MvpPresenter<HomeArticleListView> {
    private ArticleListViewModel mArticleListVM;

    @Override
    public void onCreate() {
        bindingViewModel();

        getIView().setItemClickListener(new ArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ArticleData articleData, int position) {
                ToastUtil.showToastUtil(getContext(), articleData.title);
            }
        });
        if (mArticleListVM.getBannerData().getValue() == null) {
            refreshBanner();
        }
        if (mArticleListVM.getArticleListLiveData().getValue() == null) {
            refreshArticleList();
        }
    }


    private void bindingViewModel() {
        mArticleListVM = ViewModelProviders.of(getFragment()).get(ArticleListViewModel.class);
        mArticleListVM.getArticleListLiveData().observe(getFragment(), new Observer<List<ArticleData>>() {
            @Override
            public void onChanged(@Nullable List<ArticleData> articleData) {
                getIView().setArticleData(articleData);
            }
        });
        mArticleListVM.getBannerData().observe(getFragment(), new Observer<List<BannerData>>() {
            @Override
            public void onChanged(@Nullable List<BannerData> bannerData) {
                getIView().setBannerData(bannerData);
            }
        });
    }

    private void refreshBanner() {
        ServiceApi_HomePage api = RetrofitManager.getInstance().create(ServiceApi_HomePage.class);
        api.banner().enqueue(new RetrofitCallback<List<BannerData>>() {
            @Override
            public void onSuccess(List<BannerData> data) {
                mArticleListVM.getBannerData().setValue(data);
            }

            @Override
            public void onFailed(ErrorMsg error) {

            }
        });
    }

    private void refreshArticleList() {
        ServiceApi_HomePage api = RetrofitManager.getInstance().create(ServiceApi_HomePage.class);
        api.mainList(0).enqueue(new RetrofitCallback<MainPageData>() {
            @Override
            public void onSuccess(MainPageData data) {
                mArticleListVM.getArticleListLiveData().setValue(data.datas);
            }

            @Override
            public void onFailed(ErrorMsg error) {

            }
        });
    }
}
