package com.windmillsteward.jukutech.activity.newpage.yizhan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.fragment.WorkAndRencaiInfoFragment;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.ZhongjieHomePresenter;
import com.windmillsteward.jukutech.activity.mine.activity.BusinessAuthenticationActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.common.model.CommonBannerModel;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonBannerPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.HasPublicRectuitmentModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForCommBaomu;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForJiajiaoModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForLgAndTzg;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForYinyuanModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForZhaopinModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForZhongdgModel;
import com.windmillsteward.jukutech.activity.newpage.model.NumModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYsUesAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYsYesCreateRecruitFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoCreateRecruitFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.FubuQiuzhiZhaopinFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuzhiJianliFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.YinyuanAddUserInfoActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.YinyuanInfoListFragment;
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
import com.windmillsteward.jukutech.base.interfaces.OnUserAuthenListener;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.manager.CommonActivityManager;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import top.wuhaojie.library.MultiScrollNumber;

/**
 * 劳务中介,求职招聘,姻缘服务的首页
 */
public class CommonYizhanHomeActivity extends BaseInitActivity {
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
    @Bind(R.id.left_container)
    LinearLayout leftContainer;
    @Bind(R.id.container_right)
    LinearLayout containerRight;
    @Bind(R.id.ll_indicator_container)
    LinearLayout ll_indicator_container;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.scroll_number_right1)
    MultiScrollNumber scroll_number_right1;
    @Bind(R.id.scroll_number_right2)
    MultiScrollNumber scroll_number_right2;
    @Bind(R.id.scroll_number_right3)
    MultiScrollNumber scroll_number_right3;
    @Bind(R.id.scroll_number_right4)
    MultiScrollNumber scroll_number_right4;
    @Bind(R.id.scroll_number_right5)
    MultiScrollNumber scroll_number_right5;
    @Bind(R.id.scroll_number_right6)
    MultiScrollNumber scroll_number_right6;
    @Bind(R.id.scroll_number_left1)
    MultiScrollNumber scroll_number_left1;
    @Bind(R.id.scroll_number_left2)
    MultiScrollNumber scroll_number_left2;
    @Bind(R.id.scroll_number_left3)
    MultiScrollNumber scroll_number_left3;
    @Bind(R.id.scroll_number_left4)
    MultiScrollNumber scroll_number_left4;
    @Bind(R.id.scroll_number_left5)
    MultiScrollNumber scroll_number_left5;
    @Bind(R.id.scroll_number_left6)
    MultiScrollNumber scroll_number_left6;
    @Bind(R.id.tv_left_tips)
    TextView tvLeftTips;
    @Bind(R.id.tv_right_tips)
    TextView tvRightTips;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout common_refresh;
    private TabAdapter tabAdapter;
    private List<BaseInitFragment> fragments;

    //类型
    private int type;

    private CommonBannerPresenter commonBannerPresenter;

    //劳务工和特种工状态model
    private LastPublicForLgAndTzg lastPublicForLgAndTzg;
    //保姆月嫂育儿嫂状态model
    private LastPublicForCommBaomu lastPublicForCommBaomu;
    //姻缘发布状态model
    private LastPublicForYinyuanModel lastPublicForYinyuanModel;
    //家教状态model
    private LastPublicForJiajiaoModel lastPublicForJiajiaoModel;
    //求职招聘model
    private LastPublicForZhaopinModel lastPublicForZhaopinModel;
    //钟点工发布model
    private LastPublicForZhongdgModel lastPublicForZhongdgModel;
    //招聘信息发布状态
    private HasPublicRectuitmentModel hasPublicRectuitmentModel;

    @Override
    protected void initView(View view) {
        showContentView();

        if (getIntent() != null) {
            type = getIntent().getIntExtra("type", 0);

            if (type == TYPE_ZHAOPIN) {
                setMainTitle("求职招聘");
                tvLeftTips.setText("我要求职");
                tvRightTips.setText("我要招聘");

                leftContainer.setBackgroundResource(R.drawable.shape_comm_yizhan_qzzp);
                containerRight.setBackgroundResource(R.drawable.shape_comm_yizhan_qzzp);
            } else if (type == TYPE_ZHONGJIE) {
                setMainTitle("劳务中介");
                tvLeftTips.setText("我要找工作");
                tvRightTips.setText("我要找人");

                leftContainer.setBackgroundResource(R.drawable.shape_comm_yizhan_lwzj);
                containerRight.setBackgroundResource(R.drawable.shape_comm_yizhan_lwzj);
            } else if (type == TYPE_YINYUAN) {
                setMainTitle("姻缘服务");
                tvLeftTips.setText("我要找对象");
                tvRightTips.setText("资产认证");
                ll_indicator_container.setVisibility(View.GONE);

                leftContainer.setBackgroundResource(R.drawable.shape_comm_yizhan_yyfw);
                containerRight.setBackgroundResource(R.drawable.shape_comm_yizhan_yyfw);
            } else if (type == TYPE_JIAJIAO) {
                setMainTitle("家教");
                tvLeftTips.setText("我要当家教");
                tvRightTips.setText("我要找家教");

                leftContainer.setBackgroundResource(R.drawable.shape_comm_yizhan_jj);
                containerRight.setBackgroundResource(R.drawable.shape_comm_yizhan_jj);
            } else if (type == TYPE_BAOMU) {
                setMainTitle("保姆");
                tvLeftTips.setText("我要当保姆");
                tvRightTips.setText("我要找保姆");

                leftContainer.setBackgroundResource(R.drawable.shape_comm_yizhan_bm);
                containerRight.setBackgroundResource(R.drawable.shape_comm_yizhan_bm);
            } else if (type == TYPE_YUESAO) {
                setMainTitle("月嫂");
                tvLeftTips.setText("我要当月嫂");
                tvRightTips.setText("我要找月嫂");

                leftContainer.setBackgroundResource(R.drawable.shape_comm_yizhan_ys);
                containerRight.setBackgroundResource(R.drawable.shape_comm_yizhan_ys);
            } else if (type == TYPE_YUERSAO) {
                setMainTitle("育儿嫂");
                tvLeftTips.setText("我要当育儿嫂");
                tvRightTips.setText("我要找育儿嫂");

                leftContainer.setBackgroundResource(R.drawable.shape_comm_yizhan_yes);
                containerRight.setBackgroundResource(R.drawable.shape_comm_yizhan_yes);
            } else if (type == TYPE_ZHONGDIANGONG) {
                setMainTitle("钟点工");
                tvLeftTips.setText("我要当钟点工");
                tvRightTips.setText("我要找钟点工");

                leftContainer.setBackgroundResource(R.drawable.shape_comm_yizhan_zdg);
                containerRight.setBackgroundResource(R.drawable.shape_comm_yizhan_zdg);
            } else if (type == TYPE_TEZHONGGONG) {
                setMainTitle("特种工");
                tvLeftTips.setText("我要当特种工");
                tvRightTips.setText("我要找特种工");

                leftContainer.setBackgroundResource(R.drawable.shape_comm_yizhan_tzg);
                containerRight.setBackgroundResource(R.drawable.shape_comm_yizhan_tzg);
            }
        }



//        setMainRightIvRes(R.mipmap.icon_service_gray);
//        setOnTitleAreaCliclkListener(new OnTitleAreaCliclkListener() {
//            @Override
//            public void onTitleAreaClickListener(View view) {
//                if (view.getId() == R.id.iv_right) {
//                    startAtvDonFinish(CustomerDetailActivity.class);
//                }
//            }
//        });

        //获取滚动数据
        getNumData();

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

        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshPageData();
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    common_refresh.setEnabled(true);
                } else {
                    common_refresh.setEnabled(false);
                }
            }
        });
    }

    //获取滚动数据
    private void getNumData() {
        new NetUtil().setUrl(APIS.URL_TALENT_INITIAL_VALUE)
                .setCallBackData(new BaseNewNetModelimpl<NumModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<NumModel> respnse, String source) {
                        if (respnse != null) {
                            NumModel data = respnse.getData();
                            if (data != null) {
                                int initial_value = data.getInitial_value();
                                int initial_value_start = data.getInitial_value_start();
                                int initial_value_end = data.getInitial_value_end();
                                Random random = new Random();
                                int randomLeft = random.nextInt(initial_value_end - initial_value_start) + initial_value_start;
                                int randomRight = random.nextInt(initial_value_end - initial_value_start) + initial_value_start;

                                //处理左边
                                int left = initial_value + randomLeft;
                                int right = initial_value + randomRight;

                                setScrollView(scroll_number_left1, left / 100000);
                                setScrollView(scroll_number_left2, left / 10000 % 10);
                                setScrollView(scroll_number_left3, left / 1000 % 10);
                                setScrollView(scroll_number_left4, left / 100 % 10);
                                setScrollView(scroll_number_left5, left / 10 % 10);
                                setScrollView(scroll_number_left6, left % 10);

                                setScrollView(scroll_number_right1, right / 100000);
                                setScrollView(scroll_number_right2, right / 10000 % 10);
                                setScrollView(scroll_number_right3, right / 1000 % 10);
                                setScrollView(scroll_number_right4, right / 100 % 10);
                                setScrollView(scroll_number_right5, right / 10 % 10);
                                setScrollView(scroll_number_right6, right % 10);
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<NumModel>>() {
                        }.getType();
                    }
                }).buildPost();
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

        scroll_number_right.setTextSize(22);
        scroll_number_right.setTextColors(new int[]{R.color.white});
        scroll_number_right.setScrollVelocity(24);
    }

    /**
     * 获取状态信息
     */
    private void getStateInfo(int type) {
        if (type == PageConfig.TYPE_ZHONGJIE || type == TYPE_TEZHONGGONG) {
            //查询最后一次发布劳务工/特种工信息
            addCall(new NetUtil().setUrl(APIS.URL_LABOR_LAST_APPLY_DATA)
                    .addParams("type", "" + (type == PageConfig.TYPE_ZHONGJIE ? 1 : 2))
                    .setCallBackData(new BaseNewNetModelimpl<LastPublicForLgAndTzg>() {
                        @Override
                        protected void onFail(int type, String msg) {
                            showTips(msg);
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo<LastPublicForLgAndTzg> respnse, String source) {
                            if (respnse != null) {
                                lastPublicForLgAndTzg = respnse.getData();
                            }
                        }

                        @Override
                        protected Type getType() {
                            return new TypeToken<BaseResultInfo<LastPublicForLgAndTzg>>() {
                            }.getType();
                        }
                    }).buildPost());
        } else if (type == PageConfig.TYPE_BAOMU || type == PageConfig.TYPE_YUESAO || type == PageConfig.TYPE_YUERSAO) {
            //保姆 月嫂 育儿嫂
            int require_type = 0;
            switch (type) {
                case PageConfig.TYPE_BAOMU:
                    require_type = 1;
                    break;
                case PageConfig.TYPE_YUESAO:
                    require_type = 2;
                    break;
                case PageConfig.TYPE_YUERSAO:
                    require_type = 3;
                    break;
            }
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_QUERY_LAST_REQUIRE)
                    .addParams("require_type", "" + require_type)
                    .setCallBackData(new BaseNewNetModelimpl<LastPublicForCommBaomu>() {
                        @Override
                        protected void onFail(int type, String msg) {
                            showTips(msg);
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo<LastPublicForCommBaomu> respnse, String source) {
                            if (respnse != null) {
                                lastPublicForCommBaomu = respnse.getData();
                            }
                        }

                        @Override
                        protected Type getType() {
                            return new TypeToken<BaseResultInfo<LastPublicForCommBaomu>>() {
                            }.getType();
                        }
                    }).buildPost());
        } else if (type == PageConfig.TYPE_YINYUAN) {
            //姻缘
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_LOOKING_FOR_OBJECTS)
                    .setCallBackData(new BaseNewNetModelimpl<LastPublicForYinyuanModel>() {
                        @Override
                        protected void onFail(int type, String msg) {
                            showTips(msg);
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo<LastPublicForYinyuanModel> respnse, String source) {
                            if (respnse != null) {
                                lastPublicForYinyuanModel = respnse.getData();
                            }
                        }

                        @Override
                        protected Type getType() {
                            return new TypeToken<BaseResultInfo<LastPublicForYinyuanModel>>() {
                            }.getType();
                        }
                    }).buildPost()
            );
        } else if (type == PageConfig.TYPE_JIAJIAO) {
            //家教
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_QUERY_LAST_REQUIRE_JIAJIAO)
                    .setCallBackData(new BaseNewNetModelimpl<LastPublicForJiajiaoModel>() {
                        @Override
                        protected void onFail(int type, String msg) {
                            showTips(msg);
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo<LastPublicForJiajiaoModel> respnse, String source) {
                            if (respnse != null) {
                                lastPublicForJiajiaoModel = respnse.getData();
                            }
                        }

                        @Override
                        protected Type getType() {
                            return new TypeToken<BaseResultInfo<LastPublicForJiajiaoModel>>() {
                            }.getType();
                        }
                    }).buildPost()
            );
        } else if (type == PageConfig.TYPE_ZHAOPIN) {
            //求职招聘
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_JOB_WANTED)
                    .setCallBackData(new BaseNewNetModelimpl<LastPublicForZhaopinModel>() {
                        @Override
                        protected void onFail(int type, String msg) {
                            showTips(msg);
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo<LastPublicForZhaopinModel> respnse, String source) {
                            if (respnse != null) {
                                lastPublicForZhaopinModel = respnse.getData();
                            }
                        }

                        @Override
                        protected Type getType() {
                            return new TypeToken<BaseResultInfo<LastPublicForZhaopinModel>>() {
                            }.getType();
                        }
                    }).buildPost()
            );
        } else if (type == PageConfig.TYPE_ZHONGDIANGONG) {
            //钟点工
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_APPLICATION_INFO)
                    .setCallBackData(new BaseNewNetModelimpl<LastPublicForZhongdgModel>() {
                        @Override
                        protected void onFail(int type, String msg) {
                            showTips(msg);
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo<LastPublicForZhongdgModel> respnse, String source) {
                            if (respnse != null) {
                                lastPublicForZhongdgModel = respnse.getData();
                            }
                        }

                        @Override
                        protected Type getType() {
                            return new TypeToken<BaseResultInfo<LastPublicForZhongdgModel>>() {
                            }.getType();
                        }
                    }).buildPost()
            );
        }

        //查询是否发布过招聘信息
        //type 1.劳务中介-我要找人 ，2.求职招聘-我要招聘 3.姻缘服务-资产认证，4.家教-我要找家教 5.保姆-我要找保姆 6.月嫂-我要找月嫂 * 7.育儿嫂-我要找育儿嫂 8.钟点工-我要找钟点工 9.我要找特种工
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_IS_RECRUITMENT)
                .addParams("type", (type + 1) + "")
                .setCallBackData(new BaseNewNetModelimpl<HasPublicRectuitmentModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<HasPublicRectuitmentModel> respnse, String source) {
                        if (respnse != null) {
                            hasPublicRectuitmentModel = respnse.getData();
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
     * 获取banner信息
     */
    private void getBannerData() {
        int banner_type = 0;
        switch (type) {
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
                if (common_refresh != null)
                    common_refresh.refreshComplete();
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
                    ViewWrap.setUpFlyBanner(CommonYizhanHomeActivity.this, pics, urls, flyBanner);
                }
                if (common_refresh != null)
                    common_refresh.refreshComplete();
            }
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return new ZhongjieHomePresenter();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_zhongjie_home;
    }

    @Override
    protected void initData() {
        commonBannerPresenter = new CommonBannerPresenter(this);
        fragments = new ArrayList<>();

        if (type == TYPE_YINYUAN) {
            fragments.add(new YinyuanInfoListFragment());
        } else {
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_WORK, type));
            fragments.add(WorkAndRencaiInfoFragment.newInstance(WorkAndRencaiInfoFragment.TYPE_RENCAI, type));
        }

        tabAdapter = new TabAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(tabAdapter);

        getCommonData();
    }

    private void getCommonData() {
        getBannerData();

        //获取信息
        if (TextUtils.isEmpty(getAccessToken())) {
            return;
        }
        getStateInfo(type);
    }

    @Override
    protected void refreshPageData() {
        getCommonData();
        for (BaseInitFragment fragment : fragments) {
            if (fragment instanceof WorkAndRencaiInfoFragment) {
                ((WorkAndRencaiInfoFragment) fragment).refresh();
            }
            if (fragment instanceof YinyuanInfoListFragment) {
                ((YinyuanInfoListFragment) fragment).refresh();
            }
        }
    }

    @OnClick({R.id.left_container, R.id.container_right, R.id.tv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_container:
                checkUserAuthen(new OnUserAuthenListener() {
                    @Override
                    public void isAuthen() {
                        if (type == TYPE_ZHONGJIE || type == TYPE_TEZHONGGONG) {
                            //劳务中介  特种工-个人资料
                            if (lastPublicForLgAndTzg == null || lastPublicForLgAndTzg.getIs_posted() == 0) {
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", ZjTzgAddWorkInfoFragment.TYPE_ADD);
                                bundle.putInt("roleType", type);
                                startManagerActivity(CommonActivityManager.class, ZjTzgAddWorkInfoFragment.TAG, bundle);
                            } else {
                                //发布过
                                Bundle bundle = new Bundle();
                                bundle.putInt("roleType", type);
                                startManagerActivity(CommonActivityManager.class, CommonYiZhanResultFragment.TAG, bundle);
                            }
                        } else if (type == TYPE_ZHAOPIN) {
                            //求职招聘
                            if (lastPublicForZhaopinModel == null || lastPublicForZhaopinModel.getIs_posted() == 0) {
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", QiuzhiJianliFragment.TYPE_ADD);
                                bundle.putInt("roleType", type);
                                startManagerActivity(CommonActivityManager.class, QiuzhiJianliFragment.TAG, bundle);
                            } else {
                                //发布过
                                Bundle bundle = new Bundle();
                                bundle.putInt("roleType", type);
                                startManagerActivity(CommonActivityManager.class, CommonYiZhanResultFragment.TAG, bundle);
                            }
                        } else if (type == TYPE_YINYUAN) {
                            //姻缘服务-个人资料
                            if (lastPublicForYinyuanModel == null || lastPublicForYinyuanModel.getIs_posted() == 0) {
                                startAtvDonFinish(YinyuanAddUserInfoActivity.class);
                            } else {
                                //发布过
                                Bundle bundle = new Bundle();
                                bundle.putInt("roleType", type);
                                startManagerActivity(CommonActivityManager.class, CommonYiZhanResultFragment.TAG, bundle);
                            }
                        } else if (type == TYPE_JIAJIAO) {
                            if (lastPublicForJiajiaoModel == null || lastPublicForJiajiaoModel.getIs_posted() == 0) {
                                //家教-个人资料
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", JiajiaoAddUserInfoFragment.TYPE_ADD);
                                bundle.putInt("roleType", type);
                                bundle.putString("username", lastPublicForJiajiaoModel.getUser_name());
                                bundle.putString("phone", lastPublicForJiajiaoModel.getMobile_phone());
                                startManagerActivity(CommonActivityManager.class, JiajiaoAddUserInfoFragment.TAG, bundle);
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putInt("roleType", type);
                                startManagerActivity(CommonActivityManager.class, CommonYiZhanResultFragment.TAG, bundle);
                            }
                        } else if (type == TYPE_BAOMU || type == TYPE_YUESAO || type == TYPE_YUERSAO) {
                            //保姆-个人资料 //月嫂-个人资料 //育儿嫂-个人资料
                            if (lastPublicForCommBaomu == null || lastPublicForCommBaomu.getIs_posted() == 0) {
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", CommBmYsUesAddUserInfoFragment.TYPE_ADD);
                                bundle.putInt("roleType", type);
                                bundle.putString("username", lastPublicForCommBaomu.getUser_name());
                                bundle.putString("phone", lastPublicForCommBaomu.getMobile_phone());
                                startManagerActivity(CommonActivityManager.class, CommBmYsUesAddUserInfoFragment.TAG, bundle);
                            } else {
                                //发布过
                                Bundle bundle = new Bundle();
                                bundle.putInt("roleType", type);
                                startManagerActivity(CommonActivityManager.class, CommonYiZhanResultFragment.TAG, bundle);
                            }
                        } else if (type == TYPE_ZHONGDIANGONG) {
                            //钟点工-发布需求
                            if (lastPublicForZhongdgModel == null || lastPublicForZhongdgModel.getIs_posted() == 0) {
                                //没发布过
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", ZhongdgAddUserInfoFragment.TYPE_ADD);
                                bundle.putInt("roleType", type);
                                startManagerActivity(CommonActivityManager.class, ZhongdgAddUserInfoFragment.TAG, bundle);
                            } else {
                                //发布过
                                Bundle bundle = new Bundle();
                                bundle.putInt("roleType", type);
                                startManagerActivity(CommonActivityManager.class, CommonYiZhanResultFragment.TAG, bundle);
                            }
                        }
                    }

                    @Override
                    public void isNotAuthen() {
                        final BaseDialog baseDialog = new BaseDialog(CommonYizhanHomeActivity.this);
                        baseDialog.showTwoButton("认证信息", "请完善您的认证信息", "取消", "去认证", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //取消
                                baseDialog.dismiss();
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                baseDialog.dismiss();
                                //去认证
                                startAtvDonFinish(PersonalAuthenticationActivity.class);
                            }
                        });
                    }
                });
                break;
            case R.id.container_right:
                checkUserAuthen(new OnUserAuthenListener() {

                    @Override
                    public void isAuthen() {
                        if (type == TYPE_ZHONGJIE || type == TYPE_TEZHONGGONG) {
                            //劳务中介 特种工
                            if (hasPublicRectuitmentModel != null && hasPublicRectuitmentModel.getIs_posted() == 1) {
                                //已发布过
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", type);
                                bundle.putInt("num", hasPublicRectuitmentModel.getCount());
                                startManagerActivity(CommonActivityManager.class, CommHasPublicFragment.TAG, bundle);
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putBoolean("only", false);
                                bundle.putInt("roleType", type);
                                //未发布过
                                startManagerActivity(CommonActivityManager.class, ZjTzgFabuZhaopinFragment.TAG, bundle);
                            }
                        } else if (type == TYPE_ZHAOPIN) {
                            //求职招聘
                            if (hasPublicRectuitmentModel != null && hasPublicRectuitmentModel.getIs_posted() == 1) {
                                //已发布过
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", type);
                                bundle.putInt("num", hasPublicRectuitmentModel.getCount());
                                startManagerActivity(CommonActivityManager.class, CommHasPublicFragment.TAG, bundle);
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putBoolean("only", false);
                                bundle.putInt("roleType", type);
                                //未发布过
                                startManagerActivity(CommonActivityManager.class, FubuQiuzhiZhaopinFragment.TAG, bundle);
                            }
                        } else if (type == TYPE_YINYUAN) {
                            //姻缘服务-资产认证
                            startManagerActivity(CommonActivityManager.class, ZichanRenzhenFragment.TAG);
                        } else if (type == TYPE_JIAJIAO) {
                            //家教-发布需求
                            if (hasPublicRectuitmentModel != null && hasPublicRectuitmentModel.getIs_posted() == 1) {
                                //已发布过
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", type);
                                bundle.putInt("num", hasPublicRectuitmentModel.getCount());
                                startManagerActivity(CommonActivityManager.class, CommHasPublicFragment.TAG, bundle);
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putBoolean("only", false);
                                bundle.putInt("roleType", type);
                                //未发布过
                                startManagerActivity(CommonActivityManager.class, JiajiaoCreateRecruitFragment.TAG, bundle);
                            }
                        } else if (type == TYPE_BAOMU || type == TYPE_YUESAO || type == TYPE_YUERSAO) {
                            //家教-发布需求 //月嫂-发布需求 //育儿嫂-发布需求
                            if (hasPublicRectuitmentModel != null && hasPublicRectuitmentModel.getIs_posted() == 1) {
                                //已发布过
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", type);
                                bundle.putInt("num", hasPublicRectuitmentModel.getCount());
                                startManagerActivity(CommonActivityManager.class, CommHasPublicFragment.TAG, bundle);
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putBoolean("only", false);
                                bundle.putInt("roleType", type);
                                //未发布过
                                startManagerActivity(CommonActivityManager.class, CommBmYsYesCreateRecruitFragment.TAG, bundle);
                            }
                        } else if (type == TYPE_ZHONGDIANGONG) {
                            //钟点工-发布需求
                            if (hasPublicRectuitmentModel != null && hasPublicRectuitmentModel.getIs_posted() == 1) {
                                //已发布过
                                Bundle bundle = new Bundle();
                                bundle.putInt("type", type);
                                bundle.putInt("num", hasPublicRectuitmentModel.getCount());
                                startManagerActivity(CommonActivityManager.class, CommHasPublicFragment.TAG, bundle);
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putBoolean("only", false);
                                bundle.putInt("roleType", type);
                                //未发布过
                                startManagerActivity(CommonActivityManager.class, ZhongdgCreateRecruitFragment.TAG, bundle);
                            }
                        }
                    }

                    @Override
                    public void isNotAuthen() {
                        final BaseDialog baseDialog = new BaseDialog(CommonYizhanHomeActivity.this);
                        baseDialog.showThreeButton("认证信息", "请完善您的认证信息", "取消", "个人认证", "企业认证", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                baseDialog.dismiss();
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                baseDialog.dismiss();
                                startAtvDonFinish(PersonalAuthenticationActivity.class);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                baseDialog.dismiss();
                                startAtvDonFinish(BusinessAuthenticationActivity.class);
                            }
                        });
                    }
                });

                break;
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

    @Override
    public boolean autoRefresh() {
        return true;
    }
}

