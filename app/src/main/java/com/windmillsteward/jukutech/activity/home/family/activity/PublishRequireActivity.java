package com.windmillsteward.jukutech.activity.home.family.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.video.VideoRecordingActivity;
import com.windmillsteward.jukutech.activity.home.family.adapter.ImagePickerAdapter;
import com.windmillsteward.jukutech.activity.home.family.presenter.PublishRequirePresenter;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishSuccessActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.AddRequireResultBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.IntelligentFamilyDetailBean;
import com.windmillsteward.jukutech.bean.RequireClassBean;
import com.windmillsteward.jukutech.customview.MyGridView;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.utils.SystemUtil;

import org.xutils.x;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 描述：发布需求
 * 时间：2018/1/16/016
 * 作者：xjh
 */
public class PublishRequireActivity extends BaseActivity implements PublishRequireView, BDLocationListener, View.OnClickListener, TakePhoto.TakeResultListener, InvokeListener {

    public static final String TYPE = "TYPE";
    public static final String DATA = "DATA";

    private TakePhoto takePhoto;  // 选择图片或拍照
    private InvokeParam invokeParam; // 选择图片或拍照的参数设置

    private LocationClient mLocClient;

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private ImageView iv_video;
    private EditText et_title;
    private EditText et_require_desc;

    private EditText et_price;
    private TextView tv_class;
    private TextView tv_area;
    private TextView tv_release;

    private MyGridView mgv_select_photo;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_CHOICE_CLASS = 102;
    public static final int REQUEST_CODE_MAKE_VIDEO = 104;

    private ImagePickerAdapter adapter;

    private List<String> selImageList;
    private int maxImgCount = 9;               //允许选择图片最大数

    private int require_class_id = -1;
    private int third_area_id = -1;
    private String title, price, description;
    private String longitude, latitude;

    private PublishRequirePresenter presenter;

    private String videoPath;
    private String video_cover_path;

