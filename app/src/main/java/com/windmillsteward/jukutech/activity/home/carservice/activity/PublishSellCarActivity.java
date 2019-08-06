package com.windmillsteward.jukutech.activity.home.carservice.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.houselease.presenter.PublishSellCarActivityPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishSuccessActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.SelectPhotoActivity;
import com.windmillsteward.jukutech.activity.home.think.activity.PublishIdeaThinkActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.CapitalDetailBean;
import com.windmillsteward.jukutech.bean.CarDetailBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.customview.AssortPinyin.HashList;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.BottomDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class PublishSellCarActivity extends BaseActivity implements View.OnClickListener, TakePhoto.TakeResultListener, InvokeListener, PublishSellCarActivityView {
    private static final int REQUEST_CODE_IMAGE = 100;

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    private ImageView mToolbarIvBack;
    private TextView mToolbarIvTitle;
    private ImageView mToolbarIvRight;
    private TextView mToolbarTvRight;
    private ImageView mIvPic;
    private TextView mTvClickUpload;
    private EditText mEtBrandName;
    private TextView tv_brand_name;
    private TextView mTvChoiceBrand;
    private EditText mEtDisplacement;
    private TextView mTvGearbox;
    private TextView mTvLicenseTime;
    private TextView mTvNextValidateTime;
    private TextView mTvCompulsoryInsuranceTime;
    private TextView mTvCommercialInsuranceTime;
    private EditText mEtMileage;
    private EditText mEtPrice;
    private ImageView mIvIsTransferFee;
    private TextView mTvCarColor;
    private TextView mTvDealArea;
    private ImageView mIvDrivingLicense;
    private ImageView mIvVehicleIdentification;
    private EditText mEtRequireDesc;
    private EditText mEtContacts;
    private EditText mEtPhone;
    private TextView mTvPublishArea;
    private TextView mTvPublish;

    private ArrayList<String> pic_path = new ArrayList<>();
    private int brand_id = -1;
    private String brand_name;
    private int series_id;
    private String series_name;
    private int vehicle_module_id;
    private String vehicle_name;
    private int gearbox_id = -1;
    private String car_color;
    private int deal_third_area_id;
    private int third_area_id;
    private String driving_license;
    private String vehicle_identification;
    // 首次上牌时间
    private String license_time;
    // 下次年检时间
    private String next_validate_time;
    // 交强险到期时间
    private String compulsory_insurance_time;
    // 商业险到期时间
    private String commercial_insurance_time;
    // 是否包含过户费
    private int is_transfer_fee = 1;

    private PublishSellCarActivityPresenter presenter;
    private int curr_photo;  // 当前选中的图片

    private CarDetailBean bean;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_sellcar);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt(Define.INTENT_TYPE);
            bean = (CarDetailBean) extras.getSerializable(Define.INTENT_DATA);
        }
        initView();
        initToolbar();

        presenter = new PublishSellCarActivityPresenter(this);
        if (type == 1 && bean != null) {
            initData();
        }
    }

    private void initData() {
        mTvClickUpload.setText("已选择" + bean.getPic_urls().size() + "张图片");
        pic_path.addAll(bean.getPic_urls());
        if (bean.getBrand_id() == -1) {
            mTvChoiceBrand.setTag(false);
            mEtBrandName.setText(bean.getBrand_name());
            mTvChoiceBrand.setText("选择品牌名称，请点击这里");
        } else {
            mTvChoiceBrand.setTag(true);
            tv_brand_name.setText(bean.getBrand_name());
            mTvChoiceBrand.setText("没有找到品牌，自填品牌,请点击这里");
            brand_name = bean.getBrand_name();
            brand_id = bean.getBrand_id();
            series_name = bean.getSeries_name();
            series_id = bean.getSeries_id();
            vehicle_name = bean.getVehicle_module_name();
            vehicle_module_id = bean.getVehicle_module_id();
        }
        mEtDisplacement.setText(bean.getDisplacement());
        mTvGearbox.setText(bean.getGearbox_name());
        gearbox_id = bean.getGearbox_id();
        mTvLicenseTime.setText(bean.getLicense_time());
        license_time = bean.getLicense_time();
        mTvNextValidateTime.setText(bean.getNext_validate_time());
        next_validate_time = bean.getNext_validate_time();
        mTvCompulsoryInsuranceTime.setText(bean.getCompulsory_insurance_time());
        compulsory_insurance_time = bean.getCompulsory_insurance_time();
        mTvCommercialInsuranceTime.setText(bean.getCommercial_insurance_time());
        commercial_insurance_time = bean.getCommercial_insurance_time();
        mEtMileage.setText(bean.getMileage());
        mEtPrice.setText(bean.getPrice());
        if (bean.getIs_transfer_fee() == 1) {
            mIvIsTransferFee.setImageResource(R.mipmap.icon_slipon);
        } else {
            mIvIsTransferFee.setImageResource(R.mipmap.icon_slipoff);
        }
        is_transfer_fee = bean.getIs_transfer_fee();
        mTvCarColor.setText(bean.getCar_color());
        car_color = bean.getCar_color();
        mTvDealArea.setText(bean.getDeal_area_name());
        deal_third_area_id = bean.getDeal_third_area_id();
        Glide.with(this).load(bean.getDriving_license()).into(mIvDrivingLicense);
        driving_license = bean.getDriving_license();
        Glide.with(this).load(bean.getVehicle_identification()).into(mIvVehicleIdentification);
        vehicle_identification = bean.getVehicle_identification();
        mEtRequireDesc.setText(bean.getDetails());
        mEtContacts.setText(bean.getContact_person());
        mEtPhone.setText(bean.getContact_tel());
        mTvPublishArea.setText(bean.getThird_area_name());
        third_area_id = bean.getThird_area_id();
        mTvPublish.setText("保存");
    }

    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    private void initToolbar() {
        handleBackEvent(mToolbarIvBack);
        mToolbarIvTitle.setText("我要卖车");
        mImmersionBar.keyboardEnable(true).init();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 同步图片选择器状态
        getTakePhoto().onActivityResult(requestCode, resultCode, intent);
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == SelectPhotoActivity.RESULT_CODE) {
            ArrayList<String> imageList = intent.getStringArrayListExtra(SelectPhotoActivity.RESULT_DATA);
            if (imageList != null && imageList.size() > 0) {
                pic_path.clear();
                pic_path.addAll(imageList);
                mTvClickUpload.setText("已选择" + imageList.size() + "张图片");
            } else {
                pic_path.clear();
                mTvClickUpload.setText("点击上传图片");
            }
        } else if (requestCode == 100 && resultCode == 101) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                brand_id = extras.getInt(CarBrandListActivity.ID1, 0);
                brand_name = extras.getString(CarBrandListActivity.TEXT1, "");
                series_id = extras.getInt(CarBrandListActivity.ID2, 0);
                series_name = extras.getString(CarBrandListActivity.TEXT2, "");
                vehicle_module_id = extras.getInt(CarBrandListActivity.ID3, 0);
                vehicle_name = extras.getString(CarBrandListActivity.TEXT3, "");
                tv_brand_name.setText(brand_name + " " + series_name + " " + vehicle_name);
            }
        }
