package com.wangcan.cam.chajian;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Utils {
    public static String copyAssetAndWrite(Context context,String fileName)
    {
        try{
            File cacheDir = context.getCacheDir();
            if(!cacheDir.exists())
            {
                cacheDir.mkdirs();
            }
            File outFile = new File(cacheDir,fileName);
            if(!outFile.exists())
            {
                boolean res = outFile.createNewFile();
                if(res)
                {
                    InputStream is = context.getAssets().open(fileName);
                    FileOutputStream fos = new FileOutputStream(outFile);
                    byte [] buf = new byte[is.available()];
                    int byteCount;
                    while((byteCount = is.read(buf))!=-1)
                    {
                        fos.write(buf,0,byteCount);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                    Toast.makeText(context,"下载成功",Toast.LENGTH_LONG).show();
                    return outFile.getAbsolutePath();
                }
                else
                {
                    Toast.makeText(context,"文件已存在",Toast.LENGTH_LONG).show();
                    return outFile.getAbsolutePath();
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
