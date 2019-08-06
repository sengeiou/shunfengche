package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.CreateResumePresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.EduExpBean;
import com.windmillsteward.jukutech.bean.ResumeDetailBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.bean.WorkExpBean;
import com.windmillsteward.jukutech.customview.TimelineLayout;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：创建简历
 * 时间：2018/1/12
 * 作者：xjh
 */

public class CreateResumeActivity extends BaseActivity implements CreateResumeView, View.OnClickListener {

    public static final String RESUME_ID = "RESUME_ID";
    public static final String TYPE = "TYPE";
    public static final String DATA = "DATA";
    private final int REQUEST_CODE_ADD_WORK = 100;
    private final int REQUEST_CODE_EDIT_WORK = 101;
    private final int REQUEST_CODE_ADD_EDU = 102;
    private final int REQUEST_CODE_EDIT_EDU = 103;

    private TextView tv_add_work;
    private TextView tv_add_edu;
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView toolbar_tv_right;
    private TimelineLayout timeline_work;
    private TimelineLayout timeline_edu;
    private EditText et_intro_me;
    private TextView tv_add_resume;
    private List<WorkExpBean> workExpBeans;
    private List<EduExpBean> eduExpBeans;
    private List<View> workViews;
    private List<View> eduViews;
    private CreateResumePresenter presenter;

    private String resume_id;

