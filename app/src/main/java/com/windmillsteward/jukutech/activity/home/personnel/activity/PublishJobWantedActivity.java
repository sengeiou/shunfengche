package com.windmillsteward.jukutech.activity.home.personnel.activity;

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

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
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
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.PostJobWantedPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.MoreBean;
import com.windmillsteward.jukutech.bean.PostResultBean;
import com.windmillsteward.jukutech.bean.ResumeDetailBean;
import com.windmillsteward.jukutech.bean.SalaryBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.bean.UploadResultBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.BottomDialog;
import com.windmillsteward.jukutech.customview.dialog.SexSelectDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.RegexChkUtil;
import com.windmillsteward.jukutech.utils.SystemUtil;

import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：发布求职
 * 时间：2018/1/8
 * 作者：xjh
 */

public class PublishJobWantedActivity extends BaseActivity implements PublishJobWantedView, View.OnClickListener, TakePhoto.TakeResultListener, InvokeListener {

    public static final String TYPE = "TYPE";
    public static final String DATA = "DATA";

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    private TextView tv_create_resume;
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView toolbar_tv_right;
    private ImageView iv_header;
    private EditText et_username;
    private TextView tv_select_header;
    private TextView tv_sex;
    private EditText et_phone;
    private TextView tv_birthday;
    private TextView tv_edu;
    private TextView tv_work_year;
    private TextView et_job_intention;
    private TextView tv_salary_expectation;
    private TextView tv_job_location;
    private TextView tv_publish_area;

    private String username, phone, birthday, jobIntent;
    private int sex = 2;  // 性别 2女 1男
    private int eduId = -1;  // 学历id
    private int workYearId = -1;  // 工作年限
    private int salaryIntent = -1;  // 期望薪资
    private int jobAreaId = -1;  // 工作区域id
    private int publish_area_id = -1;  // 区发布域id
    private int job_class_id_one;
    private int job_class_id_two;
    private int job_class_id_three;
    private PostJobWantedPresenter presenter;

