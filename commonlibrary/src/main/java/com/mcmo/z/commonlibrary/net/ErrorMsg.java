package com.mcmo.z.commonlibrary.net;

public class ErrorMsg {
    private int type;
    private String msg;

    public ErrorMsg(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ErrorMsg{" +
                "type=" + type +
                ", msg='" + msg + '\'' +
                '}';
    }
}
