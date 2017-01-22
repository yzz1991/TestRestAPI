package com.easemob.geri.testrestapi.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Geri on 2017/1/11.
 */

public class FileUtils {
    private Context context;

    private static FileUtils getInstance;


    private FileUtils() {

    }

    /**
     * 获取当前类实例
     */
    public static FileUtils getInstance() {
        if (getInstance == null) {
            getInstance = new FileUtils();
        }
        return getInstance;
    }

    /**
     * 使用FileInputStream读取文件
     * @return 返回String
     */
    public String getRestUser(){
        File file = new File(Environment.getExternalStorageDirectory(), "Untitled.json");
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] b = new byte[inputStream.available()];
                inputStream.read(b);
                inputStream.close();
                return new String(b);
            } catch (Exception e) {
//                Toast.makeText(context,
//                        "读取失败", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else {
//            // 此时SDcard不存在或者不能进行读写操作的
//            Toast.makeText(context,
//                    "此时SDcard不存在或者不能进行读写操作", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    /**
     * 读取assets下的图片
     * @param context 上下文
     * @param fileName 文件名
     * @return
     */
    public String getAssetsCacheFile(Context context,String fileName)   {
        File cacheFile = new File(context.getCacheDir(), fileName);
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            try {
                FileOutputStream outputStream = new FileOutputStream(cacheFile);
                try {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buf)) > 0) {
                        outputStream.write(buf, 0, len);
                    }
                } finally {
                    outputStream.close();
                }
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cacheFile.getAbsolutePath();
    }


}
