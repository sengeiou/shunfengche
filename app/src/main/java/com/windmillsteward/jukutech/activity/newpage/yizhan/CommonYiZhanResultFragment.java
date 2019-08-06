package com.windmillsteward.jukutech.activity.newpage.yizhan;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.model.BecomeMatchModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForCommBaomu;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForJiajiaoModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForLgAndTzg;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForYinyuanModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForZhaopinModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForZhongdgModel;
import com.windmillsteward.jukutech.activity.newpage.model.YinyuanMatchData;
import com.windmillsteward.jukutech.activity.newpage.model.ZhaopinMatchModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYsUesAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYsYesUsePersonFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoUsePersonFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuzhiJianliFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.ZhaopinPositionDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.YiinyuanZeOuActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.YinyuanAddUserInfoActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.YinyuanUseInfoActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.YonggongDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.ZjTzgAddWorkInfoFragment;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.ResUtils;
import com.windmillsteward.jukutech.utils.http.DataCallBack;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 * 驿站所有的提交信息之后显示的结果页面
 */
public class CommonYiZhanResultFragment extends BaseInitFragment {
    public static final String TAG = "CommonYiZhanResultFragment";

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;

    //header
    private CircleImageView mIv_avatar;
    private TextView mTv_name;
    private TextView mTv_desc;
    private TextView mTv_work;
    private View mView_line;
    private TextView mTv_pipei;

    private int page = 1;

    private int roleType;

