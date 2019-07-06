package com.wangcan.cam.pluginlib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

public class ProxyActivity extends Activity {
    private String mClassName;
    private PluginApk pluginApk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");
        pluginApk = PluginManager.getInstance().getPluginApk();

        launchPluginActivity();


    }

    private void launchPluginActivity() {
        if(pluginApk == null)
        {
            Log.e("CAM","Loading apk file first");
            return;
        }
        try
        {
            Class<?> clazz = pluginApk.mClassLoader.loadClass(mClassName);
            Object o = clazz.newInstance();
            if(o instanceof IPlugin)
            {
                IPlugin plugin = (IPlugin)o;
                plugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM",IPlugin.FROM_EXTERNAL);

                plugin.onCreate(bundle);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return pluginApk!=null ? pluginApk.mResource : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return pluginApk!= null? pluginApk.mAssetManager: super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return pluginApk!=null? pluginApk.mClassLoader: super.getClassLoader();
    }
}
