package com.windmillsteward.jukutech.activity.newpage.home;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.web.CommonWebActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyDetailActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.VideoPlayActivity;
import com.windmillsteward.jukutech.activity.home.fragment.HeaderViewPagerFragment;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseDetailActivity;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.BusinessAuthenticationActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.HasBmYsYesPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.HasJiajiaoPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.HasZhaopinPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgUsePersonFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.HasLgTzgPublishedDetailsFragment;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.interfaces.OnUserAuthenListener;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.HomeBottomBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.manager.CommonActivityManager;
import com.windmillsteward.jukutech.utils.CalculateUtils;
import com.windmillsteward.jukutech.utils.DistanceUtils;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
import com.windmillsteward.jukutech.utils.MediaUtils;
import com.windmillsteward.jukutech.utils.NumberFormatUtil;
import com.windmillsteward.jukutech.utils.ResUtils;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.TimeUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 * 首页底部当地热门fragment
 */
public class HomeBottomFragment extends HeaderViewPagerFragment implements BDLocationListener {
    public static final String TAG = "HomeBottomFragment";

    public static final int TYPE_MEISHI = 4;
    public static final int TYPE_SMART = 3;
    public static final int TYPE_RENCAI = 2;
    public static final int TYPE_FANGWU = 1;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.lay_rl_root)
    RelativeLayout layRlRoot;

    private int type;
    private int page = 1;
    private HomeBottomBean data;
    private String longitude;
    private String latitude;
    private int layoutId;
    private String district = "";
    private String city = "";

    private LocationClient mLocClient;

    private boolean isFirstPost = true;

    @Override
    protected int getContentViewId() {
        return R.layout.layout_only_recyclerview_two;
    }

    /**
     * @param type 类型：4.美食餐厅 3.智慧生活 2.人才驿站 1.房屋信息
     * @return
     */
    public static HomeBottomFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        HomeBottomFragment fragment = new HomeBottomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        hidTitleView();

        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }
        initLocation();
    }


    public void refreshData() {
        page = 1;
        getData();
    }


    @Override
    protected void initData() {

        if (type == TYPE_MEISHI) {
            layoutId = R.layout.item_home_bottom_meishi;
        } else if (type == TYPE_SMART) {
            layoutId = R.layout.item_intelligent_family_two;
        } else if (type == TYPE_RENCAI) {
            layoutId = R.layout.item_recycler_talentinn_list_two;
        } else if (type == TYPE_FANGWU) {
            layoutId = R.layout.item_buyhouse_two;
        }

        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        //加载更多
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData();
            }
        }, recyclerView);

