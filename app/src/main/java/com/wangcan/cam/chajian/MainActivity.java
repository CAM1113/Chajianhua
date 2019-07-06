package com.wangcan.cam.chajian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wangcan.cam.pluginlib.PluginManager;
import com.wangcan.cam.pluginlib.ProxyActivity;


//插件化测试
public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().init(this);

        findViewById(R.id.loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apkPath = Utils.copyAssetAndWrite(MainActivity.this,"plugin.apk");
                PluginManager.getInstance().loadApk(apkPath);
            }
        });

        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ProxyActivity.class);
                intent.putExtra("className","com.wangcan.cam.plugin.RealPluginActivity");
                startActivity(intent);
            }
        });


    }
}
