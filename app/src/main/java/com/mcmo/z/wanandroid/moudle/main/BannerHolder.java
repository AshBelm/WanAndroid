package com.mcmo.z.wanandroid.moudle.main;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mcmo.z.wanandroid.net.bean.BannerData;
import com.wenld.wenldbanner.helper.Holder;
import com.wenld.wenldbanner.helper.ViewHolder;

public class BannerHolder implements Holder<BannerData> {
    @Override
    public ViewHolder createView(Context context, ViewGroup viewGroup, int i, int i1) {
        ImageView iv = new ImageView(context);
        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return  new ViewHolder(context,iv);
    }

    @Override
    public void UpdateUI(Context context, ViewHolder viewHolder, int i, BannerData bannerData) {
        Glide.with(context).load(bannerData.imagePath).into((ImageView) viewHolder.getConvertView());
    }

    @Override
    public int getViewType(int i) {
        return 0;
    }
}
