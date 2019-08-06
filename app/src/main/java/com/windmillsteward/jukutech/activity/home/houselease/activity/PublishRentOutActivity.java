package com.windmillsteward.jukutech.activity.home.houselease.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.windmillsteward.jukutech.activity.home.commons.quickindex.QuickIndexAreaActivity;
import com.windmillsteward.jukutech.activity.home.commons.quickindex.QuickIndexStreetActivity;
import com.windmillsteward.jukutech.activity.home.houselease.presenter.PublishRentOutPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishSuccessActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.SelectPhotoActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.HouseConfigBean;
import com.windmillsteward.jukutech.bean.HouseDetailBeam;
import com.windmillsteward.jukutech.bean.HouseMoreBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.BottomDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.customview.flowlayout.TagAdapter;
import com.windmillsteward.jukutech.customview.flowlayout.TagFlowLayout;
import com.windmillsteward.jukutech.utils.StaticData;
import com.windmillsteward.jukutech.utils.SystemUtil;

import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述：发布出租
 * 时间：2018/2/2
 * 作者：xjh
 */

public class PublishRentOutActivity extends BaseActivity implements PublishRentOutView, TakePhoto.TakeResultListener,InvokeListener, View.OnClickListener {

    private static final int REQUEST_CODE_IMAGE = 100;
    private static final int REQUEST_CODE_ADDRESS = 101;
    private static final int REQUEST_CODE_DESC = 102;
    public static final String TYPE = "TYPE";
    public static final String DATA = "DATA";

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private ImageView iv_pic;
    private TextView tv_click_upload;
    private EditText et_house_name;
    private TextView tv_house_address;
    private EditText et_house_num;
    private EditText et_hall_num;
    private EditText et_floor_num;
    private EditText et_house_area;
    private EditText et_price;
    private TextView tv_cx;
    private TextView tv_fixture;
    private TextView tv_house_type;
    private TextView tv_property_right;
    private EditText et_title;
    private TextView tv_house_desc;
    private EditText et_contacts;
    private EditText et_phone;
    private TagFlowLayout tfl_config;
    private ImageView iv_add_pic;
    private TextView tv_publish_area;
    private TextView tv_publish;

    private PublishRentOutPresenter presenter;

    private ArrayList<String> pic_path = new ArrayList<>();
    private int house_third_id;
    private int house_fourth_id;
    private String description;
    private int orientation=1;
    private int decoration=1;
    private int rent_deposit_type=1;
    private int house_type=-1;
    private int third_area_id;
    private String property_cert_pic_path;
    private List<String> pic_url;
    private String property_cert_pic_url;
    private String house_rooms;
    private String house_parlor;
    private String floor;
    private String total_price;
    private String title;
    private String contact_person;
    private String contact_mobile_phone;
    private String floor_area;
    private String community_name;
    private int property_right=0;
    private String developers_name=null;
    private List<Integer> house_config_ids = new ArrayList<>();
    private List<HouseConfigBean> houseConfigBeans;
    private int school_degree_type=0;

