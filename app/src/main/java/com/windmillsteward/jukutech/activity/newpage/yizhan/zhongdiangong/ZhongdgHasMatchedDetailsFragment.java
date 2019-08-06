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
import com.windmillsteward.jukutech.activity.newpage.model.ZhongdgMatchedDetailsModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.VoiceUtils;
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
 * 已匹配到的钟点工职位详情
 */
public class ZhongdgHasMatchedDetailsFragment extends BaseInitFragment {
    public static final String TAG = "ZhongdgHasMatchedDetailsFragment";
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_pipeidu)
    TextView tvPipeidu;
    @Bind(R.id.iv_phone)
    ImageView ivPhone;
    @Bind(R.id.tv_nianji)
    TextView tvNianji;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_shijian)
    TextView tvShijian;
    @Bind(R.id.tv_work_hour)
    TextView tvWorkHour;
    @Bind(R.id.tv_xinzi)
    TextView tvXinzi;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
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
    @Bind(R.id.tv_my_publish_pic_tips)
    TextView tvMyPublishPicTips;

    private ArrayList<String> myPublishListPic;
    private PicRecyclerViewAdapter myPublishAdapter;
    private VoiceUtils myPublishvoiceUtils;

    public static ZhongdgHasMatchedDetailsFragment newInstance(int hour_matching_id) {
        Bundle args = new Bundle();
        args.putInt("hour_matching_id", hour_matching_id);
        ZhongdgHasMatchedDetailsFragment fragment = new ZhongdgHasMatchedDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_zhongdg_has_matched_details;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("简历详情");
        initPicAdapter();
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MATCH_RESUME_DETAIL_ZDG)
                .addParams("hour_matching_id", getArguments().getInt("hour_matching_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<ZhongdgMatchedDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<ZhongdgMatchedDetailsModel> respnse, String source) {
                        showContentView();
                        if (respnse.getData() != null) {
                            final ZhongdgMatchedDetailsModel data = respnse.getData();
                            GlideUtil.show(getActivity(), data.getUser_avatar_url(), ivAvatar);
                            tvTime.setText("更新时间：" + data.getAdd_time());
                            tvName.setText(data.getName() + "    " + (data.getSex() == 1 ? "男" : "女") + "|" + data.getAge() + "岁");
                            tv_phone.setText("联系电话：" + data.getContact_tel());
                            StringBuilder result = new StringBuilder();
                            for (String s : data.getService_list()) {
                                result.append(s + "、");
                            }
                            String result_str =  result.toString();
                            if (!TextUtils.isEmpty(result_str)) {
                                tvNianji.setText(result_str.substring(0,result_str.length()-1));
                            }
                            tvDiqu.setText(data.getWork_third_area_name());
                            tvShijian.setText(data.getWork_date());
                            if (data.getWork_hour() == 0) {
                                tvWorkHour.setText("全天");
                            } else {
                                tvWorkHour.setText(data.getWork_hour() + "小时");
                            }
                            if (data.getSalary_type() == 1) {
                                tvXinzi.setText("市场定价");
                            } else if (data.getSalary_type() == 2) {
                                if (!TextUtils.isEmpty(data.getSalary_start()) && !TextUtils.isEmpty(data.getSalary_end())) {
                                    tvXinzi.setText(data.getSalary_start() + "-" + data.getSalary_end() + "元/小时");
                                }
                            } else if (data.getSalary_type() == 3) {
                                tvXinzi.setText("面议");
                            }
                            tvXinzi.setText(data.getSalary_fee() + "");
                            tvDesc.setText(data.getSelf_intro());
                            String pic_urls = data.getImage_url();
                            if (!TextUtils.isEmpty(pic_urls)) {
                                List<String> mypublishPicList = Arrays.asList(pic_urls.split(","));
                                myPublishListPic.clear();
                                if (mypublishPicList != null) {
                                    if (mypublishPicList.size() == 0){
                                        tvMyPublishPicTips.setVisibility(View.GONE);
                                    }else{
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
                            tvPipeidu.setText(new SpanUtils().append("匹配度：")
                                    .append(data.getMatch_value() + "%")
                                    .setForegroundColor(ResUtils.getCommRed())
                                    .create());

                            ivPhone.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                                    if (checkPermission(permissions)) {
                                        PhoneUtils.dial(data.getContact_tel());
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<ZhongdgMatchedDetailsModel>>() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
