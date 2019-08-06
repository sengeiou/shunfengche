package com.windmillsteward.jukutech.activity.home.specialty.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.PhotoViewActivity;
import com.windmillsteward.jukutech.activity.home.specialty.presenter.SpecialtyDetailActivityPresenter;
import com.windmillsteward.jukutech.activity.shoppingcart.activity.AddOrderListActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.BeforeAddOrderRequest;
import com.windmillsteward.jukutech.bean.SpecialtyDetailBean;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.customview.flowlayout.TagAdapter;
import com.windmillsteward.jukutech.customview.flowlayout.TagFlowLayout;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.CountDownUtils;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;
import com.windmillsteward.jukutech.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * 描述：秒杀列表--详情页
 * author:lc
 * 2018-07-09
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SeckillDetailActivity extends BaseActivity implements SpecialtyDetailActivityView, View.OnClickListener, CountDownUtils.OnCountDownUpdateListener {


    private LinearLayout linear_root;
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView toolbar_tv_right;
    private FlyBanner flyBanner;
    private TextView tv_title;
    private TextView tv_price;
    private TextView tv_commodity_carriage;
    private TextView tv_inventory;// 库存
    private TextView tv_area;
    private TextView tv_commodity_model;
    private LinearLayout linear_commodity_model;
    private ImageView iv_store_thumbnail;
    private TextView tv_store_name;
    private TextView tv_goods_class;
    private TextView tv_join_store;
    private WebView webView;
    private ImageView iv_collection;
    private TextView tv_collection;
    private TextView tv_buy_now;
    private EasyPopup easyPopup;
    private TextView tv_commodity_price;
    private TextView tv_commodity_inventory;
    private ImageView iv_pic;
    private ImageView iv_close;
    private TagFlowLayout commodity_model_name;
    private TextView iv_add;
    private TextView tv_number;
    private TextView iv_reduce;
    private TextView tv_sure;
    private TextView tv_add_to_car;
    private TextView tv_add_buy;
    private LinearLayout linear_add;

    private SpecialtyDetailActivityPresenter presenter;
    private int commodity_id;
    private SpecialtyDetailBean detailBean;
    // 当前选中的型号
    private SpecialtyDetailBean.CommodityModelListBean curr_model;
    // 数量
    private int num;
    // 点击的type 0 选择类型 1 加入购物车 2 立即购买
    private int currClickType;

    private TextView tv_xgsl;// 每人限购数量
    private TextView tv_count_down_title;// 倒计时标题
    private ImageView iv_count_down_icon;// 倒计时左边的图标
    private TextView tv_count_down;// 倒计时
    private RelativeLayout rl_count_down_lay;// 倒计时布局

    private CountDownUtils countDownUtils;
    private boolean is_seckill_start;// 是否开始秒杀 true开始秒杀  false还没开始秒杀
    private int restriction_num;// 每人限购数量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seckill_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            commodity_id = extras.getInt(Define.INTENT_DATA);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        initFlyBanner();
        initPopup();
        initWebView();
        presenter = new SpecialtyDetailActivityPresenter(this);
        presenter.initData(getAccessToken(), commodity_id);
    }

    private void initPopup() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.view_seckill_goods_moel, null);
        tv_commodity_price = (TextView) inflate.findViewById(R.id.tv_commodity_price);
        tv_commodity_inventory = (TextView) inflate.findViewById(R.id.tv_commodity_inventory);
        iv_pic = (ImageView) inflate.findViewById(R.id.iv_pic);
        iv_close = (ImageView) inflate.findViewById(R.id.iv_close);
        commodity_model_name = (TagFlowLayout) inflate.findViewById(R.id.commodity_model_name);
        iv_add = (TextView) inflate.findViewById(R.id.iv_add);
        tv_number = (TextView) inflate.findViewById(R.id.tv_number);
        iv_reduce = (TextView) inflate.findViewById(R.id.iv_reduce);
        tv_sure = (TextView) inflate.findViewById(R.id.tv_sure);
        tv_add_to_car = (TextView) inflate.findViewById(R.id.tv_add_to_car);
        tv_add_buy = (TextView) inflate.findViewById(R.id.tv_add_buy);
        linear_add = (LinearLayout) inflate.findViewById(R.id.linear_add);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                easyPopup.dismiss();
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = tv_number.getText().toString().trim();
                int i = Integer.valueOf(trim) + 1;
                if (i == 0) {
                    i = 1;
                }
                tv_number.setText(String.valueOf(i));
            }
        });
        iv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = tv_number.getText().toString().trim();
                int i = Integer.valueOf(trim) - 1;
                if (i == 0) {
                    i = 1;
                }
                tv_number.setText(String.valueOf(i));
            }
        });
        tv_add_to_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (detailBean == null) {
                    return;
                }
                Set<Integer> selectedList = commodity_model_name.getSelectedList();
                List<SpecialtyDetailBean.CommodityModelListBean> model_list = detailBean.getCommodity_model_list();
                for (Integer integer : selectedList) {
                    if (integer < model_list.size()) {
                        curr_model = model_list.get(integer);
                        num = Integer.valueOf(tv_number.getText().toString().trim());
                    }
                }

                if (curr_model != null) {
                    tv_commodity_model.setText(curr_model.getCommodity_model_name() + "  数量  " + num);
                }
                presenter.addToCart(getAccessToken(), detailBean.getCommodity_id(), num, curr_model.getCommodity_model_id(), detailBean.getStore_id());

                easyPopup.dismiss();
            }
        });
        tv_add_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (detailBean == null) {
                    return;
                }
                Set<Integer> selectedList = commodity_model_name.getSelectedList();
                List<SpecialtyDetailBean.CommodityModelListBean> model_list = detailBean.getCommodity_model_list();
                for (Integer integer : selectedList) {
                    if (integer < model_list.size()) {
                        curr_model = model_list.get(integer);
                        num = Integer.valueOf(tv_number.getText().toString().trim());
                    }
                }

                if (num > restriction_num) {// 购买数量大于每人限购数量
                    showTips("不能超过限购数量", 1);
                    return;
                }

                if (curr_model != null) {
                    tv_commodity_model.setText(curr_model.getCommodity_model_name() + "  数量  " + num);
                }

                if (curr_model != null) {
                    BeforeAddOrderRequest request = new BeforeAddOrderRequest();
                    request.setCart_id(0);
                    request.setAccess_token(getAccessToken());
                    request.setAddress_id(0);
                    ArrayList<BeforeAddOrderRequest.CommodityListBean> commodity_list = new ArrayList<>();
                    BeforeAddOrderRequest.CommodityListBean bean = new BeforeAddOrderRequest.CommodityListBean();
                    bean.setCart_commodity_id(0);
                    bean.setCommodity_id(detailBean.getCommodity_id());
                    bean.setCommodity_model_id(curr_model.getCommodity_model_id());
                    bean.setCommodity_num(num);
                    bean.setStore_id(detailBean.getStore_id());
                    commodity_list.add(bean);
                    request.setListBeans(commodity_list);
                    Bundle bundle = new Bundle();
                    bundle.putString(Define.INTENT_DATA, JSON.toJSONString(request));
                    startAtvDonFinish(AddOrderListActivity.class, bundle);
                }
                easyPopup.dismiss();
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (detailBean == null) {
                    return;
                }
                Set<Integer> selectedList = commodity_model_name.getSelectedList();
                List<SpecialtyDetailBean.CommodityModelListBean> model_list = detailBean.getCommodity_model_list();
                for (Integer integer : selectedList) {
                    if (integer < model_list.size()) {
                        curr_model = model_list.get(integer);
                        num = Integer.valueOf(tv_number.getText().toString().trim());
                    }
                }

                if (num > restriction_num) {// 购买数量大于每人限购数量
                    showTips("不能超过限购数量", 1);
                    return;
                }

                if (curr_model != null) {
                    tv_commodity_model.setText(curr_model.getCommodity_model_name() + "  数量  " + num);
                }

                if (currClickType == 1) {
                    if (curr_model != null) {
                        presenter.addToCart(getAccessToken(), detailBean.getCommodity_id(), num, curr_model.getCommodity_model_id(), detailBean.getStore_id());
                    }
                } else if (currClickType == 2) {
                    if (curr_model != null) {
                        BeforeAddOrderRequest request = new BeforeAddOrderRequest();
                        request.setCart_id(0);
                        request.setAccess_token(getAccessToken());
                        request.setAddress_id(0);
                        ArrayList<BeforeAddOrderRequest.CommodityListBean> commodity_list = new ArrayList<>();
                        BeforeAddOrderRequest.CommodityListBean bean = new BeforeAddOrderRequest.CommodityListBean();
                        bean.setCart_commodity_id(0);
                        bean.setCommodity_id(detailBean.getCommodity_id());
                        bean.setCommodity_model_id(curr_model.getCommodity_model_id());
                        bean.setCommodity_num(num);
                        bean.setStore_id(detailBean.getStore_id());
                        commodity_list.add(bean);
                        request.setListBeans(commodity_list);
                        Bundle bundle = new Bundle();
                        bundle.putString(Define.INTENT_DATA, JSON.toJSONString(request));
                        startAtvDonFinish(AddOrderListActivity.class, bundle);
                    }
                }

                easyPopup.dismiss();
            }
        });

        easyPopup = new EasyPopup(this)
                .setContentView(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.4f)
                //变暗的背景颜色
                .setDimColor(Color.BLACK)
                //指定任意 ViewGroup 背景变暗
                .setDimView(linear_root)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                    }
                })
                .createPopup();
    }

    private void initFlyBanner() {

        ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH(this, GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH - GraphicUtil.dp2px(this, 0);
        layoutParams.height = 575 * layoutParams.width / 1080;
        flyBanner.setLayoutParams(layoutParams);

        flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (detailBean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(PhotoViewActivity.PIC_URLS, (ArrayList<String>) detailBean.getCommodity_image());
                    bundle.putInt(PhotoViewActivity.CURR_POSITION, position);
                    startAtvDonFinish(PhotoViewActivity.class, bundle);
                }
            }
        });
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("详情");
    }


    private void initView() {
        linear_root = (LinearLayout) findViewById(R.id.linear_root);
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        flyBanner = (FlyBanner) findViewById(R.id.flyBanner);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_commodity_carriage = (TextView) findViewById(R.id.tv_commodity_carriage);
        tv_inventory = (TextView) findViewById(R.id.tv_inventory);
        tv_area = (TextView) findViewById(R.id.tv_area);
        tv_commodity_model = (TextView) findViewById(R.id.tv_commodity_model);
        linear_commodity_model = (LinearLayout) findViewById(R.id.linear_commodity_model);
        iv_store_thumbnail = (ImageView) findViewById(R.id.iv_store_thumbnail);
        tv_store_name = (TextView) findViewById(R.id.tv_store_name);
        tv_goods_class = (TextView) findViewById(R.id.tv_goods_class);
        tv_join_store = (TextView) findViewById(R.id.tv_join_store);
        webView = (WebView) findViewById(R.id.webView);
        iv_collection = (ImageView) findViewById(R.id.iv_collection);
        tv_collection = (TextView) findViewById(R.id.tv_collection);
        tv_buy_now = (TextView) findViewById(R.id.tv_buy_now);

        tv_commodity_model.setOnClickListener(this);
        tv_goods_class.setOnClickListener(this);
        tv_join_store.setOnClickListener(this);
        tv_buy_now.setOnClickListener(this);
        tv_xgsl = (TextView) findViewById(R.id.tv_xgsl);
        tv_count_down_title = (TextView) findViewById(R.id.tv_count_down_title);
        tv_count_down_title.setOnClickListener(this);
        iv_count_down_icon = (ImageView) findViewById(R.id.iv_count_down_icon);
        iv_count_down_icon.setOnClickListener(this);
        tv_count_down = (TextView) findViewById(R.id.tv_count_down);
        tv_count_down.setOnClickListener(this);
        rl_count_down_lay = (RelativeLayout) findViewById(R.id.rl_count_down_lay);
        rl_count_down_lay.setOnClickListener(this);
    }

    private void initWebView() {
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSetting.setUseWideViewPort(false);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(false);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
    }

