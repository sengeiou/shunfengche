package com.windmillsteward.jukutech.utils;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * SDCard处理类 lrx 2014-4-24 注意在配置文件中配置权限
 */
public class SDCardUtils {
    public static final int SIZETYPE_B = 1;// 获取文件大小单位为B的double值
    public static final int SIZETYPE_KB = 2;// 获取文件大小单位为KB的double值
    public static final int SIZETYPE_MB = 3;// 获取文件大小单位为MB的double值
    public static final int SIZETYPE_GB = 4;// 获取文件大小单位为GB的double值


    /**
     * 检测是否存在sd卡
     *
     * @return true：存在 ；false:不存在
     */
    public static boolean checkSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    /**
     * 写信息到sd卡中的文件
     *
     * @param ex 输入的信息
     * @throws IOException
     */
    public static void writeSDCard(String ex, String filename)
            throws IOException {
        boolean b = false;
        String pathString = Environment.getExternalStorageDirectory().getPath();// 获取sd卡路径
        // :/sdcard
        File file = new File(pathString + "/" + filename + ".txt");
        if (!file.exists()) {// 判断文件是否存在
            file.createNewFile();
        }

        FileOutputStream out = new FileOutputStream(file, true);// true表示在文件末尾添加
        out.write(ex.getBytes("UTF-8"));
        out.close();
    }

    /**
     * 保存图片到SD卡
     *
     * @param bitmap   图片格式
     * @param filePath 图片路径
     */
    public static boolean writeSDCard(Bitmap bitmap, String filePath) {
        boolean b = false;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            b = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);// 把数据写入文件,第二个参数为压缩率，100表示不压缩
        } catch (FileNotFoundException e) {
            b = false;
            e.printStackTrace();
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    /**
     * 从sd卡中读取文件内容
     *
     * @param path 路径
     * @return
     * @throws Exception
     */
    public static String readSDCard(String path) throws Exception {
        String pathString = Environment.getExternalStorageDirectory().getPath();// 获取sd卡路径
        // :/sdcard
        File file = new File(pathString + "/" + path);
        if (!file.exists()) {
            return null;
        }
        byte Buffer[] = new byte[1024];
        FileInputStream in = new FileInputStream(file);
        int len = in.read(Buffer);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(Buffer, 0, len);
        String result = new String(outputStream.toByteArray());
        return result;
    }

    /**
     * 创建文件夹，成功返回true,如果已经存在，则不再创建
     *
     * @param path 文件夹路径
     * @return
     */
    public static boolean makeFile(String path) {
        boolean b = false;
        if (!checkSDCard()) {
            return false;
        }
        File file = new File(path);
        // 如果文件夹存在，不处理
        if (!file.exists()) {
            b = file.mkdirs();
        } else {
            b = true;
        }
        return b;
    }

    /**
     * 获取缓存大小
     *
     * @param filePath 文件路径
     * @param sizeType 获取大小的类型1为B、2为KB、3为MB、4为GB
     * @return double值的大小
     */
    public static double getFileOrFilesSize(String filePath, int sizeType) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FormetFileSize(blockSize, sizeType);
    }

    /**
     * 获取指定文件夹
     *
     * @param f 文件夹
     * @return
     * @throws Exception
     */
    private static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;
    }

    /**
     * 获取指定文件大小
     *
     * @param file 文件
     * @return
     * @throws Exception
     */
    private static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
        }
        return size;
    }

    /**
     * 转换文件大小,指定转换的类型
     *
     * @param fileS
     * @param sizeType
     * @return
     */
    private static double FormetFileSize(long fileS, int sizeType) {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (sizeType) {
            case SIZETYPE_B:
                fileSizeLong = Double.valueOf(df.format((double) fileS));
                break;
            case SIZETYPE_KB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
                break;
            case SIZETYPE_MB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
                break;
            case SIZETYPE_GB:
                fileSizeLong = Double.valueOf(df
                        .format((double) fileS / 1073741824));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }

    /**
     * 删除文件路径
     *
     * @param filepath 要删除的文件夹路径
     * @throws IOException
     */
    public static void delFilePath(String filepath) throws IOException {
        File f = new File(filepath);
        if (f.exists() && f.isDirectory()) {
            if (f.listFiles().length == 0) {
                f.delete();
            } else {
                File delFile[] = f.listFiles();
                int i = f.listFiles().length;
                for (int j = 0; j < i; j++) {
                    if (delFile[j].isDirectory()) {
                        delFilePath(delFile[j].getAbsolutePath());
                    }
                    delFile[j].delete();
                }
            }
        }
    }

    /**
     * 删除文件名
     *
     * @param filename 要删除的文件路径
     * @throws IOException
     */
    public static void delFile(String filename) throws IOException {
        File f = new File(filename);
        if (f.exists() && !f.isDirectory()) {
            f.delete();

        }
    }

    /**
     * uri转换字符串url
     *
     * @param uri
     * @param ctx
     * @return
     */
    public static String getPhonePath(Uri uri, Context ctx) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = ctx.getContentResolver().query(uri, filePathColumn, null, null, null);
        if (cursor == null) {
            return "";
        }
        if (cursor.moveToFirst()) {
            String phonePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();
            return phonePath;
        } else {
            return "";
        }
    }

    //--------------------------------获取从相册选择图片的路径代码开始--------------------------
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Uri uri, final Context context) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
    //--------------------------------获取从相册选择图片的路径代码结束--------------------------
}
