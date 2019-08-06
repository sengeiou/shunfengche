package com.windmillsteward.jukutech.activity.newpage.yizhan;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.dialog.LoadingDialog;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * A simple {@link Fragment} subclass.
 * 投诉
 */
public class TouSuFragment extends BaseInitFragment {
    public static final String TAG = "TouSuFragment";

    @Bind(R.id.et_desc)
    EditText etDesc;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.tv_commit)
    TextView tvCommit;

    private RecyclerViewAdapter adapter;

    private List<String> listPic;
    private List<String> newPicUrls = new ArrayList<>();
    private LoadingDialog loadingDialog;
    private int relate_id;
    private int roleType;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tousu;
    }

    public static TouSuFragment newInstance(int roleType, int relate_id) {
        Bundle args = new Bundle();
        args.putInt("relate_id", relate_id);
        args.putInt("roleType", roleType);
        TouSuFragment fragment = new TouSuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("投诉");
        showContentView();
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            relate_id = bundle.getInt("relate_id");
            roleType = bundle.getInt("roleType");
        }
        initAdapter();
        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commit();
            }
        });
    }

    @Override
    protected void refreshPageData() {

    }

    public void initAdapter() {
        listPic = new ArrayList<>();
        listPic.add("");
        adapter = new RecyclerViewAdapter(listPic);
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerview.setAdapter(adapter);
        recyclerview.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        recyclerview.setHasFixedSize(true);

        adapter.setEnableLoadMore(false);

        //事件监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (TextUtils.isEmpty(listPic.get(position))) {
                    openXiangce();
                } else {
                    ArrayList<String> urls = new ArrayList<>();
                    for (String photoType : listPic) {
                        if (!StringUtil.isEmpty(photoType))
                            urls.add(photoType);
                    }
                    ViewWrap.showPicActivity(getActivity(), urls, position);
                }
            }
        });
    }

    private void commit() {
        //检查图片或者视频
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getActivity());//初始化对化框
        }

        if (TextUtils.isEmpty(etDesc.getText().toString())) {
            showTips("请输入投诉内容");
            return;
        }

        List<String> picList = new ArrayList<>();
        picList.addAll(listPic);

        for (String s : picList) {
            if (TextUtils.isEmpty(s)) {
                picList.remove(s);
            }
        }
        if (!picList.isEmpty()) {
            loadingDialog.showLoading("正在上传...");
            //上传图片
            addCall(new NetUtil().setPic_path(picList)
                    .buildUploadFile(new NetUtil.OnPicsUploadSuccessListener() {
                        @Override
                        public void onPicsUploadSuccess(List<String> pics) {
                            newPicUrls = pics;

                            finalCommit();
                        }

                        @Override
                        public void onPicsUploadFail(String msg) {
                            showTips(msg);
                            loadingDialog.dismiss();
                        }
                    }));
        }else{
            finalCommit();
        }
    }

    private void finalCommit() {
        loadingDialog.showLoading("正在提交...");

        StringBuilder pics = new StringBuilder();
        String picStr = "";
        for (String picUrl : newPicUrls) {
            pics.append(picUrl + ",");
        }
        if (!TextUtils.isEmpty(pics)) {
            picStr = pics.substring(0, pics.length() - 1);
        }
        if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG) {
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_COMPLAINT)
                    .addParams("relate_id", relate_id + "")
                    .addParams("content", etDesc.getText().toString())
                    .addParams("pic_urls", picStr)
                    .setCallBackData(new BaseNewNetModelimpl() {
                        @Override
                        protected void onFail(int type, String msg) {
                            loadingDialog.dismiss();
                            showTips(msg);
                            dismiss();
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                            dismiss();
                            loadingDialog.dismiss();
                            if (respnse != null && respnse.getCode() == 0) {
                                //投诉成功，返回上一页面并刷新
                                ((BackFragmentActivity) getHoldingActivity()).removeFragment();
                            }
                        }

                        @Override
                        protected Type getType() {
                            return new TypeToken<BaseResultInfo>() {
                            }.getType();
                        }
                    }).buildPost());
        } else if (roleType == PageConfig.TYPE_SMART) {
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_COMPLAINT_SMART)
                    .addParams("require_id", relate_id + "")
                    .addParams("content", etDesc.getText().toString())
                    .addParams("pic_urls", picStr)
                    .setCallBackData(new BaseNewNetModelimpl() {
                        @Override
                        protected void onFail(int type, String msg) {
                            loadingDialog.dismiss();
                            showTips(msg);
                            dismiss();
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                            dismiss();
                            loadingDialog.dismiss();
                            if (respnse != null && respnse.getCode() == 0) {
                                //投诉成功，返回上一页面并刷新
                                ((BackFragmentActivity) getHoldingActivity()).removeFragment();
                            }
                        }

                        @Override
                        protected Type getType() {
                            return new TypeToken<BaseResultInfo>() {
                            }.getType();
                        }
                    }).buildPost());
        } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_COMPLAINT_SMART)
                    .addParams("hour_matching_id", relate_id + "")
                    .addParams("content", etDesc.getText().toString())
                    .addParams("pic_urls", picStr)
                    .setCallBackData(new BaseNewNetModelimpl() {
                        @Override
                        protected void onFail(int type, String msg) {
                            loadingDialog.dismiss();
                            showTips(msg);
                            dismiss();
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                            dismiss();
                            loadingDialog.dismiss();
                            if (respnse != null && respnse.getCode() == 0) {
                                //投诉成功，返回上一页面并刷新
                                ((BackFragmentActivity) getHoldingActivity()).removeFragment();
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
                .maxSelectNum(6)
                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            listPic.clear();
            if (!selectList.isEmpty()) {
                for (int i = selectList.size() - 1; i >= 0; i--) {
                    LocalMedia localMedia = selectList.get(i);
                    String currImageUrl = localMedia.getPath();
                    listPic.add(0, currImageUrl);
                }
                if (listPic.size() < 6)
                    listPic.add("");
                adapter.notifyDataSetChanged();
            }

        }
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
                GlideUtil.show(getActivity(), new File(item), (ImageView) helper.getView(R.id.iv_add_pic));
            }
            helper.getView(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listPic.remove(helper.getAdapterPosition());
                    if (listPic.size() < 6&&!listPic.contains("")) {
                        listPic.add("");
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}
