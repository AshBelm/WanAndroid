package com.mcmo.z.commonlibrary.net.interceptor;

import com.mcmo.z.commonlibrary.utils.CookiesStore;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

public class SaveCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if(!originalResponse.headers("Set-Cookie").isEmpty()){
            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")){
                cookies.add(header);
            }
            CookiesStore.save(cookies);
        }
        return originalResponse;
    }
}
