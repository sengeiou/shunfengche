package com.windmillsteward.jukutech.activity.home.family.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.presenter.IntelligentFamilyDetailPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.BusinessAuthenticationActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.base.interfaces.OnUserAuthenListener;
import com.windmillsteward.jukutech.bean.IntelligentFamilyDetailBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.ExpandTextView;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateTimeFormatUtil;
import com.windmillsteward.jukutech.utils.DistanceUtils;
import com.windmillsteward.jukutech.utils.GraphicUtil;
import com.windmillsteward.jukutech.utils.ImageUtils;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.Util;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：智慧家庭详情
 * 时间：2018/1/16/016
 * 作者：xjh
 */
public class IntelligentFamilyDetailActivity extends BaseActivity implements IntelligentFamilyDetailView, View.OnClickListener {
    public static final String DETAIL_ID = "DETAIL_ID";
    private TextView toolbar_iv_title;
    private TextView tv_back_black1;
    private ImageView toolbar_iv_right;
    private FlyBanner flyBanner;
    private ImageView iv_play;
    private ImageView iv_address;
    private CircleImageView civ_header;
    private TextView tv_username;
    private TextView tv_post_area;
    private TextView tv_range;
    private TextView tv_price;
    private TextView tv_time_times;
    private ExpandTextView expand_more;
    private TextView tv_get_order;
    private TextView tv_see_location;
    private TextView tv_qd_address;
    private TextView tv_zd_address;
    private FrameLayout fl_banner;
    private IntelligentFamilyDetailPresenter presenter;
    private int requir_id;  // 该需求详情的id
    private int position;
    private String latitude;
    private String longitude;

    private LocationClient mLocClient;
    private MyLocationListener locationListener;
    private IntelligentFamilyDetailBean bean;

    private boolean isCollect;  // 是否收藏
    private String city;//城市
    private String district;//地区

    private IWXAPI api;
    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;
    private int mTargetQuanScene = SendMessageToWX.Req.WXSceneTimeline ;
    private static final int THUMB_SIZE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligent_family_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            requir_id = extras.getInt(DETAIL_ID);
            position = extras.getInt(Define.POSITION);
        }

        initView();
        initToolbar();
        initFlyBanner();
        presenter = new IntelligentFamilyDetailPresenter(this);
        String longLat = KV.get(Define.CURR_LONGLAT_ADDRESS, "");
        city = KV.get(Define.CURR_CITY_NAME, "");
        district = KV.get(Define.CURR_CITY_THIRD_NAME, "");
        if (!StringUtil.isEmpty(longLat)) {
            longitude = longLat.split(",")[0];
            latitude = longLat.split(",")[1];
        }
        presenter.initData(getAccessToken(), requir_id, longitude, latitude);

