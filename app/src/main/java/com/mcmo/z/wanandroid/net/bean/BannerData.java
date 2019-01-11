package com.mcmo.z.wanandroid.net.bean;

public class BannerData {
    public String desc;
         public int id;
         public String imagePath;
         public int isVisible;
         public int order;
         public String title;
         public int type;
         public String url;

    @Override
    public String toString() {
        return "BannerData{" +
                "desc='" + desc + '\'' +
                ", id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", isVisible=" + isVisible +
                ", order=" + order +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                '}';
    }
}
