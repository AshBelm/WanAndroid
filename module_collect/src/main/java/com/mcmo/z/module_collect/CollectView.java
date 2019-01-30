package com.mcmo.z.module_collect;

import android.app.Activity;
import android.support.v4.widget.AutoSizeableTextView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toolbar;

import com.mcmo.z.commonlibrary.mvp.AbsActivityView;
import com.mcmo.z.module_collect.net.bean.CollectArticleData;
import com.mcmo.z.module_collect.view.CollectListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.ButterKnife;

public class CollectView extends AbsActivityView {
    private SmartRefreshLayout sfl;
    private RecyclerView rv;
    private CollectListAdapter mCollectListAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.mcollect_activity;
    }

    @Override
    public void onViewCreated(Activity act) {
        sfl = act.findViewById(R.id.sfl_collectList);
        rv = act.findViewById(R.id.rv_collectList);
        initSmartRefreshLayout();
        initRecycleView();
    }
    private void initRecycleView(){
        rv.setLayoutManager(new LinearLayoutManager(getContent()));
        mCollectListAdapter = new CollectListAdapter();
        rv.setAdapter(mCollectListAdapter);
    }
    private void initSmartRefreshLayout(){
        sfl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                sfl.finishRefresh(200);
            }
        });
        sfl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                sfl.finishLoadMore(2000);
            }
        });
    }
    public void setCollectListData(List<CollectArticleData> data){
        mCollectListAdapter.setData(data);
    }
}
