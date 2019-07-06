package com.wangcan.cam.plugin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wangcan.cam.pluginlib.PluginActivity;
import com.wangcan.cam.pluginlib.ProxyActivity;

public class RealPluginActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);


        TextView textView = getAttachActivity().findViewById(R.id.jump);
        if(textView == null)
        {
            Toast.makeText(this,"textView == null",Toast.LENGTH_LONG).show();
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass( getAttachActivity(), ProxyActivity.class);
                intent.putExtra("className","com.wangcan.cam.plugin.PlugMainActivity");
                getAttachActivity().startActivity(intent);
            }
        });

    }


}