    public static CommonYiZhanResultFragment newInstance(int roleType) {
        Bundle args = new Bundle();
        args.putInt("roleType", roleType);
        CommonYiZhanResultFragment fragment = new CommonYiZhanResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.layout_only_recyclerview_with_refresh;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        showContentView();

        if (getArguments() != null) {
            roleType = getArguments().getInt("roleType");
        }


        commonRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshPageData();
            }
        });
    }

    @Override
    protected void initData() {
        initAdapter();

        refreshPageData();
    }

    /**
     * 获取头部数据
     */
    private void getData() {
        if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG) {
            //获取劳工 特种工数据
            getCommLaogongData();
        } else if (roleType == PageConfig.TYPE_BAOMU || roleType == PageConfig.TYPE_YUESAO || roleType == PageConfig.TYPE_YUERSAO) {
            //获取保姆 月嫂 育儿嫂的数据
            getCommBaomuData();
        } else if (roleType == PageConfig.TYPE_JIAJIAO) {
            //家教
            getJiajiaoData();
        } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
            //求职招聘
            getQiuzhizhaopinData();
        } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
            //钟点工
            getZhongdgData();
        } else if (roleType == PageConfig.TYPE_YINYUAN) {
            //姻缘
            getYinyuanData();
        }
    }

    @Override
    protected void refreshPageData() {
        //获取数据
        getData();

        getMatchPosition();
    }

    @Override
    public boolean autoRefresh() {
        return true;
    }

    private RecyclerViewAdapter adapter;
    private List list;

    public void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addHeaderView();
        recyclerView.setAdapter(adapter);

        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);

        //事件监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (roleType == CommonYizhanHomeActivity.TYPE_ZHONGJIE || roleType == CommonYizhanHomeActivity.TYPE_TEZHONGGONG) {
                    //劳务中介
                    BecomeMatchModel item = (BecomeMatchModel) list.get(position);
                    ((BackFragmentActivity) getHoldingActivity()).addFragment(
                            YonggongDetailFragment.newInstance(item.getRelate_id()), true, true);
                } else if (roleType == CommonYizhanHomeActivity.TYPE_ZHAOPIN) {
                    ZhaopinMatchModel item = (ZhaopinMatchModel) list.get(position);
                    ((BackFragmentActivity) getHoldingActivity()).addFragment(
                            ZhaopinPositionDetailsFragment.newInstance(item.getJob_resume_id()), true, true);
                } else if (roleType == CommonYizhanHomeActivity.TYPE_JIAJIAO) {
                    BecomeMatchModel item = (BecomeMatchModel) list.get(position);
                    ((BackFragmentActivity) getHoldingActivity()).addFragment(
                            JiajiaoUsePersonFragment.newInstance(item.getLook_for_tutor_id()), true, true);
                } else if (roleType == CommonYizhanHomeActivity.TYPE_BAOMU || roleType == CommonYizhanHomeActivity.TYPE_YUESAO || roleType == CommonYizhanHomeActivity.TYPE_YUERSAO) {
                    BecomeMatchModel item = (BecomeMatchModel) list.get(position);
                    ((BackFragmentActivity) getHoldingActivity()).addFragment(
                            CommBmYsYesUsePersonFragment.newInstance(roleType, item.getRecruitment_id()), true, true);
                } else if (roleType == CommonYizhanHomeActivity.TYPE_ZHONGDIANGONG) {
                    BecomeMatchModel item = (BecomeMatchModel) list.get(position);
                    ((BackFragmentActivity) getHoldingActivity()).addFragment(
                            ZhongdgInfoDetailsFragment.newInstance(item.getHour_matching_id()), true, true);
                } else if (roleType == CommonYizhanHomeActivity.TYPE_YINYUAN) {
                    YinyuanMatchData item = (YinyuanMatchData) list.get(position);
                    Intent intent = new Intent(getActivity(), YinyuanUseInfoActivity.class);
                    intent.putExtra("match_id", item.getMatch_id());
                    getActivity().startActivity(intent);
                }
            }
        });
    }

    /**
     * 获取匹配数据
     */
    private void getMatchPosition() {
        if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG) {
            //获取劳工 特种工 匹配详情
            getCommLaogongMatchData();
        } else if (roleType == PageConfig.TYPE_BAOMU || roleType == PageConfig.TYPE_YUESAO || roleType == PageConfig.TYPE_YUERSAO) {
            //获取保姆 月嫂 育儿嫂匹配信息
            getCommBaomuMatchData();
        } else if (roleType == PageConfig.TYPE_JIAJIAO) {
            //家教
            getJiajiaoMatchData();
        } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
            //求职招聘
            getZhaopinMatchData();
        } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
            //钟点工
            getZhongdgMatchData();
        } else if (roleType == PageConfig.TYPE_YINYUAN) {
            //姻缘
            getYinyuanMatchData();
        }
    }

    /**
     * headerView
     */
    private void addHeaderView() {
        View view = View.inflate(getActivity(), R.layout.header_recyclerview_find_job, null);
        mIv_avatar = (CircleImageView) view.findViewById(R.id.iv_avatar);
        mTv_name = (TextView) view.findViewById(R.id.tv_name);
        mTv_desc = (TextView) view.findViewById(R.id.tv_desc);
        mTv_work = (TextView) view.findViewById(R.id.tv_work);
        mView_line = (View) view.findViewById(R.id.view_line);
        mTv_pipei = (TextView) view.findViewById(R.id.tv_pipei);

        if (roleType == CommonYizhanHomeActivity.TYPE_ZHONGJIE) {
            setMainTitle("我要找工作");
            mTv_work.setText("我要工作");
        } else if (roleType == CommonYizhanHomeActivity.TYPE_ZHAOPIN) {
            setMainTitle("我要求职");
            mTv_work.setText("个人资料");
        } else if (roleType == CommonYizhanHomeActivity.TYPE_YINYUAN) {
            setMainTitle("我要找对象");
            mTv_work.setText("个人资料");
        } else if (roleType == CommonYizhanHomeActivity.TYPE_JIAJIAO) {
            setMainTitle("我要当家教");
            mTv_work.setText("个人资料");
        } else if (roleType == CommonYizhanHomeActivity.TYPE_BAOMU) {
            setMainTitle("我要当保姆");
            mTv_work.setText("个人资料");
        } else if (roleType == CommonYizhanHomeActivity.TYPE_YUESAO) {
            setMainTitle("我要当月嫂");
            mTv_work.setText("个人资料");
        } else if (roleType == CommonYizhanHomeActivity.TYPE_YUERSAO) {
            setMainTitle("我要当育儿嫂");
            mTv_work.setText("个人资料");
        } else if (roleType == CommonYizhanHomeActivity.TYPE_ZHONGDIANGONG) {
            setMainTitle("我要当钟点工");
            mTv_work.setText("我要工作");
        } else if (roleType == CommonYizhanHomeActivity.TYPE_TEZHONGGONG) {
            setMainTitle("我要当特种工");
            mTv_work.setText("我要工作");
        }

        adapter.setHeaderView(view);
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<Object> data) {
            super(R.layout.item_recyclerview_find_job, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final Object items) {
            if (items instanceof BecomeMatchModel) {
                BecomeMatchModel item = (BecomeMatchModel) items;
                //劳工 特种工 保姆 月嫂 育儿嫂
                if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG) {
                    //获取劳工 特种工数据
                    helper.setText(R.id.tv_name, item.getTitle())
                            .setText(R.id.tv_time, item.getUpdate_time())
                            .setText(R.id.tv_location, item.getWork_second_area_name() + item.getWork_third_area_name());
                } else if (roleType == PageConfig.TYPE_BAOMU || roleType == PageConfig.TYPE_YUESAO || roleType == PageConfig.TYPE_YUERSAO) {
                    //获取保姆 月嫂 育儿嫂的数据
                    helper.setText(R.id.tv_name, item.getTitle())
                            .setText(R.id.tv_time, DateUtil.toYYYYMMDD(item.getAdd_time() * 1000))
                            .setText(R.id.tv_location, item.getArea_name());
                } else if (roleType == PageConfig.TYPE_JIAJIAO) {
                    //获取家教
                    helper.setText(R.id.tv_name, item.getTitle())
                            .setText(R.id.tv_time, DateUtil.toYYYYMMDD(item.getAdd_time() * 1000))
                            .setText(R.id.tv_location, item.getArea_name());
                } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
                    //获取求职招聘
                    helper.setText(R.id.tv_name, item.getJob_class_id_two_name())
                            .setText(R.id.tv_time, item.getUpdate_time())
                            .setText(R.id.tv_location, item.getWork_area_name());
                } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
                    //获取钟点工
                    helper.setText(R.id.tv_name, item.getTitle())
                            .setText(R.id.tv_time, item.getUpdate_time())
                            .setText(R.id.tv_location, item.getWork_second_area_name() + item.getWork_third_area_name());
                }
            } else if (items instanceof ZhaopinMatchModel) {
                //求职招聘
                ZhaopinMatchModel item = (ZhaopinMatchModel) items;
                helper.setText(R.id.tv_name, item.getTitle())
                        .setText(R.id.tv_time, item.getUpdate_time())
                        .setText(R.id.tv_location, item.getWork_area_name());
            } else if (items instanceof YinyuanMatchData) {
                //求职招聘
                YinyuanMatchData item = (YinyuanMatchData) items;
                helper.getView(R.id.iv_avatar).setVisibility(View.VISIBLE);
                GlideUtil.show(getActivity(), item.getUser_avatar_url(), (ImageView) helper.getView(R.id.iv_avatar));

                helper.setText(R.id.tv_name, item.getUser_name() + "    " + item.getSex_name() + "|" + item.getAge() + "岁")
                        .setText(R.id.tv_location, item.getArea_name());

                //设置匹配度
                ((TextView) helper.getView(R.id.tv_time)).setText(new SpanUtils().append("匹配度")
                        .append(item.getMatch_value() + "%")
                        .setForegroundColor(ResUtils.getCommRed())
                        .create());
            }
        }
    }

    /**
     * 获取劳工 特种工 匹配详情
     */
    private void getCommLaogongMatchData() {
        int type = 0;
        if (roleType == PageConfig.TYPE_ZHONGJIE) {
            type = 0;
        } else {
            type = 1;
        }
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MATCH_POSITIONE)
                .addParams("type", (type + 1) + "")
                .setCallBackData(callBack).buildPost());
    }

    private DataCallBack callBack = new BaseNewNetModelimpl<BecomeMatchModel>() {
        @Override
        protected void onFail(int type, String msg) {
            showTips(msg);
            commonRefresh.refreshComplete();
        }

        @Override
        protected void onSuccess(int code, BaseResultInfo<BecomeMatchModel> respnse, String source) {
            commonRefresh.refreshComplete();
            if (respnse.getData() != null) {
                if (page == 1) {
                    list.clear();
                }
                list.add(respnse.getData());
                adapter.loadMoreEnd();
                adapter.notifyDataSetChanged();
            } else {
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        protected Type getType() {
            return new TypeToken<BaseResultInfo<BecomeMatchModel>>() {
            }.getType();
        }
    };

    /**
     * 获取家教的匹配信息
     */
    private void getJiajiaoMatchData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MATCH_LOOKFOR_TUTOR)
                .setCallBackData(callBack)
                .buildPost()
        );
    }

    /**
     * 获取求职招聘的匹配的数据
     */
    private void getZhaopinMatchData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_GET_JOB_LIST)
                .setCallBackData(new BaseNewNetModelimpl<List<ZhaopinMatchModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        commonRefresh.refreshComplete();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<ZhaopinMatchModel>> respnse, String source) {
                        commonRefresh.refreshComplete();
                        if (respnse.getData() != null) {
                            if (page == 1) {
                                list.clear();
                            }
                            list.addAll(respnse.getData());
                            adapter.loadMoreEnd();
                            adapter.notifyDataSetChanged();
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<ZhaopinMatchModel>>>() {
                        }.getType();
                    }
                })
                .buildPost()
        );
    }

    /**
     * 获取钟点工招聘的信息
     */
    private void getZhongdgMatchData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MATCH_POSITION_ZDG)
                .setCallBackData(callBack)
                .buildPost()
        );
    }

    /**
     * 获取姻缘的信息
     */
    private void getYinyuanMatchData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MARRIAGE_MATCH_LIST)
                .setCallBackData(new BaseNewNetModelimpl<List<YinyuanMatchData>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        commonRefresh.refreshComplete();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<YinyuanMatchData>> respnse, String source) {
                        commonRefresh.refreshComplete();
                        if (respnse.getData() != null) {
                            if (page == 1) {
                                list.clear();
                            }
                            list.addAll(respnse.getData());
                            adapter.loadMoreEnd();
                            adapter.notifyDataSetChanged();
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<YinyuanMatchData>>>() {
                        }.getType();
                    }
                })
                .buildPost()
        );
    }


    /**
     * 获取保姆 月嫂 育儿嫂匹配信息
     */
    private void getCommBaomuMatchData() {
        //保姆 月嫂 育儿嫂
        int require_type = 0;
        switch (roleType) {
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
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MATCH_RECRUITMENT)
                .addParams("require_type", require_type + "")
                .setCallBackData(callBack)
                .buildPost()
        );
    }

    /**
     * 获取劳工 特种工数据
     */
    private void getCommLaogongData() {
        int type = 0;
        if (roleType == PageConfig.TYPE_ZHONGJIE) {
            type = 0;
        } else {
            type = 1;
        }
        addCall(new NetUtil().setUrl(APIS.URL_LABOR_LAST_APPLY_DATA)
                .addParams("type", (type + 1) + "")
                .setCallBackData(new BaseNewNetModelimpl<LastPublicForLgAndTzg>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        commonRefresh.refreshComplete();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LastPublicForLgAndTzg> respnse, String source) {
                        commonRefresh.refreshComplete();
                        if (respnse.getData() != null && respnse.getData().getIs_posted() != 0) {
                            LastPublicForLgAndTzg.RecordBean record = respnse.getData().getRecord();
                            GlideUtil.show(getActivity(), record.getUser_avatar_url(), mIv_avatar);
                            mTv_name.setText(record.getName());

                            try {
                                mTv_desc.setText((record.getSex() == 1 ? "男" : "女") + "  |  " +
                                        record.getAge() + "岁");
                            } catch (Exception e) {

                            }

//                            mTv_work.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    ((BackFragmentActivity) getActivity()).addFragment
//                                            (ZjTzgAddWorkInfoFragment.newInstance(ZjTzgAddWorkInfoFragment.TYPE_EDIT,
//                                                    roleType,false), true, true);
//                                }
//                            });
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LastPublicForLgAndTzg>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    //家教
    private void getJiajiaoData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_QUERY_LAST_REQUIRE_JIAJIAO)
                .setCallBackData(new BaseNewNetModelimpl<LastPublicForJiajiaoModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LastPublicForJiajiaoModel> respnse, String source) {
                        if (respnse != null) {
                            LastPublicForJiajiaoModel.RecordBean record = respnse.getData().getRecord();
                            GlideUtil.show(getActivity(), record.getUser_avatar_url(), mIv_avatar);
                            mTv_name.setText(record.getUser_name());

                            try {
                                mTv_desc.setText((record.getSex() == 1 ? "男" : "女") + "  |  " +
                                        record.getAge() + "岁");
                            } catch (Exception e) {

                            }

//                            mTv_work.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    ((BackFragmentActivity) getActivity()).addFragment
//                                            (JiajiaoAddUserInfoFragment.newInstance(JiajiaoAddUserInfoFragment.TYPE_EDIT,
//                                                    roleType, false), true, true);
//                                }
//                            });
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LastPublicForJiajiaoModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    //获取姻缘信息
    private void getYinyuanData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_LOOKING_FOR_OBJECTS)
                .setCallBackData(new BaseNewNetModelimpl<LastPublicForYinyuanModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LastPublicForYinyuanModel> respnse, String source) {
                        if (respnse != null) {
                            LastPublicForYinyuanModel.RecordBean record = respnse.getData().getRecord();
                            GlideUtil.show(getActivity(), record.getUser_avatar_url(), mIv_avatar);
                            mTv_name.setText(record.getUser_name());

                            try {
                                mTv_desc.setText((record.getSex() == 1 ? "男" : "女") + "  |  " +
                                        record.getAge() + "岁");
                            } catch (Exception e) {

                            }

                            mTv_work.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("type", YiinyuanZeOuActivity.TYPE_EDIT);
                                    startAtvDonFinish(YinyuanAddUserInfoActivity.class, bundle);
                                }
                            });
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LastPublicForYinyuanModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 获取钟点工信息
     */
    private void getZhongdgData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_APPLICATION_INFO)
                .setCallBackData(new BaseNewNetModelimpl<LastPublicForZhongdgModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LastPublicForZhongdgModel> respnse, String source) {
                        if (respnse != null) {
                            LastPublicForZhongdgModel.RecordBean record = respnse.getData().getRecord();
                            GlideUtil.show(getActivity(), record.getUser_avatar_url(), mIv_avatar);
                            mTv_name.setText(record.getName());

                            try {
                                mTv_desc.setText((record.getSex() == 1 ? "男" : "女") + "  |  " +
                                        record.getAge() + "岁");
                            } catch (Exception e) {

                            }

//                            mTv_work.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    ((BackFragmentActivity) getActivity()).addFragment
//                                            (ZhongdgAddUserInfoFragment.newInstance(ZhongdgAddUserInfoFragment.TYPE_EDIT,
//                                                    roleType, false), true, true);
//                                }
//                            });
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

    /**
     * 获取求职招聘的数据
     */
    private void getQiuzhizhaopinData() {
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
                            LastPublicForZhaopinModel.RecordBean record = respnse.getData().getRecord();
                            GlideUtil.show(getActivity(), respnse.getData().getUser_avatar_url(), mIv_avatar);
                            mTv_name.setText(record.getTrue_name());

                            try {
                                mTv_desc.setText((record.getSex() == 1 ? "男" : "女") + "  |  " +
                                        record.getAge() + "岁");
                            } catch (Exception e) {

                            }

                            mTv_work.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ((BackFragmentActivity) getActivity()).addFragment
                                            (QiuzhiJianliFragment.newInstance(0,QiuzhiJianliFragment.TYPE_EDIT,
                                                    roleType, false), true, true);
                                }
                            });
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LastPublicForZhaopinModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 获取保姆 月嫂 育儿嫂数据
     */
    private void getCommBaomuData() {
        //保姆 月嫂 育儿嫂
        int require_type = 0;
        switch (roleType) {
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
                        commonRefresh.refreshComplete();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LastPublicForCommBaomu> respnse, String source) {
                        commonRefresh.refreshComplete();
                        if (respnse != null) {
                            if (respnse.getData() != null && respnse.getData().getIs_posted() != 0) {
                                LastPublicForCommBaomu.RecordBean record = respnse.getData().getRecord();
                                GlideUtil.show(getActivity(), record.getUser_avatar_url(), mIv_avatar);
                                mTv_name.setText(record.getUser_name());

                                try {
                                    mTv_desc.setText((record.getSex() == 1 ? "男" : "女") + "  |  " +
                                            record.getAge() + "岁");
                                } catch (Exception e) {
                                }
                                mTv_work.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ((BackFragmentActivity) getActivity()).addFragment
                                                (CommBmYsUesAddUserInfoFragment.newInstance(0,CommBmYsUesAddUserInfoFragment.TYPE_EDIT,
                                                        roleType, false), true, true);
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LastPublicForCommBaomu>>() {
                        }.getType();
                    }
                }).buildPost());
    }
}
