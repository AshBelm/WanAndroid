package com.mcmo.z.wanandroid.net.bean;

import java.util.List;

public class MainPageData {
    public int curPage;
    public int offset;
    public boolean over;
    public int pageCount;
    public int size;
    public int total;
    public List<ChapterData> datas;

    @Override
    public String toString() {
        return "MainPageData{" +
                "curPage=" + curPage +
                ", offset=" + offset +
                ", over=" + over +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", total=" + total +
                ", datas=" + datas +
                '}';
    }
}
