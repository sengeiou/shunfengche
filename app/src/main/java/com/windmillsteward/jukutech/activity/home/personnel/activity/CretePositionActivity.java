package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArraySet;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.CreatePositionPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.JobClassBean;
import com.windmillsteward.jukutech.bean.MoreBean;
import com.windmillsteward.jukutech.bean.PositionDetailBean;
import com.windmillsteward.jukutech.bean.PostPositionResultBean;
import com.windmillsteward.jukutech.bean.SalaryBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.bean.WelfareBean;
import com.windmillsteward.jukutech.customview.MultChoiceDialog;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.customview.flowlayout.TagAdapter;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述：创建招聘信息
 * 时间：2018/1/13/013
 * 作者：xjh
 */
public class CretePositionActivity extends BaseActivity implements View.OnClickListener, CreatePositionView {

    public static String TYPE = "TYPE";
    public static String DATA = "DATA";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView toolbar_tv_right;
    private ImageView toolbar_iv_right;
    private EditText et_position_name;
    private TextView tv_position_class;
    private TextView tv_salary;
    private TextView tv_area;
    private EditText et_area_detail;
    private TextView tv_edu;
    private TextView tv_work;
    private EditText et_require;
    private EditText et_title;
    private EditText et_position_desc;
    private EditText et_contacts;
    private EditText et_phone;
    private TextView tv_post_area;
    private TextView tv_welfare;
    private TextView tv_release;

    private CreatePositionPresenter presenter;

    private int job_class_id_one;
    private int job_class_id_two;
    private int job_class_id_three;
    private int salary_id=-1;   // 薪资id
    private int work_third_area_id=-1;  // 工作区域3
    private int work_year_id=-1;  // 工作经验id
    private int education_background_id=-1;  // 学历id
    private Set<Integer> benefit_ids=new ArraySet<>();  // 福利id集合
    private Set<Integer> benefit_pos=new ArraySet<>();  // 福利id集合
    private int third_area_id=-1;  // 发布区域3


    private int type;
    private PositionDetailBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creteposition);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            type = extras.getInt(TYPE, 0);
        }
        initView();
        initToolbar();
        presenter = new CreatePositionPresenter(this, this);

        if (type!=0 && extras!=null) {
            bean = (PositionDetailBean) extras.getSerializable(DATA);
            initData();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode,resultCode,intent);

