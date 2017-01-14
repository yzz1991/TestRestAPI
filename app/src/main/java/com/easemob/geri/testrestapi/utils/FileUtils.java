package com.easemob.geri.testrestapi.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;

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


}