    private int type;
    private HouseDetailBeam beam;
    private String[] house_config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishrentout);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            type = extras.getInt(TYPE);
            beam = (HouseDetailBeam) extras.getSerializable(DATA);
        }
        initView();
        initToolbar();
        initHouseConfig();
        presenter = new PublishRentOutPresenter(this);
        presenter.loadHouseConfig();
    }

    private void initHouseConfig() {
        house_config = getResources().getStringArray(R.array.houseConfig);
        final LayoutInflater inflater = LayoutInflater.from(this);
        tfl_config.setAdapter(new TagAdapter<String>(house_config) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView text = (TextView) inflater.inflate(R.layout.item_textview_publish_hotelandhouse, parent,false);
                text.setText(s);
                return text;
            }
        });
    }

    private void initData() {
//        presenter.loadRentTypeDataF();
//
//        String[] cx = StaticData.getOrientation_text();
//        String[] fixture = StaticData.getDecoration_text();
//        String[] rent_deposit = StaticData.getRent_deposit_text();
//        List<String> pic_urls = beam.getPic_urls();
//        tv_click_upload.setText("已上传"+pic_urls.size()+"张图片");
//        pic_path.addAll(pic_urls);
//        et_house_name.setText(beam.getCommunity_name());
//        tv_house_address.setText(beam.getHouse_third_name()+beam.getHouse_fourth_name());
//        house_third_id = beam.getHouse_third_id();
//        house_fourth_id = beam.getHouse_fourth_id();
//        et_house_num.setText(String.valueOf(beam.getHouse_rooms_num()));
//        et_hall_num.setText(String.valueOf(beam.getHouse_parlor_num()));
//        et_floor_num.setText(String.valueOf(beam.getFloor()));
//        et_house_area.setText(String.valueOf(beam.getFloor_area()));
//        et_price.setText(beam.getTotal_price());
//        tv_cx.setText(cx[beam.getOrientation()-1]);
//        orientation = beam.getOrientation();
//        tv_fixture.setText(fixture[beam.getDecoration()-1]);
//        decoration = beam.getDecoration();
//        tv_house_type.setText(rent_deposit[beam.getRent_deposit_type()-1]);
//        rent_deposit_type = beam.getRent_deposit_type();  // 押金
//        et_title.setText(beam.getTitle());
//        tv_house_desc.setText(beam.getDescription());
//        description = beam.getDescription();
//        et_contacts.setText(beam.getContact_person());
//        et_phone.setText(beam.getContact_mobile_phone());
//
//        TagAdapter adapter = tfl_config.getAdapter();
//        if (adapter!=null) {
//            List<HouseDetailBeam.HouseConfigIdsBean> house_config_ids = beam.getHouse_config_ids();
//            Set<Integer> set = new HashSet<>();
//
//            for (int i = 0; i < houseConfigBeans.size(); i++) {
//                for (HouseDetailBeam.HouseConfigIdsBean house_config_id : house_config_ids) {
//                    if (houseConfigBeans.get(i).getFacility_id()==house_config_id.getFacility_id()) {
//                        set.add(i);
//                        this.house_config_ids.add(i);
//                    }
//                }
//            }
//            adapter.setSelectedList(set);
//        }
//        x.image().bind(iv_add_pic,beam.getProperty_cert_pic_url());
//        property_cert_pic_path = beam.getProperty_cert_pic_url();
//        tv_publish_area.setText(beam.getThird_area_name());
//        third_area_id = beam.getThird_area_id();
//        tv_publish.setText("保存");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 同步图片选择器状态
        getTakePhoto().onActivityResult(requestCode, resultCode, intent);
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode==REQUEST_CODE_IMAGE&&resultCode == SelectPhotoActivity.RESULT_CODE) {
            ArrayList<String> imageList = intent.getStringArrayListExtra(SelectPhotoActivity.RESULT_DATA);
            if (imageList!=null && imageList.size()>0) {
                pic_path.clear();
                pic_path.addAll(imageList);
                tv_click_upload.setText("已选择"+imageList.size()+"张图片");
            } else {
                pic_path.clear();
                tv_click_upload.setText("点击上传图片");
            }
        }  else if (requestCode==REQUEST_CODE_ADDRESS&&resultCode== QuickIndexStreetActivity.RESULT_CODE) {
            Bundle extras = intent.getExtras();
            if (extras!=null) {
                house_third_id = extras.getInt(QuickIndexStreetActivity.THIRD_ID);
                house_fourth_id = extras.getInt(QuickIndexStreetActivity.STREET_ID);
                String third_name = extras.getString(QuickIndexStreetActivity.THIRD_NAME);
                String street_name = extras.getString(QuickIndexStreetActivity.STREET_NAME);

                tv_house_address.setText(third_name+street_name);
            }
        } else if (requestCode == REQUEST_CODE_DESC&& resultCode == HouseDescActivity.RESULT_CODE) {
            Bundle extras = intent.getExtras();
            if (extras!=null) {
                description = extras.getString(HouseDescActivity.RESULT_DATA);
                tv_house_desc.setText(description);
            }
        }