//        if (requestCode==PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            presenter.post(getAccessToken(),et_position_name.getText().toString().trim(), job_class_id_one,job_class_id_two,job_class_id_three, salary_id, getCurrCityId(), work_third_area_id,
//                    et_area_detail.getText().toString().trim(), work_year_id, education_background_id, benefit_ids, et_require.getText().toString().trim(),
//                    et_title.getText().toString().trim(), et_contacts.getText().toString().trim(), et_phone.getText().toString().trim(),
//                    getCurrCityId(), third_area_id, et_position_desc.getText().toString().trim());
//        } else
            if (requestCode==103 && resultCode == 200) {
            Bundle extras = intent.getExtras();
            if (extras!=null) {
                job_class_id_one = extras.getInt(Define.INTENT_DATA);
                job_class_id_two = extras.getInt(Define.INTENT_DATA_TWO);
                job_class_id_three = extras.getInt(Define.INTENT_DATA_THREE);
                String job_class_name_one = extras.getString(Define.INTENT_DATA_FOUR);
                String job_class_name_two = extras.getString(Define.INTENT_DATA_FIVE);
                String job_class_name_three = extras.getString(Define.INTENT_DATA_SIX);
                tv_position_class.setText(job_class_name_one+"/"+job_class_name_two+"/"+job_class_name_three);
            }
        }
    }


    private void initData() {
        if (bean!=null) {
            et_position_name.setText(bean.getJob_name());
            job_class_id_one = bean.getJob_class_id_one();
            job_class_id_two = bean.getJob_class_id_two();
            job_class_id_three = bean.getJob_class_id_three();
            tv_position_class.setText(bean.getJob_class_id_one_name()+"/"+bean.getJob_class_id_two_name()+"/"+bean.getJob_class_id_three_name());
            tv_salary.setText(bean.getSalary_show());
            salary_id = bean.getSalary_id();
            tv_area.setText(bean.getWork_third_name());
            work_third_area_id=bean.getWork_third_area_id();
            et_area_detail.setText(bean.getWork_address());
            tv_edu.setText(bean.getEducation_name());
            education_background_id = bean.getEducation_background_id();
            tv_work.setText(bean.getWork_year_name());
            work_year_id = bean.getWork_year_id();
            et_require.setText(bean.getRequire());
            et_title.setText(bean.getTitle());
            et_position_desc.setText(bean.getDescription());
            et_contacts.setText(bean.getContact_person());
            et_phone.setText(bean.getContact_tel());
            tv_post_area.setText(bean.getThird_name());
            third_area_id = bean.getThird_area_id();
            List<String> benefit_list = bean.getBenefit_list();
            StringBuilder sb = new StringBuilder();
            if (benefit_list!=null) {
                for (String s : benefit_list) {
                    sb.append(s).append(" ");
                }
            }
            tv_welfare.setText(sb.toString());
            benefit_ids.addAll(bean.getBenefit_ids());

            tv_release.setText("保存");
        }
    }

    private void initToolbar() {
        mImmersionBar.keyboardEnable(true).init();
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("创建招聘信息");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        et_position_name = (EditText) findViewById(R.id.et_position_name);
        tv_position_class = (TextView) findViewById(R.id.tv_position_class);
        tv_salary = (TextView) findViewById(R.id.tv_salary);
        tv_area = (TextView) findViewById(R.id.tv_area);
        et_area_detail = (EditText) findViewById(R.id.et_area_detail);
        tv_edu = (TextView) findViewById(R.id.tv_edu);
        tv_work = (TextView) findViewById(R.id.tv_work);
        et_require = (EditText) findViewById(R.id.et_require);
        et_title = (EditText) findViewById(R.id.et_title);
        et_position_desc = (EditText) findViewById(R.id.et_position_desc);
        et_contacts = (EditText) findViewById(R.id.et_contacts);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_post_area = (TextView) findViewById(R.id.tv_post_area);
        tv_release = (TextView) findViewById(R.id.tv_release);

        tv_position_class.setOnClickListener(this);
        tv_salary.setOnClickListener(this);
        tv_area.setOnClickListener(this);
        tv_work.setOnClickListener(this);
        tv_edu.setOnClickListener(this);
        tv_post_area.setOnClickListener(this);
        tv_release.setOnClickListener(this);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        tv_welfare = (TextView) findViewById(R.id.tv_welfare);
        tv_welfare.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String job_name = et_position_name.getText().toString().trim();
        if (TextUtils.isEmpty(job_name)) {
            showTips("请输入职位名称",0);
            return;
        }

        String address = et_area_detail.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            showTips("请输入详细地址",0);
            return;
        }


        String title = et_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showTips("请输入标题",0);
            return;
        }

        String description = et_position_desc.getText().toString().trim();
        if (TextUtils.isEmpty(description)) {
            showTips("请输入职位描述",0);
            return;
        }

        String contact_person = et_contacts.getText().toString().trim();
        if (TextUtils.isEmpty(contact_person)) {
            showTips("请输入联系人",0);
            return;
        }

        String contact_tel = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(contact_tel)) {
            showTips("请输入联系电话",0);
            return;
        }
        if (job_class_id_one==0) {
            showTips("请选择分类",0);
            return;
        }
        if (salary_id==-1) {
            showTips("请选择薪资范围",0);
            return;
        }
        if (work_third_area_id==-1) {
            showTips("请选择工作区域",0);
            return;
        }
        if (work_year_id==-1) {
            showTips("请选择工作经验",0);
            return;
        }
        if (education_background_id==-1) {
            showTips("请选择学历",0);
            return;
        }

        if (third_area_id==-1) {
            showTips("请选择发布地区",0);
            return;
        }

        if (type==0) {
            presenter.isCharge(getAccessToken(),0);
        } else {
            presenter.edit(getAccessToken(),et_position_name.getText().toString().trim(), job_class_id_one,job_class_id_two,job_class_id_three, salary_id, getCurrCityId(), work_third_area_id,
                    et_area_detail.getText().toString().trim(), work_year_id, education_background_id, benefit_ids, et_require.getText().toString().trim(),
                    et_title.getText().toString().trim(), et_contacts.getText().toString().trim(), et_phone.getText().toString().trim(),
                    getCurrCityId(), third_area_id, et_position_desc.getText().toString().trim(),this.bean.getJob_id());
        }
    }

    @Override
    public void onClick(View view) {
        SystemUtil.dismissKeyBorwd(this);
        switch (view.getId()) {
            case R.id.tv_position_class:
                startAtvDonFinishForResult(PositionClassActivity.class,103);
//                presenter.loadJobClass();
                break;
            case R.id.tv_salary:
                presenter.loadSalaryData();
                break;
            case R.id.tv_area:
                presenter.loadAreaData(getCurrCityId());
                break;
            case R.id.tv_work:
                presenter.loadWork();
                break;
            case R.id.tv_edu:
                presenter.loadEdu();
                break;
            case R.id.tv_post_area:
                presenter.loadPostArea(getCurrCityId());
                break;
            case R.id.tv_release:
                submit();
                break;
            case R.id.tv_welfare:
                presenter.loadBenefitList();
                break;
        }
    }

    /**
     * 加载职业分类成功
     * @param list 数据
     */
    @Override
    public void loadPositionClassDataSuccess(List<JobClassBean> list) {
    }

    /**
     * 加载薪资数据成功
     * @param list 数据
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
                        tv_salary.setText(text);
                        salary_id = id;
                    }
                })
                .show();
    }

    /**
     * 加载区域数据成功
     * @param list 数据
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
                        tv_area.setText(text);
                        work_third_area_id = id;
                    }
                })
                .show();
    }

    /**
     * 加载工作经历数据成功
     * @param bean 数据
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
                        tv_work.setText(text);
                        work_year_id = id;
                    }
                })
                .show();
    }

    /**
     * 加载学历数据成功
     * @param bean 数据
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
                        tv_edu.setText(text);
                        education_background_id = id;
                    }
                })
                .show();
    }

    /**
     * 加载福利数据成功
     * @param list 数据
     */
    @Override
    public void loadWelfareDataSuccess(final List<WelfareBean> list) {

        final LayoutInflater inflater = LayoutInflater.from(this);

        TagAdapter<WelfareBean> adapter = new TagAdapter<WelfareBean>(list) {
            @Override
            public View getView(FlowLayout parent, int position, WelfareBean bean) {
                TextView view = (TextView) inflater.inflate(R.layout.item_textview_mult_select, parent, false);
                view.setText(bean.getBenefit_name());

                return view;
            }
        };
        adapter.setSelectedList(benefit_pos);
        new MultChoiceDialog(this).builder().setAdapter(adapter).setOnListener(new MultChoiceDialog.OnListener() {
            @Override
            public void onSelect(Set<Integer> ids) {
                benefit_pos = ids;
                if (ids!=null && ids.size()>0) {
                    StringBuilder sb = new StringBuilder();
                    for (Integer id : ids) {
                        int benefit_id = list.get(id).getBenefit_id();
                        CretePositionActivity.this.benefit_ids.add(benefit_id);
                        sb.append(list.get(id).getBenefit_name()).append(" ");
                    }
                    tv_welfare.setText(sb.toString());
                } else {
                    CretePositionActivity.this.benefit_ids.clear();
                    tv_welfare.setText("暂无");
                }
            }
        }).show();
    }

    /**
     * 加载职位区域数据成功
     * @param list 数据
     */
    @Override
    public void loadPostAreaDataSuccess(List<ThirdAreaBean> list) {
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
                        tv_post_area.setText(text);
                        third_area_id = id;
                    }
                })
                .show();
    }

    /**
     * 发布
     * @param bean 返回结果
     */
    @Override
    public void postResult(PostPositionResultBean bean) {
        showTips("发布成功", 0);
        PublishSuccessActivity.go(this,1);
    }

    /**
     * 判断是否需要收费
     * @param bean 结果 bean.getIs_charge()==0不收费，1收费
     */
    @Override
    public void isCharge(ChargeResultBean bean) {

        if (bean.getIs_charge()==0) {
            presenter.post(getAccessToken(),et_position_name.getText().toString().trim(), job_class_id_one,job_class_id_two,job_class_id_three, salary_id, getCurrCityId(), work_third_area_id,
                    et_area_detail.getText().toString().trim(), work_year_id, education_background_id, benefit_ids, et_require.getText().toString().trim(),
                    et_title.getText().toString().trim(), et_contacts.getText().toString().trim(), et_phone.getText().toString().trim(),
                    getCurrCityId(), third_area_id, et_position_desc.getText().toString().trim());
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("发布职位需要支付费用，继续吗")
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
}
