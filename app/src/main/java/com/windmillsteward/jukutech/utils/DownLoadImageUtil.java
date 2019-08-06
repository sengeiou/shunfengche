package com.windmillsteward.jukutech.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;

/**
 * 描述： XUtil3.0图片下载
 * author:cyq
 * 2017-08-15
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class DownLoadImageUtil {

    public static final String IMAGE_SDCARD_MADER = Environment
            .getExternalStorageDirectory()
            + "/fll/img/";

    /**
     * 下载文件
     */
    public static <T> Callback.Cancelable DownLoadFile(String url, String filepath, Callback.CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        //设置断点续传
        params.setAutoResume(true);
        params.setSaveFilePath(filepath);
        Callback.Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }


    /**
     * 获取声音文件名字
     *
     * @return 假如当前录制声音时间是2016年6月1日11点30分30秒。得到的文件名字就是20160601113030.这样保证文件名的唯一性
     */
    public static String geFileName() {
        long getNowTimeLong = System.currentTimeMillis();
        SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
        String result = time.format(getNowTimeLong);
        return result;
    }

    private static Toast toast;

    /**
     * 单例吐司
     */
    public static void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }

}
