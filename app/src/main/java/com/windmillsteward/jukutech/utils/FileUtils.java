package com.windmillsteward.jukutech.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 描述：
 * 时间：2018/1/15
 * 作者：xjh
 */

public class FileUtils {

    public static final String IMAGE_PATH = Environment.getExternalStorageDirectory().getPath()+File.separator+"shunfengche";

    /**
     * 将bitmap图片保存到本地
     * @param name
     * @param bmp
     * @return
     */
    public static File saveImage(String name, Bitmap bmp) {
        File appDir = new File(IMAGE_PATH);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
