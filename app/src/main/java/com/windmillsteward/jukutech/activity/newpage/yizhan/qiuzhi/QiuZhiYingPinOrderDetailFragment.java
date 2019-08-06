package com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi;

import android.Manifest;
import android.content.ContentResolver;
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

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.VideoPlayActivity;
import com.windmillsteward.jukutech.activity.newpage.model.GetPhoneModel;
import com.windmillsteward.jukutech.activity.newpage.model.HasZhaopinPublishedDetailsModel;
import com.windmillsteward.jukutech.activity.newpage.model.QiuZhiYingPinOrderDetailsModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.VoiceUtils;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.HasLgTzgPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.YonggongDetailFragment;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
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
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class QiuZhiYingPinOrderDetailFragment extends BaseInitFragment {
    public static final String TAG = "QiuZhiYingPinOrderDetailFragment";
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;

    TextView tvTips;
    LinearLayout layLlTips;

    CircleImageView ivAvatar;
    TextView tvName;
    TextView tvPipei;
    TextView tvPhone;
    TextView tvTime;
    ImageView ivPhone;
    LinearLayout layLlHeader;

    TextView tvBianhao;
    TextView tvBianti;
    TextView tvZhaopinGangwei;
    TextView tvZhaopinSex;
    TextView tvZhaopinAge;
    TextView tvZhaopinEducation;
    TextView tvZhaopinJingyan;
    TextView tvZhaopinRenshu;
    TextView tvZhaopinDiqu;
    TextView tvZhaopinAddress;
    TextView tvZhaopinXinzi;
    TextView tvZhaopinFuli;
    TextView tvZhaopinDesc;
    LinearLayout layLlWorkDetail;

    TextView tvPipeiStatus;
    TextView tvMyPublishFaBusj;
    TextView tvMyPublishXinxifei;
    TextView tvMyPublishDiqu;
    TextView tvMyPublishXinzi;
    TextView tvMyPublishDesc;
    TextView tvMyPublishName;
    TextView tvMyPublishSex;
    TextView tvMyPublishAge;
    TextView tvMyPublishContactTel;
    TextView tvMyPublishEducation;
    TextView tvMyPublishGangwei;
    TextView tvMyPublishJingyan;
    TextView tvMyPublishFuli;


    private RecyclerViewAdapter adapter;

    private List<QiuZhiYingPinOrderDetailsModel.RecruitmentJobListBean> list;
    private QiuZhiYingPinOrderDetailsModel data;
    private LinearLayout lay_ll_zhaopin_voice;
    private LinearLayout lay_ll_zhaopin_voice_content;
    private ImageView iv_zhaopin_voice;
    private TextView tv_zhaopin_length;
    private RecyclerView zhaopinRecyclerview;
    private RelativeLayout lay_rl_zhaopin_video;
    private ImageView iv_zhaopin_video;
    private ImageView iv_zhaopin_play;

    private LinearLayout lay_ll_my_publish_voice;
    private LinearLayout lay_ll_my_publish_voice_content;
    private ImageView iv_my_publish_voice;
    private TextView tv_my_publish_length;
    private RecyclerView myPublishRecyclerview;
    private RelativeLayout lay_rl_my_publish_video;
    private ImageView iv_my_publish_video;
    private ImageView iv_my_publish_play;
    private TextView tv_zhaopin_pic_tips;
    private TextView tv_my_publish_pic_tips;
    private LinearLayout lay_ll_my_publish_age;

    private ArrayList<String> myPublishListPic;
    private ArrayList<String> zhaopinListPic;
    private PicRecyclerViewAdapter myPublishAdapter;
    private PicRecyclerViewAdapter zhaopinAdapter;
    private VoiceUtils myPublishvoiceUtils;
    private VoiceUtils zhaoPinvoiceUtils;



    public static QiuZhiYingPinOrderDetailFragment newInstance(int relate_id) {
        Bundle args = new Bundle();
        args.putInt("relate_id", relate_id);
        QiuZhiYingPinOrderDetailFragment fragment = new QiuZhiYingPinOrderDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_qiuzhi_yingpin_order_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("求职招聘-我要找工作详情");
        initAdapter();
        initPicAdapter();
    }

    private void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        initHeader();
        initFooter();

        adapter.setEnableLoadMore(false);

        adapter.isUseEmpty(false);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (list != null) {
                    if (list.get(position) != null) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("job_resume_id", list.get(position).getJob_resume_id());
                        startManagerActivity(CommonActivityManager.class, ZhaopinPositionDetailsFragment.TAG, bundle);
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

    private void initHeader() {
        View headerView = View.inflate(getActivity(), R.layout.header_common_zhaopin_order_detail, null);
        tvTips = (TextView) headerView.findViewById(R.id.tv_tips);
        layLlTips = (LinearLayout) headerView.findViewById(R.id.lay_ll_tips);

        adapter.addHeaderView(headerView);
    }


    private void initFooter() {
        View footerView = View.inflate(getActivity(), R.layout.footer_qiuzhi_yingpin_order_detail, null);
        tvName = (TextView) footerView.findViewById(R.id.tv_name);
        tvPipei = (TextView) footerView.findViewById(R.id.tv_pipei);
        tvPhone = (TextView) footerView.findViewById(R.id.tv_phone);
        tvTime = (TextView) footerView.findViewById(R.id.tv_time);
        tvBianhao = (TextView) footerView.findViewById(R.id.tv_bianhao);
        tvBianti = (TextView) footerView.findViewById(R.id.tv_bianti);
        tvZhaopinGangwei = (TextView) footerView.findViewById(R.id.tv_zhaopin_gangwei);
        tvZhaopinSex = (TextView) footerView.findViewById(R.id.tv_zhaopin_sex);
        tvZhaopinAge = (TextView) footerView.findViewById(R.id.tv_zhaopin_age);
        tvZhaopinEducation = (TextView) footerView.findViewById(R.id.tv_zhaopin_education);
        tvZhaopinJingyan = (TextView) footerView.findViewById(R.id.tv_zhaopin_jingyan);
        tvZhaopinDiqu = (TextView) footerView.findViewById(R.id.tv_zhaopin_diqu);
        tvZhaopinRenshu = (TextView) footerView.findViewById(R.id.tv_zhaopin_renshu);
        tvZhaopinAddress = (TextView) footerView.findViewById(R.id.tv_zhaopin_address);
        tvZhaopinXinzi = (TextView) footerView.findViewById(R.id.tv_zhaopin_xinzi);
        tvZhaopinDesc = (TextView) footerView.findViewById(R.id.tv_zhaopin_desc);
        tvZhaopinFuli = (TextView) footerView.findViewById(R.id.tv_zhaopin_fuli);
        tvPipeiStatus = (TextView) footerView.findViewById(R.id.tv_pipei_status);

        lay_ll_zhaopin_voice = (LinearLayout) footerView.findViewById(R.id.lay_ll_zhaopin_voice);
        lay_ll_zhaopin_voice_content = (LinearLayout) footerView.findViewById(R.id.lay_ll_zhaopin_voice_content);
        iv_zhaopin_voice = (ImageView) footerView.findViewById(R.id.iv_zhaopin_voice);
        tv_zhaopin_length = (TextView) footerView.findViewById(R.id.tv_zhaopin_length);
        zhaopinRecyclerview = (RecyclerView) footerView.findViewById(R.id.zhaopin_recyclerview);
        lay_rl_zhaopin_video = (RelativeLayout) footerView.findViewById(R.id.lay_rl_zhaopin_video);
        iv_zhaopin_video = (ImageView) footerView.findViewById(R.id.iv_zhaopin_video);
        iv_zhaopin_play = (ImageView) footerView.findViewById(R.id.iv_zhaopin_play);
        tv_zhaopin_pic_tips = (TextView) footerView.findViewById(R.id.tv_zhaopin_pic_tips);

        tvMyPublishXinxifei = (TextView) footerView.findViewById(R.id.tv_my_publish_xinxifei);
        tvMyPublishDiqu = (TextView) footerView.findViewById(R.id.tv_my_publish_diqu);
        tvMyPublishFaBusj = (TextView) footerView.findViewById(R.id.tv_my_publish_fabusj);
        tvMyPublishName = (TextView) footerView.findViewById(R.id.tv_my_publish_name);
        tvMyPublishSex = (TextView) footerView.findViewById(R.id.tv_my_publish_sex);
        tvMyPublishAge = (TextView) footerView.findViewById(R.id.tv_my_publish_age);
        tvMyPublishContactTel = (TextView) footerView.findViewById(R.id.tv_my_publish_contact_tel);
        tvMyPublishEducation = (TextView) footerView.findViewById(R.id.tv_my_publish_education);
        tvMyPublishGangwei = (TextView) footerView.findViewById(R.id.tv_my_publish_gangwei);
        tvMyPublishJingyan = (TextView) footerView.findViewById(R.id.tv_my_publish_gongzuo_jingyan);
        tvMyPublishXinzi = (TextView) footerView.findViewById(R.id.tv_my_publish_xinzi);
        tvMyPublishFuli = (TextView) footerView.findViewById(R.id.tv_my_publish_fuli);
        tvMyPublishDesc = (TextView) footerView.findViewById(R.id.tv_my_publish_desc);
        lay_ll_my_publish_voice = (LinearLayout) footerView.findViewById(R.id.lay_ll_my_publish_voice);
        lay_ll_my_publish_voice_content = (LinearLayout) footerView.findViewById(R.id.lay_ll_my_publish_voice_content);
        iv_my_publish_voice = (ImageView) footerView.findViewById(R.id.iv_my_publish_voice);
        tv_my_publish_length = (TextView) footerView.findViewById(R.id.tv_my_publish_length);
        myPublishRecyclerview = (RecyclerView) footerView.findViewById(R.id.my_publish_recyclerview);
        lay_rl_my_publish_video = (RelativeLayout) footerView.findViewById(R.id.lay_rl_my_publish_video);
        iv_my_publish_video = (ImageView) footerView.findViewById(R.id.iv_my_publish_video);
        iv_my_publish_play = (ImageView) footerView.findViewById(R.id.iv_my_publish_play);
        tv_my_publish_pic_tips = (TextView) footerView.findViewById(R.id.tv_my_publish_pic_tips);
        lay_ll_my_publish_age = (LinearLayout) footerView.findViewById(R.id.lay_ll_my_publish_age);

        layLlHeader = (LinearLayout) footerView.findViewById(R.id.lay_ll_header);
        layLlWorkDetail = (LinearLayout) footerView.findViewById(R.id.lay_ll_work_detail);

        ivAvatar = (CircleImageView) footerView.findViewById(R.id.iv_avatar);
        ivPhone = (ImageView) footerView.findViewById(R.id.iv_phone);

        adapter.addFooterView(footerView);
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_QIUZHI_YINGPIN_ORDER_DETAIL)
                .addParams("resume_id", getArguments().getInt("relate_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<QiuZhiYingPinOrderDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        commonRefresh.refreshComplete();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<QiuZhiYingPinOrderDetailsModel> respnse, String source) {
                        showContentView();
                        commonRefresh.refreshComplete();
                        if (respnse.getData() != null) {
                            data = respnse.getData();
                            setData(respnse.getData());
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<QiuZhiYingPinOrderDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    private void setData(final QiuZhiYingPinOrderDetailsModel data) {
        int is_match = data.getIs_match();
        layLlWorkDetail.setVisibility(View.GONE);
        layLlHeader.setVisibility(View.GONE);
        layLlTips.setVisibility(View.GONE);
        //匹配成功，list会为空，则取招聘方发布信息实体，
        //还没有匹配or匹配失败or还没有获取联系方式，需要隐藏招聘方信息，等点击其中一人获取联系方式后，再显示
        if (is_match == 2) {//取招聘方发布信息实体
            tvPipeiStatus.setTextColor(Color.parseColor("#3172F4"));
            tvPipeiStatus.setText("已匹配");
        } else if (is_match == 3) {
            tvPipeiStatus.setTextColor(Color.parseColor("#fdbe44"));
            tvPipeiStatus.setText("匹配失败");
        } else if (is_match == 4) {
            //招聘方信息---start
            tvPipeiStatus.setTextColor(Color.parseColor("#FF0000"));
            tvPipeiStatus.setText("匹配成功");
            final QiuZhiYingPinOrderDetailsModel.RecruitmentBean recruitment_job = data.getRecruitment_job();
            if (recruitment_job != null) {
                layLlWorkDetail.setVisibility(View.VISIBLE);
                layLlHeader.setVisibility(View.VISIBLE);
                layLlTips.setVisibility(View.VISIBLE);
                tvTips.setText("尊敬的管家用户，恭喜您已与雇主匹配成功，请保持电话畅通，或及时与雇主联系。");
//                tvTime.setText("更新时间:"+DateUtil.StampTimeToDate(recruitment_job.getAdd_time() + "", "yyyy-MM-dd HH:mm"));
                tvBianhao.setText(TextUtils.isEmpty(data.getOrder_sn()) ? "" : data.getOrder_sn());
                tvBianti.setText(TextUtils.isEmpty(recruitment_job.getTitle()) ? "" : recruitment_job.getTitle());
                tvZhaopinGangwei.setText(TextUtils.isEmpty(recruitment_job.getJob_name()) ? "" : recruitment_job.getJob_name());
                tvZhaopinRenshu.setText(recruitment_job.getRecruitment_num() + "人");
                if (recruitment_job.getSex() ==0){
                    tvZhaopinSex.setText("不限");
                }else{
                    tvZhaopinSex.setText(recruitment_job.getSex() == 1 ? "男" : "女");
                }
                tvZhaopinAge.setText(TextUtils.isEmpty(recruitment_job.getAge_range_name()) ? "" : recruitment_job.getAge_range_name());
                tvZhaopinEducation.setText(TextUtils.isEmpty(recruitment_job.getEducation_background_name()) ? "" : recruitment_job.getEducation_background_name());
                tvZhaopinJingyan.setText(TextUtils.isEmpty(recruitment_job.getWork_year_name()) ? "" : recruitment_job.getWork_year_name());
                if (StringUtil.isAllNotEmpty(recruitment_job.getWork_second_area_name(), recruitment_job.getWork_third_area_name())) {
                    tvZhaopinDiqu.setText(recruitment_job.getWork_second_area_name() + recruitment_job.getWork_third_area_name());
                }
                final String longitude = recruitment_job.getLongitude();
                final String latitude = recruitment_job.getLatitude();
                final String address = recruitment_job.getAddress();
                tvZhaopinAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (StringUtil.isAllNotEmpty(longitude, latitude)) {
                            MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(longitude), Double.parseDouble(latitude), address);
                        }
                    }
                });
                tvZhaopinAddress.setText(TextUtils.isEmpty(address) ? "" : address);//详细地址
                int salary_type = recruitment_job.getSalary_type();
                if (salary_type == 2) {
                    tvZhaopinXinzi.setText(recruitment_job.getSalary_fee() + "-" + recruitment_job.getEnd_salary_fee() + "元");
                } else if (salary_type == 3) {
                    tvZhaopinXinzi.setText("面议");
                }
                tvZhaopinFuli.setText(TextUtils.isEmpty(recruitment_job.getBenefit_name()) ? "" : recruitment_job.getBenefit_name());
                tvZhaopinDesc.setText(TextUtils.isEmpty(recruitment_job.getDescription()) ? "" : recruitment_job.getDescription());
                String zhaopin_pic_urls = recruitment_job.getImage_url();
                if (!TextUtils.isEmpty(zhaopin_pic_urls)) {
                    List<String> zhaopinPicList = Arrays.asList(zhaopin_pic_urls.split(","));
                    zhaopinListPic.clear();
                    if (zhaopinPicList != null) {
                        if (zhaopinPicList.size() == 0) {
                            tv_zhaopin_pic_tips.setVisibility(View.GONE);
                        } else {
                            tv_zhaopin_pic_tips.setVisibility(View.VISIBLE);
                        }
                        zhaopinListPic.addAll(zhaopinPicList);
                        zhaopinAdapter.notifyDataSetChanged();
                    }
                }else{
                    tv_zhaopin_pic_tips.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(recruitment_job.getVideo_url())) {
                    lay_rl_zhaopin_video.setVisibility(View.VISIBLE);
                    iv_zhaopin_video.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (!TextUtils.isEmpty(recruitment_job.getVideo_url())) {
                                Bundle bundle = new Bundle();
                                bundle.putString(VideoPlayActivity.VIDEO_URL, recruitment_job.getVideo_url());
                                startAtvDonFinish(VideoPlayActivity.class, bundle);
                            }
                        }
                    });
                    MediaUtils.getImageForVideo(recruitment_job.getVideo_url(), new MediaUtils.OnLoadVideoImageListener() {
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
                if (!TextUtils.isEmpty(recruitment_job.getVoice_url())){
                    lay_ll_zhaopin_voice_content.setVisibility(View.VISIBLE);
                    zhaoPinvoiceUtils = new VoiceUtils(iv_zhaopin_voice);
                    zhaoPinvoiceUtils.setFilePath(recruitment_job.getVoice_url());
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
                //招聘人信息
                final String contact_tel = recruitment_job.getContact_tel();
                final String contact_person = recruitment_job.getContact_person();
                tvName.setText(TextUtils.isEmpty(recruitment_job.getContact_person()) ? "" : recruitment_job.getContact_person());
                tvPhone.setText(TextUtils.isEmpty(recruitment_job.getContact_tel()) ? "" : recruitment_job.getContact_tel());
                GlideUtil.show(getActivity(), recruitment_job.getUser_avatar_url(), ivAvatar);
                tvPipei.setText(new SpanUtils().append("匹配度：")
                        .append(recruitment_job.getMatch_value() + "%")
                        .setForegroundColor(ResUtils.getCommRed())
                        .create());
                ivPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final BaseDialog baseDialog = new BaseDialog(getActivity());
                        baseDialog.showTwoButton("提示", "是否拨打电话?\n" + contact_person + "\n" + contact_tel
                                , "拨打", "取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (baseDialog != null) {
                                            baseDialog.dismiss();
                                        }
                                        String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                                        if (checkPermission(permissions)) {
                                            PhoneUtils.dial(contact_tel);
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
            }
        } else {
            tvPipeiStatus.setTextColor(Color.parseColor("#239491"));
            tvPipeiStatus.setText("正在匹配中");
        }

        //--------------------------匹配到的人-------------|\\\\\\\\\\\\\\\\\\\\\\\\
        List<QiuZhiYingPinOrderDetailsModel.RecruitmentJobListBean> recruitment_job_list = data.getRecruitment_job_list();
        if (recruitment_job_list != null) {
            list.clear();
            if (recruitment_job_list.size() > 0) {
                layLlTips.setVisibility(View.VISIBLE);
            }
            list.addAll(recruitment_job_list);
            adapter.notifyDataSetChanged();
        }
        ///////////////////------------------------应聘方信息-------------------------\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        //发布时间
        tvMyPublishFaBusj.setText(DateUtil.StampTimeToDate(data.getAdd_time() + "", "yyyy-MM-dd HH:mm:ss"));
        //信息费
        tvMyPublishXinxifei.setText(data.getInfo_fee() + "元");
        tvMyPublishName.setText(TextUtils.isEmpty(data.getTrue_name()) ? "" : data.getTrue_name());
        tvMyPublishSex.setText(data.getSex() == 1 ? "男" : "女");
        if (data.getAge() == 0){
            lay_ll_my_publish_age.setVisibility(View.GONE);
        }else{
            lay_ll_my_publish_age.setVisibility(View.VISIBLE);
            tvMyPublishAge.setText(data.getAge() + "岁");
        }
        tvMyPublishContactTel.setText(TextUtils.isEmpty(data.getMobile_phone()) ? "" : data.getMobile_phone());
        tvMyPublishEducation.setText(TextUtils.isEmpty(data.getEducation_background_name()) ? "" : data.getEducation_background_name() );
        tvMyPublishGangwei.setText(TextUtils.isEmpty(data.getJob_name()) ? "" : data.getJob_name());
        tvMyPublishJingyan.setText(TextUtils.isEmpty(data.getWork_year_name()) ? "" : data.getWork_year_name());
        if (StringUtil.isAllNotEmpty(data.getWork_second_area_name(), data.getWork_third_area_name())) {
            tvMyPublishDiqu.setText(data.getWork_second_area_name() + data.getWork_third_area_name());
        }
        if (data.getSalary_type() == 2) {
            tvMyPublishXinzi.setText(data.getSalary_fee() + "-" + data.getEnd_salary_fee() + "元");
        } else if (data.getSalary_type() == 3) {
            tvMyPublishXinzi.setText("面议");
        }
        //福利
        tvMyPublishFuli.setText(TextUtils.isEmpty(data.getBenefit_name()) ? "" : data.getBenefit_name());
        //自我介绍
        tvMyPublishDesc.setText(TextUtils.isEmpty(data.getSelf_intro()) ? "" : data.getSelf_intro());
        String pic_urls = data.getImage_url();
        if (!TextUtils.isEmpty(pic_urls)) {
            List<String> mypublishPicList = Arrays.asList(pic_urls.split(","));
            myPublishListPic.clear();
            if (mypublishPicList != null) {
                if (mypublishPicList.size() == 0) {
                    tv_my_publish_pic_tips.setVisibility(View.GONE);
                } else {
                    tv_my_publish_pic_tips.setVisibility(View.VISIBLE);
                }
                myPublishListPic.addAll(mypublishPicList);
                myPublishAdapter.notifyDataSetChanged();
            }
        }else{
            tv_my_publish_pic_tips.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(data.getVideo_url())) {
            lay_rl_my_publish_video.setVisibility(View.VISIBLE);
            iv_my_publish_video.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    private void addOnClick(final int type, final int job_resume_id) {
        if (type == 1) {//不感兴趣
            unInterested(job_resume_id);
        } else if (type == 2) {//感兴趣
            final BaseDialog baseDialog = new BaseDialog(getActivity());
            baseDialog.showTwoButton("提示", "是否获取联系方式", "确定", "取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseDialog.dismiss();
                    getPhone(job_resume_id);
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
    private void getPhone(int job_resume_id) {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_GET_JOB_CONTACT_INFORMATION)
                .addParams("job_resume_id", job_resume_id + "")
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
                            baseDialog.showTwoButton("提示", "是否拨打电话?\n" + respnse.getData().getContact_person() + "\n" + respnse.getData().getContact_tel()
                                    , "拨打", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //获取联系方式后，需要把不感兴趣按钮隐藏
                                            refreshPageData();
                                            if (baseDialog != null) {
                                                baseDialog.dismiss();
                                            }
                                            String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                                            if (checkPermission(permissions)) {
                                                PhoneUtils.dial(respnse.getData().getContact_tel());
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
    private void unInterested(final int job_resume_id) {
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
                            addCall(new NetUtil().setUrl(APIS.URL_TALENT_UNINTERESTED_JOB)
                                    .addParams("job_resume_id", job_resume_id + "")
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


    class RecyclerViewAdapter extends BaseQuickAdapter<QiuZhiYingPinOrderDetailsModel.RecruitmentJobListBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<QiuZhiYingPinOrderDetailsModel.RecruitmentJobListBean> data) {
            super(R.layout.item_recycler_has_match_qiuzhi_yingpin, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final QiuZhiYingPinOrderDetailsModel.RecruitmentJobListBean item) {
            helper.setText(R.id.tv_title, item.getTitle())
                    .setText(R.id.tv_location, item.getWork_second_area_name() + item.getWork_third_area_name())
                    .setText(R.id.tv_type, item.getCompany_name());

            TextView tvPipei = (TextView) helper.getView(R.id.tv_pipei);
            tvPipei.setText(new SpanUtils().append("匹配度：")
                    .append(item.getMatch_value() + "%")
                    .setForegroundColor(ResUtils.getCommRed())
                    .create());
            TextView tv_salary_and_gangwei = (TextView) helper.getView(R.id.tv_salary_and_gangwei);
            String salary = "";
            int salary_type = item.getSalary_type();
            if (salary_type == 2) {
                salary = item.getSalary_fee() + "-" + item.getEnd_salary_fee()+"元/月";
            } else if (salary_type == 3) {
                salary = "面议";
            }
            tv_salary_and_gangwei.setText(new SpanUtils().append(salary)
                    .setForegroundColor(ResUtils.getColor(R.color.text_ff8819))
                    .append("  |  " + item.getJob_name())
                    .setForegroundColor(ResUtils.getColor(R.color.text_color_black))
                    .create());
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
                    addOnClick(1, item.getJob_resume_id());
                }
            });
            final TextView tv_call_phone = (TextView) helper.getView(R.id.tv_call_phone);
            tv_call_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addOnClick(2, item.getJob_resume_id());
                }
            });
        }
    }

    public void initPicAdapter() {
        myPublishListPic = new ArrayList<>();
        myPublishAdapter = new PicRecyclerViewAdapter(myPublishListPic);
        myPublishRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 3));
        myPublishRecyclerview.setNestedScrollingEnabled(false);
        myPublishRecyclerview.setAdapter(myPublishAdapter);

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
        zhaopinRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 4));
        zhaopinRecyclerview.setNestedScrollingEnabled(false);
        zhaopinRecyclerview.setAdapter(zhaopinAdapter);

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
