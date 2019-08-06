package com.windmillsteward.jukutech.activity.mine.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.AboutUsActivity;
import com.windmillsteward.jukutech.activity.mine.activity.AfterSalesListActivity;
import com.windmillsteward.jukutech.activity.mine.activity.FundsTrusteeshipHomeActivity;
import com.windmillsteward.jukutech.activity.mine.activity.MyCollectActivity;
import com.windmillsteward.jukutech.activity.mine.activity.MyCouponActivity;
import com.windmillsteward.jukutech.activity.mine.activity.MyInfoActivity;
import com.windmillsteward.jukutech.activity.mine.activity.MyInfoView;
import com.windmillsteward.jukutech.activity.mine.activity.MyOrderActivity;
import com.windmillsteward.jukutech.activity.mine.activity.MyPublishActivity;
import com.windmillsteward.jukutech.activity.mine.activity.MyWalletActivity;
import com.windmillsteward.jukutech.activity.mine.activity.ServiceCenterActivity;
import com.windmillsteward.jukutech.activity.mine.activity.SpecialtyOrderListActivity;
import com.windmillsteward.jukutech.activity.mine.activity.SystemSettingActivity;
import com.windmillsteward.jukutech.activity.mine.presenter.MyInfoImpl;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.bean.CountOrderNumberBean;
import com.windmillsteward.jukutech.bean.UserInfo;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

