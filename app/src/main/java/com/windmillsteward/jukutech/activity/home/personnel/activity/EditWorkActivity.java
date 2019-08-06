package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.WorkExpBean;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 描述：
 * 时间：2018/1/12
 * 作者：xjh
 */

public class EditWorkActivity extends BaseActivity implements View.OnClickListener {

    public static final String TYPE="TYPE";
    public static final String RESULT="RESULT";
    public static final String DATE="DATE";
    public static final int RESULT_CODE_ADD = 200;
    public static final int RESULT_CODE_EDIT = 201;
    public static final int RESULT_CODE_DELETE = 202;
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView toolbar_tv_right;
    private EditText et_corporate_name;
    private EditText et_position_name;
    private TextView tv_start_time;
    private TextView tv_end_time;
    private TextView et_delete;
    private String start_time,end_time;
    private int type; // type=0 添加； 1 修改
    private WorkExpBean bean;

    /**
     * 发车到此
     * @param activity 上个activity
     * @param type type 0 添加； 1 修改
     * @param requestCode 请求码
     */
    public static void go(Activity activity, int type, int requestCode,WorkExpBean bean) {
        Intent intent = new Intent(activity, EditWorkActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EditWorkActivity.TYPE, type);
        bundle.putParcelable(EditWorkActivity.DATE,bean);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editwork);

        Bundle extras = getIntent().getExtras();

        if (extras!=null) {
            type = extras.getInt(TYPE);
            bean = extras.getParcelable(DATE);
        }

        initView(type);

        initToolbar();
    }

    private void initToolbar() {
        toolbar_iv_title.setText("添加工作经历");
        toolbar_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar_tv_right.setText("完成");
        toolbar_tv_right.setVisibility(View.VISIBLE);
        toolbar_tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    private void initView(int type) {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        et_corporate_name = (EditText) findViewById(R.id.et_corporate_name);
        et_position_name = (EditText) findViewById(R.id.et_position_name);
        tv_start_time = (TextView) findViewById(R.id.tv_start_time);
        tv_end_time = (TextView) findViewById(R.id.tv_end_time);
        et_delete = (TextView) findViewById(R.id.et_delete);
        if (type == 0) {
            et_delete.setVisibility(View.GONE);
        } else {
            et_delete.setVisibility(View.VISIBLE);
            et_delete.setOnClickListener(this);

            if (bean!=null) {
                et_corporate_name.setText(bean.getCompany_name());
                et_position_name.setText(bean.getPosition());
                tv_start_time.setText(bean.getStart_date());
                tv_end_time.setText(bean.getEnd_date());
                start_time = bean.getStart_date();
                end_time=bean.getEnd_date();

            }
        }

        tv_start_time.setOnClickListener(this);
        tv_end_time.setOnClickListener(this);
    }

    private void submit() {
        String corporate_name = et_corporate_name.getText().toString().trim();
        if (TextUtils.isEmpty(corporate_name)) {
            showTips("请输入公司名称", Toast.LENGTH_SHORT);
            return;
        }

        String position_name = et_position_name.getText().toString().trim();
        if (TextUtils.isEmpty(position_name)) {
            showTips( "请输入职位名称", Toast.LENGTH_SHORT);
            return;
        }

        if (TextUtils.isEmpty(start_time)) {
            showTips("请选择开始时间",0);
            return;
        }
        if (TextUtils.isEmpty(end_time)) {
            showTips("请选结束始时间",0);
            return;
        }

        if (type==0) {
            Intent data = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable(RESULT,new WorkExpBean(UUID.randomUUID().toString(),"0",start_time,end_time,corporate_name,position_name));
            data.putExtras(bundle);
            setResult(RESULT_CODE_ADD, data);
            finish();
        } else {
            Intent data = new Intent();
            Bundle bundle = new Bundle();
            if (bean!=null) {
                bundle.putParcelable(RESULT,new WorkExpBean(bean.getId(),bean.getWork_experience_id(),start_time,end_time,corporate_name,position_name));
            }
            data.putExtras(bundle);
            setResult(RESULT_CODE_EDIT, data);
            finish();
        }


    }

    @Override
    public void onClick(View view) {

        SystemUtil.dismissKeyBorwd(this);

        switch (view.getId()) {
            case R.id.et_delete:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable(RESULT, bean);
                intent.putExtras(bundle);
                setResult(RESULT_CODE_DELETE,intent);
                finish();
                break;
            case R.id.tv_start_time:
                TimePickerView start = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        start_time = DateUtil.getYY_MM_DD(date,"yyyy-MM");
                        tv_start_time.setText(start_time);
                    }
                })
                        .setType(new boolean[]{true, true, false, false, false, false})
                        .build();
                start.setDate(Calendar.getInstance());
                start.show();
                break;
            case R.id.tv_end_time:
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        end_time = DateUtil.getYY_MM_DD(date,"yyyy-MM");
                        tv_end_time.setText(end_time);
                    }
                })
                        .setType(new boolean[]{true, true, false, false, false, false})
                        .build();
                pvTime.setDate(Calendar.getInstance());
                pvTime.show();
                break;
        }
    }
}
