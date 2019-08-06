package com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.activity.SelectCityActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PositionThreeCLassActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PositionTwoCLassActivity;
import com.windmillsteward.jukutech.activity.newpage.common.model.PayInfoFeeModel;
import com.windmillsteward.jukutech.activity.newpage.common.popwindow.CommonPopwindow;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.EducationModel;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.model.SalaryTypeModel;
import com.windmillsteward.jukutech.activity.newpage.model.WorkTimeModel;
import com.windmillsteward.jukutech.activity.newpage.model.request.YinyuanTransferModel;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.interfaces.OnUserAuthenListener;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.dialog.LoadingDialog;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.ResUtils;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.view.SpannableStringViewWrap;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 择偶标准
 */
public class YiinyuanZeOuActivity extends BaseInitActivity {
    //添加
    public static final int TYPE_ADD = 0;
    //编辑
    public static final int TYPE_EDIT = 1;
    @Bind(R.id.tv_heigjt_tips)
    TextView tvHeigjtTips;
    @Bind(R.id.ed_heigjt1)
    EditText edHeigjt1;
    @Bind(R.id.ed_heigjt2)
    EditText edHeigjt2;
    @Bind(R.id.tv_weight_tips)
    TextView tvWeightTips;
    @Bind(R.id.ed_weight1)
    EditText edWeight1;
    @Bind(R.id.ed_weight2)
    EditText edWeight2;
    @Bind(R.id.tv_age_tips)
    TextView tvAgeTips;
    @Bind(R.id.ed_age1)
    EditText edAge1;
    @Bind(R.id.ed_age2)
    EditText edAge2;
    @Bind(R.id.tv_xueli_tips)
    TextView tvXueliTips;
    @Bind(R.id.tv_xueli)
    TextView tvXueli;
    @Bind(R.id.tv_zhiwei_tips)
    TextView tvZhiweiTips;
    @Bind(R.id.tv_zhiwei)
    TextView tvZhiwei;
    @Bind(R.id.tv_shouru_tips)
    TextView tvShouruTips;
    @Bind(R.id.tv_shouru)
    TextView tvShouru;
    @Bind(R.id.tv_huji_tips)
    TextView tvHujiTips;
    @Bind(R.id.tv_huji)
    TextView tvHuji;
    @Bind(R.id.tv_hunyin_tips)
    TextView tvHunyinTips;
    @Bind(R.id.tv_hunyin)
    TextView tvHunyin;
    @Bind(R.id.tv_zinv_tips)
    TextView tvZinvTips;
    @Bind(R.id.tv_zinv)
    TextView tvZinv;
    @Bind(R.id.tv_zichan_tips)
    TextView tvZichanTips;
    @Bind(R.id.tv_car)
    TextView tvCar;
    @Bind(R.id.tv_house)
    TextView tvHouse;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    @Bind(R.id.tv_info)
    TextView tv_info;
    @Bind(R.id.tv_juzhudi_tips)
    TextView tv_juzhudi_tips;
    @Bind(R.id.tv_juzhudi)
    TextView tv_juzhudi;
    @Bind(R.id.lay_ll_root)
    LinearLayout lay_ll_root;

    private YinyuanTransferModel model;
    private boolean carFlag, houseFlag;
    private CommonPayPresenter payPresenter;

    private List<WorkTimeModel> hunYinlist;
    private List<WorkTimeModel> ziNvlist;

