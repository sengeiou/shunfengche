package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishSuccessActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.presenter.PublishHotelAndHousePresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.HotelConfigsBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.BottomDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.customview.flowlayout.TagAdapter;
import com.windmillsteward.jukutech.customview.flowlayout.TagFlowLayout;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述：发布房源
 * 时间：2018/1/29
 * 作者：xjh
 */

public class PublishHouseActivity extends BaseActivity implements View.OnClickListener, PublishHotelAndHouseView, TakePhoto.TakeResultListener,InvokeListener {

    private static final int REQUEST_CODE_IMAGE = 100;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private ImageView iv_pic;
    private TextView tv_click_upload;
    private EditText et_house_name;
    private TextView tv_house_type;
    private TextView tv_start_time;
    private EditText et_customer_phone;
    private TextView tv_area;
    private EditText et_address;
    private TagFlowLayout tfl_common;
    private TagFlowLayout tfl_custom;
    private TagFlowLayout tfl_activity;
    private TagFlowLayout tfl_hotel_service;
    private TextView tv_leave_time;
    private TextView tv_food_plan;
    private TextView tv_pet;
    private TextView tv_reserve;
    private EditText et_house_desc;
    private EditText et_username;
    private EditText et_phone;
    private ImageView iv_license;
    private ImageView iv_idcard;
    private ImageView iv_idcard_b;
    private TextView tv_publish_area;
    private TextView tv_publish;

    private List<String> pic_urls;
    private String 	hotel_name;
    private int hotel_type;
    private String opening_date;
    private String customer_service_phone;
    private int third_area_id;
    private String address;
    private List<Integer> public_facility_ids=new ArrayList<>();
    private List<Integer> room_facility_ids=new ArrayList<>();
    private List<Integer> activity_facility_ids = new ArrayList<>();
    private List<Integer> service_ids = new ArrayList<>();
    private String leave_time;
    private int is_food_supply;
    private int can_take_pet;
    private int is_collection_deposit;
    private String description;
    private String principal_name;
    private String principal_mobile_phone;
    private String business_license_url;
    private String legal_person_id_card_front_url;
    private String legal_person_id_card_back_url;
    private int publish_area_id;

    private PublishHotelAndHousePresenter presenter;
    private ArrayList<String> imgs = new ArrayList<>();
    private int currImage;
    private String business_license_path;
    private String legal_person_id_card_front;
    private String legal_person_id_card_back;

