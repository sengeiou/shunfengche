package com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
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

import com.alibaba.fastjson.TypeReference;
import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.map.SelectCityFromMapActivity;
import com.windmillsteward.jukutech.activity.newpage.common.model.PayInfoFeeModel;
import com.windmillsteward.jukutech.activity.newpage.common.popwindow.CommonPopwindow;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.AgeAreaModel;
import com.windmillsteward.jukutech.activity.newpage.model.BmYsYesConfig;
import com.windmillsteward.jukutech.activity.newpage.model.CoachGradeListModel;
import com.windmillsteward.jukutech.activity.newpage.model.CoachSubjectListModel;
import com.windmillsteward.jukutech.activity.newpage.model.EducationModel;
import com.windmillsteward.jukutech.activity.newpage.model.JiaJiaoRencaiListDetailsModel;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
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
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * A simple {@link Fragment} subclass.
 * 家教-发布需求
 */
public class JiajiaoCreateRecruitFragment extends BaseInitFragment {
    public static final String TAG = "JiajiaoCreateRecruitFragment";
    @Bind(R.id.tv_kemu_tips)
    TextView tvKemuTips;
    @Bind(R.id.tv_kemu)
    TextView tvKemu;
    @Bind(R.id.tv_nianji_tips)
    TextView tvNianjiTips;
    @Bind(R.id.tv_nianji)
    TextView tvNianji;
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
    @Bind(R.id.tv_yuding_tips)
    TextView tvYudingTips;
    @Bind(R.id.tv_yuding1)
    TextView tvYuding1;
    @Bind(R.id.tv_yuding2)
    TextView tvYuding2;
    @Bind(R.id.tv_shijian_tips)
    TextView tvShijianTips;
    @Bind(R.id.iv_zhoumo)
    ImageView ivZhoumo;
    @Bind(R.id.ll_zhoumo)
    LinearLayout llZhoumo;
    @Bind(R.id.iv_gongzuori)
    ImageView ivGongzuori;
    @Bind(R.id.ll_tv_gongzhongri)
    LinearLayout llTvGongzhongri;
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
    @Bind(R.id.view_line_bottom)
    View viewLineBottom;
    @Bind(R.id.ll_root)
    LinearLayout llRoot;
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
    private int tutor_pay_type;//支付方式,1全额2信息费
    private int salary_type = 2;//薪资类型：2.自定义价格 3.面议
    private int isVideoOrPic = 2;//1视频2图片
    public boolean isHiddeTitle;
    private boolean isFirst = true;
    private String videoPath;
    private String voicePath;
    private String second_area_name = KV.get(Define.CURR_CITY_NAME, "");
    private String longlati = "0" + "," + "0";

    private CommonPayPresenter payPresenter;
    private PayInfoFeeModel currModel;

    private HomeCommonPublishActivity activity;

    private List<String> listVideo;

    private List<String> listPics;
    private List<EducationModel> educationList;
    private List<CoachSubjectListModel> coachSubjectListModels;
    private List<CoachGradeListModel> gradeList;
    private List<AgeAreaModel> ageArea;
    private List<String> newVideoUrls = new ArrayList<>();
    private List<String> newPicUrls = new ArrayList<>();
    private List<String> newVoiceUrls = new ArrayList<>();

    private LoadingDialog loadingDialog;

