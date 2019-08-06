package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.BillUtils;
import com.windmillsteward.jukutech.activity.home.commons.pay.ChoiceCouponListActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.presenter.HotelAndHouseReservePresenter;
import com.windmillsteward.jukutech.activity.mine.activity.MyCouponActivity;
import com.windmillsteward.jukutech.activity.mine.activity.MyOrderActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.HotelAndHouseDetailBean;
import com.windmillsteward.jukutech.bean.HotelPayBeforeBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 描述：酒店房源预定
 * 时间：2018/1/28/028
 * 作者：xjh
 */
public class HotelAndHouseReserveActivity extends BaseActivity implements HotelAndHouseReserveView, View.OnClickListener {

    public static final String DATA = "DATA";
    public static final String POSITION = "POSITION";
    public static final String START = "START";
    public static final String END = "END";
    public static final String DAY_NUMBER = "DAY_NUMBER";

    public static final int RESULT_CODE_SUCCESS = 200;

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_hotel_name;
    private TextView tv_time;
    private TextView tv_room_type;
    private TextView tv_room_count;
    private LinearLayout linear_personals;
    private ImageView iv_add;
    private EditText et_phone;
    private TextView tv_coupon;
    private ImageView iv_choice_zfb;
    private TextView tv_zfb_price;
    private ImageView iv_choice_wx;
    private TextView tv_wx_price;
    private TextView tv_price;
    private TextView tv_deposit;
    private TextView tv_discount;
    private TextView tv_total_price;
    private TextView tv_reserve;
    private EditText et_position_name;

    private LayoutInflater inflater;

