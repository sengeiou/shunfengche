package com.windmillsteward.jukutech.activity.newpage.newpublish;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.common.model.CommonBannerModel;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonBannerPresenter;
import com.windmillsteward.jukutech.activity.newpage.me.TuiguangFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.TouSuFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYsUesAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYsYesCreateRecruitFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoCreateRecruitFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.FubuQiuzhiZhaopinFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuzhiJianliFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.YinyuanAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.ZichanRenzhenFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgCreateRecruitFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.ZjTzgAddWorkInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.ZjTzgFabuZhaopinFragment;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.adapter.TabAdapter;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.utils.GraphicUtil;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 描述：首页点击进去的新发布页面
 * author:JK
 * 2019/2/20
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */
public class HomeCommonPublishActivity extends BaseInitActivity {

    public static final int TYPE_ZHONGJIE = 0;
    public static final int TYPE_ZHAOPIN = 1;
    public static final int TYPE_YINYUAN = 2;
    public static final int TYPE_JIAJIAO = 3;
    public static final int TYPE_BAOMU = 4;
    public static final int TYPE_YUESAO = 5;
    public static final int TYPE_YUERSAO = 6;
    public static final int TYPE_ZHONGDIANGONG = 7;
    public static final int TYPE_TEZHONGGONG = 8;

    public static final int YINGPIN = 1;
    public static final int ZHAOPIN = 2;


