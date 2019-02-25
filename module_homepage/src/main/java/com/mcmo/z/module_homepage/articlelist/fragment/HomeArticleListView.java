package com.mcmo.z.module_homepage.articlelist.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.mcmo.z.commonlibrary.mvp.MvpView;
import com.mcmo.z.module_homepage.R;
import com.mcmo.z.module_homepage.net.bean.ArticleData;
import com.mcmo.z.module_homepage.net.bean.BannerData;
import com.mcmo.z.module_homepage.view.ArticleAdapter;
import com.mcmo.z.module_homepage.view.BannerHolder;
import com.wenld.wenldbanner.DefaultPageIndicator;
import com.wenld.wenldbanner.WenldBanner;

import java.util.List;

public class HomeArticleListView extends MvpView {
    private WenldBanner<BannerData> banner;
    private BannerHolder bannerHolder;
    private RecyclerView recyclerView;
    private ArticleAdapter mArticleAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.mhome_fragment_articlelist;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBanner(view);
        initRecycleView(view);
    }

    private void initBanner(View view) {
        banner = view.findViewById(R.id.banner_articlelist);
        DefaultPageIndicator defaultPageIndicator = new DefaultPageIndicator(getContext());
        defaultPageIndicator.setPageIndicator(new int[]{R.drawable.mhome_dot, R.drawable.mhome_dot_tran});
        banner.setIndicatorView(defaultPageIndicator);
        banner.setPageIndicatorAlign(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.CENTER_HORIZONTAL);    //设置指示器位置
    }

    private void initRecycleView(View view) {
        recyclerView = view.findViewById(R.id.rv_articlelist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mArticleAdapter = new ArticleAdapter(null);
        recyclerView.setAdapter(mArticleAdapter);
    }

    public void setItemClickListener(ArticleAdapter.OnItemClickListener listener) {
        mArticleAdapter.setOnItemClickListener(listener);
    }

    public void setArticleData(List<ArticleData> datas) {
        mArticleAdapter.setData(datas);
    }

    public void setBannerData(List<BannerData> data) {
        if (bannerHolder == null) {
            bannerHolder = new BannerHolder();
            banner.setPages(bannerHolder, data);
        } else {
            banner.setData(data);
        }
    }
}
