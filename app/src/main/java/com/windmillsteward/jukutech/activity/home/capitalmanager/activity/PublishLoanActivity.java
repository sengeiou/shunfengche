package com.windmillsteward.jukutech.activity.home.capitalmanager.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.windmillsteward.jukutech.activity.home.capitalmanager.presenter.PublishPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishSuccessActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.SelectPhotoActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.CapitalDetailBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.BottomDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class PublishLoanActivity extends BaseActivity implements PublishView, View.OnClickListener, TakePhoto.TakeResultListener, InvokeListener {

    private static final int REQUEST_CODE_IMAGE = 100;

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private ImageView iv_pic;
    private TextView tv_click_upload;
    private ImageView iv_logo;
    private EditText et_title;
    private EditText et_description;
    private EditText et_minimum_amount;
    private EditText et_yield_rate;
    private EditText et_introduction;
    private EditText et_contacts;
    private EditText et_phone;
    private TextView tv_publish_area;
    private TextView tv_publish;

    private PublishPresenter presenter;

    private ArrayList<String> pic_path = new ArrayList<>();
    private String logo;
    private int third_area_id;

    private CapitalDetailBean bean;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_loan);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            type = extras.getInt(Define.INTENT_TYPE);
            bean = (CapitalDetailBean) extras.getSerializable(Define.INTENT_DATA);
        }
        initView();
        initToolbar();

        presenter = new PublishPresenter(this);
        if (type==1 && bean!=null) {
            initData();
        }
    }

    private void initData() {
        tv_click_upload.setText("已选择"+bean.getPic_urls().size()+"张图片");
        pic_path.addAll(bean.getPic_urls());
        Glide.with(this).load(bean.getCapital_logo()).into(iv_logo);
        logo = bean.getCapital_logo();
        et_title.setText(bean.getTitle());
        et_description.setText(bean.getDescription());
        et_minimum_amount.setText(bean.getMinimum_amount());
        et_yield_rate.setText(bean.getYield_rate());
        et_introduction.setText(bean.getIntroduction());
        et_contacts.setText(bean.getNickname());
        et_phone.setText(bean.getContact_mobile_phone());
        tv_publish_area.setText(bean.getThird_area_name());
        third_area_id = bean.getThird_area_id();
        tv_publish.setText("保存");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == SelectPhotoActivity.RESULT_CODE) {
            ArrayList<String> imageList = data.getStringArrayListExtra(SelectPhotoActivity.RESULT_DATA);
            if (imageList != null && imageList.size() > 0) {
                pic_path.clear();
                pic_path.addAll(imageList);
                tv_click_upload.setText("已选择" + imageList.size() + "张图片");
            } else {
                pic_path.clear();
                tv_click_upload.setText("点击上传图片");
            }
        }
//        else if (requestCode == PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            presenter.uploadPic(pic_path);
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }


    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("发布产品");
        mImmersionBar.keyboardEnable(true).init();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        tv_click_upload = (TextView) findViewById(R.id.tv_click_upload);
        iv_logo = (ImageView) findViewById(R.id.iv_logo);
        et_title = (EditText) findViewById(R.id.et_title);
        et_description = (EditText) findViewById(R.id.et_description);
        et_minimum_amount = (EditText) findViewById(R.id.et_minimum_amount);
        et_yield_rate = (EditText) findViewById(R.id.et_yield_rate);
        et_introduction = (EditText) findViewById(R.id.et_introduction);
        et_contacts = (EditText) findViewById(R.id.et_contacts);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_publish_area = (TextView) findViewById(R.id.tv_publish_area);
        tv_publish = (TextView) findViewById(R.id.tv_publish);

        iv_pic.setOnClickListener(this);
        tv_publish_area.setOnClickListener(this);
        tv_publish.setOnClickListener(this);
        iv_logo = (ImageView) findViewById(R.id.iv_logo);
        iv_logo.setOnClickListener(this);
    }

    private void submit() {
        if (pic_path.size() == 0) {
            showTips("请选择图片", 0);
            return;
        }
//        if (TextUtils.isEmpty(logo)) {
//            showTips("请选择图片", 0);
//            return;
//        }
        String title = et_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showTips("请输入产品名称", 0);
            return;
        }

        String rate = et_yield_rate.getText().toString().trim();
        if (TextUtils.isEmpty(rate)) {
            showTips("请输入七日年华收益率", 0);
            return;
        }

        String amount = et_minimum_amount.getText().toString().trim();
        if (TextUtils.isEmpty(amount)) {
            showTips("请输入金额", 0);
            return;
        }

        String introduction = et_introduction.getText().toString().trim();
        if (TextUtils.isEmpty(introduction)) {
            showTips("输入对产品的描述", 0);
            return;
        }

        String contacts = et_contacts.getText().toString().trim();
        if (TextUtils.isEmpty(contacts)) {
            showTips("请输入姓名", 0);
            return;
        }

        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showTips("请输入手机号", 0);
            return;
        }
        if (third_area_id == 0) {
            showTips("请选择发布地区", 0);
            return;
        }
        if (type==0) {
            presenter.isCharge(getAccessToken(),82,0);
        } else {
            presenter.uploadPic(pic_path);
        }
    }

    @Override
    public void loadProductTypeDataSuccess(List<Map<String, Object>> maps) {

    }

    @Override
    public void loadPublishAreaDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_publish_area.setText(text);
                        third_area_id = id;
                    }
                })
                .show();
    }

    @Override
    public void uploadPicSuccess(String pic_url) {
        GlideUtil.show(this,pic_url,iv_logo,R.mipmap.icon_add_pic);
        logo = pic_url;
    }

    @Override
    public void uploadPicSuccess(List<String> img_urls) {
        if (type==0) {
            presenter.publish(getAccessToken(), 2, et_title.getText().toString().trim(), getCurrCityId(), third_area_id, et_yield_rate.getText().toString().trim(), et_minimum_amount.getText().toString().trim(),
                    et_phone.getText().toString().trim(), et_contacts.getText().toString().trim(), 0, "", et_description.getText().toString().trim(), logo, img_urls, et_introduction.getText().toString().trim());
        } else {
            presenter.edit(getAccessToken(), bean.getCapital_id(), 2, et_title.getText().toString().trim(), getCurrCityId(), third_area_id, et_yield_rate.getText().toString().trim(), et_minimum_amount.getText().toString().trim(),
                    et_phone.getText().toString().trim(), et_contacts.getText().toString().trim(), 0, "", et_description.getText().toString().trim(), logo, img_urls, et_introduction.getText().toString().trim());
        }
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            presenter.uploadPic(pic_path);
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("该发布需要支付费用，继续吗")
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
    public void publishSuccess() {
        PublishSuccessActivity.go(this,13);
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
            case R.id.iv_logo: // logo
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
            case R.id.tv_publish_area:
                presenter.loadPublishAreaData(getCurrCityId());
                break;
            case R.id.tv_publish:
                submit();
                break;
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
                presenter.uploadDrivingPic(compressPath);
            } else {
                presenter.uploadDrivingPic(originalPath);
            }
        } else {
            if (TextUtils.isEmpty(compressPath) && !TextUtils.isEmpty(originalPath)) {
                presenter.uploadDrivingPic(originalPath);
            } else if (!TextUtils.isEmpty(compressPath) && TextUtils.isEmpty(originalPath)) {
                presenter.uploadDrivingPic(compressPath);
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
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }
}
