package com.mcmo.z.wanandroid.moudle.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.mcmo.z.wanandroid.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final ImageView iv = findViewById(R.id.iv);
        iv.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv.setImageResource(R.drawable.cic);
            }
        },3000);

    }
}
