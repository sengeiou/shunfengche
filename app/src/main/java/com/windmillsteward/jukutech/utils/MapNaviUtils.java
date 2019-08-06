package com.windmillsteward.jukutech.utils;

/**
 * 描述：
 * author:cyq
 * 2018-11-06
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.windmillsteward.jukutech.customview.dialog.SelectTwoDialog;

import java.io.File;

/**
 * 地图导航
 * Created by cyq on 2018/11/6.
 */
public class MapNaviUtils {
    public static final String PN_GAODE_MAP = "com.autonavi.minimap"; // 高德地图包名
    public static final String PN_BAIDU_MAP = "com.baidu.BaiduMap"; // 百度地图包名
    public static final String DOWNLOAD_GAODE_MAP = "http://www.autonavi.com/"; // 高德地图下载地址
    public static final String DOWNLOAD_BAIDU_MAP = "http://map.baidu.com/zt/client/index/"; // 百度地图下载地址

    public static final int JUMP_BAIDU = 1; // 跳百度地图标识
    public static final int JUMP_GAODE = 2; // 跳高德地图标识



    /**
     * 检查应用是否安装
     *
     * @return
     */
    public static boolean isGdMapInstalled() {
        return isInstallPackage(PN_GAODE_MAP);
    }

    public static boolean isBaiduMapInstalled() {
        return isInstallPackage(PN_BAIDU_MAP);
    }

    private static boolean isInstallPackage(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    /**
     * 百度坐标系 (BD-09) 与 火星坐标系 (GCJ-02)的转换
     * 即 百度 转 谷歌、高德
     *
     * @param latLng
     * @returns
     */
    public static LatLng BD09ToGCJ02(LatLng latLng) {
        double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        double x = latLng.longitude - 0.0065;
        double y = latLng.latitude - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double gg_lat = z * Math.sin(theta);
        double gg_lng = z * Math.cos(theta);
        return new LatLng(gg_lat, gg_lng);
    }

    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换
     * 即谷歌、高德 转 百度
     *
     * @param latLng
     * @returns
     */
    public static LatLng GCJ02ToBD09(LatLng latLng) {
        double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        double z = Math.sqrt(latLng.longitude * latLng.longitude + latLng.latitude * latLng.latitude) + 0.00002 * Math.sin(latLng.latitude * x_pi);
        double theta = Math.atan2(latLng.latitude, latLng.longitude) + 0.000003 * Math.cos(latLng.longitude * x_pi);
        double bd_lat = z * Math.sin(theta) + 0.006;
        double bd_lng = z * Math.cos(theta) + 0.0065;
        return new LatLng(bd_lat, bd_lng);
    }

    /**
     * 打开高德地图导航功能
     *
     * @param context
     * @param slat    起点纬度
     * @param slon    起点经度
     * @param sname   起点名称 可不填（0,0，null）
     * @param dlat    终点纬度
     * @param dlon    终点经度
     * @param dname   终点名称 必填
     */
    public static void openGaoDeNavi(Context context, double slat, double slon, String sname, double dlat, double dlon, String dname) {
        LatLng destination = new LatLng(dlat, dlon);
        LatLng destinationLatLng = BD09ToGCJ02(destination);
        dlat = destinationLatLng.latitude;
        dlon = destinationLatLng.longitude;
        String uriString = null;
        StringBuilder builder = new StringBuilder("amapuri://route/plan?sourceApplication=maxuslife");
        if (0 == slat) {
//            //如果不传起点（注释下面这段），默认就是现在我的位置（手机当前定位）
//            AMapLocation location = LocationService.getInstance().getAMapLocation();
//            if (LocationService.isSuccess(location)) {
//                builder.append("&sname=我的位置")
//                        .append("&slat=").append(location.getLatitude())
//                        .append("&slon=").append(location.getLongitude());
//            }
        } else {
            builder.append("&sname=").append(sname)
                    .append("&slat=").append(slat)
                    .append("&slon=").append(slon);
        }
        builder.append("&dlat=").append(dlat)
                .append("&dlon=").append(dlon)
                .append("&dname=").append(dname)
                .append("&dev=0")
                .append("&t=0");
        uriString = builder.toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage(PN_GAODE_MAP);
        intent.setData(Uri.parse(uriString));
        context.startActivity(intent);
    }

    /**
     * 打开百度地图导航功能(默认坐标点是高德地图，需要转换)
     *
     * @param context
     * @param slat    起点纬度
     * @param slon    起点经度
     * @param sname   起点名称 可不填（0,0，null）
     * @param dlat    终点纬度
     * @param dlon    终点经度
     * @param dname   终点名称 必填
     */
    public static void openBaiDuNavi(Context context, double slat, double slon, String sname, double dlat, double dlon, String dname) {
        String uriString = null;
        //终点坐标转换
//        LatLng destination = new LatLng(dlat, dlon);
//        LatLng destinationLatLng = GCJ02ToBD09(destination);
//        dlat = destinationLatLng.latitude;
//        dlon = destinationLatLng.longitude;

        StringBuilder builder = new StringBuilder("baidumap://map/direction?mode=driving&");
        if (slat != 0) {
            //起点坐标转换
            LatLng origin = new LatLng(slat, slon);
            LatLng originLatLng = GCJ02ToBD09(origin);
            slat = originLatLng.latitude;
            slon = originLatLng.longitude;

            builder.append("origin=latlng:")
                    .append(slat)
                    .append(",")
                    .append(slon)
                    .append("|name:")
                    .append(sname);
        }
        builder.append("&destination=latlng:")
                .append(dlat)
                .append(",")
                .append(dlon)
                .append("|name:")
                .append(dname);
        uriString = builder.toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage(PN_BAIDU_MAP);
        intent.setData(Uri.parse(uriString));
        context.startActivity(intent);
    }


    public static void showDaoHangDialog(final Context context, final double longitude, final double latitude, final String area_name){
        new SelectTwoDialog(context, 0, new SelectTwoDialog.DialogListner() {
            @Override
            public void selectDialogIndex(int action, int positon) {
                if (positon == 1){//百度
                    jumpToBaiDuOrGaoDe(context,JUMP_BAIDU,longitude,latitude,area_name);
                }else if (positon == 2){//高德
                    jumpToBaiDuOrGaoDe(context,JUMP_GAODE,longitude,latitude,area_name);
                }

            }
        },"百度地图导航","高德地图导航","取消").showAtBottom();
    }


    public static void jumpToBaiDuOrGaoDe(Context mContext, int type, double longitude, double latitude, String area_name) {

        if (type == JUMP_BAIDU){//百度
            if (MapNaviUtils.isBaiduMapInstalled()) {
                MapNaviUtils.openBaiDuNavi(mContext, 0, 0, null, latitude, longitude, area_name);
            } else {
                Toast.makeText(mContext,"您还未安装百度地图！",Toast.LENGTH_SHORT).show();

            }
        }else if (type == JUMP_GAODE){//高德
            if (MapNaviUtils.isGdMapInstalled()) {
                MapNaviUtils.openGaoDeNavi(mContext, 0, 0, null, latitude, longitude, area_name);
            } else {
                Toast.makeText(mContext,"您还未安装高德地图！",Toast.LENGTH_SHORT).show();

            }
        }


    }


}