//        initLocal();
    }

    private void initFlyBanner() {
        flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (bean != null) {
                    if (position == 0) {
                        if (!TextUtils.isEmpty(bean.getVideo_url())) {
                            String video_urls = bean.getVideo_url();
                            Bundle bundle = new Bundle();
                            bundle.putString(VideoPlayActivity.VIDEO_URL, video_urls);
                            startAtvDonFinish(VideoPlayActivity.class, bundle);
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putStringArrayList(PhotoViewActivity.PIC_URLS, (ArrayList<String>) bean.getPic_urls());
                            bundle.putInt(PhotoViewActivity.CURR_POSITION, position);
                            startAtvDonFinish(PhotoViewActivity.class, bundle);
                        }
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList(PhotoViewActivity.PIC_URLS, (ArrayList<String>) bean.getPic_urls());
                        bundle.putInt(PhotoViewActivity.CURR_POSITION, position - 1);
                        startAtvDonFinish(PhotoViewActivity.class, bundle);
                    }

                }
            }
        });
    }

    private void initLocal() {
        mLocClient = new LocationClient(getApplicationContext());

        locationListener = new MyLocationListener();
        mLocClient.registerLocationListener(locationListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(500); // 大于1000才循环
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back_black:
                Intent data = new Intent();
                Bundle extras = new Bundle();
                extras.putInt(Define.POSITION, isCollect ? -1 : position);
                data.putExtras(extras);
                setResult(200, data);
                finish();
                break;
            case R.id.tv_qd_address:
                if (bean != null) {
                    if (StringUtil.isAllNotEmpty(bean.getLongitude(), bean.getLatitude())) {
                        MapNaviUtils.showDaoHangDialog(IntelligentFamilyDetailActivity.this, Double.parseDouble(bean.getLongitude()), Double.parseDouble(bean.getLatitude()), "");
                    }
                }
                break;
            case R.id.tv_zd_address:
                if (bean != null) {
                    if (StringUtil.isAllNotEmpty(bean.getT_longitude(), bean.getT_latitude())) {
                        MapNaviUtils.showDaoHangDialog(IntelligentFamilyDetailActivity.this, Double.parseDouble(bean.getT_longitude()), Double.parseDouble(bean.getT_latitude()), "");
                    }
                }
                break;
            case R.id.iv_right:
                if (isLogin()) {
                    if (bean == null) {
                        return;
                    }
                    showShareDialog(bean.getShare_url());
//                    if (isCollect) {
//                        presenter.cancelCollectRequire(getAccessToken(), requir_id);
//                    } else {
//                        presenter.collectRequire(getAccessToken(), requir_id);
//                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }

                break;
            case R.id.tv_get_order:
                if (isLogin()) {
                    if (bean != null) {
                        checkUserAuthen(new OnUserAuthenListener() {
                            @Override
                            public void isAuthen() {
                                presenter.getOrder(getAccessToken(), requir_id, city + district, longitude, latitude);
                            }

                            @Override
                            public void isNotAuthen() {
                                final BaseDialog baseDialog = new BaseDialog(IntelligentFamilyDetailActivity.this);
                                baseDialog.showThreeButton("认证信息", "请完善您的认证信息", "取消", "个人认证", "企业认证", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        baseDialog.dismiss();
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        baseDialog.dismiss();
                                        startAtvDonFinish(PersonalAuthenticationActivity.class);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        baseDialog.dismiss();
                                        startAtvDonFinish(BusinessAuthenticationActivity.class);
                                    }
                                });
                            }
                        });

                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.tv_see_location:
                if (bean != null) {
                    if (StringUtil.isAllNotEmpty(bean.getLongitude(), bean.getLatitude())) {
                        MapNaviUtils.showDaoHangDialog(IntelligentFamilyDetailActivity.this, Double.parseDouble(bean.getLongitude()), Double.parseDouble(bean.getLatitude()), "");
                    }
                }
                break;
        }
    }

    /*
     * 展示分享对话框
     */
    private void showShareDialog(String url) {
        if (TextUtils.isEmpty(url))
            return;

        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_smart_content_normal, null);

        contentView.findViewById(R.id.tv_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
                weixinShare();
            }
        });
        contentView.findViewById(R.id.tv_weixin_quan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
                weixinQuanShare();
            }
        });

        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.show();
    }

    //微信分享
    private void weixinShare() {
        api = WXAPIFactory.createWXAPI(this, Define.WX_APP_ID);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = bean.getShare_url();
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = bean.getClass_name();
        msg.description = bean.getDescription();
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_logo_rectangle);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = mTargetScene;
        api.sendReq(req);
    }

    //微信朋友圈分享
    private void weixinQuanShare() {
        api = WXAPIFactory.createWXAPI(this, Define.WX_APP_ID);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = bean.getShare_url();
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = bean.getClass_name();
        msg.description = bean.getDescription();
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_logo_rectangle);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = mTargetQuanScene;
        api.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
            latitude = String.valueOf(location.getLatitude());
            longitude = String.valueOf(location.getLongitude());
            city = location.getCity();
            district = location.getDistrict();
            presenter.refreshData(getAccessToken(), requir_id, longitude, latitude);
            mLocClient.unRegisterLocationListener(this);
        }
    }


    private void initToolbar() {
        tv_back_black1.setOnClickListener(this);
        toolbar_iv_title.setText("详情");
        toolbar_iv_right.setVisibility(View.VISIBLE);
//        toolbar_iv_right.setImageResource(R.mipmap.shoucang);
        toolbar_iv_right.setOnClickListener(this);

    }

    private void initView() {
        tv_back_black1 = (TextView) findViewById(R.id.tv_back_black);
        toolbar_iv_title = (TextView) findViewById(R.id.tv_title_info);
        tv_see_location = (TextView) findViewById(R.id.tv_see_location);
        toolbar_iv_right = (ImageView) findViewById(R.id.iv_right);
        iv_address = (ImageView) findViewById(R.id.iv_address);

        flyBanner = (FlyBanner) findViewById(R.id.flyBanner);
        fl_banner = (FrameLayout) findViewById(R.id.fl_banner);
        iv_play = (ImageView) findViewById(R.id.iv_play);
        civ_header = (CircleImageView) findViewById(R.id.civ_header);
        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_post_area = (TextView) findViewById(R.id.tv_post_area);
        tv_range = (TextView) findViewById(R.id.tv_range);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_time_times = (TextView) findViewById(R.id.tv_time_times);
        expand_more = (ExpandTextView) findViewById(R.id.expand_more);
        tv_get_order = (TextView) findViewById(R.id.tv_get_order);
        tv_qd_address = (TextView) findViewById(R.id.tv_qd_address);
        tv_zd_address = (TextView) findViewById(R.id.tv_zd_address);

        tv_get_order.setOnClickListener(this);
        tv_see_location.setOnClickListener(this);
        tv_zd_address.setOnClickListener(this);
        tv_qd_address.setOnClickListener(this);

        ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH(this, GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH - GraphicUtil.dp2px(this, 0);
        layoutParams.height = 575 * layoutParams.width / 1080;
        flyBanner.setLayoutParams(layoutParams);
    }

    @Override
    public void initData(IntelligentFamilyDetailBean bean) {
        if (bean != null) {
            this.bean = bean;
            List<String> list = new ArrayList<>();
            if (!TextUtils.isEmpty(bean.getVideo_url())) {
                list.add(bean.getVideo_url());
                flyBanner.setHaveVideoCover(true);
            }
            List<String> pic_urls = bean.getPic_urls();
            if (pic_urls != null && pic_urls.size() > 0) {
                list.addAll(pic_urls);
            }
            if (list.size() > 0) {
                fl_banner.setVisibility(View.VISIBLE);
                flyBanner.setImagesUrl(list);
            }else{
                fl_banner.setVisibility(View.GONE);
            }

            x.image().bind(civ_header, bean.getUser_avatar_url(), ImageUtils.defaulHeader());
            tv_username.setText(bean.getClass_name());
            tv_post_area.setText("发布于" + bean.getRequire_area());
            tv_range.setText("距我" + DistanceUtils.getFormatDistance(bean.getDistance()));
            tv_price.setText("市场指导价￥" + bean.getPrice());
            String result = DateTimeFormatUtil.dateTimeFormatString(bean.getUpdate_time()) + "    |    " +
                    bean.getView_num() + "次浏览";
            tv_time_times.setText(result);
            expand_more.setContent(bean.getDescription());

//            if (bean.getIs_collect() == 0) {
//                isCollect = false;
//                toolbar_iv_right.setImageResource(R.mipmap.shoucang);
//            } else {
//                toolbar_iv_right.setImageResource(R.mipmap.shoucang_red);
//                isCollect = true;
//            }

            if (bean.getIs_myorder() == 0) {
                tv_get_order.setText("抢单");
            } else {
                tv_get_order.setText("已抢单");
            }

            if(bean.getIs_ad() == 0){//不是广告发布，显示抢单按钮
                tv_get_order.setVisibility(View.VISIBLE);
            }else{
                tv_get_order.setVisibility(View.GONE);
            }

            String t_address = bean.getT_address();
            if (TextUtils.isEmpty(t_address)){//如果终点地址为空，则只显示起点地址
                tv_zd_address.setVisibility(View.GONE);
                iv_address.setVisibility(View.GONE);
                tv_qd_address.setText("任务地址:" + bean.getAddress());
            }else{
                tv_qd_address.setText("起点地址:"+ bean.getAddress());
                tv_zd_address.setVisibility(View.VISIBLE);
                iv_address.setVisibility(View.VISIBLE);
                tv_zd_address.setText("终点地址:"+bean.getT_address());
            }


        }
    }

    /**
     * 收藏成功
     */
    @Override
    public void collectionRequireSuccess() {
        toolbar_iv_right.setImageResource(R.mipmap.shoucang_red);
        isCollect = true;
    }

    /**
     * 抢单成功
     */
    @Override
    public void getOrderSuccess(final PublicResultModel resultModel, final String msg) {
//        tv_get_order.setText("已抢单");
//        showTips(msg);
//        finish();

        final BaseDialog baseDialog = new BaseDialog(IntelligentFamilyDetailActivity.this);
        baseDialog.showTwoButton("提示", "是否花取"+resultModel.getOrder_price()+"元,查看并接取此用户订单", "确定", "取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
                NewPayActivity.go(IntelligentFamilyDetailActivity.this,
                        CommonPayPresenter.TYPE_ZHIHUI_SHENGHUOXINXI, resultModel.getRelate_id(),
                        resultModel.getOrder_price() + "",
                        resultModel.getOrder_name(), NewPayActivity.CAN_USE_COUPON,msg);

            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
            }
        });

    }

    /**
     * 取消收藏成功
     */
    @Override
    public void cancelCollectionRequireSuccess() {
        isCollect = false;
        toolbar_iv_right.setImageResource(R.mipmap.shoucang);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NewPayActivity.REQUEST_CODE && resultCode == NewPayActivity.RESULT_CODE_SUCCESS) {
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocClient != null) {
            mLocClient.stop();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent data = new Intent();
            Bundle extras = new Bundle();
            extras.putInt(Define.POSITION, (isCollect) ? -1 : position);
            data.putExtras(extras);
            setResult(200, data);

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
