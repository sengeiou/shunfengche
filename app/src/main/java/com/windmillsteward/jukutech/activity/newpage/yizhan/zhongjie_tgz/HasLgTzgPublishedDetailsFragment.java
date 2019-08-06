package com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz;


import android.Manifest;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.VideoPlayActivity;
import com.windmillsteward.jukutech.activity.map.ShowMapZhaoPinActivity;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.model.HasLgTzgPublishedDetailsModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.TouSuFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.VoiceUtils;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.event.NotifyPageRefresh;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.ActivityUtils;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
import com.windmillsteward.jukutech.utils.MediaUtils;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import org.greenrobot.eventbus.Subscribe;
import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 * 已发布职位的详情 劳工 特种工
 */
public class HasLgTzgPublishedDetailsFragment extends BaseInitFragment {
    public static final String TAG = "HasLgTzgPublishedDetailsFragment";
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;
    @Bind(R.id.tv_all_pay)
    TextView tvAllPay;

    private TextView mTv_bianhao;
    private TextView mTv_bianti;
    private TextView mTv_gongzhong;
    private TextView mTv_diqu;
    private TextView mTv_address;
    private TextView mTv_date;
    private TextView mTv_desc;
    private TextView mTv_finish;
    private TextView tv_tips;
    private TextView mTv_time;
    private TextView mTv_num;
    private LinearLayout lay_ll_address;
    private TextView tv_xinzi;
    private LinearLayout lay_ll_tips;

    private HasLgTzgPublishedDetailsModel data;
    private LinearLayout lay_ll_zhaopin_voice;
    private LinearLayout lay_ll_zhaopin_voice_content;
    private ImageView iv_zhaopin_voice;
    private TextView tv_zhaopin_length;
    private RecyclerView zhaopin_recyclerview;
    private RelativeLayout lay_rl_zhaopin_video;
    private ImageView iv_zhaopin_video;
    private ImageView iv_zhaopin_play;
    private TextView tv_zhaopin_pic_tips;

    private ArrayList<String> zhaopinListPic;
    private PicRecyclerViewAdapter zhaopinAdapter;
    private VoiceUtils zhaoPinvoiceUtils;



    @Override
    protected int getContentViewId() {
        return R.layout.fragment_has_published_position_details;
    }

