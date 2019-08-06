package com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.model.CommonBannerModel;
import com.windmillsteward.jukutech.activity.newpage.common.model.PayInfoFeeModel;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonBannerPresenter;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.AuthenticationModel;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.ResUtils;
import com.windmillsteward.jukutech.utils.view.SpannableStringViewWrap;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * 资产认证
 */
public class ZichanRenzhenFragment extends BaseInitFragment {
    public static final String TAG = "ZichanRenzhenFragment";
    @Bind(R.id.flyBanner)
    FlyBanner flyBanner;
    @Bind(R.id.recyclerViewFc)
    RecyclerView recyclerViewFc;
    @Bind(R.id.recyclerViewXc)
    RecyclerView recyclerViewXc;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    @Bind(R.id.tv_reason)
    TextView tv_reason;
    @Bind(R.id.tv_info)
    TextView tv_info;
    @Bind(R.id.ll_bottom)
    LinearLayout ll_bottom;
    @Bind(R.id.tv_fangchan)
    TextView tv_fangchan;
    @Bind(R.id.tv_xingshi)
    TextView tv_xingshi;

    private int currPicType;
    public boolean isHiddeTitle;

    private PayInfoFeeModel currModel;

    private CommonBannerPresenter bannerPresenter;
    private CommonPayPresenter payPresenter;

