package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
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
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.ApplySaleRefundActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.SpecialtyOrderDetailBean;
import com.windmillsteward.jukutech.bean.SpecialtyOrderListBean;
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
 * 售后
 * Created by Administrator on 2018/4/21 0021.
 */

public class ApplySaleRefundActivity extends BaseActivity implements ApplySaleRefundActivityView, View.OnClickListener,TakePhoto.TakeResultListener,InvokeListener {

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_order_sn;
    private TextView tv_goods_info;
    private RadioButton rb_only;
    private RadioButton rb_double;
    private RadioGroup rg_refund;
    private TextView tv_reason;
    private EditText tv_price;
    private TextView tv_number;
    private TextView tv_number_hint;
    private LinearLayout linear_num;
    private TextView tv_refund_way;
    private EditText et_user_remark;
    private ImageView iv_credentials_urls_1;
    private ImageView iv_credentials_urls_2;
    private ImageView iv_credentials_urls_3;
    private ImageView iv_delete_1;
    private ImageView iv_delete_2;
    private ImageView iv_delete_3;
    private TextView tv_apply;

    private String order_sn;
    private int order_id;
    private SpecialtyOrderDetailBean.CommodityListBean bean;

    private ApplySaleRefundActivityPresenter presenter;
    private int reason_id;
    private int number;
    private int refund_way;
    private String url_1,url_2,url_3;
    private int curr_photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_refund);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String json = extras.getString(Define.INTENT_DATA);
            order_sn = extras.getString(Define.INTENT_DATA_TWO);
            order_id = extras.getInt(Define.INTENT_DATA_THREE);
            bean = JSON.parseObject(json, SpecialtyOrderDetailBean.CommodityListBean.class);
            if (bean == null) {
                finish();
                return;
            }
        } else {
            finish();
            return;
        }

        initView();
        initToolbar();
        initData();

        presenter = new ApplySaleRefundActivityPresenter(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 同步图片选择器状态
        getTakePhoto().onActivityResult(requestCode, resultCode, intent);
        super.onActivityResult(requestCode,resultCode,intent);
    }

    public TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("申请售后");
        mImmersionBar.keyboardEnable(true).init();
    }

    private void initData() {
        tv_order_sn.setText(order_sn);
        tv_goods_info.setText(bean.getCommodity_model_name());
        tv_number_hint.setText("最多退货数量"+bean.getCommodity_num()+"件");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_order_sn = (TextView) findViewById(R.id.tv_order_sn);
        tv_goods_info = (TextView) findViewById(R.id.tv_goods_info);
        rb_only = (RadioButton) findViewById(R.id.rb_only);
        rb_double = (RadioButton) findViewById(R.id.rb_double);
        rg_refund = (RadioGroup) findViewById(R.id.rg_refund);
        tv_reason = (TextView) findViewById(R.id.tv_reason);
        tv_price = (EditText) findViewById(R.id.tv_price);
        tv_number = (TextView) findViewById(R.id.tv_number);
        tv_number_hint = (TextView) findViewById(R.id.tv_number_hint);
        linear_num = (LinearLayout) findViewById(R.id.linear_num);
        tv_refund_way = (TextView) findViewById(R.id.tv_refund_way);
        et_user_remark = (EditText) findViewById(R.id.et_user_remark);
        iv_credentials_urls_1 = (ImageView) findViewById(R.id.iv_credentials_urls_1);
        iv_credentials_urls_2 = (ImageView) findViewById(R.id.iv_credentials_urls_2);
        iv_credentials_urls_3 = (ImageView) findViewById(R.id.iv_credentials_urls_3);
        iv_delete_1 = (ImageView) findViewById(R.id.iv_delete_1);
        iv_delete_2 = (ImageView) findViewById(R.id.iv_delete_2);
        iv_delete_3 = (ImageView) findViewById(R.id.iv_delete_3);
        tv_apply = (TextView) findViewById(R.id.tv_apply);

        iv_credentials_urls_1.setOnClickListener(this);
        iv_credentials_urls_2.setOnClickListener(this);
        iv_credentials_urls_3.setOnClickListener(this);
        iv_delete_1.setOnClickListener(this);
        iv_delete_2.setOnClickListener(this);
        iv_delete_3.setOnClickListener(this);
        tv_apply.setOnClickListener(this);
        tv_reason.setOnClickListener(this);
        tv_number.setOnClickListener(this);
        tv_refund_way.setOnClickListener(this);
        linear_num.setVisibility(View.GONE);
        rg_refund.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_only:
                        linear_num.setVisibility(View.GONE);
                        break;
                    case R.id.rb_double:
                        linear_num.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    private void submit() {
        String remark = et_user_remark.getText().toString().trim();

        if (reason_id==0) {
            showTips("请选择售后原因",0);
            return;
        }
        if (refund_way==0) {
            showTips("请选择退款方式",0);
            return;
        }

//        String tv_price = this.tv_price.getText().toString().trim();
//        if (TextUtils.isEmpty(tv_price)) {
//            showTips("请输入退款金额",0);
//            return;
//        }

        ArrayList<String> urls = new ArrayList<>();
        if (!TextUtils.isEmpty(url_1)) {
            urls.add(url_1);
        }
        if (!TextUtils.isEmpty(url_2)) {
            urls.add(url_2);
        }
        if (!TextUtils.isEmpty(url_3)) {
            urls.add(url_3);
        }
        int type=1;
        int radioButtonId = rg_refund.getCheckedRadioButtonId();
        if (radioButtonId==R.id.rb_only){
            type = 1;
        } else if (radioButtonId==R.id.rb_double) {
            type = 2;
            if (number==0) {
                showTips("请选择售后商品的数量",0);
                return;
            }
        }

        presenter.apply(getAccessToken(),order_sn,bean.getOrder_commodity_id(),bean.getCommodity_id(),number,type,reason_id,refund_way,remark, urls);
    }

    @Override
    public void loadAplyReasonDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_reason.setText(text);
                        reason_id = id;
                    }
                })
                .show();
    }

    @Override
    public void loadNumberDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_number.setText(text);
                        number = id;
                    }
                })
                .show();
    }

    @Override
    public void loadRefundWayDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_refund_way.setText(text);
                        refund_way = id;
                    }
                })
                .show();
    }

    @Override
    public void uploadPicSuccess(String pic_url) {
        switch (curr_photo) {
            case 1:
                url_1 = pic_url;
                GlideUtil.show(this,pic_url,iv_credentials_urls_1);
                iv_delete_1.setVisibility(View.VISIBLE);
                break;
            case 2:
                url_2 = pic_url;
                GlideUtil.show(this,pic_url,iv_credentials_urls_2);
                iv_delete_2.setVisibility(View.VISIBLE);
                break;
            case 3:
                url_3 = pic_url;
                GlideUtil.show(this,pic_url,iv_credentials_urls_3);
                iv_delete_3.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void applySuccess() {
        Bundle bundle = new Bundle();
        bundle.putInt(Define.INTENT_DATA,order_id);
        startAtvAndFinish(SpecialtyOrderDetailActivity.class,bundle);
    }

    @Override
    public void onClick(View view) {
        SystemUtil.dismissKeyBorwd(this);
        switch (view.getId()) {
            case R.id.iv_credentials_urls_1:
                curr_photo  = 1;
                new BottomDialog(this, new BottomDialog.OnSelectListener() {
                    @Override
                    public void onTakePhoneClick() {
                        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
                        Uri imageUri = Uri.fromFile(file);
                        TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
                        builder.setWithOwnGallery(true);
                        builder.setCorrectImage(true);
                        takePhoto.setTakePhotoOptions(builder.create());

                        CompressConfig config =new CompressConfig.Builder().create();
                        takePhoto.onEnableCompress(config,true);
                        takePhoto.onPickFromCapture(imageUri);
                    }

                    @Override
                    public void onChoosePhoto() {
                        TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
                        builder.setWithOwnGallery(true);
                        builder.setCorrectImage(true);
                        takePhoto.setTakePhotoOptions(builder.create());

                        CompressConfig config =new CompressConfig.Builder().create();
                        takePhoto.onEnableCompress(config,true);
                        takePhoto.onPickFromGallery();
                    }
                });
                break;
            case R.id.iv_credentials_urls_2:
                curr_photo =2;
                new BottomDialog(this, new BottomDialog.OnSelectListener() {
                    @Override
                    public void onTakePhoneClick() {
                        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
                        Uri imageUri = Uri.fromFile(file);
                        TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
                        builder.setWithOwnGallery(true);
                        builder.setCorrectImage(true);
                        takePhoto.setTakePhotoOptions(builder.create());

                        CompressConfig config =new CompressConfig.Builder().create();
                        takePhoto.onEnableCompress(config,true);
                        takePhoto.onPickFromCapture(imageUri);
                    }

                    @Override
                    public void onChoosePhoto() {
                        TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
                        builder.setWithOwnGallery(true);
                        builder.setCorrectImage(true);
                        takePhoto.setTakePhotoOptions(builder.create());

                        CompressConfig config =new CompressConfig.Builder().create();
                        takePhoto.onEnableCompress(config,true);
                        takePhoto.onPickFromGallery();
                    }
                });
                break;
            case R.id.iv_credentials_urls_3:
                curr_photo =3;
                new BottomDialog(this, new BottomDialog.OnSelectListener() {
                    @Override
                    public void onTakePhoneClick() {
                        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
                        Uri imageUri = Uri.fromFile(file);
                        TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
                        builder.setWithOwnGallery(true);
                        builder.setCorrectImage(true);
                        takePhoto.setTakePhotoOptions(builder.create());

                        CompressConfig config =new CompressConfig.Builder().create();
                        takePhoto.onEnableCompress(config,true);
                        takePhoto.onPickFromCapture(imageUri);
                    }

                    @Override
                    public void onChoosePhoto() {
                        TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
                        builder.setWithOwnGallery(true);
                        builder.setCorrectImage(true);
                        takePhoto.setTakePhotoOptions(builder.create());

                        CompressConfig config =new CompressConfig.Builder().create();
                        takePhoto.onEnableCompress(config,true);
                        takePhoto.onPickFromGallery();
                    }
                });
                break;
            case R.id.iv_delete_1:
                url_1 = "";
                iv_credentials_urls_1.setImageResource(R.mipmap.icon_add_pic);
                iv_delete_1.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_delete_2:
                url_2 = "";
                iv_credentials_urls_2.setImageResource(R.mipmap.icon_add_pic);
                iv_delete_2.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_delete_3:
                url_3 = "";
                iv_credentials_urls_3.setImageResource(R.mipmap.icon_add_pic);
                iv_delete_3.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_apply:
                submit();
                break;
            case R.id.tv_reason:
                presenter.loadAplyReasonData();
                break;
            case R.id.tv_number:
                presenter.loadNumberData(bean.getCommodity_num());
                break;
            case R.id.tv_refund_way:
                presenter.loadRefundWayData();
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
            if (compressFile.length()<originalFile.length()) {
                presenter.uploadVehiclePic(compressPath);
            } else {
                presenter.uploadVehiclePic(originalPath);
            }
        } else {
            if (TextUtils.isEmpty(compressPath) && !TextUtils.isEmpty(originalPath)) {
                presenter.uploadVehiclePic(originalPath);
            } else if (!TextUtils.isEmpty(compressPath) && TextUtils.isEmpty(originalPath)) {
                presenter.uploadVehiclePic(compressPath);
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
