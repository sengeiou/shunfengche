package com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz;


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
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.model.GongzhongModel;
import com.windmillsteward.jukutech.activity.newpage.adapter.CommonPopupAdapter;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.common.model.PayInfoFeeModel;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForLgAndTzg;
import com.windmillsteward.jukutech.activity.newpage.model.LwTzWorkListDetailModel;
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
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DateTimePicker;

/**
 * A simple {@link Fragment} subclass.
 * 添加劳工信息--应聘方发布页面
 */
public class ZjTzgAddWorkInfoFragment extends BaseInitFragment {
    public static final String TAG = "ZjTzgAddWorkInfoFragment";
    //添加
    public static final int TYPE_ADD = 0;
    //编辑
    public static final int TYPE_EDIT = 1;
    @Bind(R.id.tv_name_tips)
    TextView tvNameTips;
    @Bind(R.id.ed_name)
    EditText edName;
    @Bind(R.id.tv_sex_tips)
    TextView tvSexTips;
    @Bind(R.id.view_line_bottom)
    View view_line_bottom;
    @Bind(R.id.tv_sex_boy)
    TextView tv_sex_boy;
    @Bind(R.id.tv_sex_girl)
    TextView tv_sex_girl;
    @Bind(R.id.tv_age_tips)
    TextView tvAgeTips;
    @Bind(R.id.tv_type_tips)
    TextView tvTypeTips;
    @Bind(R.id.tv_type_tips1)
    TextView tv_type_tips1;
    @Bind(R.id.et_age)
    EditText et_age;
    @Bind(R.id.ed_type)
    TextView tvType;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.tv_phone_tips)
    TextView tvPhoneTips;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_change_phone)
    TextView tvChangePhone;
    @Bind(R.id.tv_address_tips)
    TextView tvAddressTips;
    @Bind(R.id.ll_gongzhong)
    LinearLayout ll_gongzhong;
    @Bind(R.id.ed_address)
    TextView edAddress;
    @Bind(R.id.tv_money_info)
    TextView tvMoneyInfo;
    @Bind(R.id.tv_time_tips)
    TextView tvTimeTips;
    @Bind(R.id.ed_time)
    TextView edTime;
    @Bind(R.id.tv_pipei_tips)
    TextView tv_pipei_tips;
    @Bind(R.id.tv_money_tips)
    TextView tvMoneyTips;
    @Bind(R.id.tv_money_tips1)
    TextView tv_money_tips1;
    @Bind(R.id.et_jieshao)
    EditText etJieshao;
    @Bind(R.id.lay_ll_luzhi)
    LinearLayout layLlLuzhi;

    @Bind(R.id.ll_root)
    LinearLayout ll_root;
    @Bind(R.id.ll_gongzhong1)
    LinearLayout ll_gongzhong1;
    @Bind(R.id.ed_gongzhong)
    EditText ed_gongzhong;

    @Bind(R.id.tv_shichang_select)
    TextView tvShichangSelect;
    @Bind(R.id.tv_zidingyi_select)
    TextView tvZidingyiSelect;
    @Bind(R.id.tv_mianyi_select)
    TextView tvMianyiSelect;
    @Bind(R.id.tv_shichang_dingjia)
    TextView tvShichangDingjia;
    @Bind(R.id.ll_shichang)
    LinearLayout llShichang;
    @Bind(R.id.ed_money_start)
    EditText edMoneyStart;
    @Bind(R.id.ed_money_end)
    EditText edMoneyEnd;
    @Bind(R.id.ll_zidingyi_xinzi)
    LinearLayout llZidingyiXinzi;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    @Bind(R.id.tv_money_tips2)
    TextView tv_money_tips2;
    @Bind(R.id.fl_content)
    FlowLayout flContent;
    @Bind(R.id.recyclerview_pic)
    RecyclerView recyclerviewPic;
    @Bind(R.id.iv_video)
    CircleImageView ivVideo;
    @Bind(R.id.btn_luzhi)
    Button btn_luzhi;
    @Bind(R.id.tv_length)
    TextView tv_length;
    @Bind(R.id.iv_voice)
    ImageView iv_voice;
    @Bind(R.id.lay_ll_voice)
    LinearLayout lay_ll_voice;
    @Bind(R.id.iv_voice_delete)
    ImageView ivVoiceDelete;
    @Bind(R.id.iv_delete_video)
    ImageView ivDeleteVideo;

    private CommonPayPresenter payPresenter;
    private PayInfoFeeModel currModel;

    private int workTime = 0;
    private int sex = 0;
    private int qz_id;//强制匹配id
    private int type;//1应聘2招聘
    private int roleType;
    private int isVideoOrPic = 2;//1视频2图片
    private int secondAreaId = KV.get(Define.CURR_CITY_ID, -1);
    private int thirdAreaId = KV.get(Define.CURR_CITY_THIRD_ID, -1);
    private int salaryType = 1;//默认市场定价
    public boolean isHiddeTitle;
    private boolean isFirst = true;//第一次进来不获取自定义薪资的焦点
    private String workTypeId;
    private String workDate;
    private String videoPath;
    private String second_area_name = KV.get(Define.CURR_CITY_NAME, "");
    private String[] workTimeList = {"长期", "全天", "2小时", "4小时", "6小时", "8小时"};

    private HomeCommonPublishActivity activity;

    private LoadingDialog loadingDialog;
    private String voicePath;
    private Drawable select;
    private Drawable unSelect;

    private List<GongzhongModel> gongzhongModelList = new ArrayList<>();
    private List<GongzhongModel> listSelect = new ArrayList<>();
    private List<String> newVideoUrls = new ArrayList<>();
    private List<String> newPicUrls = new ArrayList<>();
    private List<String> newVoiceUrls = new ArrayList<>();
    private List<String> listVideo;
    private List<String> listPics;
    private EasyPopup mCirclePop;
    private LimitHeightListView listView;

    private CommonPopupAdapter popAdapter;
    private RecyclerViewAdapter adapter;
    private PicRecyclerViewAdapter adapterPic;

    private LastPublicForLgAndTzg lastPublicForLgAndTzg;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_add_work_info;
    }

    public static ZjTzgAddWorkInfoFragment newInstance(int qz_id, int type, int roleType, boolean isHiddeTitle) {
        Bundle args = new Bundle();
        args.putInt("qz_id", qz_id);
        args.putInt("type", type);
        args.putInt("roleType", roleType);
        args.putBoolean("isHiddeTitle", isHiddeTitle);
        ZjTzgAddWorkInfoFragment fragment = new ZjTzgAddWorkInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        activity = (HomeCommonPublishActivity) getActivity();

        initPopup();
        initVoice();
        initSelectDrawable();
        //设置tips
        new SpannableStringViewWrap().addViews(tvNameTips, tvSexTips, tvAgeTips, tvTypeTips, tvPhoneTips
                , tvAddressTips, tvMoneyTips, tvTimeTips, tv_money_tips2, tv_pipei_tips, tv_type_tips1, tv_money_tips1).build();

    }

    private void initVoice() {

        final RecorderUtils recorderUtils = new RecorderUtils(activity, new RecorderUtils.CallBack() {
            @Override
            public void pressButton(int status) {
                if (status == 0) {
                    btn_luzhi.setText("按住不要松手");
                    layLlLuzhi.setBackgroundResource(R.drawable.shape_recoder_btn_recoding);
                } else if (status == 1) {
                    btn_luzhi.setText("录音成功");
                    layLlLuzhi.setBackgroundResource(R.drawable.shape_recoder_btn_normal);
                } else if (status == 2) {
                    btn_luzhi.setText("录音失败");
                    layLlLuzhi.setBackgroundResource(R.drawable.shape_recoder_btn_normal);
                }
            }

            @Override
            public void luzhiFinish(String filePath, long length) {
                voicePath = filePath;
                lay_ll_voice.setVisibility(View.VISIBLE);
                tv_length.setText((int) (length / 1000) + "s");
            }
        });

        recorderUtils.initView(ll_root, btn_luzhi, iv_voice);
        lay_ll_voice.setOnClickListener(new View.OnClickListener() {
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
                    lay_ll_voice.setVisibility(View.GONE);
                    btn_luzhi.setText("录制语音");
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
            qz_id = getArguments().getInt("qz_id");
            type = getArguments().getInt("type");
            roleType = getArguments().getInt("roleType");
            isHiddeTitle = getArguments().getBoolean("isHiddeTitle", false);

            LoginSuccessInfo userInfo = (LoginSuccessInfo) KV.get(Define.LOGIN_SUCCESS);
            if (userInfo != null) {
                edName.setText(TextUtils.isEmpty(userInfo.getNickname()) ? "" : userInfo.getNickname());
            }
            String account = (String) Hawk.get("account", "");
            tvPhone.setText(account);
            String city_third_name = Hawk.get(Define.CURR_CITY_THIRD_NAME, "");
            edAddress.setText(second_area_name + city_third_name);

            workDate = DateUtil.getCurrentDate("yyyy-MM-dd");
            edTime.setText(TextUtils.isEmpty(workDate) ? "" : workDate);

            if (roleType == PageConfig.TYPE_ZHONGJIE) {
                setMainTitle("劳务工信息");
            } else {
                setMainTitle("特种工信息");
                salaryType = 2;//特种工没有市场定价
                tv_type_tips1.setVisibility(View.VISIBLE);
                ll_gongzhong1.setVisibility(View.GONE);
                ll_gongzhong.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                tvShichangSelect.setVisibility(View.GONE);
                tv_money_tips1.setVisibility(View.VISIBLE);
                tv_money_tips1.setText("（2选1）");
            }
            if (isHiddeTitle) {
                hidTitleView();
            } else {
                showTitleView();
            }
        }

        payPresenter = new CommonPayPresenter(getActivity());
        getGongzhongInfo(false);
        getPayFeeInfo();
        initAdapter();
        initPicAdapter();
        initEditText();
        initWorkTime();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (qz_id == 0) {
                    getHistoryData();
                } else {
                    if (type == HomeCommonPublishActivity.YINGPIN) {
                        qzPiPei();
                    } else {
                        qz_id = 0;
                        getHistoryData();
                    }
                }
            }
        }, 500);
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
                RegexChkUtil.saveTwoPoint(s);
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

    @Override
    protected void refreshPageData() {
        getGongzhongInfo(false);
        getPayFeeInfo();
    }

    /**
     * 初始化工作时间布局
     */
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

    /**
     * 获取支付信息
     */
    private void getPayFeeInfo() {
        payPresenter.loadPayInfoFeeData(roleType == PageConfig.TYPE_ZHONGJIE
                        ? CommonPayPresenter.FEE_TYPE_LAOWU_XINXI : CommonPayPresenter.FEE_TYPE_TEZHONG_XINXI,
                new CommonPayPresenter.DataCallBack<PayInfoFeeModel>() {
                    @Override
                    public void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    public void onSucess(int code, BaseResultInfo<PayInfoFeeModel> respnse, String source) {
                        if (respnse.getData() != null) {
                            currModel = respnse.getData();
                            tvMoneyInfo.setText(new SpanUtils().append("信息费: ")
                                    .append("¥" + currModel.getCharge_fee() + "元")
                                    .setForegroundColor(ResUtils.getCommRed())
                                    .create());
                        }
                    }
                });
    }

    @OnClick({R.id.tv_sex_girl, R.id.iv_video, R.id.tv_sex_boy, R.id.tv_change_phone, R.id.iv_delete_video, R.id.tv_shichang_select, R.id.tv_zidingyi_select, R.id.tv_mianyi_select, R.id.ed_type, R.id.ed_address, R.id.ed_time, R.id.tv_submit})
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
            case R.id.tv_submit:
                submit();
                break;
            case R.id.tv_sex_girl:
                choiseSex(2);
                break;
            case R.id.tv_sex_boy:
                choiseSex(1);
                break;
            case R.id.ed_type:
                onGongzhongPicker();
                break;
            case R.id.ed_address:
                SelectAreaActivity.go(this, secondAreaId, thirdAreaId, second_area_name);
                break;
            case R.id.ed_time:
                onYearMonthDayTimePicker(0);
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
     * 1男2女
     *
     * @param sex
     */
    private void choiseSex(int sex) {
        this.sex = sex;
        if (sex == 1) {//选中男
            tv_sex_boy.setCompoundDrawables(select, null, null, null);
            tv_sex_girl.setCompoundDrawables(unSelect, null, null, null);
        } else if (sex == 2) {
            tv_sex_girl.setCompoundDrawables(select, null, null, null);
            tv_sex_boy.setCompoundDrawables(unSelect, null, null, null);
        } else {
            this.sex = 1;
            tv_sex_girl.setCompoundDrawables(unSelect, null, null, null);
            tv_sex_boy.setCompoundDrawables(select, null, null, null);
        }
    }

    /**
     * 根据选择的日薪进行UI设置
     *
     * @param salaryType 1市场2自定义3面议
     */
    private void choiseDingJia(int salaryType) {
        this.salaryType = salaryType;
        if (roleType == PageConfig.TYPE_TEZHONGGONG) {
            if (salaryType == 1) {//如果是特种工，市场定价改为自定义价格
                this.salaryType = 2;
            }
            llShichang.setVisibility(View.GONE);
            tvShichangSelect.setVisibility(View.GONE);
        }
        if (this.salaryType == 1) {//选中市场定价
            tvShichangSelect.setCompoundDrawables(select, null, null, null);
            tvZidingyiSelect.setCompoundDrawables(unSelect, null, null, null);
            tvMianyiSelect.setCompoundDrawables(unSelect, null, null, null);
            llShichang.setVisibility(View.VISIBLE);
            llZidingyiXinzi.setVisibility(View.GONE);
            if (roleType == PageConfig.TYPE_TEZHONGGONG) {
                llShichang.setVisibility(View.GONE);
                tvShichangSelect.setVisibility(View.GONE);
            }
        } else if (this.salaryType == 2) {
            tvShichangSelect.setCompoundDrawables(unSelect, null, null, null);
            tvZidingyiSelect.setCompoundDrawables(select, null, null, null);
            tvMianyiSelect.setCompoundDrawables(unSelect, null, null, null);
            llShichang.setVisibility(View.GONE);
            llZidingyiXinzi.setVisibility(View.VISIBLE);
            if (!isFirst) {
                EditTextUtil.showSoftInputFromWindow(getActivity(), edMoneyStart);
            }
        } else if (this.salaryType == 3) {
            tvShichangSelect.setCompoundDrawables(unSelect, null, null, null);
            tvZidingyiSelect.setCompoundDrawables(unSelect, null, null, null);
            tvMianyiSelect.setCompoundDrawables(select, null, null, null);
            llShichang.setVisibility(View.GONE);
            llZidingyiXinzi.setVisibility(View.GONE);
        } else {
            this.salaryType = 3;
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
        if (!TextUtils.isEmpty(sbPrice.toString())) {
            tvShichangDingjia.setText(sbPrice.toString());
        }
    }

    /**
     * 选择年月日
     *
     * @param type 0 工作日期 1 年龄
     */
    private void onYearMonthDayTimePicker(final int type) {
        if (type == 0) {
            new PickerViewWrap().showDateFromTodayPicker(getActivity(), new DatePicker.OnYearMonthDayTimePickListener() {
                @Override
                public void onDateTimePicked(String s, String s1, String s2, String s3, String s4) {
                    edTime.setText(s + "-" + s1 + "-" + s2);
                    workDate = s + "-" + s1 + "-" + s2;
                }
            }, 0);
        }
    }

    /**
     * 去支付
     */
    private void submit() {
        if (TextUtils.isEmpty(edName.getText().toString())) {
            showTips("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(et_age.getText().toString().trim())) {
            showTips("请输入年龄");
            return;
        }

        if (ll_gongzhong.getVisibility() == View.VISIBLE) {
            if (TextUtils.isEmpty(ed_gongzhong.getText().toString())) {
                showTips("请输入工种信息");
                return;
            }
        } else {
            if (TextUtils.isEmpty(workTypeId)) {
                showTips("请选择工种");
                return;
            }
        }
        if (TextUtils.isEmpty(tvPhone.getText().toString())) {
            showTips("请输入手机号");
            return;
        }
        if (thirdAreaId == -1) {
            showTips("请选择工作地区");
            return;
        }
        if (TextUtils.isEmpty(workDate)) {
            showTips("请选择工作日期");
            return;
        }
        String salary_start = edMoneyStart.getText().toString().trim();
        String salary_end = edMoneyEnd.getText().toString().trim();
        if (salaryType == 2) {
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
        } else if (salaryType == -1) {
            showTips("请选择薪资要求");
            return;
        }
        if (roleType == PageConfig.TYPE_TEZHONGGONG) {
            if (salaryType == 1) {
                showTips("请选择薪资要求");
                return;
            }
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

    /**
     * 提交信息
     */
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

    private void finalCommit() {
        String longlati = KV.get(Define.CURR_LONGLAT_ADDRESS);
        String gongzhong = ed_gongzhong.getText().toString();
        String age = et_age.getText().toString().trim();
        if (ll_gongzhong.getVisibility() == View.GONE) {
            gongzhong = "";
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

        addCall(new NetUtil().setUrl(APIS.URL_LABOR_APPLY)
                .addParams("type", roleType == PageConfig.TYPE_ZHONGJIE ? "1" : "2")
                .addParams("name", edName.getText().toString())
                .addParams("sex", sex + "")
                .addParams("age", age)
                .addParams("recruiter_id", qz_id + "")
                .addParams("image_url", picStr)
                .addParams("video_url", videoStr)
                .addParams("voice_url", voiceStr)
                .addParams("work_type_ids", workTypeId)
                .addParams("contact_tel", tvPhone.getText().toString())
                .addParams("work_first_area_id", "")
                .addParams("work_second_area_id", "" + KV.get(Define.CURR_CITY_ID))
                .addParams("work_third_area_id", "" + thirdAreaId)
                .addParams("work_date", workDate)
                .addParams("work_hour", workTime + "")
                .addParams("salary_type", salaryType + "")
                .addParams("self_intro", etJieshao.getText().toString())
                .addParams("longitude", longlati.split(",")[0])
                .addParams("latitude", longlati.split(",")[1])
                .addParams("salary_fee", edMoneyStart.getText().toString())
                .addParams("end_salary_fee", edMoneyEnd.getText().toString())
                .addParams("other_work_type", gongzhong)
                .setCallBackData(new BaseNewNetModelimpl<PublicResultModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        loadingDialog.dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<PublicResultModel> respnse, String source) {
                        dismiss();
                        loadingDialog.dismiss();
                        //去支付
                        if (respnse.getData() != null) {
                            NewPayActivity.go(ZjTzgAddWorkInfoFragment.this,
                                    roleType == PageConfig.TYPE_ZHONGJIE ? CommonPayPresenter.TYPE_LAOGONG_XINXI :
                                            CommonPayPresenter.TYPE_TEZHONG_XINXI, respnse.getData().getRelate_id(), respnse.getData().getOrder_price() + "", respnse.getData().getOrder_name(), 0);
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
     * 获取工种信息
     */
    public void getGongzhongInfo(final boolean showPop) {
        addCall(new NetUtil()
                .setUrl(APIS.URL_TALENT_LIST_WORK_TYPE)
                .setCallBackData(new BaseNewNetModelimpl<List<GongzhongModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<GongzhongModel>> respnse, String source) {
                        if (respnse.getData() != null) {
                            gongzhongModelList = respnse.getData();
                            GongzhongModel model = new GongzhongModel();
                            model.setWork_type_id(-1);
                            model.setName("其他工种");
                            gongzhongModelList.add(model);
                            if (showPop) {
                                onGongzhongPicker();
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<GongzhongModel>>>() {
                        }.getType();
                    }
                }).buildPost());
    }


    public void initAdapter() {
        listSelect = new ArrayList<>();
        adapter = new RecyclerViewAdapter(listSelect);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(adapter);

        adapter.setEnableLoadMore(false);
    }


    class RecyclerViewAdapter extends BaseQuickAdapter<GongzhongModel, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<GongzhongModel> data) {
            super(R.layout.item_recycler_gongzhong, data, false);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final GongzhongModel item) {
            helper.setText(R.id.tv_name, item.getName());
            helper.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setSelect(false);
                    listSelect.remove(helper.getAdapterPosition());
                    saveSelectGongZhongData();
                    notifyDataSetChanged();

                    if (item.getWork_type_id() == -1) {
                        ll_gongzhong.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NewPayActivity.REQUEST_CODE && resultCode == NewPayActivity.RESULT_CODE_SUCCESS) {
            //处理你的操作
            getActivity().setResult(NewPayActivity.RESULT_CODE_SUCCESS);
            getActivity().finish();
        } else if (requestCode == SelectAreaActivity.GET_CITY_REQUEST_CODE) {
            //地址
            if (data != null) {
                thirdAreaId = data.getIntExtra("thirdId", 0);
                String thirdName = data.getStringExtra("thirdName");
                second_area_name = data.getStringExtra("secondName");
                secondAreaId = data.getIntExtra("secondId", -1);
                edAddress.setText(second_area_name + thirdName);
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
     * 选择工种
     */
    public void onGongzhongPicker() {
        if (gongzhongModelList != null) {
            for (GongzhongModel serviceModel : gongzhongModelList) {
                int service_content_id = serviceModel.getWork_type_id();
                for (GongzhongModel model : listSelect) {
                    int service_content_id1 = model.getWork_type_id();
                    if (service_content_id == service_content_id1) {
                        serviceModel.setSelect(true);
                    }
                }
            }
            popAdapter = new CommonPopupAdapter(getContext(), gongzhongModelList);
            listView.setAdapter(popAdapter);
            if (mCirclePop != null) {
                mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                mCirclePop.showAtAnchorView(activity == null ? view_line_bottom : activity.getRootView(), VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
            }
        } else {
            getGongzhongInfo(true);
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

                boolean has = false;
                for (GongzhongModel model : gongzhongModelList) {
                    if (model.isSelect()) {
                        if (model.getWork_type_id() != -1) {
                            res.append(model.getWork_type_id() + ",");
                        } else {
                            has = true;
                        }
                        listSelect.add(model);
                    }
                }
                if (has) {
                    showTips("请输入其他工种的名称");
                    ll_gongzhong.setVisibility(View.VISIBLE);
                } else {
                    ll_gongzhong.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(res)) {
                    workTypeId = res.subSequence(0, res.length() - 1).toString();
                } else {
                    workTypeId = "";
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
                .setDimView(activity == null ? ll_root : activity.getRootView())
                .createPopup();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gongzhongModelList.get(position).setSelect(!gongzhongModelList.get(position).isSelect());
                if (popAdapter != null) {
                    popAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    /**
     * 获取历史数据
     */
    private void getHistoryData() {
        addCall(new NetUtil().setUrl(APIS.URL_LABOR_LAST_APPLY_DATA)
                .addParams("type", roleType == PageConfig.TYPE_ZHONGJIE ? "1" : "2")
                .setCallBackData(new BaseNewNetModelimpl<LastPublicForLgAndTzg>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LastPublicForLgAndTzg> respnse, String source) {
                        showContentView();
                        if (respnse.getData() != null) {
                            lastPublicForLgAndTzg = respnse.getData();
                            if (lastPublicForLgAndTzg != null) {
                                if (lastPublicForLgAndTzg.getIs_posted() == 1) {
                                    LastPublicForLgAndTzg.RecordBean record = lastPublicForLgAndTzg.getRecord();
                                    if (record != null) {
                                        //设置内容
                                        edName.setText(record.getName());
                                        int start = record.getSalary_fee();
                                        if (start == 0) {
                                            edMoneyStart.setText("");
                                        } else {
                                            edMoneyStart.setText(start + "");
                                        }

                                        int end = record.getEnd_salary_fee();
                                        if (end == 0) {
                                            edMoneyEnd.setText("");
                                        } else {
                                            edMoneyEnd.setText(end + "");
                                        }

                                        et_age.setText(record.getAge() + "");
                                        etJieshao.setText(record.getSelf_intro());
                                        sex = record.getSex();
                                        choiseSex(sex);
                                        salaryType = record.getSalary_type();
                                        choiseDingJia(salaryType);
                                        //todo 工种没返回，先不管
                                    }
                                }

                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LastPublicForLgAndTzg>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    private void qzPiPei() {
        if (qz_id == 0) {
            return;
        }
        addCall(new NetUtil().setUrl(APIS.URL_LW_TZ_WORK_INFO_LIST_DETAIL)
                .addParams("recruitment_id", qz_id + "")
                .setCallBackData(new BaseNewNetModelimpl<LwTzWorkListDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LwTzWorkListDetailModel> respnse, String source) {
                        showContentView();
                        if (respnse.getData() != null) {
                            LwTzWorkListDetailModel record = respnse.getData();
                            if (record != null) {
                                //设置内容

                                    int start = record.getSalary_fee();
                                    if (start == 0) {
                                        edMoneyStart.setText("");
                                    } else {
                                        edMoneyStart.setText(start+"");
                                    }


                                int end = record.getEnd_salary_fee();
                                    if (end == 0) {
                                        edMoneyEnd.setText("");
                                    } else {
                                        edMoneyEnd.setText(end+"");
                                    }

                                secondAreaId = record.getWork_second_area_id();
                                thirdAreaId = record.getWork_third_area_id();
                                if (StringUtil.isAllNotEmpty(record.getWork_second_area_name(), record.getWork_third_area_name())) {
                                    edAddress.setText(record.getWork_second_area_name() + record.getWork_third_area_name());
                                }
                                sex = record.getSex();
                                choiseSex(sex);
                                salaryType = record.getSalary_type();
                                choiseDingJia(salaryType);
                                int work_hour = record.getWork_hour();
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
                                }
                                StringBuffer sb = new StringBuffer();
                                List<LwTzWorkListDetailModel.WorkTypeNameListBean> work_type_name_list = record.getWork_type_name_list();
                                for (int i = 0; i < work_type_name_list.size(); i++) {
                                    LwTzWorkListDetailModel.WorkTypeNameListBean workTypeNameListBean = work_type_name_list.get(i);
                                    if (TextUtils.isEmpty(workTypeNameListBean.getOther_work_type())) {
                                        GongzhongModel gongzhongModel = new GongzhongModel();
                                        gongzhongModel.setInfo_fee(workTypeNameListBean.getInfo_fee());
                                        gongzhongModel.setName(workTypeNameListBean.getName());
                                        gongzhongModel.setWork_type_id(workTypeNameListBean.getWork_type_id());
                                        gongzhongModel.setSelect(true);
                                        sb.append(workTypeNameListBean.getWork_type_id() + ",");
                                        listSelect.add(gongzhongModel);
                                    } else {
                                        GongzhongModel gongzhongModel = new GongzhongModel();
                                        gongzhongModel.setInfo_fee(workTypeNameListBean.getInfo_fee());
                                        gongzhongModel.setName(workTypeNameListBean.getOther_work_type());
                                        gongzhongModel.setWork_type_id(-1);
                                        ll_gongzhong.setVisibility(View.VISIBLE);
                                        ed_gongzhong.setText(workTypeNameListBean.getOther_work_type());
                                        gongzhongModel.setSelect(true);
                                        listSelect.add(gongzhongModel);
                                    }
                                }
                                String result = sb.toString();
                                if (!TextUtils.isEmpty(result)) {
                                    workTypeId = result.substring(0, result.length() - 1);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }


                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LwTzWorkListDetailModel>>() {
                        }.getType();
                    }
                }).buildPost());
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
}
