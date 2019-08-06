package com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong;


import android.Manifest;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.VideoPlayActivity;
import com.windmillsteward.jukutech.activity.map.ShowMapZhaoPinActivity;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.model.HasZdgPublicPositionDetailsModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.TouSuFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.VoiceUtils;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoZhaoPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.PingjiaFragment;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.event.NotifyPageRefresh;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.ActivityUtils;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
import com.windmillsteward.jukutech.utils.MediaUtils;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import org.greenrobot.eventbus.Subscribe;
import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 * 钟点工-我要找人订单详情
 */
public class ZhongdgUsePersonFragment extends BaseInitFragment {
    public static final String TAG = "ZhongdgUsePersonFragment";
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;
    @Bind(R.id.tv_all_pay)
    TextView tvAllPay;

    private TextView mTv_bianhao;
    private TextView mTv_bianti;
    private TextView mTv_renshu;
    private TextView mTv_date;
    private TextView mTv_daiyu;
    private TextView mTv_gzshijian;
    private TextView mTv_diqu;
    private TextView mTv_address;
    private TextView mTv_neirong;
    private TextView mTv_desc;
    private TextView mTv_fee;
    private TextView tvPicTips;
    private LinearLayout lay_ll_tips;
    private LinearLayout lay_ll_address;
    private LinearLayout lay_ll_my_publish_voice;
    private LinearLayout lay_ll_my_publish_voice_content;
    private ImageView iv_my_publish_voice;
    private TextView tv_my_publish_length;
    private RecyclerView myPublishRecyclerview;
    private RelativeLayout lay_rl_my_publish_video;
    private ImageView iv_my_publish_video;
    private ImageView iv_my_publish_play;

    private HasZdgPublicPositionDetailsModel data;
    private ArrayList<String> myPublishListPic;
    private PicRecyclerViewAdapter myPublishAdapter;
    private VoiceUtils myPublishvoiceUtils;


