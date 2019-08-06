package com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.activity.SelectCityActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PositionThreeCLassActivity;
import com.windmillsteward.jukutech.activity.newpage.common.popwindow.CommonPopwindow;
import com.windmillsteward.jukutech.activity.newpage.model.EducationModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForYinyuanModel;
import com.windmillsteward.jukutech.activity.newpage.model.SalaryTypeModel;
import com.windmillsteward.jukutech.activity.newpage.model.WorkTimeModel;
import com.windmillsteward.jukutech.activity.newpage.model.request.YinyuanTransferModel;
import com.windmillsteward.jukutech.activity.newpage.newpublish.HomeCommonPublishActivity;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.common.SelectAreaActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.utils.RecorderUtils;
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
import com.windmillsteward.jukutech.customview.dialog.PhoneCodeDialog;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.GlideUtil;
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
 * 人才驿站-姻缘-添加个人资料
 */
public class YinyuanAddUserInfoFragment extends BaseInitFragment {
    public static final String TAG = "YinyuanAddUserInfoFragment";
    public static final int YINYUAN_ADD = 123;
    @Bind(R.id.tv_name_tips)
    TextView tvNameTips;
    @Bind(R.id.ed_name)
    EditText edName;
    @Bind(R.id.tv_phone_tips)
    TextView tvPhoneTips;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_change_phone)
    TextView tvChangePhone;
    @Bind(R.id.tv_sex_tips)
    TextView tvSexTips;
    @Bind(R.id.tv_height_tips)
    TextView tvHeightTips;
    @Bind(R.id.ed_height)
    EditText edHeight;
    @Bind(R.id.tv_weight_tips)
    TextView tvWeightTips;
    @Bind(R.id.ed_weight)
    EditText edWeight;
    @Bind(R.id.tv_birthday_tips)
    TextView tvBirthdayTips;
    @Bind(R.id.tv_birthday)
    TextView tvBirthday;
    @Bind(R.id.tv_xueli_tips)
    TextView tvXueliTips;
    @Bind(R.id.tv_xueli)
    TextView tvXueli;
    @Bind(R.id.tv_fabu_tips)
    TextView tvFabuTips;
    @Bind(R.id.tv_fabu)
    TextView tvFabu;
    @Bind(R.id.tv_zhiye_tips)
    TextView tvZhiyeTips;
    @Bind(R.id.tv_zhiye)
    TextView tvZhiye;
    @Bind(R.id.tv_shouru_tips)
    TextView tvShouruTips;
    @Bind(R.id.tv_shouru)
    TextView tvShouru;
    @Bind(R.id.tv_huji_tips)
    TextView tvHujiTips;
    @Bind(R.id.tv_huji)
    TextView tvHuji;
    @Bind(R.id.tv_juzhu_tips)
    TextView tvJuzhuTips;
    @Bind(R.id.tv_juzhu)
    TextView tvJuzhu;
    @Bind(R.id.tv_hunyin_tips)
    TextView tvHunyinTips;
    @Bind(R.id.tv_hunyin)
    TextView tvHunyin;
    @Bind(R.id.tv_zinv_tips)
    TextView tvZinvTips;
    @Bind(R.id.tv_zinv)
    TextView tvZinv;
    @Bind(R.id.et_jieshao)
    EditText etJieshao;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.iv_video)
    CircleImageView ivVideo;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    public static final int REQUEST_CODE_MAKE_VIDEO = 104;
    @Bind(R.id.tv_sex_boy)
    TextView tvSexBoy;
    @Bind(R.id.tv_sex_girl)
    TextView tvSexGirl;
    @Bind(R.id.tv_shenghuozhao_tips)
    TextView tvShenghuozhaoTips;
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
    @Bind(R.id.iv_voice_delete)
    ImageView ivVoiceDelete;
    @Bind(R.id.ll_root)
    LinearLayout llRoot;
    @Bind(R.id.iv_delete_video)
    ImageView ivDeleteVideo;
    @Bind(R.id.lay_ll_luzhi)
    LinearLayout layLlLuzhi;

    private int type;
    private int roleType;

    private int pageType;
    private LastPublicForYinyuanModel lastPublicForYinyuanModel;
    private boolean isHiddeTitle;
    private String second_area_name = KV.get(Define.CURR_CITY_NAME, "");

    private HomeCommonPublishActivity activity;
    private List<WorkTimeModel> hunYinlist;
    private List<WorkTimeModel> ziNvlist;
    private int isVideoOrPic = 2;//1视频2图片
    private List<String> listVedio;
    private String voicePath;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_yinyuan_add_user_info;
    }


    public static YinyuanAddUserInfoFragment newInstance(int type, int roleType, boolean isHiddeTitle) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putInt("roleType", roleType);
        args.putBoolean("isHiddeTitle", isHiddeTitle);
        YinyuanAddUserInfoFragment fragment = new YinyuanAddUserInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setMainTitle("个人资料");
        activity = (HomeCommonPublishActivity) getActivity();
        initVoice();
        new SpannableStringViewWrap().addViews(tvNameTips, tvPhoneTips, tvSexTips, tvHeightTips, tvWeightTips, tvBirthdayTips, tvXueliTips
                , tvZhiyeTips, tvShouruTips, tvHujiTips, tvJuzhuTips, tvHunyinTips, tvZinvTips, tvFabuTips, tvShenghuozhaoTips).build();
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
            type = getArguments().getInt("type");
            roleType = getArguments().getInt("roleType");
            isHiddeTitle = getArguments().getBoolean("isHiddeTitle", false);
            if (isHiddeTitle) {
                hidTitleView();
            }
            LoginSuccessInfo userInfo = (LoginSuccessInfo) KV.get(Define.LOGIN_SUCCESS);
            if (userInfo != null) {
                edName.setText(TextUtils.isEmpty(userInfo.getNickname()) ? "" : userInfo.getNickname());
            }
            String account = (String) Hawk.get("account", "");
            tvPhone.setText(account);
        }
        String city_third_name = Hawk.get(Define.CURR_CITY_THIRD_NAME, "");
        tvFabu.setText(second_area_name + city_third_name);
        listVedio = new ArrayList<>();
        getLastInfo();
        getHunYinZiNvList();
        initZichanAdapter();
    }

    @Override
    protected void refreshPageData() {

    }

    private void getLastInfo() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_LOOKING_FOR_OBJECTS)
                .setCallBackData(new BaseNewNetModelimpl<LastPublicForYinyuanModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LastPublicForYinyuanModel> respnse, String source) {
                        if (respnse != null) {
                            lastPublicForYinyuanModel = respnse.getData();
                            if (lastPublicForYinyuanModel != null) {
                                showContentView();
                                if (lastPublicForYinyuanModel.getIs_posted() == 1) {
                                    LastPublicForYinyuanModel.RecordBean record = lastPublicForYinyuanModel.getRecord();
                                    if (record != null) {
                                        //设置数据
                                        edName.setText(record.getUser_name());
                                        String account = (String) Hawk.get("account", "");
                                        tvPhone.setText(TextUtils.isEmpty(record.getMobile_phone()) ? account : record.getMobile_phone());
                                        sex = record.getSex();
                                        choiseSex(sex);
                                        edHeight.setText(record.getHeight() + "");
                                        edWeight.setText(record.getWeight() + "");
                                        tvBirthday.setText(record.getBirthday());
                                        birthday = record.getBirthday();
                                        etJieshao.setText(record.getSelf_intro());
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LastPublicForYinyuanModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    @OnClick({R.id.tv_sex_girl, R.id.tv_sex_boy, R.id.iv_delete_video, R.id.tv_change_phone, R.id.tv_birthday, R.id.tv_fabu, R.id.tv_xueli, R.id.tv_zhiye, R.id.tv_shouru, R.id.tv_huji, R.id.tv_juzhu, R.id.tv_hunyin, R.id.tv_zinv, R.id.iv_video, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_delete_video:
                videoPath = "";
                listVedio.clear();
                BitmapDrawable drawable = (BitmapDrawable) ivVideo.getDrawable();
                Bitmap bmp = drawable.getBitmap();
                if (null != bmp && !bmp.isRecycled()) {
                    bmp.recycle();
                    bmp = null;
                }
                ivVideo.setBackgroundResource(R.mipmap.icon_video);
                ivDeleteVideo.setVisibility(View.GONE);
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
            case R.id.tv_sex_girl:
                choiseSex(2);
                break;
            case R.id.tv_sex_boy:
                choiseSex(1);
                break;
            case R.id.tv_birthday:
                onYearMonthDayTimePicker();
                break;
            case R.id.tv_xueli:
                onXueliPicker();
                break;
            case R.id.tv_fabu:
                SelectAreaActivity.go(this, release_second_area_id, release_third_area_id, second_area_name);
                break;
            case R.id.tv_zhiye:
                startAtvDonFinishForResult(PositionThreeCLassActivity.class, PositionThreeCLassActivity.REQUEST_CODE);
                break;
            case R.id.tv_shouru:
                onSalaryPicker();
                break;
            case R.id.tv_huji:
                if (!chekcLocationPermission()) {
                    showTips("请确认是否开启了定位权限", 1);
                    return;
                }
                type = 1;
                Intent intent1 = new Intent();
                intent1.putExtra("type", SelectCityActivity.TYPE_SELECT);
                startAtvDonFinishForResult(SelectCityActivity.class, 100, intent1);
                break;
            case R.id.tv_juzhu:
                if (!chekcLocationPermission()) {
                    showTips("请确认是否开启了定位权限", 1);
                    return;
                }
                type = 2;
                Intent intent2 = new Intent();
                intent2.putExtra("type", SelectCityActivity.TYPE_SELECT);
                startAtvDonFinishForResult(SelectCityActivity.class, 100, intent2);
                break;
            case R.id.tv_hunyin:
                onSelectMarryStatus();
                break;
            case R.id.tv_zinv:
                onSelectChildStatus();
                break;
            case R.id.iv_video:
                isVideoOrPic = 1;
                selectCamera();
                break;
            case R.id.tv_submit:
                //填写择偶标准
                submit();
                break;
        }
    }

    /**
     * 打开相册选择视频
     */
    public void selectCamera() {
        List<LocalMedia> selectVedioList = new ArrayList<>();
        for (String s : listVedio) {
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
     * 根据选择的性别设置UI
     *
     * @param sex
     */
    private void choiseSex(int sex) {
        this.sex = sex;
        Drawable select = getResources().getDrawable(R.mipmap.select);
        select.setBounds(0, 0, select.getMinimumWidth(), select.getMinimumHeight());
        Drawable unSelect = getResources().getDrawable(R.mipmap.unselect);
        unSelect.setBounds(0, 0, unSelect.getMinimumWidth(), unSelect.getMinimumHeight());
        if (sex == 1) {//选中男
            tvSexBoy.setCompoundDrawables(select, null, null, null);
            tvSexGirl.setCompoundDrawables(unSelect, null, null, null);
        } else if (sex == 2) {
            tvSexBoy.setCompoundDrawables(unSelect, null, null, null);
            tvSexGirl.setCompoundDrawables(select, null, null, null);
        } else {
            this.sex = 1;
            tvSexBoy.setCompoundDrawables(unSelect, null, null, null);
            tvSexGirl.setCompoundDrawables(unSelect, null, null, null);
        }
    }

    //预提交
    private void submit() {
        if (TextUtils.isEmpty(edName.getText().toString().trim())) {
            showTips("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(tvPhone.getText().toString().trim())) {
            showTips("请输入手机号");
            return;
        }
        if (sex == -1) {
            showTips("请选择性别");
            return;
        }
        if (TextUtils.isEmpty(edHeight.getText().toString().trim())) {
            showTips("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(edWeight.getText().toString().trim())) {
            showTips("请输入体重");
            return;
        }
        if (TextUtils.isEmpty(birthday)) {
            showTips("请选择出生年月");
            return;
        }
        if (education_background_id == -1) {
            showTips("请选择学历");
            return;
        }
        if (StringUtil.isAllEmpty(job_class_id_one, job_class_id_two)) {
            showTips("请选择职业");
            return;
        }
        if (salary_id == -1) {
            showTips("请选择月收入");
            return;
        }
        if (first_area_id == -1) {
            showTips("请选择户籍地");
            return;
        }
        if (live_second_area_id == -1) {
            showTips("请选择居住地");
            return;
        }
        if (release_third_area_id == -1) {
            showTips("请选择发布地区");
            return;
        }
        if (marital_status == -1) {
            showTips("请选择婚姻状况");
            return;
        }
        if (child_status == -1) {
            showTips("请选择子女情况");
            return;
        }

        List<String> pic_urls = new ArrayList<>();
        for (String listPic : listPics) {
            if (!TextUtils.isEmpty(listPic)) {
                pic_urls.add(listPic);
            }
        }
        if (pic_urls.isEmpty()) {
            showTips("请至少上传一张个人生活照");
            return;
        }
        List<String> video_urls = new ArrayList<>();
        if (!TextUtils.isEmpty(videoPath)) {
            video_urls.add(videoPath);
        }
        List<String> voice_urls = new ArrayList<>();
        if (!TextUtils.isEmpty(voicePath)) {
            voice_urls.add(voicePath);
        }
        YinyuanTransferModel model = new YinyuanTransferModel();
        model.setUser_name(edName.getText().toString().trim());
        model.setMobile_phone(tvPhone.getText().toString().trim());
        model.setSex(sex);
        model.setType(pageType);
        model.setHeight(edHeight.getText().toString().trim());
        model.setWeight(edWeight.getText().toString().trim());
        model.setBirthday(birthday);
        model.setEducation_background_id(education_background_id);
        model.setJob_class_id_one(job_class_id_one);
        model.setJob_class_id_two(job_class_id_two);
        model.setSalary_id(salary_id);
        model.setFirst_area_id(first_area_id);
        model.setLive_second_area_id(live_second_area_id);
        model.setMarital_status(marital_status);
        model.setChild_status(child_status);
        model.setRelease_third_area_id(release_third_area_id);
        model.setSelf_intro(etJieshao.getText().toString().trim());
        model.setPic_urls(pic_urls);
        model.setVideo_url(video_urls);
        model.setVoice_url(voice_urls);
        //跳转页面
        Intent intent = new Intent(getActivity(), YiinyuanZeOuActivity.class);
        intent.putExtra("model", model);
        startActivityForResult(intent, YINYUAN_ADD);
    }

    private int sex = 1, education_background_id = -1, salary_id = -1, first_area_id = -1,
            live_second_area_id = -1, marital_status = -1, child_status = -1,
            release_third_area_id = KV.get(Define.CURR_CITY_THIRD_ID, -1),
            release_second_area_id = KV.get(Define.CURR_CITY_ID, -1);
    private String birthday;
    private String videoPath;
    private String job_class_id_one;
    private String job_class_id_two;
    private List<EducationModel> educationList;

    /**
     * 获取学历信息
     *
     * @param showPop
     */
    private void getXueliList(final boolean showPop) {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_EDUCATION_LIST)
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

    private List<SalaryTypeModel> salaryList;

    /**
     * 获取月收入信息
     *
     * @param showPop
     */
    private void getSalaryList(final boolean showPop) {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_GET_MONTHLY_INCOME_LIST)
                .setCallBackData(new BaseNewNetModelimpl<List<SalaryTypeModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<SalaryTypeModel>> respnse, String source) {
                        if (respnse.getData() != null) {
                            salaryList = respnse.getData();
                            if (salaryList != null && salaryList.size() > 0) {
                                salaryList.get(0).setSelect(true);
                            }
                            if (showPop) {
                                onSalaryPicker();
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<SalaryTypeModel>>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 选择月收入
     */
    private void onSalaryPicker() {
        if (salaryList != null) {
            if (activity != null) {
                CommonPopwindow commonPopwindow = new CommonPopwindow(getActivity(), activity.getRootView(), salaryList);
                final EasyPopup circlePop = commonPopwindow.getCirclePop();
                LimitHeightListView listView = commonPopwindow.getListView();
                commonPopwindow.bindAdapter();
                commonPopwindow.setTitle("选择月收入");
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SalaryTypeModel educationModel = (SalaryTypeModel) parent.getItemAtPosition(position);
                        if (circlePop != null) {
                            circlePop.dismiss();
                        }
                        for (SalaryTypeModel bean : salaryList) {
                            bean.setSelect(false);
                        }
                        if (educationModel != null) {
                            educationModel.setSelect(true);
                            tvShouru.setText(educationModel.getSalary_name());
                            salary_id = educationModel.getSalary_id();
                        }
                    }
                });
                if (circlePop != null) {
                    circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    circlePop.showAtAnchorView(activity.getRootView(), VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
                }
            }
        } else {
            getSalaryList(true);
        }
    }

    /**
     * 选择年月日
     */
    private void onYearMonthDayTimePicker() {
        new PickerViewWrap().showSelectBirthdayPicker(getActivity(), new DatePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String s, String s1, String s2, String s3, String s4) {
                tvBirthday.setText(s + "-" + s1 + "-" + s2);
                birthday = s + "-" + s1 + "-" + s2;
            }
        });
    }

    private void getHunYinZiNvList() {
        hunYinlist = new ArrayList<>();
        WorkTimeModel workTimeModel1 = new WorkTimeModel();
        WorkTimeModel workTimeModel2 = new WorkTimeModel();

        workTimeModel1.setName("未婚");
        workTimeModel1.setSelect(true);

        workTimeModel2.setName("离异");
        workTimeModel2.setSelect(false);

        hunYinlist.add(workTimeModel1);
        hunYinlist.add(workTimeModel2);

        ziNvlist = new ArrayList<>();
        WorkTimeModel workTimeModel3 = new WorkTimeModel();
        WorkTimeModel workTimeModel4 = new WorkTimeModel();

        workTimeModel3.setName("有子女");
        workTimeModel3.setSelect(true);

        workTimeModel4.setName("无子女");
        workTimeModel4.setSelect(false);

        ziNvlist.add(workTimeModel3);
        ziNvlist.add(workTimeModel4);
    }

    /**
     * 选择婚姻状态
     */
    private void onSelectMarryStatus() {

        if (activity != null) {
            CommonPopwindow commonPopwindow = new CommonPopwindow(getActivity(), activity.getRootView(), hunYinlist);
            final EasyPopup circlePop = commonPopwindow.getCirclePop();
            LimitHeightListView listView = commonPopwindow.getListView();
            commonPopwindow.bindAdapter();
            commonPopwindow.setTitle("选择婚姻状态");
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    WorkTimeModel workTimeModel = (WorkTimeModel) parent.getItemAtPosition(position);
                    if (circlePop != null) {
                        circlePop.dismiss();
                    }
                    for (WorkTimeModel model : hunYinlist) {
                        model.setSelect(false);
                    }

                    if (workTimeModel != null) {
                        workTimeModel.setSelect(true);
                        tvHunyin.setText(workTimeModel.getName());
                        marital_status = position + 1;
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
     * 选择子女状态
     */
    private void onSelectChildStatus() {
        if (activity != null) {
            CommonPopwindow commonPopwindow = new CommonPopwindow(getActivity(), activity.getRootView(), ziNvlist);
            final EasyPopup circlePop = commonPopwindow.getCirclePop();
            LimitHeightListView listView = commonPopwindow.getListView();
            commonPopwindow.bindAdapter();
            commonPopwindow.setTitle("选择子女状态");
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    WorkTimeModel workTimeModel = (WorkTimeModel) parent.getItemAtPosition(position);
                    if (circlePop != null) {
                        circlePop.dismiss();
                    }
                    for (WorkTimeModel model : ziNvlist) {
                        model.setSelect(false);
                    }

                    if (workTimeModel != null) {
                        workTimeModel.setSelect(true);
                        tvZinv.setText(workTimeModel.getName());
                        child_status = position + 1;
                    }

                }
            });
            if (circlePop != null) {
                circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                circlePop.showAtAnchorView(activity.getRootView(), VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
            }
        }
    }

    private RecyclerViewAdapter adapter;
    private List<String> listPics;

    public void initZichanAdapter() {
        listPics = new ArrayList<>();
        listPics.add(null);
        adapter = new RecyclerViewAdapter(listPics);
        adapter.setEnableLoadMore(false);
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recyclerview.setAdapter(adapter);
        recyclerview.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        recyclerview.setHasFixedSize(true);

        //事件监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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


    class RecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<String> data) {
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
                    if (listPics.size() < 6 && !listPics.contains("")) {
                        listPics.add("");
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        }
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
                .maxSelectNum(6)
                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == YINYUAN_ADD && resultCode == NewPayActivity.RESULT_CODE_SUCCESS) {
            //处理你的操作
            getActivity().finish();
        }
        if (resultCode == getActivity().RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST) {
            if (isVideoOrPic == 1) {
                if (data != null && data.getExtras() != null) {
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    listVedio.clear();
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
                    if (listPics.size() < 6) {
                        listPics.add("");
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }

        if (requestCode == 100 && resultCode == getActivity().RESULT_OK) {
            String city_name = data.getStringExtra(Define.INTENT_DATA);
            int area_id = data.getIntExtra("area_id", -1);
            if (type == 1) {
                //户籍
                tvHuji.setText(TextUtils.isEmpty(city_name) ? "" : city_name);
                first_area_id = area_id;
            } else if (type == 2) {
                //居住地
                tvJuzhu.setText(TextUtils.isEmpty(city_name) ? "" : city_name);
                live_second_area_id = area_id;
            }
        }
        if (requestCode == PositionThreeCLassActivity.REQUEST_CODE && resultCode == PositionThreeCLassActivity.RESULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras != null) {

                job_class_id_one = extras.getString(PositionThreeCLassActivity.JOB_CLASS_ONE_ID);
                job_class_id_two = extras.getString(PositionThreeCLassActivity.JOB_CLASS_TWO_ID);
                String job_class_name_two = extras.getString(PositionThreeCLassActivity.JOB_CLASS_TWO_NAME);
                String job_class_name_one = extras.getString(PositionThreeCLassActivity.JOB_CLASS_ONE_NAME);
                tvZhiye.setText(job_class_name_one + "/" + job_class_name_two);
            }
        } else if (requestCode == SelectAreaActivity.GET_CITY_REQUEST_CODE) {
            //地址
            if (data != null) {
                release_third_area_id = data.getIntExtra("thirdId", 0);
                String thirdName = data.getStringExtra("thirdName");
                second_area_name = data.getStringExtra("secondName");
                release_second_area_id = data.getIntExtra("secondId", -1);
                tvFabu.setText(second_area_name + thirdName);
            }
        }
    }


}