//        else if (requestCode==PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            presenter.uploadPic(pic_path );
//        }
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

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("出租");
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
        tv_house_address = (TextView) findViewById(R.id.tv_house_address);
        et_house_num = (EditText) findViewById(R.id.et_house_num);
        et_hall_num = (EditText) findViewById(R.id.et_hall_num);
        et_floor_num = (EditText) findViewById(R.id.et_floor_num);
        et_house_area = (EditText) findViewById(R.id.et_house_area);
        et_price = (EditText) findViewById(R.id.et_price);
        tv_cx = (TextView) findViewById(R.id.tv_cx);
        tv_fixture = (TextView) findViewById(R.id.tv_fixture);
        tv_house_type = (TextView) findViewById(R.id.tv_house_type);
        tv_property_right = (TextView) findViewById(R.id.tv_property_right);
        et_title = (EditText) findViewById(R.id.et_title);
        tv_house_desc = (TextView) findViewById(R.id.tv_house_desc);
        et_contacts = (EditText) findViewById(R.id.et_contacts);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tfl_config = (TagFlowLayout) findViewById(R.id.tfl_config);
        iv_add_pic = (ImageView) findViewById(R.id.iv_add_pic);
        tv_publish_area = (TextView) findViewById(R.id.tv_publish_area);
        tv_publish = (TextView) findViewById(R.id.tv_publish);

        iv_pic.setOnClickListener(this);
        tv_house_address.setOnClickListener(this);
        tv_house_desc.setOnClickListener(this);
        tv_cx.setOnClickListener(this);
        tv_fixture.setOnClickListener(this);
        tv_house_type.setOnClickListener(this);
        tv_property_right.setOnClickListener(this);
        tv_publish_area.setOnClickListener(this);
        iv_add_pic.setOnClickListener(this);
        tv_publish.setOnClickListener(this);
    }

    private void submit() {
        community_name = et_house_name.getText().toString().trim();

        house_rooms = et_house_num.getText().toString().trim();
        if (TextUtils.isEmpty(house_rooms)) {
            showTips("请输入房间数",0);
            return;
        }

        house_parlor = et_hall_num.getText().toString().trim();
        if (TextUtils.isEmpty(house_parlor)) {
            showTips("请输入客厅数",0);
            return;
        }

        floor = et_floor_num.getText().toString().trim();
        if (TextUtils.isEmpty(floor)) {
            showTips("请输入楼层数",0);
            return;
        }

        floor_area = et_house_area.getText().toString().trim();
        if (TextUtils.isEmpty(floor_area)) {
            showTips("请输入面积",0);
            return;
        }

        total_price = et_price.getText().toString().trim();
        if (TextUtils.isEmpty(total_price)) {
            showTips("请输入价格",0);
            return;
        }

        if (house_type==-1) {
            showTips("请选择类型",0);
            return;
        }

        title = et_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showTips("请输入标题",0);
            return;
        }

        if (title.length()<8){
            showTips("标题长度在8-28字之间",0);
            return;
        }

        contact_person = et_contacts.getText().toString().trim();
        if (TextUtils.isEmpty(contact_person)) {
            showTips("请输入联系人",0);
            return;
        }

        contact_mobile_phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(contact_mobile_phone)) {
            showTips("请输入手机号",0);
            return;
        }
        Set<Integer> selectedList = tfl_config.getSelectedList();
        house_config_ids.clear();
        for (Integer integer : selectedList) {
            house_config_ids.add(houseConfigBeans.get(integer).getFacility_id());
        }

        if (house_config_ids.isEmpty()) {
            showTips("请选择配置",0);
            return;
        }

        if (pic_path.isEmpty()) {
            showTips("请上传图片",0);
            return;
        }

