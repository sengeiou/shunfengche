//package com.windmillsteward.jukutech.activity.home.commons.pay;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.windmillsteward.jukutech.R;
//import com.windmillsteward.jukutech.activity.BillUtils;
//import com.windmillsteward.jukutech.base.BaseActivity;
//import com.windmillsteward.jukutech.bean.PayBeforeResultBean;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
///**
// * 描述：这个界面是发布，察看联系方式的支付的界面
// * 时间：2018/3/4/004
// * 作者：xjh
// */
//public class PayActivity extends BaseActivity implements PayView, View.OnClickListener {
//
//    public static final int REQUEST_CODE = 1024;
//    public static final int RESULT_CODE_SUCCESS = 200;
//    public static final String TYPE = "TYPE";
//    public static final String RELATE_ID = "RELATE_ID";
//
//    private ImageView toolbar_iv_back;
//    private TextView toolbar_iv_title;
//    private ImageView toolbar_iv_right;
//    private TextView toolbar_tv_right;
//    private TextView tv_order_price;
//    private TextView tv_order_sn;
//    private TextView tv_order_name;
//    private TextView tv_coupon_receive_id;
//    private ImageView iv_choice_zfb;
//    private TextView tv_zfb_price;
//    private ImageView iv_choice_wx;
//    private TextView tv_wx_price;
//    private TextView tv_sure;
//
//    private PayPresenter presenter;
//    private int type;
//    private int relate_id;
//    private int pay_type=2;
//    // 优惠券id
//    private int receive_id=0;
//
//    private PayBeforeResultBean bean;
//
//    private ProgressDialog dialog;
//
//    /**
//     * @param activity  activity
//     * @param type      【下单的类型，1.用户注册，
//     *                  21.人才驿站发布求职，22.人才驿站发布招聘，23.人才驿站职位联系，24.简历联系，
//     *                  31.思想智库发布需求，32.思想智库联系费用，
//     *                  43.智慧家庭联系费用，
//     *                  51.发布卖房，52.发布买房，53.（出售）联系，54.（求购）联系，55.发布出租，56.发布求租，57.（租房）联系，58.（求租）联系，
//     *                  64.旅游联系】
//     *                  70.发布保险信息，71.联系保险
//     *
//     * @param relate_id 关联的详情的id（如果是联系费用，为相关详情的id，如：联系职位，则relate_id为职位job_id，不是联系则传0或不传该参数）
//     */
//    public static void go(Activity activity, int type, int relate_id) {
//        Intent intent = new Intent(activity, PayActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putInt(TYPE, type);
//        bundle.putInt(RELATE_ID, relate_id);
//        intent.putExtras(bundle);
//        activity.startActivityForResult(intent, REQUEST_CODE);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pay);
//
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            type = extras.getInt(TYPE);
//            relate_id = extras.getInt(RELATE_ID, 0);
//        }
//
//        initView();
//        initToolbar();
//        dialog = new ProgressDialog(this);
//        presenter = new PayPresenter(this);
//        if (type == 1){//用户注册access_token传空即可
//            presenter.payBrfore("", type, 0, "", relate_id);
//        }else{
//            presenter.payBrfore(getAccessToken(), type, 0, "", relate_id);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode==100 && resultCode==200) {
//            Bundle extras = data.getExtras();
//            if (extras!=null) {
//                receive_id = extras.getInt(ChoiceCouponListActivity.SELECT_ID);
//                tv_coupon_receive_id.setText((receive_id==0)?"不使用":"已选择优惠券");
//                presenter.payBrfore(getAccessToken(), type, receive_id, tv_order_sn.getText().toString().trim(), relate_id);
//            }
//        }
//    }
//
//    private void initToolbar() {
//        handleBackEvent(toolbar_iv_back);
//        toolbar_iv_title.setText("收银台");
//    }
//
//    private void initView() {
//
//        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
//        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
//        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
//        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
//        tv_order_price = (TextView) findViewById(R.id.tv_order_price);
//        tv_order_sn = (TextView) findViewById(R.id.tv_order_sn);
//        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
//        tv_coupon_receive_id = (TextView) findViewById(R.id.tv_coupon_receive_id);
//        iv_choice_zfb = (ImageView) findViewById(R.id.iv_choice_zfb);
//        tv_zfb_price = (TextView) findViewById(R.id.tv_zfb_price);
//        iv_choice_wx = (ImageView) findViewById(R.id.iv_choice_wx);
//        tv_wx_price = (TextView) findViewById(R.id.tv_wx_price);
//        tv_sure = (TextView) findViewById(R.id.tv_sure);
//
//        tv_coupon_receive_id.setOnClickListener(this);
//        iv_choice_zfb.setOnClickListener(this);
//        iv_choice_wx.setOnClickListener(this);
//        tv_sure.setOnClickListener(this);
//
//        String initInfo = BCPay.initWechatPay(this, "ad45d74584ugf41ft6u4ty24w9a1r4b1");
//        if (initInfo != null) {
//            showTips("微信支付不可用", 0);
//            iv_choice_wx.setEnabled(false);
//        }
//    }
//
//    @Override
//    public void payBefore(PayBeforeResultBean bean) {
//        this.bean = bean;
//        tv_order_price.setText("￥" + bean.getOrder_price());
//        tv_order_sn.setText(bean.getOrder_sn());
//        tv_order_name.setText(bean.getOrder_name());
//        tv_zfb_price.setText("￥"+bean.getOrder_price());
//        tv_wx_price.setText("￥"+bean.getOrder_price());
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (bean == null) {
//            return;
//        }
//        switch (v.getId()) {
//            case R.id.tv_coupon_receive_id:
//                Bundle bundle = new Bundle();
//                bundle.putInt(ChoiceCouponListActivity.SELECT_ID,receive_id);
//                bundle.putFloat(ChoiceCouponListActivity.CURR_PRICE, Float.parseFloat(bean.getOrder_price()));
//                startAtvDonFinishForResult(ChoiceCouponListActivity.class,100, bundle);
//                break;
//            case R.id.iv_choice_zfb:
//                pay_type = 1;
//                iv_choice_zfb.setImageResource(R.mipmap.icon_choise);
//                iv_choice_wx.setImageResource(R.mipmap.icon_choise_no);
//                tv_zfb_price.setText("￥"+bean.getOrder_price());
//                tv_wx_price.setText("");
//                break;
//            case R.id.iv_choice_wx:
//                pay_type = 2;
//                iv_choice_zfb.setImageResource(R.mipmap.icon_choise_no);
//                iv_choice_wx.setImageResource(R.mipmap.icon_choise);
//                tv_wx_price.setText("￥"+bean.getOrder_price());
//                tv_zfb_price.setText("");
//                break;
//            case R.id.tv_sure:
//                if (pay_type==1) {
////                    showDialog("");
//                    dialog.show();
//                    Map<String, String> mapOptional = new HashMap<>();
//                    BCPay.PayParams aliParam = new BCPay.PayParams();
//                    aliParam.channelType = BCReqParams.BCChannelTypes.ALI_APP;
//                    aliParam.billTitle = bean.getOrder_name();
//                    aliParam.billTotalFee = (int) (Float.valueOf(bean.getOrder_price()) * 100);
//                    aliParam.billNum = BillUtils.genBillNum();
//                    aliParam.optional = mapOptional;
//                    aliParam.billNum = bean.getOrder_sn();
//
//                    BCPay.getInstance(this).reqPaymentAsync(
//                            aliParam, bcCallback);
//                } else if (pay_type==2){
////                    showDialog("");
//                    dialog.show();
//                    pay_type = 2;
//                    Map<String, String> mapOptiona2 = new HashMap<>();
//                    if (BCPay.isWXAppInstalledAndSupported() &&
//                            BCPay.isWXPaySupported()) {
//
//                        BCPay.PayParams payParams = new BCPay.PayParams();
//                        payParams.channelType = BCReqParams.BCChannelTypes.WX_APP;
//                        payParams.billTitle = bean.getOrder_name();   //订单标题
//                        payParams.billTotalFee = (int) (Float.valueOf(bean.getOrder_price()) * 100);    //订单金额(分)
//                        payParams.billNum = BillUtils.genBillNum();  //订单流水号
//                        payParams.optional = mapOptiona2;            //扩展参数(可以null)
//                        payParams.billNum = bean.getOrder_sn();
//
//                        BCPay.getInstance(this).reqPaymentAsync(
//                                payParams,
//                                bcCallback);            //支付完成后回调入口
//                    } else {
//                        showTips("您尚未安装微信或者安装的微信版本不支持", 0);
//                        dismiss();
//                    }
//                }
//
//                break;
//        }
//    }
//
//    private String toastMsg;
//
//    private Handler mHandler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            switch (msg.what) {
//                case 2:
//                    showTips(toastMsg, 0);
//                    break;
//                case 200:  // 支付成功
//                    showTips(toastMsg, 0);
//                    setResult(RESULT_CODE_SUCCESS);
//                    finish();
//                    break;
//            }
//            return true;
//        }
//    });
//
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
//}
