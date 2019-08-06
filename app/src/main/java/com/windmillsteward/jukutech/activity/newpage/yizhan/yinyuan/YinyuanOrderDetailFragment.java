package com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.VideoPlayActivity;
import com.windmillsteward.jukutech.activity.newpage.model.GetPhoneModel;
import com.windmillsteward.jukutech.activity.newpage.model.QiuZhiYingPinOrderDetailsModel;
import com.windmillsteward.jukutech.activity.newpage.model.YinyuanOrderDetailModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.VoiceUtils;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuZhiYingPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.ZhaopinPositionDetailsFragment;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.manager.CommonActivityManager;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
import com.windmillsteward.jukutech.utils.MediaUtils;
import com.windmillsteward.jukutech.utils.ResUtils;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class YinyuanOrderDetailFragment extends BaseInitFragment implements View.OnClickListener {

    public static final String TAG = "YinyuanOrderDetailFragment";

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;

    private LinearLayout lay_ll_header;
    private LinearLayout lay_ll_tips;
    private TextView tv_name_and_age;
    private TextView tv_diqu;
    private TextView tv_edu;
    private TextView tv_gangwei;
    private TextView tv_height;
    private TextView tvPipei;
    private ImageView ivPhone;
    private ImageView ivAvatar;

    private TextView tv_zhaopin_selfinfo_open_status;
    private LinearLayout lay_ll_zhaopin_selfinfo;
    private LinearLayout lay_ll_zhaopin_all_info;
    private TextView tv_order_sn;
    private TextView tv_zhaopin_selfinfo_name;
    private TextView tv_zhaopin_selfinfo_sex;
    private TextView tv_zhaopin_selfinfo_age;
    private TextView tv_zhaopin_selfinfo_contact_tel;
    private TextView tv_zhaopin_selfinfo_education;
    private TextView tv_zhaopin_selfinfo_height;
    private TextView tv_zhaopin_selfinfo_weight;
    private TextView tv_zhaopin_selfinfo_gangwei;
    private TextView tv_zhaopin_selfinfo_yueshouru;
    private TextView tv_zhaopin_selfinfo_hujidi;
    private TextView tv_zhaopin_selfinfo_juzhudi;
    private TextView tv_zhaopin_selfinfo_diqu;
    private TextView tv_zhaopin_selfinfo_hyzt;
    private TextView tv_zhaopin_selfinfo_znqk;
    private TextView tv_zhaopin_selfinfo_desc;
    private RecyclerView zhaopin_selfinfo_recyclerview;
    private ImageView iv_zhaopin_selfinfo_video;
    private ImageView iv_zhaopin_selfinfo_play;
    private LinearLayout lay_ll_zhaopin_voice;
    private LinearLayout lay_ll_zhaopin_voice_content;
    private ImageView iv_zhaopin_voice;
    private TextView tv_zhaopin_length;

    private TextView tv_zhaopin_zeou_open_status;
    private LinearLayout lay_ll_zhaopin_zeou_info;
    private TextView tv_zhaopin_zeou_height;
    private TextView tv_zhaopin_zeou_weight;
    private TextView tv_zhaopin_zeou_age;
    private TextView tv_zhaopin_zeou_education;

    private TextView tv_zhaopin_zeou_zhiwei;
    private TextView tv_zhaopin_zeou_yueshouru;
    private TextView tv_zhaopin_zeou_hujidi;
    private TextView tv_zhaopin_zeou_juzhudi;
    private TextView tv_zhaopin_zeou_hyzk;
    private TextView tv_zhaopin_zeou_zcyq;
    private TextView tv_zhaopin_zeou_znqk;

    private TextView tv_pipei_status;
    private TextView tv_my_publish_myinfo_open_status;
    private LinearLayout lay_ll_mypublish_myinfo;
    private TextView tv_my_publish_fabusj;
    private TextView tv_my_publish_xinxifei;
    private TextView tv_my_publish_name;
    private TextView tv_my_publish_sex;
    private TextView tv_my_publish_age;
    private TextView tv_my_publish_contact_tel;
    private TextView tv_my_publish_education;
    private TextView tv_my_publish_height;
    private TextView tv_my_publish_weight;
    private TextView tv_my_publish_gangwei;
    private TextView tv_my_publish_yueshouru;
    private TextView tv_my_publish_hujidi;
    private TextView tv_my_publish_juzhudi;
    private TextView tv_my_publish_diqu;
    private TextView tv_my_publish_hyzt;
    private ImageView iv_my_publish_play;
    private ImageView iv_my_publish_video;
    private TextView tv_my_publish_znqk;
    private TextView tv_my_publish_desc;
    private RecyclerView my_publish_recyclerview;
    private LinearLayout lay_ll_my_publish_voice;
    private LinearLayout lay_ll_my_publish_voice_content;
    private ImageView iv_my_publish_voice;
    private TextView tv_my_publish_length;

    private TextView tv_my_publish_zeou_open_status;
    private LinearLayout lay_ll_mypublish_zeou_info;
    private TextView tv_my_publish_zeou_height;
    private TextView tv_my_publish_zeou_weight;
    private TextView tv_my_publish_zeou_age;
    private TextView tv_my_publish_zeou_education;
    private TextView tv_my_publish_zeou_zhiwei;
    private TextView tv_my_publish_zeou_yueshouru;
    private TextView tv_my_publish_zeou_hujidi;
    private TextView tv_my_publish_zeou_juzhudi;
    private TextView tv_my_publish_zeou_hyzk;
    private TextView tv_my_publish_zeou_zcyq;
    private TextView tv_my_publish_zeou_znqk;


    private RecyclerViewAdapter adapter;

    private List<YinyuanOrderDetailModel.MarriageListBean> list;
    private YinyuanOrderDetailModel data;

    private PicRecyclerViewAdapter myPublishAdapter;
    private PicRecyclerViewAdapter zhaopinAdapter;
    private ArrayList<String> myPublishListPic;
    private ArrayList<String> zhaopinListPic;
    private YinyuanOrderDetailModel.MarriageListBean zo_marriage;
    private RelativeLayout lay_rl_my_publish_video;
    private RelativeLayout lay_rl_zhaopin_selfinfo_video;
    private VoiceUtils myPublishvoiceUtils;
    private VoiceUtils zhaoPinvoiceUtils;


    public static YinyuanOrderDetailFragment newInstance(int relate_id) {
        Bundle args = new Bundle();
        args.putInt("relate_id", relate_id);
        YinyuanOrderDetailFragment fragment = new YinyuanOrderDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_yinyuan_order_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("姻缘服务-我要找对象详情");
        initAdapter();
        initPicAdapter();
    }

    private void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        initFooter();

        adapter.setEnableLoadMore(false);

        adapter.isUseEmpty(false);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (list != null) {
                    if (list.get(position) != null) {
                        Intent intent = new Intent(getActivity(), YinyuanUseInfoActivity.class);
                        intent.putExtra("match_id", list.get(position).getMatch_id());
                        getActivity().startActivity(intent);
                    }
                }
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy == 0) {
                    commonRefresh.setEnabled(true);
                } else {
                    commonRefresh.setEnabled(false);
                }
            }
        });

        commonRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshPageData();
            }
        });

    }

    private void initFooter() {
        View footerView = View.inflate(getActivity(), R.layout.footer_yinyuan_order_detail, null);
        lay_ll_header = (LinearLayout) footerView.findViewById(R.id.lay_ll_header);
        lay_ll_tips = (LinearLayout) footerView.findViewById(R.id.lay_ll_tips);

        tv_name_and_age = (TextView) footerView.findViewById(R.id.tv_name_and_age);
        tvPipei = (TextView) footerView.findViewById(R.id.tv_pipei);
        tv_diqu = (TextView) footerView.findViewById(R.id.tv_diqu);
        tv_edu = (TextView) footerView.findViewById(R.id.tv_edu);
        tv_gangwei = (TextView) footerView.findViewById(R.id.tv_gangwei);
        tv_height = (TextView) footerView.findViewById(R.id.tv_height);
        ivPhone = (ImageView) footerView.findViewById(R.id.iv_phone);
        ivAvatar = (ImageView) footerView.findViewById(R.id.iv_avatar);

        //匹配方的个人信息
        tv_zhaopin_selfinfo_open_status = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_open_status);
        lay_ll_zhaopin_selfinfo = (LinearLayout) footerView.findViewById(R.id.lay_ll_zhaopin_selfinfo);
        lay_ll_zhaopin_all_info = (LinearLayout) footerView.findViewById(R.id.lay_ll_zhaopin_all_info);
        tv_order_sn = (TextView) footerView.findViewById(R.id.tv_order_sn);
        tv_zhaopin_selfinfo_name = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_name);
        tv_zhaopin_selfinfo_sex = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_sex);
        tv_zhaopin_selfinfo_age = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_age);
        tv_zhaopin_selfinfo_contact_tel = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_contact_tel);
        tv_zhaopin_selfinfo_education = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_education);
        tv_zhaopin_selfinfo_height = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_height);
        tv_zhaopin_selfinfo_weight = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_weight);
        tv_zhaopin_selfinfo_gangwei = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_gangwei);
        tv_zhaopin_selfinfo_yueshouru = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_yueshouru);
        tv_zhaopin_selfinfo_hujidi = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_hujidi);
        tv_zhaopin_selfinfo_juzhudi = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_juzhudi);
        tv_zhaopin_selfinfo_diqu = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_diqu);
        tv_zhaopin_selfinfo_hyzt = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_hyzt);
        tv_zhaopin_selfinfo_znqk = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_znqk);
        tv_zhaopin_selfinfo_desc = (TextView) footerView.findViewById(R.id.tv_zhaopin_selfinfo_desc);
        zhaopin_selfinfo_recyclerview = (RecyclerView) footerView.findViewById(R.id.zhaopin_selfinfo_recyclerview);
        iv_zhaopin_selfinfo_video = (ImageView) footerView.findViewById(R.id.iv_zhaopin_selfinfo_video);
        iv_zhaopin_selfinfo_play = (ImageView) footerView.findViewById(R.id.iv_zhaopin_selfinfo_play);
        lay_rl_zhaopin_selfinfo_video = (RelativeLayout) footerView.findViewById(R.id.lay_rl_zhaopin_selfinfo_video);
        lay_ll_zhaopin_voice = (LinearLayout) footerView.findViewById(R.id.lay_ll_zhaopin_voice);
        lay_ll_zhaopin_voice_content = (LinearLayout) footerView.findViewById(R.id.lay_ll_zhaopin_voice_content);
        iv_zhaopin_voice = (ImageView) footerView.findViewById(R.id.iv_zhaopin_voice);
        tv_zhaopin_length = (TextView) footerView.findViewById(R.id.tv_zhaopin_length);

        //匹配方的择偶信息
        tv_zhaopin_zeou_open_status = (TextView) footerView.findViewById(R.id.tv_zhaopin_zeou_open_status);
        lay_ll_zhaopin_zeou_info = (LinearLayout) footerView.findViewById(R.id.lay_ll_zhaopin_zeou_info);
        tv_zhaopin_zeou_height = (TextView) footerView.findViewById(R.id.tv_zhaopin_zeou_height);
        tv_zhaopin_zeou_weight = (TextView) footerView.findViewById(R.id.tv_zhaopin_zeou_weight);
        tv_zhaopin_zeou_age = (TextView) footerView.findViewById(R.id.tv_zhaopin_zeou_age);
        tv_zhaopin_zeou_education = (TextView) footerView.findViewById(R.id.tv_zhaopin_zeou_education);
        tv_zhaopin_zeou_zhiwei = (TextView) footerView.findViewById(R.id.tv_zhaopin_zeou_zhiwei);
        tv_zhaopin_zeou_yueshouru = (TextView) footerView.findViewById(R.id.tv_zhaopin_zeou_yueshouru);
        tv_zhaopin_zeou_hujidi = (TextView) footerView.findViewById(R.id.tv_zhaopin_zeou_hujidi);
        tv_zhaopin_zeou_juzhudi = (TextView) footerView.findViewById(R.id.tv_zhaopin_zeou_juzhudi);
        tv_zhaopin_zeou_hyzk = (TextView) footerView.findViewById(R.id.tv_zhaopin_zeou_hyzk);
        tv_zhaopin_zeou_znqk = (TextView) footerView.findViewById(R.id.tv_zhaopin_zeou_znqk);
        tv_zhaopin_zeou_zcyq = (TextView) footerView.findViewById(R.id.tv_zhaopin_zeou_zcyq);
        //我发布的个人信息
        tv_pipei_status = (TextView) footerView.findViewById(R.id.tv_pipei_status);
        tv_my_publish_myinfo_open_status = (TextView) footerView.findViewById(R.id.tv_my_publish_myinfo_open_status);
        lay_ll_mypublish_myinfo = (LinearLayout) footerView.findViewById(R.id.lay_ll_mypublish_myinfo);
        tv_my_publish_fabusj = (TextView) footerView.findViewById(R.id.tv_my_publish_fabusj);
        tv_my_publish_xinxifei = (TextView) footerView.findViewById(R.id.tv_my_publish_xinxifei);
        tv_my_publish_name = (TextView) footerView.findViewById(R.id.tv_my_publish_name);
        tv_my_publish_sex = (TextView) footerView.findViewById(R.id.tv_my_publish_sex);
        tv_my_publish_age = (TextView) footerView.findViewById(R.id.tv_my_publish_age);
        tv_my_publish_contact_tel = (TextView) footerView.findViewById(R.id.tv_my_publish_contact_tel);
        tv_my_publish_education = (TextView) footerView.findViewById(R.id.tv_my_publish_education);
        tv_my_publish_height = (TextView) footerView.findViewById(R.id.tv_my_publish_height);
        tv_my_publish_weight = (TextView) footerView.findViewById(R.id.tv_my_publish_weight);
        tv_my_publish_gangwei = (TextView) footerView.findViewById(R.id.tv_my_publish_gangwei);
        tv_my_publish_yueshouru = (TextView) footerView.findViewById(R.id.tv_my_publish_yueshouru);
        tv_my_publish_hujidi = (TextView) footerView.findViewById(R.id.tv_my_publish_hujidi);
        tv_my_publish_juzhudi = (TextView) footerView.findViewById(R.id.tv_my_publish_juzhudi);
        tv_my_publish_diqu = (TextView) footerView.findViewById(R.id.tv_my_publish_diqu);
        tv_my_publish_hyzt = (TextView) footerView.findViewById(R.id.tv_my_publish_hyzt);
        tv_my_publish_znqk = (TextView) footerView.findViewById(R.id.tv_my_publish_znqk);
        tv_my_publish_desc = (TextView) footerView.findViewById(R.id.tv_my_publish_desc);
        my_publish_recyclerview = (RecyclerView) footerView.findViewById(R.id.my_publish_recyclerview);
        iv_my_publish_video = (ImageView) footerView.findViewById(R.id.iv_my_publish_video);
        iv_my_publish_play = (ImageView) footerView.findViewById(R.id.iv_my_publish_play);
        lay_rl_my_publish_video = (RelativeLayout) footerView.findViewById(R.id.lay_rl_my_publish_video);
        lay_ll_my_publish_voice = (LinearLayout) footerView.findViewById(R.id.lay_ll_my_publish_voice);
        lay_ll_my_publish_voice_content = (LinearLayout) footerView.findViewById(R.id.lay_ll_my_publish_voice_content);
        iv_my_publish_voice = (ImageView) footerView.findViewById(R.id.iv_my_publish_voice);
        tv_my_publish_length = (TextView) footerView.findViewById(R.id.tv_my_publish_length);

        //我发布的择偶信息
        tv_my_publish_zeou_open_status = (TextView) footerView.findViewById(R.id.tv_my_publish_zeou_open_status);
        lay_ll_mypublish_zeou_info = (LinearLayout) footerView.findViewById(R.id.lay_ll_mypublish_zeou_info);
        tv_my_publish_zeou_height = (TextView) footerView.findViewById(R.id.tv_my_publish_zeou_height);
        tv_my_publish_zeou_weight = (TextView) footerView.findViewById(R.id.tv_my_publish_zeou_weight);
        tv_my_publish_zeou_age = (TextView) footerView.findViewById(R.id.tv_my_publish_zeou_age);
        tv_my_publish_zeou_education = (TextView) footerView.findViewById(R.id.tv_my_publish_zeou_education);
        tv_my_publish_zeou_zhiwei = (TextView) footerView.findViewById(R.id.tv_my_publish_zeou_zhiwei);
        tv_my_publish_zeou_yueshouru = (TextView) footerView.findViewById(R.id.tv_my_publish_zeou_yueshouru);
        tv_my_publish_zeou_hujidi = (TextView) footerView.findViewById(R.id.tv_my_publish_zeou_hujidi);
        tv_my_publish_zeou_juzhudi = (TextView) footerView.findViewById(R.id.tv_my_publish_zeou_juzhudi);
        tv_my_publish_zeou_hyzk = (TextView) footerView.findViewById(R.id.tv_my_publish_zeou_hyzk);
        tv_my_publish_zeou_znqk = (TextView) footerView.findViewById(R.id.tv_my_publish_zeou_znqk);
        tv_my_publish_zeou_zcyq = (TextView) footerView.findViewById(R.id.tv_my_publish_zeou_zcyq);


        iv_zhaopin_selfinfo_video.setOnClickListener(this);
        iv_zhaopin_selfinfo_play.setOnClickListener(this);
        iv_my_publish_video.setOnClickListener(this);
        iv_my_publish_play.setOnClickListener(this);
        tv_my_publish_zeou_open_status.setOnClickListener(this);
        tv_my_publish_myinfo_open_status.setOnClickListener(this);
        tv_zhaopin_zeou_open_status.setOnClickListener(this);
        tv_zhaopin_selfinfo_open_status.setOnClickListener(this);

        adapter.addFooterView(footerView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_my_publish_myinfo_open_status:
                if (lay_ll_mypublish_myinfo.getVisibility() == View.GONE) {
                    tv_my_publish_myinfo_open_status.setText("收起");
                    tv_my_publish_myinfo_open_status.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.icon_arrows_up), null);
                    lay_ll_mypublish_myinfo.setVisibility(View.VISIBLE);
                } else {
                    tv_my_publish_myinfo_open_status.setText("展开");
                    tv_my_publish_myinfo_open_status.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.icon_arrows_down), null);
                    lay_ll_mypublish_myinfo.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_my_publish_zeou_open_status:
                if (lay_ll_mypublish_zeou_info.getVisibility() == View.GONE) {
                    tv_my_publish_zeou_open_status.setText("收起");
                    tv_my_publish_zeou_open_status.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.icon_arrows_up), null);
                    lay_ll_mypublish_zeou_info.setVisibility(View.VISIBLE);
                } else {
                    tv_my_publish_zeou_open_status.setText("展开");
                    tv_my_publish_zeou_open_status.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.icon_arrows_down), null);
                    lay_ll_mypublish_zeou_info.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_zhaopin_selfinfo_open_status:
                if (lay_ll_zhaopin_selfinfo.getVisibility() == View.GONE) {
                    tv_zhaopin_selfinfo_open_status.setText("收起");
                    tv_zhaopin_selfinfo_open_status.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.icon_arrows_up), null);
                    lay_ll_zhaopin_selfinfo.setVisibility(View.VISIBLE);
                } else {
                    tv_zhaopin_selfinfo_open_status.setText("展开");
                    tv_zhaopin_selfinfo_open_status.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.icon_arrows_down), null);
                    lay_ll_zhaopin_selfinfo.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_zhaopin_zeou_open_status:
                if (lay_ll_zhaopin_zeou_info.getVisibility() == View.GONE) {
                    tv_zhaopin_zeou_open_status.setText("收起");
                    tv_zhaopin_zeou_open_status.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.icon_arrows_up), null);
                    lay_ll_zhaopin_zeou_info.setVisibility(View.VISIBLE);
                } else {
                    tv_zhaopin_zeou_open_status.setText("展开");
                    tv_zhaopin_zeou_open_status.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.icon_arrows_down), null);
                    lay_ll_zhaopin_zeou_info.setVisibility(View.GONE);
                }
                break;
            case R.id.iv_my_publish_play:
                if (data != null) {
                    String s = data.getVideo_url();
                    if (!TextUtils.isEmpty(s)) {
                        Bundle bundle = new Bundle();
                        bundle.putString(VideoPlayActivity.VIDEO_URL, s);
                        startAtvDonFinish(VideoPlayActivity.class, bundle);
                    }
                }
                break;
            case R.id.iv_zhaopin_selfinfo_play:
                if (zo_marriage != null) {
                    String s = zo_marriage.getVideo_url();
                    if (!TextUtils.isEmpty(s)) {
                        Bundle bundle = new Bundle();
                        bundle.putString(VideoPlayActivity.VIDEO_URL, s);
                        startAtvDonFinish(VideoPlayActivity.class, bundle);
                    }
                }
                break;
        }
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_YINYUAN_ORDER_DETAIL)
                .addParams("marriage_id", getArguments().getInt("relate_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<YinyuanOrderDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        commonRefresh.refreshComplete();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<YinyuanOrderDetailModel> respnse, String source) {
                        showContentView();
                        commonRefresh.refreshComplete();
                        if (respnse.getData() != null) {
                            data = respnse.getData();
                            setData(respnse.getData());
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<YinyuanOrderDetailModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    private void setData(YinyuanOrderDetailModel data) {
        int is_match = data.getIs_match();
        lay_ll_header.setVisibility(View.GONE);
        lay_ll_tips.setVisibility(View.GONE);
        lay_ll_zhaopin_all_info.setVisibility(View.GONE);
        lay_ll_zhaopin_selfinfo.setVisibility(View.GONE);
        lay_ll_zhaopin_zeou_info.setVisibility(View.GONE);
        lay_ll_mypublish_myinfo.setVisibility(View.VISIBLE);
        lay_ll_mypublish_zeou_info.setVisibility(View.VISIBLE);
        //匹配成功，list会为空，则取招聘方发布信息实体，
        //还没有匹配or匹配失败or还没有获取联系方式，需要隐藏招聘方信息，等点击其中一人获取联系方式后，再显示
        if (is_match == 2) {//取招聘方发布信息实体
            tv_pipei_status.setTextColor(Color.parseColor("#3172f4"));
            tv_pipei_status.setText("已匹配");
        } else if (is_match == 3) {
            tv_pipei_status.setTextColor(Color.parseColor("#fdbe44"));
            tv_pipei_status.setText("匹配失败");
        }else if (is_match == 4){
            tv_pipei_status.setTextColor(Color.parseColor("#FF0000"));
            tv_pipei_status.setText("匹配成功");
            //招聘方信息---start
            zo_marriage = data.getZo_marriage();
            if (zo_marriage != null) {
                lay_ll_header.setVisibility(View.VISIBLE);
                lay_ll_zhaopin_selfinfo.setVisibility(View.VISIBLE);
                lay_ll_zhaopin_zeou_info.setVisibility(View.VISIBLE);
                lay_ll_zhaopin_all_info.setVisibility(View.VISIBLE);
                lay_ll_tips.setVisibility(View.VISIBLE);
                lay_ll_mypublish_myinfo.setVisibility(View.GONE);
                lay_ll_mypublish_zeou_info.setVisibility(View.GONE);
                //---------------获取了联系方式人的信息----------\\
                if (!TextUtils.isEmpty(zo_marriage.getUser_name())){
                    tv_name_and_age.setText(zo_marriage.getUser_name()+ zo_marriage.getAge()+"岁");
                }
                tv_diqu.setText(TextUtils.isEmpty(zo_marriage.getRelease_third_area_name()) ? "" : zo_marriage.getRelease_third_area_name());
                tv_edu.setText(TextUtils.isEmpty(zo_marriage.getEducation_background_name()) ? "" : zo_marriage.getEducation_background_name());
                tv_gangwei.setText(TextUtils.isEmpty(zo_marriage.getJob_name()) ? "" : zo_marriage.getJob_name());
                tv_height.setText(zo_marriage.getHeight()+"cm");
                GlideUtil.show(getActivity(), zo_marriage.getUser_avatar_url(), ivAvatar);
                tvPipei.setText(new SpanUtils().append("匹配度：")
                        .append(zo_marriage.getMatch_value() + "%")
                        .setForegroundColor(ResUtils.getCommRed())
                        .create());
                final String mobile_phone = zo_marriage.getMobile_phone();
                final String user_name = zo_marriage.getUser_name();
                ivPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final BaseDialog baseDialog = new BaseDialog(getActivity());
                        baseDialog.showTwoButton("提示", "是否拨打电话?\n" + user_name + "\n" + mobile_phone
                                , "拨打", "取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (baseDialog != null) {
                                            baseDialog.dismiss();
                                        }
                                        String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                                        if (checkPermission(permissions)) {
                                            PhoneUtils.dial(mobile_phone);
                                        }
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (baseDialog != null) {
                                            baseDialog.dismiss();
                                        }
                                    }
                                });
                    }
                });
                //---------------匹配人的个人信息---------------\\
                tv_order_sn.setText(TextUtils.isEmpty(data.getOrder_sn()) ? "" : data.getOrder_sn());
                tv_zhaopin_selfinfo_name.setText(TextUtils.isEmpty(zo_marriage.getUser_name()) ? "" : zo_marriage.getUser_name());
                tv_zhaopin_selfinfo_sex.setText(zo_marriage.getSex() == 1 ? "男" : "女");
                tv_zhaopin_selfinfo_age.setText(zo_marriage.getAge() + "岁");
                tv_zhaopin_selfinfo_contact_tel.setText(TextUtils.isEmpty(zo_marriage.getMobile_phone()) ? "" : zo_marriage.getMobile_phone());
                tv_zhaopin_selfinfo_education.setText(TextUtils.isEmpty(zo_marriage.getEducation_background_name()) ? "" : zo_marriage.getEducation_background_name());
                tv_zhaopin_selfinfo_height.setText(zo_marriage.getHeight() + "cm");
                tv_zhaopin_selfinfo_weight.setText(zo_marriage.getWeight() + "kg");
                tv_zhaopin_selfinfo_gangwei.setText(TextUtils.isEmpty(zo_marriage.getJob_name()) ? "" : zo_marriage.getJob_name());
                tv_zhaopin_selfinfo_yueshouru.setText(TextUtils.isEmpty(zo_marriage.getSalary_name()) ? "" : zo_marriage.getSalary_name());
                tv_zhaopin_selfinfo_hujidi.setText(TextUtils.isEmpty(zo_marriage.getFirst_area_name()) ? "" : zo_marriage.getFirst_area_name());
                tv_zhaopin_selfinfo_juzhudi.setText(TextUtils.isEmpty(zo_marriage.getLive_second_area_name()) ? "" : zo_marriage.getLive_second_area_name());
                tv_zhaopin_selfinfo_diqu.setText(TextUtils.isEmpty(zo_marriage.getRelease_third_area_name()) ? "" : zo_marriage.getRelease_third_area_name());
                if(zo_marriage.getMarital_status()==0 ){
                    tv_zhaopin_selfinfo_hyzt.setText("");
                }else {
                    tv_zhaopin_selfinfo_hyzt.setText(zo_marriage.getMarital_status() == 1 ? "未婚" : "离异");
                }
                if(zo_marriage.getChild_status()==0 ){
                    tv_zhaopin_selfinfo_znqk.setText("");
                }else {
                    tv_zhaopin_selfinfo_znqk.setText(zo_marriage.getChild_status() == 1 ? "有子女" : "无子女");
                }
                tv_zhaopin_selfinfo_desc.setText(TextUtils.isEmpty(zo_marriage.getSelf_intro()) ? "" : zo_marriage.getSelf_intro());
                String pic_urls = zo_marriage.getPic_urls();
                if (!TextUtils.isEmpty(pic_urls)) {
                    List<String> zhaopinPicList = Arrays.asList(pic_urls.split(","));
                    zhaopinListPic.clear();
                    zhaopinListPic.addAll(zhaopinPicList);
                    zhaopinAdapter.notifyDataSetChanged();
                }
                if (!TextUtils.isEmpty(zo_marriage.getVideo_url())) {
                    lay_rl_zhaopin_selfinfo_video.setVisibility(View.VISIBLE);
                    MediaUtils.getImageForVideo(zo_marriage.getVideo_url(), new MediaUtils.OnLoadVideoImageListener() {
                        @Override
                        public void onLoadImage(File file) {
                            if (file == null) {
                                return;
                            }
                            final Uri uri = Uri.fromFile(file);
                            if (getActivity() != null) {
                                final ContentResolver contentResolver = getActivity().getContentResolver();
                                if (contentResolver != null) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri);
                                                iv_zhaopin_selfinfo_video.setImageBitmap(bitmap);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });
                }else{
                    lay_rl_zhaopin_selfinfo_video.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(zo_marriage.getVoice_url())){
                    lay_ll_zhaopin_voice_content.setVisibility(View.VISIBLE);
                    zhaoPinvoiceUtils = new VoiceUtils(iv_zhaopin_voice);
                    zhaoPinvoiceUtils.setFilePath(zo_marriage.getVoice_url());
                    zhaoPinvoiceUtils.getTime(tv_zhaopin_length);
                    lay_ll_zhaopin_voice.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            boolean playing = zhaoPinvoiceUtils.isPlaying();
                            if (playing) {
                                zhaoPinvoiceUtils.stop();
                            } else {
                                zhaoPinvoiceUtils.play();
                            }
                        }
                    });
                }else{
                    lay_ll_zhaopin_voice_content.setVisibility(View.GONE);
                }


                //---------------匹配人的择偶信息---------------\\
                tv_zhaopin_zeou_height.setText(zo_marriage.getStart_zo_height() + "-" + zo_marriage.getEnd_zo_height() + "cm");
                tv_zhaopin_zeou_weight.setText(zo_marriage.getStart_zo_weight() + "-" + zo_marriage.getEnd_zo_weight() + "kg");
                tv_zhaopin_zeou_age.setText(zo_marriage.getStart_zo_age() + "-" + zo_marriage.getEnd_zo_age() + "岁");
                tv_zhaopin_zeou_education.setText(TextUtils.isEmpty(zo_marriage.getZo_education_background_name()) ? "" : zo_marriage.getZo_education_background_name());
                tv_zhaopin_zeou_zhiwei.setText(TextUtils.isEmpty(zo_marriage.getZo_job_name()) ? "" : zo_marriage.getZo_job_name());
                tv_zhaopin_zeou_yueshouru.setText(TextUtils.isEmpty(zo_marriage.getZo_salary_name()) ? "" : zo_marriage.getZo_salary_name());
                tv_zhaopin_zeou_hujidi.setText(TextUtils.isEmpty(zo_marriage.getZo_first_area_name()) ? "" : zo_marriage.getZo_first_area_name());
                tv_zhaopin_zeou_juzhudi.setText(TextUtils.isEmpty(zo_marriage.getZo_live_second_area_name()) ? "" : zo_marriage.getZo_live_second_area_name());

                if(zo_marriage.getZo_marital_status()==0 ){
                    tv_zhaopin_zeou_hyzk.setText("");
                }else {
                    tv_zhaopin_zeou_hyzk.setText(zo_marriage.getZo_marital_status() == 1 ? "未婚" : "离异");
                }
                if(zo_marriage.getZo_child_status()==0 ){
                    tv_zhaopin_zeou_znqk.setText("");
                }else {
                    tv_zhaopin_zeou_znqk.setText(zo_marriage.getZo_child_status() == 1 ? "有子女" : "无子女");
                }
                String assets_require = zo_marriage.getAssets_require();
                if (assets_require.contains("1")) {
                    tv_zhaopin_zeou_zcyq.setText("有车");
                }
                if (assets_require.contains("2")) {
                    tv_zhaopin_zeou_zcyq.setText("有房");
                }
                if (assets_require.contains("1") && assets_require.contains("2")) {
                    tv_zhaopin_zeou_zcyq.setText("有车，有房");
                }
            }
        }else{
            tv_pipei_status.setTextColor(Color.parseColor("#239491"));
            tv_pipei_status.setText("正在匹配中");
        }
        //--------------------------匹配到的人-------------|\\\\\\\\\\\\\\\\\\\\\\\\
        List<YinyuanOrderDetailModel.MarriageListBean> marriage_list = data.getMarriage_list();
        if (marriage_list != null) {
            list.clear();
            list.addAll(marriage_list);
            adapter.notifyDataSetChanged();
        }
        ///////////////////------------------------应聘方个人信息-------------------------\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        //发布时间
        tv_my_publish_fabusj.setText(DateUtil.StampTimeToDate(data.getAdd_time() + "", "yyyy-MM-dd HH:mm:ss"));
        //信息费
        tv_my_publish_xinxifei.setText(data.getInfo_fee() + "元");
        tv_my_publish_name.setText(TextUtils.isEmpty(data.getUser_name()) ? "" : data.getUser_name());
        tv_my_publish_sex.setText(data.getSex() == 1 ? "男" : "女");
        tv_my_publish_age.setText(data.getAge() + "岁");
        tv_my_publish_contact_tel.setText(TextUtils.isEmpty(data.getMobile_phone()) ? "" : data.getMobile_phone());
        tv_my_publish_education.setText(TextUtils.isEmpty(data.getEducation_background_name()) ? "" : data.getEducation_background_name() );
        tv_my_publish_height.setText(data.getHeight() + "cm");
        tv_my_publish_weight.setText(data.getWeight() + "kg");
        tv_my_publish_gangwei.setText(TextUtils.isEmpty(data.getJob_name()) ? "" : data.getJob_name());
        tv_my_publish_yueshouru.setText(TextUtils.isEmpty(data.getSalary_name()) ? "" : data.getSalary_name());
        tv_my_publish_hujidi.setText(TextUtils.isEmpty(data.getFirst_area_name()) ? "" : data.getFirst_area_name());
        tv_my_publish_juzhudi.setText(TextUtils.isEmpty(data.getLive_second_area_name()) ? "" : data.getLive_second_area_name());
        if (StringUtil.isAllNotEmpty(data.getRelease_third_area_name())) {
            tv_my_publish_diqu.setText(data.getRelease_third_area_name());
        }

        if(data.getMarital_status()==0 ){
            tv_my_publish_hyzt.setText("");
        }else {
            tv_my_publish_hyzt.setText(data.getMarital_status() == 1 ? "未婚" : "离异");
        }
        if(data.getChild_status()==0 ){
            tv_my_publish_znqk.setText("");
        }else {
            tv_my_publish_znqk.setText(data.getChild_status() == 1 ? "有子女" : "无子女");
        }
        tv_my_publish_desc.setText(TextUtils.isEmpty(data.getSelf_intro()) ? "" : data.getSelf_intro());
        String pic_urls = data.getPic_urls();
        if (!TextUtils.isEmpty(pic_urls)) {
            List<String> mypublishPicList = Arrays.asList(pic_urls.split(","));
            myPublishListPic.clear();
            myPublishListPic.addAll(mypublishPicList);
            myPublishAdapter.notifyDataSetChanged();
        }
        if (!TextUtils.isEmpty(data.getVideo_url())) {
            lay_rl_my_publish_video.setVisibility(View.VISIBLE);
            MediaUtils.getImageForVideo(data.getVideo_url(), new MediaUtils.OnLoadVideoImageListener() {
                @Override
                public void onLoadImage(File file) {
                    if (file == null) {
                        return;
                    }
                    final Uri uri = Uri.fromFile(file);
                    if (getActivity() != null) {
                        final ContentResolver contentResolver = getActivity().getContentResolver();
                        if (contentResolver != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri);
                                        iv_my_publish_video.setImageBitmap(bitmap);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }else{
            lay_rl_my_publish_video.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(data.getVoice_url())){
            myPublishvoiceUtils = new VoiceUtils(iv_my_publish_voice);
            lay_ll_my_publish_voice_content.setVisibility(View.VISIBLE);
            myPublishvoiceUtils.setFilePath(data.getVoice_url());
            myPublishvoiceUtils.getTime(tv_my_publish_length);
            lay_ll_my_publish_voice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean playing = myPublishvoiceUtils.isPlaying();
                    if (playing) {
                        myPublishvoiceUtils.stop();
                    } else {
                        myPublishvoiceUtils.play();
                    }
                }
            });
        }else{
            lay_ll_my_publish_voice_content.setVisibility(View.GONE);
        }
        ///////////////////------------------------应聘方择偶信息-------------------------\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        tv_my_publish_zeou_height.setText(data.getStart_zo_height() + "-" + data.getEnd_zo_height() + "cm");
        tv_my_publish_zeou_weight.setText(data.getStart_zo_weight() + "-" + data.getEnd_zo_weight() + "kg");
        tv_my_publish_zeou_age.setText(data.getStart_zo_age() + "-" + data.getEnd_zo_age() + "岁");
        tv_my_publish_zeou_education.setText(TextUtils.isEmpty(data.getZo_education_background_name()) ? "" : data.getZo_education_background_name());
        tv_my_publish_zeou_zhiwei.setText(TextUtils.isEmpty(data.getZo_job_name()) ? "" : data.getZo_job_name());
        tv_my_publish_zeou_yueshouru.setText(TextUtils.isEmpty(data.getZo_salary_name()) ? "" : data.getZo_salary_name());
        tv_my_publish_zeou_hujidi.setText(TextUtils.isEmpty(data.getZo_first_area_name()) ? "" : data.getZo_first_area_name());
        tv_my_publish_zeou_juzhudi.setText(TextUtils.isEmpty(data.getZo_live_second_area_name()) ? "" : data.getZo_live_second_area_name());

        if(data.getZo_marital_status()==0 ){
            tv_my_publish_zeou_hyzk.setText("");
        }else {
            tv_my_publish_zeou_hyzk.setText(data.getZo_marital_status() == 1 ? "未婚" : "离异");
        }
        if(data.getZo_child_status()==0 ){
            tv_my_publish_zeou_znqk.setText("");
        }else {
            tv_my_publish_zeou_znqk.setText(data.getZo_child_status() == 1 ? "有子女" : "无子女");
        }
        String assets_require = data.getAssets_require();
        if (assets_require.contains("1")) {
            tv_my_publish_zeou_zcyq.setText("有车");
        }
        if (assets_require.contains("2")) {
            tv_my_publish_zeou_zcyq.setText("有房");
        }
        if (assets_require.contains("1") && assets_require.contains("2")) {
            tv_my_publish_zeou_zcyq.setText("有车，有房");
        }
        if (assets_require.equals("")) {
            tv_my_publish_zeou_zcyq.setText("");
        }

    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    @Override
    public boolean autoRefresh() {
        return true;
    }

    private void addOnClick(final int type, final int getMatch_id) {
        if (type == 1) {//不感兴趣
            unInterested(getMatch_id);
        } else if (type == 2) {//感兴趣
            final BaseDialog baseDialog = new BaseDialog(getActivity());
            baseDialog.showTwoButton("提示", "是否获取联系方式", "确定", "取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseDialog.dismiss();
                    getPhone(getMatch_id);
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseDialog.dismiss();
                }
            });
        }

    }

    //获取联系方式
    private void getPhone(int match_id) {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_GET_CONTACT_INFORMATION)
                .addParams("match_id", match_id + "")
                .setCallBackData(new BaseNewNetModelimpl<GetPhoneModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, final BaseResultInfo<GetPhoneModel> respnse, String source) {
                        dismiss();
                        if (respnse.getData() != null) {
                            refreshPageData();
                            final BaseDialog baseDialog = new BaseDialog(getActivity());

                            baseDialog.showTwoButton("提示", "是否拨打电话?\n" + respnse.getData().getUser_name() + "\n" + respnse.getData().getMobile_phone()
                                    , "拨打", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //获取联系方式之后，需要刷新数据
                                            initData();
                                            if (baseDialog != null) {
                                                baseDialog.dismiss();
                                            }
                                            String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                                            if (checkPermission(permissions)) {
                                                PhoneUtils.dial(respnse.getData().getMobile_phone());
                                            }
                                        }
                                    }, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (baseDialog != null) {
                                                baseDialog.dismiss();
                                            }
                                        }
                                    });
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<GetPhoneModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }


    //不感兴趣
    private void unInterested(final int match_id) {
        if (data != null) {
            int num = data.getUninterested_num();
            final BaseDialog baseDialog = new BaseDialog(getActivity());
            baseDialog.showTwoButton("提示", "管家还可以为您提供" + num + "次匹配机会", "重新匹配", "取消",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            baseDialog.dismiss();
                            //完成之后 tvLeft 隐藏
                            showDialog();
                            addCall(new NetUtil().setUrl(APIS.URL_TALENT_UNINTERESTED_REQUIRE_YINYUAN)
                                    .addParams("match_id", match_id + "")
                                    .setCallBackData(new BaseNewNetModelimpl() {
                                        @Override
                                        protected void onFail(int type, String msg) {
                                            dismiss();
                                            showTips(msg);
                                        }

                                        @Override
                                        protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                                            dismiss();
                                            if (respnse.getCode() == 0) {
                                                showTips("操作成功");
                                                refreshPageData();
                                            }
                                        }

                                        @Override
                                        protected Type getType() {
                                            return new TypeToken<BaseResultInfo>() {
                                            }.getType();
                                        }
                                    }).buildPost()
                            );
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            baseDialog.dismiss();
                        }
                    });
        }
    }


    class RecyclerViewAdapter extends BaseQuickAdapter<YinyuanOrderDetailModel.MarriageListBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<YinyuanOrderDetailModel.MarriageListBean> data) {
            super(R.layout.item_recycler_has_match_yinyuan, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final YinyuanOrderDetailModel.MarriageListBean item) {
            helper.setText(R.id.tv_name_and_age, item.getUser_name() + " " + item.getAge() )
                    .setText(R.id.tv_diqu, item.getRelease_third_area_name())
                    .setText(R.id.tv_edu, item.getEducation_background_name())
                    .setText(R.id.tv_gangwei, item.getJob_name())
                    .setText(R.id.tv_height, item.getHeight() + "cm");

            TextView tvPipei = (TextView) helper.getView(R.id.tv_pipei);
            tvPipei.setText(new SpanUtils().append("匹配度：")
                    .append(item.getMatch_value() + "%")
                    .setForegroundColor(ResUtils.getCommRed())
                    .create());

            GlideUtil.show(getActivity(), item.getUser_avatar_url(), (ImageView) helper.getView(R.id.iv_avatar));

            final TextView tv_interestring = (TextView) helper.getView(R.id.tv_interestring);
            int is_uninterested = item.getIs_uninterested();
            if (is_uninterested == 1) {
                tv_interestring.setVisibility(View.GONE);
            }
            if (data != null) {
                if (data.getUninterested_num() == 0) {
                    tv_interestring.setVisibility(View.GONE);
                }
            }
            tv_interestring.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addOnClick(1, item.getMatch_id());
                }
            });
            final TextView tv_call_phone = (TextView) helper.getView(R.id.tv_call_phone);
            tv_call_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addOnClick(2, item.getMatch_id());
                }
            });
        }
    }


    public void initPicAdapter() {
        myPublishListPic = new ArrayList<>();
        myPublishAdapter = new PicRecyclerViewAdapter(myPublishListPic);
        my_publish_recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 4));
        my_publish_recyclerview.setNestedScrollingEnabled(false);
        my_publish_recyclerview.setAdapter(myPublishAdapter);

        myPublishAdapter.setEnableLoadMore(false);

        myPublishAdapter.isUseEmpty(false);

        //事件监听
        myPublishAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ViewWrap.showPicActivity(getActivity(), myPublishListPic, position);
            }
        });

        zhaopinListPic = new ArrayList<>();
        zhaopinAdapter = new PicRecyclerViewAdapter(zhaopinListPic);
        zhaopin_selfinfo_recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 3));
        zhaopin_selfinfo_recyclerview.setNestedScrollingEnabled(false);
        zhaopin_selfinfo_recyclerview.setAdapter(zhaopinAdapter);

        zhaopinAdapter.setEnableLoadMore(false);

        zhaopinAdapter.isUseEmpty(false);

        //事件监听
        zhaopinAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ViewWrap.showPicActivity(getActivity(), zhaopinListPic, position);
            }
        });
    }

    class PicRecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PicRecyclerViewAdapter(@Nullable List<String> data) {
            super(R.layout.item_recycler_add_pic, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.getView(R.id.iv_close).setVisibility(View.GONE);
            GlideUtil.show(getActivity(), item, (ImageView) helper.getView(R.id.iv_add_pic));
        }
    }
}
