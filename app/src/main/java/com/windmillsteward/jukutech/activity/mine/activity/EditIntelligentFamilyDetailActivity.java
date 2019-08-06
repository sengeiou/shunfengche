package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.PhotoViewActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.PublishRequireActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.VideoPlayActivity;
import com.windmillsteward.jukutech.activity.mine.presenter.EditIntelligentFamilyDetailPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.IntelligentFamilyDetailBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.ExpandTextView;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.utils.DateTimeFormatUtil;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：智慧家庭详情
 * 时间：2018/1/16/016
 * 作者：xjh
 */
public class EditIntelligentFamilyDetailActivity extends BaseActivity implements EditIntelligentFamilyDetailView, View.OnClickListener {

    public static final String DETAIL_ID = "DETAIL_ID";
    public static final String POSITION = "POSITION";
    public static final String PUBLISH_STATUS = "PUBLISH_STATUS";
    private static final int DELETE_CODE = 104;
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;

    private FlyBanner flyBanner;
    private ImageView iv_play;
    private CircleImageView civ_header;
    private TextView tv_username;
    private TextView tv_post_area;
    private TextView tv_range;
    private TextView tv_desc;
    private TextView tv_price;
    private TextView tv_update_time;
    private TextView tv_read_times;
    private ExpandTextView expand_more;
    private TextView tv_hosted_id;
    private TextView tv_o_user_name;
    private TextView tv_o_user_phone;
    private TextView tv_edit;
    private ImageView iv_delete;
    private TextView tv_delete;
    private ImageView iv_edit;
    private LinearLayout linear_delete;
    private LinearLayout linear_edit;
    private LinearLayout linear_bottom;

    private EditIntelligentFamilyDetailPresenter presenter;
    private int requir_id;  // 该需求详情的id
    private int position;
    private int publish_status;  // 0审核中 1审核通过 2审核不通过

    private String latitude;
    private String longitude;

    private LocationClient mLocClient;
    private MyLocationListener locationListener;