    public static ZhongdgUsePersonFragment newInstance(int lookfor_bell_worker_id, int showAddress) {
        Bundle args = new Bundle();
        args.putInt("lookfor_bell_worker_id", lookfor_bell_worker_id);
        args.putInt("showAddress", showAddress);//0不显示1显示
        ZhongdgUsePersonFragment fragment = new ZhongdgUsePersonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_has_published_position_details;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("钟点工-我要找钟点工详情");

        initAdapter();
        initPicAdapter();
        commonRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshPageData();
            }
        });
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_PUBLISHED_POSITION_DETAIL_ZDG)
                .addParams("lookfor_bell_worker_id", getArguments().getInt("lookfor_bell_worker_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<HasZdgPublicPositionDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        dismiss();
                        commonRefresh.refreshComplete();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<HasZdgPublicPositionDetailsModel> respnse, String source) {
                        showContentView();
                        dismiss();
                        commonRefresh.refreshComplete();

                        tvAllPay.setVisibility(View.GONE);

                        data = respnse.getData();
                        if (data != null) {
                            //设置数据
                            setHeaderData(data);

                            //设置listView数据
                            List<HasZdgPublicPositionDetailsModel.WhenBellWorkerListBean> when_bell_worker_list = data.getWhen_bell_worker_List();
                            if (when_bell_worker_list != null) {
                                list.clear();
                                list.addAll(when_bell_worker_list);
                                adapter.notifyDataSetChanged();
                                adapter.loadMoreEnd();
                                lay_ll_tips.setVisibility(View.VISIBLE);
                                if (getArguments().getInt("showAddress", 1) == 0) {
                                    lay_ll_address.setVisibility(View.GONE);
                                    lay_ll_tips.setVisibility(View.GONE);
                                    list.clear();
                                    adapter.notifyDataSetChanged();
                                    adapter.loadMoreEnd();
                                } else {
                                    if (TextUtils.isEmpty(data.getAddress())) {
                                        lay_ll_address.setVisibility(View.GONE);
                                    } else {
                                        lay_ll_address.setVisibility(View.VISIBLE);
                                    }
                                }

                                //支付方式 1.全额支付 2.只支付信息费用。 如果pay_type=1
                                if (data.getPay_type() == 1) {
                                    //处理数据
                                    boolean hasAllPay = true;
                                    HH:
                                    for (HasZdgPublicPositionDetailsModel.WhenBellWorkerListBean laborInfoListBean : when_bell_worker_list) {
                                        if (laborInfoListBean.getIs_pay() == 0) {
                                            hasAllPay = false;
                                            break HH;
                                        }
                                    }
                                    if (!hasAllPay) {
                                        tvAllPay.setVisibility(View.VISIBLE);
                                        if (getArguments().getInt("showAddress", 1) == 0) {
                                            tvAllPay.setVisibility(View.GONE);
                                        }
                                        tvAllPay.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                final BaseDialog baseDialog = new BaseDialog(getActivity());
                                                baseDialog.showTwoButton("提示", "您确定要支付全部人员的工资？", "确定", "取消", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        baseDialog.dismiss();
                                                        payAll();
                                                    }
                                                }, new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        baseDialog.dismiss();
                                                    }
                                                });

                                            }
                                        });
                                    }
                                } else {
                                    //只支付信息费用 只显示评价按钮 评价完就显示已评价
                                }
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<HasZdgPublicPositionDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    //设置头部数据
    private void setHeaderData(final HasZdgPublicPositionDetailsModel data) {
        mTv_bianhao.setText(data.getOrder_sn());
        mTv_bianti.setText(data.getTitle());
        mTv_renshu.setText("还差" + data.getNum() + "人");
        mTv_date.setText(data.getWork_date());

        if (data.getSalary_type() == 1) {
            mTv_daiyu.setText("市场定价");
        } else if (data.getSalary_type() == 2) {
            if (!TextUtils.isEmpty(data.getSalary_start()) && !TextUtils.isEmpty(data.getSalary_end())) {
                mTv_daiyu.setText(data.getSalary_start() + "-" + data.getSalary_end() + "元/小时");
            }
        } else if (data.getSalary_type() == 3) {
            mTv_daiyu.setText("面议");
        }
        if (data.getWork_hour() == 0) {
            mTv_gzshijian.setText("全天");
        } else {
            mTv_gzshijian.setText(data.getWork_hour() + "小时");
        }
        mTv_diqu.setText(data.getWork_second_area_name() + data.getWork_third_area_name());
        mTv_address.setText(data.getAddress());
        mTv_neirong.setText(data.getService_name());
        mTv_desc.setText(data.getJob_describe());
        mTv_fee.setText(data.getInfo_fee());

        mTv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtil.isAllNotEmpty(data.getLongitude(), data.getLatitude())) {
                    MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(data.getLongitude()), Double.parseDouble(data.getLatitude()), "");
                }
            }
        });
        String pic_urls = data.getImage_url();
        if (!TextUtils.isEmpty(pic_urls)) {
            List<String> mypublishPicList = Arrays.asList(pic_urls.split(","));
            if (mypublishPicList != null) {
                if (mypublishPicList.size() > 0) {
                    tvPicTips.setVisibility(View.VISIBLE);
                } else {
                    tvPicTips.setVisibility(View.GONE);
                }
                myPublishListPic.clear();
                myPublishListPic.addAll(mypublishPicList);
                myPublishAdapter.notifyDataSetChanged();
            }
        }else{
            tvPicTips.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(data.getVideo_url())) {
            lay_rl_my_publish_video.setVisibility(View.VISIBLE);
            iv_my_publish_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(data.getVideo_url())) {
                        Bundle bundle = new Bundle();
                        bundle.putString(VideoPlayActivity.VIDEO_URL, data.getVideo_url());
                        startAtvDonFinish(VideoPlayActivity.class, bundle);
                    }
                }
            });
            MediaUtils.getImageForVideo(data.getVideo_url(), new MediaUtils.OnLoadVideoImageListener() {
                @Override
                public void onLoadImage(File file) {
                    if (file == null) {
                        return;
                    }
                    final Uri uri = Uri.fromFile(file);
                    if (getActivity() != null) {
                        final ContentResolver contentResolver = getActivity().getContentResolver();
                        if (contentResolver != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri);
                                        iv_my_publish_video.setImageBitmap(bitmap);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                }
            });
        } else {
            lay_rl_my_publish_video.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(data.getVoice_url())) {
            myPublishvoiceUtils = new VoiceUtils(iv_my_publish_voice);
            lay_ll_my_publish_voice_content.setVisibility(View.VISIBLE);
            myPublishvoiceUtils.setFilePath(data.getVoice_url());
            myPublishvoiceUtils.getTime(tv_my_publish_length);
            lay_ll_my_publish_voice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean playing = myPublishvoiceUtils.isPlaying();
                    if (playing) {
                        myPublishvoiceUtils.stop();
                    } else {
                        myPublishvoiceUtils.play();
                    }
                }
            });
        } else {
            lay_ll_my_publish_voice_content.setVisibility(View.GONE);
        }

    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    /**
     * 全额支付
     */
    private void payAll() {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_ALL_PAY_ZDG)
                .addParams("lookfor_bell_worker_id", "" + getArguments().getInt("lookfor_bell_worker_id"))
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
                            //成功
                            showTips("支付成功");
                            refreshPageData();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    private RecyclerViewAdapter adapter;
    private List<HasZdgPublicPositionDetailsModel.WhenBellWorkerListBean> list;

    public void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        initHeader();

        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);

        adapter.isUseEmpty(false);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy == 0) {
                    commonRefresh.setEnabled(true);
                } else {
                    commonRefresh.setEnabled(false);
                }
            }
        });
    }

    //设置header
    private void initHeader() {
        View headerView = View.inflate(getActivity(), R.layout.header_layout_zhongdg_info_details, null);
        mTv_bianhao = (TextView) headerView.findViewById(R.id.tv_bianhao);
        mTv_bianti = (TextView) headerView.findViewById(R.id.tv_bianti);
        mTv_renshu = (TextView) headerView.findViewById(R.id.tv_renshu);
        mTv_date = (TextView) headerView.findViewById(R.id.tv_date);
        mTv_daiyu = (TextView) headerView.findViewById(R.id.tv_daiyu);
        mTv_gzshijian = (TextView) headerView.findViewById(R.id.tv_gzshijian);
        mTv_diqu = (TextView) headerView.findViewById(R.id.tv_diqu);
        mTv_address = (TextView) headerView.findViewById(R.id.tv_address);
        mTv_neirong = (TextView) headerView.findViewById(R.id.tv_neirong);
        mTv_desc = (TextView) headerView.findViewById(R.id.tv_desc);
        mTv_fee = (TextView) headerView.findViewById(R.id.tv_fee);
        tvPicTips = (TextView) headerView.findViewById(R.id.tv_pic_tips);
        lay_ll_address = (LinearLayout) headerView.findViewById(R.id.lay_ll_address);
        lay_ll_tips = (LinearLayout) headerView.findViewById(R.id.lay_ll_tips);
        lay_ll_my_publish_voice = (LinearLayout) headerView.findViewById(R.id.lay_ll_my_publish_voice);
        lay_ll_my_publish_voice_content = (LinearLayout) headerView.findViewById(R.id.lay_ll_my_publish_voice_content);
        iv_my_publish_voice = (ImageView) headerView.findViewById(R.id.iv_my_publish_voice);
        tv_my_publish_length = (TextView) headerView.findViewById(R.id.tv_my_publish_length);
        myPublishRecyclerview = (RecyclerView) headerView.findViewById(R.id.my_publish_recyclerview);
        lay_rl_my_publish_video = (RelativeLayout) headerView.findViewById(R.id.lay_rl_my_publish_video);
        iv_my_publish_video = (ImageView) headerView.findViewById(R.id.iv_my_publish_video);
        iv_my_publish_play = (ImageView) headerView.findViewById(R.id.iv_my_publish_play);


        lay_ll_tips.setVisibility(View.GONE);
        adapter.setHeaderView(headerView);
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<HasZdgPublicPositionDetailsModel.WhenBellWorkerListBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<HasZdgPublicPositionDetailsModel.WhenBellWorkerListBean> data) {
            super(R.layout.item_recycler_has_match_resume, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final HasZdgPublicPositionDetailsModel.WhenBellWorkerListBean item) {
            helper.setText(R.id.tv_name, item.getName() + "    " + (item.getSex() == 1 ? "男" : "女"))
                    .setText(R.id.tv_position, "服务内容：" + item.getService_name())
                    .setText(R.id.tv_phone, "联系电话：" + item.getContact_tel())
                    .setText(R.id.tv_location, item.getWork_second_area_name() + item.getWork_third_area_name());

            GlideUtil.show(getActivity(),item.getUser_avatar_url(),(CircleImageView)helper.getView(R.id.iv_avatar));

            helper.getView(R.id.tv_confirm).setVisibility(View.GONE);
            helper.getView(R.id.tv_tousu).setVisibility(View.GONE);

            helper.getView(R.id.tv_shishi).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_shishi).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.jumpToShowMapZhaopin(getActivity(), ShowMapZhaoPinActivity.TYPE_ZHONGDIANGONG, item.getWhen_bell_worker_id(), data.getLongitude(), data.getLatitude());
                }
            });

            //是否已支付 0.未支付 1.已支付
            //支付方式 1.全额支付 2.只支付信息费用。 如果pay_type=1
            if (data.getPay_type() == 1) {
                helper.getView(R.id.tv_confirm).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_tousu).setVisibility(View.VISIBLE);
                if (item.getIs_pay() == 1) {
                    //隐藏投诉按钮
                    helper.getView(R.id.tv_tousu).setVisibility(View.GONE);

                    helper.setText(R.id.tv_confirm, "已支付");
                    helper.getView(R.id.tv_confirm).setBackground(null);

                    helper.getView(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                } else {
                    //是否已投诉 0.未投诉 1.已投诉 is_complaint 2客服已处理
                    if (item.getIs_complaint() == 0) {
                        helper.setText(R.id.tv_tousu, "投诉");
                        helper.getView(R.id.tv_tousu).setBackgroundResource(R.drawable.shape_border_orange_f79c59);

                        helper.getView(R.id.tv_tousu).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (item.getIs_complaint() == 0) {

                                    final BaseDialog baseDialog = new BaseDialog(getActivity());
                                    baseDialog.showThreeButton("投诉提示", "如需投诉请点击确认，管家将冻结该笔资金，请您在两小时内联系客服处理，逾期视为自动放弃，平台将默认打款。\n客服电话：" + data.getConsumer_hotline(), "确认", "取消", "拨打电话", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            baseDialog.dismiss();
                                            ((BackFragmentActivity) getActivity()).
                                                    addFragment(TouSuFragment.newInstance(getArguments().getInt("roleType"), getArguments().getInt("relate_id")), true, true);
//                                            complaint(item.getHour_matching_id(), helper.getAdapterPosition() - 1);
                                        }
                                    }, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            baseDialog.dismiss();
                                        }
                                    }, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            baseDialog.dismiss();
                                            PhoneUtils.dial(data.getConsumer_hotline());
                                        }
                                    });
                                }
                            }
                        });

                        //去支付
                        helper.setText(R.id.tv_confirm, "确认支付");
                        helper.getView(R.id.tv_confirm).setBackgroundResource(R.drawable.shape_border_yellow_fbb939);

                        helper.getView(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final BaseDialog baseDialog = new BaseDialog(getActivity());
                                baseDialog.showTwoButton("提示", "您确定要支付工资？", "确定", "取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        baseDialog.dismiss();
                                        pay(item.getHour_matching_id(), helper.getAdapterPosition() - 1);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        baseDialog.dismiss();
                                    }
                                });
                            }
                        });
                    } else if ((item.getIs_complaint() == 1)) {
                        helper.setText(R.id.tv_tousu, "已投诉");
                        helper.getView(R.id.tv_tousu).setBackground(null);
                        helper.getView(R.id.tv_tousu).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                BaseDialog baseDialog = new BaseDialog(getActivity());
                                baseDialog.showTwoButton("提示", "客服电话：" + data.getConsumer_hotline(), "确认", "取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dismiss();
                                        PhoneUtils.dial(data.getConsumer_hotline());
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dismiss();
                                    }
                                });
                            }
                        });
                        //隐藏支付按钮
                        helper.getView(R.id.tv_confirm).setVisibility(View.GONE);

                    } else if ((item.getIs_complaint() == 2)) {
                        helper.setText(R.id.tv_tousu, "客服已处理");
                        helper.getView(R.id.tv_tousu).setBackground(null);
                        helper.getView(R.id.tv_tousu).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        });

                        //隐藏支付按钮
                        helper.getView(R.id.tv_confirm).setVisibility(View.GONE);

                    }
                }
            } else {
                helper.getView(R.id.tv_confirm).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_tousu).setVisibility(View.GONE);

                //是否评价 0 未评价 大于0评价 evaluation_id
                if (item.getEvaluation_id() == 0) {
                    //未评价
                    helper.setText(R.id.tv_confirm, "去评价");
                    helper.getView(R.id.tv_confirm).setBackgroundResource(R.drawable.shape_border_yellow_fbb939);

                    helper.getView(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //去评价
                            ((BackFragmentActivity) getActivity()).addFragment(PingjiaFragment.newInstance(PageConfig.TYPE_ZHONGDIANGONG, item.getHour_matching_id()), true, true);
                        }
                    });
                } else {
                    //以评价
                    helper.setText(R.id.tv_confirm, "已评价");
                    helper.getView(R.id.tv_confirm).setBackground(null);
                }
            }

            //打电话
            helper.getView(R.id.iv_phone).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                    if (checkPermission(permissions)) {
                        PhoneUtils.dial(item.getContact_tel());
                    }
                }
            });

            //定位
            helper.getView(R.id.tv_location).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(item.getLongitude()), Double.parseDouble(item.getLatitude()), "");
                }
            });
        }
    }


    /**
     * 支付
     *
     * @param hour_matching_id
     */
    private void pay(final int hour_matching_id, final int position) {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_CONFIRM_PAY_ZDG)
                .addParams("hour_matching_id", hour_matching_id + "")
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
                            list.get(position).setIs_pay(1);
                            adapter.notifyDataSetChanged();
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
    public boolean isNeedEventBus() {
        return true;
    }

    @Override
    public boolean autoRefresh() {
        return true;
    }

    @Subscribe
    public void notifyPageRefresh(NotifyPageRefresh event) {
        if (event.getTag().equals(TAG)) {
            refreshPageData();
        }
    }

    public void initPicAdapter() {
        myPublishListPic = new ArrayList<>();
        myPublishAdapter = new PicRecyclerViewAdapter(myPublishListPic);
        myPublishRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 3));
        myPublishRecyclerview.setNestedScrollingEnabled(false);
        myPublishRecyclerview.setAdapter(myPublishAdapter);

        myPublishAdapter.setEnableLoadMore(false);

        myPublishAdapter.isUseEmpty(false);

        //事件监听
        myPublishAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ViewWrap.showPicActivity(getActivity(), myPublishListPic, position);
            }
        });

    }


    class PicRecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PicRecyclerViewAdapter(@Nullable List<String> data) {
            super(R.layout.item_recycler_add_pic, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.getView(R.id.iv_close).setVisibility(View.GONE);
            GlideUtil.show(getActivity(), item, (ImageView) helper.getView(R.id.iv_add_pic));
        }
    }


}