    private List<String> newVideoUrls= new ArrayList<>();
    private List<String> newPicUrls= new ArrayList<>();
    private List<String> newVoiceUrls= new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_yiinyuan_ze_ou;
    }

    @Override
    protected void initView(View view) {
        setMainTitle("择偶标准");
        showContentView();

        if (getIntent() != null) {
            model = getIntent().getParcelableExtra("model");
            if (model == null)
                return;
        }

        new SpannableStringViewWrap().addViews(tvHeigjtTips, tvWeightTips, tvAgeTips, tvXueliTips, tvZhiweiTips, tvShouruTips, tvHujiTips
                , tvHunyinTips, tvZinvTips, tvZichanTips, tv_juzhudi_tips).build();

        TextView leftBackView = getLeftBackView();
        leftBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BaseDialog baseDialog = new BaseDialog(YiinyuanZeOuActivity.this);
                baseDialog.showTwoButton("温馨提示", "确定放弃编辑并返回上一页吗？，放弃将不会保存您编辑的内容", "确定", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        baseDialog.dismiss();
                        finish();
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

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @Override
    protected void initData() {
        getHunYinZiNvList();
        payPresenter = new CommonPayPresenter(this);
        getPayFeeInfo();
    }

    @Override
    protected void refreshPageData() {
        getPayFeeInfo();
    }

    private int type;

    @OnClick({R.id.tv_xueli, R.id.tv_juzhudi, R.id.tv_zhiwei, R.id.tv_shouru, R.id.tv_huji, R.id.tv_hunyin, R.id.tv_zinv, R.id.tv_car, R.id.tv_house, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_xueli:
                onXueliPicker();
                break;
            case R.id.tv_zhiwei:
                startAtvDonFinishForResult(PositionTwoCLassActivity.class, PositionTwoCLassActivity.REQUEST_CODE);
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
            case R.id.tv_juzhudi:
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
            case R.id.tv_car:
                carFlag = !carFlag;
                if (carFlag) {
                    tvCar.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.select), null, null, null);
                } else {
                    tvCar.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.unselect), null, null, null);
                }
                break;
            case R.id.tv_house:
                houseFlag = !houseFlag;
                if (houseFlag) {
                    tvHouse.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.select), null, null, null);
                } else {
                    tvHouse.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.unselect), null, null, null);
                }
                break;
            case R.id.tv_submit:
                submit();
                break;
        }
    }

    private int zo_education_background_id = 0, zo_salary_id = 0, zo_first_area_id = 0, zo_live_second_area_id = 0, zo_marital_status = 0, zo_child_status = 0;
    private String assets_require = "";
    private String zo_job_class_id_one ="0";
    private String zo_job_class_id_two = "0";

    //预提交
    private void submit() {
        if (StringUtil.isSomeOneEmpty(edHeigjt1.getText().toString().trim(), edHeigjt2.getText().toString().trim())) {
            showTips("请完善身高信息");
            return;
        }
        if (Double.parseDouble(edHeigjt1.getText().toString().trim()) >= Double.parseDouble(edHeigjt2.getText().toString().trim())) {
            showTips("身高前者不能大于后者");
            return;
        }
        if (StringUtil.isSomeOneEmpty(edWeight1.getText().toString().trim(), edWeight2.getText().toString().trim())) {
            showTips("请完善体重信息");
            return;
        }
        if (Double.parseDouble(edWeight1.getText().toString().trim()) >= Double.parseDouble(edWeight2.getText().toString().trim())) {
            showTips("体重前者不能大于后者");
            return;
        }
        if (StringUtil.isSomeOneEmpty(edAge1.getText().toString().trim(), edAge2.getText().toString().trim())) {
            showTips("请完善年龄信息");
            return;
        }
        if (Double.parseDouble(edAge1.getText().toString().trim()) >= Double.parseDouble(edAge2.getText().toString().trim())) {
            showTips("年龄前者不能大于后者");
            return;
        }
//        if (zo_education_background_id == -1) {
//            showTips("请选择学历");
//            return;
//        }
//
//        if (zo_job_class_id_one == -1 || zo_job_class_id_two == -1) {
//            showTips("请选择职位");
//            return;
//        }
//
//        if (zo_salary_id == -1) {
//            showTips("请选择月收入");
//            return;
//        }
//
//        if (zo_first_area_id == -1) {
//            showTips("请选择户籍地");
//            return;
//        }
//
//        if (zo_marital_status == -1) {
//            showTips("请选择婚姻状况");
//            return;
//        }
//
//        if (zo_child_status == -1) {
//            showTips("请选择子女状况");
//            return;
//        }

        if (carFlag && houseFlag) {
            assets_require = "1,2";
        } else if (carFlag) {
            assets_require = "1";
        } else if (houseFlag) {
            assets_require = "2";
        }

//        if (TextUtils.isEmpty(assets_require)) {
//            showTips("请选择资产要求");
//            return;
//        }

        //可以提交
        checkUserAuthen(new OnUserAuthenListener() {
            @Override
            public void isAuthen() {
                commit();
            }

            @Override
            public void isNotAuthen() {

            }
        });
    }

    private LoadingDialog loadingDialog;

    //提交
    private void commit() {
        if (model == null)
            return;
        //检查图片或者视频
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);//初始化对化框
        }
        loadingDialog.showLoading("");


        for (String s : model.getPic_urls()) {
            if (TextUtils.isEmpty(s)) {
                model.getPic_urls().remove(s);
            }
        }
        //如果有图片，先上传图片，再检查是否有视频，如果有，先判断是否上传过，没有则可以再上传视频，如果没有则直接提交
        if (!model.getPic_urls().isEmpty()) {
            upLoadPic(model.getPic_urls());
        } else if (!model.getVideo_url().isEmpty()) {
            upLoadVideo();
        } else if (!model.getVoice_url().isEmpty()) {
            upLoadVoice();
        } else {
            lastCommit();
        }

    }

    private void upLoadPic(List<String> picList) {
        //先上传图片，再上传视频
        addCall(new NetUtil().setPic_path(picList)
                .buildUploadFile(new NetUtil.OnPicsUploadSuccessListener() {
                    @Override
                    public void onPicsUploadSuccess(List<String> pics) {
                        newPicUrls = pics;
                        if (!model.getVideo_url().isEmpty()) {
                            upLoadVideo();
                        } else if (!model.getVoice_url().isEmpty()){
                            upLoadVoice();
                        }else {
                            lastCommit();
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
        voiceList.addAll(model.getVoice_url());
        //先上传图片，再上传视频
        addCall(new NetUtil().setPic_path(voiceList)
                .buildUploadFile(new NetUtil.OnPicsUploadSuccessListener() {
                    @Override
                    public void onPicsUploadSuccess(List<String> pics) {
                        newVoiceUrls = pics;
                        lastCommit();
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
        videosList.addAll(model.getVideo_url());
        addCall(new NetUtil().setPic_path(videosList)
                .buildUploadFile(new NetUtil.OnPicsUploadSuccessListener() {
                    @Override
                    public void onPicsUploadSuccess(List<String> pics) {
                        newVideoUrls = pics;
                        if (!model.getVoice_url().isEmpty()) {
                            upLoadVoice();
                        } else {
                            lastCommit();
                        }
                    }

                    @Override
                    public void onPicsUploadFail(String msg) {
                        showTips(msg);
                        loadingDialog.dismiss();
                    }
                }));

    }


    private void lastCommit() {
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

        addCall(new NetUtil().setUrl(APIS.URL_TALENT_RELEASE_LOOKING_FOR_OBJECTS)
                .addParams("user_name", model.getUser_name())
                .addParams("mobile_phone", model.getMobile_phone())
                .addParams("sex", model.getSex() + "")
                .addParams("height", model.getHeight())
                .addParams("weight", model.getWeight())
                .addParams("birthday", model.getBirthday())
                .addParams("image_url", picStr)
                .addParams("video_url", videoStr)
                .addParams("voice_url", voiceStr)
                .addParams("education_background_id", model.getEducation_background_id() + "")
                .addParams("job_class_id_one", model.getJob_class_id_one() + "")
                .addParams("job_class_id_two", model.getJob_class_id_two() + "")
                .addParams("salary_id", model.getSalary_id() + "")
                .addParams("first_area_id", model.getFirst_area_id() + "")
                .addParams("live_second_area_id", model.getLive_second_area_id() + "")
                .addParams("release_third_area_id", model.getRelease_third_area_id() + "")
                .addParams("marital_status", model.getMarital_status() + "")
                .addParams("child_status", model.getChild_status() + "")
                .addParams("self_intro", model.getSelf_intro())
                .addParams("pic_urls", picStr)
                .addParams("video_url", videoStr)
                .addParams("start_zo_height", edHeigjt1.getText().toString().trim())
                .addParams("end_zo_height", edHeigjt2.getText().toString().trim())
                .addParams("start_zo_weight", edWeight1.getText().toString().trim())
                .addParams("end_zo_weight", edWeight2.getText().toString().trim())
                .addParams("start_zo_age", edAge1.getText().toString().trim())
                .addParams("end_zo_age", edAge2.getText().toString().trim())
                .addParams("zo_education_background_id", zo_education_background_id + "")
                .addParams("zo_job_class_id_one", zo_job_class_id_one + "")
                .addParams("zo_job_class_id_two", zo_job_class_id_two + "")
                .addParams("zo_salary_id", zo_salary_id + "")
                .addParams("zo_first_area_id", zo_first_area_id + "")
                .addParams("zo_live_second_area_id", zo_live_second_area_id + "")
                .addParams("zo_marital_status", zo_marital_status + "")
                .addParams("zo_child_status", zo_child_status + "")
                .addParams("assets_require", assets_require)
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
                            NewPayActivity.go(YiinyuanZeOuActivity.this,
                                    CommonPayPresenter.TYPE_YINYUAN_DUIXIANG, respnse.getData().getRelate_id(),
                                    respnse.getData().getOrder_price() + "",
                                    respnse.getData().getOrder_name(),0);
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

    private void getHunYinZiNvList(){
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

    private PayInfoFeeModel currModel;

    /**
     * 获取支付信息
     */
    private void getPayFeeInfo() {
        payPresenter.loadPayInfoFeeData(CommonPayPresenter.FEE_TYPE_DUIXIANG_ZHAOPIN, new CommonPayPresenter.DataCallBack<PayInfoFeeModel>() {
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
                    tv_info.setText(new SpanUtils().append("信息费")
                            .append("¥" + currModel.getCharge_fee())
                            .setForegroundColor(ResUtils.getCommRed())
                            .create());
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NewPayActivity.REQUEST_CODE && resultCode == NewPayActivity.RESULT_CODE_SUCCESS) {
            //处理你的操作
            setResult(NewPayActivity.RESULT_CODE_SUCCESS);
            finish();
        }
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String city_name = data.getStringExtra(Define.INTENT_DATA);
            int area_id = data.getIntExtra("area_id", -1);
            if (type == 1) {
                //户籍
                tvHuji.setText(TextUtils.isEmpty(city_name) ? "" : city_name);
                zo_first_area_id = area_id;
            } else if (type == 2) {
                //居住地
                tv_juzhudi.setText(TextUtils.isEmpty(city_name) ? "" : city_name);
                zo_live_second_area_id = area_id;
            }
        }

        if (requestCode == PositionTwoCLassActivity.REQUEST_CODE && resultCode == PositionTwoCLassActivity.RESULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                zo_job_class_id_one = extras.getString(PositionThreeCLassActivity.JOB_CLASS_ONE_ID);
                zo_job_class_id_two = extras.getString(PositionThreeCLassActivity.JOB_CLASS_TWO_ID);
                String job_class_name_two = extras.getString(PositionThreeCLassActivity.JOB_CLASS_TWO_NAME);
                String job_class_name_one = extras.getString(PositionThreeCLassActivity.JOB_CLASS_ONE_NAME);
                tvZhiwei.setText(job_class_name_one + "/" + job_class_name_two);
            }
        }
    }

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
                            if (educationList  != null && educationList.size() >0){
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
            if (lay_ll_root != null) {
                CommonPopwindow commonPopwindow = new CommonPopwindow(this, lay_ll_root, educationList);
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
                        for (EducationModel bean: educationList){
                            bean.setSelect(false);
                        }

                        if (educationModel != null) {
                            educationModel.setSelect(true);
                            tvXueli.setText(educationModel.getEducation_background_name());
                            zo_education_background_id = educationModel.getEducation_background_id();
                        }
                    }
                });
                if (circlePop != null) {
                    circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    circlePop.showAtAnchorView(lay_ll_root, VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
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
                            if (salaryList  != null && salaryList.size() >0){
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
            if (lay_ll_root != null) {
                CommonPopwindow commonPopwindow = new CommonPopwindow(this, lay_ll_root, salaryList);
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
                        for (SalaryTypeModel bean : salaryList){
                            bean.setSelect(false);
                        }
                        if (educationModel != null) {
                            educationModel.setSelect(true);
                            tvShouru.setText(educationModel.getSalary_name());
                            zo_salary_id = educationModel.getSalary_id();
                        }
                    }
                });
                if (circlePop != null) {
                    circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    circlePop.showAtAnchorView(lay_ll_root, VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
                }
            }
        } else {
            getSalaryList(true);
        }
    }

    /**
     * 选择婚姻状态
     */
    private void onSelectMarryStatus() {
        if (lay_ll_root != null){
            CommonPopwindow commonPopwindow = new CommonPopwindow(this, lay_ll_root, hunYinlist);
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
                    for (WorkTimeModel model : hunYinlist){
                        model.setSelect(false);
                    }
                    if (workTimeModel != null) {
                        workTimeModel.setSelect(true);
                        tvHunyin.setText(workTimeModel.getName());
                        zo_marital_status = position + 1;
                    }

                }
            });
            if (circlePop != null) {
                circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                circlePop.showAtAnchorView(lay_ll_root, VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
            }
        }

    }

    /**
     * 选择子女状态
     */
    private void onSelectChildStatus() {

        if (lay_ll_root != null){
            CommonPopwindow commonPopwindow = new CommonPopwindow(this, lay_ll_root, ziNvlist);
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
                    for (WorkTimeModel model : ziNvlist){
                        model.setSelect(false);
                    }
                    if (workTimeModel != null) {
                        workTimeModel.setSelect(true);
                        tvZinv.setText(workTimeModel.getName());
                        zo_child_status = position + 1;
                    }


                }
            });
            if (circlePop != null) {
                circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                circlePop.showAtAnchorView(lay_ll_root, VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
            }
        }
    }
}