//    private int a = 0;

    @Override
    public void initDataSuccess(SpecialtyDetailBean bean) {
        detailBean = bean;
        List<String> pic_urls = bean.getCommodity_image();
        if (pic_urls != null && pic_urls.size() > 0) {
            flyBanner.setImagesUrl(pic_urls);
        }
        tv_title.setText(bean.getCommodity_title());
        String commodity_peak_price = bean.getCommodity_peak_price();
        if (TextUtils.isEmpty(commodity_peak_price) || TextUtils.equals(commodity_peak_price, "0")) {
            tv_price.setText("￥" + commodity_peak_price);
        } else {
            tv_price.setText("￥" + bean.getCommodity_reserve_price() + "-￥" + commodity_peak_price);
        }
        tv_commodity_carriage.setText(bean.getCommodity_carriage() + "元");
        tv_inventory.setText(bean.getCommodity_inventory() + "");
        tv_area.setText(bean.getSecond_area_name() + bean.getThird_area_name());
        GlideUtil.show(this, bean.getStore_thumbnail(), iv_store_thumbnail, R.mipmap.icon_default_pic, R.mipmap.icon_default_pic);
        tv_store_name.setText(bean.getStore_name());
//        tv_detail.setText(Html.fromHtml(bean.getCommodity_detail()));
        String content = bean.getCommodity_detail();//后台接口返回的需要在WebView中显示的HTML代码
        content = content.replace("<img", "<img style=\"display:        ;max-width:100%;\"");
        webView.loadData(content, "text/html;charset=utf-8", "UTF-8");

        restriction_num = bean.getRestriction_num();
        tv_xgsl.setText(restriction_num + "");

        // 就两种状态
        // 一种是商品等待秒杀
        // 一种是商品正在秒杀
        int start_time = bean.getStart_time();//活动开始时间，注：如果是秒杀商品
        int end_time = bean.getEnd_time();//活动结束时间，注：如果是秒杀商品
        long currentTimesTamp = TimeUtils.getCurrentTimesTamp();
//        if (a == 0) {
//            a++;
//            currentTimesTamp = 1531227570;
//        } else if (a == 1) {
//            a++;
//            currentTimesTamp = 1531231170;
//        } else {
//            currentTimesTamp = 1531231290;
//        }
        if (currentTimesTamp < start_time) {// 如果当前时间小于开始时间就是 即将开始 否则就是 正在抢购
            startCountDown((int) ((start_time - currentTimesTamp)));
            tv_count_down_title.setText("秒杀开始");
            tv_count_down_title.setTextColor(getResources().getColor(R.color.color_text_014));
            tv_count_down.setTextColor(getResources().getColor(R.color.color_text_014));
            iv_count_down_icon.setImageResource(R.drawable.icon_time_r);
            tv_buy_now.setText("等待开始");
            tv_buy_now.setTextColor(getResources().getColor(R.color.color_white));
            tv_buy_now.setBackgroundColor(getResources().getColor(R.color.color_939395));
            tv_buy_now.setClickable(false);
            rl_count_down_lay.setBackgroundColor(getResources().getColor(R.color.color_fff3e5));
            is_seckill_start = false;
        } else {
            startCountDown((int) ((end_time - currentTimesTamp)));
            tv_count_down_title.setText("秒杀结束");
            tv_count_down_title.setTextColor(getResources().getColor(R.color.color_text_491));
            tv_count_down.setTextColor(getResources().getColor(R.color.color_text_491));
            iv_count_down_icon.setImageResource(R.drawable.icon_time_g);
            tv_buy_now.setText("立即秒杀");
            tv_buy_now.setTextColor(getResources().getColor(R.color.color_white));
            tv_buy_now.setBackgroundColor(getResources().getColor(R.color.color_text_014));
            tv_buy_now.setClickable(true);
            rl_count_down_lay.setBackgroundColor(getResources().getColor(R.color.color_text_9ec));
            is_seckill_start = true;
        }
    }

    private void startCountDown(int count) {
        if (countDownUtils == null) {
            countDownUtils = new CountDownUtils(count, 1, 1);
            countDownUtils.setOnCountDownUpdateListener(this);
        }
        countDownUtils.startCountDown();
    }

    @Override
    public void countDownUpdate(final boolean isEnd, final int number) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_count_down.setText("倒计时: " + String.format(Locale.getDefault(), TimeUtils.getLeftTime(number)));
                if (isEnd) {
                    countDownUtils.stopCountDown();
                    if (is_seckill_start) {// 秒杀活动结束把立即购买按钮至灰
                        tv_buy_now.setText("秒杀结束");
                        tv_buy_now.setTextColor(getResources().getColor(R.color.color_white));
                        tv_buy_now.setBackgroundColor(getResources().getColor(R.color.color_939395));
                        tv_buy_now.setClickable(false);
                        tv_count_down.setText("倒计时: 0小时 0分 0秒");
                    } else {// 刷新页面数据
                        presenter.initData(getAccessToken(), commodity_id);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownUtils != null) {
            countDownUtils.stopCountDown();
        }
    }

    @Override
    public void collectSuccess() {
    }

    @Override
    public void cancelCollectSuccess() {
    }

    @Override
    public void collectStoreSuccess() {
    }

    @Override
    public void cancelcollectStoreSuccess() {
    }

    @Override
    public void addToCarSuccess() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commodity_model:
                currClickType = 0;
                linear_add.setVisibility(View.VISIBLE);
                tv_sure.setVisibility(View.GONE);
                if (detailBean != null)
                    setData(detailBean.getCommodity_model_list());
                break;
            case R.id.tv_goods_class:
                if (detailBean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Define.INTENT_DATA, detailBean.getCommodity_id());
                    startAtvDonFinish(SpecialtyClassListActivity.class, bundle);
                }
                break;
            case R.id.tv_join_store:
                if (detailBean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Define.INTENT_DATA, detailBean.getStore_id());
                    bundle.putString(Define.INTENT_DATA_TWO, detailBean.getStore_name());
                    startAtvDonFinish(StoreGoodsListActivity.class, bundle);
                }
                break;
            case R.id.tv_buy_now:
                currClickType = 2;
                linear_add.setVisibility(View.GONE);
                tv_sure.setVisibility(View.VISIBLE);
                if (detailBean != null) {
                    setData(detailBean.getCommodity_model_list());
                }
                break;
            case R.id.toolbar_iv_right:// 跳转去购物车
                startAtvDonFinish(ShopCartActivity.class);
                break;
        }
    }

    public void setData(final List<SpecialtyDetailBean.CommodityModelListBean> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        SpecialtyDetailBean.CommodityModelListBean bean = data.get(0);
        tv_commodity_price.setText("￥" + bean.getCommodity_price());
        tv_commodity_inventory.setText("库存" + bean.getCommodity_inventory());
        GlideUtil.show(this, bean.getCommodity_model_image(), iv_pic, R.mipmap.icon_default_pic, R.mipmap.icon_default_pic);
        final LayoutInflater inflater = LayoutInflater.from(this);
        TagAdapter<SpecialtyDetailBean.CommodityModelListBean> adapter = new TagAdapter<SpecialtyDetailBean.CommodityModelListBean>(data) {
            @Override
            public View getView(FlowLayout parent, int position, SpecialtyDetailBean.CommodityModelListBean o) {
                TextView view = (TextView) inflater.inflate(R.layout.item_goods_model_popup_bg, parent, false);
                view.setText(o.getCommodity_model_name());
                return view;
            }
        };
        commodity_model_name.setAdapter(adapter);
        adapter.setSelectedList(0);
        commodity_model_name.setMaxSelectCount(1);
        commodity_model_name.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if (selectPosSet != null && selectPosSet.size() > 0) {
                    for (Integer integer : selectPosSet) {
                        if (integer >= 0 && integer < data.size()) {
                            SpecialtyDetailBean.CommodityModelListBean bean1 = data.get(integer);
                            tv_commodity_price.setText("￥" + bean1.getCommodity_price());
                            tv_commodity_inventory.setText("库存" + bean1.getCommodity_inventory());
                        }
                    }
                }
            }
        });
        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        easyPopup.showAtAnchorView(parent, VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
    }
}
