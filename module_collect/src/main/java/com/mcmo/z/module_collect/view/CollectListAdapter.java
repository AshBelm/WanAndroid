package com.mcmo.z.module_collect.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcmo.z.module_collect.R;
import com.mcmo.z.module_collect.net.bean.CollectArticleData;

import java.util.List;

public class CollectListAdapter extends RecyclerView.Adapter<CollectListAdapter.CollectArticleHolder> {
    private List<CollectArticleData> mDatas;
    @NonNull
    @Override
    public CollectArticleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CollectArticleHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mcollect_item_collect_article_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CollectArticleHolder collectArticleHolder, int i) {
        CollectArticleData data = mDatas.get(i);
        collectArticleHolder.tv.setText(data.title);
    }

    @Override
    public int getItemCount() {
        return mDatas==null?0:mDatas.size();
    }

    public void setData(List<CollectArticleData> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public class CollectArticleHolder extends RecyclerView.ViewHolder{
        private TextView tv ;
        public CollectArticleHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_collectArticleItem);
        }
    }
}

