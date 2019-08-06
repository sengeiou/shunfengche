package com.windmillsteward.jukutech.activity.newpage.yizhan.baomu;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.VideoPlayActivity;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.BaomuInfoDetailsModel;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.VoiceUtils;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoZhaoPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.PingjiaFragment;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.event.NotifyPageRefresh;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.MediaUtils;
import com.windmillsteward.jukutech.utils.ResUtils;
import com.windmillsteward.jukutech.utils.view.PhoneWrap;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 保姆-查看信息
 */
public class BaomuInfoDetailsFragment extends BaseInitFragment {
    public static final String TAG = "BaomuInfoDetailsFragment";
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_xueli)
    TextView tvXueli;
    @Bind(R.id.tv_jingyan)
    TextView tvJingyan;
    @Bind(R.id.tv_leixing)
    TextView tvLeixing;
    @Bind(R.id.tv_shijian)
    TextView tvShijian;
    @Bind(R.id.tv_xinzi)
    TextView tvXinzi;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_nianji)
    TextView tvNianji;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_pipei)
    TextView tv_pipeidu;
    @Bind(R.id.tv_type)
    TextView tv_type;
    @Bind(R.id.iv_phone)
    ImageView iv_phone;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.iv_my_publish_voice)
    ImageView ivMyPublishVoice;
    @Bind(R.id.tv_my_publish_length)
    TextView tvMyPublishLength;
    @Bind(R.id.lay_ll_my_publish_voice)
    LinearLayout layLlMyPublishVoice;
    @Bind(R.id.lay_ll_my_publish_voice_content)
    LinearLayout layLlMyPublishVoiceContent;
    @Bind(R.id.my_publish_recyclerview)
    RecyclerView myPublishRecyclerview;
    @Bind(R.id.iv_my_publish_video)
    ImageView ivMyPublishVideo;
    @Bind(R.id.iv_my_publish_play)
    ImageView ivMyPublishPlay;
    @Bind(R.id.lay_rl_my_publish_video)
    RelativeLayout layRlMyPublishVideo;


    private BaomuInfoDetailsModel currData;
    private int pay_type;//1全额2信息费
    private ArrayList<String> myPublishListPic;
    private PicRecyclerViewAdapter myPublishAdapter;
    private VoiceUtils myPublishvoiceUtils;


    public static BaomuInfoDetailsFragment newInstance(int require_id, int roleType) {
        Bundle args = new Bundle();
        args.putInt("require_id", require_id);
        args.putInt("roleType", roleType);
        BaomuInfoDetailsFragment fragment = new BaomuInfoDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_baomu_info_details;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        if (getArguments() != null) {
            int roleType = getArguments().getInt("roleType");
            if (roleType == PageConfig.TYPE_BAOMU) {
                tv_type.setText("保姆类型");
                setMainTitle("查看保姆应聘信息");
            } else if (roleType == PageConfig.TYPE_YUESAO) {
                tv_type.setText("月嫂类型");
                setMainTitle("查看月嫂应聘信息");
            } else if (roleType == PageConfig.TYPE_YUERSAO) {
                tv_type.setText("育儿嫂类型");
                setMainTitle("查看育儿嫂应聘信息");
            }
        }
        initPicAdapter();
    }

    @Override
    protected void initData() {
        getData();
    }

    /**
     * 获取数据
     */
    private void getData() {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MATCH_REQUIRE_DETAIL)
                .addParams("require_id", getArguments().getInt("require_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<BaomuInfoDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<BaomuInfoDetailsModel> respnse, String source) {
                        showContentView();
                        dismiss();
                        if (respnse.getData() != null) {
                            currData = respnse.getData();
                            setData(currData);
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<BaomuInfoDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    private BaomuInfoDetailsModel currModel;

    //设置数据
    private void setData(final BaomuInfoDetailsModel data) {
        currModel = data;
        pay_type = currModel.getPay_type();
        GlideUtil.show(getActivity(), data.getUser_avatar_url(), ivAvatar);
        tvTime.setText("更新时间：" + DateUtil.toYYYYMMDD(data.getAdd_time() * 1000L));
        tv_name.setText(data.getUser_name() + "    " + data.getSex_name() + "|" + data.getAge() + "岁");
        tv_pipeidu.setText(new SpanUtils().append("匹配度：")
                .append(data.getMatch_value() + "%")
                .setForegroundColor(ResUtils.getCommRed())
                .create()
        );
        tvPhone.setText("联系电话：" + data.getMobile_phone());
        PhoneWrap.bindPhoneView(iv_phone, data.getMobile_phone());
        tvXueli.setText(data.getEducation_background_name());
        tvJingyan.setText(data.getWork_experience_name());
        tvShijian.setText(data.getWork_time_type_name());

        if (data.getSalary_type() == 1) {
            tvXinzi.setText("市场定价");
        } else if (data.getSalary_type() == 2) {
            tvXinzi.setText(data.getSalary_fee() + "-" + data.getEnd_salary_fee() + "元/天");
        } else if (data.getSalary_type() == 3) {
            tvXinzi.setText("面议");
        }
        tvDiqu.setText(data.getArea_name());
        tvLeixing.setText(data.getPerson_type_name());
        List<String> service_contents = data.getService_contents();
        StringBuilder stringBuilder = new StringBuilder();
        if (service_contents != null) {
            for (String service_content : service_contents) {
                stringBuilder.append(service_content + "、");
            }
            if (TextUtils.isEmpty(stringBuilder)) {
                tvNianji.setText("");
            } else {
                tvNianji.setText(stringBuilder.subSequence(0, stringBuilder.length() - 1));
            }
        } else {
            tvNianji.setText("");
        }
        tvDesc.setText(data.getSelf_intro());
        String pic_urls = data.getImage_url();
        if (!TextUtils.isEmpty(pic_urls)) {
            List<String> mypublishPicList = Arrays.asList(pic_urls.split(","));
            myPublishListPic.clear();
            myPublishListPic.addAll(mypublishPicList);
            myPublishAdapter.notifyDataSetChanged();
        }
        if (!TextUtils.isEmpty(data.getVideo_url())) {
            layRlMyPublishVideo.setVisibility(View.VISIBLE);
            ivMyPublishVideo.setOnClickListener(new View.OnClickListener() {
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
                                        ivMyPublishVideo.setImageBitmap(bitmap);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }else{
            layRlMyPublishVideo.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(data.getVoice_url())){
            myPublishvoiceUtils = new VoiceUtils(ivMyPublishVoice);
            layLlMyPublishVoiceContent.setVisibility(View.VISIBLE);
            myPublishvoiceUtils.setFilePath(data.getVoice_url());
            myPublishvoiceUtils.getTime(tvMyPublishLength);
            layLlMyPublishVoice.setOnClickListener(new View.OnClickListener() {
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
        }else{
            layLlMyPublishVoiceContent.setVisibility(View.GONE);
        }



        //是否确认匹配过了 0否 1是。 注：如果 is_match=1，就只显示"确认完成工作"按钮，如果is_match=0，则显示“不感兴趣”和“确认支付工程尾款”
        if (data.getIs_match() == 1) {
            tvRight.setVisibility(View.GONE);
            tvLeft.setVisibility(View.VISIBLE);
            tvLeft.setText("确认完成工作");
            tvLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final BaseDialog baseDialog = new BaseDialog(getActivity());
                    baseDialog.showTwoButton("提示", "是否确认完成工作？", "确定", "取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            baseDialog.dismiss();
                            finishWork();
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            baseDialog.dismiss();
                        }
                    });

                }
            });
            int status = data.getStatus();
            if (status == 4) {//已完成，"不需要显示确认完成工作"按钮
                tvLeft.setVisibility(View.GONE);
                if (data.getEvaluation_id() == 0) {
                    tvLeft.setVisibility(View.VISIBLE);
                    tvLeft.setText("评价");
                    tvLeft.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((BackFragmentActivity) getActivity()).addFragment(PingjiaFragment.newInstance(PageConfig.TYPE_BAOMU, getArguments().getInt("require_id")), true, true);
                        }
                    });
                }
            }
        } else {
            //是否已经点过不感兴趣 0否 1是（点过就不再显示不感兴趣按钮）
            if (data.getIs_uninterested() == 1) {
                tvLeft.setVisibility(View.GONE);
            } else {
                tvLeft.setVisibility(View.VISIBLE);

                tvLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        noInterested();
                    }
                });
            }
            if (pay_type == 1) {
                tvRight.setText("确认用工支付尾款");
                tvRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final BaseDialog baseDialog = new BaseDialog(getActivity());
                        baseDialog.showTwoButton("提示", "是否确认支付尾款？", "确定", "取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                baseDialog.dismiss();
                                pay();
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                baseDialog.dismiss();
                            }
                        });
                    }
                });
            } else if (pay_type == 2) {
                tvRight.setText("确认用工");
                tvRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final BaseDialog baseDialog = new BaseDialog(getActivity());
                        baseDialog.showTwoButton("提示", "是否确认用工？", "确定", "取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                baseDialog.dismiss();
                                onlyPayInfoFee();
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

        }

//        if (tvLeft.getVisibility() == View.VISIBLE && tvRight.getVisibility() == View.VISIBLE) {
//            view_tips.setVisibility(View.VISIBLE);
//        } else {
//            view_tips.setVisibility(View.GONE);
//        }
        tvPhone.setText(data.getMobile_phone());
        if (data.getIs_match() == 1) {//已确认匹配
            tvPhone.setVisibility(View.VISIBLE);
            iv_phone.setVisibility(View.VISIBLE);
        } else {
            tvPhone.setVisibility(View.GONE);
            iv_phone.setVisibility(View.GONE);
        }
        iv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BaseDialog baseDialog = new BaseDialog(getActivity());
                baseDialog.showTwoButton("提示", "是否拨打以下电话" + "\n" + data.getMobile_phone(), "确定", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseDialog.dismiss();
                        PhoneUtils.dial(data.getMobile_phone());
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseDialog.dismiss();
                    }
                });
            }
        });
    }

    /**
     * 只收信息费时，确认用工接口
     */
    private void onlyPayInfoFee() {
        addCall(new NetUtil().setUrl(APIS.URL_BM_YS_YES_COMFIRM_USER)
                .addParams("require_id", getArguments().getInt("require_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<String>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<String> respnse, String source) {
                        dismiss();
                        refreshPageData();
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<String>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }


    //支付尾款
    private void pay() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_PROJECT_PAYMENTRE)
                        .addParams("require_id", getArguments().getInt("require_id") + "")
                        .setCallBackData(new BaseNewNetModelimpl<PublicResultModel>() {
                            @Override
                            protected void onFail(int type, String msg) {
                                showTips(msg);
                            }

                            @Override
                            protected void onSuccess(int code, BaseResultInfo<PublicResultModel> respnse, String source) {
                                if (respnse.getCode() == 0) {
                                    if (respnse.getData() != null) {
                                        NewPayActivity.go(BaomuInfoDetailsFragment.this, CommonPayPresenter.TYPE_BAOMU_WEIKUAN, respnse.getData().getRelate_id(),
                                                respnse.getData().getOrder_price() + "",
                                                respnse.getData().getOrder_name(), 1);
                                    }

//                            showTips("操作成功");
//                            getData();
                                    //去评价
//                            ((BackFragmentActivity) getActivity()).addFragment(PingjiaFragment.newInstance(PageConfig.TYPE_BAOMU, getArguments().getInt("require_id")), true, true);

                                }
                            }

                            @Override
                            protected Type getType() {
                                return new TypeToken<BaseResultInfo<PublicResultModel>>() {
                                }.getType();
                            }
                        }).buildPost()
        );
    }

    //不感兴趣
    private void noInterested() {
        if (currModel != null) {
            int num = currModel.getUninterested_num();
            final BaseDialog baseDialog = new BaseDialog(getActivity());
            baseDialog.showTwoButton("提示", "管家还可以为您提供" + num + "次匹配机会", "重新匹配", "取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseDialog.dismiss();
                    //完成之后 tvLeft 隐藏
                    addCall(new NetUtil().setUrl(APIS.URL_TALENT_UNINTERESTED_REQUIRE)
                            .addParams("require_id", getArguments().getInt("require_id") + "")
                            .setCallBackData(new BaseNewNetModelimpl() {
                                @Override
                                protected void onFail(int type, String msg) {
                                    showTips(msg);
                                }

                                @Override
                                protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                                    if (respnse.getCode() == 0) {
                                        showTips("操作成功");
                                        EventBus.getDefault().post(new NotifyPageRefresh(TAG));
                                        ((BackFragmentActivity) getActivity()).removeFragment();
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
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseDialog.dismiss();
                }
            });
        }
    }

    //确定完成工作
    private void finishWork() {
        //完成之后 tvLeft 隐藏
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_BOSS_CONFIRM_WORK_DONE)
                .addParams("require_id", getArguments().getInt("require_id") + "")
                .setCallBackData(new BaseNewNetModelimpl() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        if (respnse.getCode() == 0) {
                            showTips("确定完成工作成功");
                            getData();
                            //去评价
                            ((BackFragmentActivity) getActivity()).addFragment(PingjiaFragment.newInstance(PageConfig.TYPE_BAOMU, getArguments().getInt("require_id")), true, true);
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

    @Override
    protected void refreshPageData() {
        getData();
    }

    @Override
    public boolean isNeedEventBus() {
        return true;
    }

    @Subscribe
    public void notifyPageRefresh(NotifyPageRefresh event) {
        if (event.getTag().equals(TAG)) {
            refreshPageData();
        }
    }

    @Override
    public boolean autoRefresh() {
        return true;
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
