package com.windmillsteward.jukutech.activity.home.family.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.baidu.location.BDLocation;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.video.VideoRecordingActivity;
import com.windmillsteward.jukutech.activity.map.SelectCityFromMapActivity;
import com.windmillsteward.jukutech.activity.newpage.common.popwindow.CommonPopwindow;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.common.SelectAreaActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.interfaces.OnUserAuthenListener;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.LoginSuccessInfo;
import com.windmillsteward.jukutech.bean.RequireClassBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.dialog.LoadingDialog;
import com.windmillsteward.jukutech.customview.dialog.PhoneCodeDialog;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.interfaces.MainLocationListener;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;
import com.windmillsteward.jukutech.utils.MathUtils;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.view.PickerViewWrap;
import com.windmillsteward.jukutech.utils.view.SpannableStringViewWrap;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class PublishRequireNewActivity extends BaseInitActivity {
    @Bind(R.id.tv_zhidaojia)
    TextView tvZhidaojia;
    @Bind(R.id.ed_jine)
    EditText edJine;
    @Bind(R.id.et_require_desc)
    EditText etRequireDesc;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.iv_video)
    CircleImageView ivVideo;
    @Bind(R.id.tv_fabu_address_tips)
    TextView tvFabuAddressTips;
    @Bind(R.id.tv_class)
    TextView tvClass;

    @Bind(R.id.iv_fabu_location)
    ImageView ivFabuLocation;
    @Bind(R.id.iv_fabu_right)
    ImageView ivFabuRight;
    @Bind(R.id.tv_renwu_address_tips)
    TextView tvRenwuAddressTips;
    @Bind(R.id.ed_renwu_address)
    TextView edRenwuAddress;
    @Bind(R.id.tv_lianxiren_tips)
    TextView tvLianxirenTips;
    @Bind(R.id.ed_lianxiren)
    EditText edLianxiren;
    @Bind(R.id.tv_phone_tips)
    TextView tvPhoneTips;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_change_phone)
    TextView tvChangePhone;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    @Bind(R.id.tv_info)
    TextView tv_info;
    public static final int REQUEST_CODE_MAKE_VIDEO = 104;
    @Bind(R.id.tv_fabu_address)
    TextView tvFabuAddress;
    @Bind(R.id.tv_zhongdian_address)
    TextView tvZhongdianAddress;
    @Bind(R.id.lay_ll_zd_address)
    LinearLayout layLlZdAddress;
    @Bind(R.id.lay_ll_root)
    LinearLayout lay_ll_root;
    @Bind(R.id.lay_ll_yellow)
    LinearLayout lay_ll_yellow;
    @Bind(R.id.lay_ll_qidian_address)
    LinearLayout lay_ll_qidian_address;
    @Bind(R.id.line_divider)
    View line_divider;
    private List<String> listPic;
    private List<String> listVedio;
    private String videoPath;
    private String video_cover_path;

    private String name;
    private int id;
    private String price;
    private String info_fee;
    private int addressType;//1任务2起点3终点

    //记录当前分类的数据
    private RequireClassBean currClass;

    private List<RequireClassBean> classBeanList;
    private String longlati;//任务地址,起点地址
    private String zdLonglati;//终点地址
    private int two_address;
    private int is_ad;
    private String second_area_name = KV.get(Define.CURR_CITY_NAME, "");
    private int isVideoOrPic = 2;//1视频2图片


    @Override
    protected int getContentViewId() {
        return R.layout.activity_publish_require_new;
    }

    @Override
    protected void initView(View view) {
        setMainTitle("下单发布");

        showContentView();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            name = extras.getString("name","");
            id = extras.getInt("id", -1);
            is_ad = extras.getInt("is_ad", -1);
            two_address = extras.getInt("two_address", 0);
            price = extras.getString("price");
            info_fee = extras.getString("info_fee");

            setMainTitle(name + "下单发布");

            showTwoAddress(two_address);

            tvClass.setText(name);
            tv_info.setText("信息费 ¥" + info_fee);
            if (is_ad == 0){
                tv_info.setVisibility(View.GONE);
            }else{
                tv_info.setVisibility(View.VISIBLE);
            }
            String account = Hawk.get("account", "");
            LoginSuccessInfo userInfo = (LoginSuccessInfo) KV.get(Define.LOGIN_SUCCESS);
            if (userInfo != null) {
                edLianxiren.setText(TextUtils.isEmpty(userInfo.getNickname()) ? "" : userInfo.getNickname());
            }
            tvPhone.setText(TextUtils.isEmpty(account) ? "" : account);
            String city_third_name = Hawk.get(Define.CURR_CITY_THIRD_NAME, "");
            tvFabuAddress.setText(city_third_name);
        }

        new SpannableStringViewWrap().addViews(tvFabuAddressTips, tvLianxirenTips, tvPhoneTips).build();

    }
    private void showTwoAddress(int two_address) {
        if (two_address == 0) {//只显示一个地址
            tvRenwuAddressTips.setText("任务地址");
            layLlZdAddress.setVisibility(View.GONE);
            edRenwuAddress.setHint("请选择任务地址");
            lay_ll_yellow.setVisibility(View.GONE);
            setMargins(lay_ll_qidian_address, 0);
            setMargins(layLlZdAddress, 0);
            setMargins(line_divider, 20);
        } else if (two_address == 1) {//显示两个地址
            tvRenwuAddressTips.setText("起点地址");
            layLlZdAddress.setVisibility(View.VISIBLE);
            edRenwuAddress.setHint("请选择起点地址");
            lay_ll_yellow.setVisibility(View.VISIBLE);
            setMargins(lay_ll_qidian_address, 20);
            setMargins(layLlZdAddress, 20);
            setMargins(line_divider, 40);
        }
    }

    private void setMargins(View ll, int dp) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(GraphicUtil.dp2px(this, dp), 0, 0, 0);
        ll.setLayoutParams(params);
    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        initAdapter();

        getClassData(false);
    }


    @Override
    protected void refreshPageData() {
        getClassData(false);
    }

    @OnClick({R.id.tv_zhongdian_address,R.id.tv_change_phone, R.id.tv_zhidaojia, R.id.iv_video, R.id.iv_fabu_location, R.id.ed_renwu_address, R.id.tv_submit, R.id.iv_fabu_right, R.id.tv_fabu_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_change_phone:
                new PhoneCodeDialog(this, new PhoneCodeDialog.DataCallBack() {
                    @Override
                    public void verifyCodeSuccess(String new_phone) {
                        if(!TextUtils.isEmpty(new_phone)){
                            showTips("更换成功");
                            tvPhone.setText(new_phone);
                        }
                    }
                }).builder().setCancelable(true).show();
                break;
            case R.id.tv_zhidaojia:
                showSelectClass();
                break;
            case R.id.iv_video:
                if (!chekcLocationPermission()) {
                    return;
                }
                isVideoOrPic = 1;
                selectCamera();
                break;
            case R.id.iv_fabu_location:
                getLocationOnce(new MainLocationListener() {
                    @Override
                    public void locationSuccess(String addr, String longilati, BDLocation location) {
                        tvFabuAddress.setText(addr);
                    }

                    @Override
                    public void locationFail(String msg) {
                        showTips(msg);
                    }
                });
                break;
            case R.id.ed_renwu_address:
                if (!chekcLocationPermission()) {
                    return;
                }
                addressType = 1;
                SelectCityFromMapActivity.go(this);
                return;
            case R.id.tv_submit:
                final BaseDialog baseDialog = new BaseDialog(this);
                baseDialog.showTwoButton("提示", "是否发布此消息", "确定", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        baseDialog.dismiss();
                        submit();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        baseDialog.dismiss();
                    }
                });
                break;
            case R.id.iv_fabu_right:
            case R.id.tv_fabu_address:
                SelectAreaActivity.go(this, second_area_id, third_area_id, second_area_name);
                break;
            case R.id.tv_zhongdian_address:
                if (!chekcLocationPermission()) {
                    return;
                }
                addressType = 2;
                SelectCityFromMapActivity.go(this);
                break;
        }
    }

    //预提交
    private void submit() {
        if (TextUtils.isEmpty(etRequireDesc.getText().toString().trim())) {
            showTips("请输入工作内容");
            return;
        }
//        if (listPic.size() <= 1 && TextUtils.isEmpty(videoPath)) {
//            showTips("请至少添加一个图片或者视频");
//            return;
//        }
        if (TextUtils.isEmpty(tvFabuAddress.getText().toString().trim())) {
            showTips("请选择发布地区");
            return;
        }
        if (second_area_id == -1 || third_area_id == -1) {
            showTips("请选择发布地区");
            return;
        }
        if (TextUtils.isEmpty(edRenwuAddress.getText().toString().trim())) {
            showTips("请选择任务地址");
            return;
        }
        if (two_address == 0) {
            if (TextUtils.isEmpty(longlati) || longlati.equals("0")) {
                showTips("请重新选择任务地址");
                return;
            }
        } else if (two_address == 1) {
            if (TextUtils.isEmpty(longlati) || longlati.equals("0")) {
                showTips("请重新选择起点地址");
                return;
            }
            if (TextUtils.isEmpty(zdLonglati) || zdLonglati.equals("0")) {
                showTips("请重新选择终点地址");
                return;
            }
        }

        if (TextUtils.isEmpty(edLianxiren.getText().toString().trim())) {
            showTips("请输入联系人");
            return;
        }
        if (TextUtils.isEmpty(tvPhone.getText().toString().trim())) {
            showTips("请输入电话");
            return;
        }
        if (!chekcLocationPermission()) {
            return;
        }
        //可以提交
        checkUserAuthen(new OnUserAuthenListener() {
            @Override
            public void isAuthen() {
                commit();
            }

            @Override
            public void isNotAuthen() {

            }

        });
    }

    private List<String> newPicUrls = new ArrayList<>();
    private List<String> newVideoUrls = new ArrayList<>();
    private int second_area_id = KV.get(Define.CURR_CITY_ID, -1);
    private int third_area_id = KV.get(Define.CURR_CITY_THIRD_ID, -1);

    private LoadingDialog loadingDialog;

    //提交
    private void commit() {
        //检查图片或者视频
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);//初始化对化框
        }
        loadingDialog.showLoading("");

        List<String> picList = new ArrayList<>();
        picList.addAll(listPic);

        for (String s : picList) {
            if (TextUtils.isEmpty(s)) {
                picList.remove(s);
            }
        }
        if (!picList.isEmpty()) {
            //先上传图片，再上传视频
            addCall(new NetUtil().setPic_path(picList)
                    .buildUploadFile(new NetUtil.OnPicsUploadSuccessListener() {
                        @Override
                        public void onPicsUploadSuccess(List<String> pics) {
                            newPicUrls = pics;
                            if (!TextUtils.isEmpty(videoPath)) {//
                                List<String> videos = new ArrayList<>();
                                videos.add(videoPath);
                                addCall(new NetUtil().setPic_path(videos)
                                        .buildUploadFile(new NetUtil.OnPicsUploadSuccessListener() {
                                            @Override
                                            public void onPicsUploadSuccess(List<String> pics) {
                                                newVideoUrls = pics;
                                                finalCommit();
                                            }

                                            @Override
                                            public void onPicsUploadFail(String msg) {
                                                showTips(msg);
                                                loadingDialog.dismiss();
                                            }
                                        }));
                            } else {
                                finalCommit();
                            }
                        }

                        @Override
                        public void onPicsUploadFail(String msg) {
                            showTips(msg);
                            loadingDialog.dismiss();
                        }
                    }));
        }else if (!TextUtils.isEmpty(videoPath)){//如果只上传了视频
                List<String> videos = new ArrayList<>();
                videos.add(videoPath);
                addCall(new NetUtil().setPic_path(videos)
                        .buildUploadFile(new NetUtil.OnPicsUploadSuccessListener() {
                            @Override
                            public void onPicsUploadSuccess(List<String> pics) {
                                newVideoUrls = pics;
                                finalCommit();
                            }

                            @Override
                            public void onPicsUploadFail(String msg) {
                                showTips(msg);
                                loadingDialog.dismiss();
                            }
                        }));

        }else {
            finalCommit();
        }
    }

    /**
     * 最后的提交
     */
    private void finalCommit() {
//        second_area_id = KV.get(Define.CURR_CITY_ID, -1);
        StringBuilder pics = new StringBuilder();
        String picStr = "";
        String videoStr = "";
        for (String picUrl : newPicUrls) {
            pics.append(picUrl + ",");
        }
        if (!TextUtils.isEmpty(pics)) {
            picStr = pics.substring(0, pics.length() - 1);
        }
        if (newVideoUrls != null && !newVideoUrls.isEmpty()) {
            videoStr = newVideoUrls.get(0);
        }
        String longitude1 = "";
        String latitude1 = "";
        String longitude2 = "";
        String latitude2 = "";

        if (!StringUtil.isEmpty(longlati)) {
            longitude1 = longlati.split(",")[0];
            latitude1 = longlati.split(",")[1];
        }
        if (!StringUtil.isEmpty(zdLonglati)) {
            longitude2 = zdLonglati.split(",")[0];
            latitude2 = zdLonglati.split(",")[1];
        }

        addCall(new NetUtil().setUrl(APIS.URL_TALENT_ADD_REQUIRE_FW)
                .addParams("require_class_id", currClass.getRequire_class_id() + "")
                .addParams("price", edJine.getText().toString().trim())
                .addParams("description", etRequireDesc.getText().toString().trim())
                .addParams("second_area_id", second_area_id + "")
                .addParams("third_area_id", third_area_id + "")
                .addParams("longitude", longitude1)
                .addParams("latitude", latitude1)
                .addParams("pic_urls", picStr)
                .addParams("video_url", videoStr)
                .addParams("contact_person", edLianxiren.getText().toString().trim())
                .addParams("contact_tel", tvPhone.getText().toString().trim())
                .addParams("address", edRenwuAddress.getText().toString().trim())
                .addParams("t_address", tvZhongdianAddress.getText().toString().trim())
                .addParams("t_longitude", longitude2)
                .addParams("t_latitude", latitude2)
                .setCallBackData(new BaseNewNetModelimpl<PublicResultModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        loadingDialog.dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<PublicResultModel> respnse, String source) {
                        loadingDialog.dismiss();
                        //发布成功，回到上一个页面
                        if (is_ad == 0) {//不是广告发布，可以直接发单，不需要支付
                            showTips("尊敬的管家用户：您好，欢迎您使用顺风车管家，您的智慧生活订单已经发布成功，请您耐心等待抢单。");
                            setResult(NewPayActivity.RESULT_CODE_SUCCESS);
                            finish();
                        }else{//广告发布，要支付，跳去收银台
                            PublicResultModel data = respnse.getData();
                            if (data != null){
                                NewPayActivity.go(PublishRequireNewActivity.this, CommonPayPresenter.TYPE_SMART_PUBLIC_AD,
                                        data.getRelate_id(),data.getOrder_price()+"",data.getOrder_name(),0);
                            }
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

    /**
     * 获取区列表
     *
     * @param showPop
     */
    private List<ThirdAreaBean> areaList;

    private void getAreaData(final boolean showPop) {
        addCall(new NetUtil().setUrl(APIS.URL_THIRD_AREA_LIST)
                .addParams("second_area_id", getCurrCityId() + "")
                .setCallBackData(new BaseNewNetModelimpl<List<ThirdAreaBean>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<ThirdAreaBean>> respnse, String source) {
                        if (respnse.getData() != null) {
                            areaList = respnse.getData();
                            if (showPop) {
                                onAreaPicker();
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    /**
     * 选择区
     */
    public void onAreaPicker() {
        if (areaList != null) {
            new PickerViewWrap<ThirdAreaBean>().showCommonSelectPicker(this, -1, new cn.qqtheme.framework.picker.SinglePicker.OnItemPickListener<ThirdAreaBean>() {
                @Override
                public void onItemPicked(int i, ThirdAreaBean areaBean) {
                    tvFabuAddress.setText(areaBean.getThird_area_name());
                    third_area_id = areaBean.getThird_area_id();
                }
            }, areaList);
        } else {
            getAreaData(true);
        }
    }

    private RecyclerViewAdapter adapter;

    public void initAdapter() {
        listVedio = new ArrayList<>();
        listPic = new ArrayList<>();
        listPic.add("");
        adapter = new RecyclerViewAdapter(listPic);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerview.setAdapter(adapter);
        recyclerview.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        recyclerview.setHasFixedSize(true);

        adapter.setEnableLoadMore(false);

        //事件监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                isVideoOrPic = 2;
                if (TextUtils.isEmpty(listPic.get(position))) {
                    openXiangce();
                } else {
                    ArrayList<String> urls = new ArrayList<>();
                    for (String photoType : listPic) {
                        if (!StringUtil.isEmpty(photoType))
                            urls.add(photoType);
                    }
                    ViewWrap.showPicActivity(PublishRequireNewActivity.this, urls, position);
                }
            }
        });
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<String> data) {
            super(R.layout.item_recycler_add_pic, data, false);
        }

        @Override
        protected void convert(final BaseViewHolder helper, String item) {
            if (TextUtils.isEmpty(item)) {
                //加号
                helper.setImageResource(R.id.iv_add_pic, R.mipmap.add_pic_icon);
                helper.getView(R.id.iv_close).setVisibility(View.GONE);
            } else {
                helper.getView(R.id.iv_close).setVisibility(View.VISIBLE);
                GlideUtil.show(PublishRequireNewActivity.this, new File(item), (ImageView) helper.getView(R.id.iv_add_pic));
            }
            helper.getView(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listPic.remove(helper.getAdapterPosition());
                    if (listPic.size() < 6 && !listPic.contains("")) {
                        listPic.add("");
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    /**
     * 打开相册
     */
    public void openXiangce() {
        List<LocalMedia> selectList = new ArrayList<>();
        for (String s : listPic) {
            if (!StringUtil.isEmpty(s)) {
                LocalMedia localMedia = new LocalMedia();
                localMedia.setChecked(true);
                localMedia.setMimeType(PictureMimeType.ofImage());
                localMedia.setPath(s);
                selectList.add(localMedia);
            }
        }
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
                .maxSelectNum(9)
                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }


        /**
         * 打开相册选择视频
         */
        public void selectCamera() {
            List<LocalMedia> selectVedioList = new ArrayList<>();
            for (String s : listVedio) {
                if (!StringUtil.isEmpty(s)) {
                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setChecked(true);
                    localMedia.setMimeType(PictureMimeType.ofVideo());
                    localMedia.setPath(s);
                    selectVedioList.add(localMedia);
                }
            }
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofVideo())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                    .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                    .previewImage(true)// 是否可预览图片 true or false
                    .isCamera(true)// 是否显示拍照按钮 true or false
                    .imageSpanCount(3)// 每行显示个数 int
                    .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    .isGif(false)// 是否显示gif图片 true or false
                    .enableCrop(false)
                    .videoMaxSecond(60)
                    .withAspectRatio(1, 1)
                    .maxSelectNum(1)
                    .selectionMedia(selectVedioList)
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NewPayActivity.REQUEST_CODE && resultCode == NewPayActivity.RESULT_CODE_SUCCESS) {
            //处理你的操作
            setResult(NewPayActivity.RESULT_CODE_SUCCESS);
            finish();
        }
        if (resultCode == RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST) {
            if (isVideoOrPic == 1) {
                if (data != null && data.getExtras() != null) {
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    listVedio.clear();
                    if (selectList != null) {
                        if (selectList.size() > 0) {
                            videoPath = selectList.get(0).getPath();
                        }
                    }
                ivVideo.setImageBitmap(ViewWrap.getVideoThumbnail(PublishRequireNewActivity.this, videoPath, 800, 800, MediaStore.Images.Thumbnails.MICRO_KIND));
            }
            } else if (isVideoOrPic == 2) {
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                listPic.clear();
                if (!selectList.isEmpty()) {
                    for (int i = selectList.size() - 1; i >= 0; i--) {
                        LocalMedia localMedia = selectList.get(i);
                        String currImageUrl = localMedia.getPath();
                        listPic.add(0, currImageUrl);
                    }
                    if (listPic.size() < 9)
                        listPic.add("");
                    adapter.notifyDataSetChanged();
                }
            }
        }  else if (requestCode == SelectCityFromMapActivity.GET_CITY_REQUEST_CODE) {
            //地址
            if (data != null) {
                double lat = data.getDoubleExtra("lat", 0);
                double lng = data.getDoubleExtra("lng", 0);
                String address = data.getStringExtra("address");

                if (addressType == 1) {
                    longlati = lng + "," + lat;
                    edRenwuAddress.setText(address);
                } else if (addressType == 2) {
                    zdLonglati = lng + "," + lat;
                    tvZhongdianAddress.setText(address);
                }
            }
        } else if (requestCode == SelectAreaActivity.GET_CITY_REQUEST_CODE) {
            //地址
            if (data != null) {
                third_area_id = data.getIntExtra("thirdId", 0);
                String thirdName = data.getStringExtra("thirdName");
                second_area_name = data.getStringExtra("secondName");
                second_area_id = data.getIntExtra("secondId", -1);
                tvFabuAddress.setText(second_area_name + thirdName);
            }
        }

    }

    /**
     * 获取分类信息
     */
    private void getClassData(final boolean showPop) {
        addCall(new NetUtil().setUrl(APIS.URL_REQUIRE_CLASS_LIST)
                .setCallBackData(new BaseNewNetModelimpl<List<RequireClassBean>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<RequireClassBean>> respnse, String source) {
                        if (respnse.getData() != null) {
                            List<RequireClassBean> data = respnse.getData();
                            classBeanList = data;
                            if (classBeanList != null && classBeanList.size() > 0) {
                                classBeanList.get(0).setSelect(true);
                            }
                            for (RequireClassBean datum : data) {
                                if (datum.getRequire_class_id() == id) {
                                    //找到了数据
                                    currClass = datum;

                                    setClassData("");
                                }
                            }
                            if (showPop) {
                                showSelectClass();
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<RequireClassBean>>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    /**
     * 设置分类信息
     *
     * @param s
     */
    private void setClassData(CharSequence s) {
        if (currClass != null) {
            //设置标题
            setMainTitle(currClass.getClass_name() + "下单发布");
            //设置分类名
            tvClass.setText(currClass.getClass_name());
            //设置时长指导价格
            tvZhidaojia.setText("市场指导价为" + currClass.getPrice() + "元");
            //设置信息费啥的先不管
            String money = edJine.getText().toString().trim();
            String add = "";
            if (TextUtils.isEmpty(money)) {
                add = MathUtils.add(currClass.getPrice(), currClass.getInfo_fee(), 2);
            } else {
                add = MathUtils.add(money, currClass.getInfo_fee(), 2);
            }
            is_ad = currClass.getIs_ad();
            if (is_ad == 0){
                tv_info.setVisibility(View.GONE);
            }else{
                tv_info.setVisibility(View.VISIBLE);
            }
            tv_info.setText("信息费 ¥" + currClass.getInfo_fee());
        }
    }

    /**
     * 选择类型
     */
    private void showSelectClass() {
        if (classBeanList == null) {
            getClassData(true);
        } else {
            if (lay_ll_root != null) {
                CommonPopwindow commonPopwindow = new CommonPopwindow(this, lay_ll_root, classBeanList);
                final EasyPopup circlePop = commonPopwindow.getCirclePop();
                LimitHeightListView listView = commonPopwindow.getListView();
                commonPopwindow.bindAdapter();
                commonPopwindow.setTitle("选择分类");
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        RequireClassBean model = (RequireClassBean) parent.getItemAtPosition(position);
                        if (circlePop != null) {
                            circlePop.dismiss();
                        }
                        for (RequireClassBean bean : classBeanList) {
                            bean.setSelect(false);
                        }
                        if (model != null) {
                            model.setSelect(true);
                            currClass = model;
                            info_fee = currClass.getInfo_fee();
                            two_address = currClass.getTwo_address();
                            price = currClass.getPrice();
                            setClassData("");
                            showTwoAddress(two_address);
                        }
                    }
                });
                if (circlePop != null) {
                    circlePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    circlePop.showAtAnchorView(lay_ll_root, VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
                }
            }
        }
    }

}
