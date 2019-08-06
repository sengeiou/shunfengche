package com.windmillsteward.jukutech.activity.newpage.order;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseDetailActivity;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.model.CommonOrderModel;
import com.windmillsteward.jukutech.activity.newpage.model.OrderParentModel;
import com.windmillsteward.jukutech.activity.newpage.smartlife.DingcanOrderDetailActivity;
import com.windmillsteward.jukutech.activity.newpage.smartlife.SmartLifeDetailsActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.BaomuInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYingPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYsYesUsePersonFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmZhaoPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoUsePersonFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoYingpinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoZhaoPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuZhiYingPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuZhiZhaoPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.ZhaopinInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.ZhaopinPositionDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.YinyuanOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.YinyuanUseInfoActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgUsePersonFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.HasLgTzgPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.YonggongDetailFragment;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseMultiItemQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.event.NotifyOrderUnReadCount;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.manager.CommonActivityManager;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
import com.windmillsteward.jukutech.utils.ResUtils;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.TimeUtils;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 * 订单列表页面
 */
public class OrderListFragment extends BaseInitFragment {
    public static final String TAG = "OrderListFragment";
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;

    private int type = 1;

    private int page = 1;
    private int unReadNum;//未读消息数

    public static OrderListFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        OrderListFragment fragment = new OrderListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.layout_only_recyclerview_with_refresh;
    }

    public void refresh() {
        refreshPageData();
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        hidTitleView();
        initAdapter();

        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }

        commonRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshPageData();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            commonRefresh.setBackgroundColor(getActivity().getColor(R.color.color_bg));
        } else {
            commonRefresh.setBackgroundColor(getActivity().getResources().getColor(R.color.color_bg));
        }

    }


    @Override
    protected void initData() {
        getData();
    }

    private void getData() {
        //	状态 1.进行中，2.已完成
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_GET_MATCH_ORDER_LIST_NEW)
                .addParams("page", page + "")
                .addParams("page_count", 10 + "")
                .addParams("status", type + "")
                .setCallBackData(new BaseNewNetModelimpl<OrderParentModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        showErrorView();
                        showTips(msg);
                        commonRefresh.refreshComplete();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<OrderParentModel> respnse, String source) {
                        dismiss();
                        showContentView();
                        commonRefresh.refreshComplete();
                        if (respnse.getData() != null) {
                            OrderParentModel data = respnse.getData();
                            EventBus.getDefault().post(new NotifyOrderUnReadCount(type,data.getUnReadNum(),data.getTotalUnreadNum()));
                            if (data.getList() != null) {
                                if (data.getList().getList() != null) {
                                    if (data.getList().isFirstPage()) {
                                        list.clear();
                                    }
                                    list.addAll(data.getList().getList());
                                    if (data.getList().isLastPage()) {
                                        adapter.loadMoreEnd();
                                    } else {
                                        adapter.loadMoreComplete();
                                    }
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }
                        page++;
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<OrderParentModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }



    @Override
    protected void refreshPageData() {
        page = 1;
        getData();
    }

    private RecyclerViewAdapter adapter;
    private List<CommonOrderModel> list;

    public void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreEndTextBgColor(ResUtils.getColor(R.color.color_bg));
        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);
        //加载更多
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData();
            }
        }, recyclerView);
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                final CommonOrderModel item = list.get(position);
                if (type == 2){
                    final BaseDialog baseDialog = new BaseDialog(getActivity());
                    baseDialog.showTwoButton("提示","是否删除已完成订单","确定","取消",new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            baseDialog.dismiss();
                            deleteOrder(position,item.getMatching_order_id());
                        }
                    },new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            baseDialog.dismiss();
                        }
                    });
                }
                return true;
            }
        });
        //事件监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommonOrderModel item = list.get(position);
                if (item == null) {
                    return;
                }
                //1.智慧生活-抢单人 2.智慧生活-发布人，3.房屋模块，5劳务中介-应聘方，6.劳务中介-招聘方，7.求职招聘-应聘方，
                // 8求职招聘-招聘方 9.姻缘 10.家教-应聘方 11.家教-招聘方 12.保姆-应聘方，13.保姆-招聘方，14.月嫂-应聘方
                // 15.月嫂-招聘方 16.育儿嫂应聘方 17.育儿嫂-招聘方 18.钟点工-应聘方，19.钟点工-招聘方
                // 20.特种工-应聘方，21.特种工-招聘方
                final CommonOrderModel.RecordBean record = item.getRecord();
                if (item.getItemType() == 1) {
                    //智慧生活-抢单人
                    SmartLifeDetailsActivity.go(getActivity(), record.getRequire_id(), record.getLongitude(), record.getLatitude());
                } else if (item.getItemType() == 2) {
                    //智慧生活-发布人
                    SmartLifeDetailsActivity.go(getActivity(), record.getRequire_id(), record.getLongitude(), record.getLatitude());
                } else if (item.getItemType() == 3) {
                    //房屋模块
                    Bundle bundle = new Bundle();
                    bundle.putInt(HouseDetailActivity.DETAIL_ID, record.getHouse_id());
                    startAtvDonFinish(HouseDetailActivity.class, bundle);
                } else if (item.getItemType() == 5) {
                    //劳务中介-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("relate_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, YonggongDetailFragment.TAG, bundle);
                } else if (item.getItemType() == 6) {
                    //劳务中介-招聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("recruitment_id", item.getRelate_id());
                    bundle.putInt("roleType", PageConfig.TYPE_ZHONGJIE);
                    bundle.putInt("showAddress", 1);
                    startManagerActivity(CommonActivityManager.class, HasLgTzgPublishedDetailsFragment.TAG, bundle);
                } else if (item.getItemType() == 7) {
                    //求职招聘-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("job_resume_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, ZhaopinPositionDetailsFragment.TAG, bundle);
                } else if (item.getItemType() == 8) {
                    //求职招聘-招聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("job_resume_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, ZhaopinInfoDetailsFragment.TAG, bundle);
                } else if (item.getItemType() == 9) {
                    //姻缘
                    Intent intent = new Intent(getActivity(), YinyuanUseInfoActivity.class);
                    intent.putExtra("match_id", item.getRelate_id());
                    getActivity().startActivity(intent);
                } else if (item.getItemType() == 10) {
                    //家教-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("look_for_tutor_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, JiajiaoUsePersonFragment.TAG, bundle);
                } else if (item.getItemType() == 11) {
                    //家教-招聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("when_tutor_id", item.getRelate_id());
                    bundle.putInt("showAddress", 1);
                    startManagerActivity(CommonActivityManager.class, JiajiaoInfoDetailsFragment.TAG, bundle);
                } else if (item.getItemType() == 12) {
                    //保姆-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("recruitment_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, CommBmYsYesUsePersonFragment.TAG, bundle);
                } else if (item.getItemType() == 13) {
                    //保姆-招聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("require_id", item.getRelate_id());
                    bundle.putInt("roleType", PageConfig.TYPE_BAOMU);
                    bundle.putInt("showAddress", 1);
                    startManagerActivity(CommonActivityManager.class, BaomuInfoDetailsFragment.TAG, bundle);
                } else if (item.getItemType() == 14) {
                    //月嫂-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("recruitment_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, CommBmYsYesUsePersonFragment.TAG, bundle);
                } else if (item.getItemType() == 15) {
                    //月嫂-招聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("require_id", item.getRelate_id());
                    bundle.putInt("roleType", PageConfig.TYPE_YUESAO);
                    startManagerActivity(CommonActivityManager.class, BaomuInfoDetailsFragment.TAG, bundle);
                } else if (item.getItemType() == 16) {
                    //育儿嫂应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("recruitment_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, CommBmYsYesUsePersonFragment.TAG, bundle);
                } else if (item.getItemType() == 17) {
                    //育儿嫂-招聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("require_id", item.getRelate_id());
                    bundle.putInt("roleType", PageConfig.TYPE_YUERSAO);
                    startManagerActivity(CommonActivityManager.class, BaomuInfoDetailsFragment.TAG, bundle);
                } else if (item.getItemType() == 18) {
                    //钟点工-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("hour_matching_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, ZhongdgInfoDetailsFragment.TAG, bundle);
                } else if (item.getItemType() == 19) {
                    //钟点工-招聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("lookfor_bell_worker_id", item.getRelate_id());
                    bundle.putInt("showAddress", 1);
                    startManagerActivity(CommonActivityManager.class, ZhongdgUsePersonFragment.TAG, bundle);
                } else if (item.getItemType() == 20) {
                    //特种工-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("relate_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, YonggongDetailFragment.TAG, bundle);
                } else if (item.getItemType() == 21) {
                    //特种工-招聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("recruitment_id", item.getRelate_id());
                    bundle.putInt("roleType", PageConfig.TYPE_TEZHONGGONG);
                    bundle.putInt("showAddress", 1);
                    startManagerActivity(CommonActivityManager.class, HasLgTzgPublishedDetailsFragment.TAG, bundle);
                }else if (item.getItemType() == 22) {
                    //劳务工-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("relate_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, YonggongDetailFragment.TAG, bundle);
                }else if (item.getItemType() == 23) {
                    //特种工-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("relate_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, YonggongDetailFragment.TAG, bundle);
                }else if (item.getItemType() == 24) {
                    //钟点工-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("hour_matching_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, ZhongdgInfoDetailsFragment.TAG, bundle);
                }else if (item.getItemType() == 25) {
                    //求职招聘-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("relate_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, QiuZhiYingPinOrderDetailFragment.TAG, bundle);
                }
                else if (item.getItemType() == 26) {
                    //姻缘
                    Bundle bundle = new Bundle();
                    bundle.putInt("relate_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, YinyuanOrderDetailFragment.TAG, bundle);
                }else if (item.getItemType() == 27) {
                    //家教-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("when_tutor_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, JiajiaoYingpinOrderDetailFragment.TAG, bundle);
                }else if (item.getItemType() == 28) {
                    //保姆-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("relate_id", item.getRelate_id());
                    bundle.putInt("roleType", PageConfig.TYPE_BAOMU);
                    startManagerActivity(CommonActivityManager.class, CommBmYingPinOrderDetailFragment.TAG, bundle);
                }else if (item.getItemType() == 29) {
                    //月嫂-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("relate_id", item.getRelate_id());
                    bundle.putInt("roleType", PageConfig.TYPE_YUESAO);
                    startManagerActivity(CommonActivityManager.class, CommBmYingPinOrderDetailFragment.TAG, bundle);
                }else if (item.getItemType() == 30) {
                    //育儿嫂-应聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("relate_id", item.getRelate_id());
                    bundle.putInt("roleType", PageConfig.TYPE_YUERSAO);
                    startManagerActivity(CommonActivityManager.class, CommBmYingPinOrderDetailFragment.TAG, bundle);
                }else if (item.getItemType() == 31) {
                    //求职招聘-招聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("relate_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, QiuZhiZhaoPinOrderDetailFragment.TAG, bundle);
                }else if (item.getItemType() == 32) {
                    //家教-招聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("relate_id", item.getRelate_id());
                    startManagerActivity(CommonActivityManager.class, JiajiaoZhaoPinOrderDetailFragment.TAG, bundle);
                }else if (item.getItemType() == 33) {
                    //保姆-招聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("relate_id", item.getRelate_id());
                    bundle.putInt("roleType", PageConfig.TYPE_BAOMU);
                    startManagerActivity(CommonActivityManager.class, CommBmZhaoPinOrderDetailFragment.TAG, bundle);
                }else if (item.getItemType() == 34) {
                    //月嫂-招聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("relate_id", item.getRelate_id());
                    bundle.putInt("roleType", PageConfig.TYPE_YUESAO);
                    startManagerActivity(CommonActivityManager.class, CommBmZhaoPinOrderDetailFragment.TAG, bundle);
                }else if (item.getItemType() == 35) {
                    //育儿嫂-招聘方
                    Bundle bundle = new Bundle();
                    bundle.putInt("relate_id", item.getRelate_id());
                    bundle.putInt("roleType", PageConfig.TYPE_YUERSAO);
                    startManagerActivity(CommonActivityManager.class, CommBmZhaoPinOrderDetailFragment.TAG, bundle);
                }else if (item.getItemType() == 36 || item.getItemType() == 37 ) {
                    //订餐酒店门票服务
                    DingcanOrderDetailActivity.go(getActivity(),item.getRelate_id(),item.getRecord()==null?0:item.getRecord().getIndex_type());
                }
            }
        });
    }

    class RecyclerViewAdapter extends BaseMultiItemQuickAdapter<CommonOrderModel, BaseViewHolder> {
        public RecyclerViewAdapter(List<CommonOrderModel> data) {
            super(data);
            addItemType(1, R.layout.item_recycler_order_smart_get_order);//
            addItemType(2, R.layout.item_recycler_order_smart_publish);
            addItemType(3, R.layout.item_recycler_order_fw);
            addItemType(5, R.layout.item_recycler_order_lw);//
            addItemType(6, R.layout.item_recycler_order_lw_zpf);//
            addItemType(7, R.layout.item_recycler_order_zp);//
            addItemType(8, R.layout.item_recycler_order_jj_bm_ys_yes_zpf);//
            addItemType(9, R.layout.item_recycler_order_yy);//
            addItemType(10, R.layout.item_recycler_order_jj_bm_ys_yes);
            addItemType(11, R.layout.item_recycler_order_jj_bm_ys_yes_zpf);//
            addItemType(12, R.layout.item_recycler_order_jj_bm_ys_yes);
            addItemType(13, R.layout.item_recycler_order_jj_bm_ys_yes_zpf);//
            addItemType(14, R.layout.item_recycler_order_jj_bm_ys_yes);
            addItemType(15, R.layout.item_recycler_order_jj_bm_ys_yes_zpf);//
            addItemType(16, R.layout.item_recycler_order_jj_bm_ys_yes);
            addItemType(17, R.layout.item_recycler_order_jj_bm_ys_yes_zpf);//
            addItemType(18, R.layout.item_recycler_order_lw);//
            addItemType(19, R.layout.item_recycler_order_lw_zpf);//
            addItemType(20, R.layout.item_recycler_order_lw);//
            addItemType(21, R.layout.item_recycler_order_lw_zpf);//
            addItemType(22, R.layout.item_recycler_order_lw_yingpin);//
            addItemType(23, R.layout.item_recycler_order_lw_yingpin);//
            addItemType(24, R.layout.item_recycler_order_lw_yingpin);//
            addItemType(25, R.layout.item_recycler_order_lw_yingpin);//
            addItemType(26, R.layout.item_recycler_order_lw_yingpin);//
            addItemType(27, R.layout.item_recycler_order_lw_yingpin);//
            addItemType(28, R.layout.item_recycler_order_lw_yingpin);//
            addItemType(29, R.layout.item_recycler_order_lw_yingpin);//
            addItemType(30, R.layout.item_recycler_order_lw_yingpin);//
            addItemType(31, R.layout.item_recycler_order_zhaopin);//
            addItemType(32, R.layout.item_recycler_order_zhaopin);//
            addItemType(33, R.layout.item_recycler_order_zhaopin);//
            addItemType(34, R.layout.item_recycler_order_zhaopin);//
            addItemType(35, R.layout.item_recycler_order_zhaopin);//
            addItemType(36, R.layout.item_recycler_order_dingcan);//
            addItemType(37, R.layout.item_recycler_order_dingcan);//
        }

        @Override
        protected void convert(final BaseViewHolder helper, final CommonOrderModel item) {
            //1.智慧生活-抢单人 2.智慧生活-发布人，3.房屋模块，5劳务中介-应聘方，
            // 6.劳务中介-招聘方，7.求职招聘-应聘方，8求职招聘-招聘方 9.姻缘
            // 10.家教-应聘方 11.家教-招聘方 12.保姆-应聘方，13.保姆-招聘方，
            // 14.月嫂-应聘方 15.月嫂-招聘方 16.育儿嫂应聘方 17.育儿嫂-招聘方
            // 18.钟点工-应聘方，19.钟点工-招聘方 20.特种工-应聘方，21.特种工-招聘方
            final CommonOrderModel.RecordBean record = item.getRecord();
            int read = item.getRead();
            int order_status = record.getStatus();
            if (read == 0){
                helper.setVisible(R.id.tv_hongdian,true);
            }else{
                helper.setVisible(R.id.tv_hongdian,false);
            }
            if (item.getItemType() == 1) {
                //1.智慧生活-抢单人 2.智慧生活-发布人
                //1：等待接单，2：已接单，3：已完成
                String status = "";
                int color = 0;
                switch (record.getStatus()) {
                    case 1:
                        status = "等待接单";
                        color = Color.parseColor("#36acf0");
                        break;
                    case 2:
                        status = "已接单";
                        color = Color.parseColor("#46cdbc");
                        break;
                    case 3:
                        status = "已完成";
                        color = Color.parseColor("#f79c59");
                        break;
                }
                helper.setText(R.id.tv_title, record.getTitle())
                        .setText(R.id.tv_left, record.getArea_name())
                        .setText(R.id.tv_time, TimeUtils.formatMillToYYYYMMDD(record.getAdd_time() * 1000));
                ((TextView) helper.getView(R.id.tv_status)).setText(status);
                ((TextView) helper.getView(R.id.tv_status)).setTextColor(color);

                helper.getView(R.id.ll_info).setVisibility(View.VISIBLE);

                helper.setText(R.id.tv_info, "联系人：" + record.getUser_name());
                helper.setText(R.id.tv_info_tel, "联系电话：" + record.getMobile_phone());
                helper.getView(R.id.iv_phone).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final BaseDialog baseDialog = new BaseDialog(getActivity());
                        baseDialog.showTwoButton("提示", "是否拨打以下电话" + "\n" + record.getMobile_phone(), "确定", "取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                baseDialog.dismiss();
                                PhoneUtils.dial(record.getMobile_phone());
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                baseDialog.dismiss();
                            }
                        });
                    }
                });
                helper.getView(R.id.tv_right_bottom).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commCompleteJob(helper.getPosition(), item.getItemType(), record.getRequire_id());
                    }
                });
                helper.getView(R.id.tv_right_top).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (StringUtil.isAllNotEmpty(record.getLongitude(), record.getLatitude())) {
                            MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(record.getLongitude()), Double.parseDouble(record.getLatitude()), record.getAddress());
                        }
                    }
                });
                if (record.getIs_push_msg() == 1) {
                    helper.getView(R.id.tv_right_bottom).setBackground(null);
                    ((TextView) helper.getView(R.id.tv_right_bottom)).setTextColor(Color.parseColor("#666666"));
                    ((TextView) helper.getView(R.id.tv_right_bottom)).setText("已通知用人单位");
                } else {
                    helper.getView(R.id.tv_right_bottom).setBackgroundResource(R.drawable.shape_bg_blue_60bdd1);
                    ((TextView) helper.getView(R.id.tv_right_bottom)).setTextColor(Color.parseColor("#3172f4"));
                    ((TextView) helper.getView(R.id.tv_right_bottom)).setText("确认完成工作");
                }
                if (record.getStatus() == 3) {
                    helper.getView(R.id.tv_right_bottom).setVisibility(View.GONE);
                }

            } else if (item.getItemType() == 2) {
                //1.智慧生活-抢单人 2.智慧生活-发布人
                //1：等待接单，2：已接单，3：已完成
                String status = "";
                int color = 0;
                switch (record.getStatus()) {
                    case 1:
                        status = "等待接单";
                        color = Color.parseColor("#36acf0");
                        break;
                    case 2:
                        status = "已接单";
                        color = Color.parseColor("#46cdbc");
                        break;
                    case 3:
                        status = "已完成";
                        color = Color.parseColor("#f79c59");
                        break;
                }
                helper.setText(R.id.tv_title, record.getTitle())
                        .setText(R.id.tv_left, record.getArea_name())
                        .setText(R.id.tv_time, TimeUtils.formatMillToYYYYMMDD(record.getAdd_time() * 1000));
                ((TextView) helper.getView(R.id.tv_status)).setText(status);
                ((TextView) helper.getView(R.id.tv_status)).setTextColor(color);

                if (record.getStatus() == 2) {
                    //已接单
                    helper.getView(R.id.ll_info).setVisibility(View.VISIBLE);

                    helper.setText(R.id.tv_info, "联系人：" + record.getUser_name());
                    helper.setText(R.id.tv_info_tel, "联系电话：" + record.getMobile_phone());
                    helper.getView(R.id.iv_phone).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final BaseDialog baseDialog = new BaseDialog(getActivity());
                            baseDialog.showTwoButton("提示", "是否拨打以下电话" + "\n" + record.getMobile_phone(), "确定", "取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    baseDialog.dismiss();
                                    PhoneUtils.dial(record.getMobile_phone());
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    baseDialog.dismiss();
                                }
                            });
                        }
                    });
                    helper.getView(R.id.tv_info).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final BaseDialog baseDialog = new BaseDialog(getActivity());
                            baseDialog.showTwoButton("提示", "是否拨打以下电话" + "\n" + record.getMobile_phone(), "确定", "取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    baseDialog.dismiss();
                                    PhoneUtils.dial(record.getMobile_phone());
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    baseDialog.dismiss();
                                }
                            });
                        }
                    });
                    int is_complaint = record.getIs_complaint();
                    if (is_complaint == 1) {//已投诉，隐藏支付按钮
                        helper.getView(R.id.tv_right_bottom).setVisibility(View.GONE);
                    } else {
                        helper.getView(R.id.tv_right_bottom).setVisibility(View.VISIBLE);
                    }

                    helper.getView(R.id.tv_right_bottom).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zhshConfirmPay(helper.getPosition(), record.getRequire_id());
                        }
                    });
                    helper.getView(R.id.tv_right_bottom).setVisibility(View.GONE); //接单人已经不需要确认支付按钮了
                } else {
                    helper.getView(R.id.ll_info).setVisibility(View.GONE);
                }
            } else if (item.getItemType() == 3) {
                helper.setText(R.id.tv_title, record.getTitle())
                        .setText(R.id.tv_left, record.getArea_name())
                        .setText(R.id.tv_time, record.getUpdate_time());
            } else if (item.getItemType() == 5 || item.getItemType() == 20 || item.getItemType() == 18) {
                String areaName = "";
                if (item.getItemType() == 5) {
                    //劳务中介-应聘方
                    helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_lwzj);
                    ((TextView) helper.getView(R.id.tv_type)).setText("劳务工订单");
                    areaName = record.getAddress();
                } else if (item.getItemType() == 20) {
                    //特种工-应聘方
                    helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_tzg);
                    ((TextView) helper.getView(R.id.tv_type)).setText("特种工订单");
                    areaName = record.getAddress();
                } else if (item.getItemType() == 18) {
                    //钟点工-应聘方
                    helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_zdg);
                    ((TextView) helper.getView(R.id.tv_type)).setText("钟点工订单");
                    areaName = record.getArea_name();
                }
                String info = "联系人：" + record.getContact_person() + "\n联系电话：" + record.getContact_tel();
                helper.setText(R.id.tv_title, record.getTitle())
                        .setText(R.id.tv_left, info)
                        .setText(R.id.tv_time, record.getUpdate_time());

                helper.setText(R.id.tv_area, areaName);

                helper.getView(R.id.tv_right_top).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (StringUtil.isAllNotEmpty(record.getLongitude(), record.getLatitude())) {
                            MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(record.getLongitude()), Double.parseDouble(record.getLatitude()), record.getAddress());
                        }
                    }
                });

                //是否确认完成 0否 1.是
                if (record.getConfirm_complete() == 0) {
                    helper.getView(R.id.tv_right_bottom).setBackgroundResource(R.drawable.shape_bg_blue_60bdd1);
                    ((TextView) helper.getView(R.id.tv_right_bottom)).setTextColor(Color.parseColor("#3172f4"));
                    ((TextView) helper.getView(R.id.tv_right_bottom)).setText("确认完成工作");
                    helper.getView(R.id.tv_right_bottom).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            commCompleteJob(helper.getPosition(), item.getItemType(), item.getRelate_id());
                        }
                    });
                } else {
                    helper.getView(R.id.tv_right_bottom).setBackground(null);
                    ((TextView) helper.getView(R.id.tv_right_bottom)).setTextColor(Color.parseColor("#666666"));
                    ((TextView) helper.getView(R.id.tv_right_bottom)).setText("已通知用人单位");
                }
            } else if (item.getItemType() == 7) {
                //求职招聘-应聘方
                helper.setText(R.id.tv_title, record.getTitle())
                        .setText(R.id.tv_time, record.getUpdate_time())
                        .setText(R.id.tv_left, record.getCompany_name())
                        .setText(R.id.tv_right_top, record.getArea_name());
            } else if (item.getItemType() == 9) {
                //姻缘-应聘方
                GlideUtil.show(getActivity(), record.getUser_avatar_url(), (ImageView) helper.getView(R.id.iv_avatar));
                helper.setText(R.id.tv_info, record.getUser_name()
                        + "    " + (record.getSex() == 1 ? "男" : "女") + "·" + record.getAge() + "岁")
                        .setText(R.id.tv_right_top, record.getLive_second_area_name());
                //设置匹配度
                ((TextView) helper.getView(R.id.tv_pipridu)).setText(new SpanUtils().append("匹配度：")
                        .append(record.getMatch_value() + "%")
                        .setForegroundColor(ResUtils.getCommRed())
                        .create());

                if (!TextUtils.isEmpty(record.getPersonal_assets_status())) {
                    helper.getView(R.id.ll_car_rent).setVisibility(View.VISIBLE);
                    if (record.getPersonal_assets_status().contains("1")) {
                        helper.getView(R.id.tv_has_car).setVisibility(View.VISIBLE);
                    } else {
                        helper.getView(R.id.tv_has_car).setVisibility(View.GONE);
                    }

                    if (record.getPersonal_assets_status().contains("2")) {
                        helper.getView(R.id.tv_has_house).setVisibility(View.VISIBLE);
                    } else {
                        helper.getView(R.id.tv_has_house).setVisibility(View.GONE);
                    }
                } else {
                    helper.getView(R.id.ll_car_rent).setVisibility(View.GONE);
                    helper.getView(R.id.tv_has_car).setVisibility(View.GONE);
                    helper.getView(R.id.tv_has_house).setVisibility(View.GONE);
                }
            } else if (item.getItemType() == 10) {
                //家教应聘方
                helper.setText(R.id.tv_title, record.getTitle())
                        .setText(R.id.tv_right_top, record.getArea_name());

                //设置匹配度
                ((TextView) helper.getView(R.id.tv_pipeidu)).setText(new SpanUtils().append("匹配度：")
                        .append(record.getMatch_value() + "%")
                        .setForegroundColor(ResUtils.getCommRed())
                        .create());
            } else if (item.getItemType() == 12 || item.getItemType() == 14 || item.getItemType() == 16) {
                //保姆 月嫂 育儿嫂 应聘方
                helper.setText(R.id.tv_title, record.getTitle())
                        .setText(R.id.tv_right_top, record.getArea_name());

                if (item.getItemType() == 12) {
                    //保姆-应聘方
                    helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_bm);
                    ((TextView) helper.getView(R.id.tv_type)).setText("保姆服务");
                } else if (item.getItemType() == 14) {
                    //月嫂-应聘方
                    helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_ys);
                    ((TextView) helper.getView(R.id.tv_type)).setText("月嫂服务");
                } else if (item.getItemType() == 16) {
                    //育儿嫂-应聘方
                    helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_yes);
                    ((TextView) helper.getView(R.id.tv_type)).setText("育儿嫂服务");
                }

                //设置匹配度
                ((TextView) helper.getView(R.id.tv_pipeidu)).setText(new SpanUtils().append("匹配度：")
                        .append(record.getMatch_value() + "%")
                        .setForegroundColor(ResUtils.getCommRed())
                        .create());
            }

            //招聘方
            else if (item.getItemType() == 6 || item.getItemType() == 19 || item.getItemType() == 21) {
                String areaName = "";
                if (item.getItemType() == 6) {
                    //劳务中介-招聘方
                    helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_lwzj);
                    ((TextView) helper.getView(R.id.tv_type)).setText("劳务工订单");
                    areaName = record.getArea_name();
                } else if (item.getItemType() == 21) {
                    //特种工-招聘方
                    helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_tzg);
                    ((TextView) helper.getView(R.id.tv_type)).setText("特种工订单");
                    areaName = record.getArea_name();
                } else if (item.getItemType() == 19) {
                    //钟点工-招聘方
                    helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_zdg);
                    ((TextView) helper.getView(R.id.tv_type)).setText("钟点工订单");
                    areaName = record.getArea_name();
                }
                helper.setText(R.id.tv_num, "还差" + record.getNum() + "人");
                helper.setText(R.id.tv_title, record.getTitle())
                        .setText(R.id.tv_right_top, areaName)
                        .setText(R.id.tv_time, record.getUpdate_time());

                helper.getView(R.id.tv_right_bottom).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (StringUtil.isAllNotEmpty(record.getLongitude(), record.getLatitude())) {
                            MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(record.getLongitude()), Double.parseDouble(record.getLatitude()), "");
                        }
                    }
                });

                //是否确认完成 0否 1.是
                if (record.getIs_pay() == 0) {
                    ((TextView) helper.getView(R.id.tv_right_bottom)).setVisibility(View.VISIBLE);
                    //确认支付
                    helper.getView(R.id.tv_right_bottom).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            commpay(helper.getPosition(), item.getItemType(), item.getRelate_id());
                        }
                    });
                } else {
                    ((TextView) helper.getView(R.id.tv_right_bottom)).setVisibility(View.GONE);
                }
            } else if (item.getItemType() == 8 || item.getItemType() == 11 || item.getItemType() == 13 || item.getItemType() == 15 || item.getItemType() == 17) {
                GlideUtil.show(getActivity(), record.getUser_avatar_url(), (ImageView) helper.getView(R.id.iv_avatar));
                String info = "";
                String address = "";
                String match = "";
                if (item.getItemType() == 8) {
                    //求职招聘-招聘方
                    helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_qzzp);
                    ((TextView) helper.getView(R.id.tv_type)).setText("求职招聘订单");
                    match = record.getMatch();
                    address = record.getArea_name();
                    info = record.getTrue_name() + "    " + (record.getSex() == 1 ? "男" : "女") + "·" + record.getAge() + "岁";
                } else if (item.getItemType() == 11) {
                    //家教-招聘方
                    helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_jj);
                    ((TextView) helper.getView(R.id.tv_type)).setText("家教服务");
                    match = record.getMatch_value();
                    address = record.getArea_name();
                    info = record.getUser_name() + "    " + (record.getSex_name()) + "·" + record.getAge() + "岁";
                } else if (item.getItemType() == 13) {
                    //保姆-招聘方
                    helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_bm);
                    ((TextView) helper.getView(R.id.tv_type)).setText("保姆服务");
                    match = record.getMatch_value();
                    address = record.getArea_name();
                    info = record.getUser_name() + "    " + (record.getSex_name()) + "·" + record.getAge() + "岁";
                } else if (item.getItemType() == 15) {
                    //月嫂-招聘方
                    helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_ys);
                    ((TextView) helper.getView(R.id.tv_type)).setText("月嫂服务");
                    match = record.getMatch_value();
                    address = record.getArea_name();
                    info = record.getUser_name() + "    " + (record.getSex_name()) + "·" + record.getAge() + "岁";
                } else if (item.getItemType() == 17) {
                    //育儿嫂-招聘方
                    helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_yes);
                    ((TextView) helper.getView(R.id.tv_type)).setText("育儿嫂服务");
                    match = record.getMatch_value();
                    address = record.getArea_name();
                    info = record.getUser_name() + "    " + (record.getSex_name()) + "·" + record.getAge() + "岁";
                }
                //家教应聘方
                helper.setText(R.id.tv_info, info)
                        .setText(R.id.tv_right_top, address);

                //设置匹配度
                ((TextView) helper.getView(R.id.tv_pipridu)).setText(new SpanUtils().append("匹配度：")
                        .append(match + "%")
                        .setForegroundColor(ResUtils.getCommRed())
                        .create());
            }else if (item.getItemType() == 22){//劳务---应聘方
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_lwzj);
                ((TextView) helper.getView(R.id.tv_type)).setText("劳务-我要找工作");
                ((TextView) helper.getView(R.id.tv_gz_dq)).setText("工作地区:"+record.getArea_name());
                ((TextView) helper.getView(R.id.tv_time)).setText(record.getUpdate_time());
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText("工作日期:"+record.getWork_date());
                ((TextView) helper.getView(R.id.tv_gz_sj)).setText("工作时间:"+getWorkHourType(record.getWork_hour()));
                ((TextView) helper.getView(R.id.tv_gz)).setText("工         种:"+record.getWork_type_name());
                setStatusFromIsMatch(((TextView) helper.getView(R.id.tv_order_status)),record.getIs_match());
            }else if (item.getItemType() == 23){//特种---应聘方
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_tzg);
                ((TextView) helper.getView(R.id.tv_type)).setText("特种工-我要找工作");
                ((TextView) helper.getView(R.id.tv_gz_dq)).setText("工作地区:"+record.getArea_name());
                ((TextView) helper.getView(R.id.tv_time)).setText(record.getUpdate_time());
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText("工作日期:"+record.getWork_date());
                ((TextView) helper.getView(R.id.tv_gz_sj)).setText("工作时间:"+getWorkHourType(record.getWork_hour()));
                ((TextView) helper.getView(R.id.tv_gz)).setText("工         种:"+record.getWork_type_name());
                setStatusFromIsMatch(((TextView) helper.getView(R.id.tv_order_status)),record.getIs_match());
            }else if (item.getItemType() == 24){//钟点---应聘方
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_zdg);
                ((TextView) helper.getView(R.id.tv_type)).setText("钟点工-我要找工作");
                ((TextView) helper.getView(R.id.tv_gz_dq)).setText("工作地区:"+record.getArea_name());
                ((TextView) helper.getView(R.id.tv_time)).setText(record.getUpdate_time());
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText("工作日期:"+record.getWork_date());
                ((TextView) helper.getView(R.id.tv_gz_sj)).setText("工作时间:"+getWorkHourType(record.getWork_hour()));
                ((TextView) helper.getView(R.id.tv_gz)).setText("服务内容:"+record.getService_name());
                setStatusFromIsMatch(((TextView) helper.getView(R.id.tv_order_status)),record.getIs_match());
            }else if (item.getItemType() == 25){//求职招聘---应聘方
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_qzzp);
                ((TextView) helper.getView(R.id.tv_type)).setText("求职招聘-我要求职");
                ((TextView) helper.getView(R.id.tv_gz_dq)).setText("求职区域:"+record.getArea_name());
                ((TextView) helper.getView(R.id.tv_time)).setText(record.getUpdate_time());
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText("期望职位:"+record.getJob_name());
                ((TextView) helper.getView(R.id.tv_gz_sj)).setVisibility(View.GONE);
                ((TextView) helper.getView(R.id.tv_gz)).setText("月         薪:"+record.getSalary_name());
                setStatusFromIsMatch(((TextView) helper.getView(R.id.tv_order_status)),record.getIs_match());
            }else if (item.getItemType() == 26){//姻缘---应聘方
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_yy);
                ((TextView) helper.getView(R.id.tv_type)).setText("姻缘服务-我要找对象");
                ((TextView) helper.getView(R.id.tv_gz_dq)).setText("身         高:"+record.getStart_zo_height()+"-"+record.getEnd_zo_height()+"CM");
                ((TextView) helper.getView(R.id.tv_time)).setText(record.getUpdate_time());
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText("体         重:"+record.getStart_zo_weight()+"-"+record.getEnd_zo_weight()+"KG");
                ((TextView) helper.getView(R.id.tv_gz_sj)).setVisibility(View.GONE);
                ((TextView) helper.getView(R.id.tv_gz)).setText("年         龄:"+record.getStart_zo_age()+"-"+record.getEnd_zo_age()+"岁");
                setStatusFromIsMatch(((TextView) helper.getView(R.id.tv_order_status)),record.getIs_match());
            }else if (item.getItemType() == 27){//家教---应聘方
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_jj);
                ((TextView) helper.getView(R.id.tv_type)).setText("家教-我要当家教");
                ((TextView) helper.getView(R.id.tv_gz_dq)).setText("工作地区:"+record.getArea_name());
                ((TextView) helper.getView(R.id.tv_time)).setText(record.getUpdate_time());
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText("辅导年级:"+record.getCoach_grade_name());
                ((TextView) helper.getView(R.id.tv_gz_sj)).setText("辅导科目:"+record.getCoach_subject_name());
                ((TextView) helper.getView(R.id.tv_gz)).setText("辅导时间:"+record.getCoach_time_name());
                setStatusFromIsMatch(((TextView) helper.getView(R.id.tv_order_status)),record.getIs_match());
            }else if (item.getItemType() == 28){//保姆---应聘方
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_bm);
                ((TextView) helper.getView(R.id.tv_type)).setText("保姆-我要当保姆");
                ((TextView) helper.getView(R.id.tv_gz_dq)).setText("工作地区:"+record.getArea_name());
                ((TextView) helper.getView(R.id.tv_time)).setText(record.getUpdate_time());
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText("保姆类型:"+record.getPerson_type_name());
                ((TextView) helper.getView(R.id.tv_gz_sj)).setText("工作时间:"+record.getWork_time_type_name());
                ((TextView) helper.getView(R.id.tv_gz)).setText("服务内容:"+record.getService_content_name());
                setStatusFromIsMatch(((TextView) helper.getView(R.id.tv_order_status)),record.getIs_match());
            }else if (item.getItemType() == 29){//月嫂---应聘方
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_ys);
                ((TextView) helper.getView(R.id.tv_type)).setText("月嫂-我要当月嫂");
                ((TextView) helper.getView(R.id.tv_gz_dq)).setText("工作地区:"+record.getArea_name());
                ((TextView) helper.getView(R.id.tv_time)).setText(record.getUpdate_time());
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText("月嫂类型:"+record.getPerson_type_name());
                ((TextView) helper.getView(R.id.tv_gz_sj)).setText("工作时间:"+record.getWork_time_type_name());
                ((TextView) helper.getView(R.id.tv_gz)).setText("服务内容:"+record.getService_content_name());
                setStatusFromIsMatch(((TextView) helper.getView(R.id.tv_order_status)),record.getIs_match());
            }else if (item.getItemType() == 30){//育儿嫂---应聘方
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_yes);
                ((TextView) helper.getView(R.id.tv_type)).setText("育儿嫂-我要当育儿嫂");
                ((TextView) helper.getView(R.id.tv_gz_dq)).setText("工作地区  :"+record.getArea_name());
                ((TextView) helper.getView(R.id.tv_time)).setText(record.getUpdate_time());
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText("育儿嫂类型:"+record.getPerson_type_name());
                ((TextView) helper.getView(R.id.tv_gz_sj)).setText("工作时间  :"+record.getWork_time_type_name());
                ((TextView) helper.getView(R.id.tv_gz)).setText("服务内容  :"+record.getService_content_name());
                setStatusFromIsMatch(((TextView) helper.getView(R.id.tv_order_status)),record.getIs_match());
            }
            else if (item.getItemType() == 31){//求职招聘---招聘方
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_qzzp);
                ((TextView) helper.getView(R.id.tv_type)).setText("求职招聘-我要招聘");
                ((TextView) helper.getView(R.id.tv_gz_dq)).setText("工作区域:"+record.getArea_name());
                ((TextView) helper.getView(R.id.tv_time)).setText(record.getUpdate_time());
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText("招聘职位:"+record.getJob_name());
                ((TextView) helper.getView(R.id.tv_gz_sj)).setVisibility(View.GONE);
                ((TextView) helper.getView(R.id.tv_gz)).setText("月         薪:"+record.getSalary_name());
                setStatusFromIsMatch(((TextView) helper.getView(R.id.tv_order_status)),record.getIs_match());
            } else if (item.getItemType() == 32){//家教---招聘方
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_jj);
                ((TextView) helper.getView(R.id.tv_type)).setText("家教-我要找家教");
                ((TextView) helper.getView(R.id.tv_gz_dq)).setText("工作地区:"+record.getArea_name());
                ((TextView) helper.getView(R.id.tv_time)).setText(record.getUpdate_time());
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText("辅导年级:"+record.getCoach_grade_name());
                ((TextView) helper.getView(R.id.tv_gz_sj)).setText("辅导科目:"+record.getCoach_subject_name());
                ((TextView) helper.getView(R.id.tv_gz)).setText("辅导时间:"+record.getCoach_time_name());
                setStatusFromIsMatch(((TextView) helper.getView(R.id.tv_order_status)),record.getIs_match());
            }else if (item.getItemType() == 33){//保姆---招聘方
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_bm);
                ((TextView) helper.getView(R.id.tv_type)).setText("保姆-我要找保姆");
                ((TextView) helper.getView(R.id.tv_gz_dq)).setText("工作地区:"+record.getArea_name());
                ((TextView) helper.getView(R.id.tv_time)).setText(record.getUpdate_time());
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText("保姆类型:"+record.getPerson_type_name());
                ((TextView) helper.getView(R.id.tv_gz_sj)).setText("工作时间:"+record.getWork_time_type_name());
                ((TextView) helper.getView(R.id.tv_gz)).setText("服务内容:"+record.getService_content_name());
                setStatusFromIsMatch(((TextView) helper.getView(R.id.tv_order_status)),record.getIs_match());
            }else if (item.getItemType() == 34){//月嫂---招聘方
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_ys);
                ((TextView) helper.getView(R.id.tv_type)).setText("月嫂-我要找月嫂");
                ((TextView) helper.getView(R.id.tv_gz_dq)).setText("工作地区:"+record.getArea_name());
                ((TextView) helper.getView(R.id.tv_time)).setText(record.getUpdate_time());
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText("月嫂类型:"+record.getPerson_type_name());
                ((TextView) helper.getView(R.id.tv_gz_sj)).setText("工作时间:"+record.getWork_time_type_name());
                ((TextView) helper.getView(R.id.tv_gz)).setText("服务内容:"+record.getService_content_name());
                setStatusFromIsMatch(((TextView) helper.getView(R.id.tv_order_status)),record.getIs_match());
            }else if (item.getItemType() == 35){//育儿嫂---招聘方
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_yes);
                ((TextView) helper.getView(R.id.tv_type)).setText("育儿嫂-我要找育儿嫂");
                ((TextView) helper.getView(R.id.tv_gz_dq)).setText("工作地区  :"+record.getArea_name());
                ((TextView) helper.getView(R.id.tv_time)).setText(record.getUpdate_time());
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText("育儿嫂类型:"+record.getPerson_type_name());
                ((TextView) helper.getView(R.id.tv_gz_sj)).setText("工作时间  :"+record.getWork_time_type_name());
                ((TextView) helper.getView(R.id.tv_gz)).setText("服务内容  :"+record.getService_content_name());
                setStatusFromIsMatch(((TextView) helper.getView(R.id.tv_order_status)),record.getIs_match());
            }else if (item.getItemType() == 36 || item.getItemType() == 37 ){//智慧生活---订餐酒店门票
                helper.getView(R.id.tv_type).setBackgroundResource(R.mipmap.order_zhihui);
                String time = "";
                if (record.getIndex_type() == 1){
                    time = "订餐时间:";
                    ((TextView) helper.getView(R.id.tv_type)).setText("智慧生活-订餐服务");
                    ((TextView) helper.getView(R.id.tv_gz_dq)).setText("订餐人数:"+record.getPeople_num()+"人");
                }else if (record.getIndex_type() == 2){
                    time = "订房时间:";
                    ((TextView) helper.getView(R.id.tv_type)).setText("智慧生活-订房服务");
                    ((TextView) helper.getView(R.id.tv_gz_dq)).setText("订房间数:"+record.getPeople_num()+"间");
                }else if (record.getIndex_type() == 3){
                    time = "订票时间:";
                    ((TextView) helper.getView(R.id.tv_type)).setText("智慧生活-订票服务");
                    ((TextView) helper.getView(R.id.tv_gz_dq)).setText("订票张数:"+record.getPeople_num()+"张");
                }
                ((TextView) helper.getView(R.id.tv_time)).setText(DateUtil.StampTimeToDate(record.getAdd_time()+"","yyyy-MM-dd HH:ss:mm"));
                ((TextView) helper.getView(R.id.tv_gz_rq)).setText(time+ DateUtil.StampTimeToDate(record.getBooking_time()+"","yyyy-MM-dd HH:ss:mm"));
                ((TextView) helper.getView(R.id.tv_gz_sj)).setVisibility(View.GONE);
                ((TextView) helper.getView(R.id.tv_gz)).setText("备  注:"+record.getRemark());
                int status = record.getStatus();
                String status_str = "";
                if (status == 1){
                    status_str = "待接单";
                    ((TextView) helper.getView(R.id.tv_order_status)).setTextColor(Color.parseColor("#3172f4"));
                }else if (status == 2){
                    status_str = "已完成";
                    ((TextView) helper.getView(R.id.tv_order_status)).setTextColor(Color.parseColor("#FF0000"));
                }else if (status == 3){
                    status_str = "已取消";
                    ((TextView) helper.getView(R.id.tv_order_status)).setTextColor(Color.parseColor("#239491"));
                }
                ((TextView) helper.getView(R.id.tv_order_status)).setText(status_str);
            }
        }
    }

    private void setStatusFromIsMatch(TextView tv,int is_match){
        if (is_match == 1){
            tv.setText("正在匹配");
            tv.setTextColor(Color.parseColor("#239491"));
        }else if (is_match == 2){
            tv.setText("已匹配");
            tv.setTextColor(Color.parseColor("#3172f4"));
        }else if (is_match == 3){
            tv.setText("匹配失败");
            tv.setTextColor(Color.parseColor("#fdbe44"));
        }else if (is_match == 4){
            tv.setText("匹配成功");
            tv.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    private String getWorkHourType(int work_hour){
        String hour = "";
        switch (work_hour){
            case 0:
               return hour = "全天";
            case 2:
                return hour = "2小时";
            case 4:
                return hour = "4小时";
            case 6:
                return hour = "6小时";
            case 8:
                return hour = "8小时";
                default:
                    return hour = "全天";
        }
    }

    /**
     * 删除已完成订单
     * @param position
     * @param require_id
     */
    private void deleteOrder(final int position, int require_id){
        showDialog();

        addCall(new NetUtil().setUrl(APIS.URL_DELETE_FINISHED_ORDER)
                .addParams("matching_order_id", require_id + "")
                .setCallBackData(new BaseNewNetModelimpl() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        dismiss();
                        if (respnse != null && respnse.getCode() == 0) {
                            CommonOrderModel commonOrderModel = list.get(position);
                            commonOrderModel.getRecord().setIs_pay(11);
                            adapter.notifyItemChanged(position);
                            refresh();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                }).buildPost());
    }


    //智慧生活确认支付
    private void zhshConfirmPay(final int position, int require_id) {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_FINISH_SMARTHOMEORDER)
                .addParams("require_id", require_id + "")
                .setCallBackData(new BaseNewNetModelimpl() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        dismiss();
                        if (respnse != null && respnse.getCode() == 0) {
                            CommonOrderModel commonOrderModel = list.get(position);
                            commonOrderModel.getRecord().setIs_pay(11);
                            adapter.notifyItemChanged(position);
                            refresh();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                }).buildPost());
    }

    //确认支付
    private void commpay(final int position, int itemType, int relate_id) {
        if (itemType == 6 || itemType == 21) {
            //劳务中介-招聘方   //特种工-招聘方
            showDialog();
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_ALL_PAY)
                    .addParams("recruitment_id", relate_id + "")
                    .setCallBackData(new BaseNewNetModelimpl() {
                        @Override
                        protected void onFail(int type, String msg) {
                            showTips(msg);
                            dismiss();
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                            dismiss();
                            if (respnse != null && respnse.getCode() == 0) {
                                CommonOrderModel commonOrderModel = list.get(position);
                                commonOrderModel.getRecord().setIs_pay(11);
                                adapter.notifyItemChanged(position);
                                refresh();
                            }
                        }

                        @Override
                        protected Type getType() {
                            return new TypeToken<BaseResultInfo>() {
                            }.getType();
                        }
                    }).buildPost());
        } else if (itemType == 19) {
            //钟点工-招聘方
            showDialog();
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_ALL_PAY_ZDG)
                    .addParams("lookfor_bell_worker_id", relate_id + "")
                    .setCallBackData(new BaseNewNetModelimpl() {
                        @Override
                        protected void onFail(int type, String msg) {
                            showTips(msg);
                            dismiss();
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                            dismiss();
                            if (respnse != null && respnse.getCode() == 0) {
                                CommonOrderModel commonOrderModel = list.get(position);
                                commonOrderModel.getRecord().setIs_pay(11);
                                adapter.notifyItemChanged(position);
                                refresh();
                            }
                        }

                        @Override
                        protected Type getType() {
                            return new TypeToken<BaseResultInfo>() {
                            }.getType();
                        }
                    }).buildPost());
        }
    }

    //确认完成任务
    private void commCompleteJob(final int position, int itemType, int relate_id) {
        if (itemType == 5 || itemType == 20) {
            //劳务中介-应聘方   //特种工-应聘方
            showDialog();
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_CONFIRM_FINISH_WORK)
                    .addParams("relate_id", relate_id + "")
                    .setCallBackData(new BaseNewNetModelimpl() {
                        @Override
                        protected void onFail(int type, String msg) {
                            showTips(msg);
                            dismiss();
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                            dismiss();
                            if (respnse.getCode() == 0) {
                                showTips("操作成功");
                                CommonOrderModel commonOrderModel = list.get(position);
                                commonOrderModel.getRecord().setConfirm_complete(1);
                                adapter.notifyItemChanged(position);
                            }
                        }

                        @Override
                        protected Type getType() {
                            return new TypeToken<BaseResultInfo>() {
                            }.getType();
                        }
                    }).buildPost());
        } else if (itemType == 18) {
            //钟点工-应聘方
            showDialog();
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_CONFIRM_FINISH_WORK_ZDG)
                    .addParams("hour_matching_id", relate_id + "")
                    .setCallBackData(new BaseNewNetModelimpl() {
                        @Override
                        protected void onFail(int type, String msg) {
                            dismiss();
                            showTips(msg);
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                            dismiss();
                            if (respnse.getCode() == 0) {
                                CommonOrderModel commonOrderModel = list.get(position);
                                commonOrderModel.getRecord().setConfirm_complete(1);
                                adapter.notifyItemChanged(position);
                            }
                        }

                        @Override
                        protected Type getType() {
                            return new TypeToken<BaseResultInfo>() {
                            }.getType();
                        }
                    }).buildPost()
            );
        } else if (itemType == 1) {
            //智慧生活抢单人确认完成工作
            showDialog();
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_EMPLOYEE_FINISH_SMARTHOMEORDER)
                    .addParams("require_id", relate_id + "")
                    .setCallBackData(new BaseNewNetModelimpl() {
                        @Override
                        protected void onFail(int type, String msg) {
                            showTips(msg);
                            dismiss();
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                            dismiss();
                            if (respnse.getCode() == 0) {
                                showTips("尊敬的管家用户：您好，管家已通知发单方为您支付悬赏，请您稍等片刻。");
//                                CommonOrderModel commonOrderModel = list.get(position);
//                                commonOrderModel.getRecord().setConfirm_complete(1);
//                                adapter.notifyItemChanged(position);
                            }
                        }

                        @Override
                        protected Type getType() {
                            return new TypeToken<BaseResultInfo>() {
                            }.getType();
                        }
                    }).buildPost());
        }
    }


}