    private HotelAndHouseDetailBean bean;
    private int position;
    private String startTime, endTime;
    private int days;
    private int rooms=1;
    private List<String> personals = new ArrayList<>();
    private List<EditText> editTexts = new ArrayList<>();
    private int pay_type=2;
    private HotelAndHouseReservePresenter presenter;
    private int coupon_receive_id=0;  // 优惠券id
    private HotelPayBeforeBean hotelPayBeforeBean;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotelandhouse_reserve);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            bean = (HotelAndHouseDetailBean) extras.getSerializable(DATA);
            position = extras.getInt(POSITION);
            days = extras.getInt(DAY_NUMBER);
            startTime = extras.getString(START);
            endTime = extras.getString(END);
        } else {
            finish();
            return;
        }

        initView();
        dialog = new ProgressDialog(this);

        inflater = LayoutInflater.from(this);
        initToolbar();
        initData();
        presenter = new HotelAndHouseReservePresenter(this);
        presenter.payBefore(getAccessToken(),bean.getHotel_id(),startTime,
                endTime,days,bean.getRoom_list().get(position).getRoom_type_id(),
                rooms,personals,"",coupon_receive_id,"" );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==100 && resultCode==200) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                coupon_receive_id = extras.getInt(ChoiceCouponListActivity.SELECT_ID);
                tv_coupon.setText((coupon_receive_id==0)?"不使用":"已选择优惠券");
                if (hotelPayBeforeBean!=null) {
                    presenter.payBefore(getAccessToken(),bean.getHotel_id(),startTime,
                            endTime,days,bean.getRoom_list().get(position).getRoom_type_id(),
                            rooms,personals,"",coupon_receive_id,hotelPayBeforeBean.getTotal_order_sn() );
                }
            }
        }
    }

    private void initData() {
        if (bean != null) {
            tv_hotel_name.setText(bean.getHotel_name());
            tv_time.setText("入离时间：" + DateUtil.StampTimeToDate(startTime, "MM月dd日") + "-" + DateUtil.StampTimeToDate(endTime, "MM月dd日") + " | 共" + days + "晚");
            tv_room_type.setText(bean.getRoom_list().get(position).getRoom_type_name());
        }
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("预定");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_hotel_name = (TextView) findViewById(R.id.tv_hotel_name);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_room_type = (TextView) findViewById(R.id.tv_room_type);
        tv_room_count = (TextView) findViewById(R.id.tv_room_count);
        linear_personals = (LinearLayout) findViewById(R.id.linear_personals);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_coupon = (TextView) findViewById(R.id.tv_coupon);
        iv_choice_zfb = (ImageView) findViewById(R.id.iv_choice_zfb);
        tv_zfb_price = (TextView) findViewById(R.id.tv_zfb_price);
        iv_choice_wx = (ImageView) findViewById(R.id.iv_choice_wx);
        tv_wx_price = (TextView) findViewById(R.id.tv_wx_price);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_deposit = (TextView) findViewById(R.id.tv_deposit);
        tv_discount = (TextView) findViewById(R.id.tv_discount);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        tv_reserve = (TextView) findViewById(R.id.tv_reserve);
        et_position_name = (EditText) findViewById(R.id.et_position_name);

        tv_coupon.setOnClickListener(this);
        tv_room_count.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        iv_choice_zfb.setOnClickListener(this);
        iv_choice_wx.setOnClickListener(this);
        tv_reserve.setOnClickListener(this);

//        String initInfo = BCPay.initWechatPay(this, "ad45d74584ugf41ft6u4ty24w9a1r4b1");
//        if (initInfo != null) {
//            showTips("微信支付不可用", 0);
//            iv_choice_wx.setEnabled(false);
//        }
    }

    private void submit() {
        // validate
        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showTips("请输入手机号", 0);
            return;
        }
        String et_position_name = this.et_position_name.getText().toString().trim();
        if (TextUtils.isEmpty(et_position_name)) {
            showTips("请输入入住人",0);
            return;
        }
        personals.add(et_position_name);

        for (EditText editText : editTexts) {
            String name = editText.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                showTips("请输入入住人",0);
                return;
            }
            personals.add(name);
        }

        presenter.pay(getAccessToken(),bean.getHotel_id(),startTime,
                endTime,days,bean.getRoom_list().get(position).getRoom_type_id(),
                rooms,personals,phone,coupon_receive_id,hotelPayBeforeBean.getTotal_order_sn() );
    }

    @Override
    public void loadRoomNumSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_room_count.setText(text);
                        rooms = id;
                        presenter.payBefore(getAccessToken(),bean.getHotel_id(),startTime,
                                endTime,days,bean.getRoom_list().get(position).getRoom_type_id(),
                                rooms,personals,"",0,"" );
                    }
                })
                .show();
    }

    @Override
    public void payBeforeResult(HotelPayBeforeBean bean) {
        hotelPayBeforeBean = bean;
        tv_price.setText("￥"+bean.getPay_fee());
        tv_deposit.setText("￥"+bean.getPrepay_fee());
        tv_discount.setText("￥"+bean.getTotal_coupon_fee());
        tv_total_price.setText("￥"+bean.getTotal_pay_fee());
        tv_zfb_price.setText("￥"+bean.getTotal_pay_fee());
        tv_wx_price.setText("￥"+bean.getTotal_pay_fee());

        if (pay_type==1) {
            tv_zfb_price.setVisibility(View.VISIBLE);
            tv_wx_price.setVisibility(View.GONE);
        } else {
            tv_zfb_price.setVisibility(View.GONE);
            tv_wx_price.setVisibility(View.VISIBLE);
        }

        personals.clear();

        if (rooms-1>editTexts.size()){
            int i1 = rooms - editTexts.size();
            for (int i = 1; i < i1; i++) {
                final View child = inflater.inflate(R.layout.view_hotelandhouse_reserve_add, linear_personals, false);
                linear_personals.addView(child);
                EditText name = child.findViewById(R.id.et_position_name);
                editTexts.add(name);
            }
        } else if (rooms-1<editTexts.size()) {
            for (int size = editTexts.size(); size > rooms-1; size--) {
                linear_personals.removeViewAt(size-1);
                editTexts.remove(size-1);
            }
        }
    }

    @Override
    public void pay(HotelPayBeforeBean bean) {
        if (pay_type==1) {
            dialog.show();
//            Map<String, String> mapOptional = new HashMap<>();
//            BCPay.PayParams aliParam = new BCPay.PayParams();
//            aliParam.channelType = BCReqParams.BCChannelTypes.ALI_APP;
//            aliParam.billTitle = "酒店预订";
//            aliParam.billTotalFee = (int) (Float.valueOf(bean.getTotal_pay_fee()) * 100);
//            aliParam.billNum = BillUtils.genBillNum();
//            aliParam.optional = mapOptional;
//            aliParam.billNum = bean.getTotal_order_sn();
//
//            BCPay.getInstance(this).reqPaymentAsync(
//                    aliParam, bcCallback);
        } else {
            dialog.show();
            pay_type = 2;
            Map<String, String> mapOptiona2 = new HashMap<>();
//            if (BCPay.isWXAppInstalledAndSupported() &&
//                    BCPay.isWXPaySupported()) {
//
//                BCPay.PayParams payParams = new BCPay.PayParams();
//                payParams.channelType = BCReqParams.BCChannelTypes.WX_APP;
//                payParams.billTitle = "酒店预订";  //订单标题
//                payParams.billTotalFee = (int) (Float.valueOf(bean.getTotal_pay_fee()) * 100);    //订单金额(分)
//                payParams.billNum = BillUtils.genBillNum();  //订单流水号
//                payParams.optional = mapOptiona2;            //扩展参数(可以null)
//                payParams.billNum = bean.getTotal_order_sn();
//
//                BCPay.getInstance(this).reqPaymentAsync(
//                        payParams,
//                        bcCallback);            //支付完成后回调入口

//            } else {
//                showTips("您尚未安装微信或者安装的微信版本不支持", 0);
//                dialog.dismiss();
//            }
        }
    }

    private String toastMsg;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    showTips(toastMsg, 0);
                    break;
                case 200:  // 支付成功

                    new AlertDialog(HotelAndHouseReserveActivity.this).builder()
                            .setTitle("提示")
                            .setMsg("预定酒店成功")
                            .setCancelable(true)
                            .setNegativeButton("返回", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setResult(RESULT_CODE_SUCCESS);
                                    finish();
                                }
                            })
                            .setPositiveButton("去订单", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startAtvDonFinish(MyOrderActivity.class);
                                    setResult(RESULT_CODE_SUCCESS);
                                    finish();
                                }
                            })
                            .show();
                    break;
            }
            return true;
        }
    });

