package com.mcmo.z.commonlibrary.net.interceptor;

import com.mcmo.z.commonlibrary.utils.CookiesStore;

import java.io.IOException;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class SetCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Set<String> cookies = CookiesStore.getCookie();
        if (cookies == null || cookies.isEmpty()) {
            return chain.proceed(chain.request());
        } else {
            Request.Builder builder = chain.request().newBuilder();
            for (String cookie : cookies) {
                builder.addHeader("Cookie", cookie);
            }
            return chain.proceed(builder.build());
        }
    }
}
