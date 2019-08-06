package com.windmillsteward.jukutech.interfaces;

import android.os.Environment;

/**
 * 一般的常量表（不包括接口)
 * String, Int，Double等类型
 *
 * @author: zhuxian
 * @data: 2016/11/30 16:05
 * Created by  2016 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface Define {

    /**
     * *****************************Int类型常量*****************************************************
     */
    int COMPRESS_TAG = 101;//图片压缩标志
    int IMAGE_SIZE = 500;//压缩的图片大小上限
    int ALL_JURISDICTION_APPLY = 17;// 所有权限申请码
    /**
     * *****************************String类型常量**************************************************
     */
    String APP_CACHE = Environment.getExternalStorageDirectory().toString() + "/sfcgj/";
    String APP_NAME = "sfcgj.apk";//检查更新文件名
    String PUBLIC_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";//公共的时间格式
    String LANGUAGE_TYPE = "language_type";// string类型 多语言中的语言版本
    String APP_TEMP_HEAD = APP_CACHE + "temp_head.jpg";//临时头像地址，使用时候，直接覆盖旧的

    String WX_APP_ID = "wx58fd613158c1dde6";//微信开放平台的APP_ID
    String WX_APP_SECRET = "239bd40d06b7447073f3da26652dc8ba";// 微信开放平台的APP_SECRET
    String QQ_APP_ID = "1106737687";//QQ开放平台的APP_ID
    String QQ_APP_SECRET = "a0YYUB4dfPhm2ey0";// QQ开放平台的APP_SECRET

    String BANNER = "banner";//recyclerview轮播图标识
    String CLASS_VIEW_PAGE = "class_view_page";//recyclerview特产首页商品分类标识
    String SECKILL = "seckill";//recyclerview特产首页秒杀标识
    String RECOMMEND_GRID = "recommend_grid";//recyclerview特产首页推荐标识
    String GUESS_YOU_LIKE = "guess_you_like";//recyclerview特产首页猜你喜欢标识
    String BOTTOM = "bottom";//recyclerview应用首页/特产首页底部标识

    /**
     * *****************************登录返回的信息常量**************************************************
     */
    String USER_INFO = "USER_INFO";

    /**
     * *****************************百度地图**************************************************
     */
    String LONGITUDE = "longitude";//经度
    String LATITUDE = "latitude";//纬度

    //------------------------------------------Intent标识------------------------------------------
    String INTENT_DATA = "intent_data";
    String INTENT_DATA_TWO = "intent_data_two";
    String INTENT_DATA_THREE = "intent_data_three"; // 一般数据超过三个就要用到bundle传递了
    String INTENT_DATA_FOUR = "intent_data_four"; // 一般数据超过三个就要用到bundle传递了
    String INTENT_DATA_FIVE = "intent_data_five"; // 一般数据超过三个就要用到bundle传递了
    String INTENT_DATA_SIX = "intent_data_six"; // 一般数据超过三个就要用到bundle传递了
    String INTENT_TYPE = "intent_type";
    String INTENT_TYPE_TWO = "intent_type_two";
    String INTENT_TYPE_THREE = "intent_type_three"; // 一般数据超过三个就要用到bundle传递了
    String POSITION = "position";// item下标
    String RECEIVE_ID = "receive_id";// 优惠券id
    String CURR_PRICE = "CURR_PRICE";// 当前价格

    /**
     * *****************************sp键标识**************************************************
     */
    String LOGIN_SUCCESS = "LoginSuccess";// 类型 LoginSuccessInfo类  登录成功后的实体
    String SEARCH_HISTORY = "SEARCH_HISTORY";// 类型 List<SearchHistoryBean>  搜索历史记录
    String ACCESS_TOKEN = "ACCESS_TOKEN";// 类型 String  token
    String USER_ID = "USER_ID";// 用户id int
    String CURR_CITY_ID = "CURR_CITY_ID";// 类型 int  当前城市id
    String CURR_CITY_NAME = "CURR_CITY_NAME";// 类型 String  当前城市
    String CURR_CITY_THIRD_ID = "CURR_CITY_THIRD_ID";// 类型 String  当前区id
    String CURR_CITY_THIRD_NAME = "CURR_CITY_THIRD_NAME";// 类型 String  当前区
    String IS_FIRST_OPEN = "is_first_open";// 是否是第一次启动应用，用于显示导航页做判断
    String DEVICE_TOKEN = "device_token";// 设备token，Umeng需要用
    String DEFAULT_SHOPPING_ADDRESS = "DEFAULT_SHOPPING_ADDRESS";// 默认收货地址  AddressListBean.ListBean类型的json数据
    String POI_INDEX_HISTORY = "POI_INDEX_HISTORY";// 租车管理历史记录

    String CURR_LONGLAT_ADDRESS = "CURR_LONGLAT_ADDRESS";//当前定位的经纬度信息 经度在前
    String MSG_SHAKE = "msg_shake";//消息震动开启
    String MSG_RING = "msg_ring";//消息响铃开启


}
