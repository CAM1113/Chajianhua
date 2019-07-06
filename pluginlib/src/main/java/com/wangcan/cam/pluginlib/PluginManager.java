package com.wangcan.cam.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {
    private final static PluginManager instance = new PluginManager();
    private PluginManager (){}
    public static PluginManager getInstance()
    {
        return instance;
    }

    private Context context;
    private PluginApk pluginApk;
    public void init(Context context)
    {
        this.context = context.getApplicationContext();
    }

    public PluginApk getPluginApk()
    {
        return pluginApk;
    }

    //加载Apk文件
    public void loadApk(String path)
    {
        PackageInfo packageInfo = context.getPackageManager().getPackageArchiveInfo(path,
                PackageManager.GET_ACTIVITIES|PackageManager.GET_SERVICES);
        if(packageInfo == null)
        {
            return;
        }
        DexClassLoader classLoader = createDexClassLoader(path);
        AssetManager am = createAssetManager(path);
        Resources resources =  createResources(am);
        pluginApk = new PluginApk(classLoader,resources,packageInfo);
    }

    private Resources createResources(AssetManager am) {
        Resources resources = context.getResources();
        return new Resources(am,resources.getDisplayMetrics(),resources.getConfiguration());
    }

    private AssetManager createAssetManager(String path) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
            method.invoke(assetManager,path);
            return assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private DexClassLoader createDexClassLoader(String path) {
        File file = context.getDir("dex",Context.MODE_PRIVATE);
        return new DexClassLoader(path,file.getAbsolutePath(),null,context.getClassLoader());
    }
}