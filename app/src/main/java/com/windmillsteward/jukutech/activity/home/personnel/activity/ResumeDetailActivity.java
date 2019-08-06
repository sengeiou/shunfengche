package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.ResumeDetailPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.ResumeDetailBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.TimelineLayout;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateTimeFormatUtil;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.List;

/**
 * 描述：简历详情界面
 * 时间：2018/1/10
 * 作者：xjh
 */
public class ResumeDetailActivity extends BaseActivity implements ResumeDetailView, View.OnClickListener {

    public static final String RESUME_ID="RESUME_ID";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private CircleImageView civ_header;
    private ImageView iv_sex;
    private TextView tv_age;
    private TextView tv_edu;
    private TextView tv_work_year;
    private TextView tv_update_time;
    private TextView tv_position_name;
    private TextView tv_area;
    private TextView tv_salary;
    private TextView tv_intro_me;
    private TextView tv_hosted_id;
    private TextView tv_look;
    private TimelineLayout timeline_work;
    private TimelineLayout timeline_edu;
    private TextView tv_username;
    private int resume_id;
    private int position;
    private int is_collect=-1;
    private ResumeDetailPresenter presenter;
    private ResumeDetailBean bean;

    private boolean isCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumedetail);

        Bundle extras = getIntent().getExtras();
        if (extras==null) {
            finish();
        } else {
            resume_id = extras.getInt(RESUME_ID);
            position = extras.getInt(Define.POSITION,-1);
        }

        initView();
        initToolbar();
        presenter = new ResumeDetailPresenter(this);
        presenter.initData(getAccessToken(),resume_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode==PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            isCall = true;
//            presenter.initData(getAccessToken(),resume_id);
//        }
    }

    private void initToolbar() {
        toolbar_iv_back.setOnClickListener(this);
        toolbar_iv_title.setText("简历详情");
        toolbar_iv_right.setVisibility(View.VISIBLE);
        toolbar_iv_right.setImageResource(R.mipmap.icon_star);
        toolbar_iv_right.setOnClickListener(this);
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        civ_header = (CircleImageView) findViewById(R.id.civ_header);
        iv_sex = (ImageView) findViewById(R.id.iv_sex);
        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_edu = (TextView) findViewById(R.id.tv_edu);
        tv_work_year = (TextView) findViewById(R.id.tv_work_year);
        tv_update_time = (TextView) findViewById(R.id.tv_update_time);
        tv_position_name = (TextView) findViewById(R.id.tv_position_name);
        tv_area = (TextView) findViewById(R.id.tv_area);
        tv_salary = (TextView) findViewById(R.id.tv_salary);
        tv_intro_me = (TextView) findViewById(R.id.tv_intro_me);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        tv_look = (TextView) findViewById(R.id.tv_look);
        timeline_work = (TimelineLayout) findViewById(R.id.timeline_work);
        timeline_edu = (TimelineLayout) findViewById(R.id.timeline_edu);
        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_look.setOnClickListener(this);
    }

    /**
     * 获取简历详情成功
     * @param bean 数据
     */
    @Override
    public void initDataSuccess(ResumeDetailBean bean) {
        this.bean = bean;
        x.image().bind(civ_header, bean.getUser_avatar_url(), ImageUtils.defaulHeader());
        if (bean.getSex() == 1) {
            iv_sex.setImageResource(R.mipmap.icon_male);
        } else {
            iv_sex.setImageResource(R.mipmap.icon_famale);
        }
        tv_username.setText(bean.getTrue_name());
        tv_age.setText(bean.getAge() + "岁");
        tv_edu.setText(bean.getEducation_name());
        tv_work_year.setText(bean.getWork_year_name());
        tv_update_time.setText("更新时间：" + DateTimeFormatUtil.dateTimeFormatString(bean.getUpdate_time()));
        tv_position_name.setText(bean.getExpected_position());
        tv_area.setText(bean.getWork_area());
        tv_salary.setText(bean.getSalary_show());

        List<ResumeDetailBean.WorkExperienceListBean> work_experience_list = bean.getWork_experience_list();
        if (work_experience_list!=null) {
            for (ResumeDetailBean.WorkExperienceListBean listBean : work_experience_list) {
                View view = LayoutInflater.from(this).inflate(R.layout.view_work_experience, timeline_work, false);
                TextView timeline_point = (TextView) view.findViewById(R.id.timeline_point);
                String start_date = listBean.getStart_date();
                String end_date = listBean.getEnd_date();
                String timeText = DateUtil.replaceTime_(start_date) + "-" + DateUtil.replaceTime_(end_date);
                timeline_point.setText(timeText);
                TextView tv_position_name = (TextView) view.findViewById(R.id.tv_position_name);
                tv_position_name.setText(listBean.getPosition());
                TextView tv_company_name = (TextView) view.findViewById(R.id.tv_company_name);
                tv_company_name.setText(listBean.getCompany_name());
                timeline_work.addView(view);
            }
        }
        List<ResumeDetailBean.EducationListBean> education_list = bean.getEducation_list();
        if (education_list!=null) {
            for (ResumeDetailBean.EducationListBean listBean : education_list) {
                View view = LayoutInflater.from(this).inflate(R.layout.view_education_experience, timeline_edu, false);
                TextView timeline_point = (TextView) view.findViewById(R.id.timeline_point);
                String start_date = listBean.getStart_date();
                String end_date = listBean.getEnd_date();
                String timeText =  DateUtil.replaceTime_(start_date) + "-" + DateUtil.replaceTime_(end_date);
                timeline_point.setText(timeText);
                TextView tv_school_name = (TextView) view.findViewById(R.id.tv_school_name);
                tv_school_name.setText(listBean.getSchool_name());
                TextView tv_profession = (TextView) view.findViewById(R.id.tv_profession);
                tv_profession.setText(listBean.getMajor());
                timeline_edu.addView(view);
            }
        }

        tv_intro_me.setText(bean.getSelf_intro());
        tv_hosted_id.setText(bean.getHosting_show());

        if (bean.getIs_collect()==0) {
            toolbar_iv_right.setImageResource(R.mipmap.icon_star);
        } else {
            toolbar_iv_right.setImageResource(R.mipmap.icon_star_sol);
        }
        is_collect = bean.getIs_collect();

        if (isCall) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + bean.getMobile_phone());
            intent.setData(uri);
            startActivity(intent);
            isCall = false;
        }
    }

    /**
     * 收藏成功
     */
    @Override
    public void collectionSuccess() {
        is_collect = 1;
        toolbar_iv_right.setImageResource(R.mipmap.icon_star_sol);
    }

    /**
     * 取消收藏成功
     */
    @Override
    public void cancelCollectSuccess() {
        is_collect = 0;
        toolbar_iv_right.setImageResource(R.mipmap.icon_star);
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + this.bean.getMobile_phone());
                intent.setData(data);
                startActivity(intent);
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("查看联系方式需要支付费用，继续吗")
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_iv_back:
                Intent data = new Intent();
                Bundle extras = new Bundle();
                extras.putInt(Define.POSITION,(is_collect==1)?-1:position);
                data.putExtras(extras);
                setResult(200, data);
                finish();
                break;
            case R.id.toolbar_iv_right:
                if (isLogin()) {
                    if (bean==null) {
                        return;
                    }
                    if (is_collect==0) {   // 没有收藏，收藏
                        presenter.collect(getAccessToken(),resume_id);
                    } else if (is_collect==1){  // 已经收藏了，取消收藏
                        presenter.cancelCollect(getAccessToken(),resume_id);
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }

                break;
            case R.id.tv_look:
                if (isLogin()) {
                    if (bean==null) {
                        return;
                    }

                    presenter.isCharge(getAccessToken(),resume_id);
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            Intent data = new Intent();
            Bundle extras = new Bundle();
            extras.putInt(Define.POSITION,(is_collect==1)?-1:position);
            data.putExtras(extras);
            setResult(200, data);

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