    private IntelligentFamilyDetailBean bean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_intelligent_family_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            requir_id = extras.getInt(DETAIL_ID);
            position = extras.getInt(POSITION);
            publish_status = extras.getInt(PUBLISH_STATUS);
        }

        initView();
        initToolbar();
        initFlyBanner();
        presenter = new EditIntelligentFamilyDetailPresenter(this);
        presenter.initData(getAccessToken(), requir_id, longitude, latitude);

        initLocal();
    }

    private void initFlyBanner() {
        flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (bean != null) {
                    if (position == 0) {
                        if (bean.getVideo_cover() != null && bean.getVideo_cover().length() > 0) {
                            if (bean.getVideo_url() != null ) {
                                String s = bean.getVideo_url();
                                Bundle bundle = new Bundle();
                                bundle.putString(VideoPlayActivity.VIDEO_URL, s);
                                startAtvDonFinish(VideoPlayActivity.class, bundle);
                            }
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
            case R.id.linear_delete:
                if (bean != null) {
                    if (bean.getOrder_detail_status()==0) {  // 等待别人抢单
                        new AlertDialog(this).builder()
                                .setTitle("提示")
                                .setMsg("确定删除吗")
                                .setCancelable(true)
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                })
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        presenter.deleteRequire(bean.getRequire_id(), getAccessToken());
                                    }
                                })
                                .show();
                    } else if (bean.getOrder_detail_status()==1) {  // 等待确认订单，取消订单
                        new AlertDialog(this).builder()
                                .setTitle("提示")
                                .setMsg("确定取消订单吗")
                                .setCancelable(true)
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                })
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        presenter.cancelRequire(bean.getRequire_id(), getAccessToken());
                                    }
                                })
                                .show();
                    } else if (bean.getOrder_detail_status()==2 || bean.getOrder_detail_status()==3) {
                        new AlertDialog(this).builder()
                                .setTitle("提示")
                                .setMsg("确定将订单设置为未完成吗")
                                .setCancelable(true)
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                })
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        presenter.onFinishRequire(bean.getRequire_id(), getAccessToken());
                                    }
                                })
                                .show();
                    } else if (bean.getOrder_detail_status()==4) {
                        new AlertDialog(this).builder()
                                .setTitle("提示")
                                .setMsg("确定删除吗")
                                .setCancelable(true)
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                })
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        presenter.deleteRequire(bean.getRequire_id(), getAccessToken());
                                    }
                                })
                                .show();
                    }

                }
                break;
            case R.id.linear_edit:
                if (bean != null) {
                    if (bean.getOrder_detail_status()==0) {  // 编辑
                        Bundle bundle = new Bundle();
                        bundle.putInt(PublishRequireActivity.TYPE, 1);
                        bundle.putSerializable(PublishRequireActivity.DATA, bean);
                        startAtvDonFinish(PublishRequireActivity.class, bundle);
                    } else if (bean.getOrder_detail_status()==1) {  // 确认订单
                        new AlertDialog(this).builder()
                                .setTitle("提示")
                                .setMsg("请确认该订单信息，一旦确认不可取消")
                                .setCancelable(true)
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                })
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        presenter.sureRequire(getAccessToken(),bean.getRequire_id());
                                    }
                                })
                                .show();
                    } else if (bean.getOrder_detail_status()==2 || bean.getOrder_detail_status()==3) {
                        new AlertDialog(this).builder()
                                .setTitle("提示")
                                .setMsg("信息设置为“已完成”状态后，钱款会打到对方账户。您确认这条信息吗")
                                .setCancelable(true)
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                })
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        presenter.finishRequire(bean.getRequire_id(),getAccessToken());
                                    }
                                })
                                .show();
                    }

                }
                break;
        }
    }

    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
            latitude = String.valueOf(location.getLatitude());
            longitude = String.valueOf(location.getLongitude());
            presenter.refreshData(getAccessToken(), requir_id, longitude, latitude);
            mLocClient.unRegisterLocationListener(this);
        }
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("详情");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        flyBanner = (FlyBanner) findViewById(R.id.flyBanner);
        iv_play = (ImageView) findViewById(R.id.iv_play);
        civ_header = (CircleImageView) findViewById(R.id.civ_header);
        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_post_area = (TextView) findViewById(R.id.tv_post_area);
        tv_range = (TextView) findViewById(R.id.tv_range);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_update_time = (TextView) findViewById(R.id.tv_update_time);
        tv_read_times = (TextView) findViewById(R.id.tv_read_times);
        expand_more = (ExpandTextView) findViewById(R.id.expand_more);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        tv_o_user_name = (TextView) findViewById(R.id.tv_o_user_name);
        tv_o_user_phone = (TextView) findViewById(R.id.tv_o_user_phone);
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        linear_delete = (LinearLayout) findViewById(R.id.linear_delete);
        linear_edit = (LinearLayout) findViewById(R.id.linear_edit);
        linear_bottom = (LinearLayout) findViewById(R.id.linear_bottom);
        iv_delete = (ImageView) findViewById(R.id.iv_delete);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        iv_edit = (ImageView) findViewById(R.id.iv_edit);
    }

    @Override
    public void initData(IntelligentFamilyDetailBean bean) {
        if (bean != null) {
            this.bean = bean;
            List<String> list = new ArrayList<>();
            if (bean.getVideo_url() != null ) {
                list.add(bean.getVideo_cover());
                flyBanner.setHaveVideoCover(true);
            }
            List<String> pic_urls = bean.getPic_urls();
            if (pic_urls != null && pic_urls.size() > 0) {
                list.addAll(pic_urls);
            }
            if (list.size()>0) {
                flyBanner.setImagesUrl(list);
            }

            x.image().bind(civ_header, bean.getUser_avatar_url(), ImageUtils.defaulHeader());
            tv_username.setText(bean.getNickname());
            tv_post_area.setText("发布于" + bean.getRequire_area());
            tv_range.setText("距我" + bean.getDistance() + "米");
            tv_desc.setText(bean.getTitle());
            tv_price.setText("￥" + bean.getPrice());
            tv_update_time.setText(DateTimeFormatUtil.dateTimeFormatString(bean.getUpdate_time()));
            tv_read_times.setText("浏览：" + bean.getView_num() + "次");
            expand_more.setContent(bean.getDescription());
            tv_hosted_id.setText(bean.getHosting_show());
            tv_o_user_name.setText(bean.getO_user_name());
            tv_o_user_phone.setText(bean.getO_mobile_phone());

            if (publish_status == 0) {  // 审核中
                linear_bottom.setVisibility(View.GONE);
            } else if (publish_status == 1) {  // 审核通过
                linear_bottom.setVisibility(View.VISIBLE);
                int order_detail_status = bean.getOrder_detail_status();
                // 需求状态 0待抢单 1待确认,2已确认,3待检查,4已完成,5.已取消
                if (order_detail_status == 0) {  // 无人抢单，显示删除和编辑
                    linear_delete.setOnClickListener(this);
                    linear_edit.setOnClickListener(this);
                    linear_delete.setVisibility(View.VISIBLE);
                    linear_edit.setVisibility(View.VISIBLE);
                    iv_delete.setImageResource(R.mipmap.icon_rub);
                    tv_delete.setText("删除");
                    iv_edit.setImageResource(R.mipmap.icon_writeagain);
                    tv_edit.setText("编辑");
                } else if (order_detail_status == 1) {  // 有人抢单，等待确认
                    linear_delete.setOnClickListener(this);
                    linear_edit.setOnClickListener(this);
                    linear_delete.setVisibility(View.VISIBLE);
                    linear_edit.setVisibility(View.VISIBLE);
                    iv_delete.setImageResource(R.mipmap.icon_cancel);
                    tv_delete.setText("取消订单");
                    iv_edit.setImageResource(R.mipmap.icon_confirm);
                    tv_edit.setText("确认订单");
                } else if (order_detail_status==2) {  // 已确认
                    linear_delete.setOnClickListener(this);
                    linear_edit.setOnClickListener(this);
                    linear_delete.setVisibility(View.VISIBLE);
                    linear_edit.setVisibility(View.VISIBLE);
                    iv_delete.setImageResource(R.mipmap.icon_cancel);
                    tv_delete.setText("未完成");
                    iv_edit.setImageResource(R.mipmap.icon_confirm);
                    tv_edit.setText("已完成");
                } else if (order_detail_status==3) {  // 客户点击完成了发布人这里变成了待检查
                    linear_delete.setOnClickListener(this);
                    linear_edit.setOnClickListener(this);
                    linear_delete.setVisibility(View.VISIBLE);
                    linear_edit.setVisibility(View.VISIBLE);
                    iv_delete.setImageResource(R.mipmap.icon_cancel);
                    tv_delete.setText("未完成");
                    iv_edit.setImageResource(R.mipmap.icon_confirm);
                    tv_edit.setText("已完成");
                } else if (order_detail_status==4) {  // 整个过程交易完成 只显示删除
                    linear_delete.setOnClickListener(this);
                    linear_delete.setVisibility(View.VISIBLE);
                    linear_edit.setVisibility(View.GONE);
                    iv_delete.setImageResource(R.mipmap.icon_rub);
                    tv_delete.setText("删除");
                } else if (order_detail_status==5) {  // 取消了交易
                    linear_delete.setVisibility(View.VISIBLE);
                    linear_edit.setVisibility(View.VISIBLE);

                }
            } else if (publish_status == 2) {  // 审核不通过，删除和重新编辑
                linear_delete.setOnClickListener(this);
                linear_edit.setOnClickListener(this);
                linear_delete.setVisibility(View.VISIBLE);
                linear_edit.setVisibility(View.VISIBLE);
                iv_delete.setImageResource(R.mipmap.icon_rub);
                tv_delete.setText("删除");
                iv_edit.setImageResource(R.mipmap.icon_writeagain);
                tv_edit.setText("重新编辑");
            }
        }
    }

    @Override
    public void deleteRequireSuccess() {
        Intent data = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, position);
        data.putExtras(bundle);
        setResult(DELETE_CODE, data);
        finish();
    }

    @Override
    public void cancelRequireSuccess() {
        presenter.initData(getAccessToken(),requir_id,longitude,latitude);
    }

    @Override
    public void sureRequireSuccess() {
        presenter.initData(getAccessToken(),requir_id,longitude,latitude);
    }

    @Override
    public void onFinishRequireSuccess() {
        presenter.initData(getAccessToken(),requir_id,longitude,latitude);
    }

    @Override
    public void finishRequireSuccess() {
        presenter.initData(getAccessToken(),requir_id,longitude,latitude);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocClient!=null) {
            mLocClient.stop();
        }
    }
}
