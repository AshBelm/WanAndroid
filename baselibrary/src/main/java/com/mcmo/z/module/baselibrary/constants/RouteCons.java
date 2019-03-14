package com.mcmo.z.module.baselibrary.constants;

public class RouteCons {
    public static final int EXTRA_LOGIN_NEEDED = 1;//需要登陆

    public static final String KEY_PATH = "navpath";//传递给登陆页面的前一个页面的route地址

    public static final class Login {
        public static final String PATH = "login";
        public static final String ACTIVITY = "/" + PATH + "/act";
        public static final String LOGIN_FRAGMENT = "/" + PATH + "/login";
        public static final String REGISTER_FRAGMENT = "/" + PATH + "/register";
    }

    public static final class Home {
        public static final String PATH = "home";
        public static final String ACTIVITY = "/" + PATH + "/act";
        public static final String ARTICLE_LIST_FRAGMENT = "/" + PATH + "/articlelist";
    }

    public static final class Collect {
        public static final String PATH = "collect";
        public static final String COLLECT_LIST = "/" + PATH + "/list";
    }
}
