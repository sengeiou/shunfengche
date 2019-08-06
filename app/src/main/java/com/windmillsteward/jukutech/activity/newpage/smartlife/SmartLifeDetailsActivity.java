package com.windmillsteward.jukutech.activity.newpage.smartlife;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.model.SmartLifeDetailModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.TouSuFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.PingjiaFragment;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.manager.CommonActivityManager;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class SmartLifeDetailsActivity extends BaseInitActivity {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.tv_bottom)
    TextView tv_bottom;
    FlyBanner flyBanner;


    public static final int TYPE_QDR = 1;
    public static final int TYPE_FBR = 2;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.lay_ll_bottom)
    LinearLayout layLlBottom;

    private TextView mTv_zhuangtai;
    private TextView mTv_bianhao;
    private TextView mTv_biaoti;
    private TextView mTv_diqu;
    private TextView mTv_dizhi;
    private TextView mTv_feiyong;
    private TextView mTv_xiangqing;
    private LinearLayout lay_ll_tips;

    private int relate_id;
    private String longitude;
    private String latitude;

    private SmartLifeDetailModel data;
    private LinearLayout lay_ll_order_sn;
    private LinearLayout lay_qidian_address;
    private LinearLayout lay_zd_address;
    private TextView tv_qidian_address_tips;
    private TextView tv_zd_address_tips;
    private TextView tv_zd_dizhi;

    @Override
    protected void initView(View view) {
        setMainTitle("订单详情");

        if (getIntent() != null) {
            relate_id = getIntent().getIntExtra("relate_id", 0);
            longitude = getIntent().getStringExtra("longitude");
            latitude = getIntent().getStringExtra("latitude");
        }

        initAdapter();
    }

    public static void go(Activity activity, int relate_id, String longitude, String latitude) {
        Intent intent = new Intent(activity, SmartLifeDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("relate_id", relate_id);
        bundle.putString("longitude", longitude);
        bundle.putString("latitude", latitude);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_smart_life_details;
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_REQUIRE_ORDER_DETAIL)
                .addParams("require_id", relate_id + "")
                .addParams("longitude", longitude + "")
                .addParams("latitude", latitude + "")
                .setCallBackData(new BaseNewNetModelimpl<SmartLifeDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<SmartLifeDetailModel> respnse, String source) {
                        dismiss();
                        showContentView();
                        if (respnse.getData() != null) {
                            data = respnse.getData();

                            //角色.1：发布人，2：抢单人
                            if (data.getMy_role() == 1) {
                                if (data.getStatus() == 1) {
                                    tv_bottom.setVisibility(View.VISIBLE);
                                    layLlBottom.setVisibility(View.GONE);
                                    tvLeft.setVisibility(View.GONE);
                                    tvRight.setVisibility(View.GONE);
                                    tv_bottom.setText("取消订单");
                                    tv_bottom.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //取消订单
                                            final BaseDialog baseDialog = new BaseDialog(SmartLifeDetailsActivity.this);
                                            baseDialog.showTwoButton("提示", "是否取消订单？", "确定", "取消", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    calcenOrder(data.getRequire_id());
                                                    baseDialog.dismiss();
                                                }
                                            }, new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    baseDialog.dismiss();
                                                }
                                            });
                                        }
                                    });
                                    list.clear();
                                    adapter.notifyDataSetChanged();
                                    adapter.loadMoreEnd();
                                } else if (data.getStatus() == 2) {//已接单，隐藏底部按钮
                                    tv_bottom.setVisibility(View.GONE);
                                    int is_complaint = data.getIs_complaint();
                                    layLlBottom.setVisibility(View.VISIBLE);
                                    tvLeft.setVisibility(View.VISIBLE);
                                    tvRight.setVisibility(View.VISIBLE);
                                    if (is_complaint == 1) {
                                        layLlBottom.setVisibility(View.GONE);
                                    }
                                    tvLeft.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            //取消订单
                                            final BaseDialog baseDialog = new BaseDialog(SmartLifeDetailsActivity.this);
                                            baseDialog.showTwoButton("提示", "是否取消订单？", "确定", "取消", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    calcenOrder(data.getRequire_id());
                                                    baseDialog.dismiss();
                                                }
                                            }, new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    baseDialog.dismiss();
                                                }
                                            });

                                        }
                                    });
                                    tvRight.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            //取消接单人
                                            final BaseDialog baseDialog = new BaseDialog(SmartLifeDetailsActivity.this);
                                            baseDialog.showTwoButton("提示", "是否取消该接单人", "确定", "取消", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    fbrCancelPerson();
                                                    baseDialog.dismiss();
                                                }
                                            }, new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    baseDialog.dismiss();
                                                }
                                            });

                                        }
                                    });
                                }else{
                                    tv_bottom.setVisibility(View.GONE);
                                    layLlBottom.setVisibility(View.GONE);
                                    tvLeft.setVisibility(View.GONE);
                                    tvRight.setVisibility(View.GONE);
                                }
                            } else {//抢单人
                                if (data.getIs_push_msg() == 1) {
                                    tv_bottom.setVisibility(View.GONE);
                                } else {
                                    tv_bottom.setVisibility(View.VISIBLE);
                                    tv_bottom.setText("任务完成确认");
                                }
                                lay_ll_tips.setVisibility(View.GONE);
                                if (data.getStatus() == 2) {
                                    tv_bottom.setVisibility(View.VISIBLE);
                                    tv_bottom.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            qdrFinish(data.getRequire_id());
                                        }
                                    });
                                }else if (data.getStatus() == 3){
                                    tv_bottom.setVisibility(View.GONE);
                                }
                            }

                            setData(data);
                        }
                    }

                    private void setData(final SmartLifeDetailModel data) {
                        //状态：1：等待接单 2:已接单 3：已完成
                        String status = "";
                        int color = 0;
                        switch (data.getStatus()) {
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
                                lay_ll_tips.setVisibility(View.GONE);
                                break;
                        }
                        mTv_zhuangtai.setTextColor(color);
                        mTv_zhuangtai.setText(status);
                        mTv_bianhao.setText(data.getOrder_sn());
                        mTv_biaoti.setText(data.getTitle());
                        mTv_diqu.setText(data.getArea_name());
                        mTv_dizhi.setText(data.getAddress());
                        mTv_feiyong.setText(data.getFee());
                        mTv_xiangqing.setText(data.getDescription());
                        if (TextUtils.isEmpty(data.getOrder_sn())){
                            lay_ll_order_sn.setVisibility(View.GONE);
                        }else{
                            lay_ll_order_sn.setVisibility(View.VISIBLE);
                        }

                        String t_address = data.getT_address();
                        if (TextUtils.isEmpty(t_address)){//如果终点地址为空，则只显示起点地址
                            lay_zd_address.setVisibility(View.GONE);
                            tv_qidian_address_tips.setText("任务地址");
                        }else{
                            tv_qidian_address_tips.setText("起点地址");
                            lay_zd_address.setVisibility(View.VISIBLE);
                            tv_zd_dizhi.setText(t_address);
                        }

                        List<String> pics = data.getPic_urls();
                        if (pics != null ){
                            if (pics.size() == 0){
                                flyBanner.setVisibility(View.GONE);
                            }else{
                                flyBanner.setVisibility(View.VISIBLE);
                                ViewWrap.setUpFlyBanner(SmartLifeDetailsActivity.this, pics, null, flyBanner, 335, 175);
                            }
                        }

                        if (data.getMy_role() == 1) {//发布人进来详情才显示抢单人信息
                            if (data.getURecord() != null) {
                                SmartLifeDetailModel.URecordBean uRecord = data.getURecord();
                                list.clear();
                                list.add(uRecord);
                                adapter.notifyDataSetChanged();
                                adapter.loadMoreEnd();
                            }
                        }
                        mTv_dizhi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (StringUtil.isAllNotEmpty(data.getLongitude(), data.getLatitude())) {
                                    MapNaviUtils.showDaoHangDialog(SmartLifeDetailsActivity.this, Double.parseDouble(data.getLongitude()), Double.parseDouble(data.getLatitude()), "");
                                }
                            }
                        });
                        tv_zd_dizhi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (StringUtil.isAllNotEmpty(data.getT_longitude(), data.getT_latitude())) {
                                    MapNaviUtils.showDaoHangDialog(SmartLifeDetailsActivity.this, Double.parseDouble(data.getT_longitude()), Double.parseDouble(data.getT_latitude()), "");
                                }
                            }
                        });
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<SmartLifeDetailModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 发布人取消接单人
     */
    private void fbrCancelPerson(){
        addCall(new NetUtil().setUrl(APIS.URL_CANCEL_PERSON)
                .addParams("require_id", data.getRequire_id() + "")
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
                            showTips("已取消该接单人");
                            refreshPageData();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                }).buildPost());
    }


    @Override
    protected void refreshPageData() {
        initData();
    }

    @Override
    public boolean autoRefresh() {
        return true;
    }

    private RecyclerViewAdapter adapter;
    private List<SmartLifeDetailModel.URecordBean> list;

    public void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        initHeader();

        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);

        adapter.isUseEmpty(false);

        //事件监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    private void initHeader() {
        View headerView = View.inflate(this, R.layout.header_recycler_smart_life, null);

        mTv_zhuangtai = (TextView) headerView.findViewById(R.id.tv_zhuangtai);
        mTv_bianhao = (TextView) headerView.findViewById(R.id.tv_bianhao);
        mTv_biaoti = (TextView) headerView.findViewById(R.id.tv_biaoti);
        mTv_diqu = (TextView) headerView.findViewById(R.id.tv_diqu);
        mTv_dizhi = (TextView) headerView.findViewById(R.id.tv_dizhi);
        mTv_feiyong = (TextView) headerView.findViewById(R.id.tv_feiyong);
        mTv_xiangqing = (TextView) headerView.findViewById(R.id.tv_xiangqing);
        lay_ll_tips = (LinearLayout) headerView.findViewById(R.id.lay_ll_tips);
        lay_ll_order_sn = (LinearLayout) headerView.findViewById(R.id.lay_ll_order_sn);
        lay_qidian_address = (LinearLayout) headerView.findViewById(R.id.lay_qidian_address);
        lay_zd_address = (LinearLayout) headerView.findViewById(R.id.lay_zd_address);

        tv_qidian_address_tips = (TextView) headerView.findViewById(R.id.tv_qidian_address_tips);
        tv_zd_address_tips = (TextView) headerView.findViewById(R.id.tv_zd_address_tips);
        tv_zd_dizhi = (TextView) headerView.findViewById(R.id.tv_zd_dizhi);

        flyBanner = headerView.findViewById(R.id.flyBanner);

        adapter.setHeaderView(headerView);
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<SmartLifeDetailModel.URecordBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<SmartLifeDetailModel.URecordBean> data) {
            super(R.layout.item_recycler_has_match_resume, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final SmartLifeDetailModel.URecordBean item) {
            GlideUtil.show(SmartLifeDetailsActivity.this, item.getUser_avatar_url(), (ImageView) helper.getView(R.id.iv_avatar));
            helper.setText(R.id.tv_name, item.getTrue_name() + "    " + item.getSex_name())
                    .setText(R.id.tv_phone, "联系电话：" + item.getMobile_phone())
                    .setText(R.id.tv_location, item.getArea_name());

            helper.getView(R.id.tv_shishi).setVisibility(View.GONE);
            if (data.getStatus() == 2) {
                helper.getView(R.id.tv_tousu).setVisibility(View.GONE);
                helper.getView(R.id.tv_confirm).setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_confirm,"确认完成工作");
                helper.getView(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final BaseDialog baseDialog = new BaseDialog(SmartLifeDetailsActivity.this);
                        baseDialog.showTwoButton("提示", "您确定要完成工作？", "确定", "取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                baseDialog.dismiss();
                                fdrComplete();
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                baseDialog.dismiss();
                            }
                        });
                    }
                });
            } else if (data.getStatus() == 3) {
                helper.getView(R.id.tv_tousu).setVisibility(View.GONE);
                helper.getView(R.id.tv_confirm).setVisibility(View.VISIBLE);
                if (data.getEvaluation_id() ==0){//未评价
                    helper.setText(R.id.tv_confirm, "评价");
                    helper.getView(R.id.tv_confirm).setVisibility(View.VISIBLE);
                    helper.getView(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //去评价
                            Bundle bundle = new Bundle();
                            bundle.putInt("relate_id", relate_id);
                            bundle.putInt("roleType", PageConfig.TYPE_SMART);
                            startManagerActivity(CommonActivityManager.class, PingjiaFragment.TAG, bundle);
                        }
                    });
                }else {//已评价，把按钮隐藏
                    helper.getView(R.id.tv_confirm).setVisibility(View.GONE);
                }
            }
            int is_complaint = data.getIs_complaint();
            if (is_complaint == 1) {
                helper.setText(R.id.tv_tousu, "已投诉");
            } else {
                helper.setText(R.id.tv_tousu, "投诉");
            }
            helper.getView(R.id.tv_position).setVisibility(View.GONE);

            helper.getView(R.id.tv_location).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (StringUtil.isAllNotEmpty(item.getLongitude(), item.getLatitude())) {
                        MapNaviUtils.showDaoHangDialog(SmartLifeDetailsActivity.this, Double.parseDouble(item.getLongitude()), Double.parseDouble(item.getLatitude()), "");
                    }
                }
            });

            helper.getView(R.id.tv_tousu).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (baseDialog == null) {
                        baseDialog = new BaseDialog(SmartLifeDetailsActivity.this);
                    }
                    String title;
                    String content;
                    String confirmName;
                    if (data.getIs_complaint() == 1) {
                        title = "提示";
                        content = "是否拨打客服电话" + "\n" + item.getComplaint_call();
                        confirmName = "拨打电话";
                    } else {
                        title = "投诉提示";
                        content = "如需投诉请点击确认，管家将冻结该笔资金，请您在两小时内联系客服处理，逾期视为自动放弃，平台将默认打款。" + "\n客服电话:" + item.getComplaint_call();
                        confirmName = "确定";
                    }
                    baseDialog.showTwoButton(title, content, confirmName, "取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (baseDialog != null) {
                                baseDialog.dismiss();
                            }
                            if (data.getIs_complaint() == 1) {
                                String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                                if (checkPermission(permissions)) {
                                    PhoneUtils.dial(item.getComplaint_call());
                                }
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putInt("relate_id", relate_id);
                                bundle.putInt("roleType", PageConfig.TYPE_SMART);
                                startManagerActivity(CommonActivityManager.class, TouSuFragment.TAG, bundle);
//                                ((BackFragmentActivity) getActivity()).
//                                        addFragment(TouSuFragment.newInstance(getArguments().getInt("roleType"), getArguments().getInt("relate_id")), true, true);
//                                tousu();
                            }

                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (baseDialog != null) {
                                baseDialog.dismiss();
                            }
                        }
                    });
                }
            });



            //打电话
            helper.getView(R.id.iv_phone).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                    if (checkPermission(permissions)) {
                        PhoneUtils.dial(item.getMobile_phone());
                    }
                }
            });
        }
    }

    //支付
    private void fdrComplete() {
        if (data == null)
            return;
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_SMART_FDR_COMPLETE)
                .addParams("require_id", data.getRequire_id() + "")
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
                            showTips("确认完成工作成功");
                            refreshPageData();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                }).buildPost());
    }


    //支付
    private void pay() {
        if (data == null)
            return;
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_FINISH_SMARTHOMEORDER)
                .addParams("require_id", data.getRequire_id() + "")
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
                            showTips("支付成功");
                            refreshPageData();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                }).buildPost());
    }

    //抢单人 结束工作
    private void qdrFinish(int require_id) {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_EMPLOYEE_FINISH_SMARTHOMEORDER)
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
                        showTips("尊敬的管家用户：您好，管家已通知发单方为您支付悬赏，请您稍等片刻。");
                        if (respnse != null && respnse.getCode() == 0) {
                            refreshPageData();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                }).buildPost());
    }

    //发布人 取消订单
    private void calcenOrder(int require_id) {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_CANCEL_REQUIRE)
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
                            showTips("取消成功");
                            finish();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                }).buildPost());
    }

    private BaseDialog baseDialog;

    //投诉
    private void tousu() {
        if (data == null)
            return;
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_COMPLAINT_SMART)
                .addParams("require_id", data.getRequire_id() + "")
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
                            refreshPageData();
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
