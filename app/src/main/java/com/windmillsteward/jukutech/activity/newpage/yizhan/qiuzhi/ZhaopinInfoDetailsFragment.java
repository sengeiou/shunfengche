package com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi;


import android.Manifest;
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
import com.windmillsteward.jukutech.activity.newpage.model.GetPhoneModel;
import com.windmillsteward.jukutech.activity.newpage.model.ZhaopinInfoDetailsModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.VoiceUtils;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.PingjiaFragment;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.MediaUtils;
import com.windmillsteward.jukutech.utils.ResUtils;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

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
 * 求职招聘--招聘方查看简历
 */
public class ZhaopinInfoDetailsFragment extends BaseInitFragment {
    public static final String TAG = "ZhaopinInfoDetailsFragment";
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_xueli)
    TextView tvXueli;
    @Bind(R.id.tv_jingyan)
    TextView tvJingyan;
    @Bind(R.id.tv_zhiwei)
    TextView tvZhiwei;
    @Bind(R.id.tv_xinzi)
    TextView tvXinzi;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_fuli)
    TextView tvFuli;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_name)
    TextView tvInfo;
    @Bind(R.id.tv_pipei)
    TextView tvPipeidu;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.iv_phone)
    ImageView iv_phone;
    @Bind(R.id.iv_my_publish_voice)
    ImageView ivMyPublishVoice;
    @Bind(R.id.tv_my_publish_length)
    TextView tvMyPublishLength;
    @Bind(R.id.lay_ll_my_publish_voice)
    LinearLayout layLlMyPublishVoice;
    @Bind(R.id.lay_ll_my_publish_voice_content)
    LinearLayout layLlMyPublishVoiceContent;
    @Bind(R.id.tv_my_publish_pic_tips)
    TextView tvMyPublishPicTips;
    @Bind(R.id.my_publish_recyclerview)
    RecyclerView myPublishRecyclerview;
    @Bind(R.id.iv_my_publish_video)
    ImageView ivMyPublishVideo;
    @Bind(R.id.iv_my_publish_play)
    ImageView ivMyPublishPlay;
    @Bind(R.id.lay_rl_my_publish_video)
    RelativeLayout layRlMyPublishVideo;

    private ZhaopinInfoDetailsModel data;
    private BaseDialog baseDialog = null;


    private ArrayList<String> myPublishListPic;
    private PicRecyclerViewAdapter myPublishAdapter;
    private VoiceUtils myPublishvoiceUtils;

    public static ZhaopinInfoDetailsFragment newInstance(int job_resume_id) {
        Bundle args = new Bundle();
        args.putInt("job_resume_id", job_resume_id);
        ZhaopinInfoDetailsFragment fragment = new ZhaopinInfoDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_zhaopin_info_details;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("求职招聘-应聘方详情");
        showContentView();
        initPicAdapter();
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_JOB_SEEKER_DETAILS)
                .addParams("job_resume_id", getArguments().getInt("job_resume_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<ZhaopinInfoDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        showErrorView();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<ZhaopinInfoDetailsModel> respnse, String source) {
                        showContentView();
                        dismiss();

                        if (respnse.getData() != null) {
                            data = respnse.getData();
                            iv_phone.setVisibility(View.GONE);
                            tv_phone.setText("");

                            tvInfo.setText(data.getTrue_name() + "    " + (data.getSex() == 1 ? "男" : "女") + "|" + data.getAge() + "岁");
                            tvTime.setText("更新时间：" + data.getUpdate_time());
                            tvXueli.setText(data.getEducation_background_name());
                            tvJingyan.setText(data.getWork_year_name());
                            tvZhiwei.setText(data.getJob_name());
                            if (StringUtil.isAllNotEmpty(data.getWork_second_area_name(),data.getWork_third_area_name())){
                                tvDiqu.setText(data.getWork_second_area_name()+data.getWork_third_area_name());
                            }
                            if (data.getSalary_type() == 1) {
                                tvXinzi.setText("市场定价");
                            } else if (data.getSalary_type() == 2) {
                                if (!TextUtils.isEmpty(data.getSalary_fee()) && !TextUtils.isEmpty(data.getEnd_salary_fee())) {
                                    tvXinzi.setText(data.getSalary_fee() + "-" + data.getEnd_salary_fee() + "元/月");
                                }
                            } else if (data.getSalary_type() == 3) {
                                tvXinzi.setText("面议");
                            }
                            List<String> benefit_name_list = data.getBenefit_ids();
                            StringBuilder stringBuilder = new StringBuilder();
                            for (String s : benefit_name_list) {
                                stringBuilder.append(s + "、");
                            }
                            String fuli = stringBuilder.toString();
                            if (!TextUtils.isEmpty(fuli)) {
                                tvFuli.setText(fuli.substring(0, fuli.length() - 1));
                            }
                            tvDesc.setText(data.getSelf_intro());
                            String pic_urls = data.getImage_url();
                            if (!TextUtils.isEmpty(pic_urls)) {
                                List<String> mypublishPicList = Arrays.asList(pic_urls.split(","));
                                myPublishListPic.clear();
                                if (mypublishPicList != null) {
                                    if (mypublishPicList.size() == 0) {
                                        tvMyPublishPicTips.setVisibility(View.GONE);
                                    } else {
                                        tvMyPublishPicTips.setVisibility(View.VISIBLE);
                                    }
                                    myPublishListPic.addAll(mypublishPicList);
                                    myPublishAdapter.notifyDataSetChanged();
                                }
                            }else{
                                tvMyPublishPicTips.setVisibility(View.GONE);
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

                            //设置匹配度
                            tvPipeidu.setText(new SpanUtils().append("匹配度：")
                                    .append(data.getMatch_value() + "%")
                                    .setForegroundColor(ResUtils.getCommRed())
                                    .create());

                            GlideUtil.show(getActivity(), data.getUser_avatar_url(), ivAvatar);

                            //状态（0.待匹配 1.已匹配待确认 2.已完成，3.超时订单）

                            //是否已经不感兴趣：0否 1是（点过就不再显示不感兴趣按钮）
                            if (data.getIs_uninterested() == 0) {
                                tvLeft.setVisibility(View.VISIBLE);
                            } else {
                                tvLeft.setVisibility(View.GONE);
                            }
                            if (data.getIs_view() == 1) {
                                tvLeft.setVisibility(View.GONE);
                                tvRight.setText("拨打电话");
                            }
                            //不感兴趣
                            tvLeft.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    unInterested();
                                }
                            });
                            if (data.getPublish_status() == 2) {
                                if (data.getEvaluation_id() == 0) {
                                    tvLeft.setText("评价");
                                    tvLeft.setVisibility(View.VISIBLE);
                                    tvLeft.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ((BackFragmentActivity) getActivity()).addFragment(PingjiaFragment.newInstance(PageConfig.TYPE_ZHAOPIN,
                                                    getArguments().getInt("job_resume_id")), true, true);
                                        }
                                    });
                                } else {
                                    tvLeft.setVisibility(View.GONE);
                                }

                            }
                            //获取联系方式
                            tvRight.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (baseDialog == null) {
                                        baseDialog = new BaseDialog(getActivity());
                                    }
                                    String content = "";
                                    if (data.getIs_view() == 1) {
                                        content = "是否拨打以下电话？" + "\n" + data.getMobile_phone();
                                    } else {
                                        content = "是否获取联系方式";
                                    }
                                    baseDialog.showTwoButton("提示", content
                                            , "确定", "取消", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    if (baseDialog != null) {
                                                        baseDialog.dismiss();
                                                    }
                                                    if (data.getIs_view() == 1) {
                                                        if (!TextUtils.isEmpty(data.getMobile_phone())) {
                                                            PhoneUtils.dial(data.getMobile_phone());
                                                        }
                                                    } else {
                                                        getPhone();
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
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<ZhaopinInfoDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    //不感兴趣
    private void unInterested() {
        if (data != null) {
            int num = data.getUninterested_num();
            final BaseDialog baseDialog = new BaseDialog(getActivity());
            baseDialog.showTwoButton("提示", "管家还可以为您提供" + num + "次匹配机会", "重新匹配", "取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseDialog.dismiss();
                    //完成之后 tvLeft 隐藏
                    showDialog();
                    addCall(new NetUtil().setUrl(APIS.URL_TALENT_UNINTERESTED_JOB_SEEKER)
                            .addParams("job_resume_id", getArguments().getInt("job_resume_id") + "")
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
                                        showTips("操作成功");
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

    //获取联系方式
    private void getPhone() {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_GET_JOB_SEEKER_CONTACT_INFORMATION)
                .addParams("job_resume_id", getArguments().getInt("job_resume_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<GetPhoneModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, final BaseResultInfo<GetPhoneModel> respnse, String source) {
                        dismiss();
                        if (respnse.getData() != null) {
                            //获取电话号码成功需要把不感兴趣按钮隐藏起来,刷新一下数据
                            initData();
                            if (baseDialog == null) {
                                baseDialog = new BaseDialog(getActivity());
                            }
                            baseDialog.showTwoButton("提示", "是否拨打电话?\n" + respnse.getData().getTrue_name() + "\n" + respnse.getData().getMobile_phone()
                                    , "拨打", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (baseDialog != null) {
                                                baseDialog.dismiss();
                                            }
                                            String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                                            if (checkPermission(permissions)) {
                                                PhoneUtils.dial(respnse.getData().getMobile_phone());
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
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<GetPhoneModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    @Override
    protected void refreshPageData() {
        initData();
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
