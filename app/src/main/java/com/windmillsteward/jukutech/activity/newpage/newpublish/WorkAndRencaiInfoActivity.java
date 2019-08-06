package com.windmillsteward.jukutech.activity.newpage.newpublish;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.YinyuanAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.ZichanRenzhenFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgCreateRecruitFragment;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.adapter.TabAdapter;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.utils.DateUtil;
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
public class WorkAndRencaiInfoActivity extends BaseInitActivity {

    public static final int TYPE_ZHONGJIE = 0;
    public static final int TYPE_ZHAOPIN = 1;
    public static final int TYPE_YINYUAN = 2;
    public static final int TYPE_JIAJIAO = 3;
    public static final int TYPE_BAOMU = 4;
    public static final int TYPE_YUESAO = 5;
    public static final int TYPE_YUERSAO = 6;
    public static final int TYPE_ZHONGDIANGONG = 7;
    public static final int TYPE_TEZHONGGONG = 8;


    @Bind(R.id.flyBanner)
    FlyBanner flyBanner;
    @Bind(R.id.iv_publish)
    ImageView ivPublish;
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

    //添加 or 编辑
    public int is_qz_pipei;

    public static final String MODULE_TYPE = "module_type";//模块编号

    private CommonBannerPresenter commonBannerPresenter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_new_home_common_publish;
    }

    @Override
    protected void initView(View view) {
        showContentView();
        flyBanner.setPoinstPosition(FlyBanner.RIGHT);
        flyBanner.setPointPadding(0, 0, GraphicUtil.dp2px(this, 10), GraphicUtil.dp2px(this, 45));
        if (getIntent() != null) {
            moduleType = getIntent().getIntExtra(MODULE_TYPE, 0);
            if (moduleType == TYPE_ZHAOPIN) {
                setMainTitle("求职招聘");
                tvLeft.setText("招聘单位列表");
                tvRight.setText("求职人员列表");
            } else if (moduleType == TYPE_ZHONGJIE) {
                setMainTitle("劳务中介");
                tvLeft.setText("用人单位列表");
                tvRight.setText("劳务人员列表");
            } else if (moduleType == TYPE_YINYUAN) {
                setMainTitle("姻缘服务");
                tvLeft.setText("我要找对象");
                tvRight.setText("资产认证");
            } else if (moduleType == TYPE_JIAJIAO) {
                setMainTitle("家教");
                tvLeft.setText("招聘家教列表");
                tvRight.setText("家教人员列表");
            } else if (moduleType == TYPE_BAOMU) {
                setMainTitle("保姆");
                tvLeft.setText("招聘保姆列表");
                tvRight.setText("保姆人员列表");
            } else if (moduleType == TYPE_YUESAO) {
                setMainTitle("月嫂");
                tvLeft.setText("招聘月嫂列表");
                tvRight.setText("月嫂人员列表");

            } else if (moduleType == TYPE_YUERSAO) {
                setMainTitle("育儿嫂");
                tvLeft.setText("招聘育儿嫂列表");
                tvRight.setText("育儿嫂人员列表");

            } else if (moduleType == TYPE_ZHONGDIANGONG) {
                setMainTitle("钟点工");
                tvLeft.setText("用人单位列表");
                tvRight.setText("劳务人员列表");

            } else if (moduleType == TYPE_TEZHONGGONG) {
                setMainTitle("特种工");
                tvLeft.setText("用人单位列表");
                tvRight.setText("劳务人员列表");
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
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_WORK,PageConfig.TYPE_ZHONGJIE));
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_RENCAI,PageConfig.TYPE_ZHONGJIE));
        } else if (moduleType == TYPE_ZHAOPIN) {
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_WORK,PageConfig.TYPE_ZHAOPIN));
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_RENCAI,PageConfig.TYPE_ZHAOPIN));
        } else if (moduleType == TYPE_YINYUAN) {
            fragments.add(YinyuanAddUserInfoFragment.newInstance(is_qz_pipei, PageConfig.TYPE_YINYUAN, true));
            fragments.add(ZichanRenzhenFragment.newInstance(true));
        } else if (moduleType == TYPE_JIAJIAO) {
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_WORK,PageConfig.TYPE_JIAJIAO));
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_RENCAI,PageConfig.TYPE_JIAJIAO));
        } else if (moduleType == TYPE_BAOMU) {
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_WORK,PageConfig.TYPE_BAOMU));
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_RENCAI,PageConfig.TYPE_BAOMU));
        } else if (moduleType == TYPE_YUESAO) {
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_WORK,PageConfig.TYPE_YUESAO));
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_RENCAI,PageConfig.TYPE_YUESAO));
        } else if (moduleType == TYPE_YUERSAO) {
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_WORK,PageConfig.TYPE_YUERSAO));
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_RENCAI,PageConfig.TYPE_YUERSAO));
        } else if (moduleType == TYPE_ZHONGDIANGONG) {
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_WORK,PageConfig.TYPE_ZHONGDIANGONG));
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_RENCAI,PageConfig.TYPE_ZHONGDIANGONG));
        } else if (moduleType == TYPE_TEZHONGGONG) {
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_WORK,PageConfig.TYPE_TEZHONGGONG));
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_RENCAI,PageConfig.TYPE_TEZHONGGONG));
        }

        tabAdapter = new TabAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(tabAdapter);

        viewpager.setCurrentItem(0);
        getBannerData();

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
                    ViewWrap.setUpFlyBanner(WorkAndRencaiInfoActivity.this, pics, urls, flyBanner);
                }
            }
        });
    }
    int currentItem = 0;

    @OnClick({R.id.tv_left, R.id.tv_right, R.id.iv_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                if (viewpager.getCurrentItem() != 0) {
                    viewpager.setCurrentItem(0);
                    currentItem = 0;
                }
                break;
            case R.id.tv_right:
                if (viewpager.getCurrentItem() != 1) {
                    viewpager.setCurrentItem(1);
                    currentItem = 1;
                }
                break;
            case R.id.iv_publish:
//                showDialog();
//                int currentItem = viewpager.getCurrentItem();
                Intent intent = new Intent(this, HomeCommonPublishActivity.class);
                intent.putExtra(HomeCommonPublishActivity.SHOW_PAGE, currentItem+1);
                intent.putExtra(HomeCommonPublishActivity.MODULE_TYPE, moduleType);
                startActivity(intent);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        startActivity(intent);
//                        dismiss();
//                    }
//                },500);
                break;
        }
    }

    public LinearLayout getRootView() {
        return lay_ll_root;
    }

    @Override
    public boolean autoRefresh() {
        return true;
    }


}
