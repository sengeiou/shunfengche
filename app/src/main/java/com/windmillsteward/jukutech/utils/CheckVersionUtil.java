package com.windmillsteward.jukutech.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.windmillsteward.jukutech.bean.CheckVersionUpdateBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * author@: cyq
 * 2017/4/14 14:49
 * Created by  2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class CheckVersionUtil {

    public static ProgressDialog progressDialog;
    public static Callback.Cancelable cancelable;


    /**
     * @param activity                 当前活动
     * @param checkVersionUpdateEntity 检测新版本实体
     * @param NewestVersionsToast      是否显示最新版本提示 true显示  false不显示
     */
    public static void checkVersionUpdate(final Activity activity, final CheckVersionUpdateBean checkVersionUpdateEntity, boolean NewestVersionsToast) {
        if (ConfigUtil.getVersionCode(activity) < checkVersionUpdateEntity.getVersion_num()) {//当前版本号小于服务器返回的版本号
            if (checkVersionUpdateEntity.getForce_update() == 1) {//等于1,表示强制更新,等于0,表示非强制更新
                if (ConfigUtil.getVersionCode(activity) < checkVersionUpdateEntity.getMin_version_allowed()) {//当前版本在允许的版本号数组里,可以不强制更新,即弹出选择框
                    forceDownload(activity, checkVersionUpdateEntity);//强制下载
                } else {
                    showComfirmDialog(activity, checkVersionUpdateEntity);//弹出确定框选择更新
                }
            } else {
                showComfirmDialog(activity, checkVersionUpdateEntity);//弹出确定框选择更新
            }
        } else {
            if (NewestVersionsToast) {
                Toast.makeText(activity, "已是最新版本", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


    /**
     * 弹确定框,点击确定可下载
     *
     * @param activity                 上下文
     * @param checkVersionUpdateEntity
     */
    public static void showComfirmDialog(final Activity activity, final CheckVersionUpdateBean checkVersionUpdateEntity) {
        new AlertDialog(activity).builder().setTitle(checkVersionUpdateEntity.getVersion_title()).setMsg(checkVersionUpdateEntity.getVersion_info()).setPositiveButton("确定", new View.OnClickListener() {
            public void onClick(View paramView) {
                /**
                 * 进度条对话框
                 */
                progressDialog = new ProgressDialog(activity);
                //设置标题
                progressDialog.setTitle("下载文件");
                //设置信息
                progressDialog.setMessage("为了给您更好的服务，管家正在更新中");
                //设置显示的格式
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setCancelable(false);

                /**
                 * 可取消的任务
                 */
                RequestParams params = new RequestParams(checkVersionUpdateEntity.getDownload_url());
                params.setAutoRename(false);//设置是否根据头信息自动命名文件
//                            params.setSaveFilePath(Define.APP_CACHE);
                params.setSaveFilePath(Define.APP_CACHE + Define.APP_NAME);
                params.setExecutor(new PriorityExecutor(2, true));//自定义线程池,有效的值范围[1, 3], 设置为3时, 可能阻塞图片加载.
                params.setCancelFast(true);//是否可以被立即停止.
                //下面的回调都是在主线程中运行的,这里设置的带进度的回调
                cancelable = x.http().get(params, new Callback.ProgressCallback<File>() {
                    @Override
                    public void onCancelled(CancelledException arg0) {
                        Log.i("tag", "取消" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable arg0, boolean arg1) {
                        Log.i("tag", "onError: 失败" + Thread.currentThread().getName());
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFinished() {
                        Log.i("tag", "完成,每次取消下载也会执行该方法" + Thread.currentThread().getName());
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onSuccess(File arg0) {
                        Log.i("tag", "下载成功的时候执行" + Thread.currentThread().getName());
//                                    SystemUtil.install(arg0.getAbsolutePath());
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(arg0),
                                "application/vnd.android.package-archive");
                        activity.startActivity(intent);
                        activity.finish();
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isDownloading) {
                        if (isDownloading) {
                            progressDialog.setProgress((int) (current * 100 / total));
                            Log.i("tag", "下载中,会不断的进行回调:" + Thread.currentThread().getName());
                        }
                    }

                    @Override
                    public void onStarted() {
                        Log.i("tag", "开始下载的时候执行" + Thread.currentThread().getName());
                        progressDialog.show();
                    }

                    @Override
                    public void onWaiting() {
                        Log.i("tag", "等待,在onStarted方法之前执行" + Thread.currentThread().getName());
                    }

                });
                //设置按钮
                progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //点击取消正在下载的操作
//                                    cancelable.cancel();
                    }
                });

            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            public void onClick(View paramView) {
            }
        }).show();
    }

    /**
     * 强制下载
     *
     * @param activity                 上下文
     * @param checkVersionUpdateEntity
     */
    public static void forceDownload(final Activity activity, final CheckVersionUpdateBean checkVersionUpdateEntity) {
        //不匹配的时候,则强制更新
        /**
         * 进度条对话框
         */
        progressDialog = new ProgressDialog(activity);
        //设置标题
        progressDialog.setTitle("下载文件");
        //设置信息
        progressDialog.setMessage("为了给您更好的服务，管家正在更新中");
        //设置显示的格式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);

        /**
         * 可取消的任务
         */
        RequestParams params = new RequestParams(checkVersionUpdateEntity.getDownload_url());
        params.setAutoRename(false);//设置是否根据头信息自动命名文件
//                            params.setSaveFilePath(Define.APP_CACHE);
        params.setSaveFilePath(Define.APP_CACHE + Define.APP_NAME);
        params.setExecutor(new PriorityExecutor(2, true));//自定义线程池,有效的值范围[1, 3], 设置为3时, 可能阻塞图片加载.
        params.setCancelFast(true);//是否可以被立即停止.
        //下面的回调都是在主线程中运行的,这里设置的带进度的回调
        cancelable = x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onCancelled(CancelledException arg0) {
                Log.i("tag", "取消" + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                Log.i("tag", "onError: 失败" + Thread.currentThread().getName());
                progressDialog.dismiss();
            }

            @Override
            public void onFinished() {
                Log.i("tag", "完成,每次取消下载也会执行该方法" + Thread.currentThread().getName());
                progressDialog.dismiss();
            }

            @Override
            public void onSuccess(File arg0) {
                Log.i("tag", "下载成功的时候执行" + Thread.currentThread().getName());
//                                    SystemUtil.install(arg0.getAbsolutePath());
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(arg0),
                        "application/vnd.android.package-archive");
                activity.startActivity(intent);
                activity.finish();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                if (isDownloading) {
                    progressDialog.setProgress((int) (current * 100 / total));
                    Log.i("tag", "下载中,会不断的进行回调:" + Thread.currentThread().getName());
                }
            }

            @Override
            public void onStarted() {
                Log.i("tag", "开始下载的时候执行" + Thread.currentThread().getName());
                progressDialog.show();
            }

            @Override
            public void onWaiting() {
                Log.i("tag", "等待,在onStarted方法之前执行" + Thread.currentThread().getName());
            }

        });

    }
}
