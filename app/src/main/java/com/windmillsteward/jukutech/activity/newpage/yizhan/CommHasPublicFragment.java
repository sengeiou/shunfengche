package com.windmillsteward.jukutech.activity.newpage.yizhan;


import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.model.HasPublicRectuitmentModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYsYesCreateRecruitFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoCreateRecruitFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.FubuQiuzhiZhaopinFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgCreateRecruitFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.ZjTzgFabuZhaopinFragment;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.interfaces.APIS;

import java.lang.reflect.Type;

import butterknife.Bind;
import butterknife.OnClick;
import top.wuhaojie.library.MultiScrollNumber;

/**
 * A simple {@link Fragment} subclass.
 * 公共已发布页面
 */
public class CommHasPublicFragment extends BaseInitFragment {
    public static final String TAG = "CommHasPublicFragment";
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.scroll_number_1)
    MultiScrollNumber scroll_number_1;
    @Bind(R.id.scroll_number_2)
    MultiScrollNumber scroll_number_2;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.iv_public)
    RelativeLayout ivPublic;
    @Bind(R.id.iv_public_bg)
    ImageView iv_public_bg;
    @Bind(R.id.ll_header_bg)
    LinearLayout ll_header_bg;

    private int roleType;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_comm_has_public;
    }

    public static CommHasPublicFragment newInstance(int roleType, int num) {
        Bundle args = new Bundle();
        args.putInt("num", num);
        args.putInt("roleType", roleType);
        CommHasPublicFragment fragment = new CommHasPublicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        showContentView();
        hidLineDivider();

        if (getArguments() != null) {
            //角色类型
            roleType = getArguments().getInt("roleType");
            int num = getArguments().getInt("num");
            if (num < 0) {
                //自己去请求数据
                getCountData();
            } else {
                setScrollView(scroll_number_1, num / 10);
                setScrollView(scroll_number_2, num % 10);
            }

            if (roleType == PageConfig.TYPE_ZHONGJIE) {
                setMainTitle("我要找人");
                tvLeft.setText("已发布职位");
                tvRight.setText("已匹配到的简历");

                ll_header_bg.setBackgroundResource(R.mipmap.aim_bg_lwzj);
                iv_public_bg.setImageResource(R.mipmap.btn_bg_lwzj);
            } else if (roleType == PageConfig.TYPE_TEZHONGGONG) {
                setMainTitle("我要找特种工");
                tvLeft.setText("已发布职位");
                tvRight.setText("已匹配到的特种工");

                ll_header_bg.setBackgroundResource(R.mipmap.aim_bg_tzg);
                iv_public_bg.setImageResource(R.mipmap.btn_bg_tzg);
            } else if (roleType == PageConfig.TYPE_BAOMU) {
                setMainTitle("我要找保姆");
                tvLeft.setText("已发布职位");
                tvRight.setText("已匹配到的保姆");

                ll_header_bg.setBackgroundResource(R.mipmap.aim_bg_bm);
                iv_public_bg.setImageResource(R.mipmap.btn_bg_bm);
            } else if (roleType == PageConfig.TYPE_YUESAO) {
                setMainTitle("我要找月嫂");
                tvLeft.setText("已发布职位");
                tvRight.setText("已匹配到的月嫂");

                ll_header_bg.setBackgroundResource(R.mipmap.aim_bg_ys);
                iv_public_bg.setImageResource(R.mipmap.btn_bg_ys);
            } else if (roleType == PageConfig.TYPE_YUERSAO) {
                setMainTitle("我要找育儿嫂");
                tvLeft.setText("已发布职位");
                tvRight.setText("已匹配到的育儿嫂");

                ll_header_bg.setBackgroundResource(R.mipmap.aim_bg_yes);
                iv_public_bg.setImageResource(R.mipmap.btn_bg_yes);
            } else if (roleType == PageConfig.TYPE_JIAJIAO) {
                setMainTitle("我要找家教");
                tvLeft.setText("已发布职位");
                tvRight.setText("已匹配到的家教");

                ll_header_bg.setBackgroundResource(R.mipmap.aim_bg_jj);
                iv_public_bg.setImageResource(R.mipmap.btn_bg_jj);
            } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
                setMainTitle("我要找钟点工");
                tvLeft.setText("已发布职位");
                tvRight.setText("已匹配到的钟点工");

                ll_header_bg.setBackgroundResource(R.mipmap.aim_bg_zdg);
                iv_public_bg.setImageResource(R.mipmap.btn_bg_zdg);
            } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
                setMainTitle("我要招聘");
                tvLeft.setText("已发布职位");
                tvRight.setText("已匹配到的简历");

                ll_header_bg.setBackgroundResource(R.mipmap.aim_bg_zhaopin);
                iv_public_bg.setImageResource(R.mipmap.btn_bg_zhaopin);
            }
        }
    }

    /**
     * 获取数量数据
     */
    private void getCountData() {
        //roleType 1.劳务中介-我要找人 ，2.求职招聘-我要招聘 3.姻缘服务-资产认证，4.家教-我要找家教 5.保姆-我要找保姆 6.月嫂-我要找月嫂 * 7.育儿嫂-我要找育儿嫂 8.钟点工-我要找钟点工 9.我要找特种工
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_IS_RECRUITMENT)
                .addParams("type", (roleType + 1) + "")
                .setCallBackData(new BaseNewNetModelimpl<HasPublicRectuitmentModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<HasPublicRectuitmentModel> respnse, String source) {
                        if (respnse != null && respnse.getData() != null) {
                            int count = respnse.getData().getCount();
                            setScrollView(scroll_number_1, count / 10);
                            setScrollView(scroll_number_2, count % 10);
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<HasPublicRectuitmentModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    /**
     * 设置scrollNum
     *
     * @param scroll_number_right
     */
    private void setScrollView(MultiScrollNumber scroll_number_right, int num) {
        if (num == 0) {
            scroll_number_right.setNumber(0);
        } else {
            scroll_number_right.setNumber(0, num);
        }

        scroll_number_right.setScrollVelocity(24);
        scroll_number_right.setTextSize(36);
        scroll_number_right.setTextColors(new int[]{R.color.white});

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {
        getCountData();
    }

    @OnClick({R.id.tv_left, R.id.tv_right, R.id.iv_public})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                doLeftClick();
                break;
            case R.id.tv_right:
                doRightClick();
                break;
            case R.id.iv_public:
                //重新发布
                rePublic();
                break;
        }
    }

    /**
     * 左边按钮点击
     */
    private void doLeftClick() {
        if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG
                || roleType == PageConfig.TYPE_BAOMU || roleType == PageConfig.TYPE_YUESAO || roleType == PageConfig.TYPE_YUERSAO
                || roleType == PageConfig.TYPE_JIAJIAO || roleType == PageConfig.TYPE_ZHONGDIANGONG
                || roleType == PageConfig.TYPE_ZHAOPIN) {
            //跳转发布招聘页面  保姆 月嫂 育儿嫂 劳工 特种工 家教 钟点工 求职招聘
            ((BackFragmentActivity) getHoldingActivity())
                    .addFragment(HasPublishedPositionFragment.newInstance(roleType), true, true);
        }
    }

    /**
     * 右边按钮点击
     */
    private void doRightClick() {
        if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG
                || roleType == PageConfig.TYPE_BAOMU || roleType == PageConfig.TYPE_YUESAO || roleType == PageConfig.TYPE_YUERSAO
                || roleType == PageConfig.TYPE_JIAJIAO || roleType == PageConfig.TYPE_ZHONGDIANGONG
                || roleType == PageConfig.TYPE_ZHAOPIN) {
            //跳转发布招聘页面 劳工 特种工 保姆 月嫂 育儿嫂 家教 钟点工 求职招聘
            ((BackFragmentActivity) getHoldingActivity())
                    .addFragment(HasMatchedResumeFragment.newInstance(roleType), true, true);
        }
    }

    //发布新的
    private void rePublic() {
//        if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG) {
//            //跳转发布招聘页面
//            ((BackFragmentActivity) getHoldingActivity())
//                    .addFragment(ZjTzgFabuZhaopinFragment.newInstance(true, roleType,false), true, true);
//        } else if (roleType == PageConfig.TYPE_BAOMU || roleType == PageConfig.TYPE_YUESAO || roleType == PageConfig.TYPE_YUERSAO) {
//            //保姆 月嫂 育儿嫂
//            ((BackFragmentActivity) getHoldingActivity())
//                    .addFragment(CommBmYsYesCreateRecruitFragment.newInstance(true, roleType,false), true, true);
//        } else if (roleType == PageConfig.TYPE_JIAJIAO) {
//            //家教
//            ((BackFragmentActivity) getHoldingActivity())
//                    .addFragment(JiajiaoCreateRecruitFragment.newInstance(true, roleType,false), true, true);
//        } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
//            //钟点工
//            ((BackFragmentActivity) getHoldingActivity())
//                    .addFragment(ZhongdgCreateRecruitFragment.newInstance(true, roleType,false), true, true);
//        } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
//            //求职招聘
//            ((BackFragmentActivity) getHoldingActivity())
//                    .addFragment(FubuQiuzhiZhaopinFragment.newInstance(true, roleType,false), true, true);
//        }
    }

    @Override
    public boolean autoRefresh() {
        return true;
    }
}