//        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);
        adapter.isUseEmpty(false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (list != null && list.size() != 0) {
                    HomeBottomBean.DataListBean.ListBean listBean = list.get(position);
                    if (listBean != null) {
                        if (type == TYPE_MEISHI) {
                            //这个不用跳转
                            if (listBean != null){
                                if (!TextUtils.isEmpty(listBean.getDesc_url())){
                                    Bundle bundle = new Bundle();
                                    bundle.putString(Define.INTENT_DATA, listBean.getDesc_url());
                                    startAtvDonFinish(CommonWebActivity.class, bundle);
                                }
                            }
                        } else if (type == TYPE_SMART) {
                            int require_id = listBean.getRequire_id();
                            Bundle bundle = new Bundle();
                            bundle.putInt(IntelligentFamilyDetailActivity.DETAIL_ID, require_id);
                            startAtvDonFinish(IntelligentFamilyDetailActivity.class, bundle);
                        } else if (type == TYPE_RENCAI) {
                            int type = listBean.getType();
                            Bundle bundle;
                            switch (type) {
                                case 1://劳务中介特种工
                                    bundle = new Bundle();
                                    bundle.putInt("recruitment_id", listBean.getRelate_id());
                                    bundle.putInt("roleType", PageConfig.TYPE_ZHONGJIE);
                                    bundle.putInt("showAddress", 0);
                                    startManagerActivity(CommonActivityManager.class, HasLgTzgPublishedDetailsFragment.TAG, bundle);
                                    break;
                                case 2://求职招聘
                                    bundle = new Bundle();
                                    bundle.putInt("job_id", listBean.getRelate_id());
                                    bundle.putInt("showAddress", 0);
                                    startManagerActivity(CommonActivityManager.class, HasZhaopinPublishedDetailsFragment.TAG, bundle);
                                    break;
                                case 3://保姆月嫂育儿嫂
                                    bundle = new Bundle();
                                    int require_type = listBean.getRequire_type();
                                    bundle.putInt("recruitment_id", listBean.getRelate_id());
                                    if (require_type == 1) {//保姆
                                        bundle.putInt("roleType", PageConfig.TYPE_BAOMU);
                                    } else if (require_type == 2) {//育儿嫂
                                        bundle.putInt("roleType", PageConfig.TYPE_YUERSAO);
                                    } else if (require_type == 3) {//月嫂
                                        bundle.putInt("roleType", PageConfig.TYPE_YUESAO);
                                    }
                                    bundle.putInt("showAddress", 0);
                                    startManagerActivity(CommonActivityManager.class, HasBmYsYesPublishedDetailsFragment.TAG, bundle);
                                    break;
                                case 4://家教
                                    bundle = new Bundle();
                                    bundle.putInt("look_for_tutor_id", listBean.getRelate_id());
                                    bundle.putInt("showAddress", 0);
                                    startManagerActivity(CommonActivityManager.class, HasJiajiaoPublishedDetailsFragment.TAG, bundle);
                                    break;
                                case 5://钟点工
                                    bundle = new Bundle();
                                    bundle.putInt("lookfor_bell_worker_id", listBean.getRelate_id());
                                    bundle.putInt("showAddress", 0);
                                    startManagerActivity(CommonActivityManager.class, ZhongdgUsePersonFragment.TAG, bundle);
                                    break;
                            }
                        } else if (type == TYPE_FANGWU) {
                            int house_id = listBean.getHouse_id();
                            Bundle bundle = new Bundle();
                            bundle.putInt(HouseDetailActivity.DETAIL_ID, house_id);
                            startAtvDonFinish(HouseDetailActivity.class, bundle);
                        }
                    }
                }
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                if (isLogin()) {
                    final HomeBottomBean.DataListBean.ListBean listBean = list.get(position);
                    checkUserAuthen(new OnUserAuthenListener() {
                        @Override
                        public void isAuthen() {
                            getOrder(listBean);//抢单
                        }

                        @Override
                        public void isNotAuthen() {
                            final BaseDialog baseDialog = new BaseDialog(getActivity());
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

                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
            }
        });

//        if (!isFirstPost){
//            getData();
//        }
//        isFirstPost = false;
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        mLocClient = new LocationClient(getContext());
        mLocClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(150); // 大于1000才循环
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    private void getData() {
        String longLat = KV.get(Define.CURR_LONGLAT_ADDRESS, "");
        if (!StringUtil.isEmpty(longLat)) {
            longitude = longLat.split(",")[0];
            latitude = longLat.split(",")[1];
        }
        int second_area_id = Hawk.get(Define.CURR_CITY_ID, 0);
        int third_area_id = Hawk.get(Define.CURR_CITY_THIRD_ID, 0);
        addCall(new NetUtil()
                .setUrl(APIS.URL_HOME_BOTTOM_DATA)
                .addParams("page", "" + page)
                .addParams("page_count", "" + 10)
                .addParams("type", "" + type)
                .addParams("second_area_id", second_area_id + "")
                .addParams("third_area_id", third_area_id + "")
                .addParams("longitude", longitude + "")
                .addParams("latitude", latitude + "")
                .setCallBackData(new BaseNewNetModelimpl<HomeBottomBean>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        if (adapter != null){
                            adapter.loadMoreFail();
                        }
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<HomeBottomBean> respnse, String source) {
                        showContentView();
                        data = respnse.getData();
                        if (data != null) {
                            HomeBottomBean.DataListBean dataList = data.getDataList();
                            if (dataList != null) {
                                if (dataList.isFirstPage()) {
                                    list.clear();
                                }
                                if (dataList.getList() != null) {
                                    for (HomeBottomBean.DataListBean.ListBean listBean : dataList.getList()) {
                                        listBean.setType(type);
                                        list.add(listBean);
                                    }
                                }
                                if (dataList.isLastPage()) {
                                    adapter.loadMoreEnd();
                                } else {
                                    adapter.loadMoreComplete();
                                }
                                adapter.notifyDataSetChanged();
                            }
                            page++;
                        }

                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<HomeBottomBean>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    private void getOrder(final HomeBottomBean.DataListBean.ListBean listBean) {
        addCall(new NetUtil()
                .addParams("require_id", listBean.getRequire_id() + "")
                .addParams("area_name", city + district)
                .addParams("longitude", longitude)
                .addParams("latitude", latitude)
                .setUrl(APIS.URL_ROB_REQUIRE)
                .setCallBackData(new BaseNewNetModelimpl<PublicResultModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, final BaseResultInfo<PublicResultModel> respnse, String source) {
                        dismiss();
                        if (respnse != null) {
                            final PublicResultModel resultModel = (PublicResultModel) respnse.getData();
//                            showTips(respnse.getMsg());
//                            getData();
//                            SmartLifeDetailsActivity.go(getActivity(), listBean.getRequire_id(), longitude, latitude);

                            final BaseDialog baseDialog = new BaseDialog(getActivity());
                            baseDialog.showTwoButton("提示", "是否花取"+resultModel.getOrder_price()+"元,查看并接取此用户订单", "确定", "取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    baseDialog.dismiss();
                                    NewPayActivity.go(getActivity(),
                                            CommonPayPresenter.TYPE_ZHIHUI_SHENGHUOXINXI, resultModel.getRelate_id(),
                                            resultModel.getOrder_price() + "",
                                            resultModel.getOrder_name(), NewPayActivity.CAN_USE_COUPON, respnse.getMsg());
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    baseDialog.dismiss();
                                }
                            });


                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<PublicResultModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }


    @Override
    protected void refreshPageData() {
        list.clear();
        page = 1;
        getData();
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        if (location == null) {
            return;
        }
        district = location.getDistrict();
        city = location.getCity();
        mLocClient.unRegisterLocationListener(this);
    }

    private RecyclerViewAdapter adapter;
    private List<HomeBottomBean.DataListBean.ListBean> list;

    class RecyclerViewAdapter extends BaseQuickAdapter<HomeBottomBean.DataListBean.ListBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<HomeBottomBean.DataListBean.ListBean> data) {
            super(layoutId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final HomeBottomBean.DataListBean.ListBean item) {
            if (item.getItemType() == TYPE_MEISHI) {
                GlideUtil.show(getActivity(), item.getPic_url(), (ImageView) helper.getView(R.id.iv_pic));
                helper.setText(R.id.tv_title, item.getService_name())
                        .setText(R.id.tv_distance, "距您" + DistanceUtils.getFormatDistance(item.getDistance()))
                        .setText(R.id.tv_price, (new SpanUtils()
                                .append("人均：")
                                .setForegroundColor(ResUtils.getColor(R.color.color_939395))
                                .append(" ¥" + CalculateUtils.round(item.getPrice(), 2))
                                .create()));
                final String longitude = item.getLongitude();
                final String latitude = item.getLatitude();
                helper.getView(R.id.tv_distance).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            if (StringUtil.isAllNotEmpty(longitude, latitude)) {
                                MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(longitude), Double.parseDouble(latitude), "");
                            }
                    }
                });
                final String contact_tel = item.getContact_tel();
                helper.getView(R.id.iv_phone).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(contact_tel)){
                            final BaseDialog dialog  = new BaseDialog(getActivity());
                            dialog.showTwoButton("提示", "是否拨打以下电话\n" + contact_tel, "确定", "取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    String[] perms = {android.Manifest.permission.CALL_PHONE};
                                    if (checkPermission(perms)) {
                                        PhoneUtils.dial(contact_tel);
                                    }

                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });

                        }
                    }
                });
                List<String> descriptionsList = item.getDescriptions();
                FlowLayout fl_content = (FlowLayout) helper.getView(R.id.fl_content);
                fl_content.removeAllViews();
                if (descriptionsList != null) {
                   for (int i = 0;i <descriptionsList.size();i++){
                       if (i >2){
                           break;
                       }
                       TextView view = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.type_text_shape, fl_content, false);
                       view.setText(descriptionsList.get(i));
                       fl_content.addView(view);
                   }
                }
            } else if (item.getItemType() == TYPE_SMART) {

                if (helper.getAdapterPosition() == 0) {
                    if (helper.getView(R.id.view_line) != null) {
                        helper.getView(R.id.view_line).setVisibility(View.GONE);
                    }
                }

                helper.setText(R.id.tv_name, item.getClass_name())
                        .setText(R.id.tv_price, "市场指导价￥" + item.getPrice())
                        .setText(R.id.tv_desc, item.getDescription())
                        .setText(R.id.tv_location, item.getRequire_area())
                        .setText(R.id.tv_range, "距离我" + DistanceUtils.getFormatDistance(item.getDistance()))
                        .addOnClickListener(R.id.tv_get_order);
                if (item.getIs_ad() == 0){//不是广告发布，显示抢单按钮
                    helper.setVisible(R.id.tv_get_order,true );
                }else {
                    helper.setVisible(R.id.tv_get_order,false );
                }
                List<String> pic_urls = item.getPic_urls();
                String video_urls = item.getVideo_url();

                final String videoUrlsTemp = video_urls;
                List<String> totalList = new ArrayList<>();
                if (!TextUtils.isEmpty(video_urls)) {
                    totalList.add(video_urls);
                }
                if (pic_urls != null && pic_urls.size() > 0) {
                    totalList.addAll(pic_urls);
                }
                // 处理图片和视频
                List<ImageView> views = new ArrayList<>();
                views.add(((ImageView) helper.getView(R.id.iv_image1)));
                views.add(((ImageView) helper.getView(R.id.iv_image2)));
                views.add(((ImageView) helper.getView(R.id.iv_image3)));
                views.add(((ImageView) helper.getView(R.id.iv_image4)));
                for (ImageView imageView : views) {
                    imageView.setVisibility(View.INVISIBLE);
                }

                int picLength = 0;
                if (totalList != null) {
                    if (totalList.size() > 4) {//图片长度大于4，则只取4
                        picLength = 4;
                    } else {//图片长度小于4，则取pic_urls.size()
                        picLength = totalList.size();
                    }
                }
                if (!TextUtils.isEmpty(video_urls)) {
                    helper.getView(R.id.iv_image1).setVisibility(View.VISIBLE);
                    helper.getView(R.id.iv_play).setVisibility(View.VISIBLE);
                    helper.getView(R.id.lay_ll_pic).setVisibility(View.VISIBLE);
                    final ImageView imageView = (ImageView) helper.getView(R.id.iv_image1);

                    MediaUtils.getImageForVideo(video_urls, new MediaUtils.OnLoadVideoImageListener() {
                        @Override
                        public void onLoadImage(File file) {
                            if (file == null) {
                                return;
                            }
                            final ContentResolver contentResolver = mContext.getContentResolver();
                            if (contentResolver != null) {
                                Uri uri = Uri.fromFile(file);
                                try {
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri);
                                    imageView.setImageBitmap(bitmap);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    for (int i = 1; i < picLength; i++) {
                        views.get(i).setVisibility(View.VISIBLE);
                        GlideUtil.show(getActivity(), pic_urls.get(i - 1), views.get(i), R.mipmap.icon_default_pic);
//                        x.image().bind(views.get(i), pic_urls.get(i-1), ImageUtils.defaulHeadOptionsTwo());
                    }
                    helper.getView(R.id.iv_image1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putString(VideoPlayActivity.VIDEO_URL, videoUrlsTemp);
                            Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
                            intent.putExtras(bundle);
                            getActivity().startActivity(intent, bundle);
                        }
                    });
                } else {
                    helper.getView(R.id.iv_play).setVisibility(View.GONE);
                    if (pic_urls != null) {
                        if (pic_urls.size() == 0) {
                            helper.getView(R.id.lay_ll_pic).setVisibility(View.GONE);
                        } else {
                            helper.getView(R.id.lay_ll_pic).setVisibility(View.VISIBLE);
                            for (int i = 0; i < picLength; i++) {
                                views.get(i).setVisibility(View.VISIBLE);
                                GlideUtil.show(getActivity(), pic_urls.get(i), views.get(i), R.mipmap.icon_default_pic);
                            }
                        }
                    }
                }

                GlideUtil.show(getActivity(), item.getClass_image(), (CircleImageView) helper.getView(R.id.civ_header), R.mipmap.icon_default_pic);
            } else if (item.getItemType() == TYPE_RENCAI) {
                if (helper.getAdapterPosition() == 0) {
                    if (helper.getView(R.id.view_line) != null) {
                        helper.getView(R.id.view_line).setVisibility(View.GONE);
                    }
                }
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_address, item.getArea_name());
                helper.setText(R.id.tv_time, TimeUtils.formatMillToYYYYMMDD(item.getAdd_time() * 1000));
            } else if (item.getItemType() == TYPE_FANGWU) {
                if (helper.getAdapterPosition() == 0) {
                    if (helper.getView(R.id.view_line) != null) {
                        helper.getView(R.id.view_line).setVisibility(View.GONE);
                    }
                }
                double total_price = item.getTotal_price();
                if (total_price < 10000 ){
                    helper.setText(R.id.tv_price, "￥" + total_price + "元");
                }else{
                    helper.setText(R.id.tv_price, "￥" + NumberFormatUtil.save2Point(total_price/10000) + "万元");
                }
                helper.setText(R.id.tv_name, item.getHouse_type_name())
                        .setText(R.id.tv_desc, item.getTitle())
                        .setText(R.id.tv_location, item.getHouse_third_name() + " " + item.getHouse_fourth_name());

                List<String> pic_urls = item.getPic_urls();
                List<String> video_urls = new ArrayList<>();
                if (!TextUtils.isEmpty(item.getVideo_url())) {
                    video_urls.add(item.getVideo_url());
                }
                // 处理图片和视频
                List<ImageView> views = new ArrayList<>();
                views.add(((ImageView) helper.getView(R.id.iv_image1)));
                views.add(((ImageView) helper.getView(R.id.iv_image2)));
                views.add(((ImageView) helper.getView(R.id.iv_image3)));
                views.add(((ImageView) helper.getView(R.id.iv_image4)));
                for (ImageView imageView : views) {
                    imageView.setVisibility(View.INVISIBLE);
                }
                for (int i1 = 0; i1 < views.size(); i1++) {
                    if (video_urls != null && video_urls.size() > 0) {
                        if (i1 == 0) {
                            views.get(0).setVisibility(View.VISIBLE);
                            GlideUtil.show(getActivity(), item.getVideo_cover(), views.get(0), R.mipmap.icon_default_pic);
                        }
                        if (i1 > 0 && pic_urls != null && pic_urls.size() > i1 - 1) {
                            views.get(i1).setVisibility(View.VISIBLE);
                            GlideUtil.show(getActivity(), pic_urls.get(i1 - 1), views.get(i1), R.mipmap.icon_default_pic);
                        }
                    } else {
                        if (pic_urls != null && pic_urls.size() > i1) {
                            views.get(i1).setVisibility(View.VISIBLE);
                            GlideUtil.show(getActivity(), pic_urls.get(i1), views.get(i1), R.mipmap.icon_default_pic);
                        }
                    }
                }
                ImageView iv_play = helper.getView(R.id.iv_play);
                if (item.getVideo_cover() != null && item.getVideo_cover().length() > 0) {
                    iv_play.setVisibility(View.VISIBLE);
                } else {
                    iv_play.setVisibility(View.GONE);
                }
                GlideUtil.show(getActivity(), item.getUser_avatar_url(), (CircleImageView) helper.getView(R.id.civ_header), R.drawable.icon_head_male);
            }
        }
    }

    @Override
    public View getScrollableView() {
        return recyclerView;
    }

}