    private PicRecyclerViewAdapter adapterPic;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_jiajiao_create_recruit;
    }

    public static JiajiaoCreateRecruitFragment newInstance(int qz_id, int type, int roleType, boolean isHiddeTitle) {
        Bundle args = new Bundle();
        args.putInt("qz_id", qz_id);
        args.putInt("type", type);
        args.putInt("roleType", roleType);
        args.putBoolean("isHiddeTitle", isHiddeTitle);
        JiajiaoCreateRecruitFragment fragment = new JiajiaoCreateRecruitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("发布需求");
        activity = (HomeCommonPublishActivity) getActivity();
        initVoice();
        edMoneyStart.addTextChangedListener(textWatcher);
        edMoneyEnd.addTextChangedListener(textWatcher);

        //设置tips
        new SpannableStringViewWrap().addViews(tvKemuTips, tvNianjiTips, tvSexTips, tvAgeTips, tvXueliTips, tvDiquTips
                , tvDizhiTips, tvYudingTips, tv_money_tips2, tvShijianTips, tvUserTips, tvPhoneTips).build();
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

        recorderUtils.initView(llRoot, btnLuzhi, ivVoice);
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

    @Override
    protected void initData() {
        if (getArguments() != null) {
            qz_id = getArguments().getInt("qz_id");
            type = getArguments().getInt("type");
            roleType = getArguments().getInt("roleType");
            isHiddeTitle = getArguments().getBoolean("isHiddeTitle", false);

            String city_third_name = Hawk.get(Define.CURR_CITY_THIRD_NAME, "");
            tvDiqu.setText(second_area_name + city_third_name);
            if (isHiddeTitle) {
                hidTitleView();
            }
            String account = Hawk.get("account", "");
            LoginSuccessInfo userInfo = (LoginSuccessInfo) KV.get(Define.LOGIN_SUCCESS);
            if (userInfo != null) {
                edUser.setText(TextUtils.isEmpty(userInfo.getNickname()) ? "" : userInfo.getNickname());
                tvPhone.setText(TextUtils.isEmpty(account) ? "" : account);
                tutor_pay_type = userInfo.getTutor_pay_type();
            }
        }
        payPresenter = new CommonPayPresenter(getActivity());
        initPicAdapter();
        getPayFeeInfo();
        getXueliList(false);
        getGradeList(false);
        getCoachSubjectList(false);
        getConfiguration(false, false);
        getAgeArea(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (qz_id == 0) {
                    choiseDingJia(salary_type);
                } else {
                    if (type == HomeCommonPublishActivity.ZHAOPIN) {
                        qzPiPei();
                    } else {
                        qz_id = 0;
                        choiseDingJia(salary_type);
                    }
                }
            }
        }, 500);

    }

    private void qzPiPei() {
        if (qz_id == 0) {
            return;
        }
        addCall(new NetUtil().setUrl(APIS.URL_JIAJIAO_RENCAI_LIST_DETAIL)
                .addParams("when_tutor_id", qz_id + "")
                .setCallBackData(new BaseNewNetModelimpl<JiaJiaoRencaiListDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<JiaJiaoRencaiListDetailsModel> respnse, String source) {
                        if (respnse != null) {
                            showContentView();
                            JiaJiaoRencaiListDetailsModel data = respnse.getData();
                            if (data != null) {
                                coach_subject_id = data.getCoach_subject_id();
                                if (coachSubjectListModels != null) {
                                    for (CoachSubjectListModel model : coachSubjectListModels) {
                                        model.setSelect(false);
                                        if (model.getCoach_subject_id() == coach_subject_id) {
                                            model.setSelect(true);
                                        }
                                    }
                                }
                                tvKemu.setText(StringUtil.notEmptyBackValue(data.getCoach_subject_name()));
                                List<JiaJiaoRencaiListDetailsModel.CoachGradeListBean> coach_grade_list = data.getCoach_grade_list();
                                if (coach_grade_list != null) {
                                    if (coach_grade_list.size() > 0) {
                                        coach_grade_ids = coach_grade_list.get(0).getId();
                                        tvNianji.setText(StringUtil.notEmptyBackValue(coach_grade_list.get(0).getName()));
                                        for (CoachGradeListModel model : gradeList) {
                                            model.setSelect(false);
                                            if (model.getCoach_grade_id() == coach_grade_ids) {
                                                model.setSelect(true);
                                            }
                                        }
                                    }
                                }

                                sex = data.getSex();
                                choiseSex(sex);
                                if (educationList != null) {
                                    for (EducationModel model : educationList) {
                                        model.setSelect(false);
                                        if (model.getEducation_background_id() == data.getEducation_background_id()) {
                                            education_background_id = model.getEducation_background_id();
                                            model.setSelect(true);
                                            tvXueli.setText(StringUtil.notEmptyBackValue(model.getEducation_background_name()));
                                        }
                                    }
                                }
                                second_area_id = data.getSecond_area_id();
                                third_area_id = data.getThird_area_id();
                                if (StringUtil.isAllNotEmpty(data.getSecond_area_name(), data.getThird_area_name())) {
                                    tvDiqu.setText(data.getSecond_area_name() + data.getThird_area_name());
                                }
                                if (!TextUtils.isEmpty(data.getCoach_time())) {
                                    if (data.getCoach_time().contains("1")) {
                                        zhoumo = true;
                                        ivZhoumo.setImageResource(R.mipmap.select);
                                    }
                                    if (data.getCoach_time().contains("2")) {
                                        gongzuori = true;
                                        ivGongzuori.setImageResource(R.mipmap.select);
                                    }
                                }
                                salary_type = data.getSalary_type();
                                choiseDingJia(salary_type);
                                String end_salary_fee = data.getEnd_salary_fee();
                                String salary = data.getSalary();
                                if (salary_type == 2) {
                                    if (!TextUtils.isEmpty(salary)) {
                                        double start = Double.parseDouble(salary);
                                        if (start == 0) {
                                            edMoneyStart.setText("");
                                        } else {
                                            edMoneyStart.setText(start + "");
                                        }
                                    }
                                    if (!TextUtils.isEmpty(end_salary_fee)) {
                                        double end = Double.parseDouble(end_salary_fee);
                                        if (end == 0) {
                                            edMoneyEnd.setText("");
                                        } else {
                                            edMoneyEnd.setText(end + "");
                                        }
                                    }
                                }
                            }
                        }

                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<JiaJiaoRencaiListDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    @Override
    protected void refreshPageData() {
        getPayFeeInfo();

        getXueliList(false);
        getGradeList(false);
        getCoachSubjectList(false);
        getConfiguration(false, false);
        getAgeArea(false);
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
            if (TextUtils.isEmpty(s))
                return;

            if (tutor_pay_type == 1) {
                if (salary_type == 2) {
                    calcuMoney();
                }
            }
        }
    };

    //提交
    private void submit() {
        if (coach_subject_id == -1) {
            showTips("请选择科目");
            return;
        }
        if (coach_grade_ids == -1) {
            showTips("请选择辅导年级");
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
        if (third_area_id == -1) {
            showTips("请选择地区");
            return;
        }
        if (TextUtils.isEmpty(edDizhi.getText().toString().trim())) {
            showTips("请输入地址");
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
        if (TextUtils.isEmpty(edUser.getText().toString().trim())) {
            showTips("请输入联系人");
            return;
        }
        if (TextUtils.isEmpty(tvPhone.getText().toString().trim())) {
            showTips("请输入联系电话");
            return;
        }
        if (!zhoumo && !gongzuori) {
            showTips("请至少选择一项辅导时间");
            return;
        }
        if (!getHoldingActivity().chekcLocationPermission()) {
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
        String coach_time = "";
        if (zhoumo && gongzuori) {
            coach_time = "1,2";
        } else {
            if (zhoumo) {
                coach_time = "1";
            }
            if (gongzuori) {
                coach_time = "2";
            }
        }
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
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_ADD_LOOKFOR_TUTOR)
                .addParams("user_name", edUser.getText().toString().trim())
                .addParams("sex", sex + "")
                .addParams("age_id", age_id + "")
                .addParams("image_url", picStr)
                .addParams("video_url", videoStr)
                .addParams("voice_url", voiceStr)
                .addParams("when_tutor_id", qz_id + "")
                .addParams("education_background_id", education_background_id + "")
                .addParams("coach_subject_id", coach_subject_id + "")
                .addParams("coach_grade_id", coach_grade_ids + "")
                .addParams("coach_time", coach_time + "")
                .addParams("start_booking_time", startTime + "")
                .addParams("end_booking_time", endTime + "")
                .addParams("start_salary", edMoneyStart.getText().toString().trim() + "")
                .addParams("end_salary", edMoneyEnd.getText().toString().trim() + "")
                .addParams("salary_type", salary_type + "")
                .addParams("address", edDizhi.getText().toString().trim())
                .addParams("work_info", etJieshao.getText().toString().trim())
                .addParams("mobile_phone", tvPhone.getText().toString().trim())
                .addParams("second_area_id", second_area_id + "")
                .addParams("third_area_id", third_area_id + "")
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
                            NewPayActivity.go(JiajiaoCreateRecruitFragment.this,
                                    CommonPayPresenter.TYPE_JIAJIAO_ZHAOPIN, respnse.getData().getRelate_id(), respnse.getData().getOrder_price() + "", respnse.getData().getOrder_name(), 0);
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
                third_area_id = data.getIntExtra("thirdId", 0);
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

    private int sex = 0, education_background_id = -1, coach_grade_ids = -1, coach_subject_id = -1,
            second_area_id = KV.get(Define.CURR_CITY_ID, -1),
            third_area_id = KV.get(Define.CURR_CITY_THIRD_ID, -1),
            age_id = -1;
    private boolean zhoumo = true, gongzuori = true;
    private long startTime = -1, endTime = -1;

    @OnClick({R.id.tv_zidingyi_select, R.id.iv_video, R.id.iv_delete_video, R.id.tv_change_phone, R.id.tv_mianyi_select, R.id.tv_sex_buxian, R.id.tv_sex_boy, R.id.tv_sex_girl, R.id.tv_kemu, R.id.tv_nianji, R.id.tv_age, R.id.ed_dizhi, R.id.tv_xueli, R.id.tv_diqu, R.id.iv_location, R.id.tv_yuding1, R.id.tv_yuding2, R.id.ll_zhoumo, R.id.ll_tv_gongzhongri, R.id.tv_submit})
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
                onSubjectPicker();
                break;
            case R.id.tv_nianji:
                onGradeTypePicker();
                break;
            case R.id.tv_age:
                onAgePicker();
                break;
            case R.id.tv_xueli:
                onXueliPicker();
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
            case R.id.tv_yuding1:
                onReserveTimePicker(true);
                break;
            case R.id.tv_yuding2:
                onReserveTimePicker(false);
                break;
            case R.id.ll_zhoumo:
                zhoumo = !zhoumo;
                if (zhoumo) {
                    ivZhoumo.setImageResource(R.mipmap.select);
                } else {
                    ivZhoumo.setImageResource(R.mipmap.unselect);
                }
                break;
            case R.id.ll_tv_gongzhongri:
                gongzuori = !gongzuori;
                if (gongzuori) {
                    ivGongzuori.setImageResource(R.mipmap.select);
                } else {
                    ivGongzuori.setImageResource(R.mipmap.unselect);
                }
                break;
            case R.id.tv_submit:
                submit();
                break;
        }
    }

    private BmYsYesConfig globalConfig;

    /**
     * 获取配置信息
     */
    private void getConfiguration(final boolean showPop, final boolean isStart) {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_GET_CONFIGURATION_JIAJIAO)
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
     * 根据选择的性别设置UI
     *
     * @param sex
     */
    private void choiseSex(int sex) {
        this.sex = sex;
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
        if (salaryType == 2) {
            tvZidingyiSelect.setCompoundDrawables(select, null, null, null);
            tvMianyiSelect.setCompoundDrawables(unSelect, null, null, null);
            llZidingyiXinzi.setVisibility(View.VISIBLE);
            if (!isFirst) {
                EditTextUtil.showSoftInputFromWindow(getActivity(), edMoneyStart);
            }
        } else if (salaryType == 3) {
            tvZidingyiSelect.setCompoundDrawables(unSelect, null, null, null);
            tvMianyiSelect.setCompoundDrawables(select, null, null, null);
            llZidingyiXinzi.setVisibility(View.GONE);
        } else {//默认自定义
            this.salary_type = 2;
            tvZidingyiSelect.setCompoundDrawables(select, null, null, null);
            tvMianyiSelect.setCompoundDrawables(unSelect, null, null, null);
            llZidingyiXinzi.setVisibility(View.VISIBLE);
            if (!isFirst) {
                EditTextUtil.showSoftInputFromWindow(getActivity(), edMoneyStart);
            }
        }
        isFirst = false;
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
     * show_type  1应聘方2招聘方
     *
     * @param showPop
     */
    private void getXueliList(final boolean showPop) {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_EDUCATION_IST)
                .addParams("show_type", "2")
                .setCallBackData(new BaseNewNetModelimpl<List<EducationModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<EducationModel>> respnse, String source) {
                        if (respnse.getData() != null) {
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
     * 获取辅导科目
     *
     * @param showPop
     */
    private void getCoachSubjectList(final boolean showPop) {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_COACH_SUBJECT_LIST)
                .setCallBackData(new BaseNewNetModelimpl<List<CoachSubjectListModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<CoachSubjectListModel>> respnse, String source) {
                        if (respnse.getData() != null) {
                            coachSubjectListModels = respnse.getData();
                            if (coachSubjectListModels != null && coachSubjectListModels.size() > 0) {
                                coachSubjectListModels.get(0).setSelect(true);
                            }
                            if (showPop) {
                                onSubjectPicker();
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<CoachSubjectListModel>>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 工作辅导科目
     */
    private void onSubjectPicker() {
        if (coachSubjectListModels != null) {
            if (activity != null) {
                CommonPopwindow commonPopwindow = new CommonPopwindow(getActivity(), activity.getRootView(), coachSubjectListModels);
                final EasyPopup circlePop = commonPopwindow.getCirclePop();
                LimitHeightListView listView = commonPopwindow.getListView();
                commonPopwindow.bindAdapter();
                commonPopwindow.setTitle("选择辅导科目");
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CoachSubjectListModel model = (CoachSubjectListModel) parent.getItemAtPosition(position);
                        if (circlePop != null) {
                            circlePop.dismiss();
                        }
                        for (CoachSubjectListModel bean : coachSubjectListModels) {
                            bean.setSelect(false);
                        }
                        if (model != null) {
                            model.setSelect(true);
                            tvKemu.setText(model.getCoach_subject_name());
                            coach_subject_id = model.getCoach_subject_id();
                        }
                    }
                });
                if (circlePop != null) {
                    circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    circlePop.showAtAnchorView(activity.getRootView(), VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
                }
            }
        } else {
            getCoachSubjectList(true);
        }
    }

    /**
     * 获取年级列表
     */
    private void getGradeList(final boolean showPop) {
        addCall(new NetUtil()
                .setUrl(APIS.URL_TALENT_COACH_GRADE_LIST)
                .setCallBackData(new BaseNewNetModelimpl<List<CoachGradeListModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<CoachGradeListModel>> respnse, String source) {
                        if (respnse.getData() != null) {
                            gradeList = respnse.getData();
                            if (gradeList != null && gradeList.size() > 0) {
                                gradeList.get(0).setSelect(true);
                            }
                            if (showPop) {
                                onGradeTypePicker();
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<CoachGradeListModel>>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 选择年级
     */
    public void onGradeTypePicker() {
        if (gradeList != null) {
            if (activity != null) {
                CommonPopwindow commonPopwindow = new CommonPopwindow(getActivity(), activity.getRootView(), gradeList);
                final EasyPopup circlePop = commonPopwindow.getCirclePop();
                LimitHeightListView listView = commonPopwindow.getListView();
                commonPopwindow.bindAdapter();
                commonPopwindow.setTitle("选择年级");
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CoachGradeListModel model = (CoachGradeListModel) parent.getItemAtPosition(position);
                        if (circlePop != null) {
                            circlePop.dismiss();
                        }
                        for (CoachGradeListModel bean : gradeList) {
                            bean.setSelect(false);
                        }

                        if (model != null) {
                            model.setSelect(true);
                            tvNianji.setText(model.getCoach_grade_name());
                            coach_grade_ids = model.getCoach_grade_id();
                        }
                    }
                });
                if (circlePop != null) {
                    circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    circlePop.showAtAnchorView(activity.getRootView(), VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
                }
            }
        } else {
            getGradeList(true);
        }
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
//            new PickerViewWrap<AgeAreaModel>().showCommonSelectPicker(getActivity(), -1, new SinglePicker.OnItemPickListener<AgeAreaModel>() {
//                @Override
//                public void onItemPicked(int i, AgeAreaModel areaBean) {
//                    tvAge.setText(areaBean.getName());
//                    age_id = areaBean.getAge_id();
//
//                }
//            }, ageArea);
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
        addCall(new NetUtil().setUrl(APIS.URL_INFO_GET_AGE_LIST)
                .addParams("type", 12 + "")
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
     * 获取支付信息
     */
    private void getPayFeeInfo() {
        payPresenter.loadPayInfoFeeData(CommonPayPresenter.FEE_TYPE_JIAJIAO_ZHAOPIN, new CommonPayPresenter.DataCallBack<PayInfoFeeModel>() {
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
                    if (tutor_pay_type == 1) {
                        tvMoneyInfo.setText(new SpanUtils().append("定金")
                                .append("¥" + currModel.getCharge_fee())
                                .setForegroundColor(ResUtils.getCommRed())
                                .append("(含信息费" + currModel.getCharge_fee() + "元)")
                                .create());
                    } else {
                        tvMoneyInfo.setText("信息费: ¥" + currModel.getCharge_fee());
                    }

                }
            }
        });
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
                .append("(含信息费" + currModel.getCharge_fee() + ")")
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
