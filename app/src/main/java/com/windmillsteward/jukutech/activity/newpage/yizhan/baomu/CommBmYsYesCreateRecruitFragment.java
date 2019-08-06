package com.windmillsteward.jukutech.activity.newpage.yizhan.baomu;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.map.SelectCityFromMapActivity;
import com.windmillsteward.jukutech.activity.newpage.adapter.CommonPopupAdapter;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.common.model.PayInfoFeeModel;
import com.windmillsteward.jukutech.activity.newpage.common.popwindow.CommonPopwindow;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.AgeAreaModel;
import com.windmillsteward.jukutech.activity.newpage.model.BmYsYesConfig;
import com.windmillsteward.jukutech.activity.newpage.model.BmYsYesRencaiListDetailModel;
import com.windmillsteward.jukutech.activity.newpage.model.EducationModel;
import com.windmillsteward.jukutech.activity.newpage.model.PersonTypeModel;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.model.ServiceModel;
import com.windmillsteward.jukutech.activity.newpage.model.WorkExperienceModel;
import com.windmillsteward.jukutech.activity.newpage.model.WorkTimeModel;
import com.windmillsteward.jukutech.activity.newpage.newpublish.HomeCommonPublishActivity;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.common.SelectAreaActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.utils.RecorderUtils;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.interfaces.OnUserAuthenListener;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.LoginSuccessInfo;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.dialog.LoadingDialog;
import com.windmillsteward.jukutech.customview.dialog.PhoneCodeDialog;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.CalculateUtils;
import com.windmillsteward.jukutech.utils.EditTextUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.RegexChkUtil;
import com.windmillsteward.jukutech.utils.ResUtils;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.view.PickerViewWrap;
import com.windmillsteward.jukutech.utils.view.SpannableStringViewWrap;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import org.joda.time.DateTime;

import java.io.File;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DateTimePicker;

/**
 * A simple {@link Fragment} subclass.
 * 保姆-发布需求
 */