    private int type;
    private IntelligentFamilyDetailBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publichrequire);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt(TYPE);
            bean = (IntelligentFamilyDetailBean) extras.getSerializable(DATA);
        }

        initView();
        initToolbar();
        initGridView();
        initLocation();

        presenter = new PublishRequirePresenter(this);

        if (type != 0 && bean != null) {
            initData();
        }
    }

    private void initData() {
        String video_urls = bean.getVideo_url();
        if (video_urls != null ) {
            x.image().bind(iv_video, bean.getVideo_cover());
        }
        List<String> pic_urls = bean.getPic_urls();
        if (pic_urls != null && pic_urls.size() > 0) {
            selImageList.addAll(pic_urls);
            adapter.setImages(pic_urls);
        }
        et_title.setText(bean.getTitle());
        et_require_desc.setText(bean.getDescription());
        et_price.setText(bean.getPrice());
        tv_class.setText(bean.getRequire_class_name());
        require_class_id = bean.getRequire_class_id();
        tv_area.setText(bean.getThird_area_name());
        third_area_id = bean.getThird_area_id();

        if (type != 0) {
            tv_release.setText("保存");
        }
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(150); // 大于1000才循环
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    private TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    private void initToolbar() {
        mImmersionBar.keyboardEnable(true).init();
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("发布");
    }

    private void initGridView() {
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(new ImagePickerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == IMAGE_ITEM_ADD) {  // 添加图片
                    initTokePhoto();
                }
            }

            @Override
            public void onDeleteClick(View view, int position) {
                selImageList.remove(position);
                adapter.setImages(selImageList);
            }
        });

        mgv_select_photo.setAdapter(adapter);
    }

    private void initTokePhoto() {
        CompressConfig config = new CompressConfig.Builder().enablePixelCompress(true)
                .create();
        takePhoto.onEnableCompress(config, true);
        takePhoto.onPickMultiple(maxImgCount - selImageList.size());
    }

    /**
     * 加载地区成功
     *
     * @param maps 数据，由于多个列表样式公用，字段只有id更名称，重新构造
     */
    @Override
    public void loadAreaDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_area.setText(text);
                        third_area_id = id;
                    }
                })
                .show();
    }

    /**
     * 文件上传成功，继续发布内容
     *
     * @param urls 返回url集合
     */
    @Override
    public void uploadFileSuccess(List<String> urls) {
        if (urls == null) {
            showTips("上传失败", 0);
            return;
        }

        List<String> pic_urls = new ArrayList<>();
        List<String> video_urls = new ArrayList<>();
        String video_cover = "";

        for (String url : urls) {
            if (url != null) {
                if (url.contains(".mp4")) {
                    video_urls.add(url);
                } else if (url.contains("video_cover_path")) {
                    video_cover = url;
                } else {
                    pic_urls.add(url);
                }
            }
        }

        if (type == 0) {
//            PublishRequireModelBean requireModelBean = new PublishRequireModelBean();
//            requireModelBean.setAccess_token(getAccessToken());
//            requireModelBean.setRequire_class_id(require_class_id);
//            requireModelBean.setTitle(title);
//            requireModelBean.setPrice(price);
//            requireModelBean.setDescription(description);
//            requireModelBean.setSecond_area_id(getCurrCityId());
//            requireModelBean.setThird_area_id(third_area_id);
//            requireModelBean.setLongitude(longitude);
//            requireModelBean.setLatitude(latitude);
//            requireModelBean.setPic_urls(pic_urls);
//            requireModelBean.setVideo_url(video_urls);
//            requireModelBean.setVideo_cover(video_cover);
//            requireModelBean.setCoupon_receive_id(0);
//            requireModelBean.setOrder_sn("");
//            Bundle bundle = new Bundle();
//            bundle.putSerializable(RequirePayActivity.DATA, requireModelBean);
//            startAtvDonFinishForResult(RequirePayActivity.class, RequirePayActivity.REQUEST_CODE, bundle);

        } else {
            if (bean != null) {
                presenter.edit(bean.getRequire_id(), getAccessToken(), require_class_id, title, price, description, getCurrCityId(), third_area_id, longitude, latitude, pic_urls, video_urls, video_cover);
            }
        }
    }

    /**
     * 发布成功
     *
     * @param bean 返回数据
     */
    @Override
    public void addRequireSuccess(AddRequireResultBean bean) {
        PublishSuccessActivity.go(this, 4);
    }

    @Override
    public void isCharge(ChargeResultBean bean) {
        int is_charge = bean.getIs_charge();
        if (is_charge == 0) {
            presenter.uploadFile(selImageList, videoPath, video_cover_path);
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("发布该消息需要支付费用，继续吗")
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


    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        iv_video = (ImageView) findViewById(R.id.iv_video);
        et_title = (EditText) findViewById(R.id.et_title);
        et_require_desc = (EditText) findViewById(R.id.et_require_desc);
        mgv_select_photo = (MyGridView) findViewById(R.id.mgv_select_photo);
        et_price = (EditText) findViewById(R.id.et_price);
        tv_class = (TextView) findViewById(R.id.tv_class);
        tv_area = (TextView) findViewById(R.id.tv_area);
        tv_release = (TextView) findViewById(R.id.tv_release);

        iv_video.setOnClickListener(this);
        tv_class.setOnClickListener(this);
        tv_area.setOnClickListener(this);
        tv_release.setOnClickListener(this);
    }

    private void submit() {
        title = et_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showTips("请输入姓名", 0);
            return;
        }

        description = et_require_desc.getText().toString().trim();
        if (TextUtils.isEmpty(description)) {
            showTips("请输入需求详情", 0);
            return;
        }

        price = et_price.getText().toString().trim();
        if (TextUtils.isEmpty(price)) {
            showTips("请输入价格", 0);
            return;
        }

        if (require_class_id == -1) {
            showTips("请选择分类", 0);
            return;
        }

        if (third_area_id == -1) {
            showTips("请选择发布地区", 0);
            return;
        }

        if (TextUtils.isEmpty(longitude) || TextUtils.isEmpty(latitude)) {
            showTips("获取位置信息失败，请退出重试", 0);
            return;
        }
        if (type == 0) {
            presenter.uploadFile(selImageList, videoPath, video_cover_path);
        } else {
            if (bean != null) {
                String video_urls = bean.getVideo_url();
                if (video_urls != null ) {
                    presenter.edit_uploadFile(selImageList, videoPath, video_cover_path, bean.getVideo_cover(), bean.getVideo_url());
                } else {
                    presenter.edit_uploadFile(selImageList, videoPath, video_cover_path, bean.getVideo_cover(), null);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOICE_CLASS && resultCode == ChoiceCLassActivity.RESULT_CODE) {  // 选择分类
            if (data != null && data.getExtras() != null) {
                RequireClassBean classBean = (RequireClassBean) data.getExtras().getSerializable(ChoiceCLassActivity.DATA);
                if (classBean != null) {
                    tv_class.setText(classBean.getClass_name());
                    require_class_id = classBean.getRequire_class_id();
                }
            }
        } else if (requestCode == REQUEST_CODE_MAKE_VIDEO && resultCode == 200) {  // 拍照
            if (data != null && data.getExtras() != null) {
                videoPath = data.getExtras().getString("path");
                iv_video.setImageBitmap(getVideoThumbnail(videoPath, 800, 800, MediaStore.Images.Thumbnails.MICRO_KIND));
            }
        }
//        else if (requestCode == RequirePayActivity.REQUEST_CODE && resultCode == RequirePayActivity.RESULT_CODE_SUCCESS) {
//            PublishSuccessActivity.go(this, 4);
//        }
    }

    /**
     * 获取视频的关键帧作为封面
     *
     * @param videoPath 视频本地路径
     * @param width     宽
     * @param height    高
     * @param kind      could be MINI_KIND or MICRO_KIND
     * @return bitmap
     */
    private Bitmap getVideoThumbnail(String videoPath, int width, int height,
                                     int kind) {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        video_cover_path = getCacheDir().getAbsolutePath() + File.separator + "takephoto_cache" + File.separator + "video_cover_path.jpg";

        try {
            File file = new File(video_cover_path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    public void onClick(View v) {
        SystemUtil.dismissKeyBorwd(this);
        switch (v.getId()) {
            case R.id.iv_video:
                startActivityForResult(new Intent(this, VideoRecordingActivity.class), REQUEST_CODE_MAKE_VIDEO);
                break;
            case R.id.tv_class:
                startActivityForResult(new Intent(this, ChoiceCLassActivity.class), REQUEST_CODE_CHOICE_CLASS);
                break;
            case R.id.tv_area:
                presenter.loadAreaData(getCurrCityId());
                break;
            case R.id.tv_release:
                submit();
                break;
        }
    }

    /**
     * 由于拍照和选择图片的问题，这里要判断一下选用小的是被压缩过的
     *
     * @param result 返回数据 result.getImages(); 对应多选  result.getClass_banner();  单选
     */
    @Override
    public void takeSuccess(TResult result) {
        ArrayList<TImage> images = result.getImages();
        for (TImage image : images) {
            selImageList.add(image.getCompressPath());
        }
        adapter.setImages(selImageList);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        showTips("选择图片失败，请重试", 0);
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
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation == null) {
            return;
        }
        latitude = String.valueOf(bdLocation.getLatitude());
        longitude = String.valueOf(bdLocation.getLongitude());
        mLocClient.unRegisterLocationListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocClient != null) {
            mLocClient.stop();
        }
    }
}
