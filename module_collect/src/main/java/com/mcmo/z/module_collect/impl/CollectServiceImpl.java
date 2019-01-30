package com.mcmo.z.module_collect.impl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mcmo.z.commonlibrary.base.ContextHolder;
import com.mcmo.z.commonlibrary.mouduleinterface.CollectService;
import com.mcmo.z.commonlibrary.mouduleinterface.DefaultSuccessCallBack;
import com.mcmo.z.commonlibrary.mouduleinterface.ModuleImpCons;
import com.mcmo.z.commonlibrary.utils.ToastUtil;
import com.mcmo.z.module_collect.R;

@Route(path = ModuleImpCons.Collect.collect,name = "收藏相关")
public class CollectServiceImpl implements CollectService {
    private Context context;
    @Override
    public void collectArticle(long articleId, DefaultSuccessCallBack cb) {

    }

    @Override
    public void showCollectArticleDialog(final DefaultSuccessCallBack cb) {
        View v = LayoutInflater.from(ContextHolder.getInstance().getTopActivity()).inflate(R.layout.mcollect_dialog_collect,null,false);
        final EditText et_title = v.findViewById(R.id.et_title);
        final EditText et_author = v.findViewById(R.id.et_author);
        final EditText et_link = v.findViewById(R.id.et_link);

        AlertDialog.Builder builder = new AlertDialog.Builder(ContextHolder.getInstance().getTopActivity(),R.style.DialogTheme);
        builder.setTitle(R.string.mcollect_collectArticle_title).setView(v).setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = et_title.getText().toString();
                String author = et_author.getText().toString();
                String link = et_link.getText().toString();
                if(!TextUtils.isEmpty(title)&&!TextUtils.isEmpty(author)&&!TextUtils.isEmpty(link)){

                }else {
                    ToastUtil.showToastUtil(ContextHolder.getInstance().getTopActivity(),"请填写完整");
                }
                cb.onSuccess();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cb.onManualCancel();
            }
        }).show();

    }

    @Override
    public void init(Context context) {
        this.context = context;
    }
}