//    //支付结果返回入口
//    BCCallback bcCallback = new BCCallback() {
//        @Override
//        public void done(final BCResult bcResult) {
//            final BCPayResult bcPayResult = (BCPayResult) bcResult;
//            //此处关闭loading界面
//            dialog.dismiss();
//
//            //根据你自己的需求处理支付结果
//            String result = bcPayResult.getResult();
//
//            /*
//              注意！
//              所有支付渠道建议以服务端的状态金额为准，此处返回的RESULT_SUCCESS仅仅代表手机端支付成功
//            */
//            Message msg = mHandler.obtainMessage();
//            //单纯的显示支付结果
//            msg.what = 2;
//            if (result.equals(BCPayResult.RESULT_SUCCESS)) {
//                msg.what = 200;
//                msg.obj = bcPayResult.getId();
//                toastMsg = "支付成功";
//            } else if (result.equals(BCPayResult.RESULT_CANCEL)) {
//                toastMsg = "用户取消支付";
//            } else if (result.equals(BCPayResult.RESULT_FAIL)) {
//                toastMsg = "支付失败, 原因: " + bcPayResult.getErrCode() +
//                        " # " + bcPayResult.getErrMsg() +
//                        " # " + bcPayResult.getDetailInfo();
//
//                /*
//                 * 你发布的项目中不应该出现如下错误，此处由于支付宝政策原因，
//                 * 不再提供支付宝支付的测试功能，所以给出提示说明
//                 */
//                if (bcPayResult.getErrMsg().equals("PAY_FACTOR_NOT_SET") &&
//                        bcPayResult.getDetailInfo().startsWith("支付宝参数")) {
//                    toastMsg = "支付失败：由于支付宝政策原因，故不再提供支付宝支付的测试功能，给您带来的不便，敬请谅解";
//                }
//            } else if (result.equals(BCPayResult.RESULT_UNKNOWN)) {
//                //可能出现在支付宝8000返回状态
//                toastMsg = "订单状态未知";
//            } else {
//                toastMsg = "invalid return";
//            }
//
//            mHandler.sendMessage(msg);
//        }
//    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_coupon:
                if (hotelPayBeforeBean==null) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putInt(ChoiceCouponListActivity.SELECT_ID,coupon_receive_id);
                bundle.putFloat(ChoiceCouponListActivity.CURR_PRICE, Float.parseFloat(hotelPayBeforeBean.getPay_fee()));
                startAtvDonFinishForResult(ChoiceCouponListActivity.class,100, bundle);
                break;
            case R.id.iv_choice_zfb:
                pay_type =1;
                iv_choice_zfb.setImageResource(R.mipmap.icon_choise);
                iv_choice_wx.setImageResource(R.mipmap.icon_choise_no);
                tv_zfb_price.setVisibility(View.VISIBLE);
                tv_wx_price.setVisibility(View.GONE);
                break;
            case R.id.iv_choice_wx:
                pay_type =2;
                iv_choice_zfb.setImageResource(R.mipmap.icon_choise_no);
                iv_choice_wx.setImageResource(R.mipmap.icon_choise);
                tv_zfb_price.setVisibility(View.GONE);
                tv_wx_price.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_room_count:
                presenter.loadRoomsData();
                break;
            case R.id.tv_reserve:
                submit();
                break;
        }
    }
}

