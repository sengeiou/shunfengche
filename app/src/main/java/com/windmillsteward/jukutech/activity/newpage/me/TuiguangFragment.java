package com.windmillsteward.jukutech.activity.newpage.me;


import android.app.Dialog;
import android.app.Fragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.activity.ModuleInstructionActivity;
import com.windmillsteward.jukutech.activity.mine.activity.TuiGuangWalletListActivity;
import com.windmillsteward.jukutech.activity.newpage.model.TuiguangModel;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.CircleProgressBar;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.Util;

import java.lang.reflect.Type;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuiguangFragment extends BaseInitFragment {
    public static final String TAG = "TuiguangFragment";
    @Bind(R.id.tv_guize)
    TextView tvGuize;
    @Bind(R.id.tv_num_01)
    TextView tvNum01;
    @Bind(R.id.rela_cntainer01)
    RelativeLayout relaCntainer01;
    @Bind(R.id.view_line_01)
    View viewLine01;
    @Bind(R.id.tv_num_02)
    TextView tvNum02;
    @Bind(R.id.rela_cntainer02)
    RelativeLayout relaCntainer02;
    @Bind(R.id.view_line_02)
    View viewLine02;
    @Bind(R.id.tv_num_03)
    TextView tvNum03;
    @Bind(R.id.rela_cntainer03)
    RelativeLayout relaCntainer03;
    @Bind(R.id.view_line_03)
    View viewLine03;
    @Bind(R.id.tv_num_04)
    TextView tvNum04;
    @Bind(R.id.rela_cntainer04)
    RelativeLayout relaCntainer04;
    @Bind(R.id.tv_text01)
    TextView tvText01;
    @Bind(R.id.tv_text02)
    TextView tvText02;
    @Bind(R.id.tv_text03)
    TextView tvText03;
    @Bind(R.id.tv_text04)
    TextView tvText04;
    @Bind(R.id.iv_tequan01)
    ImageView ivTequan01;
    @Bind(R.id.tv_tequan01)
    TextView tvTequan01;
    @Bind(R.id.iv_tequan02)
    ImageView ivTequan02;
    @Bind(R.id.tv_tequan02)
    TextView tvTequan02;
    @Bind(R.id.iv_tequan03)
    ImageView ivTequan03;
    @Bind(R.id.tv_tequan03)
    TextView tvTequan03;
    @Bind(R.id.iv_tequan04)
    ImageView ivTequan04;
    @Bind(R.id.tv_tequan04)
    TextView tvTequan04;
    @Bind(R.id.circleProgressBar)
    CircleProgressBar circleProgressBar;
    @Bind(R.id.circleProgressBar2)
    CircleProgressBar circleProgressBar2;
    @Bind(R.id.tv_fenxiang)
    TextView tvFenxiang;
    @Bind(R.id.tv_jiangli)
    TextView tv_jiangli;
    @Bind(R.id.tv_rule)
    TextView tv_rule;
    @Bind(R.id.container2)
    LinearLayout container2;
    @Bind(R.id.ll_tequan)
    LinearLayout ll_tequan;
    @Bind(R.id.tv_shenfen)
    TextView tv_shenfen;
    @Bind(R.id.tv_user_shenfen)
    TextView tv_user_shenfen;
    @Bind(R.id.wv_rule)
    WebView wvRule;

    private TuiguangModel data;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tuiguang;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        showContentView();
        setMainTitle("推广奖励");
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_PROMOTE_RESUME)
                .setCallBackData(new BaseNewNetModelimpl<TuiguangModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<TuiguangModel> respnse, String source) {
                        if (respnse.getData() != null) {
                            //处理用户等级
                            data = respnse.getData();
                            int user_level = data.getUser_level();
                            checkUserLevel(user_level);

                            //等级
                            if (user_level == 0) {
                                container2.setVisibility(View.GONE);
                                circleProgressBar.setContent("还需" + data.getRecommended_num() + "人");
                                circleProgressBar.setProgress(data.getRecommended_proportion());
                                ll_tequan.setVisibility(View.GONE);
                            } else {
                                container2.setVisibility(View.VISIBLE);
                                circleProgressBar.setContent("还需" + data.getRecommended_num() + "人");
                                circleProgressBar.setProgress(data.getRecommended_proportion());
                                circleProgressBar2.setContent("还需" + data.getBreeding_num() + "人");
                                circleProgressBar2.setProgress(data.getBreeding_proportion());
                                ll_tequan.setVisibility(View.VISIBLE);
                            }

                            tv_rule.setText(data.getActivity_rule());
                            wvRule.loadData(data.getActivity_rule(), "text/html;charset=utf-8", "UTF-8");
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<TuiguangModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    private void checkUserLevel(int user_level) {
        if (user_level == 0) {
            tv_shenfen.setText("普通会员特权");
            tv_user_shenfen.setText("普通会员");
            relaCntainer01.setBackgroundResource(R.drawable.shape_tuiguang_bg);
            tvNum01.setBackgroundResource(R.drawable.shape_tuiguang_bg_deep);
            relaCntainer02.setBackground(null);
            tvNum02.setBackgroundResource(R.drawable.shape_tuiguang_bg_grey);
            relaCntainer03.setBackground(null);
            tvNum03.setBackgroundResource(R.drawable.shape_tuiguang_bg_grey);
            relaCntainer04.setBackground(null);
            tvNum04.setBackgroundResource(R.drawable.shape_tuiguang_bg_grey);
            viewLine01.setBackgroundColor(Color.parseColor("#3172f4"));
            viewLine02.setBackgroundColor(Color.parseColor("#9399a5"));
            viewLine03.setBackgroundColor(Color.parseColor("#9399a5"));
            tvText01.setTextColor(Color.parseColor("#394043"));
            tvText02.setTextColor(Color.parseColor("#9399a5"));
            tvText03.setTextColor(Color.parseColor("#9399a5"));
            tvText04.setTextColor(Color.parseColor("#9399a5"));
        } else if (user_level == 1) {
            tv_shenfen.setText("主任特权");
            tv_user_shenfen.setText("主任");
            relaCntainer01.setBackground(null);
            tvNum01.setBackgroundResource(R.drawable.shape_tuiguang_bg_deep);
            relaCntainer02.setBackgroundResource(R.drawable.shape_tuiguang_bg);
            tvNum02.setBackgroundResource(R.drawable.shape_tuiguang_bg_deep);
            relaCntainer03.setBackground(null);
            tvNum03.setBackgroundResource(R.drawable.shape_tuiguang_bg_grey);
            relaCntainer04.setBackground(null);
            tvNum04.setBackgroundResource(R.drawable.shape_tuiguang_bg_grey);
            viewLine01.setBackgroundColor(Color.parseColor("#3172f4"));
            viewLine02.setBackgroundColor(Color.parseColor("#3172f4"));
            viewLine03.setBackgroundColor(Color.parseColor("#9399a5"));
            tvText01.setTextColor(Color.parseColor("#394043"));
            tvText02.setTextColor(Color.parseColor("#394043"));
            tvText03.setTextColor(Color.parseColor("#9399a5"));
            tvText04.setTextColor(Color.parseColor("#9399a5"));
        } else if (user_level == 2) {
            tv_shenfen.setText("经理特权");
            tv_user_shenfen.setText("经理");
            relaCntainer01.setBackground(null);
            tvNum01.setBackgroundResource(R.drawable.shape_tuiguang_bg_deep);
            relaCntainer02.setBackground(null);
            tvNum02.setBackgroundResource(R.drawable.shape_tuiguang_bg_deep);
            relaCntainer03.setBackgroundResource(R.drawable.shape_tuiguang_bg);
            tvNum03.setBackgroundResource(R.drawable.shape_tuiguang_bg_deep);
            relaCntainer04.setBackground(null);
            tvNum04.setBackgroundResource(R.drawable.shape_tuiguang_bg_grey);
            viewLine01.setBackgroundColor(Color.parseColor("#3172f4"));
            viewLine02.setBackgroundColor(Color.parseColor("#3172f4"));
            viewLine03.setBackgroundColor(Color.parseColor("#3172f4"));
            tvText01.setTextColor(Color.parseColor("#394043"));
            tvText02.setTextColor(Color.parseColor("#394043"));
            tvText03.setTextColor(Color.parseColor("#394043"));
            tvText04.setTextColor(Color.parseColor("#9399a5"));
        } else if (user_level == 3) {
            tv_shenfen.setText("总监特权");
            tv_user_shenfen.setText("总监");
            relaCntainer01.setBackground(null);
            tvNum01.setBackgroundResource(R.drawable.shape_tuiguang_bg_deep);
            relaCntainer02.setBackground(null);
            tvNum02.setBackgroundResource(R.drawable.shape_tuiguang_bg_deep);
            relaCntainer03.setBackground(null);
            tvNum03.setBackgroundResource(R.drawable.shape_tuiguang_bg_deep);
            relaCntainer04.setBackgroundResource(R.drawable.shape_tuiguang_bg);
            tvNum04.setBackgroundResource(R.drawable.shape_tuiguang_bg_deep);
            viewLine01.setBackgroundColor(Color.parseColor("#3172f4"));
            viewLine02.setBackgroundColor(Color.parseColor("#3172f4"));
            viewLine03.setBackgroundColor(Color.parseColor("#3172f4"));
            tvText01.setTextColor(Color.parseColor("#394043"));
            tvText02.setTextColor(Color.parseColor("#394043"));
            tvText03.setTextColor(Color.parseColor("#394043"));
            tvText04.setTextColor(Color.parseColor("#394043"));
        }
    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    private int index = 1;

    @OnClick({R.id.tv_guize, R.id.tv_fenxiang, R.id.tv_jiangli})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_guize:
                if (data != null)
                    ModuleInstructionActivity.go(getActivity(), "等级规则", data.getLevel_rule());
                break;
            case R.id.tv_fenxiang:
                if (data == null) {
                    return;
                }
                showShareDialog(data.getShare_url());
                break;
            case R.id.tv_jiangli:
                startAtvDonFinish(TuiGuangWalletListActivity.class);
                break;
        }
    }

    private IWXAPI api;
    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;
    private int mTargetQuanScene = SendMessageToWX.Req.WXSceneTimeline ;
    private static final int THUMB_SIZE = 150;

    /*
     * 展示分享对话框
     */
    private void showShareDialog(String url) {
        if (TextUtils.isEmpty(url))
            return;

        final Dialog bottomDialog = new Dialog(getActivity(), R.style.BottomDialog);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_content_normal, null);
        contentView.findViewById(R.id.tv_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
                weixinShare();
            }
        });
        contentView.findViewById(R.id.tv_weixin_quan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
                weixinQuanShare();
            }
        });
        contentView.findViewById(R.id.tv_erweima).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
                ((BackFragmentActivity) getActivity()).addFragment(ErweimaFragment.newInstance(data.getQr_url()),
                        true, true);
            }
        });
        contentView.findViewById(R.id.tv_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
                showTips("链接已复制");
                ClipboardManager myClipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
                ClipData myClip;
                myClip = ClipData.newPlainText("text", data.getShare_url());
                myClipboard.setPrimaryClip(myClip);
            }
        });
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.show();
    }

    //微信分享
    private void weixinShare() {
        api = WXAPIFactory.createWXAPI(getActivity(), Define.WX_APP_ID);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = data.getShare_url();
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = data.getRecommended_title();
        msg.description = data.getRecommended_description();
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_logo_rectangle);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = mTargetScene;
        api.sendReq(req);
    }

    //微信朋友圈分享
    private void weixinQuanShare() {
        api = WXAPIFactory.createWXAPI(getActivity(), Define.WX_APP_ID);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = data.getShare_url();
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = data.getRecommended_title();
        msg.description = data.getRecommended_description();
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_logo_rectangle);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = mTargetQuanScene;
        api.sendReq(req);
    }


    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    @Override
    public int registStartMode() {
        return singleTask;
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
}