public class CommBmYsYesCreateRecruitFragment extends BaseInitFragment {
    public static final String TAG = "CommBmYsYesCreateRecruitFragment";
    @Bind(R.id.tv_leixing_tips)
    TextView tvLeixingTips;
    @Bind(R.id.tv_kemu)
    TextView tvKemu;
    @Bind(R.id.tv_neirong_tips)
    TextView tvNeirongTips;
    @Bind(R.id.tv_neirong)
    TextView tvNeirong;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.tv_sex_tips)
    TextView tvSexTips;

    @Bind(R.id.tv_age_tips)
    TextView tvAgeTips;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_xueli_tips)
    TextView tvXueliTips;
    @Bind(R.id.tv_xueli)
    TextView tvXueli;
    @Bind(R.id.tv_jingyan_tips)
    TextView tvJingyanTips;
    @Bind(R.id.tv_jingyan)
    TextView tvJingyan;
    @Bind(R.id.tv_gzshiijan_tips)
    TextView tvGzshiijanTips;
    @Bind(R.id.tv_gzshiijan)
    TextView tvGzshiijan;
    @Bind(R.id.tv_yuding_tips)
    TextView tvYudingTips;
    @Bind(R.id.tv_yuding1)
    TextView tvYuding1;
    @Bind(R.id.tv_yuding2)
    TextView tvYuding2;
    @Bind(R.id.tv_diqu_tips)
    TextView tvDiquTips;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_dizhi_tips)
    TextView tvDizhiTips;
    @Bind(R.id.ed_dizhi)
    EditText edDizhi;
    @Bind(R.id.iv_location)
    ImageView ivLocation;
    @Bind(R.id.et_jieshao)
    EditText etJieshao;
    @Bind(R.id.tv_user_tips)
    TextView tvUserTips;
    @Bind(R.id.ed_user)
    EditText edUser;
    @Bind(R.id.tv_phone_tips)
    TextView tvPhoneTips;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_change_phone)
    TextView tvChangePhone;
    @Bind(R.id.tv_money_info)
    TextView tvMoneyInfo;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    @Bind(R.id.ll_root)
    LinearLayout ll_root;
    @Bind(R.id.view_line_bottom)
    View view_line_bottom;
    @Bind(R.id.tv_sex_buxian)
    TextView tvSexBuxian;
    @Bind(R.id.tv_sex_boy)
    TextView tvSexBoy;
    @Bind(R.id.tv_sex_girl)
    TextView tvSexGirl;
    @Bind(R.id.tv_shichang_select)
    TextView tvShichangSelect;
    @Bind(R.id.tv_zidingyi_select)
    TextView tvZidingyiSelect;
    @Bind(R.id.tv_mianyi_select)
    TextView tvMianyiSelect;
    @Bind(R.id.lay_ll_rixin_select)
    LinearLayout layLlRixinSelect;
    @Bind(R.id.tv_money_tips)
    TextView tvMoneyTips;
    @Bind(R.id.tv_shichang_dingjia)
    TextView tvShichangDingjia;
    @Bind(R.id.ll_shichang)
    LinearLayout llShichang;
    @Bind(R.id.tv_money_tips1)
    TextView tvMoneyTips1;
    @Bind(R.id.ed_money_start)
    EditText edMoneyStart;
    @Bind(R.id.ed_money_end)
    EditText edMoneyEnd;
    @Bind(R.id.ll_zidingyi_xinzi)
    LinearLayout llZidingyiXinzi;
    @Bind(R.id.tv_money_tips2)
    TextView tv_money_tips2;
    @Bind(R.id.btn_luzhi)
    Button btnLuzhi;
    @Bind(R.id.iv_voice)
    ImageView ivVoice;
    @Bind(R.id.bubble)
    RelativeLayout bubble;
    @Bind(R.id.tv_length)
    TextView tvLength;
    @Bind(R.id.lay_ll_voice)
    LinearLayout layLlVoice;
    @Bind(R.id.recyclerview_pic)
    RecyclerView recyclerviewPic;
    @Bind(R.id.iv_video)
    CircleImageView ivVideo;
    @Bind(R.id.iv_voice_delete)
    ImageView ivVoiceDelete;
    @Bind(R.id.iv_delete_video)
    ImageView ivDeleteVideo;
    @Bind(R.id.lay_ll_luzhi)
    LinearLayout layLlLuzhi;

    private int qz_id;//是否强制匹配
    private int type;//是否强制匹配
    private int roleType;
    private int bm_pay_type; //支付方式,1全额2信息费
    private int ys_pay_type;
    private int yes_pay_type;
    private int salary_type = 1;//薪资类型：1.市场定价 2.自定义价格 3.面议
    private int isVideoOrPic = 2;//1视频2图片
    public boolean isHiddeTitle;
    private boolean isFirst = true;
    private String videoPath;
    private String voicePath;
    private String longlati = "0" + "," + "0";

    private CommonPayPresenter payPresenter;
    private PayInfoFeeModel currModel;

    private HomeCommonPublishActivity activity;
    private List<WorkTimeModel> workTypeList;
    private String second_area_name = KV.get(Define.CURR_CITY_NAME, "");

    private LoadingDialog loadingDialog;

    private List<PersonTypeModel> personTypeModelList;
    private List<AgeAreaModel> ageArea;
    private List<EducationModel> educationList;
    private List<WorkExperienceModel> experienceModelList;
    private List<ServiceModel> serviceList;
    private List<ServiceModel> listSelect;
    private List<String> listVideo;
    private List<String> listPics;
    private List<String> newVideoUrls = new ArrayList<>();
    private List<String> newPicUrls = new ArrayList<>();
    private List<String> newVoiceUrls = new ArrayList<>();

    private EasyPopup mCirclePop;
    private LimitHeightListView listView;

    private CommonPopupAdapter<ServiceModel> popAdapter;
    private RecyclerViewAdapter adapter;
    private PicRecyclerViewAdapter adapterPic;


    public static CommBmYsYesCreateRecruitFragment newInstance(int qz_id, int type, int roleType, boolean isHiddeTitle) {
        Bundle args = new Bundle();
        args.putInt("qz_id", qz_id);
        args.putInt("type", type);
        args.putInt("roleType", roleType);
        args.putBoolean("isHiddeTitle", isHiddeTitle);
        CommBmYsYesCreateRecruitFragment fragment = new CommBmYsYesCreateRecruitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_baomu_create_recruit;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("发布需求");
        activity = (HomeCommonPublishActivity) getActivity();
        initVoice();
        initPopup();
        edMoneyStart.addTextChangedListener(textWatcher);
        edMoneyEnd.addTextChangedListener(textWatcher);
        //设置tips
        new SpannableStringViewWrap().addViews(tvNeirongTips, tvLeixingTips, tvSexTips, tvAgeTips, tvXueliTips, tvJingyanTips
                , tvGzshiijanTips, tvYudingTips, tvDiquTips, tv_money_tips2, tvDizhiTips, tvUserTips, tvPhoneTips).build();
    }

    private void initVoice() {
        final RecorderUtils recorderUtils = new RecorderUtils(activity, new RecorderUtils.CallBack() {
            @Override
            public void pressButton(int status) {
                if (status == 0) {
                    btnLuzhi.setText("按住不要松手");
                    layLlLuzhi.setBackgroundResource(R.drawable.shape_recoder_btn_recoding);
                } else if (status == 1) {
                    btnLuzhi.setText("录音成功");
                    layLlLuzhi.setBackgroundResource(R.drawable.shape_recoder_btn_normal);
                } else if (status == 2) {
                    btnLuzhi.setText("录音失败");
                    layLlLuzhi.setBackgroundResource(R.drawable.shape_recoder_btn_normal);
                }
            }

            @Override
            public void luzhiFinish(String filePath, long length) {
                voicePath = filePath;
                layLlVoice.setVisibility(View.VISIBLE);
                tvLength.setText((int) (length / 1000) + "s");
            }
        });

        recorderUtils.initView(ll_root, btnLuzhi, ivVoice);
        layLlVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recorderUtils != null) {
                    recorderUtils.play();
                }
            }
        });

        ivVoiceDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recorderUtils != null) {
                    voicePath = "";
                    layLlVoice.setVisibility(View.GONE);
                    btnLuzhi.setText("录制语音");
                    recorderUtils.delete();
                }
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (TextUtils.isEmpty(s)) {
                return;
            }
            payType();

        }
    };

    @Override
    protected void initData() {
        if (getArguments() != null) {
            qz_id = getArguments().getInt("qz_id");
            type = getArguments().getInt("type");
            roleType = getArguments().getInt("roleType");
            isHiddeTitle = getArguments().getBoolean("isHiddeTitle", false);
            if (isHiddeTitle) {
                hidTitleView();
            }
            switch (roleType) {
                case PageConfig.TYPE_BAOMU:
                    tvLeixingTips.setText("*保姆类型");
                    tvKemu.setHint("选择保姆类型");
                    break;
                case PageConfig.TYPE_YUESAO:
                    tvLeixingTips.setText("*月嫂类型");
                    tvKemu.setHint("选择月嫂类型");
                    break;
                case PageConfig.TYPE_YUERSAO:
                    tvLeixingTips.setText("*育儿嫂类型");
                    tvKemu.setHint("选择育儿嫂类型");
                    break;
            }
            String account = Hawk.get("account", "");
            LoginSuccessInfo userInfo = (LoginSuccessInfo) KV.get(Define.LOGIN_SUCCESS);
            if (userInfo != null) {
                edUser.setText(TextUtils.isEmpty(userInfo.getNickname()) ? "" : userInfo.getNickname());
                tvPhone.setText(TextUtils.isEmpty(account) ? "" : account);
                bm_pay_type = userInfo.getBm_pay_type();
                ys_pay_type = userInfo.getYs_pay_type();
                yes_pay_type = userInfo.getYes_pay_type();
            }
            String city_third_name = Hawk.get(Define.CURR_CITY_THIRD_NAME, "");
            tvDiqu.setText(second_area_name + city_third_name);
        }
        payPresenter = new CommonPayPresenter(getActivity());
        initAdapter();
        initPicAdapter();
        getWorkTypeList();
        getPayFeeInfo();
        getXueliList(false);
        getServiceType(false);
        getWorkExperienceList(false);
        getPersonTypeList(false);
        getConfiguration(false, false);
        getAgeArea(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (qz_id == 0) {
                    choiseSex(sex);
                    choiseDingJia(salary_type);
                } else {
                    if (type == HomeCommonPublishActivity.ZHAOPIN) {
                        qzPiPei();
                    } else {
                        qz_id = 0;
                        choiseSex(sex);
                        choiseDingJia(salary_type);
                    }
                }
            }
        }, 500);
    }

    @Override
    protected void refreshPageData() {
        getPayFeeInfo();
        getXueliList(false);
        getServiceType(false);
        getWorkExperienceList(false);
        getPersonTypeList(false);
        getConfiguration(false, false);
        getAgeArea(false);
    }

    private void qzPiPei() {
        addCall(new NetUtil().setUrl(APIS.URL_BM_YS_YES_RENCAI_LIST_DETAIL)
                .addParams("require_id", qz_id + "")
                .setCallBackData(new BaseNewNetModelimpl<BmYsYesRencaiListDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<BmYsYesRencaiListDetailModel> respnse, String source) {
                        if (respnse != null) {
                            showContentView();
                            BmYsYesRencaiListDetailModel record = respnse.getData();
                            if (record != null) {
                                sex = record.getSex();
                                choiseSex(sex);
                                education_background_id = record.getEducation_background_id();
                                if (educationList != null) {
                                    for (EducationModel model : educationList) {
                                        model.setSelect(false);
                                        if (education_background_id == model.getEducation_background_id()) {
                                            model.setSelect(true);
                                        }
                                    }
                                }
                                tvXueli.setText(StringUtil.notEmptyBackValue(record.getEducation_background_name()));
                                person_type = record.getPerson_type();
                                for (PersonTypeModel model : personTypeModelList) {
                                    int person_type1 = model.getPerson_type();
                                    model.setSelect(false);
                                    if (person_type == person_type1) {
                                        model.setSelect(true);
                                    }
                                }
                                tvKemu.setText(StringUtil.notEmptyBackValue(record.getPerson_type_name()));
                                service_content = record.getService_content();
                                if (!TextUtils.isEmpty(service_content)) {
                                    List<String> serviceIdList = Arrays.asList(service_content.split(","));
                                    if (serviceIdList != null && serviceList != null) {
                                        for (ServiceModel model : serviceList) {
                                            int service_content_id = model.getService_content_id();
                                            model.setSelect(false);
                                            if (serviceIdList.contains(service_content_id + "")) {
                                                model.setSelect(true);
                                                listSelect.add(model);
                                            }
                                        }
                                    }
                                }
                                adapter.notifyDataSetChanged();
                                second_area_id = record.getWork_second_area_id();
                                third_area_id = record.getWork_third_area_id();
                                if (StringUtil.isAllNotEmpty(record.getSecond_area_name(), record.getThird_area_name())) {
                                    tvDiqu.setText(record.getSecond_area_name() + record.getThird_area_name());
                                }
                                work_experience = record.getWork_experience();
                                if (experienceModelList != null) {
                                    for (WorkExperienceModel model : experienceModelList) {
                                        model.setSelect(false);
                                        if (model.getWork_experience() == work_experience) {
                                            model.setSelect(true);
                                        }
                                    }
                                }
                                tvJingyan.setText(StringUtil.notEmptyBackValue(record.getWork_experience_name()));
                                work_time_type = record.getWork_time_type();
                                for (int i = 0; i < workTypeList.size(); i++) {
                                    workTypeList.get(i).setSelect(false);
                                    if (i == work_time_type - 1) {
                                        workTypeList.get(i).setSelect(true);
                                        tvGzshiijan.setText(workTypeList.get(i).getName());
                                    }
                                }
                                salary_type = record.getSalary_type();
                                choiseDingJia(salary_type);
                                int end_salary_fee = record.getEnd_salary_fee();
                                int salary = record.getSalary_fee();
                                if (salary_type == 2) {

                                    if (salary == 0) {
                                        edMoneyStart.setText("");
                                    } else {
                                        edMoneyStart.setText(salary + "");
                                    }
                                    if (end_salary_fee == 0) {
                                        edMoneyEnd.setText("");
                                    } else {
                                        edMoneyEnd.setText(end_salary_fee + "");
                                    }

                                }
                            }
                        }

                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<BmYsYesRencaiListDetailModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 根据支付方式判断是否需要重新计算价格
     */
    private void payType() {
        switch (roleType) {
            case PageConfig.TYPE_BAOMU:
                if (bm_pay_type == 1) {//全额
                    if (salary_type == 2) {
                        calcuMoney();
                    }
                }
                break;
            case PageConfig.TYPE_YUESAO:
                if (ys_pay_type == 1) {//全额
                    if (salary_type == 2) {
                        calcuMoney();
                    }
                }
                break;
            case PageConfig.TYPE_YUERSAO:
                if (yes_pay_type == 1) {//全额
                    if (salary_type == 2) {
                        calcuMoney();
                    }
                }
                break;
        }
    }


    /**
     * 获取支付信息
     */
    private void getPayFeeInfo() {
        int payType = 0;
        //	类型：1.保姆 2.月嫂 3.育儿嫂
        switch (roleType) {
            case PageConfig.TYPE_BAOMU:
                payType = CommonPayPresenter.FEE_TYPE_BAOMU_ZHAOPIN;
                break;
            case PageConfig.TYPE_YUESAO:
                payType = CommonPayPresenter.FEE_TYPE_YUESAO_ZHAOPIN;
                break;
            case PageConfig.TYPE_YUERSAO:
                payType = CommonPayPresenter.FEE_TYPE_YUERSAO_ZHAOPIN;
                break;
        }
        payPresenter.loadPayInfoFeeData(payType, new CommonPayPresenter.DataCallBack<PayInfoFeeModel>() {
            @Override
            public void onFail(int type, String msg) {
                showTips(msg);
                showErrorView();
            }

            @Override
            public void onSucess(int code, BaseResultInfo<PayInfoFeeModel> respnse, String source) {
                showContentView();
                if (respnse.getData() != null) {
                    currModel = respnse.getData();
                    if (roleType == PageConfig.TYPE_BAOMU) {
                        if (bm_pay_type == 1) {
                            tvMoneyInfo.setText(new SpanUtils().append("定金")
                                    .append("¥" + currModel.getCharge_fee())
                                    .setForegroundColor(ResUtils.getCommRed())
                                    .append("(含信息费" + currModel.getCharge_fee() + ")")
                                    .create());
                        } else {
                            tvMoneyInfo.setText("信息费: ¥" + currModel.getCharge_fee());
                        }
                    } else if (roleType == PageConfig.TYPE_YUESAO) {
                        if (ys_pay_type == 1) {
                            tvMoneyInfo.setText(new SpanUtils().append("定金")
                                    .append("¥" + currModel.getCharge_fee())
                                    .setForegroundColor(ResUtils.getCommRed())
                                    .append("(含信息费" + currModel.getCharge_fee() + ")")
                                    .create());
                        } else {
                            tvMoneyInfo.setText("信息费: ¥" + currModel.getCharge_fee());
                        }
                    } else if (roleType == PageConfig.TYPE_YUERSAO) {
                        if (yes_pay_type == 1) {
                            tvMoneyInfo.setText(new SpanUtils().append("定金")
                                    .append("¥" + currModel.getCharge_fee())
                                    .setForegroundColor(ResUtils.getCommRed())
                                    .append("(含信息费" + currModel.getCharge_fee() + ")")
                                    .create());
                        } else {
                            tvMoneyInfo.setText("信息费: ¥" + currModel.getCharge_fee());
                        }
                    }

                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NewPayActivity.REQUEST_CODE && resultCode == NewPayActivity.RESULT_CODE_SUCCESS) {
            getActivity().setResult(NewPayActivity.RESULT_CODE_SUCCESS);
            getActivity().finish();
        } else if (requestCode == SelectCityFromMapActivity.GET_CITY_REQUEST_CODE) {
            //地址
            if (data != null) {
                double lat = data.getDoubleExtra("lat", 0);
                double lng = data.getDoubleExtra("lng", 0);
                longlati = lng + "," + lat;
                String address = data.getStringExtra("address");
                edDizhi.setText(address);
            }
        } else if (requestCode == SelectAreaActivity.GET_CITY_REQUEST_CODE) {
            //地址
            if (data != null) {
                third_area_id = data.getIntExtra("thirdId", -1);
                String thirdName = data.getStringExtra("thirdName");
                second_area_name = data.getStringExtra("secondName");
                second_area_id = data.getIntExtra("secondId", -1);
                tvDiqu.setText(second_area_name + thirdName);
            }
        } else if (resultCode == getActivity().RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST) {
            if (isVideoOrPic == 1) {
                if (data != null && data.getExtras() != null) {
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    listVideo.clear();
                    if (selectList != null) {
                        if (selectList.size() > 0) {
                            videoPath = selectList.get(0).getPath();
                        }
                    }
                    ivDeleteVideo.setVisibility(View.VISIBLE);
                    ivVideo.setImageBitmap(ViewWrap.getVideoThumbnail(getActivity(), videoPath, 800, 800, MediaStore.Images.Thumbnails.MICRO_KIND));
                }
            } else if (isVideoOrPic == 2) {
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (!selectList.isEmpty()) {
                    listPics.clear();
                    for (int i = selectList.size() - 1; i >= 0; i--) {
                        LocalMedia localMedia = selectList.get(i);
                        String currImageUrl = localMedia.getPath();
                        listPics.add(0, currImageUrl);
                    }
                    if (listPics.size() < 9) {
                        listPics.add("");
                    }
                    adapterPic.notifyDataSetChanged();
                }
            }
        }
    }

    @OnClick({R.id.tv_sex_boy, R.id.tv_sex_girl, R.id.iv_video, R.id.iv_delete_video, R.id.tv_change_phone, R.id.tv_shichang_select, R.id.tv_zidingyi_select, R.id.tv_mianyi_select, R.id.tv_sex_buxian, R.id.tv_kemu, R.id.tv_neirong, R.id.ed_dizhi, R.id.tv_age, R.id.tv_xueli, R.id.tv_jingyan, R.id.tv_gzshiijan, R.id.tv_yuding1, R.id.tv_yuding2, R.id.tv_diqu, R.id.iv_location, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_delete_video:
                videoPath = "";
                listVideo.clear();
                BitmapDrawable drawable = (BitmapDrawable) ivVideo.getDrawable();
                Bitmap bmp = drawable.getBitmap();
                if (null != bmp && !bmp.isRecycled()) {
                    bmp.recycle();
                    bmp = null;
                }
                ivVideo.setBackgroundResource(R.mipmap.icon_video);
                ivDeleteVideo.setVisibility(View.GONE);
                break;
            case R.id.iv_video:
                String[] permissions = {Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE};
                if (!getHoldingActivity().checkPermission(permissions)) {
                    return;
                }
                isVideoOrPic = 1;
                selectCamera();
                break;
            case R.id.tv_change_phone:
                new PhoneCodeDialog(getActivity(), new PhoneCodeDialog.DataCallBack() {
                    @Override
                    public void verifyCodeSuccess(String new_phone) {
                        if (!TextUtils.isEmpty(new_phone)) {
                            showTips("更换成功");
                            tvPhone.setText(new_phone);
                        }
                    }
                }).builder().setCancelable(true).show();
                break;
            case R.id.tv_shichang_select:
                choiseDingJia(1);
                break;
            case R.id.tv_zidingyi_select:
                choiseDingJia(2);
                break;
            case R.id.tv_mianyi_select:
                choiseDingJia(3);
                break;
            case R.id.tv_sex_buxian:
                choiseSex(0);
                break;
            case R.id.tv_sex_boy:
                choiseSex(1);
                break;
            case R.id.tv_sex_girl:
                choiseSex(2);
                break;
            case R.id.tv_kemu:
                onTypePicker();
                break;
            case R.id.tv_neirong:
                onServiceTypePicker();
                break;
            case R.id.tv_age:
                onAgePicker();
                break;
            case R.id.tv_xueli:
                onXueliPicker();
                break;
            case R.id.tv_jingyan:
                onExperiencePicker();
                break;
            case R.id.tv_gzshiijan:
                onWorkTimePicker();
                break;
            case R.id.tv_yuding1:
                onReserveTimePicker(true);
                break;
            case R.id.tv_yuding2:
                onReserveTimePicker(false);
                break;
            case R.id.tv_diqu:
                SelectAreaActivity.go(this, second_area_id, third_area_id, second_area_name);
                break;
            case R.id.iv_location:
                if (!chekcLocationPermission()) {
                    return;
                }
                SelectCityFromMapActivity.go(this);
                break;
            case R.id.tv_submit:
                submmit();
                break;
        }
    }


    /**
     * 根据选择的性别设置UI
     *
     * @param sex
     */
    private void choiseSex(int sex) {
        this.sex = sex;
        if (roleType == PageConfig.TYPE_YUERSAO || roleType == PageConfig.TYPE_YUESAO) {
            this.sex = 2;//月嫂和育儿嫂只有女性
            sex = 2;
            tvSexBuxian.setVisibility(View.GONE);
            tvSexBoy.setVisibility(View.GONE);
        }
        Drawable select = getActivity().getResources().getDrawable(R.mipmap.select);
        select.setBounds(0, 0, select.getMinimumWidth(), select.getMinimumHeight());
        Drawable unSelect = getActivity().getResources().getDrawable(R.mipmap.unselect);
        unSelect.setBounds(0, 0, unSelect.getMinimumWidth(), unSelect.getMinimumHeight());
        if (sex == 1) {//选中男
            tvSexBuxian.setCompoundDrawables(unSelect, null, null, null);
            tvSexBoy.setCompoundDrawables(select, null, null, null);
            tvSexGirl.setCompoundDrawables(unSelect, null, null, null);
        } else if (sex == 2) {
            tvSexBuxian.setCompoundDrawables(unSelect, null, null, null);
            tvSexBoy.setCompoundDrawables(unSelect, null, null, null);
            tvSexGirl.setCompoundDrawables(select, null, null, null);
        } else if (sex == 0) {
            tvSexBuxian.setCompoundDrawables(select, null, null, null);
            tvSexBoy.setCompoundDrawables(unSelect, null, null, null);
            tvSexGirl.setCompoundDrawables(unSelect, null, null, null);
        }
    }

    /**
     * 根据选择的日薪进行UI设置
     *
     * @param salaryType 1市场2自定义3面议
     */
    private void choiseDingJia(int salaryType) {
        this.salary_type = salaryType;
        layLlRixinSelect.setVisibility(View.VISIBLE);

        Drawable select = getActivity().getResources().getDrawable(R.mipmap.select);
        select.setBounds(0, 0, select.getMinimumWidth(), select.getMinimumHeight());
        Drawable unSelect = getActivity().getResources().getDrawable(R.mipmap.unselect);
        unSelect.setBounds(0, 0, unSelect.getMinimumWidth(), unSelect.getMinimumHeight());
        if (salaryType == 1) {//选中市场定价
            tvShichangSelect.setCompoundDrawables(select, null, null, null);
            tvZidingyiSelect.setCompoundDrawables(unSelect, null, null, null);
            tvMianyiSelect.setCompoundDrawables(unSelect, null, null, null);
            llShichang.setVisibility(View.VISIBLE);
            llZidingyiXinzi.setVisibility(View.GONE);
        } else if (salaryType == 2) {
            tvShichangSelect.setCompoundDrawables(unSelect, null, null, null);
            tvZidingyiSelect.setCompoundDrawables(select, null, null, null);
            tvMianyiSelect.setCompoundDrawables(unSelect, null, null, null);
            llShichang.setVisibility(View.GONE);
            llZidingyiXinzi.setVisibility(View.VISIBLE);
            if (!isFirst) {
                EditTextUtil.showSoftInputFromWindow(getActivity(), edMoneyStart);
            }
        } else if (salaryType == 3) {
            tvShichangSelect.setCompoundDrawables(unSelect, null, null, null);
            tvZidingyiSelect.setCompoundDrawables(unSelect, null, null, null);
            tvMianyiSelect.setCompoundDrawables(select, null, null, null);
            llShichang.setVisibility(View.GONE);
            llZidingyiXinzi.setVisibility(View.GONE);
        }
        isFirst = false;
    }


    private int age_id = -1, sex = 2, education_background_id = -1, work_experience = -1,
            person_type = -1, work_time_type = -1,
            second_area_id = KV.get(Define.CURR_CITY_ID, -1),
            third_area_id = KV.get(Define.CURR_CITY_THIRD_ID, -1);
    private long startTime = 0, endTime = 0;
    private String service_content;

    //预提交
    private void submmit() {
//        second_area_id = KV.get(Define.CURR_CITY_ID, -1);

        if (person_type == -1) {
            String tips = "";
            switch (roleType) {
                case PageConfig.TYPE_BAOMU:
                    tips = "请选择保姆类型";
                    break;
                case PageConfig.TYPE_YUESAO:
                    tips = "请选择月嫂类型";
                    break;
                case PageConfig.TYPE_YUERSAO:
                    tips = "请选择育儿嫂类型";
                    break;
            }
            showTips(tips);
            return;
        }

        if (TextUtils.isEmpty(service_content)) {
            showTips("请选择服务内容");
            return;
        }

        if (sex == -1) {
            showTips("请选择性别");
            return;
        }
        if (age_id == -1) {
            showTips("请选择年龄");
            return;
        }
        if (education_background_id == -1) {
            showTips("请选择学历");
            return;
        }
        if (work_experience == -1) {
            showTips("请选择从业经验");
            return;
        }
        if (work_time_type == -1) {
            showTips("请选择工作时间");
            return;
        }
        if (startTime == 0 || endTime == 0) {
            showTips("请选择起始预定时间");
            return;
        }

        if (startTime > endTime) {
            showTips("起始时间不能大于结束时间");
            return;
        }

        String salary_start = edMoneyStart.getText().toString().trim();
        String salary_end = edMoneyEnd.getText().toString().trim();
        if (salary_type == 2) {
            if (TextUtils.isEmpty(salary_start)) {
                showTips("请输入起始薪资");
                return;
            }
            if (!RegexChkUtil.isNumeric(salary_start)) {
                showTips("请输入正确的起始薪资");
                return;
            }
            if (TextUtils.isEmpty(salary_end)) {
                showTips("请输入结束薪资");
                return;
            }
            if (!RegexChkUtil.isNumeric(salary_start)) {
                showTips("请输入正确的结束薪资");
                return;
            }
            if (Double.parseDouble(salary_start) > Double.parseDouble(salary_end)) {
                showTips("起始薪资不能大于结束薪资");
                return;
            }
        }

        if (third_area_id == 0) {
            showTips("请选择发布地区");
            return;
        }
        if (TextUtils.isEmpty(edDizhi.getText().toString().trim())) {
            showTips("请输入详细地址");
            return;
        }
        if (TextUtils.isEmpty(edUser.getText().toString().trim())) {
            showTips("请输入联系人");
            return;
        }

        if (TextUtils.isEmpty(tvPhone.getText().toString().trim())) {
            showTips("请输入联系电话");
            return;
        }

        //可以提交
        getHoldingActivity().checkUserAuthen(new OnUserAuthenListener() {
            @Override
            public void isAuthen() {
                commit();
            }

            @Override
            public void isNotAuthen() {

            }
        });
    }

    //提交
    private void commit() {

        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getActivity());//初始化对化框
        }
        loadingDialog.showLoading("");

        List<String> picList = new ArrayList<>();
        picList.addAll(listPics);

        for (String s : picList) {
            if (TextUtils.isEmpty(s)) {
                picList.remove(s);
            }
        }
        //如果有图片，先上传图片，再检查是否有视频，如果有，先判断是否上传过，没有则可以再上传视频，如果没有则直接提交
        if (!picList.isEmpty()) {
            upLoadPic(picList);
        } else if (!TextUtils.isEmpty(videoPath)) {
            upLoadVideo();
        } else if (!TextUtils.isEmpty(voicePath)) {
            upLoadVoice();
        } else {
            finalCommit();
        }

    }

    private void upLoadPic(List<String> picList) {
        //先上传图片，再上传视频
        addCall(new NetUtil().setPic_path(picList)
                .buildUploadFile(new NetUtil.OnPicsUploadSuccessListener() {
                    @Override
                    public void onPicsUploadSuccess(List<String> pics) {
                        newPicUrls = pics;
                        if (!TextUtils.isEmpty(videoPath)) {
                            upLoadVideo();
                        } else if (!TextUtils.isEmpty(voicePath)) {
                            upLoadVoice();
                        } else {
                            finalCommit();
                        }
                    }

                    @Override
                    public void onPicsUploadFail(String msg) {
                        showTips(msg);
                        loadingDialog.dismiss();
                    }
                }));
    }

    private void upLoadVoice() {
        List<String> voiceList = new ArrayList<>();
        voiceList.add(voicePath);
        //先上传图片，再上传视频
        addCall(new NetUtil().setPic_path(voiceList)
                .buildUploadFile(new NetUtil.OnPicsUploadSuccessListener() {
                    @Override
                    public void onPicsUploadSuccess(List<String> pics) {
                        newVoiceUrls = pics;
                        finalCommit();
                    }

                    @Override
                    public void onPicsUploadFail(String msg) {
                        showTips(msg);
                        loadingDialog.dismiss();
                    }
                }));
    }

    private void upLoadVideo() {
        List<String> videosList = new ArrayList<>();
        videosList.add(videoPath);
        addCall(new NetUtil().setPic_path(videosList)
                .buildUploadFile(new NetUtil.OnPicsUploadSuccessListener() {
                    @Override
                    public void onPicsUploadSuccess(List<String> pics) {
                        newVideoUrls = pics;
                        if (!TextUtils.isEmpty(voicePath)) {
                            upLoadVoice();
                        } else {
                            finalCommit();
                        }
                    }

                    @Override
                    public void onPicsUploadFail(String msg) {
                        showTips(msg);
                        loadingDialog.dismiss();
                    }
                }));

    }

    //提交
    private void finalCommit() {
        int require_type = 0;
        int payType = 0;
        //	类型：1.保姆 2.月嫂 3.育儿嫂
        switch (roleType) {
            case PageConfig.TYPE_BAOMU:
                require_type = 1;
                payType = CommonPayPresenter.TYPE_BAOMU_ZHAOPIN;
                break;
            case PageConfig.TYPE_YUESAO:
                require_type = 2;
                payType = CommonPayPresenter.TYPE_YUESAO_ZHAOPIN;
                break;
            case PageConfig.TYPE_YUERSAO:
                require_type = 3;
                payType = CommonPayPresenter.TYPE_YUER_ZHAOPIN;
                break;
        }
        final int finalPayType = payType;
        StringBuilder pics = new StringBuilder();
        String picStr = "";
        String videoStr = "";
        String voiceStr = "";
        for (String picUrl : newPicUrls) {
            pics.append(picUrl + ",");
        }
        if (!TextUtils.isEmpty(pics)) {
            picStr = pics.substring(0, pics.length() - 1);
        }
        if (newVideoUrls != null && !newVideoUrls.isEmpty()) {
            videoStr = newVideoUrls.get(0);
        }
        if (newVoiceUrls != null && !newVoiceUrls.isEmpty()) {
            voiceStr = newVoiceUrls.get(0);
        }
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_ADD_RECRUITMENT)
                .addParams("require_type", require_type + "")
                .addParams("person_type", person_type + "")
                .addParams("service_content", service_content + "")
                .addParams("sex", sex + "")
                .addParams("age_id", age_id + "")
                .addParams("require_id", qz_id + "")
                .addParams("image_url", picStr)
                .addParams("video_url", videoStr)
                .addParams("voice_url", voiceStr)
                .addParams("education_background_id", education_background_id + "")
                .addParams("work_experience", work_experience + "")
                .addParams("work_time_type", work_time_type + "")
                .addParams("start_booking_time", startTime + "")
                .addParams("end_booking_time", endTime + "")
                .addParams("start_salary", edMoneyStart.getText().toString().trim() + "")
                .addParams("end_salary", edMoneyEnd.getText().toString().trim() + "")
                .addParams("salary_type", salary_type + "")
                .addParams("second_area_id", second_area_id + "")
                .addParams("third_area_id", third_area_id + "")
                .addParams("address", edDizhi.getText().toString().trim() + "")
                .addParams("work_info", etJieshao.getText().toString().trim() + "")
                .addParams("mobile_phone", tvPhone.getText().toString().trim() + "")
                .addParams("user_name", edUser.getText().toString().trim() + "")
                .addParams("longitude", longlati.split(",")[0])
                .addParams("latitude", longlati.split(",")[1])
                .setCallBackData(new BaseNewNetModelimpl<PublicResultModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        loadingDialog.dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<PublicResultModel> respnse, String source) {
                        loadingDialog.dismiss();
                        //去支付
                        if (respnse.getData() != null) {
                            NewPayActivity.go(CommBmYsYesCreateRecruitFragment.this,
                                    finalPayType, respnse.getData().getRelate_id(),
                                    respnse.getData().getOrder_price() + "",
                                    respnse.getData().getOrder_name(), 0);
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<PublicResultModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }


    /**
     * 选择年龄
     */
    public void onAgePicker() {
        if (ageArea != null) {

            if (activity != null) {
                CommonPopwindow commonPopwindow = new CommonPopwindow(getActivity(), activity.getRootView(), ageArea);
                final EasyPopup circlePop = commonPopwindow.getCirclePop();
                LimitHeightListView listView = commonPopwindow.getListView();
                commonPopwindow.bindAdapter();
                commonPopwindow.setTitle("选择年龄");
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AgeAreaModel model = (AgeAreaModel) parent.getItemAtPosition(position);
                        if (circlePop != null) {
                            circlePop.dismiss();
                        }
                        for (AgeAreaModel bean : ageArea) {
                            bean.setSelect(false);
                        }

                        if (model != null) {
                            model.setSelect(true);
                            tvAge.setText(model.getName());
                            age_id = model.getAge_id();
                        }
                    }
                });
                if (circlePop != null) {
                    circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    circlePop.showAtAnchorView(activity.getRootView(), VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
                }
            }
        } else {
            getAgeArea(true);
        }
    }


    /**
     * 获取年龄范围
     *
     * @param showPop
     */
    private void getAgeArea(final boolean showPop) {
        int type = 0;
        switch (roleType) {
            case PageConfig.TYPE_BAOMU:
                type = 14;
                break;
            case PageConfig.TYPE_YUESAO:
                type = 15;
                break;
            case PageConfig.TYPE_YUERSAO:
                type = 16;
                break;
        }
        addCall(new NetUtil().setUrl(APIS.URL_INFO_GET_AGE_LIST)
                .addParams("type", type + "")
                .setCallBackData(new BaseNewNetModelimpl<List<AgeAreaModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<AgeAreaModel>> respnse, String source) {
                        if (respnse.getData() != null) {
                            ageArea = respnse.getData();
                            if (ageArea != null && ageArea.size() > 0) {
                                ageArea.get(0).setSelect(true);
                            }
                            if (showPop) {
                                onAgePicker();
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<AgeAreaModel>>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    /**
     * 选择学历
     */
    private void onXueliPicker() {
        if (educationList != null) {
            if (activity != null) {
                CommonPopwindow commonPopwindow = new CommonPopwindow(getActivity(), activity.getRootView(), educationList);
                final EasyPopup circlePop = commonPopwindow.getCirclePop();
                LimitHeightListView listView = commonPopwindow.getListView();
                commonPopwindow.bindAdapter();
                commonPopwindow.setTitle("选择学历");
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        EducationModel educationModel = (EducationModel) parent.getItemAtPosition(position);
                        if (circlePop != null) {
                            circlePop.dismiss();
                        }
                        for (EducationModel bean : educationList) {
                            bean.setSelect(false);
                        }
                        if (educationModel != null) {
                            educationModel.setSelect(true);
                            tvXueli.setText(educationModel.getEducation_background_name());
                            education_background_id = educationModel.getEducation_background_id();
                        }
                    }
                });
                if (circlePop != null) {
                    circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    circlePop.showAtAnchorView(activity.getRootView(), VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
                }
            }
        } else {
            getXueliList(true);
        }
    }

    /**
     * 获取学历信息
     * show_type 1应聘方2招聘方
     *
     * @param showPop
     */
    private void getXueliList(final boolean showPop) {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_EDUCATION_LIST)
                .addParams("show_type", "2")
                .setCallBackData(new BaseNewNetModelimpl<List<EducationModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<EducationModel>> respnse, String source) {
                        if (respnse.getData() != null) {
                            showContentView();
                            educationList = respnse.getData();
                            if (educationList != null && educationList.size() > 0) {
                                educationList.get(0).setSelect(true);
                            }
                            if (showPop) {
                                onXueliPicker();
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<EducationModel>>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }


    /**
     * 获取工作经验
     * show_type 1应聘方2招聘方
     *
     * @param showPop
     */
    private void getWorkExperienceList(final boolean showPop) {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_WORK_EXPERIENCE_LIST)
                .addParams("show_type", "2")
                .setCallBackData(new BaseNewNetModelimpl<List<WorkExperienceModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<WorkExperienceModel>> respnse, String source) {
                        if (respnse.getData() != null) {
                            experienceModelList = respnse.getData();
                            if (experienceModelList != null && experienceModelList.size() > 0) {
                                experienceModelList.get(0).setSelect(true);
                            }
                            if (showPop) {
                                onExperiencePicker();
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<WorkExperienceModel>>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 工作经验
     */
    private void onExperiencePicker() {
        if (experienceModelList != null) {
            if (activity != null) {
                CommonPopwindow commonPopwindow = new CommonPopwindow(getActivity(), activity.getRootView(), experienceModelList);
                final EasyPopup circlePop = commonPopwindow.getCirclePop();
                LimitHeightListView listView = commonPopwindow.getListView();
                commonPopwindow.bindAdapter();
                commonPopwindow.setTitle("选择从业经验");
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        WorkExperienceModel model = (WorkExperienceModel) parent.getItemAtPosition(position);
                        if (circlePop != null) {
                            circlePop.dismiss();
                        }
                        for (WorkExperienceModel bean : experienceModelList) {
                            bean.setSelect(false);
                        }
                        if (model != null) {
                            model.setSelect(true);
                            tvJingyan.setText(model.getWork_experience_name());
                            work_experience = model.getWork_experience();
                        }
                    }
                });
                if (circlePop != null) {
                    circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    circlePop.showAtAnchorView(activity.getRootView(), VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
                }
            }
        } else {
            getWorkExperienceList(true);
        }
    }

    private void getWorkTypeList() {
        workTypeList = new ArrayList<>();
        WorkTimeModel workTimeModel1 = new WorkTimeModel();
        WorkTimeModel workTimeModel2 = new WorkTimeModel();
        WorkTimeModel workTimeModel3 = new WorkTimeModel();
        switch (roleType) {
            case PageConfig.TYPE_BAOMU:
                workTimeModel1.setName("白班保姆");
                workTimeModel2.setName("住家保姆");
                break;
            case PageConfig.TYPE_YUESAO:
                workTimeModel1.setName("白班月嫂");
                workTimeModel2.setName("住家月嫂");
                break;
            case PageConfig.TYPE_YUERSAO:
                workTimeModel1.setName("白班育儿嫂");
                workTimeModel2.setName("住家育儿嫂");
                break;
        }
        workTimeModel1.setSelect(true);
        workTimeModel2.setSelect(false);
        workTimeModel3.setSelect(false);
        workTimeModel3.setName("均可接受");

        workTypeList.add(workTimeModel1);
        workTypeList.add(workTimeModel2);
        workTypeList.add(workTimeModel3);
    }

    /**
     * 工作时间
     */
    private void onWorkTimePicker() {
        if (activity != null) {
            CommonPopwindow commonPopwindow = new CommonPopwindow(getActivity(), activity.getRootView(), workTypeList);
            final EasyPopup circlePop = commonPopwindow.getCirclePop();
            LimitHeightListView listView = commonPopwindow.getListView();
            commonPopwindow.bindAdapter();
            commonPopwindow.setTitle("选择工作时间");
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    WorkTimeModel workTimeModel = (WorkTimeModel) parent.getItemAtPosition(position);
                    if (circlePop != null) {
                        circlePop.dismiss();
                    }
                    for (WorkTimeModel model : workTypeList) {
                        model.setSelect(false);
                    }
                    if (workTimeModel != null) {
                        workTimeModel.setSelect(true);
                        tvGzshiijan.setText(workTimeModel.getName());
                        work_time_type = position + 1;
                    }

                }
            });
            if (circlePop != null) {
                circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                circlePop.showAtAnchorView(activity.getRootView(), VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
            }
        }
    }

    /**
     * 选择人物类型
     */
    private void onTypePicker() {
        if (personTypeModelList != null) {
            if (personTypeModelList.isEmpty()) {
                showTips("无类型可选择");
                return;
            }
            if (activity != null) {
                CommonPopwindow commonPopwindow = new CommonPopwindow(getActivity(), activity.getRootView(), personTypeModelList);
                final EasyPopup circlePop = commonPopwindow.getCirclePop();
                LimitHeightListView listView = commonPopwindow.getListView();
                commonPopwindow.bindAdapter();
                commonPopwindow.setTitle("选择类型");
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        PersonTypeModel model = (PersonTypeModel) parent.getItemAtPosition(position);
                        if (circlePop != null) {
                            circlePop.dismiss();
                        }
                        for (PersonTypeModel bean : personTypeModelList) {
                            bean.setSelect(false);
                        }
                        if (model != null) {
                            model.setSelect(true);
                            etJieshao.setText(model.getDescription());
                            tvShichangDingjia.setText(model.getPerson_type_name() + ": ¥" + model.getPrice());
                            tvKemu.setText(model.getPerson_type_name());
                            person_type = model.getPerson_type();
                        }
                    }
                });
                if (circlePop != null) {
                    circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    circlePop.showAtAnchorView(activity.getRootView(), VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
                }
            }
        } else {
            getPersonTypeList(true);
        }
    }

    /**
     * 获取人员类型列表
     *
     * @param showPop
     */
    private void getPersonTypeList(final boolean showPop) {
        int require_type = 0;
        //	类型：1.保姆 2.月嫂 3.育儿嫂
        switch (roleType) {
            case PageConfig.TYPE_BAOMU:
                require_type = 1;
                break;
            case PageConfig.TYPE_YUESAO:
                require_type = 2;
                break;
            case PageConfig.TYPE_YUERSAO:
                require_type = 3;
                break;
        }
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_PERSON_TYPE_LIST)
                .addParams("require_type", require_type + "")
                .setCallBackData(new BaseNewNetModelimpl<List<PersonTypeModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<PersonTypeModel>> respnse, String source) {
                        if (respnse.getData() != null) {
                            personTypeModelList = respnse.getData();
                            if (personTypeModelList != null && personTypeModelList.size() > 0) {
                                personTypeModelList.get(0).setSelect(true);
                            }

                            if (showPop) {
                                onTypePicker();
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<PersonTypeModel>>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    private BmYsYesConfig globalConfig;

    /**
     * 获取配置信息
     */
    private void getConfiguration(final boolean showPop, final boolean isStart) {
        int require_type = 0;
        //	类型：1.保姆 2.月嫂 3.育儿嫂
        switch (roleType) {
            case PageConfig.TYPE_BAOMU:
                require_type = 1;
                break;
            case PageConfig.TYPE_YUESAO:
                require_type = 2;
                break;
            case PageConfig.TYPE_YUERSAO:
                require_type = 3;
                break;
        }
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_GET_CONFIGURATION)
                .addParams("require_type", require_type + "")
                .setCallBackData(new BaseNewNetModelimpl<BmYsYesConfig>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<BmYsYesConfig> respnse, String source) {
                        if (respnse.getData() != null) {
                            globalConfig = respnse.getData();
                            if (showPop) {
                                onReserveTimePicker(isStart);
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<BmYsYesConfig>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /***
     * 预定时间选择
     * @param isStart 开始时间
     */
    private void onReserveTimePicker(final boolean isStart) {
        if (globalConfig != null) {
            new PickerViewWrap().showDateFromTodayPicker(getActivity(), new DateTimePicker.OnYearMonthDayTimePickListener() {
                @Override
                public void onDateTimePicked(String s, String s1, String s2, String s3, String s4) {
                    if (isStart) {
                        tvYuding1.setText(s + "-" + s1 + "-" + s2);
                        DateTime time = new DateTime(Integer.parseInt(s), Integer.parseInt(s1), Integer.parseInt(s2), 0, 0, 0, 0);
                        startTime = time.getMillis() / 1000;
                    } else {
                        tvYuding2.setText(s + "-" + s1 + "-" + s2);
                        DateTime time = new DateTime(Integer.parseInt(s), Integer.parseInt(s1), Integer.parseInt(s2), 0, 0, 0, 0);
                        endTime = time.getMillis() / 1000;
                    }
                    calcuMoney();
                }
            }, globalConfig.getMatch_time());
        } else {
            getConfiguration(true, isStart);
        }
    }


    /**
     * 获取服务列表
     */
    private void getServiceType(final boolean showPop) {
        int require_type = 0;
        //	类型：1.保姆 2.月嫂 3.育儿嫂
        switch (roleType) {
            case PageConfig.TYPE_BAOMU:
                require_type = 1;
                break;
            case PageConfig.TYPE_YUESAO:
                require_type = 2;
                break;
            case PageConfig.TYPE_YUERSAO:
                require_type = 3;
                break;
        }
        addCall(new NetUtil()
                .setUrl(APIS.URL_TALENT_SERVICE_CONTENT_LIST)
                .addParams("require_type", "" + require_type)
                .setCallBackData(new BaseNewNetModelimpl<List<ServiceModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<ServiceModel>> respnse, String source) {
                        if (respnse.getData() != null) {
                            serviceList = respnse.getData();
                            if (showPop) {
                                onServiceTypePicker();
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<ServiceModel>>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 选择工种
     */
    public void onServiceTypePicker() {
        if (serviceList != null) {
            for (ServiceModel serviceModel : serviceList) {
                int service_content_id = serviceModel.getService_content_id();
                for (ServiceModel model : listSelect) {
                    int service_content_id1 = model.getService_content_id();
                    if (service_content_id == service_content_id1) {
                        serviceModel.setSelect(true);
                    }
                }
            }
            popAdapter = new CommonPopupAdapter(getContext(), serviceList);
            listView.setAdapter(popAdapter);
            if (mCirclePop != null) {
                mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                mCirclePop.showAtAnchorView(activity == null ? view_line_bottom : activity.getRootView(), VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
            }
        } else {
            getServiceType(true);
        }
    }


    /**
     * 初始化PopupWindow
     */
    private void initPopup() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_popup_area_select, null);
        listView = (LimitHeightListView) inflate.findViewById(R.id.listView);
        inflate.findViewById(R.id.tv_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCirclePop != null)
                    mCirclePop.dismiss();
            }
        });
        inflate.findViewById(R.id.tv_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCirclePop != null)
                    mCirclePop.dismiss();

                listSelect.clear();
                StringBuilder res = new StringBuilder();

                for (ServiceModel model : serviceList) {
                    if (model.isSelect()) {
                        listSelect.add(model);
                        res.append(model.getService_content_id() + ",");
                    }
                }
                if (!TextUtils.isEmpty(res)) {
                    service_content = res.subSequence(0, res.length() - 1).toString();
                } else {
                    service_content = "";
                }
                adapter.notifyDataSetChanged();
            }
        });

        mCirclePop = new EasyPopup(getContext())
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
                .setDimView(ll_root)
                .createPopup();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                serviceList.get(position).setSelect(!serviceList.get(position).isSelect());
                if (popAdapter != null) {
                    popAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void initAdapter() {
        listSelect = new ArrayList<>();
        adapter = new RecyclerViewAdapter(listSelect);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);

        adapter.setEnableLoadMore(false);
    }


    class RecyclerViewAdapter extends BaseQuickAdapter<ServiceModel, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<ServiceModel> data) {
            super(R.layout.item_recycler_gongzhong, data, false);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final ServiceModel item) {
            helper.setText(R.id.tv_name, item.getService_content_name());
            helper.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setSelect(false);
                    listSelect.remove(helper.getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
        }
    }

    /**
     * 计算金额
     */
    private void calcuMoney() {
        //定金￥1000.00(含信息费20:00)
        if (globalConfig == null) {
            return;
        }

        if (StringUtil.isAllNotEmpty(edMoneyStart.getText().toString().trim(), edMoneyEnd.getText().toString().trim())) {
            if (Double.parseDouble(edMoneyStart.getText().toString()) >= Double.parseDouble(edMoneyEnd.getText().toString())) {
                return;
            }
        } else {
            return;
        }

        if (startTime == 0 || endTime == 0) {
            return;
        }

        if (startTime > endTime) {
            return;
        }

        int day = (int) ((endTime - startTime) / (24 * 3600)) + 1;

        String momey = edMoneyEnd.getText().toString().trim();
        //可以计算了
        BigDecimal calculate = new CalculateUtils().init(momey).multiply(globalConfig.getDeposit_fee())
                .multiply(day).divide("100", 2).adds(currModel.getCharge_fee()).calculate();

        tvMoneyInfo.setText(new SpanUtils().append("定金")
                .append("¥" + calculate.toString())
                .setForegroundColor(ResUtils.getCommRed())
                .append("(含信息费" + currModel.getCharge_fee() + "元)")
                .create());
    }

    public void initPicAdapter() {
        listVideo = new ArrayList<>();
        listPics = new ArrayList<>();
        listPics.add(null);
        adapterPic = new PicRecyclerViewAdapter(listPics);
        adapterPic.setEnableLoadMore(false);
        recyclerviewPic.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerviewPic.setAdapter(adapterPic);
        recyclerviewPic.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        recyclerviewPic.setHasFixedSize(true);

        //事件监听
        adapterPic.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                isVideoOrPic = 2;
                if (TextUtils.isEmpty(listPics.get(position))) {
                    openXiangce();
                } else {
                    ArrayList<String> urls = new ArrayList<>();
                    for (String photoType : listPics) {
                        if (!StringUtil.isEmpty(photoType))
                            urls.add(photoType);
                    }
                    ViewWrap.showPicActivity(getActivity(), urls, position);
                }
            }
        });
    }

    class PicRecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PicRecyclerViewAdapter(@Nullable List<String> data) {
            super(R.layout.item_recycler_add_pic, data, false);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final String item) {
            if (TextUtils.isEmpty(item)) {
                //加号
                helper.setImageResource(R.id.iv_add_pic, R.mipmap.add_pic_icon);
                helper.getView(R.id.iv_close).setVisibility(View.GONE);
            } else {
                helper.getView(R.id.iv_close).setVisibility(View.VISIBLE);
                if (item.startsWith("http")) {
                    GlideUtil.show(getActivity(), item, (ImageView) helper.getView(R.id.iv_add_pic));
                    helper.getView(R.id.iv_close).setVisibility(View.GONE);
                } else
                    GlideUtil.show(getActivity(), new File(item), (ImageView) helper.getView(R.id.iv_add_pic));
            }
            helper.getView(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listPics.remove(helper.getPosition());
                    if (listPics.size() < 9 && !listPics.contains("")) {
                        listPics.add("");
                    }
                    adapterPic.notifyDataSetChanged();
                }
            });
        }
    }

    /**
     * 打开相册
     */
    public void openXiangce() {
        List<LocalMedia> selectList = new ArrayList<>();
        for (String s : listPics) {
            if (!TextUtils.isEmpty(s)) {
                LocalMedia localMedia = new LocalMedia();
                localMedia.setPath(s);
                localMedia.setChecked(true);
                localMedia.setMimeType(PictureMimeType.ofImage());
                selectList.add(localMedia);
            }
        }
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageSpanCount(3)// 每行显示个数 int
                .videoMaxSecond(60)
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .isGif(false)// 是否显示gif图片 true or false
                .enableCrop(false)
                .withAspectRatio(1, 1)
                .maxSelectNum(9)
                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    /**
     * 打开相册选择视频
     */
    public void selectCamera() {
        List<LocalMedia> selectVedioList = new ArrayList<>();
        for (String s : listVideo) {
            if (!StringUtil.isEmpty(s)) {
                LocalMedia localMedia = new LocalMedia();
                localMedia.setChecked(true);
                localMedia.setMimeType(PictureMimeType.ofVideo());
                localMedia.setPath(s);
                selectVedioList.add(localMedia);
            }
        }
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofVideo())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageSpanCount(3)// 每行显示个数 int
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .isGif(false)// 是否显示gif图片 true or false
                .enableCrop(false)
                .videoMaxSecond(60)
                .withAspectRatio(1, 1)
                .maxSelectNum(1)
                .selectionMedia(selectVedioList)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }
}