    private HotelConfigsBean hotelConfigsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishhouse);

        initView();
        initToolbar();
        initData();

        presenter = new PublishHotelAndHousePresenter(this);
        presenter.loadConfigsData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 同步图片选择器状态
        getTakePhoto().onActivityResult(requestCode, resultCode, intent);
        super.onActivityResult(requestCode,resultCode,intent);

        if (requestCode==REQUEST_CODE_IMAGE&&resultCode == SelectPhotoActivity.RESULT_CODE) {
            ArrayList<String> imageList = intent.getStringArrayListExtra(SelectPhotoActivity.RESULT_DATA);
            if (imageList!=null && imageList.size()>0) {
                imgs.clear();
                imgs.addAll(imageList);
                tv_click_upload.setText("已选择"+imageList.size()+"张图片");
            } else {
                imgs.clear();
                tv_click_upload.setText("点击上传图片");
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }

    public TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }

    private void initData() {


    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("房源出租");
        mImmersionBar.keyboardEnable(true).init();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        tv_click_upload = (TextView) findViewById(R.id.tv_click_upload);
        et_house_name = (EditText) findViewById(R.id.et_house_name);
        tv_house_type = (TextView) findViewById(R.id.tv_house_type);
        tv_start_time = (TextView) findViewById(R.id.tv_start_time);
        et_customer_phone = (EditText) findViewById(R.id.et_customer_phone);
        tv_area = (TextView) findViewById(R.id.tv_area);
        et_address = (EditText) findViewById(R.id.et_address);
        tfl_common = (TagFlowLayout) findViewById(R.id.tfl_common);
        tfl_custom = (TagFlowLayout) findViewById(R.id.tfl_custom);
        tfl_activity = (TagFlowLayout) findViewById(R.id.tfl_activity);
        tfl_hotel_service = (TagFlowLayout) findViewById(R.id.tfl_hotel_service);
        tv_leave_time = (TextView) findViewById(R.id.tv_leave_time);
        tv_food_plan = (TextView) findViewById(R.id.tv_food_plan);
        tv_pet = (TextView) findViewById(R.id.tv_pet);
        tv_reserve = (TextView) findViewById(R.id.tv_reserve);
        et_house_desc = (EditText) findViewById(R.id.et_house_desc);
        et_username = (EditText) findViewById(R.id.et_username);
        et_phone = (EditText) findViewById(R.id.et_phone);
        iv_license = (ImageView) findViewById(R.id.iv_license);
        iv_idcard = (ImageView) findViewById(R.id.iv_idcard);
        iv_idcard_b = (ImageView) findViewById(R.id.iv_idcard_b);
        tv_publish_area = (TextView) findViewById(R.id.tv_publish_area);
        tv_publish = (TextView) findViewById(R.id.tv_publish);

        iv_pic.setOnClickListener(this);
        tv_house_type.setOnClickListener(this);
        tv_start_time.setOnClickListener(this);
        tv_area.setOnClickListener(this);
        tv_leave_time.setOnClickListener(this);
        tv_food_plan.setOnClickListener(this);
        tv_pet.setOnClickListener(this);
        tv_reserve.setOnClickListener(this);
        iv_license.setOnClickListener(this);
        iv_idcard.setOnClickListener(this);
        iv_idcard_b.setOnClickListener(this);
        tv_publish_area.setOnClickListener(this);
        tv_publish.setOnClickListener(this);
    }

    private void submit() {
        hotel_name = et_house_name.getText().toString().trim();
        if (TextUtils.isEmpty(hotel_name)) {
            showTips("请输入房源名称",0);
            return;
        }

        customer_service_phone = et_customer_phone.getText().toString().trim();
        if (TextUtils.isEmpty(customer_service_phone)) {
            showTips("请输入客服电话",0);
            return;
        }

        address = et_address.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            showTips("请输入详细地址",0);
            return;
        }

        description = et_house_desc.getText().toString().trim();
        if (TextUtils.isEmpty(description)) {
            showTips("请输入房源介绍",0);
            return;
        }

        principal_name = et_username.getText().toString().trim();
        if (TextUtils.isEmpty(principal_name)) {
            showTips("请输入负责人",0);
            return;
        }

        principal_mobile_phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(principal_mobile_phone)) {
            showTips("请输入手机号",0);
            return;
        }
        if (imgs==null) {
            showTips("请选择图片",0);
            return;
        }
        if (hotel_type==0) {
            showTips("请选择星级",0);
            return;
        }
        if (TextUtils.isEmpty(opening_date)) {
            showTips("请选择开业时间",0);
            return;
        }
        if (third_area_id==0) {
            showTips("请选择区域",0);
            return;
        }
        // 这里不盘读，可能会什么都没有的