    public static HasLgTzgPublishedDetailsFragment newInstance(int recruitment_id, int roleType, int showAddress) {
        Bundle args = new Bundle();
        args.putInt("recruitment_id", recruitment_id);
        args.putInt("roleType", roleType);
        args.putInt("showAddress", showAddress);
        HasLgTzgPublishedDetailsFragment fragment = new HasLgTzgPublishedDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {

        if (getArguments()!= null){
            if (getArguments().getInt("roleType") == PageConfig.TYPE_ZHONGJIE){
                setMainTitle("劳务中介-我要找人详情");
            }else if (getArguments().getInt("roleType") == PageConfig.TYPE_TEZHONGGONG){
                setMainTitle("特种工-我要找人详情");
            }
        }

        initAdapter();
        initPicAdapter();
        commonRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshPageData();
            }
        });
    }

    @Override
    protected void initData() {
        addCall(new NetUtil()
                .setUrl(APIS.URL_TALENT_PUBLISHED_POSITION_DETAIL)
                .addParams("recruitment_id", "" + getArguments().getInt("recruitment_id"))
                .setCallBackData(new BaseNewNetModelimpl<HasLgTzgPublishedDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        commonRefresh.refreshComplete();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<HasLgTzgPublishedDetailsModel> respnse, String source) {
                        showContentView();
                        commonRefresh.refreshComplete();

                        data = respnse.getData();
                        if (data != null) {
                            //设置数据
                            setHeaderData(data);

                            tvAllPay.setVisibility(View.GONE);

                            //设置listView数据
                            List<HasLgTzgPublishedDetailsModel.LaborInfoListBean> labor_info_list = data.getLabor_info_list();
                            if (labor_info_list != null) {
                                list.clear();
                                if (labor_info_list.size()>0){
                                    lay_ll_tips.setVisibility(View.VISIBLE);
                                }else{
                                    lay_ll_tips.setVisibility(View.GONE);
                                }
                                list.addAll(labor_info_list);
                                adapter.notifyDataSetChanged();
                                adapter.loadMoreEnd();
                                //匹配到人才把温馨提示显示出来
                                if (getArguments().getInt("showAddress", 1) == 0) {
                                    lay_ll_address.setVisibility(View.GONE);
                                    lay_ll_tips.setVisibility(View.GONE);
                                    list.clear();
                                    adapter.notifyDataSetChanged();
                                    adapter.loadMoreEnd();
                                } else {
                                    if (TextUtils.isEmpty(data.getAddress())){
                                        lay_ll_address.setVisibility(View.GONE);
                                    }else {
                                        lay_ll_address.setVisibility(View.VISIBLE);
                                    }
                                }
                                //支付方式 1.全额支付 2.只支付信息费用。 如果pay_type=1
                                if (data.getPay_type() == 1) {
                                    //处理数据
                                    boolean hasAllPay = true;
                                    HH:
                                    for (HasLgTzgPublishedDetailsModel.LaborInfoListBean laborInfoListBean : labor_info_list) {
                                        if (laborInfoListBean.getIs_complaint() == 1 || laborInfoListBean.getIs_complaint() == 2) {//如果在投诉状态，则跳过这条
                                            break HH;
                                        }
                                        if (laborInfoListBean.getIs_pay() == 0) {
                                            hasAllPay = false;
                                            break HH;
                                        }
                                    }
                                    if (!hasAllPay) {
                                        tvAllPay.setVisibility(View.VISIBLE);
                                        if (getArguments().getInt("showAddress", 1) == 0) {
                                            tvAllPay.setVisibility(View.GONE);
                                        }
                                        tvAllPay.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                final BaseDialog baseDialog = new BaseDialog(getActivity());
                                                baseDialog.showTwoButton("提示", "您确定要支付全部人员工资？", "确定", "取消", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        baseDialog.dismiss();
                                                        payAll();
                                                    }
                                                }, new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        baseDialog.dismiss();
                                                    }
                                                });

                                            }
                                        });
                                    }
                                } else {
                                    //只支付信息费用 只显示评价按钮 评价完就显示已评价
                                }
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<HasLgTzgPublishedDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 全额支付
     */
    private void payAll() {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_ALL_PAY)
                .addParams("recruitment_id", "" + getArguments().getInt("recruitment_id"))
                .setCallBackData(new BaseNewNetModelimpl() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        dismiss();
                        if (respnse.getCode() == 0) {
                            //成功
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

    //设置头部数据
    private void setHeaderData(final HasLgTzgPublishedDetailsModel data) {
        mTv_bianhao.setText(data.getOrder_sn());
        mTv_bianti.setText(data.getTitle());
        if (data.getWork_type_list() != null) {
            StringBuilder result = new StringBuilder();
            for (HasLgTzgPublishedDetailsModel.WorkTypeListBean workTypeListBean : data.getWork_type_list()) {
                if (!TextUtils.isEmpty(workTypeListBean.getOther_work_type())) {
                    result.append(workTypeListBean.getOther_work_type() + "  " + workTypeListBean.getNum() + "人  ￥" + workTypeListBean.getPrice() + "/人\n");
                } else {
                    result.append(workTypeListBean.getName() + "  " + workTypeListBean.getNum() + "人  ￥" + workTypeListBean.getPrice() + "/人\n");
                }
            }
            if (!TextUtils.isEmpty(result)) {
               String str =  result.substring(0, result.length() - "\n".length());
                mTv_gongzhong.setText(StringUtil.notEmptyBackValue(str));
            }

        }
        if (data.getSalary_type()==1){
            tv_xinzi.setText("市场定价");
        }else if(data.getSalary_type() == 2){
            if (!TextUtils.isEmpty(data.getSalary_fee())&& !TextUtils.isEmpty(data.getEnd_salary_fee()) ){
                tv_xinzi.setText(data.getSalary_fee()+"-"+data.getEnd_salary_fee()+"元");
            }
        }else if (data.getSalary_type()  == 3){
            tv_xinzi.setText("面议");
        }

        mTv_diqu.setText(data.getWork_second_area_name() + data.getWork_third_area_name());
        mTv_address.setText(data.getAddress());
        mTv_date.setText(data.getWork_date());
        mTv_desc.setText(data.getWork_info());
        if (data.getWork_hour() == 0){
            mTv_time.setText("全天");
        }else{
            mTv_time.setText(data.getWork_hour()+"小时");
        }
//        mTv_time.setText(new DateTime(1000 * Long.parseLong(data.getMatch_time())).toString("HH:mm"));
        mTv_num.setText("还差" + data.getNum() + "人");

        mTv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtil.isAllNotEmpty(data.getLongitude(), data.getLatitude())) {
                    MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(data.getLongitude()), Double.parseDouble(data.getLatitude()), data.getAddress());
                }
            }
        });

        String zhaopin_pic_urls = data.getImage_url();
        if (!TextUtils.isEmpty(zhaopin_pic_urls)) {
            List<String> zhaopinPicList = Arrays.asList(zhaopin_pic_urls.split(","));
            zhaopinListPic.clear();
            if (zhaopinPicList != null){
                if (zhaopinPicList.size() == 0){
                    tv_zhaopin_pic_tips.setVisibility(View.GONE);
                }else{
                    tv_zhaopin_pic_tips.setVisibility(View.VISIBLE);
                }
                zhaopinListPic.addAll(zhaopinPicList);
                zhaopinAdapter.notifyDataSetChanged();
            }
        }else{
            tv_zhaopin_pic_tips.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(data.getVideo_url())) {
            lay_rl_zhaopin_video.setVisibility(View.VISIBLE);
            iv_zhaopin_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(data.getVideo_url())) {
                        Bundle bundle = new Bundle();
                        bundle.putString(VideoPlayActivity.VIDEO_URL, data.getVideo_url());
                        startAtvDonFinish(VideoPlayActivity.class, bundle);
                    }
                }
            });
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
                                        iv_zhaopin_video.setImageBitmap(bitmap);
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
            lay_rl_zhaopin_video.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(data.getVoice_url())){
            lay_ll_zhaopin_voice_content.setVisibility(View.VISIBLE);
            zhaoPinvoiceUtils = new VoiceUtils(iv_zhaopin_voice);
            zhaoPinvoiceUtils.setFilePath(data.getVoice_url());
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

    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    @Override
    public boolean autoRefresh() {
        return true;
    }

    private RecyclerViewAdapter adapter;
    private List<HasLgTzgPublishedDetailsModel.LaborInfoListBean> list;

    public void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        initHeader();

        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);

        adapter.isUseEmpty(false);

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
    }

    //设置header
    private void initHeader() {
        View headerView = View.inflate(getActivity(), R.layout.fragment_yonggong_detail, null);
        mTv_bianhao = (TextView) headerView.findViewById(R.id.tv_bianhao);
        mTv_bianti = (TextView) headerView.findViewById(R.id.tv_bianti);
        mTv_gongzhong = (TextView) headerView.findViewById(R.id.tv_gongzhong);
        mTv_num = (TextView) headerView.findViewById(R.id.tv_num);
        tv_xinzi = (TextView) headerView.findViewById(R.id.tv_xinzi);
        mTv_diqu = (TextView) headerView.findViewById(R.id.tv_diqu);
        mTv_address = (TextView) headerView.findViewById(R.id.tv_address);
        mTv_date = (TextView) headerView.findViewById(R.id.tv_date);
        mTv_desc = (TextView) headerView.findViewById(R.id.tv_desc);
        mTv_finish = (TextView) headerView.findViewById(R.id.tv_finish);
        tv_tips = (TextView) headerView.findViewById(R.id.tv_tips);
        mTv_time = (TextView) headerView.findViewById(R.id.tv_match_time);
        lay_ll_address = (LinearLayout) headerView.findViewById(R.id.lay_ll_address);
        lay_ll_tips = (LinearLayout) headerView.findViewById(R.id.lay_ll_tips);
        tv_zhaopin_pic_tips = (TextView) headerView.findViewById(R.id.tv_zhaopin_pic_tips);


        lay_ll_zhaopin_voice = (LinearLayout) headerView.findViewById(R.id.lay_ll_zhaopin_voice);
        lay_ll_zhaopin_voice_content = (LinearLayout) headerView.findViewById(R.id.lay_ll_zhaopin_voice_content);
        iv_zhaopin_voice = (ImageView) headerView.findViewById(R.id.iv_zhaopin_voice);
        tv_zhaopin_length = (TextView) headerView.findViewById(R.id.tv_zhaopin_length);
        zhaopin_recyclerview = (RecyclerView) headerView.findViewById(R.id.zhaopin_recyclerview);
        lay_rl_zhaopin_video = (RelativeLayout) headerView.findViewById(R.id.lay_rl_zhaopin_video);
        iv_zhaopin_video = (ImageView) headerView.findViewById(R.id.iv_zhaopin_video);
        iv_zhaopin_play = (ImageView) headerView.findViewById(R.id.iv_zhaopin_play);


//        tv_tips.setText("1.管家默认在工作日期当天20:00自动打款给工作方;\n2.如对方的工作已完成，请点击确认支付，管家将立即打款给对方;\n3.如对方工作有问题，请点击投诉，管家将为您冻结该笔资金，管家客服将介入处理。");
        tv_tips.setText("如已完成工作，请进行评价");
        lay_ll_tips.setVisibility(View.GONE);//默认隐藏温馨提示，匹配到人才显示出来
        mTv_finish.setVisibility(View.GONE);
        adapter.setHeaderView(headerView);
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<HasLgTzgPublishedDetailsModel.LaborInfoListBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<HasLgTzgPublishedDetailsModel.LaborInfoListBean> data) {
            super(R.layout.item_recycler_has_match_resume, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final HasLgTzgPublishedDetailsModel.LaborInfoListBean item) {
            helper.setText(R.id.tv_name, item.getName() + "  " + (item.getSex() == 1 ? "男" : "女"))
                    .setText(R.id.tv_position, "申请职位：" + item.getLabor_recruitment_info_name())
                    .setText(R.id.tv_phone, "联系电话：" + item.getContact_tel())
                    .setText(R.id.tv_location, item.getWork_second_area_name() + item.getWork_third_area_name());

            helper.getView(R.id.tv_confirm).setVisibility(View.GONE);
            helper.getView(R.id.tv_tousu).setVisibility(View.GONE);

            helper.getView(R.id.tv_shishi).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_shishi).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.jumpToShowMapZhaopin(getActivity(), ShowMapZhaoPinActivity.TYPE_LWZJ, item.getInfo_id(), data.getLongitude(), data.getLatitude());
                }
            });

            getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BackFragmentActivity) getActivity()).addFragment(JianliDetailsFragment.newInstance(item.getRelate_id(),getArguments().getInt("roleType")), true, true);
                }
            });
            //是否已支付 0.未支付 1.已支付
            //支付方式 1.全额支付 2.只支付信息费用。 如果pay_type=1
            if (data.getPay_type() == 1) {
                helper.getView(R.id.tv_confirm).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_tousu).setVisibility(View.VISIBLE);
                if (item.getIs_pay() == 1) {
                    //是否评价 0 未评价 大于0评价 evaluation_id

                    //隐藏投诉按钮
                    helper.getView(R.id.tv_tousu).setVisibility(View.GONE);

                    helper.setText(R.id.tv_confirm, "已支付");
                    helper.getView(R.id.tv_confirm).setBackground(null);

                    helper.getView(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                } else {
                    //是否已投诉 0.未投诉 1.已投诉 is_complaint 2客服已处理
                    if (item.getIs_complaint() == 0) {
                        helper.setText(R.id.tv_tousu, "投诉");
                        helper.getView(R.id.tv_tousu).setBackgroundResource(R.drawable.shape_border_orange_f79c59);

                        helper.getView(R.id.tv_tousu).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (item.getIs_complaint() == 0) {
                                    //todo 此处需要加上客服投诉电话
                                    final BaseDialog baseDialog = new BaseDialog(getActivity());
                                    baseDialog.showThreeButton("投诉提示", "如需投诉请点击确认，管家将冻结该笔资金，请您在两小时内联系客服处理，逾期视为自动放弃，平台将默认打款。\n客服电话：" + data.getConsumer_hotline(), "确认", "取消", "拨打电话", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            baseDialog.dismiss();
                                            ((BackFragmentActivity) getActivity()).
                                                    addFragment(TouSuFragment.newInstance(getArguments().getInt("roleType"), getArguments().getInt("relate_id")), true, true);
//                                            complaint(item.getRelate_id(), helper.getAdapterPosition() - 1);
                                        }
                                    }, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            baseDialog.dismiss();
                                        }
                                    }, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            baseDialog.dismiss();
                                            PhoneUtils.dial(data.getConsumer_hotline());
                                        }
                                    });
                                }
                            }
                        });

                        //去支付
                        helper.setText(R.id.tv_confirm, "确认支付");
                        helper.getView(R.id.tv_confirm).setBackgroundResource(R.drawable.shape_border_yellow_fbb939);

                        helper.getView(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final BaseDialog baseDialog = new BaseDialog(getActivity());
                                baseDialog.showTwoButton("提示", "您确定要支付工资？", "确定", "取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        baseDialog.dismiss();
                                        pay(item.getRelate_id(), helper.getAdapterPosition() - 1);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        baseDialog.dismiss();
                                    }
                                });
                            }
                        });
                    } else if (item.getIs_complaint() == 1) {
                        helper.setText(R.id.tv_tousu, "已投诉");
                        helper.getView(R.id.tv_tousu).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final BaseDialog baseDialog = new BaseDialog(getActivity());
                                baseDialog.showTwoButton("提示", "客服电话：" + data.getConsumer_hotline(), "确认", "取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        baseDialog.dismiss();
                                        PhoneUtils.dial(data.getConsumer_hotline());
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        baseDialog.dismiss();
                                    }
                                });
                            }
                        });
                        //隐藏支付按钮
                        helper.getView(R.id.tv_confirm).setVisibility(View.GONE);

                    } else if (item.getIs_complaint() == 2) {
                        helper.setText(R.id.tv_tousu, "客服已处理");
                        helper.getView(R.id.tv_tousu).setBackground(null);
                        helper.getView(R.id.tv_tousu).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        });

                        //隐藏支付按钮
                        helper.getView(R.id.tv_confirm).setVisibility(View.GONE);
                    }
                }
            } else {
                helper.getView(R.id.tv_confirm).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_tousu).setVisibility(View.GONE);
                //是否评价 0 未评价 大于0评价 evaluation_id
                if (item.getEvaluation_id() == 0) {
                    //未评价
                    helper.setText(R.id.tv_confirm, "去评价");
                    helper.getView(R.id.tv_confirm).setBackgroundResource(R.drawable.shape_border_yellow_fbb939);

                    helper.getView(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //去评价
                            ((BackFragmentActivity) getActivity()).addFragment(PingjiaFragment.newInstance(PageConfig.TYPE_ZHONGJIE, item.getRelate_id()), true, true);
                        }
                    });
                } else {
                    //以评价
                    helper.setText(R.id.tv_confirm, "已评价");
                    helper.getView(R.id.tv_confirm).setBackground(null);

                    helper.getView(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            }

            //打电话
            helper.getView(R.id.iv_phone).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                    if (checkPermission(permissions)) {
                        PhoneUtils.dial(item.getContact_tel());
                    }
                }
            });

            //定位
            helper.getView(R.id.tv_location).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (StringUtil.isAllNotEmpty(data.getLongitude(), data.getLatitude())) {
                        MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(data.getLongitude()), Double.parseDouble(data.getLatitude()), data.getAddress());
                    }
                }
            });
        }
    }

    //取消投诉
    private void cancleTousu(HasLgTzgPublishedDetailsModel.LaborInfoListBean item) {

    }

    //投诉
    private void complaint(final int relate_id, final int position) {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_COMPLAINT)
                .addParams("relate_id", relate_id + "")
                .setCallBackData(new BaseNewNetModelimpl() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        dismiss();
                        if (respnse != null && respnse.getCode() == 0) {
                            list.get(position).setIs_complaint(1);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                }).buildPost());
    }

    /**
     * 支付
     *
     * @param relate_id
     */
    private void pay(final int relate_id, final int position) {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_CONFIRM_PAY)
                .addParams("relate_id", relate_id + "")
                .setCallBackData(new BaseNewNetModelimpl() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        dismiss();
                        if (respnse != null && respnse.getCode() == 0) {
                            list.get(position).setIs_pay(1);
                            adapter.notifyDataSetChanged();
                            refreshPageData();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                }).buildPost());
    }

    @Override
    public boolean isNeedEventBus() {
        return true;
    }

    @Subscribe
    public void notifyPageRefresh(NotifyPageRefresh event) {
        if (event.getTag().equals(TAG)) {
            refreshPageData();
        }
    }
    public void initPicAdapter() {

        zhaopinListPic = new ArrayList<>();
        zhaopinAdapter = new PicRecyclerViewAdapter(zhaopinListPic);
        zhaopin_recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 3));
        zhaopin_recyclerview.setNestedScrollingEnabled(false);
        zhaopin_recyclerview.setAdapter(zhaopinAdapter);

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
