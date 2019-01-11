package com.mcmo.z.wanandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mcmo.z.wanandroid.net.TestApi;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TestApi api = new TestApi();
        api.getBanner();
    }

}