//        if (tfl_common.getSelectedList().size()==0) {
//            showTips("请选择公共设施",0);
//        }
        Set<Integer> selectedList = tfl_common.getSelectedList();
        for (Integer integer : selectedList) {
            if (hotelConfigsBean!=null) {
                List<HotelConfigsBean.PublicFacilityListBean> public_facility_list = hotelConfigsBean.getPublic_facility_list();
                if (public_facility_list!=null) {
                    public_facility_ids.add(public_facility_list.get(integer).getId());
                }
            }
        }
        Set<Integer> tfl_customSelectedList = tfl_custom.getSelectedList();
        for (Integer integer : tfl_customSelectedList) {
            if (hotelConfigsBean!=null) {
                List<HotelConfigsBean.RoomFacilityListBean> public_facility_list = hotelConfigsBean.getRoom_facility_list();
                if (public_facility_list!=null) {
                    room_facility_ids.add(public_facility_list.get(integer).getId());
                }
            }
        }
        Set<Integer> tfl_activitySelectedList = tfl_activity.getSelectedList();
        for (Integer integer : tfl_activitySelectedList) {
            if (hotelConfigsBean!=null) {
                List<HotelConfigsBean.ActivityFacilityListBean> public_facility_list = hotelConfigsBean.getActivity_facility_list();
                if (public_facility_list!=null) {
                    activity_facility_ids.add(public_facility_list.get(integer).getId());
                }
            }
        }
        Set<Integer> tfl_hotel_serviceSelectedList = tfl_hotel_service.getSelectedList();
        for (Integer integer : tfl_hotel_serviceSelectedList) {
            if (hotelConfigsBean!=null) {
                List<HotelConfigsBean.ServiceListBean> public_facility_list = hotelConfigsBean.getService_list();
                if (public_facility_list!=null) {
                    service_ids.add(public_facility_list.get(integer).getId());
                }
            }
        }

        if (TextUtils.isEmpty(leave_time)) {
            showTips("请选择离店时间",0);
            return;
        }
        if (TextUtils.isEmpty(business_license_path)) {
            showTips("请上传营业执照",0);
            return;
        }
        if (TextUtils.isEmpty(legal_person_id_card_front)) {
            showTips("请上传身份证",0);
            return;
        }
        if (TextUtils.isEmpty(legal_person_id_card_back)) {
            showTips("请上传身份证",0);
            return;
        }
        if (publish_area_id==0) {
            showTips("请选择发布地址",0);
            return;
        }
        presenter.uploadPicFile(imgs);
    }

    @Override
    public void onClick(View v) {
        SystemUtil.dismissKeyBorwd(this);
        switch (v.getId()) {
            case R.id.iv_pic:
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(SelectPhotoActivity.INIT_DATA,imgs);
                startAtvDonFinishForResult(SelectPhotoActivity.class,REQUEST_CODE_IMAGE,bundle);
                break;
            case R.id.tv_house_type:  // 星级
                presenter.loadHoseTypeData(2);
                break;
            case R.id.tv_start_time:  // 开业时间
                TimePickerView start = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        opening_date = DateUtil.getYY_MM_DD(date,"yyyy");
                        tv_start_time.setText(opening_date);
                    }
                })
                        .setType(new boolean[]{true, false, false, false, false, false})
                        .build();
                start.setDate(Calendar.getInstance());
                start.show();
                break;
            case R.id.tv_area:  // 酒店区域
                presenter.loadAreaData(getCurrCityId());
                break;
            case R.id.tv_leave_time:  // 离店时间
                TimePickerView leave = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        leave_time = DateUtil.getYY_MM_DD(date,"HH:mm");
                        tv_leave_time.setText(leave_time);
                    }
                })
                        .setType(new boolean[]{false, false, false, true, true, false})
                        .build();
                leave.setDate(Calendar.getInstance());
                leave.show();
                break;
            case R.id.tv_food_plan:  // 膳食安排
                presenter.loadFoodData();
                break;
            case R.id.tv_pet:  // 是否可以携带宠物
                presenter.loadPetData();
                break;
            case R.id.tv_reserve:  // 预定房间
                presenter.loadCollectionDepositData();
                break;
            case R.id.iv_license:  // 选择营业执照
                new BottomDialog(PublishHouseActivity.this, new BottomDialog.OnSelectListener() {
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
                currImage = 0;
                break;
            case R.id.iv_idcard:  // 选择身份证
                new BottomDialog(PublishHouseActivity.this, new BottomDialog.OnSelectListener() {
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
                currImage = 1;
                break;
            case R.id.iv_idcard_b:  // 身份证背面
                new BottomDialog(PublishHouseActivity.this, new BottomDialog.OnSelectListener() {
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
                currImage = 2;
                break;
            case R.id.tv_publish_area:  // 发布地区
                presenter.loadPublishAreaData(getCurrCityId());
                break;
            case R.id.tv_publish:  // 发布
                submit();
                break;
        }
    }


    @Override
    public void loadHouseTypeDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_house_type.setText(text);
                        hotel_type = id;
                    }
                })
                .show();
    }

    @Override
    public void loadAreaDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_area.setText(text);
                        third_area_id = id;
                    }
                })
                .show();
    }

    @Override
    public void loadFoodSupplyDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_food_plan.setText(text);
                        is_food_supply = id;
                    }
                })
                .show();
    }

    @Override
    public void loadTakePetDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_pet.setText(text);
                        can_take_pet = id;
                    }
                })
                .show();
    }

    @Override
    public void loadCollectionDepositDataSucces(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_reserve.setText(text);
                        is_collection_deposit = id;
                    }
                })
                .show();
    }

    @Override
    public void loadPublishAreaDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_publish_area.setText(text);
                        publish_area_id = id;
                    }
                })
                .show();
    }

    @Override
    public void uploadPicSuccess(List<String> pic_urls) {
        if (pic_urls!=null) {
            this.pic_urls = pic_urls;
            presenter.uploadLicense(business_license_path);
        } else {
            dismiss();
        }
    }

    @Override
    public void uploadLicenseSuccess(List<String> license_url) {
       if (license_url!=null&&license_url.size()>0) {
           business_license_url = license_url.get(0);
           presenter.uploadIdCard(legal_person_id_card_front);
       } else {
           dismiss();
       }
    }

    @Override
    public void uploadIdCardSuccess(List<String> idCard_url) {
        if (idCard_url!=null && idCard_url.size()>0) {
            legal_person_id_card_front_url = idCard_url.get(0);
            presenter.uploadIdCard_b(legal_person_id_card_back);
        } else {
            dismiss();
        }
    }

    @Override
    public void uploadIdCardSuccess_b(List<String> idCard_url_b) {
        if (idCard_url_b!=null && idCard_url_b.size()>0) {
            legal_person_id_card_back_url = idCard_url_b.get(0);

            presenter.publish(getAccessToken(),2,pic_urls,hotel_name,hotel_type,opening_date,customer_service_phone,getCurrCityId(),third_area_id,
                    address,public_facility_ids,room_facility_ids,activity_facility_ids,service_ids,leave_time,is_food_supply,can_take_pet,is_collection_deposit,
                    description,principal_name,principal_mobile_phone,business_license_url,legal_person_id_card_front_url,legal_person_id_card_back_url,publish_area_id);
        } else {
            dismiss();
        }
    }

    @Override
    public void publishSuccess(String msg) {
        PublishSuccessActivity.go(this,6);
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {


    }

    @Override
    public void loadHotelConfigsResuccess(HotelConfigsBean bean) {
        final LayoutInflater inflater = LayoutInflater.from(this);
        hotelConfigsBean = bean;
        List<HotelConfigsBean.PublicFacilityListBean> public_facility_list = bean.getPublic_facility_list();
        if (public_facility_list!=null) {
            tfl_common.setAdapter(new TagAdapter<HotelConfigsBean.PublicFacilityListBean>(public_facility_list) {
                @Override
                public View getView(FlowLayout parent, int position, HotelConfigsBean.PublicFacilityListBean s) {
                    TextView text = (TextView) inflater.inflate(R.layout.item_textview_publish_hotelandhouse, parent,false);
                    text.setText(s.getName());
                    return text;
                }
            });
        }
        List<HotelConfigsBean.RoomFacilityListBean> room_facility_list = bean.getRoom_facility_list();
        if (room_facility_list!=null) {
            tfl_custom.setAdapter(new TagAdapter<HotelConfigsBean.RoomFacilityListBean>(room_facility_list) {
                @Override
                public View getView(FlowLayout parent, int position, HotelConfigsBean.RoomFacilityListBean s) {
                    TextView text = (TextView) inflater.inflate(R.layout.item_textview_publish_hotelandhouse, parent,false);
                    text.setText(s.getName());
                    return text;
                }
            });
        }

        List<HotelConfigsBean.ActivityFacilityListBean> activity_facility_list = bean.getActivity_facility_list();
        if (activity_facility_list!=null) {
            tfl_activity.setAdapter(new TagAdapter<HotelConfigsBean.ActivityFacilityListBean>(activity_facility_list) {
                @Override
                public View getView(FlowLayout parent, int position, HotelConfigsBean.ActivityFacilityListBean s) {
                    TextView text = (TextView) inflater.inflate(R.layout.item_textview_publish_hotelandhouse, parent,false);
                    text.setText(s.getName());
                    return text;
                }
            });
        }

        List<HotelConfigsBean.ServiceListBean> service_list = bean.getService_list();
        if (service_list!=null) {
            tfl_hotel_service.setAdapter(new TagAdapter<HotelConfigsBean.ServiceListBean>(service_list) {
                @Override
                public View getView(FlowLayout parent, int position, HotelConfigsBean.ServiceListBean s) {
                    TextView text = (TextView) inflater.inflate(R.layout.item_textview_publish_hotelandhouse, parent,false);
                    text.setText(s.getName());
                    return text;
                }
            });
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
                if (currImage==0) {
                    business_license_path = compressPath;
                    iv_license.setImageBitmap(BitmapFactory.decodeFile(compressPath));
                } else if (currImage == 1) {
                    legal_person_id_card_front = compressPath;
                    iv_idcard.setImageBitmap(BitmapFactory.decodeFile(compressPath));
                } else if (currImage == 2) {
                    legal_person_id_card_back = compressPath;
                    iv_idcard_b.setImageBitmap(BitmapFactory.decodeFile(compressPath));
                }
            } else {
                if (currImage==0) {
                    business_license_path = originalPath;
                    iv_license.setImageBitmap(BitmapFactory.decodeFile(originalPath));
                } else if (currImage == 1) {
                    legal_person_id_card_front = originalPath;
                    iv_idcard.setImageBitmap(BitmapFactory.decodeFile(originalPath));
                } else if (currImage == 2) {
                    legal_person_id_card_back = originalPath;
                    iv_idcard_b.setImageBitmap(BitmapFactory.decodeFile(originalPath));
                }
            }
        } else {
            if (TextUtils.isEmpty(compressPath) && !TextUtils.isEmpty(originalPath)) {
                if (currImage==0) {
                    business_license_path = originalPath;
                    iv_license.setImageBitmap(BitmapFactory.decodeFile(originalPath));
                } else if (currImage == 1) {
                    legal_person_id_card_front = originalPath;
                    iv_idcard.setImageBitmap(BitmapFactory.decodeFile(originalPath));
                } else if (currImage == 2) {
                    legal_person_id_card_back = originalPath;
                    iv_idcard_b.setImageBitmap(BitmapFactory.decodeFile(originalPath));
                }
            } else if (!TextUtils.isEmpty(compressPath) && TextUtils.isEmpty(originalPath)) {
                if (currImage==0) {
                    business_license_path = compressPath;
                    iv_license.setImageBitmap(BitmapFactory.decodeFile(compressPath));
                } else if (currImage == 1) {
                    legal_person_id_card_front = compressPath;
                    iv_idcard.setImageBitmap(BitmapFactory.decodeFile(compressPath));
                } else if (currImage == 2) {
                    legal_person_id_card_back = compressPath;
                    iv_idcard_b.setImageBitmap(BitmapFactory.decodeFile(compressPath));
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
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }
}
