package com.windmillsteward.jukutech.activity.mine.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.presenter.AuthenticationPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：企业认证
 * 时间：2018/3/11/011
 * 作者：xjh
 */
public class BusinessAuthenticationActivity extends BaseActivity implements AuthenticationView, TakePhoto.TakeResultListener, InvokeListener, View.OnClickListener {

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private EditText et_corporate_name;
    private EditText et_address;
    private EditText et_username;
    private EditText et_itcard;
    private ImageView iv_license;
    private ImageView iv_idcard;
    private ImageView iv_idcard_b;
    private TextView tv_submit;

    private AuthenticationPresenter presenter;
    private int currImageSelect;
    private String license_path;
    private String idcard_path;
    private String idcard_path_b;

//    private TextView tv_guimo;
//    private TextView tv_xingzhi;
//    private TextView tv_hangye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitlicense);
        initView();
        initToolbar();

        presenter = new AuthenticationPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data ) {
        // 同步图片选择器状态
//        getTakePhoto().onActivityResult(requestCode, resultCode, intent);
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);

            if (!selectList.isEmpty()) {
                LocalMedia localMedia = selectList.get(0);
                String currImageUrl = localMedia.getPath();
                handleImage(currImageUrl);
            }
        }
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
        toolbar_iv_title.setText("填写企业资料");
        toolbar_tv_right.setVisibility(View.VISIBLE);
        toolbar_tv_right.setText("个人认证");
        toolbar_tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAtvDonFinish(PersonalAuthenticationActivity.class);
            }
        });
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        et_corporate_name = (EditText) findViewById(R.id.et_corporate_name);
        et_address = (EditText) findViewById(R.id.et_address);
        et_username = (EditText) findViewById(R.id.et_username);
        et_itcard = (EditText) findViewById(R.id.et_itcard);
        iv_license = (ImageView) findViewById(R.id.iv_license);
        iv_idcard = (ImageView) findViewById(R.id.iv_idcard);
        iv_idcard_b = (ImageView) findViewById(R.id.iv_idcard_b);
        tv_submit = (TextView) findViewById(R.id.tv_submit);
//        tv_guimo = (TextView) findViewById(R.id.tv_guimo);
//        tv_xingzhi = (TextView) findViewById(R.id.tv_xingzhi);
//        tv_hangye = (TextView) findViewById(R.id.tv_hangye);

        iv_license.setOnClickListener(this);
        iv_idcard.setOnClickListener(this);
        iv_idcard_b.setOnClickListener(this);
//        tv_guimo.setOnClickListener(this);
//        tv_xingzhi.setOnClickListener(this);
//        tv_hangye.setOnClickListener(this);
        tv_submit.setOnClickListener(this);

        ViewTreeObserver vto = iv_license.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onGlobalLayout() {
                iv_license.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int width = iv_license.getMeasuredWidth();
                LinearLayout.LayoutParams layoutParams =
                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.width = width;
                layoutParams.height = (int) (436f * width / 752);
                iv_license.setLayoutParams(layoutParams);
                iv_idcard.setLayoutParams(layoutParams);
                iv_idcard_b.setLayoutParams(layoutParams);
            }
        });
    }

    private void submit() {
        // validate
        String name = et_corporate_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showTips("请输入公司名称", 1);
            return;
        }

        String address = et_address.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            showTips("请输入公司地址", 1);
            return;
        }

        String username = et_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            showTips("请输入姓名", 1);
            return;
        }

        String itcard = et_itcard.getText().toString().trim();
        if (TextUtils.isEmpty(itcard)) {
            showTips("请输入身份证号", 1);
            return;
        }

        if (itcard.length()!= 18) {
            showTips("请输入正确的身份证号", 1);
            return;
        }

        if (TextUtils.isEmpty(license_path)) {
            showTips("请上传营业执照", 1);
            return;
        }
        if (TextUtils.isEmpty(idcard_path)) {
            showTips("请上传身份证", 1);
            return;
        }
        if (TextUtils.isEmpty(idcard_path_b)) {
            showTips("请上传身份证背面", 1);
            return;
        }

        presenter.companyAuthentication(getAccessToken(), et_corporate_name.getText().toString().trim(), et_address.getText().toString().trim(),
                et_username.getText().toString().trim(), et_itcard.getText().toString().trim(), license_path, idcard_path, idcard_path_b);
    }

    @Override
    public void authenticationSuccess() {
        showTips("已提交客服审核，请耐心等待", 1);
        finish();
    }

    @Override
    public void authenticationFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void uploadPicSuccess(List<String> pic_urls) {
        if (pic_urls != null && pic_urls.size() > 0) {
            switch (currImageSelect) {
                case 1:
                    license_path = pic_urls.get(0);
                    break;
                case 2:
                    idcard_path = pic_urls.get(0);
                    break;
                case 3:
                    idcard_path_b = pic_urls.get(0);
                    break;
            }
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
                handleImage(compressPath);
            } else {
                handleImage(originalPath);
            }
        } else {
            if (TextUtils.isEmpty(compressPath) && !TextUtils.isEmpty(originalPath)) {
                handleImage(originalPath);
            } else if (!TextUtils.isEmpty(compressPath) && TextUtils.isEmpty(originalPath)) {
                handleImage(compressPath);
            }
        }
    }

    private void handleImage(String img_path) {
        ArrayList<String> pic_path = new ArrayList<>();
        switch (currImageSelect) {
            case 1:
                iv_license.setImageBitmap(BitmapFactory.decodeFile(img_path));
                pic_path.clear();
                pic_path.add(img_path);
                presenter.uploadPic(pic_path);
                break;
            case 2:
                iv_idcard.setImageBitmap(BitmapFactory.decodeFile(img_path));
                pic_path.clear();
                pic_path.add(img_path);
                presenter.uploadPic(pic_path);
                break;
            case 3:
                iv_idcard_b.setImageBitmap(BitmapFactory.decodeFile(img_path));
                pic_path.clear();
                pic_path.add(img_path);
                presenter.uploadPic(pic_path);
                break;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_license:
                currImageSelect = 1;

                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .imageSpanCount(3)// 每行显示个数 int
                        .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .isGif(false)// 是否显示gif图片 true or false
                        .enableCrop(false)
                        .withAspectRatio(1, 1)
                        .maxSelectNum(1)
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                break;
            case R.id.iv_idcard:
                currImageSelect = 2;

                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .imageSpanCount(3)// 每行显示个数 int
                        .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .isGif(false)// 是否显示gif图片 true or false
                        .enableCrop(false)
                        .withAspectRatio(1, 1)
                        .maxSelectNum(1)
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

                break;
            case R.id.iv_idcard_b:
                currImageSelect = 3;

                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .imageSpanCount(3)// 每行显示个数 int
                        .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .isGif(false)// 是否显示gif图片 true or false
                        .enableCrop(false)
                        .withAspectRatio(1, 1)
                        .maxSelectNum(1)
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

                break;
            case R.id.tv_submit:
                submit();
                break;
        }
    }
}
