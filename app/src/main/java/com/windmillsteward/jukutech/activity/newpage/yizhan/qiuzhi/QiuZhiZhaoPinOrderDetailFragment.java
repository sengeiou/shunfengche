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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.VideoPlayActivity;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.model.GetPhoneModel;
import com.windmillsteward.jukutech.activity.newpage.model.QiuZhiZhaoPinOrderDetailsModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.VoiceUtils;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.PingjiaFragment;
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
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class QiuZhiZhaoPinOrderDetailFragment extends BaseInitFragment {

    public static final String TAG = "QiuZhiZhaoPinOrderDetailFragment";
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;

    TextView tvTips;
    LinearLayout layLlTips;

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

    TextView tvPipeiStatus;
    TextView tvMyPublishFaBusj;
    TextView tvMyPublishXinxifei;

    private LinearLayout lay_ll_zhaopin_voice;
    private LinearLayout lay_ll_zhaopin_voice_content;
    private ImageView iv_zhaopin_voice;
    private TextView tv_zhaopin_length;
    private RecyclerView zhaopinRecyclerview;
    private RelativeLayout lay_rl_zhaopin_video;
    private ImageView iv_zhaopin_video;
    private ImageView iv_zhaopin_play;
    private LinearLayout lay_ll_zhaopin_age;
    private LinearLayout lay_ll_zhaopin_address;

    private RecyclerViewAdapter adapter;

    private List<QiuZhiZhaoPinOrderDetailsModel.RecruitmentResumeBean> list;
    private QiuZhiZhaoPinOrderDetailsModel data;

    private ArrayList<String> zhaopinListPic;
    private PicRecyclerViewAdapter zhaopinAdapter;
    private VoiceUtils zhaoPinvoiceUtils;
    private TextView tv_zhaopin_pic_tips;



    public static QiuZhiZhaoPinOrderDetailFragment newInstance(int relate_id) {
        Bundle args = new Bundle();
        args.putInt("relate_id", relate_id);
        QiuZhiZhaoPinOrderDetailFragment fragment = new QiuZhiZhaoPinOrderDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_qiuzhi_zhaopin_order_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("求职招聘-我要招聘详情");
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
                        startManagerActivity(CommonActivityManager.class, ZhaopinInfoDetailsFragment.TAG, bundle);
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
        View footerView = View.inflate(getActivity(), R.layout.footer_qiuzhi_zhaopin_order_detail, null);

        tvMyPublishFaBusj = (TextView) footerView.findViewById(R.id.tv_my_publish_fabusj);
        tvBianhao = (TextView) footerView.findViewById(R.id.tv_bianhao);
        tvBianti = (TextView) footerView.findViewById(R.id.tv_bianti);
        tvMyPublishXinxifei = (TextView) footerView.findViewById(R.id.tv_my_publish_xinxifei);

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
        lay_ll_zhaopin_age = (LinearLayout) footerView.findViewById(R.id.lay_ll_zhaopin_age);
        lay_ll_zhaopin_address = (LinearLayout) footerView.findViewById(R.id.lay_ll_zhaopin_address);

        adapter.addFooterView(footerView);
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_QIUZHI_ZHAOPIN_ORDER_DETAIL)
                .addParams("job_id", getArguments().getInt("relate_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<QiuZhiZhaoPinOrderDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        commonRefresh.refreshComplete();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<QiuZhiZhaoPinOrderDetailsModel> respnse, String source) {
                        showContentView();
                        commonRefresh.refreshComplete();
                        if (respnse.getData() != null) {
                            data = respnse.getData();
                            setData(respnse.getData());
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<QiuZhiZhaoPinOrderDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    private void setData(final QiuZhiZhaoPinOrderDetailsModel data) {
        int is_match = data.getIs_match();
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
        } else {
            tvPipeiStatus.setTextColor(Color.parseColor("#239491"));
            tvPipeiStatus.setText("正在匹配中");
        }

        //--------------------------匹配到的人-------------|\\\\\\\\\\\\\\\\\\\\\\\\
        List<QiuZhiZhaoPinOrderDetailsModel.RecruitmentResumeBean> recruitment_resume_list = data.getRecruitment_resume_list();
        if (recruitment_resume_list != null) {
            list.clear();
            if (recruitment_resume_list.size() > 0) {
                layLlTips.setVisibility(View.VISIBLE);
            }
            list.addAll(recruitment_resume_list);
            adapter.notifyDataSetChanged();
        }
        ///////////////////------------------------应聘方信息-------------------------\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        //发布时间
        tvMyPublishFaBusj.setText(DateUtil.StampTimeToDate(data.getAdd_time() + "", "yyyy-MM-dd HH:mm:ss"));
        tvBianhao.setText(TextUtils.isEmpty(data.getOrder_sn()) ? "" : data.getOrder_sn());
        tvBianti.setText(TextUtils.isEmpty(data.getTitle()) ? "" : data.getTitle());
        //信息费
        tvMyPublishXinxifei.setText(data.getInfo_fee() + "元");
        tvZhaopinGangwei.setText(TextUtils.isEmpty(data.getJob_name()) ? "" : data.getJob_name());
        String sex = "";
        if (data.getSex() == 1) {
            sex = "男";
        } else if (data.getSex() == 2) {
            sex = "女";
        } else {
            sex = "不限";
        }
        tvZhaopinSex.setText(sex);
        if (TextUtils.isEmpty(data.getAge_range_name())){
             lay_ll_zhaopin_age.setVisibility(View.GONE);
        }else{
            lay_ll_zhaopin_age.setVisibility(View.VISIBLE);
            tvZhaopinAge.setText(data.getAge_range_name());
        }

        tvZhaopinRenshu.setText(data.getRecruitment_num() + "人");
        tvZhaopinEducation.setText(TextUtils.isEmpty(data.getEducation_background_name()) ? "" : data.getEducation_background_name());
        tvZhaopinJingyan.setText(TextUtils.isEmpty(data.getWork_year_name()) ? "" : data.getWork_year_name());
        if (StringUtil.isAllNotEmpty(data.getWork_second_area_name(), data.getWork_third_area_name())) {
            tvZhaopinDiqu.setText(data.getWork_second_area_name() + data.getWork_third_area_name());
        }
        final String longitude = data.getLongitude();
        final String latitude = data.getLatitude();
        final String address = data.getAddress();
        tvZhaopinAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtil.isAllNotEmpty(longitude, latitude)) {
                    MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(longitude), Double.parseDouble(latitude), address);
                }
            }
        });
        if (TextUtils.isEmpty(address)){
           lay_ll_zhaopin_address.setVisibility(View.GONE);
        }else{
            lay_ll_zhaopin_address.setVisibility(View.VISIBLE);
            tvZhaopinAddress.setText(TextUtils.isEmpty(address) ? "" : address);//详细地址
        }
        int salary_type = data.getSalary_type();
        if (salary_type == 2) {
            tvZhaopinXinzi.setText(data.getSalary_fee() + "-" + data.getEnd_salary_fee() + "元");
        } else if (salary_type == 3) {
            tvZhaopinXinzi.setText("面议");
        }
        tvZhaopinFuli.setText(TextUtils.isEmpty(data.getBenefit_name()) ? "" : data.getBenefit_name());
        tvZhaopinDesc.setText(TextUtils.isEmpty(data.getDescription()) ? "" : data.getDescription());
        String zhaopin_pic_urls = data.getImage_url();
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

    private void addOnClick(final int type, final int job_resume_id, final QiuZhiZhaoPinOrderDetailsModel.RecruitmentResumeBean item) {
        if (type == 1) {//不感兴趣
            unInterested(job_resume_id);
        } else if (type == 2) {//感兴趣
            final BaseDialog baseDialog = new BaseDialog(getActivity());
            if (TextUtils.isEmpty(item.getMobile_phone())) {

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
            }else{
                baseDialog.showTwoButton("提示", "是否拨打电话?\n" + item.getTrue_name() + "\n" + item.getMobile_phone(), "确定", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseDialog.dismiss();
                        String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                        if (checkPermission(permissions)) {
                            PhoneUtils.dial(item.getMobile_phone());
                        }
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseDialog.dismiss();
                    }
                });
            }
        }else if (type == 3){//评价
            Bundle bundle = new Bundle();
            bundle.putInt("relate_id", job_resume_id);
            bundle.putInt("roleType", PageConfig.TYPE_ZHAOPIN);
            startManagerActivity(CommonActivityManager.class, PingjiaFragment.TAG, bundle);
        }

    }

    //获取联系方式
    private void getPhone(int job_resume_id) {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_GET_JOB_SEEKER_CONTACT_INFORMATION)
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
                            baseDialog.showTwoButton("提示", "是否拨打电话?\n" + respnse.getData().getTrue_name() + "\n" + respnse.getData().getMobile_phone()
                                    , "拨打", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
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
                            addCall(new NetUtil().setUrl(APIS.URL_TALENT_UNINTERESTED_JOB_SEEKER)
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


    class RecyclerViewAdapter extends BaseQuickAdapter<QiuZhiZhaoPinOrderDetailsModel.RecruitmentResumeBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<QiuZhiZhaoPinOrderDetailsModel.RecruitmentResumeBean> data) {
            super(R.layout.item_recycler_has_match_qiuzhi_zhaopin, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final QiuZhiZhaoPinOrderDetailsModel.RecruitmentResumeBean item) {
            String sex ="";
            if (item.getSex() == 1){
                sex = "男";
            }else if (item.getSex() == 2){
                sex = "女";
            }else{
                sex = "不限";
            }
            helper.setText(R.id.tv_name_and_age, item.getTrue_name() + " "+sex+" " + item.getAge() + "岁")
                    .setText(R.id.tv_diqu, item.getWork_third_area_name())
                    .setText(R.id.tv_edu, item.getEducation_background_name())
                    .setText(R.id.tv_zhiwei, item.getJob_name());

            GlideUtil.show(getActivity(), item.getUser_avatar_url(), (CircleImageView) helper.getView(R.id.iv_avatar));
            TextView tvPipei = (TextView) helper.getView(R.id.tv_pipei);
            tvPipei.setText(new SpanUtils().append("匹配度：")
                    .append(item.getMatch_value() + "%")
                    .setForegroundColor(ResUtils.getCommRed())
                    .create());
            String salary = "";
            int salary_type = item.getSalary_type();
            if (salary_type == 2) {
                salary = item.getSalary_fee() + "-" + item.getEnd_salary_fee();
            } else if (salary_type == 3) {
                salary = "面议";
            }
            TextView tv_salary = (TextView) helper.getView(R.id.tv_salary);
            tv_salary.setText(salary);
            final TextView tv_interestring = (TextView) helper.getView(R.id.tv_interestring);
            final TextView tv_call_phone = (TextView) helper.getView(R.id.tv_call_phone);
            final TextView tv_evalution = (TextView) helper.getView(R.id.tv_evalution);
            int is_uninterested = item.getIs_uninterested();
            if (is_uninterested == 1) {
                tv_interestring.setVisibility(View.GONE);
            }
            int is_view = item.getIs_view();//是否获取过联系方式
            if (is_view ==1){
                tv_interestring.setVisibility(View.GONE);
                tv_evalution.setVisibility(View.VISIBLE);
                tv_call_phone.setText("拨打电话");
            }
            if (item.getIs_evaluation() == 0){
                tv_evalution.setText("评价");
            }else{
                tv_evalution.setText("已评价");
            }
            if (data != null) {
                if (data.getUninterested_num() == 0) {
                    tv_interestring.setVisibility(View.GONE);
                }
                if (data.getIs_match() == 4){
                    tv_interestring.setVisibility(View.GONE);
                }
            }

            tv_evalution.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getIs_evaluation()==0) {
                        addOnClick(3, item.getJob_resume_id(), item);
                    }
                }
            });

            tv_interestring.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addOnClick(1, item.getJob_resume_id(),item);
                }
            });

            tv_call_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addOnClick(2, item.getJob_resume_id(),item);
                }
            });
        }
    }

    @Override
    public boolean autoRefresh() {
        return true;
    }

    public void initPicAdapter() {
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
