package com.wangcan.cam.pluginlib;

import android.app.Activity;
import android.os.Bundle;

public interface IPlugin {

    int FROM_INTERNAL = 0;
    int FROM_EXTERNAL = 1;

    void attach(Activity proxyActivity);

    Activity getAttachActivity();

    void onCreate(Bundle saveInstanceState);

    void onResume();
}
