package com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong;


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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.windmillsteward.jukutech.activity.newpage.AudioRecoderDialog;
import com.windmillsteward.jukutech.activity.newpage.AudioRecoderUtils;
import com.windmillsteward.jukutech.activity.newpage.adapter.CommonPopupAdapter;
import com.windmillsteward.jukutech.activity.newpage.common.model.PayInfoFeeModel;
import com.windmillsteward.jukutech.activity.newpage.common.popwindow.CommonPopwindow;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.AgeAreaModel;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.model.ZdgRencaiListDetailModel;
import com.windmillsteward.jukutech.activity.newpage.model.ZhongdgServiceModel;
import com.windmillsteward.jukutech.activity.newpage.newpublish.HomeCommonPublishActivity;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.VoiceUtils;
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
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.CalculateUtils;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.EditTextUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.RegexChkUtil;
import com.windmillsteward.jukutech.utils.ResUtils;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.view.PickerViewWrap;
import com.windmillsteward.jukutech.utils.view.SpannableStringViewWrap;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import java.io.File;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * A simple {@link Fragment} subclass.
 * 钟点工-发布招聘
 */
public class ZhongdgCreateRecruitFragment extends BaseInitFragment {
    public static final String TAG = "ZhongdgCreateRecruitFragment";
    @Bind(R.id.tv_num_tips)
    TextView tvNumTips;
    @Bind(R.id.ed_num)
    EditText edNum;
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
    @Bind(R.id.tv_gzshiijan_tips)
    TextView tvGzshiijanTips;
    @Bind(R.id.tv_gzshiijan)
    TextView tvGzshiijan;
    @Bind(R.id.tv_pipei_tips)
    TextView tvPipeiTips;
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
    @Bind(R.id.fl_content)
    FlowLayout flContent;
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

    private String[] workTimeList = {"长期", "全天", "2小时", "4小时", "6小时", "8小时"};
    private int workTime = 0;

    private CommonPayPresenter payPresenter;
    private PayInfoFeeModel currModel;

    private int qz_id;//是否强制匹配
    private int type;//是否强制匹配
    private int roleType;
    private int salary_type = 1;
    private int isVideoOrPic = 2;//1视频2图片
    public boolean isHiddeTitle;
    private boolean isFirst = true;

    private String videoPath;
    private String voicePath;
    private String longlati = "0" + "," + "0";

    private String second_area_name = KV.get(Define.CURR_CITY_NAME, "");

    private HomeCommonPublishActivity activity;

    private PicRecyclerViewAdapter adapterPic;

    private LoadingDialog loadingDialog;

    private List<AgeAreaModel> ageArea = new ArrayList<>();
    private List<ZhongdgServiceModel> serviceModels = new ArrayList<>();
    private List<ZhongdgServiceModel> listSelect = new ArrayList<>();
    private List<String> newVideoUrls = new ArrayList<>();
    private List<String> newPicUrls = new ArrayList<>();
    private List<String> newVoiceUrls = new ArrayList<>();
    private List<String> listVideo;
    private List<String> listPics;
    private EasyPopup mCirclePop;
    private LimitHeightListView listView;
    private CommonPopupAdapter<ZhongdgServiceModel> popAdapter;
    private RecyclerViewAdapter adapter;

    private Drawable select;
    private Drawable unSelect;

