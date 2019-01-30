package com.mcmo.z.commonlibrary.mouduleinterface;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface CollectService extends IProvider {
    //收藏站内文章
    void collectArticle(long articleId, DefaultSuccessCallBack cb);

    //收藏站外文章
    void showCollectArticleDialog(DefaultSuccessCallBack cb);


}
