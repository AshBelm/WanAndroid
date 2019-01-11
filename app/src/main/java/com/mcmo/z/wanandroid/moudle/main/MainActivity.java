package com.mcmo.z.wanandroid.moudle.main;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mcmo.z.commonlibrary.compatSize.CompatSize;
import com.mcmo.z.commonlibrary.constants.RouteCons;
import com.mcmo.z.commonlibrary.mouduleinterface.LoginService;
import com.mcmo.z.commonlibrary.mouduleinterface.ModuleImpCons;
import com.mcmo.z.commonlibrary.utils.ToastUtil;
import com.mcmo.z.wanandroid.R;
import com.mcmo.z.wanandroid.moudle.search.SearchActivity;
import com.mcmo.z.wanandroid.net.SerApi;
import com.mcmo.z.wanandroid.net.bean.BannerData;
import com.mcmo.z.commonlibrary.net.NetResult;
import com.wenld.wenldbanner.DefaultPageIndicator;
import com.wenld.wenldbanner.WenldBanner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private WenldBanner<BannerData> mBanner;
    @Autowired(name = ModuleImpCons.Login.logout)
    LoginService loginModuleService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//FLAG_FULLSCREEN为全屏沉浸模式
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//底部导航栏
        }
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this);
        mBanner = findViewById(R.id.banner);
        DefaultPageIndicator defaultPageIndicator = new DefaultPageIndicator(this);
        defaultPageIndicator.setPageIndicator(new int[]{R.drawable.dot, R.drawable.dot_tran});
        mBanner.setIndicatorView(defaultPageIndicator);
        mBanner.setPageIndicatorAlign(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.CENTER_HORIZONTAL);    //设置指示器位置

        final BannerHolder holder = new BannerHolder();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(SerApi.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        SerApi api = retrofit.create(SerApi.class);
        api.banner().enqueue(new Callback<NetResult<List<BannerData>>>() {
            @Override
            public void onResponse(Call<NetResult<List<BannerData>>> call, Response<NetResult<List<BannerData>>> response) {
                mBanner.setPages(holder, response.body().data);
            }

            @Override
            public void onFailure(Call<NetResult<List<BannerData>>> call, Throwable t) {

            }
        });
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouteCons.Login.ACTIVITY).navigation();
//                loginModuleService = (LoginService) ARouter.getInstance().build(ModuleImpCons.Login.logout).navigation();
//                loginModuleService.logout(new LoginService.OnLogOut() {
//                    @Override
//                    public void onSuccess() {
//                        ToastUtil.showToastUtil(MainActivity.this,"babababa");
//                    }
//
//                    @Override
//                    public void onFailed() {
//                        ToastUtil.showToastUtil(MainActivity.this,"cccccc");
//                    }
//                });
            }
        });
        TextView tv = findViewById(R.id.tv);
        tv.setText("加固,更新来了");
    }
    private static float sNoncompatDensity;
    private static float getsNoncompatScaledDensity;

}
