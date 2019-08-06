package com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao;

import android.Manifest;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
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
import com.windmillsteward.jukutech.activity.newpage.model.JiajiaoYingpinOrderDetailModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.VoiceUtils;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
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

public class JiajiaoYingpinOrderDetailFragment extends BaseInitFragment {
    public static final String TAG = "JiajiaoYingpinOrderDetailFragment";

    @Bind(R.id.tv_tips)
    TextView tvTips;
    @Bind(R.id.lay_ll_tips)
    LinearLayout layLlTips;
    @Bind(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_pipei)
    TextView tvPipei;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.iv_phone)
    ImageView ivPhone;
    @Bind(R.id.lay_ll_header)
    LinearLayout layLlHeader;
    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_bianti)
    TextView tvBianti;
    @Bind(R.id.tv_num)
    TextView tvNum;
    @Bind(R.id.lay_ll_shengyu)
    LinearLayout layLlShengyu;
    @Bind(R.id.tv_zhaopin_kemu)
    TextView tvZhaopinKemu;
    @Bind(R.id.tv_zhaopin_nianji)
    TextView tvZhaopinNianji;
    @Bind(R.id.tv_zhaopin_sex)
    TextView tvZhaopinSex;
    @Bind(R.id.tv_zhaopin_age)
    TextView tvZhaopinAge;
    @Bind(R.id.tv_zhaopin_education)
    TextView tvZhaopinEducation;
    @Bind(R.id.tv_zhaopin_diqu)
    TextView tvZhaopinDiqu;
    @Bind(R.id.tv_zhaopin_address)
    TextView tvZhaopinAddress;
    @Bind(R.id.lay_ll_address)
    LinearLayout layLlAddress;
    @Bind(R.id.tv_zhaopin_yuding)
    TextView tvZhaopinYuding;
    @Bind(R.id.tv_zhaopin_fudao_time)
    TextView tvZhaopinFudaoTime;
    @Bind(R.id.tv_zhaopin_xinzi)
    TextView tvZhaopinXinzi;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.lay_ll_work_detail)
    LinearLayout layLlWorkDetail;
    @Bind(R.id.tv_pipei_status)
    TextView tvPipeiStatus;
    @Bind(R.id.tv_my_publish_fabusj)
    TextView tvMyPublishFabusj;
    @Bind(R.id.tv_my_publish_xinxifei)
    TextView tvMyPublishXinxifei;
    @Bind(R.id.tv_my_publish_name)
    TextView tvMyPublishName;
    @Bind(R.id.tv_my_publish_sex)
    TextView tvMyPublishSex;
    @Bind(R.id.tv_my_publish_age)
    TextView tvMyPublishAge;
    @Bind(R.id.tv_my_publish_contact_tel)
    TextView tvMyPublishContactTel;
    @Bind(R.id.tv_my_publish_education)
    TextView tvMyPublishEducation;
    @Bind(R.id.tv_my_publish_kemu)
    TextView tvMyPublishKemu;
    @Bind(R.id.tv_my_publish_nianji)
    TextView tvMyPublishNianji;
    @Bind(R.id.tv_my_publish_diqu)
    TextView tvMyPublishDiqu;
    @Bind(R.id.tv_my_publish_fudaosj)
    TextView tvMyPublishfudaosj;
    @Bind(R.id.tv_my_publish_xinzi)
    TextView tvMyPublishXinzi;
    @Bind(R.id.tv_my_publish_desc)
    TextView tvMyPublishDesc;
    @Bind(R.id.iv_zhaopin_voice)
    ImageView ivZhaopinVoice;
    @Bind(R.id.tv_zhaopin_length)
    TextView tvZhaopinLength;
    @Bind(R.id.lay_ll_zhaopin_voice)
    LinearLayout layLlZhaopinVoice;
    @Bind(R.id.lay_ll_zhaopin_voice_content)
    LinearLayout layLlZhaopinVoiceContent;
    @Bind(R.id.zhaopin_recyclerview)
    RecyclerView zhaopinRecyclerview;
    @Bind(R.id.iv_zhaopin_video)
    ImageView ivZhaopinVideo;
    @Bind(R.id.iv_zhaopin_play)
    ImageView ivZhaopinPlay;
    @Bind(R.id.lay_rl_zhaopin_video)
    RelativeLayout layRlZhaopinVideo;
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
    @Bind(R.id.tv_zhaopin_pic_tips)
    TextView tvZhaopinPicTips;
    @Bind(R.id.tv_my_publish_pic_tips)
    TextView tvMyPublishPicTips;
    @Bind(R.id.lay_ll_my_publish_age)
    LinearLayout layLlMyPublishAge;

    private ArrayList<String> myPublishListPic;
    private ArrayList<String> zhaopinListPic;
    private PicRecyclerViewAdapter myPublishAdapter;
    private PicRecyclerViewAdapter zhaopinAdapter;
    private VoiceUtils myPublishvoiceUtils;
    private VoiceUtils zhaoPinvoiceUtils;

    public static JiajiaoYingpinOrderDetailFragment newInstance(int when_tutor_id) {
        Bundle args = new Bundle();
        args.putInt("when_tutor_id", when_tutor_id);
        JiajiaoYingpinOrderDetailFragment fragment = new JiajiaoYingpinOrderDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_jiajiao_yingpin_order_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("家教-我要当家教工作详情");
        initPicAdapter();
    }

    @Override
    protected void initData() {

        addCall(new NetUtil().setUrl(APIS.URL_JIAJIAO_YINGPIN_ORDER_DETAIL)
                .addParams("when_tutor_id", getArguments().getInt("when_tutor_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<JiajiaoYingpinOrderDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<JiajiaoYingpinOrderDetailModel> respnse, String source) {
                        showContentView();
                        dismiss();
                        if (respnse.getData() != null) {
                            setData(respnse.getData());
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<JiajiaoYingpinOrderDetailModel>>() {
                        }.getType();
                    }
                })
                .buildPost()
        );
    }

    private void setData(final JiajiaoYingpinOrderDetailModel data) {
        layLlHeader.setVisibility(View.GONE);
        layLlTips.setVisibility(View.GONE);
        layLlWorkDetail.setVisibility(View.GONE);
        int is_match = data.getIs_match();
        if (is_match == 2) {//已匹配
            tvPipeiStatus.setTextColor(Color.parseColor("#ff0000"));
            tvPipeiStatus.setText("已匹配");
        } else if (is_match == 3) {
            tvPipeiStatus.setTextColor(Color.parseColor("#fdbe44"));
            tvPipeiStatus.setText("匹配失败");
        } else if (is_match == 4) {
            tvPipeiStatus.setTextColor(Color.parseColor("#FF0000"));
            tvPipeiStatus.setText("匹配成功");
            final JiajiaoYingpinOrderDetailModel.LookForTutorBean look_for_tutor = data.getLook_for_tutor();
            if (look_for_tutor != null) {
                layLlHeader.setVisibility(View.VISIBLE);
                layLlTips.setVisibility(View.VISIBLE);
                layLlWorkDetail.setVisibility(View.VISIBLE);
                //-------------------匹配到的招聘方------------------------\\

//                tvTime.setText("更新时间:"+DateUtil.StampTimeToDate(look_for_tutor.getAdd_time() + "", "yyyy-MM-dd HH:mm"));
                String age = "";
                if (look_for_tutor.getAge() != 0){
                    age = look_for_tutor.getAge()+"岁";
                }
                tvName.setText(StringUtil.notEmptyBackValue(look_for_tutor.getUser_name()) + " " + (look_for_tutor.getSex() == 1 ? "男" : "女") + " " + age);
                GlideUtil.show(getActivity(), look_for_tutor.getUser_avatar_url(), ivAvatar);
                tvPipei.setText(new SpanUtils().append("匹配度：")
                        .append(look_for_tutor.getMatch_value() + "%")
                        .setForegroundColor(ResUtils.getCommRed())
                        .create());
                final String mobile_phone = look_for_tutor.getMobile_phone();
                final String user_name = look_for_tutor.getUser_name();
                tvPhone.setText(TextUtils.isEmpty(look_for_tutor.getMobile_phone()) ? "" : look_for_tutor.getMobile_phone());
                ivPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final BaseDialog baseDialog = new BaseDialog(getActivity());
                        baseDialog.showTwoButton("提示", "是否拨打电话?\n" + mobile_phone + "\n" + user_name
                                , "拨打", "取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (baseDialog != null) {
                                            baseDialog.dismiss();
                                        }
                                        String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                                        if (checkPermission(permissions)) {
                                            PhoneUtils.dial(mobile_phone);
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

                tvBianhao.setText(TextUtils.isEmpty(look_for_tutor.getOrder_sn()) ? "" : look_for_tutor.getOrder_sn());
                tvBianti.setText(TextUtils.isEmpty(look_for_tutor.getTitle()) ? "" : look_for_tutor.getTitle());
                tvZhaopinKemu.setText(TextUtils.isEmpty(look_for_tutor.getCoach_subject_name()) ? "" : look_for_tutor.getCoach_subject_name());
                tvZhaopinNianji.setText(TextUtils.isEmpty(look_for_tutor.getCoach_grade_name()) ? "" : look_for_tutor.getCoach_grade_name());
                tvZhaopinNianji.setText(TextUtils.isEmpty(look_for_tutor.getCoach_grade_name()) ? "" : look_for_tutor.getCoach_grade_name());
                tvZhaopinSex.setText(look_for_tutor.getSex() == 1 ? "男" : "女");
                tvZhaopinAge.setText(look_for_tutor.getAge_name());
                tvZhaopinEducation.setText(TextUtils.isEmpty(look_for_tutor.getEducation_background_name()) ? "" : look_for_tutor.getEducation_background_name());
                if (StringUtil.isAllNotEmpty(look_for_tutor.getWork_second_area_name(), look_for_tutor.getWork_third_area_name())) {
                    tvZhaopinDiqu.setText(look_for_tutor.getWork_second_area_name() + look_for_tutor.getWork_third_area_name());
                }
                final String longitude = look_for_tutor.getLongitude();
                final String latitude = look_for_tutor.getLatitude();
                final String address = look_for_tutor.getAddress();
                tvZhaopinAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (StringUtil.isAllNotEmpty(longitude, latitude)) {
                            MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(longitude), Double.parseDouble(latitude), address);
                        }
                    }
                });
                tvZhaopinAddress.setText(TextUtils.isEmpty(address) ? "" : address);//详细地址
                tvZhaopinYuding.setText(DateUtil.StampTimeToDate(look_for_tutor.getStart_booking_time() + "", "yyyy-MM-dd")
                        + "至" + DateUtil.StampTimeToDate(look_for_tutor.getEnd_booking_time() + "", "yyyy-MM-dd"));
                tvZhaopinFudaoTime.setText(TextUtils.isEmpty(look_for_tutor.getCoach_time_name()) ? "" : look_for_tutor.getCoach_time_name());
                if (look_for_tutor.getSalary_type() == 2) {
                    if (StringUtil.isAllNotEmpty(look_for_tutor.getStart_salary(), look_for_tutor.getEnd_salary())) {
                        tvZhaopinXinzi.setText(look_for_tutor.getStart_salary() + "-" + look_for_tutor.getStart_salary() + "元");
                    }
                } else if (look_for_tutor.getSalary_type() == 3) {
                    tvZhaopinXinzi.setText("面议");
                }
                tvDesc.setText(TextUtils.isEmpty(look_for_tutor.getWork_info()) ? "" : look_for_tutor.getWork_info());

                String zhaopin_pic_urls = look_for_tutor.getImage_url();
                if (!TextUtils.isEmpty(zhaopin_pic_urls)) {
                    List<String> zhaopinPicList = Arrays.asList(zhaopin_pic_urls.split(","));
                    zhaopinListPic.clear();
                    if (zhaopinPicList != null) {
                        if (zhaopinPicList.size() == 0) {
                            tvZhaopinPicTips.setVisibility(View.GONE);
                        } else {
                            tvZhaopinPicTips.setVisibility(View.VISIBLE);
                        }
                    }
                    zhaopinListPic.addAll(zhaopinPicList);
                    zhaopinAdapter.notifyDataSetChanged();
                } else {
                    tvZhaopinPicTips.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(look_for_tutor.getVideo_url())) {
                    layRlZhaopinVideo.setVisibility(View.VISIBLE);
                    ivZhaopinVideo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (!TextUtils.isEmpty(look_for_tutor.getVideo_url())) {
                                Bundle bundle = new Bundle();
                                bundle.putString(VideoPlayActivity.VIDEO_URL, look_for_tutor.getVideo_url());
                                startAtvDonFinish(VideoPlayActivity.class, bundle);
                            }
                        }
                    });
                    MediaUtils.getImageForVideo(look_for_tutor.getVideo_url(), new MediaUtils.OnLoadVideoImageListener() {
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
                                                ivZhaopinVideo.setImageBitmap(bitmap);
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
                    layRlZhaopinVideo.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(look_for_tutor.getVoice_url())) {
                    layLlZhaopinVoiceContent.setVisibility(View.VISIBLE);
                    zhaoPinvoiceUtils = new VoiceUtils(ivZhaopinVoice);
                    zhaoPinvoiceUtils.setFilePath(look_for_tutor.getVoice_url());
                    zhaoPinvoiceUtils.getTime(tvZhaopinLength);
                    layLlZhaopinVoice.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            boolean playing = zhaoPinvoiceUtils.isPlaying();
                            if (playing) {
                                zhaoPinvoiceUtils.stop();
                            } else {
                                zhaoPinvoiceUtils.play();
                            }
                        }
                    });
                } else {
                    layLlZhaopinVoiceContent.setVisibility(View.GONE);
                }

            }
        } else {
            tvPipeiStatus.setTextColor(Color.parseColor("#239491"));
            tvPipeiStatus.setText("正在匹配中");
        }

        //--------------------------------我发布的信息-------------------------------\\
        //发布时间
        tvMyPublishFabusj.setText(DateUtil.StampTimeToDate(data.getAdd_time() + "", "yyyy-MM-dd HH:mm:ss"));
        //信息费
        tvMyPublishXinxifei.setText(data.getInfo_fee() + "元");
        tvMyPublishName.setText(TextUtils.isEmpty(data.getUser_name()) ? "" : data.getUser_name());
        tvMyPublishSex.setText(data.getSex() == 1 ? "男" : "女");
        if (data.getAge() != 0) {
            layLlMyPublishAge.setVisibility(View.VISIBLE);
            tvMyPublishAge.setText(data.getAge() + "岁");
        } else {
            layLlMyPublishAge.setVisibility(View.GONE);
        }

        tvMyPublishContactTel.setText(TextUtils.isEmpty(data.getMobile_phone()) ? "" : data.getMobile_phone());
        tvMyPublishEducation.setText(TextUtils.isEmpty(data.getEducation_background_name()) ? "" : data.getEducation_background_name() );
        tvMyPublishKemu.setText(TextUtils.isEmpty(data.getCoach_subject_name()) ? "" : data.getCoach_subject_name());
        tvMyPublishNianji.setText(TextUtils.isEmpty(data.getCoach_grade_name()) ? "" : data.getCoach_grade_name());
        if (StringUtil.isAllNotEmpty(data.getWork_second_area_name(), data.getWork_third_area_name())) {
            tvMyPublishDiqu.setText(data.getWork_second_area_name() + data.getWork_third_area_name());
        }
        tvMyPublishfudaosj.setText(TextUtils.isEmpty(data.getCoach_time_name()) ? "" : data.getCoach_time_name());
        if (data.getSalary_type() == 2) {
            if (StringUtil.isAllNotEmpty(data.getSalary(), data.getEnd_salary_fee())) {
                tvMyPublishXinzi.setText(data.getSalary() + "-" + data.getEnd_salary_fee() + "元");
            }
        } else if (data.getSalary_type() == 3) {
            tvMyPublishXinzi.setText("面议");
        }
        //自我介绍
        tvMyPublishDesc.setText(TextUtils.isEmpty(data.getSelf_intro()) ? "" : data.getSelf_intro());
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
        } else {
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
        } else {
            layRlMyPublishVideo.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(data.getVoice_url())) {
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
        } else {
            layLlMyPublishVoiceContent.setVisibility(View.GONE);
        }

    }

    @Override
    protected void refreshPageData() {

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

        zhaopinListPic = new ArrayList<>();
        zhaopinAdapter = new PicRecyclerViewAdapter(zhaopinListPic);
        zhaopinRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 4));
        zhaopinRecyclerview.setNestedScrollingEnabled(false);
        zhaopinRecyclerview.setAdapter(zhaopinAdapter);

        zhaopinAdapter.setEnableLoadMore(false);

        zhaopinAdapter.isUseEmpty(false);

        //事件监听
        zhaopinAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ViewWrap.showPicActivity(getActivity(), zhaopinListPic, position);
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