//        if (TextUtils.isEmpty(property_cert_pic_path)) {
//            showTips("请上传房产证",0);
//            return;
//        }

        if (house_fourth_id==0) {
            showTips("请选择房屋地址",0);
            return;
        }
        if (third_area_id==0) {
            showTips("请选择发布地区",0);
            return;
        }

        if (type==0) {
            presenter.isCharge(getAccessToken(),0);
        } else {
            presenter.uploadPic(pic_path);
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
                property_cert_pic_path = compressPath;
                iv_add_pic.setImageBitmap(BitmapFactory.decodeFile(compressPath));
            } else {
                property_cert_pic_path = originalPath;
                iv_add_pic.setImageBitmap(BitmapFactory.decodeFile(originalPath));
            }
        } else {
            if (TextUtils.isEmpty(compressPath) && !TextUtils.isEmpty(originalPath)) {
                property_cert_pic_path = originalPath;
                iv_add_pic.setImageBitmap(BitmapFactory.decodeFile(originalPath));
            } else if (!TextUtils.isEmpty(compressPath) && TextUtils.isEmpty(originalPath)) {
                property_cert_pic_path = compressPath;
                iv_add_pic.setImageBitmap(BitmapFactory.decodeFile(compressPath));
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

    @Override
    public void onClick(View v) {
        SystemUtil.dismissKeyBorwd(this);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.iv_pic:
                bundle.putStringArrayList(SelectPhotoActivity.INIT_DATA,pic_path);
                startAtvDonFinishForResult(SelectPhotoActivity.class,REQUEST_CODE_IMAGE,bundle);
                break;
            case R.id.iv_add_pic:  // 选择房产证
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

//                        takePhoto.onPickFromCaptureWithCrop(imageUri,getCropOptions());
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
            case R.id.tv_house_address:  // 房屋地址
                startAtvDonFinishForResult(QuickIndexAreaActivity.class,REQUEST_CODE_ADDRESS);
                break;
            case R.id.tv_house_desc:  // 描述
                bundle.putString(HouseDescActivity.RESULT_DATA,description);
                startAtvDonFinishForResult(HouseDescActivity.class,REQUEST_CODE_DESC,bundle);
                break;
            case R.id.tv_cx:  // 朝向
                presenter.loadCXData();
                break;
            case R.id.tv_fixture:  // 装修
                presenter.loadFixtureData();
                break;
            case R.id.tv_house_type:  // 押金
                presenter.loadRentDepositData();
                break;
            case R.id.tv_property_right:  // 类型
//                presenter.loadRentTypeData();
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
    public void loadHouseConfigSuccess(List<HouseConfigBean> beans) {
        houseConfigBeans = beans;
        final LayoutInflater inflater = LayoutInflater.from(this);

        tfl_config.setAdapter(new TagAdapter<HouseConfigBean>(beans) {
            @Override
            public View getView(FlowLayout parent, int position, HouseConfigBean s) {
                TextView text = (TextView) inflater.inflate(R.layout.item_textview_publish_hotelandhouse, parent,false);
                text.setText(s.getName());
                return text;
            }
        });

        if (type!=0 && beam!=null) {
            initData();
        }
    }

    @Override
    public void loadCXDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_cx.setText(text);
                        orientation = id;
                    }
                })
                .show();
    }

    @Override
    public void loadFixtureDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_fixture.setText(text);
                        decoration = id;
                    }
                })
                .show();
    }

    @Override
    public void loadRentDepositDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_house_type.setText(text);
                        rent_deposit_type = id;
                    }
                })
                .show();
    }

//    @Override
//    public void loadRentTypeDataSuccess(List<Map<String, Object>> maps) {
//        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
//                .setSelectListener(new SimpleListDialog.OnSelectListener() {
//                    @Override
//                    public void onSelect(int id, int pos, String text) {
//                        tv_property_right.setText(text);
//                        house_type = id;
//                    }
//                })
//                .show();
//    }

    @Override
    public void loadPublishAreaDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
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
    public void uploadPicSuccess(List<String> pic_urls) {
        pic_url = pic_urls;
        presenter.uploadPropertyCertPic(property_cert_pic_path);
    }

    @Override
    public void uploadCertPicSuccess(String cert_url) {
        property_cert_pic_url = cert_url;

        if (type==0) {
            presenter.publish(getAccessToken(),3,getCurrCityId(),house_third_id,house_fourth_id,house_rooms,house_parlor,floor,total_price,
                    title,description,contact_person,contact_mobile_phone,getCurrCityId(),third_area_id,floor_area,orientation,decoration,
                    pic_url,community_name,property_cert_pic_url,house_type,property_right,developers_name,rent_deposit_type,house_config_ids,school_degree_type);

        } else {
            if (beam!=null)
            presenter.edit(beam.getHouse_id(),getAccessToken(),3,getCurrCityId(),house_third_id,house_fourth_id,house_rooms,house_parlor,floor,total_price,
                    title,description,contact_person,contact_mobile_phone,getCurrCityId(),third_area_id,floor_area,orientation,decoration,
                    pic_url,community_name,property_cert_pic_url,house_type,property_right,developers_name,rent_deposit_type,house_config_ids,school_degree_type);

        }
    }

    @Override
    public void publishSuccess(String data) {
        PublishSuccessActivity.go(this,15);
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            presenter.uploadPic(pic_path );
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("发布租房需要支付费用，继续吗")
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

//    @Override
//    public void loadRentTypeDataSuccessF(HouseMoreBean bean) {
////        List<HouseMoreBean.HouseTypeListBean> house_type_list = bean.getHouse_type_list();
////        if (house_type_list!=null) {
////            for (HouseMoreBean.HouseTypeListBean listBean : house_type_list) {
////                if (listBean.getHouse_type_id()==beam.getHouse_type()) {
////                    tv_property_right.setText(listBean.getHouse_type_name()); // 租房类型
////                    house_type = listBean.getHouse_type_id();
////                }
////            }
////        }
//    }
}