    public static ZichanRenzhenFragment newInstance(boolean isHiddeTitle) {
        Bundle args = new Bundle();
        args.putBoolean("isHiddeTitle", isHiddeTitle);
        ZichanRenzhenFragment fragment = new ZichanRenzhenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_zichan_renzhen;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("资产认证");

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });

        new SpannableStringViewWrap().addViews(tv_fangchan, tv_xingshi).build();

        if (getArguments() != null) {
            isHiddeTitle = getArguments().getBoolean("isHiddeTitle",false);
        }

        if (isHiddeTitle){
            hidTitleView();
        }
    }

    @Override
    protected void initData() {
        bannerPresenter = new CommonBannerPresenter(getActivity());
        payPresenter = new CommonPayPresenter(getActivity());

        initZichanAdapter();
        initXingcheAdapter();

        getInfo();
//        getBannerInfo();
        getPayFeeInfo();
    }

    /**
     * 获取banner
     */
    private void getBannerInfo() {
        //获取banner信息
        bannerPresenter.loadBannerData(CommonBannerPresenter.TYPE_YINYUANFUWU, new CommonBannerPresenter.DataCallBack() {
            @Override
            public void onFail(int type, String msg) {
                showTips(msg);
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
                    ViewWrap.setUpFlyBanner(getActivity(), pics, urls, flyBanner);
                }
            }
        });
    }

    /**
     * 获取支付信息
     */
    private void getPayFeeInfo() {
        payPresenter.loadPayInfoFeeData(CommonPayPresenter.FEE_TYPE_GERENZHICHAN, new CommonPayPresenter.DataCallBack<PayInfoFeeModel>() {
            @Override
            public void onFail(int type, String msg) {
                showTips(msg);
                showErrorView();
            }

            @Override
            public void onSucess(int code, BaseResultInfo<PayInfoFeeModel> respnse, String source) {
                showContentView();
                if (respnse.getData() != null) {
                    currModel = respnse.getData();
                    tv_info.setText(new SpanUtils().append("信息费: ")
                            .append("¥" + currModel.getCharge_fee() + "元")
                            .setForegroundColor(ResUtils.getCommRed())
                            .create());
                }
            }
        });
    }

    private BaseDialog baseDialog;
    private AuthenticationModel authenticationModel = null;

    //获取信息
    private void getInfo() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_ASSET_AUTHENTICATION)
                .setCallBackData(new BaseNewNetModelimpl<AuthenticationModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<AuthenticationModel> respnse, String source) {
                        dismiss();
                        showContentView();

                        if (respnse.getData() != null) {
                            authenticationModel = respnse.getData();
                            //资产认证状态 0.未上传资料,1已上传待审核，2审核通过，3审核不通过
                            //资产认证状态 0.未上传资料,1已上传待付款 ，2已付款待审核，3审核通过，4审核不通过
                            if (authenticationModel.getAudit_assets_status() == 0 || authenticationModel.getAudit_assets_status() == 1) {
                                tv_reason.setVisibility(View.GONE);
                                ll_bottom.setVisibility(View.VISIBLE);
                            } else if (authenticationModel.getAudit_assets_status() == 2) {
                                tv_reason.setVisibility(View.VISIBLE);
                                tv_reason.setText("");
                                tv_reason.setText(new SpanUtils()
                                        .append("审核中，请等待客服审核").setForegroundColor(ResUtils.getCommRed())
                                        .create());
                                ll_bottom.setVisibility(View.GONE);
                            } else if (authenticationModel.getAudit_assets_status() == 3) {
                                tv_reason.setVisibility(View.VISIBLE);
                                ll_bottom.setVisibility(View.VISIBLE);
                                tv_reason.setText("审核已通过，您可以继续进行认证");//车辆和房产
                            } else if (authenticationModel.getAudit_assets_status() == 4) {
                                tv_reason.setVisibility(View.VISIBLE);
                                ll_bottom.setVisibility(View.VISIBLE);
                                tv_reason.setText(new SpanUtils().append("审核不通过，点击")
                                        .append("查看原因").setForegroundColor(ResUtils.getCommThem())
                                        .create());
                                tv_reason.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (baseDialog == null) {
                                            baseDialog = new BaseDialog(getActivity());
                                        }
                                        baseDialog.showTwoButton("审核不通过", authenticationModel.getAssets_audit_reason(), "确定", "取消", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                baseDialog.dismiss();
                                            }
                                        }, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                baseDialog.dismiss();
                                            }
                                        });
                                    }
                                });
                            } else if (authenticationModel.getAudit_assets_status() == 5) {//房产证通过
                                tv_reason.setVisibility(View.VISIBLE);
                                ll_bottom.setVisibility(View.VISIBLE);
                                tv_reason.setText("房产审核已通过,您可以继续进行车辆认证");
                            } else if (authenticationModel.getAudit_assets_status() == 6) {//行驶证通过
                                tv_reason.setVisibility(View.VISIBLE);
                                ll_bottom.setVisibility(View.VISIBLE);
                                tv_reason.setText("车辆审核已通过，您可以继续进行房产认证");
                            }

                            if (authenticationModel.getAudit_assets_status() > 0) {
                                //回显图片
                                setBackPics(authenticationModel);
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<AuthenticationModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    private int currStep = 0;
    private List<String> listZichanBack;
    private List<String> listXingcheBack;

    //提交认证
    private void upload() {
        //检查图片
//        if (listZichan.size() < 1) {
//            showTips("请选择一张房产证图片");
//            return;
//        }
//        if (listXingche.size() < 1) {
//            showTips("请选择一张行驶证图片");
//            return;
//        }
        List<String> uploads1 = new ArrayList<>();
        for (PhotoType s : listZichan) {
            if (s != null) {
                uploads1.add(s.getUrl());
            }
        }
        List<String> uploads2 = new ArrayList<>();
        for (PhotoType s : listXingche) {
            if (s != null) {
                uploads2.add(s.getUrl());
            }
        }
        if (uploads1.isEmpty() && uploads2.isEmpty()) {
            showTips("请至少进行一个认证");
        }


//        if (uploads1.isEmpty()) {
//            showTips("请选择一张房产证图片");
//            return;
//        } else if (uploads2.isEmpty()) {
//            showTips("请选择一张行驶证图片");
//            return;
//        }
        showDialog("正在上传图片...");
        int times = 0;

        if (!uploads1.isEmpty() && !uploads2.isEmpty()) {
            if (!uploads1.get(0).startsWith("http") && !uploads2.get(0).startsWith("http")) {//两张都有，两张都未上传
                //上传两张图片
                times = 2;
            } else if (uploads1.get(0).startsWith("http") && uploads2.get(0).startsWith("http")) {//两张都有，两张都已上传
                commit();
            } else if (!uploads1.get(0).startsWith("http") || !uploads2.get(0).startsWith("http")) {     //两张都有，一张已上传，一张未上传
                times = 1;
            }
        } else if (!uploads1.isEmpty() && uploads2.isEmpty()) {//行驶证有图，房产没图
            if (uploads1.get(0).startsWith("http")) {//已上传
                commit();
            } else {//未上传
                times = 1;
            }
        } else if (!uploads2.isEmpty() && uploads1.isEmpty()) {//房产有图，行驶证没图
            if (uploads2.get(0).startsWith("http")) {//已上传
                commit();
            } else {//未上传
                times = 1;
            }
        }

        if (!uploads1.isEmpty()) {
            if (!uploads1.get(0).startsWith("http")) {
                final int finalTimes = times;
                addCall(new NetUtil().setPic_path(uploads1)
                        .buildUploadFile(new NetUtil.OnPicsUploadSuccessListener() {
                            @Override
                            public void onPicsUploadSuccess(List<String> pics) {
                                currStep++;
                                listZichanBack = pics;
                                if (currStep >= finalTimes) {
                                    //进行下一步
                                    commit();
                                }
                            }

                            @Override
                            public void onPicsUploadFail(String msg) {
                                showTips(msg);
                                dismiss();
                            }
                        })
                );
            }
        }
        if (!uploads2.isEmpty()) {
            if (!uploads2.get(0).startsWith("http")) {
                final int finalTimes1 = times;
                addCall(new NetUtil().setPic_path(uploads2)
                        .buildUploadFile(new NetUtil.OnPicsUploadSuccessListener() {
                            @Override
                            public void onPicsUploadSuccess(List<String> pics) {
                                currStep++;
                                listXingcheBack = pics;
                                if (currStep >= finalTimes1) {
                                    //进行下一步
                                    commit();
                                }
                            }

                            @Override
                            public void onPicsUploadFail(String msg) {
                                showTips(msg);
                                dismiss();
                            }
                        })
                );
            }
        }
    }

    //提交
    private void commit() {
        if (authenticationModel == null)
            return;
        String deed_url = "";
        String driver_license_url = "";

        if (listXingcheBack != null && !listXingcheBack.isEmpty()) {
            driver_license_url = listXingcheBack.get(0);
        } else {
            if (listXingche != null && !listXingche.isEmpty())
                if (listXingche.get(0) != null) {
                    driver_license_url = listXingche.get(0).getUrl();
                }

        }

        if (listZichanBack != null && !listZichanBack.isEmpty()) {
            deed_url = listZichanBack.get(0);
        } else {
            if (listZichan != null && !listZichan.isEmpty()) {
                if (listZichan.get(0) != null) {
                    deed_url = listZichan.get(0).getUrl();
                }
            }
        }
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_SUBMISSION_ASSET_AUTHENTICATION)
                .addParams("deed_url", deed_url)
                .addParams("driver_license_url", driver_license_url)
                .setCallBackData(new BaseNewNetModelimpl<PublicResultModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<PublicResultModel> respnse, String source) {
                        dismiss();
                        showTips("图片上传成功");
                        //去支付
                        if (respnse.getData() != null) {
                            NewPayActivity.go(ZichanRenzhenFragment.this,
                                    CommonPayPresenter.TYPE_YINYUAN_ZICHAN, respnse.getData().getRelate_id(), respnse.getData().getOrder_price() + "", respnse.getData().getOrder_name(),0);
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

    //将图片回显
    private void setBackPics(AuthenticationModel authenticationModel) {
        listZichan.clear();
        String deed_url = authenticationModel.getDeed_url();

        if (TextUtils.isEmpty(deed_url)) {
            listZichan.add(null);
        } else {
            listZichan.add(new PhotoType(deed_url, PhotoType.TYPE_FANGCHAN));
        }

        adapterZichan.notifyDataSetChanged();
        String driver_license_url = authenticationModel.getDriver_license_url();
        listXingche.clear();
        if (TextUtils.isEmpty(driver_license_url)) {
            listXingche.add(null);
        } else {
            listXingche.add(new PhotoType(driver_license_url, PhotoType.TYPE_XINGCHE));
        }

        adapterXingche.notifyDataSetChanged();
    }

    @Override
    protected void refreshPageData() {
        getInfo();
    }

    private RecyclerViewAdapter adapterZichan;
    private List<PhotoType> listZichan;
    private RecyclerViewAdapter adapterXingche;
    private List<PhotoType> listXingche;

    public void initZichanAdapter() {
        listZichan = new ArrayList<>();
        listZichan.add(null);
        adapterZichan = new RecyclerViewAdapter(listZichan);
        adapterZichan.setEnableLoadMore(false);
        recyclerViewFc.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recyclerViewFc.setAdapter(adapterZichan);
        recyclerViewFc.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        recyclerViewFc.setHasFixedSize(true);

        //事件监听
        adapterZichan.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (authenticationModel == null)
                    return;
                if (listZichan.get(position) == null) {
                    currPicType = 1;
                    openXiangce();
                } else {
                    if (authenticationModel.getAudit_assets_status() != 2) {
                        currPicType = 1;
                        openXiangce();
                    } else {
                        ArrayList<String> urls = new ArrayList<>();
                        for (PhotoType photoType : listZichan) {
                            if (photoType != null)
                                urls.add(photoType.getUrl());
                        }
                        ViewWrap.showPicActivity(getActivity(), urls, position);
                    }
                }
            }
        });
    }

    public void initXingcheAdapter() {
        listXingche = new ArrayList<>();
        listXingche.add(null);
        adapterXingche = new RecyclerViewAdapter(listXingche);
        adapterXingche.setEnableLoadMore(false);
        recyclerViewXc.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recyclerViewXc.setAdapter(adapterXingche);
        recyclerViewXc.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        recyclerViewXc.setHasFixedSize(true);

        //事件监听
        adapterXingche.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (authenticationModel == null)
                    return;
                if (listXingche.get(position) == null) {
                    currPicType = 0;
                    openXiangce();
                } else {
                    if (authenticationModel.getAudit_assets_status() != 2) {
                        currPicType = 0;
                        openXiangce();
                    } else {
                        ArrayList<String> urls = new ArrayList<>();
                        for (PhotoType photoType : listXingche) {
                            if (photoType != null) {
                                urls.add(photoType.getUrl());
                            }
                        }
                        ViewWrap.showPicActivity(getActivity(), urls, position);
                    }
                }
            }
        });
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<PhotoType, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<PhotoType> data) {
            super(R.layout.item_recycler_add_pic, data, false);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final PhotoType item) {
            if (item == null) {
                //加号
                helper.setImageResource(R.id.iv_add_pic, R.mipmap.add_pic_icon);
                helper.getView(R.id.iv_close).setVisibility(View.GONE);
            } else {
                helper.getView(R.id.iv_close).setVisibility(View.VISIBLE);
                if (item.getUrl().startsWith("http")) {
                    GlideUtil.show(getActivity(), item.getUrl(), (ImageView) helper.getView(R.id.iv_add_pic));
                    helper.getView(R.id.iv_close).setVisibility(View.VISIBLE);
                } else {
                    GlideUtil.show(getActivity(), new File(item.getUrl()), (ImageView) helper.getView(R.id.iv_add_pic));
                }
            }
            if (authenticationModel == null)
                return;
            //资产认证状态 0.未上传资料,1已上传待付款 ，2已付款待审核，3审核全通过，4审核全不通过，5房产证通过 6行驶证通过
            if (authenticationModel.getAudit_assets_status() != 2) {
                helper.getView(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.getType() == PhotoType.TYPE_FANGCHAN) {
                            //房产
                            listZichan.remove(helper.getPosition());
                            listZichan.add(null);
                            adapterZichan.notifyDataSetChanged();
                        } else {
                            //行车
                            listXingche.remove(helper.getPosition());
                            listXingche.add(null);
                            adapterXingche.notifyDataSetChanged();
                        }
                    }
                });
            }
        }
    }


    /**
     * 打开相册
     */
    public void openXiangce() {
        List<LocalMedia> selectList = new ArrayList<>();
//        if (currPicType == 0) {
//            //行车证件
//            for (PhotoType s : listXingche) {
//                if (s != null) {
//                    LocalMedia localMedia = new LocalMedia();
//                    localMedia.setPath(s.getUrl());
//                    localMedia.setChecked(true);
//                    localMedia.setMimeType(PictureMimeType.ofImage());
//                    selectList.add(localMedia);
//                }
//            }
//        } else {
//            //房产
//            for (PhotoType s : listZichan) {
//                if (s != null) {
//                    LocalMedia localMedia = new LocalMedia();
//                    localMedia.setChecked(true);
//                    localMedia.setMimeType(PictureMimeType.ofImage());
//                    localMedia.setPath(s.getUrl());
//                    selectList.add(localMedia);
//                }
//            }
//        }
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageSpanCount(3)// 每行显示个数 int
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .isGif(false)// 是否显示gif图片 true or false
                .enableCrop(false)
                .withAspectRatio(1, 1)
                .maxSelectNum(1)
                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NewPayActivity.REQUEST_CODE && resultCode == NewPayActivity.RESULT_CODE_SUCCESS) {
            //处理你的操作
            getActivity().finish();
        }
        if (resultCode == RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            if (currPicType == 0) {
                //行车证件
                listXingche.clear();
            } else {
                //房产
                listZichan.clear();
            }

            if (!selectList.isEmpty()) {
                for (int i = selectList.size() - 1; i >= 0; i--) {
                    LocalMedia localMedia = selectList.get(i);
                    String currImageUrl = localMedia.getPath();
                    if (currPicType == 0) {
                        //行车证件
                        listXingche.add(0, new PhotoType(currImageUrl, PhotoType.TYPE_XINGCHE));
                    } else {
                        //房产
                        listZichan.add(0, new PhotoType(currImageUrl, PhotoType.TYPE_FANGCHAN));
                    }
                }
                if (currPicType == 0) {
                    //行车证件
//                    listXingche.add(null);
                    adapterXingche.notifyDataSetChanged();
                } else {
                    //房产
//                    listZichan.add(null);
                    adapterZichan.notifyDataSetChanged();
                }
            }
        }
    }

    class PhotoType {
        public static final int TYPE_XINGCHE = 1;
        public static final int TYPE_FANGCHAN = 2;
        private String url;
        private int type;

        public PhotoType() {

        }

        public PhotoType(String url, int type) {
            this.url = url;
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
