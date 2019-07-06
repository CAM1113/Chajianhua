package com.wangcan.cam.plugin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wangcan.cam.pluginlib.PluginActivity;

public class PlugMainActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
