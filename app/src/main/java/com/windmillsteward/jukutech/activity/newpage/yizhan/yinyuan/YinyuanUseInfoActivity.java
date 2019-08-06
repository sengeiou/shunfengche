package com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.windmillsteward.jukutech.activity.newpage.model.GetPhoneModel;
import com.windmillsteward.jukutech.activity.newpage.model.YinyuanInfoDetailsModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.VoiceUtils;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 查看姻缘个人资料
 */
public class YinyuanUseInfoActivity extends BaseInitActivity {


    @Bind(R.id.tv_jieshao)
    TextView tvJieshao;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.iv_video)
    ImageView ivVideo;
    @Bind(R.id.iv_play)
    ImageView ivPlay;
    @Bind(R.id.tv_car)
    TextView tvCar;
    @Bind(R.id.tv_house)
    TextView tvHouse;
    @Bind(R.id.tv_no)
    TextView tvNo;
    @Bind(R.id.tv_yes)
    TextView tvYes;
    @Bind(R.id.lay_rl_video)
    RelativeLayout layRlVideo;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_contact_tel)
    TextView tvContactTel;
    @Bind(R.id.lay_ll_mobile_phone)
    LinearLayout layLlMobilePhone;
    @Bind(R.id.tv_education)
    TextView tvEducation;
    @Bind(R.id.tv_height)
    TextView tvHeight;
    @Bind(R.id.tv_weight)
    TextView tvWeight;
    @Bind(R.id.tv_gangwei)
    TextView tvGangwei;
    @Bind(R.id.tv_yueshouru)
    TextView tvYueshouru;
    @Bind(R.id.tv_hujidi)
    TextView tvHujidi;
    @Bind(R.id.tv_juzhudi)
    TextView tvJuzhudi;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_hyzt)
    TextView tvHyzt;
    @Bind(R.id.tv_znqk)
    TextView tvZnqk;
    @Bind(R.id.iv_voice)
    ImageView ivVoice;
    @Bind(R.id.tv_length)
    TextView tvLength;
    @Bind(R.id.tv_pic_tips)
    TextView tvPicTips;
    @Bind(R.id.lay_ll_voice)
    LinearLayout layLlVoice;
    @Bind(R.id.lay_ll_voice_content)
    LinearLayout layLlVoiceContent;

    private int match_id;
    private YinyuanInfoDetailsModel data;
    private VoiceUtils myPublishvoiceUtils;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_yinyuan_user_info;
    }

    @Override
    protected void initView(View view) {
        setMainTitle("个人资料");

        if (getIntent() != null) {
            match_id = getIntent().getIntExtra("match_id", 0);
        }

        initAdapter();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MARRIAGE_MATCH_DETAIL)
                .addParams("match_id", match_id + "")
                .setCallBackData(new BaseNewNetModelimpl<YinyuanInfoDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        showErrorView();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<YinyuanInfoDetailsModel> respnse, String source) {
                        dismiss();
                        showContentView();
                        if (respnse.getData() != null) {
                            //设置数据
                            data = respnse.getData();

                            tvName.setText(StringUtil.notEmptyBackValue(data.getUser_name()));
                            if (TextUtils.isEmpty(data.getMobile_phone())){
                                layLlMobilePhone.setVisibility(View.GONE);
                            }else{
                                layLlMobilePhone.setVisibility(View.VISIBLE);
                                tvContactTel.setText(data.getMobile_phone());
                            }
                            tvSex.setText(data.getSex() == 1 ? "男" : "女");
                            tvAge.setText(data.getAge() + "");
                            tvEducation.setText(StringUtil.notEmptyBackValue(data.getEducation_background_name()));
                            tvHeight.setText(data.getHeight()+"cm");
                            tvWeight.setText(data.getWeight()+"kg");
                            tvGangwei.setText(StringUtil.notEmptyBackValue(data.getJob_class_id_two_name()));
                            tvYueshouru.setText(StringUtil.notEmptyBackValue(data.getSalary_name()));
                            tvJuzhudi.setText(StringUtil.notEmptyBackValue(data.getLive_second_area_name()));
                            tvHujidi.setText(StringUtil.notEmptyBackValue(data.getFirst_area_name()));
                            tvDiqu.setText(StringUtil.notEmptyBackValue(data.getRelease_third_area_name()));

                            if (data.getMarital_status() == 0){
                                tvHyzt.setText("");
                            }else{
                                tvHyzt.setText(data.getMarital_status() == 1 ? "未婚" : "离异");
                            }
                            if (data.getChild_status() == 0){
                                tvZnqk.setText("");
                            }else{
                                tvZnqk.setText(data.getChild_status() == 1 ? "有子女" : "无子女");
                            }


                            tvJieshao.setText(data.getSelf_intro());

                            String pic_urls = data.getPic_urls();
                            if (!TextUtils.isEmpty(pic_urls)) {
                                List<String> lists = Arrays.asList(pic_urls.split(","));
                                if (lists != null && !lists.isEmpty()) {
                                    tvPicTips.setVisibility(View.VISIBLE);
                                    list.addAll(lists);
                                    adapter.notifyDataSetChanged();
                                }else{
                                    tvPicTips.setVisibility(View.GONE);
                                }
                            }
                            if (data.getIs_uninterested() == 1) {
                                tvNo.setVisibility(View.GONE);
                            }
                            if (data.getUninterested_num() == 0) {
                                tvNo.setVisibility(View.GONE);
                            }
                            if (data.getMatching_stauts() == 3 || data.getMatching_stauts() == 4) {
                                tvNo.setVisibility(View.GONE);
                            }
                            if (!TextUtils.isEmpty(data.getAssets_require())) {
                                if (data.getAssets_require().contains("1")) {
                                    tvCar.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.select), null, null, null);
                                } else {
                                    tvCar.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.unselect), null, null, null);
                                }
                                if (data.getAssets_require().contains("2")) {
                                    tvHouse.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.select), null, null, null);
                                } else {
                                    tvHouse.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.unselect), null, null, null);
                                }
                            }
                            if (TextUtils.isEmpty(data.getVideo_url())) {
                                layRlVideo.setVisibility(View.GONE);
                            } else {
                                layRlVideo.setVisibility(View.VISIBLE);
                            }
                            if (!TextUtils.isEmpty(data.getVoice_url())) {
                                myPublishvoiceUtils = new VoiceUtils(ivVoice);
                                layLlVoiceContent.setVisibility(View.VISIBLE);
                                myPublishvoiceUtils.setFilePath(data.getVoice_url());
                                myPublishvoiceUtils.getTime(tvLength);
                                layLlVoice.setOnClickListener(new View.OnClickListener() {
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
                                layLlVoiceContent.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<YinyuanInfoDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    @OnClick({R.id.iv_play, R.id.tv_no, R.id.tv_yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_play:
                if (data != null) {
                    String s = data.getVideo_url();
                    if (!TextUtils.isEmpty(s)) {
                        Bundle bundle = new Bundle();
                        bundle.putString(VideoPlayActivity.VIDEO_URL, s);
                        startAtvDonFinish(VideoPlayActivity.class, bundle);
                    }
                }
                break;
            case R.id.tv_no:
                noInterested();
                break;
            case R.id.tv_yes:
                //满意
                final BaseDialog baseDialog = new BaseDialog(this);
                String content = "";
                if (TextUtils.isEmpty(data.getMobile_phone())) {
                    content = "是否获取联系方式";
                } else {
                    content = "是否拨打以下电话" + "\n" + data.getMobile_phone();
                }
                baseDialog.showTwoButton("提示", content, "确定", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(data.getMobile_phone())) {
                            getPhone();

                        } else {
                            PhoneUtils.dial(data.getMobile_phone());
                        }
                        baseDialog.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseDialog.dismiss();
                    }
                });
                break;
        }
    }

    private BaseDialog baseDialog;

    //获取联系方式
    private void getPhone() {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_GET_CONTACT_INFORMATION)
                .addParams("match_id", match_id + "")
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
                            refreshPageData();//先刷新页面
                            if (baseDialog == null) {
                                baseDialog = new BaseDialog(YinyuanUseInfoActivity.this);
                            }

                            baseDialog.showTwoButton("提示", "是否拨打电话?\n" + respnse.getData().getUser_name() + "\n" + respnse.getData().getMobile_phone()
                                    , "拨打", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //获取联系方式之后，需要刷新数据
                                            initData();
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

    //不感兴趣
    private void noInterested() {
        if (data != null) {
            int num = data.getUninterested_num();
            final BaseDialog baseDialog = new BaseDialog(this);
            baseDialog.showTwoButton("提示", "管家还可以为您提供" + num + "次匹配机会", "重新匹配", "取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseDialog.dismiss();
                    //完成之后 tvLeft 隐藏
                    showDialog();
                    addCall(new NetUtil().setUrl(APIS.URL_TALENT_UNINTERESTED_REQUIRE_YINYUAN)
                            .addParams("match_id", match_id + "")
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
                                        finish();
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

    private RecyclerViewAdapter adapter;
    private ArrayList<String> list;

    public void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerview.setAdapter(adapter);

        adapter.setEnableLoadMore(false);

        adapter.isUseEmpty(false);

        //事件监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ViewWrap.showPicActivity(YinyuanUseInfoActivity.this, list, position);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<String> data) {
            super(R.layout.item_recycler_add_pic, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.getView(R.id.iv_close).setVisibility(View.GONE);
            GlideUtil.show(YinyuanUseInfoActivity.this, item, (ImageView) helper.getView(R.id.iv_add_pic));
        }
    }
}