    private int type;
    private ResumeDetailBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createresume);
        initView();

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            resume_id = extras.getString(RESUME_ID);
            type = extras.getInt(TYPE);
        }

        initToolbar();
        workExpBeans = new ArrayList<>();
        eduExpBeans = new ArrayList<>();
        workViews = new ArrayList<>();
        eduViews = new ArrayList<>();

        presenter = new CreateResumePresenter(this);

        if (type!=0 && extras!=null) {
            bean = (ResumeDetailBean) extras.getSerializable(DATA);
            initData();
        }
    }

    private void initData() {
        if (bean!=null) {
            List<ResumeDetailBean.EducationListBean> education_list = bean.getEducation_list();
            if (education_list!=null) {
                for (ResumeDetailBean.EducationListBean listBean : education_list) {
                    EduExpBean expBean = new EduExpBean(String.valueOf(listBean.getEducation_id()),String.valueOf(listBean.getEducation_id()),listBean.getStart_date(),
                            listBean.getEnd_date(),listBean.getSchool_name(),listBean.getMajor());
                    eduExpBeans.add(expBean);

                    View view = LayoutInflater.from(CreateResumeActivity.this).inflate(R.layout.view_timeline_inner_exp, null);
                    view.setTag(expBean);
                    eduViews.add(view);
                    initEduView();
                    timeline_edu.addView(view);
                }
            }

            List<ResumeDetailBean.WorkExperienceListBean> work_experience_list = bean.getWork_experience_list();
            if (work_experience_list!=null) {
                for (ResumeDetailBean.WorkExperienceListBean listBean : work_experience_list) {
                    WorkExpBean expBean = new WorkExpBean(String.valueOf(listBean.getWork_experience_id()),String.valueOf(listBean.getWork_experience_id()),listBean.getStart_date(),
                            listBean.getEnd_date(),listBean.getCompany_name(),listBean.getPosition());
                    workExpBeans.add(expBean);

                    View view = LayoutInflater.from(CreateResumeActivity.this).inflate(R.layout.view_timeline_inner_exp, null);
                    view.setTag(expBean);
                    workViews.add(view);
                    initWorkView();
                    timeline_work.addView(view);
                }
            }

            et_intro_me.setText(bean.getSelf_intro());
            toolbar_iv_title.setText("编辑");
            tv_add_resume.setText("保存");
        }
    }

    private void initToolbar() {
        mImmersionBar.keyboardEnable(true).init();
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("发布求职");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        timeline_work = (TimelineLayout) findViewById(R.id.timeline_work);
        timeline_edu = (TimelineLayout) findViewById(R.id.timeline_edu);
        et_intro_me = (EditText) findViewById(R.id.et_intro_me);
        tv_add_work = (TextView) findViewById(R.id.tv_add_work);
        tv_add_edu = (TextView) findViewById(R.id.tv_add_edu);
        tv_add_resume = (TextView) findViewById(R.id.tv_add_resume);

        tv_add_work.setOnClickListener(this);
        tv_add_edu.setOnClickListener(this);
        tv_add_resume.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        SystemUtil.dismissKeyBorwd(this);
        switch (v.getId()) {
            case R.id.tv_add_work:
                EditWorkActivity.go(this,0,REQUEST_CODE_ADD_WORK,null);
                break;
            case R.id.tv_add_edu:
                EditEduActivity.go(this,0,REQUEST_CODE_ADD_EDU, null);
                break;
            case R.id.tv_add_resume:
                commit();
                break;
        }
    }

    private void commit() {
        String intro_me = et_intro_me.getText().toString().trim();
        if (!TextUtils.isEmpty(intro_me)) {
            if (intro_me.length()<20) {
                showTips("自我介绍必须大于20字",0);
                return;
            } else if (intro_me.length()>200) {
                showTips("自我介绍必须在200字以内",0);
                return;
            }
        }

        if (type==0) {
            presenter.push(getAccessToken(),workExpBeans,eduExpBeans,intro_me,resume_id);
        } else {
            if (bean!=null) {
                presenter.edit(getAccessToken(),workExpBeans,eduExpBeans,intro_me,bean.getResume_id());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_WORK && resultCode == EditWorkActivity.RESULT_CODE_ADD) {  // 添加工作
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                final WorkExpBean expBean = bundle.getParcelable(EditWorkActivity.RESULT);
                if (expBean==null) {
                    showTips("错啦",0);
                    return;
                }
                workExpBeans.add(expBean);

                View view = LayoutInflater.from(CreateResumeActivity.this).inflate(R.layout.view_timeline_inner_exp, null);
                view.setTag(expBean);
                workViews.add(view);
                initWorkView();
                timeline_work.addView(view);
            }
        } else if (requestCode == REQUEST_CODE_EDIT_WORK && resultCode == EditWorkActivity.RESULT_CODE_EDIT) {   // 编辑工作
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                final WorkExpBean expBean = bundle.getParcelable(EditWorkActivity.RESULT);
                if (expBean==null) {
                    showTips("错啦",0);
                    return;
                }
                for (WorkExpBean bean : workExpBeans) {
                    if (TextUtils.equals(bean.getId(),expBean.getId())) {
                        bean.setCompany_name(expBean.getCompany_name());
                        bean.setPosition(expBean.getPosition());
                        bean.setStart_date(expBean.getStart_date());
                        bean.setEnd_date(expBean.getEnd_date());
                        break;
                    }
                }

                initWorkView();
            }
        } else if (requestCode == REQUEST_CODE_EDIT_WORK && resultCode == EditWorkActivity.RESULT_CODE_DELETE) {  // 删除工作
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                final WorkExpBean expBean = bundle.getParcelable(EditWorkActivity.RESULT);
                if (expBean==null) {
                    showTips("错啦",0);
                    return;
                }

                for (int i = 0; i < workExpBeans.size(); i++) {
                    WorkExpBean bean = workExpBeans.get(i);
                    if (TextUtils.equals(bean.getId(),expBean.getId())) {
                        workExpBeans.remove(i);
                        break;
                    }
                }

                for (View view : workViews) {
                    WorkExpBean tag = (WorkExpBean) view.getTag();
                    if (TextUtils.equals(tag.getId(),expBean.getId())) {
                        timeline_work.removeView(view);
                        break;
                    }
                }
            }

        } else if (requestCode==REQUEST_CODE_ADD_EDU && resultCode==EditEduActivity.RESULT_CODE_ADD) {  // 添加学历
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                final EduExpBean eduExpBean = bundle.getParcelable(EditWorkActivity.RESULT);
                if (eduExpBean==null) {
                    showTips("错啦",0);
                    return;
                }
                eduExpBeans.add(eduExpBean);

                View view = LayoutInflater.from(CreateResumeActivity.this).inflate(R.layout.view_timeline_inner_exp, null);
                view.setTag(eduExpBean);
                eduViews.add(view);
                initEduView();
                timeline_edu.addView(view);
            }
        } else if (requestCode==REQUEST_CODE_EDIT_EDU && resultCode == EditEduActivity.RESULT_CODE_EDIT) {  // 编辑学历
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                final EduExpBean expBean = bundle.getParcelable(EditEduActivity.RESULT);
                if (expBean==null) {
                    showTips("错啦",0);
                    return;
                }
                for (EduExpBean bean : eduExpBeans) {
                    if (TextUtils.equals(bean.getId(),expBean.getId())) {
                        bean.setSchool_name(expBean.getSchool_name());
                        bean.setMajor(expBean.getMajor());
                        bean.setStart_date(expBean.getStart_date());
                        bean.setEnd_date(expBean.getEnd_date());
                        break;
                    }
                }

                initEduView();
            }
        } else if (requestCode==REQUEST_CODE_EDIT_EDU && resultCode == EditEduActivity.RESULT_CODE_DELETE) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                final EduExpBean expBean = bundle.getParcelable(EditEduActivity.RESULT);
                if (expBean==null) {
                    showTips("错啦",0);
                    return;
                }

                for (int i = 0; i < eduExpBeans.size(); i++) {
                    EduExpBean bean = eduExpBeans.get(i);
                    if (TextUtils.equals(bean.getId(),expBean.getId())) {
                        eduExpBeans.remove(i);
                        break;
                    }
                }

                for (View view : eduViews) {
                    EduExpBean tag = (EduExpBean) view.getTag();
                    if (TextUtils.equals(tag.getId(),expBean.getId())) {
                        timeline_edu.removeView(view);
                        break;
                    }
                }
            }
        }

    }

    private void initEduView() {
        for (View view : eduViews) {
            final EduExpBean expBean = (EduExpBean) view.getTag();
            ImageView iv_edit = (ImageView) view.findViewById(R.id.iv_edit);
            TextView tv_work_time = (TextView) view.findViewById(R.id.timeline_point);
            TextView tv_company_name = (TextView) view.findViewById(R.id.tv_company_name);
            TextView tv_position_name = (TextView) view.findViewById(R.id.tv_position_name);
            tv_work_time.setText(expBean.getStart_date()+"到"+expBean.getEnd_date());
            tv_company_name.setText(expBean.getSchool_name());
            tv_position_name.setText(expBean.getMajor());

            iv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditEduActivity.go(CreateResumeActivity.this,1,REQUEST_CODE_EDIT_EDU,expBean);
                }
            });
        }
    }

    private void initWorkView() {
        for (View view : workViews) {
            final WorkExpBean expBean = (WorkExpBean) view.getTag();
            ImageView iv_edit = (ImageView) view.findViewById(R.id.iv_edit);
            TextView tv_work_time = (TextView) view.findViewById(R.id.timeline_point);
            TextView tv_company_name = (TextView) view.findViewById(R.id.tv_company_name);
            TextView tv_position_name = (TextView) view.findViewById(R.id.tv_position_name);
            tv_work_time.setText(expBean.getStart_date()+"到"+expBean.getEnd_date());
            tv_company_name.setText(expBean.getCompany_name());
            tv_position_name.setText(expBean.getPosition());

            iv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditWorkActivity.go(CreateResumeActivity.this,1,REQUEST_CODE_EDIT_WORK,expBean);
                }
            });
        }
    }


    @Override
    public void publishSuccess() {
        PublishSuccessActivity.go(this,2);
    }


}