    private File file_data;
    private int type;
    private ResumeDetailBean bean;
    private String headUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 同步图片选择器
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postjobwanted);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt(TYPE);
        }

        initView();
        initToolbar();
        presenter = new PostJobWantedPresenter(this);

        if (type != 0 && extras != null) {
            bean = (ResumeDetailBean) extras.getSerializable(DATA);
            initData();
        }
    }

    private void initData() {
        if (bean != null) {
            x.image().bind(iv_header, bean.getUser_avatar_url());
            headUrl = bean.getUser_avatar_url();

            et_username.setText(bean.getTrue_name());
            tv_sex.setText(bean.getSex() == 1 ? "男" : "女");
            sex = bean.getSex();
            et_phone.setText(bean.getMobile_phone());
            tv_birthday.setText(bean.getBirthday());
            birthday = bean.getBirthday();
            tv_edu.setText(bean.getEducation_name());
            eduId = bean.getEducation_background_id();
            tv_work_year.setText(bean.getWork_year_name());
            workYearId = bean.getWork_year_id();
            job_class_id_one = bean.getJob_class_id_one();
            job_class_id_two = bean.getJob_class_id_two();
            job_class_id_three = bean.getJob_class_id_three();
            et_job_intention.setText(bean.getJob_class_id_one_name() + "/" + bean.getJob_class_id_two_name() + "/" + bean.getJob_class_id_three_name());
            tv_salary_expectation.setText(bean.getSalary_show());
            salaryIntent = bean.getSalary_id();
            tv_job_location.setText(bean.getWork_third_area_name());
            jobAreaId = bean.getWork_third_area_id();
            tv_publish_area.setText(bean.getThird_area_name());
            publish_area_id = bean.getThird_area_id();

            toolbar_iv_title.setText("编辑");
            tv_create_resume.setText("保存");
        }
    }

    /**
     * 权限处理
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    /**
     * 初始化图片选择器
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    private void initToolbar() {
        mImmersionBar.keyboardEnable(true).init();
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("发布求职");
    }

    private void initView() {
        tv_create_resume = (TextView) findViewById(R.id.tv_create_resume);
        tv_create_resume.setOnClickListener(this);
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);

        iv_header = (ImageView) findViewById(R.id.iv_header);
        iv_header.setOnClickListener(this);
        et_username = (EditText) findViewById(R.id.et_username);
        tv_select_header = (TextView) findViewById(R.id.tv_select_header);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_sex.setOnClickListener(this);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        tv_birthday.setOnClickListener(this);
        tv_edu = (TextView) findViewById(R.id.tv_edu);
        tv_edu.setOnClickListener(this);
        tv_work_year = (TextView) findViewById(R.id.tv_work_year);
        tv_work_year.setOnClickListener(this);
        et_job_intention = (TextView) findViewById(R.id.et_job_intention);
        et_job_intention.setOnClickListener(this);
        tv_salary_expectation = (TextView) findViewById(R.id.tv_salary_expectation);
        tv_salary_expectation.setOnClickListener(this);
        tv_job_location = (TextView) findViewById(R.id.tv_job_location);
        tv_job_location.setOnClickListener(this);
        tv_publish_area = (TextView) findViewById(R.id.tv_publish_area);
        tv_publish_area.setOnClickListener(this);
    }

    private void submit() {
        if (headUrl == null) {
            if (file_data == null) {
                showTips("请上传头像", 0);
                return;
            }
        }

        username = et_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            showTips("请输入姓名", 0);
            return;
        }
        phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showTips("请输入手机号码", 0);
            return;
        } else if (!RegexChkUtil.checkCellPhone(phone)) {
            showTips("请输入正确的手机号码", 0);
            return;
        }

        if (TextUtils.isEmpty(birthday)) {
            showTips("请选择出生年月", 0);
            return;
        }

        if (jobAreaId == -1) {
            showTips("请选择工作区域", 0);
            return;
        }
        if (publish_area_id == -1) {
            showTips("请选发布区域", 0);
            return;
        }

        jobIntent = et_job_intention.getText().toString().trim();
        if (TextUtils.isEmpty(jobIntent)) {
            showTips("请输入职位", 0);
            return;
        }

        if (job_class_id_one == 0) {
            showTips("请选择职位", 0);
            return;
        }

        if (eduId == -1) {
            showTips("请选择学历", 0);
            return;
        }
        if (workYearId == -1) {
            showTips("请选择工作经历", 0);
            return;
        }
        if (salaryIntent == -1) {
            showTips("请选择期望薪资", 0);
            return;
        }

        if (headUrl == null) {
            presenter.isContacCharge(getAccessToken(), 0);
        } else {
            if (type != 0 && this.bean != null) {
                presenter.edit(this.bean.getResume_id(), getAccessToken(), headUrl, username, sex, phone,
                        birthday, eduId, workYearId, job_class_id_one, job_class_id_two, job_class_id_three,
                        salaryIntent, getCurrCityId(), publish_area_id, getCurrCityId(), jobAreaId);
            }
        }
    }

    @Override
    public void onClick(View view) {

        SystemUtil.dismissKeyBorwd(this);

        switch (view.getId()) {
            case R.id.iv_header:   // 选择头像
                new BottomDialog(PublishJobWantedActivity.this, new BottomDialog.OnSelectListener() {
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
            case R.id.tv_sex:  // 选择性别
                new SexSelectDialog(this).builder().setSelectListener(new SexSelectDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int sex) {
                        PublishJobWantedActivity.this.sex = sex;
                        PublishJobWantedActivity.this.tv_sex.setText(sex == 1 ? "男" : "女");
                    }
                }).show();

                break;
            case R.id.tv_birthday:  // 选择生日
                //时间选择器
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        birthday = DateUtil.getYY_MM_DD(date, "yyyy-MM-dd");
                        PublishJobWantedActivity.this.tv_birthday.setText(birthday);
                    }
                })
                        .setType(new boolean[]{true, true, true, false, false, false})
                        .build();
                pvTime.setDate(Calendar.getInstance());
                pvTime.show();
                break;
            case R.id.tv_edu:  // 选择学历
                presenter.loadEdu();
                break;
            case R.id.tv_work_year:  // 选择工作年限
                presenter.loadWork();
                break;
            case R.id.tv_salary_expectation:  // 选择期望薪资
                presenter.loadSalaryData();
                break;
            case R.id.tv_job_location:  // 现在工作区域
                presenter.loadAreaData(getCurrCityId());
                break;
            case R.id.tv_publish_area:
                presenter.loadPublishAreaData(getCurrCityId());
                break;
            case R.id.tv_create_resume:
                submit();
                break;
            case R.id.et_job_intention:
                startAtvDonFinishForResult(PositionClassActivity.class, 101);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 同步图片选择器状态
        getTakePhoto().onActivityResult(requestCode, resultCode, intent);
        super.onActivityResult(requestCode, resultCode, intent);

//        if (requestCode==PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            presenter.upload(file_data);
//        }  else
        if (requestCode == 101 && resultCode == 200) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                job_class_id_one = extras.getInt(Define.INTENT_DATA);
                job_class_id_two = extras.getInt(Define.INTENT_DATA_TWO);
                job_class_id_three = extras.getInt(Define.INTENT_DATA_THREE);
                String job_class_name_one = extras.getString(Define.INTENT_DATA_FOUR);
                String job_class_name_two = extras.getString(Define.INTENT_DATA_FIVE);
                String job_class_name_three = extras.getString(Define.INTENT_DATA_SIX);
                et_job_intention.setText(job_class_name_one + "/" + job_class_name_two + "/" + job_class_name_three);
            }
        }
    }

    /**
     * 加载教育经历成功
     */
    @Override
    public void loadEduDataSuccess(List<MoreBean.EducationListBean> bean) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (MoreBean.EducationListBean listBean : bean) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", listBean.getEducation_background_id());
            map.put("text", listBean.getEducation_name());
            list.add(map);
        }
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, list))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        PublishJobWantedActivity.this.tv_edu.setText(text);
                        PublishJobWantedActivity.this.eduId = id;
                    }
                })
                .show();
    }

    /**
     * 加载工作经历成功
     */
    @Override
    public void loadWorkDataSuccess(List<MoreBean.WordYearListBean> bean) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (MoreBean.WordYearListBean listBean : bean) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", listBean.getWork_year_id());
            map.put("text", listBean.getWork_year_show());
            list.add(map);
        }
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, list))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        PublishJobWantedActivity.this.tv_work_year.setText(text);
                        PublishJobWantedActivity.this.workYearId = id;
                    }
                })
                .show();
    }

    /**
     * 加载薪资成功
     */
    @Override
    public void loadSalaryDataSuccess(List<SalaryBean> list) {
        List<Map<String, Object>> maps = new ArrayList<>();
        for (SalaryBean listBean : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", listBean.getSalary_id());
            map.put("text", listBean.getSalary_show());
            maps.add(map);
        }
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        PublishJobWantedActivity.this.tv_salary_expectation.setText(text);
                        PublishJobWantedActivity.this.salaryIntent = id;
                    }
                })
                .show();
    }

    /**
     * 加载区域成功
     */
    @Override
    public void loadAreaDataSuccess(List<ThirdAreaBean> list) {
        List<Map<String, Object>> maps = new ArrayList<>();
        for (ThirdAreaBean listBean : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", listBean.getThird_area_id());
            map.put("text", listBean.getThird_area_name());
            maps.add(map);
        }
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        PublishJobWantedActivity.this.tv_job_location.setText(text);
                        PublishJobWantedActivity.this.jobAreaId = id;
                    }
                })
                .show();
    }

    @Override
    public void loadPublishAreaDataSuccess(List<ThirdAreaBean> list) {
        List<Map<String, Object>> maps = new ArrayList<>();
        for (ThirdAreaBean listBean : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", listBean.getThird_area_id());
            map.put("text", listBean.getThird_area_name());
            maps.add(map);
        }
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        PublishJobWantedActivity.this.tv_publish_area.setText(text);
                        PublishJobWantedActivity.this.publish_area_id = id;
                    }
                })
                .show();
    }

    /**
     * 提交
     *
     * @param bean
     */
    @Override
    public void postResult(PostResultBean bean) {
        if (type == 0) {
            int resume_id = bean.getResume_id();
            Bundle bundle = new Bundle();
            bundle.putString(CreateResumeActivity.RESUME_ID, String.valueOf(resume_id));
            startAtvAndFinish(CreateResumeActivity.class, bundle);
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt(CreateResumeActivity.TYPE, type);
            bundle.putSerializable(CreateResumeActivity.DATA, this.bean);
            startAtvAndFinish(CreateResumeActivity.class, bundle);
        }

    }

    /**
     * 上传头像成功后再上传数据
     */
    @Override
    public void uploadSuccess(UploadResultBean bean) {
        headUrl = bean.getUrl();
        if (type == 0) {
            presenter.post(getAccessToken(), headUrl, username, sex, phone,
                    birthday, eduId, workYearId, job_class_id_one, job_class_id_two, job_class_id_three,
                    salaryIntent, getCurrCityId(), publish_area_id, getCurrCityId(), jobAreaId);
        } else {
            if (this.bean != null)
                presenter.edit(this.bean.getResume_id(), getAccessToken(), headUrl, username, sex, phone,
                        birthday, eduId, workYearId, job_class_id_one, job_class_id_two, job_class_id_three,
                        salaryIntent, getCurrCityId(), publish_area_id, getCurrCityId(), jobAreaId);
        }

    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge() == 0) {
            presenter.upload(file_data);
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("发布求职需要支付费用，继续吗")
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

    /**
     * 选择头像成功，这里是取小的图片
     */
    @Override
    public void takeSuccess(TResult result) {
        String compressPath = result.getImage().getCompressPath();
        String originalPath = result.getImage().getOriginalPath();
        if (!TextUtils.isEmpty(compressPath) && !TextUtils.isEmpty(originalPath)) {
            File compressFile = new File(compressPath);
            File originalFile = new File(originalPath);
            if (compressFile.length() < originalFile.length()) {
                file_data = compressFile;
                iv_header.setImageBitmap(BitmapFactory.decodeFile(compressPath));
            } else {
                file_data = originalFile;
                iv_header.setImageBitmap(BitmapFactory.decodeFile(originalPath));
            }
        } else {
            if (TextUtils.isEmpty(compressPath) && !TextUtils.isEmpty(originalPath)) {
                file_data = new File(originalPath);
                iv_header.setImageBitmap(BitmapFactory.decodeFile(originalPath));
            } else if (!TextUtils.isEmpty(compressPath) && TextUtils.isEmpty(originalPath)) {
                file_data = new File(compressPath);
                iv_header.setImageBitmap(BitmapFactory.decodeFile(compressPath));
            }
        }
        headUrl = null;
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
