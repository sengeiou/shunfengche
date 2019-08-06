package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.ApplyLabourServicePresenter;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.LabourLastApplyBean;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.StateButton;

import java.util.List;
import java.util.Map;

/**
 * 描述：申请劳务职位页面
 * author:cyq
 * 2018-07-31
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class ApplyLabourServiceActivity extends BaseActivity implements View.OnClickListener, ApplyLabourServiceView {

    public static final String DETAIL_ID = "DETAIL_ID";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private EditText et_name;
    private TextView tv_sex;
    private EditText et_mobile_phone;
    private TextView tv_craft;
    private EditText et_instruction;
    private StateButton btn_commit;

    private int job_class_id_one;
    private int job_class_id_two;
    private int job_class_id_three = 0;
    private int sex;//1男2女
    private int labor_intermediary_id;//劳务中介id

    private ApplyLabourServicePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_labour_service);
        initView();

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
        } else {
            labor_intermediary_id = extras.getInt(DETAIL_ID);
        }

        presenter = new ApplyLabourServicePresenter(this);
        presenter.initLastData(getAccessToken());
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        et_name = (EditText) findViewById(R.id.et_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        et_mobile_phone = (EditText) findViewById(R.id.et_mobile_phone);
        tv_craft = (TextView) findViewById(R.id.tv_craft);
        et_instruction = (EditText) findViewById(R.id.et_instruction);
        btn_commit = (StateButton) findViewById(R.id.btn_commit);

        btn_commit.setOnClickListener(this);
        tv_craft.setOnClickListener(this);
        tv_sex.setOnClickListener(this);
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("填写个人资料");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_craft:
                startAtvDonFinishForResult(PositionClassActivity.class, 103);
                break;
            case R.id.tv_sex:
                presenter.loadSexModule();
                break;
            case R.id.btn_commit:
                commit();
                break;
        }
    }

    private void commit() {
        String name = et_name.getText().toString();
        String mobile_phone = et_mobile_phone.getText().toString();
        String instruction = et_instruction.getText().toString();

        if (TextUtils.isEmpty(name)) {
            showTips("请输入姓名", 1);
            return;
        }
        if (TextUtils.isEmpty(mobile_phone)) {
            showTips("请输入手机号码", 1);
            return;
        }
        if (sex == 0) {
            showTips("请选择性别", 1);
            return;
        }
        if (job_class_id_three == -1) {
            showTips("请选择工种", 1);
            return;
        }

        presenter.applyCommit(getAccessToken(), labor_intermediary_id, name, sex, mobile_phone, job_class_id_three, instruction);

    }

    @Override
    public void loadSexModuleSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        sex = id;
                        if (id == 1) {
                            tv_sex.setText("男");
                        } else if (id == 2) {
                            tv_sex.setText("女");
                        }
                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 103 && resultCode == 200) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                job_class_id_one = extras.getInt(Define.INTENT_DATA);
                job_class_id_two = extras.getInt(Define.INTENT_DATA_TWO);
                job_class_id_three = extras.getInt(Define.INTENT_DATA_THREE);
                String job_class_name_one = extras.getString(Define.INTENT_DATA_FOUR);
                String job_class_name_two = extras.getString(Define.INTENT_DATA_FIVE);
                String job_class_name_three = extras.getString(Define.INTENT_DATA_SIX);
                tv_craft.setText(job_class_name_one + "/" + job_class_name_two + "/" + job_class_name_three);
            }
        }
    }

    @Override
    public void initLastDataSuccess(LabourLastApplyBean bean) {
        if (bean != null) {
            job_class_id_three = bean.getJob_id();

            String mobile_phone = bean.getMobile_phone();
            et_mobile_phone.setText(mobile_phone);

            String true_name = bean.getTrue_name();
            et_name.setText(true_name);

            String self_intro = bean.getSelf_intro();
            et_instruction.setText(TextUtils.isEmpty(self_intro) ? "" : self_intro);

            sex = bean.getSex();
            if (sex == 1) {
                tv_sex.setText("男");
            } else if (sex == 2) {
                tv_sex.setText("女");
            }

        }
    }

    @Override
    public void applyCommitSuccess() {
        showTips("提交成功", 1);
        finish();
        AppManager.getAppManager().finishActivity(LabourServiceCenterDetailActivity.class);
    }
}
