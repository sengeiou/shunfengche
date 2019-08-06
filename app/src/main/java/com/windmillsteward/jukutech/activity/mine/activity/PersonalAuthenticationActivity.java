package com.windmillsteward.jukutech.activity.mine.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
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

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.presenter.AuthenticationPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.dialog.SelectTwoDialog;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：个人认证
 * author:cyq
 * 2018-03-09
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class PersonalAuthenticationActivity extends BaseActivity implements View.OnClickListener, AuthenticationView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private EditText et_name;
    private EditText et_id_no;
    private ImageView iv_front_side;
    private ImageView iv_reverse_side;
    private TextView btn_commit;

    private AuthenticationPresenter presenter;

    private SelectTwoDialog selectTwoDialog;//图片选择弹窗

    private Uri existUri;//指定路径uri
    private String img_url;// 图片上传成功后,后台返回来的图片URL
    private String img_sdcard;// 图片在本地SDCARD的路径

    private int type;//1正面2反面
    private String front_url = "";//身份证正面
    private String reverse_url = "";//身份证反面
    private List<String> front_side_listPic;
    private List<String> reverse_side_listPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_authentication);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        et_name = (EditText) findViewById(R.id.et_name);
        et_id_no = (EditText) findViewById(R.id.et_id_no);
        iv_front_side = (ImageView) findViewById(R.id.iv_front_side);
        iv_reverse_side = (ImageView) findViewById(R.id.iv_reverse_side);
        btn_commit = (TextView) findViewById(R.id.btn_commit);

        btn_commit.setOnClickListener(this);
        iv_front_side.setOnClickListener(this);
        iv_reverse_side.setOnClickListener(this);
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("个人认证");

        ViewTreeObserver vto = iv_front_side.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onGlobalLayout() {
                iv_front_side.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int width = iv_front_side.getMeasuredWidth();
                LinearLayout.LayoutParams layoutParams =
                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.width = width;
                layoutParams.height = (int) (436f * width / 752);
                iv_front_side.setLayoutParams(layoutParams);
                iv_reverse_side.setLayoutParams(layoutParams);
            }
        });
    }

    private void initData() {
        presenter = new AuthenticationPresenter(this);
        front_side_listPic = new ArrayList<>();
        reverse_side_listPic = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                String name = et_name.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    showTips("请输入真实姓名", 1);
                    return;
                }
                String id_card = et_id_no.getText().toString();
                if (TextUtils.isEmpty(id_card)) {
                    showTips("请输入身份证号", 1);
                    return;
                }
                if (id_card.length() != 18) {
                    showTips("请输入正确的身份证号", 1);
                    return;
                }
//                IdCardUtil idCardUtil = new IdCardUtil();
//                String tosat = idCardUtil.IDCardValidate(id_card);
//                if (!tosat.equals("")) {//为空表示校验成功,不为空表示校验失败，不通过
//                    showTips(tosat, 1);
//                    return;
//                }
                if (TextUtils.isEmpty(front_url)) {
                    showTips("请上传身份证正面", 1);
                    return;
                }
                if (TextUtils.isEmpty(reverse_url)) {
                    showTips("请上传身份证反面", 1);
                    return;
                }
                presenter.personalAuthentication(getAccessToken(), name, id_card, front_url, reverse_url);
                break;
            case R.id.iv_front_side:
                type = 1;
                openXiangce();
                break;
            case R.id.iv_reverse_side:
                type = 2;
                openXiangce();
                break;
        }
    }

    /**
     * 打开相册
     */
    public void openXiangce() {
        List<LocalMedia> selectList = new ArrayList<>();
        if (type == 1) {
            for (String s : front_side_listPic) {
                if (s != null) {
                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setChecked(true);
                    localMedia.setMimeType(PictureMimeType.ofImage());
                    localMedia.setPath(s);
                    selectList.add(localMedia);
                }
            }
        } else if (type == 2) {
            for (String s : reverse_side_listPic) {
                if (s != null) {
                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setChecked(true);
                    localMedia.setMimeType(PictureMimeType.ofImage());
                    localMedia.setPath(s);
                    selectList.add(localMedia);
                }
            }
        }
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
                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
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
        String pic_str = pic_urls.get(0);
        if (type == 1) {
            this.front_url = pic_str;
            GlideUtil.show(this, pic_str, iv_front_side, R.mipmap.icon_id_b, R.mipmap.icon_id);
        } else if (type == 2) {
            this.reverse_url = pic_str;
            GlideUtil.show(this, pic_str, iv_reverse_side, R.mipmap.icon_id_b, R.mipmap.icon_id_b);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            if (type == 1){
                front_side_listPic.clear();
            }else if (type == 2){
                reverse_side_listPic.clear();
            }
            if (!selectList.isEmpty()) {
                List<String> selected_list = new ArrayList<>();
                for (int i = selectList.size() - 1; i >= 0; i--) {
                    LocalMedia localMedia = selectList.get(i);
                    String currImageUrl = localMedia.getPath();
                    if (type == 1) {
                        //正面
                        selected_list.add(0, currImageUrl);
                        front_side_listPic.add(0, currImageUrl);
                    } else {
                        //反面
                        selected_list.add(0, currImageUrl);
                        reverse_side_listPic.add(0, currImageUrl);
                    }
                }
                showDialog("正在上传...");

                //上传图片
                addCall(new NetUtil().setPic_path(selected_list)
                        .buildUploadFile(new NetUtil.OnPicsUploadSuccessListener() {
                            @Override
                            public void onPicsUploadSuccess(List<String> pic_urls) {
                                dismiss();
                                if (pic_urls.size() != 0 && pic_urls != null){
                                    if (type == 1){
                                      front_url  = pic_urls.get(0);
                                        GlideUtil.show(PersonalAuthenticationActivity.this, front_url, iv_front_side, R.mipmap.icon_id_b, R.mipmap.icon_id);
                                    }else if (type ==2 ){
                                        reverse_url  = pic_urls.get(0);
                                        GlideUtil.show(PersonalAuthenticationActivity.this, reverse_url, iv_reverse_side, R.mipmap.icon_id_b, R.mipmap.icon_id_b);
                                    }
                                }
                            }

                            @Override
                            public void onPicsUploadFail(String msg) {
                                dismiss();
                                showTips(msg);
                            }
                        }));
            }
        }
    }

}
