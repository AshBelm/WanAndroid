package com.mcmo.z.commonlibrary.net;

import android.support.annotation.NonNull;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RetrofitCallback<T> implements Callback<NetResult<T>> {
    public void onCallBack(){}
    public abstract void onSuccess(T data);

    public abstract void onFailed(ErrorMsg error);

    @Override
    public void onResponse(Call<NetResult<T>> call, Response<NetResult<T>> response) {
        onCallBack();
        NetResult<T> netResult = response.body();
        if (response.isSuccessful()) {
            if (netResult != null) {
                if(netResult.errorCode == 0){
                    //表示成功
                    onSuccess(netResult.data);
                }else{
                    onFailed(new ErrorMsg(netResult.errorCode,netResult.errorMsg));
                }
            }else{
                onFailed(new ErrorMsg(0,"没有返回data"));
            }
        }else{
            final String msg;
            msg = parseErrorMsg(response);
            onFailed(new ErrorMsg(0,msg));
        }
    }

    @Override
    public void onFailure(Call<NetResult<T>> call, Throwable t) {
        onCallBack();
        if (t instanceof UnknownHostException) {
            onFailed(new ErrorMsg(0,"网络异常"));
        } else if (t instanceof ConnectException) {
            onFailed(new ErrorMsg(0,"网络异常"));
        } else if (t instanceof SocketException) {
            onFailed(new ErrorMsg(0,"服务异常"));
        } else if (t instanceof SocketTimeoutException) {
            onFailed(new ErrorMsg(0,"响应超时"));
        } else {
            onFailed(new ErrorMsg(0,"请求失败"));
        }
    }
    @NonNull
    private String parseErrorMsg(Response<NetResult<T>> response) {
        String msg;
        switch (response.code()) {
            case 400:
                msg = "参数错误";
                break;
            case 401:
                msg = "身份未授权";
                break;
            case 403:
                msg = "禁止访问";
                break;
            case 404:
                msg = "地址未找到";
                break;
            default:
                msg = "服务异常";
        }
        return msg;
    }

}
