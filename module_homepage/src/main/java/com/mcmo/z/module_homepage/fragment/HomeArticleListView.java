package com.mcmo.z.module_homepage.fragment;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import com.mcmo.z.commonlibrary.mvp.AbsFragmentView;
import com.mcmo.z.module_homepage.R;
import com.mcmo.z.module_homepage.net.bean.BannerData;
import com.mcmo.z.module_homepage.view.BannerHolder;
import com.wenld.wenldbanner.DefaultPageIndicator;
import com.wenld.wenldbanner.WenldBanner;

import java.util.List;

public class HomeArticleListView extends AbsFragmentView {
    private WenldBanner<BannerData> banner;
    private BannerHolder bannerHolder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_articlelist;
    }

    @Override
    public void onViewCreated(View view) {
        initBanner(view);
    }
    private void initBanner(View view) {
        banner = view.findViewById(R.id.banner_articlelist);
        DefaultPageIndicator defaultPageIndicator = new DefaultPageIndicator(getContent());
        defaultPageIndicator.setPageIndicator(new int[]{R.drawable.dot, R.drawable.dot_tran});
        banner.setIndicatorView(defaultPageIndicator);
        banner.setPageIndicatorAlign(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.CENTER_HORIZONTAL);    //设置指示器位置
    }
    public void setBannerData(List<BannerData> data){
        if(bannerHolder == null){
            bannerHolder = new BannerHolder();
            banner.setPages(bannerHolder,data);
        }else{
            banner.setData(data);
        }
    }
}