//        else if (requestCode == PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            presenter.uploadPic(pic_path);
//        }
    }

    private void initView() {
        mToolbarIvBack = (ImageView) findViewById(R.id.toolbar_iv_back);
        mToolbarIvTitle = (TextView) findViewById(R.id.toolbar_iv_title);
        mToolbarIvRight = (ImageView) findViewById(R.id.toolbar_iv_right);
        mToolbarTvRight = (TextView) findViewById(R.id.toolbar_tv_right);
        mIvPic = (ImageView) findViewById(R.id.iv_pic);
        mTvClickUpload = (TextView) findViewById(R.id.tv_click_upload);
        mEtBrandName = (EditText) findViewById(R.id.et_brand_name);
        tv_brand_name = (TextView) findViewById(R.id.tv_brand_name);
        mTvChoiceBrand = (TextView) findViewById(R.id.tv_choice_brand);
        mEtDisplacement = (EditText) findViewById(R.id.et_displacement);
        mTvGearbox = (TextView) findViewById(R.id.tv_gearbox);
        mTvLicenseTime = (TextView) findViewById(R.id.tv_license_time);
        mTvNextValidateTime = (TextView) findViewById(R.id.tv_next_validate_time);
        mTvCompulsoryInsuranceTime = (TextView) findViewById(R.id.tv_compulsory_insurance_time);
        mTvCommercialInsuranceTime = (TextView) findViewById(R.id.tv_commercial_insurance_time);
        mEtMileage = (EditText) findViewById(R.id.et_mileage);
        mEtPrice = (EditText) findViewById(R.id.et_price);
        mIvIsTransferFee = (ImageView) findViewById(R.id.iv_is_transfer_fee);
        mTvCarColor = (TextView) findViewById(R.id.tv_car_color);
        mTvDealArea = (TextView) findViewById(R.id.tv_deal_area);
        mIvDrivingLicense = (ImageView) findViewById(R.id.iv_driving_license);
        mIvVehicleIdentification = (ImageView) findViewById(R.id.iv_vehicle_identification);
        mEtRequireDesc = (EditText) findViewById(R.id.et_require_desc);
        mEtContacts = (EditText) findViewById(R.id.et_contacts);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mTvPublishArea = (TextView) findViewById(R.id.tv_publish_area);
        mTvPublish = (TextView) findViewById(R.id.tv_publish);

        mTvChoiceBrand.setTag(true);  // 默认
        mIvPic.setOnClickListener(this);
        mTvChoiceBrand.setOnClickListener(this);
        mTvGearbox.setOnClickListener(this);
        mTvCarColor.setOnClickListener(this);
        mTvDealArea.setOnClickListener(this);
        mIvDrivingLicense.setOnClickListener(this);
        mIvVehicleIdentification.setOnClickListener(this);
        mTvLicenseTime.setOnClickListener(this);
        mTvNextValidateTime.setOnClickListener(this);
        mTvCompulsoryInsuranceTime.setOnClickListener(this);
        mTvCommercialInsuranceTime.setOnClickListener(this);
        mTvPublishArea.setOnClickListener(this);
        mIvIsTransferFee.setOnClickListener(this);
        mTvPublish.setOnClickListener(this);
        tv_brand_name.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SystemUtil.dismissKeyBorwd(this);
        switch (view.getId()) {
            case R.id.iv_pic:
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(SelectPhotoActivity.INIT_DATA, pic_path);
                startAtvDonFinishForResult(SelectPhotoActivity.class, REQUEST_CODE_IMAGE, bundle);
                break;
            case R.id.tv_choice_brand:
                if (((Boolean) mTvChoiceBrand.getTag())) {  // 之前是默认,现在变成
                    mEtBrandName.setText("");
                    mEtBrandName.setVisibility(View.VISIBLE);
                    tv_brand_name.setText("");
                    tv_brand_name.setVisibility(View.GONE);
                    mTvChoiceBrand.setText("选择品牌名称，请点击这里");
                    brand_id = -1;
                    series_id = -1;
                    vehicle_module_id = -1;
                } else {
                    mEtBrandName.setText("");
                    mEtBrandName.setVisibility(View.GONE);
                    tv_brand_name.setText("");
                    tv_brand_name.setVisibility(View.VISIBLE);
                    mTvChoiceBrand.setText("没有找到品牌，自填品牌,请点击这里");
                }

                mTvChoiceBrand.setTag(!(Boolean) mTvChoiceBrand.getTag());
                break;
            case R.id.tv_brand_name:
                startAtvDonFinishForResult(CarBrandListActivity.class, 100);
                break;
            case R.id.tv_gearbox:
                presenter.loadGearboxData();
                break;
            case R.id.tv_car_color:
                presenter.loadCarColorData();
                break;
            case R.id.tv_deal_area:
                presenter.loadDealAreaData(getCurrCityId());
                break;
            case R.id.tv_publish_area:
                presenter.loadPublishAreaData(getCurrCityId());
                break;
            case R.id.iv_driving_license:  // 行驶证照片url
                curr_photo = 1;
                new BottomDialog(this, new BottomDialog.OnSelectListener() {
                    @Override
                    public void onTakePhoneClick() {
                        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                        Uri imageUri = Uri.fromFile(file);
                        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
                        builder.setWithOwnGallery(true);
                        builder.setCorrectImage(true);
                        takePhoto.setTakePhotoOptions(builder.create());

                        CompressConfig config = new CompressConfig.Builder().create();
                        takePhoto.onEnableCompress(config, true);
                        takePhoto.onPickFromCapture(imageUri);
                    }

                    @Override
                    public void onChoosePhoto() {
                        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
                        builder.setWithOwnGallery(true);
                        builder.setCorrectImage(true);
                        takePhoto.setTakePhotoOptions(builder.create());

                        CompressConfig config = new CompressConfig.Builder().create();
                        takePhoto.onEnableCompress(config, true);
                        takePhoto.onPickFromGallery();
                    }
                });
                break;
            case R.id.iv_vehicle_identification:  // 车架号实拍照片url
                curr_photo = 2;
                new BottomDialog(this, new BottomDialog.OnSelectListener() {
                    @Override
                    public void onTakePhoneClick() {
                        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                        Uri imageUri = Uri.fromFile(file);
                        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
                        builder.setWithOwnGallery(true);
                        builder.setCorrectImage(true);
                        takePhoto.setTakePhotoOptions(builder.create());

                        CompressConfig config = new CompressConfig.Builder().create();
                        takePhoto.onEnableCompress(config, true);
                        takePhoto.onPickFromCapture(imageUri);
                    }

                    @Override
                    public void onChoosePhoto() {
                        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
                        builder.setWithOwnGallery(true);
                        builder.setCorrectImage(true);
                        takePhoto.setTakePhotoOptions(builder.create());

                        CompressConfig config = new CompressConfig.Builder().create();
                        takePhoto.onEnableCompress(config, true);
                        takePhoto.onPickFromGallery();
                    }
                });
                break;
            case R.id.tv_license_time:  // 首次上牌时间【格式如：2017年03月】
                TimePickerView start = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        license_time = DateUtil.getYY_MM_DD(date, "yyyy年MM月");
                        mTvLicenseTime.setText(license_time);
                    }
                })
                        .setType(new boolean[]{true, true, false, false, false, false})
                        .build();
                start.setDate(Calendar.getInstance());
                start.show();
                break;
            case R.id.tv_next_validate_time: // 下次验证时间【格式如：2017年03月】
                TimePickerView next = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        next_validate_time = DateUtil.getYY_MM_DD(date, "yyyy年MM月");
                        mTvNextValidateTime.setText(next_validate_time);
                    }
                })
                        .setType(new boolean[]{true, true, false, false, false, false})
                        .build();
                next.setDate(Calendar.getInstance());
                next.show();
                break;
            case R.id.tv_compulsory_insurance_time:  // 交强险过期时间【格式如：2017年03月】
                TimePickerView compulsory = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        compulsory_insurance_time = DateUtil.getYY_MM_DD(date, "yyyy年MM月");
                        mTvCompulsoryInsuranceTime.setText(compulsory_insurance_time);
                    }
                })
                        .setType(new boolean[]{true, true, false, false, false, false})
                        .build();
                compulsory.setDate(Calendar.getInstance());
                compulsory.show();
                break;
            case R.id.tv_commercial_insurance_time: // 商业险过期时间【格式如：2017年03月】
                TimePickerView commercial = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        commercial_insurance_time = DateUtil.getYY_MM_DD(date, "yyyy年MM月");
                        mTvCommercialInsuranceTime.setText(commercial_insurance_time);
                    }
                })
                        .setType(new boolean[]{true, true, false, false, false, false})
                        .build();
                commercial.setDate(Calendar.getInstance());
                commercial.show();
                break;
            case R.id.iv_is_transfer_fee:
                if (is_transfer_fee == 1) {
                    is_transfer_fee = 0;
                    mIvIsTransferFee.setImageResource(R.mipmap.icon_slipoff);
                } else if (is_transfer_fee == 0) {
                    is_transfer_fee = 1;
                    mIvIsTransferFee.setImageResource(R.mipmap.icon_slipon);
                }
                break;
            case R.id.tv_publish:
                submit();
                break;
        }
    }

    private void submit() {
        if (pic_path.size() == 0) {
            showTips("请选择图片", 0);
            return;
        }
        if ((Boolean) mTvChoiceBrand.getTag()) {
            if (brand_id <= 0 || TextUtils.isEmpty(tv_brand_name.getText().toString().trim())) {
                showTips("请选择车辆品牌", 0);
                return;
            }
        } else {
            if (TextUtils.isEmpty(mEtBrandName.getText().toString().trim())) {
                showTips("请输入车辆品牌", 0);
                return;
            }
            brand_name = mEtBrandName.getText().toString().trim();
        }

        if (TextUtils.isEmpty(mEtDisplacement.getText().toString().trim())) {
            showTips("请输入排量", 0);
            return;
        }
        if (gearbox_id == -1) {
            showTips("请选择变速箱类型", 0);
            return;
        }
        if (TextUtils.isEmpty(license_time)) {
            showTips("请选择首次上牌时间", 0);
            return;
        }
        if (TextUtils.isEmpty(next_validate_time)) {
            showTips("请选择下次年审时间", 0);
            return;
        }
        if (TextUtils.isEmpty(compulsory_insurance_time)) {
            showTips("请选择交强险过期时间", 0);
            return;
        }
        if (TextUtils.isEmpty(commercial_insurance_time)) {
            showTips("请选择商业险过期时间", 0);
            return;
        }
        if (TextUtils.isEmpty(mEtMileage.getText().toString().trim())) {
            showTips("请输入里程数", 0);
            return;
        }
        if (TextUtils.isEmpty(mEtPrice.getText().toString().trim())) {
            showTips("请输入价格", 0);
            return;
        }
        if (TextUtils.isEmpty(car_color)) {
            showTips("请输入车辆颜色", 0);
            return;
        }
        if (deal_third_area_id == 0) {
            showTips("请选择交易区域", 0);
            return;
        }
        if (TextUtils.isEmpty(driving_license)) {
            showTips("请上传行驶证", 0);
            return;
        }
        if (TextUtils.isEmpty(vehicle_identification)) {
            showTips("请上传车架号照片", 0);
            return;
        }
        if (TextUtils.isEmpty(mEtRequireDesc.getText().toString().trim())) {
            showTips("请输入详情", 0);
            return;
        }
        if (TextUtils.isEmpty(mEtContacts.getText().toString().trim())) {
            showTips("请输入联系人", 0);
            return;
        }
        if (TextUtils.isEmpty(mEtPhone.getText().toString().trim())) {
            showTips("请输入联系电话", 0);
            return;
        }
        if (third_area_id == 0) {
            showTips("请选择发布区域", 0);
            return;
        }
        if (type == 0) {
            presenter.isCharge(getAccessToken(), 0);
        } else {
            presenter.uploadPic(pic_path);
        }
    }

    @Override
    public void loadGearboxDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        mTvGearbox.setText(text);
                        gearbox_id = id;
                    }
                })
                .show();
    }

    @Override
    public void loadCarColorDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        mTvCarColor.setText(text);
                        car_color = text;
                    }
                })
                .show();
    }

    @Override
    public void loadDealAreaDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        mTvDealArea.setText(text);
                        deal_third_area_id = id;
                    }
                })
                .show();
    }

    @Override
    public void uploadPicSuccess(String pic_url) {
        if (curr_photo == 1) {
            GlideUtil.show(this, pic_url, mIvDrivingLicense, R.mipmap.icon_add_pic);
            driving_license = pic_url;
        } else if (curr_photo == 2) {
            GlideUtil.show(this, pic_url, mIvVehicleIdentification, R.mipmap.icon_add_pic);
            vehicle_identification = pic_url;
        }
    }

    @Override
    public void uploadPicSuccess(List<String> pic_urls) {
        if (type == 0) {
            presenter.publish(getAccessToken(), pic_urls, brand_id, series_id, vehicle_module_id, brand_name, mEtDisplacement.getText().toString().trim(), gearbox_id, license_time, next_validate_time,
                    compulsory_insurance_time, commercial_insurance_time, mEtMileage.getText().toString().trim(), mEtPrice.getText().toString().trim(),
                    is_transfer_fee, car_color, getCurrCityId(), deal_third_area_id, driving_license, vehicle_identification, mEtRequireDesc.getText().toString().trim(),
                    mEtContacts.getText().toString().trim(), mEtPhone.getText().toString().trim(), getCurrCityId(), third_area_id);
        } else {
            presenter.edit(getAccessToken(), bean.getCar_id(), pic_urls, brand_id, series_id, vehicle_module_id, brand_name, mEtDisplacement.getText().toString().trim(), gearbox_id, license_time, next_validate_time,
                    compulsory_insurance_time, commercial_insurance_time, mEtMileage.getText().toString().trim(), mEtPrice.getText().toString().trim(),
                    is_transfer_fee, car_color, getCurrCityId(), deal_third_area_id, driving_license, vehicle_identification, mEtRequireDesc.getText().toString().trim(),
                    mEtContacts.getText().toString().trim(), mEtPhone.getText().toString().trim(), getCurrCityId(), third_area_id);
        }
    }

    @Override
    public void loadPublishAreaDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        mTvPublishArea.setText(text);
                        third_area_id = id;
                    }
                })
                .show();
    }

    @Override
    public void publishSuccess() {
        PublishSuccessActivity.go(this, 10);
    }

    @Override
    public void isCharge(ChargeResultBean bean) {
        if (bean.getIs_charge() == 0) {
            presenter.uploadPic(pic_path);
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("发布需求需要支付费用，继续吗")
                    .setCancelable(true)
                    .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .show();
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        String compressPath = result.getImage().getCompressPath();
        String originalPath = result.getImage().getOriginalPath();
        if (!TextUtils.isEmpty(compressPath) && !TextUtils.isEmpty(originalPath)) {
            File compressFile = new File(compressPath);
            File originalFile = new File(originalPath);
            if (compressFile.length() < originalFile.length()) {
                if (curr_photo == 1) {
                    presenter.uploadDrivingPic(compressPath);
                } else if (curr_photo == 2) {
                    presenter.uploadVehiclePic(compressPath);
                }
            } else {
                if (curr_photo == 1) {
                    presenter.uploadDrivingPic(originalPath);
                } else if (curr_photo == 2) {
                    presenter.uploadVehiclePic(originalPath);
                }
            }
        } else {
            if (TextUtils.isEmpty(compressPath) && !TextUtils.isEmpty(originalPath)) {
                if (curr_photo == 1) {
                    presenter.uploadDrivingPic(originalPath);
                } else if (curr_photo == 2) {
                    presenter.uploadVehiclePic(originalPath);
                }
            } else if (!TextUtils.isEmpty(compressPath) && TextUtils.isEmpty(originalPath)) {
                if (curr_photo == 1) {
                    presenter.uploadDrivingPic(compressPath);
                } else if (curr_photo == 2) {
                    presenter.uploadVehiclePic(compressPath);
                }
            }
        }
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }
}
