package com.mcmo.z.commonlibrary.net;

public class NetResult<T> {
    public T data;
    public int errorCode;
    public String errorMsg;

    @Override
    public String toString() {
        return "NetResult{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