    @Bind(R.id.flyBanner)
    FlyBanner flyBanner;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ll_indicator_container)
    LinearLayout llIndicatorContainer;
    @Bind(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.lay_ll_root)
    LinearLayout lay_ll_root;
    @Bind(R.id.lay_ll_tab_header)
    LinearLayout layLlTabHeader;
    private TabAdapter tabAdapter;
    private List<BaseInitFragment> fragments;

    //类型
    private int moduleType;
    //显示哪一个page
    private int show_page;

    public int is_zp_or_yp;
    public int qz_id;

    //    public static final String IS_ZP_OR_YP = "is_zp_or_yp";//是否强制匹配
    public static final String MODULE_TYPE = "module_type";//模块编号
    public static final String SHOW_PAGE = "show_page";//显示应聘还是招聘，1应聘2招聘
    public static final String QZ_ID = "qz_id";//强制匹配ID


    private CommonBannerPresenter commonBannerPresenter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home_common_publish;
    }

    @Override
    protected void initView(View view) {

        flyBanner.setPoinstPosition(FlyBanner.RIGHT);
        flyBanner.setPointPadding(0, 0, GraphicUtil.dp2px(this, 10), GraphicUtil.dp2px(this, 45));
        if (getIntent() != null) {
            moduleType = getIntent().getIntExtra(MODULE_TYPE, PageConfig.TYPE_ZHAOPIN);
            show_page = getIntent().getIntExtra(SHOW_PAGE, 1);
            qz_id = getIntent().getIntExtra(QZ_ID, 0);
            if (moduleType == TYPE_ZHAOPIN) {
                setMainTitle("求职招聘");
                tvLeft.setText("我要求职");
                tvRight.setText("我要招聘");
            } else if (moduleType == TYPE_ZHONGJIE) {
                setMainTitle("劳务中介");
                tvLeft.setText("我要找工作");
                tvRight.setText("我要找人");
            } else if (moduleType == TYPE_YINYUAN) {
                setMainTitle("姻缘服务");
                tvLeft.setText("我要找对象");
                tvRight.setText("资产认证");
            } else if (moduleType == TYPE_JIAJIAO) {
                setMainTitle("家教");
                tvLeft.setText("我要当家教");
                tvRight.setText("我要找家教");
            } else if (moduleType == TYPE_BAOMU) {
                setMainTitle("保姆");
                tvLeft.setText("我要当保姆");
                tvRight.setText("我要找保姆");
            } else if (moduleType == TYPE_YUESAO) {
                setMainTitle("月嫂");
                tvLeft.setText("我要当月嫂");
                tvRight.setText("我要找月嫂");
            } else if (moduleType == TYPE_YUERSAO) {
                setMainTitle("育儿嫂");
                tvLeft.setText("我要当育儿嫂");
                tvRight.setText("我要找育儿嫂");
            } else if (moduleType == TYPE_ZHONGDIANGONG) {
                setMainTitle("钟点工");
                tvLeft.setText("我要当钟点工");
                tvRight.setText("我要找钟点工");
            } else if (moduleType == TYPE_TEZHONGGONG) {
                setMainTitle("特种工");
                tvLeft.setText("我要当特种工");
                tvRight.setText("我要找特种工");
            }
        }

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    tvLeft.setTextColor(Color.parseColor("#3172f4"));
                    tvRight.setTextColor(Color.parseColor("#333333"));
                    tvLeft.setBackgroundResource(R.mipmap.icon_rencai_home_tab_s);
                    tvRight.setBackgroundResource(R.mipmap.icon_rencai_home_tab_n);
                } else {
                    tvLeft.setTextColor(Color.parseColor("#333333"));
                    tvRight.setTextColor(Color.parseColor("#3172f4"));
                    tvRight.setBackgroundResource(R.mipmap.icon_rencai_home_tab_s);
                    tvLeft.setBackgroundResource(R.mipmap.icon_rencai_home_tab_n);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        TextView leftBackView = getLeftBackView();
//        leftBackView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final BaseDialog baseDialog = new BaseDialog(HomeCommonPublishActivity.this);
//                baseDialog.showTwoButton("温馨提示", "确定放弃编辑，放弃将不会保存您编辑的内容", "确定", "取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        baseDialog.dismiss();
//                        finish();
//                    }
//                }, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        baseDialog.dismiss();
//                    }
//                });
//            }
//        });


    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @Override
    protected void initData() {
        commonBannerPresenter = new CommonBannerPresenter(this);
        fragments = new ArrayList<>();
        if (moduleType == TYPE_ZHONGJIE) {
            fragments.add(ZjTzgAddWorkInfoFragment.newInstance(qz_id, show_page, PageConfig.TYPE_ZHONGJIE, true));
            fragments.add(ZjTzgFabuZhaopinFragment.newInstance(qz_id, show_page, PageConfig.TYPE_ZHONGJIE, true));
        } else if (moduleType == TYPE_ZHAOPIN) {
//            fragments.add(new TouSuFragment());
//            fragments.add(new TuiguangFragment());
            fragments.add(QiuzhiJianliFragment.newInstance(qz_id, show_page, PageConfig.TYPE_ZHAOPIN, true));
            fragments.add(FubuQiuzhiZhaopinFragment.newInstance(qz_id, show_page, PageConfig.TYPE_ZHAOPIN, true));
        } else if (moduleType == TYPE_YINYUAN) {
            fragments.add(YinyuanAddUserInfoFragment.newInstance(is_zp_or_yp, PageConfig.TYPE_YINYUAN, true));
            fragments.add(ZichanRenzhenFragment.newInstance(true));
        } else if (moduleType == TYPE_JIAJIAO) {
            fragments.add(JiajiaoAddUserInfoFragment.newInstance(qz_id, show_page, PageConfig.TYPE_JIAJIAO, true));
            fragments.add(JiajiaoCreateRecruitFragment.newInstance(qz_id, show_page, PageConfig.TYPE_JIAJIAO, true));
        } else if (moduleType == TYPE_BAOMU) {
            fragments.add(CommBmYsUesAddUserInfoFragment.newInstance(qz_id, show_page, PageConfig.TYPE_BAOMU, true));
            fragments.add(CommBmYsYesCreateRecruitFragment.newInstance(qz_id, show_page, PageConfig.TYPE_BAOMU, true));
        } else if (moduleType == TYPE_YUESAO) {
            fragments.add(CommBmYsUesAddUserInfoFragment.newInstance(qz_id, show_page, PageConfig.TYPE_YUESAO, true));
            fragments.add(CommBmYsYesCreateRecruitFragment.newInstance(qz_id, show_page, PageConfig.TYPE_YUESAO, true));
        } else if (moduleType == TYPE_YUERSAO) {
            fragments.add(CommBmYsUesAddUserInfoFragment.newInstance(qz_id, show_page, PageConfig.TYPE_YUERSAO, true));
            fragments.add(CommBmYsYesCreateRecruitFragment.newInstance(qz_id, show_page, PageConfig.TYPE_YUERSAO, true));
        } else if (moduleType == TYPE_ZHONGDIANGONG) {
            fragments.add(ZhongdgAddUserInfoFragment.newInstance(qz_id,show_page, PageConfig.TYPE_ZHONGDIANGONG, true));
            fragments.add(ZhongdgCreateRecruitFragment.newInstance(qz_id,show_page, PageConfig.TYPE_ZHONGDIANGONG, true));
        } else if (moduleType == TYPE_TEZHONGGONG) {
            fragments.add(ZjTzgAddWorkInfoFragment.newInstance(qz_id, show_page, PageConfig.TYPE_TEZHONGGONG, true));
            fragments.add(ZjTzgFabuZhaopinFragment.newInstance(qz_id, show_page, PageConfig.TYPE_TEZHONGGONG, true));
        }

        tabAdapter = new TabAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(tabAdapter);

        viewpager.setCurrentItem(show_page - 1);
        viewpager.setOffscreenPageLimit(0);
        getBannerData();
        showContentView();
    }


    @Override
    protected void refreshPageData() {

    }

    /**
     * 获取banner信息
     */
    private void getBannerData() {
        int banner_type = 0;
        switch (moduleType) {
            case TYPE_ZHONGJIE:
                banner_type = CommonBannerPresenter.TYPE_LAOWUZHONGJIE;
                break;
            case TYPE_TEZHONGGONG:
                banner_type = CommonBannerPresenter.TYPE_TEZHONGGONG;
                break;
            case TYPE_BAOMU:
                banner_type = CommonBannerPresenter.TYPE_BAOMU;
                break;
            case TYPE_YUESAO:
                banner_type = CommonBannerPresenter.TYPE_YUESAO;
                break;
            case TYPE_YUERSAO:
                banner_type = CommonBannerPresenter.TYPE_YUERSAO;
                break;
            case TYPE_ZHAOPIN:
                banner_type = CommonBannerPresenter.TYPE_QIUZHIZHAOPIN;
                break;
            case TYPE_YINYUAN:
                banner_type = CommonBannerPresenter.TYPE_YINYUANFUWU;
                break;
            case TYPE_JIAJIAO:
                banner_type = CommonBannerPresenter.TYPE_JIAJIAO;
                break;
            case TYPE_ZHONGDIANGONG:
                banner_type = CommonBannerPresenter.TYPE_ZHONGDIANGONG;
                break;
        }
        //获取banner信息
        commonBannerPresenter.loadBannerData(banner_type, new CommonBannerPresenter.DataCallBack() {
            @Override
            public void onFail(int type, String msg) {
                showTips(msg);
            }

            @Override
            public void onSucess(int code, BaseResultInfo<List<CommonBannerModel>> respnse, String source) {
                if (respnse != null && respnse.getData() != null) {
                    List<String> pics = new ArrayList<>();
                    List<String> urls = new ArrayList<>();
                    for (CommonBannerModel commonBannerModel : respnse.getData()) {
                        pics.add(commonBannerModel.getPic_url());
                        urls.add(commonBannerModel.getHref_url());
                    }
                    ViewWrap.setUpFlyBanner(HomeCommonPublishActivity.this, pics, urls, flyBanner);
                }
            }
        });
    }


    @OnClick({R.id.tv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                if (viewpager.getCurrentItem() != 0) {
                    viewpager.setCurrentItem(0);
                }
                break;
            case R.id.tv_right:
                if (viewpager.getCurrentItem() != 1) {
                    viewpager.setCurrentItem(1);
                }
                break;
        }
    }

    public LinearLayout getRootView() {
        return lay_ll_root;
    }

}
