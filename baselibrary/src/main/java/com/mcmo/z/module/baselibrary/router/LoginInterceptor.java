package com.mcmo.z.module.baselibrary.router;

import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mcmo.z.commonlibrary.utils.CookiesStore;
import com.mcmo.z.module.baselibrary.constants.RouteCons;

import java.util.Set;

@Interceptor(priority = 1, name = "拦截需要登陆的界面")
public class LoginInterceptor implements IInterceptor {
    private Context context;
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        boolean needLogin = false;
        int extra = postcard.getExtra();
        if ((extra & RouteCons.EXTRA_LOGIN_NEEDED) == RouteCons.EXTRA_LOGIN_NEEDED) {
            Set<String> cookie = CookiesStore.getCookie();
            needLogin = cookie == null || cookie.isEmpty();
        }
        if(needLogin){
            Bundle bundle = postcard.getExtras();
            String path = postcard.getPath();
            bundle.putString(RouteCons.KEY_PATH,path);
            ARouter.getInstance().build(RouteCons.Login.ACTIVITY).with(bundle).navigation(context);
        }else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        this.context = context;
    }
}

