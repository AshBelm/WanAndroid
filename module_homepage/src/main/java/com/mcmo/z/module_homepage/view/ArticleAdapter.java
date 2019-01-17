package com.mcmo.z.module_homepage.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcmo.z.module_homepage.R;
import com.mcmo.z.module_homepage.net.bean.ArticleData;

import java.util.List;


public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {
    private List<ArticleData> mArticleDatas;
    private OnItemClickListener mListener;
    public ArticleAdapter(List<ArticleData> mArticleDatas) {
        this.mArticleDatas = mArticleDatas;
    }
    public void setData(List<ArticleData> mArticleDatas){
        this.mArticleDatas = mArticleDatas;
        notifyDataSetChanged();;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ArticleHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mhome_item_acticle, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder articleHolder, int i) {
        ArticleData data = mArticleDatas.get(i);
        if(data!=null){
            articleHolder.setData(data);
        }
    }

    @Override
    public int getItemCount() {
        return mArticleDatas == null ? 0 : mArticleDatas.size();
    }

    public class ArticleHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_author;
        private ImageView iv_collect;

        public ArticleHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_article_title);
            tv_author = itemView.findViewById(R.id.tv_author);
            iv_collect = itemView.findViewById(R.id.iv_collect);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position = getAdapterPosition();
                        mListener.onItemClick(mArticleDatas.get(position),position);
                    }
                }
            });
        }

        public void setData(ArticleData data) {
            tv_author.setText("作者：" + data.author);
            tv_title.setText(data.title);
            setCollect(data.collect);
        }

        public void setCollect(boolean collected) {
            iv_collect.setImageResource(collected ? R.drawable.mhome_ic_favorite_black_24dp : R.drawable.mhome_ic_favorite_border_black_24dp);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(ArticleData articleData,int position);
    }
}
