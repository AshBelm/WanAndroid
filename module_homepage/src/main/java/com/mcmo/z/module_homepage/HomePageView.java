package com.mcmo.z.module_homepage;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mcmo.z.commonlibrary.mvp.AbsActivityView;
import com.mcmo.z.commonlibrary.mvp.MvpView;

import butterknife.OnTextChanged;


public class HomePageView extends MvpView {
    private ImageView img1;
    private EditText edit1;
    private EditText edit2;
    private AnimatedVectorDrawable anim1;
    private AnimatedVectorDrawable anim2;
    private AnimatedVectorDrawable anim3;
    private AnimatedVectorDrawable anim3_back1;
    private AnimatedVectorDrawable anim_judge;
    private AnimatedVectorDrawable anim3_back1_no_pass;
    private AnimatedVectorDrawable anim3_no_pass;
    private boolean isGoBack;
    private boolean isShowTrue;
    private boolean isPass;
    @Override
    public int getLayoutId() {
        return R.layout.mhome_activity_homepage;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate() {
        super.onCreate();
        Activity activity = (Activity) getContext();
//        img1 = ((ImageView) activity.findViewById(R.id.img1));
//        edit1 = ((EditText) activity.findViewById(R.id.edit1));
//        edit2 = ((EditText) activity.findViewById(R.id.edit2));
//        anim1 = (AnimatedVectorDrawable) getContext().getResources().getDrawable(R.drawable.mhome_anim1);
//        anim2 = (AnimatedVectorDrawable) getContext().getResources().getDrawable(R.drawable.mhome_anim2);
//        anim3 = (AnimatedVectorDrawable) getContext().getResources().getDrawable(R.drawable.mhome_anim3);
//        anim3_back1 = (AnimatedVectorDrawable) getContext().getResources().getDrawable(R.drawable.mhome_anim3_back1);
//        anim3_back1_no_pass = (AnimatedVectorDrawable) getContext().getResources().getDrawable(R.drawable.mhome_anim3_back1_no_pass);
//        anim3_no_pass = (AnimatedVectorDrawable) getContext().getResources().getDrawable(R.drawable.mhome_anim3_no_pass);
//        anim_judge = (AnimatedVectorDrawable) getContext().getResources().getDrawable(R.drawable.mhome_anim_judge);
//        // 设置焦点变化的监听
//        edit1.setOnFocusChangeListener(this);
//        edit2.setOnFocusChangeListener(this);
//        // 文本变化的监听
//        edit1.addTextChangedListener(this);
    }


}
