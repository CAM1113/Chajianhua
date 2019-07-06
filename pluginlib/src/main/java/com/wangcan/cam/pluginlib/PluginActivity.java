package com.wangcan.cam.pluginlib;

import android.app.Activity;
import android.os.Bundle;

public class PluginActivity extends Activity implements IPlugin {

    private int mFrom = FROM_INTERNAL;
    private Activity proxyActivity;
    @Override
    public void attach(Activity proxyActivity) {
        this.proxyActivity = proxyActivity;
    }

    @Override
    public Activity getAttachActivity() {
        return proxyActivity;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        if(saveInstanceState != null)
        {
            mFrom = saveInstanceState.getInt("FROM");
        }
        if(mFrom == FROM_INTERNAL)
        {
            super.onCreate(saveInstanceState);
            proxyActivity=this;
        }
    }

    @Override
    public void onResume() {
        if(mFrom == FROM_INTERNAL)
        {
            super.onResume();
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if(mFrom == FROM_INTERNAL)
        {
            super.setContentView(layoutResID);
        }
        else
        {
            proxyActivity.setContentView(layoutResID);
        }
    }
}
