package com.windmillsteward.jukutech.activity.chat.redpacketdialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.chat.activity.RedPacketDetailActivity;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.RedPacketDetailModel;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.dialog.LoadingDialog;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.GlideUtil;

import org.xutils.common.Callback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author CaiXi on  2016/12/8 00:45.
 * @Github: https://github.com/cxMax
 * @Description dialog具体实现和业务实现
 */

public class DialogController implements DialogHelper.Result, View.OnClickListener {

    private Context mContext;

    private View mPopView;
    private PopupWindow mPopupWindow;

    private ImageView iv_close;

    private LinearLayout lay_ll_content;
    private TextView tv_title;
    private TextView tv_desc;
    private TextView tv_tips;
    private ImageView iv_open;
    private CircleImageView iv_head;
    private LoadingDialog loadingDialog;

    private List<Callback.Cancelable> cancelableList = new ArrayList<>();

    private AnimatorUtil mAnimatorUtil = new AnimatorUtil();

    RedPacketDetailModel data;
    private int packet_id;

    public DialogController(Context context) {
        this.mContext = context;
        if (mPopupWindow == null) {
            initPopupWindow();
        }
    }

    private void initPopupWindow() {
        loadingDialog = new LoadingDialog(mContext);
        mPopView = ((Activity) mContext).getLayoutInflater().inflate(R.layout.red_packet_pop_view, null);
        mPopupWindow = new PopupWindow(mPopView,
                (int) (312 * mContext.getResources().getDisplayMetrics().density),
                (int) (390 * mContext.getResources().getDisplayMetrics().density), false);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                final WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
                if (lp.alpha != 1.0f) {
                    lp.alpha = 1.0f;
                    ((Activity) mContext).getWindow().setAttributes(lp);
                }
                lay_ll_content.setVisibility(View.GONE);
            }
        });
        initPopView(mPopView);
    }

    private void initPopView(View rootView) {
        iv_close = (ImageView) rootView.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);

        lay_ll_content = (LinearLayout) rootView.findViewById(R.id.lay_ll_content);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_desc = (TextView) rootView.findViewById(R.id.tv_desc);
        tv_tips = (TextView) rootView.findViewById(R.id.tv_tips);
        iv_open = (ImageView) rootView.findViewById(R.id.iv_open);
        iv_head = (CircleImageView) rootView.findViewById(R.id.iv_head);
        iv_open.setOnClickListener(this);
        tv_tips.setOnClickListener(this);

    }

    @Override
    public void showDilaog(DialogHelper.DilaogBean bean, RedPacketDetailModel model, int packet_id) {
        this.data = model;
        this.packet_id = packet_id;
        showPop(bean, model);
        flipCardShow();
    }

    @Override
    public void hideDialog() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            cancelCall();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_open:
                getRedPacket();
                break;
            case R.id.iv_close:
                hideDialog();
                break;
            case R.id.tv_tips:
                if (packet_id != 0) {
                    hideDialog();
                    Intent intent = new Intent(mContext, RedPacketDetailActivity.class);
                    intent.putExtra(RedPacketDetailActivity.PACKET_ID, packet_id);
                    mContext.startActivity(intent);
                }
                break;
        }
    }

    private void showPop(DialogHelper.DilaogBean bean, RedPacketDetailModel model) {
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(mPopView, Gravity.CENTER, 0, 0);
        }
        if (model != null) {
            tv_title.setText(model.getNickname() + "的红包");
            tv_desc.setText(model.getPacket_desc());
            GlideUtil.show(mContext, model.getUser_avatar_url(), iv_head);
            if (model.getIs_expired() == 1 //已过期
                    || model.getGet_num() == model.getNum()//已抢红包数等于总红包数
                    || model.getStatus() == 2) {//已经抢过
                iv_open.setVisibility(View.INVISIBLE);
            }
            if (model.getStatus() == 1) {//红包已过期
                iv_open.setVisibility(View.INVISIBLE);
                tv_desc.setText("红包已过期");
            }else if (model.getStatus() == 3){//红包被抢完
                tv_desc.setText("红包已被抢光啦！");
            }
        }

        if (!((Activity) mContext).isDestroyed()
                && bean != null
                && bean.getStatus() == DialogHelper.DilaogBean.STATUS_ROTATE
                && lay_ll_content != null
                && mAnimatorUtil != null) {
            lay_ll_content.setVisibility(View.VISIBLE);
            mAnimatorUtil.cardChange(lay_ll_content, (Activity) mContext);
        }
    }

    private void flipCardShow() {
        if (!((Activity) mContext).isDestroyed()
                && lay_ll_content != null
                && mAnimatorUtil != null) {
            lay_ll_content.setRotationY(0);
            mAnimatorUtil.cardChange(lay_ll_content, (Activity) mContext);
        }
    }

    private void flipCardChange() {
        AwardRotateAnimation animation = new AwardRotateAnimation();
        animation.setRepeatCount(Animation.INFINITE);
        iv_open.startAnimation(animation);

        if (!((Activity) mContext).isDestroyed()
                && lay_ll_content != null
                && mAnimatorUtil != null) {
            getTimer();
        }
    }

    private void getTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideDialog();
                        iv_open.clearAnimation();
                    }
                });
                Intent intent = new Intent(mContext, RedPacketDetailActivity.class);
                intent.putExtra(RedPacketDetailActivity.PACKET_ID, packet_id);
                mContext.startActivity(intent);
            }
        }, 2000);
    }


    private void getRedPacket() {
        loadingDialog.showLoading("");
        addCall(new NetUtil().setUrl(APIS.URL_GET_RED_PACKET)
                .addParams("packet_id", packet_id + "")
                .setCallBackData(new BaseNewNetModelimpl<RedPacketDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        loadingDialog.dismiss();
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                        ((Activity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iv_open.clearAnimation();
                            }
                        });
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<RedPacketDetailModel> respnse, String source) {
                        loadingDialog.dismiss();
                        RedPacketDetailModel data = respnse.getData();
                        if (data != null) {
                            int is_expired = data.getIs_expired();
                            if (is_expired != 1) {
                                flipCardChange();
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeReference<BaseResultInfo<RedPacketDetailModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    /**
     * 添加请求到队列
     */
    public void addCall(Callback.Cancelable cancelable) {
        cancelableList.add(cancelable);
    }

    private void  cancelCall(){
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            if (cancelableList != null){
                for (Callback.Cancelable cancelable:cancelableList){
                    if (cancelable != null){
                        if (!cancelable.isCancelled()){
                            cancelable.cancel();
                        }
                    }
                }
            }
        }

    }
}
