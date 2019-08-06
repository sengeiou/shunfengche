package com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.model.GongzhongModel;
import com.windmillsteward.jukutech.activity.map.SelectCityFromMapActivity;
import com.windmillsteward.jukutech.activity.newpage.AudioRecoderDialog;
import com.windmillsteward.jukutech.activity.newpage.AudioRecoderUtils;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.common.model.PayInfoFeeModel;
import com.windmillsteward.jukutech.activity.newpage.common.popwindow.CommonPopwindow;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.AgeAreaModel;
import com.windmillsteward.jukutech.activity.newpage.model.LwTzRencaiListDetalModel;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.model.request.WorkTypeRequestModel;
import com.windmillsteward.jukutech.activity.newpage.newpublish.HomeCommonPublishActivity;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.common.SelectAreaActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.utils.RecorderUtils;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.LoginSuccessInfo;
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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DateTimePicker;

/**
 * A simple {@link Fragment} subclass.
 * 发布招聘页面--招聘方发布页面
 */
public class ZjTzgFabuZhaopinFragment extends BaseInitFragment {
    public static final String TAG = "ZjTzgFabuZhaopinFragment";
    @Bind(R.id.tv_select_01)
    TextView tvSelect01;
    @Bind(R.id.et_num_01)
    EditText etNum01;
    @Bind(R.id.tv_money_01)
    TextView tvMoney01;
    @Bind(R.id.iv_tips_01)
    ImageView ivTips01;
    @Bind(R.id.tv_select_02)
    TextView tvSelect02;
    @Bind(R.id.tv_fee_info)
    TextView tv_fee_info;
    @Bind(R.id.et_num_02)
    EditText etNum02;
    @Bind(R.id.tv_money_02)
    TextView tvMoney02;
    @Bind(R.id.iv_tips_02)
    ImageView ivTips02;
    @Bind(R.id.tv_select_03)
    TextView tvSelect03;
    @Bind(R.id.et_num_03)
    EditText etNum03;
    @Bind(R.id.tv_money_03)
    TextView tvMoney03;
    @Bind(R.id.iv_tips_03)
    ImageView ivTips03;
    @Bind(R.id.tv_select_04)
    EditText tvSelect04;
    @Bind(R.id.et_num_04)
    EditText etNum04;
    @Bind(R.id.ed_money_04)
    EditText ed_money_04;
    @Bind(R.id.tv_sex_tips)
    TextView tvSexTips;
    @Bind(R.id.tv_age_tips)
    TextView tvAgeTips;
    @Bind(R.id.tv_age_value)
    TextView tvAgeValue;
    @Bind(R.id.tv_diqu_tips)
    TextView tvDiquTips;
    @Bind(R.id.tv_diqu_value)
    TextView tvDiquValue;
    @Bind(R.id.tv_address_tips)
    TextView tvAddressTips;
    @Bind(R.id.tv_address_value)
    EditText tvAddressValue;
    @Bind(R.id.tv_riqi_tips)
    TextView tvRiqiTips;
    @Bind(R.id.tv_riqi_value)
    TextView tvRiqiValue;
    @Bind(R.id.tv_shijian_tips)
    TextView tvShijianTips;
    @Bind(R.id.tv_miaoshu_tips)
    TextView tvMiaoshuTips;
    @Bind(R.id.et_miaoshu_value)
    EditText etMiaoshuValue;
    @Bind(R.id.tv_lianxiren_tips)
    TextView tvLianxirenTips;
    @Bind(R.id.tv_lianxiren_value)
    EditText tvLianxirenValue;
    @Bind(R.id.tv_phone_tips)
    TextView tvPhoneTips;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    @Bind(R.id.iv_location)
    ImageView ivLocation;
    @Bind(R.id.ll_selectorr)
    LinearLayout ll_selectorr;
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
    @Bind(R.id.lay_ll_money_04)
    LinearLayout layLlMoney04;
    @Bind(R.id.tv_phone)
    TextView tvPhoneValue;
    @Bind(R.id.tv_change_phone)
    TextView tvChangePhone;
    @Bind(R.id.fl_content)
    FlowLayout flContent;
    @Bind(R.id.btn_luzhi)
    Button btnLuzhi;
    @Bind(R.id.iv_voice)
    ImageView ivVoice;
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
    @Bind(R.id.ll_root)
    LinearLayout llRoot;
    @Bind(R.id.lay_ll_luzhi)
    LinearLayout layLlLuzhi;


    private int qz_id;//强制匹配id
    private int type;//1应聘2招聘
    private int roleType;
    private int workTime = 0;
    private int currGongzhongSelect = -1;
    private int isVideoOrPic = 2;//1视频2图片
    public boolean isHiddeTitle;
    private boolean isFirst = true;
    private boolean isLoad;
    private String videoPath;
    private String voicePath;
    private String longlati;
    private String second_area_name = KV.get(Define.CURR_CITY_NAME, "");
    private String[] workTimeList = {"长期", "全天", "2小时", "4小时", "6小时", "8小时"};