/**
 * 描述：购物车
 * author:cyq
 * 2018-02-07
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MineFragment extends BaseFragment implements View.OnClickListener, MyInfoView {

    private LinearLayout lay_ll_head;
    private ImageView iv_head;
    private TextView tv_user_name;
    private TextView tv_my_publish;
    private TextView tv_my_order;
    private TextView tv_my_collection;
    private TextView tv_my_wallet;
    private TextView tv_wait_payment_number;
    private RelativeLayout lay_rl_wait_payment;
    private TextView tv_wait_shipments_number;
    private RelativeLayout lay_rl_wait_shipments;
    private TextView tv_wait_receive_number;
    private RelativeLayout lay_rl_wait_receive;
    private TextView tv_canceled_number;
    private RelativeLayout lay_rl_canceled;
    private TextView tv_drawback_number;
    private RelativeLayout lay_rl_drawback;
    private TextView tv_discount;
    private TextView tv_trusteeship;
    private TextView tv_service;
    private TextView tv_system_center;
    private TextView tv_about_us;
    private TextView tv_check_all_order;

    private MyInfoImpl myInfoImpl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateView(R.layout.fragment_mine);
        initView(view);
        return view;
    }

    @Override
    public int registStartMode() {
        return singleTask;
    }

    private void initView(View view) {
        iv_head = (ImageView) view.findViewById(R.id.iv_head);
        lay_ll_head = (LinearLayout) view.findViewById(R.id.lay_ll_head);
        tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
        tv_my_publish = (TextView) view.findViewById(R.id.tv_my_publish);
        tv_my_order = (TextView) view.findViewById(R.id.tv_my_order);
        tv_my_collection = (TextView) view.findViewById(R.id.tv_my_collection);
        tv_my_wallet = (TextView) view.findViewById(R.id.tv_my_wallet);
        tv_wait_payment_number = (TextView) view.findViewById(R.id.tv_wait_payment_number);
        lay_rl_wait_payment = (RelativeLayout) view.findViewById(R.id.lay_rl_wait_payment);
        tv_wait_shipments_number = (TextView) view.findViewById(R.id.tv_wait_shipments_number);
        lay_rl_wait_shipments = (RelativeLayout) view.findViewById(R.id.lay_rl_wait_shipments);
        tv_wait_receive_number = (TextView) view.findViewById(R.id.tv_wait_receive_number);
        lay_rl_wait_receive = (RelativeLayout) view.findViewById(R.id.lay_rl_wait_receive);
        tv_canceled_number = (TextView) view.findViewById(R.id.tv_canceled_number);
        lay_rl_canceled = (RelativeLayout) view.findViewById(R.id.lay_rl_canceled);
        tv_drawback_number = (TextView) view.findViewById(R.id.tv_drawback_number);
        lay_rl_drawback = (RelativeLayout) view.findViewById(R.id.lay_rl_drawback);
        tv_discount = (TextView) view.findViewById(R.id.tv_discount);
        tv_trusteeship = (TextView) view.findViewById(R.id.tv_trusteeship);
        tv_service = (TextView) view.findViewById(R.id.tv_service);
        tv_system_center = (TextView) view.findViewById(R.id.tv_system_center);
        tv_about_us = (TextView) view.findViewById(R.id.tv_about_us);
        tv_check_all_order = (TextView) view.findViewById(R.id.tv_check_all_order);

        lay_ll_head.setOnClickListener(this);

        tv_my_publish.setOnClickListener(this);
        tv_my_order.setOnClickListener(this);
        tv_my_collection.setOnClickListener(this);
        tv_my_wallet.setOnClickListener(this);

        tv_check_all_order.setOnClickListener(this);
        lay_rl_wait_payment.setOnClickListener(this);
        lay_rl_wait_shipments.setOnClickListener(this);
        lay_rl_wait_receive.setOnClickListener(this);
        lay_rl_canceled.setOnClickListener(this);
        lay_rl_drawback.setOnClickListener(this);

        tv_discount.setOnClickListener(this);
        tv_trusteeship.setOnClickListener(this);
        tv_service.setOnClickListener(this);
        tv_system_center.setOnClickListener(this);
        tv_about_us.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        String access_token = (String) Hawk.get(Define.ACCESS_TOKEN);
        if (TextUtils.isEmpty(access_token)) {
            tv_user_name.setText("登录");
            x.image().bind(iv_head, "", ImageUtils.defaultPersonHeadOptions());
        } else {
            //请求用户数据
            myInfoImpl = new MyInfoImpl(this);
            myInfoImpl.getMyInfo();
            myInfoImpl.geCountOrderNumberData();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lay_ll_head:
                String access_token = (String) Hawk.get(Define.ACCESS_TOKEN);
                if (TextUtils.isEmpty(access_token)) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    //跳到个人中心
                    startAtvDonFinish(MyInfoActivity.class);
                }
                break;
            case R.id.tv_my_publish://我的发布
                if (TextUtils.isEmpty(getAccessToken())) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    startAtvDonFinish(MyPublishActivity.class);
                }
                break;
            case R.id.tv_my_order://我的订单
                if (TextUtils.isEmpty(getAccessToken())) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    startAtvDonFinish(MyOrderActivity.class);
                }
                break;
            case R.id.tv_my_collection://我的收藏
                if (TextUtils.isEmpty(getAccessToken())) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    startAtvDonFinish(MyCollectActivity.class);
                }
                break;
            case R.id.tv_my_wallet://我的钱包
                if (TextUtils.isEmpty(getAccessToken())) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    startAtvDonFinish(MyWalletActivity.class);
                }
                break;
            case R.id.tv_check_all_order:
                startAtvDonFinish(SpecialtyOrderListActivity.class);
                break;
            case R.id.lay_rl_wait_payment://代付款
                Bundle bundle1 = new Bundle();
                bundle1.putInt(Define.INTENT_TYPE,1);
                startAtvDonFinish(SpecialtyOrderListActivity.class, bundle1);
                break;
            case R.id.lay_rl_wait_shipments://待发货
                Bundle bundle2 = new Bundle();
                bundle2.putInt(Define.INTENT_TYPE,2);
                startAtvDonFinish(SpecialtyOrderListActivity.class, bundle2);
                break;
            case R.id.lay_rl_wait_receive://待收货
                Bundle bundle3 = new Bundle();
                bundle3.putInt(Define.INTENT_TYPE,3);
                startAtvDonFinish(SpecialtyOrderListActivity.class, bundle3);
                break;
            case R.id.lay_rl_canceled://已取消
                Bundle bundle6 = new Bundle();
                bundle6.putInt(Define.INTENT_TYPE,6);
                startAtvDonFinish(SpecialtyOrderListActivity.class, bundle6);
                break;
            case R.id.lay_rl_drawback://退款/售后
                startAtvDonFinish(AfterSalesListActivity.class);
                break;
            case R.id.tv_discount://优惠券
                if (TextUtils.isEmpty(getAccessToken())) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    startAtvDonFinish(MyCouponActivity.class);
                }
                break;
            case R.id.tv_trusteeship://资金托管
                if (TextUtils.isEmpty(getAccessToken())) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    startAtvDonFinish(FundsTrusteeshipHomeActivity.class);
                }
                break;
            case R.id.tv_service://服务中心
                startAtvDonFinish(ServiceCenterActivity.class);
                break;
            case R.id.tv_system_center://系统设置
                startAtvDonFinish(SystemSettingActivity.class);
                break;
            case R.id.tv_about_us://关于我们
                startAtvDonFinish(AboutUsActivity.class);
//                startAtvDonFinish(SeckillListActivity.class);
                break;
        }
    }

    @Override
    public void getMyInfoSuccess(UserInfo userInfo) {
        if (userInfo == null) {
            return;
        }
        String nickname = userInfo.getNickname();
        tv_user_name.setText(TextUtils.isEmpty(nickname) ? "" : nickname);
        String user_avatar_url = userInfo.getUser_avatar_url();
        int sex = userInfo.getSex();
        if (sex == 1) {
            x.image().bind(iv_head, user_avatar_url, ImageUtils.defaultBoyHeadOptions());
        } else if (sex == 2) {
            x.image().bind(iv_head, user_avatar_url, ImageUtils.defaultGirlHeadOptions());
        }
    }

    @Override
    public void getMyInfoFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void getCountOrderNumber(CountOrderNumberBean bean) {
        int cancel_number = bean.getCancel_number();

        if (cancel_number!=0) {
            tv_canceled_number.setVisibility(View.VISIBLE);
            tv_canceled_number.setText(String.valueOf(cancel_number));
        } else {
            tv_canceled_number.setVisibility(View.GONE);
        }
        int goods_received_number = bean.getGoods_received_number();
        if (goods_received_number!=0) {
            tv_wait_receive_number.setVisibility(View.VISIBLE);
            tv_wait_receive_number.setText(String.valueOf(goods_received_number));
        } else {
            tv_wait_receive_number.setVisibility(View.GONE);
        }
        int pending_delivery_number = bean.getPending_delivery_number();
        if (pending_delivery_number!=0) {
            tv_wait_shipments_number.setVisibility(View.VISIBLE);
            tv_wait_shipments_number.setText(String.valueOf(pending_delivery_number));
        } else {
            tv_wait_shipments_number.setVisibility(View.GONE);
        }
        int pending_payment_number = bean.getPending_payment_number();
        if (pending_payment_number!=0) {
            tv_wait_payment_number.setVisibility(View.VISIBLE);
            tv_wait_payment_number.setText(String.valueOf(pending_payment_number));
        } else {
            tv_wait_payment_number.setVisibility(View.GONE);
        }
    }
}