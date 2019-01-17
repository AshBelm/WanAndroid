package com.mcmo.z.module_collect;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class CollectActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(this);
        button.setText("1212");
        setContentView(button);
    }
}
