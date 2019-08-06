package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.presenter.WalletListDetailPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.WalletListDetailBean;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;

/**
 * 描述：钱包明细详情
 * author:cyq
 * 2018-03-29
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class WalletListDetailActivity extends BaseActivity implements WalletListDetailView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView tv_money_tag;
    private TextView tv_money;
    private TextView tv_time;
    private TextView tv_detail_type;
    private TextView tv_title;
    private TextView tv_order_sn;
    private TextView tv_pay_type;
    private TextView tv_balance;

    private WalletListDetailPresenter presenter;



    /**
     * 跳转页面
     * @param detail_id 明细id
     * @param detail_type  明细类型【0.全部明细,1.收入,2.支出,3.退款,4.提现】
     */
    public static void go(Context context, int detail_id, int detail_type){
        Intent intent = new Intent(context,WalletListDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Define.INTENT_DATA,detail_id);
        bundle.putInt(Define.INTENT_DATA_TWO,detail_type);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_list_detail);
        initView();
        initData();
    }

    private void initView() {

        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        tv_money_tag = (TextView) findViewById(R.id.tv_money_tag);
        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_detail_type = (TextView) findViewById(R.id.tv_detail_type);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_order_sn = (TextView) findViewById(R.id.tv_order_sn);
        tv_pay_type = (TextView) findViewById(R.id.tv_pay_type);
        tv_balance = (TextView) findViewById(R.id.tv_balance);

        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("费用明细");
    }

    private void initData(){
        Bundle extras = getIntent().getExtras();
        int detail_id = extras.getInt(Define.INTENT_DATA);
        int detail_type = extras.getInt(Define.INTENT_DATA_TWO);

        presenter = new WalletListDetailPresenter(this);
        presenter.getDetailData(getAccessToken(),detail_id,detail_type);
    }

    @Override
    public void getWalletListDetailSuccess(WalletListDetailBean bean) {
        if (bean ==null){
            return;
        }
        //交易金额
        double price = bean.getPrice();
        tv_money.setText(price+"");
        //收入类型 1收入2提现
        int detail_type = bean.getDetail_type();
        String status_name = bean.getStatus_name();
        tv_detail_type.setText(TextUtils.isEmpty(status_name)?"":status_name);
        int transaction_type = bean.getTransaction_type();
        if (detail_type == 1){//
            if (transaction_type == 1){//其它模块的类型：1.收入，2.支出，3.退款
                tv_money_tag.setText("入账金额");
            }else if (transaction_type == 2){
                tv_money_tag.setText("支出金额");
            }else{
                tv_money_tag.setText("退款金额");
            }
        }else if (detail_type == 2){
            tv_detail_type.setText("提现");
            tv_money_tag.setText("出账金额");
        }
        //交易时间
        int add_time = bean.getAdd_time();
        tv_time.setText(DateUtil.StampTimeToDate(add_time+"","yyyy-MM-dd HH:mm"));
        //名称
        String title = bean.getTitle();
        tv_title.setText(TextUtils.isEmpty(title)?"":title);
        //交易单号
        String order_sn = bean.getOrder_sn();
        tv_order_sn.setText(TextUtils.isEmpty(order_sn)?"":order_sn);
        //交易方式
        String payment_name = bean.getPayment_name();
        tv_pay_type.setText(TextUtils.isEmpty(payment_name)?"":payment_name);
        //账户余额
        double current_fee = bean.getCurrent();
        tv_balance.setText("¥ "+current_fee);


    }

    @Override
    public void getWalletListDetailFailed(int code, String msg) {

    }
}