    public static ZhongdgCreateRecruitFragment newInstance(int qz_id, int type, int roleType, boolean isHiddeTitle) {
        Bundle args = new Bundle();
        args.putInt("qz_id", qz_id);
        args.putInt("type", type);
        args.putInt("roleType", roleType);
        args.putBoolean("isHiddeTitle", isHiddeTitle);
        ZhongdgCreateRecruitFragment fragment = new ZhongdgCreateRecruitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_zhongdg_create_recruit;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("发布钟点工招聘");
        activity = (HomeCommonPublishActivity) getActivity();
        initVoice();
        initPopup();
        edNum.addTextChangedListener(textWatcher);
        edMoneyStart.addTextChangedListener(textWatcher);
        edMoneyEnd.addTextChangedListener(textWatcher);
        //设置TIPS
        new SpannableStringViewWrap().addViews(tvNumTips, tvNeirongTips, tvSexTips, tvAgeTips, tvGzshiijanTips, tvPipeiTips
                , tvDiquTips, tvDizhiTips, tvUserTips, tv_money_tips2, tvPhoneTips).build();
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
            String account = Hawk.get("account", "");
            LoginSuccessInfo userInfo = (LoginSuccessInfo) KV.get(Define.LOGIN_SUCCESS);
            if (userInfo != null) {
                edUser.setText(TextUtils.isEmpty(userInfo.getNickname()) ? "" : userInfo.getNickname());
                tvPhone.setText(TextUtils.isEmpty(account) ? "" : account);
            }
            work_date = DateUtil.getCurrentDate("yyyy-MM-dd");
            tvGzshiijan.setText(TextUtils.isEmpty(work_date) ? "" : work_date);
            if (isHiddeTitle) {
                hidTitleView();
            }
        }
        payPresenter = new CommonPayPresenter(getActivity());
        initAdapter();
        initPicAdapter();
        initSelectDrawable();
        getPayFeeInfo();
        getAgeArea(false);
        getServicesList(false);
        initWorkTime();

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

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(s.toString()))
                calcuMoney();
        }
    };

    @Override
    protected void refreshPageData() {
        getPayFeeInfo();
    }

    private void initSelectDrawable() {
        select = getActivity().getResources().getDrawable(R.mipmap.select);
        select.setBounds(0, 0, select.getMinimumWidth(), select.getMinimumHeight());
        unSelect = getActivity().getResources().getDrawable(R.mipmap.unselect);
        unSelect.setBounds(0, 0, unSelect.getMinimumWidth(), unSelect.getMinimumHeight());
    }

    private void qzPiPei() {
        if (qz_id == 0) {
            return;
        }
        addCall(new NetUtil().setUrl(APIS.URL_ZDG_RENCAI_INFO_LIST_DETAIL)
                .addParams("when_bell_worker_id", qz_id + "")
                .setCallBackData(new BaseNewNetModelimpl<ZdgRencaiListDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<ZdgRencaiListDetailModel> respnse, String source) {
                        showContentView();
                        if (respnse.getData() != null) {
                            ZdgRencaiListDetailModel data = respnse.getData();
                            if (data != null) {
                                //设置内容
                                List<ZdgRencaiListDetailModel.ServiceIdsBean> service_ids = data.getService_ids();
                                service_id = data.getService_id();
                                if (!TextUtils.isEmpty(service_id)) {
                                    List<String> serviceIdList = Arrays.asList(service_id.split(","));
                                    if (serviceIdList != null) {
                                        for (ZdgRencaiListDetailModel.ServiceIdsBean model : service_ids) {
                                            if (serviceIdList.contains(model.getId() + "")) {
                                                ZhongdgServiceModel serviceModel = new ZhongdgServiceModel();
                                                serviceModel.setName(model.getName());
                                                serviceModel.setService_id(model.getId());
                                                listSelect.add(serviceModel);
                                            }
                                        }
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                                edNum.setText("1");
                                sex = data.getSex();
                                choiseSex(sex);
                                salary_type = data.getSalary_type();
                                choiseDingJia(salary_type);
                                if (salary_type == 2){
                                    edMoneyStart.setText(StringUtil.notEmptyBackValue(data.getSalary_fee()));
                                    edMoneyEnd.setText(StringUtil.notEmptyBackValue(data.getEnd_salary_fee()));
                                }
                                work_second_area_id = data.getWork_second_area_id();
                                work_third_area_id = data.getWork_third_area_id();
                                if (StringUtil.isAllNotEmpty(data.getWork_second_area_name(), data.getWork_third_area_name())) {
                                    tvDiqu.setText(data.getWork_second_area_name() + data.getWork_third_area_name());
                                }
                                tvGzshiijan.setText(StringUtil.notEmptyBackValue(data.getWork_date()));
                                workTime = data.getWork_hour();
                                int childCount = flContent.getChildCount();//6
                                for (int i = 0; i < childCount; i++) {
                                    TextView childAt = (TextView) flContent.getChildAt(i);
                                    childAt.setCompoundDrawables(unSelect, null, null, null);
                                }
                                if (childCount == 6) {
                                    switch (workTime) {
                                        case 0:
                                            TextView childAt0 = (TextView) flContent.getChildAt(0);
                                            childAt0.setCompoundDrawables(select, null, null, null);
                                            break;
                                        case 24:
                                            TextView childAt1 = (TextView) flContent.getChildAt(1);
                                            childAt1.setCompoundDrawables(select, null, null, null);
                                            break;
                                        case 2:
                                            TextView childAt2 = (TextView) flContent.getChildAt(2);
                                            childAt2.setCompoundDrawables(select, null, null, null);
                                            break;
                                        case 4:
                                            TextView childAt3 = (TextView) flContent.getChildAt(3);
                                            childAt3.setCompoundDrawables(select, null, null, null);
                                            break;
                                        case 6:
                                            TextView childAt4 = (TextView) flContent.getChildAt(4);
                                            childAt4.setCompoundDrawables(select, null, null, null);
                                            break;
                                        case 8:
                                            TextView childAt5 = (TextView) flContent.getChildAt(5);
                                            childAt5.setCompoundDrawables(select, null, null, null);
                                            break;
                                    }
                                }
                            }
                        }

                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<ZdgRencaiListDetailModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    /**
     * 获取支付信息
     */
    private void getPayFeeInfo() {
        payPresenter.loadPayInfoFeeData(CommonPayPresenter.FEE_TYPE_ZHONGDIANGONG_ZHAOPIN, new CommonPayPresenter.DataCallBack<PayInfoFeeModel>() {
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
                    LoginSuccessInfo loginSuccessInfo = (LoginSuccessInfo) Hawk.get(Define.LOGIN_SUCCESS);
                    int hw_pay_type = loginSuccessInfo.getHw_pay_type();
                    if (hw_pay_type == 1) {
                        tvMoneyInfo.setText(new SpanUtils().append("定金")
                                .append("¥" + currModel.getCharge_fee())
                                .setForegroundColor(ResUtils.getCommRed())
                                .append("(含信息费" + currModel.getCharge_fee() + ")")
                                .create());
                    } else if (hw_pay_type == 2) {
                        tvMoneyInfo.setText(new SpanUtils()
                                .append("信息费: ¥" + currModel.getCharge_fee())
                                .create());
                    }

                }
            }
        });
    }

    private int sex = 0, age_id = -1, work_first_area_id = -1,
            work_second_area_id = KV.get(Define.CURR_CITY_ID, -1),
            work_third_area_id = KV.get(Define.CURR_CITY_THIRD_ID, -1);
    private String work_date, service_id;

    @OnClick({R.id.tv_shichang_select, R.id.iv_video, R.id.tv_change_phone,R.id.iv_delete_video,  R.id.tv_zidingyi_select, R.id.tv_mianyi_select, R.id.tv_sex_girl, R.id.tv_sex_boy, R.id.tv_sex_buxian, R.id.tv_neirong, R.id.tv_age, R.id.tv_gzshiijan, R.id.ed_dizhi, R.id.tv_diqu, R.id.iv_location, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_delete_video:
                videoPath = "";
                listVideo.clear();
                BitmapDrawable drawable =(BitmapDrawable) ivVideo.getDrawable();
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
                if (!getHoldingActivity().checkPermission(permissions)){
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
            case R.id.tv_sex_girl:
                choiseSex(2);
                break;
            case R.id.tv_sex_boy:
                choiseSex(1);
                break;
            case R.id.tv_sex_buxian:
                choiseSex(0);
                break;
            case R.id.tv_neirong:
                onServicePicker();
                break;
            case R.id.tv_age:
                onAgePicker();
                break;
            case R.id.tv_gzshiijan:
                onYearMonthDayTimePicker();
                break;
            case R.id.tv_diqu:
                SelectAreaActivity.go(this, work_second_area_id, work_third_area_id, second_area_name);
                break;
            case R.id.iv_location:
                if (!chekcLocationPermission()) {
                    return;
                }
                SelectCityFromMapActivity.go(this);
                break;
            case R.id.tv_submit:
                submit();
                break;
        }
    }

    /**
     * 1男2女
     *
     * @param sex
     */
    private void choiseSex(int sex) {
        this.sex = sex;
        if (sex == 0) {//选中男
            tvSexBuxian.setCompoundDrawables(select, null, null, null);
            tvSexBoy.setCompoundDrawables(unSelect, null, null, null);
            tvSexGirl.setCompoundDrawables(unSelect, null, null, null);
        } else if (sex == 1) {
            tvSexBuxian.setCompoundDrawables(unSelect, null, null, null);
            tvSexBoy.setCompoundDrawables(select, null, null, null);
            tvSexGirl.setCompoundDrawables(unSelect, null, null, null);
        } else if (sex == 2) {
            tvSexBuxian.setCompoundDrawables(unSelect, null, null, null);
            tvSexBoy.setCompoundDrawables(unSelect, null, null, null);
            tvSexGirl.setCompoundDrawables(select, null, null, null);
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
        if (salaryType == 1) {
            tvShichangSelect.setCompoundDrawables(select, null, null, null);
            tvZidingyiSelect.setCompoundDrawables(unSelect, null, null, null);
            tvMianyiSelect.setCompoundDrawables(unSelect, null, null, null);
            llShichang.setVisibility(View.VISIBLE);
            llZidingyiXinzi.setVisibility(View.GONE);
        } else if (salaryType == 2) {
            tvShichangSelect.setCompoundDrawables(unSelect, null, null, null);
            tvZidingyiSelect.setCompoundDrawables(select, null, null, null);
            tvMianyiSelect.setCompoundDrawables(unSelect, null, null, null);
            llZidingyiXinzi.setVisibility(View.VISIBLE);
            llShichang.setVisibility(View.GONE);
            if (!isFirst) {
                EditTextUtil.showSoftInputFromWindow(getActivity(), edMoneyStart);
            }
        } else if (salaryType == 3) {
            tvShichangSelect.setCompoundDrawables(unSelect, null, null, null);
            tvZidingyiSelect.setCompoundDrawables(unSelect, null, null, null);
            tvMianyiSelect.setCompoundDrawables(select, null, null, null);
            llZidingyiXinzi.setVisibility(View.GONE);
            llShichang.setVisibility(View.GONE);
        } else {//默认市场
            this.salary_type = 1;
            tvShichangSelect.setCompoundDrawables(select, null, null, null);
            tvZidingyiSelect.setCompoundDrawables(unSelect, null, null, null);
            tvMianyiSelect.setCompoundDrawables(unSelect, null, null, null);
            llShichang.setVisibility(View.VISIBLE);
            llZidingyiXinzi.setVisibility(View.GONE);
        }
        isFirst = false;
    }

    /**
     * 自动填充描述和市场定价
     */
    private void saveSelectGongZhongData() {
        etJieshao.setText("");
        tvShichangDingjia.setText("");
        StringBuilder sbDescription = new StringBuilder();
        StringBuilder sbPrice = new StringBuilder();
        for (int i = 0; i < listSelect.size(); i++) {
            String description = listSelect.get(i).getDescription();
            double price = listSelect.get(i).getPrice();
            String name = listSelect.get(i).getName();
            if (i == 0) {//偶数
                sbDescription.append(description + ";");
                sbPrice.append(name + "：¥" + price + ";");
            } else if (i % 2 == 1) {//单数
                sbDescription.append(description + ";" + "\n");
                sbPrice.append(name + "：¥" + price + ";" + "\n");
            } else if (i % 2 == 0) {//双数
                sbDescription.append(description + ";");
                sbPrice.append(name + "：¥" + price + ";");
            }
        }
        if (!TextUtils.isEmpty(sbDescription.toString())) {
            etJieshao.setText(sbDescription.toString());
        }
        if (!TextUtils.isEmpty(sbPrice.toString())) {
            tvShichangDingjia.setText(sbPrice.toString());
        }

    }

    //预提交
    private void submit() {
        if (TextUtils.isEmpty(edNum.getText().toString().trim())) {
            showTips("请输入招聘人数");
            return;
        }

        if (TextUtils.isEmpty(service_id)) {
            showTips("请输入服务内容");
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

        if (TextUtils.isEmpty(work_date)) {
            showTips("请选择工作日期");
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

        if (work_third_area_id == -1) {
            showTips("请选择工作地区");
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


        addCall(new NetUtil().setUrl(APIS.URL_TALENT_RECRUITMENT_ZDG)
                .addParams("service_id", service_id)
                .addParams("sex", "" + sex)
                .addParams("image_url", picStr)
                .addParams("video_url", videoStr)
                .addParams("voice_url", voiceStr)
                .addParams("age_id", age_id + "")
                .addParams("when_bell_worker_id", qz_id + "")
                .addParams("work_first_area_id", "")
                .addParams("work_second_area_id", work_second_area_id + "")
                .addParams("work_third_area_id", work_third_area_id + "")
                .addParams("address", edDizhi.getText().toString().trim())
                .addParams("work_date", work_date)
                .addParams("work_hour", workTime + "")
                .addParams("job_describe", etJieshao.getText().toString().trim())
                .addParams("contact_person", edUser.getText().toString().trim())
                .addParams("contact_tel", tvPhone.getText().toString().trim())
                .addParams("longitude", longlati.split(",")[0])
                .addParams("latitude", longlati.split(",")[1])
                .addParams("salary_start", edMoneyStart.getText().toString().trim())
                .addParams("salary_end", edMoneyEnd.getText().toString().trim())
                .addParams("salary_type", salary_type + "")
                .addParams("recruit_number", edNum.getText().toString().trim())
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
                            NewPayActivity.go(ZhongdgCreateRecruitFragment.this,
                                    CommonPayPresenter.TYPE_ZHONGDIANGONG_ZHAOPIN, respnse.getData().getRelate_id(), respnse.getData().getOrder_price() + "", respnse.getData().getOrder_name(), 0);
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
//            //处理你的操作
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
                work_third_area_id = data.getIntExtra("thirdId", 0);
                String thirdName = data.getStringExtra("thirdName");
                second_area_name = data.getStringExtra("secondName");
                work_second_area_id = data.getIntExtra("secondId", -1);
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
        addCall(new NetUtil().setUrl(APIS.URL_INFO_GET_AGE_LIST)
                .setCallBackData(new BaseNewNetModelimpl<List<AgeAreaModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<AgeAreaModel>> respnse, String source) {
                        showContentView();
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
     * 选择年月日
     */
    private void onYearMonthDayTimePicker() {
        new PickerViewWrap().showDateFromTodayPicker(getActivity(), new DatePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String s, String s1, String s2, String s3, String s4) {
                tvGzshiijan.setText(s + "-" + s1 + "-" + s2);
                work_date = s + "-" + s1 + "-" + s2;
            }
        }, 0);
    }


    /**
     * 获取年级列表
     */
    private void getServicesList(final boolean showPop) {
        addCall(new NetUtil()
                .setUrl(APIS.URL_TALENT_LIST_WORK_TYPE_ZDG)
                .setCallBackData(new BaseNewNetModelimpl<List<ZhongdgServiceModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<ZhongdgServiceModel>> respnse, String source) {
                        if (respnse.getData() != null) {
                            serviceModels = respnse.getData();
                            if (showPop) {
                                onServicePicker();
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<ZhongdgServiceModel>>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 初始化工作时间布局
     */
    private void initWorkTime() {
        flContent.removeAllViews();
        final Drawable select = getActivity().getResources().getDrawable(R.mipmap.select);
        select.setBounds(0, 0, select.getMinimumWidth(), select.getMinimumHeight());
        final Drawable unSelect = getActivity().getResources().getDrawable(R.mipmap.unselect);
        unSelect.setBounds(0, 0, unSelect.getMinimumWidth(), unSelect.getMinimumHeight());

        final TextView textView[] = new TextView[workTimeList.length];
        for (int i = 0; i < workTimeList.length; i++) {
            final TextView view = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.flowlayout_work_time, flContent, false);
            view.setText(workTimeList[i]);
            textView[i] = view;
            if (i == 0) {
                view.setCompoundDrawables(select, null, null, null);
            } else {
                view.setCompoundDrawables(unSelect, null, null, null);
            }
            final int position = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 0) {
                        workTime = position;
                    } else if (position == 1) {
                        workTime = 24;
                    } else {
                        workTime = 2 * (position - 1);
                    }
                    for (int i = 0; i < textView.length; i++) {
                        if (i == position) {
                            textView[i].setCompoundDrawables(select, null, null, null);
                        } else {
                            textView[i].setCompoundDrawables(unSelect, null, null, null);
                        }
                    }
                }
            });
            flContent.addView(view);
        }
    }

    /**
     * 选择服务内容
     */
    public void onServicePicker() {
        if (serviceModels != null) {
            for (ZhongdgServiceModel serviceModel : serviceModels) {
                int service_content_id = serviceModel.getService_id();
                for (ZhongdgServiceModel model : listSelect) {
                    int service_content_id1 = model.getService_id();
                    if (service_content_id == service_content_id1) {
                        serviceModel.setSelect(true);
                    }
                }
            }
            popAdapter = new CommonPopupAdapter(getContext(), serviceModels);
            listView.setAdapter(popAdapter);
            if (mCirclePop != null) {
                mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                mCirclePop.showAtAnchorView(activity == null ? viewLineBottom : activity.getRootView(), VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
            }
        } else {
            getServicesList(true);
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

                for (ZhongdgServiceModel model : serviceModels) {
                    if (model.isSelect()) {
                        listSelect.add(model);
                        res.append(model.getService_id() + ",");
                    }
                }
                if (!TextUtils.isEmpty(res)) {
                    service_id = res.subSequence(0, res.length() - 1).toString();
                } else {
                    service_id = "";
                }
                saveSelectGongZhongData();
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
                .setDimView(llRoot)
                .createPopup();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                serviceModels.get(position).setSelect(!serviceModels.get(position).isSelect());
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<ZhongdgServiceModel, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<ZhongdgServiceModel> data) {
            super(R.layout.item_recycler_gongzhong, data, false);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final ZhongdgServiceModel item) {
            helper.setText(R.id.tv_name, item.getName());
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
        if (currModel == null)
            return;

        if (TextUtils.isEmpty(edNum.getText().toString().trim())) {
            return;
        }

        if (TextUtils.isEmpty(edMoneyStart.getText().toString().trim())) {
            return;
        }

        if (TextUtils.isEmpty(edMoneyEnd.getText().toString().trim())) {
            return;
        }

        if (Double.parseDouble(edMoneyStart.getText().toString()) >= Double.parseDouble(edMoneyEnd.getText().toString())) {
            return;
        }

        String momey = edMoneyEnd.getText().toString().trim();
        BigDecimal calculate1 = new CalculateUtils().init(currModel.getCharge_fee()).multiply(edNum.getText().toString().trim()).divide("1", 2).calculate();

        //可以计算了
//        BigDecimal calculate = new CalculateUtils().init(momey).multiply(edNum.getText().toString().trim())
//                .multiply(work_hour).divide("1", 2).adds(calculate1.toString()).calculate();

        LoginSuccessInfo loginSuccessInfo = (LoginSuccessInfo) Hawk.get(Define.LOGIN_SUCCESS);
        int hw_pay_type = loginSuccessInfo.getHw_pay_type();
        if (hw_pay_type == 1) {
            tvMoneyInfo.setText(new SpanUtils().append("定金")
                    .append("¥" + currModel.getCharge_fee())
                    .setForegroundColor(ResUtils.getCommRed())
                    .append("(含信息费" + currModel.getCharge_fee() + ")")
                    .create());
        } else if (hw_pay_type == 2) {
            tvMoneyInfo.setText(new SpanUtils()
                    .append("信息费: ¥" + currModel.getCharge_fee())
                    .create());
        }
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
