package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.search.SearchItemActivity;
import com.windmillsteward.jukutech.activity.home.personnel.fragment.PositionFragment;
import com.windmillsteward.jukutech.activity.home.personnel.fragment.ResumeFragment;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.TalentInnListPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.BusinessAuthenticationActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.customview.dialog.SelectTwoDialog;

/**
 * 描述：人才驿站
 * 时间：2018/1/8
 * 作者：xjh
 */

public class TalentInnListActivity extends BaseActivity implements View.OnClickListener, TalentInnListView {

    public static final String CURR_POSITION = "CURR_POSITION";
    public static final String CURR_CLASS = "CURR_CLASS";
    public static final String KEYWORD = "KEYWORD";

    private TextView tvPostJob;  // 标题栏发布
    private ImageView ivBack;    // 返回
    private LinearLayout linearSearch;  // 搜索
    private TextView tvSearchHint;  // 搜索提示
    private LinearLayout linearPosition;  // 职位
    private LinearLayout linearResume;   // 简历
    private FrameLayout flContent;
    private ImageView ivPosition;
    private TextView tvPosition;
    private ImageView ivResume;
    private TextView tvResume;



    private TalentInnListPresenter presenter;

    // 当前选中的fragment
    private int curr_position;
    // 由外部传过来的分类
    private int curr_class;
    //搜索页面过来的关键字
    private String keyword = "";
    private PositionFragment positionFragment;
    private ResumeFragment resumeFragment;

    /**
     * 跳转
     *
     * @param context
     * @param keyword 搜索关键字
     * @param curr_position 需要显示的tab
     */
    public static void go(Context context, String keyword,int curr_position) {
        Intent intent = new Intent(context, TalentInnListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(KEYWORD, keyword);
        bundle.putInt(CURR_POSITION, curr_position);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talentinnlist);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            curr_position = extras.getInt(CURR_POSITION);
            curr_class = extras.getInt(CURR_CLASS);
            keyword = extras.getString(KEYWORD,"");
        }
        initView();
        initToolbar();

        presenter = new TalentInnListPresenter(this,this);
    }

    private void initView() {
        tvPostJob = (TextView) findViewById(R.id.tv_postJob);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        linearSearch = (LinearLayout) findViewById(R.id.linear_search);
        tvSearchHint = (TextView) findViewById(R.id.tv_searchHint);
        linearPosition = (LinearLayout) findViewById(R.id.linear_position);
        linearResume = (LinearLayout) findViewById(R.id.linear_resume);
        flContent = (FrameLayout) findViewById(R.id.fl_content);
        ivPosition = (ImageView) findViewById(R.id.iv_position);
        tvPosition = (TextView) findViewById(R.id.tv_position);
        ivResume = (ImageView) findViewById(R.id.iv_resume);
        tvResume = (TextView) findViewById(R.id.tv_resume);


        linearPosition.setOnClickListener(this);
        linearResume.setOnClickListener(this);
        setParamInt(R.id.fl_content);
        positionFragment = PositionFragment.getInstance(keyword, curr_class);
        resumeFragment = ResumeFragment.getInstance(0, keyword, curr_class);
        // 设置当前选中的fragment
        if (curr_position==0) {
            startFragment(null,positionFragment);
        } else if (curr_position==1){
            startFragment(null,resumeFragment);
        }
        setTabStatus();

    }

    private void initToolbar() {
        handleBackEvent(ivBack);
        linearSearch.setOnClickListener(this);
        tvPostJob.setOnClickListener(this);
        tvPostJob.setVisibility(View.VISIBLE);
        tvPostJob.setText("发布求职");
        tvSearchHint.setText(getString(R.string.search_position));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.linear_search:
                if (curr_position == 0) {
                    SearchItemActivity.go(TalentInnListActivity.this,0,0);
                } else if (curr_position == 1) {
                    SearchItemActivity.go(TalentInnListActivity.this,1,0);
                }
                break;
            case R.id.tv_postJob:
                if (isLogin()) {
                    presenter.isAuthen(getAccessToken(),curr_position);
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.linear_position: // 切换到职位fragment
                if (curr_position == 0) {
                    return;
                }
                curr_position = 0;
                startFragment(null,positionFragment);
                setTabStatus();
                break;
            case R.id.linear_resume:  // 切换到简历fragment
                if (curr_position == 1) {
                    return;
                }
                curr_position = 1;
                startFragment(null,resumeFragment);
                setTabStatus();
                break;
            default:
                break;
        }
    }

    private void setTabStatus() {
        if (curr_position==0) {
            tvSearchHint.setText(TextUtils.isEmpty(keyword)?getString(R.string.search_position):keyword);
            tvPostJob.setText("发布求职");
            ivPosition.setImageResource(R.mipmap.icon_work);
            tvPosition.setTextColor(ContextCompat.getColor(this,R.color.color_them));
            ivResume.setImageResource(R.mipmap.icon_pro_n);
            tvResume.setTextColor(ContextCompat.getColor(this,R.color.text_color_black));
        } else if (curr_position==1) {
            tvSearchHint.setText(TextUtils.isEmpty(keyword)?getString(R.string.search_resume):keyword);
            tvPostJob.setText("发布招聘");
            ivPosition.setImageResource(R.mipmap.icon_work_n);
            tvPosition.setTextColor(ContextCompat.getColor(this,R.color.text_color_black));
            ivResume.setImageResource(R.mipmap.icon_pro);
            tvResume.setTextColor(ContextCompat.getColor(this,R.color.color_them));
        }

    }




    /**
     * 获取认证状态
     * @param bean
     */
    @Override
    public void isAuthen(AuthenResultBean bean) {
        if (bean!=null) {
            if (bean.getIs_authen()==1) {
                if (curr_position == 0) {
                    startAtvDonFinish(PublishJobWantedActivity.class);
                    positionFragment.needRefresh = true;
                } else if (curr_position == 1) {
                    startAtvDonFinish(CretePositionActivity.class);
                    resumeFragment.needRefresh = true;
                }
            } else if (bean.getIs_authen()==0){
                if (curr_position == 0) {
                    startAtvDonFinish(PersonalAuthenticationActivity.class);
                } else if (curr_position == 1) {
                    SelectTwoDialog selectTwoDialog = new SelectTwoDialog(TalentInnListActivity.this, 0, new SelectTwoDialog.DialogListner() {
                        @Override
                        public void selectDialogIndex(int action, int positon) {
                            if (positon==1) {
                                startAtvDonFinish(BusinessAuthenticationActivity.class);
                            } else if (positon==2){
                                startAtvDonFinish(PersonalAuthenticationActivity.class);
                            }
                        }
                    }, "企业认证", "个人认证", "取消");
                    selectTwoDialog.showAtBottom();
                }
            } else if (bean.getIs_authen()==2) {
                showTips("客服正在审核认证信息，审核完成后可发布信息",1);
            }
        }
    }
}