    private HomeCommonPublishActivity activity;

    private LoadingDialog loadingDialog;
    private Drawable select;
    private Drawable unSelect;
    private List<GongzhongModel> gongzhongModelList = new ArrayList<>();
    private List<GongzhongModel> leftModelList = new ArrayList<>();
    private List<GongzhongModel> listSelect = new ArrayList<>();
    private List<AgeAreaModel> ageArea;
    private List<String> newVideoUrls = new ArrayList<>();
    private List<String> newPicUrls = new ArrayList<>();
    private List<String> newVoiceUrls = new ArrayList<>();
    private List<String> listVideo;
    private List<String> listPics;
    private GongzhongModel[] gongzhongModels = new GongzhongModel[3];

    private CommonPopwindow agePopwindow;

    private CommonPayPresenter payPresenter;

    private PicRecyclerViewAdapter adapterPic;

    private PayInfoFeeModel commPayInfoFee;


    //only 是否需要进入下个页面
    public static ZjTzgFabuZhaopinFragment newInstance(int qz_id, int type, int roleType, boolean isHiddeTitle) {
        Bundle args = new Bundle();
        args.putInt("qz_id", qz_id);
        args.putInt("type", type);
        args.putInt("roleType", roleType);
        args.putBoolean("isHiddeTitle", isHiddeTitle);
        ZjTzgFabuZhaopinFragment fragment = new ZjTzgFabuZhaopinFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_fabu_zhaopin;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("发布招聘");
        activity = (HomeCommonPublishActivity) getActivity();
        initVoice();
        initSelectDrawable();
        initPicAdapter();
        etNum01.addTextChangedListener(textWatcher);
        etNum02.addTextChangedListener(textWatcher);
        etNum03.addTextChangedListener(textWatcher);
        etNum04.addTextChangedListener(textWatcher);
        ed_money_04.addTextChangedListener(textWatcher);

        //将*标红
        //设置tips
        new SpannableStringViewWrap().addViews(tvSexTips, tvAgeTips, tvAddressTips, tvDiquTips, tvRiqiTips
                , tvShijianTips, tvMoneyTips, tvLianxirenTips, tvPhoneTips).build();
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
            calcuFee();
        }
    };

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

    private void initSelectDrawable() {
        select = getActivity().getResources().getDrawable(R.mipmap.select);
        select.setBounds(0, 0, select.getMinimumWidth(), select.getMinimumHeight());
        unSelect = getActivity().getResources().getDrawable(R.mipmap.unselect);
        unSelect.setBounds(0, 0, unSelect.getMinimumWidth(), unSelect.getMinimumHeight());
    }

    @Override
    protected void initData() {
        if (getArguments() != null) {
            roleType = getArguments().getInt("roleType");
            qz_id = getArguments().getInt("qz_id");
            type = getArguments().getInt("type");
            isHiddeTitle = getArguments().getBoolean("isHiddeTitle", false);

            String city_third_name = Hawk.get(Define.CURR_CITY_THIRD_NAME, "");
            tvDiquValue.setText(second_area_name + city_third_name);
            listSelect = new ArrayList<>();
            gongzhongModelList = new ArrayList<>();
            leftModelList = new ArrayList<>();

            if (roleType == PageConfig.TYPE_TEZHONGGONG) {
                tvSelect04.setEnabled(true);
                tvSelect04.setText("");
                tvSelect04.setHint("输入工种名称");
                ll_selectorr.setVisibility(View.GONE);
                llShichang.setVisibility(View.GONE);
                tvShichangSelect.setVisibility(View.GONE);
                tvMoneyTips1.setText("（2选1）");
                layLlMoney04.setVisibility(View.GONE);
                choiseDingJia(2);
            } else {
                tvSelect04.setEnabled(true);
                choiseDingJia(salary_type);
            }

            if (isHiddeTitle) {
                hidTitleView();
            }

            LoginSuccessInfo userInfo = (LoginSuccessInfo) KV.get(Define.LOGIN_SUCCESS);
            if (userInfo != null) {
                tvLianxirenValue.setText(TextUtils.isEmpty(userInfo.getNickname()) ? "" : userInfo.getNickname());
            }
            String account = (String) Hawk.get("account", "");
            if (!TextUtils.isEmpty(account)) {
                tvPhoneValue.setText(account);
            }
            work_date = DateUtil.getCurrentDate("yyyy-MM-dd");
            tvRiqiValue.setText(TextUtils.isEmpty(work_date) ? "" : work_date);
        }
        payPresenter = new CommonPayPresenter(getActivity());
        getAgeArea(false);
        getGongzhongInfo(false);
        getOtherWorkFee(false);
        initEditText();
        initWorkTime();
    }

    /**
     * 请求工种列表成功后再去请求强制匹配详情
     */
    private void getQzPiPei() {
        if (qz_id == 0) {

        } else {
            if (type == HomeCommonPublishActivity.ZHAOPIN) {
                qzPiPei();
            } else {
                qz_id = 0;
            }
        }
    }


    @Override
    protected void refreshPageData() {
        getGongzhongInfo(false);
        getOtherWorkFee(false);
    }

    private void initEditText() {
        edMoneyStart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                RegexChkUtil.saveTwoPoint(s);//只能输入两位小数
            }
        });
        edMoneyEnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                RegexChkUtil.saveTwoPoint(s);
            }
        });
    }

    private void initWorkTime() {
        flContent.removeAllViews();
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

    @OnClick({R.id.tv_shichang_select, R.id.iv_video, R.id.tv_zidingyi_select, R.id.iv_delete_video, R.id.tv_change_phone, R.id.tv_mianyi_select, R.id.tv_sex_buxian, R.id.tv_sex_boy, R.id.tv_sex_girl, R.id.tv_select_01, R.id.iv_tips_01, R.id.tv_address_value, R.id.tv_select_02, R.id.tv_age_value, R.id.iv_tips_02, R.id.tv_select_03, R.id.iv_tips_03, R.id.tv_diqu_value, R.id.iv_location, R.id.tv_riqi_value, R.id.tv_submit})
    public void onViewClicked(final View view) {
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
                            tvPhoneValue.setText(new_phone);
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
            case R.id.tv_select_01:
                currGongzhongSelect = 0;
                onGongzhongPicker();
                break;
            case R.id.iv_tips_01:
                showPriceDialog(tvMoney01);
                break;
            case R.id.tv_select_02:
                currGongzhongSelect = 1;
                onGongzhongPicker();
                break;
            case R.id.iv_tips_02:
                showPriceDialog(tvMoney02);
                break;
            case R.id.tv_select_03:
                currGongzhongSelect = 2;
                onGongzhongPicker();
                break;
            case R.id.iv_tips_03:
                showPriceDialog(tvMoney03);
                break;
            case R.id.tv_diqu_value:
                SelectAreaActivity.go(this, work_second_area_id, work_third_area_id, second_area_name);
                break;
            case R.id.tv_age_value:
                onAgePicker();
                break;
            case R.id.iv_location:
                if (!chekcLocationPermission()) {
                    return;
                }
                SelectCityFromMapActivity.go(this);
                break;
            case R.id.tv_riqi_value:
                onYearMonthDayTimePicker();
                break;
            case R.id.tv_submit:
                submit();
                break;
        }
    }

    private void qzPiPei() {
        if (qz_id == 0) {
            return;
        }
        addCall(new NetUtil()
                .setUrl(APIS.URL_LW_TZ_RENCAI_INFO_LIST_DETAIL)
                .addParams("info_id", qz_id + "")
                .setCallBackData(new BaseNewNetModelimpl<LwTzRencaiListDetalModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LwTzRencaiListDetalModel> respnse, String source) {
                        if (respnse.getData() != null) {
                            showContentView();
                            LwTzRencaiListDetalModel data = respnse.getData();
                            sex = data.getSex();
                            choiseSex(sex);
                            if (StringUtil.isAllNotEmpty(data.getWork_second_area_name(), data.getWork_third_area_name())) {
                                tvDiquValue.setText(data.getWork_second_area_name() + data.getWork_third_area_name());
                            }
                            tvRiqiValue.setText(StringUtil.notEmptyBackValue(data.getWork_date()));
                            salary_type = data.getSalary_type();
                            choiseDingJia(salary_type);
                            if (salary_type == 2) {

                                int start = data.getSalary_fee();
                                if (start == 0) {
                                    edMoneyStart.setText("");
                                } else {
                                    edMoneyStart.setText(start + "");
                                }
                                int end = data.getEnd_salary_fee();
                                if (end == 0) {
                                    edMoneyEnd.setText("");
                                } else {
                                    edMoneyEnd.setText( data.getEnd_salary_fee()+"" );
                                }

                            }
                            int work_hour = data.getWork_hour();
                            int childCount = flContent.getChildCount();//6
                            for (int i = 0; i < childCount; i++) {
                                TextView childAt = (TextView) flContent.getChildAt(i);
                                childAt.setCompoundDrawables(unSelect, null, null, null);
                            }
                            if (childCount == 6) {
                                switch (work_hour) {
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
                                if (roleType == PageConfig.TYPE_ZHONGJIE) {
                                    List<LwTzRencaiListDetalModel.WorkTypeNameListBean> work_type_list = data.getWork_type_name_list();
                                    for (int i = 0; i < work_type_list.size(); i++) {
                                        LwTzRencaiListDetalModel.WorkTypeNameListBean workTypeBean = work_type_list.get(i);
                                        String name = workTypeBean.getName();
                                        GongzhongModel model = new GongzhongModel();
                                        model.setName(workTypeBean.getName());
                                        model.setWork_type_id(workTypeBean.getWork_type_id());
                                        model.setInfo_fee(workTypeBean.getInfo_fee());
                                        model.setPrice(workTypeBean.getPrice());
                                        if (i == 0) {
                                            currGongzhongSelect = 0;
                                            model.setPosition(currGongzhongSelect + 1);
                                            gongzhongModels[i] = model;
                                            tvSelect01.setText(name);
                                            etNum01.setText("1");
                                            tvMoney01.setText("¥" + model.getPrice() + "元/天");
                                            listSelect.add(model);
                                        } else if (i == 1) {
                                            currGongzhongSelect = 1;
                                            model.setPosition(currGongzhongSelect + 1);
                                            gongzhongModels[i] = model;
                                            tvSelect02.setText(name);
                                            etNum02.setText("1");
                                            tvMoney02.setText("¥" + model.getPrice() + "元/天");
                                            listSelect.add(model);
                                        } else if (i == 2) {
                                            currGongzhongSelect = 2;
                                            model.setPosition(currGongzhongSelect + 1);
                                            gongzhongModels[i] = model;
                                            tvSelect03.setText(name);
                                            etNum03.setText("1");
                                            tvMoney03.setText("¥" + model.getPrice() + "元/天");
                                            listSelect.add(model);
                                        }
                                    }
                                    for (GongzhongModel model : gongzhongModelList) {
                                        if (listSelect.contains(model)) {
                                            leftModelList.remove(model);
                                        }
                                    }
                                }
                                String other_work_type = data.getOther_work_type();
                                if (!TextUtils.isEmpty(other_work_type)) {
                                    tvSelect04.setText(StringUtil.notEmptyBackValue(other_work_type));
                                    etNum04.setText("1");
                                }
                                calcuFee();
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LwTzRencaiListDetalModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    /**
     * 显示定价的弹窗
     *
     * @param view
     */
    private void showPriceDialog(TextView view) {
        String content = view.getText().toString();
        if (TextUtils.isEmpty(content)) {
            return;
        }
        final BaseDialog baseDialog = new BaseDialog(getHoldingActivity());
        baseDialog.showTwoButton("提示", "该工种的市场定价为" + content, "确定", "取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
            }
        });
    }

    /**
     * 获取工种信息
     */
    public void getGongzhongInfo(final boolean showPop) {
        addCall(new NetUtil()
                .setUrl(APIS.URL_TALENT_LIST_WORK_TYPE)
                .setCallBackData(new BaseNewNetModelimpl<List<GongzhongModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<GongzhongModel>> respnse, String source) {
                        if (respnse.getData() != null) {
                            showContentView();
                            isLoad = true;
                            gongzhongModelList.addAll(respnse.getData());
                            leftModelList.addAll(respnse.getData());
                            if (showPop) {
                                onGongzhongPicker();
                            }
                            //放在这里是因为，必须得请求工种列表成功之后，工种赋值才没问题。
                            getQzPiPei();

                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<GongzhongModel>>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    /**
     * 根据选择的性别设置UI
     *
     * @param sex
     */
    private void choiseSex(int sex) {
        this.sex = sex;
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
        if (roleType == PageConfig.TYPE_TEZHONGGONG) {
            if (salaryType == 1) {//如果是特种工，市场定价改为自定义价格
                this.salary_type = 2;
            }
            llShichang.setVisibility(View.GONE);
            tvShichangSelect.setVisibility(View.GONE);
        }
        if (this.salary_type == 1) {//选中市场定价
            tvShichangSelect.setCompoundDrawables(select, null, null, null);
            tvZidingyiSelect.setCompoundDrawables(unSelect, null, null, null);
            tvMianyiSelect.setCompoundDrawables(unSelect, null, null, null);
            llShichang.setVisibility(View.VISIBLE);
            llZidingyiXinzi.setVisibility(View.GONE);
        } else if (this.salary_type == 2) {
            tvShichangSelect.setCompoundDrawables(unSelect, null, null, null);
            tvZidingyiSelect.setCompoundDrawables(select, null, null, null);
            tvMianyiSelect.setCompoundDrawables(unSelect, null, null, null);
            llShichang.setVisibility(View.GONE);
            llZidingyiXinzi.setVisibility(View.VISIBLE);
            if (!isFirst) {
                EditTextUtil.showSoftInputFromWindow(getActivity(), edMoneyStart);
            }
        } else if (this.salary_type == 3) {
            tvShichangSelect.setCompoundDrawables(unSelect, null, null, null);
            tvZidingyiSelect.setCompoundDrawables(unSelect, null, null, null);
            tvMianyiSelect.setCompoundDrawables(select, null, null, null);
            llShichang.setVisibility(View.GONE);
            llZidingyiXinzi.setVisibility(View.GONE);
        }
        isFirst = false;
    }

    /**
     * 自动填充描述和市场定价
     */
    private void saveSelectGongZhongData() {
        etMiaoshuValue.setText("");
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
            etMiaoshuValue.setText(sbDescription.toString());
        }
        if (!TextUtils.isEmpty(sbPrice.toString())) {
            tvShichangDingjia.setText(sbPrice.toString());
        }

    }

    /**
     * 选择年月日
     */
    private void onYearMonthDayTimePicker() {
        new PickerViewWrap().showDateFromTodayPicker(getActivity(), new DatePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String s, String s1, String s2, String s3, String s4) {
                tvRiqiValue.setText(s + "-" + s1 + "-" + s2);
                work_date = s + "-" + s1 + "-" + s2;
            }
        }, 0);
    }

    /**
     * 选择工种
     */
    public void onGongzhongPicker() {
        if (!leftModelList.isEmpty()) {

            if (activity != null) {
                CommonPopwindow commonPopwindow = new CommonPopwindow(getActivity(), activity.getRootView(), leftModelList);
                final EasyPopup circlePop = commonPopwindow.getCirclePop();
                LimitHeightListView listView = commonPopwindow.getListView();
                commonPopwindow.bindAdapter();
                commonPopwindow.setTitle("选择工种");
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        GongzhongModel gongzhongModel = (GongzhongModel) parent.getItemAtPosition(position);
                        if (circlePop != null) {
                            circlePop.dismiss();
                        }
                        if (gongzhongModel != null) {
                            gongzhongModel.setPosition(currGongzhongSelect + 1);

                            if (!listSelect.contains(gongzhongModel))
                                listSelect.add(gongzhongModel);
                            else {
                                listSelect.set(listSelect.indexOf(gongzhongModel), gongzhongModel);
                            }

                            leftModelList.clear();//
                            leftModelList.addAll(gongzhongModelList);

                            GongzhongModel last = gongzhongModels[currGongzhongSelect];
                            if (last != null)
                                if (listSelect.contains(last)) {
                                    listSelect.remove(last);
                                }
//已选列表数据contain leftList里，则leftlist移除这条数据
                            for (GongzhongModel model : gongzhongModelList) {
                                if (listSelect.contains(model)) {
                                    leftModelList.remove(model);
                                }
                            }

                            //重置状态
                            reset();

                            for (GongzhongModel model : listSelect) {
                                if (model.getPosition() - 1 == 0) {
                                    tvSelect01.setText(model.getName());
                                    tvMoney01.setText("¥" + model.getPrice() + "元/天");
                                } else if (model.getPosition() - 1 == 1) {
                                    tvSelect02.setText(model.getName());
                                    tvMoney02.setText("¥" + model.getPrice() + "元/天");
                                } else if (model.getPosition() - 1 == 2) {
                                    tvSelect03.setText(model.getName());
                                    tvMoney03.setText("¥" + model.getPrice() + "元/天");
                                }
                            }
                            saveSelectGongZhongData();
                            calcuFee();

                            gongzhongModels[currGongzhongSelect] = gongzhongModel;
                        }
                    }

                });
                if (circlePop != null) {
                    circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    circlePop.showAtAnchorView(activity.getRootView(), VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
                }
            }

        } else {
            if (isLoad)
                return;
            getGongzhongInfo(true);
        }
    }

    private void reset() {
        tvSelect01.setHint("选择工种");
        tvMoney01.setText("");
        tvSelect02.setHint("选择工种");
        tvMoney02.setText("");
        tvSelect03.setHint("选择工种");
        tvMoney03.setText("");
    }

    /**
     * 获取年龄范围
     *
     * @param showPop
     */
    private void getAgeArea(final boolean showPop) {
        addCall(new NetUtil().setUrl(APIS.URL_INFO_GET_AGE_LIST)
                .addParams("type", roleType == PageConfig.TYPE_ZHONGJIE ? "10" : "17")
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
                                agePopwindow = new CommonPopwindow(getActivity(), activity.getRootView(), ageArea);
                                agePopwindow.updateAdapter();
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
     * 选择年龄
     */
    public void onAgePicker() {
        if (ageArea != null) {
            if (activity != null) {
                final EasyPopup circlePop = agePopwindow.getCirclePop();
                LimitHeightListView listView = agePopwindow.getListView();
                agePopwindow.bindAdapter();
                agePopwindow.setTitle("选择年龄");
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AgeAreaModel model = (AgeAreaModel) parent.getItemAtPosition(position);
                        if (circlePop != null) {
                            circlePop.dismiss();
                        }
                        for (AgeAreaModel areaModel : ageArea) {
                            areaModel.setSelect(false);
                        }
                        if (model != null) {
                            model.setSelect(true);
                            tvAgeValue.setText(model.getName());
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

    private String work_type_info, work_date;
    private int sex = 0, age_id = -1, salary_type = 1,
            work_second_area_id = KV.get(Define.CURR_CITY_ID, -1),
            work_third_area_id = KV.get(Define.CURR_CITY_THIRD_ID, -1);


    private void submit() {
        if (!chekcLocationPermission())
            return;

        if (ll_selectorr.getVisibility() == View.GONE) {//特种工不用输入价格
            if (StringUtil.isSomeOneEmpty(tvSelect04.getText().toString(), etNum04.getText().toString())) {
                showTips("请完善工种的信息");
                return;
            }
        } else {
            if (listSelect.isEmpty()) {
                //没有选择工种
                if (StringUtil.isSomeOneEmpty(tvSelect04.getText().toString(), etNum04.getText().toString(), ed_money_04.getText().toString())) {
                    showTips("至少需要添加一条工种信息");
                    return;
                }
            } else {
                for (GongzhongModel model : listSelect) {
                    if (model.getPosition() - 1 == 0) {
                        if (TextUtils.isEmpty(etNum01.getText().toString())) {
                            showTips("请输入工种'" + model.getName() + "'的人数");
                            return;
                        }
                    } else if (model.getPosition() - 1 == 1) {
                        if (TextUtils.isEmpty(etNum02.getText().toString())) {
                            showTips("请输入工种'" + model.getName() + "'的人数");
                            return;
                        }
                    } else if (model.getPosition() - 1 == 2) {
                        if (TextUtils.isEmpty(etNum03.getText().toString())) {
                            showTips("请输入工种'" + model.getName() + "'的人数");
                            return;
                        }
                    }
                }
                if (StringUtil.isAllEmpty(tvSelect01.getText().toString(), etNum01.getText().toString(), tvMoney01.getText().toString())) {
                    if (StringUtil.hasEmptyAndNotEmpty(tvSelect04.getText().toString(), etNum04.getText().toString(), ed_money_04.getText().toString())) {
                        showTips("请完善其他工种的信息");
                        return;
                    }
                } else if (StringUtil.isAllEmpty(tvSelect02.getText().toString(), etNum02.getText().toString(), tvMoney02.getText().toString())) {
                    if (StringUtil.hasEmptyAndNotEmpty(tvSelect04.getText().toString(), etNum04.getText().toString(), ed_money_04.getText().toString())) {
                        showTips("请完善其他工种的信息");
                        return;
                    }
                } else if (StringUtil.isAllEmpty(tvSelect03.getText().toString(), etNum03.getText().toString(), tvMoney03.getText().toString())) {
                    if (StringUtil.hasEmptyAndNotEmpty(tvSelect04.getText().toString(), etNum04.getText().toString(), ed_money_04.getText().toString())) {
                        showTips("请完善其他工种的信息");
                        return;
                    }
                }
                if (StringUtil.isAllEmpty(etNum01.getText().toString(), etNum02.getText().toString(), etNum03.getText().toString())) {//如果选择的工种都为空，则需要判断其他工种有没有输入
                    if (StringUtil.hasEmptyAndNotEmpty(etNum04.getText().toString(), ed_money_04.getText().toString())) {
                        showTips("请完善其他工种的信息");
                        return;
                    }
                }

            }
        }
        if (sex == -1) {
            showTips("请选择性别");
            return;
        }

        if (age_id == -1) {
            showTips("请选择年龄");
            return;
        }

        if (work_third_area_id == -1) {
            showTips("请选择地区");
            return;
        }

        if (TextUtils.isEmpty(tvAddressValue.getText().toString())) {
            showTips("请输入地址");
            return;
        }
//        if (TextUtils.isEmpty(longlati)) {//改为输入之后，可以不传经纬度
//            showTips("请输入地址");
//            return;
//        }

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

        if (TextUtils.isEmpty(tvLianxirenValue.getText().toString())) {
            showTips("请输入联系人");
            return;
        }

        if (TextUtils.isEmpty(tvPhoneValue.getText().toString())) {
            showTips("请输入手机号");
            return;
        }
        List<WorkTypeRequestModel> req = new ArrayList<>();
        //组装work_type_info
        for (GongzhongModel model : listSelect) {
            WorkTypeRequestModel workTypeRequestModel = new WorkTypeRequestModel();
            workTypeRequestModel.setWork_type_id(model.getWork_type_id());
            workTypeRequestModel.setPrice(model.getPrice());
            if (model.getPosition() - 1 == 0) {
                if (!TextUtils.isEmpty(etNum01.getText().toString()))
                    workTypeRequestModel.setNum(Integer.parseInt(etNum01.getText().toString()));
            } else if (model.getPosition() - 1 == 1) {
                if (!TextUtils.isEmpty(etNum02.getText().toString()))
                    workTypeRequestModel.setNum(Integer.parseInt(etNum02.getText().toString()));
            } else if (model.getPosition() - 1 == 2) {
                if (!TextUtils.isEmpty(etNum03.getText().toString()))
                    workTypeRequestModel.setNum(Integer.parseInt(etNum03.getText().toString()));
            }
            req.add(workTypeRequestModel);
        }
        WorkTypeRequestModel workTypeRequestModel = new WorkTypeRequestModel();
        if (ll_selectorr.getVisibility() == View.GONE) {
            workTypeRequestModel.setOther_work_type(tvSelect04.getText().toString());
        } else
            workTypeRequestModel.setOther_work_type(tvSelect04.getText().toString());
        if (!TextUtils.isEmpty(etNum04.getText().toString()))
            workTypeRequestModel.setNum(Integer.parseInt(etNum04.getText().toString()));
        if (!TextUtils.isEmpty(ed_money_04.getText().toString()))
            workTypeRequestModel.setPrice(Double.parseDouble(ed_money_04.getText().toString()));
        req.add(workTypeRequestModel);
        work_type_info = new Gson().toJson(req);

        commit();
    }

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

    /**
     * 提交
     */
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

        addCall(new NetUtil().setUrl(APIS.URL_TALENT_RECRUITMENT)
                .addParams("type", ll_selectorr.getVisibility() == View.VISIBLE ? "1" : "2")
                .addParams("work_type_info", work_type_info)
                .addParams("sex", sex + "")
                .addParams("age_id", age_id + "")
                .addParams("image_url", picStr)
                .addParams("applicant_id", qz_id + "")
                .addParams("video_url", videoStr)
                .addParams("voice_url", voiceStr)
                .addParams("contact_tel", tvPhoneValue.getText().toString())
                .addParams("work_first_area_id", "")
                .addParams("work_second_area_id", work_second_area_id + "")
                .addParams("work_third_area_id", work_third_area_id + "")
                .addParams("work_date", work_date)
                .addParams("work_hour", workTime + "")
                .addParams("work_info", etMiaoshuValue.getText().toString())
                .addParams("longitude", longlati.split(",")[0])
                .addParams("latitude", longlati.split(",")[1])
                .addParams("address", tvAddressValue.getText().toString())
                .addParams("contact_person", tvLianxirenValue.getText().toString())
                .addParams("salary_type", salary_type + "")
                .addParams("salary_fee", edMoneyStart.getText().toString())
                .addParams("end_salary_fee", edMoneyEnd.getText().toString())
                .setCallBackData(new BaseNewNetModelimpl<PublicResultModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        loadingDialog.dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<PublicResultModel> respnse, String source) {
                        loadingDialog.dismiss();
                        //去支付
                        if (respnse.getData() != null) {
                            NewPayActivity.go(ZjTzgFabuZhaopinFragment.this, roleType == PageConfig.TYPE_ZHONGJIE ? CommonPayPresenter.TYPE_LAOGONG_ZHAOPIN : CommonPayPresenter.TYPE_TEZHONG_ZHAOPIN, respnse.getData().getRelate_id(), respnse.getData().getOrder_price() + "", respnse.getData().getOrder_name(), 0);
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<PublicResultModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    /**
     * 获取支付信息
     */

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
                tvAddressValue.setText(address);

            }
        } else if (requestCode == SelectAreaActivity.GET_CITY_REQUEST_CODE) {
            //地区
            if (data != null) {
                work_third_area_id = data.getIntExtra("thirdId", 0);
                String thirdName = data.getStringExtra("thirdName");
                second_area_name = data.getStringExtra("secondName");
                work_second_area_id = data.getIntExtra("secondId", -1);
                tvDiquValue.setText(second_area_name + thirdName);
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


    private void getOtherWorkFee(final boolean calcu) {
        int type = 0;
        if (roleType == PageConfig.TYPE_ZHONGJIE) {
            type = CommonPayPresenter.FEE_TYPE_LAOWU_ZHAOPIN;
        } else if (roleType == PageConfig.TYPE_TEZHONGGONG) {
            type = CommonPayPresenter.FEE_TYPE_TEZHONG_ZHAOPIN;
        }
        payPresenter.loadPayInfoFeeData(type, new CommonPayPresenter.DataCallBack<PayInfoFeeModel>() {
            @Override
            public void onFail(int type, String msg) {
                showTips(msg);
            }

            @Override
            public void onSucess(int code, BaseResultInfo<PayInfoFeeModel> respnse, String source) {
                if (respnse.getData() != null) {
                    showContentView();
                    commPayInfoFee = respnse.getData();
                    if (calcu) {
                        calcuFee();
                    }
                }
            }
        });
    }

    //计算
    private void calcuFee() {
        if (commPayInfoFee == null) {
            getOtherWorkFee(true);
        } else {
            /**
             * 劳务中介发布招聘总费用计算：
             假如现在选了X,Y,Z和其它工种，这四个工种
             那么，总价
             = (X的单价乘以X的人数+X的信息费乘以X的人数)+ (Y的单价乘以Y的人数+Y的信息费乘以Y的人数)+
             (Z的单价乘以Z的人数+Z的信息费乘以Z的人数)+(其它工种的单价乘以其它工种的人数+其它工种的信息费乘以其它工种的人数)
             */
            String charge_fee = commPayInfoFee.getCharge_fee();
            CalculateUtils calculateUtils1 = new CalculateUtils();
            CalculateUtils calculateUtils2 = new CalculateUtils();
            CalculateUtils calculateUtils3 = new CalculateUtils();
            CalculateUtils calculateUtils4 = new CalculateUtils();
            BigDecimal result = new BigDecimal("0");
            BigDecimal infoResult = new BigDecimal("0");
            for (GongzhongModel model : listSelect) {
                if (model.getPosition() - 1 == 0) {
                    String num1 = etNum01.getText().toString();
                    if (TextUtils.isEmpty(num1)) {
                        continue;
                    }
                    BigDecimal calculate = calculateUtils1.init(num1).multiply(model.getPrice()).calculate();
                    BigDecimal calculate1 = calculateUtils1.init(num1).multiply(model.getInfo_fee()).calculate();
                    infoResult = infoResult.add(calculate1);
                    result = result.add(calculate.add(calculate1));
                } else if (model.getPosition() - 1 == 1) {
                    String num2 = etNum02.getText().toString();
                    if (TextUtils.isEmpty(etNum02.getText().toString())) {
                        continue;
                    }
                    BigDecimal calculate = calculateUtils2.init(num2).multiply(model.getPrice()).calculate();
                    BigDecimal calculate1 = calculateUtils2.init(num2).multiply(model.getInfo_fee()).calculate();
                    infoResult = infoResult.add(calculate1);
                    result = result.add(calculate.add(calculate1));
                } else if (model.getPosition() - 1 == 2) {
                    String num3 = etNum03.getText().toString();
                    if (TextUtils.isEmpty(etNum03.getText().toString())) {
                        continue;
                    }
                    BigDecimal calculate = calculateUtils3.init(num3).multiply(model.getPrice()).calculate();
                    BigDecimal calculate1 = calculateUtils3.init(num3).multiply(model.getInfo_fee()).calculate();
                    infoResult = infoResult.add(calculate1);
                    result = result.add(calculate.add(calculate1));
                }
            }
            if (roleType == PageConfig.TYPE_ZHONGJIE) {
                //计算其他工种
                if (StringUtil.isAllNotEmpty(etNum04.getText().toString(), ed_money_04.getText().toString())) {
                    String num4 = etNum04.getText().toString();
                    String money4 = ed_money_04.getText().toString();
                    BigDecimal calculate = calculateUtils4.init(num4).multiply(money4).calculate();
                    BigDecimal calculate1 = calculateUtils4.init(num4).multiply(charge_fee).calculate();
                    infoResult = infoResult.add(calculate1);
                    result = result.add(calculate.add(calculate1));
                }
            } else if (roleType == PageConfig.TYPE_TEZHONGGONG) {
                //计算其他工种
                if (StringUtil.isAllNotEmpty(etNum04.getText().toString())) {
                    String num4 = etNum04.getText().toString();
                    String money4 = ed_money_04.getText().toString();
                    money4 = "1";
                    BigDecimal calculate = calculateUtils4.init(num4).multiply(money4).calculate();
                    BigDecimal calculate1 = calculateUtils4.init(num4).multiply(charge_fee).calculate();
                    infoResult = infoResult.add(calculate1);
                    result = result.add(calculate.add(calculate1));
                }
            }


            //得到结果 result
            if (roleType == PageConfig.TYPE_ZHONGJIE) {
                LoginSuccessInfo loginSuccessInfo = (LoginSuccessInfo) Hawk.get(Define.LOGIN_SUCCESS);
                int sw_pay_type = loginSuccessInfo.getSw_pay_type();
                if (sw_pay_type == 1) {//全额
                    tv_fee_info.setText(new SpanUtils().append("")
                            .append("￥" + result.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP))
                            .setForegroundColor(ResUtils.getCommRed())
                            .append("（信息费: ¥" + infoResult.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP) + "）")
                            .create());
                } else if (sw_pay_type == 2) {//只付信息费
                    tv_fee_info.setText(new SpanUtils().append("")
                            .setForegroundColor(ResUtils.getCommRed())
                            .append("信息费: ¥" + infoResult.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP))
                            .create());
                }
            } else if (roleType == PageConfig.TYPE_TEZHONGGONG) {
                LoginSuccessInfo loginSuccessInfo = (LoginSuccessInfo) Hawk.get(Define.LOGIN_SUCCESS);
                int spw_pay_type = loginSuccessInfo.getSpw_pay_type();
                if (spw_pay_type == 1) {//全额
                    tv_fee_info.setText(new SpanUtils().append("")
                            .append("￥" + result.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP))
                            .setForegroundColor(ResUtils.getCommRed())
                            .append("（信息费: ¥" + infoResult.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP) + "）")
                            .create());
                } else if (spw_pay_type == 2) {//只付信息费
                    tv_fee_info.setText(new SpanUtils().append("")
                            .setForegroundColor(ResUtils.getCommRed())
                            .append("信息费: ¥" + infoResult.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP))
                            .create());
                }
            }
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

    /**
     * 打开相册
     */
    public void openXiangce() {
        List<LocalMedia> selectList = new ArrayList<>();
        //行车证件
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

}
